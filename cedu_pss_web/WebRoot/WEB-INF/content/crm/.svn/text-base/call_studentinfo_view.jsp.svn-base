
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生跟进(详细)</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 分页 -->
		<jc:plugin name="page" />
		<jc:plugin name="loading" />

		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />		
		<script type="text/javascript">
			//回调函数
			function sendMail(data){
				$('#message_confirm').dialog("close");
				$('#send_mail').dialog("close");
			}
			//批次
			function pici(doc){
				$("#pici").html("");
               	$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo($("#pici"));
				$(doc.academyEnrollBatchs).each(function(){
					if('${student.enrollmentBatchId}'==this.id){
						sValue=this.id;
						$("<option selected='selected' value='" + this.id + "'>" + this.enrollmentName + "</option>").appendTo($("#pici"));
						$('#globalBatchIdSp').html(this.enrollmentName);
					}else{
						$("<option value='" + this.id + "'>" + this.enrollmentName + "</option>").appendTo($("#pici"));
					}
				});
				ajax_002_4();//层次，中心
			}
			//层次学习中心
			function cengci(doc){
				$("#cengci").html("");
               	$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo($("#cengci"));
				$(doc.academyLevels).each(function(){
					if('${student.levelId}'==this.level.id){
						sValue=this.id;
						$("<option  selected='selected' value='" + this.id + "'>" + this.level.name + "</option>").appendTo($("#cengci"));
						$('#levelIdSp').html(this.level.name);
					}else{
						$("<option value='" + this.id + "'>" + this.level.name + "</option>").appendTo($("#cengci"));
					}
				});
							
				$("#branch").html("");
               	$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo($("#branch"));
				$(doc.branchlist).each(function(){
					
					if('${student.branchId}'==this.id){
						$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
						$('#branchSp').html(this.name);
					}else{
						$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
					}
				});
				ajax_002_5();//专业
			}
			//专业
			function zhuanye(doc){
				$("#zhuanye").html("");
               	$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo($("#zhuanye"));
				$(doc.academyMajors).each(function(){
					if('${student.majorId}'==this.majorId){
					 	$("<option selected='selected' value='" + this.majorId + "' title='" + this.majorName + "' >" + this.majorName + "</option>").appendTo($("#zhuanye"));
					 	$('#majorIdSp').html(this.majorName);
					 }else{
					 	$("<option value='" + this.majorId + "' title='" + this.majorName + "'  >" + this.majorName + "</option>").appendTo($("#zhuanye"));
					 }
				});
			}
			
			//更新学生
			function updateStudentCallBack(doc){
				if(doc.student!=null){
					//保存成功
					alert('保存成功！！');
					//刷新学生跟进记录
					studentId=doc.student.id;
					//学生隐藏ID
					$("#student_id").val(studentId);
					//$("#sfzh").attr("disabled","true");
				}
				$("#save").removeAttr("disabled");
				search001();
			}
			
			
		</script>
		
		<a:ajax 
			pluginCode="002"
			urls="template/send_mail;/crm/academys_academie_list;/crm/academy_enroll_batch_list;/crm/level_list;/crm/base_majors_list;/crm/update_student_lc" 
			parameters="$('#send_mail_form').serializeObject();null;{'id':sValue};{'id':sValue,'academyId':parseInt($('#school').val())};{'id':sValue};$('#create_student_form').serializeObject()" 
			successCallbackFunctions="sendMail;initSelect;pici;cengci;zhuanye;updateStudentCallBack" 
		/>
		<script type="text/javascript">
			var pcas;
			var studentId=${student.id};
			var sValue=0;
			function init(){
				$('#send_mail').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'发送邮件',
					width: 500,
					height: 340,
					buttons: {
						'发送': function() { 
							$('#message_confirm').dialog({
								buttons: {
									'确认': function() { 
										//发送邮件
										ajax_002_1();
									}, 
									'取消': function() { 
										$(this).dialog("close"); 
									} 
								}
							});
							$('#message_confirm').dialog("open"); 
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				$('#message_confirm').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'提示',
					width: 260,
					height: 125
					
				});
				
				$('#lianxifangshi').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'提示',
					width: 260,
					height: 125
					
				});
			}
			function initSelect(doc){
					var school=$("#school");
					var pici=$("#pici");
					var cengci=$("#cengci");
					var zhuanye=$("#zhuanye");
					var status=$("#stuStatus");
					var branch=$("#branch");
				
					school.html("");
               		$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
               		pici.html("");
               		$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
               		cengci.html("");
               		$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               		zhuanye.html("");
               		$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
               		status.html("");
               		$("<option value='" + 0 + "'>请选择学生状态</option>").appendTo(status);
               		branch.html("");
               		$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo(branch);
               				
					$(doc.academysAcademies).each(function(){
							if('${student.academyId}'==this.id){
								sValue=this.id;
								$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
								$('#academyIdSp').html(this.name);
							}else{
								$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
							}
					});
					if('${student.academyId}'=='0'||'${student.academyId}'=='null'){
						//return;
					}else{
						//招生批次
						ajax_002_3();
					}
					
				//注册事件
				school.change(function(){
					sValue=$(this).val();
					pici.trigger("focus");
					if(sValue==0){
							//school.html("");
               				//$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
               				pici.html("");
               				$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
               				branch.html("");
               				$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo(branch);
							return;
					}
					//招生批次
					ajax_002_3();
					
				});
				
				pici.change(function(){
					sValue=$(this).val();
					cengci.trigger("focus");
					branch.trigger("focus");
					if(sValue==0){
               				//pici.html("");
               				//$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
               				branch.html("");
               				$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo(branch);
							return;
					}
					//层次
					ajax_002_4();
				});
				cengci.change(function(){
					sValue=$(this).val();
					zhuanye.trigger("focus");
					if(sValue==0){
               				//cengci.html("");
               				//$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
							return;
					}
					//查询专业
					ajax_002_5();
				});
			}
			//增加学生
			function updateStudent(){
				//增加学生
				ajax_002_6();
			}
			//呼叫等级
			function callGradeValue(callStatusId){
				return callStatusId.getCallGrade();
			}
			//跟进状态
			function creatorValue(creatorId){
				
				return creatorId;
			}
			$(document).ready(function(){
				//pcas=new $.PCAS({province:'select[name=student.livingPlace]',provinceV:"${student.livingPlace}"});
				
				//选中下拉菜单
				$("#livingPlace").val("${student.livingPlace}");
				$("#sex").val("${student.gender}");
				$("#status").val('${student.status}');
				$('#call_status').val('${student.callStatusId}');
				
				$("#livingPlaceSp").html($("#livingPlace").val());
				
				if(${student.gender}==1)
					$("#sexSp").html('男');
				else if(${student.gender}==0)
					$("#sexSp").html('女');
				else
					$("#sexSp").html('');
				
				if(${student.status}==STU_CALL_STATUS_YI_FENG_PEI){$('#statusSp').html('已分配');}
				if(${student.status}==STU_CALL_STATUS_WU_YI_YUAN){$('#statusSp').html('无意愿');}
				if(${student.status}==STU_CALL_STATUS_GENG_JIN_ZHONG){$('#statusSp').html('跟进中');}
				
				if('${student.status}'=='<%=Constants.STU_CALL_STATUS_GENG_JIN_ZHONG %>')
				{
					$('#call_status').removeAttr('disabled')
					if(${student.callStatusId}==CALL_GRADE_A){$('#call_statusSp').html('A级：++++++');}
					if(${student.callStatusId}==CALL_GRADE_B){$('#call_statusSp').html('B级：+++++');}
					if(${student.callStatusId}==CALL_GRADE_C){$('#call_statusSp').html('C级：++++');}
					if(${student.callStatusId}==CALL_GRADE_D){$('#call_statusSp').html('D级：+++');}
					if(${student.callStatusId}==CALL_GRADE_E){$('#call_statusSp').html('E级：++');}
					if(${student.callStatusId}==CALL_GRADE_F){$('#call_statusSp').html('F级：+');}
				}
				else
				{
					$('#call_status').val(0);
					$('#call_status').attr('disabled','true');
					$('#call_statusSp').html('无');
				}
				
				

				
				//初始化
				init();
				ajax_002_2();
				
				
			
			});
	
		</script>
		
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="学生跟进(详细)">
		</head:head>
		<!--主体层开始 -->
		<body:body>
					<div id="message_confirm" style="display:none">
						<div>确认执行该操作！</div>
					</div>
					<div id="lianxifangshi" style="display:none">
						<div>请填写手机或座机！</div>
					</div>
					
					
					<div id="send_mail" style="display:none">
						<form id="send_mail_form">
							 <table class="add_table">
							 	<tr>
							 		<td align="right">邮件地址：</td>
							 		<td><input type="text" class="txt_box_200" name="eamilAddress" /></td>
							 	</tr>
							 	<tr>
							 		<td align="right">标题：</td>
							 		<td><input type="text" class="txt_box_200" name="title" /></td>
							 	</tr>
							 	
							 	<tr>
							 		<td align="right" valign="top">邮件正文：</td>
							 		<td><textarea class="txt_box_335" rows="10" name="context"></textarea></td>
							 	</tr>
							 </table>
						</form> 
					</div>
					<table style="width: 100%; border: 0px;"">

						<tr>
							<td style="width: 44%;" valign="top">
									<table class="gv_table_2">
										<tr>
											<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/plugin/base_css/images/operating/title_left.gif" /></th>
										 	<th style="text-align:left; font-weight:bold;">基本信息</th>
										</tr>
									</table>
									<table  class="add_table" >
										<tr>
											<td align="right" width="100px;"> 数据来源：</td>
						
											<td align="left">
													<script type="text/javascript">
														var stuDataSource=${student.studentDataSource};
														document.write(stuDataSource.getStudentDatasource());
													</script>
												
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;"> 姓名：</td>
											<td align="left">${student.name}</td>
										</tr>
						
										<tr>
											<td align="right" width="100px;"> 省/市：</td>
											<td align="left">${student.livingPlace}</td>
										</tr>
										<tr>
											<td align="right" width="100px;"> 座机：</td>
						
											<td align="left">${student.phone}</td>
										</tr>
										<tr>
											<td align="right" width="100px;"> 手机：</td>
											<td align="left">${student.mobile}</td>
										</tr>
										<tr>
						
											<td align="right" width="100px;"> 学历：</td>
											<td align="left">
													<script type="text/javascript">
														var degree=${student.degree};
														document.write(degree.getStuDegree());
													</script>
											</td>
										</tr>
										
										<tr>
											<td align="right" width="100px;"> 备注：</td>
											<td align="left">${student.remark}</td>
										</tr>
						
									</table>
									<table class="gv_table_2">
										<tr>
											<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/plugin/base_css/images/operating/title_left.gif" /></th>
											<th style="text-align: left; font-weight: bold;">
												当前联系人呼叫记录
											</th>

										</tr>
									</table>
									<page:plugin 
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="crm_followup_list"
										searchCountActionpath="crm_followup_count"
										columnsStr="followUpName;createdTime;callStatusId;remark;followUpBranchName;"
										customColumnValue="2,callGradeValue(callStatusId);"
										pageSize="10"
										subStringLength="1000"
										columnsWidth="[3,300px]"
										isNumber="true"
										params="'followUp.studentId':studentId,'result.order':'createdTime','result.sort':'desc'"
									/>
									<!--params='followUp.statusId':STU_CALL_STATUS_GENG_JIN_ZHONG-->
							</td>
							<td style="width: 56%;" valign="top">
							<form id="create_student_form">
								<input type="hidden" id="student_id" name="student.id" value="${student==null?0:student.id}"/>
								<table class="gv_table_2">
										<tr>
											<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/plugin/base_css/images/operating/title_left.gif" /></th>
										<th style="text-align: left; font-weight: bold;">
											基本资料
										</th>

									</tr>
								</table>

								<table class="add_table" border="0">
									<tr>

										<td align="right" width="100px;">
											姓名：
										</td>
										<td align="left" valign="middle">
											<span style="color:black;">${student.name}</span>
										</td>
										
									</tr>
									<tr>
										<td align="right" width="100px;">
											手机：
										</td>
										<td align="left">
											<span style="color:black;">${student.mobile}</span>
										</td>
									</tr>
									<tr>
									<td align="right" width="100px;">
											座机：
										</td>
										<td align="left">
											<span style="color:black;">${student.phone}</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											性别：
										</td>
										<td align="left">
											
											<select style="display:none;" id="sex" class="txt_box_100" name="student.gender">
														<option selected="selected" value="-1">--请选择--</option>
														<option value="<%=Constants.SEX_MALE %>">男</option>
														<option value="<%=Constants.SEX_FAMALE %>">女</option>
											</select>	
											<span style="color:black;" id="sexSp" ></span>
										</td>
									</tr>

									<tr>
										<td align="right" width="100px;">
											所在城市：
										</td>
										<td align="left">
											<select style="display:none;" name="student.livingPlace" id="livingPlace" class="txt_box_150">
												<%@ include file="city.jsp" %>
											</select>
											<span style="color:black;" id="livingPlaceSp"></span>
										</td>
									</tr>
							
									<tr>
										<td align="right" width="100px;">
											证件号：
										</td>
										<td align="left">
											<span style="color:black;">${student.certNo}</span>
										</td>
									</tr>
								
									<tr>
										<td align="right" width="100px;">
											MSN：
										</td>
										<td align="left">
											<span style="color:black;">${student.msn}</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											QQ：
										</td>

										<td align="left">
											<span style="color:black;">${student.qq}</span>
										</td>
									</tr>

									<tr>
										<td align="right" width="100px;">
											邮件：
										</td>
										<td align="left">
											<span style="color:black;">${student.email}</span>
										</td>
									</tr>
									<c:if test="${student.email!=null&&student.email!=''}">
									<tr>
										<td align="right" width="100px;">
											发送邮件：
										</td>

										<td align="left">
											<a href="#" onclick="$('#send_mail').dialog('open');">发送邮件</a>
										</td>
									</tr>
									</c:if>
									<tr>
										<td align="right" width="100px;">
											备注：
										</td>
										<td align="left">
											<span style="color:black;">${student.remark}</span>
										</td>
									</tr>
									
								</table>
								<table class="gv_table_2">
										<tr>
											<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/plugin/base_css/images/operating/title_left.gif" /></th>
										<th style="text-align: left; font-weight: bold;">
											初步意向
										</th>

									</tr>
								</table>
				<c:if test="${student.status>=6 }">
								<table class="add_table">
									<tr>
										<td align="right" width="100px;">
											院校：
										</td>
										<td align="left">
											
											<select style="display:none;" disabled="disabled" class="txt_box_150" id="school" name="student.academyId"></select>
											<span style="color:black;" id="academyIdSp"></span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" >
											批次：
										</td>
										<td align="left">
											
											<select style="display:none;" disabled="disabled" class="txt_box_150" id="pici" name="student.enrollmentBatchId"></select>
											<span style="color:black;" id="globalBatchIdSp"></span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" >
											学习中心：
										</td>
										<td align="left">
											
											<select style="display:none;" disabled="disabled" class="txt_box_150" id="branch" name="student.branchId"></select>
											<span style="color:black;" id="branchSp"></span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											层次：
										</td>
										<td align="left">
											
											<select style="display:none;" disabled="disabled" class="txt_box_150" id="cengci" name="student.levelId"></select>
											<span style="color:black;" id="levelIdSp"></span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											专业：
										</td>
										<td align="left">
											<select style="display:none;" disabled="disabled" class="txt_box_150" id="zhuanye" name="student.majorId"></select>
											<span style="color:black;" id="majorIdSp"></span>
										</td>
									</tr>
								</table>
					
								<table class="gv_table_2">
										<tr>
											<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/plugin/base_css/images/operating/title_left.gif" /></th>
										<th style="text-align: left; font-weight: bold;">
											报名
										</th>

									</tr>
								</table>
				</c:if>
				<c:if test="${student.status<6 }">
								<table class="add_table">
									<tr>
										<td align="right" width="100px;">
											院校：
										</td>
										<td align="left">
											
											<div id="schoolTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<select style="display:none;" class="txt_box_150" id="school" name="student.academyId"></select>
											<span style="color:black;" id="academyIdSp"></span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" >
											批次：
										</td>
										<td align="left">
											
											<div id="piciTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<select style="display:none;" class="txt_box_150" id="pici" name="student.enrollmentBatchId"></select>
											<span style="color:black;" id="globalBatchIdSp"></span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" >
											学习中心：
										</td>
										<td align="left">
											
											<div id="branchTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<select style="display:none;" disabled="disabled" class="txt_box_150" id="branch" name="student.branchId"></select>
											<span style="color:black;" id="branchSp"></span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											层次：
										</td>
										<td align="left">
											
											<div id="cengciTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<select style="display:none;" class="txt_box_150" id="cengci" name="student.levelId"></select>
											<span style="color:black;" id="levelIdSp"></span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											专业：
										</td>
										<td align="left">
											
											<div id="zhuanyeTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<select style="display:none;" class="txt_box_150" id="zhuanye" name="student.majorId"></select>
											<span style="color:black;" id="majorIdSp"></span>
										</td>
									</tr>
								</table>
					
								<table class="gv_table_2">
										<tr>
											<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/plugin/base_css/images/operating/title_left.gif" /></th>
										<th style="text-align: left; font-weight: bold;">
											报名
										</th>

									</tr>
								</table>
				</c:if>
			<c:if test="${student.status>=6 }">
							<table class="add_table">
									<tr>

										<td align="right" width="100px;">
											
											学生状态：
										</td>
										<td align="left">
											<script type="text/javascript">
												document.write(parseInt('${student.status}').getStudentStatus());
											</script>
											<input type="hidden" id="status" name="student.status" value="${student.status}" />
										</td>
									</tr>


							</table>			
			</c:if>
			<c:if test="${student.status<6 }">
								<table class="add_table">
									<tr>
										<td align="right" width="100px;" >
											该学生状态：
										</td>
										<td align="left">
										<div id="status_div">
											<span style="color:black;" id="statusSp"></span>
										</div>
										</td>
										
									</tr>
									
									<tr>
										<td align="right" width="100px;">
											呼叫等级：
										</td>
										<td align="left">
											<select style="display:none;" id="call_status" class="txt_box_200" name="followUp.callStatusId">
												<option value="0">
													请选择呼叫等级
												</option>
												<option value="<%=Constants.CALL_GRADE_A %>">
													A级：++++++
												</option>
												<option value="<%=Constants.CALL_GRADE_B %>">
													B级：+++++
												</option>

												<option value="<%=Constants.CALL_GRADE_C %>">
													C级：++++
												</option>
												<option value="<%=Constants.CALL_GRADE_D %>">
													D级：+++
												</option>
												<option value="<%=Constants.CALL_GRADE_E %>">
													E级：++
												</option>
												<option value="<%=Constants.CALL_GRADE_F %>">
													F级：+
												</option>

											</select>
											<span style="color:black;" id="call_statusSp"></span>
										</td>
									</tr>
									
									<tr>
										<td align="right" width="100px;">
											希望联系时间：
										</td>

										<td align="left">
											
											<span style="color:black;">${student.serviceTime}</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" >
											提示您回访跟进：
										</td>
										<td align="left">
											<span style="color:black;">${student.returnVisitTime}</span>
										</td>
									</tr>
								</table>
								
								<table class="gv_table_2">
										<tr>
											<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/plugin/base_css/images/operating/title_left.gif" /></th>

										<th style="text-align: left; font-weight: bold;">
											跟进
										</th>
									</tr>
								</table>
								<table class="add_table">
									<tr>
										<td align="right" width="100px;">
											当前跟进人：
										</td>
										<td align="left">
											${session.userTicket.fullName}
										</td>
									</tr>


								</table>
		</c:if>
								
								
								
							 </form>
							</td>
						</tr>
					</table>

			</body:body>
	</body>

</html>