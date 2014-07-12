<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>退费单审批</title>
		
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
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<script type="text/javascript">
			//加载事件
			jQuery(function()
			{
				//是否是修改完后的页面
				$('#null_for_close').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示',
					buttons: {
						'确定': function() { 
							window.close();
							} 
					}
				});	
			});
			function showsubmit()
			{
				ajax_130_1();
			}
			//ajax回调函数   审批退费
			function ajax_conrefundpayment(data)
			{			
				if(data.isback)
				{						
					jQuery("#showclose").html('<b>操作成功！<br/>点击确定关闭当前页。</b>');
					$('#null_for_close').dialog("open");	
				}
				else
				{
					jQuery("#showDialog").html('<b>操作失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
			}
		</script>
		<!--审批退费单-->
		<a:ajax 
			pluginCode="130"
			successCallbackFunctions="ajax_conrefundpayment" 
			parameters="{feePaymentId:jQuery('#feePaymentId').val(),types:jQuery(\"input[name='confrimrad']:checked\").val()}" 
			urls="/finance/refund/confirm_refund_payment_ajax" 			
		/>
	</head>
  
  <body>
		<!-- 头开始 -->
		<head:head title="退费单审批">
			<html:a text="关闭" icon="return" onclick="window.close();" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				 <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">退费单</th>
							
					</tr>
				  </table>
				  <table class="add_table" id="enrollbatch" class="enrollbatch" border="0" cellpadding="2" cellspacing="2">
				  	<tbody>
				  		<tr>
							<td class="lable_100">退费单单号:</td>					
							<td >
					  			${feePayment.code}	
							</td>
							<td class="lable_100">总退费金额:</td>					
							<td >
					  			${feePayment.feePayment}	
							</td>
						</tr>
					  	<tr>
							<td class="lable_100">院校:</td>					
							<td >
					  			${academyName}	
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
						</tr>
						<tr>	
							<td class="lable_100">层次:</td>					
							<td >
					  			${levelName}	
							</td>
							<td class="lable_100">专业:</td>					
							<td >
					  			${majorName}	
							</td>
						</tr>
						<tr>
							<td class="lable_100">退费原因:</td>	
							<td colspan="3">
					  			<textarea name="feePayment.note" id="note" cols="60" rows="4" readonly="readonly">${feePayment.note}</textarea>	
							</td>
						</tr>
				  	</tbody>
				  </table>
				  
				  <s:if test="%{feePaymentDetailList!=null && feePaymentDetailList.size()>0}">
					   <table class="gv_table_2">
					  	<tr>
							 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
							 	<th style="text-align:left; font-weight:bold;">缴费单退费明细</th>
								
					  	</tr>
					  </table>
					 
					  <table class="add_table" id="feebatch" border="0" cellpadding="2" cellspacing="2" style="">		                	
							 <tbody>
							 	<tr>
								 	<td align="center" width="20%">费用项目</td>							 	
								 	<td align="center" width="20%">中心退学生金额</td>
								 	<td align="center" width="20%">弘成退学生金额</td>
								 	<td align="center" width="20%">院校退学生金额</td>
								 	<td align="center" width="20%">合作方退学生金额</td>
								 	
							 	</tr>
							 	<s:iterator id="item" value="feePaymentDetailList" status="sta"> 
							 		<tr>
							 			<td align="center">${item.paymentSubjectName}</td>
									 	<td align="center">${item.branchAccount}</td>
									 	<td align="center">${item.ceduAccount}</td>
									 	<td align="center">${item.academyAccount}</td>
									 	<td align="center">${item.channelAccount}</td>
	
							 		</tr>
							 	</s:iterator>
					        </tbody>
					  </table>	
				  </s:if>	
				  <s:if test="%{saamlist!=null && saamlist.size()>0}">	
				  	<table class="gv_table_2">
					  	<tr>
							 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
							 	<th style="text-align:left; font-weight:bold;">充值账户退费明细</th>
								
					  	</tr>
					  </table>
					 
					  <table class="add_table" id="feebatch" border="0" cellpadding="2" cellspacing="2" style="">		                	
							 <tbody>
							 	<tr>
								 	<td align="center" width="20%">费用项目</td>							 	
								 	<td align="center" width="20%">退费金额</td>
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
				  <s:if test="%{(feePaymentDetailList!=null && feePaymentDetailList.size()>0) || (saamlist!=null && saamlist.size()>0)}">
				  	  <table class="add_table" id="feebatch" border="0" cellpadding="2" cellspacing="2" style="">		                	
						<tbody>
				  			<tr>
						 		<td align="center" colspan="6">
						 			<input type="hidden" name="feePaymentId" id="feePaymentId" value="${feePayment.id}" />
						 			<input type="radio" name="confrimrad" checked="checked" value="<%=Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN %>"/>通过
						 			<input type="radio" name="confrimrad" value="<%=Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_SHI_BAI %>"/>不通过
									<input class="btn_black_61" type="button" value="确定" onclick="showsubmit();" id="submit"/>
									<input class="btn_black_61" type="button" value="关闭" onclick="javascript:window.close();"/>
								</td>
						 	</tr>
						 </tbody>
					 </table> 	
				  </s:if>	  			
			</body:body>
		
		<!-- 弹出层 -->
		<div id="null_for_close" style="display:none">
			<div align="center" id="showclose">
			
			</div>
		</div>
		<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
			
		</div>
	</div>
  </body>
</html>
