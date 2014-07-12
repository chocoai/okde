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
							
								theadStr+=
									"<tr style='background:white;'><td align='center'>"+quyuObject.quyuName+"</td>"
								   +"<td align='center'>"+xuexiObject.xuexiName+"</td>"
								   +"<td align='center'>"+xuexiObject.zhurenName+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.userZhaoShengZhiBiaoSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.cc_yuBaoMingCountSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.cc_wanChengCountSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.zz_baoMingCountSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.zz_wanChengCountSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.leiJiCountSum+"</td>"
								   +"<td align='center'>"+xuexiObject.fuwuzhanHeJiMap.leiJiCountSumP+"</td>"
								   +"</tr>";
							}
							
							theadStr+=
								"<tr style='background:#FFCC66;'>"
							   +"<td align='center' colspan='3'>合计</td>"
							   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.userZhaoShengZhiBiaoSum+"</td>"
							   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.cc_yuBaoMingCountSum+"</td>"
							   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.cc_wanChengCountSum+"</td>"
							   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.zz_baoMingCountSum+"</td>"
							   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.zz_wanChengCountSum+"</td>"
							   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.leiJiCountSum+"</td>"
							   +"<td align='center'>"+quyuObject.fuwuzhanHeJiMap.leiJiCountSumP+"</td>"
							   +"</tr>";
						}
						
						theadStr+=
								"<tr style='background:yellow;'>"
							   +"<td align='center' colspan='3'>总合计</td>"
							   +"<td align='center'> "+data.resultMap.zhiBiaoSum+"</td>"
							   +"<td align='center'> "+data.resultMap.cc_yuBaoMingSum+"</td>"
							   +"<td align='center'> "+data.resultMap.cc_wanChengSum+"</td>"
							   +"<td align='center'> "+data.resultMap.zz_baoMingSum+"</td>"
							   +"<td align='center'> "+data.resultMap.zz_wanChengSum+"</td>"
							   +"<td align='center'> "+data.resultMap.leiJiSum+"</td>"
							   +"<td align='center'> "+data.resultMap.leiJiSumP+"</td>"
					
						
						$("#tongjibiao >tbody").html(theadStr);
						_w_table_rowspan("#tongjibiao",0);
						_w_table_rowspan("#tongjibiao",1);
						_w_table_rowspan("#tongjibiao",2);
						//_w_table_rowspan("#tongjibiao",3);
						//$("#tongjibiao >tbody").append(zonghejiStr);
						
					}
				}
				
		</script>
		<a:ajax successCallbackFunctions="reportSuccessCallback" pluginCode="student_progress" urls="/report/report_branch_student_enrollment_progress" parameters="$('#search').serializeObject()" />
		
	</head>
	<body>
	
		<!--头部层开始 -->
		<head:head title="周例会招生进展表">
		</head:head>
		<!--主体层开始 -->
		<body:body >
			<form id="search" action="<uu:url url="report/excel_export_branch_student_enrollment_progress" />">

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
								<!-- 
								开始时间：
								-->
							</td>
							<td>
								<!-- 
								<input id="starttime" class="Wdate" type="text" name="dateParams.startDate" value="" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" size="17"/>
								-->
							</td>
							<td class="lable_100">
								<!-- 
								结束时间：
								-->
							</td>
							<td>
								<!-- 
								<input id="endtime" class="Wdate" type="text" name="dateParams.endDate" value="" size="17" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
								-->
							</td>
							<td class="lable_100">
							</td>
							<td>
								<input type="hidden" name="mapParams.fuwu" value="-2"/>
								<input type="hidden" name="mapParams.user" value="-2"/>
							</td>
							
						</tr>
						<tr>
							<td class="lable_100"></td>
							<td></td>
							<td class="lable_100"></td>
							<td></td>
							<td></td>
							<td></td>
							<td class="lable_100"></td>
							<td></td>
							<td class="lable_100">
								<input class="btn_black_61" type="button" onclick="ajax_student_progress_1();" value="查询" />
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
								<td colspan="10" style="background-color: #F3F3F3;">
									<h2 id="settitle" style="text-align: center;padding:10px;">
										周例会招生进展表
									</h2>
								</td>
							</tr>
							<tr align="center" style="background-color: #F3F3F3;">
								<td colspan="4" style="background-color: #F3F3F3;">
									中心信息
								</td>
								<td colspan="2" style="background-color: #F3F3F3;">
									CC推送
								</td>
								<td colspan="2" style="background-color: #F3F3F3;">
									自主招生
								</td>
								<td colspan="2" style="background-color: #F3F3F3;">
									进展情况
								</td>
							</tr>
							<tr align="center" style="background-color: #F3F3F3;">
								<td style="background-color: #F3F3F3;" width="50px">
									区域经理
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									学习中心
								</td>
								<td style="background-color: #F3F3F3;" width="80px">
									中心主任
								</td>
								<td  width="80px">
									招生人数指标
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									跟进中
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									报名完成
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									报名
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									报名完成
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									累计报名人数
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									完成率
								</td>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
		</body:body>
	</body>
</html>