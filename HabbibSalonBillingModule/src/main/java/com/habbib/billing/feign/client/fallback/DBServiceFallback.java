/**
 * 
 */
package com.habbib.billing.feign.client.fallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.habbib.billing.dbrequest.model.BillRequest;
import com.habbib.billing.feign.clients.DBServiceFeignClient;
import com.habbib.billing.model.Bill;
import com.habbib.billing.model.Customerinfo;
import com.habbib.billing.model.Paymenttype;
import com.habbib.billing.model.Salonservice;
import com.habbib.billing.model.Shopinfo;
import com.habbib.billing.model.Staffinfo;

/**
 * @author yash
 *
 */
@Component
public class DBServiceFallback implements DBServiceFeignClient {

	
	@Override
	public Bill saveBill(BillRequest billModel) {
		// TODO Auto-generated method stub
		return null;
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
	public Optional<Bill> findByBillId(int billId, int shopId) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#findByBillNum(java.lang.String)
	 */
	@Override
	public Optional<Bill> findByBillNum(String billNumber, int shopId) {
		return null;
	}

	

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#getServiceInfo(int)
	 */
	@Override
	public Optional<Salonservice> getServiceInfo(int serviceId) {
		return null;
	}



	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#findByCustId(int)
	 */
	@Override
	public Optional<Customerinfo> findByCustId(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#findBillByShopId(int)
	 */
	@Override
	public List<Bill> findBillByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#filterByDateRange(java.lang.String, java.lang.String, int)
	 */
	@Override
	public List<Bill> filterByDateRange(String startDate, String endDate, int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#filterByDate(int)
	 */
	@Override
	public List<Bill> filterByDate(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#findByShopId(int)
	 */
	@Override
	public Optional<Shopinfo> findByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#findStaffByid(int)
	 */
	@Override
	public Staffinfo findStaffByid(int staffId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#fetchByPaymentTypeID(int)
	 */
	@Override
	public Paymenttype fetchByPaymentTypeID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.habbib.billing.feign.clients.DBServiceFeignClient#fetchBillByCustId(int)
	 */
	@Override
	public List<Bill> fetchBillByCustId(int custId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
