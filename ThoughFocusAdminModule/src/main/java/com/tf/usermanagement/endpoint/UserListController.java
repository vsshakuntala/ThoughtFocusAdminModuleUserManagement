package com.tf.usermanagement.endpoint;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spaneos.dtssp.output.DataTablesOutput;
import com.tf.usermanagement.dto.DivisionDto;
import com.tf.usermanagement.dto.ResponseMessage;
import com.tf.usermanagement.dto.RoleDto;
import com.tf.usermanagement.report.UserFilterReport;
import com.tf.usermanagement.service.DivisionService;
import com.tf.usermanagement.service.RoleService;
import com.tf.usermanagement.service.UserListService;

@RestController
@RequestMapping(value = "/userlists")
public class UserListController {

	@Autowired
	private UserFilterReport userFilterReport;
	@Autowired
	private DivisionService divisionService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserListService userListService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserListController.class);

	@RequestMapping(value = "/getfiltereduserlist", method = RequestMethod.GET)
	public DataTablesOutput getFiltereduser(UserFilterReport.UserFilterReportDtInput filterData) {
		LOGGER.info("User report input :{}", filterData.getRoles());
		LOGGER.debug("start of calling getfiltereduserlist api");
		DataTablesOutput obj = userFilterReport.fetchData(filterData);
		LOGGER.debug("end of calling getfiltereduserlist api");
		return obj;
	}

	@RequestMapping(value = "/getalldivisions", method = RequestMethod.GET)
	public List<DivisionDto> getDivisions() {
		LOGGER.debug("start of calling getalldivisions api");
		List<DivisionDto> orgIdNameList = divisionService.getAllDivision();
		LOGGER.debug("end of calling getalldivisions api");
		return orgIdNameList;
	}

	@RequestMapping(value = "/getallroles", method = RequestMethod.GET)
	public List<RoleDto> getRoles() {
		LOGGER.debug("start of calling getallroles api");
		List<RoleDto> rolIdNameList = roleService.getRoles();
		LOGGER.debug("end of calling getallroles api");
		return rolIdNameList;
	}

	@RequestMapping(value = "/downloaddocument/", method = RequestMethod.POST)
	public ResponseEntity<?> downloadDocument(HttpServletRequest request, HttpServletResponse response,
			@RequestBody String searhfilter) {
		try {
			LOGGER.info("in download ");
			LOGGER.info("search inputs" + searhfilter);
			LOGGER.debug("start of calling downloaddocument api");
			return new ResponseEntity<>(userListService.downloadCustomerResult(response, searhfilter), HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("error while downloading the fiel" + e.getMessage());
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		}

	}
}
