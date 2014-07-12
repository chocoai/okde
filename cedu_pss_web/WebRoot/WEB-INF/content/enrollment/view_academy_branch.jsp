<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>院校详情-授权中心</title>

<!--  jquery库 -->
<jc:plugin name="jquery" />
<!-- jquery-UI -->
<jc:plugin name="jquery_ui" />
<!-- 整体样式 -->
<jc:plugin name="default" />
<!--  分页 -->
<jc:plugin name="page" />
<!-- Tab 分页样式 -->
<jc:plugin name="tab" />

<script type="text/javascript">
function batch_change_listener()
{
	var $this = $(this);
	$('#granted_branch > tbody').html('<tr><td>请稍等....</td></tr>');
	$('#candidate_branch > tbody').html('<tr><td>请稍等....</td></tr>');
	$.ajax({
		url: '<s:url value="/enrollment/list_academy_branch"/>',
		async: true,
		type: 'POST',
		dataType: 'json',
		data: {academyId:'${param.academyId}', batchId:$this.val(), includeCandidate:true},
		success: function(data){
			if(!data){
				alert('');
			}
			
			var indexg=1;
			$('#granted_branch > tbody').empty();
			$(data.grantedList).each(function(){
				var tr = $('<tr/>');
				tr.append('<td width="30"><input type="checkbox" name="branch" value="'+this.id+'"/></td><td>'+indexg+'.'+this.name+'</td>');
				$('#granted_branch').append(tr);
				indexg++;
			});
			var indexc=1;
			$('#candidate_branch > tbody').empty();
			$(data.candidateList).each(function(){
				var tr = $('<tr/>');
				tr.append('<td width="30"><input type="checkbox" name="branch" value="'+this.id+'"/></td><td>'+indexc+'.'+this.name+'</td>');
				$('#candidate_branch').append(tr);
				indexc++;
			});
		},
		error: function(data){
			$.alert(data);
		}
	});
}

function save_granted_branches()
{
	var params = {academyId: '${param.academyId}'};
	
	var batch;
	$('#batch_list :radio[name="batch"]').each(function(){
		if(this.checked){
			batch = this.value;
			return false;
		}
	});
	
	if(!batch){ //如果没有选择招生批次
		$.alert('请先选择一个招生批次！');
		
		return;
	}
	
	params.batchId = batch;

	var branches = [];
	
	$('#granted_branch :checkbox[name="branch"]').each(function(){
		//if(this.checked){
			branches.push(this.value);
		//}
	});
	
	if(branches.length==0){ //如果移除全部授权中心
		if(!confirm('这样会移除所有已授权的分公司！\n您确实要删除吗？')){
			return;
		}
	} else { //提交重新整理授权后的学习中心ID
		params.branchIds = branches;
	}
	
	$.ajax({
		url: '<s:url value="save_academy_batch_branch_list"/>',
		type:'POST',
		traditional: true,
		data: params,
		dataType: 'json',
		success: function(data){
			/*
			if(!data || data.errno<0){
				alert(data.errnoMsg);
			} else {
				alert('保存成功！');
			}
			*/
			alert(data.errnoMsg);
		},
		error: function(xhr){
			$.alert('访问服务器出错！');
		}
	});
}
</script>

<script type="text/javascript">
function shift_branch()
{
	var branches = new Array();
	$('#granted_branch :checkbox[name="branch"]').each(function(){
		if(this.checked)
		{
			branches.push(this.value);
			var parentRow = $(this).parent().parent();
			parentRow.appendTo('#candidate_branch');
			this.checked = false;
			//$('td',parentRow).attr('class','');
			$('td',parentRow).addClass('removed_recently');
		}
	});
	
	if(branches.length>0)
		;
		//alert('移除成功');
	else
		$.alert('没有待移除授权的学习中心');
	//alert(branches.join('\n'));
}

function unshift_branch()
{
	var branches = new Array();
	$('#candidate_branch :checkbox[name="branch"]').each(function(){
		if(this.checked)
		{
			branches.push(this.value);
			var parentRow = $(this).parent().parent();
			parentRow.appendTo('#granted_branch');
			this.checked = false;
			$('td',parentRow).attr('class','');
			$('td',parentRow).addClass('added_recently');
		}
	});
	
	if(branches.length>0)
		;
		//alert('添加成功');
	else
		$.alert('没有待授权的学习中心');
	//alert(branches.join('\n'));
}

function batchTrigger(_this, tableId){
	$(':checkbox', '#'+tableId).attr('checked', !!_this.checked);
}

$(function(){
	$(':radio[name=batch]').click(batch_change_listener);
	$('#btn_grant').click(unshift_branch);
	$('#btn_revoke').click(shift_branch);
	
	$('#btn_save_grantes').click(save_granted_branches);
	
	$(':radio[name=batch]:nth(0)').attr('checked', true).click();
});

</script>

</head>  
<body>
<!-- 头开始 -->
		<head:head title="招生计划">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Menu Begin-->
			<%@include file="_tab/academy_enroll_tab.jsp" %>
			<!--Menu End-->
			
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
					<th style="text-align:left; font-weight:bold;">院校名称：${request.academy.name}</th>
				</tr>
			</table>
			<!--Left Begin-->
			<div style="float:left; width:20%; margin-rigth:1%">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">招生批次</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2" id="batch_list">
			<s:iterator var="batch" value="batches">
				<tr>
					<td width="30"><input type="radio" name="batch" value="<s:property value="id"/>"/></td>
					<td>
							<s:property value="enrollmentName"/>
					</td>
				</tr>
			</s:iterator>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<tr><td><input id="btn_save_grantes" class="btn_black_130" type="button" value="保存更改" /></td></tr>
				</tr>
			</table>
			</div>
			<!--Left End-->
			
			<!-- Center BEGIN -->
		<div style="float:left; width:20%; margin-left:1%;">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">已分配学习中心<input type="checkbox" onclick="batchTrigger(this,'granted_branch')"/></th>
				</tr>
			</table>
			<table id="granted_branch" class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tbody>
					<tr><td align="center">请选择招生批次!</td></tr>
				</tbody>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<tr><td><a id="btn_revoke" href="javascript:void(0);">移除选中中心 &gt;&gt;</a></td></tr>
				</tr>
			</table>
		</div>
			<!-- Center END -->
			
			<!--Right Begin-->
		<div style="width:57%; float: right;">
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">可选学习中心<input type="checkbox" onclick="batchTrigger(this,'candidate_branch')"/></th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td>
						<table id="candidate_branch" class="gv_table_2" border="0" cellpadding="2" cellspacing="2">
							<tbody>
								<tr><td align="center">请选择招生批次!</td></tr>
							</tbody>
						</table>
					</td>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr><td><a id="btn_grant" href="javascript:void(0);">&lt;&lt; 添加选中中心</a></td></tr>
			</table>
		</div>
			<!--Right End-->
  		</body:body>
</body>
</html>
