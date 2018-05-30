<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<html lang="zh-hans">
<head>
<%@ include file="../include/footer.jsp"%>
<script type="text/javascript">

</script>
</head>
<body>
<c:if test="${commList.size()>0}">

<table cellpadding="0" cellspacing="1" class="table table-responsive coeus-table">
			<tr>
				<th data-align="center">商编</th>
           		<th data-align="center" >商户名称</th>
           		<th data-align="center">借记卡费率</th>
           		<th data-align="center" >贷记卡费率</th>
                <th data-align="center">商户状态</th>
                <th data-align="center">真实性</th>
                <th data-align="center" >是否大POS商户</th>
                <th data-align="center" >商户等级</th>
				<th data-align="center" >服务商编号</th>
				<th data-align="center">服务商名称</th>
				<th data-align="center" >卡友APP绑定</th>
				<th data-align="center" >是否绑定公众号</th>
				<th data-align="center" >月交易额</th>
			</tr>
			<c:forEach items="${commList }" var="so" varStatus="vs"> 
				<tr>
				<td>${so.CUSTOMERNO }</td>
				<td>${so.CUSTOMERFULLNAME }</td>
				<td>${so.debitcard }</td>
				<td>${so.creditcard }</td>
				<td>
					<c:choose>
	             		<c:when test="${so.STATUS=='TRUE' }">可用</c:when>
	             		<c:when test="${so.STATUS=='FALSE' }">禁用</c:when>
	             		<c:when test="${so.STATUS=='INIT' }">待提交</c:when>
	             		<c:when test="${so.STATUS=='WAIT_AUDIT' }">待审核</c:when>
	             		<c:when test="${so.STATUS=='AUDITING' }">审核中</c:when>
	             		<c:when test="${so.STATUS=='AUDIT_REJECT' }">审核拒绝</c:when>
	             		<c:when test="${so.STATUS=='DELETE' }">删除</c:when>
	             		<c:when test="${so.STATUS=='SUSPEND' }">挂起</c:when>
	             		<c:when test="${so.STATUS=='PRETRANS' }">预交易</c:when>
	             		<c:when test="${so.STATUS=='WAIT_OPEN' }">待开通</c:when>
            		</c:choose>	
				</td>			
				<td>${so.REALYTYPE }</td>
				<td>${so.isPos }</td>
				<td>${so.CUSTOMERLEVEL }</td>
				<td>${so.AGENTNO }</td>
				<td>${so.AGENTFULLNAME }</td>
				<td>${so.app }</td>
				<td>${so.wx }</td>
				<td>${so.sumamount }</td>
				</tr>

			</c:forEach>
		</table>
		</c:if>
		<c:if test="${commList == null}" >
		<div>
			<div>没有找到符合条件的记录．</div>
		</div>
	</c:if>

</body>
</html>