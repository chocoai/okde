<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
   <title>监考审批</title>
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
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<jc:plugin name="loading" />
		<jc:plugin name="validator" />
		<a:ajax successCallbackFunctions="updatestatus_callback" parameters="{'id':examroom_id,'status':examroom_status};" urls="/examination/examroom/update_examroom_status" pluginCode="002"/>
		<script>
			function updatestatus(id,status)
		{
			examroom_id=id;
			examroom_status=status;
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
		function ap(op)
		{
			jQuery("#status").val(op);
			search123();
		}
		function operatingTwo(id,status)
		{
			if(status==0)
			{
				return '<a href="javascript:updatestatus('+id+',1)">审批通过</a> &nbsp; &nbsp; &nbsp;<a href="javascript:updatestatus('+id+',2)">审批未通过</a>'
			}
			if(status==1)
			{
				return '<a href="javascript:updatestatus('+id+',2)">审批未通过</a>'
			}
			if(status==2)
			{
				return '<a href="javascript:updatestatus('+id+',1)">审批通过</a>'
			}
			
			
		}
		
		$(document).ready(function(){
				
				$("#menu>li>a").click(function(){
						$(this).attr("class","current");
						var selObj=this;
							$("#menu>li>a").each(function(){
									if(this!=selObj){
										$(this).attr("class","");
									}
							});
					});
			});
		</script>
	
</head>
  <body>
  
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>考场审批</a> </li>
		</ul>
	</div>
</div>
<div class="block">
<br/>
<div>
	<ul id="menu">
		<li><a href="javascript:ap(0)" title="待审批" class="current">待审批</a></li>
		<li><a href="javascript:ap(1)" title="审批通过" >审批通过</a></li>
		<li><a href="javascript:ap(2)" title="审批未通过" >审批未通过</a></li>
	</ul>
</div>
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2">
 		<input type="hidden" name="status" id="status" value="<%=Constants. AUDIT_STATUS_FALSE %>"/>
		 <table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
		   <tr>
		   <td class="lable_100">学习中心：</td><td><input type="text" name="branch" id="branch" class="txt_box_150"/></td>
		   <td class="lable_100">联系人:</td><td><input type="text" name="linkman" id="linkman" class="txt_box_150"/></td>
		  <td class="lable_100">考场名称</td><td><input type="text" name="name" id="name" class="txt_box_150"/></td>
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
				columnsStr="name;linkman;phone;roomcount;email;address;status;#operating"
				customColumnValue="6,replay(status);7,operatingTwo(id,status)"
				view="http,examination/examroom/view_examroom,id,id,_blank"
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
