<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>汇款总部(中心)</title>
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
				
		});
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
		
		function buildOperation(id, status,types){
			var str = '';
			
			str += '<a target="_blank" href="<uu:url url="/finance/orderbranchcedu/view_order_branch_cedu"/>?orderId='+id+'"><img src="<ui:img url="/images/icon_view.gif" />" alt="详情"/></a>';
			
			if(status==PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN || types==FALLBACK_TYPES_ROLLED_BACK)
			{
				str += '&nbsp;';
				//str+= '<a href="<uu:url url="/finance/orderbranchcedu/edit_order_branch_cedu"/>?orderId='+id+'"><img src="<ui:img url="/images/icon_edit.gif" />" alt="修改"/></a>';
				str += '&nbsp;';
				str+= '<a href="javascript:deleteOrder('+id+');"><img src="<ui:img url="/images/icon_del.gif" />" alt="删除"/></a>';
			}
			
			//str += '&nbsp;';
			//str += '<a href="<uu:url url="/finance/orderbranchcedu/export_order_branch_cedu"/>?orderId='+id+'"><img src="<ui:img url="/images/icon_export.gif" />" alt="导出"/></a>';
			
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
		
		DELETE_PARAM = null;
		
		function deleteOrder(id){
			DELETE_PARAM = { orderId: id };
			
			$('#message_confirm').dialog({
					title:'确认操作',
					buttons: {
						'确认': function() { 
								ajax_delete_1();	
								$(this).dialog("close");				
							}, 
						'取消': function() { 
								$(this).dialog("close"); 
							} 
						}
				});
			$('#message_confirm').dialog("open"); 
			//$.confirm({msg: '您确认要删除吗？', confirm: function(){
			//	ajax_delete_1();
			//}});
		}
		
		function deleteCallback(data)
		{
			//$.alert({msg:'删除成功！', close:function(){ search001(); }});
			search001();
			jQuery("#showDialog").html('<b>删除成功！</b>');
			$('#message_returns_tips').dialog("open");
		}
		function showdate(date)
		{
			if(date!=null && date.length>10)
			{
				return date.substring(0,10);
			}
			return date;
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
	<a:ajax successCallbackFunctions="deleteCallback" parameters="DELETE_PARAM" pluginCode="delete" urls="/finance/orderbranchcedu/delete_order_branch_cedu"/>
	<!-- 统计汇款单金额 -->
	<a:ajax 
		parameters="jQuery('#searchParam').serializeObject()" 
		successCallbackFunctions="countallmoney" 
		pluginCode="1110" 
		urls="finance/orderbranchcedu/count_order_branch_cedu_all_money_ajax"
	/>
</head>
<body>
	<head:head title="汇款总部">
		<html:a text="新增汇款单" icon="add" href="/finance/orderbranchcedu/add_order_branch_cedu1" target="_blank"/>
	</head:head>
	<body:body>
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">汇款总部 &gt;&gt; 汇款单查询</th>
				<th></th>
			</tr>
		</table>
			
	<form id="searchParam">
		<table class="add_table">
			<tr>
				<td class="lable_100">汇款单位：</td>
				<td><s:property value="branch.name"/></td>
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
				<td class="lable_100">汇款金额：</td>
				<td><input type="text" name="amount" id="amount" class="txt_box_150"/></td>
				<td class="lable_100">汇款单号：</td>
				<td><input type="text" name="order.orderNo" class="txt_box_150"/></td>
				<td class="lable_100">汇款单状态：</td>
				<td>
					<select name="order.status" class="txt_box_150">
						<option value="-1">全部状态</option>
						<option value="<s:property value="@net.cedu.common.Constants@PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN"/>">已填汇款单</option>
						<option value="<s:property value="@net.cedu.common.Constants@PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU"/>">已汇款总部</option>
						<option value="<s:property value="@net.cedu.common.Constants@PAYMENT_STATUS_ZONG_BU_QUE_REN"/>">总部确认</option>
					</select>
				</td>
			</tr>
			<tr>	
				<td class="lable_100">回退状态：</td>
				<td>
					<select name="order.types" class="txt_box_150">
						<option value="0">--请选择--</option>
						<option value="<s:property value="@net.cedu.common.Constants@FALLBACK_TYPES_SUBMIT"/>">已提交</option>
						<option value="<s:property value="@net.cedu.common.Constants@FALLBACK_TYPES_ROLLED_BACK"/>">已回退</option>
					</select>
				</td>
				<td colspan="3"></td>
				<td><input type="button" class="btn_black_61" value="查询" onclick="showsearch()"/></td>
			</tr>
		</table>
		
		<input type="hidden" name="order.remitterId" value="<s:property value="branch.id"/>"/>
		<input type="hidden" name="order.remitteeId" value="<s:property value="cedu.id"/>"/>
	</form>

		<!--  table class="add_table">
			<tr>
				<td align="right">
					<input type="button" class="btn_black_61" value="查询" onclick="search001()"/>&nbsp;&nbsp;
					<!-- input type="button" onclick="window.open('<uu:url url="/finance/orderbranchcedu/add_order_branch_cedu"/>?branchId=<s:property value="branch.id"/>','_blank')" class="btn_black_130" value="添加汇款单" style="text-align:center"/>
					<input type="button" onclick="window.open('<uu:url url="/finance/orderbranchcedu/add_order_branch_cedu1"/>','_blank')" class="btn_black_130" value="添加汇款单" style="text-align:center"/>
				</td>
			</tr>
		</table>-->

		<page:plugin 
			pluginCode="001"
			il8nName="finance_payment"
			subStringLength="10"
			searchListActionpath="/finance/orderbranchcedu/list_order_branch_cedu_ajax"
			searchCountActionpath="/finance/orderbranchcedu/count_order_branch_cedu_ajax"
			columnsStr="#operation;remitterName;remitteeName;remitterAccount;remitteeAccount;amount;note;orderNo;remittanceDate;#status;#types"
			customColumnValue="0,buildOperation(id,status,types);8,showdate(remittanceDate);9,getStatus(status);10,showtypes(types)"
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
	<!--  弹出层           -->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
	<div id="message_confirm" style="display:none">
		<div align="center" id="showconfirm">
			<b>确认删除汇款单么？</b>
		</div>
	</div>	
</body>
</html>
