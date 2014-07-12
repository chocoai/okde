<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
	<!-- 分页插件 -->
	<jc:plugin name="page" />
	<!-- 时间控件 -->
	<jc:plugin name="calendar" />
	<script type="text/javascript">
		
		CHANNEL_PARMA = null;
		function channelCallback(data){
			//$('#channelId').append('<option value="-1">---请选择---</option>');
			$(data.list).each(function(){
				$('#channelId').append('<option value="'+this.id+'">'+this.name+'</option>');
			});
		}
	
		function listChannel(channelType){
			CHANNEL_PARAM = {channelType: channelType,branchId:jQuery("#branchId").val()};
			$('#channelId').empty();
			$('#channelId').append('<option value="0">---请选择---</option>');
			
			if(channelType==0) {
				return;
			}
			
			ajax_channel_1();
		}
		
		function parseStatus(id,status)
		{
			if(status!=PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN)	
			{
				isPageOperating(id,"001","delete");
			}	
			return status.getPaymentStatus();;
		}
		
		function showmoney(amountPaied)
		{
			return amountPaied;
		}
	
		//查询数据
		function showsearch()
		{
			//alert(jQuery("#amount").val());
			if(jQuery("#amount").val()!="" && dealwithmoney(jQuery("#amount").val())==-1)
			{
				jQuery("#showDialog").html('<b>查询条件“实返金额”输入格式不正确，只能输入整数和小数！</b>');
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
		jQuery(function(){
			$('#channelType').change(function(){
				listChannel(this.value);
				//search001();
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
			//$('#channelId').change(function(){
			//	//search001();
			//});
			//$('#status').change(function(){
				//search001();
			//});
		});
		
		//删除院校返款单
		function deleteFun(id)
		{
			$('#message_confirm').dialog({
				title:'确认操作',
				buttons: {
					'确认': function() { 
							$(this).dialog("close"); 
							orderCeduChannelId=id;
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
		var orderCeduChannelId=0;
		function ajax_delpac(data)
		{		
			if(data.isback)
			{
				search001();
				jQuery("#showDialog").html('<b>操作成功！</b>');
				jQuery('#message_returns_tips').dialog('open');			
				
			}
			else
			{
				jQuery("#showDialog").html('<b>操作失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
		
		//统计招生返款单金额 
		function countallmoney(data)
		{
			//alert(data.allFeePaymentMoney);
			jQuery("#moneyall").html("合计：<font style='color:red'><b>"+data.allchannelmoney+"</b></font>元");
		}
		
		//统计金额
		function countCallback001(data)
		{
			ajax_1110_1();//统计汇款单金额
		}
	</script>
	
	<a:ajax successCallbackFunctions="channelCallback" parameters="CHANNEL_PARAM" pluginCode="channel" urls="/finance/branchchannelpayment/find_branch_channel_list_ajax"/>
	
	<!--删除院校返款-->
	<a:ajax 
		successCallbackFunctions="ajax_delpac" 
		parameters="{orderCeduChannelId:orderCeduChannelId}" 
		urls="/finance/branchchannelpayment/del_pay_branch_channel_ajax" 
		pluginCode="110"
	/>
	
	<!-- 统计招生返款单金额 -->
	<a:ajax 
		parameters="jQuery('#myform').serializeObject()" 
		successCallbackFunctions="countallmoney" 
		pluginCode="1110" 
		urls="finance/branchchannelpayment/count_order_cedu_channel_all_money_ajax"
	/>
	
</head>
<body>
	<head:head title="招生返款(中心)">
		<html:a text="单院校计算返款" icon="add" href="/finance/branchchannelpayment/add_pay_branch_channel" target="_blank"/>
		<html:a text="多院校合并计算返款" icon="add" href="/finance/branchchannelpayment/add_pay_branch_channel_common" target="_blank"/>
		<html:a text="学习中心与服务站合并计算返款" icon="add" href="/finance/branchchannelpayment/add_pay_branch_channel_special" target="_blank"/>
	</head:head>
	<body:body>
		<form id="myform" name="myform">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">招生返款</th>
					<th></th>
				</tr>
			</table>
			
			<table class="add_table" style="text-align:center !important;">
				<tr>
					<td class="lable_100">
						学习中心：
					</td>
					<td align="left">
						<s:property value="branch.name"/>
						<input type="hidden" name="orderCeduChannel.branchId" id="branchId" value="${branch.id}"/>
					</td>
					<td class="lable_100">招生途径：</td>
					<td align="left">
						<s:if test="%{channelTypes!=null}">
							<s:select id="channelType" name="orderCeduChannel.channelType"
								cssClass="txt_box_150" list="channelTypes" listKey="id"
								listValue="name" headerKey="0" headerValue="--请选择--">
							</s:select>
						</s:if>
		                <s:else>
		                	<select name="orderCeduChannel.channelType" id="channelType" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
		                </s:else>	
					</td>
					<td class="lable_100">合作方：</td>
					<td align="left">
						<select class="txt_box_150" id="channelId" name="orderCeduChannel.channelId">
							<option value="0">--请选择--</option>
						</select>
					</td>
					<td class="lable_100">状态：</td>
					<td align="left">
						<select id="status" name="orderCeduChannel.status" class="txt_box_150">
							<option value="0">--请选择--</option>
							<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN %>">已填招生返款</option>
							<option value="<%=Constants.PAYMENT_STATUS_QU_DAO_YI_SHENG_HE %>">渠道已审核</option>
							<option value="<%=Constants.PAYMENT_STATUS_SHANG_WU_YI_SHENG_HE %>">商务已审核</option>
							<option value="<%=Constants.PAYMENT_STATUS_CAI_WU_YI_SHEN_HE %>">财务已审核</option>
							<option value="<%=Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO %>">已汇款渠道</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lable_100">实返金额：</td>
		            <td align="left">
						<input type="text" name="amount" id="amount" class="txt_box_150" />
					</td>
					<td class="lable_100">返款日期起：</td>
					<td align="left">
						<input type="text" name="starttime" id="starttime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'endtime\',{d:-1});}'})" readonly="readonly"/>
					</td>
					<td class="lable_100">返款日期止：</td>
					<td align="left">
						<input type="text" name="endtime" id="endtime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'starttime\',{d:1});}'})" readonly="readonly"/>
					</td>
					<td></td>
					<td align="left">
						<input type="button" class="btn_black_61" value="查询" onclick="showsearch()"/>
					</td>
				</tr>
			</table>
		</form>	
						<page:plugin 
							pluginCode="001"
							il8nName="finance"
							subStringLength="20"
							searchListActionpath="list_pay_branch_channel_ajax"
							searchCountActionpath="count_pay_branch_channel_ajax"
							columnsStr="branchName;channelTypeName;channelName;amountToPay;#amountpaied;adjustReason;payDate;#statusname"
							customColumnValue="4,showmoney(amountPaied);7,parseStatus(id,status)"
							delete="json,deleteFun,id"
							view="http,/finance/branchchannelpayment/view_branch_channel,id,id,_blank"
							isPage="true"
							searchFormId="myform"
							isonLoad="true"
							isPackage="true"
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
	<!--弹出层    确认操作-->
	<div id="message_confirm" style="display:none">
		<div align="center" >
			<b>确认删除招生返款单么？</b>
		</div>
	</div>
</body>
</html>
