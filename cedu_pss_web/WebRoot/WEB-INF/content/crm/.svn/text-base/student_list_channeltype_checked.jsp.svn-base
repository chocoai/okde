<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>来源复核</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<jc:plugin name="loading" />
		<jc:plugin name="tab" />
		<jc:plugin name="page" />
		<script type="text/javascript">
			$(document).ready(function()
				{
					//选择中心事件
					jQuery('#branchId').change(function()
					{
						ajax_130_1();//跟进人
						if(jQuery('#source').val()!=0)
						{
							ajax_160_1();//合作方
						}
					});
					//选择学生阶段事件
					jQuery('#jieduan').change(function()
					{
						ajax_120_1();//学生状态级联
					});
					//选择招生途径事件
					jQuery('#source').change(function()
					{
						ajax_160_1();//合作方
					});
				});
				
				
				//分页列表显示（只在未监控页签上可批量复核）
				function nameValue(name,isChannelTypeChecked,id){
					if(isChannelTypeChecked!=STUDENT_CHANNEL_TYPE_CHECKED_FALSE){
						isPageOperating(id,"001","checkbox");
					}
					//else{
					//	if(jQuery("#lastMonitorResult").val()!=0){
					//		isPageOperating(id,"001","checkbox");
					//	}
					//}
					return name;
				}
				function sexValue(sex)
				{
					return sex.getSex();
				}
				function statusValue(status,rebateFpdCount,id)
				{
					//学生有招生返款单时不让修改来源
					if(rebateFpdCount>0)
					{
						isPageOperating(id,"001","update");
					}
					return status.getStudentStatus();
				}
				//修改查询条件
				function changeChecked(id,monitorStatus,lastMonitorResult)
				{
					jQuery("#isChannelTypeChecked").val(id);
					jQuery("#jiankong").val(monitorStatus);
					jQuery("#lastMonitorResult").val(lastMonitorResult);
					if(lastMonitorResult>0)
					{
						jQuery("#mrdiv").hide();
						jQuery("#mrspan").html("来源监控");
						jQuery("#conmenu").hide();//显示、隐藏批量复核链接
					}
					else
					{
						jQuery("#mrdiv").show();
						jQuery("#mrspan").html("");
						//显示、隐藏批量复核链接
						if(id==STUDENT_CHANNEL_TYPE_CHECKED_TRUE)
						{
							jQuery("#conmenu").hide();
						}
						else
						{
							jQuery("#conmenu").show();
						}
					}
					search001();
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
				   	/**jQuery("#way").empty();
				    jQuery("#way").append('<option value="0">--请选择--</option>');
				    if(data.enrollmentWayList!=null && data.enrollmentWayList.length>0)
				    {
				    	$.each(data.enrollmentWayList,function(){	
				    		jQuery("#way").append('<option value="'+this.id+'">'+this.name+'</option>'); 
				    	});
				   	}**/
				    $("#way").html("");
	   				var wayStr="<option value='" + 0 + "'>--请选择--</option>";
					$.each(data.enrollmentWaysMap,function(key,value){
							wayStr+="<optgroup label='"+key+"'>";
							$(this).each(function(){
									wayStr+="<option value='" + this.id + "'>" + this.name + "</option>";
							});
							wayStr+="</optgroup>";
					});
					$("#way").html(wayStr);
				   	//招生途径
				   	jQuery("#source").empty();
				    jQuery("#source").append('<option value="0">--请选择--</option>');
				    if(data.enrollmentSourceList!=null && data.enrollmentSourceList.length>0)
				    {
				    	$.each(data.enrollmentSourceList,function(){	
				    		jQuery("#source").append('<option value="'+this.id+'">'+this.name+'</option>'); 
				    	});
				   	}
				   	//学生阶段
				   	jQuery("#jieduan").empty();
				    jQuery("#jieduan").append('<option value="0">--请选择--</option>');
				    if(data.stuStatusStageList!=null && data.stuStatusStageList.length>0)
				    {
				    	$.each(data.stuStatusStageList,function(){	
				    		jQuery("#jieduan").append('<option value="'+this.stageCode+'">'+this.stage+'</option>'); 
				    	});
				   	}
				}	
				
				//ajax回调函数   学生状态（通过学生阶段级联）
				function ajax_stustatusajax(data)
				{
					//学生状态
					jQuery("#status").empty();
				    jQuery("#status").append('<option value="0">--请选择--</option>');
				    if(data.stuStatusList!=null && data.stuStatusList.length>0)
				    {
				    	$.each(data.stuStatusList,function(){	
				    		jQuery("#status").append('<option value="'+this.id+'">'+this.name+'</option>'); 
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
				//删除学生
				function deleteStudent(id){
					student_id=id;
					
					$('#message_confirm').dialog("open");
					
				}
				function delete_student_callback(data){
					
					$('#message_confirm').dialog("close");
					//成功删除
					if(data.errorCode=="200001"){
						//刷新列表      
						refresh001();
					}else if(data.errorCode=="200002"){//该状态不能删除
						alert("不能删除该学生！");
					}else if(data.errorCode=="500"){//服务器异常
						alert("服务器异常！");
					}else if(data.errorCode=="404"){//已删除
						alert("该学生不存在或者已删除！");
					}
					
				}
				//监控状态
				function monitorStatusValue(monitorStatus){
					return monitorStatus.getStudentJianKongZhuangTai();
				}
				
				//判断批量复核
				function batchChecked()
				{
					if(getCheckedValues001()==null || getCheckedValues001()=="")
					{
						alert('请选择学生！');
					}
					else
					{
						if (confirm('确定批量复核？')){ 
							ajax_150_1();
						}
					}
				}
				
				//批量复核回调函数
				function ajax_updatebatch(){
					refresh001();
					alert('批量复核成功！');
				}
				
				//ajax回调函数   合作方级联
				function ajax_stuchannelajax(data)
				{
					// 合作方
					jQuery("#channelId").empty();
					jQuery("#channelId").append('<option value="0">--请选择--</option>');
					if(data.channelList!=null && data.channelList.length>0)
					{
					    $.each(data.channelList,function(){	
					    	jQuery("#channelId").append('<option value="'+this.id+'">'+this.name+'</option>'); 
					    });
					}
				}
			</script>
			<a:ajax parameters="{'student.id':student_id}" successCallbackFunctions="delete_student_callback" pluginCode="0001" urls="/crm/crm_delete_student"/>
			<!-- 学生共有的查询条件-->
			<a:ajax 
				pluginCode="110"
				successCallbackFunctions="ajax_stusearchallajax" 
				urls="/crm/for_stu_search_all_ajax"
				isOnload="all" 			
			/>
			<!-- 学生状态（通过学生阶段级联）-->
			<a:ajax 
				pluginCode="120"
				successCallbackFunctions="ajax_stustatusajax" 
				parameters="{stuStatusStage:jQuery('#jieduan').val()}"
				urls="/crm/stu_status_list_for_stu_search_ajax"
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
			
			<!-- 批量复核 -->
			<a:ajax 
				parameters="{stuIds:getCheckedValues001()}" 
				successCallbackFunctions="ajax_updatebatch" 
				pluginCode="150" 
				urls="crm/upate_batch_student_is_channel_type_checked_ajax"
			/>
			<!-- 合作方（通过学习中心和招生途径级联）-->
			<a:ajax 
				pluginCode="160"
				successCallbackFunctions="ajax_stuchannelajax" 
				parameters="{branchId:jQuery('#branchId').val(),channelType:jQuery('#source').val()}"
				urls="/crm/channel_list_for_stu_search_ajax"		
			/>
			<script type="text/javascript">
					$(document).ready(function(){
						//加载学生状态
						$('#message_confirm').dialog({
							autoOpen:false,
							modal:true,
							draggable:false,
							resizable:false,
							title:'提示',
							width: 260,
							height: 125,
							buttons: {
									'确认': function() { 
											ajax_0001_1();
									}, 
									'取消': function() { 
											$(this).dialog("close"); 
									} 
							}
						});
						//选项卡
						$("#menu>li>a").click(function(){
						$(this).attr("class","current");
							var selObj=this;
							$("#menu>li>a").each(function(){
								if(this!=selObj){
									$(this).attr("class","");
								}
							});
						});
						
					});
			</script>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="来源复核">
			<html:a text="批量复核" icon="update" onclick="batchChecked()"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<div id="message_confirm" style="display:none">
						<div>确认执行该操作！</div>
				</div>
				<div>
					<ul id="menu">
						<li><a href="#" title="" onclick="changeChecked(<%=Constants.STUDENT_CHANNEL_TYPE_CHECKED_FALSE %>,-1,0);" class="current">未复核</a></li>
						<li><a href="#" title="" onclick="changeChecked(<%=Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE %>,-1,0);">已复核</a></li>	
						<li><a href="#" title="" onclick="changeChecked(<%=Constants.STUDENT_CHANNEL_TYPE_CHECKED_FALSE %>,<%=Constants.STU_MONITOR_STATUS_JIAN_KONG_WEI_CHENG_GONG %>,<%=Constants.STU_MONITOR_RESULTS_SOURCE_ID %>);">回访来源不合格待复核</a></li>						
					</ul>
				</div>
				<form id="search_form">
					<input type="hidden" name="student.isChannelTypeChecked" id="isChannelTypeChecked" value="<%=Constants.STUDENT_CHANNEL_TYPE_CHECKED_FALSE %>"/>
					<!-- input type="hidden" name="student.lastMonitorResult" id="lastMonitorResult" value="0"/> -->
					
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
								<td align="right">学生阶段：</td>
								<td align="left">
									<select id="jieduan" name="stuStage" class="txt_box_130" >
										<option value="0">--请选择--</option>
									</select>
								</td>
								<td align="right">学生状态：</td>
								<td align="left">
									<select id="status" name="student.status" class="txt_box_130">
										<option value="0">--请选择--</option>
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
								<td align="right">最新监控结果：</td>
								<td align="left">
									<div id="mrdiv">
										<s:select list="monitorResultslist" listValue="name" listKey="id" cssClass="txt_box_130" name="student.lastMonitorResult" id="lastMonitorResult"  headerKey="0" headerValue="--请选择--" ></s:select>
									</div>
									<span id="mrspan" style="color:black"></span>
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
								<td align="right">合作方：</td>
								<td align="left">
									<select id="channelId" class="txt_box_130"  name="student.channelId">
										<option value="0">--请选择--</option>
									</select>
								</td>
								<td align="right">市场途径：</td>
								<td align="left">
									<select id="way" name="student.enrollmentWay" class="txt_box_130">
										<option value="0">--请选择--</option>
									</select>
								</td>
								
								<td align="right"></td>
								<td align="left">
									<input  type="button" class="btn_black_61" onclick="search001()" value="查询"/>
								</td>
							</tr>		
						</table>
					</form>
									<page:plugin 
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="list_student_channeltype_checked_ajax"
										searchCountActionpath="count_student_channeltype_checked_ajax"
										columnsStr="name;certNo;status;gender;monitorStatus;enrollmentSourceName1;branchName;schoolName;academyenrollbatchName;levelName;majorName"
										customColumnValue="0,nameValue(name,isChannelTypeChecked,id);2,statusValue(status,rebateFpdCount,id);3,sexValue(gender);4,monitorStatusValue(monitorStatus)"
										
										pageSize="10"
										
										isChecked="true"
										checkboxValue="id"
										
										view="http,/crm/view_home,studentId,id,_blank"
										update="http,/crm/update_student_channeltype_checked,studentId,id,_blank"
										searchFormId="search_form"
										params="'result.order':'name','result.sort':'asc'"
									/>
					
			</body:body>
	</body>

</html>