package com.tf.usermanagement.serviceimpl;

/**
 * @author Biswajit
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.usermanagement.dao.DefaultAddressDao;
import com.tf.usermanagement.dto.BillAddressInputDto;
import com.tf.usermanagement.dto.DefaultAddressCheckDto;
import com.tf.usermanagement.dto.PaginationDto;
import com.tf.usermanagement.dto.PaginationResult;
import com.tf.usermanagement.dto.SalesCustomerDto;
import com.tf.usermanagement.dto.SalesOrgDto;
import com.tf.usermanagement.dto.ShipAddressInputDto;
import com.tf.usermanagement.dto.UserOrgBillShipSalesAreaDto;
import com.tf.usermanagement.dto.UserOrgMapDto;
import com.tf.usermanagement.endpoint.ApiConstants;
import com.tf.usermanagement.exceptions.EmptyListException;
import com.tf.usermanagement.service.DefaultAddressService;

@Service
public class DefaultAddressServiceImpl implements DefaultAddressService {

	@Autowired
	private DefaultAddressDao salescustomerdao;

	@Override
	public PaginationResult salescustgetAll(long salesAreaId, long userId,PaginationDto paginationDto) {

		List<SalesCustomerDto> salesCustomerList = new ArrayList<SalesCustomerDto>();
		salesCustomerList=salescustomerdao.getAllSalesCust(salesAreaId, userId,paginationDto.getPageNumber()==null?ApiConstants.DEFAULT_PAGE_NUMBER:paginationDto.getPageNumber()
				,paginationDto.getCount()==null?ApiConstants.DEFAULT_PAGE_COUNT:paginationDto.getCount(),paginationDto.getQueryString());
		
		long totalCount=salescustomerdao.countAllSalesCust(salesAreaId, userId, paginationDto.getQueryString());
		
				
		PaginationResult paginationResult=new PaginationResult();
		paginationResult.setData(salesCustomerList);
		boolean flag=totalCount-(paginationDto.getPageNumber()==null?ApiConstants.DEFAULT_PAGE_NUMBER:paginationDto.getPageNumber()*(paginationDto.getCount()==null?ApiConstants.DEFAULT_PAGE_COUNT:paginationDto.getCount()))>0;
		paginationResult.setHasNext(flag);
		return paginationResult;
		
		/*if(salesCustomerList!=null && !salesCustomerList.isEmpty()){
			return salesCustomerList;
		}
		else{
			throw new EmptyListException(EmptyListException.EMPTY_LIST);
		}*/	
		
	}

	@Override
	public PaginationResult custgetAll(long userId,long organizationId,PaginationDto paginationDto) {
		
		List<SalesCustomerDto> salesCustomerList = new ArrayList<SalesCustomerDto>();
		salesCustomerList=salescustomerdao.getAllUserCust(userId,organizationId,paginationDto.getPageNumber()==null?ApiConstants.DEFAULT_PAGE_NUMBER:paginationDto.getPageNumber()
				,paginationDto.getCount()==null?ApiConstants.DEFAULT_PAGE_COUNT:paginationDto.getCount(),paginationDto.getQueryString());
		
		long totalCount=salescustomerdao.countAllUserCust(userId, paginationDto.getQueryString());
		
		PaginationResult paginationResult=new PaginationResult();
		paginationResult.setData(salesCustomerList);
		boolean flag=totalCount-(paginationDto.getPageNumber()==null?ApiConstants.DEFAULT_PAGE_NUMBER:paginationDto.getPageNumber()*(paginationDto.getCount()==null?ApiConstants.DEFAULT_PAGE_COUNT:paginationDto.getCount()))>0;
		paginationResult.setHasNext(flag);
		return paginationResult;
		
		/*if(salesCustomerList!=null && !salesCustomerList.isEmpty()){
			return salesCustomerList;
		}
		else{
			throw new EmptyListException(EmptyListException.EMPTY_LIST);
		}*/
	}

	@Override
	public List<BillAddressInputDto> billaddgetAll(long customerId, Integer addressTypeId) {
		List<BillAddressInputDto> BillAddressInputList = new ArrayList<BillAddressInputDto>();
		BillAddressInputList=salescustomerdao.getAllBillAdd(customerId, addressTypeId);
		if(BillAddressInputList!=null && !BillAddressInputList.isEmpty()){
		return BillAddressInputList;
		}
		else{
			throw new EmptyListException(EmptyListException.EMPTY_LIST);
		}
	}

	@Override
	public List<ShipAddressInputDto> shipaddgetAll(long customerId, Integer addressTypeId) {
		List<ShipAddressInputDto> ShipAddressInputList = new ArrayList<ShipAddressInputDto>();
		ShipAddressInputList=salescustomerdao.getAllShipAdd(customerId, addressTypeId);
		if(ShipAddressInputList!=null && !ShipAddressInputList.isEmpty()){
		return ShipAddressInputList;
		}
		else{
			throw new EmptyListException(EmptyListException.EMPTY_LIST);
		}
	}

	@Override
	public PaginationResult salesgetAll(long organizationId,PaginationDto paginationDto) {
		List<SalesOrgDto> list= salescustomerdao.getAllSales(organizationId,paginationDto.getPageNumber()==null?ApiConstants.DEFAULT_PAGE_NUMBER:paginationDto.getPageNumber()
				,paginationDto.getCount()==null?ApiConstants.DEFAULT_PAGE_COUNT:paginationDto.getCount(),paginationDto.getQueryString());
		long totalCount=salescustomerdao.countSales(organizationId, paginationDto.getQueryString());
		
		PaginationResult paginationResult=new PaginationResult();
		paginationResult.setData(list);
		boolean flag=totalCount-(paginationDto.getPageNumber()==null?ApiConstants.DEFAULT_PAGE_NUMBER:paginationDto.getPageNumber()*(paginationDto.getCount()==null?ApiConstants.DEFAULT_PAGE_COUNT:paginationDto.getCount()))>0;
		paginationResult.setHasNext(flag);
		return paginationResult;
	}

	@Override
	public List<UserOrgMapDto> userorgidAll(UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto) {
		return salescustomerdao.getAllUserOrgId(userOrgBillShipSalesAreaDto);
	}

	public List<UserOrgBillShipSalesAreaDto> billshipmapAll(UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto) {
		return salescustomerdao.getAllBillShipMap(userOrgBillShipSalesAreaDto);
	}

	@Override
	public List<UserOrgBillShipSalesAreaDto> salesareamapAll(UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto) {
			return salescustomerdao.getAllSalesAreaMap(userOrgBillShipSalesAreaDto);
		}

	@Override
	public boolean saveOrUpdateDefaultAddressForUser(DefaultAddressCheckDto defaultAddressCheckDto) {
		return salescustomerdao.saveOrUpdateDefaultAddressForUser(defaultAddressCheckDto);
	}


	

}
