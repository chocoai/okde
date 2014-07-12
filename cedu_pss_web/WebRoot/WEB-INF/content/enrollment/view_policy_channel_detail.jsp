<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>招生返款政策详情</title>
		
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
    <form id="myform" action="modify_channel" method="post">
		<!-- 头开始 -->
		<head:head title="招生返款政策详情">
		</head:head>
		<!--主体层开始 -->
		<body:body>
					<table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">基本资料</th>
					  </tr>
					</table>
						<table class="add_table">  
						        <tr>
							      	<td class="lable_100">政策名称：</td>
							        <td>
							        	${cpd.channelpolicy.name}
							        </td>
								</tr>
								<tr>
								    <td class="lable_100"> 院校名称：</td>
									<td>${cpd.academy.name}</td>
								</tr>
								<tr>
									<td class="lable_100">院校招生批次：</td>
							        <td>
							        ${cpd.academyenrollbatch.enrollmentName}
							        </td>	 
						        
						        </tr>
							    <tr>
								    <td class="lable_100"> 层次：</td>
									<td>
									 ${cpd.level.name}
									</td>
		                       </tr>
		                       <tr>
							    <td class="lable_100">专业：</td>
								<td >${cpd.majors}</td>
							   </tr>
							   <tr>
		                        <td class="lable_100"> 学习中心：</td>
								<td>${cpd.branches}</td>
							 </tr>
							 <tr>
							    <td class="lable_100"> 合作方类别：</td>
								<td>${cpd.enrollmentsource.name}</td>
		                        </tr>
								<tr>
		                        <td class="lable_100"> 费用科目：</td>
								<td>${cpd.feeSubjects}</td>
							 </tr>
							
						</table>	
		       </body:body>
	</form>
  </body>
</html>
