<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生跟进</title>
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
		<!-- 省市级联 -->
		<jc:plugin name="provinces" />
		<script type="text/javascript">
			function search_status_counts_callback(data){
					$("#status_"+<%=Constants.STU_CALL_STATUS_YI_FENG_PEI %>).html("("+data.statusCountList.status_<%=Constants.STU_CALL_STATUS_YI_FENG_PEI %>+")");
					$("#status_"+<%=Constants.STU_CALL_STATUS_WU_YI_YUAN %>).html("("+data.statusCountList.status_<%=Constants.STU_CALL_STATUS_WU_YI_YUAN %>+")");
					$("#status_"+<%=Constants.STU_CALL_STATUS_GENG_JIN_ZHONG %>).html("("+data.statusCountList.status_<%=Constants.STU_CALL_STATUS_GENG_JIN_ZHONG %>+")");
					$("#status_"+<%=Constants.STU_CALL_STATUS_YI_DAO_MING %>).html("("+data.statusCountList.status_<%=Constants.STU_CALL_STATUS_YI_DAO_MING %>+")");
			}
			//学生数据来源
			function studentDataSourceCallback(data){
				$("#studentDataSource").html("");
               	$("<option value='" + 0 + "'>请选择学生数据来源</option>").appendTo($("#studentDataSource"));
				$(data.basedictlst).each(function(){
					$("<option value='" + this.code + "'>" + this.name + "</option>").appendTo($("#studentDataSource"));
				});
				//刷新列表
				changeStatus(parseInt(status));
			}
			//招生途径和市场途径回调函数
			function wayAndSourceCallback(doc){
				$("#source").html("");
	            $("<option value='" + 0 + "'>请选择招生途径</option>").appendTo($("#source"));
								
				$(doc.enrollmentSources).each(function(){
					$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#source"));
				});
								
				$("#way").html("");
	            $("<option value='" + 0 + "'>请选择市场途径</option>").appendTo($("#way"));
								
				$(doc.enrollmentWays).each(function(){
					$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#way"));
				});
			}
			//批次
			function pici(doc){
				$("#pici").html("");
               	$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo($("#pici"));
				$(doc.academyEnrollBatchs).each(function(){
					
					if('${enrollmentBatchId}'==this.id){
						sValue=this.id;
						$("<option selected='selected' value='" + this.id + "'>" + this.enrollmentName + "</option>").appendTo($("#pici"));
					}else{
						$("<option value='" + this.id + "'>" + this.enrollmentName + "</option>").appendTo($("#pici"));
					}
				});
				
			}
			//层次学习中心
			function cengci(doc){
				$("#cengci").html("");
               	$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo($("#cengci"));
				$(doc.academyLevels).each(function(){
					if('${student.levelId}'==this.level.id){
						$("<option  selected='selected' value='" + this.level.id + "'>" + this.level.name + "</option>").appendTo($("#cengci"));
					}else{
						$("<option value='" + this.level.id + "'>" + this.level.name + "</option>").appendTo($("#cengci"));
					}
				});
				
			}
			//专业
			function zhuanye(doc){
				$("#zhuanye").html("");
               	$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo($("#zhuanye"));
				$(doc.academyMajors).each(function(){
					if('${student.majorId}'==this.id){
					 	$("<option selected='selected' value='" + this.id + "' title='" + this.majorName + "' >" + this.majorName + "</option>").appendTo($("#zhuanye"));
					 }else{
					 	$("<option value='" + this.id + "' title='" + this.majorName + "' >" + this.majorName + "</option>").appendTo($("#zhuanye"));
					 }
				});
			}
		</script>
		
		<a:ajax 
			pluginCode="0004"
			urls="/crm/academys_academie_list;/crm/academy_enroll_batch_list;/crm/level_list;/crm/base_majors_list;/crm/add_student_cc" 
			parameters="null;{'id':sValue};{'id':sValue,'academyId':parseInt($('#school').val())};{'id':sValue};$('#create_student_form').serializeObject()" 
			successCallbackFunctions="initSelect;pici;cengci;zhuanye;addStudentCallBack" 
			isOnload="1"
		/>
		
		<a:ajax successCallbackFunctions="search_status_counts_callback" pluginCode="0001" urls="/crm/search_status_counts" isOnload="all"/>
		<a:ajax parameters="{'basedicttype':BASEDICT_STYLE_STUDATASOURCE}" successCallbackFunctions="studentDataSourceCallback" pluginCode="0002" urls="/basesetting/basedict/list_base_dict_flag" isOnload="all"/>
		<a:ajax successCallbackFunctions="wayAndSourceCallback" pluginCode="0003" urls="/crm/student_way_list" isOnload="all"/>
		<script type="text/javascript">
			var status=<%=Constants.STU_CALL_STATUS_YI_DAO_MING %>;
			
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
						if('${academyId}'==this.id){
							sValue=this.id;
							$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
						}else{
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
						}
					});
					if('${academyId}'!=""&&'${academyId}'!="0"){
						//招生批次
						ajax_0004_2();
					}
				//注册事件
				school.change(function(){
					sValue=$(this).val();
					if(sValue==0){
							school.html("");
               				$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
               				pici.html("");
               				$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
							return;
					}
					//招生批次
					ajax_0004_2();
					
				});
				
				pici.change(function(){
					sValue=$(this).val();
					if(sValue==0){
               				pici.html("");
               				$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
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
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
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
			function phoneValue(phone,mobile){
			  var str="--";
				/*if(phone!=null){
					str+=phone+"<br/>";
				}*/
				if(mobile!=null){
					str=mobile;
				}
				return str;
			}
			function operating(id){
				return "<a href='#'>报名</a>&nbsp;<a href='#'>申请优惠</a>";
			}
			
			//呼叫等级
			function callGradeValue(callStatusId){
				return callStatusId.getCallGrade();
			}
			//根据状态查询学生跟进列表
			function changeStatus(st){
				
				//跟进中
				if(STU_CALL_STATUS_GENG_JIN_ZHONG==st){
					status=st;
					$("#table001").css({"display":"none"});
					$("#table002").css({"display":""});
					$("#pager001").css({"display":"none"});
					$("#pager002").css({"display":""});
					$("#table003").css({"display":"none"});
					$("#pager003").css({"display":"none"});
					search002();
				}else if(STU_CALL_STATUS_YI_FENG_PEI==st){//已分配
					status=st;
					$("#table001").css({"display":""});
					$("#table002").css({"display":"none"});
					$("#pager001").css({"display":""});
					$("#pager002").css({"display":"none"});
					$("#table003").css({"display":"none"});
					$("#pager003").css({"display":"none"});
					search001();
				}else if(STU_CALL_STATUS_WU_YI_YUAN==st){//无意愿
					status=st;
					$("#table001").css({"display":""});
					$("#table002").css({"display":"none"});
					$("#pager001").css({"display":""});
					$("#pager002").css({"display":"none"});
					$("#table003").css({"display":"none"});
					$("#pager003").css({"display":"none"});
					search001();
				}else if(STU_CALL_STATUS_YI_DAO_MING==st){//已报名
					status=STU_CALL_STATUS_YI_DAO_MING;
					$("#table001").css({"display":"none"});
					$("#table002").css({"display":"none"});
					$("#pager001").css({"display":"none"});
					$("#pager002").css({"display":"none"});
					$("#table003").css({"display":""});
					$("#pager003").css({"display":""});
					// 由于ajax加载，延迟500ms保证数据准确性
					setTimeout(function(){search003();}, 500);
				}
			}
			$(document).ready(function(){
				//选中下拉菜单
				pcas=new $.PCAS({province:'select[name=student.livingPlace]',provinceV:""});
				//学历
				util.select.initOption('select[name=student.degree]', getDegree());
					$("#menu>li>a").click(function(){
						$(this).attr("class","current");
						var selObj=this;
							$("#menu>li>a").each(function(){
									if(this!=selObj){
										$(this).attr("class","");
									}
							});
					});
					//隐藏分页控件
					$("#table001").css({"display":"none"});
					$("#table002").css({"display":"none"});
					//$("#table003").css({"display":"none"});
			});
			
		</script>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="学生跟进">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<div>
				<ul id="menu">
					<li><a href="#" title="" onclick="changeStatus(<%=Constants.STU_CALL_STATUS_YI_FENG_PEI %>);" >已分配<font id="status_<%=Constants.STU_CALL_STATUS_YI_FENG_PEI %>">(0)</font></a></li>
					<li><a href="#" title="" onclick="changeStatus(<%=Constants.STU_CALL_STATUS_WU_YI_YUAN %>);">无意愿<font id="status_<%=Constants.STU_CALL_STATUS_WU_YI_YUAN %>">(0)</font></a></li>
					<li><a href="#" title="" onclick="changeStatus(<%=Constants.STU_CALL_STATUS_GENG_JIN_ZHONG %>);">跟进中<font id="status_<%=Constants.STU_CALL_STATUS_GENG_JIN_ZHONG %>">(0)</font></a></li>
					<li><a href="#" title="" onclick="changeStatus(<%=Constants.STU_CALL_STATUS_YI_DAO_MING %>);" class="current">已报名<font id="status_<%=Constants.STU_CALL_STATUS_YI_DAO_MING %>">(0)</font></a></li>
				</ul>
			</div>
				
				<form id="search_form">
					<table class="add_table" cellpadding="2" cellspacing="2">
							<tr>
								
								<td align="right">姓名：</td><td align="left"><input  name="student.name" class="txt_box_130" type="text" value=""></td>
								<td align="right">性别：</td><td align="left">
											<select id="sex" class="txt_box_130" name="student.gender">
														<option selected="selected" value="-1">--请选择--</option>
														<option value="<%=Constants.SEX_MALE %>">男</option>
														<option value="<%=Constants.SEX_FAMALE %>">女</option>
											</select>	</td>
								<td align="right">学历：</td><td align="left"><select id="xueli" class="txt_box_130" name="student.degree"></td>
								<td align="right">证件号：</td><td align="left"><input  name="student.certNo" class="txt_box_130" type="text"></td>
							</tr>
							<tr>
								<td align="right">MSN/QQ：</td><td align="left"><input name="student.qq" class="txt_box_130" type="text"></td>
								<td align="right">手机/座机：</td><td align="left"><input name="student.phone" class="txt_box_130" type="text"></td>
								<td align="right">邮箱地址：</td><td align="left"><input  name="student.email" class="txt_box_130" type="text"></td>
								<td align="right">所在城市：</td><td align="left"><select class="txt_box_130" style="margin-left:10px;" name="student.livingPlace"></td>
							</tr>
							<tr>
							  <td align="right">意向院校：</td><td align="left"><select class="txt_box_130" name="student.academyId" id="school"></select></td>
							  <td align="right">批次：</td><td align="left"><select class="txt_box_130" name="student.enrollmentBatchId" id="pici"></select></td>
							  <td align="right">意向层次：</td><td align="left"><select class="txt_box_130" name="student.levelId" id="cengci"></select></td>
							  <td align="right">意向专业：</td><td align="left"><select class="txt_box_130" name="student.majorId" id="zhuanye"></select></td>
							</tr>
							<tr>
								<td align="right">数据来源：</td><td align="left"><select id="studentDataSource" name="student.studentDataSource" class="txt_box_130"></td>
								<td align="right">招生途径：</td><td align="left"><select id="source" name="student.enrollmentSource" class="txt_box_130"></select></td>
								<td align="right">市场途径：</td><td align="left"><select id="way" name="student.enrollmentWay" class="txt_box_130"></select></td>
								<td align="right"></td><td align="center"><input  type="button" class="btn_black_61" onclick="changeStatus(parseInt(status));" value="查询"/></td>
							</tr>
					</table>
				</form>
			
									<page:plugin 
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="crm_student_list"
										searchCountActionpath="crm_student_count"
										columnsStr="name;mobile;status;followCount;registrationTime;upFollowUpName;followUpName;#public.operating"
										customColumnValue="2,statusValue(status);7,operating(id)"
										pageSize="10"
										update="http,/crm/call,studentId,id,_self"
										view="http,/crm/view,studentId,id,_self"
										isonLoad="false"
										searchFormId="search_form"
										params="'result.order':'id','student.userId':'${sessionScope.userTicket.userId}','student.status':parseInt(status),'student.callStatus':1"
									/>
									<!-- 招生 -->
									<page:plugin 
										pluginCode="003"
										il8nName="crm"
										searchListActionpath="crm_student_list"
										searchCountActionpath="crm_student_count"
										columnsStr="name;mobile;status;followCount;registrationTime;upFollowUpName;followUpName;#public.operating"
										customColumnValue="2,statusValue(status);7,operating(id)"
										pageSize="10"
										update="http,/crm/call,studentId,id,_self"
										view="http,/crm/view,studentId,id,_self"
										isonLoad="false"
										searchFormId="search_form"
										params="'result.order':'id','student.userId':'${sessionScope.userTicket.userId}','student.startStatusId':parseInt(STU_CALL_STATUS_GENG_JIN_ZHONG),'student.callStatus':1"
									/>
									<!--  -->
									<page:plugin 
										pluginCode="002"
										il8nName="crm"
										searchListActionpath="crm_student_list"
										searchCountActionpath="crm_student_count"
										columnsStr="name;#phone;status;callStatusId;followCount;registrationTime;upFollowUpName;followUpName;#public.operating"
										customColumnValue="1,phoneValue(phone,mobile);3,callGradeValue(callStatusId);2,statusValue(status);8,operating(id)"
										pageSize="10"
										update="http,/crm/call,studentId,id,_self"
										view="http,/crm/view,studentId,id,_self"
										isonLoad="false"
										searchFormId="search_form"
										params="'student.userId':'${sessionScope.userTicket.userId}','student.status':parseInt(status),'student.callStatus':1"
									/>
		 </body:body>
	</body>

</html>