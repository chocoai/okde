<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
	<title>复核</title>
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
					location.href="<uu:url url='enrollment/enrollaudit/list_enroll_audit'/>";
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
			pluginCode="003" urls="/enrollment/enrollaudit/update_enroll_audit_stuinfo"/>
		
		<script type="text/javascript">
			var pcas;
			var studentId=0;
			var sValue=0;
			//电话号码
			var phone_number;
			
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
			}
			
			$(document).ready(function(){
			//获取学生状态对应的中文名称
			var statusval=statusValue(${stu.status});
			$("#stustatus").html(statusval);
			//选中下拉菜单
			pcas=new $.PCAS({province:'select[name=student.livingPlace]',provinceV:"${stu.livingPlace}"});
			$("#sex").val("${stu.gender}");
			//初始化
			init();
			//表单验证				
				$.formValidator.initConfig({formID:"update_student_form",submitOnce:"true",debug:"false",
					onError:function(msg,obj,errorlist){
						alert(msg);
					},
					onSuccess:function(){
						ajax_003_1();
						return false;
					},
					submitAfterAjaxPrompt:"当前有数据正在进行服务器端校验，请稍候"
				});
				
				$("#name").formValidator({onShow:"请输入姓名！",onFocus:"用户名至少2个字符,最多6个字符",onCorrect:"姓名验证通过"}).inputValidator({min:2,max:6,onError:"你输入的姓名错误,请确认"})
				<c:if test="${stu!=null}">
					.defaultPassed();
				</c:if>
				$("#shouji").formValidator({onShow:"请输入你的手机号码",onFocus:"你要是输入了，必须输入正确",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留手机号码啊？"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"你输入的手机号码格式不正确"})
				.ajaxValidator({
					dataType : "json",
					url : WEB_PATH+"/crm/exist_student_phone",
					type:'post',
					success : function(data){
						<c:if test="${stu!=null}">
							if($("#shouji").val()=="${stu.mobile}"){
							    return true;
						    }
					    </c:if>
						if(data.exist){
							return true;
						}else{
							return false;
						}
					},
					error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
					onError : "该手机号码已存在，请更换手机号码",
					onWait : "正在对手机号码进行合法性校验，请稍候..."
				})
				<c:if test="${stu!=null}">
					.defaultPassed();
				</c:if>
				
			
				//证件号
				$("#sfzh").formValidator({onShow:"请输入15或18位的证件号",onFocus:"输入15或18位的证件号",onCorrect:"输入正确"}).functionValidator({fun:isCardID})
				.ajaxValidator({
					dataType : "json",
					url : WEB_PATH+"/crm/exist_student_certno",
					type:'post',
					success : function(data){
						<c:if test="${stu!=null}">
							if($("#sfzh").val()=="${stu.certNo}"){
							    return true;
						    }
					    </c:if>
						if(data.exist){
							return true;
						}else{
							return false;
						}
					},
					error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
					onError : "该证件号已存在，请更换证件号",
					onWait : "正在对证件号进行合法性校验，请稍候..."
				})
				<c:if test="${stu!=null}">
					.defaultPassed();
				</c:if>
				
				$("#email").formValidator({empty:true,onShow:"请输入邮箱,可以为空",onFocus:"邮箱6-50个字符",onCorrect:"恭喜你,你输对了"}).inputValidator({min:6,max:50,onError:"你输入的邮箱长度错误,请确认"}).regexValidator({regExp:regexEnum.email,onError:"你输入的邮箱格式不正确"})
				<c:if test="${stu!=null}">
					.defaultPassed();
				</c:if>
			});
			
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
							columnsStr="createdTime;content"
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
									<input type="text" class="txt_box_150" id="name" name="student.name"  value="${stu.name}" />
									 <div id="nameTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
								</td>
							</tr>
							<tr>  
								<td align="right" width="130px;">
									手机：
								</td>
								<td align="left">
									<input type="text" id="shouji" class="txt_box_150" name="student.mobile" value="${stu.mobile}" />
									<div id="shoujiTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>								
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									性别：
								</td>
								<td align="left">
									<select id="sex" class="txt_box_150" name="student.gender" >
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
									<select name="student.livingPlace" class="txt_box_150" style="margin-left:10px;"></select>
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									证件号：
								</td>
								<td align="left"> 
									<input type="text" id="sfzh" class="txt_box_150" name="student.certNo"
										value="${stu.certNo}" />
									<div id="sfzhTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>					
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
									<input type="text" id="email" class="txt_box_150" name="student.email"
										value="${stu.email}" />
										<div id="emailTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
								</td>
							</tr>
							<tr>
								<td align="right" width="130px;">
									发送邮件：
								</td>
								<td align="left">
									<a href="#" onclick="$('#send_mail').dialog('open');">发送邮件</a>
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
									<input class="btn_black_61" type="submit" value="复核">
								</td>
								
							</tr>
							
						</table>
						</form>
					 	</td>
					</tr>
				</table>
		  		<div id="error_Msg" style="display:none;font-weight:bold" align="center"><br/>服务器繁忙，请您稍后尝试本次操作！<br/></div>	
			</body:body>
</body>
</html>
