<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html lang="zh-hans">
<head>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">

function rowStyle(row, index) {
    if (index % 2 === 0) {
        return {
            classes: 'active'
        };
    }
    return {};
}

function sub(){
	submitForm();
}

function queryParams(params){
	 return {
         offset: params.offset,  //页码
         limit: params.limit,   //页面大小
         search : params.search, //搜索
         order : params.order, //排序
         ordername : params.sort, //排序
     };
}

function toCheckSource(value,row,index){
	if(value == "HF"){
		return "运营回访";
	}else if(value == "IVR"){
		return "IVR";
	}else if(value == "KF"){
		return "客服来电";
	}else if(value == "WEIXIN"){
		return "验证码登录_公众号";
	}else if(value == "KYSF"){
		return "验证码登陆_卡友商服APP";
	}else if(value == "ACTIVITY"){
		return "营销活动收集";
	}else if(value == "CPKT"){
		return "产品开通_信用卡认证提额";
	}else if(value == "COEUS"){
		return "社群收集";
	}else{
		return value;
	} 
}

function toCheckRole(value,row,index){
	if(value == "LEGAL"){
		return "法人";
	}else if(value == "FINANCE"){
		return "会计";
	}else if(value == "CASHIER"){
		return "收银员";
	}else if(value == "AGENT"){
		return "服务商";
	}else{
		return value;
	} 
}

//转换时间格式
function add0(m){return m<10?'0'+m:m;}
function formatDateTime(value,row,index){
	if(value==null || value==""){
		return value;
	}
	//shijianchuo是整数，否则要parseInt转换
	var time = new Date(value);
	var y = time.getFullYear();
	var m = time.getMonth()+1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
}

function toUpload(){
	location.href ='${pageContext.request.contextPath}/contactInfo/toUpload';
}


function toDoValue(value,row,index){
	/* return '<a class="btn active" href="${pageContext.request.contextPath}/contactInfo/queryDetail.action?id='+value+'">编辑</a>'; */
	return '<a class="btn active" href="#" onclick="updateToAgent('+value+')">标记为服务商</a>';
}

function updateToAgent(value){
	if(confirm("确认标记此商编为服务商？")){
		$.ajax({ 
			url: "${pageContext.request.contextPath}/contactInfo/updateToAgent.action?id="+value, 
			success: function(data){
				alert("设置成功");
	   		},
	   		fail: function(data){
	        	alert("设置失败");
	   		}
		});
	}
}

window.toDoValueEvents = {
	'click .mod': function(e, value, row, index) {
      //修改操作
	  alert(row.id);
    },
	'click .delete' : function(e, value, row, index) {
      //删除操作 
	  alert(e);
      alert(value);
      alert(row);
      alert(index);
    }
}
	
function toAdd(){
	location.href ='${pageContext.request.contextPath}/contactInfo/queryDetail.action';
}
</script>
</head>
<body>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<form action="${pageContext.request.contextPath}/contactInfo/findAllList.action" class="coeus-form form-inline col3" method="post" role="form" id="queryForm">
		<div class="panel panel-primary coeus-primary">
			<div class="panel-body">
				<div class="form-group">
					<div class="input-group">
						<label class="input-group-addon">商编</label>
						<input class="form-control" name="customerNo" onkeyup="value=value.replace(/[^\d]/g,'')" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">电话</label>
						<input class="form-control" name="phone" onkeyup="value=value.replace(/[^\d]/g,'')" />
					</div>
					<div class="input-group">
						<label class="input-group-addon">渠道</label>
						<!-- <input class="form-control" name="source"/> -->
						<select class="form-control"  name="source">
			                <option value=""></option>
			                <option value="HF">运营回访</option>
			                <option value="IVR">IVR</option>
			                <option value="KF">客服来电</option>
			                <option value="WEIXIN">验证码登录_公众号</option>
			                <option value="KYSF">验证码登陆_卡友商服APP</option>
			                <option value="ACTIVITY">营销活动收集</option>
			                <option value="CPKT">产品开通_信用卡认证提额</option>
			                <option value="COEUS">社群收集</option>
            			</select>
					</div>
					<div class="input-group">
						<label class="input-group-addon">更新时间</label> 
						<div class="coeus-time" >
							<input class="form-control form-time-control" placeholder="开始时间" value="<%=DateUtils.formatDate(new Date(),"yyyy-MM-dd 00:00")%>" name="createTimeStart" style="width: 45%;float: none;">
							<span>&nbsp;至&nbsp;</span>
							<input class="form-control form-time-control" placeholder="结束时间" value="<%=DateUtils.formatDate(new Date(),"yyyy-MM-dd 23:59")%>" name="createTimeEnd" style="width: 45%;float: none;">
						</div>
					</div> 
				</div>
			</div>
			<div class="text-center">
	            <button type="button" class="btn btn-primary btn-blue" id="findlist" onclick="sub()">查询</button>
	            <button type="reset" class="btn btn-default btn-initial">重置</button> 
	            <!-- <button type="button" class="btn btn-primary btn-blue" onclick="toAdd()">新增</button> -->
	        </div>
	        <br>
		</div>
	</form>
</div>
<div class="panel-body panel-body-noheader panel-body-noborder" style="width: auto; height: auto;">
	<table id="table" class="table table-responsive coeus-table" >
		<thead>
			<tr>
           		<th data-field="customerNo" data-align="center">商编</th>
           		<th data-field="customerRole" data-align="center" data-formatter="toCheckRole">身份</th>
           		<th data-field="phone" data-align="center">电话</th>
           		<th data-field="source" data-align="center" data-formatter="toCheckSource">渠道</th>
                <th data-field="updateTime" data-align="center" data-formatter="formatDateTime">创建时间</th>
                <th data-field="id" data-align="center" data-formatter="toDoValue" data-events="toDoValueEvents">操作</th>
			</tr>
		</thead>
	</table>
</div>
</body>
</html>