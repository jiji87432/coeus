<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html lang="zh-hans">
<head>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">
	function submitSub(){
		var month = $("#month").val();
		if(month == null || month == ""){
			toastr.info("请输入想要查询的月份");
			return;
		}
		if(month < 1){
			toastr.info("月份数大于等于1");
			return;
		}
		$.ajax({
		  type: 'POST',
		  url: '${pageContext.request.contextPath}/dataQuery/activateRetaine.action',
		  data: {month:month},
		  beforeSend:function(xhr){
			  $("#loadingModal").modal("show");
			  $("#loadingModal").modal({backdrop: 'static', keyboard: false});
		  },
		  success: (function(data){
			  // console.log(data);
			  var res = $.parseJSON(data);
			  if (res.code != 20) {
					$("#loadingModal").modal("hide");
					toastr.error(" code[" + res.code + " " + res.type + " ] "
									+ res.msg);
				} else {
					 $("#loadingModal").modal("hide");
					 $.each(res.data,function(key,value){  
				        $("#"+key).val(this);
					  }); 
				}
		  })
		});
	}
</script>
</head>
<body>

<div class="modal hade" id="loadingModal" aria-hidden="true" data-keyboard="false">
	<div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
		<div class="progress progress-striped active" style="margin-bottom: 0;">
			<div class="progress-bar" style="width: 100%;"></div>
		</div>
		<h5>正在努力疯狂的查询中...</h5>
	</div>
</div>

<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="#" class="coeus-form form-inline col3" method="post" role="form" id="queryForm">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">月份</label>
						<input class="form-control" id="month" value="1" />
					</div>
				</div>
			</div>
			<div class="text-center">
	            <button type="button" class="btn btn-primary btn-blue" onclick="submitSub()">查询</button>
	            <button type="reset" class="btn btn-default btn-initial">重置</button> 
	        </div>
		</div>
	</form>
</div>

<div class="panel panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form class="coeus-form form-inline col3" role="form">
		<div class="panel panel-primary coeus-panel-primary">
			<div class="panel-heading">
				<h4>
					留存商户数据
				</h4>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">留存时间</label>
						<input class="form-control" id="retainedTime" readonly="readonly"/>
					</div>
					<div class="input-group">
						<label class="input-group-addon">活跃商户</label>
						<input class="form-control" id="totalCount" readonly="readonly" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">留存商户</label> 
						<input class="form-control" id="retained" readonly="readonly"/>
					</div> 
					<div class="input-group">
						<label class="input-group-addon">交易额</label> 
						<input class="form-control" id="transAmount" readonly="readonly" />
					</div> 
				</div>
			</div>
		</div>
		
		<div class="panel panel-primary coeus-panel-primary">
			<div class="panel-heading">
				<h4>
					激活商户数据
				</h4>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">激活时间</label>
						<input class="form-control" id="activiteTime" readonly="readonly"/>
					</div>
					<div class="input-group">
						<label class="input-group-addon">激活商户</label>
						<input class="form-control" id="activiteCount" readonly="readonly" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">交易额</label> 
						<input class="form-control" id="activiteTransAmount" readonly="readonly" />
					</div> 
				</div>
			</div>
		</div>
	</form>
</div>
</body>
</html>