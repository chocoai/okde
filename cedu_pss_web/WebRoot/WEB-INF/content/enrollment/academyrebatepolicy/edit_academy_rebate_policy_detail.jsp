<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>院校返款政策 修改</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!--  分页 -->
	<jc:plugin name="page" />
	<jc:plugin name="loading" />
<script type="text/javascript">
UPDATE_PARAM = 0;
function prepareUpdate()
{
	var policyId = $(':radio[name="policyId"]:checked').val();
	if(!policyId){
		$.warn({title:'操作提示', msg:'请选择政策标准！'});
		return;
	}
	$.confirm({
		title:'修改提示',
		msg:'您真的要修改吗？',
		confirm:function(){
			UPDATE_PARAM = {policyId: policyId, detailId:'${param.detailId}'};
			ajax_update_1();
		}
	});
}
function updateCallback(data)
{
	UPDATE_PARAM = null;
	$.warn({msg:'更新成功！'});
}
</script>
<a:ajax successCallbackFunctions="updateCallback" parameters="UPDATE_PARAM" pluginCode="update" urls="/enrollment/academyrebatepolicy/update_acdm_rbt_plcy_dtl"/>
  </head>

  <body>
		<!--头部层开始 -->
		<head:head title="院校返款政策修改">
			<html:a text="返回" icon="return" onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		  <table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">院校</th>
			</tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2">
		  	<tr>
		  		<td class="lable_110">院校：</td>
		  		<td><s:property value="academy.name"/></td>
				<td class="lable_110">学习中心：</td>
		  		<td><s:property value="branch.name"/></td>
				<td class="lable_110">费用项目：</td>
		  		<td><s:property value="feeSubject.name"/></td>
		  	</tr>
			<tr>
				<td class="lable_110">批次：</td>
		  		<td><s:property value="batch.enrollmentName"/></td>
				<td class="lable_110">层次：</td>
		  		<td><s:property value="level.name"/></td>
				<td class="lable_110">专业：</td>
		  		<td><s:property value="major.name"/></td>
			</tr>
		  </table>
		  
		  <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">政策标准</th>
		    </tr>
		  </table>
		  
		  <table class="gv_table_2" border="0" cellpadding="2" cellspacing="2" id="policyList">
			  <thead>
			  	<tr>
			  		<th></th>
					<th>标题</th>
			  		<th>院校</th>
			  		<th>费用项目</th>
			  		<th>金额/比例</th>
			  	</tr>
			  </thead>
			  <tbody>
			<s:iterator value="policyList" status="status" var="policy">
			  	<tr>
					<td><input type="radio" name="policyId" 
						value="<s:property value="#policy.id"/>" 
						<s:if test="#policy.id==detail.policyId">checked="checked"</s:if>
						/>
					</td>
					<td><s:property value="#policy.title"/></td>
					<td>
						<s:property value="#policy.academyName"/>
						<s:property value="@net.cedu.common.Constants@ACADEMY_ID_ALL == #policy.academyId ? '所有院校' : ''"/>
					</td>
					<td><s:property value="#policy.feeSubjectName"/></td>
					<td>
						<s:iterator value="#policy.standards" var="std">
						<ul class="ul_for_policy_standard">
							<li><s:property value="#std.floor"/>人</li>
							<li>&mdash;&mdash;</li>
							<li><s:property value="#std.floor"/>人</li>
							<li><s:property value="#std.value"/></li>
							<li>
								<s:property value="@net.cedu.common.Constants@MONEY_FORM_RATIO == #std.valueForm ? '%' : ''"/>
								<s:property value="@net.cedu.common.Constants@MONEY_FORM_AMOUNT == #std.valueForm ? '元' : ''"/>
								<s:property value="@net.cedu.common.Constants@MONEY_FORM_SCORE == #std.valueForm ? '分' : ''"/>
							</li>
						</ul>
						</s:iterator>
					</td>
				</tr>
			</s:iterator>
			  </tbody>
		  </table>
		  
		  <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="submit" value="保存" onclick="prepareUpdate()" /></td></tr></table>
		</body:body>

  </body>
</html>
