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
					if(data.resultMap.branchList!=null&&data.resultMap.branchList.length!=0){
						var theadStr="";
						for ( var int = 0; int < data.resultMap.branchList.length; int++) {
							var branch=data.resultMap.branchList[int];
							
							theadStr+="<tr>";
							theadStr+="<td align='center'>"+branch.branch_name+"</td>";
							theadStr+="<td align='center'>"+branch.branch_week_zhaoshengrenshu+"</td>";
							theadStr+="<td align='center'>"+branch.branch_week_jiaofeirenshu+"</td>";
							
							theadStr+="<td align='right'>"+branch.branch_week_xuefei+"&nbsp;</td>";
							theadStr+="<td align='right'>"+branch.branch_week_baomingfei+"&nbsp;</td>";
							theadStr+="<td align='right'>"+branch.branch_week_ceshifei+"&nbsp;</td>";
							theadStr+="<td align='right'>"+branch.branch_week_jiaocaifei+"&nbsp;</td>";
							theadStr+="<td align='right'>"+branch.branch_week_tongkaopeixunfei+"&nbsp;</td>";
							
							theadStr+="<td align='center'>"+branch.branch_zhibiao+"</td>";
							theadStr+="<td align='center'>"+branch.branch_batch_zhaoshengrenshu+"</td>";
							theadStr+="<td align='center'>"+branch.branch_batch_wanchenglv+"</td>";
							theadStr+="<td align='center'>"+branch.branch_batch_jiaofeirenshu+"</td>";
							theadStr+="<td align='center'>"+branch.branch_batch_jiaofeilv+"</td>";
							
							theadStr+="<td align='right'>"+branch.branch_batch_xuefei+"&nbsp;</td>";
							theadStr+="<td align='right'>"+branch.branch_batch_baomingfei+"&nbsp;</td>";
							theadStr+="<td align='right'>"+branch.branch_batch_ceshifei+"&nbsp;</td>";
							theadStr+="<td align='right'>"+branch.branch_batch_jiaocaifei+"&nbsp;</td>";
							theadStr+="<td align='right'>"+branch.branch_batch_tongkaopeixunfei+"&nbsp;</td>";
							
							theadStr+="</tr>";
						}
						theadStr+="<tr style='background:yellow;'>";
						theadStr+="<td align='center' colspan='1'>总计</td>";
						
						theadStr+="<td align='center'>"+resultMap.sum_branch_week_zhaoshengrenshu+"</td>";
						theadStr+="<td align='center'>"+resultMap.sum_branch_week_jiaofeirenshu+"</td>";
						
						theadStr+="<td align='right'>"+resultMap.sum_branch_week_xuefei+"&nbsp;</td>";
						theadStr+="<td align='right'>"+resultMap.sum_branch_week_baomingfei+"&nbsp;</td>";
						theadStr+="<td align='right'>"+resultMap.sum_branch_week_ceshifei+"&nbsp;</td>";
						theadStr+="<td align='right'>"+resultMap.sum_branch_week_jiaocaifei+"&nbsp;</td>";
						theadStr+="<td align='right'>"+resultMap.sum_branch_week_tongkaopeixunfei+"&nbsp;</td>";
						
						theadStr+="<td align='center'>"+resultMap.sum_branch_zhibiao+"</td>";
						theadStr+="<td align='center'>"+resultMap.sum_branch_batch_zhaoshengrenshu+"</td>";
						theadStr+="<td align='center'>"+resultMap.sum_branch_batch_wanchenglv+"</td>";
						theadStr+="<td align='center'>"+resultMap.sum_branch_batch_jiaofeirenshu+"</td>";
						theadStr+="<td align='center'>"+resultMap.sum_branch_batch_jiaofeilv+"</td>";
						
						theadStr+="<td align='right'>"+resultMap.sum_branch_batch_xuefei+"&nbsp;</td>";
						theadStr+="<td align='right'>"+resultMap.sum_branch_batch_baomingfei+"&nbsp;</td>";
						theadStr+="<td align='right'>"+resultMap.sum_branch_batch_ceshifei+"&nbsp;</td>";
						theadStr+="<td align='right'>"+resultMap.sum_branch_batch_jiaocaifei+"&nbsp;</td>";
						theadStr+="<td align='right'>"+resultMap.sum_branch_batch_tongkaopeixunfei+"&nbsp;</td>";
						
						theadStr+="</tr>";
						$("#tongjibiao >tbody").html(theadStr);
						_w_table_rowspan("#tongjibiao",1);
					}
				}
				
				function searchTable(){
					if(jQuery('#batch').val()<0){
						alert('请选择批次！');
						return false;
					}
					ajax_student_source_1();
				}
				function checkSearch(){
					if(jQuery('#batch').val()<0){
						alert('请选择批次！');
						return false;
					}
					return true;
				}
				jQuery(document).ready(function(){
					$("#starttime").val(new Date().pattern("yyyy-MM-dd"));
					$("#endtime").val(new Date().pattern("yyyy-MM-dd"));
				});
				
		</script>
		<a:ajax successCallbackFunctions="reportSuccessCallback" pluginCode="student_source" urls="/report/report_student_enrollment_monitor" parameters="$('#search').serializeObject()" />
		
	</head>
	<body>
	
		<!--头部层开始 -->
		<head:head title="周招生监控报表">
		</head:head>
		<!--主体层开始 -->
		<body:body >
			<form id="search" action="<uu:url url="report/excel_export_student_enrollment_monitor" />" onsubmit="return checkSearch()">

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
								<span>*</span>批次：
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
								<input id="starttime" class="Wdate" type="text" name="dateParams.startDate" value="" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" size="17"/>
							</td>
							<td class="lable_100">
								结束时间
							</td>
							<td>
								<input id="endtime" class="Wdate" type="text" name="dateParams.endDate" value="" size="17" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
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
								<input class="btn_black_61" type="button" onclick="searchTable();" value="查询" />
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
								<td colspan="18" style="background-color: #F3F3F3;">
										<h2 id="settitle" style="text-align: center;padding:10px;">
											周招生监控报表
										</h2>
								</td>
							</tr>
							<tr align="center" style="background-color: #F3F3F3;">
								<td rowspan="2" style="background-color: #F3F3F3;">
									学习中心
								</td>
								<td colspan="7" style="background-color: #F3F3F3;">
									时间段内
								</td>
								<td colspan="10" >
									当前批次
								</td>
							</tr>

							<tr align="center" style="background-color: #F3F3F3;">
								<td  >
									招生人数
								</td>
								<td  >
									缴学费人数
								</td>
								<td  >
									学费总额
								</td>
								<td  >
									报名费总额
								</td>
								<td  >
									测试费总额
								</td>
								<td  >
									教材费总额
								</td>
								<td  >
									统考培训费总额
								</td>
								<td  >
									招生指标
								</td>
								<td  >
									招生人数
								</td>
								<td  >
									指标完成率
								</td>
								<td  >
									缴学费人数
								</td>
								<td  >
									缴费率
								</td>
								<td  >
									学费总额
								</td>
								<td  >
									报名费总额
								</td>
								<td  >
									测试费总额
								</td>
								<td  >
									教材费总额
								</td>
								<td  >
									统考培训费总额
								</td>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
		</body:body>
		
	</body>
</html>