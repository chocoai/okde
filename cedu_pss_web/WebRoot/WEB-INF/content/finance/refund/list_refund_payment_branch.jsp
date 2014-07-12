<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>退费查询(中心)</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		
		<%@ include file="common_jilian.jsp" %>
		<script type="text/javascript">
			var feePaymentId = 0;
			var status = 0;
			
			//查询数据
			function showsearch()
			{
				if(jQuery("#feemoney").val()!="" && dealwithmoney(jQuery("#feemoney").val())==-1)
				{
					jQuery("#showDialog").html('<b>缴费金额查询条件输入格式不正确，只能输入整数和小数！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else
				{
					if(jQuery("#feemoney").val()!="")
					{
						jQuery("#feemoney").val(dealwithmoney(jQuery("#feemoney").val()));
					}
					search001();
				}
			}
			
			function feePaymentValue(feePayment){
				return (feePayment+"").toMoney();
			}
			function pamentTypeValue(pamentType)
			{
				if(pamentType==FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE)
				{
					return "正式缴费单";
				}
				else if(pamentType==FEE_PAYMENT_TYPE_PRE_BILLING)
				{
					return "预缴费单";
				}
				else if(pamentType==FEE_PAYMENT_TYPE_REFUND_SINGLE)
				{
					return "退费单";
				}
				else
				{
					return "--";
				}
				
			}
			
			function statusValue(status)
			{
			
				return status.getPaymentStatus();
			}
			
			function showcode(code,id)
			{
				return "<a href='"+WEB_PATH+"/finance/refund/view_refund_payment?feePaymentId="+id+"' target='_blank'>"+code+"</a>";
			}
			
			//修改成功退费
			function customOperation(id,status)
			{
				if(status==PAYMENT_STATUS_KE_YI_ZHI_JIE_TUI_FEI)
				{
					return "<a href='#' onclick='updateStatus("+id+")'>成功退费</a>";
				}
				return "";
			}
			
			$(document).ready(function(){
				//信息提示
				jQuery('#message_returns_tips').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示',
					buttons: {
						'关闭': function() { 
							jQuery(this).dialog("close"); 
						} 
					}
				});	
				$('#message_confirm_one').dialog({
					autoOpen:false,
					modal:true,
					title:'确认操作',
					buttons: {
						'确认': function() { 
							updateStatusAjax();
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						}
					}
				});
			});
			//统计缴费单金额 
			function countallmoney(data)
			{
				//alert(data.allFeePaymentMoney);
				jQuery("#moneyall").html("合计：<font style='color:red'><b>"+data.allFeePaymentMoney+"</b></font>元 (只统计退费审批通过后的退费单金额)");
			}
			function countCallback001(data){
				ajax_1110_1();//统计缴费单金额
			}
			//修改状态
			function updateStatus(id)
			{
				feePaymentId = id;
				$('#message_confirm_one').dialog('open');
			}
			//修改状态：退费单和明细:成功退费，费用表:学生退费已确认
			function updateStatusAjax()
			{
				status = PAYMENT_STATUS_TUI_FEI_SUCCESS;
				status2 = REFUND_BRANCH_STATUS_YI_CHENG_GONG_HUI_KUAN;
				ajax_2220_1();
			}
			//确认回调函数
			function update_status_callback(data)
			{
				$('#message_confirm_one').dialog('close');
				if(data.isback)
				{
					jQuery("#showDialog").html('<b>确认成功！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else
				{
					jQuery("#showDialog").html('<b>确认失败！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				//search001();
				refresh001();
			}
		</script>
		<!-- 统计缴费单金额 -->
		<a:ajax 
			parameters="jQuery('#myform').serializeObject()" 
			successCallbackFunctions="countallmoney" 
			pluginCode="1110" 
			urls="finance/payment/count_payment_serach_all_money_ajax"
		/>
		<!-- 修改状态 -->
		<a:ajax 
			parameters="{'feePaymentId':feePaymentId,'status':status,'status2':status2}" 
			successCallbackFunctions="update_status_callback" 
			pluginCode="2220" 
			urls="finance/refund/update_status_refund_payment_ajax"
		/>
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="退费查询(中心)">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">退费查询</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">	
				<table class="add_table" border="0">
					<tr>
						<td align="right">学习中心：</td>
		                <td align="left">
							${branch.name}
							<input type="hidden" id="branchId" name="student.branchId" value="${branch.id}"/>
						</td>
		                
		                <td align="right">全局批次：</td>
		                <td align="left">
							<select name="" id="globalBatchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td align="right">院校：</td>
		                <td align="left">
		                	<select name="student.academyId" id="academyId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>	
						</td>
		               
						
	              	</tr>
				  	<tr>
				  		<td align="right">招生批次：</td>
		                <td align="left">
							<span style="color: black !important;" id="batch" name="batch"></span>
							<input type="hidden" id="batchId" name="student.enrollmentBatchId" value="0"/>
						</td>
				  		<td align="right">层次：</td>
		                <td>
							<select name="student.levelId" id="levelId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td align="right">专业：</td>
		                <td align="left">
							<select name="student.majorId" id="majorId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
						
						
	             	</tr>
	             	<tr >
	             		<td align="right">姓名：</td>
		                <td align="left">
		                	<input type="text" name="student.name" id="name" class="txt_box_150"/>					
						</td>
						<td align="right">身份证：</td>
		                <td align="left">
		                	<input type="text" name="student.certNo" id="certNo" class="txt_box_150"/>					
						</td>
	             		<td align="right">退费单号：</td>
		                <td align="left">
		                	<input type="text" name="feePayment.code" id="code" class="txt_box_150"/>					
						</td>
	             		<!-- td align="right">POS流水号：</td>
	             		<td><input type="text" name="feePayment.posSerialNo" id="pno" class="txt_box_150"/></td> -->
	             		
						
	             	</tr>
	             	<tr >
	             		<!-- td align="right">缴费金额：</td>
		                <td align="left">
		                	<input type="text" name="feemoney" id="feemoney" class="txt_box_150"/>					
						</td> -->
	             		<td align="right">退费单状态：</td>
	             		<td>
	             			<select name="feePayment.status" id="paymentStatus" class="txt_box_150">
								<option value="0">--请选择--</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_TUI_FEI_DAN %>">已申请退费</option>
								<option value="<%=Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_TONG_GUO %>">已退费确认</option>
								<option value="<%=Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_SHI_BAI %>">退费审批失败</option>
								<option value="<%=Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN %>">退费审批通过待确认</option>	
								<option value="<%=Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN %>">退费审批通过已确认</option>
								<option value="<%=Constants.PAYMENT_STATUS_KE_YI_ZHI_JIE_TUI_FEI %>">可以直接退费</option>
								<option value="<%=Constants.PAYMENT_STATUS_TUI_FEI_SUCCESS %>">成功退费</option>					  
							</select>
						</td>
 						
						<td align="right">退费日期起：</td>
		                <td align="left">
							<input type="text" name="starttime" id="starttime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'endtime\',{d:0});}'})" readonly="readonly"/>					  			
						</td>
		                <td align="right">退费日期止：</td>
		                <td align="left">
							<input type="text" name="endtime" id="endtime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'starttime\',{d:0});}'})" readonly="readonly"/>
						</td>
	             	</tr>
	             	<tr>
				  		
				  		
						<td colspan="5"></td>
		                <td align="left">
		                	<input type="hidden" name="feePayment.isPrint" id="isPrint" value="-1"/>
		                	<input type="hidden" name="feePayment.pamentType" id="pamentType" value="<%=Constants.FEE_PAYMENT_TYPE_REFUND_SINGLE %>"/>
							<input type="button" class="btn_black_61"  onclick="search001();" value="查询"/>
						</td>
	             	</tr>
				</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="finance_payment"
										subStringLength="10"
										searchCountActionpath="finance/payment/count_payment_serach_page_ajax"
										searchListActionpath="finance/payment/list_payment_serach_page_ajax"
										columnsStr="createdTime;#refundcode;studentName;schoolName;academyenrollbatchName;levelName;majorName;#refundmoney;rechargeAmount;#refundallmoney;note;pamentType;status;#operation"
										customColumnValue="1,showcode(code,id);7,feePaymentValue(feePayment);8,feePaymentValue(rechargeAmount);9,feePaymentValue(totalAmount);11,pamentTypeValue(pamentType);12,statusValue(status);13,customOperation(id,status)"
										pageSize="10"
										isPackage="false"
										params="'result.order':'createdTime','result.sort':'desc'"
										searchFormId="myform"
										customToolbarID="moneyall"
										listCallback="countCallback"
									/>
				
		</body:body>
		<!--  弹出层           -->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="message_confirm_one" style="display:none">
			<div align="center">是否要执行确认？</div>
		</div>
  </body>
</html>
