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
// 	return '<a class="btn active" href="${pageContext.request.contextPath}/test/queryDetail?id='+value+'">编辑</a>'+
// 	 '<a class="btn active" href="#">准入</a><button class="btn active mod" href="#">删除</button>';

	var code = null;
	if(value=="TRUE" || value == "true"){
		code="是";
	}else{
		code="否";
	}
	return code;
}

</script>
</head>
<body>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="${pageContext.request.contextPath}/dailyCount/findAllList.action" class="coeus-form form-inline col3" method="post" role="form" id="queryForm">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">日期</label>
						<!-- <input class="form-control form-time-control" data-date-format="dd-MM-yyyy" name="dailyDate" style="width: 45%;float: none;"> -->
						<input class="form-control" name="dailyDate" />
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
           		<th data-field="dailyDate" data-align="center">日期</th>
           		<th data-field="weixin" data-align="center">微信活跃</th>
           		<th data-field="weixinAll" data-align="center">整体微信商户数</th>
           		<th data-field="weixinPos" data-align="center">POS微信商户数</th>
           		<th data-field="app" data-align="center">APP活跃</th>
           		<th data-field="merchant" data-align="center">商户后台活跃</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>