<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<%@ include file="../template/common/download_excel.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>呼叫报名表</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<jc:plugin name="tab" />
		<jc:plugin name="loading" />
		<jc:plugin name="calendar" />
		<jc:plugin name="page" />
		<script type="text/javascript">
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
			//ajax回调函数   学生状态（通过学生阶段级联）
			function ajax_stustatusajax(data)
			{
				//学生状态
				jQuery("#status").empty();
			    jQuery("#status").append('<option value="0">--请选择--</option>');
			    if(data.stuStatusList!=null && data.stuStatusList.length>0)
			    {
			    	$.each(data.stuStatusList,function(){	
			    		if(STU_CALL_STATUS_YU_BAO_MING==this.id){
			    			jQuery("#status").append('<option selected="selected" value="'+this.id+'">'+this.name+'</option>'); 
			    		}else{
			    			jQuery("#status").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    		}
			    		
			    	});
			   	}
			}
			//ajax回调函数 推送人
			function ajax_push_userajax(data)
			{
				if(data!=null && data.users!=null)
				{
					var lists="<option value='0'>--请选择--</option>";
					$(data.users).each(function(){
						lists+="<option value='"+this.id+"'>"+this.fullName+"</option>"
					});
					$('#pushId').html(lists);
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
		<!-- 学习中心-->
		<a:ajax 
			pluginCode="140"
			successCallbackFunctions="ajax_stubranchajax" 
			urls="/crm/branch_all_list_for_stu_search_ajax"
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
		<!-- 推送人 -->
			<a:ajax 
				pluginCode="130" 
				successCallbackFunctions="ajax_push_userajax" 
				urls="/crm/zong_bu_user_list"
				isOnload="all"
			/>
		
		<script type="text/javascript">
			function delete_student_callback(data){
				$('#message_confirm').dialog("close");
				//刷新列表      
				changeStatus(parseInt(status));
			}
		</script>
		<!-- 下载地址 -->
		<a:ajax 
			parameters="jQuery('#search_form').serializeObject()" 
			successCallbackFunctions="excel_export_callback" 
			pluginCode="download_zip" 
			urls="/crm/export/excel_export_cc_student"
		/>
		
		<a:ajax parameters="{'student.id':student_id}" successCallbackFunctions="delete_student_callback" pluginCode="0001" urls="/crm/crm_delete_student"/>
		<script type="text/javascript">
			var status=<%=Constants.STU_CALL_STATUS_YU_BAO_MING %>;
			var sex=-1;
			var student_id=0;
			function sexValue(sex){
				return sex.getSex();
			}
			
			function statusValue(status){
				
				return status.getStudentStatus();
			}
			//删除学生
			function deleteStudent(id){
				student_id=id;
				
				$('#message_confirm').dialog("open");
				
			}
			
			function download(){
				if(confirm("您确定要导出数据吗？")){
					ajax_download_zip_1();
				}
			}
			//根据状态查询学生跟进列表
			
			function changeStatus(st){
				
				if(st==-1)
				{
					st=status;
					//$("#status").val(status);
				}
				//跟进中
				if(STU_CALL_STATUS_WU_YI_YUAN_CHUSHI==st){//无意愿
					status=st;
					$("#status").attr("disabled",true);
					$("#status").val(STU_CALL_STATUS_WU_YI_YUAN_CHUSHI);
					$("#table001").css({"display":"none"});
					$("#table002").css({"display":""});
					$("#pager001").css({"display":"none"});
					$("#pager002").css({"display":""});
					$("#table003").css({"display":"none"});
					$("#pager003").css({"display":"none"});
					$("#export_student_data_btn").css({"display":"none"});
					$(".date_class").css({"display":"none"});
					$("#pushId").val(0);
					$("#starttime").val("");
					$("#endtime").val("");
					//search002();
					refresh002();
				}else if(STU_CALL_STATUS_YU_BAO_MING==st){//预报名
					status=st;
					$("#export_student_data_btn").css({"display":"none"});
					$("#status").attr("disabled",true);
					$("#status").val(STU_CALL_STATUS_YU_BAO_MING);
					$("#table001").css({"display":""});
					$("#table002").css({"display":"none"});
					$("#table003").css({"display":"none"});
					$("#pager001").css({"display":""});
					$("#pager002").css({"display":"none"});
					$("#pager003").css({"display":"none"});
					$(".date_class").css({"display":"none"});
					$("#pushId").val(0);
					$("#starttime").val("");
					$("#endtime").val("");
					//search001();
					refresh001();
				}else if(STU_CALL_STATUS_YI_DAO_RU==st){//已分配
					status=st;
					//$("#status").val("0");
					$("#status").attr("disabled",false);
					$("#table001").css({"display":"none"});
					$("#table002").css({"display":"none"});
					$("#pager001").css({"display":"none"});
					$("#pager002").css({"display":"none"});
					$("#table003").css({"display":""});
					$("#pager003").css({"display":""});
					$(".date_class").css({"display":"block"});
					//search003();
					refresh003();
				}
			}
			function yiTuiSongCallBack003(data){
				if(data.result.recordCount>0){
					$("#export_student_data_btn").css({"display":""});
				}else{
					$("#export_student_data_btn").css({"display":"none"});
				}
			}
			$(document).ready(function(){
				//隐藏开始时间结束时间
				$(".date_class").css({"display":"none"});
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
				
				
				//刷新列表      首次加载页面
				//changeStatus(parseInt(status));
				
				$("#menu>li>a").click(function(){
						$(this).attr("class","current");
						var selObj=this;
							$("#menu>li>a").each(function(){
									if(this!=selObj){
										$(this).attr("class","");
									}
							});
					});
					//隐藏分页控件
					$("#table002").css({"display":"none"});
					$("#table003").css({"display":"none"});
			});
			function studentDataSourceValue(id){
				return id.getStudentDatasource();
			}
			/*function alert_message(){
				if(confirm("一次只能导出3000条数据!确定要导出吗？")){
					return true;
				}
				return false;
			}*/
		</script>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="呼叫报名表">
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<div id="message_confirm" style="display:none">
						<div>确认执行该操作！</div>
				</div>
				
				<div>
					<ul id="menu">
						<li><a href="#" title="" onclick="changeStatus(<%=Constants.STU_CALL_STATUS_YU_BAO_MING %>);" class="current">未处理</a></li>
						<li><a href="#" title="" onclick="changeStatus(<%=Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI %>);">无意愿(不推送)</a></li>
						<li><a href="#" title="" onclick="$('#status').val('0');changeStatus(<%=Constants.STU_CALL_STATUS_YI_DAO_RU %>);">已推送</a></li>
					</ul>
				</div>	
				
				<form id="search_form">
					<table class="add_table" cellpadding="2" cellspacing="2" border="0">
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
								<td align="right">性别：</td>
								<td align="left">
									<select id="sex" name="student.gender" class="txt_box_100">
										<option value="-1" selected="selected">--请选择--</option>
										<option value="1">男</option>
										<option value="0">女</option>
									</select>
								</td>
								<td align="right">学生状态：</td>
								<td align="left">
									<select id="status" name="student.status" class="txt_box_130" disabled="disabled">
										<option value="0">--请选择--</option>
									</select>
								</td>
								
						</tr>
							<tr>
								
								
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
								<td align="right">
									<div class="date_class">开始时间：</div>
									
								</td>
								<td align="left">
									<div class="date_class">
									<input id="starttime" class="Wdate" type="text"
										name="student.pushStartDate" value=""
										onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
										size="17">
									</div>
								</td>
								<td align="right">
									<div class="date_class">
									结束时间：
									</div>
								</td>
								<td align="left">
									<div class="date_class">
									<input id="endtime" class="Wdate" type="text"
										name="student.pushEndDate" value="" size="17"
										onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})">
									</div>
								</td>
								<td align="right">
									<div class="date_class">
									推送人：
									</div>
								</td>
								<td align="left">
									<div class="date_class">
									<select id="pushId" name="student.pushId" class="txt_box_130">
										<option value="0">--请选择--</option>
									</select>
									</div>
								</td>
							</tr>
							<tr>
								
								<td align="right" colspan="9"></td>
								<td align="left">
									<input  type="button" class="btn_black_61" onclick="changeStatus(-1)" value="查询"/>
									<input  id="export_student_data_btn" style="display: none;" type="button" onclick="download();" class="btn_black_61"  value="导出"/>
									
								</td>
							</tr>
							
							
							
					</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="crm"
										isonLoad="true"
										searchListActionpath="crm_student_list"
										searchCountActionpath="crm_student_count"
										columnsStr="name;mobile;status;branchName;gender;enrollmentSourceName1;enrollmentWayName;schoolName;academyenrollbatchName;levelName;majorName"
										customColumnValue="2,statusValue(status);4,sexValue(gender)"
										pageSize="10"
										
										view="http,/crm/view_home,studentId,id,_blank"
										update="http,/crm/call_home,studentId,id,_blank"
										searchFormId="search_form"
										
										params="'student.callStatus':-1,'student.status':STU_CALL_STATUS_YU_BAO_MING,'student.studentDataSource':STU_SOURCE_CC"
									/>
									<page:plugin 
										isonLoad="false"
										pluginCode="002"
										il8nName="crm"
										searchListActionpath="crm_student_list"
										searchCountActionpath="crm_student_count"
										columnsStr="name;mobile;status;branchName;gender;enrollmentSourceName1;enrollmentWayName;schoolName;academyenrollbatchName;levelName;majorName"
										customColumnValue="2,statusValue(status);4,sexValue(gender)"
										pageSize="10"
										
										view="http,/crm/view_home,studentId,id,_blank"
										update="http,/crm/call_home,studentId,id,_blank"
										searchFormId="search_form"
										params="'student.callStatus':-1,'student.status':STU_CALL_STATUS_WU_YI_YUAN_CHUSHI,'student.studentDataSource':STU_SOURCE_CC"
									/>
									<page:plugin 
										pluginCode="003"
										isonLoad="false"
										il8nName="crm"
										searchListActionpath="crm_student_list"
										searchCountActionpath="crm_student_count"
										columnsStr="name;mobile;status;branchName;gender;enrollmentSourceName1;enrollmentWayName;schoolName;academyenrollbatchName;globalBatchName;levelName;majorName;pushName;pushDate;followUpName;latestFollowUpDate"
										customColumnValue="2,statusValue(status);4,sexValue(gender)"
										pageSize="10"
										searchFormId="search_form"
										view="http,/crm/view_home,studentId,id,_blank"
										params="'student.callStatus':-1,'student.startStatusId':STU_CALL_STATUS_YU_BAO_MING,'student.endStatusId':STU_CALL_STATUS_WU_YI_YUAN_CHUSHI,'student.studentDataSource':STU_SOURCE_CC"
										countCallback="yiTuiSongCallBack"
									/>
					
				</body:body>
		
	</body>

</html>