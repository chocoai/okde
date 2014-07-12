<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>总部替院校垫付退费(总部收到确认)</title>
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
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		
		<%@ include file="common_jilian.jsp" %>
		<script type="text/javascript">
			var refundCeduAcademyId = 0;
			var status = 0;
			
			function customCreatedTime(createdtime)
			{
				return createdtime;
			}
			function customPaymentCode(code)
			{
				return code;
			}
			function customBranchName(branchName)
			{
				return branchName
			}
			function customStuName(stuname)
			{
				return stuname;
			}
			function customSchoolName(schoolname)
			{
				return schoolname;
			}
			function customAcademyenrollbatchName(academyenrollbatchname)
			{
				return academyenrollbatchname;
			}
			function customLevelName(levelname)
			{
				return levelname;
			}
			function customMajorName(majorname)
			{
				return majorname;
			}
			function customFeePayment(amountpaied)
			{
				return amountpaied;
			}
			function customRechargeAmount(rechargeamount)
			{
				return rechargeamount;
			}
			function customRefundPayment(refundpayment)
			{
				return refundpayment;
			}
			function customTotalAmount(totalamount)
			{
				return totalamount;
			}
			function customNote(note)
			{
				return note;
			}
			function customStatus(status)
			{
				if(status == REFUND_ADVANCE_STATUS_NOT_PAYMENT)
				{
					return "院校未汇款";
				}
				else if(status == REFUND_ADVANCE_STATUS_PAYMENT)
				{
					return "院校已汇款";
				}
				else if(status == REFUND_ADVANCE_STATUS_RECEIVE)
				{
					return "总部已收款";
				}
				else
				{
					return "--";
				}
			}
			function customOperation(id,status)
			{
				if(status==REFUND_ADVANCE_STATUS_PAYMENT)
				{
					return "<a href='#' onclick='updateStatus("+id+")'>确认</a>";
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
				jQuery('#branchId').change(function(){
					ajax_100_1();//全局批次					
				});
				
			});
			//统计缴费单金额 
			function countallmoney(data)
			{
				//alert(data.allRefundPaymentMoney);
				jQuery("#moneyall").html("退费合计：<font style='color:red'><b>"+data.allRefundPaymentMoney+"</b></font>元");
			}
			function countCallback001(data){
				ajax_1110_1();//统计退费单明细金额
			}
			//修改状态
			function updateStatus(id)
			{
				refundCeduAcademyId = id;
				$('#message_confirm_one').dialog('open');
			}
			//修改状态：总部已收到
			function updateStatusAjax()
			{
				status = REFUND_ADVANCE_STATUS_RECEIVE;
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
					jQuery("#showDialog").html('<b>确认失败！状态可能已被修改！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				//search001();
				refresh001();
			}
		</script>
		<!-- 统计退费单明细金额 -->
		<a:ajax 
			parameters="jQuery('#myform').serializeObject()" 
			successCallbackFunctions="countallmoney" 
			pluginCode="1110" 
			urls="finance/refund/sum_refund_payment_cedu_academy_ajax"
		/>
		<!-- 修改状态 -->
		<a:ajax 
			parameters="{'refundCeduAcademyId':refundCeduAcademyId,'status':status}" 
			successCallbackFunctions="update_status_callback" 
			pluginCode="2220" 
			urls="finance/refund/update_refund_payment_cedu_academy_status_ajax"
		/>
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="退费（总部垫付）">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<%@include file="_refund_cedu_advance.jsp" %>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">总部收款确认</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">
				<table class="add_table" border="0">
					<tr>
						<td align="right">学习中心：</td>
		                <td align="left">
							<s:if test="%{branchlist!=null}">
								<s:select list="%{branchlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="student.branchId" id="branchId" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="student.branchId" id="branchId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>
						</td>
		                
		                <td align="right">全局批次：</td>
		                <td align="left">
							<select id="globalBatchId" name="feePayment.commonBatch" class="txt_box_150">
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
	             	</tr>
	             	<tr >
	             		<td align="right">状态：</td>
	             		<td>
	             			<select name="refundCeduAcademy.status" id="paymentStatus" class="txt_box_150">
								<option value="0">--请选择--</option>
								<option value="<%=Constants.REFUND_ADVANCE_STATUS_NOT_PAYMENT %>">院校未汇款</option>
								<option selected="selected" value="<%=Constants.REFUND_ADVANCE_STATUS_PAYMENT %>">院校已汇款</option>
								<option value="<%=Constants.REFUND_ADVANCE_STATUS_RECEIVE %>">总部已收款</option>					  
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
										searchCountActionpath="finance/refund/count_refund_payment_cedu_academy_ajax"
										searchListActionpath="finance/refund/list_refund_payment_cedu_academy_ajax"
										columnsStr="refundcreatedtime;refundpaymentcode;branchName;studentName;schoolName;academyenrollbatchName;levelName;majorName;refundPayment;refundnote;#status;#operation"
										customColumnValue="0,customCreatedTime(feePayment.createdTime);1,customPaymentCode(feePayment.code);2,customBranchName(student.branchName);3,customStuName(student.name);4,customSchoolName(student.schoolName);5,customAcademyenrollbatchName(student.academyenrollbatchName);6,customLevelName(student.levelName);7,customMajorName(student.majorName);8,customRefundPayment(refundAmount);9,customNote(feePayment.note);10,customStatus(status);11,customOperation(id,status)"
										pageSize="10"
										isPackage="false"
										params="'result.order':'createdTime','result.sort':'desc'"
										searchFormId="myform"
										customToolbarID="moneyall"
										listCallback="countCallback"
									/>
									<!-- 
									暂时屏蔽的列：feePayment;rechargeAmount;totalAmount;
									7,customFeePayment(amountPaied);8,customRechargeAmount(rechargeAmount);10,customTotalAmount(totalAmount);
									 -->
				
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
