<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生首次跟进</title>
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
					}else{
						$("<option value='" + this.id + "'>" + this.level.name + "</option>").appendTo($("#cengci"));
					}
				});
							
				$("#branch").html("");
               	$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo($("#branch"));
				$(doc.branchlist).each(function(){
					
					if('${student.branchId}'==this.id){
						$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
					}else{
						$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
					}
				});
				ajax_002_5();// 
			}
			//专业
			function zhuanye(doc){
				$("#zhuanye").html("");
               	$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo($("#zhuanye"));
				$(doc.academyMajors).each(function(){
					 if('${student.majorId}'==this.majorId){
					 	$("<option selected='selected' value='" + this.majorId + "' title='" + this.majorName + "' >" + this.majorName + "</option>").appendTo($("#zhuanye"));
					 }else{
					 	$("<option value='" + this.majorId + "' title='" + this.majorName + "' >" + this.majorName + "</option>").appendTo($("#zhuanye"));
					 }
				});
			}
			
			//更新学生
			function updateStudentCallBack(doc){
				if(doc.student!=null){
					//刷新学生跟进记录
					studentId=doc.student.id;
					//学生隐藏ID
					$("#student_id").val(studentId);
					//$("#sfzh").attr("disabled","true");
					alert("保存成功！！");
				}
				$("#save").removeAttr("disabled");
				$("#up_btn").removeAttr("disabled");
				$("#next_btn").removeAttr("disabled");
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
							}else{
								$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
							}
					});
					//alert(1111);
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
			//锁定姓名,证件号
			function lock(checkObject){
				if(checkObject.checked){
					//锁定
					$("#"+checkObject.id+"hidden").val(LOCKING_TRUE);
				}else{
					//未锁定
					$("#"+checkObject.id+"hidden").val(LOCKING_FALSE);
				}
			}
			$(document).ready(function(){
				//pcas=new $.PCAS({province:'select[name=student.livingPlace]',provinceV:"${student.livingPlace}"});
				
				$("#livingPlace").val("${student.livingPlace}");
				//证件类型
				$("#cert_type").val("${student.certType}");
				$("#sex").val("${student.gender}");
				$("#status").val('${student.status}');
				$('#call_status').val('${student.callStatusId}');
				
				if('${student.status}'=='<%=Constants.STU_CALL_STATUS_GENG_JIN_ZHONG %>'){$('#call_status').removeAttr('disabled')}else{$('#call_status').val(0);$('#call_status').attr('disabled','true')}
				//初始化
				init();
				ajax_002_2();
				//表单验证				
				$.formValidator.initConfig({formID:"create_student_form",submitOnce:"true",debug:"true",
					onError:function(msg,obj,errorlist){
						alert(msg);
					},
					onSuccess:function(){
						
						if( $.trim($('#shouji').val())=="")
						{
							if($.trim($('#zuoji').val())=="")
							{
								$('#lianxifangshi').dialog("open");
								return false;
							}
							
						}
						if($.trim($('#zuoji').val())=="")
						{
							if($.trim($('#shouji').val())=="")
							{
								$('#lianxifangshi').dialog("open");
								return false;
							}
						}
						
						if($("#student_id").val()=="0"){
							alert("数据异常！");
							return false;
						}else{
							updateStudent();
						}
						
						return false;
					},
					submitAfterAjaxPrompt:"当前有数据正在进行服务器端校验，请稍候"
				});
				
				$("#name").formValidator({onShow:"请输入姓名！",onFocus:"用户名至少2个字符,最多6个字符",onCorrect:"姓名验证通过"}).inputValidator({min:2,max:20,onError:"你输入的姓名错误,请确认"})
				<c:if test="${student!=null}">
					.defaultPassed();
				</c:if>
				$("#shouji").formValidator({empty:true,onShow:"请输入你的手机号码",onFocus:"你要是输入了，必须输入正确",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留手机号码啊？"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"你输入的手机号码格式不正确"})
				.ajaxValidator({
					dataType : "json",
					url : WEB_PATH+"/crm/exist_student_phone?student.id=${student.id}",
					type:'post',
					success : function(data){
						<c:if test="${student!=null}">
							if($("#shouji").val()=="${student.mobile}"){
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
				}).defaultPassed();
				
				$("#zuoji").formValidator({empty:true,onShow:"请输入你的座机号码",onFocus:"你要是输入了，必须输入正确",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留座机号码啊？"}).inputValidator({min:7,onError:"座机号码最少是7位的,请确认"}).regexValidator({regExp:"tel",dataType:"enum",onError:"你输入的座机号码格式不正确,例如:010-84186633-1000,010-84186633"})
				.ajaxValidator({
					dataType : "json",
					url : WEB_PATH+"/crm/exist_student_tel?student.id=${student.id}",
					type:'post',
					success : function(data){
						<c:if test="${student!=null}">
							if($("#zuoji").val()=="${student.phone}"){
							    return true;
						    }
					    </c:if>
						if(data.exist){
							//隐藏调出信息超链接
							$("#diao_chu_xin_xi_2").attr("style","display:none");
							$("#diao_chu_xin_xi_2").attr("href","#");
							return true;
						}else{
							//显示调出信息超链接
							$("#diao_chu_xin_xi_2").attr("href","<uu:url url="crm/view_home?tel=" />"+$("#zuoji").val());
							$("#diao_chu_xin_xi_2").attr("style","display:block");
							return false;
						}
					},
					error: function(jqXHR, textStatus, errorThrown){alert("服务器没有返回数据，可能服务器忙，请重试"+errorThrown);},
					onError : "该座机号码已存在，请更换座机号码",
					onWait : "正在对座机号码进行合法性校验，请稍候..."
				})
				<c:if test="${student!=null}">
					.defaultPassed();
				</c:if>
				
				<c:if test="${student.status<6 && ischecked==1}">
				//证件号
				$("#sfzh").formValidator({empty:true,onShow:"请输入15或18位的证件号",onFocus:"输入15或18位的证件号",onCorrect:"输入正确"}).functionValidator({fun:isCardID})
				.ajaxValidator({
					dataType : "json",
					url : WEB_PATH+"/crm/exist_student_certno?student.id=${student.id}",
					type:'post',
					success : function(data){
						<c:if test="${student!=null}">
							if($("#sfzh").val()=="${student.certNo}"){
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
				}).defaultPassed();
				</c:if>
				
				$("#email").formValidator({empty:true,onShow:"请输入邮箱,可以为空",onFocus:"邮箱6-50个字符",onCorrect:"恭喜你,你输对了"}).inputValidator({min:6,max:50,onError:"你输入的邮箱长度错误,请确认"}).regexValidator({regExp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onError:"你输入的邮箱格式不正确"})
					.defaultPassed();
				<c:if test="${student.status<6 && ischecked==1}">
					$("#school").formValidator({onShow:"请选择院校",onFocus:"院校必须选择",onCorrect:"输入正确"}).inputValidator({min:1,onError: "你是不是忘记选择院校了!"})
						<c:if test="${student!=null}">
							.defaultPassed();
						</c:if>
					$("#pici").formValidator({onShow:"请选择你的批次",onFocus:"批次必须选择",onCorrect:"输入正确"}).inputValidator({min:1,onError: "你是不是忘记选择批次了!"})
						<c:if test="${student!=null}">
							.defaultPassed();
						</c:if>
					//$("#branch").formValidator({onShow:"请选择你的学习中心",onFocus:"学习中心必须选择",onCorrect:"输入正确"}).inputValidator({min:1,onError: "你是不是忘记选择学习中心了!"})
						<c:if test="${student!=null}">
					//		.defaultPassed();
						</c:if>
					$("#cengci").formValidator({onShow:"请选择你的层次",onFocus:"层次必须选择",onCorrect:"输入正确"}).inputValidator({min:1,onError: "你是不是忘记选择层次了!"})
						<c:if test="${student!=null}">
							.defaultPassed();
						</c:if>
					$("#zhuanye").formValidator({onShow:"请选择专业",onFocus:"专业必须选择",onCorrect:"输入正确"}).inputValidator({min:1,onError: "你是不是忘记选择专业了!"})
						<c:if test="${student!=null}">
							.defaultPassed();
						</c:if>
				</c:if>
			
			});
			
		</script>
		
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="学生首次跟进">
		</head:head>
		<!--主体层开始 -->
		<body:body>
					<table class="add_table">
						<tr>
							<td align="center">
								<!-- a href="#"><input onclick="location.href='<%=Constants.WEB_PATH %>/crm/call?result.currentPageIndex=${result.currentPageIndex-1}'" id="up_btn" class="btn_black_61" type="button" value="上一个" /></a> -->
								<a href="#"><input onclick="location.href='<%=Constants.WEB_PATH %>/crm/call?result.currentPageIndex=${result.currentPageIndex+1}'" id="next_btn" class="btn_black_61" type="button" value="下一个" /></a>
								(未首次跟进：<span>${count}</span>人)
							</td>
						</tr>
				</table>
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
										 	<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" /></th>
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
											<th style="width: 20px;">
												<img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" />
											</th>
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
										isNumber="false"
										subStringLength="1000"
										columnsWidth="[3,300px]"
										params="'followUp.studentId':studentId,'result.order':'createdTime','result.sort':'desc'"
									/>
									<!-- 'followUp.statusId':STU_CALL_STATUS_GENG_JIN_ZHONG -->
									
							</td>
							<td style="width: 56%;" valign="top">
							<form id="create_student_form">
								<input type="hidden" id="student_id" name="student.id" value="${student==null?0:student.id}"/>
								<!-- 锁定 -->
								<c:if test="${student==null }">
									<!-- 姓名锁定 -->
									<input type="hidden" id="name_locking_hidden" name="student.nameLockingStatus" value="0"/>
									<!-- 证件号锁定 -->
									<input type="hidden" id="cert_no_locking_hidden" name="student.certNoLockingStatus" value="0"/>
								</c:if>
								<!-- 姓名未锁定 -->
								<c:if test="${student!=null&&student.nameLockingStatus==0 }">
									<!-- 姓名锁定 -->
									<input type="hidden" id="name_locking_hidden" name="student.nameLockingStatus" value="0"/>
								</c:if>
								<!-- 证件号未锁定 -->
								<c:if test="${student!=null&&student.certNoLockingStatus==0 }">
									<!-- 证件号锁定 -->
									<input type="hidden" id="cert_no_locking_hidden" name="student.certNoLockingStatus" value="0"/>
								</c:if>
								<table class="gv_table_2">

									<tr>
										<th style="width: 20px;">
											<img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" />
										</th>
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
											
												<!-- 姓名未锁定 -->
											<c:if test="${student!=null&&student.nameLockingStatus==0 }">
												<div id="nameTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
												<input type="text" class="txt_box_100" id="name" name="student.name"  value="${student.name}" />
												<label><input type="checkbox" value="0" id="name_locking_" onclick="lock(this);"/>锁定</label>
											</c:if>
											<c:if test="${student==null}">
												<div id="nameTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
												<input type="text" class="txt_box_100" id="name" name="student.name"  value="${student.name}" />
												<label><input type="checkbox" value="0" id="name_locking_" onclick="lock(this);"/>锁定</label>
											</c:if>
											<!-- 姓名已锁定 -->
											<c:if test="${student!=null&&student.nameLockingStatus==1 }">
												${student.name}<font color="red">(姓名已锁定不能修改)</font>
											</c:if>

										</td>
										
									</tr>
									<tr>
										<td align="right" width="100px;">
											手机：
										</td>
										<td align="left">
											
											<div id="shoujiTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<input type="text" id="shouji" class="txt_box_150" name="student.mobile" value="${student.mobile}" />
										</td>
									</tr>
									<tr>
									<td align="right" width="100px;">
											座机：
										</td>
										<td align="left">
												<div id="zuojiTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<input type="text" id="zuoji" class="txt_box_150" name="student.phone" value="${student.phone}" />
											<!-- 调出学生信息 -->
											<a id="diao_chu_xin_xi_2" style="display: none;float: right;" href="#" target="_blank">调出信息</a>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											性别：
										</td>
										<td align="left">
											
											<select id="sex" class="txt_box_100" name="student.gender">
														<option selected="selected" value="-1">--请选择--</option>
														<option value="<%=Constants.SEX_MALE %>">男</option>
														<option value="<%=Constants.SEX_FAMALE %>">女</option>
											</select>	
												 
										</td>
									</tr>

									<tr>
										<td align="right" width="100px;">
											所在城市：
										</td>
										<td align="left">
											<select name="student.livingPlace" id="livingPlace" class="txt_box_150" style="margin-left:10px;">
												<%@ include file="city.jsp" %>
											</select>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											证件类型：
										</td>
										<td>
											<select id="" class="txt_box_70" id="cert_type"  name="student.certType">
												<option value="<%=Constants.CERTIFICATE_TYPE_ID%>"
													selected="selected">
													身份证
												</option>
												<option value="<%=Constants.CERTIFICATE_TYPE_DRIVER_ID%>">
													护照
												</option>
												<option value="<%=Constants.CERTIFICATE_TYPE_NCO_ID%>">
													士官证
												</option>
											</select>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											证件号：
										</td>
										<td align="left">
											<!-- 证件号未锁定 -->
											<c:if test="${student!=null&&student.certNoLockingStatus==0 }">
												<div id="sfzhTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											 	<input type="text" id="sfzh" class="txt_box_150" name="student.certNo" value="${student.certNo}" />
												<label><input type="checkbox" value="0" id="cert_no_locking_" onclick="lock(this);"/>锁定</label>
											</c:if>
											<c:if test="${student==null}">
												<div id="sfzhTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											 	<input type="text" id="sfzh" class="txt_box_150" name="student.certNo" value="${student.certNo}" /> 
												<label><input type="checkbox" value="0" id="cert_no_locking_" onclick="lock(this);"/>锁定</label>
											</c:if>
											
											<!-- 证件号已锁定 -->
											<c:if test="${student!=null&&student.certNoLockingStatus==1 }">
												${student.certNo}<font color="red">(证件号已锁定不能修改)</font>
											</c:if>
										</td>
									</tr>
								
								
									<tr>
										<td align="right" width="100px;">
											MSN：
										</td>
										<td align="left">
											<input type="text" class="txt_box_150" name="student.msn"
												value="${student.msn}" />
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											QQ：
										</td>

										<td align="left">
											<input type="text" class="txt_box_150" value="${student.qq}"  name="student.qq"/>
										</td>
									</tr>

									<tr>
										<td align="right" width="100px;">
											邮件：
										</td>
										<td align="left">
											
												<div id="emailTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
												<input type="text" id="email" class="txt_box_150" name="student.email"
												value="${student.email}" />
										</td>
									</tr>
									
									<tr>
										<td align="right" width="100px;">
											备注：
										</td>
										<td align="left">
											<textarea rows="4" maxlength="200"  class="txt_box_350" name="student.remark">${student.remark}</textarea>
										</td>
									</tr>
									
								</table>
								<table class="gv_table_2">
									<tr>
										<th style="width: 20px;">
											<img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" />
										</th>
										<th style="text-align: left; font-weight: bold;">
											初步意向
										</th>

									</tr>
								</table>
								<table class="add_table">
									<tr>
										<td align="right" width="100px;">
											院校：
										</td>
										<td align="left">
											
											<div id="schoolTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<select class="txt_box_150" id="school" name="student.academyId"></select>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" >
											批次：
										</td>
										<td align="left">
											
											<div id="piciTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<select class="txt_box_150" id="pici" name="student.enrollmentBatchId"></select>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" >
											学习中心：
										</td>
										<td align="left">
											
											<div id="branchTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<select disabled="disabled" class="txt_box_150" id="branch" name="student.branchId"></select>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											层次：
										</td>
										<td align="left">
											
											<div id="cengciTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<select class="txt_box_150" id="cengci" name="student.levelId"></select>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											专业：
										</td>
										<td align="left">
											
											<div id="zhuanyeTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<select class="txt_box_150" id="zhuanye" name="student.majorId"></select>
										</td>
									</tr>
								</table>
					
								<table class="gv_table_2">
									<tr>
										<th style="width: 20px;">
											<img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" />
										</th>
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
											<select id="status" class="txt_box_100" name="student.status" onchange="if(this.value=='<%=Constants.STU_CALL_STATUS_GENG_JIN_ZHONG %>'){$('#call_status').removeAttr('disabled')}else{$('#call_status').val(0);$('#call_status').attr('disabled','true')}">
												<option value="<%=Constants.STU_CALL_STATUS_YI_FENG_PEI %>"> 已分配</option>
												<option value="<%=Constants.STU_CALL_STATUS_WU_YI_YUAN %>">无意愿</option>
												<option value="<%=Constants.STU_CALL_STATUS_GENG_JIN_ZHONG %>">跟进中</option>
											</select>
										</td>
										</div>
									</tr>
									
									<tr>
										<td align="right" width="100px;">
											呼叫等级：
										</td>
										<td align="left">
											<select id="call_status" class="txt_box_200" name="followUp.callStatusId">
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
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											希望联系时间：
										</td>

										<td align="left">
											<input type="text" class="txt_box_200" value="${student.serviceTime}"  name="student.serviceTime"/>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" >
											提示您回访跟进：
										</td>
										<td align="left">
											<input id="BackDate" type="text" class="Wdate"  class="txt_box_200"  onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})" name="student.returnVisitTime"
												value="<fmt:formatDate value='${student.returnVisitTime}' pattern='yyyy-MM-dd HH:mm'/>" />
											
										</td>
									</tr>
								</table>
								
								<table class="gv_table_2">
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

								<table class="gv_table_2">
									<tr>
										<th style="width: 20px;">
											<img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" />
										</th>
										<th style="text-align: left; font-weight: bold;">
											其他
										</th>

									</tr>
								</table>
								<table class="add_table">
									<tr>

										<td align="right" width="100px;">
											
											跟进内容：
										</td>
										<td align="left">
											<textarea rows="4" cols="60" class="txt_box_350" name="followUp.remark"></textarea>
										</td>
									</tr>


								</table>
								<table class="add_table">

									<tr>
										<td align="center" width="100px;" colspan="2">
											<input class="btn_black_61" type="submit" id="save" value="保存" />
										</td>
										
									</tr>

								</table>
							 </form>
							</td>
						</tr>
					</table>

			</body:body>
	</body>

</html>