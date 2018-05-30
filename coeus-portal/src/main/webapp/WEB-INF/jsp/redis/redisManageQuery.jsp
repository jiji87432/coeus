<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html lang="zh-hans">
<head>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">

	function sub(){
		if($("#redisKey").val() == ""){
			alert("请输入redis key");
		}else{
			window.location.href='${ctx}/redisManage/query.action?redisKey='+$("#redisKey").val();
		}
	}
</script>

</head>
<body>
	<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
		<form method="post" target="queryResult">
			<div class="panel panel-primary coeus-primary">
				<div class="panel-body">
					<div class="form-group">
						<div class="input-group">
							<label class="input-group-addon">redisKey</label>
							<input class="form-control" name="redisKey" id="redisKey"/>
						</div>
						</div>
					</div>
				<div class="text-center">
		            <button type="button" class="btn btn-primary btn-blue" onclick="sub()">提交</button>
		            <button type="reset" class="btn btn-default btn-initial">重置</button> 
		        </div>
		        <br>
			</div>
		</form>
	</div>
	<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
		<table border='1' style="width: 100%; height: 100%;">
			<tr>
				 <th>用途</th>
				 <th>KEY</th>
			</tr>
			<tr>
				<td>支持SIM卡监控供货商</td>
				<td>SIM_MONITOR_SUPPLIERS</td>
			</tr>
		</table>
	</div>
</body>
</html>