<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html lang="zh-hans">
<head>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">
function sub(){
	if($("#param1").val() == null || $("#param1").val() == ''){
		alert("请输入月份");
		return;
	}
	// 设置为正在查询的状态
	$("#findlist").html('查询中，请稍候');
	$("#findlist").removeAttr("onclick"); 
	
	$("#activeNum").val("");
	$("#activetransNum").val("");
	$("#activeAmount").val("");
	
	$("#newNum").val("");
	$("#newActiveNum").val("");
	$("#newActivetransNum").val("");
	$("#newActiveAmount").val("");
	
	/* $("#newJihuoNum").val("");
	$("#newJihuoPosNum").val(""); */
	$.ajax({ 
		url: "${pageContext.request.contextPath}/newOpen/getDetail.action?dateParam="+$("#param1").val(), 
		success: function(data){
			// 设置为查询完成的状态
    		$("#findlist").html('查询');
    		$("#findlist").attr("onclick","sub()"); 
        	var result = eval('(' + data + ')');
        	if(result.code != null && result.code == "20"){
        		$("#activeNum").val(result.data.activeNum);
        		$("#activetransNum").val(result.data.activetransNum);
        		$("#activeAmount").val(result.data.activeAmount);
        		$("#newNum").val(result.data.newNum);
        		$("#newActiveNum").val(result.data.newActiveNum);
        		$("#newActivetransNum").val(result.data.newActivetransNum);
        		$("#newActiveAmount").val(result.data.newActiveAmount);
        		/* $("#newJihuoNum").val(result.data.newJihuoNum);
        		$("#newJihuoPosNum").val(result.data.newJihuoPosNum); */
        	}else{
        		alert("查询失败");
        	}
   		},
   		fail: function(data){
   		// 设置为查询完成的状态
   			$("#findlist").html('查询');
   			$("#findlist").attr("onclick","sub()"); 
        	alert(data);
   		}
	});
}
</script>
</head>
<body>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="" class="coeus-form form-inline col3" method="post" role="form" id="queryForm">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">月份</label>
						<input class="form-control" name="startDate" id="param1" placeholder="例如：201711"/>
					</div>
				</div>
			</div>
			<div class="text-center">
	            <button type="button" class="btn btn-primary btn-blue" name="findlist" id="findlist" onclick="sub()">查询</button>
	        </div>
		</div>
	</form>
</div>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="" class="coeus-form form-inline col3" method="" role="form" id="">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">活跃商户数</label>
						<input class="form-control" name="activeNum" id="activeNum" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">活跃商户交易笔数</label>
						<input class="form-control" name="activetransNum" id="activetransNum" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">活跃商户交易金额</label>
						<input class="form-control" name="activeAmount" id="activeAmount" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">新入网商户数</label>
						<input class="form-control" name="newNum" id="newNum" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">新入网活跃商户数</label>
						<input class="form-control" name="newActiveNum" id="newActiveNum" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">新入网活跃商户交易笔数</label>
						<input class="form-control" name="newActivetransNum" id="newActivetransNum" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">新入网活跃商户交易金额</label>
						<input class="form-control" name="newActiveAmount" id="newActiveAmount" />
					</div>
					<!-- <div class="input-group">
						<label class="input-group-addon">新入网当月激活商户数</label>
						<input class="form-control" name="newJihuoNum" id="newJihuoNum" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">新增激活商户数</label>
						<input class="form-control" name="newJihuoPosNum" id="newJihuoPosNum" />
					</div> -->
				</div>
			</div>
		</div>
	</form>
</div>

</body>
</html>