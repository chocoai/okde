<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>报名资料复核</title>
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
			//批次
			function pici(doc){
				$("#pici").html("");
               	$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo($("#pici"));
				$(doc.academyEnrollBatchs).each(function(){
					//sValue=this.id;
					$("<option value='" + this.id + "'>" + this.enrollmentName + "</option>").appendTo($("#pici"));
				});
			}
			//层
			function cengci(doc){
				$("#cengci").html("");
               	$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo($("#cengci"));
				$(doc.academyLevels).each(function(){
					$("<option value='" + this.id + "'>" + this.level.name + "</option>").appendTo($("#cengci"));
				});
			}
			//专业
			function zhuanye(doc){
				$("#zhuanye").html("");
               	$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo($("#zhuanye"));
				$(doc.academyMajors).each(function(){
					 $("<option value='" + this.majorId + "'>" + this.majorName + "</option>").appendTo($("#zhuanye"));	 
				});
			}
			//ajax回调函数   学习中心
			function ajax_stubranchajax(data)
			{
				// 学习中心
				jQuery("#branchId").empty();
			    jQuery("#branchId").append('<option value="0">请选择学习中心</option>');
			    if(data.branchList!=null && data.branchList.length>0)
			    {
			    	$.each(data.branchList,function(){	
			    		jQuery("#branchId").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			}
		</script>
		
		<a:ajax 
			pluginCode="0004"
			urls="/crm/academys_academie_list;/crm/academy_enroll_batch_list;/crm/level_list;/crm/base_majors_list;/crm/add_student_cc" 
			parameters="null;{'id':sValue};{'id':sValue,'academyId':parseInt($('#school').val())};{'id':sValue};$('#create_student_form').serializeObject()" 
			successCallbackFunctions="initSelect;pici;cengci;zhuanye;addStudentCallBack" 
			isOnload="1"
		/>
	    <!-- 学习中心-->
		<a:ajax 
			pluginCode="140"
			successCallbackFunctions="ajax_stubranchajax" 
			urls="/crm/branch_all_list_for_stu_search_ajax"
			isOnload="all" 			
		/>
		<script type="text/javascript">
			var status='<%=Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI %>,<%=Constants.STU_CALL_STATUS_YI_CE_SHI %>,<%=Constants.STU_CALL_STATUS_YI_FU_HE %>';
			
			function initSelect(doc){
				var school=$("#school");
				var pici=$("#pici");
				var cengci=$("#cengci");
				var zhuanye=$("#zhuanye");
			
				school.html("");
              		$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
              		pici.html("");
              		$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
              		cengci.html("");
              		$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
              		zhuanye.html("");
              		$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
              				
				$(doc.academysAcademies).each(function(){
					//sValue=this.id;else{
					$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
				});	
				//注册事件
				school.change(function(){
					sValue=$(this).val();
					if(sValue==0){
               				pici.html("");
               				$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
               				ajax_0004_1();
							return;
					}
					//招生批次
					ajax_0004_2();
					
				});
				
				pici.change(function(){
					sValue=$(this).val();
					if(sValue==0){
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
            
							return;
					}
					//层次
					ajax_0004_3();
				});
				
				
				cengci.change(function(){
					sValue=$(this).val();
					if(sValue==0){
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
							return;
					}
					//查询专业
					ajax_0004_4();
				});
			}
			function sexValue(sex){
				return sex.getSex();
			}
			function statusValue(status){
				return status.getStudentStatus();
			}
			function operating(id,stustatus){
				//var result='';
				if(stustatus!='<%=Constants.STU_CALL_STATUS_YI_FU_HE %>'){
					return '<a href="'+WEB_PATH+'/enrollment/enrollaudit/view_enroll_audit?stuid='+id+'">复核</a>';
				}
				return "";
				//return result;
			}
			
			
		</script>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="报名资料复核">
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="search_form">
					<table class="add_table" cellpadding="0" cellspacing="0" width="100%">
							<tr>
							  <td class="lable_100">学习中心：</td>
							  <td>
							  		<select class="txt_box_130" name="student.branchId" id="branchId">
								  		<option value="0">请选择学习中心</option>
								  	</select>
							  </td>
							  <td class="lable_100">院校：</td><td><select class="txt_box_130" name="student.academyId" id="school"></select></td>
							  <td class="lable_100">批次：</td><td><select class="txt_box_130" name="student.enrollmentBatchId" id="pici"></select></td>
							  
							</tr>
							<tr>
							  <td class="lable_100">层次：</td><td><select class="txt_box_130" name="student.levelId" id="cengci"></select></td>
							  <td class="lable_100">专业：</td><td><select class="txt_box_130" name="student.majorId" id="zhuanye"></select></td>
							
							  <td class="lable_100">姓名：</td><td align="left"><input  name="student.name" class="txt_box_130" type="text" value=""/></td>
							</tr>
							<tr>
								<td class="lable_100">证件号：</td>
								<td>
									<input  name="student.certNo" class="txt_box_130" type="text" />
								</td>
								<td class="lable_100">手机/座机：</td>
								<td>
									<input name="student.phone" class="txt_box_130" type="text" />
								</td>
								 <td class="lable_100"></td><td><input type="button" value="查询" class="btn_black_61" onclick="search001()"/></td>
							</tr>
					</table>
				</form>
			
									<page:plugin 
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="enrollment/enrollmonitor/list_monitor_student"
										searchCountActionpath="enrollment/enrollmonitor/count_monitor_student"
										columnsStr="name;gender;schoolName;majorName;levelName;academyenrollbatchName;status;#public.operating"
										customColumnValue="1,sexValue(gender);6,statusValue(status);7,operating(id,status)"
										
										searchFormId="search_form"
										params="'student.statusIds':status,'student.gender':-1,'student.callStatus':1"
										isPackage="false"
									/>
									
				</body:body>
				
	</body>

</html>