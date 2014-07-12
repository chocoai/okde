<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>网上报名表</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<jc:plugin name="loading" />
		<jc:plugin name="page" />
	<script type="text/javascript">
			function delete_student_callback(data){
				$('#message_confirm').dialog("close");
				search001();
			}
		</script>
		
		<a:ajax parameters="{'student.id':student_id}" successCallbackFunctions="delete_student_callback" pluginCode="0001" urls="/crm/crm_delete_student"/>
	<script type="text/javascript">
		var sex=-1;
		var student_id=0;
		function sexValue(sex){
			return sex.getSex();
		}
		function statusValue(status){
			return status.getStudentStatus();
		}
		function levelValue(preferLevel){
			return preferLevel.getLevel();
		}
		//删除学生
			function deleteStudent(id){
				student_id=id;
				
				$('#message_confirm').dialog("open");
				
			}
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
			});
	</script>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="网上报名表">
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
					<div id="message_confirm" style="display:none">
						<div>确认执行该操作！</div>
					</div>
					<table class="add_table">
					   <tr>
					    <td align="right">学号：</td>
		                <td align="right" style="width:100px;">
							<input type="text" class="txt_box_100" name="number" id="number"/>
						</td>
					   	 <td align="right">姓名：</td>
		                <td align="right" style="width:100px;">
							<input type="text" class="txt_box_100" id="name"/>
						</td>
						 <td align="right">证件号：</td>
		                <td align="right" style="width:100px;">
							<input type="text" class="txt_box_100" id="cert_no"/>
						</td>
						 <td align="right">性别：</td>
		                <td align="right" style="width:100px;">
							<select id="sex" class="txt_box_100" onchange="sex=this.value;">
										<option selected="selected" value="-1">--请选择--</option>
										<option value="<%=Constants.SEX_MALE %>">男</option>
										<option value="<%=Constants.SEX_FAMALE %>">女</option>
							</select>	
						</td>
		                <td align="right">
							<input type="button" class="btn_black_61" value="查询" onclick="search001()"/>
						</td>
						<td></td>
		              </tr>
		            </table>
									<page:plugin 
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="crm_student_list"
										searchCountActionpath="crm_student_count"
										columnsStr="name;status;gender;preferAcademy;preferLevel;preferMajor"
										customColumnValue="1,statusValue(status);2,sexValue(gender);4,levelValue(preferLevel)"
										pageSize="10"
										delete="json,deleteStudent,id"
										view="http,/crm/view_home,studentId,id,_blank"
										update="http,/crm/call_view,studentId,id,_blank"
										params="'student.certNo':$('#cert_no').val(),'student.status':STU_CALL_STATUS_YU_BAO_MING,'student.studentDataSource':STU_SOURCE_NP,'student.number':$('#number').val(),'student.name':$('#name').val(),'student.gender':parseInt(sex)"
									/>
					
				</body:body>
	</body>

</html>