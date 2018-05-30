package com.pay.coeus.core.dubbo.inner;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.coeus.api.inner.dubbo.CommunityInformationFacade;
import com.pay.coeus.core.service.CommunityInformationService;

@Service("communityInformationFacade")
public class CommunityInformationFacadeImpl implements CommunityInformationFacade{

	@Autowired
	private CommunityInformationService communityInformationService;
	
	@Override
	public Map<String, String> CommunityInformationQuery(String customerNo,String orderTime) {
		
		Map<String, String> communityInformatQuery = communityInformationService.CommunityInformatQuery(customerNo, orderTime);
		if(communityInformatQuery != null){
		return communityInformatQuery;
		}
		return null;
	}

	

}
