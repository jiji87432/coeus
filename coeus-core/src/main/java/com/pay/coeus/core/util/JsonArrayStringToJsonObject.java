package com.pay.coeus.core.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pay.commons.utils.lang.StringUtils;

public class JsonArrayStringToJsonObject {

	public static JSONObject getJsonObject(String jsonArrayString) {
		if (StringUtils.isBlank(jsonArrayString)) {
			return new JSONObject();
		}
		JSONArray jsonArray = (JSONArray) JSONObject.parse(jsonArrayString);
		if (jsonArray.isEmpty()) {
			return new JSONObject();
		}
		JSONObject jsonObject = JSONObject.parseObject(jsonArray.getString(0));
		return jsonObject;
	}
}
