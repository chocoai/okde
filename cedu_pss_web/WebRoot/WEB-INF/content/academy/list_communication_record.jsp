<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>院校沟通记录</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		
		<script type="text/javascript">
		
		var idStr=0;
		var pagerObj;
		var pindex=1;
		var psize=2;
		var count=0;
		var academyId=${param.id};
		var linkmanId=0;
		
		//删除沟通记录
		function ajax_delete(data)
		{
			closes('isdeleteDiv');
			//ajax_123_3();
			//search001();
			refresh001();
		}
		
		
		//添加沟通记录
		function ajax_add(data)
		{
			show('addDiv','提示',250,150);
			closes('comment_save_dialog');
			//ajax_123_3();
			search001();
		}
		
		function getvalue(str)
		{
			if(str==null)
			{
				return '';
			}else
			{
				return str;
			}
		}
		
		//修改沟通记录
		function ajax_update(data)
		{
			show('updateDiv','提示',250,150);
			closes('comment_save_dialog');
			//ajax_123_3();
			//search001();
			refresh001();
		}
	
		</script>
		<a:ajax parameters="{id:idStr};{'academyId':academyId,'linkManId':linkmanId,'title':$('#title').val(),'content':$('#content').val(),'result':$('#result').val()};{'academyId':academyId,linkMan:linkmanId,subject:$('#subject').val(),comresult:$('#comresult').val(),time:$('#time').val()};{'academyId':academyId,'results.currentPageIndex':pindex,'results.pageSize':psize,linkMan:linkmanId,subject:$('#subject').val(),comresult:$('#comresult').val(),time:$('#time').val()};{'id':idStr,'academyId':academyId,linkManId:linkmanId,subject:$('#title').val(),content:$('#content').val(),result:$('#result').val()}"  
		successCallbackFunctions="ajax_delete;ajax_add;ajax_countcommunication;ajax_listcommunication;ajax_update" 
		urls="/academy/deleteacademycomunicationrecord;/academy/addacademycomunicationrecord;/academy/countacademycomunicationrecord;/academy/listacademycomunicationrecord;/academy/updateacademycomunicationrecord" 
		pluginCode="123" 
		isOnload=""/>

		<script type="text/javascript">
			
		/*
		 打开沟通记录层
		*/
		function create_comment()
		{
			var ui = jQuery('#comment_save_dialog');
			jQuery('form', ui)[0].reset();
			jQuery('#save_submit')[0].onclick=addcomm;
			show('comment_save_dialog','新增沟通记录',450,420);
		}
			
		function addcomm()
		{
			var title=$('#title').val();
			var content=$('#content').val();
			var result=$('#result').val();
			if($.trim(title) =="")
			{
				show('titleDiv','提示',250,150);
				return;
			}
			if($.trim(content) =="")
			{
				show('contentDiv','提示',250,150);
				return;
			}
			if($.trim(result) =="")
			{
				show('resultDiv','提示',250,150);
				return;
			}
			linkmanId=document.getElementById("linkManId").value;
			if(linkmanId==null)
			{
				linkmanId=0;
			}
			ajax_123_2();
		}	
		
		/*
		打开修改沟通记录层
		*/
		function update_comment(id,academyLinkmanId,subject,content,result)
		{
			var ui = jQuery('#comment_save_dialog');
			idStr=id;
			$('#linkManId').val(academyLinkmanId);
			$('#title').val(subject);
			$('#content').val(content);
			$('#result').val(result);
			jQuery('#save_submit')[0].onclick=updatecomm;
			show('comment_save_dialog','修改沟通记录',450,420);
			
		}
		
		
		function updatecomm()
		{
			var title=$('#title').val();
			var content=$('#content').val();
			var result=$('#result').val();
			if($.trim(title) =="")
			{
				show('titleDiv','提示',250,150);
				return;
			}
			if($.trim(content) =="")
			{
				show('contentDiv','提示',250,150);
				return;
			}
			if($.trim(result) =="")
			{
				show('resultDiv','提示',250,150);
				return;
			}
			linkmanId=document.getElementById("linkManId").value;
			if(linkmanId==null)
			{
				linkmanId=0;
			}
			ajax_123_5();
		}
		
		
		/*
		删除沟通记录
		*/
		function  delete_comment(id)
		{	
			idStr=id;
			show('isdeleteDiv','提示',250,150);
			
		}

		/*
		取消删除
		*/
		function cdiv()
		{
			closes('isdeleteDiv');
		}	

		/*
		页面加载
		*/
		$(function(){
		//ajax_123_3();
		search001();
		});
		
		function customContent(id,subject,comtime,userName,academyLinkmanId,academylinkman,content,result)
		{
			var lists='';
			lists+='<table class="gv_table_2" border="0" cellpadding="2" cellspacing="2">';
			lists+='<tr><td style="background-color:#F3F3F3;font-weight:bold;" colspan="2">';
			lists+='<div style="float:left;">';
			lists+='<span style="color:black;" >'+subject+'</span>';
			lists+='<span style="word-width:30px;">&nbsp;</span>';
			lists+='<span style="color:black; font-weight:normal; font-size:80%; color: #CAD;">'+comtime.replace("T"," ")+'</span></div>';
			lists+='<div style="float: right !important; width:50px !imporant;">';
			lists+='<a href="javascript:void(0);" onclick="update_comment('+id+','+academyLinkmanId+',\''+subject+'\',\''+content+'\',\''+result+'\')" class="icon"><img src="'+WEB_IMAGES+'/images/icon_edit.gif"  border="0" title="修改"/></a>';
			//lists+='<a href="javascript:void(0);" onclick="delete_comment('+id+')" class="icon"><img src="'+WEB_IMAGES+'/images/icon_del.gif"  border="0" title="删除"/></a>';
			lists+='</div></td></tr>';		
			lists+='<tr><td width="50%">沟通人：<span name="communicater">'+userName+'</span></td>';
			lists+='<td>院校联系人：<span name="contact">'+getvalue(academylinkman)+'</span></td></tr>';	
			lists+='<tr><td id="comment_content_C09988234" colspan="2">'+content+'</td></tr>';
			lists+='<tr><td id="comment_result_C09988234" colspan="2">'+result+'</td></tr>';
			lists+='</table>';
			return lists;
		}
		</script>
	
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="院校名称：${academy.name}">
		 <html:a text="返回" icon="return" onclick="history.go(-1);" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">沟通记录</th>
					<th>
						<div style="text-align:right;">
							<img src="<ui:img url="images/icon_add.gif"/>" width="16" height="16" align="absmiddle"/>&nbsp;<a href="javascript:void(0);" onclick="create_comment()" >新增沟通记录</a>
						</div>
					</th>
				</tr>
			</table>
			<form id="myform" name="myform">
			<input type="hidden" name="academyId" value="${param.id}"/>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td class="lable_100">主题：</td>
					<td><input type="text" id="subject" name="subject" /></td>
					<td class="lable_100">日期：</td>
					<td><input readonly="readonly" id="time" name="time" class="Wdate" type="text" onclick="WdatePicker()"/></td>
					<td class="lable_100">院校联系人：</td>
					<td><select style="width:104px; display:block; margin-left:0px !important;" id="linkMan" name="linkMan" class="txt_box_100">
							<option value="0">--请选择--</option>
							<s:iterator var="linkman"  value="academylinkman">
							<option value="<s:property value="#linkman.id" />"><s:property value="#linkman.name" /></option>
							</s:iterator>
							
						</select></td>
					<td class="lable_100">沟通结果：</td>
					<td><input type="text" id="comresult" name="comresult" /></td>
					<td class="lable_100"></td>
					<td><input type="button" onclick="search001();"  value="查询" class="btn_black_61"/></td>
				</tr>
			</table>
			</form>
				<page:plugin
					pluginCode="001"
					il8nName="academy"
					searchListActionpath="listacademycomunicationrecord"
					searchCountActionpath="countacademycomunicationrecord"
					columnsStr=""
					customColumnValue="0,customContent(id,subject,comtime,userName,academyLinkmanId,academylinkman,content,result)"
					pageSize="5"
					isPageSize="true"
					listAttribute="results.list"
					pageSizeAttribute="results.pageSize"
					recordCountAttribute="results.recordCount"
					currentPageIndexAttribute="results.currentPageIndex"
					pageAttribute="results.page"
					searchFormId="myform"
					isOrder="false"
					isonLoad="false"
				/>
			<div id="comment_save_dialog" style="display:none">
			<form>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td class="lable_110">标题：</td>
					<td><input type="text" id="title" name="title" class="txt_box_100"/></td>
				</tr>
				<tr>
					<td class="lable_110">沟通人：</td>
					<td>
						<input type="hidden" id="projectManagerId" name="projectManagerId" value="${userinfo.id}" />
						${userinfo.fullName}
					</td>
				</tr>
				<tr>
					<td class="lable_110">院校联系人：</td>
					<td>
						<select style="width:104px; display:block; margin-left:0px !important;" id="linkManId" name="linkManId" class="txt_box_100">
							<s:iterator var="linkman"  value="academylinkman">
							<option value="<s:property value="#linkman.id" />"><s:property value="#linkman.name" /></option>
							</s:iterator>
							
						</select>
					</td>
				</tr>
				<tr>
					<td class="lable_110">内容：</td>
					<td>
						<textarea class="txt_box_300" id="content" name="content" cols="36" rows="5" ></textarea>
					</td>
				</tr>
				<tr>
					<td class="lable_110">结果：</td>
					<td>
						<textarea class="txt_box_300" id="result" name="result" cols="36" rows="3" ></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="padding-top:7px !important; text-align:center !important;"  class="lable_110"><input type="button" value="提交" id="save_submit" class="btn_black_61"/></td>
				</tr>
			</table>
			<input type="hidden" id="comment_code"/>
			<input type="hidden" id="academy_code" code="A009882993"/>
			</form>
		</div>
					  
			<div id="isdeleteDiv" style="display:none">
					<div style="float:inherit">确定要删除吗？</div><br/>
					<input type="button" value="确定" id="btnok" name="btnok" onclick="ajax_123_1()" class="btn_black_61"/> 
					<input type="button" value="取消" id="tt" name="tt" onclick="cdiv()" class="btn_black_61"/> 
				
			</div>
								
			<div id="addDiv" style="display:none">
			添加成功！！
			</div>
			
			<div id="updateDiv" style="display:none">
			修改成功！！
			</div>		
		
			<div id="delDiv" style="display:none">
			删除成功！！
			</div>	
			
			<div id="titleDiv" style="display:none">
			标题不能为空，请确认！！
			</div>
			
			<div id="contentDiv" style="display:none">
			内容不能为空，请确认！！
			</div>
			
			<div id="resultDiv" style="display:none">
			结果不能为空，请确认！！
			</div>
			
  		</body:body>
  </body>
</html>
