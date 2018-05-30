<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html lang="zh-hans">
<head>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">
$(function(){
	//0.初始化fileinput
    var oFileInput = new FileInput();
    oFileInput.Init("txt_file", "/api/OrderApi/ImportOrder");
})



//初始化fileinput
var FileInput = function () {
    var oFile = new Object();

    //初始化fileinput控件（第一次初始化）
    oFile.Init = function(ctrlName, uploadUrl) {
    var control = $('#' + ctrlName);

    //初始化上传控件的样式
    control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: uploadUrl, //上传的地址
        allowedFileExtensions: ['xls', 'xlsx', 'png'],//接收的文件后缀
       // showUpload: true, //是否显示上传按钮
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式     
        //dropZoneEnabled: false,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount: 1, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        //validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
    });

    //导入文件上传完成之后的事件
    $("#txt_file").on("fileuploaded", function (event, data, previewId, index) {
        $("#myModal").modal("hide");
        var data = data.response.lstOrderImport;
        if (data == undefined) {
            toastr.error('文件格式类型不正确');
            return;
        }
        //1.初始化表格
        var oTable = new TableInit();
        oTable.Init(data);
        $("#div_startimport").show();
    });
}
    return oFile;
};
</script>
</head>
<body>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="${pageContext.request.contextPath}/test/findList2" class="coeus-form form-inline col3" method="post" role="form" id="queryForm">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">id</label>
						<input class="form-control" name="id" value="${testCoeus.id }" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">name</label>
						<input class="form-control" name="name" value="${testCoeus.name }"/>
					</div>
					<div class="input-group">
						<label class="input-group-addon">value</label> 
						<input class="form-control" name="value" value="${testCoeus.value }"/>
					</div> 
				</div>
			</div>
			<div class="text-center">
	            <button type="button" class="btn btn-primary btn-blue" id="findlist" onclick="sub()">提交</button>
	            <button type="reset" class="btn btn-default btn-initial">你好</button> 
	        </div>
		</div>
	</form>
</div>

<!-- <div class="tabs-panels" style="height: auto; width: auto;">
<div class="panel" style="display: block; width: auto;"> -->
	<div class="panel panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
		<form class="coeus-form form-inline col3" role="form">
			<div class="panel panel-primary coeus-panel-primary">
				<div class="panel-heading">
					<h4>
						订单信息
					</h4>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<div class="input-group">
							<label class="input-group-addon">id</label>
							<input class="form-control" name="id" value="${testCoeus.id }" readonly="readonly"/>
						</div>
						<div class="input-group">
							<label class="input-group-addon">name</label>
							<input class="form-control" name="name" value="${testCoeus.name }" />
						</div>
						<div class="input-group">
							<label class="input-group-addon">value</label> 
							<input class="form-control" name="value" value="${testCoeus.value }" readonly="readonly"/>
						</div> 
						<div class="input-group">
							<label class="input-group-addon">value</label> 
							<input type="file" name="myfile" class="form-control" value=""/>
						</div> 
						
					</div>
					<div>
						<a href="~/Data/ExcelTemplate/Order.xlsx" class="form-control" style="border:none;">下载导入模板</a>
	                  <input type="file" name="txt_file" id="txt_file" multiple class="file-loading" />
					</div>
				</div>
				
			</div>
		</form>
	</div>
	<!-- <div class="modal " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"> -->
	     <!--  <div class="modal-dialog modal-lg" role="document">
	          <div class="modal-content">
	              <div class="modal-header">
	                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                  <h4 class="modal-title" id="myModalLabel">请选择Excel文件</h4>
	              </div>
	              <div class="modal-body">
	                  <a href="~/Data/ExcelTemplate/Order.xlsx" class="form-control" style="border:none;">下载导入模板</a>
	                  <input type="file" name="txt_file" id="txt_file" multiple class="file-loading" />
	              </div>
	           </div>
	      </div> -->
	<!-- </div> -->
<!-- </div>
</div> -->
</body>
</html>