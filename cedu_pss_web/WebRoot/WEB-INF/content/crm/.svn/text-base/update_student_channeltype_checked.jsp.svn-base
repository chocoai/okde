<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改学生信息</title>
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
			var batchId=0;
			function all_branch_list_call_back(doc){
				if(doc.branchlist==null||doc.branchlist.length==0){
					return;
				}
				school=$("#academyId");
				pici=$("#globalBatchId");
				cengci=$("#levelId");
				zhuanye=$("#majorId");
				branch=$("#branch");
				
				jQuery("#branch").html("");
				school.html("");
				pici.html("");
				cengci.html("");
				zhuanye.html("");
				branch.html("");
				$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo(branch);
				$("<option value='" + 0 + "'>请选择全局批次</option>").appendTo(pici);
           		$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
           		$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
           		$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
           		
				jQuery(doc.branchlist).each(function(){
					if('${student.branchId}'==this.id){
						$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
					}else{
						$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
					}
				});
				//移出所有事件
			   	branch.unbind();
				branch.change(function(){
					if($(this).val()==0){
							pici.html("");
           					$("<option value='" + 0 + "'>请选择全局批次</option>").appendTo(pici);
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
			    jQuery('#globalBatchId').append('<option value="0">请选择全局批次</option>');
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
		<a:ajax successCallbackFunctions="all_branch_list_call_back" pluginCode="000" urls="/crm/all_branch_list" isOnload="all"/>

		<!--全局批次(学习中心)-->
		<a:ajax successCallbackFunctions="ajax_global_batch" parameters="{branchId:jQuery('#branch').val()}" urls="/enrollment/cascade_global_batch_branch_ajax" pluginCode="100"/>
		
		<!--院校(学习中心、全局批次)-->
		<a:ajax successCallbackFunctions="ajax_academy" parameters="{branchId:jQuery('#branch').val(),globalBatchId:jQuery('#globalBatchId').val()}" urls="/enrollment/cascade_branch_global_batch_academy_ajax" pluginCode="110"/>
		
		<!--层次(招生批次)-->
		<a:ajax successCallbackFunctions="ajax_level" parameters="{batchId:jQuery('#batchId').val()}" urls="/enrollment/cascade_batch_level_ajax" pluginCode="120"/>
		
		<!--专业(层次)-->
		<a:ajax successCallbackFunctions="ajax_major" parameters="{levelId:jQuery('#levelId').val()}" urls="/enrollment/cascade_level_major_ajax" pluginCode="130"/>
		
		<!--招生批次(院校、全局批次)-->
		<a:ajax successCallbackFunctions="ajax_batch" parameters="{academyId:jQuery('#academyId').val(),globalBatchId:jQuery('#globalBatchId').val()}" urls="/enrollment/cascade_global_batch_academy_batch_ajax" pluginCode="140"/>

		<script type="text/javascript">
			var sex=-1;
			
			//批次
			function pici(doc){
				$("#pici").html("");
               	$("<option value='" + 0 + "'>请选择全局批次</option>").appendTo($("#pici"));
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
				alert('保存成功！！');
				search001();
			}
			//更新学生
			function updateStudentCallBack(doc){
				if(doc.student!=null){
					//刷新学生跟进记录
					studentId=doc.student.id;
					//学生隐藏ID
					$("#student_id").val(studentId);
					if(doc.student.status==1 || doc.student.status==STU_CALL_STATUS_WU_YI_YUAN_CHUSHI)
					{}
					else
					{
						$("#save_and_push").css({"display":"none"});
						$("#save_and_nopush").css({"display":"none"});
						$("#status_div").html(doc.student.status.getStudentStatus());
						//$("#sfzh").attr("disabled","true");
						
						$("#school").attr("disabled","true");
						$("#pici").attr("disabled","true");
						$("#branch").attr("disabled","true");
						$("#cengci").attr("disabled","true");
						$("#zhuanye").attr("disabled","true");
					}
				}
				$("#save").removeAttr("disabled");
				$("#save_and_push").removeAttr("disabled");
				$("#save_and_nopush").removeAttr("disabled");
				$("button").removeAttr("disabled");
				alert('保存成功！！');
				search001();
				search002();
			}
			//招生途径和市场途径回调函数
			function wayAndSourceCallback(doc){
								$("#source").html("");
	               				//$("<option value='" + 0 + "'>请选择招生途径</option>").appendTo($("#source"));
								var islaodaixin = false;//标识列表中是否出现老带新
								$(doc.enrollmentSources).each(function(){
								
									if(this.id=='${student.enrollmentSource}'){
										$("<option selected='selected' value='" + this.id + "' title='" + this.name + "' >" + this.name + "</option>").appendTo($("#source"));
									}else{
										$("<option value='" + this.id + "'  title='" + this.name + "' >" + this.name + "</option>").appendTo($("#source"));
									}
									//添加老生续读选项by dongminghao 2012-03-07 15:57:01
									if(this.id==WEB_STU_ENROLLMENT_SOURCE){
										if('${student.enrollmentSource}'==WEB_STU_LAO_SHENG_XU_DU){
											$("<option selected='selected' value='" + WEB_STU_LAO_SHENG_XU_DU + "'  title='老生续读' >老生续读</option>").appendTo($("#source"));
										}else{
											$("<option value='" + WEB_STU_LAO_SHENG_XU_DU + "'  title='老生续读' >老生续读</option>").appendTo($("#source"));
										}
										islaodaixin = true;
									}
								});
								//如果存在老带新，则老生续读加入在老带新下面，如果不存在老带新，则老生续读放在最后
								if(!islaodaixin){
									if('${student.enrollmentSource}'==WEB_STU_LAO_SHENG_XU_DU){
										$("<option selected='selected' value='" + WEB_STU_LAO_SHENG_XU_DU + "'  title='老生续读' >老生续读</option>").appendTo($("#source"));
									}else{
										$("<option value='" + WEB_STU_LAO_SHENG_XU_DU + "'  title='老生续读' >老生续读</option>").appendTo($("#source"));
									}
								}
								
								$("#way").html("");
	               				var wayStr="<option value='" + 0 + "'>请选择市场途径</option>";
								$.each(doc.enrollmentWaysMap,function(key,value){
									if(key!="大客户"&&key!="渠道"&&key!="老带新"&&key!="加盟"&&key!="共建"){
										wayStr+="<optgroup label='"+key+"'>";
										$(this).each(function(){
											if(this.id=='${student.enrollmentWay}'){
												wayStr+="<option selected='selected' value='" + this.id + "'>" + this.name + "</option>";
											}else{
												wayStr+="<option value='" + this.id + "'>" + this.name + "</option>";
											}
										});
										wayStr+="</optgroup>";
									}
								});
								$("#way").html(wayStr);
								$("#channel").html("");
	               				$("<option value='" + 0 + "'>请选择合作方</option>").appendTo($("#channel"));
	               				
	               				$("#channel").attr("disabled",true);
	               				$("#way").attr("disabled",false);
	               				
								$("#source").change(function(){
										if($('#source').val()==0){
											$("#channel").html("");
	               							$("<option value='" + 0 + "'>请选择合作方</option>").appendTo($("#channel"));
											$('#way option:selected').val(0);
											$('#way option:selected').text('请选择市场途径');
											
											$("#channel").attr("disabled",true);
	               							$("#way").attr("disabled",true);
	               							
											return;
										}
										if($('#source').val()==WEB_STU_SOURCE_DEFAULT){
											//社招
											$("#hezuofang").html("合作方：");
											$("#channel").val("0");
											$("#channel").attr("disabled",true);
											$("#way").attr("disabled",false);
											
											$('#way option:selected').val(0);
											$('#way option:selected').text('请选择市场途径')
											
											$("#way").html("");
				               				var wayStr="<option value='" + 0 + "'>请选择市场途径</option>";
											$.each(doc.enrollmentWaysMap,function(key,value){
												if(key!="大客户"&&key!="渠道"&&key!="老带新"&&key!="加盟"&&key!="共建"){
													wayStr+="<optgroup label='"+key+"'>";
													$(this).each(function(){
														if(this.id=='${student.enrollmentWay}'){
															wayStr+="<option selected='selected' value='" + this.id + "'>" + this.name + "</option>";
														}else{
															wayStr+="<option value='" + this.id + "'>" + this.name + "</option>";
														}
													});
													wayStr+="</optgroup>";
												}
											});
											$("#way").html(wayStr);
										
										}else{
											$("#hezuofang").html("<span>*</span>合作方：");
											$("#hezuofang_t").html('<select id="channel" name="student.channelId" class="txt_box"></select>');											
											$('#way option:selected').text($('#source option:selected').text());
											$('#way option:selected').val(1);
											$('#enrollmentWayName').val($('#source option:selected').text());
											$("#way").attr("disabled",true);
											if($('#source').val()==WEB_STU_LAO_SHENG_XU_DU){
												$("#channel").attr("disabled",true);
											}
										}
										ajax_005_1();
								});
								<c:if test="${student!=null}">
									//激活招生途径change事件
									$("#source").trigger("change");
								</c:if>
								
			}
			//查询合作方
			function channelCallback(doc){
					$("#channel").html("");
	               	$("<option value='" + 0 + "'>请选择合作方</option>").appendTo($("#channel"));
					$(doc.channels).each(function(){
						if(this.id=='${student.channelId}'){
							$("<option selected='selected' value='" + this.id + "' title=" + this.name + " >" + this.name + "</option>").appendTo($("#channel"));
						}else{
							$("<option value='" + this.id + "' title=" + this.name + " >" + this.name + "</option>").appendTo($("#channel"));
						}
					});
			}
			
			//根据手机号调出学生信息
			function select_student_phone_success_callback(doc)
			{
				if(null!=doc.student)
				{
					if(null!=doc.student.name)
					{
						$('#name').val(doc.student.name);
					}
					if(null!=doc.student.gender)
					{
						$('#sex').val(doc.student.gender);
					}
					if(null!=doc.student.certNo)
					{
						$('#sfzh').val(doc.student.certNo);
					}
					if(null!=doc.student.email)
					{
						$('#email').val(doc.student.email);
					}
					if(null!=doc.student.remark)
					{
						$('#remark').val(doc.student.remark);
					}
					if(null!=doc.student.branchId)
					{
						$('#branch').val(doc.student.branchId);
					}
					if(null!=doc.student.workUnitInfo)
					{
						$('#workUnitInfo').val(doc.student.workUnitInfo);
					}
					if(null!=doc.student.qq)
					{
						$('#qq').val(doc.student.qq);
					}
					if(null!=doc.student.msn)
					{
						$('#msn').val(doc.student.msn);
					}
					
					
				}
			}
			
			//学生数据来源
			function studentDataSourceCallback(data)
			{
				$("#studentDataSource").html("");
               	$("<option value='" + 0 + "'>请选择学生数据来源</option>").appendTo($("#studentDataSource"));
				$(data.basedictlst).each(function(){
					if(this.id=='${student.studentDataSource}')
					{
						$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo($("#studentDataSource"));
					}
					else
					{
						$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#studentDataSource"));
					}
				});
			}
		</script>
		
		<a:ajax pluginCode="002" urls="/crm/add_student_cc"  parameters="$('#create_student_form').serializeObject()"  successCallbackFunctions="addStudentCallBack" />
		
		<a:ajax pluginCode="003" urls="/crm/update_student_channeltype_checked_ajax" parameters="$('#create_student_form').serializeObject()" successCallbackFunctions="updateStudentCallBack" />
		
		<a:ajax pluginCode="004" urls="/crm/update_student_way_list" parameters="{branchId:jQuery('#branchId').val()}"  successCallbackFunctions="wayAndSourceCallback"  isOnload="all"/>
		
		<a:ajax pluginCode="005" urls="crm/student_channel_list_checked" parameters="{'id':parseInt($('#source').val()),'branchId':studentBranchId}" successCallbackFunctions="channelCallback" />
		
		<a:ajax parameters="{'basedicttype':BASEDICT_STYLE_STUDATASOURCE}" successCallbackFunctions="studentDataSourceCallback" pluginCode="0002" urls="/basesetting/basedict/list_base_dict_flag" isOnload="all"/>
		
		<script type="text/javascript">
			var pcas;
			var studentId=0;
			var sValue=0;
			var studentBranchId=0;
			//电话号码
			var phone_number;
			//增加学生
			function addStudent(){
				//增加学生
				ajax_002_1();
			}
			//更新学生
			function updateStudent(){
				//更新学生
				ajax_003_1();
			}
			
			
			//呼叫等级
			function callGradeValue(callStatusId){
				return callStatusId.getCallGrade();
			}
			//跟进状态
			function statusValue(statusId){
				return statusId.getStudentStatus();
			}
			//回访
			function followUpNameValue(followUpName){
				return followUpName;
			}
			//修改隐藏状态值
			function changeHiddenStatus(status){
				//修改为已导入
				$("#status").val(status);
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
				//如果该学生没有全局批次则可以修改全局批次
				if('${student.globalBatch}'=='' || '${student.globalBatch}'=='0')
				{
					$('#globalBatchId').attr({'disabled':''});
				}
				
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
				//招生途径
				if(${student.isChannelTypeChecked}==STUDENT_CHANNEL_TYPE_CHECKED_TRUE)
				{
					jQuery("#isChannelTypeChecked").attr("checked",true);
				}
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
					
						if($("#student_id").val()=="0"){
							
						}else{
							//请选择学生来源
							if($("#source").val()=="0"){
								$('#message_source').dialog("open");
							}else{
								//市场途径
								if($("#way").val()=="0"){
									$('#message_way').dialog("open");
								}else{
									
									if($('#source').val()==WEB_STU_SOURCE_DEFAULT){
										//社招
										updateStudent();
									}else{
										if($("#channel").val()!=null&&$("#channel").val()!="0"){
											updateStudent();
										}else{
											//如果是老生续读则不用选合作方 by dongminghao 2012-03-07 16:07:20
											if($("#source").val()==WEB_STU_LAO_SHENG_XU_DU){
												updateStudent();
											}
											else{
												$('#message_hezuofang').dialog("open");
											}
										}
									}
								}
							}
							
						}
						return false;
					},
					submitAfterAjaxPrompt:"当前有数据正在进行服务器端校验，请稍候"
				});
				
				
				$("#name").formValidator({onShow:"请输入姓名！必须输入姓",onFocus:"请输入姓名！必须输入姓",onCorrect:"姓名验证通过"}).inputValidator({min:2,max:20,onError:"你输入的姓名错误,请确认"})
				<c:if test="${student!=null}">
					.defaultPassed();
				</c:if>
				//证件号
				
				$("#sfzh").formValidator({onShow:"请输入证件号",onFocus:"请输入证件号",onCorrect:"输入正确"})
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
				});//.defaultPassed();
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
				
				$("#email").formValidator({empty:true,onShow:"请输入邮箱,可以为空！",onFocus:"请输入邮箱,可以为空！",onCorrect:"邮箱验证通过"}).inputValidator({min:6,max:50,onError:"你输入的邮箱长度错误,请确认"}).regexValidator({regExp:regexEnum.email,onError:"你输入的邮箱格式不正确"})
				<c:if test="${student!=null}">
					.defaultPassed();
				</c:if>
				
				$("#studentDataSource").formValidator({onShow:"请选择数据来源",onFocus:"数据来源必须选择",onCorrect:"输入正确"}).inputValidator({min:1,onError: "你是不是忘记选择数据来源了!"})
					.defaultPassed();
				
				<c:if test="${(student!=null&&student.status==1)||(student==null)}">
					$("#branch").formValidator({onShow:"请选择你的学习中心",onFocus:"学习中心必须选择",onCorrect:"输入正确"}).inputValidator({min:1,onError: "你是不是忘记选择学习中心了!"})
						<c:if test="${student!=null}">
							.defaultPassed();
						</c:if>
				</c:if>
				
				$("#globalBatchId").formValidator({onShow:"请选择你的批次",onFocus:"批次必须选择",onCorrect:"输入正确"}).inputValidator({min:1,onError: "你是不是忘记选择批次了!"})
				
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
		<head:head title="${student==null?'呼叫首页':'学生详情'}">
		</head:head>
		<!--主体层开始 -->
		<body:body>
					<div id="message_confirm" style="display:none">
						<div>确认执行该操作！</div>
					</div>
					<div id="message_laodaixin" style="display:none">
						<div>请选择学生！</div>
					</div>
					<div id="message_hezuofang" style="display:none">
						<div>请选择合作方！</div>
					</div>
					<div id="message_source" style="display:none">
						<div>请选择招生途径！</div>
					</div>
					<div id="message_way" style="display:none">
						<div>请选择市场途径！</div>
					</div>
						<div id="lianxifangshi" style="display:none">
						<div>请填写手机或座机！</div>
					</div>
					
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
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="crm_followup_list"
										searchCountActionpath="crm_followup_count"
										columnsStr="#returnVisit;createdTime;remark"
										customColumnValue="0,followUpNameValue(followUpName)"
										pageSize="10"
										isNumber="true"
										subStringLength="1000"
										columnsWidth="[2,300px]"
										params="'followUp.studentId':studentId"
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
											<span>*</span>姓名：
										</td>
										<td align="left" valign="middle">
											<div id="nameTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<input type="text" class="txt_box_100" id="name" name="student.name"  value="${student.name}" />
											
										</td>
										
									</tr>
									<tr>
										<td align="right" width="100px;">
											手机：
										</td>
										<td align="left">
											
											<div id="shoujiTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<input type="text" id="shouji" class="txt_box_150" name="student.mobile" value="${student.mobile}" />
											<!-- 调出学生信息 -->
											<a id="diao_chu_xin_xi" style="display: none;float: right;" href="#" target="_blank">调出信息</a>
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
											<select class="txt_box_70" id="cert_type"  name="student.certType">
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
										
											<div id="sfzhTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											 <input type="text" id="sfzh" class="txt_box_150" name="student.certNo" value="${student.certNo}" /> 
										
										</td>
									</tr>
							
									<tr>
							
										<td align="right" width="100px;">
											MSN：
										</td>
										<td align="left">
											<input type="text" id="msn" class="txt_box_150" name="student.msn"
												value="${student.msn}" />
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											QQ：
										</td>

										<td align="left">
											<input type="text" id="qq" class="txt_box_150" value="${student.qq}"  name="student.qq"/>
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
											单位信息：
										</td>
										<td align="left">
											<textarea id="workUnitInfo" rows="4" maxlength="200"  class="txt_box_350" name="student.workUnitInfo">${student.workUnitInfo}</textarea>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											备注：
										</td>
										<td align="left">
											<textarea id="remark" rows="4" maxlength="200"  class="txt_box_350" name="student.remark">${student.remark}</textarea>
										</td>
									</tr>
									
									<tr>
										<td align="right" width="100px;">
											<span>*</span>招生途径：
										</td>
										<td align="left">
											<select id="source" name="student.enrollmentSource" class="txt_box_150"></select>
											<input type="checkbox" name="student.isChannelTypeChecked" id="isChannelTypeChecked" value="<%=Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE %>"/>复核完毕
										</td>
									
									</tr>
									<tr>
										<td align="right" width="100px;" id="hezuofang">
											合作方：
										</td>
										<td align="left" id="hezuofang_t">
												<select id="channel" name="student.channelId"  class="txt_box"></select>
										</td>
										
									</tr>
									<tr>
									
										<td align="right" width="100px;">
											<span>*</span>市场途径：
											<input type="hidden" id="enrollmentWayName" name="student.enrollmentWayName" />
										</td>
										<td align="left">
											<select id="way" class="txt_box_150"  name="student.enrollmentWay"></select>
										</td>
										
									</tr>
									<tr>
										<td align="right" width="100px;"><span>*</span>数据来源：</td>
										<td align="left">
											<div id="studentDataSourceTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<select id="studentDataSource" name="student.studentDataSource" class="txt_box_150" />
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
											学习中心：
										</td>
										<td align="left">
											<div id="branchTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
											<select disabled="disabled" class="txt_box_150" id="branch"  name="student.branchId"></select>
											<input type="hidden" name="branchId" id="branchId" value="${student.branchId}"/>
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" >
											批次：
										</td>
										<td align="left">
												<div id="globalBatchIdTip" style="width:180px;float: right;padding-right:100px;height: 18px;"></div>
												<select  disabled="disabled" class="txt_box_150" id="globalBatchId" name="student.globalBatch"></select>
												<input type="hidden" name="globalBatchId" id="globalBatchId" value="${student.globalBatch}"/>
											
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											院校：
										</td>
										<td align="left">
										
												<select  disabled="disabled" class="txt_box_150" id="academyId" name="student.academyId" ></select>
											
											
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;" >
											招生批次：
										</td>
										<td align="left">
											<span id="batchName" name="batch" style="color: black"></span>
											<input id="batchId" type="hidden" value="0" name="student.enrollmentBatchId" />
										</td>
									</tr>
									
									<tr>
										<td align="right" width="100px;">
											层次：
										</td>
										<td align="left">
										
											<select  disabled="disabled"  class="txt_box_150" id="levelId" name="student.levelId" ></select>
											
										</td>
									</tr>
									<tr>
										<td align="right" width="100px;">
											专业：
										</td>
										<td align="left">
										
												<select  disabled="disabled" class="txt_box_150" id="majorId" name="student.majorId" ></select>
											
											
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
												<script type="text/javascript">
													document.write(parseInt('${student.status}').getStudentStatus());
												</script>
												<input type="hidden" id="status" name="student.status" value="${student.status}" />
											</div>
										</td>
									</tr>
									
								</table>
								
								<table class="add_table">

									<tr>
										<td align="center" colspan="2">
											<input  class="btn_black_61" type="submit" id="save" onclick="changeHiddenStatus(<%=Constants.STU_CALL_STATUS_YU_BAO_MING %>);"  value="保存" />
											
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