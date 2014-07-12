<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>院校返款单（总部）</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 表单验证 -->
	<jc:plugin name="validator" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	<jc:plugin name="calendar" />
	<jc:plugin name="page" />
	<script type="text/javascript">
	
		//查询数据
		function showsearch()
		{
			//alert(jQuery("#amount").val());
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
		//显示
		function showtitle(remitterName)
		{
			return remitterName;
		}
		//显示金额
		function feePaymentValue(feePayment)
		{
			return (feePayment+"").toMoney();
		}
		//状态
		function toStatus(status,types,id)
		{
			if(status==PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN)
			{
				return '已填返款单';
			} 
			else if(status==PAYMENT_STATUS_FAN_KUAN_QUE_REN && types==FALLBACK_TYPES_SUBMIT)
			{
				isPageOperating(id,"001","delete");
				return '返款确认';
			}
			
			return '';
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
		//操作
		function toOperation(id, status)
		{
			if(status==PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN)
			{
				return '<a href="javascript:void(0);" onclick="confirmPay('+id+')">返款确认</a>';
			}
			else
			{
				isPageOperating(id,"001","delete");
				return '';
			}
			
		}
		
		CONFIRM_DATA = null;
		function confirmPay(id){
			CONFIRM_DATA = {payAcademyCeduId: id};
			$('#message_confirm_rebate').dialog({
				title:'确认操作',
				buttons: {
					'确认': function() { 
							$(this).dialog("close"); 
							ajax_confirm_1();//确认返款单
						}, 
					'取消': function() { 
							$(this).dialog("close"); 
						} 
				}
			});
			$('#message_confirm_rebate').dialog("open");
		}
		function confirmCallback(data)
		{
			if(data.isback)
			{
				//search001();
				jQuery("#showDialog").html('<b>操作成功！</b>');
				jQuery('#message_returns_tips').dialog('open');			
				
			}
			else
			{
				jQuery("#showDialog").html('<b>操作失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			refresh001();
		}
		
		function toAddPage(){
			location.href = '<uu:url url="/finance/payacademycedu/add_pay_academy_cedu"/>';
		}
		
		jQuery(function(){
			//util.select.initOption('[name="branch"]', get_branch(), '', {text:'弘成总部',value:''});
			
			//初始化弹出框				
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
		
		//删除院校返款单
		function deleteFun(id)
		{
			$('#message_confirm').dialog({
				title:'确认操作',
				buttons: {
					'确认': function() { 
							$(this).dialog("close"); 
							payAcademyCeduId=id;
							ajax_110_1();//删除
						}, 
					'取消': function() { 
							$(this).dialog("close"); 
						} 
				}
			});
			$('#message_confirm').dialog("open"); 
		}
		
		//删除院校返款
		var payAcademyCeduId=0;
		function ajax_delpac(data)
		{
			if(data.isfail)
			{
				jQuery("#showDialog").html('<b>已产生追加金额，无法删除！</b>');
				jQuery('#message_returns_tips').dialog('open');	
			}
			else if(data.isback)
			{
				//search001();
				jQuery("#showDialog").html('<b>操作成功！</b>');
				jQuery('#message_returns_tips').dialog('open');			
				
			}
			else
			{
				jQuery("#showDialog").html('<b>操作失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			refresh001();
		}
		
		//统计招生返款单金额 
		function countallmoney(data)
		{
			//alert(data.allFeePaymentMoney);
			jQuery("#moneyall").html("合计：<font style='color:red'><b>"+data.allacademymoney+"</b></font>元");
		}
		
		//统计金额
		function countCallback001(data)
		{
			ajax_1110_1();//统计汇款单金额
		}
	</script>
	
	<a:ajax 
		successCallbackFunctions="confirmCallback" 
		parameters="CONFIRM_DATA" 
		pluginCode="confirm" 
		urls="/finance/payacademycedu/confirm_pay_academy_cedu"
	/>

	<!--删除院校返款-->
	<a:ajax 
		successCallbackFunctions="ajax_delpac" 
		parameters="{payAcademyCeduId:payAcademyCeduId}" 
		urls="/finance/academyrebatereview/del_academy_rebate_cedu_review_ajax" 
		pluginCode="110"
	/>
	
	<!-- 统计院校返款单金额 -->
	<a:ajax 
		parameters="jQuery('#searchForm').serializeObject()" 
		successCallbackFunctions="countallmoney" 
		pluginCode="1110" 
		urls="finance/payacademycedu/count_pay_academy_cedu_all_money_ajax"
	/>
</head>
<body>
	<head:head title="院校返款单（总部）">
		<html:a text="添加院校返款单" icon="add" href="/finance/academyrebatereview/add_academy_rebate_review" target="_blank"/>		
	</head:head>
	<body:body>
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">院校返款单（总部） &gt;&gt; 返款单查询</th>
				<th></th>
			</tr>
		</table>
		
	<form id="searchForm">
		<table class="add_table">
			<tr>
				<td class="lable_100">返款院校：</td>
				<td align="left">
					<s:select id="remitterId" name="payAcademyCedu.remitterId" headerKey="0" headerValue="--请选择--" list="academies" listKey="id" listValue="name" cssClass="txt_box_150"></s:select>
				</td>
				<td class="lable_100">返款单状态：</td>
				<td align="left">
					<select id="status" name="payAcademyCedu.status" class="txt_box_150">
						<option value="0">--请选择--</option>
						<option value="<s:property value="@net.cedu.common.Constants@PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN"/>">已填返款单</option>
						<option value="<s:property value="@net.cedu.common.Constants@PAYMENT_STATUS_FAN_KUAN_QUE_REN"/>">返款确认</option>
					</select>
				</td>
				<td class="lable_100">实返金额：</td>
		        <td align="left">
					<input type="text" name="amount" id="amount" class="txt_box_150" />
				</td>
			</tr>
			<tr>
				<td class="lable_100">返款日期起：</td>
				<td align="left">
					<input type="text" name="starttime" id="starttime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'endtime\',{d:0});}'})" readonly="readonly"/>
				</td>
				
				<td class="lable_100">返款日期止：</td>
				<td align="left">
					<input type="text" name="endtime" id="endtime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'starttime\',{d:0});}'})" readonly="readonly"/>
				</td>
				<td class="lable_100">回退状态：</td>
				<td>
					<select name="payAcademyCedu.types" class="txt_box_150">
						<option value="0">--请选择--</option>
						<option value="<s:property value="@net.cedu.common.Constants@FALLBACK_TYPES_SUBMIT"/>">已提交</option>
						<option value="<s:property value="@net.cedu.common.Constants@FALLBACK_TYPES_ROLLED_BACK"/>">已回退</option>
					</select>
				</td>
			</tr>
			<tr>	
				<td colspan="5"></td>				
				<td>
					<input type="hidden" name="payAcademyCedu.isYearCount" id="isYearCount" value="<%=Constants.ACADEMY_REBATE_DAN_YEAR_COUNT_ACADEMY %>"/>
					
					<input type="button" onclick="showsearch()" class="btn_black_61" value="查询"/>
				</td>
			</tr>
		</table>
	</form>

		<page:plugin 
			pluginCode="001"
			il8nName="finance"
			subStringLength="20"
			searchListActionpath="/cedu/finance/payacademycedu/list_pay_academy_cedu_ajax"
			searchCountActionpath="/cedu/finance/payacademycedu/count_pay_academy_cedu_ajax"
			columnsStr="#remitteracademy;#amount;adjustPaied;addPaied;#amountpaied;note;#remittancedate;#status;#types"
			customColumnValue="0,showtitle(remitterName);1,feePaymentValue(amount);2,feePaymentValue(adjustPaied);3,feePaymentValue(addPaied);4,feePaymentValue(amountPaied);6,showtitle(remittanceDate);7,toStatus(status,types,id);8,showtypes(types)"
			pageSize="20"			
			view="http,/finance/academyrebatereview/view_pay_academy_cedu_review,payAcademyCeduId,id,_blank"
			delete="json,deleteFun,id"
			params="'result.order':'id','result.sort':'desc'"
			searchFormId="searchForm"
			customToolbarID="moneyall"
			listCallback="countCallback"
		/>

	</body:body>
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
			
		</div>
	</div>
	<!--弹出层    确认操作-->
	<div id="message_confirm" style="display:none">
		<div align="center" >
			<b>确认删除院校返款单么？</b>
		</div>
	</div>
	<!--弹出层    确认操作-->
	<div id="message_confirm_rebate" style="display:none">
		<div align="center" >
			<b>确认收到院校返款么？</b>
		</div>
	</div>
</body>
</html>
