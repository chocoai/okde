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
			//招生途径和市场途径回调函数
			function wayAndSourceCallback(data){
				$("#source").html("");
	            $("<option value='" + -2 + "'>全部</option>").appendTo($("#source"));
								
				$(data.enrollmentSources).each(function(){
					$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#source"));
				});
								
//				$("#way").html("");
//				var wayStr="<option value='" + -2 + "'>全部</option>";
//				$.each(data.enrollmentWaysMap,function(key,value){
//					if(key!="大客户"&&key!="渠道"&&key!="老带新"&&key!="加盟"&&key!="共建"){
//						wayStr+="<optgroup label='"+key+"'>";
//						$(this).each(function(){
//							if(this.id=='${student.enrollmentWay}'){
//								wayStr+="<option selected='selected' value='" + this.id + "'>" + this.name + "</option>";
//							}else{
//								wayStr+="<option value='" + this.id + "'>" + this.name + "</option>";
//							}
//						});
//						wayStr+="</optgroup>";
//					}
//				});
//				$("#way").html(wayStr);
			}
		</script>
		<a:ajax successCallbackFunctions="zhongxingSuccessCallback"  urls="/crm/all_branch_list" pluginCode="zhongxing" isOnload="all"/>
		<a:ajax successCallbackFunctions="piciSuccessCallback"  urls="/crm/gl_enroll_batch_list" pluginCode="pici" isOnload="all"/>
		<a:ajax successCallbackFunctions="wayAndSourceCallback"  urls="/crm/student_way_list" pluginCode="laiyuan_tujing" isOnload="all"/>
		<script type="text/javascript">
			function report_branch_rebate_success_callback(data) {
				if(data.resultMap!=null){
					var listStr="";
					$(data.resultMap.reportList).each(function(){
						listStr+="<tr>";
						listStr+="<td align='center'>"+this.number+"</td>";
						listStr+="<td align='center'>"+this.branchName+"</td>";
						listStr+="<td align='center'>"+this.startDate+"</td>";
						listStr+="<td align='center'>"+this.endDate+"</td>";
						
						listStr+="<td align='right'>"+this.qudaoMoney+"&nbsp;&nbsp;</td>";
						listStr+="<td align='right'>"+this.laodaixinMoney+"&nbsp;&nbsp;</td>";
						listStr+="<td align='right'>"+this.dakehuMoney+"&nbsp;&nbsp;</td>";
						listStr+="<td align='right'>"+this.jiamengMoney+"&nbsp;&nbsp;</td>";
						
						listStr+="<td align='right'>"+this.gongjianMoney+"&nbsp;&nbsp;</td>";
						listStr+="<td align='right'>"+this.otherMoney+"&nbsp;&nbsp;</td>";
						listStr+="<td align='right'>"+this.tiaozhengMoney+"&nbsp;&nbsp;</td>";
						
						listStr+="<td align='right'>"+this.yingHejiMoney+"&nbsp;&nbsp;</td>";
						listStr+="<td align='right'>"+this.yiHejiMoney+"&nbsp;&nbsp;</td>";
						listStr+="<td align='right'>"+this.weiHejiMoney+"&nbsp;&nbsp;</td>";
						listStr+="</tr>";
					});
						listStr+="<tr style='background:yellow;'>";
							listStr+="<td align='center'>"+""+"</td>";
							listStr+="<td align='center'>"+""+"</td>";
							listStr+="<td align='center'>"+""+"</td>";
							listStr+="<td align='center'>"+""+"</td>";
							
							listStr+="<td align='right'>"+data.resultMap.qudaoMoneySum+"&nbsp;&nbsp;</td>";
							listStr+="<td align='right'>"+data.resultMap.laodaixinMoneySum+"&nbsp;&nbsp;</td>";
							listStr+="<td align='right'>"+data.resultMap.dakehuMoneySum+"&nbsp;&nbsp;</td>";
							listStr+="<td align='right'>"+data.resultMap.jiamengMoneySum+"&nbsp;&nbsp;</td>";
						
							listStr+="<td align='right'>"+data.resultMap.gongjianMoneySum+"&nbsp;&nbsp;</td>";
							listStr+="<td align='right'>"+data.resultMap.otherMoneySum+"&nbsp;&nbsp;</td>";
							listStr+="<td align='right'>"+data.resultMap.tiaozhengMoneySum+"&nbsp;&nbsp;</td>";
							
							listStr+="<td align='right'>"+data.resultMap.yingHejiMoneySum+"&nbsp;&nbsp;</td>";
							listStr+="<td align='right'>"+data.resultMap.yiHejiMoneySum+"&nbsp;&nbsp;</td>";
							listStr+="<td align='right'>"+data.resultMap.weiHejiMoneySum+"&nbsp;&nbsp;</td>";
							listStr+="</tr>";
					$("#tongjibiao >tbody").html(listStr);
				}
			}
			function submitBranchRebate(){
				if($("#starttime").val()==null||$("#starttime").val()==""){
					alert("开始时间不能为空");
					return ;
				}
				if($("#endtime").val()==null||$("#endtime").val()==""){
					alert("结束时间不能为空");
					return ;
				}
				ajax_report_branch_rebate_1();
			}
			jQuery(document).ready(function(){
				$("#starttime").val(getYearFirstDay());
				$("#endtime").val(new Date().pattern("yyyy-MM-dd"));
			});
		</script>
		<a:ajax parameters="$('#search').serializeObject()"
			successCallbackFunctions="report_branch_rebate_success_callback"
			urls="/report/report_branch_rebate"
			pluginCode="report_branch_rebate" />

	</head>
	<body>

		<!--头部层开始 -->
		<head:head title="招生返款情况表">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="search"
				action="<uu:url url="report/excel_export_branch_rebate" />">
				<table style="height: 100%; width: 100%;" cellpadding="0"
					cellspacing="0" border="0">
					<td style="vertical-align: top;" height="100%" width="100%">
						<table width="100%" class="add_table" border="0" cellpadding="1"
							cellspacing="0">
							<tr>
								<td class="lable_100">全局批次：</td>
								<td>
									<select class="txt_box_130" id="batch" name="mapParams.batch"></select>
								</td>
								<td class="lable_100">学习中心：</td>
								<td>
									<select class="txt_box_130" id="branch" name="mapParams.branch"></select>
								</td>
								<td class="lable_100">招生途经：</td>
								<td>
									<select class="txt_box_130" id="source" name="mapParams.source"></select>
								</td>
								<td class="lable_100"></td>
								<td></td>
							</tr>
							<tr>
								<td class="lable_100">
									开始时间：
								</td>
								<td style="width: 150px;">
									<input id="starttime" class="Wdate" type="text"
										name="dateParams.startDate" value=""
										onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
										size="17">
								</td>
								<td class="lable_100">
									结束时间：
								</td>
								<td>
									<input id="endtime" class="Wdate" type="text"
										name="dateParams.endDate" value="" size="17"
										onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})">
								</td>

								<td class="lable_100">
									<input class="btn_black_61" type="button"
										onclick="submitBranchRebate();" value="查询" />
								</td>
								<td>

									<input class="btn_black_61" type="submit" value="导出" />
								</td>

							</tr>
						</table>
						</form>
						<table id="tongjibiao" class="gv_table" border="0"
							style="width: 100%" cellpadding="0" cellspacing="0">
							<thead>
								<tr align="center" style="background-color: #F3F3F3;">
									<td colspan="23" style="background-color: #F3F3F3;">
										<h2 id="settitle" style="text-align: center; padding: 10px;">
											招生返款情况表
										</h2>
									</td>
								</tr>
								<tr align="center" style="background-color: #F3F3F3;">
									<td style="background-color: #F3F3F3;">
										序号
									</td>
									<td style="background-color: #F3F3F3;">
										学习中心
									</td>
									<td style="background-color: #F3F3F3;">
										返款开始日期
									</td>
									<td style="background-color: #F3F3F3;">
										返款结束日期
									</td>
									<td style="background-color: #F3F3F3;">
										渠道(已返/应返)
									</td>
									<td style="background-color: #F3F3F3;">
										老带新(已返/应返)
									</td>
									<td style="background-color: #F3F3F3;">
										大客户(已返/应返)
									</td>
									<td style="background-color: #F3F3F3;">
										加盟(已返/应返)
									</td>
									<td style="background-color: #F3F3F3;">
										共建(已返/应返)
									</td>
									<td style="background-color: #F3F3F3;">
										其他(已返/应返)
									</td>
									<td style="background-color: #F3F3F3;">
										调整金额(已返/应返)
									</td>
									<td style="background-color: #F3F3F3;">
										应返款额合计
									</td>
									<td style="background-color: #F3F3F3;">
										已返款额合计
									</td>
									<td style="background-color: #F3F3F3;">
										未返款额合计
									</td>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
		</body:body>

	</body>
</html>