<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>招生返款政策 详情</title>
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

function validAndSubmit()
{
	if($(':radio[name=auditStatus]:checked').size()!=1){
		$.warn({msg:'请选择审批结果'});
	} else {
		$('#auditForm').get(0).submit();
	}
}
</script>
<a:ajax successCallbackFunctions="updateCallback" parameters="UPDATE_PARAM" pluginCode="update" urls="/enrollment/academyrebatepolicy/update_acdm_rbt_plcy_dtl"/>
  </head>

  <body>
		<!--头部层开始 -->
		<head:head title="院校返款政策 详情">
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
				<td colspan="4" align="center"><div style="font-weight:bolder; font-size:14px">合作方：<s:property value="channel.name"/></div></td>
         	</tr>
		  	<tr>
		  		<td class="lable_110">院校：</td>
		  		<td><s:property value="academy.name"/></td>
				<td class="lable_110">批次：</td>
		  		<td><s:property value="batch.enrollmentName"/></td>
		  	</tr>
			<tr>
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
		  
		  <table class="add_table">
			<tr>
				<td></td>
				<td class="lable_80">合作方：</td>
				<td style="width:100px;text-align:left;"><s:property value="channelType.name"/></td>
				<td></td>
				<td class="lable_80">费用项目：</td>
				<td style="width:100px;text-align:left;"><s:property value="feeSubject.name"/></td>
				<td></td>
			</tr>
		  </table>
		  
		  <table class="gv_table_2" border="0" cellpadding="2" cellspacing="2" id="policyList">
			  <thead>
			  	<tr>
					<th>标题</th>
			  		<th>院校</th>
			  		<th>费用项目</th>
			  		<th>金额/比例</th>
			  	</tr>
			  </thead>
			  <tbody>
			  	<tr>
					</td>
					<td><s:property value="policy.title"/></td>
					<td>
						<s:property value="policy.academyId==-1 ? '所有院校' : policy.academyName"/>
					</td>
					<td><s:property value="feeSubject.name"/></td>
					<td>
						<s:iterator value="policy.standards" var="std">
						<ul class="ul_for_policy_standard">
							<li><s:property value="#std.enrollmentFloor"/>人</li>
							<li>&mdash;&mdash;</li>
							<li><s:property value="#std.enrollmentCeil"/>人</li>
							<li><s:property value="#std.value"/></li>
							<li>
								<s:property value="@net.cedu.common.Constants@MONEY_FORM_RATIO == #std.rebatesId ? '%' : ''"/>
								<s:property value="@net.cedu.common.Constants@MONEY_FORM_AMOUNT == #std.rebatesId ? '元' : ''"/>
								<s:property value="@net.cedu.common.Constants@MONEY_FORM_SCORE == #std.rebatesId ? '分' : ''"/>
							</li>
						</ul>
						</s:iterator>
					</td>
				</tr>
			  </tbody>
		  </table>
		<form id="auditForm" action="<uu:url url="/enrollment/chnlplcy/update_chnl_plcy_dtl_audit_status"/>" method="post">
		  <table class="add_table">
			<tr>
				<td></td>
				<td width="90"><input type="radio" name="auditStatus" value="<s:property value="@net.cedu.common.Constants@POLICY_AUDIT_STATUS_GOOD"/>" id="approve_t"/><label for="approve_t">通过</label></td>
				<td width="90"><input type="radio" name="auditStatus" value="<s:property value="@net.cedu.common.Constants@POLICY_AUDIT_STATUS_BAD"/>" id="approve_f"/><label for="approve_f">未通过</label></td>
				<td></td>
			</tr>
		  </table>
		  
		  <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="button" value="保存" onclick="validAndSubmit()" /></td></tr></table>
		  
		  <input value="${param.detailId}" name="detailId" type="hidden"/>
		</form>
		</body:body>

  </body>
</html>
