<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>院校返款标准 详情</title>
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
<script type="text/javascript">
var ValueForms = {
	<s:property value="@net.cedu.common.Constants@MONEY_FORM_RATIO" />: {name:'比率',unit:'%'},
	<s:property value="@net.cedu.common.Constants@MONEY_FORM_SCORE" />: {name:'学分',unit:'分'},
	<s:property value="@net.cedu.common.Constants@MONEY_FORM_AMOUNT" />: {name:'金额',unit:'元'}
};
</script>
<script type="text/javascript">
function initData()
{
	var policyId = '${param.policyId}';
	$.ajax({
		url: '<uu:url url="/enrollment/academyrebatestd/get_academy_rebate_policy"/>',
		type: 'POST',
		data: {policyId: policyId},
		dataType: 'json',
		success: function(data){
			var policy = data.policy;
			var academyName = policy.academyName;
			if(policy.academyId=='<s:property value="@net.cedu.entity.enrollment.AcademyRebatePolicy@ACADEMY_ID_ALL"/>')
				academyName = '所有院校';
			$('#academyId').text(academyName);
			$('#feeSubjectId').text(policy.feeSubjectName);
			$('#title').text(policy.title);
			
			var stdLen = (policy.standards ? policy.standards.length : 0);
			
			for(var i=0; i<stdLen; i++){
				var std = policy.standards[i];
				var row = $('#standardTemplate tr').clone();
				$('.stdNo', row).html(i+1);
				$('.stdFloor', row).text(std.floor+'人');
				$('.stdCeil', row).text(std.ceil+'人');
				$('.stdValue', row).text(std.value+ValueForms[std.valueForm].unit);
				
				$('#rebate_table > tbody').append(row);
			}
		},
		error: function(xhr){
			var c =0;
			c+= '\t\tERR';
			alert(c);
		}
	});
}
</script>

<script type="text/javascript">
jQuery(function(){
	initData();
});
</script>
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="院校返款标准 详情">
			<html:a text="关闭" icon="return" onclick="self.close();" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
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
				<td id="academyId"></td>
			</tr>
		  	<tr>
				<td class="lable_100">费用项目：</td>
				<td id="feeSubjectId"></td>
			</tr>
			<tr>
				<td class="lable_100">标题：</td>					
				<td id="title"></td>
			</tr>
		  </table>
		  <table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold; width:80px;">返款标准</th>
					<th></th>
			 </tr>
		  </table>
		<table class="add_table" border="0" cellpadding="2" cellspacing="2" id="rebate_table">
		  <thead>
		  	<tr>
				<td width="5%">&nbsp;</td>
				<td width="15%" align="center">招生人数下限</td>
				<!-- td width="5%"></td -->
				<td width="20%" align="center">招生人数上限</td>
		  		<!-- td width="15%"></td -->
		  		<td width="15%">金额/比例/学分</td>
				<!-- td></td -->
		  	</tr>
		  </thead>
		  <tbody></tbody>
		</table>

		  <table class="add_table" style="text-align:center"><tr><td><input class="btn_black_61" type="button" value="关闭" onclick="self.close()"/></td></tr></table>
		</body:body>

<table style="display:none" id="standardTemplate">
	<tr>
		<td class="stdNo">(序号)</td>
		<td class="stdFloor" align="center"></td>
		<!-- td align="center">&mdash;&mdash;&mdash;</td -->
		<td class="stdCeil" align="center"></td>
		<!-- td align="right" class="ValueFormName"></td -->
		<td align="left" class="stdValue"></td>
		<!-- td class="ValueFormUnit"></td -->
	</tr>
</table>
  </body>
</html>
