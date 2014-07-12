<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<%@ include file="../template/common/download_excel.jsp" %>
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
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		
		<jc:plugin name="page" />
		<jc:plugin name="tab" />
		<jc:plugin name="loading" />
		<jc:plugin name="base_js" />
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
					$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#studentDataSource"));
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
				
			}
			//专业
			function zhuanye(doc){
				$("#zhuanye").html("");
               	$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo($("#zhuanye"));
				$(doc.academyMajors).each(function(){
					if('${student.majorId}'==this.majorId){
					 	$("<option selected='selected' value='" + this.majorId + "'>" + this.majorName + "</option>").appendTo($("#zhuanye"));
					 }else{
					 	$("<option value='" + this.majorId + "' title=" + this.majorName + " >" + this.majorName + "</option>").appendTo($("#zhuanye"));
					 }
				});
			}
			//导出
			function download(){
				if(confirm("您确定要导出数据吗？")){
					ajax_download_zip_1();
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
		
		<a:ajax successCallbackFunctions="search_status_counts_callback" pluginCode="0001" urls="/crm/search_status_counts" isOnload="all"/>
		<a:ajax parameters="{'basedicttype':BASEDICT_STYLE_STUDATASOURCE}" successCallbackFunctions="studentDataSourceCallback" pluginCode="0002" urls="/basesetting/basedict/list_base_dict_flag" isOnload="all"/>
		<a:ajax successCallbackFunctions="wayAndSourceCallback" pluginCode="0003" urls="/crm/student_way_list" isOnload="all"/>
		
		<!-- 下载地址 -->
		<a:ajax 
			parameters="jQuery('#search_form').serializeObject()" 
			successCallbackFunctions="excel_export_callback" 
			pluginCode="download_zip" 
			urls="/crm/export/excel_export_list_student_gen_jin_zhong"
		/>
		<script type="text/javascript">
			var status=<%=Constants.STU_CALL_STATUS_YI_FENG_PEI %>;
			var student_registration_id = 0;
			
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
						if('${student.academyId}'==this.id){
							sValue=this.id;
							$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
						}else{
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
						}
					});
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
				return "<a href='#' onclick='studentRegistration("+id+")'>报名</a>&nbsp;<a href='javascript:addapplicationdiscount("+id+");'>申请优惠</a>";
			}
			function showoperating(id,status)
			{
				
				if(status==STU_CALL_STATUS_YI_DAO_MING)
				{
					return "<a href='"+WEB_PATH+"/crm/student_registration?id="+id+"'>报名缴费</a>"
				}
				return ""
			}
			// 学生报名确认
			function studentRegistration(id){
				student_registration_id=id;
				$('#message_confirm').dialog("open");
			}
			//呼叫等级
			function callGradeValue(callStatusId){
				return callStatusId.getCallGrade();
			}
			//根据状态查询学生跟进列表
			function changeStatus(st){
				if(st==-1)
				{
					st=status;
				}
				else
				{
					$('#callStatusId').val(0);
					$('#followUpTimeBegin').val("");
					$('#followUpTimeEnd').val("");
				}
				//跟进中
				if(STU_CALL_STATUS_GENG_JIN_ZHONG==st){//跟进中
					status=st;
					$("#table001").css({"display":"none"});
					$("#table002").css({"display":""});
					$("#pager001").css({"display":"none"});
					$("#pager002").css({"display":""});
					$("#table003").css({"display":"none"});
					$("#pager003").css({"display":"none"});
					$("#table004").css({"display":"none"});
					$("#pager004").css({"display":"none"});
					$(".hujiaodengji").css({"display":""});
					$(".genjinshijian").css({"display":""});
					$("#genjinzhongdaochu").css({"display":""});
					search002();
				}else if(STU_CALL_STATUS_YI_FENG_PEI==st){//已分配
					status=st;
					$("#table001").css({"display":""});
					$("#table002").css({"display":"none"});
					$("#pager001").css({"display":""});
					$("#pager002").css({"display":"none"});
					$("#table003").css({"display":"none"});
					$("#pager003").css({"display":"none"});					
					$("#table004").css({"display":"none"});
					$("#pager004").css({"display":"none"});
					$(".hujiaodengji").css({"display":"none"});
					$(".genjinshijian").css({"display":"none"});
					$("#genjinzhongdaochu").css({"display":"none"});
					search001();
				}else if(STU_CALL_STATUS_WU_YI_YUAN==st){//无意愿
					status=st;
					$("#table001").css({"display":"none"});
					$("#table002").css({"display":"none"});
					$("#pager001").css({"display":"none"});
					$("#pager002").css({"display":"none"});
					$("#table003").css({"display":"none"});
					$("#pager003").css({"display":"none"});
					$("#table004").css({"display":""});
					$("#pager004").css({"display":""});
					$(".hujiaodengji").css({"display":"none"});
					$(".genjinshijian").css({"display":""});
					$("#genjinzhongdaochu").css({"display":"none"});
					search004();
				}else if(STU_CALL_STATUS_YI_DAO_MING==st){//已报名
					status=STU_CALL_STATUS_YI_DAO_MING;
					$("#table001").css({"display":"none"});
					$("#table002").css({"display":"none"});
					$("#pager001").css({"display":"none"});
					$("#pager002").css({"display":"none"});
					$("#table003").css({"display":""});
					$("#pager003").css({"display":""});
					$("#table004").css({"display":"none"});
					$("#pager004").css({"display":"none"});
					$(".hujiaodengji").css({"display":"none"});
					$(".genjinshijian").css({"display":""});
					$("#genjinzhongdaochu").css({"display":"none"});
					search003();
				}
			}
			$(document).ready(function(){
				//选中下拉菜单
				//pcas=new $.PCAS({province:'select[name=student.livingPlace]',provinceV:""});
				//学历
					//util.select.initOption('select[name=student.degree]', getDegree());
				
					initSelectOptions(getDegree(),"xueli","0");
					
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
					$("#table002").css({"display":"none"});
					$("#table003").css({"display":"none"});
					$("#table004").css({"display":"none"});
			});
			
		</script>
		<script type="text/javascript">
			//加载事件
			jQuery(function(){
				//初始化弹出框
				
				//添加优惠卷
				jQuery('#add_for_discount').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'申请优惠卷',
					width: 600,
					buttons: {
						'添加': function() { 
							addapplicationed();
						}, 
						'取消': function() { 
							jQuery(this).dialog("close"); 
						} 
					}
				});	
				//信息提示
				jQuery('#message_returns_tips').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示',
					buttons: {
						'关闭': function() { 
							jQuery(this).dialog("close"); 
						} 
					}
				});	
				//确认操作
				$('#message_confirm').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'提示',
					width: 260,
					height: 150,
					buttons: {
							'确认': function() { 
									document.location.href=WEB_PATH+"/crm/student_registration?id="+student_registration_id;
							}, 
							'取消': function() { 
									$(this).dialog("close"); 
							} 
					}
				});		
				
				//提示回访跟进类型
				jQuery('#returnVisitTimeType').change(function(){
					if(this.value==0)
					{
						jQuery("#returnVisitTimeBegin").attr("disabled",false);
						jQuery("#returnVisitTimeEnd").attr("disabled",false);
					}	
					else
					{
						jQuery("#returnVisitTimeBegin").val("");
						jQuery("#returnVisitTimeEnd").val("");
						jQuery("#returnVisitTimeBegin").attr("disabled",true);
						jQuery("#returnVisitTimeEnd").attr("disabled",true);
					}					
				});		
				
			});
			//显示可以申请的优惠ajax
			function addapplicationdiscount(id)
			{
				studentId=id;
				
				ajax_150_1();//显示可以添加的优惠申请	
			}
			//添加优惠申请
			function addapplicationed()
			{
				if(jQuery("input[name='policyradio']").length==0 || jQuery("input[name='policyradio']:checked").length==0)
				{
					jQuery("#showDialog").html('<b>请选择要申请的优惠！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else
				{
					 ajax_160_1();//添加优惠政策
				}
			}
			
			//ajax回调函数  学生优惠申请
			var studentId=0;
			function ajax_adddiscount(data)
			{				
				$('#adddiscount > tbody').empty();
			   	var list='';
			    if(data.discountPolicyList!=null && data.discountPolicyList.length>0)
			    {
			    	$.each(data.discountPolicyList,function(){	
			    		list+='<tr>';
			    		list+='<td align="center"><input type="radio" name="policyradio" value="'+this.id+'" /></td>'
			    		list+='<td align="center">'+this.title+'</td>';
			    		list+='<td align="center">'+this.feesubjectname+'</td>';
			    		//list+='<td align="center">第'+this.feePaymentId+'次缴费</td>';
			    		if(this.isApplicationNeeded==STUDENT_DISCOUNT_AUDIT_CEDU)
			    		{
			    			list+='<td align="center">总部审批</td>';
			    		}
			    		else if(this.isApplicationNeeded==STUDENT_DISCOUNT_AUDIT_BRANCH)
			    		{
			    			list+='<td align="center">中心审批</td>';
			    		}
			    		else
			    		{
			    			list+='<td align="center">无需审批</td>';
			    		}
			    		list+='<td align="center">'+this.useBeginDate+"~"+this.useEndDate+'</td>';
			    		list+='<td align="center">';
			    		if(this.discountWayId==MONEY_FORM_AMOUNT)
			    		{
			    			list+="优惠金额："+this.money+"元";
			    		}
			    		else
			    		{
			    			list+="优惠比例："+this.money+"%";
			    		}
			    		if(this.mutable==MONEY_FORM_GRADIENT)
			    		{
			    			if(this.discountWayId==MONEY_FORM_AMOUNT)
			    			{
			    				list+='<br/>渐变金额：'+this.delta+'元';
			    			}
			    			else
			    			{
			    				list+='<br/>渐变比例：'+this.delta+'%';
			    			}
			    		}
			    		list+='</td>';
			    		list+='</tr>';
			    	});
			    }
			    else
			    {
			    	list+='<tr><td colspan="6" align="center">没有可以申请的优惠政策！</td></tr>';
			    }
			    $('#adddiscount > tbody').html(list);
			    $("#remark").val("");	
			    $('#add_for_discount').dialog('open');
			}
			
			//ajax回调函数  添加学生优惠申请
			function ajax_addstudentapp(data)
			{				
			    if(data.isfailcount)
			    {
			    	jQuery("#showDialog").html('<b>已申请过该优惠！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }	
			    else if(data.isfail)
			    {
			    	jQuery("#showDialog").html('<b>添加成功！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }
			    else
			    {
			    	jQuery("#showDialog").html('<b>添加失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }			  
			}
		</script>
		<!--学生优惠申请-->
		<a:ajax successCallbackFunctions="ajax_adddiscount" parameters="{studentId:studentId}" urls="/enrollment/list_all_student_apply_unadademy_ajax" pluginCode="150"/>
		
		<!--添加学生优惠申请-->
		<a:ajax successCallbackFunctions="ajax_addstudentapp" parameters="{studentId:studentId,remark:jQuery('#remark').val(),discountPolicyId:jQuery(\"input[name='policyradio']:checked\").val()}" urls="/enrollment/add_student_application_ajax" pluginCode="160"/>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="学生跟进">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<div>
				<ul id="menu">
					<li><a href="#" title="" onclick="changeStatus(<%=Constants.STU_CALL_STATUS_YI_FENG_PEI %>);" class="current">已分配<font id="status_<%=Constants.STU_CALL_STATUS_YI_FENG_PEI %>">(0)</font></a></li>
					<li><a href="#" title="" onclick="changeStatus(<%=Constants.STU_CALL_STATUS_WU_YI_YUAN %>);">无意愿<font id="status_<%=Constants.STU_CALL_STATUS_WU_YI_YUAN %>">(0)</font></a></li>
					<li><a href="#" title="" onclick="changeStatus(<%=Constants.STU_CALL_STATUS_GENG_JIN_ZHONG %>);">跟进中<font id="status_<%=Constants.STU_CALL_STATUS_GENG_JIN_ZHONG %>">(0)</font></a></li>
					<li><a href="#" title="" onclick="changeStatus(<%=Constants.STU_CALL_STATUS_YI_DAO_MING %>);">已报名<font id="status_<%=Constants.STU_CALL_STATUS_YI_DAO_MING %>">(0)</font></a></li>
				</ul>
			</div>
				
				<form id="search_form">
					<table class="add_table" cellpadding="2" cellspacing="2">
							<tr>
								
								<td align="right">姓名：</td><td align="left"><input name="student.name" class="txt_box_130" type="text" value=""/></td>
								<td align="right">性别：</td><td align="left">
											<select id="sex" class="txt_box_130" name="student.gender">
														<option selected="selected" value="-1">--请选择--</option>
														<option value="<%=Constants.SEX_MALE %>">男</option>
														<option value="<%=Constants.SEX_FAMALE %>">女</option>
											</select>	</td>
								<td align="right">学历：</td><td align="left"><select id="xueli" class="txt_box_130" name="student.degree" /></td>
								<td align="right">证件号：</td><td align="left"><input  name="student.certNo" class="txt_box_130" type="text" /></td>
							</tr>
							<tr>
								<td align="right">MSN/QQ：</td><td align="left"><input name="student.qq" class="txt_box_130" type="text" /></td>
								<td align="right">手机/座机：</td><td align="left"><input name="student.phone" class="txt_box_130" type="text" /></td>
								<td align="right">邮箱地址：</td><td align="left"><input  name="student.email" class="txt_box_130" type="text" /></td>
								<td align="right">所在城市：</td><td align="left"><select class="txt_box_130" style="margin-left:10px;" name="student.livingPlace"><%@ include file="city.jsp" %></select></td>
							</tr>
							<tr>
							  <td align="right">意向院校：</td><td align="left"><select class="txt_box_130" name="student.academyId" id="school"></select></td>
							  <td align="right">批次：</td><td align="left"><select class="txt_box_130" name="student.enrollmentBatchId" id="pici"></select></td>
							  <td align="right">意向层次：</td><td align="left"><select class="txt_box_130" name="student.levelId" id="cengci"></select></td>
							  <td align="right">意向专业：</td><td align="left"><select class="txt_box_130" name="student.majorId" id="zhuanye"></select></td>
							</tr>
							<tr>
								<td align="right">数据来源：</td><td align="left"><select id="studentDataSource" name="student.studentDataSource" class="txt_box_130"/></td>
								<td align="right">招生途径：</td><td align="left"><select id="source" name="student.enrollmentSource" class="txt_box_130"></select></td>
								<td align="right">市场途径：</td><td align="left"><select id="way" name="student.enrollmentWay" class="txt_box_130"></select></td>
								<td align="right"><div id="stucalldiv1" class="hujiaodengji">呼叫等级：</div></td>
								<td align="left">
									<div id="stucalldiv" class="hujiaodengji">
										<select id="callStatusId" class="txt_box_130" name="callStatusId">
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
									</div>
								</td>
							</tr>
							<tr>
								<td align="right">
									提示回访跟进：
								</td>
								<td align="left">
									<select id="returnVisitTimeType" class="txt_box_130" name="student.returnVisitTimeType">
											<option value="0">
												请选择
											</option>
											<option value="<%=Constants.STUDENT_FOLLOW_GEN_JIN_LEI_XING_WEEK %>">
												本周
											</option>
											<option value="<%=Constants.STUDENT_FOLLOW_GEN_JIN_LEI_XING_MONTH %>">
												本月
											</option>
										</select>
								</td>
								<td align="right">
									提示回访跟进起：
								</td>
								<td align="left">
									<input type="text" name="student.returnVisitTimeBegin" id="returnVisitTimeBegin" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'returnVisitTimeEnd\',{d:-0});}',dateFmt:'yyyy-MM-dd'})" readonly="readonly" />
								</td>
								<td align="right">
									提示回访跟进止
								</td>
								<td align="left">
									<input type="text" name="student.returnVisitTimeEnd" id="returnVisitTimeEnd" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'returnVisitTimeBegin\',{d:0});}',dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								</td>
								<td align="right"></td>
								<td align="left"></td>
							</tr>
							<tr>
								<td align="right">
									<div class="genjinshijian">
										跟进时间起：
									</div>
								</td>
								<td align="left">
									<div class="genjinshijian">
										<input type="text" name="student.followUpTimeBegin" id="followUpTimeBegin" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'followUpTimeEnd\',{d:-0});}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
									</div>
								</td>
								<td align="right">
									<div class="genjinshijian">
										跟进时间止：
									</div>
								</td>
								<td align="left">
									<div class="genjinshijian">
										<input type="text" name="student.followUpTimeEnd" id="followUpTimeEnd" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'followUpTimeBegin\',{d:0});}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly"/>
									</div>
								</td>
								<td align="right" colspan="2"></td>
								<td align="center">
									<input type="button" class="btn_black_61" onclick="changeStatus(-1);" value="查询"/>
								</td>
								<td align="center">
									<div id="genjinzhongdaochu">
										<input type="button" class="btn_black_61" onclick="download()" value="导出"/>
									</div>
								</td>
							</tr>
					</table>
				</form>
			
									<page:plugin 
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="crm_student_list"
										searchCountActionpath="crm_student_count"
										columnsStr="name;certNo;mobile;status;upFollowUpName;followUpName;#public.operating"
										customColumnValue="3,statusValue(status);6,operating(id)"
										pageSize="10"
										update="http,/crm/call,studentId,id,_blank"
										view="http,/crm/view_home,studentId,id,_blank"
										isonLoad="false"
										searchFormId="search_form"
										params="'result.order':'id','student.userId':'${sessionScope.userTicket.userId}','student.status':parseInt(status),'student.callStatus':1"
									/>
									<!-- 已报名 -->
									<page:plugin 
										pluginCode="003"
										il8nName="crm"
										searchListActionpath="crm_student_list"
										searchCountActionpath="crm_student_count"
										columnsStr="name;certNo;mobile;status;followCount;registrationTime;upFollowUpName;followUpName;#public.operating"
										customColumnValue="3,statusValue(status);8,showoperating(id,status)"
										pageSize="10"
										update="http,/crm/call,studentId,id,_blank"
										view="http,/crm/view_home,studentId,id,_blank"
										isonLoad="false"
										searchFormId="search_form"
										params="'result.order':'id','student.userId':'${sessionScope.userTicket.userId}','student.startStatusId':parseInt(STU_CALL_STATUS_GENG_JIN_ZHONG),'student.callStatus':-1"
									/>
									<!-- 跟进中 -->
									<page:plugin 
										pluginCode="002"
										il8nName="crm"
										searchListActionpath="crm_student_list"
										searchCountActionpath="crm_student_count"
										columnsStr="name;certNo;#phone;status;callStatusId;followCount;latestFollowUpDate;upFollowUpName;followUpName;#public.operating"
										customColumnValue="2,phoneValue(phone,mobile);4,callGradeValue(callStatusId);3,statusValue(status);9,operating(id)"
										pageSize="10"
										update="http,/crm/call,studentId,id,_blank"
										view="http,/crm/view_home,studentId,id,_blank"
										isonLoad="false"
										searchFormId="search_form"
										params="'student.userId':'${sessionScope.userTicket.userId}','student.status':parseInt(status),'student.callStatus':-1,'student.callStatusId':jQuery('#callStatusId').val()"
									/>
									<!-- 无意愿 -->
									<page:plugin 
										pluginCode="004"
										il8nName="crm"
										searchListActionpath="crm_student_list"
										searchCountActionpath="crm_student_count"
										columnsStr="name;certNo;mobile;status;followCount;latestFollowUpDate;upFollowUpName;followUpName;#public.operating"
										customColumnValue="3,statusValue(status);8,operating(id)"
										pageSize="10"
										update="http,/crm/call,studentId,id,_blank"
										view="http,/crm/view_home,studentId,id,_blank"
										isonLoad="false"
										searchFormId="search_form"
										params="'result.order':'id','student.userId':'${sessionScope.userTicket.userId}','student.status':parseInt(status),'student.callStatus':-1"
									/>
		 </body:body>
		 
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
	<div id="message_confirm" style="display:none">
		<div id="messageDiv">确认报名吗？</div>
	</div>

	<div id="add_for_discount" style="display:none">
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
				<th style="text-align:left; font-weight:bold;" class="feehtml">申请的优惠标准</th>					
			</tr>
		</table>
		<table class="gv_table_2" id="adddiscount">
			<thead>
				<tr>
					<th></th>
					<th>优惠标题</th>
					<th>费用科目</th>
					<th>审批方</th>
					<th>有效期</th>
					<th>优惠标准</th>
				</tr>
			</thead>
			<tbody>
				
			</tbody>
		</table>
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
				<th style="text-align:left; font-weight:bold;" class="feehtml">申请备注</th>					
			</tr>
		</table>
		<table class="add_table" id="">					
			<tfoot>
	            <tr>
	            	<th class="lable_100">优惠备注：</th>
	                <th colspan="4"><textarea  id="remark" name="remark"  cols="35" rows="8" class="txt_box_350"></textarea></th>
	            </tr>
	        </tfoot>    
		</table>
	</div>	
	</body>

</html>