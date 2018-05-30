<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html lang="zh-hans">
<head>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">
function sub(){
	if($("#param1").val() == null || $("#param1").val() == ''){
		alert("请输入商编");
		return;
	}
	if($("#param3").val() == null || $("#param3").val() == ''){
		alert("请输入电话");
		return;
	}
	$("#queryForm").submit();
}
</script>
</head>
<body>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="${pageContext.request.contextPath}/contactInfo/update.action" class="coeus-form form-inline col3" method="get" role="form" id="queryForm">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">ID</label>
						<input class="form-control" name="id" value="${contactInfo.id }" readonly="readonly"/>
					</div>
					<div class="input-group">
						<label class="input-group-addon">商编</label>
						<input class="form-control" name="customerNo" id="param1"  value="${contactInfo.customerNo }" onkeyup="value=value.replace(/[^\d]/g,'')" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">商户身份</label> 
						<input class="form-control" name="customerRole" value="${contactInfo.customerRole }" placeholder="LEGAL、FINANCE、CASHIER"/>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">电话</label> 
						<input class="form-control" name="phone" id="param3" value="${contactInfo.phone }" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入数字"/>
					</div>
					<div class="input-group">
						<label class="input-group-addon">备注</label> 
						<input class="form-control" name="remark" value="${contactInfo.remark }"/>
					</div> 
				</div>
			</div>
			<div class="text-center">
	            <button type="button" class="btn btn-primary btn-blue" id="findlist" onclick="sub()">提交</button>
	            <button type="button" class="btn btn-default btn-initial" onClick="javascript :history.back(-1);">返回</button> 
	        </div>
		</div>
	</form>
</div>

</body>
</html>