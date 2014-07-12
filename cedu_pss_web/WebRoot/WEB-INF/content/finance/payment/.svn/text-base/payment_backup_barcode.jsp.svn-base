<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<%@ include file="../../template/common/download_excel.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>收据号还原</title>
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
			//ajax回调函数     释放收据号
			var fpId=0;
			function ajax_barcode(data)
			{
				if(data.isback)
			    {
				    jQuery("#showDialog").html('<b>还原收据号成功！</b>');
					jQuery('#message_returns_tips').dialog("open"); 	
					
			    }
			    else
			    {
			    	jQuery("#showDialog").html('<b>还原收据号失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }				    
			    refresh001();
			}				
			
		</script>
		<a:ajax 
			parameters="null" 
			successCallbackFunctions="listbranch" 
			pluginCode="0001" 
			urls="crm/all_branch_list"
		/>
		<!--释放收据号-->
		<a:ajax 
			pluginCode="620"
			successCallbackFunctions="ajax_barcode" 
			parameters="{fpId:fpId}" 
			urls="/finance/payment/update_payment_bar_code_ajax" 
		/>
		
		<script type="text/javascript">
		
			//缴费单收据号还原
			function backUpBarCode(id)
			{
				$('#message_confirm').dialog({
					title:'确认操作',
					buttons: {
						'确认': function() { 
							//alert(id);
							fpId=id;
							ajax_620_1();
							$(this).dialog("close"); 
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				$('#message_confirm').dialog("open");
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
			//操作
			function operatingValue(id,barCode)
			{
				var str="";
				if(barCode!=null && barCode!="")
				{
					str="<a href='javascript:backUpBarCode("+id+")'>还原收据号</a>&nbsp;&nbsp;";
				}				
				return str;
			}
			$(document).ready(function(){
				ajax_0001_1();
				
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
			
		</script>
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="收据号还原">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
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
	             		
	             	</tr>
	             	<tr >
	             		<td align="right">缴费金额：</td>
		                <td align="left">
		                	<input type="text" name="feemoney" id="feemoney" class="txt_box_150"/>					
						</td>
						
						<td align="right">缴费方式：</td>
	             		<td>
							<s:select name="feePayment.feeWayId" id="feeway" cssClass="txt_box_150" headerKey="0" headerValue="--请选择--" list="feeWayList" listKey="id" listValue="name"></s:select>
						</td>
						<td align="right">收据号：</td>
	             		<td>
	             			<input type="text" name="feePayment.barCode" id="barCode" class="txt_box_150"/>	
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
						<td align="right"></td>
						<td align="left">
							<input type="hidden" name="feePayment.status" id="status" value="<%=Constants.PAYMENT_STATUS_ZUO_FEI %>" />
							<input type="button" class="btn_black_61"  onclick="showsearch();" value="查询"/>
						</td>
	             	</tr>
				</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="finance_payment"
										searchCountActionpath="finance/payment/count_payment_serach_page_ajax"
										searchListActionpath="finance/payment/list_payment_serach_page_ajax"
										columnsStr="createdTime;paymentcode;studentName;schoolName;academyenrollbatchName;levelName;majorName;feeWayId;feePayment;rechargeAmount;discountAmount;totalAmount;pamentType;barCode;status;#public.operating"
										customColumnValue="1,showcode(code,id,pamentType);7,feeWayIdValue(feeWayId);8,feePaymentValue(feePayment);9,feePaymentValue(rechargeAmount);10,feePaymentValue(discountAmount);11,feePaymentValue(totalAmount);12,pamentTypeValue(pamentType);14,statusValue(status);15,operatingValue(id,barCode)"
										pageSize="15"
										isPackage="false"
										params="'result.order':'createdTime','result.sort':'desc'"
										searchFormId="myform"
										columnsWidth="[3,80px][6,120px]"
									/>
				
		</body:body>
		<!--  弹出层           -->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="message_confirm" style="display:none">
			<div align="center"><b>确认还原缴费单的收据号为未作废的状态么？</b></div>
		</div>
  </body>
</html>
