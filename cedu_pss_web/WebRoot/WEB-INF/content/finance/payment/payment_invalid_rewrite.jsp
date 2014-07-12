<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>缴费单作废</title>
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
			//显示列表||
			//金额
			function feePaymentValue(feePayment)
			{
				return (feePayment+"").toMoney();
			}
			//缴费单类型
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
			//操作
			function operatingValue(id,status,pamentType,code)
			{
				var str="";
				if(status==PAYMENT_STATUS_ZUO_FEI || pamentType==FEE_PAYMENT_TYPE_REFUND_SINGLE)
				{
					
				}
				else 
				{
					str+="<a href='javascript:paymentInvalid("+id+")'>作废</a>&nbsp;&nbsp;";
				}
				if(status==PAYMENT_STATUS_ZUO_FEI)
				{
					
				}
				else 
				{
					str+='&nbsp;&nbsp;<a href="javascript:updatetime('+id+',\''+code+'\')">修改日期</a>';
				}
				return str;
			}
			//显示状态
			function statusValue(status)
			{			
				return status.getPaymentStatus();
			}
			//缴费方式
			function feeWayIdValue(feeWayId)
			{
				return feeWayId.getPaymentWay();
			}
			//查看详细
			function showcode(code,id)
			{
				return "<a href='"+WEB_PATH+"/finance/payment/payment_view?feePaymentId="+id+"' target='_blank'>"+code+"</a>";
			}
			
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
				//修改日期提示
				jQuery('#message_update_time').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'修改缴费日期',
					buttons: {
						'确认': function() { 
							if(jQuery("#xiugai").val()=="" || jQuery.trim(jQuery("#xiugai").val())=="")
							{
								jQuery("#showDialog").html('<b>请选择缴费日期！</b>');
								$('#message_returns_tips').dialog("open"); 	
							}
							else
							{
								createdTime=jQuery("#xiugai").val();
								ajax_610_1();
								$(this).dialog("close"); 
							}
							
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				//释放收据号
				jQuery('#back_up_bar_code').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'操作提示',
					buttons: {
						'是': function() { 
							if(jQuery("#hyfpdId").val()-0>0)
							{
								ajax_620_1();
								jQuery(this).dialog("close");
							}
							else
							{
								 jQuery("#showDialog").html('<b>该缴费单无收据号，不用作废！</b>');
								 jQuery('#message_returns_tips').dialog("open");
								 jQuery(this).dialog("close");
							}							
						}, 
						'否': function() { 
							jQuery(this).dialog("close"); 
						} 
					}
				});
			});
			
			//修改日期
			function updatetime(id,code)
			{
				//alert(code);

				fpId=id;
				jQuery("#jfcod").val(code);					
				jQuery('#message_update_time').dialog("open");
				
			}
			
			//缴费单作废
			function paymentInvalid(id)
			{
				$('#message_confirm').dialog({
					title:'确认操作',
					buttons: {
						'确认': function() { 
							//alert(id);
							feePaymentId=id;
							ajax_600_1();
							$(this).dialog("close"); 
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				$('#message_confirm').dialog("open");
			}
			
			//ajax回调函数     缴费单作废
			var feePaymentId=0;
			function ajax_paymentInvalid(data)
			{
				if(data.count==0)
			    {
			    	if(data.feePayment.barCode!=null && data.feePayment.barCode!="")
			    	{
			    		jQuery("#showbarcode").text(data.feePayment.barCode);
			    		jQuery("#hyfpdId").val(data.feePayment.id);
			    		jQuery('#back_up_bar_code').dialog("open");
			    	}
			    	else
			    	{
					    jQuery("#showDialog").html('<b>作废成功！</b>');
						jQuery('#message_returns_tips').dialog("open"); 	
					}
					//search001();
			    }
			    else if(data.count==1)
			    {
			    	jQuery("#showDialog").html('<b>该缴费单不能作废,缴费单明细不满足作废条件！</b>');
					$('#message_returns_tips').dialog("open");
			    }	
			    else if(data.count==2)
			    {
			    	jQuery("#showDialog").html('<b>报名费或测试费的缴费单不是开单情况不能作废！</b>');
					$('#message_returns_tips').dialog("open");
			    }
			    else if(data.count==4)
			    {
			    	jQuery("#showDialog").html('<b>该学生费用科目账户剩余金额小于预缴费单充值金额，不能作废！</b>');
					$('#message_returns_tips').dialog("open");
			    }
			    else
			    {
			    	jQuery("#showDialog").html('<b>作废失败！</b>');
					$('#message_returns_tips').dialog("open");
			    }
			    refresh001();
			}			
			
			//ajax回调函数     缴费单修改缴费日期
			var fpId=0;
			var createdTime="";
			function ajax_time(data)
			{
				if(data.index==0)
			    {
				    jQuery("#showDialog").html('<b>修改成功！</b>');
					$('#message_returns_tips').dialog("open"); 	
					
			    }
			    else if(data.index==1)
			    {
			    	jQuery("#showDialog").html('<b>该缴费单不能修改,缴费单明细总部已确认！</b>');
					$('#message_returns_tips').dialog("open");
			    }	
			    else if(data.index==2)
			    {
			    	jQuery("#showDialog").html('<b>该退费单不能修改,退费单已审批通过！</b>');
					$('#message_returns_tips').dialog("open");
			    }				    
			    else
			    {
			    	jQuery("#showDialog").html('<b>修改失败！</b>');
					$('#message_returns_tips').dialog("open");
			    }
			    refresh001();
			}		
			//ajax回调函数     释放收据号
			function ajax_barcode(data)
			{
				if(data.isback)
			    {
				    jQuery("#showDialog").html('<b>还原收据号成功！</b>');
				    jQuery("#hyfpdId").val(0);
					$('#message_returns_tips').dialog("open"); 	
					
			    }
			    else
			    {
			    	jQuery("#showDialog").html('<b>还原收据号失败！</b>');
					$('#message_returns_tips').dialog("open");
			    }	
			    
			    refresh001();
			}				
		</script>
		
		<!--缴费单作废-->
		<a:ajax 
			pluginCode="600"
			successCallbackFunctions="ajax_paymentInvalid" 
			parameters="{feePaymentId:feePaymentId}" 
			urls="/finance/payment/update_payment_invalid_rewrite_ajax" 
		/>
		<!--缴费单修改缴费日期-->
		<a:ajax 
			pluginCode="610"
			successCallbackFunctions="ajax_time" 
			parameters="{fpId:fpId,createdTime:createdTime}" 
			urls="/finance/payment/update_payment_created_time_ajax" 
		/>
		<!--释放收据号-->
		<a:ajax 
			pluginCode="620"
			successCallbackFunctions="ajax_barcode" 
			parameters="{fpId:jQuery('#hyfpdId').val()}" 
			urls="/finance/payment/update_payment_bar_code_ajax" 
		/>
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="缴费单作废">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">缴费单</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">
				
				<table class="add_table" border="0">
					<tr>
						<td align="right">学习中心：</td>
		                <td align="left">
							${branch.name}
							<input type="hidden" id="branchId" name="student.branchId" value="${branch.id}"/>
						</td>
		                
		                <td align="right">全局批次：</td>
		                <td align="left">
							<select name="" id="globalBatchId" class="txt_box_150">
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
	             			<select name="feePayment.feeWayId" id="paymentway" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
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
						<td ></td>
		                <td align="left">
		                	<input type="hidden" name="feePayment.isPrint" id="isPrint" value="-1"/>
		                	<!-- input name="feePayment.pamentType" id="pamentType" type="hidden" value="<%=Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE %>"/> -->
							<input type="button" class="btn_black_61"  onclick="showsearch();" value="查询"/>
						</td>
	             	</tr>
				</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="finance_payment"
										searchCountActionpath="finance/payment/count_payment_invalid_rewrite_page_ajax"
										searchListActionpath="finance/payment/list_payment_invalid_rewrite_page_ajax"
										columnsStr="createdTime;paymentcode;studentName;schoolName;academyenrollbatchName;levelName;majorName;feeWayId;feePayment;rechargeAmount;totalAmount;pamentType;barCode;status;#public.operating"
										customColumnValue="1,showcode(code,id);7,feeWayIdValue(feeWayId);8,feePaymentValue(feePayment);9,feePaymentValue(rechargeAmount);10,feePaymentValue(totalAmount);11,pamentTypeValue(pamentType);13,statusValue(status);14,operatingValue(id,status,pamentType,code)"
										pageSize="10"
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
			<div align="center"><b>确认作废该缴费单么？</b></div>
		</div>
		<div id="message_update_time" style="display:none">
			<table class="add_table">
				<tr>
					<td class="lable_100"><span style="color:red">*</span>缴费单号：</td>
					<td><input type="text" name="jfcod" id="jfcod" readonly="readonly"/></td>
				</tr>
				<tr>
					<td class="lable_100"><span style="color:red">*</span>缴费日期：</td>
					<td><input type="text" name="xiugai" id="xiugai" onfocus="WdatePicker({skin:'whyGreen'})" class="Wdate" readonly="readonly"/></td>
				</tr>
			</table>
		</div>
		
		<div id="back_up_bar_code" style="display:none">
			<div align="center">
				<b>已作废成功，是否将收据号<span id="showbarcode"></span>还原为未作废状态？</b>
				<input type="hidden" name="hyfpdId" id="hyfpdId" value="0" />
			</div>
		</div>
  </body>
</html>
