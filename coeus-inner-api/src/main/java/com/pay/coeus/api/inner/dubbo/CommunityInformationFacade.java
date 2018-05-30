package com.pay.coeus.api.inner.dubbo;

import java.util.Map;

public interface CommunityInformationFacade {

	public Map<String, String> CommunityInformationQuery(String customerNo,String orderTime);
}
