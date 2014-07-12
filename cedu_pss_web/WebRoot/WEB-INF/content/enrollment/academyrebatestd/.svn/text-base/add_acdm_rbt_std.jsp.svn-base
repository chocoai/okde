<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>新增院校返款标准</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
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
var ValueForms = {
	<s:property value="@net.cedu.common.Constants@MONEY_FORM_RATIO" />: {name:'比率',unit:'%'},
	<s:property value="@net.cedu.common.Constants@MONEY_FORM_AMOUNT" />: {name:'金额',unit:'元'}
};
</script>
<script type="text/javascript">
function chgUnit(slct)
{
	$('.ValueFormUnit', $(slct).parent().parent()).text(ValueForms[$(slct).val()].unit);

	$('.stdValue', $(slct).parent().parent()).val('');
}

function addStandardItem()
{
	var std = $('#standardTemplate tr').clone();
	std.appendTo('#rebate_table > tbody');
	chgUnit($('.stdValueForm', std));
	
	cooRN();
}

function removeStd(a)
{
	if($('#rebate_table > tbody > tr').size()==1)
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
	$('#rebate_table > tbody > tr').each(function(index){
		$('.stdNo',this).html(index+1);
		$('.stdFloor', this).attr('name', 'policy.standards['+index+'].floor');
		$('.stdCeil', this).attr('name', 'policy.standards['+index+'].ceil');
		$('.stdValue', this).attr('name', 'policy.standards['+index+'].value');
		$('.stdValueForm', this).attr('name', 'policy.standards['+index+'].valueForm');
	});
}

function validate(){
	$('#rebate_table > tbody > tr .stdNo').css({color:''});
	$('#feeSubjectId').parent().parent().children(':nth(0)').css({color: ''});
	$('input[name=title]').parent().parent().children(':nth(0)').css({color:''});
	
	var feeSubject = $('#feeSubjectId').val();
	if(feeSubject==0){
		$('#feeSubjectId').parent().parent().children(':nth(0)').css({color: 'red'});
		$.alert({ msg: '请选择费用科目' });
		return false;
	}
	
	var title = $('input[name=title]').val();
	if(!title || /^[^\s].*[\s]$/.test(title)){
		$('input[name=title]').parent().parent().children(':nth(0)').css({color: 'red'});
		$.alert({ msg: '请填写标题' });
		return false;
	}

	var flag = true; //是否通过验证
	
	$('#rebate_table > tbody > tr').each(function(index){
		//var form = $('.stdValueForm', this);
		//if(form == '<s:property value="@net.cedu.common.Constants@MONEY_FORM_RATIO" />'){
			// /100|^[1-9]?[0-9]/.test($('.stdFloor', this))
			var floor = $('.stdFloor', this).val();
			var ceil = $('.stdCeil', this).val();
			var value = $('.stdValue', this).val();
			
			var err = [];
			if(! /^[1-9]\d*$/.test(floor) || !/^[1-9]\d*$/.test(ceil)){
				err.push('招生人数上下限都必须是一个大于0的数字');
			} else if(parseInt(floor)>=parseInt(ceil)) {
				err.push('招生人数上限必须小于下限');
			}
			
			if(index>0){
				var preCeil = $('#rebate_table > tbody > tr:nth('+(index-1)+')').find('.stdCeil').val();
				preCeil = parseInt(preCeil);
				if(preCeil+1 != floor){
					err.push('招生人数下限没有与第'+index+'行标准的上限相连接');
				}
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
		//}
		//<s:property value="@net.cedu.common.Constants@MONEY_FORM_SCORE" />
		//<s:property value="@net.cedu.common.Constants@MONEY_FORM_AMOUNT" />
	});

	return flag;
}
</script>

<script type="text/javascript">
jQuery(function(){
	addStandardItem();
	document.getElementById('form').reset();
});
</script>
  </head>
  
  <body>
<!--头部层开始 -->
		<head:head title="新增院校返款标准">
			<html:a text="关闭" icon="return" onclick="self.close();" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>

<form id="form" action="<uu:url url="/enrollment/academyrebatestd/save_acdm_rbt_plcy" />" method="post" onsubmit="return validate()">
		<table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">基本数据
					
			 	   </th>
		    </tr>
		</table>
		<table class="add_table" border="0" cellpadding="2" cellspacing="2" id="fee_subjects">
		  	<tr>
				<td class="lable_100">院校：</td>
				<td>
		  			<s:select list="academies" listKey="id" listValue="name"
						headerKey="%{@net.cedu.entity.enrollment.AcademyRebatePolicy@ACADEMY_ID_ALL}" headerValue="所有院校"
						name="academyId" id="academyId" cssClass="txt_box_100">
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
		</table>
		<table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold; width:80px;">返款标准</th>
					<th></th>
					<th width="70" align="center"><a href="javascript:void(0);" onclick="addStandardItem()">添加标准项</a></th>
			 </tr>
		</table>
		<table class="add_table" border="0" cellpadding="2" cellspacing="2" id="rebate_table">
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

		  <div class="add_table" style="text-align:center"><input class="btn_black_61" type="submit" value="添加"/></div>
		  
</form>

		</body:body>

<table style="display:none" id="standardTemplate">
	<tr>
		<td class="stdNo">(序号)</td>
		<td><input type="text" name="standards[1].floor" class="stdFloor"/>人</td>
		<td><input type="text" name="standards[1].ceil" class="stdCeil"/>人</td>
		<td>
			<select class="stdValueForm" name="standards[1].valueForm" onchange="chgUnit(this)">
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

  </body>
</html>
