<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>回访</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		
		<jc:plugin name="page" />
	<script type="text/javascript">
		var sex=-1;
		var status=0;
		function sexValue(sex){
			return (sex+"").getSex();
		}
		$(document).ready(function(){
			$.ajax({
						url: WEB_PATH+'/crm/student_status_list',
						type: "post",
						dataType: 'json',
						success: function(doc) {
               				$("#status").html("");
               				$("<option value='" + 0 + "'>请选择学生状态</option>").appendTo($("#status"));
							
							$(doc.studentStatus).each(function(){
								 $("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#status"));
							});

						},
						error:function(datas){
							
						}
				});
		});
	</script>
	</head>
	<body>
		<!--标题 -->
		<div id="title_new">
			<div id="contitle">
				<ul id="tags">
					<li class="selectTag">
						<a>回访</a>
					</li>
				</ul>
				
			</div>
			<div id="conmenu">
				<!--  
					<img src="<s:url value="/images/icon_sender.gif" />"  />
		   			<a href="/oauser/email/add;"  class="emailword" target="main">快捷菜单1</a>
	   			-->
			</div>
		</div>
		<!--主体层开始 -->
		<div class="block">
			<div class="public_table_bg_766">
				<div class="tb_table_default_2">
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
		
		                <td align="right">状态：</td>
		                <td align="right" style="width:150px;">
							<select id="status" class="txt_box_150" onchange="status=this.value;">
								
							</select>
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
										columnsStr="number;name;status;gender;schoolName;academyenrollbatchName;levelName;majorName"
										customColumnValue="3,sexValue(gender)"
										pageSize="10"
										view="http,/cedu/template/index,id,id,_self"
										params="'student.number':$('#number').val(),'student.name':$('#name').val(),'student.gender':parseInt(sex),'student.status':parseInt(status)"
									/>
					
				</div>
		    </div>
	    </div>
	</body>

</html>