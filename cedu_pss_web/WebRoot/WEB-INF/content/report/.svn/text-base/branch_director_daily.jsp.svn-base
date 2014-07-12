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
		<jc:plugin name="tree" />
		<script type="text/javascript">
			var userId=0;
			var cuDepartmentId=0;//部门
			var setting = {
				view: {
					showLine: true,
					selectedMulti: false,
					dblClickExpand: false
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				check: {
					enable: true
				},
				callback: {
					onClick: onClick,
					onCheck: onCheck
				}
			};
		
			var zNodes = [];
		
			var newNode;
			var pnode;
			var branchUserTreeObject;
			
			var adminNewNode;
			var adminPnode;
			var branchUserTreeObject;
		
			function onClick(e, treeId, node) {
				branchUserTreeObject.checkAllNodes(false);
				branchUserTreeObject.checkNode(node, true, true);
			}
			function onCheck(e, treeId, treeNode) {
				submitReportSearch();
			}
			
			function org_tree_success_callback(data){
				if(data.treeList!=null){
					$(data.treeList).each(function(){
						//学习中心对象
						var branch=this;
							newNode = [ {
								id : "x_"+-6,
								name : "全部中心用户",
								open : true,
								isParent : true
							} ];
						<c:if test="${sessionScope.userTicket.orgId==1 }">
							//父节点中心主任
								adminNewNode = [ {
									id : "z_"+-5,
									name : "全部中心主任",
									open : false,
									isParent : true,
									collapse:false
								} ];
							//总部
						
							branchUserTreeObject.addNodes(null, adminNewNode);
						</c:if>
						
						//branchUserTreeObject.addNodes(null, newNode);
						
						
						$(branch.departments).each(function(){
							//部门对象
							var department=this;
								newNode = [ {
									id : "d_"+department.departmentId,
									name : department.departmentName,
									open : true,
									isParent : true
								} ];
							
								//pnode = branchUserTreeObject.getNodeByParam("id", "x_"+-6);
								//branchUserTreeObject.addNodes(pnode, newNode);
							$(department.users).each(function(){
								//用户对象
								var user=this;
								newNode = [ {
									id : "u_"+user.userId,
									name : user.userName,
									open : true
								} ];
								//pnode = branchUserTreeObject.getNodeByParam("id", "d_"+department.departmentId);
								//branchUserTreeObject.addNodes(pnode, newNode);
								
								if(user.job_level_id==USER_CENTER_ID){
									
									//总部
									<c:if test="${sessionScope.userTicket.orgId==1 }">
										adminNewNode = [ {
											id : "u_"+user.userId,
											name : user.userName,
											open : false
										} ];
										adminPnode = branchUserTreeObject.getNodeByParam("id", "z_"+-5);
										branchUserTreeObject.addNodes(adminPnode, adminNewNode);
									</c:if>
									
								}
								
								
							});
						});
						
					});
					//合并
					<c:if test="${sessionScope.userTicket.orgId==1 }">
						branchUserTreeObject.checkNode(branchUserTreeObject.getNodeByParam("id", "z_"+-5), true, true);
						branchUserTreeObject.expandNode(branchUserTreeObject.getNodeByParam("id", "x_"+-6), false);
					</c:if>
					<c:if test="${sessionScope.userTicket.orgId!=1 }">
						//branchUserTreeObject.expandAll(false);
						branchUserTreeObject.checkNode(branchUserTreeObject.getNodeByParam("id", "x_"+-6), true, true);
					</c:if>
					
					//加载列表
					//searchpage();
				}
			}
			//获取选中节点ID
			function getNodes(){
				var nodes=branchUserTreeObject.getCheckedNodes();
				var ids=",";
				$.each(nodes, function(){
					if(0==this.id.indexOf("u_")){
						if(ids==","){
							ids=this.id.replace("u_","");
						}else{
							ids+=(","+this.id.replace("u_",""));
						}
						
					}
				});
				if(ids==","){
					ids="0";
				}
				return ids;
			}
		</script>
		<a:ajax successCallbackFunctions="org_tree_success_callback" pluginCode="001" urls="worklog/org_tree" isOnload="" />
		<script type="text/javascript">
			
			function piciSuccessCallback(data){
				$("#batch").html("");
               	//$("<option value='" + -2 + "'>全部</option>").appendTo($("#batch"));
				$(data.globalEnrollBatchs).each(function(){
						$("<option value='" + this.id + "'>" + this.batch + "</option>").appendTo($("#batch"));
					
				});
				// 由于ajax延迟问题，所以先查询批次后再查询用户
				ajax_001_1();
			}
		</script>
		<a:ajax successCallbackFunctions="piciSuccessCallback"  urls="/crm/gl_enroll_batch_list" pluginCode="pici" isOnload="all"/>
		<script type="text/javascript">
			function branch_director_daily_success_callback(data){
				if(data.reportList!=null){
					var listStr="";
					$(data.reportList).each(function(){
						listStr+="<tr>";
						listStr+="<td align='center'>"+this.name+"</td>";
						listStr+="<td align='center' title='"+this.dang_tian_zhu_yao_gong_zuo+"'>"+this.dang_tian_zhu_yao_gong_zuo.toSubString(15)+"</td>";
						listStr+="<td align='center'>"+this.dang_tian_gen_zong_xue_sheng_count+"</td>";
						listStr+="<td align='center'>"+this.she_zhao_count+"</td>";
						listStr+="<td align='center'>"+this.qu_dao_count+"</td>";
						listStr+="<td align='center'>"+this.da_ke_hu_count+"</td>";
						listStr+="<td align='center'>"+this.lao_dai_xin_count+"</td>";
						listStr+="<td align='center'>"+this.lao_sheng_xu_du_count+"</td>";
						listStr+="<td align='center'>"+this.jia_meng_count+"</td>";
						listStr+="<td align='center'>"+this.gong_jian_count+"</td>";
						listStr+="<td align='center'>"+this.sum_count+"</td>";
						listStr+="<td align='center'>"+this.zhi_biao+"</td>";
						listStr+="<td align='center'>"+this.lei_ji+"</td>";
						listStr+="<td align='center'>"+this.zhi_biao_avg+"</td>";
						listStr+="<td align='center'>"+this.branch_dang_tian_gen_zong_xue_sheng_count+"</td>";
						listStr+="<td align='center'>"+this.branch_she_zhao_count+"</td>";
						listStr+="<td align='center'>"+this.branch_qu_dao_count+"</td>";
						listStr+="<td align='center'>"+this.branch_da_ke_hu_count+"</td>";
						listStr+="<td align='center'>"+this.branch_lao_dai_xin_count+"</td>";
						listStr+="<td align='center'>"+this.branch_lao_sheng_xu_du_count+"</td>";
						listStr+="<td align='center'>"+this.branch_jia_meng_count+"</td>";
						listStr+="<td align='center'>"+this.branch_gong_jian_count+"</td>";
						listStr+="<td align='center'>"+this.branch_sum_count+"</td>";
						listStr+="<td align='center'>"+this.branch_zhi_biao+"</td>";
						listStr+="<td align='center'>"+this.branch_lei_ji+"</td>";
						listStr+="<td align='center'>"+this.branch_zhi_biao_avg+"</td>";
						listStr+="</tr>";
						//alert(listStr);
					});
					$("#tongjibiao >tbody").html(listStr);
					$(".divTips").tip();
				}
			}
			function submitReportSearch(){
				if($("#date").val()==null||$("#date").val()==""){
					alert("日期不能为空");
					return ;
				}
				$("#userIds").val(getNodes());
				ajax_branch_director_daily_1();
			}
			$(document).ready(function(){
				$("#date").val(new Date().pattern("yyyy-MM-dd"));
				
				$.fn.zTree.init($("#branchUserTree"), setting, zNodes);
				branchUserTreeObject = $.fn.zTree.getZTreeObj("branchUserTree");
			});
		</script>
		<a:ajax parameters="$('#search').serializeObject()" successCallbackFunctions="branch_director_daily_success_callback" urls="/report/report_branch_director_daily" pluginCode="branch_director_daily"/>
		
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="日报表(按中心)">
		</head:head>
		<!--主体层开始 -->
		<body:body>
		<form id="search" action="<uu:url url="report/excel_export_branch_director_daily" />">
				<input type="hidden" value="0" id="userIds" name="strParams.userIds" />
						<table width="100%" class="add_table" border="0" cellpadding="1"
							cellspacing="0">
							
							<tr>
								<td class="lable_100">
									日期：
								</td>
								<td style="width:150px;">
									<input id="date" class="Wdate" type="text"
										name="dateParams.date"
										onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
										size="17"/>
								</td>
								<td class="lable_100">
									当前批次：
								</td>
								<td  colspan="3">
									<select class="txt_box_130" id="batch" name="mapParams.batch">
										
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
		<table width="100%" border="0">
				<tr>
					<td style="width: 12%" valign="top">
						<fieldset>
							<ul id="branchUserTree" class="ztree"></ul>
						</fieldset>
					</td>
					<td style="width: 88%" valign="top">
			<table id="tongjibiao" class="gv_table" border="0"
				style="width: 100%" cellpadding="0" cellspacing="0">
				<thead>
					<tr align="center" style="background-color: #CCCCCC;">
						<td align="center" style="background-color: #F3F3F3;" colspan="26">
							<h2 id="settitle" style="text-align: center; padding: 10px;">
								学习中心日报表
							</h2>
						</td>
					</tr>
					<tr align="center">
						<td align="center" style="background-color: #F3F3F3;">
						</td>
						<td align="center" style="background-color: #F3F3F3;">
						</td>
						<td align="center" style="background-color: #F3F3F3;" colspan="12">
							主任工作数据
						</td>
						<td align="center" style="background-color: #F3F3F3;" colspan="12">
							学习中心总体数据
						</td>
					</tr>
					<tr align="center">
						<td align="center" style="background-color: #F3F3F3;" rowspan="2"
							width="50px">
							姓名
						</td>
						<td align="center" style="background-color: #F3F3F3;" rowspan="2">
							当天主要工作
						</td>

						<td align="center" style="background-color: #F3F3F3;" rowspan="2"
							width="50px">
							当天跟踪学生数量
						</td>
						<td align="center" style="background-color: #F3F3F3;" colspan="8">
							新增报名人数
						</td>
						<td align="center" style="background-color: #F3F3F3;" colspan="3">
							指标情况
						</td>
						<td align="center" style="background-color: #F3F3F3;" rowspan="2"
							width="50px">
							当天跟踪学生数量
						</td>
						<td align="center" style="background-color: #F3F3F3;" colspan="8">
							新增报名人数
						</td>
						<td align="center" style="background-color: #F3F3F3;" colspan="3">
							指标情况
						</td>
					</tr>
					<tr align="center">
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
							当天招生人数合计
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							个人招生指标
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							个人累计招生人数
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							个人指标完成率
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
							当天招生人数合计
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							中心招生指标
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							中心累计招生人数
						</td>
						<td align="center" style="background-color: #F3F3F3;">
							中心指标完成率
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