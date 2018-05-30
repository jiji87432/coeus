<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html lang="zh-hans">
<head>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">
	
	function query()
	{
		var sqlScript = $("#sqlScript").val();
		if(sqlScript.trim() == ""){
			dialogMessage("执行语句不能为空！");
			return;
		}
		if(confirm("确认提交该信息吗?")){
			$("#form1").attr("action", "${ctx}/commonScript/commonQuery.action");
        	$("#form1").submit();
		}
	}
</script>

</head>
<body>
	<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form id="form1" action="" method="post" enctype="multipart/form-data"  role="form">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">SQL：</label>
						<textarea style="width:500px;height:300px;" id="sqlScript" name="sqlScript" >
						</textarea>
						<label class="input-group-addon">仅支持查询*</label>
					</div>
				</div>
			</div>
			<div class="text-center">
	            <button type="button" class="btn btn-primary btn-blue" id="findlist" onclick="query()">保存</button>
	        </div>
		</div>
	</form>
</div>
</body>
<script type="text/javascript">
	
</script>
</html>
