<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html lang="zh-hans">
<head>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">

function queryParams(params){
	 return {
         offset: params.offset,  //页码
         limit: params.limit,   //页面大小
         search : params.search, //搜索
         order : params.order, //排序
         ordername : params.sort, //排序
     };
}
function toCheckValue(value,row,index){
	
	var code = null;
	if(value=="TRUE"){
		code = '<span class="label label-success">开通</span>';
	}else if(value=="FALSE"){
		code = '<span class="label label-danger">关闭</span>';
	}else if(value=="DELETE"){
		code = '<span class="label label-default">删除</span>';
	} else {
		code= value;
	}
	return code;
	
}
function toSetValue(value,row,index){
	var code = null;
	if(value=="LOSS" ){
		code = '<span class="label label-primary">流失</span>';
	}else if(value=="SUSPECT_LOSS" ){
		code = '<span class="label label-info">疑似流失</span>';
	} else {
		code = value;
	}
	return code;
	
}
//设置显示值
function toDoValue(value,row,index){
	var code = null;
	if(value=="Y" || value == "y"){
		code="是";
	}else{
		code="否";
	}
	return code;
}
//转换时间格式
function add0(m){return m<10?'0'+m:m;}
function format(shijianchuo)
{
	if(shijianchuo==null || shijianchuo==""){
		return shijianchuo;
	}
	//shijianchuo是整数，否则要parseInt转换
	var time = new Date(shijianchuo);
	var y = time.getFullYear();
	var m = time.getMonth()+1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}

function manageValue(value,row,index){
	if(value==null|| value=="" ){
		return 4;
	} else {
		return value;
	}
}
function customerLoss(value,row,index){
	if(value == "Y" || value=="y"){
		return "有";
	}else{
		return "无";
	}
}
function exportForm(url){
	var formUrl = $(".coeus-form").attr("action");
	$(".coeus-form").attr("action", url);
	$(".coeus-form").attr("target", "exportResult");
	$(".coeus-form").submit();
	$(".coeus-form").attr("action", formUrl);
	
}

function isTransFormate(value,row,index){
	var code = null;
	if(value=="Y" || value == "y"){
		code="是";
	}else{
		code="否";
	}
	return code;
}

</script>
</head>
<!-- new_customer_activate -->
<!-- 状态   -->
<!-- NO_ACTIVATE  未激活 -->
<!-- NO_REMAIN    未留存 -->

<!-- old_customer_loss -->
<!-- 状态 -->
<!-- LOSS          流失 -->
<!-- SUSPECT_LOSS  疑似流失 -->


<!-- 商户状态 -->
<!-- TRUE    开通 -->
<!-- DELETE  删除 -->
<!-- FALSE   关闭 -->
<body>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="${pageContext.request.contextPath}/oldCustomer/findCustomerLoss.action" class="coeus-form form-inline col3" method="post" role="form" id="queryForm">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">商户状态</label>
						<select class="form-control"  name="customerStatus">
                			<option value=""></option>
			                <option value="TRUE">开通</option>
			                <option value="FALSE">关闭</option>
			                <option value="DELETE">删除</option>
            			</select>
					</div>
					<div class="input-group">
						<label class="input-group-addon">商户分级</label> 
						<select class="form-control"  name="customerLevel">
                			<option value=""></option>
			                <option value="1">1</option>
			                <option value="2">2</option>
			                <option value="3">3</option>
			                <option value="null">4</option>
            			</select>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">商户真实性</label> 
						<select class="form-control" name="customerCheckInfo" >
                			<option value=""></option>
			                <option value="Y">是</option>
			                <option value="N">否</option>
            			</select>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">结果类型</label> 
						<select class="form-control" name="resultType" >
                			<option value=""></option>
			                <option value="LOSS">流失</option>
			                <option value="SUSPECT_LOSS">疑似流失</option>
            			</select>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">是否有流量</label> 
						<select class="form-control" name="isLoss" >
                			<option value=""></option>
			                <option value="YOU">有</option>
			                <option value="WU">无</option>
            			</select>
					</div>
					<div class="input-group">
						<label class="input-group-addon">创建时间</label> 
						<div class="coeus-time" >
							<input class="form-control form-time-control" placeholder="开始时间" value="<%=DateUtils.formatDate(new Date(),"yyyy-MM-dd 00:00")%>" name="createTimeStart" style="width: 45%;float: none;">
							<span>&nbsp;至&nbsp;</span>
							<input class="form-control form-time-control" placeholder="结束时间" value="<%=DateUtils.formatDate(new Date(),"yyyy-MM-dd 23:59")%>" name="createTimeEnd" style="width: 45%;float: none;">
						</div>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">更新时间</label> 
						<div class="coeus-time" >
							<input class="form-control form-time-control" placeholder="开始时间" name="updateTimeStart" style="width: 45%;float: none;">
							<span>&nbsp;至&nbsp;</span>
							<input class="form-control form-time-control" placeholder="结束时间" name="updateTimeEnd" style="width: 45%;float: none;">
						</div>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">是否有交易</label> 
						<select class="form-control" name="isTrans" >
                			<option value=""></option>
			                <option value="Y">是</option>
			                <option value="null">否</option>
            			</select>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">是否有POS</label> 
						<select class="form-control" name="hasPos" >
                			<option value=""></option>
			                <option value="Y">是</option>
			                <option value="N">否</option>
            			</select>
					</div> 
				</div>
			</div>
			<div class="text-center">
	            <button type="button" class="btn btn-primary btn-blue" id="findlist" onclick="submitForm()">查询</button>
	            <button type="reset" class="btn btn-default btn-initial">重置</button> 
	            <button type="button" class="btn btn-info btn-blue" onclick="exportForm('${pageContext.request.contextPath}/oldCustomer/exportCustomerLoss.action')">
				  	<span class="glyphicon glyphicon-save" aria-hidden="true"></span> 导出
				</button>
	        </div>
	        <br>
		</div>
	</form>
</div>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<table id="table" class="table table-responsive coeus-table" >
		<thead>
			<tr>
				<!-- <th data-field="id" data-visible="false" data-align="center">id</th> -->
           		<th data-field="customerNo" data-align="center">商编</th>
                <th data-field="customerName" data-align="center">商户名称</th>
                <th data-field="linkMan" data-align="center">联系人</th>
                <th data-field="customerStatus" data-align="center" data-formatter="toCheckValue">商户状态</th>
                <th data-field="customerLevel" data-align="center" data-formatter="manageValue">商户等级</th>
                <th data-field="customerCheckInfo" data-align="center" data-formatter="toDoValue">是否真实</th>
                <th data-field="bindTime" data-align="center" data-formatter="format">绑定时间</th>
                <th data-field="activateTime" data-align="center" data-formatter="format"> 激活时间</th>
                <th data-field="resultType" data-align="center" data-formatter="toSetValue" >结果类型</th>
                <th data-field="createTime" data-align="center" data-formatter="format">创建时间</th>
                <th data-field="isLoss" data-align="center" data-formatter="customerLoss">是否有流量</th>
                <th data-field="isTrans" data-align="center" data-formatter="isTransFormate">是否有交易</th>
                <th data-field="posCount" data-align="center" >POS数量</th>
                <th data-field="transTime" data-align="center" data-formatter="format">交易时间</th>
                <th data-field="updateTime" data-align="center" data-formatter="format">更新时间</th>
			</tr>
		</thead>
	</table>
</div>
<iframe name="exportResult" src="" width="100%" height="0px"  ></iframe>
</body>
</html>