<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>院校返款政策</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- Tab 分页样式 -->
	<jc:plugin name="tab" />
	<!--  分页 -->
	<jc:plugin name="page" />
	<!--  GridView  -->
	<jc:plugin name="ajaxgridview" />
	<!--  院校返款政策相关常量 -->
	<jc:plugin name="enum.academy_rebate_policy" />
	<script type="text/javascript">
	function getPolicyDialogData()
	{
		var param = {};
		
		var id = $('#policyid').val();
		var code = $('#policycode').val();
		var name = $('#policyname').val();
		var version = $('#policyversion').val();
		var type = $(':radio[name="policy.type"]:checked').val();
		
		if(id) param['policy.id'] = id;
		if(code) param['policy.code'] = code;
		if(name) param['policy.name'] = name;
		if(version) param['policy.version'] = version;
		if(type) param['policy.type'] = type;
		
		param['policy.academyId'] = '${param.academyId}';
		
		return param;
	}
	
	function buildStatusBox(status){
		var str = '<select>';
		str += '<option value="1" '+(status ? 'selected="selected"' : '')+'>启用</option>';
		str += '<option value="0" '+(!status ? 'selected="selected"' : '')+'>停用</option>';
		
		return str;
	}
	
	function prepareUpdate(id){
		$('#policy_dialog').dialog('open');
		
		$.ajax({
			url: '<s:url value="/enrollment/get_academy_rebate_policy"/>',
			type: 'POST',
			data: {id:id},
			dataType: 'json',
			success: function(data){
				if(data && data.policy){
					$('#policyid').val(id);
					$('#policycode').val(data.policy.code);
					$('#policyname').val(data.policy.name);
					$('#policyversion').val(data.policy.version);
					$('#policystatus').val(data.policy.status);
					$(':radio[name="policy.type"]').each(function(){
						if(this.value == data.policy.type)
							this.checked = true;
					});
					
					$('#policysubmit').get(0).onclick=updatePolicy;
				}
				/*else {
					alert('');
				}*/
			}
		});
	}
	
	function deletePolicy(id)
	{
		alert('删除成功！');
	}
	
	function addPolicy()
	{
			var param = getPolicyDialogData();
			
			jQuery.ajax({
				url: '<s:url value="/enrollment/add_academy_rebate_policy"/>',
				type: 'POST',
				dataType: 'json',
				data: param,
				success: function(data){
					$('#policy_dialog').dialog('close');
					alert('添加成功！');
					gridview.render(1);
				},
				error: function(data){
					alert('添加失败！');
				}
			});
	}
	
	function updatePolicy()
	{
		var param = getPolicyDialogData();
		
		jQuery.ajax({
			url: '<s:url value="/enrollment/update_academy_rebate_policy"/>',
			type: 'POST',
			dataType: 'json',
			data: param,
			success: function(data){
				$('#policy_dialog').dialog('close');
				alert('修改成功！');
				gridview.render(1);
			},
			error: function(data){
				alert('修改失败！');
			}
		});
	}
	
	function get_list_param()
	{
		var param = {};
		
		var name = $('#cpolicyname').val();
		if(name) param['policy.name'] = name;
		
		var status = $('#cpolicystatus').val();
		if(status) param['policy.status'] = status;
		
		param['policy.academyId'] = '${param.id}';
		
		return param;
	}
	
	function parseRebatePolicy(policy)
	{
		var row = $('<tr/>');
		
		var detailUrl = '<s:url value="/enrollment/overview_academy_rebate_policy"/>';//DETAIL_URL[policy.type];
		
		var viewIcon = $('<a/>').attr('href', detailUrl+'?tab=${param.tab}&academyId=${param.academyId}&policyId='+policy.id+'&type='+policy.type).attr('target', '_blank');
		$('<img/>').attr('src', '<s:url value="/images/icon_view.gif"/>').addClass('img_icon').appendTo(viewIcon);
		
		var editIcon = $('<a/>').attr('href','javascript:void(0);').attr('onclick', 'prepareUpdate('+policy.id+')');
		$('<img/>').attr('src', '<s:url value="/images/icon_edit.gif"/>').addClass('img_icon').appendTo(editIcon);
		
		$('<td/>').append(viewIcon).append(editIcon).appendTo(row);
		
		$('<td/>').append(policy.code).appendTo(row);
		$('<td/>').append(policy.name).appendTo(row);
		
		var academy = policy.academy;
		var name = (academy && academy.name) ? academy.name : '';
		$('<td/>').append(name).appendTo(row);
		
		$('<td/>').append(policy.version).appendTo(row);
		
		$('<td/>').append(AcademyRP.TypeName.get(policy.type)).appendTo(row);
		
		row.css({textAlign:'center'}); //让行中各个单元格居中显示
		
		return row;
	}
	
	var gridview; //====>
	
	jQuery(function(){
		var ui = jQuery('#policy_dialog');
		
		ui.dialog({
			title: '新增院校返款政策',
			autoOpen: false,
			modal: true,
			width: 355,
			height: 230,
			beforeClose: function(){
				$('form', ui).get(0).reset();
				$('#policyid').val(null);
			}
		});
		
		$('#addpolicyHandler').click(function(event){
			jQuery('#policysubmit').get(0).onclick=addPolicy;
			ui.dialog('open');
			event.preventDefault();
		});
		
		gridview = new jQuery.AjaxGridView({
			url: '<s:url value="/enrollment/list_academy_rebate_policy"/>',
			listAttr: 'list',
			countAttr: 'total',
			rowRender: parseRebatePolicy,
			param: get_list_param
		});
		
		gridview.render(1);
		
		$('#cpolicystatus').change(function(){
			gridview.render(1);
		});
		
		$('#doSearch').click(function(){
			gridview.render(1);
		});
	});
	
	</script>
</head>
<body>
<!-- 头开始 -->
		<head:head title="院校名称：${academy.name}">
			<html:a text="返回" icon="return" onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
			
			<%@ include file="_tab/academy_enroll_tab.jsp" %>

			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">院校返款政策</th>
					<th>
						<div style="text-align:right;">
							<img src="<s:url value="/images/title_menu/icon_add.gif"/>" width="16" height="16" align="middle"/>
							<a href="javascipt:void(0);" id="addpolicyHandler">新增返款政策</a>
						</div>
					</th>
				</tr>
			</table>

			<table class="add_table">
				<tr>
					<td align="right">院校：</td>
					<td>东北财经网院</td>
					<td width="70">政策名称：</td>
					<td align="left"><input type="text" id="cpolicyname"/></td>
					<td></td>
					<td width="70" align="right"><input type="button" value="查询" id="doSearch" class="btn_black_61"/></td>
				</tr>
			</table>
			
			<!-- GRID VIEW START -->
			<table class="gv_table_2" width="100%" border="0" cellpadding="0" cellspacing="0" id="ajax_grid_view">
			  <thead>
				<tr>
				  <th>操作</th>
				  <th>政策编码</th>
				  <th>政策名称</th>
				  <th>院校</th>
				  <th>版本</th>
				  <th>政策类别</th>
			    </tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>
			<div class="pager2" id="ajax_grid_pager"></div>
			<!-- GRID VIEW END -->
			
		</body:body>

<div id="policy_dialog" style="display: none;">
<form>
	<input type="hidden" id="policyid"/>
	<table>
		<tr>
			<td class="lable_100">政策编号：</td>
			<td><input type="text" id="policycode" name="policy.code" maxlength="8" /></td>
		</tr>
		<tr>
			<td class="lable_100">政策名称：</td>
			<td><input type="text" id="policyname" name="policy.name" /></td>
		</tr>
		<tr>
			<td class="lable_100">版本：</td>
			<td><input type="text" id="policyversion" name="policy.version" maxlength="8" /></td>
		</tr>
		<tr>
			<td class="lable_100">政策类别：</td>
			<td>
				<input type="radio" value="<s:property value="@net.cedu.common.Constants@BATCH_TYPE_ENROLL"/>" name="policy.type"/>招生 &nbsp;&nbsp;
				<input type="radio" value="<s:property value="@net.cedu.common.Constants@BATCH_TYPE_NON_ENROLL"/>" name="policy.type"/>非招生
			</td>
		</tr>
		<tr>
			<td align="center" colspan="2"><input type="button" value="提交" id="policysubmit" class="btn_black_61" /></td>
		</tr>
	</table>
</form>
</div>

</body>
</html>
