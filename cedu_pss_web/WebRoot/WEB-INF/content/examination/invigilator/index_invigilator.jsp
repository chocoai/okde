<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
  	 <title>监考备案</title>
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
		
		//删除监考老师
		function del(id)
		{
			jQuery.post('<s:url value="delete_invigilator"/>',{"id":id},
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
			
		//修改状态
		function updatestatus(id,status)
		{
			invigilator_id=id;
			invigilator_status=status;
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
		<a:ajax successCallbackFunctions="updatestatus_callback" parameters="{'id':invigilator_id,'status':invigilator_status};" urls="/examination/invigilator/update_invigilator_status" pluginCode="002"/>
		<script>
		
		//显示头像
		
			function showphoto(photo,name)
		{
			if(null==photo)
			{
				photo='<m:il8n key="photo.default.search" il8nName="admin"/>';
			}
			var image='<img class="user_list_iamge" src="<ui:img url="'+photo+'"/>" /><br/>'+name+'';
			return image;
		}
		
		//显示状态
		function replay(status)
		{
			if(status==0)			
				return '待聘用'		
			if(status==1)
				return '已聘用'
			if(status==2)
				return '未聘用'
			if(status==3)
				return '已冻结'
			return null;
		}
		
		function showDegree(data){
		if(data==1){
		return '博士后';
		}
		if(data==2){
		return '博士';
		}
		if(data==3){
		return '硕士';
		}
		if(data==4){
		return '研究生';
		}
		if(data==5){
		return '本科';
		}
		}
		
		//操作列头
		function show(feeStandard)
		{
			return feeStandard+'元/天';
		}
	
		//修改状态
		function operatingTwo(id,status)
		{
			if(status==1)
			{
				return '<a href="javascript:updatestatus('+id+',3)">冻结</a> &nbsp; &nbsp; &nbsp;<a href="javascript:updatestatus('+id+',0)">解聘</a>'
			}
			if(status==0)
			{
				return '<a href="javascript:updatestatus('+id+',1)">聘用</a> &nbsp; &nbsp; &nbsp;<a href="javascript:updatestatus('+id+',2)">不聘用</a>'
			}
			if(status==3)
			{
				return '<a href="javascript:updatestatus('+id+',0)">解冻</a>'
			}
			if(status==2)
			{
				return '<a href="javascript:updatestatus('+id+',0)">待聘用</a>'
			}
			
		}
		
		</script>
</head>
  <body>
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>监考备案</a> </li>
		</ul>
	</div>
	<div id="conmenu">	 
	 <img src="<ui:img url="images/title_menu/icon_add.gif"/>"/>
	 <a href="<s:url value="add_invigilator"/>">新增监考老师</a></div>
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 
		<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
		   <tr>
		   <td class="lable_100">姓名：</td><td><input type="text" name="name" id="name" class="txt_box_150"/></td>
		   <td class="lable_100">工作单位:</td><td><input type="text" name="workUnit" id="workUnit" class="txt_box_150"/></td>
		      <td class="lable_100">审核状态：</td>
		      <td><select class="txt_box_150" name="status" id="status">
							<!--option value="1">上报</option-->
							<option value="0">待聘用</option>
							<option value="1">已聘用</option>
							<option value="2">未聘用</option>
							<option value="3">已冻结</option>	
					   </select></td>
		   <td><input type="button" name="" id="" value="查询" class="btn_black_61" onclick="search123();"/>
		   </td>
		   </tr>
		</table> 
	    <table class="gv_table_2" id="resTable" border="0" cellpadding="0" cellspacing="0">
	    	<tbody>
			 <page:plugin 
				pluginCode="123"
				il8nName="examination"
				searchListActionpath="findByconditions_invigilator"
				searchCountActionpath="count_invigilator"
				columnsStr="name;degree;creatorname;workUnit;feeStandard;invigilationExperience;status;#operating"
				customColumnValue="0,showphoto(photo,name);1,showDegree(degree);4,show(feeStandard);6,replay(status);7,operatingTwo(id,status)"
				view="http,examination/invigilator/view_invigilator,id,id,_blank"
				update="http,examination/invigilator/update_invigilator,id,id,_self"
				delete="json,del,id"
				pageSize="5"
				ontherOperatingWidth="80px"	
				params="'invigilator.name':$('#name').val(),'invigilator.workUnit':$('#workUnit').val(),'invigilator.status':$('#status').val()"
		     />  	 
	    	</tbody>
	    </table>
   </div>
  </div>
 </div>
 <div id="showDialog" style="display:none">
 
 </div>
</body>
</html>
