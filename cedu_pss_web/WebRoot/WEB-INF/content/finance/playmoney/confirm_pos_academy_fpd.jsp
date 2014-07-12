<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>直汇院校确认</title>
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
		function buildOp(id, status){
			var str = '';
			
			if(status==PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN)
			{
				str+= '<a href="javascript:confirmOrder('+id+')">总部确认</a>';
			}
			else if(status==PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO)
			{
				str+= '<a href="javascript:fallbackOrder('+id+')">回退</a>';
				isPageOperating(id,"001","checkbox");
			}
			else
			{
				isPageOperating(id,"001","checkbox");
			}
			return str;
		}
		
		function getStatus(status){
			return status.getPaymentStatus();
		}
		//显示缴费单状态
		function showstatus(status)
		{
			if(status==PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN)
			{
				return "已汇款待确认";
			}
			return status.getPaymentStatus();
		}
		//显示金额
		function feePaymentValue(feePayment)
		{
			return (feePayment+"").toMoney();
		}
		
		//总部确认
		function confirmOrder(id)
		{
			$('#message_confirm').dialog({
				title:'确认操作',
				buttons: {
					'确认': function() { 
							fpdId=id;
							ajax_100_1();//删除
							$(this).dialog("close"); 
						}, 
					'取消': function() { 
							$(this).dialog("close"); 
						} 
				}
			});
			$('#message_confirm').dialog("open");
		}
		
		//判断批量审批
		function batchApproval()
		{
			if(getCheckedValues001()==null || getCheckedValues001()=="")
			{
				jQuery("#showDialog").html('<b>请选择要批量确认的缴费单！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				$('#message_confirm_batch').dialog({
					title:'确认操作',
					buttons: {
						'确认': function() { 
								ajax_120_1();
								$(this).dialog("close");				
							}, 
						'取消': function() { 
								$(this).dialog("close"); 
							} 
						}
				});
				$('#message_confirm_batch').dialog("open"); 
			}
		}
		//回退
		function fallbackOrder(id)
		{			
			$('#message_fallback').dialog({
				title:'确认操作',
				buttons: {
					'确认': function() { 
							feePaymentDetailId=id;
							ajax_130_1();	
							$(this).dialog("close");				
						}, 
					'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
			});
			$('#message_fallback').dialog("open");
		}
	
		$(function(){
			//$('#table001 > tbody').html('<tr><td colspan="11" align="center">请单击[查询]！</td></tr>');
			//院校改变事件
			jQuery('#academyId').change(function(){
				ajax_110_1();
			});	
			
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
		
		//ajax回调函数  总部确认
		var fpdId=0;//缴费单明细id
		function confirmCallback(data)
		{
			if(data.isback)
			{
				//search001();
				jQuery("#showDialog").html('<b>操作成功！</b>');
				$('#message_returns_tips').dialog("open");
			}
			else
			{
				jQuery("#showDialog").html('<b>操作失败！</b>');
				$('#message_returns_tips').dialog("open");
			}
			refresh001();
		}
		//ajax回调函数   级联批次
		function ajax_jilianpici(data)
		{
			$('#batchId').empty();
			$('#batchId').append('<option value="0">--请选择--</option>');	
			if(data.batchlist!=null&&data.batchlist.length>0)
			{
				$.each(data.batchlist,function(){	
				    $('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				});
			}
		}
		//ajax回调函数   批量确认
		function ajax_batchconfirm(data)
		{	
			if(data.isback)
			{
				//search001();
				jQuery("#showDialog").html('<b>批量确认成功！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				jQuery("#showDialog").html('<b>批量确认失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			refresh001();
		}
		//ajax回调函数  回退总部确认过的单据
		var feePaymentDetailId=0;
		function ajax_fallback(data)
		{	
			if(data.isback)
			{
				//search001();
				jQuery("#showDialog").html('<b>回退成功！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				jQuery("#showDialog").html('<b>回退失败！</b><br/>(该缴费单已经进行退费操作)');
				jQuery('#message_returns_tips').dialog('open');
			}
			refresh001();
		}
		//统计缴费单金额 
		function countallmoney(data)
		{
			jQuery("#moneyall").html("合计：<font style='color:red'><b>"+data.allMoney+"</b></font>元(打款金额)");
		}
		
		//统计金额
		function countCallback001(data){
			ajax_1110_1();//统计汇款单金额
		}
	</script>
	<!-- 总部确认 -->
	<a:ajax successCallbackFunctions="confirmCallback" 
		parameters="{fpdId:fpdId}" 
		pluginCode="100" 
		urls="/finance/playmoney/confirm_pos_eback_fpd_for_academy_ajax"
	/>
	<!-- 级联批次 -->
	<a:ajax 
		successCallbackFunctions="ajax_jilianpici" 
		parameters="{academyId:jQuery('#academyId').val()}" 
		pluginCode="110" 
		urls="/enrollment/cascade_academy_batch_all_ajax"
	/>
	<!-- 批量确认 -->
	<a:ajax 
		parameters="{fpdIds:getCheckedValues001()}" 
		successCallbackFunctions="ajax_batchconfirm" 
		pluginCode="120" 
		urls="finance/playmoney/batch_confirm_pos_eback_fpd_for_academy_ajax"
	/>
	<!-- 回退 -->
	<a:ajax 
		parameters="{fpdId:feePaymentDetailId}" 
		successCallbackFunctions="ajax_fallback" 
		pluginCode="130" 
		urls="finance/playmoney/fallback_pos_eback_fpd_for_academy_ajax"
	/>
	<!-- 统计缴费单金额 -->
	<a:ajax 
		parameters="jQuery('#searchParam').serializeObject()" 
		successCallbackFunctions="countallmoney" 
		pluginCode="1110" 
		urls="finance/playmoney/count_pos_academy_fpd_all_money_ajax"
	/>
	
</head>
<body>
	<head:head title="直汇院校确认">
		<html:a text="批量确认" icon="update" onclick="batchApproval()"/>
		
	</head:head>
	<body:body>
	<%@include file="_cedu_confirm_tab.jsp" %>
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">POS直汇院校  总部确认</th>
				<th></th>
			</tr>
		</table>
			
	<form id="searchParam">
		<table class="add_table">
			<tr>			
				<td class="lable_100">收款单位：</td>
				<td><s:property value="cedu.name"/></td>
				<td class="lable_100">学习中心：</td>
				<td><s:select list="branches" headerKey="0" headerValue="--请选择--" listKey="id" listValue="name" name="student.branchId" cssClass="txt_box_150"></s:select></td>
				<td class="lable_100">院校：</td>
				<td>
					<s:if test="%{academyList!=null}">
						<s:select list="%{academyList}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="student.academyId" id="academyId" cssClass="txt_box_150"/>
					</s:if>
			        <s:else>
			            <select name="student.academyId" id="academyId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>
			        </s:else>	
				</td>
				
			</tr>
			<tr>
				<td class="lable_100">批次：</td>
				<td>
					<select name="student.enrollmentBatchId" id="batchId" class="txt_box_150">
						<option value="0">--请选择--</option>
					</select>
				</td>
				<td class="lable_100">层次：</td>
				<td>
					<s:if test="%{levelList!=null}">
						<s:select list="%{levelList}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="student.levelId" id="levelId" cssClass="txt_box_150"/>
					</s:if>
			        <s:else>
			            <select name="levelId" id="levelId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>
			        </s:else>	
				</td>
				<td class="lable_100">姓名：</td>
				<td>
					<input  name="student.name" class="txt_box_150" type="text" value="" />
				</td>
				
			</tr>
			<tr>
				<td class="lable_100">证件号：</td>
				<td>
					<input  name="student.certNo" class="txt_box_150" type="text" />
				</td>
				<td class="lable_100">缴费科目：</td>
				<td>
					<s:if test="%{feesubjectlist!=null}">
		                <s:select list="%{feesubjectlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="feePaymentDetail.feeSubjectId" id="feeSubjectId" cssClass="txt_box_150"/>
		            </s:if>
		            <s:else>
		                <select name="feePaymentDetail.feeSubjectId" id="feeSubjectId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>
		            </s:else>		
				</td>
				<td class="lable_100">缴费单状态：</td>
				<td >
					<select name="feePaymentDetail.status" class="txt_box_150">
						<option value="0">--请选择--</option>
						<option selected="selected" value="<%=Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN %>">已汇款待确认</option>
						<option value="<%=Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO %>">已打款院校</option>
						<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN %>">已填返款单</option>
						<option value="<%=Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN %>">返款确认</option>								 
					</select>
				</td>
			</tr>
			<tr>
				<td class="lable_100">缴费单号：</td>
				<td>
					<input type="text" name="code" id="code" class="txt_box_150" />
				</td>
				<td class="lable_100">缴费时间起：</td>
				<td><input type="text" name="startDate" id="startDate" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'endDate\',{d:0});}'})" readonly="readonly"/></td>
				<td class="lable_100">缴费时间止：</td>
				<td><input type="text" name="endDate" id="endDate" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'startDate\',{d:0});}'})" readonly="readonly"/></td>
			</tr>
			<tr>	
				<td colspan="5"></td>
				<td>
					<input type="hidden" name="feeWayId" id="feeWayId" value="<%=Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO %>"/>
					<input type="button" class="btn_black_61" value="查询" onclick="search001()"/>
				</td>
			</tr>
		</table>
	</form>


		<page:plugin 
			pluginCode="001"
			il8nName="finance_payment"
			subStringLength="50"
			searchListActionpath="list_pos_academy_fpd_page_ajax"
			searchCountActionpath="count_pos_academy_fpd_page_ajax"
			columnsStr="createdTime;paymentCode;branchName;schoolName;academyenrollbatchName;levelName;majorName;paymentSubjectName;studentName;jiaofeiValue;discountAmount;payCeduAcademy;#status;#operation"
			customColumnValue="9,feePaymentValue(jiaofeiValue);10,feePaymentValue(discountAmount);11,feePaymentValue(payCeduAcademy);12,showstatus(status);13,buildOp(id,status)"
			isPage="true"
			isChecked="true"
			checkboxValue="id"
			params="'result.order':'createdTime','result.sort':'desc'"
			searchFormId="searchParam"
			isonLoad="true"
			isOrder="false"
			columnsWidth="[6,120px]"
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
			<div align="center"><b>确认收到汇款么？</b></div>
		</div>
		<div id="message_confirm_batch" style="display:none">
			<div align="center" id="showconfirm">
				<b>确认批量选择的缴费单汇款无误么？</b>
			</div>
		</div>	
		<div id="message_fallback" style="display:none">
		<div align="center" id="showconfirm">
			<b>确认回退吗？</b><br/>(回退后，必须重新确认)
		</div>
	</div>	
</body>
</html>
