<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html lang="zh-hans">
<head>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">
$(
	function(){
		$("#param4").find("option[value=${contactType.type}]").attr("selected", true);
	}
)
function sub(){
	if($("#param1").val() == null || $("#param1").val() == ''){
		alert("请输入名称");
		return;
	}
	if($("#param2").val() == null || $("#param2").val() == ''){
		alert("请输入简码");
		return;
	}
	if($("#param3").val() == null || $("#param3").val() == ''){
		alert("请输入权重");
		return;
	}
	$("#queryForm").submit();
}
</script>
</head>
<body>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="${pageContext.request.contextPath}/contactType/update.action" class="coeus-form form-inline col3" method="get" role="form" id="queryForm">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">ID</label>
						<input class="form-control" name="id" value="${contactType.id }" readonly="readonly"/>
					</div>
					<div class="input-group">
						<label class="input-group-addon">名称</label>
						<input class="form-control" name="name" id="param1"  value="${contactType.name }"/>
					</div>
					<div class="input-group">
						<label class="input-group-addon">简码(研发分配，不要修改)</label> 
						<input class="form-control" name="keyword" id="param2" value="${contactType.keyword }" placeholder="最好是英文大写字母，不强求"/>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">权重(大在前)</label> 
						<input class="form-control" name="rank" id="param3" value="${contactType.rank }" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入数字"/>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">是否有效</label> 
						<select class="form-control"  name="type" id="param4" value="${contactType.type }">
			                <option value="0">否</option>
			                <option value="1">是</option>
            			</select>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">备注</label> 
						<input class="form-control" name="remark" value="${contactType.remark }"/>
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