<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>Demo</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		
		<jc:plugin name="base_js" />
		<script type="text/javascript">
			(function($){
				$(function(){
					util.select.initOption('select[name=student.degree]', getDegree());
					
					$.ajax({
							url: WEB_PATH+'/crm/student_way_list',
							type: "post",
							dataType: 'json',
							success: function(doc) {
	               				$("#source").html("");
	               				$("<option value='" + 0 + "'>请选择招生途径</option>").appendTo($("#source"));
								
								$(doc.enrollmentSources).each(function(){
									 $("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#source"));
								});
								
								$("#way").html("");
	               				$("<option value='" + 0 + "'>请选择市场途径</option>").appendTo($("#way"));
								
								$(doc.enrollmentWays).each(function(){
									 $("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#way"));
								});
	
							},
							error:function(datas){
								
							}
					});
				});
			})(jQuery);
			//增加学生
			function addStudent(){
				$.ajax({
						url: WEB_PATH+'/crm/add_student_lc',
						type: "post",
						data:$("#create_student_form").serializeObject(),
						dataType: 'json',
						success: function(doc) {
               				alert("保存成功");
						},
						error:function(datas){
							alert("保存失败！");
						}
				   });
			}
		</script>
	</head>
	<body>
		<!--标题 -->
		<div id="title_new">
			<div id="contitle">
				<ul id="tags">
					<li class="selectTag">
						<a>学生录入</a>
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
		<form id="create_student_form">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">学生详情</th>
				</tr>
			</table>
			<table  class="add_table" >

				<tr>
					<td align="right" style="width:10%;">数据来源：</td>
					<td align="left" style="width:200px;">
									学习中心
					</td>
					
				</tr>
				<tr>
					<td align="right" style="width:10%;">姓名：</td>

					<td align="left" style="width:200px;"><input name="student.name" type="text" class="txt_box_150" /></td>
					<td align="right" style="width:10%;">性别：</td>
					<td align="left">
					  	<select id="sex" class="txt_box_100" >
										<option selected="selected" value="-1">--请选择--</option>
										<option value="<%=Constants.SEX_MALE %>">男</option>
										<option value="<%=Constants.SEX_FAMALE %>">女</option>
							</select>

					</td>
				</tr>
				<tr>
					<td align="right" style="width:10%" >手机：</td>
					<td align="left"><input name="student.mobile" type="text" class="txt_box_150"  /></td>
					<td align="right" style="width:10%" >座机：</td>
					<td align="left"><input name="student.phone" type="text" class="txt_box_150"  /></td>

				</tr>
				
				<tr>
					<td align="right" style="width:10%" >邮箱：</td>
					<td align="left"><input name="student.email" type="text" class="txt_box_150"  /></td>
					<td align="right" style="width:10%" >邮编：</td>
					<td align="left"><input name="student.zipcode" type="text" class="txt_box_150"  /></td>
				</tr>

				<tr>
					<td align="right" style="width:10%" >学历：</td>
					<td align="left">
					<div class="seloutdiv_100">
						<div class="selectborder_100">
							<select class="txt_box_100" name="student.degree">
						</div>
					</div></td>
					<td align="right" style="width:10%" >证件：</td>
					<td align="left">
						<div class="seloutdiv_100">
						<div class="selectborder_100">
							<select id="" class="txt_box_100" name="student.certType">
								<option value="<%=Constants.CERTIFICATE_TYPE_ID %>" selected="selected">证件号</option>
								<option value="<%=Constants.CERTIFICATE_TYPE_DRIVER_ID %>">驾照</option>
								<option value="<%=Constants.CERTIFICATE_TYPE_NCO_ID %>">士官证</option>
							</select>
						</div>
					  </div>
					  &nbsp;&nbsp;&nbsp;
					  <input name="student.certNo" type="text" class="txt_box_200"  />
					  </td>
				</tr>
				<tr>
					<td align="right" style="width:10%" >招生途径：</td>
				  <td align="left">
						<select id="source" name="student.enrollmentSource" class="txt_box_150"></select>
				    </td>
					<td align="right" style="width:10%" >市场途径：</td>
					<td align="left" ><select id="way" class="txt_box_150"  name="student.enrollmentWay"></select></td>
				</tr>
				<tr>
					<td align="right" style="width:10%" >合作方：</td>

					<td align="left">
							<select id="" class="txt_box_100" name="student.channelId"></select>
					</td>
					<td align="right" style="width:10%" >地址：</td>
					<td align="left" ><input name="student.address" type="text" class="txt_box_300"  /></td>
				</tr>
			</table>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">分配信息</th>
				</tr>
			</table>
			<table  class="add_table" >
				<tr>
					<td align="right" style="width:10%" >分配：</td>
					<td align="left" style="width:200px;">北京学习中心 </td>
					<td align="right" style="width:10%" >意向：</td>
					<td align="left">
					</td>	
				</tr>

				<tr>
					<td align="right" style="width:10%" >层次：</td>
					<td align="left"></td>
					<td align="right" style="width:10%" >状态：</td>
					<td align="left">
					已分配
					</td>
					
				</tr>

				<tr>
					<td align="right" valign="top">备注：</td>
					<td align="left" colspan="3"><textarea name="student.remark" cols="80" rows="4"></textarea></td>	
				</tr>
				
				
				<tr>
				<td></td>

				<td align="left" ><input name="" type="button" class="btn_black_61"  onclick="addStudent()" value="保存" />	
				</td>
				<tr>
			</table>
		</form>
				</div>
		    </div>
	    </div>
	</body>

</html>