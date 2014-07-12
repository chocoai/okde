<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>合同详情</title>
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
		
		</script>
	
  </head>
  
  <body>
  <!--头部层开始 -->
		<head:head title="合同详情">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			
			
		
			
			<table class="add_table" width="100%" border="0" cellpadding="2" cellspacing="2">
				
				<tr>
					<td width="8%" class="lable_100">合同名称：</td>
					<td width="92%">${academycontract.name}</td>
				</tr>
				<tr>
					<td class="lable_100">签约人：</td>
					<td>${userinfo.fullName}</td>
				</tr>
				<tr>
					<td class="lable_100">签约日期：</td>
					<td>${academycontract.signupDate}</td>
				</tr>
				<tr>
					<td class="lable_100">开始日期：</td>
					<td>${academycontract.beginDate}</td>
				</tr>
				<tr>
					<td class="lable_100">结束日期：</td>
					<td>${academycontract.endDate}</td>
				</tr>
				<tr>
					<td class="lable_100">备注：</td>
					<td width="50%" >${academycontract.note}</td>
				</tr>		  
				
				
			</table>
  	
  		</body:body>
  </body>
</html>
