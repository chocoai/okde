<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生账户（总部）</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<jc:plugin name="loading" />
		<jc:plugin name="page" />		
		<script type="text/javascript">
			$(document).ready(function()
			{
				//选择中心事件
				jQuery('#branchId').change(function()
				{
					ajax_130_1();//跟进人
				});
				
			});
			
			
			//分页列表显示
			function sexValue(sex)
			{
				return sex.getSex();
			}
			function statusValue(status)
			{
				return status.getStudentStatus();
			}
			function getoperation(id)
			{
				return '<a  target="_blank"  href="<s:url value="view_student_account_management"  />?id='+id+'&tab=1">查看账户</a>';
			}
				
			//ajax回调函数   学生共有的查询条件
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
			   	//全局批次
			   	jQuery("#glopici").empty();
			    jQuery("#glopici").append('<option value="0">--请选择--</option>');
			    if(data.globalBatchList!=null && data.globalBatchList.length>0)
			    {
			    	$.each(data.globalBatchList,function(){	
			    		jQuery("#glopici").append('<option value="'+this.id+'">'+this.title+'</option>'); 
			    	});
			   	}
			   	//层次
			   	jQuery("#cengci").empty();
			    jQuery("#cengci").append('<option value="0">--请选择--</option>');
			    if(data.levelList!=null && data.levelList.length>0)
			    {
			    	$.each(data.levelList,function(){	
			    		jQuery("#cengci").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			   	//基础专业
			   	jQuery("#jczhuanye").empty();
			    jQuery("#jczhuanye").append('<option value="0">--请选择--</option>');
			    if(data.baseMajorList!=null && data.baseMajorList.length>0)
			    {
			    	$.each(data.baseMajorList,function(){	
			    		jQuery("#jczhuanye").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			   	//数据来源
			   	jQuery("#studentDataSource").empty();
			    jQuery("#studentDataSource").append('<option value="0">--请选择--</option>');
			    if(data.studentDataSourceList!=null && data.studentDataSourceList.length>0)
			    {
			    	$.each(data.studentDataSourceList,function(){	
			    		jQuery("#studentDataSource").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			   	//市场途径
			   	jQuery("#way").empty();
			    jQuery("#way").append('<option value="0">--请选择--</option>');
			    if(data.enrollmentWayList!=null && data.enrollmentWayList.length>0)
			    {
			    	$.each(data.enrollmentWayList,function(){	
			    		jQuery("#way").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			   	//招生途径
			   	jQuery("#source").empty();
			    jQuery("#source").append('<option value="0">--请选择--</option>');
			    if(data.enrollmentSourceList!=null && data.enrollmentSourceList.length>0)
			    {
			    	$.each(data.enrollmentSourceList,function(){	
			    		jQuery("#source").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			   	
			}	
			
			
			
			//ajax回调函数   跟进人（通过学习中心级联）
			function ajax_stufollowupajax(data)
			{
				// 跟进人
				jQuery("#followUpId").empty();
			    jQuery("#followUpId").append('<option value="0">--请选择--</option>');
			    if(data.userList!=null && data.userList.length>0)
			    {
			    	$.each(data.userList,function(){	
			    		jQuery("#followUpId").append('<option value="'+this.id+'">'+this.fullName+'</option>'); 
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
		</script>
		
		<!-- 学生共有的查询条件-->
		<a:ajax 
			pluginCode="110"
			successCallbackFunctions="ajax_stusearchallajax" 
			urls="/crm/for_stu_search_all_ajax"
			isOnload="all" 			
		/>
		
		
		<!-- 跟进人（通过学习中心级联）-->
		<a:ajax 
			pluginCode="130"
			successCallbackFunctions="ajax_stufollowupajax" 
			parameters="{branchId:jQuery('#branchId').val()}"
			urls="/crm/stu_followup_list_for_stu_search_ajax"
			isOnload="all" 			
		/>
		<!-- 学习中心-->
		<a:ajax 
			pluginCode="140"
			successCallbackFunctions="ajax_stubranchajax" 
			urls="/crm/branch_all_list_for_stu_search_ajax"
			isOnload="all" 			
		/>

	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="学生账户（总部）">
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="search_form">
					<table class="add_table" cellpadding="2" cellspacing="2">
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
							  <td align="right">全局批次：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.glbtach" id="glopici">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
							  <td align="right">层次：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.levelId" id="cengci">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
							  <td align="right">基础专业：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.glmajor" id="jczhuanye">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
						</tr>
						<tr>
								<td align="right">数据来源：</td>
								<td align="left">
									<select id="studentDataSource" name="student.studentDataSource" class="txt_box_130" >
										<option value="0">--请选择--</option>
									</select>
								</td>
								<td align="right">招生途径：</td>
								<td align="left">
									<select id="source" name="student.enrollmentSource" class="txt_box_130">
										<option value="0">--请选择--</option>
									</select>
								</td>
								<td align="right">市场途径：</td>
								<td align="left">
									<select id="way" name="student.enrollmentWay" class="txt_box_130">
										<option value="0">--请选择--</option>
									</select>
								</td>
								<td align="right">监控状态：</td>
								<td align="left">
									<select class="txt_box_130" id="jiankong" name="student.monitorStatus">
										<option value="-1">--请选择--</option>
										<option value="<%=Constants.STU_MONITOR_STATUS_WEI_JIAN_KONG %>">未监控</option>
										<option value="<%=Constants.STU_MONITOR_STATUS_JIAN_KONG_ZAI_SHENG_QIN %>">监控再申请</option>
										<option value="<%=Constants.STU_MONITOR_STATUS_JIAN_KONG_YI_CHENG_GONG %>">已监控已成功</option>
										<option value="<%=Constants.STU_MONITOR_STATUS_JIAN_KONG_WEI_CHENG_GONG %>">已监控未成功</option>
									</select>
								</td>
								<td align="right">学费状态：</td>
								<td align="left">
									<select class="txt_box_130" id="jiaofei" name="student.tuitionStatus">
										<option value="-1">--请选择--</option>
										<option value="<%=Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI %>">未缴学费</option>
										<option value="<%=Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI %>">首次缴费</option>
										<option value="<%=Constants.STU_TUITION_STATUS_DUO_CI_JIAO_FEI %>">多次缴费</option>
										<option value="<%=Constants.STU_TUITION_STATUS_JIAO_FEI_WAN_CHENG %>">缴费完成</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">姓名：</td>
								<td align="left">
									<input  name="student.name" class="txt_box_130" type="text" value="" />
								</td>
								<td align="right">证件号：</td>
								<td align="left">
									<input  name="student.certNo" class="txt_box_130" type="text" />
								</td>
								<td align="right">手机/座机：</td>
								<td align="left">
									<input name="student.phone" class="txt_box_130" type="text" />
								</td>
								<td align="right">学号：</td>
								<td align="left">
									<input  name="student.number" class="txt_box_130" type="text" />
								</td>
								<td align="right">跟进人：</td>
								<td align="left">
									<select id="followUpId" class="txt_box_130"  name="student.followUpId">
										<option value="0">--请选择--</option>
									</select>
								</td>
							</tr>
							<tr>
								
								<td align="right" colspan="9"></td>
								<td align="left">
									<input  type="button" class="btn_black_61" onclick="search001()" value="查询"/>
								</td>
							</tr>
						
					</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="list_student_acount_student_page_ajax"
										searchCountActionpath="count_student_acount_student_page_ajax"
										columnsStr="name;certNo;status;gender;schoolName;academyenrollbatchName;levelName;majorName;#operation"
										customColumnValue="2,statusValue(status);3,sexValue(gender);8,getoperation(id)"
										searchFormId="search_form"
										
										params="'result.order':'name','result.sort':'asc'"
									/>
					
			</body:body>
	</body>

</html>