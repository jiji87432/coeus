<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html lang="zh-hans">
<head>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">
$(function(){
	
})

	//保存表单
	function mySubmit() {
		if (checkInput()) {
			if(confirm("确认提交该信息吗?")){
				$("#form1").attr("action", "${ctx}/redisManage/saveRedisManage.action");
            	$("#form1").submit();
			}
		}
	}
	
	//删除表单
	function myDel() {
		if (checkInput()) {
			if(confirm("确认删除该信息吗?")){
				$("#form1").attr("action", "${ctx}/redisManage/deleteRedisMange.action");
	        	$("#form1").submit();
			}
		}
	}

	//校验必填项
	function checkInput() {
		var redisKey = $("#redisKey").val();
		var redisValue = $("#redisValue").val();
		if(redisKey == null || redisKey == ""){
			alert("redisKey不能为空");
			return false;
		}
		if(redisValue == null || redisValue == ""){
			alert("redisValue不能为空");
			return false;
		}
		if($("#changeSecond").val() == "1"){
			if($("#seconds").val() == null || $("#seconds").val() == ""){
				alert("请输入自定义过期时间");
				return false;
			}
		}
		return true;
	}




function changeSeconds(){
	if($("#changeSecond").val() == "1"){
		$("#seconds").attr("readonly","");
		$("#seconds").val("");
	}else{
		$("#seconds").val(0);
		$("#seconds").attr("readonly","readonly");
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
						<label class="input-group-addon">redisKey：</label>
						<input class="form-control" id="redisKey" name="redisKey" value="${redisKey}" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">redisValue：</label>
						<input class="form-control" id="redisValue" name="redisValue" value="${redisValue }"/>
					</div>
					<div class="input-group">
						<label class="input-group-addon">失效时间：</label> 
						<select id="changeSecond" id="changeSecond" name="changeSecond" style="width:140px" onchange="changeSeconds()">
							<option value="0">不限</option>
					   		<option value="1">自定义</option>
						</select>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">时效时间设置(秒)：</label>
						<input class="form-control" id="seconds" name="seconds" readonly="readonly"
						onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/D/g,'')}"
						onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/D/g,'')}"/>
					</div>
				</div>
			</div>
			<div class="text-center">
	            <button type="button" class="btn btn-primary btn-blue" id="findlist" onclick="mySubmit()">保存</button>
	            <button type="button" class="btn btn-primary btn-blue" id="findlist" onclick="myDel()">删除</button>
	            <button type="button" class="btn btn-primary btn-blue" id="findlist" onclick="location.href='javascript:history.go(-1);'">返回</button>
	        </div>
		</div>
	</form>
</div>

</body>
</html>