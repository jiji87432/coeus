package com.pay.coeus.api.inner.bean;

public class ReturnUtil {
	
	/**
	 * 操作成功
	 * @param t
	 * @return
	 */
	public static ReturnConstant success(Object t){
		
		return new ReturnConstant(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getType(),t);
		
	}
	
	/**
	 * 操作失败
	 * @param msg 失败原因
	 * @param t 返回值
	 * @return
	 */
	public static ReturnConstant fail(String msg,Object t){
		
		return new ReturnConstant(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getType(), msg, t);
		
	}
	
	/**
	 * 操作异常
	 * @param msg
	 * @param t
	 * @return
	 */
	public static ReturnConstant error(Exception e){
		
		return new ReturnConstant(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getType(), e.getMessage(), e);
		
	}

}
