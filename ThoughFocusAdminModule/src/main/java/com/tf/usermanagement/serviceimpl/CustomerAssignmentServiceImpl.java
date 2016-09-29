package com.tf.usermanagement.serviceimpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.tf.usermanagement.dao.CustomerAssignmentDao;
import com.tf.usermanagement.dto.CustomerAssignmentDto;
import com.tf.usermanagement.dto.CustomerCount;
import com.tf.usermanagement.dto.CustomerDownloadDto;
import com.tf.usermanagement.dto.CustomerListDto;
import com.tf.usermanagement.exceptions.InsufficientDataException;
import com.tf.usermanagement.service.CustomerAssignmentService;
import com.tf.usermanagement.utils.CustomerAssignmentQueryBuilder;
import com.tf.usermanagement.utils.CustomerAssignmentQueryBuilderForDownload;

/**
 * 
 * @author Santosh
 *
 */
@Service
public class CustomerAssignmentServiceImpl implements CustomerAssignmentService {

	private static final String USERIDMANDATORY = "User id is mandatory ";
	private static final String ORGIDMANDATORY = "org Id is mandatory ";

	private static final String LOGINUSERIDMANDATORY = "loginUser Id is mandatory ";

	private static final String QUERYBUILDER = "query builder ";

	@Autowired
	private CustomerAssignmentDao customerAssignmentDao;

	@Autowired
	private CustomerAssignmentQueryBuilder customerAssignmentQueryBuilder;

	@Autowired
	private CustomerAssignmentQueryBuilderForDownload customerAssignmentQueryBuilderForDownload;

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAssignmentServiceImpl.class);

	/**
	 * used to assign the customer for a user input- user id, list of customers
	 * which have to assign for user
	 * 
	 * @throws Exception
	 * 
	 */
	@Override
	public String assignCustomer(Long loginUserId, Long userId, Long orgId, String jsonData) throws Exception {
		String msg = null;
		Gson gson = new Gson();

		CustomerListDto customerList = gson.fromJson(jsonData, CustomerListDto.class);
		if (userId == null || userId < 1) {

			throw new InsufficientDataException(USERIDMANDATORY);
		}

		if (orgId == null || orgId < 1) {
			throw new InsufficientDataException(ORGIDMANDATORY);
		}

		if (loginUserId == null || loginUserId < 1) {
			throw new InsufficientDataException(LOGINUSERIDMANDATORY);
		}

		if (customerList != null && !customerList.getCustList().isEmpty()) {
			msg = customerAssignmentDao.assignCustomer(loginUserId, userId, customerList.getCustList());
		} else {
			throw new NullPointerException();
		}

		return msg;
	}

	/**
	 * used to remove the customer for a user input- user id, list of customers
	 * which have to remove for user
	 * 
	 * @throws Exception
	 * 
	 */
	@Override
	public String removeCustomer(Long loginUserId, Long userId, Long orgId, String jsonData) throws Exception {
		String msg = null;
		Gson gson = new Gson();

		CustomerListDto customerList = gson.fromJson(jsonData, CustomerListDto.class);

		if (userId == null || userId < 1) {

			throw new InsufficientDataException(USERIDMANDATORY);
		}

		if (orgId == null || orgId < 1) {
			throw new InsufficientDataException(ORGIDMANDATORY);
		}

		if (loginUserId == null || loginUserId < 1) {
			throw new InsufficientDataException(LOGINUSERIDMANDATORY);
		}

		if (customerList != null && !customerList.getCustList().isEmpty()) {
			msg = customerAssignmentDao.removeCustomer(loginUserId, userId, customerList.getCustList());
		} else {
			throw new NullPointerException();
		}

		return msg;
	}

	/**
	 * used to assign all customer for a user input- user id, list of all
	 * customers which have to assign for user
	 * 
	 */
	@Override
	public String assignAllCustomer(Long loginUserId, Long userId, Long orgId, String customer) {

		String msg = null;
		Gson gson = new Gson();

		if (userId == null || userId < 1) {

			throw new InsufficientDataException(USERIDMANDATORY);
		}

		if (orgId == null || orgId < 1) {
			throw new InsufficientDataException(ORGIDMANDATORY);
		}

		if (loginUserId == null || loginUserId < 1) {
			throw new InsufficientDataException(LOGINUSERIDMANDATORY);
		}

		
			if (customer != null) {
				CustomerAssignmentDto custDto = gson.fromJson(customer, CustomerAssignmentDto.class);
				if (custDto != null) {

					String query = customerAssignmentQueryBuilder.allCustomerAssignToUser(userId, orgId,
							custDto.getCustomername(), custDto.getCustomernum(), custDto.getBilltonum(),
							custDto.getAddressone(), custDto.getCity(), custDto.getState(), custDto.getPostal(),
							custDto.getStatus());
					LOGGER.info(QUERYBUILDER + query);
					List<Long> custIdList = customerAssignmentDao.getUnassignedList(query);
					if (custIdList != null && !custIdList.isEmpty()) {
						msg = customerAssignmentDao.assignAllCustomer(loginUserId, userId, query, custIdList);
						return msg;
					} else {
						throw new NullPointerException("error while fetching the unassigned list");
					}
				} else {
					throw new NullPointerException();
				}
			} else {
				throw new NullPointerException();
			}
		

	}

	/**
	 * used to remove all customer for a user input- user id, list of all
	 * customers which have to remove for user
	 * 
	 */
	@Override
	public String removeAllCustomer(Long loginUserId, Long userId, Long orgId, String customer) {
		String msg = null;
		Gson gson = new Gson();

		if (userId == null || userId < 1) {

			throw new InsufficientDataException(USERIDMANDATORY);
		}

		if (orgId == null || orgId < 1) {
			throw new InsufficientDataException(ORGIDMANDATORY);
		}

		if (loginUserId == null || loginUserId < 1) {
			throw new InsufficientDataException(LOGINUSERIDMANDATORY);
		}

	
			if (customer != null) {
				CustomerAssignmentDto custDto = gson.fromJson(customer, CustomerAssignmentDto.class);
				if (custDto != null) {

					String query = customerAssignmentQueryBuilder.allCustomerRemoveFromUser(userId, orgId,
							custDto.getCustomername(), custDto.getCustomernum(), custDto.getBilltonum(),
							custDto.getAddressone(), custDto.getCity(), custDto.getState(), custDto.getPostal(),
							custDto.getStatus());
					LOGGER.info(QUERYBUILDER + query);
					if (query != null) {
						msg = customerAssignmentDao.removeAllCustomer(loginUserId, userId, query);
						return msg;
					} else {
						throw new NullPointerException();
					}
				} else {
					throw new NullPointerException();
				}
			} else {
				throw new NullPointerException("status should not be null");
			}
		
	}

	/**
	 * This method is used to download the customer report based on filter
	 * criteria
	 */
	@Override
	public String downloadCustomerResult(HttpServletResponse response, Long loginUserId, Long userId, Long orgId,
			String searhfilter) {
		Gson gson = new Gson();

		if (userId == null || userId < 1) {

			throw new InsufficientDataException(USERIDMANDATORY);
		}

		if (orgId == null || orgId < 1) {
			throw new InsufficientDataException(ORGIDMANDATORY);
		}

		if (loginUserId == null || loginUserId < 1) {
			throw new InsufficientDataException(LOGINUSERIDMANDATORY);
		}
		

			if (searhfilter != null) {
				CustomerAssignmentDto custDto = gson.fromJson(searhfilter, CustomerAssignmentDto.class);
				if (custDto != null) {
					if (custDto.getStatus() != null && custDto.getStatus().equalsIgnoreCase("assigned")) {

						String query = customerAssignmentQueryBuilderForDownload.allCustomerRemoveFromUser(userId,
								orgId, custDto.getCustomername(), custDto.getCustomernum(), custDto.getBilltonum(),
								custDto.getAddressone(), custDto.getCity(), custDto.getState(), custDto.getPostal(),
								custDto.getStatus());
						LOGGER.info(QUERYBUILDER + query);

						if (query != null) {
							List<CustomerDownloadDto> custList = new ArrayList<CustomerDownloadDto>();
							custList = customerAssignmentDao.getAllCustomer(query);

							if (custList != null && !custList.isEmpty()) {
								downloadCustomerReport(response, custDto, custList, "customer_assignmet_");
							}
						} else {
							throw new NullPointerException();
						}
					} else if (custDto.getStatus() != null && custDto.getStatus().equalsIgnoreCase("notassigned")) {

						String query = customerAssignmentQueryBuilderForDownload.allCustomerAssignToUser(userId, orgId,
								custDto.getCustomername(), custDto.getCustomernum(), custDto.getBilltonum(),
								custDto.getAddressone(), custDto.getCity(), custDto.getState(), custDto.getPostal(),
								custDto.getStatus());
						LOGGER.info(QUERYBUILDER + query);

						if (query != null) {
							List<CustomerDownloadDto> unAssignedCustList = new ArrayList<CustomerDownloadDto>();
							unAssignedCustList = customerAssignmentDao.getAllCustomer(query);

							if (unAssignedCustList != null && !unAssignedCustList.isEmpty()) {
								downloadCustomerReport(response, custDto, unAssignedCustList, "customer_unassignmet_");
							}
						} else {
							throw new NullPointerException();
						}

					} else {

						String query = customerAssignmentQueryBuilderForDownload.allCustomerRemoveFromUser(userId,
								orgId, custDto.getCustomername(), custDto.getCustomernum(), custDto.getBilltonum(),
								custDto.getAddressone(), custDto.getCity(), custDto.getState(), custDto.getPostal(),
								custDto.getStatus());
						LOGGER.info(QUERYBUILDER + query);

						if (query != null) {
							List<CustomerDownloadDto> custList = new ArrayList<CustomerDownloadDto>();
							custList = customerAssignmentDao.getAllCustomer(query);

							if (custList != null && !custList.isEmpty()) {
								downloadCustomerReport(response, custDto, custList, "customer_assignmet_");
							}
						} else {
							throw new NullPointerException();
						}

					}

				}
			} else {
				throw new NullPointerException();
			}

		
		return null;
	}

	/**
	 * use to write the data into excel format
	 * 
	 * @param response
	 * @param custDto
	 * @param custList
	 */
	@SuppressWarnings("resource")
	private void downloadCustomerReport(HttpServletResponse response, CustomerAssignmentDto custDto,
			List<CustomerDownloadDto> custList, String fileName) {

		// (1) get today's date
		Date today = Calendar.getInstance().getTime();

		// (2) create a date "formatter" (the date format we want)
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-hh:mm:ss");

		// (3) create a new String using the date format we want
		String folderName = formatter.format(today);
		String fName = fileName + folderName + ".xlsx";

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Customer Assignemnt");
		int rownum = 0;

		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		Font font = sheet.getWorkbook().createFont();
		font.setBoldweight((short) 1);
		font.setFontHeightInPoints((short) 12);
		cellStyle.setFont(font);

		// Start of create the search header criteria
		Row row = sheet.createRow(rownum++);

		Cell customeNameCell = row.createCell(0);
		customeNameCell.setCellStyle(cellStyle);
		customeNameCell.setCellValue("Customer Name");

		Cell customerNumberCell = row.createCell(1);
		customerNumberCell.setCellStyle(cellStyle);
		customerNumberCell.setCellValue("Customer #");

		Cell billToCell = row.createCell(2);
		billToCell.setCellStyle(cellStyle);
		billToCell.setCellValue("Bill to #");

		Cell addressCell = row.createCell(3);
		addressCell.setCellStyle(cellStyle);
		addressCell.setCellValue("Address 1");

		Cell cityCell = row.createCell(4);
		cityCell.setCellStyle(cellStyle);
		cityCell.setCellValue("City");

		Cell stateCell = row.createCell(5);
		stateCell.setCellStyle(cellStyle);
		stateCell.setCellValue("State");

		Cell postalCell = row.createCell(6);
		postalCell.setCellStyle(cellStyle);
		postalCell.setCellValue("Postal");

		Cell statusCell = row.createCell(7);
		statusCell.setCellStyle(cellStyle);
		statusCell.setCellValue("Status");

		// END of create the search header criteria

		// Start of create the search data
		Row nextRow = sheet.createRow(rownum++);

		if (custDto.getCustomername() != null && custDto.getCustomername().trim().length() > 0) {
			Cell cell1 = nextRow.createCell(0);
			cell1.setCellValue(custDto.getCustomername());
		}

		if (custDto.getCustomernum() != null && custDto.getCustomernum().trim().length() > 0) {
			Cell cell1 = nextRow.createCell(1);
			cell1.setCellValue(custDto.getCustomernum());
		}

		if (custDto.getBilltonum() != null && custDto.getBilltonum().trim().length() > 0) {
			Cell cell1 = nextRow.createCell(2);
			cell1.setCellValue(custDto.getBilltonum());
		}

		if (custDto.getAddressone() != null && custDto.getAddressone().trim().length() > 0) {
			Cell cell1 = nextRow.createCell(3);
			cell1.setCellValue(custDto.getAddressone());
		}

		if (custDto.getCity() != null && custDto.getCity().trim().length() > 0) {
			Cell cell1 = nextRow.createCell(4);
			cell1.setCellValue(custDto.getCity());
		}

		if (custDto.getState() != null && custDto.getState().trim().length() > 0) {
			Cell cell1 = nextRow.createCell(5);
			cell1.setCellValue(custDto.getState());
		}

		if (custDto.getPostal() != null && custDto.getPostal().trim().length() > 0) {
			Cell cell1 = nextRow.createCell(6);
			cell1.setCellValue(custDto.getPostal());
		}

		if (custDto.getStatus() != null && custDto.getStatus().trim().length() > 0) {
			Cell cell1 = nextRow.createCell(7);
			cell1.setCellValue(custDto.getStatus());
		}
		// End of create the search data

		// Start of create the Header of table
		Row nextNewRow = sheet.createRow(rownum++);

		Cell customerNumberCellData = nextNewRow.createCell(0);
		customerNumberCellData.setCellStyle(cellStyle);
		customerNumberCellData.setCellValue("Customer #");

		Cell customeNameCellData = nextNewRow.createCell(1);
		customeNameCellData.setCellStyle(cellStyle);
		customeNameCellData.setCellValue("Name");

		Cell addressCellData = nextNewRow.createCell(2);
		addressCellData.setCellStyle(cellStyle);
		addressCellData.setCellValue("Address 1");

		Cell cityCellData = nextNewRow.createCell(3);
		cityCellData.setCellStyle(cellStyle);
		cityCellData.setCellValue("City");

		Cell stateCellData = nextNewRow.createCell(4);
		stateCellData.setCellStyle(cellStyle);
		stateCellData.setCellValue("State");

		Cell postalCellData = nextNewRow.createCell(5);
		postalCellData.setCellStyle(cellStyle);
		postalCellData.setCellValue("Postal Code");

		Cell countryCellData = nextNewRow.createCell(6);
		countryCellData.setCellStyle(cellStyle);
		countryCellData.setCellValue("Country");

		/*
		 * Cell statusCellData = nextNewRow.createCell(7);
		 * statusCellData.setCellStyle(cellStyle);
		 * statusCellData.setCellValue("Status");
		 */

		Cell typeCellData = nextNewRow.createCell(7);
		typeCellData.setCellStyle(cellStyle);
		typeCellData.setCellValue("Group Name");

		// End of create the Header of table

		// Start of insert the data from database
		if (custList != null && !custList.isEmpty()) {

			for (CustomerDownloadDto custDtoObj : custList) {
				Row row1 = sheet.createRow(rownum++);
				Cell customerNumberCellData1 = row1.createCell(0);
				customerNumberCellData1.setCellValue(custDtoObj.getCustomerNum());

				Cell customeNameCellData1 = row1.createCell(1);
				customeNameCellData1.setCellValue(custDtoObj.getName());

				Cell addressCellData1 = row1.createCell(2);
				addressCellData1.setCellValue(custDtoObj.getAddress1());

				Cell cityCellData1 = row1.createCell(3);
				cityCellData1.setCellValue(custDtoObj.getCity());

				Cell stateCellData1 = row1.createCell(4);
				stateCellData1.setCellValue(custDtoObj.getState());

				Cell postalCellData1 = row1.createCell(5);
				postalCellData1.setCellValue(custDtoObj.getPostal());

				Cell countryCellData1 = row1.createCell(6);
				countryCellData1.setCellValue(custDtoObj.getCountry());

				/*
				 * Cell statusCellData1 = row1.createCell(7);
				 * statusCellData1.setCellValue(custDtoObj.getStatus());
				 */

				Cell typeCellData1 = row1.createCell(7);
				typeCellData1.setCellValue(custDtoObj.getType());
			}

		}
		// End of insert the data from database

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", "Customer Result.xlsx");
		response.setHeader(headerKey, headerValue);
		response.addHeader("x-filename", fName);
		try {
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
		}

	}

	/**
	 * used to get the number of available customer and assigned customer for a
	 * division
	 */
	@Override
	public CustomerCount getavailableCustomerCount(Long userId, Long orgId) {
		List<CustomerCount> customerCount = customerAssignmentDao.getavailableCustomerCount(userId, orgId);
		LOGGER.info("customerCount " + customerCount);
		if (customerCount != null && !customerCount.isEmpty() && customerCount.size() > 0) {
			return customerCount.get(0);
		} else {

			return null;
		}
	}

	/**
	 * used to get the number of assigned customer for a division
	 */
	@Override
	public CustomerCount getAssignedCustomerCount(Long userId, Long orgId) {
		List<CustomerCount> customerCount = customerAssignmentDao.getAssignedCustomerCount(userId, orgId);
		LOGGER.info("customerCount " + customerCount);
		if (customerCount != null && !customerCount.isEmpty() && customerCount.size() > 0) {
			return customerCount.get(0);
		} else {

			return null;
		}
	}

}
