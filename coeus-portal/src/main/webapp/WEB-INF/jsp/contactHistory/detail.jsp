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
	<form action="${pageContext.request.contextPath}/contactHistory/add.action" class="coeus-form form-inline col3" method="get" role="form" id="queryForm">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">ID</label>
						<input class="form-control" name="id" value="${contactHistory.id }" readonly="readonly"/>
					</div>
					<div class="input-group">
						<label class="input-group-addon">商编</label>
						<input class="form-control" name="customerNo" id="param1"  value="${contactHistory.customerNo }" onkeyup="value=value.replace(/[^\d]/g,'')" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">商户身份</label> 
						<select class="form-control"  name="customerRole">
							<option value=""></option>
							<option value="LEGAL">法人</option>
			                <option value="FINANCE">会计</option>
			                <option value="CASHIER">收银员</option>
			                <option value="AGENT">服务商</option>
            			</select>
						<%-- <input class="form-control" name="" value="${contactHistory.customerRole }" placeholder=""/> --%>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">电话</label> 
						<input class="form-control" name="phone" id="param3" value="${contactHistory.phone }" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入数字"/>
					</div>
					<!-- <div class="input-group">
						<label class="input-group-addon">渠道</label> 
						<select class="form-control"  name="source">
							<option value="COEUS">社群收集</option>
			                <option value="HF">运营回访</option>
			                <option value="IVR">IVR</option>
			                <option value="KF">客服来电</option>
			                <option value="WEIXIN">验证码登录_公众号</option>
			                <option value="KYSF">验证码登陆_卡友商服APP</option>
			                <option value="ACTIVITY">营销活动收集</option>
			                <option value="CPKT">产品开通_信用卡认证提额</option>
            			</select>
					</div> -->
					<div class="input-group">
						<label class="input-group-addon">备注</label> 
						<input class="form-control" name="remark" value="${contactHistory.remark }"/>
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