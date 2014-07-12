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
		<!-- 省市级联 -->
		<jc:plugin name="provinces" />
		<jc:plugin name="base_js" />
		<jc:plugin name="page" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		
		<jc:plugin name="loading" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<script type="text/javascript">
		
			function updateStudentInfo(data){
				if(data.updaterltbool){
					location.href="<uu:url url='enrollment/enrollmonitorapplication/list_enroll_monitor_application'/>";
				}else{
					show('error_Msg','信息提示:',300,100);
				}
				
			}
		</script>
		<a:ajax 
			parameters="jQuery('#update_student_form').serializeObject();" 
			successCallbackFunctions="updateStudentInfo"
			pluginCode="003" urls="/enrollment/enrollmonitorapplication/update_enroll_monitor_application_stuinfo"/>
		
		
		<script type="text/javascript">
			var pcas;
			var studentId=0;
			var sValue=0;
			//电话号码
			var phone_number;
			var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;//email
			function init(){
				$('#message_confirm').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'提示',
					width: 260,
					height: 125
					
				});
			}
			
			$(document).ready(function(){
			//获取学生状态对应的中文名称
			var statusval=statusValue(${stu.status});
			$("#stustatus").html(statusval);
			//选中下拉菜单
			//pcas=new $.PCAS({province:'select[name=student.livingPlace]',provinceV:"${stu.livingPlace}"});
			//选中出生地下拉菜单
			$("#livingPlace").val("${stu.livingPlace}");
			$("#sex").val("${stu.gender}");
			//初始化
			init();
			//表单验证				
				$.formValidator.initConfig({formID:"update_student_form",submitOnce:false,debug:true,
					onError:function(msg,obj,errorlist){
						alert(msg);
					},
					onSuccess:function(){
						chkEmail();
						return false;
					},
					submitAfterAjaxPrompt:"当前有数据正在进行服务器端校验，请稍候"
				});
				
				$("#shouji").formValidator({empty:true,onShow:"请输入你的手机号码",onFocus:"你要是输入了，必须输入正确",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留手机号码啊？"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"你输入的手机号码格式不正确"})
				.ajaxValidator({
					dataType : "json",
					url : WEB_PATH+"/crm/exist_student_phone?student.id=${stu.id}",
					type:'post',
					success : function(data){
						<c:if test="${stu!=null}">
							if($("#shouji").val()=="${stu.mobile}"){
							    return true;
						    }
					    </c:if>
						if(data.exist){
							//隐藏调出信息超链接
							$("#diao_chu_xin_xi").attr("style","display:none");
							$("#diao_chu_xin_xi").attr("href","#");
							return true;
						}else{
							//显示调出信息超链接
							$("#diao_chu_xin_xi").attr("href","<uu:url url="crm/view_home?phone=" />"+$("#shouji").val());
							$("#diao_chu_xin_xi").attr("style","display:block");
							return false;
						}
					},
					error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
					onError : "该手机号码已存在，请更换手机号码",
					onWait : "正在对手机号码进行合法性校验，请稍候..."
				}).defaultPassed();
				
			});
			
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
			function statusValue(status){
				return status.getStudentStatus();
			}
		</script>
  </head>
  
 <body>
		<head:head title="回访">
		</head:head>
		<body:body>
			<div id="message_confirm" style="display:none">
						<div>确认执行该操作！</div>
			</div>
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
							subStringLength="20"
							searchListActionpath="enrollment/returningvisit/list_returning_visit_by_flag"
							searchCountActionpath="enrollment/returningvisit/count_returning_visit"
							columnsStr="createdTime;content"
							params="'returningVisit.studentId':'${stu.id}'"
							isPackage="false"
							isPage="true"
							isNumber="true"
							isonLoad="true"
						/>
				 </td>
				 <td style="width:56%;" valign="top"> 	
				 <form id="update_student_form" method="post" action="list_submit_enroll_monitor_application">	
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
									<!-- <input type="text" class="txt_box_150" value="${stu.mobile}" disabled="disabled"/> -->
								
									<input type="text" id="shouji" class="txt_box_150" name="student.mobile" value="${stu.mobile}" />
									<div id="shoujiTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
								
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									性别：
								</td>
								<td align="left">
									<!--<s:property value="stu.gender==@net.cedu.common.Constants@SEX_MALE?'男':'女'"/>-->	 
									
									<select id="sex" class="txt_box_150" disabled="disabled" name="student.gender" disabled="disabled" >
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
									<!--select name="student.livingPlace" disabled="disabled" class="txt_box_150" style="margin-left:10px;"></select>  -->
									<select name="student.livingPlace" id="livingPlace" disabled="disabled" class="txt_box_150" style="margin-left:10px;">
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
									<!--  
									<input type="text" id="sfzh" class="txt_box_150" name="student.certNo"
										value="${stu.certNo}" />
									<div id="sfzhTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
									-->
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									MSN：
								</td>
								<td align="left">
									<input type="text" class="txt_box_150" disabled="disabled" name="student.msn"
										value="${stu.msn}" />
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									QQ：
								</td>
	
								<td align="left">
									<input type="text" disabled="disabled" class="txt_box_150" value="${stu.qq}"  name="student.qq"/>
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
									<input type="text" id="email" class="txt_box_150" name="student.email" value="${stu.email}" />
									<div id="emailTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
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
									<br/>(是指该学生工作单位的相关资料)
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
								<td align="right" width="130px;"> 
									学生状态：
								</td>
								<td align="left">
									<span id="stustatus"></span>
								</td>
							</tr>
						</table>
						<table  class="add_table" >
							<tr>
								<td align="center" width="100px;" colspan="2">
									<input class="btn_black_130"  type="submit" id="save"  value="监控再申请" />
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
