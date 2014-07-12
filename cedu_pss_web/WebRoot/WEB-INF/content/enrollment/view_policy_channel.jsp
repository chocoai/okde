<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>招生返款政策</title>
	
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
	
	<script type="text/javascript">
	function create_dialog()
	{
		var dialogId = (Math.random()+'').replace('0\.','');
		var ui = jQuery('<div/>').attr('id',dialogId);
		
		ui.appendTo(document);
		return ui;
	}
	
	function prepare_add_fee()
	{
		var ui = create_dialog();
		
		var table = jQuery('<table/>');
		
		
		jQuery('<tr/>')
		.append('<td class="lable_100">政策编号：</td>')
		.append('<td><input type="text" name="" id=""/></td>')
		.appendTo(table);
		
		jQuery('<tr/>')
		.append('<td class="lable_100">政策名称：</td>')
		.append('<td><input type="text" name="" id=""/></td>')
		.appendTo(table);
		
		jQuery('<tr/>')
		.append('<td class="lable_100">版本：</td>')
		.append('<td><input type="text" name="" id=""/></td>')
		.appendTo(table);
		
		jQuery('<tr/>')
		.append('<td class="lable_100">状态：</td>')
		.append('<td><select><option>启用</option><option>停用</option></select></td>')
		.appendTo(table);
		
		jQuery('<tr/>')
		.append('<td colspan="2" align="center"><input type="button" class="btn_black_61" name="" id="" value="提交"/></td>')
		.appendTo(table);
		
		table.appendTo(ui);
		
		ui.dialog({
			title: '新增招生政策',
			modal: true,
			width: 300,
			height: 240,
			beforeClose: function(){
				jQuery(this).remove();
			}
		});
		
		jQuery(':button', ui).click(function(){
			alert('添加成功！');
			ui.dialog('close');
		});
		
		ui.dialog('open');
	}
	
	function get_list_param()
	{
		var param = {};
		
	}
	
	function parseChannelPolicy(policy, index)
	{
		var row = $('<tr/>');
		
		var viewIcon = $('<a/>').attr('href','<s:url value="/enrollment/view_channel_policy_overview"/>?tab=7&academyId=${param.academyId}&policyId='+policy.id).attr('target', '_blank');
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
		
		var status = '';
		
		if(policy.status>2){
			status = $('<select/>');
			status.append('<option value="3">启用</option>');
			status.append('<option value="4">禁用</option>');
			status.val(policy.status);
		} else if(policy.status==1) {
			status = '待审批';
		} else if(policy.status==2) {
			status = '审批未通过';
		}
		
		$('<td/>').append(status).appendTo(row);
		
		row.css({textAlign:'center'}); //让行中各个单元格居中显示
		
		return row;
	}
	</script>
	<script type="text/javascript">
	jQuery(function(){
		var gridview = new jQuery.AjaxGridView({
			url: '<s:url value="/enrollment/list_channel_policy"/>',
			listAttr: 'list',
			countAttr: 'totalSize',
			rowRender: parseChannelPolicy,
			param: get_list_param,
			table: '#list',
			pager: '#listPager'
		});
		
		//gridview.render(1);
	});
	</script>
	
</head>
<body>

<!-- 头开始 -->
		<head:head title="招生返款政策">
			<html:a text="返回" icon="return" onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>"/></th>
				 	<th style="text-align:left; font-weight:bold;">招生返款政策</th>
					<th>
						<div style="text-align:right;">
							<img src="<s:url value="/images/title_menu/add.png"/>"  class="img_icon" align="absmiddle"/>
							<a href="javascript:void(0);" onclick="prepare_add_fee()">新增招生返款政策</a>
						</div>
					</th>
				</tr>
			</table>

			<table class="add_table">
				<tr>
					<td width="70">政策名称：</td>
					<td align="left"><input type="text"/></td>
					
					<td align="right">院校：</td>
					<td><s:select name="academy" id="academy" list="academys" cssClass="txt_box_100" listKey="id" listValue="name" theme="simple"></s:select></td>
					<td>b</td>
					<td width="70" align="right"><input type="button" value="查询" class="btn_black_61"/></td>
				</tr>
			</table>
			
			<table id="list" class="gv_table_2" width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th>操作</th>
						<th>政策编码</th>
						<th>政策名称</th>
						<th>版本</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">
							<a href="view_policy_channel_overview.html"><img class="img_icon" src="<ui:img url="/images/icon_view.gif" />" title="详细" /></a>
							<a href="#" ><img class="img_icon" src="<ui:img url="/images/icon_edit.gif" />" title="设置"/></a>
						</td>
						<td align="center">CF0092934</td>
						<td align="center">大客户政策</td>
						<td align="center">20110901</td>
					</tr>
				</tbody>
			</table>
			
			<div id="listPager" class="pager2"></div>
			
		</div>
	</div>
</div>

<div id="add_policy_fee_dialog" style="display: none;">
	<table class="add_table"> 
		
		<tr>
			<td class="lable_100">政策编码：</td>
			<td>2011100993888</td>
		</tr>
		<tr>
			<td class="lable_100">政策名称：</td>
			<td><input class="txt_box_150" name="" type="text" id="" maxlength="4"/></td>
		</tr>
		 <tr>
			<td class="lable_100">版本：</td>
			<td>20110939320009</td>
		</tr>
		<tr>
			<td class="lable_100">状态：</td>
			<td><select class="txt_box_150" name="" id="" >
			  <option>启用</option>
			  <option>停用</option>
			</select></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input class="btn_black_61" type="button" value="保存"/></td>
		</tr>
	</table>
</div>

</body:body>
</html>
