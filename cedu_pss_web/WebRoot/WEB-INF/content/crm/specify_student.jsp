<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>任务分配</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<jc:plugin name="page" />
		<jc:plugin name="loading" />
		<script type="text/javascript">
			//招生途径
			function crm_student_datasource_callback(data){
					$("#source1").html("");
	               	$("<option value='" + 0 + "'>请选择招生途径</option>").appendTo($("#source1"));
	               				
					$(data.enrollmentSources).each(function(){
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#source1"));
					});
					
					$("#source2").html("");
	               	$("<option value='" + 0 + "'>请选择招生途径</option>").appendTo($("#source2"));
	               				
					$(data.enrollmentSources).each(function(){
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#source2"));
					});
			}
			//院校
			function crm_school_callback(data){
					$("#school1").html("");
               		$("<option value='" + 0 + "'>请选择院校</option>").appendTo($("#school1"));
               				
					$(data.academysAcademies).each(function(){
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#school1"));
					});
					$("#school2").html("");
               		$("<option value='" + 0 + "'>请选择院校</option>").appendTo($("#school2"));
               				
					$(data.academysAcademies).each(function(){
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#school2"));
					});
					
					//招生途径
					ajax_0001_1();
					search001();
					search002();
			}
			//同中心用户
			function userList(data){		
					$(data.users).each(function(){
							$("<option value='" + this.id + "'>" + this.fullName + "</option>").appendTo($("#user"));
					});
			}
			//分配用户
			function crm_distribution_student_callback(data){
				search001();
				search002();
			}
			//转移用户
			function crm_shift_student_callback(data){
				search001();
			}
			//ajax回调函数   数据来源
			function ajax_studatasource(data)
			{
				//数据来源
				jQuery("#studentDataSource1").empty();
			    jQuery("#studentDataSource1").append('<option value="0">--请选择--</option>');
			    jQuery("#studentDataSource2").empty();
			    jQuery("#studentDataSource2").append('<option value="0">--请选择--</option>');
			    if(data.studentDataSourceList!=null && data.studentDataSourceList.length>0)
			    {
			    	$.each(data.studentDataSourceList,function(){	
			    		jQuery("#studentDataSource1").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    		jQuery("#studentDataSource2").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			}
		</script>
		<!-- 查询下拉菜单 -->
		<a:ajax successCallbackFunctions="crm_student_datasource_callback;crm_school_callback;userList" pluginCode="0001" urls="/crm/student_way_list;crm/academys_academie_list;crm/crm_user_list" isOnload="2;3"/>
		<!-- 分配或转移用户 -->
		<a:ajax parameters="{'userId':userId,'studentIds':getCheckedValues002()};{'shiftUserId':shiftUserId,'studentIds':getCheckedValues001()}" successCallbackFunctions="crm_distribution_student_callback;crm_shift_student_callback" pluginCode="0002" urls="/crm/crm_distribution_student;/crm/crm_shift_student"/>
		<!-- 数据来源-->
		<a:ajax 
			pluginCode="110"
			successCallbackFunctions="ajax_studatasource" 
			urls="/crm/data_source_all_list_for_stu_search_ajax"
			isOnload="all" 			
		/>
		<script type="text/javascript">
			var userId=parseInt("<%=request.getParameter("userId") %>");
			var shiftUserId=0;
			var orgId=parseInt('${session.userTicket.orgId}');
			//分配
			function distribution(){
				if(getCheckedValues002()==null||getCheckedValues002()==""){
					return;
				}
				ajax_0002_1();
			}
			//转移
			function shift(){
				if(getCheckedValues001()==null||getCheckedValues001()==""){
					return;
				}
				if($("#user").val()==null||$("#user").val()==""){
					return;
				}
				shiftUserId=parseInt($("#user").val());
				ajax_0002_2();
			}
			$(document).ready(function(){
				//加载学生状态
			});
			function statusValue(status){
				return status.getStudentStatus();
			}
			//呼叫时间
			function showdate(createDate)
			{
				
				if(createDate!=null && createDate.length>10)
				{
					return createDate.substring(0,10)
				}
				return createDate;
			}
			function getsourceid(studentDataSource)
			{
				if(studentDataSource==STU_SOURCE_LC)
				{
					return '学习中心';
				}else if(studentDataSource==STU_SOURCE_NP)
				{
					return '网络报名';
				}else if(studentDataSource==STU_SOURCE_CC)
				{
					return '呼叫中心';
				}
				else if(studentDataSource==STU_SOURCE_HS)
				{
					return '历史数据';
				}
				else
				{
					return '--';
				}
				
			}
			
		</script>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="任务分配">
			<html:a text="关闭" icon="return" onclick="window.close();" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
				 <table style="width:100%;border:0px;">
					<tr>
						<td style="50%"  valign="top">
							<table class="gv_table_2">
						  		<tr>
								 	<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" /></th>
								 	<th style="text-align:left; font-weight:bold;">已分配学生列表</th>
								 	<th style="text-align:right; font-weight:bold;padding-right: 10px;">
										转移到
										<select id="user" name="user" class="txt_box_100" >
										</select>
										<a href="javascript:shift();"><img src="<%=Constants.WEB_IMAGES %>/images/icon_up.gif" border="0px" width="12" height="12" />转移</a>
				
									</th>
								</tr>
							</table>
						
							<table class="add_table">
								<tr>
								   	<td align="right">姓名：</td>
									<td align="left">
										<input id="name1" name="name1" type="text"  class="txt_box_100" />
									</td>
									<td align="right">招生途径：</td>
									<td align="left">
										<select id="source1" name="student.enrollmentSource" class="txt_box_150">
											<option value="0">请选择招生途径</option>
										</select>
									</td>
									<td></td>
								</tr>
								<tr>	
									<td align="right">数据来源：</td>
									<td align="left">
										<select id="studentDataSource1" name="student.studentDataSource" class="txt_box_150">
											<option value="0">请选择数据来源</option>
											
										</select>
									</td>
									<td align="right">意向院校：</td>
									<td align="left">
										<select id="school1" class="txt_box_150" name="school">
											<option value="0">请选择院校</option>
										</select>
									</td>
									<td align="left">
										<input type="button" class="btn_black_61" onclick="search001();" value="查询"/>
									</td>
								</tr>
							</table>
								<page:plugin 
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="crm_specify_studentk_list"
										searchCountActionpath="crm_specify_student_count"
										columnsStr="name;sourceid;status;createdTime"
										customColumnValue="1,getsourceid(studentDataSource);2,statusValue(status);3,showdate(createDate)"
										pageSize="10"
										isChecked="true"
										params="'student.callStatus':-1,'student.userId':userId,'student.gender':parseInt(-1),'student.academyId':$('#school1').val(),'student.enrollmentSource':$('#source1').val(),'student.name':$('#name1').val(),'student.studentDataSource':$('#studentDataSource1').val()"
										isonLoad="false"
										isOrder="false"
									/>
					</td>
					<td style="50%" valign="top">

						<table class="gv_table_2">
					  		<tr>
							 	<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" /></th>
							 	<th style="text-align:left; font-weight:bold;">待分配学生列表</th>
							 	<th style="text-align:right; font-weight:bold;padding-right: 10px;">
									<a href="javascript:distribution();"> <img src="<%=Constants.WEB_IMAGES %>/images/icon_add.gif" border="0px" width="12" height="12" />添加</a>
								</th>
			
							</tr>
						</table>
						<table class="add_table">
						   <tr>
							   	<td align="right">姓名：</td>
								<td align="left">
									<input id="name" name="name" type="text"  class="txt_box_100" />
								</td>
								<td align="right">招生途径：</td>
								<td align="left">
									<select id="source2" name="student.enrollmentSource" class="txt_box_150">
											<option value="0">请选择招生途径</option>
									</select>
								</td>
								<td></td>
							</tr>
							<tr>	
								<td align="right">数据来源：</td>
									<td align="left">
										<select id="studentDataSource2" name="student.studentDataSource" class="txt_box_150">
											<option value="0">请选择数据来源</option>
											
										</select>
									</td>
								<td align="right">意向院校：</td>
	
								<td align="left">
									<select id="school2" class="txt_box_150" name="school">
											<option value="0">请选择院校</option>
									</select>
								</td>
								<td align="left">
									<input type="button" class="btn_black_61" onclick="search002();" value="查询"/>
								</td>
							
							</tr>
						</table>
								<page:plugin 
										pluginCode="002"
										il8nName="crm"
										searchListActionpath="weifenpei_student_list"
										searchCountActionpath="weifenpei_student_count"
										columnsStr="name;sourceid;status;createdTime"
										customColumnValue="1,getsourceid(studentDataSource);2,statusValue(status);3,showdate(createDate)"
										pageSize="10"
										params="'student.userId':-1,'student.name':$('#name').val(),'student.callStatus':-1,'student.status':STU_CALL_STATUS_YI_DAO_RU,'student.gender':parseInt(-1),'student.academyId':$('#school2').val(),'student.enrollmentSource':$('#source2').val(),'student.studentDataSource':$('#studentDataSource2').val()"
										isChecked="true"
										isonLoad="false"
										isOrder="false"
									/>
					</td>
				</tr>
			</table>
					
		</body:body>
	</body>

</html>