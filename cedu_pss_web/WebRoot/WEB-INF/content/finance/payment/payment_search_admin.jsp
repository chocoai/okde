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
			parameters="jQuery('#myform').serializeObject()" 
			successCallbackFunctions="countallmoney" 
			pluginCode="1110" 
			urls="finance/payment/count_payment_serach_all_money_ajax"
		/>
		<!-- 下载地址 -->
		<a:ajax 
			parameters="jQuery('#myform').serializeObject()" 
			successCallbackFunctions="excel_export_callback" 
			pluginCode="download_zip" 
			urls="finance/payment/excel_export_payment_search_admin"
		/>
		
		<!-- 下载预缴费单地址 -->
		<a:ajax 
			parameters="jQuery('#myform').serializeObject()" 
			successCallbackFunctions="excel_export_callback" 
			pluginCode="download_yujiaofei_zip" 
			urls="finance/payment/excel_export_yujiaofei_payment_search_admin"
		/>
		
		<script type="text/javascript">
		
			function download(){
				if($("#branchId").val()=="0"){
					alert("必须选择学习中心！");
					return false;
				}
				if($("#pamentType").val()==FEE_PAYMENT_TYPE_PRE_BILLING){
					alert("要导入预缴费单请用“导出预缴费单”功能！");
					return false;
				}
				if(confirm("您确定要导出数据吗？")){
					ajax_download_zip_1();
				}
			}
			//导出预缴费单
			function downloadyujiaofeidan(){
				if($("#pamentType").val()!=FEE_PAYMENT_TYPE_PRE_BILLING){
					alert("缴费单类别必须选择为预缴费单！");
					return false;
				}
				if(confirm("您确定要导出预缴费单数据吗？")){
					ajax_download_yujiaofei_zip_1();
				}
			}
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
				jQuery('#branchId').change(function(){
					ajax_100_1();//全局批次					
				});	
			});
			
			function countCallback001(data){
				ajax_1110_1();//统计缴费单金额
			}
		</script>
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="缴费单查询">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="myform1" name="myform1" >
					<input type="hidden" name="feePayment.isPrint" value="1"/>
					<input type="hidden" id="fid" name="feePayment.id" value=""/>
				</form>
				<div id="message_confirm" style="display:none">
						<div>确认执行该操作！</div>
				</div>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">缴费单查询</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">
					<input type="hidden" name="feePayment.isPrint" id="isPrint" value="-1"/>
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
							<select name="student.glbtach" id="globalBatchId" class="txt_box_150">
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
						<td align="right">证件号：</td>
		                <td align="left">
		                	<input type="text" name="student.certNo" id="certNo" class="txt_box_150"/>					
						</td>
	             		<td align="right">缴费单号：</td>
		                <td align="left">
		                	<input type="text" name="feePayment.code" id="code" class="txt_box_150"/>					
						</td>
	             		<!-- td align="right">POS流水号：</td>
	             		<td><input type="text" name="feePayment.posSerialNo" id="pno" class="txt_box_150"/></td> -->
	             		
						
	             	</tr>
	             	<tr >
	             		<td align="right">缴费金额：</td>
		                <td align="left">
		                	<input type="text" name="feemoney" id="feemoney" class="txt_box_150"/>					
						</td>
						<td align="right">缴费单类别：</td>
		                <td align="left">
		                	<select name="feePayment.pamentType" id="pamentType" class="txt_box_150">
								<option value="0">--请选择--</option>
								<option value="<%=Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE %>">正式缴费单</option>
								<option value="<%=Constants.FEE_PAYMENT_TYPE_PRE_BILLING %>">预缴费单</option>						
								<option value="<%=Constants.FEE_PAYMENT_TYPE_REFUND_SINGLE %>">退费单</option>
							</select>					
						</td>
						<!-- td align="right">是否打印：</td>
		                <td align="left">
		                	<select name="feePayment.isPrint" id="isPrint" class="txt_box_150">
								<option value="-1">--请选择--</option>
								<option value="0">未打印</option>
								<option value="1">已打印</option>						
							</select>					
						</td> -->
						<td align="right">缴费方式：</td>
	             		<td>
	             			<!--select name="feePayment.feeWayId" id="paymentway" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select-->
							<s:select name="feePayment.feeWayId" id="feeway" cssClass="txt_box_150" headerKey="0" headerValue="--请选择--" list="feeWayList" listKey="id" listValue="name"></s:select>
						</td>
						
					</tr>
					<tr>	
	             		
	             		<td align="right">缴费单状态：</td>
	             		<td>
	             			<select name="feePayment.status" id="paymentStatus" class="txt_box_150">
								<option value="0">--请选择--</option>
								<option value="<%=Constants.PAYMENT_STATUS_ZUO_FEI %>">已作废</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_KAI_DAN %>">已开单</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN %>">已收费确认</option>						
								<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN %>">已填汇款单</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU %>">已汇款总部</option>				
								<option value="<%=Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN %>">总部确认</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN %>">已填打款单</option>								
								<option value="<%=Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN %>">已确认待汇款</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO %>">已打款院校</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN %>">已填返款单</option>
								<option value="<%=Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN %>">返款确认</option>								 
								<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN %>">已填招生返款</option>
								<option value="<%=Constants.PAYMENT_STATUS_SHANG_WU_YI_SHENG_HE %>">商务已审核</option> 
								<option value="<%=Constants.PAYMENT_STATUS_CAI_WU_YI_SHEN_HE %>">财务已审核</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO %>">已汇款渠道</option>  
							</select>
						</td>
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
	             		<td align="right">收据号：</td>
		                <td align="left">
							<input type="text" name="feePayment.barCode" id="barCode" class="txt_box_150"/>
						</td> 		
						<td colspan="2"></td>
		                <td align="right">
							<input type="button" class="btn_black_61"  onclick="showsearch();" value="查询"/>
						</td>
						<td align="left">
								<input class="btn_black_61" type="button" onclick="download();"  value="导出" />
								
								<input class="btn_black_130" type="button" onclick="downloadyujiaofeidan();"  value="导出预缴费单" />
						</td>
	             	</tr>
				</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="finance_payment"
										searchCountActionpath="finance/payment/count_payment_serach_page_ajax"
										searchListActionpath="finance/payment/list_payment_serach_page_ajax"
										columnsStr="createdTime;paymentcode;studentName;schoolName;academyenrollbatchName;levelName;majorName;feeWayId;feePayment;rechargeAmount;discountAmount;totalAmount;barCode;pamentType;status"
										customColumnValue="1,showcode(code,id,pamentType);7,feeWayIdValue(feeWayId);8,feePaymentValue(feePayment);9,feePaymentValue(rechargeAmount);10,feePaymentValue(discountAmount);11,feePaymentValue(totalAmount);13,pamentTypeValue(pamentType);14,statusValue(status)"
										pageSize="15"
										isPackage="false"
										params="'result.order':'createdTime','result.sort':'desc'"
										searchFormId="myform"
										customToolbarID="moneyall"
										listCallback="countCallback"
										columnsWidth="[6,120px]"
									/>
				
		</body:body>
		<!--  弹出层           -->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		
  </body>
</html>
