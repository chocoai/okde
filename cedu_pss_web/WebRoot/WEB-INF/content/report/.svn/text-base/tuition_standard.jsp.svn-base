<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<jc:plugin name="jquery" />
		<jc:plugin name="default" />
		<jc:plugin name="loading" />
		
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
			
		</script>
		<a:ajax successCallbackFunctions="yuanxiaoSuccessCallback"  urls="/crm/academys_academie_list" pluginCode="yuanxiao" isOnload="all"/>
		<a:ajax successCallbackFunctions="zhongxingSuccessCallback"  urls="/crm/all_branch_list" pluginCode="zhongxing" isOnload="all"/>
		<a:ajax successCallbackFunctions="piciSuccessCallback"  urls="/crm/gl_enroll_batch_list" pluginCode="pici" isOnload="all"/>
		
		<script type="text/javascript">
				function reportSuccessCallback(data){
					var resultMap=data.resultMap;
					if(data.resultMap.branchList!=null&&data.resultMap.branchList.length!=0){
						if(data.resultMap.academyList!=null&&data.resultMap.academyList.length!=0){
							var theadStr="";
							// 标题
							theadStr+="<tr align='center' style='background-color: #F3F3F3;'>";
							theadStr+="<td colspan='"+(data.resultMap.academyList.length+2)+"' style='background-color: #F3F3F3;'>";
							theadStr+="<h2 id='settitle' style='text-align: center;padding:10px;'>";
							theadStr+="学费标准汇总";
							theadStr+="</h2>";
							theadStr+="</td>";
							theadStr+="</tr>";
							// 第一行(院校名称)
							theadStr+="<tr align='center' style='background-color: #F3F3F3;'>";
							theadStr+="<td align='center' style='background-color: #F3F3F3;'>城市</td>";
							theadStr+="<td align='center' style='background-color: #F3F3F3;'>中心/院校</td>";
							for(var i = 0;i<data.resultMap.academyList.length; i++){
								var academy = data.resultMap.academyList[i];
								theadStr+="<td align='center' style='background-color: #F3F3F3;'>"+academy.name+"</td>";
							}
							theadStr+="</tr>";
							for(var i = 0;i<data.resultMap.branchList.length; i++){
								var branch = data.resultMap.branchList[i];
								theadStr+="<tr align='center'>";
								theadStr+="<td align='center'>";
								theadStr+=branch.branch_city;
								theadStr+="</td>"
								theadStr+="<td align='center'>";
								theadStr+=branch.branch_name;
								theadStr+="</td>"
								if(branch.tuitionStandard!=null&&branch.tuitionStandard.length!=0){
									for(var j = 0;j<branch.tuitionStandard.length;j++){
										theadStr+="<td align='center'>";
										theadStr+=branch.tuitionStandard[j];
										theadStr+="</td>"
									}
								}
								theadStr+="</tr>";
							}
						}
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
				
				function checkSeacrh(){
					if(jQuery('#batch').val()<0){
						alert('请选择批次！');
						return false;
					}
					return true;
				}
		</script>
		<a:ajax successCallbackFunctions="reportSuccessCallback" pluginCode="student_source" urls="/report/report_tuition_standard" parameters="$('#search').serializeObject()" />
		
	</head>
	<body>
	
		<!--头部层开始 -->
		<head:head title="学费标准汇总">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="search" action="<uu:url url="report/excel_export_tuition_standard" />" onsubmit="return checkSeacrh()">

					<table width="100%" class="add_table" border="0" cellpadding="2"
						cellspacing="0">
						<tr>
							<td class="lable_100">
								<span>*</span>全局批次：
							</td>
							<td >
								<select class="txt_box_130" id="batch" name="mapParams.batch"></select>
							</td>
							<td class="lable_100">
								学习中心：
							</td>
							<td>
								<select class="txt_box_130" id="branch" name="mapParams.branch"></select>
							</td>
							<td class="lable_100" >
								院校：
							</td>
							<td>
								<select class="txt_box_130" id="school" name="mapParams.school"></select>
							</td>
							<td class="lable_100">
								
							</td>
							<td>
								
							</td>
						</tr>
						<tr>
							<td ></td>
							<td></td>
							<td ></td>
							<td></td>
							<td class="lable_100"></td>
							<td></td>
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
							
						</thead>
						<tbody>
							
						</tbody>
					</table>
		</body:body>
		
	</body>
</html>