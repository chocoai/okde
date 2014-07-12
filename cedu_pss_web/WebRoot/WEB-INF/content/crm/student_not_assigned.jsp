<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>未分配学生</title>
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
				
				search002();
			}
			//转移用户
			function crm_shift_student_callback(data){
				search001();
			}
		</script>
		<!-- 查询下拉菜单 -->
		<a:ajax successCallbackFunctions="crm_student_datasource_callback;crm_school_callback;userList" pluginCode="0001" urls="/crm/student_way_list;crm/academys_academie_list;crm/crm_user_list" isOnload="2;3"/>
		<!-- 分配或转移用户 -->
		<a:ajax parameters="{'userId':userId,'studentIds':getCheckedValues002()};{'shiftUserId':shiftUserId,'studentIds':getCheckedValues001()}" successCallbackFunctions="crm_distribution_student_callback;crm_shift_student_callback" pluginCode="0002" urls="/crm/crm_distribution_student;/crm/crm_shift_student"/>
		
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
			function sexValue(sex){
				return sex.getSex();
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
				}else
				{
					return '--';
				}
				
			}
			
		</script>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="未分配学生">
			<html:a text="返回" icon="return" onclick="history.go(-1);" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
						<table class="gv_table_2">
					  		<tr>
							 	<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" /></th>
							 	<th style="text-align:left; font-weight:bold;">待分配学生列表</th>
							</tr>
						</table>
						<table class="add_table">
						   <tr>
						   <td align="right">姓名：</td>
							<td align="left">
								<input id="name" name="name" type="text"  class="txt_box_150" />
							</td>
							<td align="right">招生途径：</td>
							<td align="left">
								<select id="source2" name="student.enrollmentSource" class="txt_box_150">
										<option value="0">请选择招生途径</option>
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
							<td></td>
						  </tr>
						</table>
								<page:plugin 
										pluginCode="002"
										il8nName="crm"
										searchListActionpath="crm_student_list"
										searchCountActionpath="crm_student_count"
										columnsStr="name;sourceid;status;gender;schoolName;academyenrollbatchName;levelName;majorName;createdTime"
										customColumnValue="1,getsourceid(studentDataSource);2,statusValue(status);3,sexValue(gender)"
										pageSize="10"
										params="'student.name':$('#name').val(),'student.branchId':-1,'student.callStatus':0,'student.status':STU_CALL_STATUS_YI_DAO_RU,'student.gender':parseInt(-1),'student.academyId':$('#school2').val(),'student.enrollmentSource':$('#source2').val(),'student.branchId':orgId"
										isonLoad="false"
									/>
				
		</body:body>
	</body>

</html>