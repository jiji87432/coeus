var TableInit = function () {
 var oTableInit = new Object();
 //初始化Table
 oTableInit.Init = function () {
	 var action = $(".coeus-form").attr("action");
	 var method = $(".coeus-form").attr("method") == "" ? "get":$(".coeus-form").attr("method");
	 //先销毁表格  
     $('.coeus-table').bootstrapTable('destroy');
	 $('.coeus-table').bootstrapTable({
		 url: action,         //请求后台的URL（*）
		 method: method,                      //请求方式（*）
		 toolbar: '#toolbar',                //工具按钮用哪个容器
		 striped: true,                      //是否显示行间隔色
		 cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		 pagination: true,                   //是否显示分页（*）
		 sortable: false,                     //是否启用排序
		 sortOrder: "asc",                   //排序方式
		 queryParamsType : "undefined", 
		 queryParams: oTableInit.queryParams,//传递参数（*）
		 sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
		 pageNumber: 1,                       //初始化加载第一页，默认第一页
		 pageSize: 10,                       //每页的记录行数（*）
		 pageList: [5, 10, 20, 50, 100, 200],        //可供选择的每页的行数（*）
		 //search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		 contentType: "application/x-www-form-urlencoded",
		 //strictSearch: true,
		 showColumns: true,                  //是否显示所有的列
		 showRefresh: true,                  //是否显示刷新按钮
		 minimumCountColumns: 1,             //最少允许的列数
		 clickToSelect: true,                //是否启用点击选中行
		 height: 650,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		 uniqueId: "id",                     //每一行的唯一标识，一般为主键列
		 showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
		 cardView: false,                    //是否显示详细视图
		 detailView: false,                   //是否显示父子表
		 rowStyle: function (row, index) {
			 var classesArr = ['active', ''];
			 var strclass = "";
			 if (index % 2 === 0) {//偶数行
				 strclass = classesArr[0];
			 } else {//奇数行
				 strclass = classesArr[1];
			 }
			 return { classes: strclass };
		 },//隔行变色
		 showExport: true,                    //是否显示导出
		 exportDataType: "all",              //basic', 'all', 'selected'. 
		 responseHandler:oTableInit.responseHandler
	 });

 };

 //得到查询的参数
 oTableInit.queryParams = function(params) {
		var d = {};
		var t = $(".coeus-form").serializeArray();
		$.each(t, function() {
			d[this.name] = this.value;
		});
		var param = $.extend({}, d, {
			currentPage : params.pageNumber,
			showCount : params.pageSize
		});
		return param;
	};
 

	oTableInit.responseHandler = function(res) {
		if (res.code != 20) {
			toastr.error(" code[" + res.code + " " + res.type + " ] "
							+ res.msg);
		} else {
			if (res.data.totalResult > 0) {
				return {
					"rows" : res.data.object,
					"total" : res.data.totalResult
				}
			} else {
				return {
					"rows" : [],
					"total" : 0
				}
			}
		}
	};
 return oTableInit;
};