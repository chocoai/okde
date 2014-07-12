<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>报名监控反馈</title>
		
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
		
		<script type="text/javascript">

			//加载事件
			jQuery(function(){
				
				//全局招生批次
				ajax_100_1();
				//全局批次级联
				jQuery('#globalBatchId').change(function(){
					ajax_110_1();//院校					
				});			
				//院校相关级联
				jQuery('#academyId').change(function(){
					ajax_140_1();//招生批次						
				});					
				//层次专业级联
				jQuery('#levelId').change(function(){
					ajax_130_1();//专业	
				});
			});
			
			//分页列表
			function sexValue(sex){
				return sex.getSex();
			}
			function statusValue(status){
				return status.getStudentStatus();
			}
			function operating(id){
				return '<a href="'+WEB_PATH+'/enrollment/enrollmonitorapplication/list_submit_enroll_monitor_application?stuid='+id+'">监控再申请</a>';
			}
			
		</script>
			
		<script type="text/javascript">
			var status='<%=Constants.STU_MONITOR_STATUS_JIAN_KONG_WEI_CHENG_GONG %>';
			//ajax回调函数   全局批次(学习中心)
			function ajax_global_batch(data)
			{		
				$('#globalBatchId').empty();
			    $('#globalBatchId').append('<option value="0">--请选择--</option>');
			    if(data.globalBatchList!=null && data.globalBatchList.length>0)
			    {
			    	$.each(data.globalBatchList,function(){	
			    		$('#globalBatchId').append('<option value="'+this.id+'">'+this.batch+'</option>'); 
			    	});
			   	}
			   	ajax_110_1();//院校
			}
			//ajax回调函数   院校(学习中心、全局批次)
			function ajax_academy(data)
			{				
				$('#academyId').empty();
			    $('#academyId').append('<option value="0">--请选择--</option>');
			    if(data.academyList!=null && data.academyList.length>0)
			    {
			    	$.each(data.academyList,function(){	
			    		$('#academyId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}	
			   	
			   	$('#batch').html('');
			   	$('#batchId').val(0);
			   	
			   	ajax_120_1();//层次	
			}
			//ajax回调函数  层次(招生批次)
			function ajax_level(data)
			{				
				$('#levelId').empty();
			    $('#levelId').append('<option value="0">--请选择--</option>');
			    if(data.levellist!=null && data.levellist.length>0)
			    {
			    	$.each(data.levellist,function(){	
			    		$('#levelId').append('<option value="'+this.id+'">'+this.level.name+'</option>'); 
			    	});
			   	}	
			   	ajax_130_1();//专业	
			}
			//ajax回调函数  专业(层次)
			function ajax_major(data)
			{				
				$('#majorId').empty();
			    $('#majorId').append('<option value="0">--请选择--</option>');
			    if(data.majorlist!=null && data.majorlist.length>0)
			    {
			    	$.each(data.majorlist,function(){	
			    		$('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			    }	
			}
			//ajax回调函数  招生批次(院校、全局批次)
			function ajax_batch(data)
			{				
			    if(data.batch!=null)
			    {
			    	$('#batch').html(data.batch.enrollmentName);
			   		$('#batchId').val(data.batch.id);
			    }	
			    else
			    {
			    	$('#batch').html('');
			   		$('#batchId').val(0);
			    }
			    
			    ajax_120_1();//层次	
			}
			
			var studentId=0;
	
		</script>
		<!--全局批次(学习中心)-->
		<a:ajax successCallbackFunctions="ajax_global_batch" parameters="{branchId:jQuery('#branchId').val()}" urls="/enrollment/cascade_global_batch_branch_ajax" pluginCode="100"/>
		
		<!--院校(学习中心、全局批次)-->
		<a:ajax successCallbackFunctions="ajax_academy" parameters="{branchId:jQuery('#branchId').val(),globalBatchId:jQuery('#globalBatchId').val()}" urls="/enrollment/cascade_branch_global_batch_academy_ajax" pluginCode="110"/>
		
		<!--层次(招生批次)-->
		<a:ajax successCallbackFunctions="ajax_level" parameters="{batchId:jQuery('#batchId').val()}" urls="/enrollment/cascade_batch_level_ajax" pluginCode="120"/>
		
		<!--专业(层次)-->
		<a:ajax successCallbackFunctions="ajax_major" parameters="{levelId:jQuery('#levelId').val()}" urls="/enrollment/cascade_level_major_ajax" pluginCode="130"/>
		
		<!--招生批次(院校、全局批次)-->
		<a:ajax successCallbackFunctions="ajax_batch" parameters="{academyId:jQuery('#academyId').val(),globalBatchId:jQuery('#globalBatchId').val()}" urls="/enrollment/cascade_global_batch_academy_batch_ajax" pluginCode="140"/>
		
		
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="报名监控反馈">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">报名监控反馈</th>
						
					</tr>
				</table>
				<form id="search_form" name="search_form">
				<input type="hidden" name="student.noLastMonitorResults" value="<%=Constants.STU_MONITOR_RESULTS_SOURCE_ID %>"/>
				<table class="add_table">
					<tr>
						<td align="right">学习中心：</td>
		                <td align="left">
							${branch.name}
							<input type="hidden" id="branchId" name="student.branchId" value="${branch.id}"/>
						</td>
		                
		                <td align="right">全局批次：</td>
		                <td align="left">
							<select name="globalBatchId" id="globalBatchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td align="right">院校：</td>
		                <td align="left">
		                	<select name="student.academyId" id="academyId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>	
						</td>
		                <td align="right">招生批次：</td>
		                <td align="left">
							<span style="color: black !important;" id="batch" name="batch"></span>
							<input type="hidden" id="batchId" name="student.enrollmentBatchId" value="0"/>
						</td>
						
	              	</tr>
				  	<tr>
				  		<td align="right">层次：</td>
		                <td>
							<select name="student.levelId" id="levelId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td align="right">专业：</td>
		                <td align="left">
							<select name="student.majorId" id="majorId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
						<td align="right">姓名：</td>
		                <td align="left">
		                	<input type="text" name="student.name" id="name" class="txt_box_150"/>					
						</td>
						<td align="right">监控结果：</td>
		                <td align="left">
							<c:if test="${monitorResultslist!=null}">
								<s:select list="monitorResultslist" listValue="name" listKey="id" cssClass="txt_box_130" name="student.lastMonitorResult"  headerKey="0" headerValue="--请选择--" ></s:select>
							</c:if>
							<c:if test="${monitorResultslist==null}">
								<select id="lastMonitorResult" class="txt_box_150" name="student.lastMonitorResult">
									<option value="0">--请选择--</option>
								</select>
							</c:if>
						</td>
	             	</tr>
	             	<tr>
	             		<td align="right"></td>
	             		<td align="left"></td>
						<td align="right"></td>
						<td align="left"></td>
						<td align="right"></td>
						<td align="left"></td>
						<td align="right"></td>
						<td align="center">
							<input type="button" class="btn_black_61"  onclick="search911();" value="查询"/>
						</td>
	             	</tr>
				</table>
				</form>
				<page:plugin 
						pluginCode="911"
						il8nName="crm"
						searchListActionpath="enrollment/enrollmonitor/list_monitor_student"
						searchCountActionpath="enrollment/enrollmonitor/count_monitor_student"
						columnsStr="name;gender;schoolName;majorName;levelName;academyenrollbatchName;status;lastMonitorResultName;#public.operating"
						customColumnValue="1,sexValue(gender);6,statusValue(status);8,operating(id)"
						
						searchFormId="search_form"
						params="'student.monitorStatus':status,'student.gender':-1,'student.callStatus':-1"
						isPackage="false"
					/>
		</body:body>
  </body>
</html>
