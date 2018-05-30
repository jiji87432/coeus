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


//转换时间格式
function add0(m){return m<10?'0'+m:m;}
function formatDateTime(value,row,index){
	if(value==null || value==""){
		return value;
	}
	//shijianchuo是整数，否则要parseInt转换
	var time = new Date(value);
	var y = time.getFullYear();
	var m = time.getMonth()+1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}

function toDoValue(value,row,index){
	/* return '<a class="btn active" href="${pageContext.request.contextPath}/contactType/queryDetail.action?id='+value+'">编辑</a>'; */
	return "-";
}

function toDoValueValid(value,row,index){
	if(value == 1){
		return "是";
	}else{
		return "否";
	}
}
	
function toAdd(){
	location.href ='${pageContext.request.contextPath}/contactType/queryDetail.action';
}

</script>
</head>
<body>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="${pageContext.request.contextPath}/contactType/findAllList.action" class="coeus-form form-inline col3" method="post" role="form" id="queryForm">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">名称</label>
						<input class="form-control" name="name" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">简码</label>
						<input class="form-control" name="keyword"/>
					</div>
					<div class="input-group">
						<label class="input-group-addon">是否有效</label>
						<select class="form-control"  name="type">
                			<option value=""></option>
			                <option value="1">是</option>
			                <option value="0">否</option>
            			</select>
					</div>
				</div>
			</div>
			<div class="text-center">
	            <button type="button" class="btn btn-primary btn-blue" id="findlist" onclick="sub()">查询</button>
	            <button type="reset" class="btn btn-default btn-initial">重置</button> 
	            <!-- <button type="button" class="btn btn-primary btn-blue" onclick="toAdd()">新增</button> -->
	        </div>
	        <br>
		</div>
	</form>
</div>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<table id="table" class="table table-responsive coeus-table" >
		<thead>
			<tr>
           		<th data-field="name" data-align="center">名称</th>
           		<th data-field="keyword" data-align="center">简码</th>
           		<th data-field="rank" data-align="center">权重(大在前)</th>
           		<th data-field="type" data-align="center" data-formatter="toDoValueValid">是否有效</th>
                <th data-field="createTime" data-align="center" data-formatter="formatDateTime">创建时间</th>
                <th data-field="updateTime" data-align="center" data-formatter="formatDateTime">修改时间</th>
                <th data-field="remark" data-align="center">备注</th>
                <th data-field="id" data-align="center" data-formatter="toDoValue" data-events="toDoValueEvents">操作</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>