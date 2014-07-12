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
			
			//学习中心和服务站
			function zhongxingSuccessCallback(data){
					$("#branch").html("");
	               	$("<option value='" + -2 + "'>全部</option>").appendTo($("#branch"));
					$(data.branchlist).each(function(){
						$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
					});
				
			}
			function piciSuccessCallback(data){
				$("#batch").html("");
               	$("<option value='" + -2 + "'>全部</option>").appendTo($("#batch"));
				$(data.globalEnrollBatchs).each(function(){
						$("<option value='" + this.id + "'>" + this.batch + "</option>").appendTo($("#batch"));
					
				});
			}
			function dataSourceSuccessCallback(data){
				$("#studentDataSource").html("");
               	$("<option value='" + -2 + "'>全部</option>").appendTo($("#studentDataSource"));
				$(data.basedictlst).each(function(){
					$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#studentDataSource"));
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
		<a:ajax parameters="{'basedicttype':BASEDICT_STYLE_STUDATASOURCE}" successCallbackFunctions="dataSourceSuccessCallback" pluginCode="shujulaiyuan" urls="/basesetting/basedict/list_base_dict_flag" isOnload="all"/>
		<a:ajax successCallbackFunctions="wayAndSourceCallback"  urls="/crm/student_way_list" pluginCode="laiyuan_tujing" isOnload="all"/>
		
		
		<script type="text/javascript">
				function reportSuccessCallback(data){
					var resultMap=data.resultMap;
					if(data.resultMap.academyList!=null&&data.resultMap.academyList.length!=0){
						var theadStr="";
						for ( var int = 0; int < data.resultMap.academyList.length; int++) {
							var academy=data.resultMap.academyList[int];
							
							if(academy.academy_branch_list!=null&&academy.academy_branch_list.length!=0){
								for ( var int2 = 0; int2 < academy.academy_branch_list.length; int2++) {
									var branch = academy.academy_branch_list[int2];
									theadStr+="<tr>";
										theadStr+="<td align='center'>"+academy.academy_name+"</td>";
										theadStr+="<td align='center'>"+branch.branch_name+"</td>";
										
										theadStr+="<td align='center'>"+branch.branch_zhi_biao+"</td>";
										theadStr+="<td align='center'>"+branch.branch_bao_ming_count+"</td>";
										theadStr+="<td align='center'>"+branch.branch_bao_ming_count_p+"</td>";
										
										theadStr+="<td align='center'>"+branch.branch_lu_qu_count+"</td>";
										theadStr+="<td align='center'>"+branch.branch_no_lu_qu_count+"</td>";
										theadStr+="<td align='center'>"+branch.branch_lu_qu_count_p+"</td>";
										
										theadStr+="<td align='center'>"+branch.branch_jiao_fei_count+"</td>";
										theadStr+="<td align='right'>"+branch.branch_jiao_fei_jin_e+"&nbsp;</td>";
										theadStr+="<td align='center'>"+branch.branch_jiao_fei_count_p+"</td>";
										
									theadStr+="</tr>";
								}
								
								theadStr+="<tr style='background:#CCFFFF;'>";
										theadStr+="<td align='center' colspan='2'>合计</td>";
										
										theadStr+="<td align='center'>"+academy.academy_branch_zhi_biao+"</td>";
										theadStr+="<td align='center'>"+academy.academy_branch_bao_ming_count+"</td>";
										theadStr+="<td align='center'>"+academy.academy_branch_bao_ming_count_p+"</td>";
										
										theadStr+="<td align='center'>"+academy.academy_branch_lu_qu_count+"</td>";
										theadStr+="<td align='center'>"+academy.academy_branch_no_lu_qu_count+"</td>";
										theadStr+="<td align='center'>"+academy.academy_branch_lu_qu_count_p+"</td>";
										
										theadStr+="<td align='center'>"+academy.academy_branch_jiao_fei_count+"</td>";
										theadStr+="<td align='right'>"+academy.academy_branch_jiao_fei_jin_e+"&nbsp;</td>";
										theadStr+="<td align='center'>"+academy.academy_branch_jiao_fei_count_p+"</td>";
										
									theadStr+="</tr>";
								
							}else{
								theadStr+="<tr style='background-color: red;'>";
										theadStr+="<td align='center'>"+academy.academy_name+"</td>";
										theadStr+="<td colspan='10' align='center' >该院校没有授权的学习中心</td>";
								theadStr+="</tr>";
							}
							
						}
						
							theadStr+="<tr style='background:yellow;'>";
									theadStr+="<td align='center' colspan='2'>总合计</td>";
										
									theadStr+="<td align='center'>"+resultMap.sum_academy_branch_zhi_biao+"</td>";
									theadStr+="<td align='center'>"+resultMap.sum_academy_branch_bao_ming_count+"</td>";
									theadStr+="<td align='center'>"+resultMap.sum_academy_branch_bao_ming_count_p+"</td>";
										
									theadStr+="<td align='center'>"+resultMap.sum_academy_branch_lu_qu_count+"</td>";
									theadStr+="<td align='center'>"+resultMap.sum_academy_branch_no_lu_qu_count+"</td>";
									theadStr+="<td align='center'>"+resultMap.sum_academy_branch_lu_qu_count_p+"</td>";
										
									theadStr+="<td align='center'>"+resultMap.sum_academy_branch_jiao_fei_count+"</td>";
									theadStr+="<td align='right'>"+resultMap.sum_academy_branch_jiao_fei_jin_e+"&nbsp;</td>";
									theadStr+="<td align='center'>"+resultMap.sum_academy_branch_jiao_fei_count_p+"</td>";
										
							theadStr+="</tr>";
						$("#tongjibiao >tbody").html(theadStr);
						_w_table_rowspan("#tongjibiao",1);
					}
				}
				
		</script>
		<a:ajax successCallbackFunctions="reportSuccessCallback" pluginCode="student_source" urls="/report/report_academy_enrollment" parameters="$('#search').serializeObject()" />
		
	</head>
	<body>
	
		<!--头部层开始 -->
		<head:head title="院校招生统计表">
		</head:head>
		<!--主体层开始 -->
		<body:body >
			<form id="search" action="<uu:url url="report/excel_export_academy_enrollment" />">

					<table width="100%" class="add_table" border="0" cellpadding="2"
						cellspacing="0">
						<tr>
							<td class="lable_100">
								院校：
							</td>
							<td >
								<select class="txt_box_130" id="school" name="mapParams.school">
									
								</select>
							</td>
							
							<td class="lable_100">
								数据来源：
							</td>
							<td>
								<select class="txt_box_130" id="studentDataSource" name="mapParams.studentDataSource">
									
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
								批次：
							</td>
							<td>
								<select class="txt_box_130" id="batch" name="mapParams.batch">
									
								</select>
							</td>
							<td class="lable_100">
								学习中心：
							</td>
							<td>
									<select class="txt_box_130" id="branch" name="mapParams.branch"></select>
							</td>
							<td class="lable_100">
								开始时间：
							</td>
							<td>
								<input id="starttime" class="Wdate" type="text" name="dateParams.startDate" value="" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" size="17">
							</td>
							<td class="lable_100">
								结束时间：
							</td>
							<td>
								<input id="endtime" class="Wdate" type="text" name="dateParams.endDate" value="" size="17" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})">
							</td>
							
							<td>
								
							</td>
							
						</tr>
						<tr>
							
							<td >
								
							</td>
							<td>
								
							</td>
							<td >
								
							</td>
							<td>
								
							</td>
							<td class="lable_100">
								
							</td>
							<td>
								
							</td>
							<td class="lable_100">
								<input class="btn_black_61" type="button" onclick="ajax_student_source_1();" value="查询" />
							</td>
							<td>
								
								<input class="btn_black_61" type="submit"  value="导出" />
							</td>
							
						</tr>
					</table>
				</form>

					<table id="tongjibiao" class="gv_table" border="0" style="width:100%"
						cellpadding="0" cellspacing="0">
						<thead>
							<tr align="center" style="background-color: #F3F3F3;">
								<td colspan="23" style="background-color: #F3F3F3;">
										<h2 id="settitle" style="text-align: center;padding:10px;">
											院校招生统计表
										</h2>
								</td>
							</tr>
							<tr align="center" style="background-color: #F3F3F3;">
								<td rowspan="2" style="background-color: #F3F3F3;">
									学校
								</td>
								<td  rowspan="2">
									学习中心
								</td>
								<td colspan="3" style="background-color: #F3F3F3;">
									招生指标
								</td>
								<td colspan="3" >
									录取人数
								</td>
								<td colspan="3" style="background-color: #F3F3F3;">
									缴费人数
								</td>
								
							</tr>

							<tr align="center" style="background-color: #F3F3F3;">
								<td  >
									招生指标
								</td>
								<td  >
									报名人数
								</td>
								<td  >
									报名完成率
								</td>
								
								<td  >
									录取人数
								</td>
								<td  >
									未录取人数
								</td>
								<td  >
									录取率
								</td>
								
								<td  >
									缴学费人数
								</td>
								<td  >
									缴学费金额
								</td>
								<td  >
									缴费率
								</td>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
		</body:body>
		
	</body>
</html>