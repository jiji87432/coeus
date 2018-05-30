<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<html lang="zh-hans">
<head>
</head>
<body>
	<h1>${hello }</h1>
	<div class="alert alert-success" role="alert">${hello }</div>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="findStoreOutBill" class="coeus-form form-inline col3" method="post" role="form" id="queryForm">
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
				</div>
			</div>
			<div class="text-center">
	            <button type="button" class="btn btn-primary btn-blue" id="findlist">你好</button>
	            <button type="reset" class="btn btn-default btn-initial">你好</button> 
	        </div>
	        <br>
		</div>
	</form>
</div>

<%@ include file="include/footer.jsp"%>
</body>
</html>