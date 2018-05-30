package com.pay.coeus.core.service;

public interface CustomerNewOldInfoService {
	
	public int loadCustomerNewOpen();

	public void updateBindAndActivateTime();
	
	int loadCustomerActivateTimeDay();

}
