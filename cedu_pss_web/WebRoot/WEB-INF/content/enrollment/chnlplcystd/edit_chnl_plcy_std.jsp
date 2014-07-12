<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>修改招生返款标准</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<jc:plugin name="loading" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- Tab 分页样式 -->
	<jc:plugin name="tab" />
	<!--  分页 -->
	<jc:plugin name="page" />
<style type="text/css">
	.stdCeil, .stdFloor { width: 50px; }
</style>


<script type="text/javascript">
	
	function list_branch(doc)
	{
		var lists='';
		if(doc.branchlist!=null && doc.branchlist.length>0)
		{
			$.each(doc.branchlist,function(){
				lists+='<tr><td align="center">';
				lists+='<input  type="checkbox" id="b_'+this.id+'" name="branchcha"  value="'+this.id+'" />';
				lists+='</td><td align="center">'+this.name+'</td></tr>';
			});
			//lists+='<tr><td colspan="2" align="center"><input id="save" class="btn_black_61" type="button" onclick="savebranch()" value="保存"></td></tr>';
		}
		$('#table_branch > tbody').html(lists);
		initData();
	}
	
</script>
<a:ajax parameters="null" successCallbackFunctions="list_branch" pluginCode="001" urls="crm/all_branch_list"/>

<script type="text/javascript">
var ValueForms = {
	<s:property value="@net.cedu.common.Constants@MONEY_FORM_RATIO" />: {name:'比率',unit:'%'},
	//<s:property value="@net.cedu.common.Constants@MONEY_FORM_SCORE" />: {name:'学分',unit:'分'},
	<s:property value="@net.cedu.common.Constants@MONEY_FORM_AMOUNT" />: {name:'金额',unit:'元'}
};
</script>
<script type="text/javascript">
function chgUnit(slct)
{
	$('.ValueFormUnit', $(slct).parent().parent()).text(ValueForms[$(slct).val()].unit);
}

function addStandardItem()
{
	var std = $('#standardTemplate tr').clone();
	std.appendTo('#standardTable > tbody');
	chgUnit($('.stdValueForm', std));
	
	cooRN();
}

function removeStd(a)
{
	if($('#standardTable > tbody > tr').size()==1)
	{
		alert('您至少要填写一条！');
		return;
	}
	var tr = $(a).parent().parent();
	tr.remove();
	
	cooRN();
}

function cooRN() //重新校正返款标准 行 序号
{
	$('#standardTable > tbody > tr').each(function(index){
		$('.stdNo',this).html(index+1);
		$('.stdFloor', this).attr('name', 'standards['+index+'].enrollmentFloor');
		$('.stdCeil', this).attr('name', 'standards['+index+'].enrollmentCeil');
		$('.stdValue', this).attr('name', 'standards['+index+'].value');
		$('.stdValueForm', this).attr('name', 'standards['+index+'].rebatesId');
	});
}

function validate(){

	var branchIds='';
	$("#branchDiv input[name='branchcha']:checked").each(function(){
		if(this.checked)
		{
			branchIds+='#'+this.value;
		}
		
	});
	if(branchIds=='')
	{
		$.alert({ msg: '请至少选择一个学习中心' });
		return false;
	}else
	{
		branchIds=branchIds+'#';
		$('#branchIds').val(branchIds);
		
	}


	var title = $('input[name=title]').val();
	if(!title || /^[^\s].*[\s]$/.test(title)){
		$('input[name=title]').parent().parent().children(':nth(0)').css({color: 'red'});
		$.alert({ msg: '请填写标题' });
		return false;
	}
	
	var flag = true;
	
	$('#standardTable > tbody > tr').each(function(index){
		var floor = $('.stdFloor', this).val();
		var ceil = $('.stdCeil', this).val();
		var value = $('.stdValue', this).val();
		
		var err = [];
		if(! /^[1-9]\d*$/.test(floor) || !/^[1-9]\d*$/.test(ceil)){
			$('.stdNo', this).css({color: 'red'});
			err.push('招生人数上下限都必须是一个大于0的数字');
		} else if(parseInt(floor)>=parseInt(ceil)) {
			$('.stdNo', this).css({color: 'red'});
			err.push('招生人数上限必须小于下限');
		}
		
		if(!value){
			err.push('金额/比例不能为空！');
		} else if(!/^[1-9]\d*$/.test(value)){
			err.push('金额/比例必须为整数！');
		} else {
			value = parseInt(value);
			if(MONEY_FORM_RATIO==$('.stdValueForm', this).val()){
				if(value<0 || value>100){
					err.push('比例的数值应该在1-100之间！');
				}
			}
		}
		
		if(err.length > 0){
			var ul = $('<ul/>').addClass('validateErrorList');
			for(var i=0; i<err.length; i++){
				ul.append('<li>'+err[i]+'</li>');
			}
			
			$.alert({msg: ul, title: '第'+(index+1)+'行标准出错啦'});
			$(this).find('.stdNo').css({color:'red'});
			flag = false;
		}
		
		return flag; //如果此行通过验证，则继续验证剩余行，否则中断验证余下的行
	});
	
	return flag;
}

function initData()
{
	var policyId = '${param.id}';
	$.ajax({
		url: '<uu:url url="/enrollment/chnlplcystd/get_chnl_plcy_std"/>',
		type: 'POST',
		data: {id: policyId},
		dataType: 'json',
		success: function(data){
			var policy = data.policy;
			$('#academyId').val(policy.academyId);
			var branchIds=policy.branchIds;
			if(branchIds!=null && branchIds.length>0)
			{
				var branchid=branchIds.split("#");
				for(var i=0;i<branchid.length;i++)
				{
					
					if(branchid[i]!="")
					{
						$('#b_'+branchid[i]+'').attr("checked",true);
					}
				}
			}
			
			$('#feeSubjectId').val(policy.feeSubjectId);
			$('#title').val(policy.title);
			
			$(':radio[name=requiresAudit][value='+policy.requiresAudit+']').attr('checked', true);
			
			var stdLen = (policy.standards ? policy.standards.length : 0);
			
			for(var i=0; i<stdLen; i++){
				var std = policy.standards[i];
				var row = $('#standardTemplate tr').clone();
				
				//$('.stdNo', row).html(i+1);
				$('.stdFloor', row).val(std.enrollmentFloor);
				$('.stdCeil', row).val(std.enrollmentCeil);
				$('.stdValue', row).val(std.value);
				$('.ValueFormUnit', row).text(ValueForms[std.rebatesId].unit);
				$('.stdValueForm', row).val(std.rebatesId);
				
				$('#standardTable > tbody').append(row);
			}
			
			if(stdLen>0){
				cooRN();
			} else {
				addStandardItem();
			}
		},
		error: function(xhr){
			alert('In ERROR block');
		}
	});
}
</script>

<script type="text/javascript">
jQuery(function(){
	document.getElementById('form').reset();
	ajax_001_1();
});
	function findbranch()
	{
		show('branchDiv','学习中心',500,500);
	}
	function chkall(obj)
	{
		$("#branchDiv :checkbox").attr('checked',obj.checked);
	}
	function savebranch()
	{
		var branchIds='';
		$("#branchDiv input[name='branchcha']:checked").each(function(){
			if(this.checked)
			{
				branchIds+='#'+this.value;
			}
			
		});
		if(branchIds=='')
		{
			$.alert({ msg: '请至少选择一个学习中心' });
			
		}else
		{
			$.alert({ msg: '保存成功' });
			closes('branchDiv');
		}
	}
	
	
	
</script>
  </head>
  
  <body>
		<!-- 头开始 -->
		<head:head title="招生返款标准">
			<html:a text="关闭" icon="return" onclick="self.close();" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>

<form id="form" action="<uu:url url="/enrollment/chnlplcystd/update_chnl_plcy_std" />" onsubmit="return validate()" method="post" >

		   <table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">基本数据
					
			 	   </th>
		    </tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="fee_subjects">
		  	<tr>
					
				<td class="lable_100">学习中心:</td>					
				<td >
		  		<a href="#" onclick="findbranch()">选择学习中心</a>
		  		<input type="hidden" id="branchIds" name="branchIds" />
				</td>
			</tr>
		  	<tr>
				<td class="lable_100">院校：</td>
				<td >
		  			<s:select list="academies" listKey="id" listValue="name" headerKey="-1"
						headerValue="所有院校"
						name="academyId" id="academyId" cssClass="txt_box_100">
					</s:select>
				</td>
			</tr>
			<tr>
				<td class="lable_100">合作方:</td>					
				<td >
		  			<s:select list="channelTypes" listKey="id" listValue="name"
						headerKey="-1" headerValue="所有合作方"
						name="channelTypeId" id="channelTypeId" cssClass="txt_box_150">
					</s:select>
				</td>
			</tr>
		  	<tr>
				<td class="lable_100">费用项目：</td>
				<td >
		  			<s:select list="feeSubjects" listKey="id" listValue="name"
						headerKey="0" headerValue="--请选择--"
						name="feeSubjectId" id="feeSubjectId" cssClass="txt_box_100">
					</s:select>
				</td>
			</tr>
			<tr>
				<td class="lable_100">标题：</td>					
				<td >
		  			<input name="title" id="title"/>
				</td>
			</tr>
			<tr>
				<td class="lable_100">是否需要审批</td>
				<td><input type="radio" value="1" name="requiresAudit"/>需要&nbsp;&nbsp;&nbsp;<input type="radio" value="0" name="requiresAudit"/>不需要</td>
			</tr>
		  </table>
		  <table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold; width:80px;">返款标准</th>
					<th></th>
					<th width="70" align="center"><a href="javascript:void(0);" onclick="addStandardItem()">添加标准项</a></th>
			 </tr>
		  </table>
		<table class="add_table" border="0" cellpadding="2" cellspacing="2" id="standardTable">
		  <thead>
		  	<tr>
				<td width="20">&nbsp;</td>
				<td width="100">招生人数下限</td>
				<td width="100">招生人数上限</td>
		  		<td>返款方式</td>
		  		<td>金额/比例</td>
				<td></td>
		  	</tr>
		  </thead>
		  <tbody></tbody>
		</table>

		  <div class="add_table" style="text-align:center"><input class="btn_black_61" type="submit" value="保存修改"/></div>
		  
		  <input type="hidden" value="${ param.id }" name="id" />
</form>

		</body:body>

<table style="display:none" id="standardTemplate">
	<tr>
		<td class="stdNo">(序号)</td>
		<td><input type="text" name="standards[1].floor" class="stdFloor"/>人</td>
		<td><input type="text" name="standards[1].ceil" class="stdCeil"/>人</td>
		<td>
			<select class="stdValueForm" name="standards[1].valueForm" onchange="chgUnit(this)">
				<!-- option value="<s:property value="@net.cedu.common.Constants@MONEY_FORM_SCORE"/>">学分</option -->
				<option value="<s:property value="@net.cedu.common.Constants@MONEY_FORM_RATIO"/>">比例</option>
				<option value="<s:property value="@net.cedu.common.Constants@MONEY_FORM_AMOUNT"/>">金额</option>
			</select>
		</td>
		<td>
			<input type="text" name="standards[1].value" class="stdValue"/><span class="ValueFormUnit" style="color: black !important;"></span>
		</td>
		<td align="center"><a href="javascript:void(0);" onclick="removeStd(this)">删除</a></td>
	</tr>
</table>
		<div id="branchDiv" style="display:none">
		 <input id="save" class="btn_black_61" type="button" onclick="savebranch()" value="保存" />
		 <table id="table_branch" class="gv_table_2" border="0" cellpadding="2" cellspacing="2">
			  <thead>
			  	<tr>
			  		<th>全选<input type="checkbox" onclick="chkall(this)" /></th>
			  		<th>学习中心</th>
			  	</tr>
			  </thead>
			  <tbody>
			  	
			  </tbody>
		  </table>
	</div>

  </body>
</html>
