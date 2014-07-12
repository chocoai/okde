<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<jc:plugin name="jquery" />
		<jc:plugin name="default" />
		<jc:plugin name="loading" />
		<jc:plugin name="calendar" />
				<script type="text/javascript">
			function yuanxiaoSuccessCallback(data){
				$("#school").html("");
           		$("<option value='" + -2 + "'>全部</option>").appendTo($("#school"));		
				$(data.academysAcademies).each(function(){
						$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#school"));
				});
			}
			
			//学习中心
			function zhongxingSuccessCallback(data){
				<c:if test="${branch==null}">
					$("#branch").html("");
	               	$("<option value='" + -2 + "'>全部</option>").appendTo($("#branch"));
					$(data.branchlist).each(function(){
						//if(this.level==1){
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
						//}
					});
					$("#city").html("");
	               	$("<option value='" + -2 + "'>全部</option>").appendTo($("#city"));
					$(data.branchlist).each(function(){
						//if(this.level==2){	
							$("<option value='" + this.id + "'>" + this.shortName + "</option>").appendTo($("#city"));
						//}
					});
				</c:if>
				
			}
			function piciSuccessCallback(data){
				$("#batch").html("");
               	$("<option value='" + -2 + "'>全部</option>").appendTo($("#batch"));
				$(data.globalEnrollBatchs).each(function(){
						$("<option value='" + this.id + "'>" + this.batch + "</option>").appendTo($("#batch"));
					
				});
			}
			//招生途径和市场途径回调函数
			function wayAndSourceCallback(data){
				$("#source").html("");
	            $("<option value='" + -2 + "'>全部</option>").appendTo($("#source"));
								
				$(data.enrollmentSources).each(function(){
					$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#source"));
				});
								
				$("#way").html("");
   				var wayStr="<option value='" + -2 + "'>全部</option>";
				$.each(data.enrollmentWaysMap,function(key,value){
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
				
			}
		</script>
		<a:ajax successCallbackFunctions="yuanxiaoSuccessCallback"  urls="/crm/academys_academie_list" pluginCode="yuanxiao" isOnload="all"/>
		<a:ajax successCallbackFunctions="zhongxingSuccessCallback"  urls="/crm/all_branch_list" pluginCode="zhongxing" isOnload="all"/>
		<a:ajax successCallbackFunctions="piciSuccessCallback"  urls="/crm/gl_enroll_batch_list" pluginCode="pici" isOnload="all"/>
		<a:ajax successCallbackFunctions="wayAndSourceCallback"  urls="/crm/student_way_list" pluginCode="laiyuan_tujing" isOnload="all"/>
		<script type="text/javascript">
			function branch_student_status_success_callback(data){
				if(data.reportList!=null){
					var listStr="";
					$(data.reportList).each(function(){
						listStr+="<tr>";
						listStr+="<td align='center'>"+this.cityName+"</td>";
						listStr+="<td align='center'>"+this.branchName+"</td>";
						
						if(parseInt(this.cc_weichuli_count)>0){
							listStr+="<td align='center' style='color:red;'>"+this.cc_weichuli_count+"</td>";
						}else{
							listStr+="<td align='center'>"+this.cc_weichuli_count+"</td>";
						}
						
						if(parseInt(this.lc_weichuli_count)>0){
							listStr+="<td align='center' style='color:red;'>"+this.lc_weichuli_count+"</td>";
						}else{
							listStr+="<td align='center'>"+this.lc_weichuli_count+"</td>";
						}
						listStr+="<td align='center'>"+this.genjingzhong_count+"</td>";
						listStr+="<td align='center'>"+this.wuyiyuan_count+"</td>";
						listStr+="<td align='center'>"+this.yibaoming_count+"</td>";
						listStr+="<td align='center'>"+this.yibaoming_count_p+"</td>";
						listStr+="<td align='center'>"+this.all_count+"</td>";
						listStr+="</tr>";
						//alert(listStr);
					});
					$("#tongjibiao >tbody").html(listStr);
				}
			}
		</script>
		<a:ajax successCallbackFunctions="branch_student_status_success_callback" parameters="$('#search').serializeObject()" urls="/report/report_branch_student_status" pluginCode="branch_student_status"/>
		
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="CC推送查询">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="search" action="<uu:url url="report/excel_export_student_status" />">
			<table style="height: 100%; width: 100%;" cellpadding="0"
				cellspacing="0" border="0">
				<td style="vertical-align: top;" height="100%" width="100%">
					<table width="100%" class="add_table" border="0" cellpadding="2"
						cellspacing="0">
						<tr>
							<td class="lable_100">
								院校：
							</td>
							<td>
								<select class="txt_box_130" id="school" name="mapParams.school">
									
								</select>
							</td>
							<td class="lable_100">
								批次：
							</td>
							<td>
								<select class="txt_box_130" id="batch" name="mapParams.batch">
									
								</select>
							</td>
							
							<td class="lable_100" >
								市场途径：
							</td>
							<td>
								<select class="txt_box_130" id="way" name="mapParams.way">
								</select>
							</td>
							<td class="lable_100">
								招生途径：
							</td>
							<td>
								<select class="txt_box_130" id="source" name="mapParams.source">
									
								</select>
							</td>
						</tr>
						<tr>
							<td class="lable_100">
								城市：
							</td>
							<td>
								<c:if test="${branch==null}">
									<!-- 总部  -->
									<select class="txt_box_130" id="city" name="mapParams.city"></select>
								</c:if>
								<c:if test="${branch!=null}">
									<!-- 学习中心  -->
									${branch.shortName }
									<input type="hidden" name="mapParams.city" value="${branch.id }"/>
								</c:if>
							</td>
							<td class="lable_100">
								学习中心：
							</td>
							<td>
								<c:if test="${branch==null}">
									<!-- 总部  -->
									<select class="txt_box_130" id="branch" name="mapParams.branch"></select>
								</c:if>
								<c:if test="${branch!=null}">
									<!-- 学习中心  -->
									${branch.name }
									<input type="hidden" name="mapParams.branch" value="${branch.id }"/>
								</c:if>
							</td>
							<td class="lable_100">
								数据来源：
							</td>
							<td>
								<select class="txt_box_130" id="studentDataSource" name="mapParams.studentDataSource">
									<option value="-2">全部</option>
									
										<option value="<%=Constants.STU_SOURCE_CC %>">呼叫中心</option>
										<option value="<%=Constants.STU_SOURCE_NP %>">网络报名</option>
								</select>
							</td>
							<td class="lable_100">
								
							</td>
							<td>
								<input type="hidden" value="-2" name="mapParams.status" />
							</td>
							
							
						</tr>
						
						<tr>
							<td class="lable_100">
									开始时间：
								</td>
								<td style="width:150px;">
									<input id="starttime" class="Wdate" type="text"
										name="dateParams.pushStartDate" value=""
										onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
										size="17">
								</td>
								<td class="lable_100">
									结束时间：
								</td>
								<td>
									<input id="endtime" class="Wdate" type="text"
										name="dateParams.pushEndDate" value="" size="17"
										onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})">
								</td>
							<td class="lable_100">
							</td>
							<td>
								
							</td>
							
							<td class="lable_100">
								<input class="btn_black_61" type="button" onclick="ajax_branch_student_status_1();" value="查询" />
							</td>
							<td>
								
								<input class="btn_black_61" type="submit"  value="导出" />
							</td>
							
						</tr>
					</table>
				</form>
			<table id="tongjibiao" class="gv_table" border="0"
				style="width: 100%" cellpadding="0" cellspacing="0">
				<thead>
					<tr align="center" style="background-color: #CCCCCC;">
						<td align="center" style="background-color: #F3F3F3;" colspan="20">
							<h2 id="settitle" style="text-align: center; padding: 10px;">
								 CC推送统计
							</h2>
						</td>
					</tr>

					<tr align="center">
						
						<td align="center" style="background-color: #F3F3F3;">
							城市
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							学习中心
						</td>
						
						<td align="center" style="background-color: #F3F3F3;">
							呼叫中心未处理
						</td>
						 
						<td align="center" style="background-color: #F3F3F3;">
							学习中心未处理
						</td>

						<td align="center" style="background-color: #F3F3F3;">
							跟进
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							学习中心无意愿
						</td>
						<%--
						<td align="center" style="background-color: #F3F3F3;">
							已报名未缴学费
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							已报名已缴学费
						</td>
						 --%>
						 <td align="center" style="background-color: #F3F3F3;">
							已报名
						</td>
						 <td align="center" style="background-color: #F3F3F3;">
							报名转换率
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							全部信息
						</td>

					</tr>


				</thead>
				<tbody>

				</tbody>
			</table>
		</body:body>
	</body>
</html>