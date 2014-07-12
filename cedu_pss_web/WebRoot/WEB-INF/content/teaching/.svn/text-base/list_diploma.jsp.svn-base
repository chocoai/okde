<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>毕业证书</title>
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
			var ids=0;
			var studentId=0;
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
				
				//信息提示
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
			
			});
			
			
			//分页列表显示
			function sexValue(sex)
			{
				return sex.getSex();
			}
			function statusValue(status)
			{
				return status.getStudentStatus();
			}
			function shwodipstatus(diplomaStatus)
			{
				if(diplomaStatus==DIPLOMA_STATUS_NO_FA)
				{
					return "未颁发"
				}
				else if(diplomaStatus==DIPLOMA_STATUS_YES_FA)
				{
				  return  "已颁发";
				}else
				{
				  return "已领取";
				}
			}
			function lingqu(id,diplomaStatus)
			{
					//return "1";
	     	  if(diplomaStatus==DIPLOMA_STATUS_YES_FA)
			  {
			    isPageOperating(id,"001","checkbox");
			    return "<a href='javascript:diplomaqu("+id+");'>领取</a>";   
			  }
			   else if(diplomaStatus==DIPLOMA_STATUS_YES_QU)
			  {
			  	isPageOperating(id,"001","checkbox");
			  
			  }
			 
				return "";
			}
			
			//领取
			function diplomaqu(id)
			{
				$('#message_confirm').dialog({
			     title:'确认操作',
					buttons: {
						'确认': function() { 
							studentId=id;
								ajax_150_1();
								$(this).dialog("close");				
							}, 
						'取消': function() { 
								$(this).dialog("close"); 
							} 
						}
				});
				$('#message_confirm').dialog("open");
			}
			
			//批量颁发
			function batchbanfa()
			{
				if(getCheckedValues001()==null || getCheckedValues001()=="")
				{
					jQuery("#showDialog").html('<b>请选择要颁发的证书！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else
				{
					$('#message_confirm2').dialog({
						title:'确认操作',
						buttons: {
							'确认': function() { 
								    // alert(getCheckedValues001());
									ajax_160_1();//批量颁发	
									$(this).dialog("close");				
								}, 
							'取消': function() { 
									$(this).dialog("close"); 
								} 
							}
					});
					$('#message_confirm2').dialog("open");
				}
			}
				
				//ajax回调函数   
		
			function ajax_updatestatus(data)
			{
			  if(data.isfail)
				{
					jQuery("#showDialog").html('<b>已颁发后才能领取！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else
				{
					search001();
					jQuery("#showDialog").html('<b>操作成功！</b>');
					jQuery('#message_returns_tips').dialog("open");	
				} 
			}
			
			//批量录领取  ajax回调函数
			function ajax_updatebatchstatus(data)
			{
				if(data.isfail)
				{
					jQuery("#showDialog").html('<b>操作失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else
				{
					search001();
					jQuery("#showDialog").html('<b>操作成功！</b>');
					jQuery('#message_returns_tips').dialog("open");	
				}
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
			
				/*
		删除
		*/
		function deleteFun(id)
		{
			ids=id;
			//alert(id);
			show('isdeleteDiv','提示',250,150);
			
		}		  
				  
		/*
		取消删除
		*/
		function cdiv()
		{
			closes('isdeleteDiv');
		}	
			
			
			//删除
		function ajax_delete(data)
		{
			closes('isdeleteDiv');
			search001();
		}	
		
		function showName(id,name)
			{
			return "<a href='view_diploma_student?id="+id+"'>"+name+"</a>";
			//return "1";
			}
		</script>
		
		<!-- 删除学生-->
		<a:ajax parameters="{'student.id':ids}" 
		successCallbackFunctions="ajax_delete" 
		pluginCode="123" urls="/crm/crm_delete_student"
		/>
	
	<!-- 领取-->
		<a:ajax 
			pluginCode="150"
			parameters="{'student.id':studentId}" 
			successCallbackFunctions="ajax_updatestatus"
			urls="/teaching/upate_diplomastatus_ajax"
		/>
			<!-- 批量领取-->
		<a:ajax 
			pluginCode="160"
			parameters="{stuIds:getCheckedValues001()}" 
			successCallbackFunctions="ajax_updatebatchstatus"
			urls="/teaching/upate_batch_diplomastatus_ajax"
		/>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="毕业证书">
		 <html:a text="导入学生毕业信息" icon="add" href="/academy/add_academy" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
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
								
								 <td align="right">毕业状态：</td>
							  <td align="left">
							  <s:if test="%{diplomaList == null}">
							  <select class="txt_box_130" name="student.status" >
							  		<option value="0">--请选择--</option>
							  	</select>
							  </s:if>
							  	<s:else>
								<select class="txt_box_150" id="school2" name="student.status">
									<option value="0">---请选择毕业状态---</option>
							        <option value="<%=Constants.STU_CALL_STATUS_YI_LU_QU %>">已录取</option>
							         <option value="<%=Constants.STU_CALL_STATUS_YI_JIAO_XUE_FEI %>">已缴学费</option>
							          <option value="<%=Constants.STU_CALL_STATUS_YI_QU_DE_XUE_JI %>">已取得学籍</option>
							           <option value="<%=Constants.STU_CALL_STATUS_KE_CHENG_JING_XIU %>">课程进修</option>
							            <option value="<%=Constants.STU_CALL_STATUS_YI_FU_XUE %>">已复学</option>
							             <option value="<%=Constants.STU_CALL_STATUS_YI_XIU_XUE %>">已休学</option>
							              <option value="<%=Constants.STU_CALL_STATUS_YI_BI_YI %>">已毕业</option>
							               <option value="<%=Constants.STU_CALL_STATUS_YI_TUI_XUE %>">已退学</option>
								</select>
							</s:else>
							  </td>
								
							  </tr>
							  
							  <tr>
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
						</tr>
						
						<tr>
						  <td align="right">专业：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.majorId" id="majorId" >
							  		<option value="0">--请选择--</option>
							  	</select>
							 </td>
							 	<td align="right">颁发时间介于:</td>
								<td align="left">
									<input id="startDate" name="student.startDate" class="Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  type="text" />-
									<input id="endDate"  name="student.endDate" class="Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"  type="text" />
								</td>
							<td align="right"></td>
								<td align="left">
								<input  type="button" class="btn_black_61" onclick="search001()" value="查询"/>
							</td>
						</tr>
						
					</table>
					
				</form>
						<page:plugin 
							pluginCode="001"
							il8nName="teaching"
							searchListActionpath="list_diploma_page_ajax"
							searchCountActionpath="count_diploma_page_ajax"
							columnsStr="number;name;gender;schoolName;majorName;levelName;enrollmentBatchName;admissionTime;graduateTime;status;diplomaStatus;operating"
							customColumnValue="1,showName(id,name);2,sexValue(gender);9,statusValue(status);10,shwodipstatus(diplomaStatus);11,lingqu(id,diplomaStatus)"
							pageSize="10"
							isChecked="true"
							checkboxValue="id"
							searchFormId="search_form"
							params="'result.order':'name','result.sort':'asc'"
						/>
						 <input class="btn_black_61" align="right"  type="button"
													onclick="batchbanfa()"
													value="批量颁发" />
			</body:body>
			
			
			
		<div id="isdeleteDiv" style="display:none">
		<div style="float:inherit">确定要删除吗？</div><br/>
		<input type="button" value="确定" id="btnok" name="btnok" onclick="ajax_123_1()" class="btn_black_61"/> 
		<input type="button" value="取消" id="tt" name="tt" onclick="cdiv()" class="btn_black_61"/> 
		</div>
		
		
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="message_confirm" style="display:none">
			<div align="center" id="showconfirm">
				<b>确认领取么？</b>
			</div>
		</div>		
		<div id="message_confirm2" style="display:none">
			<div align="center" id="showconfirm2">
				<b>确认领取选中的证书么？</b>
			</div>
		</div>	
	</body>

</html>