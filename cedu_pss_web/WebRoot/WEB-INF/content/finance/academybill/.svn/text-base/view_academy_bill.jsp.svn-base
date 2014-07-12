<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>财务管理 &nbsp; 应收学校款 详情</title>
    <!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
  </head>
  
  <body>
<head:head title="应收学校款详情">
	<html:a text="返回" icon="return" href="/finance/academybill/list_academy_bill"/>
</head:head>
<body:body>
	<table class="gv_table_2">
		<tr>
			<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
			<th style="text-align:left; font-weight:bold;">应收学校款详情</th>
		</tr>
	</table>
	
	<table class="add_table">
		<tr>
			<td class="lable_100">院校：</td>
			<td><s:property value="planedAcademyBill.academyName"/></td>
		</tr>
		<tr>
			<td class="lable_100">学习中心：</td>
			<td><s:property value="planedAcademyBill.branchName"/></td>
		</tr>
		<tr>
			<td class="lable_100">收款类型：</td>
			<td><s:property value="planedAcademyBill.receivedWayName"/></td>
		</tr>
			<tr>
			<td class="lable_100">应收金额：</td>
			<td><s:property value="planedAcademyBill.amount"/></td>
		</tr>
		
		 <tr>
			<td class="lable_100">备注：</td>
			<td><s:property value="planedAcademyBill.note"/></td>
		</tr>
		 <tr>
			<td class="lable_100">附件：</td>
			<td><a target="_blank" href="<uu:url url="/finance/academybill/download_academy_bill_attachment" />?billId=<s:property value="planedAcademyBill.id"/>">下载</a></td>
		</tr>
	</table>
</body:body>
  </body>
</html>
