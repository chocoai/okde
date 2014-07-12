<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<jc:plugin name="jquery" />
		<jc:plugin name="default" />
		<jc:plugin name="loading" />
		<jc:plugin name="tree" />
		<jc:plugin name="calendar" />
		<script type="text/javascript">
			function tree_callback(data)
			{
				zTree1=$.fn.zTree.getZTreeObj("treeDemo");
				var lists="";
				if(null!=data.trslist)
				{
					$.each(data.trslist, function()
			    	{	
			    		//添加一级途径	
			    		if(0==this.parentNode)
			    		{  
			    			newNode = [ {id:"_s_"+this.id,name:this.name,open:true,isParent:true}];
							zTree1.addNodes(null, newNode);
						}	     		    	
			    	});
			    	$.each(data.trslist, function()
			    	{		
			    		if(0<this.parentNode)
			    		{  
			    			//添加二级途径
					    	newNode = [ {id:"_s_"+this.id,name:this.name,open:false}];
		     		    	pnode = zTree1.getNodeByParam("id","_s_"+this.parentNode);
							zTree1.addNodes(pnode, newNode);
						}     		    	
			    	});
				}
			}
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
					}else{
					}
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
					if(data.reportList!=null&&data.reportList.length!=0){
						//获取区域经理下的用户数量
						var theadStr="";
						var usersum = 0;
						var usersumP = 0.00;
					
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
										theadStr+=
											"<tr><td align='center'>"+quyuObject.quyuName+"</td>"
										   +"<td align='center'>"+xuexiObject.xuexiName+"</td>"
										   +"<td align='center'>"+fuwuObject.fuwuName+"</td>"
										   +"<td align='center'>"+userObject.name+"</td>";
//											   <c:forEach items="${enrollmentWaysMapPingYin }" var="enrollmentWayParent">
//													<c:forEach items="${enrollmentWayParent.value }" var="enrollmentWayChild">
//														+"<td align='center'>"+userObject.${enrollmentWayChild.name}_count+"</td>"
//														+"<td align='center'>"+userObject.${enrollmentWayChild.name}_percentage+"</td>"
//													</c:forEach>
//												</c:forEach>
												<c:forEach items="${enrollmentWaysMapPingYin }" var="enrollmentWayParent">
													usersum = 0;
													usersumP = 0.00;
													<c:forEach items="${enrollmentWayParent.value }" var="enrollmentWayChild">
														var tempcount = userObject.${enrollmentWayChild.name}_count;
														if(!isNaN(tempcount)){
															usersum += parseInt(tempcount);
														}
														var tempcountP = userObject.${enrollmentWayChild.name}_percentage;
														if(tempcountP.indexOf("%")>0){
															tempcountP = tempcountP.substr(0,tempcountP.length-1);
															if(!isNaN(tempcountP)){
																usersumP += parseFloat(tempcountP);
															}
														}
													</c:forEach>
													theadStr+="<td align='center'>"+usersum+"</td>";
													theadStr+="<td align='center'>"+usersumP.toFixed(2)+"%</td>";
												</c:forEach>
										  theadStr+="<td align='center'>"+userObject.count+"</td><td align='center'>"+userObject.countP+"</td></tr>";
										   
									
									  }
								   }
										
									//if(fuwuObject.userList.length!=0){
										theadStr+=
											"<tr style='background:gray;'><td align='center'>"+quyuObject.quyuName+"</td>"
										   +"<td align='center'>"+xuexiObject.xuexiName+"</td>"
										   +"<td align='center'>"+fuwuObject.fuwuName+"</td>"
										   +"<td align='center'>小计</td>";
//										   		<c:forEach items="${enrollmentWaysMapPingYin }" var="enrollmentWayParent">
//													<c:forEach items="${enrollmentWayParent.value }" var="enrollmentWayChild">
//														+"<td align='center'>"+fuwuObject.${enrollmentWayChild.name}_fuwu_count+"</td>"
//														+"<td align='center'>"+fuwuObject.${enrollmentWayChild.name}_fuwu_percentage+"</td>"
//													</c:forEach>
//												</c:forEach>
												<c:forEach items="${enrollmentWaysMapPingYin }" var="enrollmentWayParent">
													usersum = 0;
													usersumP = 0.00;
													<c:forEach items="${enrollmentWayParent.value }" var="enrollmentWayChild">
														var tempcount = fuwuObject.${enrollmentWayChild.name}_fuwu_count;
														if(!isNaN(tempcount)){
															usersum += parseInt(tempcount);
														}
														var tempcountP = fuwuObject.${enrollmentWayChild.name}_fuwu_percentage;
														if(tempcountP.indexOf("%")>0){
															tempcountP = tempcountP.substr(0,tempcountP.length-1);
															if(!isNaN(tempcountP)){
																usersumP += parseFloat(tempcountP);
															}
														}
													</c:forEach>
													theadStr+="<td align='center'>"+usersum+"</td>";
													theadStr+="<td align='center'>"+usersumP.toFixed(2)+"%</td>";
												</c:forEach>
										  theadStr+="<td align='center'>"+fuwuObject.count+"</td><td align='center'>"+fuwuObject.countP+"</td></tr>";
									//}
									
								}
								
								
								theadStr+=
									"<tr style='background:#CCFFFF;'><td align='center'>"+quyuObject.quyuName+"</td>"
								   +"<td align='center' colspan='3'>合计</td>";
//								   				<c:forEach items="${enrollmentWaysMapPingYin }" var="enrollmentWayParent">
//													<c:forEach items="${enrollmentWayParent.value }" var="enrollmentWayChild">
//														+"<td align='center'>"+xuexiObject.${enrollmentWayChild.name}_branch_count+"</td>"
//														+"<td align='center'>"+xuexiObject.${enrollmentWayChild.name}_branch_percentage+"</td>"
//													</c:forEach>
//												</c:forEach>
												<c:forEach items="${enrollmentWaysMapPingYin }" var="enrollmentWayParent">
													usersum = 0;
													usersumP = 0.00;
													<c:forEach items="${enrollmentWayParent.value }" var="enrollmentWayChild">
														var tempcount = xuexiObject.${enrollmentWayChild.name}_branch_count;
														if(!isNaN(tempcount)){
															usersum += parseInt(tempcount);
														}
														var tempcountP = xuexiObject.${enrollmentWayChild.name}_branch_percentage;
														if(tempcountP.indexOf("%")>0){
															tempcountP = tempcountP.substr(0,tempcountP.length-1);
															if(!isNaN(tempcountP)){
																usersumP += parseFloat(tempcountP);
															}
														}
													</c:forEach>
													theadStr+="<td align='center'>"+usersum+"</td>";
													theadStr+="<td align='center'>"+usersumP.toFixed(2)+"%</td>";
												</c:forEach>
								  theadStr+="<td align='center'>"+xuexiObject.count+"</td><td align='center'>"+xuexiObject.countP+"</td></tr>";
	
								
							}
							
							theadStr+=
								"<tr style='background:yellow;'>"
								   +"<td align='center' colspan='4'>总合计</td>";
//								  				 <c:forEach items="${enrollmentWaysMapPingYin }" var="enrollmentWayParent">
//													<c:forEach items="${enrollmentWayParent.value }" var="enrollmentWayChild">
//														+"<td align='center'>"+quyuObject.${enrollmentWayChild.name}_zong_count+"</td>"
//														+"<td align='center'>"+quyuObject.${enrollmentWayChild.name}_zong_percentage+"</td>"
//													</c:forEach>
//												</c:forEach>
												<c:forEach items="${enrollmentWaysMapPingYin }" var="enrollmentWayParent">
													usersum = 0;
													usersumP = 0.00;
													<c:forEach items="${enrollmentWayParent.value }" var="enrollmentWayChild">
														var tempcount = quyuObject.${enrollmentWayChild.name}_zong_count;
														if(!isNaN(tempcount)){
															usersum += parseInt(tempcount);
														}
														var tempcountP = quyuObject.${enrollmentWayChild.name}_zong_percentage;
														if(tempcountP.indexOf("%")>0){
															tempcountP = tempcountP.substr(0,tempcountP.length-1);
															if(!isNaN(tempcountP)){
																usersumP += parseFloat(tempcountP);
															}
														}
													</c:forEach>
													theadStr+="<td align='center'>"+usersum+"</td>";
													theadStr+="<td align='center'>"+usersumP.toFixed(2)+"%</td>";
												</c:forEach>
										   theadStr+="<td align='center'>"+quyuObject.count+"</td><td align='center'>"+quyuObject.countP+"</td></tr>";
						}
						
						
						
						$("#tongjibiao >tbody").html(theadStr);
						_w_table_rowspan("#tongjibiao",1);
						_w_table_rowspan("#tongjibiao",2);
						_w_table_rowspan("#tongjibiao",3);
						//$("#tongjibiao >tbody").append(zonghejiStr);
						
					}
				}
		</script>
		<a:ajax successCallbackFunctions="reportSuccessCallback" pluginCode="student_way" urls="/report/report_student_enrollment_way" parameters="$('#search').serializeObject()" />
		<a:ajax pluginCode="001" successCallbackFunctions="tree_callback" urls="/basesetting/branchenrollmentway/tree_branch_enrollment_way" />
		<script type="text/javascript">		
			/*var setting = {
				view: {
					showLine: true,
					showIcon: true,
					showTitle: true
				},
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};
	
			var zNodes =[];		
			
			var newNode;
			var pnode;
			var zTree1;
			
			jQuery(document).ready(function(){
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				ajax_001_1();	
			});	
			function getTreeIds(){
				zTree1=$.fn.zTree.getZTreeObj("treeDemo");
				var nodes=zTree1.getCheckedNodes();
				var ids=",";
	
				b_id=jQuery('#branch_id').val();
				$.each(nodes, function()
				{
					if(0==this.id.indexOf("_s_"))
					{
						if(ids==","){
							ids=this.id.replace("_s_","");
						}else{
							ids+=(","+this.id.replace("_s_",""));
						}
						
					}
				});
				if(ids==","){
					ids="0";
				}
				$("#baseDictIds").val(ids);
				ajax_student_way_1();
			}	*/
		</script>
	</head>
	
	<body style="overflow: auto;">
		<!--头部层开始 -->
		<head:head title="市场途径统计报表">
		</head:head>
		<!--主体层开始 -->
		<body:body>
		<form id="search"  action="<uu:url url="report/excel_export_student_enrollment_way" />">
			<input type="hidden" name="strParams.baseDictIds"  value="0"/>
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
								<select class="txt_box_130" id="school" name="mapParams.school" >
									
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
							<td class="lable_100">
								
							</td>
							<td>
								
							</td>
							<td class="lable_100" >
								
							</td>
							<td>
								
							</td>
							<td class="lable_100">
								<input  class="btn_black_61" type="button" onclick="ajax_student_way_1();" value="查询" />
							</td>
							<td>
								<input class="btn_black_61" type="submit"  value="导出" />
							</td>
						</tr>
					</table>
				</form>
		<table width="100%" border="0">
			
				<tr>
					<!-- 
					<td style="width: 12%" valign="top">
						<fieldset>
							<ul id="treeDemo" class="ztree" style="display: none;"></ul>
						</fieldset>
					</td>
					 -->
					<td style="width: 100%" valign="top">
			<table id="tongjibiao" class="gv_table" border="0"
				style="width: 100%" cellpadding="0" cellspacing="0">
				<thead>
					<tr align="center" style="background-color: #CCCCCC;">
						<td colspan="1000" style="background-color: #F3F3F3;">
							<h2 id="settitle" style="text-align: center; padding: 10px;">
								学生市场途径统计报表
							</h2>
						</td>
					</tr>
					<tr align="center" style="background-color: #CCCCCC;">
						<td rowspan="3" style="background-color: #F3F3F3;">
							区域经理
						</td>
						<td rowspan="3" style="background-color: #F3F3F3;">
							学习中心
						</td>
						<td rowspan="3" style="background-color: #F3F3F3;">
							服务站
						</td>
						<td rowspan="3" style="background-color: #F3F3F3;width: 100px">
							人员
						</td>
						<c:forEach items="${enrollmentWaysMap }" var="enrollmentWayParent">
						<!-- 
							<td colspan="${fn:length(enrollmentWayParent.value)*2 }"
								style="background-color: #F3F3F3;">
								${enrollmentWayParent.key }
							</td>
						-->
							<!-- 取消市场途经细节显示 -->
							<td colspan="2" rowspan="2"
								style="background-color: #F3F3F3;">
								${enrollmentWayParent.key }
							</td>
						</c:forEach>

						<td colspan="2" rowspan="2" style="background-color: #F3F3F3;">
							合计
						</td>

					</tr>
					<tr align="center" style="background-color: #CCCCCC;">
					<!-- 
						<c:forEach items="${enrollmentWaysMap }" var="enrollmentWayParent">
							<c:forEach items="${enrollmentWayParent.value }"
								var="enrollmentWayChild">
								<td colspan="2" style="background-color: #F3F3F3;">
									${enrollmentWayChild.name }
								</td>
							</c:forEach>
						</c:forEach>
					-->
					</tr>
					<tr align="center" style="background-color: #CCCCCC;">
						<!-- 
						<c:forEach items="${enrollmentWaysMap }" var="enrollmentWayParent">
							<c:forEach items="${enrollmentWayParent.value }"
								var="enrollmentWayChild">
								<td style="background-color: #F3F3F3;">
									人数
								</td>
								<td style="background-color: #F3F3F3;">
									比例
								</td>
							</c:forEach>
						</c:forEach>
						-->
						<!-- 取消市场途经细节显示 -->
						<c:forEach items="${enrollmentWaysMap }" var="enrollmentWayParent">
							<td style="background-color: #F3F3F3;">
								人数
							</td>
							<td style="background-color: #F3F3F3;">
								比例
							</td>
						</c:forEach>
						
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
	</tr>
</table>
		</body:body>
	</body>
</html>