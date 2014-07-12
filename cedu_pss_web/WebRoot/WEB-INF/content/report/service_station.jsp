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
								
								for(var k=0;k<xuexiObject.fuwuList.length;k++){
									//服务站对象
									var fuwuObject=xuexiObject.fuwuList[k];
									
									theadStr+=
										"<tr style='background:white;'><td align='center'>"+quyuObject.quyuName+"</td>"
									   +"<td align='center'>"+xuexiObject.xuexiName+"</td>"
									   +"<td align='center'>"+fuwuObject.fuwuName+"</td>"
									   +"<td align='center'>"+fuwuObject.zhurenName+"</td>"
									   
									   +"<td align='center'>"+fuwuObject.fuwuzhanHeJiMap.userZhaoShengZhiBiaoSum+"</td>"
									   
									   +"<td align='center'>"+fuwuObject.fuwuzhanHeJiMap.dateBaoMingCountSum+"</td>"
									   +"<td align='center' style='"+(fuwuObject.fuwuzhanHeJiMap.dateBaoMingCountSumSort>3||fuwuObject.fuwuzhanHeJiMap.dateBaoMingCountSumSort==0?"":"background:white;")+"'>"+(fuwuObject.fuwuzhanHeJiMap.dateBaoMingCountSumSort==0?"":fuwuObject.fuwuzhanHeJiMap.dateBaoMingCountSumSort)+"</td>"
									   +"<td align='center'>"+fuwuObject.fuwuzhanHeJiMap.leijiBaoMingCountSum+"</td>"
									   +"<td align='center' style='"+(fuwuObject.fuwuzhanHeJiMap.leijiBaoMingCountSumSort>3||fuwuObject.fuwuzhanHeJiMap.leijiBaoMingCountSumSort==0?"":"background:white;")+"'>"+(fuwuObject.fuwuzhanHeJiMap.leijiBaoMingCountSumSort==0?"":fuwuObject.fuwuzhanHeJiMap.leijiBaoMingCountSumSort)+"</td>"
									   +"<td align='center'>"+fuwuObject.fuwuzhanHeJiMap.leijiBaoMingCountPSum+"</td>"
									   +"<td align='center' style='"+(fuwuObject.fuwuzhanHeJiMap.leijiBaoMingCountPSumSort>3||fuwuObject.fuwuzhanHeJiMap.leijiBaoMingCountPSumSort==0?"":"background:white;")+"'>"+(fuwuObject.fuwuzhanHeJiMap.leijiBaoMingCountPSumSort==0?"":fuwuObject.fuwuzhanHeJiMap.leijiBaoMingCountPSumSort)+"</td>"
									   
									   +"<td align='center'>"+fuwuObject.fuwuzhanHeJiMap.leijiLuQuCountSum+"</td>"
									   +"<td align='center' style='"+(fuwuObject.fuwuzhanHeJiMap.dateLuQuCountSumSort>3||fuwuObject.fuwuzhanHeJiMap.dateLuQuCountSumSort==0?"":"background:white;")+"'>"+(fuwuObject.fuwuzhanHeJiMap.dateLuQuCountSumSort==0?"":fuwuObject.fuwuzhanHeJiMap.dateLuQuCountSumSort)+"</td>"
									   +"<td align='center'>"+fuwuObject.fuwuzhanHeJiMap.leijiLuQuCountSum+"</td>"
									   +"<td align='center' style='"+(fuwuObject.fuwuzhanHeJiMap.leijiLuQuCountSumSort>3||fuwuObject.fuwuzhanHeJiMap.leijiLuQuCountSumSort==0?"":"background:white;")+"'>"+(fuwuObject.fuwuzhanHeJiMap.leijiLuQuCountSumSort==0?"":fuwuObject.fuwuzhanHeJiMap.leijiLuQuCountSumSort)+"</td>"
									   +"<td align='center'>"+fuwuObject.fuwuzhanHeJiMap.leijiLuQuCountPSum+"</td>"
									   +"<td align='center' style='"+(fuwuObject.fuwuzhanHeJiMap.leijiLuQuCountPSumSort>3||fuwuObject.fuwuzhanHeJiMap.leijiLuQuCountPSumSort==0?"":"background:white;")+"'>"+(fuwuObject.fuwuzhanHeJiMap.leijiLuQuCountPSumSort==0?"":fuwuObject.fuwuzhanHeJiMap.leijiLuQuCountPSumSort)+"</td>"
									   
									   
									   +"<td align='center'>"+fuwuObject.fuwuzhanHeJiMap.dateJiaoFeiCountSum+"</td>"
									   +"<td align='center' style='"+(fuwuObject.fuwuzhanHeJiMap.dateJiaoFeiCountSumSort>3||fuwuObject.fuwuzhanHeJiMap.dateJiaoFeiCountSumSort==0?"":"background:white;")+"'>"+(fuwuObject.fuwuzhanHeJiMap.dateJiaoFeiCountSumSort==0?"":fuwuObject.fuwuzhanHeJiMap.dateJiaoFeiCountSumSort)+"</td>"
									   +"<td align='center'>"+fuwuObject.fuwuzhanHeJiMap.leijiJiaoFeiCountSum+"</td>"
									   +"<td align='center' style='"+(fuwuObject.fuwuzhanHeJiMap.leijiJiaoFeiCountSumSort>3||fuwuObject.fuwuzhanHeJiMap.leijiJiaoFeiCountSumSort==0?"":"background:white;")+"'>"+(fuwuObject.fuwuzhanHeJiMap.leijiJiaoFeiCountSumSort==0?"":fuwuObject.fuwuzhanHeJiMap.leijiJiaoFeiCountSumSort)+"</td>"
									   +"<td align='center'>"+fuwuObject.fuwuzhanHeJiMap.leijiJiaoFeiCountPSum+"</td>"
									   +"<td align='center' style='"+(fuwuObject.fuwuzhanHeJiMap.leijiJiaoFeiCountPSumSort>3||fuwuObject.fuwuzhanHeJiMap.leijiJiaoFeiCountPSumSort==0?"":"background:white;")+"'>"+(fuwuObject.fuwuzhanHeJiMap.leijiJiaoFeiCountPSumSort==0?"":fuwuObject.fuwuzhanHeJiMap.leijiJiaoFeiCountPSumSort)+"</td>";
	
								}
							}
   	
						}
						
						theadStr+=
								"<tr style='background:yellow;'>"
							   +"<td align='center' colspan='4'>总合计</td>"
							   +"<td align='center'> "+data.resultMap.zhibiaoSum+"</td>"
							   +"<td align='center'> "+data.resultMap.dateBaomingSum+"</td>"
							   +"<td align='center'> "+"&nbsp;"+"</td>"
							   +"<td align='center'> "+data.resultMap.leijiBaomingSum+"</td>"
							   +"<td align='center'> "+"&nbsp;"+"</td>"
							   +"<td align='center'> "+data.resultMap.leijiBaomingSumP+"</td>"
							   +"<td align='center'> "+"&nbsp;"+"</td>"
							    +"<td align='center'> "+data.resultMap.dateLuquSum+"</td>"
							   +"<td align='center'> "+"&nbsp;"+"</td>"
							   +"<td align='center'> "+data.resultMap.leijiLuquSum+"</td>"
							   +"<td align='center'> "+"&nbsp;"+"</td>"
							   +"<td align='center'> "+data.resultMap.leijiLuquSumP+"</td>"
							   +"<td align='center'> "+"&nbsp;"+"</td>"
							    +"<td align='center'> "+data.resultMap.dateJiaofeiSum+"</td>"
							   +"<td align='center'> "+"&nbsp;"+"</td>"
							   +"<td align='center'> "+data.resultMap.leijiJiaofeiSum+"</td>"
							   +"<td align='center'> "+"&nbsp;"+"</td>"
							   +"<td align='center'> "+data.resultMap.leijiJiaofeiSumP+"</td>"
							   +"<td align='center'> "+"&nbsp;"+"</td>";
					
						
						$("#tongjibiao >tbody").html(theadStr);
						_w_table_rowspan("#tongjibiao",1);
						_w_table_rowspan("#tongjibiao",2);
						_w_table_rowspan("#tongjibiao",3);
						//$("#tongjibiao >tbody").append(zonghejiStr);
						
					}
				}
				
		</script>
		<a:ajax successCallbackFunctions="reportSuccessCallback" pluginCode="student_source" urls="/report/report_service_station" parameters="$('#search').serializeObject()" />
		
	</head>
	<body>
	
		<!--头部层开始 -->
		<head:head title="周例会服务站招生统计表">
		</head:head>
		<!--主体层开始 -->
		<body:body >
			<form id="search" action="<uu:url url="report/excel_export_service_station" />">
			
					<input type="hidden" name="mapParams.searchType" value="3"/>
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
								
							</td>
							<td>
								<input type="hidden" name="mapParams.user" value="-2"/>
							</td>
							<td class="lable_100">
							</td>
							<td>
								
							</td>
							
						</tr>
						<tr>
							<td class="lable_100">
								开始时间：
							</td>
							<td>
								<input id="starttime" class="Wdate" type="text" name="dateParams.startDate" value="" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" size="17"/>
							</td>
							<td class="lable_100">
								结束时间：
							</td>
							<td>
								<input id="endtime" class="Wdate" type="text" name="dateParams.endDate" value="" size="17" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
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
											周例会服务站招生统计表
										</h2>
								</td>
							</tr>
							<tr align="center" style="background-color: #F3F3F3;">
								<td colspan="4" style="background-color: #F3F3F3;">
									显示内容
								</td>
								<td >
									招生指标
								</td>
								<td colspan="6" style="background-color: #F3F3F3;">
									新生报名情况
								</td>
								<td colspan="6" >
									新生录取情况
								</td>
								<td colspan="6" style="background-color: #F3F3F3;">
									新生缴费情况
								</td>
								
							</tr>

							<tr align="center" style="background-color: #F3F3F3;">
								<td style="background-color: #F3F3F3;" width="50px">
									区域经理
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									学习中心
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									服务站 
								</td>
								<td style="background-color: #F3F3F3;" width="80px">
									中心主任
								</td>
								<td  width="80px">
									招生人数指标
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									时间段内报名人数
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									时间段内报名排名
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									累计报名人数 
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									累计报名人数排名
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									累计完成率
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									累计完成率排名
								</td>
								<td  width="50px">
									时间段内录取人数
								</td>
								<td  width="50px">
									时间段内录取排名
								</td>
								<td  width="50px">
									累计录取人数
								</td>
								<td  width="50px">
									累计录取人数排名
								</td>
								<td  width="50px">
									累计完成率
								</td>
								<td  width="50px">
									累计完成率排名
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									时间段内缴费人数
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									时间段内缴费排名
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									累计缴费人数
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									累计缴费人数排名
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									累计完成率
								</td>
								<td style="background-color: #F3F3F3;" width="50px">
									累计完成率排名
								</td>
								
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
		</body:body>
		
	</body>
</html>