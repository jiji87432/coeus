<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ page import="com.pay.coeus.common.utils.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	var rootPath = '${pageContext.request.contextPath}';
</script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
<title>Coeus考伊斯系统</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/coeus-form.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-datetimepicker.min.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-table.min.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-fileinput.min.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/toastr.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/My97DatePicker/skin/WdatePicker.css"  type="text/css"/>