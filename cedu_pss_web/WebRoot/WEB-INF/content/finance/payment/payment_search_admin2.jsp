<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<%@ include file="../../template/common/download_excel.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>缴费单查询(总部)</title>
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
		<%@ include file="common.jsp" %>
		<script type="text/javascript">
			function successCallbackFunction(data){
				$('#message_confirm').dialog("close");
			}
			
			function listbranch(data)
			{
				var lists='';
				
				if(data.branchlist!=null && data.branchlist.length>0)
				{
					lists+='<option value="0" >--请选择--</option>';
					$.each(data.branchlist,function(){
					lists+='<option value="'+this.id+'" >'+this.name+'</option>';
					});
				}
				$('#branchId').html(lists);
			}
			
			//统计缴费单金额 
			function countallmoney(data)
			{
				//alert(data.allFeePaymentMoney);
				jQuery("#moneyall").html("合计：<font style='color:red'><b>"+data.allFeePaymentMoney+"</b></font>元");
			}
			
			
		</script>
		<a:ajax parameters="jQuery('#myform1').serializeObject();null" successCallbackFunctions="successCallbackFunction;listbranch" pluginCode="0001" urls="finance/payment/update_paymentl_print_status;crm/all_branch_list"/>
		<!-- 统计缴费单金额 -->
		<a:ajax 
			parameters="jQuery('#myform').serializeObject(),'globalids':globalids" 
			successCallbackFunctions="countallmoney" 
			pluginCode="1110" 
			urls="finance/payment/count_payment_serach_all_money_ajax2"
		/>
		<a:ajax 
			parameters="null"
			successCallbackFunctions="ajax_globalenroll" 
			pluginCode="1200" 
			urls="crm/gl_enroll_batch_list"
		/>
		
		<!-- 下载地址 -->
		<a:ajax 
			parameters="jQuery('#myform').serializeObject(),'globalids':globalids" 
			successCallbackFunctions="excel_export_callback" 
			pluginCode="download_zip" 
			urls="finance/payment/excel_export_payment_search_admin2"
		/>
		<script type="text/javascript">
		var globalids='';
		
			//导出
			function download(){
				if($("#branchId").val()=="0"){
					alert("必须选择学习中心！");
					return false;
				}
				if(confirm("您确定要导出数据吗？")){
					globalids='';
					$('.golbalenrolllists').each(function(){
						if($(this).attr("checked"))
						{
							globalids+=$(this).val();
							globalids+=',';
						} 
					}) 
					ajax_download_zip_1();
				}
			}
			//查询数据
			function showsearch()
			{
				globalids='';
				$('.golbalenrolllists').each(function(){
					if($(this).attr("checked"))
					{
						globalids+=$(this).val();
						globalids+=',';
					} 
				}) 
				$('#globalids').val(globalids);
				search001();
			}
			isLoadPaymentWay=true;
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
			function operatingValue(id,isPrint){
				if(isPrint==1)
					return "--";
				return "<a href='#' onclick='paymentConfirm("+id+")'>打印</a>";
			}
			function isPrintValue(isPrint){
				return isPrint==0?"未打印":"已打印";
			}
			function statusValue(status){
				return status.getPaymentStatus();
			}
			function feeWayIdValue(feeWayId){
				return feeWayId.getPaymentWay();
			}
			function showcode(code,id,pamentType)
			{
				if(pamentType==FEE_PAYMENT_TYPE_REFUND_SINGLE)
				{
					return "<a href='"+WEB_PATH+"/finance/refund/view_refund_payment?feePaymentId="+id+"' target='_blank'>"+code+"</a>";
				}
				return "<a href='"+WEB_PATH+"/finance/payment/payment_view?feePaymentId="+id+"' target='_blank'>"+code+"</a>";
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
				ajax_0001_2();
				
				
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
				
				
				//学习中心改变事件
				//jQuery('#branchId').change(function(){
				//	ajax_100_1();//全局批次					
				//});
				
				//查询所有全局批次
				ajax_1200_1();
				
			});
			
			function countCallback001(data){
				ajax_1110_1();//统计缴费单金额
			}
			
			//回调函数，查询所有全局批次
			function ajax_globalenroll(data){
				var lists='';
				var i=0;
				$(data.globalEnrollBatchs).each(function(){
					if(i>0&&i%4==0)
					{
						lists+='<br/>';
					}
					lists+='<input type="checkbox" class="golbalenrolllists" value="'+this.id+'"/>'+this.batch;
					i++;
				});
				$('#globalBatchCheckBox').html(lists);
			}
		</script>
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="多批次缴费单查询">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="myform1" name="myform1">
					<input type="hidden" name="feePayment.isPrint" value="1"/>
					<input type="hidden" id="fid" name="feePayment.id" value=""/>
				</form>
				<div id="message_confirm" style="display:none">
						<div>确认执行该操作！</div>
				</div>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">多批次缴费单查询</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">
					<input type="hidden" name="feePayment.isPrint" id="isPrint" value="-1"/>
					<input type="hidden" name="globalids" id="globalids"/>
				<table class="add_table" border="0">
					<tr>
						<td align="right">学习中心：</td>
		                <td align="left">
							
							<select id="branchId" name="student.branchId"  class="txt_box_150">
							<option value="0">--请选择--</option>
							</select>
						</td>
						<td align="right">全局批次：</td>
		                <td align="left">
		                	<div id="globalBatchCheckBox"></div>
							<!-- 
							<select name="" id="globalBatchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
							 -->
						</td>
	              	</tr>
				  		
	             	<tr >
						
						<td align="right">缴费方式：</td>
	             		<td>
	             			<!-- 
	             			<select name="feePayment.feeWayId" id="paymentway" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
							 -->
							
							<s:select name="feePayment.feeWayId" id="paymentway" cssClass="txt_box_150" headerKey="0" headerValue="-----请选择-----" list="list" listKey="id" listValue="name"></s:select>
						
						
						
						</td>
					</tr>
					<tr>	
 						<td align="right">缴费日期起：</td>
		                <td align="left">
							<input type="text" name="starttime" id="starttime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'endtime\',{d:0});}'})" readonly="readonly"/>					  			
						</td>
		                <td align="right">缴费日期止：</td>
		                <td align="left">
							<input type="text" name="endtime" id="endtime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'starttime\',{d:0});}'})" readonly="readonly"/>
						</td>
	             	</tr>
	             	<tr>	  		
						<td colspan="4"></td>
		                <td align="right">
							<input type="button" class="btn_black_61"  onclick="showsearch();" value="查询"/>
						</td>
						<td align="left">
								<input class="btn_black_61" type="button" onclick="download();"  value="导出" />
						</td>
	             	</tr>
				</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="finance_payment"
										searchCountActionpath="finance/payment/count_payment_serach_page_ajax2"
										searchListActionpath="finance/payment/list_payment_serach_page_ajax2"
										columnsStr="createdTime;paymentcode;studentName;schoolName;academyenrollbatchName;levelName;majorName;feeWayId;feePayment;rechargeAmount;discountAmount;totalAmount;pamentType;status"
										customColumnValue="1,showcode(code,id,pamentType);7,feeWayIdValue(feeWayId);8,feePaymentValue(feePayment);9,feePaymentValue(rechargeAmount);10,feePaymentValue(discountAmount);11,feePaymentValue(totalAmount);12,pamentTypeValue(pamentType);13,statusValue(status)"
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
  </body>
</html>
