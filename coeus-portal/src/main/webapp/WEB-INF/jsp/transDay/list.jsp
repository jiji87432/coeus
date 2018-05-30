<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html lang="zh-hans">
<head>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">

function rowStyle(row, index) {
    if (index % 2 === 0) {
        return {
            classes: 'active'
        };
    }
    return {};
}

function sub(){
	submitForm();
}

function queryParams(params){
	 return {
         offset: params.offset,  //页码
         limit: params.limit,   //页面大小
         search : params.search, //搜索
         order : params.order, //排序
         ordername : params.sort, //排序
     };
}

function toDoValue(value,row,index){
	var code = null;
	if(value=="TRUE" || value == "true"){
		code="是";
	}else{
		code="否";
	}
	return code;
}

//转换时间格式
function add0(m){return m<10?'0'+m:m;}
function formatDate(shijianchuo)
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

</script>
</head>
<body>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="${pageContext.request.contextPath}/transDay/findAllList.action" class="coeus-form form-inline col3" method="post" role="form" id="queryForm">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">商编</label>
						<input class="form-control" name="customerNo" onkeyup="value=value.replace(/[^\d]/g,'')" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">代理商编</label>
						<input class="form-control" name="agentNo" onkeyup="value=value.replace(/[^\d]/g,'')" />
					</div>
				</div>
				<div class="input-group">
					<label class="input-group-addon">创建时间</label> 
					<div class="coeus-time" >
						<input class="form-control form-time-control" placeholder="开始时间" value="<%=DateUtils.formatDate(new Date(),"yyyy-MM-dd 00:00")%>" name="createTimeStart" style="width: 45%;float: none;">
						<span>&nbsp;至&nbsp;</span>
						<input class="form-control form-time-control" placeholder="结束时间" value="<%=DateUtils.formatDate(new Date(),"yyyy-MM-dd 23:59")%>" name="createTimeEnd" style="width: 45%;float: none;">
					</div>
				</div> 
			</div>
			<div class="text-center">
	            <button type="button" class="btn btn-primary btn-blue" id="findlist" onclick="sub()">查询</button>
	            <button type="reset" class="btn btn-default btn-initial">重置</button> 
	        </div>
	        <br>
		</div>
	</form>
</div>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<table id="table" class="table table-responsive coeus-table" >
		<thead>
			<tr>
				<th data-field="id" data-align="center">编号</th>
           		<th data-field="customerNo" data-align="center">商编</th>
           		<th data-field="customerName" data-align="center">商户名称</th>
           		<th data-field="agentNo" data-align="center">代理商</th>
           		<th data-field="transAmount" data-align="center">交易金额</th>
           		<th data-field="transNum" data-align="center">交易订单数</th>
           		<th data-field="orderTime" data-align="center" data-formatter="formatDate">交易日期</th>
           		<th data-field="createTime" data-align="center" data-formatter="formatDate">创建时间</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>