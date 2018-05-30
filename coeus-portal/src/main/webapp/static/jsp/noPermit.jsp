<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="zh-hans">
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" >
<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function() {
	
	<%  String type = request.getParameter("type"); 
   		if("A".equals(type)){
   			out.println("noPermit();");
   		}else if("B".equals(type)){
   			out.println("sessionExpired();");
   		}else if("C".equals(type)){
   			out.println("compelLogout();");
   		}else if("D".equals(type)){
   			out.println("illegalUrl();");
   		}
   		
	%>
});
	function noPermit(){	
		/* $.messager.alert("操作提示", "无操作此功能的权限！");		 */		
		$("#noPermit").removeClass("hide");
	}
	function illegalUrl(){	
		//$.messager.alert("操作提示", "非法的URL请求！");		
		$("#illegalUrl").removeClass("hide");
	}		
	function sessionExpired(){		
		/* $.messager.alert('操作提示', 'Session信息过期或未登录，请重新登录系统！', '',function(){
				window.top.location.href="${pageContext.request.contextPath}/jsp/login.jsp";	
		}); */
		$("#sessionExpired").removeClass("hide");
	}							
	function compelLogout(){
		/* $.messager.alert('操作提示', '用户名在其它客户端重新登录，您被强制登出！','', function(){
				window.top.location.href="${pageContext.request.contextPath}/jsp/login.jsp";	
		}); */
		$("#compelLogout").removeClass("hide");
	}
</script>
</head>
<body>

<div id="noPermit" class="alert alert-warning hide">
	<a href="javascript:history.go(-1);" class="close" data-dismiss="alert">&times;</a>
	<strong>操作提示！</strong>无操作此功能的权限！
</div>

<div id="illegalUrl" class="alert alert-warning hide">
	<a href="javascript:history.go(-1);" class="close" data-dismiss="alert">&times;</a>
	<strong>操作提示！</strong>非法的URL请求！
</div>

<div id="sessionExpired" class="alert alert-danger hide">
	<a href="${pageContext.request.contextPath}/jsp/login.jsp" class="close" data-dismiss="alert">&times;</a>
	<strong>警告！</strong>Session信息过期或未登录，请重新登录系统！
</div>

<div id="compelLogout" class="alert alert-danger hide">
	<a href="${pageContext.request.contextPath}/jsp/login.jsp" class="close" data-dismiss="alert">&times;</a>
	<strong>警告！</strong>用户名在其它客户端重新登录，您被强制登出！
</div>

</body>
</html>