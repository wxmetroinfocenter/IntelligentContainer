<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>智能货柜表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/icmgmt/intelligentContainer/">货柜柜箱列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="intelligentContainer" action="${ctx}/icmgmt/intelligentContainer/openbox" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li>货柜内部识别码： ${intelligentContainer.id} </li><li class="clearfix"></li>
			<li>货柜设备编码： ${intelligentContainer.deviceId} </li><li class="clearfix"></li>
			<li>货柜名： ${intelligentContainer.deviceName} </li><li class="clearfix"></li>
			<li>货柜状态： ${fns:getDictLabel(intelligentContainer.status, 'ic_status', '')} </li><li class="clearfix"></li>
		</ul>
		<sys:message content="${message}"/>
	</form:form>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>货柜柜箱编号</th>
				<th>货柜柜箱类型</th>
				<th>货柜柜箱状态</th>
				<th>备注信息</th>
				<shiro:hasPermission name="icmgmt:intelligentContainer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${intelligentContainer.intelligentContainerBoxList}" var="item">
			<tr>
				<td>
					${item.no}
				</td>
				<td>
					${fns:getDictLabel(item.type, 'ic_box_type', '')}
				</td>
				<td>
					${fns:getDictLabel(item.status, 'ic_box_status', '')}
				</td>
				<td>
					${item.remarks}
				</td>
				<shiro:hasPermission name="icmgmt:intelligentContainer:edit"><td>
					<a href="${ctx}/icmgmt/intelligentContainer/openbox?id=${intelligentContainer.id}&no=${item.no}&boxStatus=1" onclick="return confirmx('确认要打开此柜箱吗？', this.href)" class="btn btn-primary">开锁</a>
					&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/icmgmt/intelligentContainer/openbox?id=${intelligentContainer.id}&no=${item.no}&boxStatus=0" class="btn">关锁</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<div class="form-actions">
		<a href="${ctx}/icmgmt/intelligentContainer/" class="btn">返 回</a>
	</div>
</body>
</html>