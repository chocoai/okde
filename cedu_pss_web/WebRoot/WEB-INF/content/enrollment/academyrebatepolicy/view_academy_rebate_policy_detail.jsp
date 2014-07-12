<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>院校返款政策 详情</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- Tab 分页样式 -->
	<jc:plugin name="tab" />
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="院校返款政策明细">
			<html:a text="关闭" icon="return" onclick="self.close();" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		  <table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">招生批次</th>
			</tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="batch">
			<tr>
				<td><s:property value="batch.enrollmentName"/></td>
			</tr>
		  </table>
			
		  <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">学习中心</th>
			</tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="branch">
			<s:iterator var="branch" status="curStatus" value="branches">
				<s:if test="(#curStatus.count)%4==1">
					<tr>
				</s:if>
					<td><s:property value="#branch.name"/></td>
				<s:if test="(#curStatus.count)%4==0">
					</tr>
				</s:if>
			</s:iterator>
			<s:if test="branches==null">
				<tr><td>适用于所有学习中心</td></tr>
			</s:if>
		  </table>
		
		 
		   <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">层次
			    </th>
			 </tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="level">
			<s:if test="level!=null">
				<tr><td><s:property value="level.name"/></td></tr>
			</s:if>
			<s:else>
				<tr><td>适用于所有层次</td></tr>
			</s:else>
		  </table>
		  
		  <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">专业</th>
		    </tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="major">
		  	<s:iterator var="major" status="curStatus" value="majors">
				<s:if test="(#curStatus.count)%4==1">
					<tr>
				</s:if>
					<td width="25%"><s:property value="#major.name"/></td>
				<s:if test="(#curStatus.count)%4==0">
					</tr>
				</s:if>
			</s:iterator>
			<s:if test="majors==null || majors.size()==0">
				<tr><td>适用于所有专业</td></tr>
			</s:if>
		  </table>
		  
		  <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold; width:80px;">返款标准</th>
				<th></th>
			</tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="rebate_table">
			<s:iterator var="std" value="standards" status="status">
			<tr>
				<td width="20"><s:property value="#status.count"/></td>
				<td>
					<s:property value="#std.floor"/>人
				</td>
				<td>
					<s:if test="#std.ceil>0">
						&nbsp;-&nbsp; <s:property value="#std.ceil"/>人
					</s:if>
					<s:else>
						&nbsp;以上
					</s:else>
				</td>
		  		<td width="25%" align="right">
					<s:if test="#std.valueForm == @net.cedu.common.Constants@MONEY_FORM_RATIO">比率</s:if>
					<s:elseif test="#std.valueForm == @net.cedu.common.Constants@MONEY_FORM_AMOUNT">金额</s:elseif>
					<s:elseif test="#std.valueForm == @net.cedu.common.Constants@MONEY_FORM_SCORE">学分</s:elseif>
					:
				</td>
		  		<td align="left">
		  			<s:property value="#std.value"/>
		  			<s:if test="#std.valueForm == @net.cedu.common.Constants@MONEY_FORM_RATIO">％</s:if>
					<s:elseif test="#std.valueForm == @net.cedu.common.Constants@MONEY_FORM_AMOUNT">元</s:elseif>
					<s:elseif test="#std.valueForm == @net.cedu.common.Constants@MONEY_FORM_SCORE">分</s:elseif>
				</td>
		  	</tr>
			</s:iterator>
			<s:if test="standards==null">
				<tr><td>没有具体标准准则</td></tr>
			</s:if>
		  </table>
		  <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="button" value="关闭" onclick="self.close()"/></td></tr></table>
		</body:body>
  </body>
</html>
