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
			function piciSuccessCallback(data) {
				$("#batch").html("");
				$(data.globalEnrollBatchs).each(
						function() {
							$(
									"<option value='" + this.id + "'>" + this.batch
											+ "</option>").appendTo($("#batch"));
		
						});
				//加载个人日报
				ajax_individual_daily_1();
			}
		</script>
				<a:ajax successCallbackFunctions="piciSuccessCallback" urls="/crm/gl_enroll_batch_list" pluginCode="pici" isOnload="all" />
		<script type="text/javascript">
			function individual_daily_success_callback(data) {
				if (data.reportList != null) {
					var listStr = "";
					$(data.reportList)
							.each(
									function() {
										listStr += "<tr>";
										listStr += "<td align='center'>"
												+ this.daily_date + "</td>";
										listStr += "<td align='center'>"
												+ this.dang_tian_gen_zong_xue_sheng_count
												+ "</td>";
										listStr += "<td align='center'>"
												+ this.she_zhao_count + "</td>";
										listStr += "<td align='center'>"
												+ this.qu_dao_count + "</td>";
										listStr += "<td align='center'>"
												+ this.da_ke_hu_count + "</td>";
										listStr += "<td align='center'>"
												+ this.lao_dai_xin_count + "</td>";
										listStr += "<td align='center'>"
												+ this.lao_sheng_xu_du_count + "</td>";
										listStr += "<td align='center'>"
												+ this.jia_meng_count + "</td>";
										listStr += "<td align='center'>"
												+ this.gong_jian_count + "</td>";
										listStr += "<td align='right' style='padding-right:5px;'>"
												+ this.dang_qian_pi_ci_money + "</td>";
										listStr += "<td align='right' style='padding-right:5px;'>"
												+ this.lao_pi_ci_money + "</td>";
										listStr += "</tr>";
										//alert(listStr);
									});
					$("#tongjibiao >tbody").html(listStr);
				}
			}
		</script>
		<a:ajax parameters="$('#search').serializeObject()" successCallbackFunctions="individual_daily_success_callback" urls="/report/report_individual_daily" pluginCode="individual_daily" />



	</head>
	<body>
		<form id="search">
			<input type="hidden"  name="dateParams.startDate" value="${ date }" />
			<input type="hidden" name="dateParams.endDate" value="${ date }" />
			<input type="hidden" name="mapParams.user" value="${ userId }" />


			<table id="tongjibiao" class="gv_table" border="0" cellpadding="0"
				cellspacing="0">
				<thead>

					<tr align="center" style="background-color: #CCCCCC;">
						<td style="background-color: #F3F3F3;">
							日期
						</td>
						<td colspan="10" style="background-color: #F3F3F3;">
							当日工作情况
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

					</tr>
					<tr align="center" style="background-color: #CCCCCC;">
						<td style="background-color: #F3F3F3;">

						</td>
						<td style="background-color: #F3F3F3;">

						</td>
						<td style="background-color: #F3F3F3;">
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
							当前批次&nbsp;&nbsp;
							<select class="txt_box_70" id="batch" name="mapParams.batch" onchange="ajax_individual_daily_1();"></select>
						</td>
						<td style="background-color: #F3F3F3;">
							老批次
						</td>

					</tr>

				</thead>
				<tbody>

				</tbody>
			</table>
			</td>
			</table>
		</form>

	</body>
</html>