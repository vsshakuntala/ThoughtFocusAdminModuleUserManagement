package com.tf.usermanagement.service;

/**
 * @author Biswajit
 */

import java.util.List;

import com.tf.usermanagement.dto.BillAddressInputDto;
import com.tf.usermanagement.dto.DefaultAddressCheckDto;
import com.tf.usermanagement.dto.PaginationDto;
import com.tf.usermanagement.dto.PaginationResult;
import com.tf.usermanagement.dto.ShipAddressInputDto;
import com.tf.usermanagement.dto.UserOrgBillShipSalesAreaDto;
import com.tf.usermanagement.dto.UserOrgMapDto;

public interface DefaultAddressService {

	public PaginationResult salesgetAll(long organizationId,PaginationDto paginationDto);
	

	public PaginationResult salescustgetAll(long salesAreaId, long userId,PaginationDto paginationDto);

	public PaginationResult custgetAll(long userId,long organizationId,PaginationDto paginationDto);

	public List<BillAddressInputDto> billaddgetAll(long customerId, Integer addressTypeId);

	public List<ShipAddressInputDto> shipaddgetAll(long customerId, Integer addressTypeId);

	public List<UserOrgMapDto> userorgidAll(UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto);

	public List<UserOrgBillShipSalesAreaDto> billshipmapAll(UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto);

	public List<UserOrgBillShipSalesAreaDto> salesareamapAll(UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto);
	
	public boolean saveOrUpdateDefaultAddressForUser(DefaultAddressCheckDto defaultAddressCheckDto);
}
