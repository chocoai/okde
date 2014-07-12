<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>老生续读(总部呼叫中心)</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		
		<jc:plugin name="page" />
		<jc:plugin name="tab" />
		<jc:plugin name="loading" />
		<jc:plugin name="base_js" />
		
		<script type="text/javascript">
			var sValue=0;
			var studentId=0;
			
			$(document).ready(function()
			{
				//选择中心事件
				//jQuery('#branchId').change(function()
				//{
				//	ajax_130_1();//跟进人
				//});
				//选择学生阶段事件
				jQuery('#jieduan').change(function()
				{
					ajax_120_1();//学生状态级联
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
				
				//加载院校
				ajax_002_1();			
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
			function showxudu(id,newstuId)
			{
				if(newstuId == 0)
					return "<a href='add_student_continue_call?studentId="+id+"'>续读</a>";
				return "";
			}
			//读取学生信息
			function stucontinue(id)
			{
				studentId=id;
				$('#id').val(id);
				$("#school2").attr("value",0);
				$("#pici2").html("");
               	$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
               	$("#cengci2").html("");
               	$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               	$("#zhuanye2").html("");
               	$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
				ajax_001_1();
			}
			
			function ajax_continue(data)
			{
				$('#sname').html(data.student.name);
				$('#ctype').attr("value",data.student.certType);
				if(data.student.certType == CERTIFICATE_TYPE_ID)
				{
					$('#ctype').html('证件号');
				}
				else if(data.student.certType == CERTIFICATE_TYPE_DRIVER_ID)
				{
					$('#ctype').html('护照');
				}
				else if(data.student.certType == CERTIFICATE_TYPE_NCO_ID)
				{
					$('#ctype').html('士官证');
				}
				else
				{
					$('#ctype').html('其他（未知）');
				}
				$('#cno').html(data.student.certNo);
				$('#message_confirm').dialog({
					title:'确认操作',
					buttons: {
						'确认': function() {
								if($('#school2').val()==0)
								{
									alert('请选择院校！');
									return false;
								}
								if($('#pici2').val()==0)
								{
									alert('请选择批次！');
									return false;
								}
								if($('#cengci2').val()==0)
								{
									alert('请选择层次！');
									return false;
								}
								if($('#zhuanye2').val()==0)
								{
									alert('请选择专业！');
									return false;
								}
								ajax_150_1();//续读
								$(this).dialog("close");				
							}, 
						'取消': function() { 
								$(this).dialog("close"); 
							} 
						}
				});
				$('#message_confirm').dialog("open");
			}
				
			//ajax回调函数   学生共有的查询条件
			function ajax_stusearchallajax(data)
			{
				//院校
				jQuery("#academyId").empty();
			    jQuery("#academyId").append('<option value="0">--请选择--</option>');
			    if(data.academyList!=null && data.academyList.length>0)
			    {
			    	$.each(data.academyList,function(){	
			    		jQuery("#academyId").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			   	//全局批次
			   	jQuery("#glopici").empty();
			    jQuery("#glopici").append('<option value="0">--请选择--</option>');
			    if(data.globalBatchList!=null && data.globalBatchList.length>0)
			    {
			    	$.each(data.globalBatchList,function(){	
			    		jQuery("#glopici").append('<option value="'+this.id+'">'+this.title+'</option>'); 
			    	});
			   	}
			   	//层次
			   	jQuery("#cengci").empty();
			    jQuery("#cengci").append('<option value="0">--请选择--</option>');
			    if(data.levelList!=null && data.levelList.length>0)
			    {
			    	$.each(data.levelList,function(){	
			    		jQuery("#cengci").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			   	//基础专业
			   	jQuery("#jczhuanye").empty();
			    jQuery("#jczhuanye").append('<option value="0">--请选择--</option>');
			    if(data.baseMajorList!=null && data.baseMajorList.length>0)
			    {
			    	$.each(data.baseMajorList,function(){	
			    		jQuery("#jczhuanye").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			   	//数据来源
			   	jQuery("#studentDataSource").empty();
			    jQuery("#studentDataSource").append('<option value="0">--请选择--</option>');
			    if(data.studentDataSourceList!=null && data.studentDataSourceList.length>0)
			    {
			    	$.each(data.studentDataSourceList,function(){	
			    		jQuery("#studentDataSource").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
				//市场途径
			   	/**jQuery("#way").empty();
			    jQuery("#way").append('<option value="0">--请选择--</option>');
			    if(data.enrollmentWayList!=null && data.enrollmentWayList.length>0)
			    {
			    	$.each(data.enrollmentWayList,function(){	
			    		jQuery("#way").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}**/
			    $("#way").html("");
   				var wayStr="<option value='" + 0 + "'>--请选择--</option>";
				$.each(data.enrollmentWaysMap,function(key,value){
						wayStr+="<optgroup label='"+key+"'>";
						$(this).each(function(){
								wayStr+="<option value='" + this.id + "'>" + this.name + "</option>";
						});
						wayStr+="</optgroup>";
				});
				$("#way").html(wayStr);
			   	//招生途径
			   	jQuery("#source").empty();
			    jQuery("#source").append('<option value="0">--请选择--</option>');
			    if(data.enrollmentSourceList!=null && data.enrollmentSourceList.length>0)
			    {
			    	$.each(data.enrollmentSourceList,function(){	
			    		jQuery("#source").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			   	//学生阶段
			   	jQuery("#jieduan").empty();
			    jQuery("#jieduan").append('<option value="0">--请选择--</option>');
			    if(data.stuStatusStageList!=null && data.stuStatusStageList.length>0)
			    {
			    	$.each(data.stuStatusStageList,function(){	
			    		jQuery("#jieduan").append('<option value="'+this.stageCode+'">'+this.stage+'</option>'); 
			    	});
			   	}
			}	
			
			//ajax回调函数   学生状态（通过学生阶段级联）
			function ajax_stustatusajax(data)
			{
				//学生状态
				jQuery("#status").empty();
			    jQuery("#status").append('<option value="0">--请选择--</option>');
			    if(data.stuStatusList!=null && data.stuStatusList.length>0)
			    {
			    	$.each(data.stuStatusList,function(){	
			    		jQuery("#status").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			}
			
			//ajax回调函数   跟进人（通过学习中心级联）
			function ajax_stufollowupajax(data)
			{
				// 跟进人
				jQuery("#followUpId").empty();
			    jQuery("#followUpId").append('<option value="0">--请选择--</option>');
			    if(data.userList!=null && data.userList.length>0)
			    {
			    	$.each(data.userList,function(){	
			    		jQuery("#followUpId").append('<option value="'+this.id+'">'+this.fullName+'</option>'); 
			    	});
			   	}
			}
			
			
			//ajax回调函数   

			function ajax_updatestatus(data)
			{
				search001();
				jQuery("#showDialog").html('<b>操作成功！</b>');
				jQuery('#message_returns_tips').dialog("open");	
			}
			
			function initSelect(doc){
				var school=$("#school2");
				var pici=$("#pici2");
				var cengci=$("#cengci2");
				var zhuanye=$("#zhuanye2");
				school.html("");
               	$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
               	pici.html("");
               	$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
               	cengci.html("");
               	$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               	zhuanye.html("");
               	$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
               	//院校
               	$(doc.academysAcademies).each(function(){
					$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
				});
				school.change(function(){
               		sValue=$(this).val();
					pici.trigger("focus");
					if(sValue==0){
               			pici.html("");
               			$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
               			cengci.html("");
               			$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               			zhuanye.html("");
               			$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
						return;
					}
					//招生批次
					ajax_002_2();
				});
				pici.change(function(){
					sValue=$(this).val();
					cengci.trigger("focus");
					if(sValue==0){
               			cengci.html("");
               			$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               			zhuanye.html("");
               			$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
						return;
					}
					//层次
					ajax_002_3();
				});
				cengci.change(function(){
					sValue=$(this).val();
					zhuanye.trigger("focus");
					if(sValue==0){
               			zhuanye.html("");
               			$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
						return;
					}
					//查询专业
					ajax_002_4();
				});
			}
			
			//批次
			function pici(doc){
				$("#pici2").html("");
               	$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo($("#pici2"));
				$(doc.academyEnrollBatchs).each(function(){
					$("<option value='" + this.id + "'>" + this.enrollmentName + "</option>").appendTo($("#pici2"));
				});
			}
			
			//层次
			function cengci(doc){
				$("#cengci2").html("");
               	$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo($("#cengci2"));
				$(doc.academyLevels).each(function(){
					$("<option value='" + this.id + "'>" + this.level.name + "</option>").appendTo($("#cengci2"));
				});
			}
			
			//专业
			function zhuanye(doc){
				$("#zhuanye2").html("");
               	$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo($("#zhuanye2"));
				$(doc.academyMajors).each(function(){
					 $("<option value='" + this.majorId + "'>" + this.majorName + "</option>").appendTo($("#zhuanye2"));
				});
			}	
		</script>
		
		<!-- 学生共有的查询条件 -->
		<a:ajax 
			pluginCode="110"
			successCallbackFunctions="ajax_stusearchallajax" 
			urls="/crm/for_stu_search_all_ajax"
			isOnload="all" 			
		/>
		<!-- 学生状态（通过学生阶段级联）-->
		<a:ajax 
			pluginCode="120"
			successCallbackFunctions="ajax_stustatusajax" 
			parameters="{stuStatusStage:jQuery('#jieduan').val()}"
			urls="/crm/stu_status_list_for_stu_search_ajax"
			isOnload="all" 			
		/>
		
		<!-- 跟进人（通过学习中心级联）-->
		<a:ajax 
			pluginCode="130"
			successCallbackFunctions="ajax_stufollowupajax" 
			parameters="{branchId:jQuery('#branchId').val()}"
			urls="/crm/stu_followup_list_for_stu_search_ajax"
			isOnload="all" 			
		/>
		
		<!-- 续读 -->
		<a:ajax 
			pluginCode="150"
			parameters="$('#create_student_form').serializeObject()" 
			successCallbackFunctions="ajax_updatestatus"
			urls="finance/studentsenrolled/upate_student_continue_call_ajax"
		/>
		<!-- 读取学生信息  -->
		<a:ajax 
			pluginCode="001"
			parameters="{studentId:studentId}"
			successCallbackFunctions="ajax_continue"
			urls="finance/studentsenrolled/view_student_continue_call_ajax"
		/>
		
		<!-- 院校下拉列表级联 -->
		<a:ajax pluginCode="002"
			urls="/crm/academys_academie_list;/crm/academy_enroll_batch_list_input;/crm/level_list;/crm/base_majors_list;/crm/add_student_lc"
			parameters="null;{'id':sValue};{'id':sValue,'academyId':parseInt($('#school2').val())};{'id':sValue};$('#create_student_form').serializeObject()"
			successCallbackFunctions="initSelect;pici;cengci;zhuanye;addStudentCallBack" />
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="老生续读（总部呼叫中心）">
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="search_form">
					<table class="add_table" cellpadding="2" cellspacing="2">
						<tr>
							  <td align="right">学习中心：</td>
							  <td align="left">		
							    <!-- ${branch.name} -->
							    <select class="txt_box_130" name="student.branchId">
							    	<option value="0">--请选择--</option>
							    <s:iterator value="%{branchList}" var="branchTemp">
							    	<option value="${branchTemp.id}">${branchTemp.name}</option>
							    </s:iterator>
							    </select>
							  	<!-- <input type="hidden" name="student.branchId" id="branchId" value="${branch.id}"/> -->
							  </td>	
							  <td align="right">院校：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.academyId" id="academyId">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
							  <td align="right">全局批次：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.glbtach" id="glopici">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
							  <td align="right">层次：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.levelId" id="cengci">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
							  <td align="right">基础专业：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.glmajor" id="jczhuanye">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
						</tr>
						<tr>
								<td align="right">数据来源：</td>
								<td align="left">
									<select id="studentDataSource" name="student.studentDataSource" class="txt_box_130" >
										<option value="0">--请选择--</option>
									</select>
								</td>
								<td align="right">招生途径：</td>
								<td align="left">
									<select id="source" name="student.enrollmentSource" class="txt_box_130">
										<option value="0">--请选择--</option>
									</select>
								</td>
								<td align="right">市场途径：</td>
								<td align="left">
									<select id="way" name="student.enrollmentWay" class="txt_box_130">
										<option value="0">--请选择--</option>
									</select>
								</td>
								<td align="right">监控状态：</td>
								<td align="left">
									<select class="txt_box_130" id="jiankong" name="student.monitorStatus">
										<option value="-1">--请选择--</option>
										<option value="<%=Constants.STU_MONITOR_STATUS_WEI_JIAN_KONG %>">未监控</option>
										<option value="<%=Constants.STU_MONITOR_STATUS_JIAN_KONG_ZAI_SHENG_QIN %>">监控再申请</option>
										<option value="<%=Constants.STU_MONITOR_STATUS_JIAN_KONG_YI_CHENG_GONG %>">已监控已成功</option>
										<option value="<%=Constants.STU_MONITOR_STATUS_JIAN_KONG_WEI_CHENG_GONG %>">已监控未成功</option>
									</select>
								</td>
								<td align="right">学费状态：</td>
								<td align="left">
									<select class="txt_box_130" id="jiaofei" name="student.tuitionStatus">
										<option value="-1">--请选择--</option>
										<option value="<%=Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI %>">未缴学费</option>
										<option value="<%=Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI %>">首次缴费</option>
										<option value="<%=Constants.STU_TUITION_STATUS_DUO_CI_JIAO_FEI %>">多次缴费</option>
										<option value="<%=Constants.STU_TUITION_STATUS_JIAO_FEI_WAN_CHENG %>">缴费完成</option>
									</select>
								</td>
						</tr>
							<tr>
								<td align="right">姓名：</td>
								<td align="left">
									<input  name="student.name" class="txt_box_130" type="text" value="" />
								</td>
								<td align="right">证件号：</td>
								<td align="left">
									<input  name="student.certNo" class="txt_box_130" type="text" />
								</td>
								<td align="right">手机/座机：</td>
								<td align="left">
									<input name="student.phone" class="txt_box_130" type="text" />
								</td>
								<td align="right">学号：</td>
								<td align="left">
									<input  name="student.number" class="txt_box_130" type="text" />
								</td>
								<td align="right">跟进人：</td>
								<td align="left">
									<select id="followUpId" class="txt_box_130"  name="student.followUpId">
										<option value="0">--请选择--</option>
									</select>
								</td>
							</tr>
							<tr>
								
								<td align="right" colspan="9"></td>
								<td align="left">
									<input  type="button" class="btn_black_61" onclick="search001()" value="查询"/>
								</td>
							</tr>
							
							
							
					</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="list_student_continue_call_page_ajax"
										searchCountActionpath="count_student_continue_call_page_ajax"
										columnsStr="name;branchName;certNo;schoolName;academyenrollbatchName;levelName;majorName;enrollmentSourceName1;status;#operation"
										customColumnValue="8,statusValue(status);9,showxudu(id,newstuId)"
										pageSize="10"
										searchFormId="search_form"
										view="http,/crm/view_home,studentId,id,_blank"
										params="'result.order':'status','result.sort':'asc'"
										isonLoad="true"
									/>
					
			</body:body>
		<!--弹出层-->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="message_confirm" style="display:none">
			<div align="center" id="showconfirm">
				<form id="create_student_form">
					<input type="hidden" id="id" name="student.id" value="0"/>
					<table class="gv_table_2">
						<tr>
							<td align="right">姓名：</td>
							<td><span id="sname"></span></td>
						</tr>
						<tr>
							<td align="right">证件类型：</td>
							<td><span id ="ctype"></span></td>
						</tr>
						<tr>
							<td align="right">证件号码：</td>
							<td><span id="cno">无</span></td>
						</tr>
						<tr>
							<td align="right">院校：</td>
							<td><select class="txt_box_150" id="school2" name="student.academyId"></select></td>
						</tr>
						<tr>
							<td align="right">批次：</td>
							<td><select class="txt_box_150" id="pici2" name="student.enrollmentBatchId"></select></td>
						</tr>
						<tr>
							<td align="right">层次：</td>
							<td><select class="txt_box_150" id="cengci2" name="student.levelId"></select></td>
						</tr>
						<tr>
							<td align="right">专业：</td>
							<td><select class="txt_box_150" id="zhuanye2" name="student.majorId"></select></td>
						</tr>
					</table>
				</form>
			</div>
		</div>		
	</body>
	
</html>