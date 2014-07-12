<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生录入</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 分页 -->
		<jc:plugin name="page" />
		<!-- 省市级联 -->
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />

		<jc:plugin name="loading" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />

		<jc:plugin name="base_js" />

		<!-- 时间控件 -->
		<jc:plugin name="calendar" />

		<script type="text/javascript">
			var sex=-1;
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
				//是否呼叫
				if(${student!=null}){
					ajax_002_4();//层次，中心
				}
			}
			//层次学习中心
			function cengci(doc){
				$("#cengci").html("");
               	$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo($("#cengci"));
				$(doc.academyLevels).each(function(){
					if('${student.levelId}'==this.level.id){
						$("<option  selected='selected' value='" + this.id + "'>" + this.level.name + "</option>").appendTo($("#cengci"));
					}else{
						$("<option value='" + this.id + "'>" + this.level.name + "</option>").appendTo($("#cengci"));
					}
				});
							
				/*$("#branch").html("");
               	$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo($("#branch"));
				$(doc.branchlist).each(function(){
					
					if('${student.branchId}'==this.id){
						$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
					}else{
						$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
					}
				});*/
				//是否呼叫
				if(${student!=null}){
					ajax_002_5();//专业
				}
			}
			//专业
			function zhuanye(doc){
				$("#zhuanye").html("");
               	$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo($("#zhuanye"));
				$(doc.academyMajors).each(function(){
					if('${student.majorId}'==this.majorId){
					 	$("<option selected='selected' value='" + this.majorId + "'>" + this.majorName + "</option>").appendTo($("#zhuanye"));
					 }else{
					 	$("<option value='" + this.majorId + "'>" + this.majorName + "</option>").appendTo($("#zhuanye"));
					 }
				});
			}
			
			//增加学生
			function addStudentCallBack(doc){
				if(doc.exist){
					$('#error_message').dialog("open");
					
				}else{
					alert("学生录入成功！");
				}
				$("#save").removeAttr("disabled");
				$("button").removeAttr("disabled");
				//$("#sfzh").attr("disabled","true");
			}
			//招生途径和市场途径回调函数
			function wayAndSourceCallback(doc){
								$("#source").html("");
	               				$("<option value='" + 0 + "'>请选择招生途径</option>").appendTo($("#source"));
								
								$(doc.enrollmentSources).each(function(){
									 $("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#source"));
								});
								
								$("#way").html("");
	               				var wayStr="<option value='" + 0 + "'>请选择市场途径</option>";
								$.each(doc.enrollmentWaysMap,function(key,value){
									if(key!="大客户"&&key!="渠道"&&key!="老带新"&&key!="加盟"&&key!="共建"){
										wayStr+="<optgroup label='"+key+"'>";
										$(this).each(function(){
												wayStr+="<option value='" + this.id + "'>" + this.name + "</option>";
										});
										wayStr+="</optgroup>";
									}
								});
								$("#way").html(wayStr);								


								$("#channel").html("");
	               				$("<option value='" + 0 + "'>请选择合作方</option>").appendTo($("#channel"));
	               				
	               				$("#channel").attr("disabled",true);
	               				$("#way").attr("disabled",true);
	               				
								$("#source").change(function(){
										if($('#source').val()==0){
											$("#channel").html("");
	               							$("<option value='" + 0 + "'>请选择合作方</option>").appendTo($("#channel"));
											//$("#way").attr("disabled",false);
											$('#way option:selected').val(0);
											$('#way option:selected').text('请选择市场途径')
											
											$("#channel").attr("disabled",true);
	               							$("#way").attr("disabled",true);
	               							
											return;
										}
										if($('#source').val()==WEB_STU_SOURCE_DEFAULT){
											
											//社招
											$("#hezuofang").html("合作方：");
											$("#channel").val("0");
											$("#channel").attr("disabled",true);
											$("#way").attr("disabled",false);    //a
											$('#way option:selected').val(0);    //b
											$('#way option:selected').text('请选择市场途径');  //c
											/*
											特殊技术处理，如回复正常功能请屏蔽代码（d,e,f）,并解除（a,b,c）
											*/
											//$("#way").attr("disabled",true);  //d
											//$('#way option:selected').val(47);//e
											//$('#way option:selected').text('未知')//f
											
											//$("#way").html("");
	               							//$("<option value='" + 0 + "'>请选择市场途径</option>").appendTo($("#way"));
											$(doc.enrollmentWays).each(function(){
											if(this.id=='${student.enrollmentWay}'){
									 			$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo($("#way"));
											}else{
												$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#way"));
											}
											});
										
											
										}else{
											$("#hezuofang").html("<span>*</span>合作方：");
											$("#hezuofang_t").html('<select id="channel" name="student.channelId" class="txt_box"></select>');											
					
											$('#way option:selected').text($('#source option:selected').text());
											$('#way option:selected').val(1);
											
											$('#enrollmentWayName').val($('#source option:selected').text());
											
											$("#way").attr("disabled",true);
											
										}
										ajax_004_1();
								});
								
			}
			//查询合作方
			function channelCallback(doc){
					$("#channel").html("");
	               	$("<option value='" + 0 + "'>请选择合作方</option>").appendTo($("#channel"));
					$(doc.channels).each(function(){
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#channel"));
					});
			}
			//查询合作方
			function searchChannelCallback(doc){
					$("#channel").html("");
	               	$("<option value='" + 0 + "'>请选择合作方</option>").appendTo($("#channel"));
	         		var channelName=$("#channelName").val();
	         		alert(channelName);
	         		if(channelName!=null&&channelName!=""){
						$(doc.channels).each(function(){
								if(this.name.indexOf(channelName)!=-1){
									$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#channel"));
								}
						});
					}
			}
		</script>

		<a:ajax pluginCode="002"
			urls="template/send_mail;/crm/academys_academie_list;/crm/academy_enroll_batch_list_input;/crm/level_list;/crm/base_majors_list;/crm/add_student_lc"
			parameters="$('#send_mail_form').serializeObject();null;{'id':sValue};{'id':sValue,'academyId':parseInt($('#school').val())};{'id':sValue};$('#create_student_form').serializeObject()"
			successCallbackFunctions="sendMail;initSelect;pici;cengci;zhuanye;addStudentCallBack" />

		<a:ajax parameters="{'id':parseInt($('#source').val())}"
			successCallbackFunctions="channelCallback" pluginCode="004"
			urls="crm/student_channel_list" />
			
		<a:ajax parameters="{'id':parseInt($('#source').val())}"
			successCallbackFunctions="searchChannelCallback" pluginCode="005"
			urls="crm/student_channel_list" />

		<a:ajax successCallbackFunctions="wayAndSourceCallback"
			pluginCode="003" urls="/crm/student_way_list" isOnload="all" />
		<script type="text/javascript">
			var pcas;
			var studentId=0;
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
               		/*branch.html("");
               		$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo(branch);*/
               				
					$(doc.academysAcademies).each(function(){
						if('${student.academyId}'==this.id){
							sValue=this.id;
							$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
						}else{
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
						}
					});
				//是否呼叫
				if(${student!=null}){
					ajax_002_3();//批次
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
							return;}
					
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
			function addStudent(){
				//增加学生
				ajax_002_6();
				
			}
			//呼叫等级
			function callGradeValue(callStatusId){
				return callStatusId.getCallGrade();
			}
			//跟进状态
			function statusValue(statusId){
				return statusId.getStudentStatus();
			}
			//修改隐藏状态值
			function changeHiddenStatus(status){
				//修改为已导入
				$("#status").val(status);
			}
			//打开人员选择
			function openStudentDiv(){
				$('#student_div').dialog("open");
			}
			function operatingValue(id,name){
				return '<input type="radio" id="'+name+'" name="stidentId_radio" value="'+id+'" />';
			}
			$(document).ready(function(){
				//选中下拉菜单
				//pcas=new $.PCAS({province:'select[name=student.livingPlace]',provinceV:""});
				//$("#livingPlace").val("${student.livingPlace}");
				//学历
				//util.select.initOption('select[name=student.degree]', getDegree());
				initSelectOptions(getDegree(),"xueli","0");
				//初始化
				init();
				//加载院校
				ajax_002_2();				
				//表单验证				
				$.formValidator.initConfig({formID:"create_student_form",submitOnce:false,debug:true,
					onError:function(msg,obj,errorlist){
						alert(msg);
					},
					onSuccess:function(){
						//屏蔽掉身份证的非空验证
						//if( $.trim($('#sfzh').val())=="")
						//{
						//	alert("请输入证件号！");
						//	return false;
						//}
					
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
						//请选择学生来源
						if($("#source").val()=="0"){
							$('#message_source').dialog("open");
						}else{
							//市场途径
							if($("#way").val()=="0"){
								$('#message_way').dialog("open");
							}else{
								//老带新
								if($("#source").val()==WEB_STU_ENROLLMENT_SOURCE){
									if($("#channel").val()!=null&&$("#channel").val()!="0"){
										addStudent();
									}else{
										$('#message_hezuofang').dialog("open");
									}
								}else if($('#source').val()==WEB_STU_SOURCE_DEFAULT){
									//社招
									addStudent();
								}else{
									if($("#channel").val()!=null&&$("#channel").val()!="0"){
										addStudent();
									}else{
										$('#message_hezuofang').dialog("open");
									}
								}
							}
						}
						
						return false;
					},
					submitAfterAjaxPrompt:"当前有数据正在进行服务器端校验，请稍候"
				});
				
				
				$("#name").formValidator({onShow:"请输入姓名！",onFocus:"请输入姓名!",onCorrect:"姓名验证通过"}).inputValidator({min:2,max:20,onError:"你输入的姓名错误,请确认"});				
				//证件号
				$("#sfzh").formValidator({empty:true,onShow:"请输入证件号",onFocus:"请输入证件号",onCorrect:"输入正确",onEmpty:"证件号为空"}).functionValidator({fun:isCardID})
				.ajaxValidator({
					dataType : "json",
					url : WEB_PATH+"/crm/exist_student_certno",
					type:'post',
					success : function(data){
						<c:if test="${student!=null}">
							if($("#sfzh").val()=="${student.mobile}"){
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
				});
				$("#shouji").formValidator({empty:true,onShow:"请输入你的手机号码",onFocus:"你要是输入了，必须输入正确",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留手机号码啊？"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"你输入的手机号码格式不正确"})
				.ajaxValidator({
					dataType : "json",
					url : WEB_PATH+"/crm/exist_student_phone",
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
				});
				
				$("#email").formValidator({empty:true,onShow:"请输入邮箱,可以为空",onFocus:"邮箱6-50个字符",onCorrect:"输入正确"}).inputValidator({min:6,max:50,onError:"你输入的邮箱长度错误,请确认"}).regexValidator({regExp:"^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",onError:"你输入的邮箱格式不正确"});
			
			//	$("#zuoji").formValidator({empty:true,onShow:"请输入你的联系电话，可以为空",onFocus:"格式例如：0577-88888888",onCorrect:"输入正确",onempty:"请输入你的联系电话，可以为空"}).regexValidator({regExp:"^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",onError:"你输入的联系电话格式不正确"});
				
				$("#zuoji").formValidator({empty:true,onShow:"请输入你的座机号码",onFocus:"你要是输入了，必须输入正确",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留座机号码啊？"}).inputValidator({min:7,onError:"座机号码最少是7位的,请确认"}).regexValidator({regExp:"tel",dataType:"enum",onError:"你输入的座机号码格式不正确,例如:010-84186633-1000,010-84186633"})
				.ajaxValidator({
					dataType : "json",
					url : WEB_PATH+"/crm/exist_student_tel",
					type:'post',
					success : function(data){
						<c:if test="${student!=null}">
							if($("#zuoji").val()=="${student.phone}"){
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
					onError : "该座机号码已存在，请更换座机号码",
					onWait : "正在对座机号码进行合法性校验，请稍候..."
				});
				
				$("#school").formValidator({empty:true,onShow:"请选择院校",onFocus:"请选择院校",onCorrect:"输入正确",onEmpty:"院校为空"}).inputValidator({min:1,onError: "你是不是忘记选择院校了!"})
				$("#pici").formValidator({empty:true,onShow:"请选择你的批次",onFocus:"请选择你的批次",onCorrect:"输入正确",onEmpty:"批次为空"}).inputValidator({min:1,onError: "你是不是忘记选择批次了!"})
				$("#branch").formValidator({onShow:"请选择你的层次",onFocus:"层次必须选择",onCorrect:"输入正确"}).inputValidator({min:1,onError: "你是不是忘记选择层次了!"})
				$("#cengci").formValidator({empty:true,onShow:"请选择你的层次",onFocus:"请选择你的层次",onCorrect:"输入正确",onEmpty:"层次为空"}).inputValidator({min:1,onError: "你是不是忘记选择层次了!"})
				$("#zhuanye").formValidator({empty:true,onShow:"请选择专业",onFocus:"请选择专业",onCorrect:"输入正确",onEmpty:"专业为空"}).inputValidator({min:1,onError: "你是不是忘记选择专业了!"})
				$("#livingPlace").formValidator({onShow:"请选择城市",onFocus:"请选择城市",onCorrect:"输入正确"}).inputValidator({min:1,onError: "请选择城市"})
				
				
				
				$('#error_message').dialog({
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
				
				$('#message_laodaixin').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'提示',
					width: 260,
					height: 125
					
				});
				$('#message_hezuofang').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'提示',
					width: 260,
					height: 125
					
				});
				
				$('#message_source').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'提示',
					width: 260,
					height: 125
					
				});
				$('#message_way').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'提示',
					width: 260,
					height: 125
					
				});
				
				
				$('#student_div').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'学生列表',
					width: 700,
					height: 340,
					buttons: {
						'确认': function() { 
							var s_id="";
							var s_name="";
							$("[name=stidentId_radio]").each(function(){
								if(this.checked){
									s_id=$(this).val();
									s_name=$(this).attr("id");
									return false;
								}
							});
							$("#student_channelId").val(s_id);
							$("#stuName").val(s_name);
							
							$(this).dialog("close"); 
						}, 
						'取消': function() { 
							//$("#student_channelId").val(s_id);
							//$("#stuName").val(""");
							$(this).dialog("close"); 
						} 
					}
				});
			
			});
			//取消证件号验证
			function quxiaoShenFenZhengYanZheng(obj){
				if(obj.value=='<%=Constants.CERTIFICATE_TYPE_DRIVER_ID%>'||obj.value=='<%=Constants.CERTIFICATE_TYPE_NCO_ID%>'){
					$("#sfzh").attr("disabled",false).unFormValidator(true);
				}else{
					$("#sfzh").attr("disabled",false).unFormValidator(false);
				}
				
			} 
		</script>

	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="学生录入">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<div id="error_message" style="display: none">
				<div>
					学生信息已存在！
				</div>
			</div>
			<div id="message_confirm" style="display: none">
				<div>
					确认执行该操作！
				</div>
			</div>
			<div id="message_laodaixin" style="display: none">
				<div>
					请选择学生！
				</div>
			</div>
			<div id="message_hezuofang" style="display: none">
				<div>
					请选择合作方！
				</div>
			</div>
			<div id="message_source" style="display: none">
				<div>
					请选择招生途径！
				</div>
			</div>
			<div id="message_way" style="display: none">
				<div>
					请选择市场途径！
				</div>
			</div>
			<div id="lianxifangshi" style="display: none">
				<div>
					请填写手机或座机！
				</div>
			</div>
			<form id="create_student_form">
				<table class="gv_table_2">

					<tr>
						<th style="width: 20px;">
							<img
								src="<%=Constants.WEB_IMAGES%>/images/title_menu/title_left.gif" />
						</th>
						<th style="text-align: left; font-weight: bold;">
							基本资料
						</th>

					</tr>
				</table>

				<table class="add_table" border="0">
					<tr>
						<td align="right" width="100px;">
							数据来源：
						</td>
						<td align="left" colspan="5">
							学习中心
						</td>
						<td></td>
					</tr>
					<tr>

						<td align="right" width="100px;">
							<span>*</span>姓名：
						</td>
						<td align="left" valign="middle" width="210px">
							<input type="text" class="txt_box_200" id="name"
								name="student.name" value="" />

						</td>
						<td>
							<div id="nameTip" style="width: 200px;"></div>
						</td>

						<td align="right" width="100px;">
							学历：
						</td>
						<td align="left">
							<select id="xueli" class="txt_box_150" name="student.degree">
						</td>
						<td>
							<div id="xueliTip" style="width: 200px;"></div>
						</td>

					</tr>
					<tr>
						<td align="right" width="100px;">
							证件号：
						</td>
						<td align="left">
							<select id="" class="txt_box_70" id="cert_type" onchange="quxiaoShenFenZhengYanZheng(this);"
								name="student.certType">
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
							<input type="text" id="sfzh" class="txt_box_130"
								name="student.certNo" value="" />
						</td>
						<td>
							<div id="sfzhTip" style="width: 200px;"></div>
						</td>
						<td align="right" width="100px;">
							性别：
						</td>
						<td align="left">

							<select id="sex" class="txt_box_150" name="student.gender">
								<option selected="selected" value="-1">
									--请选择--
								</option>
								<option value="<%=Constants.SEX_MALE%>">
									男
								</option>
								<option value="<%=Constants.SEX_FAMALE%>">
									女
								</option>
							</select>

						</td>
					</tr>
					<tr>
						<td align="right" width="100px;">
							<span>*</span>手机：
						</td>
						<td align="left">
							<input type="text" id="shouji" class="txt_box_200"
								name="student.mobile" value="" />

						</td>
						<td>
							<div id="shoujiTip" style="width: 200px;"></div>
						</td>
						<td align="right" width="100px;">
							邮件：
						</td>
						<td align="left">
							<input type="text" id="email" class="txt_box_150"
								name="student.email" value="" />

						</td>
						<td>
							<div id="emailTip" style="width: 200px;"></div>
						</td>
					</tr>
					<tr>
						<td align="right" width="100px;">
							<span>*</span>座机：
						</td>
						<td align="left">
							<input type="text" id="zuoji" class="txt_box_200"
								name="student.phone" value="" />
						</td>
						<td>
							<div id="zuojiTip" style="width: 200px;"></div>
						</td>
						<td align="right" width="100px;">
							<span>*</span>所在城市：
						</td>
						<td align="left">
							<select name="student.livingPlace" id="livingPlace"
								class="txt_box_150" style="margin-left: 10px;">
								<%@ include file="city.jsp"%>
							</select>
						</td>
						<td>
							<div id="livingPlaceTip" style="width: 200px;"></div>
						</td>
					</tr>
					<tr>
						<td align="right" width="100px;">
							
						</td>
						<td align="left" colspan="3">
							<font color="red">请填写您的手机号码，参考格式：13912345678;<br />若无手机号码，请填写您的座机号码，参考格式：010-66666666-1234</font>
						</td>
					</tr>
					<tr>
						<td align="right" width="100px;">
							MSN：
						</td>
						<td align="left">
							<input type="text" class="txt_box_200" name="student.msn"
								value="" />
						</td>
					</tr>
					<tr>
						<td align="right" width="100px;">
							QQ：
						</td>

						<td align="left">
							<input type="text" class="txt_box_200" value="" name="student.qq" />
						</td>
					</tr>

					<tr>
						<td align="right" width="100px;">
							邮编：
						</td>
						<td align="left">
							<input type="text" id="youbian" class="txt_box_200"
								name="student.zipcode" value="" />

						</td>
						<td>
							<div id="youbianTip" style="width: 200px;"></div>
						</td>
					</tr>

					<tr>
						<td align="right" width="100px;">
							地址：
						</td>
						<td align="left" colspan="2">
							<input type="text" class="txt_box_300" name="student.address"
								value="" />

						</td>

					</tr>
					<tr>
						<td align="right" width="100px;">
							备注：
						</td>
						<td align="left" colspan="2">
							<textarea rows="4" maxlength="200" class="txt_box_400"
								name="student.remark"></textarea>
						</td>
					</tr>
					<tr>
						<td align="right" width="100px;">
							<span>*</span>招生途径：
						</td>
						<td align="left">
							<select id="source" name="student.enrollmentSource"
								class="txt_box_150"></select>

						</td>
						<td>
							<div id="sourceTip" style="width: 200px"></div>
						</td>
					</tr>
					<tr>
						<td align="right" width="100px;" id="hezuofang">
							合作方：
						</td>
						<td align="left" id="hezuofang_t">
							<select id="channel" name="student.channelId" class="txt_box"></select>
						</td>
						<td>
							<!-- 
							<input type="text" class="txt_box_100" id="channelName" value="" />
							<input class="btn_black_61" type="button" onclick="ajax_005_1();" value="搜索" />
							 -->
						</td>
					</tr>
					<tr>
						<input type="hidden" id="enrollmentWayName"
							name="student.enrollmentWayName" />
						<td align="right" width="100px;">
							<span>*</span>市场途径：
						</td>
						<td align="left">
							<select id="way" class="txt_box_150" name="student.enrollmentWay"></select>
						</td>
						<td>
							<div id="wayTip" style="width: 200px"></div>
						</td>
					</tr>

				</table>
				<table class="gv_table_2">
					<tr>
						<th style="width: 20px;">
							<img
								src="<%=Constants.WEB_IMAGES%>/images/title_menu/title_left.gif" />
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
							<select class="txt_box_150" id="school" name="student.academyId"></select>
						</td>
						<td>
							<div id="schoolTip" style="width: 200px"></div>
						</td>
					</tr>
					<tr>
						<td align="right" width="100px;">
							批次：
						</td>
						<td align="left">
							<select class="txt_box_150" id="pici"
								name="student.enrollmentBatchId"></select>

						</td>
						<td>
							<div id="piciTip" style="width: 200px"></div>
						</td>
					</tr>

					<tr>
						<td align="right" width="100px;">
							层次：
						</td>
						<td align="left">
							<select class="txt_box_150" id="cengci" name="student.levelId"></select>

						</td>
						<td>
							<div id="cengciTip" style="width: 200px"></div>
						</td>
					</tr>
					<tr>
						<td align="right" width="100px;">
							专业：
						</td>
						<td align="left">
							<select class="txt_box_150" id="zhuanye" name="student.majorId"></select>

						</td>
						<td>
							<div id="zhuanyeTip" style="width: 200px"></div>
						</td>
					</tr>
				</table>
				<table class="gv_table_2">
					<tr>
						<th style="width: 20px;">
							<img
								src="<%=Constants.WEB_IMAGES%>/images/title_menu/title_left.gif" />
						</th>
						<th style="text-align: left; font-weight: bold;">
							报名
						</th>

					</tr>
				</table>

				<table class="add_table">
					<tr>
						<td align="right" width="100px;">
							该学生状态：
						</td>
						<td align="left">
							已分配
							<input type="hidden" id="status" name="student.status"
								value="<%=Constants.STU_CALL_STATUS_YI_FENG_PEI%>" />
						</td>
						</div>
					</tr>



					<tr>
						<td align="right" width="100px;">
							提示回访跟进时间：
						</td>
						<td align="left">
							<input id="BackDate" type="text" class="Wdate"
								class="txt_box_200"
								onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})"
								name="student.returnVisitTime" value="" />
						</td>
						<td></td>
					</tr>

					<tr>
						<td align="right" width="100px;">
							希望联系时间段：
						</td>

						<td align="left">
							<input type="text" class="txt_box_200" name="student.serviceTime"
								value="" />
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


		</body:body>
	</body>

</html>