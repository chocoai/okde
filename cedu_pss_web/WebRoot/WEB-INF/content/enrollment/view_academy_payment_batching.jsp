<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>缴费方式</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!--  分页 -->
	<jc:plugin name="page" />
	<!-- 选项卡 -->
	<jc:plugin name="tab" />
<script type="text/javascript">
function remove_way()
{
	var ways = new Array();
	$('#added_way :checkbox[name="way"]').each(function(){
		if(this.checked)
		{
			ways.push(this.value);
			var parentRow = $(this).parent().parent();
			parentRow.appendTo('#candidate_way');
			this.checked = false;
			
			$('td',parentRow).addClass('removed_recently');
		}
	});
	
	if(ways.length>0)
		;
		//alert('移除成功');
	else
		$.alert('没有待移除缴费方式');
	//alert(ways.join('\n'));
	
	afterSwitch();
}

function add_way()
{
	var ways = new Array();
	$('#candidate_way :checkbox[name="way"]').each(function(){
		if(this.checked)
		{
			ways.push(this.value);
			var parentRow = $(this).parent().parent();
			parentRow.appendTo('#added_way');
			this.checked = false;
			$('td',parentRow).attr('class','');
			$('td',parentRow).addClass('added_recently');
		}
	});
	
	if(ways.length>0)
		;
		//alert('添加成功');
	else
		$.alert('没有可选的缴费方式');
	//alert(ways.join('\n'));
	
	afterSwitch();
}

function afterSwitch()
{
	$('#candidate_way > tfoot').empty();
	if($('#candidate_way :checkbox').size()==0){
		$('#candidate_way > tfoot').append('<tr><td align="center">已经没有可选的缴费方式了！</td></tr>');
	}
	
	$('#added_way > tfoot').empty();
	if($('#added_way :checkbox').size()==0){
		$('#added_way > tfoot').append('<tr><td algin="center">请从右边选择</td></tr>');
	}
}

function saveFW()
{
	var batchId = '${param.batchId}';
	var academyId = '${param.academyId}';
	
	var branchIds = [];
	
	var fwids = [];
	
	$('#added_way :checkbox').each(function(){
		fwids.push(this.value);
	});
	
	$('#branches :checkbox').each(function(){
		if(this.checked)
			branchIds.push(this.value);
	});
	
	if(branchIds.length==0){
		$.alert('您还没有选择将要设置的学习中心！');
		return;
	}
	
	if(fwids.length==0 && !confirm('您确认清空所有缴费方式吗？'))
		return; //用户取消清空

	$.ajax({
		type: 'POST',
		traditional: true,
		url: '<uu:url url="/enrollment/save_payment_way_batching"/>',
		data: {academyId: academyId, batchId: batchId, branchIds: branchIds, fwIds: fwids},
		dataType: 'json',
		success: function(data){
			$.alert('设置成功！');
		},
		error: function(xhr){
			$.alert('设置失败!');
		}
	});
}

function batchTrigger(_this, tableId){
	$(':checkbox', '#'+tableId).attr('checked', !!_this.checked);
}

$(function(){
	$('#btn_add').click(function(){
		$('#added_way > tfoot').hide();
		add_way();
		
		if($('#added_way > tbody > tr').size()==0){
			$('#added_way > tfoot').show();
		}
	});
	$('#btn_remove').click(function(){
		remove_way();
		
		if($('#added_way > tbody > tr').size()==0){
			$('#added_way > tfoot').show();
		}
	});
	
	$('#btnSave').click(function(){
		saveFW();
	});
});

</script>
</head>  
<body>
<!-- 头开始 -->
		<head:head title="招生计划${academy.name}">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Menu Begin-->
			<%@ include file="_tab/academy_enroll_tab.jsp" %>
			<!--Menu End-->
			
			<!--Left Begin-->
			<div style="float:left; width:20%; margin-right:1%;">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">学习中心</th>
					<th width="30"><input type="checkbox" checked="checked" onclick="$('#branches :checkbox').attr('checked', this.checked)"/></th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2" id="branches">
				<tbody>
				<s:iterator var="branch" value="branches">
					<tr>
						<td width="30"><input type="checkbox" name="branch" value="<s:property value="#branch.id"/>" checked="checked"/></td>
						<td><s:property value="#branch.name"/></td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tbody>
					<tr><td><input id="btnSave" class="btn_black_130" type="button" value="保存设置" /></td></tr>
				</tbody>
			</table>
			</div>
			<!--Left End-->
			
			<!-- Center BEGIN -->
			<div style="float:left; width:38%;">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">已有缴费方式<input type="checkbox" onclick="batchTrigger(this,'added_way')"/></th>
				</tr>
			</table>
			<table id="added_way" class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tbody>
					<s:iterator var="way" value="oldFeeWays">
						<tr>
							<td width="30"><input type="checkbox" name="way" value="<s:property value="#way.id"/>" /></td>
							<td><s:property value="#way.name" /></td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
				<s:if test="!oldFeeWays || oldFeeWays.size()==0">
					<tr><td align="center" colspan="2">请从右边选择</td></tr>
				</s:if>
				</tfoot>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tbody>
					<tr><td><input id="btn_remove" class="btn_gray_130" type="button" value="移除选中缴费方式"/></td></tr>
				</tbody>
			</table>
			</div>
			
			<!-- Center END -->
			
			<!--Right Begin-->
		<div style="margin-left:1%; float:right; width: 39%">
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">可选缴费方式<input type="checkbox" onclick="batchTrigger(this,'candidate_way')"/></th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
			  <tbody>
				<tr>
					<td>
						<table id="candidate_way" class="gv_table_2" border="0" cellpadding="2" cellspacing="2">
						  <tbody>
							<s:iterator var="other" value="otherFeeWays">
								<tr><td width="30"><input type="checkbox" name="way" value="<s:property value="#other.id"/>" /></td><td><s:property value="#other.name"/></td></tr>
							</s:iterator>
						  </tbody>
						  <tfoot>
								<s:if test="otherFeeWays.size()==0">
									<tr><td>已经没有可选的缴费方式了！</td></tr>
								</s:if>
						  </tfoot>
						</table>
					</td>
				</tr>
			  </tbody>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr><td><input id="btn_add" class="btn_gray_130" type="button" value="添加选中缴费方式"/></td></tr>
			</table>
		</div>
			<!--Right End-->		
			
  		</body:body>
</body>
</html>
