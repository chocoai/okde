<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>招生返款政策标准 详情</title>
    <jc:plugin name="jquery" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
  </head>
  
  <body>
<!-- 头开始 -->
		<head:head title="招生返款政策标准">
			<html:a text="关闭" icon="return" onclick="self.close();" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		<table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">基本数据</th>
		    </tr>
		</table>
		<table class="add_table" border="0" cellpadding="2" cellspacing="2" id="fee_subjects">
			<tr>
				<td class="lable_100">标题:</td>					
				<td id="title"><s:property value="policy.title"/></td>
			</tr>
		  	<tr>
				<td class="lable_100">院校:</td>					
				<td id="academy"><s:property value="policy.academyId==-1 ? '所有院校' : policy.academyName"/></td>
			</tr>
			<tr>
				<td class="lable_100">合作方:</td>					
				<td id="channelType"><s:property value="policy.channelTypeId==-1 ? '所有合作方' : policy.channelTypeName"/></td>
			</tr>
		  	<tr>
				<td class="lable_100">费用项目:</td>					
				<td id="feeSubject"><s:property value="policy.feeSubjectName"/></td>
			</tr>
			<tr>
				<td class="lable_100">是否需要审批:</td>
				<td id="requiresAudit"><s:property value="policy.requiresAudit==1 ? '' : '不'"/>需要</td>
			</tr>
		</table>
		  
		<table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold; width:80px;">返款标准</th>
					<th></th>
			 </tr>
		</table>
		<table class="add_table" border="0" cellpadding="2" cellspacing="2" id="standardTable">
		  <thead>
		  	<tr>
				<td width="30">&nbsp;</td>
				<td width="150"><span>招生人数</span>下限</td>
				<td width="150"><span>招生人数</span>上限</td>
		  		<td width="90">返款方式</td>
		  		<td width="200">金额/比例</td>
		  	</tr>
		  </thead>
		  <tbody>
		  <s:iterator var="std" value="policy.standards" status="sts">
		  	<tr>
		  		<td width="30"><s:property value="#sts.count"/></td>
				<td width="150"><s:property value="#std.enrollmentFloor"/>人</td>
				<td width="150"><s:property value="#std.enrollmentCeil"/>人</td>
		  		<td width="90">
		  			<s:if test="#std.rebatesId==@net.cedu.common.Constants@MONEY_FORM_RATIO">比例</s:if>
		  			<s:if test="#std.rebatesId==@net.cedu.common.Constants@MONEY_FORM_AMOUNT">金额</s:if>
		  			<s:if test="#std.rebatesId==@net.cedu.common.Constants@MONEY_FORM_SCORE">学分</s:if>
		  		</td>
		  		<td width="200">
		  			<s:property value="#std.value"/> &nbsp;
		  			<s:if test="#std.rebatesId==@net.cedu.common.Constants@MONEY_FORM_RATIO">%</s:if>
		  			<s:if test="#std.rebatesId==@net.cedu.common.Constants@MONEY_FORM_AMOUNT">元</s:if>
		  			<s:if test="#std.rebatesId==@net.cedu.common.Constants@MONEY_FORM_SCORE">分</s:if>
		  		</td>
		  	</tr>
		  </s:iterator>
		  </tbody>
		</table>

		<table class="add_table"><tr><td align="center"><input class="btn_black_61" type="button" value="关闭" onclick="self.close()"/></td></tr></table>
		</body:body>

<table style="display:none" id="standardTemplate">
	<tr>
		<td class="stdNo">(序号)</td>
		<td><input type="text" name="standards[1].floor" class="stdFloor"/>人</td>
		<td><input type="text" name="standards[1].ceil" class="stdCeil"/>人</td>
		<td></td>
		<td></td>
	</tr>
</table>

  </body>
</html>
