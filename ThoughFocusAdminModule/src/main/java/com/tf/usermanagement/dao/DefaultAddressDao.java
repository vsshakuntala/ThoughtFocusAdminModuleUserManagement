package com.tf.usermanagement.dao;

/**
 * @author Biswajit
 */

import java.util.List;

import com.tf.usermanagement.dto.BillAddressInputDto;
import com.tf.usermanagement.dto.SalesCustomerDto;
import com.tf.usermanagement.dto.SalesOrgDto;
import com.tf.usermanagement.dto.ShipAddressInputDto;
import com.tf.usermanagement.dto.UserOrgBillShipSalesAreaDto;
import com.tf.usermanagement.dto.UserOrgMapDto;

public interface DefaultAddressDao {

	public List<SalesOrgDto> getAllSales(long organizationId,int pageNumber,int count,String queryString);
	public long countSales(long organizationId,String queryString);

	public List<SalesCustomerDto> getAllSalesCust(long salesAreaId, long userId,int pageNumber,int count,String queryString);
	public long countAllSalesCust(long salesAreaId, long userId,String queryString);

	public List<SalesCustomerDto> getAllUserCust(long userId,int pageNumber,int count,String queryString);
	long countAllUserCust(long userId, String queryString);

	public List<BillAddressInputDto> getAllBillAdd(long customerId, Integer addressTypeId);

	public List<ShipAddressInputDto> getAllShipAdd(long customerId, Integer addressTypeId);

	public List<UserOrgMapDto> getAllUserOrgId(UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto);

	public List<UserOrgBillShipSalesAreaDto> getAllBillShipMap(UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto);

	public List<UserOrgBillShipSalesAreaDto> getAllSalesAreaMap(
			UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto);
}
