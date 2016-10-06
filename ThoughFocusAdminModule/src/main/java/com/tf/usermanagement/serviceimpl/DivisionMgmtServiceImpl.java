package com.tf.usermanagement.serviceimpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tf.usermanagement.dao.DivisionMgmtDao;
import com.tf.usermanagement.daoimpl.DivisionMgmtDaoImpl;
import com.tf.usermanagement.domain.Organization;
import com.tf.usermanagement.domain.User;
import com.tf.usermanagement.domain.UserOrganizationMap;
import com.tf.usermanagement.dto.AdminOrgListDto;
import com.tf.usermanagement.dto.AssignOrgDto;
import com.tf.usermanagement.dto.AssignOrgMapDto;
import com.tf.usermanagement.dto.CatalogTotalCountDto;
import com.tf.usermanagement.dto.CustomerCatalogCountDto;
import com.tf.usermanagement.dto.CustomerTotalCountDto;
import com.tf.usermanagement.dto.DeAssignUserToOrgInputDto;
import com.tf.usermanagement.dto.DefaultAddressCheckDto;
import com.tf.usermanagement.dto.DefaultAddressCountDto;
import com.tf.usermanagement.dto.DivisionAssignmentDto;
import com.tf.usermanagement.dto.GroupCatalogCountDto;
import com.tf.usermanagement.dto.GroupCustomerCountDto;
import com.tf.usermanagement.dto.GroupTotalCountDto;
import com.tf.usermanagement.dto.OrganizationDTO;
import com.tf.usermanagement.dto.OrganizationRoleCountDto;
import com.tf.usermanagement.dto.UserNotesDto;
import com.tf.usermanagement.dto.UserUnassignedOrgDto;
import com.tf.usermanagement.exceptions.EmptyListException;
import com.tf.usermanagement.service.DivisionMgmtService;

@Service
public class DivisionMgmtServiceImpl implements DivisionMgmtService {

	@Autowired
	private DivisionMgmtDao divisionMgmtDao;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Value("${default.associate.note.message}")
	private String ASSOCIATE_DEFAULT_NOTE_MESSAGE;
	
	@Value("${default.disassociate.note.message}")
	private String DISASSOCIATE_DEFAULT_NOTE_MESSAGE;

	private static final Logger LOGGER = Logger.getLogger(DivisionMgmtServiceImpl.class);
	
	@Override
	public List<GroupCustomerCountDto> getCustomerCountForGroupsByOrganization(long userId) {
		return divisionMgmtDao.getCustomerCountForGroupsByOrganization(userId);
	}

	@Override
	public List<GroupCatalogCountDto> getCatalogCountForGroupsByOrganization(long userId) {
		return divisionMgmtDao.getCatalogCountForGroupsByOrganization(userId);
	}

	@Override
	public List<CustomerCatalogCountDto> getCustomerAndCatalogCount(long userId) {
		return divisionMgmtDao.getCustomerAndCatalogCount(userId);
	}
	
	//new implementation for customer and catalog
	@Override
	public List<CustomerTotalCountDto> getTotalCustomerCount(long userId) {
		
		return divisionMgmtDao.getTotalCustomerCount(userId);
	}

	@Override
	public List<CatalogTotalCountDto> getTotalCatalogCount(long userId) {
		
		 return divisionMgmtDao.getTotalCatalogCount(userId);
	}
	
	@Override
	public List<GroupTotalCountDto> getGroupCount(long userId) {
		
		 return divisionMgmtDao.getGroupCount(userId);
	}

	
	@Override
	public List<OrganizationRoleCountDto> getRoleCountByOrganization(long userId) {
		return divisionMgmtDao.getRoleCountByOrganization(userId);
	}

	@Override
	public List<DefaultAddressCountDto> getDefaultAddressCountByOrganization(long userId) {
		return divisionMgmtDao.getDefaultAddressCountByOrganization(userId);
	}

	@Override
	public List<DivisionAssignmentDto> getAllDivisionAssignment(long userId, long adminId) {
				
		//get total customer count
		List<CustomerTotalCountDto> customerTotalCountList=getTotalCustomerCount(userId);
		//get total catalog count
		List<CatalogTotalCountDto> catalogTotalCountList=getTotalCatalogCount(userId);
		//get group count
		List<GroupTotalCountDto> groupTotalCountList=getGroupCount(userId);
		// get the role count based on organization for the given user Id
		List<OrganizationRoleCountDto> organizationRoleCountList = getRoleCountByOrganization(userId);
		// get the default address count based on organization for given user Id
		List<DefaultAddressCountDto> defaultAddressCountList = getDefaultAddressCountByOrganization(userId);

		List<DivisionAssignmentDto> divisionAssignmentList = new ArrayList<>();
		
		if ((customerTotalCountList.size() == catalogTotalCountList.size())
				&& (catalogTotalCountList.size() == groupTotalCountList.size())
				&& (groupTotalCountList.size() == organizationRoleCountList.size())
				&& (organizationRoleCountList.size() == defaultAddressCountList.size())){
			
			for (int i = 0; i < organizationRoleCountList.size(); i++) {

				DivisionAssignmentDto assignmentDto = new DivisionAssignmentDto();
				assignmentDto.setOrganizationId(organizationRoleCountList.get(i).getOrganizationId());
				assignmentDto.setOrganizationName(organizationRoleCountList.get(i).getOrganizationName());

				// group count
				assignmentDto.setGroupsCount(groupTotalCountList.get(i).getTotalGroupCount());

				// group customer count total
				assignmentDto.setTotalCustomerCount(customerTotalCountList.get(i).getTotalCustomerCount());

				// group catalog count total
				assignmentDto.setTotalCatalogCount(catalogTotalCountList.get(i).getTotalCatalogCount());

				if (organizationRoleCountList.get(i).getApprovalStatus() == 1) {
					assignmentDto.setStatus("Approved");
				} else {
					assignmentDto.setStatus("Pending");
				}

				// role count
				assignmentDto.setRoleCount(organizationRoleCountList.get(i).getRoleCount());
				if(organizationRoleCountList.get(i).getModifiedDate()==null){
				assignmentDto.setCreatedDate(organizationRoleCountList.get(i).getCreatedDate());
				}else{
					assignmentDto.setCreatedDate(organizationRoleCountList.get(i).getModifiedDate());
				}
				assignmentDto.setLastLoginDate(organizationRoleCountList.get(i).getLastLoginDate());

				// default address count
				assignmentDto.setDefaultAddressCount(defaultAddressCountList.get(i).getDefaultAddressCount());

				divisionAssignmentList.add(assignmentDto);
			}
		}
		
		
		
		
		
		
		// check if logged in admin have admin access to organization that user
		// belongs to
		List<AdminOrgListDto> adminOrgList = new ArrayList<>();
		adminOrgList = getOrganizationListOfAdmin(adminId);
		for (int i = 0; i < adminOrgList.size(); i++) {
			for (int j = 0; j < divisionAssignmentList.size(); j++) {
				if (adminOrgList.get(i).getOrganizationId() == divisionAssignmentList.get(j).getOrganizationId()) {
					divisionAssignmentList.get(j).setAdminAccess(true);
				}
			}
		}
		
		if (divisionAssignmentList != null && !divisionAssignmentList.isEmpty()) {
			return divisionAssignmentList;
		} else {
			throw new EmptyListException(EmptyListException.EMPTY_LIST);
		}
	}

	/**
	 * all de-assignments for user order of de-assignment to be followed as
	 * mentioned in method impl
	 */
	@Override
	public boolean deAssignAllAllocationsForUserByOrganization(DeAssignUserToOrgInputDto deAssignUserToOrgInputDto) {
		//TODO
		
		
		Session session = null;
		Transaction tx = null;
		boolean result=false;
		
		try{
			
			
			Organization organization=divisionMgmtDao.getOrganizationById(deAssignUserToOrgInputDto.getOrganizationId());
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			boolean resultForCatalog = divisionMgmtDao.deAssignCatalogsOfOrganization(deAssignUserToOrgInputDto,session);

			boolean resultForCustomer = divisionMgmtDao.deAssignCustomerOfOrganization(deAssignUserToOrgInputDto,session);

			boolean resultForGroup = divisionMgmtDao.deAssignGroupOfOrganization(deAssignUserToOrgInputDto,session);

			boolean resultForRole = divisionMgmtDao.deAssignRoleOfOrganization(deAssignUserToOrgInputDto,session);

			boolean resultForOrg = divisionMgmtDao.deAssignUserFromOrganization(deAssignUserToOrgInputDto,session);
			
			boolean resultForAddress=divisionMgmtDao.deAssignDefaultAddress(deAssignUserToOrgInputDto,session);
			
			boolean resultForDeActivateUser=divisionMgmtDao.deActivateUser(deAssignUserToOrgInputDto,session);
			
			if (resultForCatalog && resultForCustomer && resultForGroup && resultForRole && resultForOrg && resultForAddress && resultForDeActivateUser) {
				//Sending note
				UserNotesDto userNotesDto =new UserNotesDto();
				if(deAssignUserToOrgInputDto.getNotes()==null || deAssignUserToOrgInputDto.getNotes().length()==0){
					userNotesDto.setNotes(DISASSOCIATE_DEFAULT_NOTE_MESSAGE+" : "+organization.getOrganizationName());
				}else{
					userNotesDto.setNotes(deAssignUserToOrgInputDto.getNotes()+System.lineSeparator()+DISASSOCIATE_DEFAULT_NOTE_MESSAGE+" : "+organization.getOrganizationName());
				}
				userNotesDto.setUserId(deAssignUserToOrgInputDto.getUserId());
				userNotesDto.setCreatedBy(deAssignUserToOrgInputDto.getModifiedById());
				if(divisionMgmtDao.addNotesToUserForDeassignmentOfOrg(userNotesDto, session)){
					tx.commit();
					result= true;	
				}else{
					tx.rollback();
					result= false;
					LOGGER.error("unable to add notes to user while de-assigning organization" );
				}
				
				System.out.println("All deassociations were successfull in Service Impl");
			} else {
				tx.rollback();
				result= false;
			}
			
			
		}catch (HibernateException e) {
			result= false;
			tx.rollback();
			LOGGER.error("Exception in deAssignAllAllocationsForUserByOrganization " + e);
		} catch (Exception e) {
			result= false;
			tx.rollback();
			LOGGER.error("Exception in deAssignAllAllocationsForUserByOrganization " + e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}
	
	
	

	/**
	 * this method is used to get the list of organizations that admin related
	 * to
	 */
	@Override
	public List<AdminOrgListDto> getOrganizationListOfAdmin(long adminId) {

		List<AdminOrgListDto> list = new ArrayList<>();
		list = divisionMgmtDao.getOrganizationListOfAdmin(adminId);

		Map<Long, AdminOrgListDto> map = new LinkedHashMap<>();
		for (AdminOrgListDto adminOrgObject : list) {
			map.put(adminOrgObject.getOrganizationId(), adminOrgObject);
		}
		list.clear();
		list.addAll(map.values());

		return list;

	}

	@Override
	public List<OrganizationDTO> getAllOrganizations() {

		return divisionMgmtDao.getAllOrganizations();
	}

	@Override
	public List<UserUnassignedOrgDto> getUnassignedOrganizationsForUser(long userId, long adminId) {

		List<UserUnassignedOrgDto> umAssignedOrgList = null;
		umAssignedOrgList = divisionMgmtDao.getUnassignedOrganizationsForUser(userId, adminId);

		if (umAssignedOrgList != null && !umAssignedOrgList.isEmpty()) {
			return umAssignedOrgList;
		} else {
			throw new EmptyListException(EmptyListException.EMPTY_LIST);
		}

	}

	@Override
	public boolean insertOrUpdateUserOrganizationMap(AssignOrgDto deAssignUserToOrgInputDto) {
		// collect the user Id and organization Id to create a UserOrgMap i/p
		// for DAO
		
		if(deAssignUserToOrgInputDto==null || deAssignUserToOrgInputDto.getOrganizationIds()==null || deAssignUserToOrgInputDto.getOrganizationIds().size()==0)
			return false;
		for(AssignOrgMapDto d:deAssignUserToOrgInputDto.getOrganizationIds()){
			User user = new User();
			user.setUserId(deAssignUserToOrgInputDto.getUserId());
			
			UserOrganizationMap userOrganizationMap = new UserOrganizationMap();
			userOrganizationMap.setOrganizationId(d.getOrganizationId());
			
			userOrganizationMap.setUser(user);
			
			if(!divisionMgmtDao.insertOrUpdateUserOrganizationMap(userOrganizationMap, deAssignUserToOrgInputDto))
				return false;
		}
		
		//Sending note
		UserNotesDto userNotesDto =new UserNotesDto();
		if(deAssignUserToOrgInputDto.getNote()==null || deAssignUserToOrgInputDto.getNote().length()==0){
			userNotesDto.setNotes(ASSOCIATE_DEFAULT_NOTE_MESSAGE);
		}else{
			userNotesDto.setNotes(deAssignUserToOrgInputDto.getNote());
		}
		userNotesDto.setUserId(deAssignUserToOrgInputDto.getUserId());
		userNotesDto.setCreatedBy(deAssignUserToOrgInputDto.getModifiedById());
		addNotesToUser(userNotesDto);
		return true;
	}

	@Override
	public List<UserNotesDto> getNotesListForUser(long userId) {
		List<UserNotesDto> userNotesList = null;
		userNotesList = divisionMgmtDao.getNotesListForUser(userId);

		if (userNotesList != null && !userNotesList.isEmpty()) {
			return userNotesList;
		} else {
			throw new EmptyListException(EmptyListException.EMPTY_LIST);
		}
	}

	@Override
	public boolean addNotesToUser(UserNotesDto userNotesDto) {

		return divisionMgmtDao.addNotesToUser(userNotesDto);
	}

	@Override
	public DefaultAddressCheckDto getDefaultAddressForUserOrg(long userOrgId) {
		
		return divisionMgmtDao.getDefaultAddressForUserOrg(userOrgId);
	}
	

}
