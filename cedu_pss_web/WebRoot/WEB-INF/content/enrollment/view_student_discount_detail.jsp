<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>查看学生优惠政策</title>
		
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
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		
		<script type="text/javascript">

			//加载事件
			jQuery(function(){
				
				
			});
			
		</script>
	</head>
  
  <body>
<!-- 头开始 -->
		<head:head title="查看学生优惠政策">
			<html:a text="关闭" icon="return" onclick="window.close();" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
			
				 <table class="gv_table_2">
				  	<tr>
						 <th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 <th style="text-align:left; font-weight:bold;">基本信息</th>							
					</tr>
				  </table>
				  <table class="add_table" id="brach" class="brach" border="0" cellpadding="2" cellspacing="2">
				  	<s:if test="%{studentDiscountDetail.academyId!=-1}">
					  	<tr>
			                <td class="lable_100">合作方类别：</td>
			                <td align="left">
			                	${studentDiscountDetail.channeltypename}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">合作方：</td>
			                <td align="left">
			                	${studentDiscountDetail.channelname}
							</td>
						</tr>
					  	<tr>
			                <td class="lable_100">院校：</td>
			                <td align="left">
			                	${studentDiscountDetail.academyname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">学习中心：</td>
			                <td align="left">
			                	${studentDiscountDetail.branchname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">批次：</td>
			                <td align="left">
			                	${studentDiscountDetail.batchname}
							</td>
						</tr>	
						<tr>
			                <td class="lable_100">层次：</td>
			                <td align="left">
			                	${studentDiscountDetail.levelname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">专业：</td>
			                <td align="left">
			                	${studentDiscountDetail.majorname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">费用科目：</td>
			                <td align="left">
			                	${studentDiscountDetail.feesubjectname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">缴费次数：</td>
			                <td align="left">
			                	第${studentDiscountDetail.feeCountId}次缴费
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
			                <td class="lable_100">学习中心：</td>
			                <td align="left">
			                	${studentDiscountDetail.branchname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">费用科目：</td>
			                <td align="left">
			                	${studentDiscountDetail.feesubjectname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">缴费次数：</td>
			                <td align="left">
			                	第${studentDiscountDetail.feeCountId}次缴费
							</td>
						</tr>
					</s:else>
				  </table>
								  
				   <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;" class="feehtml">政策标准
					 	   </th>
				    </tr>
				  </table>
				  <table class="gv_table_2" border="0" cellpadding="2" cellspacing="2" id="fee_subjects">
				  	 <tr>
					  	<th>序号</th>
					  	<th>院校</th>
		                <th>标题</th>
		                <th>费用科目</th>
		                <th>缴费次数</th>
		                <th>优惠主体</th>
		                <th>优惠标准</th>
		             </tr>
		             <s:iterator id="item" value="discountPolicyList" status="dp"> 
						<tr>
							<td align="center"><s:property value="#dp.count"/></td>
							<td align="center">${item.academyname}</td>
							<td align="center">${item.title}</td>
							<td align="center">${item.feesubjectname}</td>
							<td align="center">第${item.feePaymentId}次缴费</td>
							<td align="center">${item.targetObjectName}</td>
							<td align="center">${item.discountstandard}</td>
						</tr>
					</s:iterator>
				  </table>
				 
				  <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="button" onclick="javascript:window.close();" value="关闭" /></td></tr></table>
				  
			</body:body>
  </body>
</html>
