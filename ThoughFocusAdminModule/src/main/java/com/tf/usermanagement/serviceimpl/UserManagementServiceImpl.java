package com.tf.usermanagement.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tf.usermanagement.dao.UserManagementDao;
import com.tf.usermanagement.daoimpl.DivisionMgmtDaoImpl;
import com.tf.usermanagement.dto.AdminOrgListDto;
import com.tf.usermanagement.dto.CreateUserWebServiceRequestModel;
import com.tf.usermanagement.dto.CreateUserWebServiceResponseModel;
import com.tf.usermanagement.dto.DivisionsDTO;
import com.tf.usermanagement.dto.LanguageDTO;
import com.tf.usermanagement.dto.OrganizationsDTO;
import com.tf.usermanagement.dto.UserDTO;
import com.tf.usermanagement.dto.UserEmail;
import com.tf.usermanagement.dto.UserNotesDto;
import com.tf.usermanagement.dto.UserOrgActiveDTO;
import com.tf.usermanagement.service.UserManagementService;
import com.tf.usermanagement.utils.EmailUtility;
import com.tf.usermanagement.utils.PostToServer;

/**
 * @author Narasingha
 *
 */
@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserManagementDao userManagementDao;
	
	@Autowired
	private DivisionMgmtDaoImpl divisionMgmtDaoImpl;
	
	@Autowired
	private EmailUtility emailUtility;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementServiceImpl.class);
	
	@Value("${user.create}")
	private String OAUTH_USER_CREATE_API;
	@Value("${user.delete}")
	private String OAUTH_USER_DELETE_API;
	@Value("${registerUserSyncExternalSystem}")
	private boolean OAUTH_IS_CALLABLE;
	@Value("${authenticationTokenForUserApproval}")
	private String OAUTH_TOKEN;
	@Value("${default.password}")
	private String DEFAULT_PASSWORD;

	@Override
	public List<OrganizationsDTO> getDivisions() {
		List<OrganizationsDTO> orgIdNameList = userManagementDao.getOrgNameAndId();
		LOGGER.info("Organization list size :{}", orgIdNameList.size());
		return orgIdNameList;
	}

	@Override
	public List<LanguageDTO> getLanguages() {
		List<LanguageDTO> langNameList=userManagementDao.getLanguages();
		LOGGER.info("Lanagues list size :{}",langNameList.size());
		return langNameList;
	}

	@Override
	public List<OrganizationsDTO> getAssignedOrganization(long adminId){
		return userManagementDao.getAssignedOrganization(adminId);
	}
	
	@Override
	public boolean saveUser(UserDTO user) {
		LOGGER.info("Saving user with details :"+user);
		
		//
		if(!OAUTH_IS_CALLABLE)
			return userManagementDao.saveUser(user);
			
		CreateUserWebServiceRequestModel cRequestModel = new CreateUserWebServiceRequestModel();
		cRequestModel.setEmail(user.getEmail());
		cRequestModel.setFirstName(user.getFirstName());
		cRequestModel.setLastName(user.getLastName());
		cRequestModel.setPassword(user.getPassword());
		cRequestModel.setUsername(user.getEmail());


		cRequestModel.setAuthToken(OAUTH_TOKEN);

		Gson gson = new Gson();
		String jsonToBeSent = gson.toJson(cRequestModel,CreateUserWebServiceRequestModel.class);
		
		//Posting to OAuth server
		String response[]=PostToServer.post(OAUTH_USER_CREATE_API, jsonToBeSent);
		
		if(response[0]==null || Integer.parseInt(response[0])!=200)
			return false;
		
		CreateUserWebServiceResponseModel cResponseModel = gson.fromJson(response[1], CreateUserWebServiceResponseModel.class);
		Map<String, String> externalReferenceMap=cResponseModel.getExternalUserReference();
		if (externalReferenceMap!=null){
			for (String columnName : externalReferenceMap.keySet()){
				if (columnName.equals("externalUserReference")){
					cRequestModel.setAuthToken(externalReferenceMap.get(columnName));
				}
				//else add more checks here for more external systems and their columns
			}

			LOGGER.debug("res " + cResponseModel.toString());
		}
		
		user.setExternalAuthenticationToken(cRequestModel.getAuthToken());
		return userManagementDao.saveUser(user);
		
	}

	@Override
	public UserDTO getUserById(Long userId, Long adminId) {
		LOGGER.info("This userId is from controller : " + userId + " And adminId :" + adminId);
		UserDTO userDTO = userManagementDao.getUserById(userId);
		List<AdminOrgListDto> adminOrgList = getOrganizationListOfAdmin(adminId);

		// admin org list duplicate org ID 's removel
		Map<Long, AdminOrgListDto> adminOrgMap = new HashMap<Long, AdminOrgListDto>();

		for (int i = 0; i < adminOrgList.size(); i++) {
			if (!adminOrgMap.containsKey(adminOrgList.get(i).getOrganizationId())) {
				adminOrgMap.put(adminOrgList.get(i).getOrganizationId(), adminOrgList.get(i));
			}
		}

		List<AdminOrgListDto> adminOrgUniqueList = new ArrayList<>();

		for (Map.Entry<Long, AdminOrgListDto> entry : adminOrgMap.entrySet()) {
			AdminOrgListDto adminOrg = new AdminOrgListDto();
			adminOrg = entry.getValue();
			adminOrgUniqueList.add(adminOrg);
		}

		List<DivisionsDTO> userDivisionsList = new ArrayList<>();
		for (int i = 0; i < adminOrgUniqueList.size(); i++) {
			DivisionsDTO divisionsDTO = new DivisionsDTO();
			divisionsDTO.setOrganizationId((int) adminOrgUniqueList.get(i).getOrganizationId());
			divisionsDTO.setOrganizationName(adminOrgUniqueList.get(i).getOrganizationName());
			userDivisionsList.add(divisionsDTO);
		}
		userDTO.setUserDivision(userDivisionsList);
		return userDTO;
	}
	
	@Override
	public List<UserOrgActiveDTO> getUserOrgActive(Long userId) {
		return userManagementDao.getUserOrgActive(userId);
	}
	
	@Override
	public boolean updateUser(UserDTO user) {
		LOGGER.info("The Updated user is : {}"+user);
		boolean updateBasicDetialsResult= userManagementDao.updateUser(user);
		boolean updateUserOrgDetails=userManagementDao.updateUserOrganizations(user.getUserDivision(), user.getUserId(), user.getCurrentLoggedUserId());
		boolean updateUserPreferenceDetails=userManagementDao.upateUserPreferences(user.getUserId(),user.getUserLanguage(), user.getCurrentLoggedUserId());
		if(user.getNotes()!=null){
			UserNotesDto userNotesDto=new UserNotesDto();
			userNotesDto.setUserId(user.getUserId());
			userNotesDto.setUserName(user.getUserName());
			userNotesDto.setNotes(user.getNotes());
			userNotesDto.setCreatedDate(new Date());
			userNotesDto.setCreatedBy(user.getCurrentLoggedUserId());
			divisionMgmtDaoImpl.addNotesToUser(userNotesDto);
			
		}
		if(updateBasicDetialsResult && updateUserOrgDetails && updateUserPreferenceDetails ){
			return true;
		} else{
			return false;
		}
	}

	@Override
	public boolean checkEmail(String email) {
		return userManagementDao.checkEmail(email);
	}
	
	/**
	 * this method is used to get the organizations that admin
	 * have access to where he have role 4 or 5 
	 */
	@Override
	public List<AdminOrgListDto> getOrganizationListOfAdmin(long adminId) {
		return divisionMgmtDaoImpl.getOrganizationListOfAdmin(adminId);
	}

	@Override
	public void resetPassword(String email) {
		StandardPasswordEncoder passwordEncoder=new StandardPasswordEncoder();
		String encodedPassword=passwordEncoder.encode(DEFAULT_PASSWORD);
		userManagementDao.resetPassword(email, encodedPassword);
		emailUtility.sendRestPasswordToUser(email,DEFAULT_PASSWORD);
	}

	@Override
	public UserEmail getUserEmailByUserId(Long userId) {
		
		return userManagementDao.getUserEmailByUserId(userId);
	}

	
	
}
