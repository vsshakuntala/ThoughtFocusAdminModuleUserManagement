package com.tf.usermanagement.serviceimpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tf.usermanagement.dao.MachineAssignmentDao;
import com.tf.usermanagement.dto.CatalogCountForOrgDto;
import com.tf.usermanagement.dto.MachineAssignmentDto;
import com.tf.usermanagement.dto.MachineDownloadDto;
import com.tf.usermanagement.dto.MachineJsonDto;
import com.tf.usermanagement.dto.RemoveMachineDto;
import com.tf.usermanagement.exceptions.InsufficientDataException;
import com.tf.usermanagement.service.MachineAssignmentService;
import com.tf.usermanagement.utils.MachineAssignmentQueryBuilder;
import com.tf.usermanagement.utils.MachineAssignmentQueryBuilderForDownload;
@Service
public class MachineAssignmentServiceimpl implements MachineAssignmentService{

    @Autowired
    private MachineAssignmentDao machineAssignmentDao;
    
    @Autowired
    private MachineAssignmentQueryBuilder machineAssignmentQueryBuilder;
    
    @Autowired
    private MachineAssignmentQueryBuilderForDownload machineAssignmentQueryBuilderForDownload;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MachineAssignmentServiceimpl.class);

  
    @Override
    public String assignMachine(Long loginUserId,Long userId, Long orgId,String catalogList) throws Exception {
	
	String msg=null;
	
	
	
	try{
		Gson gson = new Gson();
		MachineJsonDto unassignedMAchineList= gson.fromJson(catalogList, MachineJsonDto.class);
	    if(unassignedMAchineList != null){
		    msg = machineAssignmentDao.assignMachine(loginUserId,userId,orgId, unassignedMAchineList.getParams());
		}else{
		    throw new NullPointerException();
		}
	}catch(Exception e){
	    throw new Exception();
	}
	
	
	return msg;
    }

    @Override
    public String removeMachine(Long loginUserId,Long userId, Long orgId,String catalogList) throws Exception {
	String msg=null;
	try{
		Gson gson = new Gson();
		MachineJsonDto assignedMAchineList= gson.fromJson(catalogList, MachineJsonDto.class);
	    if(assignedMAchineList != null){
		    msg = machineAssignmentDao.removeMachine(loginUserId,userId,orgId,assignedMAchineList.getParams());
		}else{
		    throw new NullPointerException();
		}
	}catch(Exception e){
		LOGGER.info("in service remove Exception : "+e.getMessage());
	    throw new Exception();
	}
	LOGGER.info("in service remove machine"+msg);
	return msg;

    }

    /**
     * used to assign all customer for a user
     * input- user id, list of all customers which have to assign for user
     * 
     */
    @Override
    public String assignAllMachine(Long loginUserId,Long userId ,Long orgId,String machine) {
	String msg = null;
	Gson gson = new Gson();
	try{
		RemoveMachineDto removeMachineDto = gson.fromJson(machine, RemoveMachineDto.class);
		MachineAssignmentDto catDto=removeMachineDto.getParams();
		String query =null;
		if(catDto != null){    
			
			query = machineAssignmentQueryBuilder.allMachineAssignToUser(userId,orgId,catDto.getCatalog_id(),catDto.getModel(),
					catDto.getCatalog_reference(),catDto.getCustomer_name(),catDto.getStatus(),catDto.getGroup_name());
			LOGGER.info("query builder in if"+query);
				
		}else{
			 query = machineAssignmentQueryBuilder.allMachineAssignToUser(userId,orgId,null,null,
					 null,null,null,null);
			LOGGER.info("query builder else"+query);
			}
		if(query != null){
		    msg = machineAssignmentDao.assignAllMachine(loginUserId, userId, query, orgId);
		    return msg;
		}else{
		    throw new NullPointerException();
	}
	}catch(InsufficientDataException e){
		throw new InsufficientDataException(e.getMessage());
	    }catch(NullPointerException e){
		throw new NullPointerException();
	    }catch(Exception e){
		LOGGER.error(e.getMessage());		
	    }	
	return null;
	
    }

    
    @Override
    public String removeAllMachine(Long loginUserId,Long userId,Long orgId,String machine) {
	 String msg = null;
	Gson gson = new Gson();
	try{
		RemoveMachineDto removeMachineDto = gson.fromJson(machine, RemoveMachineDto.class);
		MachineAssignmentDto catDto=removeMachineDto.getParams();
		String query =null;
		if(catDto != null){    
			
			query = machineAssignmentQueryBuilder.allMachineRemoveFromUser(userId,orgId,catDto.getCatalog_id(),catDto.getModel(),
					catDto.getCatalog_reference(),catDto.getCustomer_name(),catDto.getStatus(),catDto.getGroup_name());
			LOGGER.info("query builder in if"+query);
			
				
		}else{
			 query = machineAssignmentQueryBuilder.allMachineRemoveFromUser(userId,orgId,null,null,
					 null,null,null,null);
			LOGGER.info("query builder else"+query);
			}
		if(query != null){
		    msg = machineAssignmentDao.removeAllMachine(loginUserId,userId,query);
		    return msg;
		}else{
		    throw new NullPointerException();
		}
		}catch(InsufficientDataException e){
			throw new InsufficientDataException(e.getMessage());
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return msg;
    }

	@Override
	public Object downloadMachineResult(HttpServletResponse response, Long loginUserId, Long userId, Long orgId,
			String searhfilter) {
		Gson gson = new Gson();
		String msg= null;
		
		 if(userId == null || userId < 1){
			    
			    throw new InsufficientDataException("User id is mandatory ");
			}
			
			if(orgId == null || orgId < 1){
			    throw new InsufficientDataException("org Id is mandatory ");
			}
			
			if(loginUserId == null || loginUserId < 1){
			    throw new InsufficientDataException("loginUser Id is mandatory ");
			}
		try{
		    if(searhfilter != null){
		    	MachineAssignmentDto machineDto = gson.fromJson(searhfilter, MachineAssignmentDto.class);		
			if(machineDto != null) {
			    if(machineDto.getStatus() != null && machineDto.getStatus().equalsIgnoreCase("assigned")){
				
				String query = machineAssignmentQueryBuilderForDownload.getAssignedMachineQueryForUser(userId,orgId,machineDto.getCatalog_id(),
						machineDto.getModel(),machineDto.getCatalog_reference(),machineDto.getCustomer_name(),machineDto.getStatus(),machineDto.getGroup_name());
				LOGGER.info("query builder downloadMachineResult:"+query);
				
				if(query != null){
				    List<MachineDownloadDto> machineList = machineAssignmentDao.getAllAssignedMachineList(query);
				    if(machineList != null && !machineList.isEmpty()){
				    	downloadMachineReport(response,machineDto,machineList);
				    }
				}else{
				    throw new NullPointerException();
				}	
			    }else if(machineDto.getStatus() != null && machineDto.getStatus().equalsIgnoreCase("notassigned")){
			    	String query = machineAssignmentQueryBuilderForDownload.getUnAssignedMachineQueryForUser(userId,orgId,machineDto.getCatalog_id(),
							machineDto.getModel(),machineDto.getCatalog_reference(),machineDto.getCustomer_name(),machineDto.getStatus(),machineDto.getGroup_name());
						LOGGER.info("query builder notassigned :"+query);
						
						if(query != null){
						    List<MachineDownloadDto> unAssignedCustList = machineAssignmentDao.getAllUnAssignedMachineList(query);
						    if(unAssignedCustList != null && !unAssignedCustList.isEmpty()){
							downloadMachineReport(response,machineDto,unAssignedCustList);
						    }
						}else{
						    throw new NullPointerException();
						}	    
			    }else{
				
			    }  
			    
			    
			}
		    }else{
		    	throw new NullPointerException();
		    }
		    
		}catch(Exception e){
		    LOGGER.error(e.getMessage());
		}
		
		return null;
	}
	
	
	 @SuppressWarnings("resource")
	private void downloadMachineReport(HttpServletResponse response,
			    MachineAssignmentDto machineDto, List<MachineDownloadDto> machineList) {

			   XSSFWorkbook workbook = new XSSFWorkbook();
			   XSSFSheet sheet = workbook.createSheet("Machine Assignemnt");
			   int rownum = 0;   
			        
			        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
			        Font font = sheet.getWorkbook().createFont();
			        font.setBoldweight((short)1);
			        font.setFontHeightInPoints((short) 12);
			        cellStyle.setFont(font);
			        CellStyle filterStyle = sheet.getWorkbook().createCellStyle();
					Font fonts = sheet.getWorkbook().createFont();
					fonts.setBoldweight((short) 6);
					fonts.setFontHeightInPoints((short) 15);
					filterStyle.setFont(fonts);

					//filter criteria
					Row firstrow = sheet.createRow(rownum++);
					Cell FilterCell = firstrow.createCell(0);
					FilterCell.setCellStyle(filterStyle);
					FilterCell.setCellValue("Filter Criteria");
					//end of filter criteria

					//Start of create the search header criteria
			        Row row = sheet.createRow(rownum++);
			        Cell customeNameCell = row.createCell(0);				     
			        customeNameCell.setCellStyle(cellStyle);
			        customeNameCell.setCellValue("Serial #");
			        Cell customerNumberCell = row.createCell(1);
			        customerNumberCell.setCellStyle(cellStyle);
			        customerNumberCell.setCellValue("Model");
			        Cell billToCell = row.createCell(2);
			        billToCell.setCellStyle(cellStyle);
			        billToCell.setCellValue("Machine Reference");
			        Cell addressCell = row.createCell(3);
			        addressCell.setCellStyle(cellStyle);
			        addressCell.setCellValue("Customer Name");
			        Cell cityCell = row.createCell(4);
			        cityCell.setCellStyle(cellStyle);
			        cityCell.setCellValue("Status");
			        
			        Cell stateCell = row.createCell(5);
			        stateCell.setCellStyle(cellStyle);
			        stateCell.setCellValue("Group Name");
			        
			      //END of create the search header criteria
			        
			        //Start of create the search data
			        
			           Row nextRow = sheet.createRow(rownum++);
			        if(machineDto.getCatalog_id()!=null )
			        {	            
			            Cell cell1 = nextRow.createCell(0);
			            cell1.setCellValue(machineDto.getCatalog_id());				            
			        }
			        
			        if(machineDto.getModel() != null && machineDto.getModel().trim().length() > 0)
			        {	            
			            Cell cell1 = nextRow.createCell(1);
			            cell1.setCellValue(machineDto.getModel());				            
			        }
			        
			        if(machineDto.getCatalog_reference() != null && machineDto.getCatalog_reference().trim().length() > 0)
			        {	            
			            Cell cell1 = nextRow.createCell(2);
			            cell1.setCellValue(machineDto.getCatalog_reference());				            
			        }
			        
			        if(machineDto.getCustomer_name() != null && machineDto.getCustomer_name().trim().length() > 0)
			        {	            
			            Cell cell1 = nextRow.createCell(3);
			            cell1.setCellValue(machineDto.getCustomer_name());				            
			        }
			        
			        if(machineDto.getStatus() != null && machineDto.getStatus().trim().length() > 0)
			        {	            
			            Cell cell1 = nextRow.createCell(4);
			            cell1.setCellValue(machineDto.getStatus());				            
			        }
			        
			       
			        
			        if(machineDto.getGroup_name() != null && machineDto.getGroup_name().trim().length() > 0)
			        {	            
			            Cell cell1 = nextRow.createCell(5);
			            cell1.setCellValue(machineDto.getGroup_name());				            
			        }
			        
			      //End of create the search data

			        //start filter result
					Row secondrow = sheet.createRow(rownum++);
					Cell FilterDataCell = secondrow.createCell(0);
					FilterDataCell.setCellStyle(filterStyle);
					FilterDataCell.setCellValue("Filter Result");
					//end of filter result

					//Start of create the Header of table
			        Row nextNewRow = sheet.createRow(rownum++);				        
			     
			        Cell customerNumberCellData = nextNewRow.createCell(0);
			        customerNumberCellData.setCellStyle(cellStyle);
			        customerNumberCellData.setCellValue("Serial #");
			        
			        Cell customeNameCellData = nextNewRow.createCell(1);				     
			        customeNameCellData.setCellStyle(cellStyle);
			        customeNameCellData.setCellValue("Model");				     
			        
			        Cell addressCellData = nextNewRow.createCell(2);
			        addressCellData.setCellStyle(cellStyle);
			        addressCellData.setCellValue("Machine Reference");
			        
			        Cell cityCellData = nextNewRow.createCell(3);
			        cityCellData.setCellStyle(cellStyle);
			        cityCellData.setCellValue("Customer Name");
			        
//			        Cell stateCellData = nextNewRow.createCell(4);
//			        stateCellData.setCellStyle(cellStyle);
//			        stateCellData.setCellValue("Status");				        
			        
			        Cell postalCellData = nextNewRow.createCell(4);
			        postalCellData.setCellStyle(cellStyle);
			        postalCellData.setCellValue("Group Name");
			     
			        //End of create the Header of table
			      
			        
			        //Start of insert the data from database
			       if(machineList != null && !machineList.isEmpty()){
				   
				   for(MachineDownloadDto machineDtoObj:machineList){
				       Row row1 = sheet.createRow(rownum++);
				       
				       Cell customerNumberCellData1 = row1.createCell(0);
				        customerNumberCellData1.setCellValue(machineDtoObj.getCatalog_name());
				        
				        Cell customeNameCellData1 = row1.createCell(1);				     
				        customeNameCellData1.setCellValue(machineDtoObj.getModel());				     
				        
				        Cell addressCellData1 = row1.createCell(2);
				        addressCellData1.setCellValue(machineDtoObj.getCatalog_reference());
				        
				        Cell cityCellData1 = row1.createCell(3);
				        cityCellData1.setCellValue(machineDtoObj.getCustomer_name());
				        
//				        Cell stateCellData1 = row1.createCell(4);
//				        stateCellData1.setCellValue(machineDtoObj.getStatus());				        
				        
				        Cell postalCellData1 = row1.createCell(4);
				        postalCellData1.setCellValue(machineDtoObj.getGroup_name());	
				   }
				   
			       }
			       //End of insert the data from database
			    
			        response.setCharacterEncoding("UTF-8");
			        response.setContentType("application/vnd.ms-excel");
				// set headers for the response
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", "Machine Result.xlsx");
				response.setHeader(headerKey, headerValue); 
				response.addHeader("x-filename", "Machine Report.xlsx");
			        try {
				    workbook.write(response.getOutputStream());
				} catch (IOException e) {
				   LOGGER.error(e.getMessage());
				} 

		    }

	@Override
	public CatalogCountForOrgDto getCatalogsCountForOrganization(Long usrId,Long orgId) {
		return machineAssignmentDao.getCatalogsCountForOrganization(usrId,orgId);
	}

	@Override
	public Long getCatalogAssignedCount(Long orgId, Long userId) {
		return machineAssignmentDao.getCatalogAssignedCount(userId, orgId);
	} 
}
