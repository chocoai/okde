<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学籍管理</title>
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
				jQuery('#academyId').change(function(){
					selectbatch();
				});	
				jQuery('#batchId').change(function(){
					selectlevel();
				});	
				//层次专业级联
				jQuery('#levelId').change(function(){
					selectmajor();
				});	
			});	
		
		
		function add()
		{
			alert("添加成功");	
		}	
		
			 //读取批次
		    function selectbatch()
		    {
		    	$.ajax({
		    		url:'<s:url value="/enrollment/cascade_academy_batch_all_ajax" />',
		    		data:{academyId:jQuery('#academyId').val()},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#batchId').empty();
			    	 	$('#batchId').append('<option value="0">--请选择--</option>');	
			    	 	if(data.batchlist!=null&&data.batchlist.length>0)
			    	 	{
				    	 	$.each(data.batchlist,function(){	
				    	 		$('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				    	 	});
			    	 	}
			    	 	selectlevel();//读取层次  	
			    	 }	
		    	});
		    }
				 //读取层次
		    function selectlevel()
		    {
		   		var batchId=$('#batchId').val();
		    	$.ajax({
		    		url:'<s:url value="/enrollment/cascade_batch_level_ajax" />',
		    		data:{batchId:batchId},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#levelId').empty();
				    	$('#levelId').append('<option value="0">--请选择--</option>');
			    	 	if(data.levellist!=null&&data.levellist.length>0)
			    	 	{	
				    	 	$.each(data.levellist,function(){	
				    	 		$('#levelId').append('<option value="'+this.id+'">'+this.level.name+'</option>'); 
				    	 	});
				    	 }	
				    	 selectmajor();//读取专业
			    	 }	
		    	});
		    	
		    }
			 //读取专业
		    function selectmajor()
		    {
		   		var levelId=$('#levelId').val();
		    	$.ajax({
		    		url:'<s:url value="/enrollment/cascade_level_major_ajax" />',
		    		data:{levelId:levelId},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#majorId').empty();
			    	 	$('#majorId').append('<option value="0">--请选择--</option>');
			    	 	if(data.majorlist!=null&&data.majorlist.length>0)
			    	 	{
			    	 		$.each(data.majorlist,function(){	
			    	 			$('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	 		});
			    	 	}	
			    	 }	
		    	});
		    }
		
		
		
	</script>
</head>
  <body>

  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>学籍管理</a> </li>
		</ul>
	</div>
</div>
<div class="block">
	<div class="public_table_bg_766">

		<div class="tb_table_default_2">
      <form id="search_form">
					<table class="add_table" cellpadding="2" cellspacing="2">
							<tr>
							<td align="right">学号：</td>
								<td align="left">
									<input  name="student.number" class="txt_box_130" type="text" />
								</td>
								<td align="right">姓名：</td>
								<td align="left">
									<input  name="student.name" class="txt_box_130" type="text" value="" />
								</td>
						      <td align="right">院校：</td>
							   <td align="left">
		                	<s:if test="%{academyList == null}">
								<select class="txt_box_150" id="academyId" name="student.academyId">
									<option value="0">---请选择院校---</option>
								</select>
							</s:if>
							<s:else>
								<select class="txt_box_150" id="academyId" name="student.academyId">
									<option value="0">---请选择院校---</option>
								<s:iterator value="%{academyList}" var="academy">
									<option value="${academy.id }">${academy.name }</option>
								</s:iterator>
								</select>
							</s:else>
						  </td>
							  </tr>
							  
							  <tr>
							
							  <td align="right">批次：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.enrollmentBatchId" id="batchId">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
							  
							  <td align="right">层次：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.levelId" id="levelId">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
						 
						  <td align="right">专业：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.majorId" id="majorId" >
							  		<option value="0">--请选择--</option>
							  	</select>
							 </td>
						 
							 
								<td align="left">
								<input  type="button" class="btn_black_61" onclick="search001()" value="查询"/>
							</td>
						</tr>
					</table>				
				</form>
	    <table class="gv_table_2" id="resTable" border="0" cellpadding="0" cellspacing="0">
 
	    	 <page:plugin pluginCode="001" il8nName="schoolmanagement"
								searchListActionpath="page_schoolrollmanagement_list"
								searchCountActionpath="page_shoolrollmanagement_count"
								columnsStr="operating;number;name;gender;schoolName;majorName;levelName;enrollmentBatchName;batchId;caozuo"
								customColumnValue=""
								searchFormId="search_form"
								pageSize="3" ontherOperatingWidth="80px" isPageSize="3"
							 					
								/>
	    	 </table>
   </div>
  </div>
 </div>
</body>
</html>