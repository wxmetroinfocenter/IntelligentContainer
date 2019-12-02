<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>智能货柜表管理</title>
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
		<li class="active"><a href="${ctx}/icmgmt/intelligentContainer/">智能货柜表列表</a></li>
		<shiro:hasPermission name="icmgmt:intelligentContainer:edit"><li><a href="${ctx}/icmgmt/intelligentContainer/form">智能货柜表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="intelligentContainer" action="${ctx}/icmgmt/intelligentContainer/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>货柜内部识别码：
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li>货柜设备编码：
				<form:input path="deviceId" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>货柜名：</label>
				<form:input path="deviceName" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>货柜设备编码</th>
				<th>货柜名</th>
				<th>货柜状态</th>
				<th>令牌</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="icmgmt:intelligentContainer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="intelligentContainer">
			<tr>
				<td><a href="${ctx}/icmgmt/intelligentContainer/form?id=${intelligentContainer.id}">
					${intelligentContainer.id}
				</a></td>
				<td>
					${intelligentContainer.deviceId}
				</td>
				<td>
					${intelligentContainer.deviceName}
				</td>
				<td>${fns:getDictLabel(intelligentContainer.status, 'ic_status', '')}</td>
				<td>
					${intelligentContainer.token}
				</td>
				<td>
					<fmt:formatDate value="${intelligentContainer.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${intelligentContainer.remarks}
				</td>
				<shiro:hasPermission name="icmgmt:intelligentContainer:edit"><td>
					<a href="${ctx}/icmgmt/intelligentContainer/boxlist?id=${intelligentContainer.id}">开锁</a>
    				<a href="${ctx}/icmgmt/intelligentContainer/form?id=${intelligentContainer.id}">修改</a>
					<a href="${ctx}/icmgmt/intelligentContainer/delete?id=${intelligentContainer.id}" onclick="return confirmx('确认要删除该智能货柜表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>