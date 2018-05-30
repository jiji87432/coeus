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

window.toDoValueEvents = {
	'click .mod': function(e, value, row, index) {
      //修改操作
	  alert(row.id);
    },
	'click .delete' : function(e, value, row, index) {
      //删除操作 
	  alert(e);
      alert(value);
      alert(row);
      alert(index);
    }
}

</script>
</head>
<body>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="${pageContext.request.contextPath}/test/findList2.action" class="coeus-form form-inline col3" method="post" role="form" id="queryForm">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">你好</label>
						<input class="form-control" name="billNo" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">你好</label>
						<input class="form-control" name="salesBillNo"/>
					</div>
					<div class="input-group">
						<label class="input-group-addon">你好</label> 
						<input class="form-control" name="manNo"/>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">时间</label> 
						<!-- <input type="text" class="form-control form-time-control" id="demo" > -->
						<div class="coeus-time" >
							<input class="form-control form-time-control" placeholder="开始时间" name="createTimeStart" style="width: 45%;float: none;">
							<span>&nbsp;至&nbsp;</span>
							<input class="form-control form-time-control" placeholder="结束时间" name="createTimeEnd" style="width: 45%;float: none;">
						</div>
					</div> 
				</div>
			</div>
			<div class="text-center">
	            <button type="button" class="btn btn-primary btn-blue" id="findlist" onclick="sub()">提交</button>
	            <button type="reset" class="btn btn-default btn-initial">你好</button> 
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
           		<th data-field="name" data-align="center">name</th>
                <th data-field="value" data-align="center" data-formatter="FormatDateTime">value</th>
                <th data-field="id" data-align="center" data-formatter="toDoValue" data-events="toDoValueEvents">操作</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>