<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>教学公告</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
	    <jc:plugin name="calendar" />
		<jc:plugin name="page" />
		<jc:plugin name="tab" />
		<jc:plugin name="loading" />
		<jc:plugin name="base_js" />
		
		<script type="text/javascript">
			
			$(document).ready(function()
			{
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
				$('#message_confirm').dialog({
					autoOpen:false,
					title:'新增公告',
					buttons: {
						'添加': function() {
						  if($('#school2').val()==-1)
								{
									alert('请选择院校！');
									return false;
								}
						  if($('#title').val()==null || $('#title').val()==""){
						     alert('请填写标题！');
									return false;
						  }
						  if($('#content').val()==null || $('#content').val()==""){
						     alert('请填写内容！');
									return false;
						  }
						 // alert("ok");
								ajax_100_1();
								$(this).dialog("close");				
							}, 
						'关闭': function() { 
								$(this).dialog("close"); 
							} 
						}
				});
				
				
			});
			
			function addnotice()
			{
				$('#message_confirm').dialog("open");
			}
			
			function ajax_addnotice(data)
			{
				search001();
				jQuery("#showDialog").html('<b>添加成功！</b>');
				jQuery('#message_returns_tips').dialog("open");	
			}
			function showTitle(id,title)
			{
				return "<a href='view_teaching_notice?teachingNotice.id="+id+"'>"+title+"</a>";
			}
		</script>
		<a:ajax 
			pluginCode="100"
		    parameters="{'teachingNotice.title':$('#title').val(),'teachingNotice.content':$('#content').val(),'teachingNotice.academyId':$('#school2').val()}"
			successCallbackFunctions="ajax_addnotice"
			urls="teaching/add_teaching_notice_page_ajax"
		/>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="教学公告">
			 <a href="javascript:addnotice()"><img src="<ui:img url="images/title_menu/icon_add.gif" />" />新增公告</a>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="search_form">
					<table class="add_table" cellpadding="2" cellspacing="2">
							<tr>
								<td align="right">标题：</td>
								<td align="left">
									<input  name="teachingNotice.title" class="txt_box_130" type="text" value="" />
								</td>
							</tr>	
								<tr>
								<td align="right">时间介于：</td>
								<td align="left">
									<input id="publishStartTime" name="teachingNotice.publishStartTime" class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'publishEndTime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  type="text" />-
									<input id="publishEndTime"  name="teachingNotice.publishEndTime" class="Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'publishStartTime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  type="text" />
								</td>
								</tr>
								<tr>
								<td align="right">公告内容：</td>
								<td align="left">
									<input  name="teachingNotice.content" class="txt_box_130" type="text" />
								</td>
							</tr>
							<tr>
							   <td align="center" >
								</td>
								<td align="left" >
									<input  type="button"  class="btn_black_61" onclick="search001()" value="查询"/>
								</td>
							</tr>
					</table>
			</form>
						<page:plugin 
							pluginCode="001"
							il8nName="teaching"
							searchListActionpath="list_teaching_notice_page_ajax"
							searchCountActionpath="count_teaching_notice_page_ajax"
							columnsStr="schoolName;title;publisher;publishTime"
							pageSize="10"
							searchFormId="search_form"
							customColumnValue="1,showTitle(id,title)"
						/>
					
			</body:body>
			
				<!--弹出层-->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		
		
			<div id="message_confirm" style="display:none">
			<div align="center" id="showconfirm">
				<form id="add_teachingnotice">
					<table class="add_table" >
					    <tr>
							<td align="right">所属院校：</td>
							<td>
							
							<s:if test="%{academyList == null}">
								<select class="txt_box_150" id="school2" name="teachingNotice.academyId">
									<option value="-1">---请选择院校---</option>
								</select>
							</s:if>
							<s:else>
								<select class="txt_box_150" id="school2" name="teachingNotice.academyId">
									<option value="-1">---请选择院校---</option>
								<s:iterator value="%{academyList}" var="academy">
									<option value="${academy.id }">${academy.name }</option>
								</s:iterator>
								</select>
							</s:else>
							</td>
						</tr>
						<tr>
							<td align="right">标题：</td>
							<td><input id="title" class="txt_box_130" type="text" /></td>
						</tr>
						<tr>
							<td align="right">内容：</td>
							<td><input id ="content" class="txt_box_130" type="text" ></input></td>
						</tr>
					</table>
				</form>
			</div>
		</div>		
	</body>

</html>