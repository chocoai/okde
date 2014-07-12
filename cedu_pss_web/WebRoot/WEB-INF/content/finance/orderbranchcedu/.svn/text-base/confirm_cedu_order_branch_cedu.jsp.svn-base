<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>学习中心汇款确认</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!--  分页 -->
	<jc:plugin name="page" />
	<!-- 时间控件 -->
	<jc:plugin name="calendar" />
	<!-- 选项卡 -->
	<jc:plugin name="tab" />
	<!-- 表单验证 -->
	<jc:plugin name="validator" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	
	<script type="text/javascript">
		//查询数据
		function showsearch()
		{
			if(jQuery("#amount").val()!="" && dealwithmoney(jQuery("#amount").val())==-1)
			{
				jQuery("#showDialog").html('<b>查询条件“汇款金额”输入格式不正确，只能输入整数和小数！</b>');
				$('#message_returns_tips').dialog("open");
			}
			else
			{
				if(jQuery("#amount").val()!="")
				{
					jQuery("#amount").val(dealwithmoney(jQuery("#amount").val()));
				}
				search001();
			}
		}
		//显示金额
		function feePaymentValue(feePayment)
		{
			return (feePayment+"").toMoney();
		}
		function buildOperation(id, status,types)
		{
			var str = '';		
			if(status==PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU && types==FALLBACK_TYPES_SUBMIT)
			{
				//str+= '<a href="javascript:ceduConfirm('+id+')">总部确认</a>';
				str+= '<a href="javascript:ceduConfirm('+id+')">总部确认</a>&nbsp;&nbsp;<a href="javascript:fallBack('+id+')">回退</a>';
			}
			if(status==PAYMENT_STATUS_ZONG_BU_QUE_REN)
			{
				str+= '<a href="javascript:revertOrder('+id+')">回退</a>';
			}
			return str;
		}
		//总部确认
		function ceduConfirm(id)
		{
			$('#message_confirm').dialog({
				title:'确认操作',
				buttons: {
					'确认': function() { 
							orderId=id;
							ajax_100_1();//更新汇款单状态	
							$(this).dialog("close");				
						}, 
					'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
			});
			$('#message_confirm').dialog("open"); 
		
		}
		//回退
		function fallBack(id)
		{
			$('#message_fall_back').dialog({
				title:'回退操作',
				buttons: {
					'确认': function() { 
							orderId=id;
							ajax_110_1();//更新汇款单回退状态	
							$(this).dialog("close");				
						}, 
					'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
			});
			$('#message_fall_back').dialog("open");
		}
		//回退总部确认过的汇款单
		function revertOrder(id)
		{
			$('#message_revert').dialog({
				title:'回退操作',
				buttons: {
					'确认': function() { 
							orderCeduConfirmId=id;
							ajax_120_1();//回退
							$(this).dialog("close");				
						}, 
					'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
			});
			$('#message_revert').dialog("open");
		}
		
		function showdate(date)
		{
			if(date!=null && date.length>10)
			{
				return date.substring(0,10);
			}
			return date;
		}
		function getStatus(status){
			var str = '';
			
			switch(status){
			case PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN:
				str = '已填汇款单';
				break;
			case PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU:
				str = '已汇款总部';
				break;
			case PAYMENT_STATUS_ZONG_BU_QUE_REN:
				str = '总部确认';
				break;
			}
			
			return str;
		}
		//显示回退状态
		function showtypes(types)
		{
			if(types==FALLBACK_TYPES_SUBMIT)
			{
				return '已提交';
			}
			else if(types==FALLBACK_TYPES_ROLLED_BACK)
			{
				return '<span style="color:red">已回退</span>';
			}
			return '--';
			
		}
		
		CONFIRM_DATA = null;
		function confirmOrder(id){
			CONFIRM_DATA = {orderId: id};
			$.confirm({
				msg: '您确认已收到汇款吗？',
				confirm: function(){
					ajax_confirm_1();
				}
			});
		}
		function confirmCallback(data){
			$.alert(data.errMsg);
			//search001();
			refresh001();
		}
		
		$(function(){
			$('#table001 > tbody').html('<tr><td colspan="12" align="center">请单击[查询]！</td></tr>');
			
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
		//ajax回调函数   
		var orderId=0;
		var orderStatus=PAYMENT_STATUS_ZONG_BU_QUE_REN;
		function ajax_updatestatus(data)
		{
			//search001();
			refresh001();
			jQuery("#showDialog").html('<b>操作成功！</b>');
			jQuery('#message_returns_tips').dialog("open");		
		}
		
		//ajax 回调函数     回退状态
		var orderTypes=FALLBACK_TYPES_ROLLED_BACK;
		function ajax_updatetypes(data)
		{
			if(data.isback)
			{
				//search001();
				jQuery("#showDialog").html('<b>操作成功！</b>');
				jQuery('#message_returns_tips').dialog("open");	
			}	
			else
			{
				jQuery("#showDialog").html('<b>操作失败！</b>');
				jQuery('#message_returns_tips').dialog("open");
			}
			refresh001();
		}
		
		//ajax 回调函数     回退总部确认过的汇款单
		var orderCeduConfirmId=0;
		function ajax_reverttypes(data)
		{
			if(data.isback)
			{
				//search001();
				jQuery("#showDialog").html('<b>操作成功！</b>');
				jQuery('#message_returns_tips').dialog("open");	
			}	
			else if(data.count==1)
			{
				jQuery("#showDialog").html('<b>汇款单中的缴费单明细已经进行打款操作，不能回退！</b>');
				jQuery('#message_returns_tips').dialog("open");	
			}
			else if(data.count==2)
			{
				jQuery("#showDialog").html('<b>汇款单中的缴费单明细已经进行退费操作，不能回退！</b>');
				jQuery('#message_returns_tips').dialog("open");	
			}
			else if(data.count==3)
			{
				jQuery("#showDialog").html('<b>汇款单中的缴费单明细已经进行招生返款操作，不能回退！</b>');
				jQuery('#message_returns_tips').dialog("open");	
			}
			else
			{
				jQuery("#showDialog").html('<b>操作失败！</b>');
				jQuery('#message_returns_tips').dialog("open");
			}
			refresh001();
		}
		
		//统计汇款单金额 
		function countallmoney(data)
		{
			//alert(data.allFeePaymentMoney);
			jQuery("#moneyall").html("合计：<font style='color:red'><b>"+data.allordermoney+"</b></font>元");
		}
		
		//统计金额
		function countCallback001(data){
			ajax_1110_1();//统计汇款单金额
		}
	</script>
	<!--更新汇款单状态-->
	<a:ajax 
		pluginCode="100"
		parameters="{orderId:orderId,orderStatus:orderStatus}" 
		successCallbackFunctions="ajax_updatestatus"
		urls="finance/orderbranchcedu/update_order_branch_cedu_status_for_cedu_ajax"
	/>
	<!--更新汇款单回退状态-->
	<a:ajax 
		pluginCode="110"
		parameters="{orderId:orderId,orderTypes:orderTypes}" 
		successCallbackFunctions="ajax_updatetypes"
		urls="finance/orderbranchcedu/update_order_branch_cedu_types_ajax"
	/>
	
	<!--回退总部确认过的汇款单-->
	<a:ajax 
		pluginCode="120"
		parameters="{orderId:orderCeduConfirmId}" 
		successCallbackFunctions="ajax_reverttypes"
		urls="finance/orderbranchcedu/fallback_order_branch_cedu_status_for_cedu_confirm_ajax"
	/>
	
	<a:ajax successCallbackFunctions="confirmCallback" parameters="CONFIRM_DATA" pluginCode="confirm" urls="/finance/orderbranchcedu/confirm_order_branch_cedu_by_cedu"/>
	
	<!-- 统计汇款单金额 -->
	<a:ajax 
		parameters="jQuery('#searchParam').serializeObject()" 
		successCallbackFunctions="countallmoney" 
		pluginCode="1110" 
		urls="finance/orderbranchcedu/count_order_branch_cedu_all_money_ajax"
	/>
</head>
<body>
	<head:head title="学习中心汇款确认">
		
	</head:head>
	<body:body>
	<%@include file="_cedu_confirm_tab.jsp"%>
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">现金汇总部 &gt;&gt; 总部确认</th>
				<th></th>
			</tr>
		</table>
			
	<form id="searchParam">
		<table class="add_table">
			<tr>
				<td class="lable_100">汇款单位：</td>
				<td><s:select headerKey="-1" headerValue="--请选择--" list="branches" listKey="id" listValue="name" name="order.remitterId" cssClass="txt_box_150"></s:select></td>
				<td class="lable_100">收款单位：</td>
				<td><s:property value="cedu.name"/></td>
				<td class="lable_100">汇款账号：</td>
				<td><input type="text" class="txt_box_150" name="order.remitterAccount" /></td>
				
			</tr>
			<tr>			
				<td class="lable_100">收款账号：</td>
				<td><input type="text" class="txt_box_150" name="order.remitteeAccount" /></td>
				<td class="lable_100">汇款日期起：</td>
				<td><input type="text" name="startDate" id="startDate" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" readonly="readonly"/></td>
				<td class="lable_100">汇款日期止：</td>
				<td><input type="text" name="endDate" id="endDate" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="lable_100">费用金额：</td>
				<td><input type="text" name="amount" id="amount" class="txt_box_150"/></td>
				<td class="lable_100">汇款单号：</td>
				<td><input type="text" name="order.orderNo" class="txt_box_150"/></td>
				<td class="lable_100">汇款单状态：</td>
				<td>
					<select name="order.status" class="txt_box_150">
						<option value="-1">--请选择--</option>
						<option value="<s:property value="@net.cedu.common.Constants@PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN"/>">已填汇款单</option>
						<option selected="selected" value="<s:property value="@net.cedu.common.Constants@PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU"/>">已汇款总部</option>
						<option value="<s:property value="@net.cedu.common.Constants@PAYMENT_STATUS_ZONG_BU_QUE_REN"/>">总部确认</option>
					</select>
				</td>
			</tr>
			<tr>	
				<td class="lable_100">回退状态：</td>
				<td>
					<select name="order.types" class="txt_box_150">
						<option value="0">--请选择--</option>
						<option selected="selected" value="<s:property value="@net.cedu.common.Constants@FALLBACK_TYPES_SUBMIT"/>">已提交</option>
						<option value="<s:property value="@net.cedu.common.Constants@FALLBACK_TYPES_ROLLED_BACK"/>">已回退</option>
					</select>
				</td>
				<td colspan="3"></td>
				<td><input type="button" class="btn_black_61" value="查询" onclick="showsearch()"/></td>
			</tr>
		</table>

		<input type="hidden" name="order.remitteeId" value="<s:property value="cedu.id"/>"/>
	</form>


		<page:plugin 
			pluginCode="001"
			il8nName="finance_payment"
			subStringLength="10"
			searchListActionpath="/finance/orderbranchcedu/list_order_branch_cedu_ajax"
			searchCountActionpath="/finance/orderbranchcedu/count_order_branch_cedu_ajax"
			columnsStr="remitterName;remitteeName;remitterAccount;remitteeAccount;amount;note;orderNo;remittanceDate;#status;#types;#operation"
			customColumnValue="4,feePaymentValue(amount);7,showdate(remittanceDate);8,getStatus(status);9,showtypes(types);10,buildOperation(id,status,types)"
			view="http,/finance/orderbranchcedu/view_order_branch_cedu,orderId,id,_blank"
			isPage="true"
			searchFormId="searchParam"
			params="'result.order':'remittanceDate','result.sort':'desc'"
			isonLoad="true"
			isPackage="false"
			isOrder="false"
			customToolbarID="moneyall"
			listCallback="countCallback"
		/>

	</body:body>
	<!--弹出层-->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="message_confirm" style="display:none">
			<div align="center" id="showconfirm">
				<b>确认汇款单已汇款么？</b>
			</div>
		</div>	
		<div id="message_fall_back" style="display:none">
			<div align="center" id="showfallback">
				<b>确认汇款单回退么？</b>
			</div>
		</div>
		<div id="message_revert" style="display:none">
			<div align="center" id="showrevert">
				<b>该汇款单总部已确认过<br/>现在确认回退么？</b>
			</div>
		</div>
</body>
</html>
