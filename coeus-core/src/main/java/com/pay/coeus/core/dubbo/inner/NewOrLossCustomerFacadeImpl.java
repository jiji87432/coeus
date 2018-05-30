package com.pay.coeus.core.dubbo.inner;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.api.inner.dubbo.NewOrLossCustomerFacade;
import com.pay.coeus.core.service.NewCustomerActivateService;
import com.pay.coeus.core.service.OldCustomerLossService;
import com.pay.coeus.model.entity.NewCustomerActivate;
import com.pay.coeus.model.entity.OldCustomerLoss;
import com.pay.commons.utils.Page;
@Service("newOrLossCustomerFacade")
public class NewOrLossCustomerFacadeImpl implements NewOrLossCustomerFacade{

	Logger logger = LoggerFactory.getLogger(NewOrLossCustomerFacadeImpl.class);
	@Autowired
	private NewCustomerActivateService newCustomerActivateService;
	@Autowired
	private OldCustomerLossService oldCustomerLossService;
	@Override
	public Page<List<NewCustomerActivate>> findAllNewCustomerList(Page<List<NewCustomerActivate>> page,
			Map<String, String> param) {
		logger.info("findAllNewCustomerList参数:{}",param.toString());
		List<NewCustomerActivate> list = newCustomerActivateService.findAllList(page, param);
		page.setObject(list);
		return page;
	}
	@Override
	public Page<List<OldCustomerLoss>> findAllOldCustomerList(Page<List<OldCustomerLoss>> page,
			Map<String, String> param) {
		List<OldCustomerLoss> list = oldCustomerLossService.findAllOldCustomer(page, param);
		page.setObject(list);
		return page;
	}

}
