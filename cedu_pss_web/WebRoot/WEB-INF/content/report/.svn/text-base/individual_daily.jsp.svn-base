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
		<jc:plugin name="page" />
		<script type="text/javascript">
			function org_tree_success_callback(data){
				if(data.treeList!=null){
					var strHtml="";
					$("#user").html("");
					$(data.treeList).each(function(){
						var branch=this;
						$(branch.departments).each(function(){
							//部门对象
							var department=this;
							strHtml+="<optgroup label='"+department.departmentName+"'>";
							$(department.users).each(function(){
								//用户对象
								var user=this;
								strHtml+=("<option value='" + user.userId + "' title=" + user.userName + " >"+ user.userName + "</option>")
							});
							strHtml+="</optgroup>";
						});
						
					});
					$("#user").html(strHtml);
				}
			}
			function piciSuccessCallback(data){
				$("#batch").html("");
               	//$("<option value='" + -2 + "'>全部</option>").appendTo($("#batch"));
				$(data.globalEnrollBatchs).each(function(){
						$("<option value='" + this.id + "'>" + this.batch + "</option>").appendTo($("#batch"));
					
				});
			}
		</script>
		<a:ajax successCallbackFunctions="org_tree_success_callback" pluginCode="001" urls="worklog/org_tree" isOnload="all" />
		<a:ajax successCallbackFunctions="piciSuccessCallback"  urls="/crm/gl_enroll_batch_list" pluginCode="pici" isOnload="all"/>
		<script type="text/javascript">
			function individual_daily_success_callback(data){
				if(data.reportList!=null){
					var listStr="";
					$(data.reportList).each(function(){
						listStr+="<tr>";
						listStr+="<td align='center'>"+this.daily_date+"</td>";
						listStr+="<td align='center'>"+this.dang_tian_gen_zong_xue_sheng_count+"</td>";
						listStr+="<td align='center'>"+this.she_zhao_count+"</td>";
						listStr+="<td align='center'>"+this.qu_dao_count+"</td>";
						listStr+="<td align='center'>"+this.da_ke_hu_count+"</td>";
						listStr+="<td align='center'>"+this.lao_dai_xin_count+"</td>";
						listStr+="<td align='center'>"+this.lao_sheng_xu_du_count+"</td>";
						listStr+="<td align='center'>"+this.jia_meng_count+"</td>";
						listStr+="<td align='center'>"+this.gong_jian_count+"</td>";
						listStr+="<td align='right' style='padding-right:5px;'>"+this.dang_qian_pi_ci_money+"</td>";
						listStr+="<td align='right' style='padding-right:5px;'>"+this.lao_pi_ci_money+"</td>";
						listStr+="<td align='center'><div class='divTips' title='"+this.dang_tian_zhu_yao_gong_zuo+"'>"+this.dang_tian_zhu_yao_gong_zuo.toSubString(15)+"</div></td>";
						listStr+="<td align='center'><div class='divTips' title='"+this.ling_dao_ping_jia+"'>"+this.ling_dao_ping_jia.toSubString(15)+"</div></td>";
						listStr+="<td align='center'><div class='divTips' title='"+this.ling_dao_ping_fen+"'>"+this.ling_dao_ping_fen.toSubString(15)+"</div></td>";
						listStr+="</tr>";
						//alert(listStr);
					});
					$("#tongjibiao >tbody").html(listStr);
					$(".divTips").tip();
				}
			}
			function submitReportSearch(){
				if($("#starttime").val()==null||$("#starttime").val()==""){
					alert("开始时间不能为空");
					return ;
				}
				if($("#endtime").val()==null||$("#endtime").val()==""){
					alert("结束时间不能为空");
					return ;
				}
				ajax_individual_daily_1();
			}
			$(document).ready(function(){
				$("#starttime").val(getFirstDay());
				$("#endtime").val(new Date().pattern("yyyy-MM-dd"));
			});
		</script>
		<a:ajax parameters="$('#search').serializeObject()" successCallbackFunctions="individual_daily_success_callback"urls="/report/report_individual_daily" pluginCode="individual_daily"/>
		
		

	</head>
	<body>

		<!--头部层开始 -->
		<head:head title="日报表(按人)">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="search"
				action="<uu:url url="report/excel_export_individual_daily" />">
				<table style="height: 100%; width: 100%;" cellpadding="0"
					cellspacing="0" border="0">
					<td style="vertical-align: top;" height="100%" width="100%">
						<table width="100%" class="add_table" border="0" cellpadding="1"
							cellspacing="0">
							<tr>

								<td class="lable_100">
									个人：
								</td>
								<td>
									<select class="txt_box_130" id="user" name="mapParams.user">
										
									</select>
								</td>
								<td class="lable_100">
									当前批次：
								</td>
								<td  colspan="3">
									<select class="txt_box_130" id="batch" name="mapParams.batch">
										
									</select>
								</td>

							</tr>
							<tr>
								<td class="lable_100">
									开始时间：
								</td>
								<td style="width:150px;">
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
										onclick="submitReportSearch();" value="查询" />
								</td>
								<td>

									<input class="btn_black_61" type="submit" value="导出" />
								</td>

							</tr>
						</table>
						</form>

						<table id="tongjibiao"  class="gv_table" style="width:100%" border="0" cellpadding="0"
							cellspacing="0">
							<thead>
								<tr align="center" style="background-color: #CCCCCC;">
									<td colspan="14" style="background-color: #F3F3F3;">
										<h2 id="settitle" style="text-align: center; padding: 10px;">
											个人日报表
										</h2>
									</td>

								</tr>
								<tr align="center" style="background-color: #CCCCCC;">
									<td style="background-color: #F3F3F3;">
										日期
									</td>
									<td colspan="11" style="background-color: #F3F3F3;">
										当日工作情况
									</td>
									<td colspan="2" style="background-color: #F3F3F3;">
										评价情况
									</td>
								</tr>
								<tr align="center" style="background-color: #CCCCCC;">
									<td style="background-color: #F3F3F3;">
										
									</td>
									<td style="background-color: #F3F3F3;">
										跟踪数量
									</td>
									<td colspan="7" style="background-color: #F3F3F3;">
										新增报名人数
									</td>
									<td colspan="2" style="background-color: #F3F3F3;">
										当天缴费金额
									</td>
									<td colspan="1" style="background-color: #B8860B;">
										当天主要工作
									</td>
									<td colspan="1" style="background-color: #B8860B;">
										当日领导评价
									</td>
									<td colspan="1" style="background-color: #B8860B;">
										当日领导评分
									</td>
								</tr>
								<tr align="center" style="background-color: #CCCCCC;">
									<td style="background-color: #F3F3F3;">
										
									</td>
									<td style="background-color: #F3F3F3;">
									
									</td>
									<td  style="background-color: #F3F3F3;">
										社招
									</td>
									<td style="background-color: #F3F3F3;">
										渠道
									</td>
									<td style="background-color: #F3F3F3;">
										大客户
									</td>
									<td style="background-color: #F3F3F3;">
										老带新
									</td>
									<td style="background-color: #F3F3F3;">
										老生续读
									</td>
									<td style="background-color: #F3F3F3;">
										加盟
									</td>
									<td style="background-color: #F3F3F3;">
										共建
									</td>
									<td style="background-color: #F3F3F3;">
										当前批次
									</td>
									<td style="background-color: #F3F3F3;">
										老批次
									</td>
									<td style="background-color: #B8860B;">
									
									</td>
									<td style="background-color: #B8860B;">
									
									</td>
									<td style="background-color: #B8860B;">
									
									</td>
									
								</tr>
								
							</thead>
							<tbody>

							</tbody>
						</table>
					</td>
				</table>
		</body:body>

	</body>
</html>