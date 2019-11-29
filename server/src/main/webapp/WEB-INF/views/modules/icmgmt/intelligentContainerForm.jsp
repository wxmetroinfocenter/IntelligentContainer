<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>智能货柜表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/icmgmt/intelligentContainer/">智能货柜表列表</a></li>
		<li class="active"><a href="${ctx}/icmgmt/intelligentContainer/form?id=${intelligentContainer.id}">智能货柜表<shiro:hasPermission name="icmgmt:intelligentContainer:edit">${not empty intelligentContainer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="icmgmt:intelligentContainer:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="intelligentContainer" action="${ctx}/icmgmt/intelligentContainer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">货柜设备编码：</label>
			<div class="controls">
				<form:input path="deviceId" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货柜名：</label>
			<div class="controls">
				<form:input path="deviceName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">令牌：</label>
			<div class="controls">
				<form:input path="token" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">货柜柜箱表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>货柜柜箱编号</th>
								<th>货柜柜箱状态</th>
								<th>排序</th>
								<th>备注信息</th>
								<shiro:hasPermission name="icmgmt:intelligentContainer:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="intelligentContainerBoxList">
						</tbody>
						<shiro:hasPermission name="icmgmt:intelligentContainer:edit"><tfoot>
							<tr><td colspan="6"><a href="javascript:" onclick="addRow('#intelligentContainerBoxList', intelligentContainerBoxRowIdx, intelligentContainerBoxTpl);intelligentContainerBoxRowIdx = intelligentContainerBoxRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="intelligentContainerBoxTpl">//<!--
						<tr id="intelligentContainerBoxList{{idx}}">
							<td class="hide">
								<input id="intelligentContainerBoxList{{idx}}_id" name="intelligentContainerBoxList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="intelligentContainerBoxList{{idx}}_delFlag" name="intelligentContainerBoxList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="intelligentContainerBoxList{{idx}}_no" name="intelligentContainerBoxList[{{idx}}].no" type="text" value="{{row.no}}" maxlength="32" class="input-small "/>
							</td>
							<td>
								<select id="intelligentContainerBoxList{{idx}}_status" name="intelligentContainerBoxList[{{idx}}].status" data-value="{{row.status}}" class="input-small ">
									<option value=""></option>
									<c:forEach items="${fns:getDictList('ic_box_status')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input id="intelligentContainerBoxList{{idx}}_sort" name="intelligentContainerBoxList[{{idx}}].sort" type="text" value="{{row.sort}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								<textarea id="intelligentContainerBoxList{{idx}}_remarks" name="intelligentContainerBoxList[{{idx}}].remarks" rows="4" maxlength="500" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="icmgmt:intelligentContainer:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#intelligentContainerBoxList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var intelligentContainerBoxRowIdx = 0, intelligentContainerBoxTpl = $("#intelligentContainerBoxTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(intelligentContainer.intelligentContainerBoxList)};
							for (var i=0; i<data.length; i++){
								addRow('#intelligentContainerBoxList', intelligentContainerBoxRowIdx, intelligentContainerBoxTpl, data[i]);
								intelligentContainerBoxRowIdx = intelligentContainerBoxRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="icmgmt:intelligentContainer:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>