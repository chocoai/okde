<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生缴费</title>
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
		
		<%@ include file="common.jsp" %>
		<script type="text/javascript">
		
			//加载事件
			jQuery(function(){
		
				//初始化弹出框
				//所有优惠券
				jQuery('#show_for_prompt').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'所有优惠卷',
					width: 650,
					buttons: {
						'关闭': function() { 
							jQuery(this).dialog("close"); 
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
			function operatingValue(sid,status,pendingTestCounts,isExemption){
				//if(status>=STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI){
				//	return "<a href='#'>缴费</a>";
				//}else{
				if(isExemption==STUDENT_IS_EXEMPTION_FALSE && pendingTestCounts>0)
				{
					//return "<a href='"+WEB_PATH+"/finance/payment/add_school_payment?student.id="+sid+"' target='_blank'>缴费</a> <a href='"+WEB_PATH+"/finance/payment/add_test_payment?student.id="+sid+"' target='_blank'>缴测试费</a>";
					return "<a href='"+WEB_PATH+"/finance/payment/add_school_payment?student.id="+sid+"' target='_blank'>缴费</a>";
				}
				else
				{
					return "<a href='"+WEB_PATH+"/finance/payment/add_school_payment?student.id="+sid+"' target='_blank'>缴费</a>";
				}
				//}
				
			}
			function couponValue(id){
				return '<a href="javascript:showdiscount('+id+')" title="查看优惠卷"><img src="<ui:img url="/images/icon_discount.gif" />" /></a>';
				
			}
			
			//刷新不需要申请的学生优惠
			function refreshdiscount(id)
			{
				studentId=id;
				ajax_190_1();//刷新优惠
			}
			
			//显示已经申请的优惠ajax
			function showdiscount(id)
			{
				//refreshdiscount(id);//先刷新优惠
				$('#usageFlag').empty();
			    $('#usageFlag').append('<option value="'+POLICY_USING_STATUS_ALL+'">--全部--</option>');
			    $('#usageFlag').append('<option value="'+POLICY_USING_STATUS_APPLY+'">--已申请--</option>');
			    $('#usageFlag').append('<option value="'+POLICY_USING_STATUS_FALSE+'">--未使用--</option>');
			    $('#usageFlag').append('<option value="'+POLICY_USING_STATUS_TRUE+'">--已使用--</option>');
			   
				studentId=id;
				
				ajax_170_1();//显示已经申请的优惠
			}
			
			//ajax回调函数   显示已申请的学生优惠
			function ajax_showdiscount(data)
			{				
				$('#showdiscount > tbody').empty();
			   	var list='';
			    if(data.discountApplicationList!=null && data.discountApplicationList.length>0)
			    {
			    	$.each(data.discountApplicationList,function(){	
			    		list+='<tr>';
			    		list+='<td align="center">'+this.code+'</td>';
			    		list+='<td align="center">'+this.feeSubjectName+'</td>';
			    		list+='<td align="center">'+this.startTime.substring(0,10)+"~"+this.endTime.substring(0,10)+'</td>';
			    		if(this.discountWay==MONEY_FORM_RATIO)
			    		{
			    			list+='<td align="center">'+this.money+'%</td>';
			    		}
			    		else
			    		{
			    			list+='<td align="center">'+this.money+'元</td>';
			    		}
			    		list+='<td align="center">';
			    		if(this.usageFlag==POLICY_USING_STATUS_APPLY)
			    		{
			    			list+='已申请';
			    		}
			    		else if(this.usageFlag==POLICY_USING_STATUS_FALSE)
			    		{
			    			list+='未使用';
			    		}
			    		else
			    		{	
			    			list+='已使用';
			    		}
			    		list+='</td>';
			    		//list+='<td align="center">';
			    		//if(this.usageFlag==POLICY_USING_STATUS_APPLY)
			    		//{
			    			
			    		//	list+='<a href="javascript:deldiscountapplied('+this.id+')">删除</a>';
			    		//}
			    		//else
			    		//{
			    		//	list+='';
			    		//}
			    		//list+='</td>';
			    		list+='</tr>';
			    	});
			    }
			    else
			    {
			    	list+='<tr><td colspan="6" align="center">没有已申请的优惠政策！</td></tr>';
			    }
			    $('#showdiscount > tbody').html(list);	
			    $('#show_for_prompt').dialog('open');
			}
			
			//ajax回调函数  刷新不需要申请的学生优惠
			var studentId=0;
			function ajax_refreshdiscount(data)
			{				
			    if(data.isfail)
			    {
			    	jQuery("#showDialog").html('<b>操作成功！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }
			    else
			    {
			    	jQuery("#showDialog").html('<b>操作失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }			  
			}
		</script>
		<!--显示已申请的学生优惠-->
		<a:ajax successCallbackFunctions="ajax_showdiscount" parameters="{studentId:studentId,usageFlag:jQuery('#usageFlag').val()}" urls="/enrollment/list_all_student_applied_ajax" pluginCode="170"/>
		
		<!--刷新不需要申请的学生优惠-->
		<a:ajax successCallbackFunctions="ajax_refreshdiscount" parameters="{studentId:studentId}" urls="/enrollment/refresh_student_application_unapply_ajax" pluginCode="190"/>
	</head>
  <body>
	
	<!-- 头开始 -->
		<head:head title="学生缴费">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">学生缴费</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">
				<table class="add_table">
					<tr>
						<td>学习中心：</td>
		                <td align="left">
							${branch.name}
							<input type="hidden" id="branchId" name="student.branchId" value="${branch.id}"/>
							<input type="hidden" id="startStatusId" name="student.startStatusId" value="<%=Constants.STU_CALL_STATUS_GENG_JIN_ZHONG %>"/>
							<input type="hidden" name="student.callStatus" value="-1"/>
						
						</td>
		                
		                <td>全局批次：</td>
		                <td align="left">
							<select name="" id="globalBatchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td>院校：</td>
		                <td align="left">
		                	<select name="student.academyId" id="academyId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>	
						</td>
		                <td>招生批次：</td>
		                <td align="left">
							<span style="color: black !important;" id="batch" name="batch"></span>
							<input type="hidden" id="batchId" name="student.enrollmentBatchId" value="0"/>
						</td>
						
	              	</tr>
				  	<tr>
				  		<td>层次：</td>
		                <td>
							<select name="student.levelId" id="levelId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td>专业：</td>
		                <td align="left">
							<select name="student.majorId" id="majorId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
						<td>姓名：</td>
		                <td align="left">
		                	<input type="text" name="student.name" id="name" class="txt_box_150"/>					
						</td>
						<td>证件号：</td>
		                <td align="left">
		                	<input type="text" name="student.certNo" id="certNo" class="txt_box_150"/>					
						</td>
						
	             	</tr>
	             	<tr>
	             		<td colspan="7"></td>
		                <td align="left">
							<input type="button" class="btn_black_61"  onclick="search001();" value="查询"/>
						</td>
	             	</tr>
				</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="list_student_payment_main_ajax"
										searchCountActionpath="count_student_payment_main_ajax"
										columnsStr="#coupon;#public.operating;name;certNo;schoolName;academyenrollbatchName;levelName;majorName"
										customColumnValue="0,couponValue(id);1,operatingValue(id,status,pendingTestCounts,isExemption)"
										pageSize="10"
										params="'student.gender':-1,'result.order':'name','result.sort':'asc'"
										searchFormId="myform"
									/>
				
		</body:body>
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
	
	<div id="show_for_prompt" style="display:none">
		<table class="add_table" cellpadding="2" cellspacing="2">
			<tr>
				<td align="right">优惠券状态</td>
				<td align="left">

					<select id="usageFlag" name="usageFlag" class="txt_box_100">
						<option value="<%=Constants.POLICY_USING_STATUS_ALL %>">--全部--</option>
						<option value="<%=Constants.POLICY_USING_STATUS_APPLY %>">已申请</option>
						<option value="<%=Constants.POLICY_USING_STATUS_FALSE %>">未使用</option>
						<option value="<%=Constants.POLICY_USING_STATUS_TRUE %>">已使用</option>
					</select>
				</td>

				<td><input type="button" onclick="showusageflag()" value="查询"/></td>
			</tr>
		</table>
		<table class="gv_table_2" id="showdiscount">
			<thead>
				<th>优惠编号</th>
				<th>费用科目</th>
				<th>有效期</th>
				<th>优惠金额/比例</th>
				<th>状态</th>
				
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>	
  </body>
</html>
