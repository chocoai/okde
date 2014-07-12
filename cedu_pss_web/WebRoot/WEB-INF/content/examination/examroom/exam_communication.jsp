<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<a:ajax parameters="{'titles':$('#title').val(),'contents':$('#content').val(),'nid':$('#nid').val()}"  
		successCallbackFunctions="ajax_add" 
		urls="/examination/examroom/add_examcommunication" 
		pluginCode="123" 
		/>
		<script type="text/javascript">
		jQuery(function(){
		jQuery('#comment_save_dialog').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示'
				});	
		});
		
		function ajax_add(){
			show('添加成功','提示',200,100);
			 closes('comment_save_dialog');
		       search0909();
			}
			
			function del(id)
		{
			jQuery.post('<s:url value="delete_examcommunication"/>',{"id":id},
			        function(data)
			    	{
			    		if(data.results)
			    		{
			    			show('showDialog','删除成功!',150,100);
			    			search0909();
			    		}
			    		else {show('showDialog','删除失败!',150,100);}	
			    	},
			 "json");	
		}
			
	function addcomm()
		{
		
			var title=$('#title').val();
			var content=$('#content').val();//$('#content').html();
			alert(title+",,"+content);
			
			if($.trim(title)=="")
			{
			alert(' 标题不能为空');
             return;
			}
			if($.trim(content)=="")
			{
			alert('内容不能为空');
			return;
	         }
			ajax_123_1();
			search0909();
	}	
	function  showtime(data){
	
  return data.substring(0,10);}
		</script>
  </head>
  
  <body>
   <head:head title="巡考总结">
		</head:head>
    <body:body>
    	
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">沟通记录</th>
					<th style="text-align: right;">
						<img src="<ui:img url="images/icon_add.gif"/>" width="16" height="16" />
					<a href="javascript:show('comment_save_dialog','新增沟通记录',450,350);" >添加沟通记录</a></th>
				</tr>
			</table>
			<input type="hidden" name="nid" id="nid" value="${id}"/>
		 <table class="gv_table_2" id="resTable" border="0" cellpadding="0" cellspacing="0">
	    	<tbody>
			 <page:plugin 
				pluginCode="0909"
				il8nName="examschedule"
				searchListActionpath="list_communication"
				searchCountActionpath="count_communication"
				columnsStr="title;createTime;content"
				customColumnValue="1,showtime(createTime)"
				update="http,examination/examroom/update_examschedule,id,id,_self"
				delete="json,del,id"
				pageSize="5"
				params="'id':$('#nid').val()"
				ontherOperatingWidth="80px"	
		     />  	 
	    	</tbody>
	    </table>			
			</body:body>
	
	<div id="comment_save_dialog" style="display:none">
			<form id="myform">
			<table class="add_table">
			<tr>
            	<td class="lable_100">标题：</td>
                <td><input type="text" name="title" id="title" class="txt_box_130"/></td>
            </tr>
            <tr>
            	<td class="lable_100">内容：</td>
                <td>
                <textarea rows="10" cols="15" id="content" name="content">
                </textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                	<input type="button" class="btn_black_61"  value="添加"  onclick="addcomm()" />
                	<input type="button" class="btn_black_61" value="关闭" onclick="closes('comment_save_dialog');" />
                </td>
            </tr>
        </table>
			</form>
		</div>
		<div id="showDialog" style="display:none">
		</div>
  </body>
</html>
