<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<%@ include file="../../template/common/download_excel.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>日收款单查询</title>
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
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	<!-- 时间控件 -->
	<jc:plugin name="calendar" />
	
	<script type="text/javascript">

		jQuery(function(){
					
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
			
			//选择费用科目
			jQuery('#fee_subject_div').dialog({
				autoOpen:false,
				modal:true,
				draggable:false,
				resizable:false,
				title:'费用科目',
				buttons: {
					'确定': function() { 
						//获取选中的值
						var fsIds='';
						var fsNames='';
						jQuery("input[name='fscheck']").each(function(){
							if(jQuery(this).attr("checked")==true)
							{
							 	fsIds+=jQuery(this).val()+",";
							 	fsNames+=jQuery("#sp_"+jQuery(this).val()).text()+";";
							}
						});
						jQuery('#feeSubjectIds').val(fsIds);
						jQuery('#feeSubjectNames').val(fsNames);
						
						jQuery(this).dialog("close"); 
					}, 
					'关闭': function() { 
						jQuery(this).dialog("close"); 
					} 
				}
			});	
			
			//院校相关级联(批次、(防止刷新时其他不刷新))
			jQuery('#academyId').change(function(){
				ajax_130_1();
					
			});	
			//招生批次相关级联(层次、专业(防止刷新层次，专业不刷新))
			jQuery('#batchId').change(function(){
				ajax_140_1();
				
			});	
			//层次专业级联
			jQuery('#levelId').change(function(){
				ajax_150_1();
			});	
			
			//获取当天日期
			jQuery("#ccStartTime").val(new Date().pattern("yyyy-MM-dd"));
			
		});
		//全选
		function chkall(obj)
		{
			jQuery(".fsclass").attr('checked',obj.checked);
		}
		function showdiv()
		{
			jQuery('#fee_subject_div').dialog('open');
		}
		//查询
		function showsearch()
		{
			if(jQuery("#ccStartTime").val()=="" || jQuery.trim(jQuery("#ccStartTime").val())=="")
			{
				jQuery("#showDialog").html('<b>请选择总部确认日期！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				search001();
			}
			
		}
		
		//显示缴费单状态
		function showstatus(status)
		{
			return status.getPaymentStatus();
		}
		//显示列表金额
		function feePaymentValue(accountMoney){
			return (accountMoney+"").toMoney();
		}
		//显示充值时间
		function showtime(createdTime)
		{
			if(createdTime.length>10)
			{
				return  createdTime.substring(0,10);
			}
			return createdTime;
		}
		//读取批次
		function selectbatch(data)
		{
		    $('#batchId').empty();
			$('#batchId').append('<option value="0">--请选择--</option>');	
			if(data.batchlist!=null&&data.batchlist.length>0)
			{
				$.each(data.batchlist,function(){	
				   $('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				});
			}
			ajax_140_1();
		 }
		 //读取层次
		 function selectlevel(data)
		 {
		    $('#levelId').empty();
			$('#levelId').append('<option value="0">--请选择--</option>');
			if(data.levellist!=null&&data.levellist.length>0)
			{	
				$.each(data.levellist,function(){	
				   $('#levelId').append('<option value="'+this.id+'">'+this.level.name+'</option>'); 
				});
			}	
			ajax_150_1();
			       	
		 }
		//读取专业
		 function selectmajor(data)
		 {
		    $('#majorId').empty();
			$('#majorId').append('<option value="0">--请选择--</option>');
			if(data.majorlist!=null&&data.majorlist.length>0)
			{
			   $.each(data.majorlist,function(){	
			     $('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			   });
			}				   
		  }
		//ajax回调函数   院校
		function ajax_stusearchallajax(data)
		{
			//院校
			jQuery("#academyId").empty();
			jQuery("#academyId").append('<option value="0">--请选择--</option>');
			if(data.academyList!=null && data.academyList.length>0)
			{
				$.each(data.academyList,function(){	
				    jQuery("#academyId").append('<option value="'+this.id+'">'+this.name+'</option>'); 
				});
			}
			
		}	
		
		//ajax回调函数   学习中心
		function ajax_stubranchajax(data)
		{
			// 学习中心
			jQuery("#branchId").empty();
			jQuery("#branchId").append('<option value="0">--请选择--</option>');
			if(data.branchList!=null && data.branchList.length>0)
			{
				$.each(data.branchList,function(){	
					jQuery("#branchId").append('<option value="'+this.id+'">'+this.name+'</option>'); 
				});
			}
		}
		
		//统计缴费单金额 
		function countfpdallmoney(data)
		{
			//alert(data.allStuFpdMoney);
			jQuery("#fpdmoneyall").html("合计：<font style='color:red'><b>"+data.allStuFpdMoney+"</b></font>元");
		}
		
		function countCallback001(data)
		{
			ajax_1120_1();//统计缴费单明细金额
		}
			
	</script>
	<!-- 院校-->
	<a:ajax 
		pluginCode="110"
		successCallbackFunctions="ajax_stusearchallajax" 
		urls="/crm/academy_all_list_for_stu_search_ajax"
		isOnload="all" 			
	/>
	<!-- 学习中心-->
	<a:ajax 
		pluginCode="120"
		successCallbackFunctions="ajax_stubranchajax" 
		urls="/crm/branch_all_list_for_stu_search_ajax"
		isOnload="all" 			
	/>	
	<!-- 招生批次-->
	<a:ajax 
		pluginCode="130"
		successCallbackFunctions="selectbatch" 
		urls="/enrollment/cascade_academy_batch_all_ajax"
		parameters="{academyId:jQuery('#academyId').val()}"		
	/>
	<!-- 层次-->
	<a:ajax 
		pluginCode="140"
		successCallbackFunctions="selectlevel" 
		urls="/enrollment/cascade_batch_level_ajax"
		parameters="{batchId:jQuery('#batchId').val()}"		
	/>
	<!-- 专业-->
	<a:ajax 
		pluginCode="150"
		successCallbackFunctions="selectmajor" 
		urls="/enrollment/cascade_level_major_ajax"
		parameters="{levelId:jQuery('#levelId').val()}"		
	/>
	
	<!-- 统计缴费单金额 -->
	<a:ajax 
		parameters="jQuery('#myform').serializeObject()" 
		successCallbackFunctions="countfpdallmoney" 
		pluginCode="1120" 
		urls="finance/payment/count_payment_search_admin_day_money_ajax"
	/>
	
</head>
<body>
	<head:head title="日收款单查询">
		
	</head:head>
	<body:body>
		<form id="myform" method="post">
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">日收款单查询</th>
					<th></th>
				</tr>
			</table>
			<table class="add_table">
				<tbody id="rebateCondition">	
					 <tr>
						<td align="right">学习中心：</td>
						<td align="left">
							<select class="txt_box_130" name="student.branchId" id="branchId">
								<option value="0">--请选择--</option>
							</select>
						 </td>	
						<td align="right">院校：</td>
						<td align="left">
							<select class="txt_box_130" name="student.academyId" id="academyId">
								 <option value="0">--请选择--</option>
							</select>
						</td>
						<td align="right">招生批次：</td>
						<td align="left">
							<select class="txt_box_130" name="student.enrollmentBatchId" id="batchId">
								  <option value="0">--请选择--</option>
							</select>
						</td>
					</tr>
					<tr>	
						<td align="right">层次：</td>
						<td align="left">
							<select class="txt_box_130" name="levelId" id="levelId">
								<option value="0">--请选择--</option>
							</select>
						</td>
						<td align="right">专业：</td>
						<td align="left">
							<select class="txt_box_130" name="student.majorId" id="majorId">
								<option value="0">--请选择--</option>
							</select>
						</td>
						<td align="right">姓名：</td>
						<td align="left">
							<input  name="student.name" class="txt_box_130" type="text" value="" />
						</td>
				     </tr>
				     <tr>
				     	<td align="right">证件号：</td>
						<td align="left">
							<input  name="student.certNo" class="txt_box_130" type="text" />
						</td>	  	
				        <td align="right">缴费日期起：</td>
					    <td align="left">
							<input type="text" name="starttime" id="starttime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'endtime\',{d:0});}'})" readonly="readonly"/>					  			
						</td>
					    <td align="right">缴费日期止：</td>
					    <td align="left">
							<input type="text" name="endtime" id="endtime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'starttime\',{d:0});}'})" readonly="readonly"/>
						</td>	
					</tr>
					<tr>	
						<td align="right">费用科目：</td>
						<td align="left" colspan="3">
							<input name="feeSubjectNames" id="feeSubjectNames" class="txt_box_300" type="text" value="" readonly="readonly"/>&nbsp;&nbsp;<a href="javascript:showdiv();" style="text-decoration:underline">请选择</a>
							<input name="feeSubjectIds" id="feeSubjectIds" type="hidden" value="" />
						</td>
						<td align="right">缴费单状态：</td>
					    <td align="left">
							<select name="status" id="status" class="txt_box_150">
								<option value="0">--请选择--</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN %>">已收费确认</option>						
								<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN %>">已填汇款单</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU %>">已汇款总部</option>				
								<option value="<%=Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN %>">总部确认</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN %>">已填打款单</option>								
								<option value="<%=Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN %>">已确认待汇款</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO %>">已打款院校</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN %>">已填返款单</option>
								<option value="<%=Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN %>">返款确认</option>								 
							</select>
						</td>
				   	</tr>
				    <tr>
				    	<td align="right"><span>*</span>总部确认日期：</td>
					    <td align="left">
							<input type="text" name="ccStartTime" id="ccStartTime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen'})" readonly="readonly"/>					  			
						</td>
					    <td align="right"></td>
					    <td align="left">
										
						</td>
				        <td ></td>
					    <td align="left">
							<input type="button" class="btn_black_61"  onclick="showsearch();" value="查询"/>
						</td>
				     </tr>
				</tbody>
			</table>
		</form>	
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">缴费单明细</th>
					<th style="text-align:right; font-weight:bold;"></th>
				</tr>
			</table>
								 
				<page:plugin 
						pluginCode="001"
						il8nName="finance_payment"
						subStringLength="20"
						searchListActionpath="list_payment_search_admin_day_ajax"
						searchCountActionpath="count_payment_search_admin_day_ajax"
						columnsStr="createdTime;paymentCode;studentName;branchName;schoolName;academyenrollbatchName;levelName;majorName;paymentSubjectName;jiaofeiValue;#status;ceduConfirmTime"
						customColumnValue="0,showtime(createdTime);9,feePaymentValue(jiaofeiValue);10,showstatus(status);11,showtime(ceduConfirmTime)"
						isPage="true"
						params="'result.order':'createdTime','result.sort':'desc'"
						searchFormId="myform"
						customToolbarID="fpdmoneyall"
						listCallback="countCallback"
				/>
	</body:body>
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
			
		</div>
	</div>

	<!--弹出层    确认操作-->
	<div id="fee_subject_div" style="display:none">
		<table class="gv_table_2" id="">
			<thead>
				<tr>
					<th align="center"><input type="checkbox" onclick="chkall(this)" /></th>
			  		<th align="center">费用科目</th>
				</tr>
			</thead>					
			<tbody>	
				<s:if test="%{feesubjectlist!=null && feesubjectlist.size()>0}">
					<s:iterator id="item" value="feesubjectlist" >
						<tr>
							<td align="center"><input type="checkbox" id="fs_${item.id}" name="fscheck" class="fsclass" value="${item.id}" /></td>
					  		<td align="center"><span id="sp_${item.id}" style="color:black">${item.name}</span></td>
						</tr>
					</s:iterator>
		       </s:if>
		       <s:else>
			       <tr>
			       	 <td colspan="2" align="center">无费用科目</td>
			       </tr>
		      </s:else>							
			</tbody>						
		</table>
	</div>
</body>
</html>