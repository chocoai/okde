<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>老生续读（总部呼叫中心）</title>
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
var batchId = 0;
function all_branch_list_call_back(doc) {
	if (doc.branchlist == null || doc.branchlist.length == 0) {
		return;
	}
	school = $("#academyId");
	pici = $("#globalBatchId");
	cengci = $("#levelId");
	zhuanye = $("#majorId");
	branch = $("#branch");

	jQuery("#branch").html("");
	school.html("");
	pici.html("");
	cengci.html("");
	zhuanye.html("");
	branch.html("");
	$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo(branch);
	$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
	$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
	$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
	$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);

	jQuery(doc.branchlist).each(
			function() {
				if ('${student.branchId}' == this.id) {
					$(
							"<option selected='selected' value='" + this.id
									+ "'>" + this.name + "</option>").appendTo(
							$("#branch"));
				} else {
					$(
							"<option value='" + this.id + "'>" + this.name
									+ "</option>").appendTo($("#branch"));
				}
			});
	//移出所有事件
	branch.unbind();
	branch.change(function() {
		if ($(this).val() == 0) {
			pici.html("");
			$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
			school.html("");
			$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
			cengci.html("");
			$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
			zhuanye.html("");
			$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
			return;
		}
		//全局招生批次
			ajax_100_1();
		});
	//是否编辑
if(${student!=null}){
					//全局招生批次
					ajax_100_1();
				}
			}
			//ajax回调函数   全局批次(学习中心)
			function ajax_global_batch(data)
			{		
				jQuery('#globalBatchId').empty();
			    jQuery('#globalBatchId').append('<option value="0">请选择院校批次</option>');
			    if(data.globalBatchList!=null && data.globalBatchList.length>0)
			    {
			    	jQuery.each(data.globalBatchList,function(){	
			    		if(this.id=="${student.globalBatch}"){
			    			jQuery('#globalBatchId').append('<option selected="selected" value="'+this.id+'">'+this.batch+'</option>');
			    		}else{
			    			jQuery('#globalBatchId').append('<option  value="'+this.id+'">'+this.batch+'</option>');
			    		}
			    		 
			    	});
			   	}
			  	//移出所有事件
			   	pici.unbind();
			    pici.change(function(){
					if($(this).val()==0){
               				school.html("");
               				$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
							return;
					}
					ajax_110_1();//院校
				});
			  //是否编辑
				if(${student!=null}){
					ajax_110_1();//院校
				}
			}
			//ajax回调函数   院校(学习中心、全局批次)
			function ajax_academy(data)
			{				
				jQuery('#academyId').empty();
			    jQuery('#academyId').append('<option value="0">请选择院校</option>');
			    if(data.academyList!=null && data.academyList.length>0)
			    {
			    	jQuery.each(data.academyList,function(){	
			    		if('${student.academyId}'==this.id){
							$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
						}else{
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
						}
			    	});
			   	}
			    jQuery('#batchName').html('');
			   	jQuery('#batchId').val(0);
			   	//移出所有事件
			   	school.unbind();
			    school.change(function(){
					if($(this).val()==0){
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
							return;
					}	
					ajax_140_1();//招生批次	
				});	
			  //是否编辑
				if(${student!=null}){
					ajax_140_1();//招生批次
				}
			    
			}
			//ajax回调函数  层次(招生批次)
			function ajax_level(data)
			{				
				jQuery('#levelId').empty();
			    jQuery('#levelId').append('<option value="0">请选择院校层次</option>');
			    if(data.levellist!=null && data.levellist.length>0)
			    {
			    	jQuery.each(data.levellist,function(){	
			    		if('${student.levelId}'==this.level.id){
							$("<option  selected='selected' value='" + this.id + "'>" + this.level.name + "</option>").appendTo(cengci);
						}else{
							$("<option value='" + this.id + "'>" + this.level.name + "</option>").appendTo(cengci);
						}
			    	});
			   	}	
			  	//移出所有事件
			   	cengci.unbind();	
			   	cengci.change(function(){
					if($(this).val()==0){
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
							return;
					}	
					ajax_130_1();//专业
				});	
			  	//是否编辑
				if(${student!=null}){
					ajax_130_1();//专业
				}
			   		
			}
			//ajax回调函数  专业(层次)
			function ajax_major(data)
			{				
				jQuery('#majorId').empty();
			    jQuery('#majorId').append('<option value="0">请选择院校专业</option>');
			    if(data.majorlist!=null && data.majorlist.length>0)
			    {
			    	jQuery.each(data.majorlist,function(){	
			    		 
			    		if('${student.majorId}'==this.id){
			    			jQuery('#majorId').append('<option selected="selected" value="'+this.id+'" title="' + this.name + '" >'+this.name+'</option>');
			    		}else{
			    			jQuery('#majorId').append('<option value="'+this.id+'" title="' + this.name + '" >'+this.name+'</option>');
						 }
			    	});
			    }	
			}
			//ajax回调函数  招生批次(院校、全局批次)
			function ajax_batch(data)
			{				
			    if(data.batch!=null)
			    {
			    	jQuery('#batchName').html(data.batch.enrollmentName);
			   		jQuery('#batchId').val(data.batch.id);
			    }	
			    else
			    {
			    	jQuery('#batchName').html('');
			   		jQuery('#batchId').val(0);
			    }
			    //batchId=parseInt(jQuery('#batchId').val());
			    
			    ajax_120_1();//层次	
			}
		</script>
		<a:ajax successCallbackFunctions="all_branch_list_call_back"
			pluginCode="000" urls="/crm/all_branch_list" isOnload="all" />

		<!--全局批次(学习中心)-->
		<a:ajax successCallbackFunctions="ajax_global_batch"
			parameters="{branchId:jQuery('#branch').val()}"
			urls="/enrollment/cascade_global_batch_branch_ajax" pluginCode="100" />

		<!--院校(学习中心、全局批次)-->
		<a:ajax successCallbackFunctions="ajax_academy"
			parameters="{branchId:jQuery('#branch').val(),globalBatchId:jQuery('#globalBatchId').val()}"
			urls="/enrollment/cascade_branch_global_batch_academy_ajax"
			pluginCode="110" />

		<!--层次(招生批次)-->
		<a:ajax successCallbackFunctions="ajax_level"
			parameters="{batchId:jQuery('#batchId').val()}"
			urls="/enrollment/cascade_batch_level_ajax" pluginCode="120" />

		<!--专业(层次)-->
		<a:ajax successCallbackFunctions="ajax_major"
			parameters="{levelId:jQuery('#levelId').val()}"
			urls="/enrollment/cascade_level_major_ajax" pluginCode="130" />

		<!--招生批次(院校、全局批次)-->
		<a:ajax successCallbackFunctions="ajax_batch"
			parameters="{academyId:jQuery('#academyId').val(),globalBatchId:jQuery('#globalBatchId').val()}"
			urls="/enrollment/cascade_global_batch_academy_batch_ajax"
			pluginCode="140" />

		<script type="text/javascript">
var sex = -1;

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
function cengci(doc) {
	$("#cengci").html("");
	$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo($("#cengci"));
	$(doc.academyLevels).each(
			function() {
				if ('${student.levelId}' == this.level.id) {
					sValue = this.id;
					$(
							"<option  selected='selected' value='" + this.id
									+ "'>" + this.level.name + "</option>")
							.appendTo($("#cengci"));
				} else {
					$(
							"<option value='" + this.id + "'>"
									+ this.level.name + "</option>").appendTo(
							$("#cengci"));
				}
			});

	$("#branch").html("");
	$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo($("#branch"));
	$(doc.branchlist).each(
			function() {

				if ('${student.branchId}' == this.id) {
					$(
							"<option selected='selected' value='" + this.id
									+ "'>" + this.name + "</option>").appendTo(
							$("#branch"));
				} else {
					$(
							"<option value='" + this.id + "'>" + this.name
									+ "</option>").appendTo($("#branch"));
				}
			});
			//是否呼叫
			if(${student!=null}){
				ajax_002_5();//专业
				}
		}

			
			//增加学生
			function addStudentCallBack(doc){
				if(doc.student!=null){
					//刷新学生跟进记录	
					studentId=doc.student.id;
					//学生隐藏ID
					$("#student_id").val(studentId);
					if(doc.student.status==STU_CALL_STATUS_YU_BAO_MING || doc.student.status==STU_CALL_STATUS_WU_YI_YUAN_CHUSHI)
					{
						
					}
					else
					{
						$("#save_and_push").css({"display":"none"});
						$("#save_and_nopush").css({"display":"none"});
					}
				}
				$("#save").removeAttr("disabled");
				$("#save_and_push").removeAttr("disabled");
				$("#save_and_nopush").removeAttr("disabled");
				$("button").removeAttr("disabled");
				//$("#sfzh").attr("disabled","true");
				alert('老生续读保存成功！！');
				window.location.href='list_student_continue_call';
				//search001();
			}
		</script>

		<a:ajax pluginCode="002" urls="finance/studentsenrolled/upate_student_continue_call_ajax"
			parameters="$('#create_student_form').serializeObject()"
			successCallbackFunctions="addStudentCallBack" />

		<script type="text/javascript">
var pcas;
var studentId = 0;
var sValue = 0;
var studentBranchId = 0;
//电话号码
var phone_number;
//增加学生
function addStudent() {
	//增加学生
	ajax_002_1();
}

//呼叫等级
function callGradeValue(callStatusId) {
	return callStatusId.getCallGrade();
}
//跟进状态
function statusValue(statusId) {
	return statusId.getStudentStatus();
}
//回访
function followUpNameValue(followUpName) {
	return followUpName;
}
//修改隐藏状态值
function changeHiddenStatus(status) {
	//修改为已导入
	$("#status").val(status);
}

$(document).ready(function(){
				//学生ID
				studentId=parseInt("${student==null?0:student.id}");
				//学生中心
				studentBranchId=parseInt("${student==null?0:student.branchId}");
				//选中出生地下拉菜单
				$("#livingPlace").val("${student.livingPlace}");
				//证件类型
				$("#cert_type").val("${student.certType}");
				//性别
				$("#sex").val("${student.gender}");
				//表单验证				
				$.formValidator.initConfig({formID:"create_student_form",submitOnce:false,debug:true,
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
						addStudent();
						return false;
					},
					submitAfterAjaxPrompt:"当前有数据正在进行服务器端校验，请稍候"
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
				})
				<c:if test="${student!=null}">
					.defaultPassed();
				</c:if>
				
				$("#email").formValidator({empty:true,onShow:"请输入邮箱,可以为空！",onFocus:"请输入邮箱,可以为空！",onCorrect:"邮箱验证通过"}).inputValidator({min:6,max:50,onError:"你输入的邮箱长度错误,请确认"}).regexValidator({regExp:regexEnum.email,onError:"你输入的邮箱格式不正确"})
				<c:if test="${student!=null}">
					.defaultPassed();
				</c:if>
				
				<c:if test="${(student!=null&&student.status==1)||(student==null)}">
					$("#branch").formValidator({onShow:"请选择你的学习中心",onFocus:"学习中心必须选择",onCorrect:"输入正确"}).inputValidator({min:1,onError: "你是不是忘记选择学习中心了!"})
						<c:if test="${student!=null}">
							.defaultPassed();
						</c:if>
				</c:if>
				
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
				
				$('#lianxifangshi').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'提示',
					width: 260,
					height: 125
					
				});	
			});
		</script>

	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="老生续读">
		</head:head>
		<!--主体层开始 -->
		<body:body>
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
			<table style="width: 100%; border: 0px;">
				<tr>
					<td style="width: 44%;" valign="top">
						<table class="gv_table_2">
							<tr>
								<th style="width: 20px;">
									<img
										src="<%=Constants.WEB_IMAGES%>/plugin/base_css/images/operating/title_left.gif" />
								</th>
								<th style="text-align: left; font-weight: bold;">
									客服回访记录
								</th>
							</tr>
						</table>
						
						<page:plugin pluginCode="001" il8nName="crm"
							searchListActionpath="/cedu/crm/crm_followup_list"
							searchCountActionpath="/cedu/crm/crm_followup_count"
							columnsStr="#returnVisit;createdTime;remark"
							customColumnValue="0,followUpNameValue(followUpName)"
							pageSize="10" isNumber="true"
							params="'followUp.studentId':studentId,'followUp.statusId':STU_CALL_STATUS_YU_BAO_MING" />
					</td>
					<td style="width: 56%;" valign="top">
						<c:if test="${request.students!=null}">
							<table class="gv_table_2">
								<tr>
									<th style="width: 20px;">
										<img
											src="<%=Constants.WEB_IMAGES%>/images/title_menu/title_left.gif" />
									</th>
									<th style="text-align: left; font-weight: bold;">
										电话号码匹配人员
									</th>
								</tr>
							</table>
							<table class="gv_table_2" border="0" cellpadding="0"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th align="center">
											姓名
										</th>
										<th align="center">
											证件号
										</th>
										<th align="center">
											手机
										</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="stu" items="${request.students}">
										<tr>
											<td width="80px" align="center"
												style="background-color: yellow;">
												<a
													href="<%=Constants.WEB_PATH%>/crm/view_home?phone=${stu.mobile}&studentId=${stu.id}"
													target="_blank" style="cursor: pointer;">${stu.name}</a>
											</td>
											<td align="center" style="background-color: yellow;">
												${stu.certNo}
											</td>
											<td width="280px" align="left"
												style="padding-left: 10px; background-color: yellow;">
												${stu.mobile}
											</td>
										</tr>
									</c:forEach>
								</tbody>
								</c:if>
								<form id="create_student_form" style="margin: 0px;">
									<input type="hidden" id="student_id" name="student.id"
										value="${student==null?0:student.id}" />
									<!-- 锁定 -->
									<c:if test="${student==null }">
										<!-- 姓名锁定 -->
										<input type="hidden" id="name_locking_hidden"
											name="student.nameLockingStatus" value="0" />
										<!-- 证件号锁定 -->
										<input type="hidden" id="cert_no_locking_hidden"
											name="student.certNoLockingStatus" value="0" />
									</c:if>
									<!-- 姓名未锁定 -->
									<c:if test="${student!=null&&student.nameLockingStatus==0 }">
										<!-- 姓名锁定 -->
										<input type="hidden" id="name_locking_hidden"
											name="student.nameLockingStatus" value="0" />
									</c:if>
									<!-- 证件号未锁定 -->
									<c:if test="${student!=null&&student.certNoLockingStatus==0 }">
										<!-- 证件号锁定 -->
										<input type="hidden" id="cert_no_locking_hidden"
											name="student.certNoLockingStatus" value="0" />
									</c:if>
									<table class="gv_table_2">
										<tr>
											<th style="width: 20px;">
												<img
													src="<%=Constants.WEB_IMAGES%>/plugin/base_css/images/operating/title_left.gif" />
											</th>
											<th style="text-align: left; font-weight: bold;">
												基本资料
											</th>
										</tr>
									</table>
									<table class="add_table" border="0">
										<tr>

											<td align="right" width="100px;">
												<span>*</span>姓名：
											</td>
											<td align="left" valign="middle">
												${student.name}
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												手机：
											</td>
											<td align="left">
												<div id="shoujiTip"
													style="width: 180px; float: right; padding-right: 100px; height: 18px;"></div>
												<input type="text" id="shouji" class="txt_box_150"
													name="student.mobile" value="${student.mobile}" />
												<!-- 调出学生信息 -->
												<a id="diao_chu_xin_xi" style="display: none; float: right;"
													href="#" target="_blank">调出信息</a>
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												座机：
											</td>
											<td align="left">
												<input type="text" id="zuoji" class="txt_box_200"
													name="student.phone" value="${student.phone}" />
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												性别：
											</td>
											<td align="left">
												<select id="sex" class="txt_box_100" name="student.gender">
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
												所在城市：
											</td>
											<td align="left">
												<select name="student.livingPlace" id="livingPlace"
													class="txt_box_150" style="margin-left: 10px;">
													<%@ include file="../../crm/city.jsp"%>
												</select>
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												证件类型：
											</td>
											<td>
												<select class="txt_box_70" id="cert_type"
													name="student.certType" disabled="disabled">
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
												${student.certNo}
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												MSN：
											</td>
											<td align="left">
												<input type="text" id="msn" class="txt_box_150"
													name="student.msn" value="${student.msn}" />
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												QQ：
											</td>
											<td align="left">
												<input type="text" id="qq" class="txt_box_150"
													value="${student.qq}" name="student.qq" />
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												邮件：
											</td>
											<td align="left">
												<div id="emailTip"
													style="width: 180px; float: right; padding-right: 100px; height: 18px;"></div>
												<input type="text" id="email" class="txt_box_150"
													name="student.email" value="${student.email}" />
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												单位信息：
											</td>
											<td align="left">
												<textarea id="workUnitInfo" rows="4" maxlength="200"
													class="txt_box_350" name="student.workUnitInfo">${student.workUnitInfo}</textarea>
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												备注：
											</td>
											<td align="left">
												<textarea id="remark" rows="4" maxlength="200"
													class="txt_box_350" name="student.remark">${student.remark}</textarea>
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												招生途径：
											</td>
											<td align="left">
												老生续读
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												数据来源：
											</td>
											<td align="left">
												呼叫中心
											</td>
										</tr>
									</table>
									<table class="gv_table_2">
										<tr>
											<th style="width: 20px;">
												<img
													src="<%=Constants.WEB_IMAGES%>/plugin/base_css/images/operating/title_left.gif" />
											</th>
											<th style="text-align: left; font-weight: bold;">
												初步意向
											</th>

										</tr>
									</table>
									<table class="add_table">
										<tr>
											<td align="right" width="100px;">
												<span>*</span>学习中心：
											</td>
											<td align="left">
												<div id="branchTip"
													style="width: 180px; float: right; padding-right: 100px; height: 18px;"></div>
												<select class="txt_box_150" id="branch"
													name="student.branchId"></select>
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												批次：
											</td>
											<td align="left">
												<select class="txt_box_150" id="globalBatchId"></select>
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												院校：
											</td>
											<td align="left">

												<select class="txt_box_150" id="academyId"
													name="student.academyId"></select>
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												招生批次：
											</td>
											<td align="left">
												<span id="batchName" name="batch" style="color: black"></span>
												<input id="batchId" type="hidden" value="0"
													name="student.enrollmentBatchId"/>
											</td>
										</tr>

										<tr>
											<td align="right" width="100px;">
												层次：
											</td>
											<td align="left">

												<select class="txt_box_150" id="levelId"
													name="student.levelId"></select>
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												专业：
											</td>
											<td align="left">

												<select class="txt_box_150" id="majorId"
													name="student.majorId"></select>
											</td>
										</tr>
									</table>
									<table class="gv_table_2">
										<tr>
											<th style="width: 20px;">
												<img
													src="<%=Constants.WEB_IMAGES%>/plugin/base_css/images/operating/title_left.gif" />
											</th>
											<th style="text-align: left; font-weight: bold;">
												报名
											</th>
										</tr>
									</table>

									<table class="add_table">
										<tr>
											<td align="right" width="100px;">
												学生状态：
											</td>
											<td align="left">
												<div id="status_div">
													预报名
													<input type="hidden" id="status" name="student.status"
														value="<%=Constants.STU_CALL_STATUS_YU_BAO_MING%>" />
												</div>
											</td>
										</tr>
										<tr>
											<td align="right" width="100px;">
												希望联系时间：
											</td>
											<td align="left">
												<input type="text" class="txt_box_200"
													name="student.serviceTime" value="${student.serviceTime}" />
											</td>
										</tr>
									</table>
									<table class="gv_table_2">
										<tr>
											<th style="width: 20px;">
												<img
													src="<%=Constants.WEB_IMAGES%>/plugin/base_css/images/operating/title_left.gif" />
											</th>
											<th style="text-align: left; font-weight: bold;">
												其他
											</th>
										</tr>
									</table>
									<table class="add_table">
										<tr>
											<td align="right" width="100px;">
												客服回访内容：
											</td>
											<td align="left">
												<textarea rows="4" cols="60" class="txt_box_350"
													name="followUp.remark"></textarea>
											</td>
										</tr>
									</table>
									<table class="add_table">
										<tr>
											<td align="center" colspan="2">
												<input class="btn_black_61" type="submit" id="save"
													onclick="changeHiddenStatus(<%=Constants.STU_CALL_STATUS_YU_BAO_MING%>);"
													value="保存" />
												<input class="btn_black_130" type="submit"
													id="save_and_push"
													onclick="changeHiddenStatus(<%=Constants.STU_CALL_STATUS_YI_DAO_RU%>);"
													value="保存并推送" />
												<input class="btn_black_130" type="submit"
													id="save_and_nopush"
													onclick="changeHiddenStatus(<%=Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI%>);"
													value="无意愿不推送" />
												<input class="btn_black_130" type="button"
													onclick="window.location.href='list_student_continue_call';"
													value="返回" />
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