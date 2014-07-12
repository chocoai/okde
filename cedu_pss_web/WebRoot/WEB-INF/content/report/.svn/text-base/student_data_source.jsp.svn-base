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
				<c:if test="${branch!=null}">
					$("#branch_fuwu").html("");
	               	$("<option value='" + -2 + "'>全部</option>").appendTo($("#branch_fuwu"));
					$(data.branchlist).each(function(){
						if(this.level==2&&this.parentId=='${branch.id}'){	
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch_fuwu"));
						}
					});
				</c:if>
				<c:if test="${branch==null}">
					$("#branch").html("");
	               	$("<option value='" + -2 + "'>全部</option>").appendTo($("#branch"));
					$(data.branchlist).each(function(){
						if(this.level==1){
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
						}
					});
					$("#branch_fuwu").html("");
	               	$("<option value='" + -2 + "'>全部</option>").appendTo($("#branch_fuwu"));
					$(data.branchlist).each(function(){
						if(this.level==2){	
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch_fuwu"));
						}
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
			function userSuccessCallback(data){
				$("#user").html("");
	            $("<option value='" + -2 + "'>全部</option>").appendTo($("#user"));
								
				$(data.ulist).each(function(){
					if(this.isManager==0){
						$("<option value='" + this.id + "' title=" + this.fullName + " >" + this.fullName + "</option>").appendTo($("#user"));
					}
					// edit by dongminghao 屏蔽掉显示2次区域经理
					//else{
					//	$("<option value='" + this.id + "' title=" + this.fullName + " >" + this.fullName + "</option>").appendTo($("#manager"));
					//}
				});
			}
			function quYuCallback(data){
				$("#manager").html("");
	            $("<option value='" + -2 + "'>全部</option>").appendTo($("#manager"));
								
				$(data.areaUserList).each(function(){
					$("<option value='" + this.id + "' title=" + this.fullName + " >" + this.fullName + "</option>").appendTo($("#manager"));
				});
			}
		</script>
		<a:ajax successCallbackFunctions="yuanxiaoSuccessCallback"  urls="/crm/academys_academie_list" pluginCode="yuanxiao" isOnload="all"/>
		<a:ajax successCallbackFunctions="zhongxingSuccessCallback"  urls="/crm/all_branch_list" pluginCode="zhongxing" isOnload="all"/>
		<a:ajax successCallbackFunctions="piciSuccessCallback"  urls="/crm/gl_enroll_batch_list" pluginCode="pici" isOnload="all"/>
		<a:ajax parameters="{'basedicttype':BASEDICT_STYLE_STUDATASOURCE}" successCallbackFunctions="dataSourceSuccessCallback" pluginCode="shujulaiyuan" urls="/basesetting/basedict/list_base_dict_flag" isOnload="all"/>
		<a:ajax successCallbackFunctions="wayAndSourceCallback"  urls="/crm/student_way_list" pluginCode="laiyuan_tujing" isOnload="all"/>
		<a:ajax successCallbackFunctions="userSuccessCallback"  urls="/admin/user/list_user" pluginCode="user" isOnload="all"/>
		<a:ajax successCallbackFunctions="quYuCallback"  urls="/admin/areamanager/interface_search_area_manager_user" pluginCode="quyu" isOnload="all"/>
		
		
		<script type="text/javascript">
				function reportSuccessCallback(data){
					if(data.resultMap.quyuList!=null&&data.resultMap.quyuList.length!=0){
						//获取区域经理下的用户数量
						var theadStr="";
						for(var i=0;i<data.resultMap.quyuList.length;i++){
							//区域经理对象
							var quyuObject=data.resultMap.quyuList[i];
							for(var j=0;j<quyuObject.xuexiList.length;j++){
								//学习中心
								var xuexiObject=quyuObject.xuexiList[j];
								
								for(var k=0;k<xuexiObject.fuwuList.length;k++){
									//服务站对象
									var fuwuObject=xuexiObject.fuwuList[k];
									
									for(var l=0;l<fuwuObject.userList.length;l++){
										//用户
										var userObject=fuwuObject.userList[l];
										if(userObject!=null){
										theadStr+=
											"<tr><td align='center'>"+quyuObject.quyuName+"</td>"
										   +"<td align='center'>"+xuexiObject.xuexiName+"</td>"
										   +"<td align='center'>"+fuwuObject.fuwuName+"</td>"
										   +"<td align='center'>"+userObject.name+"</td>"
										   +"<td align='center'>"+userObject.userZhaoShengZhiBiao+"</td>"
										   +"<td align='center'>"+userObject.wangluobaomingCount+"</td>"
										   +"<td align='center'>"+userObject.wangluobaomingCountP+"</td>"
										   +"<td align='center'>"+userObject.hujiaozhongxinCount+"</td>"
										   +"<td align='center'>"+userObject.hujiaozhongxinCountP+"</td>"
										   +"<td align='center'>"+userObject.xuexizhongxinCount+"</td>"
										   +"<td align='center'>"+userObject.xuexizhongxinCountP+"</td>"
										   +"<td align='center'>"+userObject.lishishujuCount+"</td>"
										   +"<td align='center'>"+userObject.lishishujuCountP+"</td>"
										   +"<td align='center' style='background:yellow;'>"+userObject.hejiCount+"</td>"
										   +"<td align='center' style='background:yellow;'>"+userObject.hejiCountP+"</td></tr>";
										   
										
									  }
								   }
										
									//if(fuwuObject.userList.length!=0){
										
										theadStr+=
											"<tr><td align='center'>"+quyuObject.quyuName+"</td>"
										   +"<td align='center'>"+xuexiObject.xuexiName+"</td>"
										   +"<td align='center'>"+fuwuObject.fuwuName+"</td>"
										   +"<td align='center' style='background:gray;'>小计</td>"
										   +"<td align='center' style='background:gray;'>"+fuwuObject.fuwuzhanHeJiMap.userZhaoShengZhiBiaoSum+"</td>"
										   +"<td align='center' style='background:gray;'>"+fuwuObject.fuwuzhanHeJiMap.wangluobaomingCountSum+"</td>"
										   +"<td align='center' style='background:gray;'>"+fuwuObject.fuwuzhanHeJiMap.wangluobaomingCountPSum+"</td>"
										   +"<td align='center' style='background:gray;'>"+fuwuObject.fuwuzhanHeJiMap.hujiaozhongxingCountSum+"</td>"
										   +"<td align='center' style='background:gray;'>"+fuwuObject.fuwuzhanHeJiMap.hujiaozhongxingCountPSum+"</td>"
										   +"<td align='center' style='background:gray;'>"+fuwuObject.fuwuzhanHeJiMap.xuexizhongxinCountSum+"</td>"
										   +"<td align='center' style='background:gray;'>"+fuwuObject.fuwuzhanHeJiMap.xuexizhongxinCountPSum+"</td>"
										   +"<td align='center' style='background:gray;'>"+fuwuObject.fuwuzhanHeJiMap.lishishujuCountSum+"</td>"
										   +"<td align='center' style='background:gray;'>"+fuwuObject.fuwuzhanHeJiMap.lishishujuCountPSum+"</td>"
										   +"<td align='center' style='background:gray;'>"+fuwuObject.fuwuzhanHeJiMap.hejiCountSum+"</td>"
										   +"<td align='center' style='background:gray;'>"+fuwuObject.fuwuzhanHeJiMap.hejiCountPSum+"</td></tr>";
									
									//}
									
									
								}
								
								theadStr+=
									"<tr style='background:#CCFFFF;'><td align='center'>"+quyuObject.quyuName+"</td>"
								   +"<td align='center' colspan='3'>合计</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.x_userZhaoShengZhiBiaoSum+"  </td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.x_wangluobaomingCountSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.x_wangluobaomingCountPSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.x_hujiaozhongxingCountSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.x_hujiaozhongxingCountPSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.x_xuexizhongxinCountSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.x_xuexizhongxinCountPSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.x_lishishujuCountSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.x_lishishujuCountPSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.x_hejiCountSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.x_hejiCountPSum+"</td></tr>";
								   
								 
								
							}
							theadStr+=
								"<tr style='background:#FFCC66;'>"
								   +"<td align='center' colspan='4'>合计</td>"
								   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.z_x_userZhaoShengZhiBiaoSum+"  </td>"
								   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.z_x_wangluobaomingCountSum+"</td>"
								   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.z_x_wangluobaomingCountPSum+"</td>"
								   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.z_x_hujiaozhongxingCountSum+"</td>"
								   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.z_x_hujiaozhongxingCountPSum+"</td>"
								   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.z_x_xuexizhongxinCountSum+"</td>"
								   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.z_x_xuexizhongxinCountPSum+"</td>"							   
								   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.z_x_lishishujuCountSum+"</td>"
								   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.z_x_lishishujuCountPSum+"</td>"
								   +"<td align='center' style=''>"+quyuObject.fuwuzhanHeJiMap.z_x_hejiCountSum+"</td>"
								   +"<td align='center' style=''>"+quyuObject.fuwuzhanHeJiMap.z_x_hejiCountSumP+"</td></tr>";
						}
						
						theadStr+=
								"<tr style='background:yellow;'>"
								   +"<td align='center' colspan='4'>总合计</td>"
								   +"<td align='center'>"+data.resultMap.zhubiaoSum+"  </td>"
								   +"<td align='center'>"+data.resultMap.wangluobaomingSum+"</td>"
								   +"<td align='center'>"+data.resultMap.wangluobaomingSumP+"</td>"
								   +"<td align='center'>"+data.resultMap.hujiaozhongxinSum+"</td>"
								   +"<td align='center'>"+data.resultMap.hujiaozhongxinSumP+"</td>"
								   +"<td align='center'>"+data.resultMap.xuexizhongxinSum+"</td>"
								   +"<td align='center'>"+data.resultMap.xuexizhongxinSumP+"</td>"							   
								   +"<td align='center'>"+data.resultMap.lishishujuSum+"</td>"
								   +"<td align='center'>"+data.resultMap.lishishujuSumP+"</td>"
								   +"<td align='center' style='background:yellow;'>"+data.resultMap.heji+"</td>"
								   +"<td align='center' style='background:yellow;'>&nbsp;</td></tr>";
					
						
						$("#tongjibiao >tbody").html(theadStr);
						_w_table_rowspan("#tongjibiao",1);
						_w_table_rowspan("#tongjibiao",2);
						_w_table_rowspan("#tongjibiao",3);
						//$("#tongjibiao >tbody").append(zonghejiStr);
						
					}
				}
				
		</script>
		<a:ajax successCallbackFunctions="reportSuccessCallback" pluginCode="student_source" urls="/report/report_student_data_source" parameters="$('#search').serializeObject()" />
	</head>
	<body>
		
		<!--头部层开始 -->
		<head:head title="数据来源统计表">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="search" action="<uu:url url="report/excel_export_student_data_source" />">
			<table style="height: 100%; width: 99.5%;" cellpadding="0"
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
								区域经理：
							</td>
							<td>
								<select class="txt_box_130" id="manager" name="mapParams.manager">
								</select>
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
								服务站：
							</td>
							<td>
								<select class="txt_box_130" id="branch_fuwu" name="mapParams.fuwu">
								</select>
							</td>
							<td class="lable_100">
								个人：
							</td>
							<td>
								<select class="txt_box_130" id="user" name="mapParams.user">
									
								</select>
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
							<tr align="center" style="background-color: #CCCCCC;">
								<td colspan="21" style="background-color: #F3F3F3;">
										<h2 id="settitle" style="text-align: center;padding:10px;">
											数据来源统计表
										</h2>
								</td>

							</tr>
							<tr align="center" style="background-color: #CCCCCC;">
								<td rowspan="2" style="background-color: #F3F3F3;">
									区域经理
								</td>
								<td rowspan="2" style="background-color: #F3F3F3;">
									学习中心
								</td>
								<td rowspan="2" style="background-color: #F3F3F3;">
									服务站
								</td>
								<td rowspan="2" style="background-color: #F3F3F3;">
									人员
								</td>
								<td rowspan="2" style="background-color: #F3F3F3;">
									招生指标
								</td>
								<td colspan="2" style="background-color: #F3F3F3;">
									网络报名
								</td>
								<td colspan="2" style="background-color: #F3F3F3;">
									呼叫中心
								</td>
								<td colspan="2" style="background-color: #F3F3F3;">
									学习中心
								</td>
								<td colspan="2" style="background-color: #F3F3F3;">
									历史数据
								</td>
								
								<td colspan="2" style="background-color: #F3F3F3;">
									合计
								</td>

							</tr>

							<tr align="center" style="background-color: #CCCCCC;">
							
								<td style="background-color: #F3F3F3;">
									人数
								</td>
								<td style="background-color: #F3F3F3;">
									比例
								</td>
								<td style="background-color: #F3F3F3;">
									人数
								</td>
								<td style="background-color: #F3F3F3;">
									比例
								</td>
								<td style="background-color: #F3F3F3;">
									人数
								</td>
								<td style="background-color: #F3F3F3;">
									比例
								</td>
								<td style="background-color: #F3F3F3;">
									人数
								</td>
								<td style="background-color: #F3F3F3;">
									比例
								</td>
								<td style="background-color: #F3F3F3;">
									人数
								</td>
								<td style="background-color: #F3F3F3;">
									比例
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