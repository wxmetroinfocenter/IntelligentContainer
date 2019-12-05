<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>柜箱开锁表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/icmgmt/intelligentContainerBoxOpening/">柜箱开锁表列表</a></li>
		<shiro:hasPermission name="icmgmt:intelligentContainerBoxOpening:edit"><li><a href="${ctx}/icmgmt/intelligentContainerBoxOpening/form">柜箱开锁表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="intelligentContainerBoxOpening" action="${ctx}/icmgmt/intelligentContainerBoxOpening/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>货柜内部识别码：</label>
				<form:input path="icid" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>货柜柜箱编号：</label>
				<form:input path="no" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>货柜内部识别码</th>
				<th>货柜柜箱编号</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="icmgmt:intelligentContainerBoxOpening:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="intelligentContainerBoxOpening">
			<tr>
				<td><a href="${ctx}/icmgmt/intelligentContainerBoxOpening/form?id=${intelligentContainerBoxOpening.id}">
					${intelligentContainerBoxOpening.icid}
				</a></td>
				<td>
					${intelligentContainerBoxOpening.no}
				</td>
				<td>
					<fmt:formatDate value="${intelligentContainerBoxOpening.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${intelligentContainerBoxOpening.remarks}
				</td>
				<shiro:hasPermission name="icmgmt:intelligentContainerBoxOpening:edit"><td>
    				<a href="${ctx}/icmgmt/intelligentContainerBoxOpening/form?id=${intelligentContainerBoxOpening.id}">修改</a>
					<a href="${ctx}/icmgmt/intelligentContainerBoxOpening/delete?id=${intelligentContainerBoxOpening.id}" onclick="return confirmx('确认要删除该柜箱开锁表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>