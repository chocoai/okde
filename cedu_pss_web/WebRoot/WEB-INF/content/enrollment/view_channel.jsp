<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>合作方详情</title>
		
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		
		<script type="text/javascript">
			$(document).ready(function(){

			});
			
			
		</script>
	</head>
  
  <body>
		<!-- 头开始 -->
		<head:head title="合作方详情">
			<html:a text="返回" icon="return"  onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="myform" action="modify_channel" method="post" >
					<table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">基本资料</th>
					  </tr>
					</table>
					<input type="hidden" class="txt_box_150" name="id" id="id" value="${channel.id}"/>
						<table class="add_table">  
						        <tr>
							      	<td class="lable_100"> 合作方名称：</td>
							        <td>
							        	${channel.name}
							        </td>
								</tr>
								<tr>
								    <td class="lable_100"> 合同编号：</td>
									<td>${channel.contractNo}</td>
								</tr>
								<tr>
									<td class="lable_100"> 合作方类别：</td>
							        <td>
							        ${enrollmentsource.name}
							        </td>	 
						        
						        </tr>
							    <tr>
								    <td class="lable_100"> 学习中心：</td>
									<td>
									 ${branch.name}
									</td>
		                       </tr>
		                       <tr>
							    <td class="lable_100">办公地址：</td>
								<td >${channel.officeAddress}</td>
							   </tr>
							   <tr>
		                        <td class="lable_100"> 开户行：</td>
								<td>${channel.accountBank}</td>
							 </tr>
							 <tr>
							    <td class="lable_100"> 开户名：</td>
								<td>${channel.accountName}</td>
		                        </tr>
								<tr>
		                        <td class="lable_100"> 账号：</td>
								<td>${channel.account}</td>
							 </tr>
							 <tr>
							    <td class="lable_100"> 类别：</td>
								<td>
								<s:if test="%{channel.isAll==0}">
								全国性合作方
								</s:if>
								<s:else>
								学习中心合作方 
								</s:else>
								
								
								
								</td>
							 </tr>
								<tr>
			                        <td class="lable_100"> 联系人：</td>
									<td>${channel.linkman}</td>
							 </tr>
								<tr>
		                        <td class="lable_100"> 证件号码：</td>
								<td>${channel.sertificateNo}</td>
							 </tr>
							 <tr>
							    <td class="lable_100"> 联系电话：</td>
								<td>${channel.telephone}</td>
		                        </tr>
							 <tr>
								<td class="lable_100">附件标题：</td>
								<td>${channel.attachmentName}</td>
							 </tr>
							 <tr>
								<td class="lable_100">附件：</td>
								
								<td><a href="#">下载附件</a></td>
							 </tr>	
						</table>
				</form>
		</body:body>
  </body>
</html>
