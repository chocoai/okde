<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<a:ajax 
		parameters="{'code':$('#code').val(),'name':$('#name').val()}"
		successCallbackFunctions="ajax_add" 
		urls="/examination/examroom/add_exambatch" 
		pluginCode="123" 
		/>
	<script type="text/javascript">
	 jQuery(function(){
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
			
			
			
			 //读取学习中心
		    function selectbranch()
		    {
		   		var batchId=$('#batchId').val();
		   		alert(batchId);
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
	
		jQuery(function(){
				//信息提示
				jQuery('#createbatch').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示'
				});	
		});
		function del(id)
		{
			jQuery.post('<s:url value="delete_examplan"/>',{"id":id},
			        function(data)
			    	{
			    		if(data.results)
			    		{
			    			show('showDialog','删除成功!',150,100);
			    			search234();
			    		}
			    		else {show('showDialog','删除失败!',150,100);}	
			    	},
			 "json");	
		}
	  function addComm()
		{
		   var branchId=$('#branchId').val();
			var code=$('#code').val();
			var name=$('#name').val();
			alert(code+"--"+name);
			if($.trim(code) =="")
			{
			   alert('编号不能为空');
                return;
			}
			if($.trim(name) =="")
			{
			alert('名称不能为空！');
				return;
				}
			ajax_123_1();
		}
		function showTime(createdTime){
		return createdTime;
		}
		
		function ajax_add(){
		show('添加成功','提示',200,100);
		closes('createbatch');
		}
		
		function replay(id,status)
		{
			if(status==0)
			{
			return '未安排';
			}
			if(status==1)
			{
				return '已安排';
			}
		}
	</script>
</head>
  <body>
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>考试计划</a> </li>
		</ul>
	</div>
	<div id="conmenu">
	 <img src="<ui:img url="images/icon_title_return.jpg" />"/>
	 <a href="#" onclick="history.go(-1);">返回</a>
	</div>
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 
		<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">学院名称:${name}</th>
					<th>
						<div style="text-align:right;">
						  <a href="javascript:show('createbatch','新增考试批次',450,350);" >
	                        <img src="<ui:img url="images/wait.gif"/>" class="img_icon"/>考试批次</a>&nbsp;&nbsp;
							<img src="<ui:img url="images/cedu/icon/icon_add.gif"/>" class="img_icon" align="middle"/>
							<a href="add_exam_plan" target="_blank">添加考试计划</a>
							&nbsp;&nbsp;
						</div>
					</th>
				</tr>
			</table>
	   </div>

	 </div>
</div>
    <input type="hidden" name="id" id="academyId" value="${id}"/>
			 <table class="add_table" cellpadding="2" cellspacing="0" border="0" width="100%">
			    <tr>
				  <td>招生批次：</td>
		                <td align="left">
		                	<s:if test="%{batchlist!=null}">
								<s:select list="%{batchlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="enrollmentName" name="batchId" id="batchId" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="batchId" id="batchId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>	
						</td>
						<td>学习中心：</td>
		                <td align="left">
							<select name="branchId" id="branchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
						
		                <td>层次：</td>
		                <td>
							<select name="levelId" id="levelId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
	              	
				  	
		                <td>专业：</td>
		                <td align="left">
							<select name="majorId" id="majorId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
                   
				   <td><input type="button" value="查询" class="btn_black_61" onclick="search234()"/></td>
				</tr>
			 </table>
            <table class="gv_table_2" id="resTable" border="0" cellpadding="0" cellspacing="0">
	    	<tbody>
			 <page:plugin 
				pluginCode="234"
				il8nName="examination"
				searchListActionpath="findByconditions_examplans"
				searchCountActionpath="count_examplans"
				columnsStr="branchname;plan;levelname;majorname;batchname;#times;planCount;invigilatorIds;status"
				customColumnValue="5,showTime(createdTime);8,replay(id,status)"
				update="http,examination/examroom/update_examplan,id,id,_self"
				delete="json,del,id"
				pageSize="5"
				ontherOperatingWidth="80px"	
				params="'branchId':$('#branchId').val(),'levelId':$('#levelId').val(),'majorId':$('#majorId').val(),'batchId':$('#batchId').val(),'bid':$('#academyId').val()"
		     />  	 
	    	</tbody>
	    </table>
 <!-- 添加考试批次 -->
<div id="createbatch" style="display:none;">
    	<table class="add_table">
			<tr>
            	<td class="lable_100">批次编号：</td>

                <td><input type="text" name="code" id="code" class="txt_box_130"/></td>
            </tr>
			<tr>
            	<td class="lable_100">批次名称：</td>
                <td><input type="text" name="name" id="name" class="txt_box_130"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center">

                	<input type="button" class="btn_black_61" name="save" onclick="addComm()" value="添加"  />
                	<input type="button" class="btn_black_61" value="关闭" onclick="closes('createbatch');" />
                </td>
            </tr>
        </table>
</div>
<div id="showDialog" style="display:none">

</div>
</body>
</html>