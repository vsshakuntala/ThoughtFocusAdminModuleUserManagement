package com.tf.usermanagement.serviceimpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.tf.usermanagement.dao.UserListDao;
import com.tf.usermanagement.dto.UserListFilterDTO;
import com.tf.usermanagement.dto.UserListResultDto;
import com.tf.usermanagement.service.UserListService;
import com.tf.usermanagement.utils.UserListQueryBuilderForDownload;

/**
 * 
 * @author Rajendra
 *
 */
@Service
public class UserListServiceImpl implements UserListService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserListServiceImpl.class);

	@Autowired
	private UserListDao userListDao;

	@Override
	public String downloadCustomerResult(HttpServletResponse response, String searhfilter) {
		Gson gson = new Gson();
		try {
			if (searhfilter != null) {
				UserListFilterDTO custDto = gson.fromJson(searhfilter, UserListFilterDTO.class);
				LOGGER.info("filterDto:" + custDto.toString());
				if (custDto != null) {
					String query = UserListQueryBuilderForDownload.getUserListQuery(custDto.getDivisions(),
							custDto.getRoles(), custDto.getStatus(), custDto.getFrom_date(), custDto.getTo_date(),
							custDto.getCompanyName(), custDto.getName());
					LOGGER.info("query builder in service:" + query);

					if (query != null) {

						List<UserListResultDto> UserList = userListDao.getAllUsers(query);

						if (UserList != null && !UserList.isEmpty()) {
							downloadUserReport(response, custDto, UserList);
						}
					} else {
						throw new NullPointerException();
					}

				}
			} else {
				throw new NullPointerException();
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return null;
	}

	@SuppressWarnings("resource")
	private void downloadUserReport(HttpServletResponse response, UserListFilterDTO custDto,
			List<UserListResultDto> custList) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("User List");
		int rownum = 0;

		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		Font font = sheet.getWorkbook().createFont();
		font.setBoldweight((short) 1);
		font.setFontHeightInPoints((short) 12);
		cellStyle.setFont(font);
		
		
		//filter criteria
		CellStyle filterStyle = sheet.getWorkbook().createCellStyle();
		Font fonts = sheet.getWorkbook().createFont();
		fonts.setBoldweight((short) 6);
		fonts.setFontHeightInPoints((short) 15);
		filterStyle.setFont(fonts);
		Row firstrow = sheet.createRow(rownum++);

		Cell FilterCell = firstrow.createCell(0);
		FilterCell.setCellStyle(filterStyle);
		FilterCell.setCellValue("Filter Criteria");
		//end of filter criteria

		// Start of create the search header criteria
		Row row = sheet.createRow(rownum++);

		Cell OrganisationsCell = row.createCell(0);
		OrganisationsCell.setCellStyle(cellStyle);
		OrganisationsCell.setCellValue("Organization");

		Cell RolesCell = row.createCell(1);
		RolesCell.setCellStyle(cellStyle);
		RolesCell.setCellValue("Roles");

		Cell StatusCell = row.createCell(2);
		StatusCell.setCellStyle(cellStyle);
		StatusCell.setCellValue("Status #");

		Cell from_DateCell = row.createCell(3);
		from_DateCell.setCellStyle(cellStyle);
		from_DateCell.setCellValue("from_Date");

		Cell To_dateCell = row.createCell(4);
		To_dateCell.setCellStyle(cellStyle);
		To_dateCell.setCellValue("To_date");

		Cell CompanyCell = row.createCell(5);
		CompanyCell.setCellStyle(cellStyle);
		CompanyCell.setCellValue("Company name");

		Cell FirstNameCell = row.createCell(6);
		FirstNameCell.setCellStyle(cellStyle);
		FirstNameCell.setCellValue("First Name");

		// END of create the search header criteria
		
		
		
		// Start of create the search data
		Row nextRow = sheet.createRow(rownum++);
		
		if (custDto.getDivisions() != null) {
			Cell cell1 = nextRow.createCell(0);
			List<Integer> list = new ArrayList<Integer>();
			String ids = custDto.getDivisions();
			String[] getIds = ids.split(",");
			for (String s : getIds) {

				Integer val = Integer.valueOf(s);
				list.add(val);

			}
			List<String> orgNames = userListDao.getOrganizationNameById(list);
			StringBuilder builder = new StringBuilder();
			String separator = "";
			for (String name : orgNames) {

				builder.append(separator + name);
				separator = ",";

			}

			cell1.setCellValue(builder.toString());
		}

		if (custDto.getRoles() != null) {
			Cell cell1 = nextRow.createCell(1);
			cell1.setCellValue(custDto.getRoles());
		}

		if (custDto.getStatus() != null && custDto.getStatus().trim().length() > 0) {
			Cell cell1 = nextRow.createCell(2);
			cell1.setCellValue(custDto.getStatus());
		}

		if (custDto.getFrom_date() != null) {
			Cell cell1 = nextRow.createCell(3);
			cell1.setCellValue(custDto.getFrom_date());
		}

		if (custDto.getTo_date() != null) {
			Cell cell1 = nextRow.createCell(4);
			cell1.setCellValue(custDto.getTo_date());
		}

		if (custDto.getCompanyName() != null && custDto.getCompanyName().trim().length() > 0) {
			Cell cell1 = nextRow.createCell(5);
			cell1.setCellValue(custDto.getCompanyName());
		}

		if (custDto.getName() != null && custDto.getName().trim().length() > 0) {
			Cell cell1 = nextRow.createCell(6);
			cell1.setCellValue(custDto.getName());
		}

		// End of create the search data
		//Filter Result
		Row secondrow = sheet.createRow(rownum++);
		Cell FilterDataCell = secondrow.createCell(0);
		FilterDataCell.setCellStyle(filterStyle);
		FilterDataCell.setCellValue("Filter Result");
		//end of filter result
		// Start of create the Header of table
		Row nextNewRow = sheet.createRow(rownum++);
		Cell UserIdCellData = nextNewRow.createCell(0);
		UserIdCellData.setCellStyle(cellStyle);
		UserIdCellData.setCellValue("User Name ");

		Cell firstNameCellData = nextNewRow.createCell(1);
		firstNameCellData.setCellStyle(cellStyle);
		firstNameCellData.setCellValue("First Name ");
		
		Cell lastNameCellData = nextNewRow.createCell(2);
		lastNameCellData.setCellStyle(cellStyle);
		lastNameCellData.setCellValue("Last Name ");

		Cell FirstNameCellData = nextNewRow.createCell(3);
		FirstNameCellData.setCellStyle(cellStyle);
		FirstNameCellData.setCellValue("Registered Date");

		Cell LastNameCellData = nextNewRow.createCell(4);
		LastNameCellData.setCellStyle(cellStyle);
		LastNameCellData.setCellValue("Phone");

		Cell EmailCellData = nextNewRow.createCell(5);
		EmailCellData.setCellStyle(cellStyle);
		EmailCellData.setCellValue("Company Name");

		Cell RegisteredDateCellData = nextNewRow.createCell(6);
		RegisteredDateCellData.setCellStyle(cellStyle);
		RegisteredDateCellData.setCellValue("Status");

		Cell PhoneCellData = nextNewRow.createCell(7);
		PhoneCellData.setCellStyle(cellStyle);
		PhoneCellData.setCellValue("Approve");

		Cell CompanyCellData = nextNewRow.createCell(8);
		CompanyCellData.setCellStyle(cellStyle);
		CompanyCellData.setCellValue("Pending");

		/*
		 * Cell ApprovedCellData = nextNewRow.createCell(8);
		 * ApprovedCellData.setCellStyle(cellStyle);
		 * ApprovedCellData.setCellValue("Approved");
		 * 
		 * Cell PendingCellData = nextNewRow.createCell(9);
		 * PendingCellData.setCellStyle(cellStyle);
		 * PendingCellData.setCellValue("Pending");
		 */

		// End of create the Header of table

		// Start of insert the data from database
		if (custList != null && !custList.isEmpty()) {

			for (UserListResultDto custDtoObj : custList) {

				Row row1 = sheet.createRow(rownum++);

				Cell UserIdCellData1 = row1.createCell(0);
				if (custDtoObj.getUserName() != null)
					UserIdCellData1.setCellValue(custDtoObj.getUserName());

				Cell firstNameCellData1 = row1.createCell(1);
				if (custDtoObj.getFirstName() != null)
				    firstNameCellData1.setCellValue(custDtoObj.getFirstName());

				Cell lastNameCellData1 = row1.createCell(2);
				if (custDtoObj.getLastName() != null)
				    lastNameCellData1.setCellValue(custDtoObj.getLastName());

				
				Cell FirstNameCellData1 = row1.createCell(3);
				if (custDtoObj.getCreatedDate() != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY");
					FirstNameCellData1.setCellValue(dateFormat.format(custDtoObj.getCreatedDate()));
				}

				Cell LastNameCellData1 = row1.createCell(4);
				if (custDtoObj.getPhone() != null)
					LastNameCellData1.setCellValue(custDtoObj.getPhone());

				Cell EmailCellData1 = row1.createCell(5);
				if (custDtoObj.getCompanyName() != null)
					EmailCellData1.setCellValue(custDtoObj.getCompanyName());

				Cell RegisteredDateCellData1 = row1.createCell(6);
				// if(custDtoObj.getCreatedDate()!=null)
				String status = null;
				if (custDtoObj.getApproved() == 0 && custDtoObj.getPending() == 0) {
					status = "DELETED";
				} else if (custDtoObj.getApproved() > 0 && custDtoObj.getPending() == 0) {
					status = "APPROVED";
				} else {
					status = "PENDING";
				}
				RegisteredDateCellData1.setCellValue(status);

				Cell PhoneCellData1 = row1.createCell(7);
				if (custDtoObj.getApproved() != null)
					PhoneCellData1.setCellValue(custDtoObj.getApproved());

				Cell CompanyCellData1 = row1.createCell(8);
				if (custDtoObj.getPending() != null)
					CompanyCellData1.setCellValue(custDtoObj.getPending());

				/*
				 * Cell ApprovedCellData1 = row1.createCell(8);
				 * if(custDtoObj.getApproved()!=null)
				 * ApprovedCellData1.setCellValue(custDtoObj.getApproved());
				 * 
				 * Cell PendingCellData1 = row1.createCell(9);
				 * if(custDtoObj.getPending()!=null)
				 * PendingCellData1.setCellValue(custDtoObj.getPending());
				 */
			}

		}

		// End of insert the data from database

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", "User Result.xlsx");
		response.setHeader(headerKey, headerValue);
		response.addHeader("x-filename", "User Report.xlsx");
		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

	}

}