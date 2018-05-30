package com.pay.coeus.api.inner.dubbo;

import java.util.List;
import java.util.Map;

import com.pay.coeus.model.entity.NewCustomerActivate;
import com.pay.coeus.model.entity.OldCustomerLoss;
import com.pay.commons.utils.Page;

public interface NewOrLossCustomerFacade {

	Page<List<NewCustomerActivate>> findAllNewCustomerList(Page<List<NewCustomerActivate>> page, Map<String, String> param);
	
	Page<List<OldCustomerLoss>> findAllOldCustomerList(Page<List<OldCustomerLoss>> page, Map<String, String> param);
	
	// Page<List<Map<String,String>>> findAllCustomerNoFlowDetailByCustomerNo(Page<List<Map<String,String>>> page,String customerNo);

}
