/**
 * 
 */
package com.habbib.billing.feign.client.fallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.habbib.billing.feign.clients.DBServiceFeignClient;
import com.habbib.billing.model.Bill;
import com.habbib.billing.model.Customerinfo;
import com.habbib.billing.model.Salonservice;
import com.habbib.billing.response.model.BillResponse;

/**
 * @author yash
 *
 */
@Component
public class DBServiceFallback implements DBServiceFeignClient {

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#saveBill(com.habbib.billing.model.Bill)
	 */
	@Override
	public void saveBill(BillResponse bill) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#findAllBills()
	 */
	@Override
	public List<Bill> findAllBills() {
		
		List<Bill> bill = new ArrayList<Bill>();
		return bill;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#deleteBill(int)
	 */
	@Override
	public void deleteBill(int id) {
		
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#updateBill(com.habbib.billing.model.Bill)
	 */
	@Override
	public void updateBill(Bill bill) {
		
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#findByBillId(int)
	 */
	@Override
	public Optional<Bill> findByBillId(int billId) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#findByBillNum(java.lang.String)
	 */
	@Override
	public Optional<Bill> findByBillNum(String billNumber) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#getSalonServices(int)
	 */
	@Override
	public List<Salonservice> getSalonServices(int shopId) {
		List<Salonservice> salon = new ArrayList<Salonservice>();
		return salon;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#getServiceInfo(int)
	 */
	@Override
	public Optional<Salonservice> getServiceInfo(int serviceId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#findByShopId(int)
	 */
	@Override
	public List<Customerinfo> findByShopId(int shopId) {
		List<Customerinfo> customerList = new ArrayList<Customerinfo>();
		// TODO Auto-generated method stub
		return customerList;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#findByCustId(int)
	 */
	@Override
	public Optional<Customerinfo> findByCustId(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
