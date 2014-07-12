<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<jc:plugin name="loading" />
		<jc:plugin name="validator" />
		<script type="text/javascript">
		
		//删除考场
		function del(id)
		{
			jQuery.post('<s:url value="delete_examroom"/>',{"id":id},
			        function(data)
			    	{
			    		if(data.results)
			    		{
			    			show('showDialog','删除成功!',150,100);
			    			search123();
			    		}
			    		else {show('showDialog','删除失败!',150,100);}	
			    	},
			 "json");	
		}
		function updatestatus(id,status)
		{
			exam_room_id=id;
			exam_room_status=status;
			ajax_002_1();
		}
		function updatestatus_callback(date)
		{
			if(date.results)
			{
				show('showDialog','修改成功',150,100);
				search123();
			}
			else
			{
				show('showDialog','修改失败',150,100);
			}	
		}
		</script>
		<a:ajax successCallbackFunctions="updatestatus_callback" parameters="{'id':exam_room_id,'status':exam_room_status};" urls="/examination/examroom/update_examroom_status" pluginCode="002"/>
		<script>
		//操作列头
		function replay(status)
		{
			if(status==0)
			{
				return '未审批';
			}
			if(status==1)
			{
				return '审批通过';
			}
			if(status==2)
			{
				return '审批未通过';
			}
		} 
		function shownames(id,name){
		var names='<a href="view_examroom?id='+id+'">'+name+'</a>';
		return names;
		}
		</script>
</head>
  <body>
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>考场备案</a> </li>
		</ul>
	</div>
	<div id="conmenu">	 
	 <img src="../../images/title_menu/icon_add.gif"/>
	 <a href="<s:url value="add_exam_room"/>">添加考场信息</a></div>
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 
		<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
		   <tr>
		   <td class="lable_100">考场名称：</td><td><input type="text" name="name" id="name" class="txt_box_150"/></td>
		   <td class="lable_100">联系人:</td><td><input type="text" name="linkman" id="linkman" class="txt_box_150"/></td>
		   <td class="lable_100">审核状态：</td><td><select class="txt_box_150" name="status" id="status">
							<!--option value="1">上报</option-->
							<option value="0">未审批</option>
							<option value="1">审批通过</option>
							<option value="2">审批未通过</option>
					   </select></td>
		   <td><input type="button" name="" id="" value="查询" class="btn_black_61" onclick="search123();"/>
		   </td>
		   </tr>
		</table> 
		
	    <table class="gv_table_2" id="resTable" border="0" cellpadding="0" cellspacing="0">
	    	<tbody>
			 <page:plugin 
				pluginCode="123"
				il8nName="examroom"
				searchListActionpath="findByconditions_examroom"
				searchCountActionpath="count_examroom"
				columnsStr="name;linkman;phone;roomcount;email;address;status"
				customColumnValue="0,shownames(id,name);6,replay(status)"
				view="http,examination/examroom/view_examroom,id,id,_blank"
				update="http,examination/examroom/update_examroom,id,id,_self"
				delete="json,del,id"
				pageSize="5"
				ontherOperatingWidth="80px"	
				params="'name':$('#name').val(),'linkman':$('#linkman').val(),'status':$('#status').val()"
		     />  	 
	    	</tbody>
	    </table>
   </div>
  </div>
 </div>
</body>
</html>