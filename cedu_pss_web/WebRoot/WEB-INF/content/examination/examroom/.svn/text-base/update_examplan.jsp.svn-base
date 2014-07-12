<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>修改考试计划</title>
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
		<script type="text/javascript">
		$(document).ready(function(){
		//alert(${invigilator.certType});
	   			$('#academyId').attr("value",${examplan.academyId});
	   			selectbatch();	
	   			//$('#batchId').attr("value",${examplan.batchId});
	   			$('#examBatchId').attr("value",${examplan.examBatchId});
	   			selectlevel();
	   			
				});
		 jQuery(function(){
				//院校相关级联(批次、学习中心-层次-专业(防止刷新时其他不刷新))
				jQuery('#academyId').change(function(){
					selectbatch();
					selectbranch();
					//selectlevel();ajax延时放到批次里执行
				    //selectmajor();
				});	
				//招生批次相关级联(学习中心、层次、专业(防止刷新层次，专业不刷新))
				jQuery('#batchId').change(function(){
					selectbranch();
					selectlevel();
					//selectmajor();
				});	
				//层次专业级联
				jQuery('#levelId').change(function(){
					selectmajor();
				});	
			});
			
			
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
			    	 	//selectlevel();//读取层次  
			    	 
			    	 	if(${examplan.batchId}!=null && ${examplan.batchId}>0)
			    	 	{	
			    	 		$('#batchId').attr("value",${examplan.batchId});
			    	 		
			    	 	}
			    	 	selectbranch();
			    	 }	
		    	});
		    }
			 //读取学习中心
			 var isbranch=true;
		    function selectbranch()
		    {
		   		var batchId=$('#batchId').val();
		    	$.ajax({
		    		url:'<s:url value="/enrollment/cascade_batch_branch_ajax" />',
		    		data:{batchId:batchId,academyId:jQuery('#academyId').val()},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#branchId').empty();
			    	 	$('#branchId').append('<option value="0">--请选择--</option>');	
			    	 	if(data.branchlist!=null&&data.branchlist.length>0)
			    	 	{
				    	 	$.each(data.branchlist,function(){	
				    	 		$('#branchId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
				    	 	});
			    	 	}
			    	 	 if(${examplan.branchId}!=null && ${examplan.branchId}>0 && isbranch==true)
			    	 	{	
			    	 		$('#branchId').attr("value",${examplan.branchId});
			    	 		isbranch=false;
			    	 	}		
			    	 }
			    	
		    	});
		    }
		    var islevel=true;
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
				    	  if(${examplan.levelId}!=null && ${examplan.levelId}>0 && islevel==true)
			    	 	{	
			    	 		$('#levelId').attr("value",${examplan.levelId});
			    	 		islevel=false;
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
			    	 	 if(${examplan.majorId}!=null && ${examplan.majorId}>0)
			    	 	{	
			    	 		$('#majorId').attr("value",${examplan.majorId});
			    	 		
			    	 	}			
			    	 }	
		    	});
		    }
		    </script>
  </head>
  <body>
  <form action="update_examplan" id="myform" method="post">
    <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>修改考试计划</a> </li>
		</ul>
	</div>
	<div id="conmenu">
	 <img src="<ui:img url="images/icon_title_return.jpg"/>" />
	 <a href="#" onclick="history.go(-1);">返回</a>
	</div>

</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2">
		
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">考试计划信息</th>
				</tr>
			</table>
			<input  type="hidden" name="examplan.id"  value="${examplan.id}"/>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
			    <tr>
		                <td class="lable_150">院校：</td>
		                <td align="left">
		                	<s:if test="%{academylist!=null}">
								<s:select list="%{academylist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="examplan.academyId" id="academyId" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="examplan.academyId" id="academyId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>	
						</td>
						</tr>
						<tr>
		                <td class="lable_150">招生批次：</td>
		                <td align="left">
							<select name="examplan.batchId" id="batchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
						</tr>
						<tr>
						<td class="lable_150">学习中心：</td>
		                <td align="left">
							<select name="examplan.branchId" id="branchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
							
						</td>
						</tr>
						<tr>
		                <td class="lable_150">层次：</td>
		                <td>
							<select name="examplan.levelId" id="levelId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
	              	</tr>
				  	<tr>
		                <td class="lable_150">专业：</td>
		                <td align="left">
							<select name="examplan.majorId" id="majorId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
                    </tr>
                    <tr>
						<td class="lable_150">考试批次：</td>
						<td><s:select list="%{exambatchlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="code" name="examplan.examBatchId" id="examBatchId" cssClass="txt_box_130"/></td>
                    </tr>
				<tr>
					<td class="lable_150">考试计划：</td>
					<td><input type="text" class="txt_box_150"  id="examplan.plan" name="examplan.plan" value="${examplan.plan}" /></td>
				</tr>
				<tr>
					<td class="lable_150">考试人数：</td>
					<td><input type="text" class="txt_box_150"  id="examplan.planCount" name="examplan.planCount" value="${examplan.planCount}" /></td>

				</tr>
				<tr>
					<td class="lable_150">考试时间：</td>
					<td><input type="text" class="txt_box_150"  id="examplan.examBeginTime" name="examplan.examBeginTime" value="${examplan.examBeginTime}" /><br/><input type="text" class="txt_box_150"  id="examplan.examEndTime" name="examplan.examEndTime" value="${examplan.examEndTime}" /></td>
				</tr>
				<tr>
				<td align="center" colspan="2">
					 <input type="submit" name="" id="mysubmit" value="修改" class="btn_black_61"/>
				</td>
			 </tr>
			</table>
  		</div>
  	</div>
  </div>
  </form>
</body>
</html>

  
