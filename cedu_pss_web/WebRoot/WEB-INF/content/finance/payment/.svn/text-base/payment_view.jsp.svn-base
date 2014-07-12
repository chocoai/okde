<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>缴费单详情</title>
		
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
		<head:head title="缴费单详情">
			<html:a text="关闭" icon="return" onclick="window.close();" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				 <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">缴费单</th>
							
					</tr>
				  </table>
				  <table class="add_table" id="enrollbatch" class="enrollbatch" border="0" cellpadding="2" cellspacing="2">
				  	<tbody>
				  		<tr>
							<td class="lable_100">缴费单单号:</td>					
							<td >
					  			${feePayment.code}	
							</td>
							<td class="lable_100">收据号:</td>					
							<td >
					  			${feePayment.barCode}	
							</td>
							<td class="lable_100">总缴费金额:</td>					
							<td >
					  			${feePayment.totalAmount}	
							</td>
						</tr>
					  	<tr>
							<td class="lable_100">院校:</td>					
							<td >
					  			${academyName}	
							</td>
							<td class="lable_100">学习中心:</td>					
							<td >
					  			${branchName}	
							</td>
							<td class="lable_100">姓名:</td>					
							<td >
					  			${student.name}	
							</td>
							
						</tr>
						<tr>
							<td class="lable_100">性别:</td>					
							<td >
					  			${student.gender==1?"男":"女"}	
							</td>
							<td class="lable_100">批次:</td>					
							<td >
					  			${batchName}	
							</td>
							<td class="lable_100">层次:</td>					
							<td >
					  			${levelName}	
							</td>					
						</tr>
						<tr>
							<td class="lable_100">专业:</td>					
							<td >
					  			${majorName}	
							</td>
							<td colspan="4"></td>
						</tr>
				  	</tbody>
				  </table>
				  <s:if test="%{feePaymentDetailList!=null && feePaymentDetailList.size()>0}">	
				   <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">缴费单明细</th>
							
				  	</tr>
				  </table>
				 
				  <table class="add_table" id="feebatch" border="0" cellpadding="2" cellspacing="2" style="">
					 	                	
						 <tbody>
						 	<tr>
							 	<td align="center">费用项目</td>	
							 	<td align="center">应缴金额</td>
							 	<td align="center">总优惠金额</td>
							 	<td align="center">院校优惠金额</td>
							 	<td align="center">院校_弘成共同优惠金额</td>
							 	<td align="center">弘成优惠金额</td>
							 	<td align="center">中心优惠金额</td>
							 	<td align="center">合作方优惠金额</td>
							 	<td align="center">实收金额</td>
							 	<td align="center">使用充值金额</td>
							 	
						 	</tr>
						 	<s:iterator id="item" value="feePaymentDetailList" status="sta"> 
						 		<tr>
						 			<td align="center">${item.paymentSubjectName}</td>								 	
								 	<td align="center">${item.moneyToPay}</td>
								 	<td align="center">${item.discountAmount}</td>
								 	<td align="center">${item.academyDiscount}</td>
								 	<td align="center">${item.academyCeduDiscount}</td>
								 	<td align="center">${item.ceduDiscount}</td>
								 	<td align="center">${item.branchDiscount}</td>
								 	<td align="center">${item.channelDiscount}</td>
								 	<td align="center">${item.amountPaied}</td>
								 	<td align="center">${item.rechargeAmount}</td>
								 	
						 		</tr>
						 	</s:iterator>
				        </tbody>
				  </table>	
				 </s:if>
				 <s:if test="%{saamlist!=null && saamlist.size()>0}">	
				  	<table class="gv_table_2">
					  	<tr>
							 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
							 	<th style="text-align:left; font-weight:bold;">学生账户充值明细</th>
								
					  	</tr>
					  </table>
					 
					  <table class="add_table" id="feebatch" border="0" cellpadding="2" cellspacing="2" style="">		                	
							 <tbody>
							 	<tr>
								 	<td align="center" width="20%">费用项目</td>							 	
								 	<td align="center" width="20%">充值金额</td>
								 	<td align="center" width="60%"></td>
								 	
							 	</tr>
							 	<s:iterator id="item" value="saamlist" status="sta"> 
							 		<tr>
							 			<td align="center">${item.feeSubjectName}</td>
									 	<td align="center">${item.accountMoney}</td>
										<td align="center"></td>
								 		
							 		</tr>
							 	</s:iterator>
					        </tbody>
					  </table>
				  </s:if>		
			</body:body>
	
  </body>
</html>
