<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<%@ include file="../../template/common/download_excel.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>所有缴费单</title>
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
	<!-- 页签 -->	
	<jc:plugin name="tab" />
	<!-- 时间 -->
	<jc:plugin name="calendar" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	
	<script type="text/javascript">
	
		//显示充值时间
		function showtime(createdTime)
		{
			if(createdTime==null)
			{
				return createdTime;
			}
			if(createdTime.length>10)
			{
				return  createdTime.substring(0,10);
			}
			return createdTime;
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
		
		//查询
		function findfeepayment()
		{
			if(jQuery("#channelType").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择招生途径！</b>');
				jQuery('#message_returns_tips').dialog("open");
			}
			else
			{
				search001();
			}
		}
		
		jQuery(function(){
			
			//学习中心改变事件
			jQuery('#branchId').change(function(){
				
				if(jQuery('#channelType').val()!=0)
				{
					ajax_600_1();//合作方
				}
			});		
			
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
			
		});
	
		
		//合作方级联
		function ajax_channel_call_back(data)
		{
			jQuery('#channelId').empty();
			jQuery('#channelId').append('<option value="0">--请选择--</option>');
			if(data.list!=null && data.list.length>0)
			{
				jQuery.each(data.list,function(){	
			    	jQuery('#channelId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    });
			}		
		}
		
		//ajax回调函数  招生批次(院校)
		function ajax_batch(data)
		{				
			jQuery('#batchId').empty();
			jQuery('#batchId').append('<option value="0">--请选择--</option>');	
			if(data.batchlist!=null&&data.batchlist.length>0)
			{
				jQuery.each(data.batchlist,function(){	
					jQuery('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				});
			}	
			
			jQuery('#levelId').empty();
			jQuery('#levelId').append('<option value="0">--请选择--</option>');
			jQuery('#majorId').empty();
			jQuery('#majorId').append('<option value="0">--请选择--</option>');
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
		//导出
		function download(){
			if(confirm("您确定要导出数据吗？")){
				ajax_download_zip_1();
			}
		}
		
	</script>
	
	
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
	<!-- 下载地址 -->
	<a:ajax 
		parameters="jQuery('#myform').serializeObject()" 
		successCallbackFunctions="excel_export_callback" 
		pluginCode="download_zip" 
		urls="/finance/channelrebatereview/excel_export_list_channel_rebate_suffice_fpd"
	/>
		
</head>
<body>
	<head:head title="招生返款查询">
		
	</head:head>
		<body:body>
			<div>
				<s:include value="rebate_fpd_menu.jsp" />
			</div>
			<form id="myform" method="post">
				<table class="gv_table_2">
					<tr>
						<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
						<th style="text-align:left; font-weight:bold;">符合未返款</th>
						<th></th>
					</tr>
				</table>
						
				<table class="add_table">
				  <tbody id="rebateCondition">
					<tr>
						<td class="lable_100">学习中心：</td>
						<td>
							<s:if test="%{branchlist!=null}">
								<s:select list="%{branchlist}" headerKey="0" headerValue="--请选择--" listKey="id" theme="simple" listValue="name" name="student.branchId" id="branchId" cssClass="txt_box_150"/>
							</s:if>
							<s:else>
								<select name="student.branchId" id="branchId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
							</s:else>
						</td>
						<td class="lable_100"><span>*</span>招生途径：</td>
						<td>
							<s:if test="%{channeltypelist!=null}">
								<s:select list="%{channeltypelist}" listKey="id" theme="simple" listValue="name" name="student.enrollmentSource" id="channelType" cssClass="txt_box_150"/>
							</s:if>
			                <s:else>
			                	<select name="student.enrollmentSource" id="channelType" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
			                </s:else>	
						</td>
						<td class="lable_100">合作方：</td>
						<td>
							<select name="student.channelId" id="channelId" class="txt_box_300">
								<option value="0">--请选择--</option>
							</select>	
						</td>
					</tr>	
					<tr>
						 <td class="lable_100">全局批次：</td>
			             <td align="left">
							<s:if test="%{globalBatchList!=null}">
								<s:select list="%{globalBatchList}" headerKey="0" headerValue="--请选择--" listKey="id" theme="simple" listValue="batch" name="student.glbtach" id="globalBatchId" cssClass="txt_box_150"/>
							</s:if>
							<s:else>
								<select name="student.glbtach" id="globalBatchId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
							</s:else>
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
						<td class="lable_100">到账确认时间：</td>
						<td >
							<s:date name="channelRebateTimeLimit.ceduConfirmTime" format="yyyy-MM-dd" />
							<input type="hidden" name="feePaymentDetail.ceduConfirmTime" id="feePaymentDetail.ceduConfirmTime" value="${channelRebateTimeLimit.ceduConfirmTime}" />
						</td>
						<td class="lable_100">学费下限：</td>
						<td >
							${channelRebateTimeLimit.xueMoney}
							<input type="hidden" name="feePaymentDetail.jiaofeiValue" id="xueMoney" value="${channelRebateTimeLimit.xueMoney}" />
						</td>
					</tr>
					<tr>	
						<td class="lable_100" colspan="5"></td>
						<td>
							<input type="button" onclick="findfeepayment()" class="btn_black_61" value="查询"/>
							<input type="button" class="btn_black_61" onclick="download()" value="导出"/>
						</td>
					</tr>
				  </tbody>
				</table>
			</form> 
					<table class="gv_table_2">
						<tr>
							<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
							<th style="text-align:left; font-weight:bold;">符合未返款的缴费单</th>
							<th></th>
						</tr>
					</table>
			 
					<page:plugin 
						pluginCode="001"
						il8nName="finance_payment"
						subStringLength="20"
						searchListActionpath="list_channel_rebate_suffice_fpd_show_ajax"
						searchCountActionpath="count_channel_rebate_suffice_fpd_show_ajax"
						columnsStr="createdTime;channelName;studentName;branchName;schoolName;academyenrollbatchName;levelName;majorName;paymentSubjectName;jiaofeiValue;feeWayId;#status;ceduConfirmTime"
						customColumnValue="0,showtime(createdTime);9,feePaymentValue(jiaofeiValue);10,feeWayIdValue(feeWayId);11,showstatus(status);12,showtime(ceduConfirmTime);"
						isPage="true"
						params="'result.order':'student_id','result.sort':'asc'"
						searchFormId="myform"
						isPackage="true"
						isOrder="false"
						mergeCells="3"
					/>

		</body:body>
		<!--弹出层-->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
				
			</div>
		</div>
</body>
</html>
