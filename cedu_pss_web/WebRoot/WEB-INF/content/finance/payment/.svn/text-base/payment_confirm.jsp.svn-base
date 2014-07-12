<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>上门收费确认</title>
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
		
		<%@ include file="common.jsp" %>
		<script type="text/javascript">
			function successCallbackFunction(data){
				$('#message_confirm').dialog("close");
				//search001();
				refresh001();
				jQuery("#showDialog").html('<b>操作成功！</b>');
				jQuery('#message_returns_tips').dialog("open");
			}
		</script>
		<a:ajax parameters="jQuery('#myform1').serializeObject()" successCallbackFunctions="successCallbackFunction" pluginCode="0001" urls="finance/payment/update_paymentl_status"/>
		<script type="text/javascript">
			//开启缴费方式查询
			isLoadPaymentWay=true;
			
			function feePaymentValue(feePayment){
				return (feePayment+"").toMoney();
			}
			/*function pamentTypeValue(pamentType){
				return pamentType==0?"预缴费单":"正式缴费单";
			}*/
			function operatingValue(id){
				return "<a href='#' onclick='paymentConfirm("+id+")'>缴费确认</a>";
			}
			function statusValue(status){
				return status.getPaymentStatus();
			}
			function feeWayIdValue(feeWayId){
				return feeWayId.getPaymentWay();
			}
			function showcode(code,id)
			{
				return "<a href='"+WEB_PATH+"/finance/payment/payment_view?feePaymentId="+id+"' target='_blank'>"+code+"</a>";
			}
			
			function paymentConfirm(id){
				//alert(id);
				$("#fid").val(id);
				
				$('#message_confirm').dialog({
					buttons: {
						'确认': function() { 
							ajax_0001_1();
							//search001();
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				$('#message_confirm').dialog("open");
			}
			$(document).ready(function(){
				$('#message_confirm').dialog({
						autoOpen:false,
						modal:true,
						draggable:false,
						resizable:false,
						title:'提示',
						width: 260,
						height: 125
						
				});
				
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
			});
		</script>
		
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="上门收费确认">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="myform1" name="myform1">
					<input type="hidden" name="feePayment.status" value="<%=Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN %>"/>
					<input type="hidden" id="fid" name="feePayment.id" value=""/>
				</form>
				<div id="message_confirm" style="display:none">
						<div>确认执行该操作！</div>
				</div>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">上门收费确认</th>
						
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
	             		<td align="right">缴费方式：</td>
	             		<td>
	             			<select name="feePayment.feeWayId" id="paymentway" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>	             		
	             		<td></td>
		                <td align="left">
							<input type="button" class="btn_black_61"  onclick="search001();" value="查询"/>
						</td>
	             	</tr>
				</table>
				</form>
								<page:plugin 
										pluginCode="001"
										il8nName="finance_payment"
										searchCountActionpath="finance/payment/finance_payment_count"
										searchListActionpath="finance/payment/finance_payment_list"
										columnsStr="createdTime;paymentcode;studentName;schoolName;academyenrollbatchName;levelName;majorName;feeWayId;feePayment;rechargeAmount;totalAmount;status;#public.operating"
										customColumnValue="1,showcode(code,id);7,feeWayIdValue(feeWayId);8,feePaymentValue(feePayment);9,feePaymentValue(rechargeAmount);10,feePaymentValue(totalAmount);11,statusValue(status);12,operatingValue(id)"
										pageSize="10"
										isPackage="false"
										params="'result.order':'createdTime','result.sort':'desc','feePayment.status':PAYMENT_STATUS_YI_KAI_DAN"
										searchFormId="myform"
									/>
		</body:body>
	<!--弹出层-->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
  </body>
</html>
