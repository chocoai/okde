<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>预缴费处理</title>
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
			}
		</script>
		<a:ajax parameters="jQuery('#myform1').serializeObject()" successCallbackFunctions="successCallbackFunction" pluginCode="0001" urls="finance/payment/update_paymentdetail_status"/>
		
		<script type="text/javascript">
			isLoadPaymentWay=true;
			function feePaymentValue(feePayment){
				return (feePayment+"").toMoney();
			}
			function typesValue(types){
				if(types==1){
					return "预缴费单";
				}else if(types==2){
					return "正式缴费单";
				}else{
					return "--";
				}
			}
			function operatingValue(id){
				return "<a href='#' onclick='paymentConfirm("+id+")'>转正式缴费单 </a> 拆单";
			}
			function feeWayIdValue(feeWayId){
				return feeWayId.getPaymentWay();
			}
			function detailscodeValue(code){
				return code;
			}
			function paymentConfirm(id){
				//alert(id);
				$("#fid").val(id);
				
				$('#message_confirm').dialog({
					buttons: {
						'确认': function() { 
							ajax_0001_1();
							search001();
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
			});
		</script>
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="预缴费处理">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="myform1" name="myform1">
					<input type="hidden" name="feePaymentDetail.types" value="2"/>
					<input type="hidden" id="fid" name="feePaymentDetail.id" value=""/>
				</form>
				<div id="message_confirm" style="display:none">
						<div>确认执行该操作！</div>
				</div>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">缴费明细</th>
						
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
		               <td></td>
						
	              	</tr>
				  	<tr>
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
						<td align="right">姓名：</td>
		                <td align="left">
		                	<input type="text" name="student.name" id="name" class="txt_box_150"/>					
						</td>
						<td></td>
	             	</tr>
	             	<tr >
	             		<td align="right">缴费方式：</td>
	             		<td>
	             			<select name="feePayment.feeWayId" id="paymentway" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
	             		
	             		 <td align="right">招生批次：</td>
		                <td align="left">
							<span style="color: black !important;" id="batch" name="batch"></span>
							<input type="hidden" id="batchId" name="student.enrollmentBatchId" value="0"/>
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
										searchCountActionpath="finance/payment/finance_paymentdetail_count"
										searchListActionpath="finance/payment/finance_paymentdetail_list"
										columnsStr="createdTime;paymentCode;code;studentName;schoolName;academyenrollbatchName;levelName;majorName;feeWayId;paymentSubjectName;amountPaied;types;#public.operating"
										customColumnValue="2,detailscodeValue(code);8,feeWayIdValue(feeWayId);10,feePaymentValue(amountPaied);11,typesValue(types);12,operatingValue(id)"
										pageSize="10"
										isPackage="false"
										params="'result.order':'createdTime','result.sort':'desc','feePaymentDetail.types':1"
										searchFormId="myform"
									/>
				
		</body:body>
	
  </body>
</html>
