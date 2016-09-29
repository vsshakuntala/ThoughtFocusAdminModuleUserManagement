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
@RequestMapping(value="/userlists")
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
	
	@RequestMapping(value="/getfiltereduserlist",method=RequestMethod.GET)
	public DataTablesOutput getFiltereduser(UserFilterReport.UserFilterReportDtInput filterData) {
		LOGGER.info("User report input :{}",filterData.getRoles());
		DataTablesOutput obj=userFilterReport.fetchData(filterData);
		return obj;
	}
	
	@RequestMapping(value="/getalldivisions",method=RequestMethod.GET)
	public List<DivisionDto> getDivisions(){
		List<DivisionDto> orgIdNameList = divisionService.getAllDivision();
		return orgIdNameList;
	}
	
	@RequestMapping(value ="/getallroles" , method = RequestMethod.GET)
	public List<RoleDto> getRoles() {
		List<RoleDto> rolIdNameList = roleService.getRoles();
		return rolIdNameList;
	}
	
	@RequestMapping(value = "/downloaddocument/",method=RequestMethod.POST)
    public ResponseEntity<?> downloadDocument(HttpServletRequest request,
	    HttpServletResponse response,@RequestBody String searhfilter) {
	try {
	    LOGGER.info("in download ");
	    LOGGER.info("search inputs"+searhfilter);
	    return new ResponseEntity<>(userListService.downloadCustomerResult(response,searhfilter),HttpStatus.OK);
	} catch (Exception e) {
	    LOGGER.error("error while downloading the fiel"+e.getMessage());
	    return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.BAD_REQUEST);
	}

    }
}
