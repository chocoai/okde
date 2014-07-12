<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<jc:plugin name="jquery" />
		<jc:plugin name="jquery_ui" />
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
			// 全选
			function chkall(obj)
			{
				jQuery(".fsclass").attr('checked',obj.checked);
			}
			// 显示费用科目
			function showdiv()
			{
				jQuery('#fee_subject_div').dialog('open');
			}
		</script>
		<a:ajax successCallbackFunctions="yuanxiaoSuccessCallback"  urls="/crm/academys_academie_list" pluginCode="yuanxiao" isOnload="all"/>
		<a:ajax successCallbackFunctions="zhongxingSuccessCallback"  urls="/crm/all_branch_list" pluginCode="zhongxing" isOnload="all"/>
		<a:ajax successCallbackFunctions="piciSuccessCallback"  urls="/crm/gl_enroll_batch_list" pluginCode="pici" isOnload="all"/>
		<script type="text/javascript">
			function report_cedu_annual_income_success_callback(data) {
				// 动态加载费用科目列
				var listTitle='';
				listTitle+='<td style="background-color: #F3F3F3;">返款开始时间</td>';
				listTitle+='<td style="background-color: #F3F3F3;">返款结束时间</td>';
				listTitle+='<td style="background-color: #F3F3F3;">学习中心</td>';
				listTitle+='<td style="background-color: #F3F3F3;">院校</td>';
				listTitle+='<td style="background-color: #F3F3F3;">缴学费人数</td>';
				// 有选择的费用科目仅显示选择的，没有则返回全部费用科目
				if(jQuery('#feeSubjectNames').val()==''){
					jQuery("input[name='fscheck']").each(function(){
						listTitle+='<td style="background-color: #F3F3F3;">'+jQuery("#sp_"+jQuery(this).val()).text()+'(已收/应收)</td>';
					});
				}else{
					jQuery("input[name='fscheck']").each(function(){
						if(jQuery(this).attr("checked")==true)
						{
							listTitle+='<td style="background-color: #F3F3F3;">'+jQuery("#sp_"+jQuery(this).val()).text()+'(已收/应收)</td>';
						}
					});
				}
				listTitle+='<td style="background-color: #F3F3F3;">其他(已收/应收)</td>';
				listTitle+='<td style="background-color: #F3F3F3;">调整金额(已收/应收)</td>';
				listTitle+='<td style="background-color: #F3F3F3;">应收院校款(已收/应收)</td>';
				listTitle+='<td style="background-color: #F3F3F3;">应收合计</td>';
				listTitle+='<td style="background-color: #F3F3F3;">已收合计</td>';
				listTitle+='<td style="background-color: #F3F3F3;">未收合计</td>';
				$('#colStr').html(listTitle);
				
				if(data.resultMap!=null){
					var listStr="";
					$(data.resultMap.branchList).each(function(){
						var branch = this;
						if(branch.branch_academy_list!=null){
							$(branch.branch_academy_list).each(function(){
								var academy = this;
								listStr+="<tr>";
								listStr+="<td align='center'>"+data.resultMap.start_date+"</td>";
								listStr+="<td align='center'>"+data.resultMap.end_date+"</td>";
								listStr+="<td align='center'>"+branch.branch_name+"</td>";
								listStr+="<td align='center'>"+academy.academy_name+"</td>";
								listStr+="<td align='center'>"+academy.jiaofeiCount+"</td>";
								var value;
								var fsNames = jQuery('#feeSubjectNames').val();
								if(fsNames==''){
									jQuery("input[name='fscheck']").each(function(){
										var code = 'value = academy.feeSubject_'+jQuery(this).val();
										eval(code);
										listStr+="<td align='center'>"+value+"</td>";
									});
								}else{
									jQuery("input[name='fscheck']").each(function(){
										if(jQuery(this).attr("checked")==true){
											var code = 'value = academy.feeSubject_'+jQuery(this).val();
											eval(code);
											listStr+="<td align='center'>"+value+"</td>";
										}
									});
								}
								listStr+="<td align='right'>"+academy.otherMoney+"&nbsp;&nbsp;</td>";
								listStr+="<td align='right'>"+academy.tiaozhengSum+"&nbsp;&nbsp;</td>";
								listStr+="<td align='right'>"+academy.yingshouyuanxiaoSum+"&nbsp;&nbsp;</td>";
								listStr+="<td align='right'>"+academy.yingfan_sum+"&nbsp;&nbsp;</td>";
								listStr+="<td align='right'>"+academy.shifan_sum+"&nbsp;&nbsp;</td>";
								listStr+="<td align='right'>"+academy.weifan_sum+"&nbsp;&nbsp;</td>";
								listStr+="</tr>";
							});
						}
						listStr+="<tr style='background:#CCFFFF;'>";
						listStr+="<td align='center' colspan='4'>"+"中心合计"+"</td>";
						listStr+="<td align='center'>"+branch.branch_jiaofeiCount+"</td>";
						var b_value;
						var fsNames = jQuery('#feeSubjectNames').val();
						if(fsNames==''){
							jQuery("input[name='fscheck']").each(function(){
								var code = 'b_value = branch.branch_feeSubject_'+jQuery(this).val();
								eval(code);
								listStr+="<td align='center'>"+b_value+"</td>";
							});
						}else{
							jQuery("input[name='fscheck']").each(function(){
								if(jQuery(this).attr("checked")==true){
									var code = 'b_value = branch.branch_feeSubject_'+jQuery(this).val();
									eval(code);
									listStr+="<td align='center'>"+b_value+"</td>";
								}
							});
						}
						listStr+="<td align='right'>"+branch.branch_otherMoney+"&nbsp;&nbsp;</td>";
						listStr+="<td align='right'>"+branch.branch_tiaozhengSum+"&nbsp;&nbsp;</td>";
						listStr+="<td align='right'>"+branch.branch_yingshouyuanxiaoSum+"&nbsp;&nbsp;</td>";
						listStr+="<td align='right'>"+branch.branch_yingfanSum+"&nbsp;&nbsp;</td>";
						listStr+="<td align='right'>"+branch.branch_shifanSum+"&nbsp;&nbsp;</td>";
						listStr+="<td align='right'>"+branch.branch_weifanSum+"&nbsp;&nbsp;</td>";
						listStr+="</tr>";
					});
					
					$("#tongjibiao >tbody").html(listStr);
					_w_table_rowspan("#tongjibiao",3);
					
					listStr="<tr style='background:yellow;'>";
					listStr+="<td align='center' colspan='4'>"+"总合计"+"</td>";
					listStr+="<td align='center'>"+data.resultMap.all_jiaofeiCount+"</td>";
					var all_value;
					var fsNames = jQuery('#feeSubjectNames').val();
					if(fsNames==''){
						jQuery("input[name='fscheck']").each(function(){
							var code = 'all_value = data.resultMap.all_feeSubject_'+jQuery(this).val();
							eval(code);
							listStr+="<td align='center'>"+all_value+"</td>";
						});
					}else{
						jQuery("input[name='fscheck']").each(function(){
							if(jQuery(this).attr("checked")==true){
								var code = 'all_value = data.resultMap.all_feeSubject_'+jQuery(this).val();
								eval(code);
								listStr+="<td align='center'>"+all_value+"</td>";
							}
						});
					}
					listStr+="<td align='right'>"+data.resultMap.all_otherMoney+"&nbsp;&nbsp;</td>";
					listStr+="<td align='right'>"+data.resultMap.all_tiaozhengSum+"&nbsp;&nbsp;</td>";
					listStr+="<td align='right'>"+data.resultMap.all_yingshouyuanxiaoSum+"&nbsp;&nbsp;</td>";
					listStr+="<td align='right'>"+data.resultMap.all_yingfanSum+"&nbsp;&nbsp;</td>";
					listStr+="<td align='right'>"+data.resultMap.all_shifanSum+"&nbsp;&nbsp;</td>";
					listStr+="<td align='right'>"+data.resultMap.all_weifanSum+"&nbsp;&nbsp;</td>";
					
					$("#tongjibiao >tbody").html($("#tongjibiao >tbody").html()+listStr);
				}
			}
			function submitCeduAnnualIncome(){
				if($("#starttime").val()==null||$("#starttime").val()==""){
					alert("开始时间不能为空");
					return ;
				}
				if($("#endtime").val()==null||$("#endtime").val()==""){
					alert("结束时间不能为空");
					return ;
				}
				// 如果科目为空 返回全部科目
				var fsIds = jQuery('#feeSubjectIds').val();
				if(fsIds==''){
					jQuery("input[name='fscheck']").each(function(){
						if(fsIds==''){
							fsIds=jQuery(this).val();
						}else{
							fsIds+=","+jQuery(this).val();
						}
					});
				}
				jQuery('#feeSubjectIds').val(fsIds);
				
				ajax_cedu_annual_income_1();
			}
			function returnSubmit(){
				if($("#starttime").val()==null||$("#starttime").val()==""){
					alert("开始时间不能为空");
					return false;
				}
				if($("#endtime").val()==null||$("#endtime").val()==""){
					alert("结束时间不能为空");
					return false;
				}
				// 如果科目为空 返回全部科目
				var fsIds = jQuery('#feeSubjectIds').val();
				if(fsIds==''){
					jQuery("input[name='fscheck']").each(function(){
						if(fsIds==''){
							fsIds=jQuery(this).val();
						}else{
							fsIds+=","+jQuery(this).val();
						}
					});
				}
				jQuery('#feeSubjectIds').val(fsIds);
				return true;
			}
			jQuery(document).ready(function(){
				$("#starttime").val(getYearFirstDay());
				$("#endtime").val(new Date().pattern("yyyy-MM-dd"));
				//选择费用科目
				jQuery('#fee_subject_div').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'费用科目',
					buttons: {
						'确定': function() { 
							//获取选中的值
							var fsIds='';
							var fsNames='';
							jQuery("input[name='fscheck']").each(function(){
								if(jQuery(this).attr("checked")==true)
								{
								 	if(fsIds==''){
								 		fsIds=jQuery(this).val();
								 	}else{
								 		fsIds+=","+jQuery(this).val();
								 	}
								 	fsNames+=jQuery("#sp_"+jQuery(this).val()).text()+";";
								}
							});
							jQuery('#feeSubjectIds').val(fsIds);
							jQuery('#feeSubjectNames').val(fsNames);
							
							jQuery(this).dialog("close"); 
						}, 
						'关闭': function() { 
							jQuery(this).dialog("close"); 
						} 
					}
				});	
			});
		</script>

		<a:ajax parameters="$('#search').serializeObject()"
			successCallbackFunctions="report_cedu_annual_income_success_callback"
			urls="/report/report_cedu_annual_income"
			pluginCode="cedu_annual_income" />
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="中心收入情况表">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="search" action="<uu:url url="report/excel_export_cedu_annual_income" />" onsubmit="return returnSubmit()">
						<table width="100%" class="add_table" border="0" cellpadding="1"
							cellspacing="0">
							<tr>
								<td class="lable_100">
									学习中心：
								</td>
								<td>
									<select class="txt_box_130" id="branch" name="mapParams.branch"></select>
								</td>
								<td class="lable_100">
									全局批次：
								</td>
								<td>
									<select class="txt_box_130" id="batch" name="mapParams.batch"></select>
								</td>
								<td class="lable_100">
									院校：
								</td>
								<td>
									<select class="txt_box_130" id="school" name="mapParams.school"></select>
								</td>
							</tr>
							<tr>
								<td class="lable_100">
									开始时间：
								</td>
								<td>
									<input id="starttime" class="Wdate" type="text"
										name="dateParams.startDate" value=""
										onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
										size="17"/>
								</td>
								<td class="lable_100">
									结束时间：
								</td>
								<td>
									<input id="endtime" class="Wdate" type="text"
										name="dateParams.endDate" value="" size="17"
										onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
								</td>
								<td class="lable_100">
									<input class="btn_black_61" type="button"
										onclick="submitCeduAnnualIncome();" value="查询" />
								</td>
								<td>
									<input class="btn_black_61" type="submit" value="导出" />
								</td>
							</tr>
							<tr>
								<td class="lable_100">
									费用科目：
								</td>
								<td>
									<input id="feeSubjectNames" class="txt_box_300" type="text" value="" readonly="readonly"/>&nbsp;&nbsp;<a href="javascript:showdiv();" style="text-decoration:underline">请选择</a>
									<input name="strParams.feeSubjectIds" id="feeSubjectIds" type="hidden" value="" />
								</td>
								<td class="lable_100">
									
								</td>
								<td>
									
								</td>
								<td class="lable_100">
									
								</td>
								<td>
									
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
											中心收入情况表
										</h2>
									</td>
								</tr>
								<tr align="center" style="background-color: #F3F3F3;" id="colStr">
									<td style="background-color: #F3F3F3;">
										返款开始时间
									</td>
									<td style="background-color: #F3F3F3;">
										返款结束时间
									</td>
									<td style="background-color: #F3F3F3;">
										学习中心
									</td>
									<td style="background-color: #F3F3F3;">
										院校
									</td>
									<td style="background-color: #F3F3F3;">
										缴学费人数
									</td>
									<td style="background-color: #F3F3F3;">
										各费用科目(已收/应收)
									</td>
									<td style="background-color: #F3F3F3;">
										其他(已收/应收)
									</td>
									<td style="background-color: #F3F3F3;">
										调整金额(已收/应收)
									</td>
									<td style="background-color: #F3F3F3;">
										应收院校款(已收/应收)
									</td>
									<td style="background-color: #F3F3F3;">
										应收合计
									</td>
									<td style="background-color: #F3F3F3;">
										已收合计
									</td>
									<td style="background-color: #F3F3F3;">
										未收合计
									</td>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
		</body:body>
		<div id="fee_subject_div" style="display:none">
			<table class="gv_table_2" id="">
				<thead>
					<tr>
						<th align="center"><input type="checkbox" onclick="chkall(this)" /></th>
				  		<th align="center">费用科目</th>
					</tr>
				</thead>					
				<tbody>	
					<s:if test="%{feesubjectlist!=null && feesubjectlist.size()>0}">
						<s:iterator id="item" value="feesubjectlist" >
							<tr>
								<td align="center"><input type="checkbox" id="fs_${item.id}" name="fscheck" class="fsclass" value="${item.id}" /></td>
						  		<td align="center"><span id="sp_${item.id}" style="color:black">${item.name}</span></td>
							</tr>
						</s:iterator>
			       </s:if>
			       <s:else>
				       <tr>
				       	 <td colspan="2" align="center">无费用科目</td>
				       </tr>
			      </s:else>							
				</tbody>						
			</table>
		</div>
	</body>
</html>