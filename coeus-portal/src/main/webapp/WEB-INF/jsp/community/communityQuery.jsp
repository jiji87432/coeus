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



function queryParams(params){
	 return {
         offset: params.offset,  //页码
         limit: params.limit,   //页面大小
         search : params.search, //搜索
         order : params.order, //排序
         ordername : params.sort, //排序
     };
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

function toUpload(){
	var flag =0;
	var file  =  document.getElementById('uploadCustomerNo').value 
	
	if(file == ""){
		alert("请选择导入文件");
		flag = 1 ;
	}
	if(flag==0){
		$("#queryForm").attr("action", "communityInformationQuery.action");
		$("#queryForm").submit();
		
	}
}

function queryCust(){
		$("#queryForm").attr("action", "communityQueryByCustNo.action");
		$("#queryForm").attr("target", "queryResult");
		$("#queryForm").submit();
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
<div class="panel panel-primary coeus-primary" style="width: auto; height: auto;">
	<form action="" class="coeus-form form-inline col3" enctype="multipart/form-data" method="post" role="form" id="queryForm">
	<table id="tzd" class="table table-responsive coeus-table"" cellpadding="0" cellspacing="0">
			<tr>
				<th align="center">导入商编：</th>
				<td colspan="3" width="20%">
					<input type="file" name="uploadCustomerNo" id="uploadCustomerNo" id="input" style="width: 52%;height: float: none;" />
				</td>
				
				<th align="center">商户编号：</th>
				<td width="20%">
					<input class="form-control" name="customerNo" onkeyup="value=value.replace(/[^\d]/g,'')" />
				</td>
				
				<th align="center">交易时间：</th>
				<td style="border-style: none" colspan="2">
					<input type="text" id="ordertime" title='ordertime' name="ordertime" class="Wdate width80" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
				</td>
			</tr>
		</table>
		<center>
			<a href="${pageContext.request.contextPath}/static/template/customerNo.xlsx" class="form-control" style="border:none;color: #427cff">下载导入模板</a>
			<input type="button" class="global_btn" value="导入并导出" onclick="toUpload();" />
			<input type="button" class="global_btn" value="查询" onclick="queryCust();" />
		    <input type="reset" class="global_btn"value="重 置" />
		</center>
	</form>
</div>
<iframe name="queryResult" src="" width="100%" height="550px" scrolling="no" frameborder="0" noresize ></iframe>
</body>
</html>