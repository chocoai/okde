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
	</head>
	<script type="text/javascript">
			function piciSuccessCallback1(data){
				$("#batch1").html("");
               	//$("<option value='" + -2 + "'>全部</option>").appendTo($("#batch"));
				$(data.globalEnrollBatchs).each(function(){
						$("<option value='" + this.id + "'>" + this.batch + "</option>").appendTo($("#batch1"));
					
				});
			}
	</script>
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
			function piciSuccessCallback2(data){
				$("#batch2").html("");
               	$("<option value='" + -2 + "'>全部</option>").appendTo($("#batch2"));
				$(data.globalEnrollBatchs).each(function(){
						$("<option value='" + this.id + "'>" + this.batch + "</option>").appendTo($("#batch2"));
					
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
			function userSuccessCallback(data){
				$("#user").html("");
	            $("<option value='" + -2 + "'>全部</option>").appendTo($("#user"));
								
				$(data.ulist).each(function(){
					if(this.isManager==0){
						$("<option value='" + this.id + "' title=" + this.fullName + " >" + this.fullName + "</option>").appendTo($("#user"));
					}
					//else{
					//	$("<option value='" + this.id + "' title=" + this.fullName + " >" + this.fullName + "</option>").appendTo($("#manager"));
					//}
				});
			}
			
		</script>

	<a:ajax successCallbackFunctions="piciSuccessCallback1"
		urls="/crm/gl_enroll_batch_list" pluginCode="pici1" isOnload="all" />
	<a:ajax successCallbackFunctions="yuanxiaoSuccessCallback"
		urls="/crm/academys_academie_list" pluginCode="yuanxiao"
		isOnload="all" />
	<a:ajax successCallbackFunctions="zhongxingSuccessCallback"
		urls="/crm/all_branch_list" pluginCode="zhongxing" isOnload="all" />
	<a:ajax successCallbackFunctions="piciSuccessCallback2"
		urls="/crm/gl_enroll_batch_list" pluginCode="pici2" isOnload="all" />
	<a:ajax parameters="{'basedicttype':BASEDICT_STYLE_STUDATASOURCE}"
		successCallbackFunctions="dataSourceSuccessCallback"
		pluginCode="shujulaiyuan"
		urls="/basesetting/basedict/list_base_dict_flag" isOnload="all" />
	<a:ajax successCallbackFunctions="wayAndSourceCallback"
		urls="/crm/student_way_list" pluginCode="laiyuan_tujing"
		isOnload="all" />
	<a:ajax successCallbackFunctions="quYuCallback"
		urls="/admin/areamanager/interface_search_area_manager_user"
		pluginCode="quyu" isOnload="all" />
	<a:ajax successCallbackFunctions="userSuccessCallback"
		urls="/admin/user/list_user" pluginCode="user" isOnload="all" />
	<script type="text/javascript">
				function reportSuccessCallback(data){
					if(data.reportList!=null&&data.reportList.length!=0){
						//获取区域经理下的用户数量
						var theadStr="";
						for(var i=0;i<data.reportList.length;i++){
							//区域经理对象
							var quyuObject=data.reportList[i];
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
											theadStr+="<tr>"
											   +"<td align='center'>"+(j+1)+"</td>"
											   +"<td align='center'>"+xuexiObject.xuexiName+"</td>"
											   +"<td align='center'>"+fuwuObject.fuwuName+"</td>"
											   +"<td align='center'>"+userObject.name+"</td>"
											   +"<td align='center'>"+userObject.quanmianzhibiaoCount+"</td>"
											   +"<td align='center'>"+userObject.benpiciZhiBiao+"</td>"
											   +"<td align='center'>"+userObject.beipicileijizhaoshengCount+"</td>"
											   +"<td align='center'>"+userObject.dangtiangengzongxueshengCount+"</td>"
											   +"<td align='center'>"+userObject.shezhaoCount+"</td>"
											   +"<td align='center'>"+userObject.qudaoCount+"</td>"
											   +"<td align='center'>"+userObject.dakehuCount+"</td>"
											   +"<td align='center'>"+userObject.laodaixinCount+"</td>"
											   +"<td align='center'>"+userObject.laoshengxuduCount+"</td>"
											   +"<td align='center'>"+userObject.jiamengCount+"</td>"
											   +"<td align='center'>"+userObject.gongjianCount+"</td>"
											   +"<td align='center'>"+""+"</td>"
											   +"<td align='center'>"+""+"</td>"
											   +"<td align='center'>"+userObject.dang_tian_zhu_yao_gong_zuo+"</td>"
											   +"<td align='center'>"+userObject.ling_dao_ping_jia+"</td>"
											   +"<td align='center'>"+userObject.ling_dao_ping_fen+"</td></tr>";
											   
									    }
								   }
								}
							}
						}
						
						
						$("#tongjibiao >tbody").html(theadStr);
						_w_table_rowspan("#tongjibiao",1);
						_w_table_rowspan("#tongjibiao",2);
						_w_table_rowspan("#tongjibiao",3);
						//$("#tongjibiao >tbody").append(zonghejiStr);
						
					}
				}
		</script>
	<a:ajax successCallbackFunctions="reportSuccessCallback"
		pluginCode="daily" urls="/report/report_daily"
		parameters="$('#search').serializeObject()" />

	<script type="text/javascript">
			function submitReportSearch(){
				if($("#date").val()==null||$("#date").val()==""){
					alert("日期不能为空");
					return ;
				}
				ajax_daily_1();
			}
			$(document).ready(function(){
				$("#date").val(new Date().pattern("yyyy-MM-dd"));
			});
		</script>
	<body>
		<!--头部层开始 -->
		<head:head title="日报统计">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="search"
				action="<uu:url url="report/excel_export_individual_daily" />">
				<table width="100%" class="add_table" border="0" cellpadding="1"
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
							<select class="txt_box_130" id="batch2" name="mapParams.batch2">

							</select>
						</td>

						<td class="lable_100">
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
							数据来源：
						</td>
						<td>
							<select class="txt_box_130" id="studentDataSource"
								name="mapParams.studentDataSource">

							</select>
						</td>
						<td class="lable_100">
							当前批次：
						</td>
						<td style="width: 150px;">
							<select class="txt_box_130" id="batch1" name="mapParams.batch1"></select>
						</td>
						<td class="lable_100">
							日期：
						</td>
						<td>
							<input id="date" class="Wdate" type="text" name="dateParams.date"
								onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
								value="" size="17">
						</td>
					</tr>
					<tr>
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
									<input type="hidden" name="mapParams.branch"
									value="${branch.id }" />
							</c:if>
						</td>
						<td class="lable_100">
							服务站：
						</td>
						<td>
							<select class="txt_box_130" id="branch_fuwu"
								name="mapParams.fuwu">
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
							<input class="btn_black_61" type="button"
								onclick="submitReportSearch();" value="查询" />
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
					<tr align="center" style="background-color: #CCCCCC;">
						<td align="center" style="background-color: #F3F3F3;" colspan="20">
							<h2 id="settitle" style="text-align: center; padding: 10px;">
								日报统计
							</h2>
						</td>
					</tr>

					<tr align="center">
						<td align="center" style="background-color: #F3F3F3;" rowspan="2">
							序号
						</td>
						<td align="center" style="background-color: #F3F3F3;" rowspan="2">
							学习中心
						</td>
						<td align="center" style="background-color: #F3F3F3;" rowspan="2">
							服务站
						</td>
						<td align="center" style="background-color: #F3F3F3;" rowspan="2">
							姓名
						</td>

						<td align="center" style="background-color: #F3F3F3;" colspan="3">
							综合数据
						</td>
						<td align="center" style="background-color: #F3F3F3;" colspan="11">
							当日情况
						</td>
						<td align="center" style="background-color: #F3F3F3;" colspan="2">
							评价情况
						</td>

					</tr>

					<tr>
						<td align="center" style="background-color: #F3F3F3;">
							全年指标
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							本批次指标
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							本批次累计招生
						</td>
						<td align="center" style="background-color: #F3F3F3; width: 50px;">
							跟踪数量
						</td>

						<td align="center" style="background-color: #F3F3F3;" colspan="7">
							新增报名人数
						</td>
						<td align="center" style="background-color: #F3F3F3;" colspan="2">
							当天缴费金额
						</td>
						<td align="center" style="background-color: #B8860B;">
							当前主要工作
						</td>
						<td align="center" style="background-color: #B8860B;">
							当日领导评价
						</td>
						<td align="center" style="background-color: #B8860B;">
							当日领导评分
						</td>
					</tr>
					<tr>
						<td align="center" style="background-color: #F3F3F3;"></td>
						<td align="center" style="background-color: #F3F3F3;"></td>
						<td align="center" style="background-color: #F3F3F3;"></td>
						<td align="center" style="background-color: #F3F3F3;"></td>
						<td align="center" style="background-color: #F3F3F3;"></td>
						<td align="center" style="background-color: #F3F3F3;"></td>
						<td align="center" style="background-color: #F3F3F3;"></td>
						<td align="center" style="background-color: #F3F3F3;">

						</td>
						<td align="center" style="background-color: #F3F3F3;">
							社招
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							渠道
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							大客户
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							老带新
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							老生续读
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							加盟
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							共建
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							当前批次
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							老批次
						</td>
						<td align="center" style="background-color: #B8860B;"></td>
						<td align="center" style="background-color: #B8860B;"></td>
						<td align="center" style="background-color: #B8860B;"></td>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
		</body:body>
	</body>
</html>