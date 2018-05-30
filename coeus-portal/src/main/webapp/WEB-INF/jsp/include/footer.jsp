<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-table.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-table-export.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/tableExport.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-fileinput.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap-fileinput-zh.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/initTable.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/initForm.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/js/toastr.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/static/css/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
		
	function submitForm(){
		var oTable = new TableInit();
		oTable.Init();
	}
	
	$(function(){
		//loadTable();
		//1.初始化Table
		var oTable = new TableInit();
		oTable.Init();
	})
	
	function loadTable(){
		$('#table').bootstrapTable('destroy'); 
		$('#table').bootstrapTable({
	        url:"${pageContext.request.contextPath}/test/findList2",//请求数据url
	        queryParamsType : "undefined",   
            queryParams: function queryParams(params) {   //设置查询参数  
            	var d = {};
	    		var t = $(".coeus-form").serializeArray();
	    	    $.each(t, function() {
	    	      d[this.name] = this.value;
	    	    });
              var param = $.extend({},d,{ 
            	  currentPage: params.pageNumber,
            	  showCount: params.pageSize,
                  orderNum : $("#orderNum").val()
              });
              return param;
            }, 
	        //toolbar: "#toolbar",
	        method:'post',
	        sortable: false, 
	        cache:true,
	        toggle:true,
	        striped:true,
	        showHeader : true,
	        contentType:"application/x-www-form-urlencoded",
	        height: 600,
	        pageSize: 10,
	        showColumns : true,
	        showRefresh : true,
	        pagination: true,//分页
	        sidePagination : 'server',//服务器端分页
	        pageNumber : 1,
	        pageList: [5, 10, 25, 50, 100, 200],//分页步进值
	        //search: true,//显示搜索框
	        minimumCountColumns:1,
	        //表格的列
	        idField:"id",
	        switchable:true,
	        showExport: true,                     //是否显示导出
	        exportDataType: "all",              //basic', 'all', 'selected'. 
	        responseHandler:"responseHandler",
	        
	        onLoadError: function () {
	        	 
                alert("数据加载失败！");

            }
	    });
	}
	
	toastr.options.positionClass = 'toast-top-center';
	
</script>

