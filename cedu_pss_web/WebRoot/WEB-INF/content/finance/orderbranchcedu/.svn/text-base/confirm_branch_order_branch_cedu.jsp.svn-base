<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>汇款总部(中心) 中心确认</title>
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
	<!--  Ajax文件上传 -->
	<jc:plugin name="ajax_upload" />
	
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
		//显示时间
		function showdate(date)
		{
			if(date!=null && date.length>10)
			{
				return date.substring(0,10);
			}
			return date;
		}
		function buildOperation(id, status,types){
			var str = '';
			
			if(status==PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN && types==FALLBACK_TYPES_SUBMIT)
			{
				str+= '<a href="javascript:centerConfirm('+id+')">中心确认</a>&nbsp;&nbsp;<a href="javascript:fallBack('+id+')">回退</a>';
			}
			//str+= ' &nbsp;&nbsp;<a href="javascript:confirmOrder('+id+')">上传单据</a>';
			//上传单据     暂时屏蔽    需要修改
			return str;
		}
		//中心确认
		function centerConfirm(id)
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
	
		function confirmOrder(id){
			$('#confirmForm').find('[name=orderId]').val(id);
			$('#confirmDialog').dialog('open');
		}
		
		function validate(){
			var errMsg = [];
			
			var orderNo = $('#confirmForm').find('[name=orderNo]').val();
			if(!orderNo){
				errMsg.push('请填写汇款单号');
			} else if(/(^\s+.*)|(.*\s+$)/.test(orderNo)){
				errMsg.push('汇款单号两边不能有空格');
			}
	
			var remittanceDate = $('#confirmForm').find('[name=remittanceDate]').val();
			if(!remittanceDate || /(^\s+.*)|(.*\s+$)/.test(remittanceDate)){
				errMsg.push('请填写汇款日期');
			}
			
			if(errMsg.length > 0){
				var ul = $('<ul/>').addClass('validateErrorList');
				
				for(var i=0; i<errMsg.length; i++){
					ul.append('<li>'+errMsg[i]+'</li>');
				}
				
				$.alert({msg: ul});
				
				return false;
			}
			
			return true;
		}
		
		function doupload()
		{
			var data = {};
			
			data.orderNo = $('#confirmForm').find('[name=orderNo]').val();
			data.orderId = $('#confirmForm').find('[name=orderId]').val();
			data.remittanceDate = $('#confirmForm').find('[name=remittanceDate]').val();
			
			$.ajaxFileUpload
			(
				{
					url:'<uu:url url="/finance/orderbranchcedu/confirm_order_branch_cedu_by_branch"/>', 
					secureuri:false,
					fileElementId:'orderImage',
					dataType: 'json',
					data: data,
					success: function (data, status)
					{
						$.alert({msg: data.errMsg, ok:function(){ search001(); }});
						$('#confirmDialog').dialog('close');
					},
					error: function (data, status, e)
					{
						$.alert('访问服务器时发生错误！');
					}
				}
			)
		}	
		$(function(){
			$('#confirmDialog').dialog({
				autoOpen: false,
				modal: true,
				shadow: true,
				width: 330,
				height: 200,
				title: '汇款单确认',
				open: function(){
					$(this).parent().find('.ui-dialog-buttonset').css({cssFloat: 'none', textAlign:'center'});
				},
				beforeClose: function(){
					$('#confirmForm').find('[name=orderId]').val(0);
					$('#confirmForm').get(0).reset();
				},
				buttons:{
					'提交': function(ui){
						if(validate())
						{ 
							doupload();
						}	
					},
					'取消': function(ui){
						$(this).dialog('close');
					}
				}
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
	
		//ajax回调函数   
		var orderId=0;
		var orderStatus=PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU;
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
		//统计汇款单金额 
		function countallmoney(data)
		{
			//alert(data.allFeePaymentMoney);
			jQuery("#moneyall").html("合计：<font style='color:red'><b>"+data.allordermoney+"</b></font>元");
		}
		
		//统计金额（分页控件里的方法   最后两行控制）
		function countCallback001(data){
			ajax_1110_1();//统计汇款单金额
		}
	</script>
	<!--更新汇款单状态-->
	<a:ajax 
		pluginCode="100"
		parameters="{orderId:orderId,orderStatus:orderStatus}" 
		successCallbackFunctions="ajax_updatestatus"
		urls="finance/orderbranchcedu/update_order_branch_cedu_status_ajax"
	/>
		
	<!--更新汇款单回退状态-->
	<a:ajax 
		pluginCode="110"
		parameters="{orderId:orderId,orderTypes:orderTypes}" 
		successCallbackFunctions="ajax_updatetypes"
		urls="finance/orderbranchcedu/update_order_branch_cedu_types_ajax"
	/>
	<!-- 统计汇款单金额 -->
	<a:ajax 
		parameters="jQuery('#searchParam').serializeObject()" 
		successCallbackFunctions="countallmoney" 
		pluginCode="1110" 
		urls="finance/orderbranchcedu/count_order_branch_cedu_all_money_ajax"
	/>
</head>
<body>
	<head:head title="汇款总部确认">
	</head:head>
	<body:body>
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">汇款总部 &gt;&gt; 中心确认</th>
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
						<option selected="selected" value="<s:property value="@net.cedu.common.Constants@PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN"/>">已填汇款单</option>
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
						<option selected="selected" value="<s:property value="@net.cedu.common.Constants@FALLBACK_TYPES_SUBMIT"/>">已提交</option>
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


		<page:plugin 
			pluginCode="001"
			il8nName="finance_payment"
			subStringLength="10"
			searchListActionpath="/finance/orderbranchcedu/list_order_branch_cedu_ajax"
			searchCountActionpath="/finance/orderbranchcedu/count_order_branch_cedu_ajax"
			columnsStr="remitterName;remitteeName;remitterAccount;remitteeAccount;amount;note;orderNo;remittanceDate;#status;#types;#operation"
			customColumnValue="7,showdate(remittanceDate);8,getStatus(status);9,showtypes(types);10,buildOperation(id,status,types)"
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
	
	<div id="confirmDialog" style="display:none;">
	<form id="confirmForm">
	
		<table class="gv_table_2">
			<tr>
				<td class="lable_100"><span style="color:red">*</span>汇款单号：</td>
				<td><input type="text" name="orderNo"/></td>
			</tr>
			<tr>
				<td class="lable_100"><span style="color:red">*</span>汇款日期：</td>
				<td><input type="text" name="remittanceDate" onfocus="WdatePicker({skin:'whyGreen'})" class="Wdate"/></td>
			</tr>
			<tr>
				<td class="lable_100">票据：</td>
				<td><input type="file" name="orderImage" id="orderImage"/></td>
			</tr>
		</table>
		
		<input type="hidden" name="orderId"/>
		
	</form>
	</div>
	<!--弹出层-->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="message_confirm" style="display:none">
			<div align="center" id="showconfirm">
				<b>确认汇款么？</b>
			</div>
		</div>	
		<div id="message_fall_back" style="display:none">
			<div align="center" id="showfallback">
				<b>确认汇款单回退么？</b>
			</div>
		</div>
</body>
</html>
