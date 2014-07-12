<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<title>回访</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 分页 -->
		<jc:plugin name="page" />
		<!-- 省市级联 -->
		<jc:plugin name="provinces" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		
		<jc:plugin name="loading" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<script type="text/javascript">
			//回调函数
			function sendMail(data){
				$('#message_confirm').dialog("close");
				$('#send_mail').dialog("close");
			}
			function updateStudentCallBack(data){
				if(data.addrltbool){
					alert("操作成功");
					window.close();
					//location.href="<uu:url url='/enrollment/enrollmonitor/list_enroll_monitor'/>?viewtype=0";
				}else{
					show('error_Msg','信息提示:',300,100);
				}
				
			}
		</script>
		<a:ajax 
			pluginCode="002"
			urls="template/send_mail" 
			parameters="$('#send_mail_form').serializeObject()" 
			successCallbackFunctions="sendMail" 
		/>
		<a:ajax 
			parameters="$('#update_student_form').serializeObject()" 
			successCallbackFunctions="updateStudentCallBack" 
			pluginCode="003" urls="/enrollment/returningvisit/update_add_stu_and_returning_visit"/>
		<script type="text/javascript">
			function installSubAcademyBatch(id){
				$.post('<uu:url url="/enrollment/list_academy_enroll_batch_by_academyId"/>',{academyId:${stu.academyId}},
					function(data){
						if(data.academyBatchlist!=null&&data.academyBatchlist.length>0){
							$("#selectacademybatch").empty();
							$.each(data.academyBatchlist, function(){
								$("#selectacademybatch").append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
						 	});
						}else{
							
						}				
				},"json");
		  }
		</script>
		
		<script type="text/javascript">
			var pcas;
			var studentId=0;
			var sValue=0;
			var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;//email
			//电话号码
			var phone_number;
			$(document).ready(function(){
				//判断学生状态是否为 已成功或是未成功
				$("input[name='student.status']").each(function () { 
					if(${stu.status}==this.value){
						$("input[name='student.status']").attr("checked", true)				
					}
				});
				
				//选中下拉菜单(IE6不兼容 取消)
				//pcas=new $.PCAS({province:'select[name=student.livingPlace]',provinceV:"${stu.livingPlace}"});
				
				//选中下拉菜单
				jQuery('#livingPlace').attr('value','${stu.livingPlace}');
				
				$("#sex").val("${stu.gender}");
				//表单验证				
				$.formValidator.initConfig({formID:"update_student_form",submitOnce:true,debug:false,
					onError:function(msg,obj,errorlist){
						alert(msg);
					},
					
					submitAfterAjaxPrompt:"当前有数据正在进行服务器端校验，请稍候"
				});
				////招生途径
				//if(${stu.isChannelTypeChecked}==STUDENT_CHANNEL_TYPE_CHECKED_TRUE)
				//{
				//	jQuery("#isChannelTypeChecked").attr("checked",true);
				//}
			});
			function monitorResultsValue(monitorResults){
				return monitorResults.get();
			}
			
			//校验邮箱合法性
			function chkEmail(){
				if($("#email").val()!=null&&$("#email").val()!=""&&$.trim($('#email').val())!=""){
					if(!reg.test($.trim($("#email").val()))){
						show('email_error','信息提示:',250,100);
					}else{
						ajax_003_1();
					}
				}else{
					ajax_003_1();
				}
			}
		</script>
  </head>
  
 <body>
		<head:head title="回访">
			
		</head:head>
		<body:body>
			<table style="width:100%;border:0px;">
		  		<tr>
				 <td style="width:44%;" valign="top">
						<table class="gv_table_2">
					  		<tr>
							 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
							 	<th style="text-align:left; font-weight:bold;">回访纪录</th>
							</tr>
						</table>
						<page:plugin 
							pluginCode="001"
							il8nName="returningvisit"
							searchListActionpath="enrollment/returningvisit/list_returning_visit_by_flag"
							searchCountActionpath="enrollment/returningvisit/count_returning_visit"
							columnsStr="createdTime;strParams.returningVisitName;monitorname;content"
							subStringLength="1000"
							params="'returningVisit.studentId':'${stu.id}'"
							isPackage="false"
							isPage="true"
							isNumber="true"
							isonLoad="true"
						/>
				 </td>
				 <td style="width:56%;" valign="top"> 	
				 <form id="update_student_form">	
				 	<input type="hidden" name="student.id" value="${stu.id}"/>
					<table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">基本资料</th>
		
						</tr>
					</table>
					<table class="add_table" border="0">
							<tr>
								<td align="right" width="130px;">
									姓名：
								</td>
								<td align="left" valign="middle">
									<input type="text" class="txt_box_150" value="${stu.name}" disabled="disabled"/>
									<!-- <input type="text" class="txt_box_150" id="name" name="student.name"  value="${stu.name}" />
									 <div id="nameTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>-->
								</td>
							</tr>
							<tr>  
								<td align="right" width="130px;">
									手机：
								</td>
								<td align="left">
									<input type="text" class="txt_box_150" value="${stu.mobile}" disabled="disabled"/>
								</td>
							</tr>
							<tr>  
								<td align="right" width="130px;">
									座机：
								</td>
								<td align="left">
									<input type="text" class="txt_box_150" value="${stu.phone}" disabled="disabled"/>
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									性别：
								</td>
								<td align="left">
									<!--<s:property value="stu.gender==@net.cedu.common.Constants@SEX_MALE?'男':'女'"/>-->	 
									
									<select id="sex" class="txt_box_150" name="student.gender" disabled="disabled">
												<option selected="selected" value="-1">--请选择--</option>
												<option value="<%=Constants.SEX_MALE %>">男</option>
												<option value="<%=Constants.SEX_FAMALE %>">女</option>
									</select>	
									
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									所在城市：
								</td>
								<td align="left">
									<select id="livingPlace" name="student.livingPlace" class="txt_box_150" style="margin-left:10px;">
										<%@ include file="../../crm/city.jsp" %>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									证件号：
								</td>
								<td align="left">
									<input type="text" class="txt_box_150" value="${stu.certNo}" disabled="disabled"/>
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									MSN：
								</td>
								<td align="left">
									<input type="text" class="txt_box_150" name="student.msn"
										value="${stu.msn}" />
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									QQ：
								</td>
	
								<td align="left">
									<input type="text" class="txt_box_150" value="${stu.qq}"  name="student.qq"/>
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									批次：
								</td>
								<td align="left">	
									${stu.academyenrollbatchName } 
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									邮件：
								</td>
								<td align="left">
									<input type="text" id="email" class="txt_box_150" name="student.email" value="${stu.email}"/>
								</td>
							</tr>
							
							<tr>
								<td align="right" width="130px;">
									备注：
								</td>
								<td align="left">
									<textarea rows="4" maxlength="200"  class="txt_box_350" name="student.remark">${stu.remark}</textarea>
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									单位信息：
								</td>
								<td align="left">
									<textarea rows="4" maxlength="200"  class="txt_box_350" name="student.workUnitInfo">${stu.workUnitInfo}</textarea>
								</td>
							</tr>
							<tr>
										<td align="right" width="100px;">
											<span></span>招生途径：
										</td>
										<td align="left">
											<span style="color:black;" id="source" >${stu.enrollmentSourceName}</span>
											
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" id="hezuofang">
											合作方：
										</td>
										<td align="left" id="hezuofang_t">
										<span style="color:black;" id="channel" >${stu.channelName }</span>
										
										</td>
									</tr>
									<tr>
									<input type="hidden" id="enrollmentWayName" name="student.enrollmentWayName" />
										<td align="right" width="100px;">
											<span></span>市场途径：
										</td>
										<td align="left">
											<span style="color:black;" id="way">${stu.enrollmentWayName }</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" id="hezuofang">
											数据来源：
										</td>
										<td align="left" id="hezuofang_t">
										<span style="color:black;" id="channel" >${stu.studentDataSourceName }</span>
										
										</td>
									</tr>
						</table>			
						<table class="gv_table_2">
							<tr>
							 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
							 	<th style="text-align:left; font-weight:bold;">回访内容</th>
							</tr>
						</table>
						<table  class="add_table" border="0">
						<tr>
										<td align="right" width="100px;" >
											<span></span>学习中心：
										</td>
										<td align="left">
											<div id="branchTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<span style="color:black;" id="branchSp">${stu.branchName }</span>
										</td>
									</tr>
									
									<tr>
										<td align="right" width="100px;">
											院校：
										</td>
										<td align="left">
											<span style="color:black;" id="academyIdSp">${stu.schoolName }</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" >
											招生批次：
										</td>
										<td align="left">
											<span id="batchName" name="batch" style="color: black">${stu.enrollmentBatchName }</span>
										</td>
									</tr>
									
									<tr>
										<td align="right" width="100px;">
											层次：
										</td>
										<td align="left">
											<span style="color:black;" id="levelIdSp">${stu.levelName }</span>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											专业：
										</td>
										<td align="left">
											<span style="color:black;" id="majorIdSp">${stu.majorName }</span>
										</td>
									</tr>
							<!--<tr>
								<td align="right" width="130px;"> 
									招生途径复核：
								</td>
								<td align="left">
									<label>
										<input id="isChannelTypeChecked" type="checkbox" value="1" name="student.isChannelTypeChecked" />复核完毕 
									</label>
								</td>
							</tr>  -->
							<tr>
								<td align="right" width="130px;"> 
									监控结果：
								</td>
								<td align="left">
									<s:select list="monitorResultslist" listValue="name" listKey="id" id="moniterresults" cssClass="txt_box_150" name="moniterresults.id" ></s:select>
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;"> 
									监控状态：
								</td>
								<td align="left">
										<input type="radio" name="student.monitorStatus" value="<%=Constants.STU_MONITOR_STATUS_JIAN_KONG_YI_CHENG_GONG %>"/>已监控已成功
										<input type="radio" name="student.monitorStatus" value="<%=Constants.STU_MONITOR_STATUS_JIAN_KONG_WEI_CHENG_GONG %>" checked="checked"/>已监控未成功
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									回访内容：
								</td>
								<td align="left">
									<textarea rows="4" maxlength="200"  class="txt_box_350" name="returningvisit.content"></textarea>
								</td>
							</tr>
						</table>
						<table  class="add_table" >
							<tr>
								<td align="center" width="100px;" colspan="2">
									<input class="btn_black_61" type="button" onclick="chkEmail()" value="保存">
								</td>
								
							</tr>
							
						</table>
						</form>
					 	</td>
					</tr>
				</table>
				<div id="email_error" style="display:none;font-weight:bold" align="center">邮箱输入格式有误,请重新填写！</div>
		  		<div id="error_Msg" style="display:none;font-weight:bold" align="center"><br/>服务器繁忙，请您稍后尝试本次操作！<br/></div>	
			</body:body>
</body>
</html>
