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
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	<!-- 分页插件 -->
	<jc:plugin name="page" />
	<!-- 时间控件 -->
	<jc:plugin name="calendar" />
	<script type="text/javascript">
		
		CHANNEL_PARMA = null;
		function channelCallback(data){
			//$('#channelId').append('<option value="-1">--请选择--</option>');
			$(data.list).each(function(){
				$('#channelId').append('<option value="'+this.id+'">'+this.name+'</option>');
			});
		}
	
		function listChannel(channelType){
			CHANNEL_PARAM = {channelType: channelType,branchId:jQuery("#branchId").val()};
			$('#channelId').empty();
			$('#channelId').append('<option value="0">--请选择--</option>');
			
			if(channelType==0) {
				return;
			}
			
			ajax_channel_1();
		}
		
		function branchListChannel(branchId){
			var channelType = $('#channelType').val();
			
			CHANNEL_PARAM = {channelType: channelType,branchId: branchId};
			$('#channelId').empty();
			$('#channelId').append('<option value="0">--请选择--</option>');
			
			if(channelType==0 || branchId==0) {
				return;
			}
			
			ajax_channel_1();
		}
		
		function parseStatus(status)
		{
			return status.getPaymentStatus();;
		}
		
		function showmoney(amountPaied)
		{
			return (amountPaied+"").toMoney();
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
		
		function operate(id,status,types)
		{
			if(status==PAYMENT_STATUS_SHANG_WU_YI_SHENG_HE && types==FALLBACK_TYPES_SUBMIT)
			{
				return '<a href="javascript:updateOcc('+id+');">审核通过</a>&nbsp;&nbsp;<a href="javascript:fallBack('+id+')">回退</a>';
			}
			else
			{
				isPageOperating(id,"001","checkbox");
			}
			return "";
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
			$('#branchId').change(function(){
				branchListChannel(this.value);
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
		
		//判断批量审核
		function batchApproval()
		{
			if(getCheckedValues001()==null || getCheckedValues001()=="")
			{
				jQuery("#showDialog").html('<b>请选择要批量审核的招生返款单！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				jQuery('#message_confirm_batch').dialog({
					title:'操作提示',
					buttons: {
						'确认': function() { 
								ajax_130_1();
								jQuery(this).dialog("close");				
							}, 
						'取消': function() { 
								jQuery(this).dialog("close"); 
							} 
						}
				});
				jQuery('#message_confirm_batch').dialog("open"); 
			}
		}
		
		//回退
		function fallBack(id)
		{
			$('#message_fall_back').dialog({
				title:'请填写回退原因',
				width:330,
				buttons: {
					'确认': function() { 
							if(jQuery("#rollbackreason").val()=="" || jQuery.trim(jQuery("#rollbackreason").val())=="")
							{
								jQuery("#showDialog").html('<b>请填写回退原因！</b>');
								jQuery('#message_returns_tips').dialog("open");
							}
							else
							{
								orderId=id;
								ajax_120_1();//更新招生返款单回退状态	
								$(this).dialog("close");	
							}			
						}, 
					'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
			});
			$('#rollbackreason').val('');
			$('#rollbackreason').css('height','100');
			$('#message_fall_back').dialog("open");
		}
		
		//更新院校返款单
		function updateOcc(id)
		{
			$('#message_confirm').dialog({
				title:'确认操作',
				buttons: {
					'确认': function() { 
							$(this).dialog("close"); 
							orderCeduChannelId=id;
							ajax_110_1();//更新
						}, 
					'取消': function() { 
							$(this).dialog("close"); 
						} 
				}
			});
			$('#message_confirm').dialog("open"); 
		}
		
		//更新招生返款单
		var orderCeduChannelId=0;
		var status=PAYMENT_STATUS_CAI_WU_YI_SHEN_HE;
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
		
		//ajax 回调函数     回退状态
		var orderId=0;
		var orderTypes=FALLBACK_TYPES_ROLLED_BACK;
		function ajax_updatetypes(data)
		{
			if(data.isfail)
			{
				search001();
				jQuery("#showDialog").html('<b>操作成功！</b>');
				jQuery('#message_returns_tips').dialog("open");	
			}	
			else
			{
				jQuery("#showDialog").html('<b>操作失败！</b>');
				jQuery('#message_returns_tips').dialog("open");
			}
		}
		
		//ajax回调函数   批量审核
		function ajax_batchconfirm(data)
		{	
			if(data.isback)
			{
				//search001();
				refresh001();
				jQuery("#showDialog").html('<b>批量审核成功！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				jQuery("#showDialog").html('<b>批量审核失败！</b>');
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
	
	<a:ajax 
		successCallbackFunctions="channelCallback"
		parameters="CHANNEL_PARAM" 
		pluginCode="channel" 
		urls="/finance/branchchannelpayment/find_branch_channel_list_ajax"
	/>
	
	<!--更新招生返款单-->
	<a:ajax 
		successCallbackFunctions="ajax_delpac" 
		parameters="{orderCeduChannelId:orderCeduChannelId,status:status}" 
		urls="/finance/channelrebatereview/update_pay_branch_channel_review_ajax" 
		pluginCode="110"
	/>
	
	<!--更新招生返款单回退状态-->
	<a:ajax 
		pluginCode="120"
		parameters="{orderId:orderId,orderTypes:orderTypes,rollBackReason:jQuery.trim(jQuery('#rollbackreason').val())}" 
		successCallbackFunctions="ajax_updatetypes"
		urls="finance/channelrebatereview/update_pay_branch_channel_types_review_ajax"
	/>
	
	<!-- 批量确认 -->
	<a:ajax 
		parameters="{orderCcIds:getCheckedValues001(),status:status}" 
		successCallbackFunctions="ajax_batchconfirm" 
		pluginCode="130" 
		urls="finance/channelrebatereview/update_batch_pay_branch_channel_status_review_ajax"
	/>
	
	<!-- 统计招生返款单金额 -->
	<a:ajax 
		parameters="jQuery('#myform').serializeObject()" 
		successCallbackFunctions="countallmoney" 
		pluginCode="1110" 
		urls="finance/channelrebatereview/count_order_cedu_channel_all_money_review_ajax"
	/>
	
</head>
<body>
	<head:head title="招生返款审核(商务)">
		<html:a text="批量审核" icon="update" onclick="batchApproval()"/>
	</head:head>
	<body:body>
		<form id="myform" name="myform">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">招生返款审核(商务)</th>
					<th></th>
				</tr>
			</table>
			
			<table class="add_table" style="text-align:center !important;">
				<tr>
					<td class="lable_100">
						学习中心：
					</td>
					<td align="left">
						<s:if test="%{branchList!=null}">
							<s:select id="branchId" name="orderCeduChannel.branchId"
								cssClass="txt_box_150" list="branchList" listKey="id"
								listValue="name" headerKey="0" headerValue="--请选择--">
							</s:select>
						</s:if>
						<s:else>					
							<select class="txt_box_130" name="orderCeduChannel.branchId" id="branchId">
								 <option value="0">--请选择--</option>
							</select>
						</s:else>	
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
				</tr>
				<tr>
					<td class="lable_100">实返金额：</td>
		            <td align="left">
						<input type="text" name="amount" id="amount" class="txt_box_150" />
					</td>
					<td class="lable_100">状态：</td>
					<td align="left">
						<select id="status" name="orderCeduChannel.status" class="txt_box_150">
							<option value="0">--请选择--</option>
							<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN %>">已填招生返款</option>
							<option value="<%=Constants.PAYMENT_STATUS_QU_DAO_YI_SHENG_HE %>">渠道已审核</option>
							<option selected="selected" value="<%=Constants.PAYMENT_STATUS_SHANG_WU_YI_SHENG_HE %>">行政已审核</option>
							<option value="<%=Constants.PAYMENT_STATUS_CAI_WU_YI_SHEN_HE %>">商务已审核</option>
							<option value="<%=Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO %>">已汇款</option>
						</select>
					</td>
					<td class="lable_100">回退状态：</td>
					<td align="left">
						<select name="orderCeduChannel.types" class="txt_box_150">
							<option value="0">--请选择--</option>
							<option selected="selected" value="<s:property value="@net.cedu.common.Constants@FALLBACK_TYPES_SUBMIT"/>">已提交</option>
							<option value="<s:property value="@net.cedu.common.Constants@FALLBACK_TYPES_ROLLED_BACK"/>">已回退</option>
						</select>
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
							subStringLength="15"
							searchListActionpath="list_pay_branch_channel_review_ajax"
							searchCountActionpath="count_pay_branch_channel_review_ajax"
							columnsStr="branchName;channelTypeName;channelName;amountToPay;adjustPaied;#amountpaied;adjustReason;payDate;#statusname;#types;rollBackReason;public.operating"
							customColumnValue="3,showmoney(amountToPay);4,showmoney(adjustPaied);5,showmoney(amountPaied);8,parseStatus(status);9,showtypes(types);11,operate(id,status,types)"
							isChecked="true"
							checkboxValue="id"
							view="http,/finance/channelrebatereview/view_branch_channel_review,id,id,_blank"
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
			<b>确认该招生返款单审核无误？</b>
		</div>
	</div>
	<div id="message_fall_back" style="display:none">
		<div align="center" id="showfallback">
			<textarea maxlength="200" class="txt_box_300" cols="40" rows="5" id="rollbackreason" style="height:100px"></textarea>
		</div>
	</div>
	<div id="message_confirm_batch" style="display:none">
		<div align="center" id="showconfirm">
			<b>确认所有选择的招生返款单审核无误？</b>
		</div>
	</div>	
</body>
</html>
