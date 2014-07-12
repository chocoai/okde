<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>多院校计算返款</title>
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
	<!-- 分页 -->
	<jc:plugin name="page" />
	<jc:plugin name="calendar" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	
	<script type="text/javascript">
	
		//显示充值时间
		function showtime(createdTime)
		{
			if(createdTime.length>10)
			{
				return  createdTime.substring(0,10);
			}
			return createdTime;
		}
		//显示开课状态
		function showIsStartCourse(isStartCourse,id)
		{
			if(isStartCourse==STU_IS_START_COURSE_FALSE)
			{
				isPageOperating(id,"001","checkbox");
				return "<span style='color:red'>未开课</span>";
			}
			else if(isStartCourse==STU_IS_START_COURSE_TRUE)
			{
				return "已开课";
			}
		}
		//显示招生途径复核状态
		function showIsChannelTypeChecked(isChannelTypeChecked,id)
		{
			if(isChannelTypeChecked==STUDENT_CHANNEL_TYPE_CHECKED_FALSE)
			{
				isPageOperating(id,"001","checkbox");
				return "<span style='color:red'>未复核</span>";
			}
			else if(isChannelTypeChecked==STUDENT_CHANNEL_TYPE_CHECKED_TRUE)
			{
				return "已复核";
			}
		}
		//显示金额
		function feePaymentValue(feePayment)
		{
			return (feePayment+"").toMoney();
		}
		//显示缴费单状态
		function showstatus(status)
		{
			return status.getPaymentStatus();
		}
		//显示缴费方式
		function feeWayIdValue(feeWayId)
		{
			return feeWayId.getPaymentWay();
		}
		function showdescription(id,rebateStatus,refundLock)
		{
			if(rebateStatus<PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN)
			{
				isPageOperating(id,"001","checkbox");
				return "<span style='color:red'>总部未确认</span>";
			}
			if(refundLock==LOCKING_TRUE)
			{
				isPageOperating(id,"001","checkbox");
				return "<sapn style='color:red'>退费申请中</span>";
			}
			return "";
			
		}
		 //处理输入的钱是否正确
		function showcheckmoney()
		{
			if(dealwithnumber(jQuery("#payadd").val())=="--")
			{
				jQuery("#payadd").val("");
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
				jQuery("#showDialog").html('<b>输入格式不正确，只能输入整数和小数！</b>');
				$('#message_returns_tips').dialog("open");
			}
			else
			{
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
			}
		}
		//保存
		function doSave(){
			if(jQuery("#indexcount").val()==1)
			{
				jQuery("#showDialog").html('<b>已添加过，请重新选择合作方！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}	
			else if(jQuery("#oldChannelType").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择招生途径！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#oldChannelId").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择合作方！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#feepdIds").val()=="")
			{
				jQuery("#showDialog").html('<b>请选择要招生返款的缴费单明细！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				jQuery("#amountToPay").val(jQuery('#paysum').text());
				jQuery("#amountPaied").val(jQuery('#payall').text());
				jQuery("#adjustReason").val(jQuery('#note').val());
				
				ajax_140_1();
			}
			
		}
		//查询
		function findfeepayment()
		{
			if(jQuery("#channelType").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择招生途径！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#channelId").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择合作方！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#oldChannelId").val()==jQuery("#channelId").val())
			{
				search001();
			}
			else
			{
				jQuery("#oldChannelId").val(jQuery("#channelId").val());	
				jQuery("#oldChannelType").val(jQuery("#channelType").val());
				search001();
				jQuery('#rebateContent').show();	
					
				jQuery("#feepdIds").val("");
				jQuery("#indexcount").val(0);
				jQuery('#paysum').text("0");
				jQuery('#payall').text("0");
				jQuery('#payadd').val("");
				
				search003();
			}
		}
		//选择需要返款的待缴费单  获取缴费单明细ids集合
		function selectfpd()
		{
			if(getCheckedValues001()==null || getCheckedValues001()=="")
			{
				jQuery("#showDialog").html('<b>请选择要返款的缴费单明细！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				ajax_120_1();//判断选择	
			}
		}
		//移除不需要返款的待缴费单  获取缴费单明细ids集合
		function deletefpd()
		{
			//alert(getCheckedValues003());
			if(getCheckedValues003()==null || getCheckedValues003()=="")
			{
				jQuery("#showDialog").html('<b>请选择要移除的缴费单明细！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				$('#message_confirm').dialog({
					title:'确认操作',
					buttons: {
						'确认': function() { 
								$(this).dialog("close"); 
								ajax_130_1();//移除
							}, 
						'取消': function() { 
								$(this).dialog("close"); 
							} 
					}
				});
				$('#message_confirm').dialog("open"); 
			}
		}
	
		jQuery(function(){
			
			//jQuery("#starttime").val(getYearFirstDay());
			jQuery("#endtime").val(new Date().pattern("yyyy-MM-dd"));
			
			ajax_640_1();//全局批次
			//合作方级联
			jQuery('#channelType').change(function(){
				ajax_600_1();
			});	
			
			//院校
			jQuery('#academyId').change(function(){
				ajax_610_1();//批次
			});	
					
			//批次
			jQuery('#batchId').change(function(){
				ajax_620_1();//层次
			});			
							
			//层次
			jQuery('#levelId').change(function(){
				ajax_630_1();//专业	
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
			//不符合的缴费单
			jQuery('#show_for_feepaymentdetail').dialog({
				autoOpen:false,
				modal:true,
				draggable:false,
				resizable:false,
				title:'缴费单明细',
				width: 650,
				buttons: {
					'关闭': function() { 
						jQuery(this).dialog("close"); 
					} 
				}
			});	
			
		});
	
	
		//ajax回调函数  选取缴费单明细时的验证
		function ajax_test_fpd(data)
		{				
			if(data.isback)
			{
				jQuery("#feepdIds").val(data.newFeepdIds);
				//alert(jQuery("#feepdIds").val());
				//var paysum = jQuery('#paysum').text();
				//paysum = parseFloat(paysum);
				//jQuery('#paysum').text(paysum+parseFloat(data.allRebateMoney));	
				//jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithmoney(jQuery('#payadd').val())));
				search003();
				ajax_150_1();//统计金额
				search001();
			}
			else
			{
				//有政策人数太少的情况
				jQuery('#hasfpdtab > tbody').empty();
			   	var haslist='';
				if(data.noPeoplePoliceFpdList!=null && data.noPeoplePoliceFpdList.length>0)
				{
					jQuery.each(data.noPeoplePoliceFpdList,function(){	
						haslist+='<tr>';
						haslist+='<td align="center">'+this.paymentCode+'</td>';
						haslist+='<td align="center">'+this.studentName+'</td>';
						haslist+='<td align="center">'+this.paymentSubjectName+'</td>';
						haslist+='<td align="center">'+this.moneyToPay+'</td>';
						haslist+='</tr>';
					});
				}
				else
				{
					haslist+='<tr><td align="center" colspan="4">无相关数据！</td></tr>';
				}
				jQuery('#hasfpdtab > tbody').html(haslist);
				
				//无相关政策的数据
				jQuery('#nopolicefpdtab > tbody').empty();
			   	var noplist='';
				if(data.notPoliceFpdList!=null && data.notPoliceFpdList.length>0)
				{
					jQuery.each(data.notPoliceFpdList,function(){	
						noplist+='<tr>';
						noplist+='<td align="center">'+this.paymentCode+'</td>';
						noplist+='<td align="center">'+this.studentName+'</td>';
						noplist+='<td align="center">'+this.paymentSubjectName+'</td>';
						noplist+='<td align="center">'+this.moneyToPay+'</td>';
						noplist+='</tr>';
					});
				}
				else
				{
					noplist+='<tr><td align="center" colspan="4">无相关数据！</td></tr>';
				}
				jQuery('#nopolicefpdtab > tbody').html(noplist);
				
				jQuery("#feepdIds").val(data.newFeepdIds);
				
				search003();
				ajax_150_1();//统计金额
				search001();
				jQuery('#show_for_feepaymentdetail').dialog('open');
			}		
		}
		//ajax回调函数  移除缴费单明细
		function ajax_del_fpd(data)
		{
			if(data.isfail)
			{
				jQuery("#feepdIds").val(data.newdelFeepdIds);			
				//jQuery('#paysum').text(data.alldelRebateMoney);
				//jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithmoney(jQuery('#payadd').val())));
				search003();
				ajax_150_1();//统计金额
				search001();
			}
			else
			{
				jQuery("#showDialog").html('<b>移除失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
		
		//添加招生返款
		function ajax_pac(data)
		{
			
			if(data.isback)
			{
				jQuery("#indexcount").val(1);
				jQuery("#showDialog").html('<b>添加成功！</b>');
				jQuery('#message_returns_tips').dialog('open');			
				
			}
			else
			{
				jQuery("#showDialog").html('<b>添加失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
		
		//统计招生返款金额
		function ajax_count(data)
		{
			jQuery('#paysum').text(parseFloat(data.allMoney));
			jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithnumber(jQuery('#payadd').val())));
			
		}
		
		//合作方级联
		function ajax_channel_call_back(data)
		{
			$('#channelId').empty();
			$('#channelId').append('<option value="0">--请选择--</option>');
			if(data.list!=null && data.list.length>0)
			{
				$.each(data.list,function(){	
			    	$('#channelId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    });
			}		
		}
		
		//ajax回调函数  招生批次(院校)
		function ajax_batch(data)
		{				
			$('#batchId').empty();
			$('#batchId').append('<option value="0">--请选择--</option>');	
			if(data.batchlist!=null&&data.batchlist.length>0)
			{
				$.each(data.batchlist,function(){	
					$('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				});
			}	
			
			$('#levelId').empty();
			$('#levelId').append('<option value="0">--请选择--</option>');
			$('#majorId').empty();
			$('#majorId').append('<option value="0">--请选择--</option>');
		}
		
		//ajax回调函数  层次(招生批次)
		function ajax_level(data)
		{				
			$('#levelId').empty();
			$('#levelId').append('<option value="0">--请选择--</option>');
			if(data.levellist!=null && data.levellist.length>0)
			{
			   	$.each(data.levellist,function(){	
			   		$('#levelId').append('<option value="'+this.id+'">'+this.level.name+'</option>'); 
			 	});
			}	
			$('#majorId').empty();
			$('#majorId').append('<option value="0">--请选择--</option>');
		}
		
		//ajax回调函数  专业(层次)
		function ajax_major(data)
		{				
			$('#majorId').empty();
			$('#majorId').append('<option value="0">--请选择--</option>');
			if(data.majorlist!=null && data.majorlist.length>0)
			{
			    $.each(data.majorlist,function(){	
			    	$('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    });
			}	
		}
		
		
		//ajax回调函数   全局批次(学习中心)
		function ajax_global_batch(data)
		{		
			$('#globalBatchId').empty();
			$('#globalBatchId').append('<option value="0">--请选择--</option>');
			if(data.globalBatchList!=null && data.globalBatchList.length>0)
			{
				$.each(data.globalBatchList,function(){	
			    	$('#globalBatchId').append('<option value="'+this.id+'">'+this.batch+'</option>'); 
			    });
			}
		}
		
	</script>
	
	<!--选取缴费单明细时的验证-->
	<a:ajax 
		successCallbackFunctions="ajax_test_fpd" 
		parameters="{oldFeepdIds:jQuery('#feepdIds').val(),addFeepdIds:getCheckedValues001()}" 
		urls="/finance/channelrebate/test_fpd_for_channel_rebate_common_review_ajax" 
		pluginCode="120"
	/>
	
	<!--移除缴费单明细-->
	<a:ajax 
		successCallbackFunctions="ajax_del_fpd" 
		parameters="{allFeepdIds:jQuery('#feepdIds').val(),delFeepdIds:getCheckedValues003()}" 
		urls="/finance/channelrebate/del_fpd_for_channel_rebate_common_review_ajax" 
		pluginCode="130"
	/>
	
	<!--添加招生返款-->
	<a:ajax 
		successCallbackFunctions="ajax_pac" 
		parameters="jQuery('#myform').serializeObject()" 
		urls="/finance/channelrebate/add_channel_rebate_common_ajax" 
		pluginCode="140"
	/>
	<!--统计招生返款金额-->
	<a:ajax 
		successCallbackFunctions="ajax_count" 
		parameters="{feepdIds:jQuery('#feepdIds').val()}" 
		urls="/finance/channelrebate/count_channel_rebate_fpd_common_for_now_all_money_ajax" 
		pluginCode="150"
	/>
	
	<!--合作方级联(通过合作方类别)-->
	<a:ajax 
		successCallbackFunctions="ajax_channel_call_back" 
		parameters="{branchId:jQuery('#branchId').val(),channelType:jQuery('#channelType').val()}" 
		urls="/finance/branchchannelpayment/find_branch_channel_list_ajax"
		pluginCode="600" 
	/>
	
	<!--招生批次(院校)-->
	<a:ajax 
		successCallbackFunctions="ajax_batch" 
		parameters="{academyId:jQuery('#academyId').val()}" 
		urls="/enrollment/cascade_academy_batch_all_ajax" 
		pluginCode="610"
	/>
	
	<!--层次(招生批次)-->
	<a:ajax 
		successCallbackFunctions="ajax_level" 
		parameters="{batchId:jQuery('#batchId').val()}" 
		urls="/enrollment/cascade_batch_level_ajax" 
		pluginCode="620"
	/>
		
	<!--专业(层次)-->
	<a:ajax 
		successCallbackFunctions="ajax_major" 
		parameters="{levelId:jQuery('#levelId').val()}" 
		urls="/enrollment/cascade_level_major_ajax" 
		pluginCode="630"
	/>
	
	<!--全局批次(学习中心)-->
	<a:ajax 
		successCallbackFunctions="ajax_global_batch" 
		parameters="{branchId:jQuery('#branchId').val()}" 
		urls="/enrollment/cascade_global_batch_branch_ajax" 
		pluginCode="640"
	/>	
		
		
</head>
<body>
	<head:head title="多院校计算返款">
		
	</head:head>
	<body:body>
		<form id="myform" method="post">
		
			<input type="hidden" name="feepdIds" id="feepdIds" value=""/>
			
			<input type="hidden" name="orderCeduChannel.amountToPay" id="amountToPay" value="0"/>
			<input type="hidden" name="orderCeduChannel.amountPaied" id="amountPaied" value="0"/>
			<input type="hidden" name="orderCeduChannel.adjustReason" id="adjustReason" value=""/>
			<input type="hidden" name="indexcount" id="indexcount" value="0"/>
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">多院校计算返款</th>
					<th></th>
				</tr>
			</table>
					
			<table class="add_table">
			  <tbody id="rebateCondition">
				<tr>
					<td class="lable_100">学习中心：</td>
					<td>
						<s:property value="branch.name"/>
						<input type="hidden" name="student.branchId" id="branchId" value="${branch.id}" />
					</td>
					<td class="lable_100"><span>*</span>招生途径：</td>
					<td>
						<s:if test="%{channeltypelist!=null}">
							<s:select list="%{channeltypelist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="channelType" id="channelType" cssClass="txt_box_150"/>
						</s:if>
		                <s:else>
		                	<select name="channelType" id="channelType" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
		                </s:else>	
						<input type="hidden" name="orderCeduChannel.channelType" id="oldChannelType" value="0"/>
					</td>
					<td class="lable_100"><span>*</span>合作方：</td>
					<td>
						<select name="channelId" id="channelId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>	
						<input type="hidden" name="orderCeduChannel.channelId" id="oldChannelId" value="0"/>
					</td>
				</tr>	
				<tr>
					<td class="lable_100">全局批次：</td>
		             <td align="left">
						<select name="student.glbtach" id="globalBatchId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>
					</td>
					<td class="lable_100">院校：</td>
					<td>
						<s:if test="%{academylist!=null}">
							<s:select list="%{academylist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="student.academyId" id="academyId" cssClass="txt_box_150"/>
						</s:if>
		                <s:else>
		                	<select name="student.academyId" id="academyId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
		                </s:else>	
					</td>
					<td class="lable_100">招生批次：</td>
					<td>
						<select name="student.enrollmentBatchId" id="batchId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>
					</td>
					
				</tr>
				<tr>
					<td class="lable_100">层次：</td>
					<td>
						<select name="student.levelId" id="levelId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>	
					</td>
					<td class="lable_100">专业：</td>
					<td>
						<select name="student.majorId" id="majorId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>
					</td>
					<td class="lable_100">姓名：</td>
					<td>
						<input type="text" name="student.name" id="name" class="txt_box_150"/>
					</td>
					
				</tr>
				<tr>
					<td class="lable_100">证件号：</td>
					<td>
						<input type="text" name="student.certNo" id="certNo" class="txt_box_150"/>	
					</td>
					<td class="lable_100">缴费开始时间：</td>
					<td >
						<input id="starttime" class="Wdate" type="text" name="starttime"
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
							size="17" readonly="readonly"/>
					</td>
					<td class="lable_100">缴费结束时间：</td>
					<td>
						<input id="endtime" class="Wdate" type="text"
							name="endtime" size="17"
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						</td>
				</tr>
				<tr>	
					<td class="lable_100" colspan="5"></td>
					<td>
						<input type="button" onclick="findfeepayment()" class="btn_black_61" value="查询"/>	
					</td>
				</tr>
			  </tbody>
			 </table>
			</form> 
			<div id="rebateContent" style="display:none;" >
			 <table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">待返款缴费单明细</th>
					<th></th>
				</tr>
			</table>
			 
					<page:plugin 
						pluginCode="001"
						il8nName="finance_payment"
						subStringLength="20"
						searchListActionpath="list_channel_rebate_fpd_academy_review_ajax"
						searchCountActionpath="count_channel_rebate_fpd_academy_review_ajax"
						columnsStr="createdTime;paymentCode;studentName;schoolName;academyenrollbatchName;levelName;paymentSubjectName;jiaofeiValue;feeWayId;#status;isStartCourse;isChannelTypeChecked;#description"
						customColumnValue="0,showtime(createdTime);7,feePaymentValue(jiaofeiValue);8,feeWayIdValue(feeWayId);9,showstatus(status);10,showIsStartCourse(isStartCourse,id);11,showIsChannelTypeChecked(isChannelTypeChecked,id);12,showdescription(id,rebateStatus,refundLock)"
						isPage="true"
						isChecked="true"
						checkboxValue="id"
						params="'student.enrollmentSource':jQuery('#oldChannelType').val(),'student.channelId':jQuery('#oldChannelId').val(),'result.order':'student_id','result.sort':'asc'"
						searchFormId="myform"
						isonLoad="false"
						isPackage="true"
						isOrder="false"
						mergeCells="3"
					/>
				 
			<table class="add_table">
			  <tbody >
				<tr>
					<td align="left">
						(确认选择需要返款的缴费单明细)
						<input type="button" class="btn_black_61" value="选择" onclick="selectfpd()"/>
						
					</td>
				</tr>
				</tbody>
			</table>
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">现需返款缴费单明细</th>
					<th></th>
				</tr>
			</table>
					<page:plugin 
						pluginCode="003"
						il8nName="finance_payment"
						subStringLength="20"
						searchListActionpath="list_channel_rebate_fpd_common_for_now_review_ajax"
						searchCountActionpath="count_channel_rebate_fpd_common_for_now_review_ajax"
						columnsStr="createdTime;paymentCode;studentName;schoolName;academyenrollbatchName;levelName;majorName;paymentSubjectName;jiaofeiValue;paymentByChannel;#status"
						customColumnValue="0,showtime(createdTime);8,feePaymentValue(jiaofeiValue);9,feePaymentValue(paymentByChannel);10,showstatus(status)"
						isPage="true"
						isChecked="true"
						checkboxValue="id"
						params="'feepdIds':$('#feepdIds').val(),'result.order':'student_id','result.sort':'asc'"
						isonLoad="false"
						isPackage="true"
						isOrder="false"
					/>
				<table class="add_table">
				  <tbody >
					<tr>
						<td align="left">
							(确认移除不需要返款的缴费单明细)
							<input type="button" class="btn_black_61" value="移除" onclick="deletefpd()"/>
							
						</td>
					</tr>
					</tbody>
				</table>
			
			<table class="add_table">
			 <tbody>	 
				<tr>
					<td class="lable_100">应返款金额：</td>
					<td><span id="paysum">0</span></td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td class="lable_100">调整金额：</td>
					<td>
						<input type="text" name="payadd" id="payadd" onkeyup="javascript:showcheckmoney()" class="txt_box_100"/>
					</td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td class="lable_100">实际返款总金额：</td>
					<td><span id="payall">0</span></td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td class="lable_100">调整原因：</td>
					<td colspan="7"><textarea cols="60" rows="6" id="note" style="height:100px"></textarea></td>
				</tr>
				<tr>
					<td colspan="8" align="center"><input type="button" class="btn_black_61" value="保存" onclick="doSave()"/>&nbsp;&nbsp;<input type="button" class="btn_black_61" onclick="javascript:window.close();" value="关闭"/></td>
				</tr>
			  </tbody>
			</table>
		</div>	
	</body:body>
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
			
		</div>
	</div>
	<!--弹出层    确认操作-->
	<div id="message_confirm" style="display:none">
		<div align="center" >
			<b>确认移除选中的缴费单明细？</b>
		</div>
	</div>
	<div id="show_for_feepaymentdetail" style="display:none">
		<table class="gv_table_2">
			<tbody>
				<tr>
					<td style="width: 50%;" valign="top">
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
								<th style="text-align:left; font-weight:bold;" class="feehtml">无招生返款政策的缴费单明细</th>
							</tr>
						</table>				  
						<table class="gv_table_2" id="nopolicefpdtab">
							<thead>
								<tr>
									<th align="center">缴费单编号</th>
									<th align="center">学生姓名</th>
									<th align="center">费用科目</th>
									<th align="center">应缴金额  </th>
								</tr>
							</thead>					
							<tbody>	
											
							</tbody>						
						</table>
					</td>
					<td style="width: 50%;" valign="top">
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
								<th style="text-align:left; font-weight:bold;" class="feehtml">人数不满足招生返款标准的缴费单明细</th>
							</tr>
						</table>				  
						<table class="gv_table_2" id="hasfpdtab">
							<thead>
								<tr>
									<th align="center">缴费单编号</th>
									<th align="center">学生姓名</th>
									<th align="center">费用科目</th>
									<th align="center">应缴金额  </th>
								</tr>
							</thead>					
							<tbody>						
							</tbody>						
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</div>	
</body>
</html>
