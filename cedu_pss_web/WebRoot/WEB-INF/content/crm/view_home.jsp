<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生详情</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />

		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<jc:plugin name="loading" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- 分页 -->
		<jc:plugin name="page" />
		<script type="text/javascript">
		//回访
		function followUpNameValue(followUpName){
			return followUpName;
		}
		//呼叫等级
		function callGradeValue(callStatusId){
			return callStatusId.getCallGrade();
		}
		
		$(document).ready(function(){
			
			
			//性别
			if(${student.gender}==0)
			{$('#sexSp').html('女');}
			else if(${student.gender}==1)
			{$('#sexSp').html('男');}
			else
			{$('#sexSp').html('未知');}
			//证件类型
			if(${student.certType}==CERTIFICATE_TYPE_ID)
			{$('#zhengjianSp').html('身份证');}
			else if(${student.certType}==CERTIFICATE_TYPE_DRIVER_ID)
			{$('#zhengjianSp').html('驾照');}
			else if(${student.certType}==CERTIFICATE_TYPE_NCO_ID)
			{$('#zhengjianSp').html('士官证');}
			else
			{$('#zhengjianSp').html('未知');}
		})
		</script>
		
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="${student==null?'呼叫首页':'学生详情'}">
		</head:head>
		<!--主体层开始 -->
		<body:body>
					<table style="width: 100%; border: 0px;">

						<tr>
							<td style="width: 44%;" valign="top">
									
									<table class="gv_table_2">
										<tr>
											<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/plugin/base_css/images/operating/title_left.gif" /></th>
											<th style="text-align:left; font-weight:bold;">客服回访记录</th>					
										</tr>
									</table>
									<page:plugin 
										pluginCode="003"
										il8nName="crm"
										searchListActionpath="crm_followup_list"
										searchCountActionpath="crm_followup_count"
										columnsStr="followUpName;createdTime;callStatusId;remark;followUpBranchName;"
										customColumnValue="2,callGradeValue(callStatusId);"
										pageSize="10"
										isNumber="false"
										subStringLength="1000"
										columnsWidth="[3,300px]"
										params="'followUp.studentId':'${student.id }','result.order':'createdTime','result.sort':'desc'"
									/>
									
									<table class="gv_table_2">
										<tr>
											<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/plugin/base_css/images/operating/title_left.gif" /></th>
											<th style="text-align:left; font-weight:bold;">招生途径变更记录</th>					
										</tr>
									</table>
									<page:plugin 
										pluginCode="002"
										il8nName="student_enrollment_source_change_log"
										searchListActionpath="crm_student_enrollment_source_change_log_list"
										searchCountActionpath="crm_student_enrollment_source_change_log_count"
										columnsStr="params.changeName;changeDate;params.changeContent"
										pageSize="10"
										isNumber="true"
										params="'studentEnrollmentSourceChangeLog.studentId':'${student.id }'"
										
									/>
									
							</td>
							<td style="width: 56%;" valign="top">
									<c:if test="${request.students!=null}">
											<table class="gv_table_2">
												<tr>
													<th style="width: 20px;">
														<img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" />
													</th>
													<th style="text-align: left; font-weight: bold;">
														电话号码匹配人员
													</th>
												</tr>
											</table>
											<table class="gv_table_2" border="0"  cellpadding="0" cellspacing="0" width="100%">
												<thead>
														<tr>
															<th align="center">姓名</th>
															<th align="center">身份证</th>
															<th align="center">手机</th>	
															<th align="center">状态</th>
															<th align="center">招生批次</th>
														</tr>
												</thead>
												<tbody>
													<c:forEach var="stu" items="${request.students}">
														<tr>
															
															<td width="80px" align="center" style="background-color: yellow;">
																<a href="<%=Constants.WEB_PATH %>/crm/view_home?phone=${stu.mobile}&studentId=${stu.id}" target="_blank" style="cursor: pointer;">${stu.name}</a>
															</td>
															<td align="center" style="background-color: yellow;">
																${stu.certNo}
															</td>
															<td align="center" style="padding-left: 10px;background-color: yellow;" >
																${stu.mobile}
															</td>
															<td align="center" style="background-color: yellow;">
																<c:if test="${stu!=null}">
																	<script type="text/javascript">
																		document.write(parseInt('${stu.status}').getStudentStatus());
																	</script>
																</c:if>
															</td>
															<td align="center" style="background-color: yellow;">
																${stu.enrollmentBatchName}
															</td>
														</tr>
													</c:forEach>
												</tbody>
											
										</c:if>
							<form id="create_student_form" style="margin:0px;">
								<input type="hidden" id="student_id" name="student.id" value="${student==null?0:student.id}"/>
								
								<table class="gv_table_2">
										<tr>
											<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/plugin/base_css/images/operating/title_left.gif" /></th>
											<th style="text-align:left; font-weight:bold;">基本资料</th>					
										</tr>
									</table>

								<table class="add_table" border="0">
									<tr>

										<td align="right" width="100px;">
											<span></span>姓名：
										</td>
										<td align="left" valign="middle">
											<c:if test="${student.name!=null&&student.name!=''}">
											<span style="color:black;">${student.name}</span>
											</c:if>
											<c:if test="${student.name==null||student.name==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
										
									</tr>
									<tr>
										<td align="right" width="100px;">
											手机：
										</td>
										<td align="left">
											<c:if test="${student.mobile!=null&&student.mobile!=''}">
											<span style="color:black;">${student.mobile}</span>
											</c:if>
											<c:if test="${student.mobile==null||student.mobile==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
									</tr>
									<tr>
									<td align="right" width="100px;">
											座机：
										</td>
										<td align="left">
											<c:if test="${student.phone!=null&&student.phone!=''}">
											<span style="color:black;">${student.phone}</span>
											</c:if>
											<c:if test="${student.phone==null||student.phone==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											性别：
										</td>
										<td align="left">
											<span style="color:black;" id="sexSp" ></span>	
										</td>
									</tr>

									<tr>
										<td align="right" width="100px;">
											所在城市：
										</td>
										<td align="left">
											<c:if test="${student.livingPlace!=null&&student.livingPlace!=''}">
											<span style="color:black;">${student.livingPlace}</span>
											</c:if>
											<c:if test="${student.livingPlace==null||student.livingPlace==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
									</tr>
							
									<tr>
										<td align="right" width="100px;">
											证件类型：
										</td>
										<td align="left">
											<span style="color:black;" id="zhengjianSp">${student.livingPlace}</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											身份证号：
										</td>
										<td align="left">
											<c:if test="${student.certNo!=null&&student.certNo!=''}">
											<span style="color:black;">${student.certNo}</span>
											</c:if>
											<c:if test="${student.certNo==null||student.certNo==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
									</tr>
							
									<tr>
							
										<td align="right" width="100px;">
											MSN：
										</td>
										<td align="left">
											<c:if test="${student.msn!=null&&student.msn!=''}">
											<span style="color:black;">${student.msn}</span>
											</c:if>
											<c:if test="${student.msn==null||student.msn==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											QQ：
										</td>

										<td align="left">
											<c:if test="${student.qq!=null&&student.qq!=''}">
											<span style="color:black;">${student.qq}</span>
											</c:if>
											<c:if test="${student.qq==null||student.qq==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
									</tr>

									<tr>
										<td align="right" width="100px;">
											邮件：
										</td>
										<td align="left">
											<c:if test="${student.email!=null&&student.email!=''}">
											<span style="color:black;">${student.email}</span>
											</c:if>
											<c:if test="${student.email==null||student.email==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											单位信息：
										</td>
										<td align="left">
											<c:if test="${student.workUnitInfo!=null&&student.workUnitInfo!=''}">
											<span style="color:black;">${student.workUnitInfo}</span>
											</c:if>
											<c:if test="${student.workUnitInfo==null||student.workUnitInfo==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
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
										<td align="right" width="100px;">
											备注：
										</td>
										<td align="left">
											<c:if test="${student.remark!=null&&student.remark!=''}">
											<span style="color:black;">
											
												<textarea  cols="50" style="border:0px;background:none repeat scroll 0 0 #EFEFEF">${student.remark}</textarea>
											
											</span>
												
											</c:if>
											<c:if test="${student.remark==null||student.remark==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
									</tr>
									
									<tr>
										<td align="right" width="100px;">
											<span></span>招生途径：
										</td>
										<td align="left">
											<span style="color:black;" id="source" >${student.enrollmentSourceName}</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" id="hezuofang">
											合作方：
										</td>
										<td align="left" id="hezuofang_t">
										<span style="color:black;" id="channel" >${student.channelName }</span>
									
										</td>
									</tr>
									<tr>
									<input type="hidden" id="enrollmentWayName" name="student.enrollmentWayName" />
										<td align="right" width="100px;">
											<span></span>市场途径：
										</td>
										<td align="left">
											<span style="color:black;" id="way">${student.enrollmentWayName }</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" id="hezuofang">
											数据来源：
										</td>
										<td align="left" id="hezuofang_t">
										<span style="color:black;" id="channel" >${student.studentDataSourceName }</span>
										
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
					
								<table class="add_table">
									<tr>
										<td align="right" width="100px;" >
											<span></span>学习中心：
										</td>
										<td align="left">
											<div id="branchTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<span style="color:black;" id="branchSp">${student.branchName }</span>
										</td>
									</tr>
									
									<tr>
										<td align="right" width="100px;">
											院校：
										</td>
										<td align="left">
											<span style="color:black;" id="academyIdSp">${student.schoolName }</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" >
											招生批次：
										</td>
										<td align="left">
											<span id="batchName" name="batch" style="color: black">${student.enrollmentBatchName }</span>
										</td>
									</tr>
									
									<tr>
										<td align="right" width="100px;">
											层次：
										</td>
										<td align="left">
											<span style="color:black;" id="levelIdSp">${student.levelName }</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											专业：
										</td>
										<td align="left">
											<span style="color:black;" id="majorIdSp">${student.majorName }</span>
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

								<table class="add_table">
									<tr>
										<td align="right" width="100px;" >
											学生状态：
										</td>
										<td align="left">
										<div id="status_div">
										<c:if test="${student!=null}">
											<script type="text/javascript">
												document.write(parseInt('${student.status}').getStudentStatus());
											</script>
											<input type="hidden" id="status" name="student.status" value="${student.status}" />
										</c:if>
										<c:if test="${student==null}">
												预报名
												<input type="hidden" id="status" name="student.status" value="<%=Constants.STU_CALL_STATUS_YU_BAO_MING %>" />
										</c:if>
										</td>
										</div>
									</tr>
									
									<tr>
										<td align="right" width="100px;">
											希望联系时间：
										</td>

										<td align="left">
											<c:if test="${student.serviceTime!=null&&student.serviceTime!=''}">
											<span style="color:black;">${student.serviceTime}</span>
											</c:if>
											<c:if test="${student.serviceTime==null||student.serviceTime==''}">
											<span style="color:black;">无</span>
											</c:if>
										</td>
									</tr>
								</table>
								<!--<table class="gv_table_2">
									<tr>
										<th style="width: 20px;">
											<img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" />
										</th>

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

								-->
								
								
							 </form>
							</td>
						</tr>
					</table>
		</body:body>
	</body>

</html>