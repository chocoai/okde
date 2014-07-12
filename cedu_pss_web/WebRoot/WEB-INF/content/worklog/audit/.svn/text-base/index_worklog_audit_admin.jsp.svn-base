<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>日报评价</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui_org" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<jc:plugin name="loading" />
		<jc:plugin name="calendar" />
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
				searchpage();
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
						//父节点中心主任
							adminNewNode = [ {
								id : "z_"+-5,
								name : "全部中心主任",
								open : false,
								isParent : true
							} ];
						
						branchUserTreeObject.addNodes(null, adminNewNode);
						branchUserTreeObject.addNodes(null, newNode);
						
						$(branch.departments).each(function(){
							//部门对象
							var department=this;
								newNode = [ {
									id : "d_"+department.departmentId,
									name : department.departmentName,
									open : true,
									isParent : true
								} ];
							
								pnode = branchUserTreeObject.getNodeByParam("id", "x_"+-6);
								branchUserTreeObject.addNodes(pnode, newNode);
							$(department.users).each(function(){
								//用户对象
								var user=this;
								newNode = [ {
									id : "u_"+user.userId,
									name : user.userName,
									open : true
								} ];
								pnode = branchUserTreeObject.getNodeByParam("id", "d_"+department.departmentId);
								branchUserTreeObject.addNodes(pnode, newNode);
								
								if(user.job_level_id==USER_CENTER_ID){
									adminNewNode = [ {
										id : "u_"+user.userId,
										name : user.userName,
										open : false
									} ];
									adminPnode = branchUserTreeObject.getNodeByParam("id", "z_"+-5);
									branchUserTreeObject.addNodes(adminPnode, adminNewNode);
								}
								
								
							});
						});
						
					});
					//合并
					branchUserTreeObject.checkNode(branchUserTreeObject.getNodeByParam("id", "z_"+-5), true, true);
					branchUserTreeObject.expandNode(branchUserTreeObject.getNodeByParam("id", "x_"+-6), false);
					//branchUserTreeObject.expandAll(false);
					//treeObj.expandNode(branchUserTreeObject.getNodeByParam("id", "x_"+-6), true, false, true);
					
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
		<a:ajax successCallbackFunctions="org_tree_success_callback" pluginCode="001" urls="worklog/org_tree" isOnload="all" />
		<script type="text/javascript">
			function getDate(date) {
				return date.substring(0, 10);
			}
		
			function todo(pingFen,showPingFen,records, status, id) {
				var str = "";
				//显示评分
				if(showPingFen){
					str += '&nbsp;<a id="a_' + id
						+ '" href="javascript:showAuditRecord(' + id
						+ ')" >评价记录(展开)</a><br/>';
				}
				//是否可以评分
				if(pingFen){
					str += '&nbsp;<a target="_blank" href="<s:url value="audit_worklog" />?id=' + id
						+ '" >评分</a><br/>';
				}
				
				str += '&nbsp;<a target="_blank" href="<s:url value="/worklog/view_worklog" />?id=' + id
					+ '" >查看</a><br/>';
				
				str += '&nbsp;<a href="#" onclick="refreshpage();" >刷新</a><br/>';
				
		
				if (records != null&&records.length!=0) {
					
					$(records).each(function() {
							str += "<tr style='display:none;' class='tr_record_"+id+"'><td colspan='5' style='padding-left:10px;background:#FFFFE0;'>审批人："
								+ (this.auditUser==null?"未知":this.auditUser.fullName)
								+ "<br/>评分："
								+ this.score
								+ "<br/>意见：<div style='padding-left:40px'>"
								+ this.content + "</div></td></tr>"
					});
				}
		
				return str;
			}
			//显示审批记录
			function showAuditRecord(id) {
				if ($(".tr_record_" + id).css('display') == 'table-row') {
					$(".tr_record_" + id).css({
						'display' : 'none'
					});
					$("#a_" + id).html("评价记录(展开)");
				} else {
					$(".tr_record_" + id).css({
						'display' : 'table-row'
					});
					$("#a_" + id).html("评价记录(收缩)");
				}
		
			}
			function contentValue(content){
				return '<div style="margin: 0px;text-align: left;border:0px red solid;padding:0px;width:550px;overflow:auto;border:0px red solid;float:left;">'+content+'</div>';
			}
			$(document).ready(function() {
				$("#starttime").val(getFirstDay());
				$("#endtime").val(new Date().pattern("yyyy-MM-dd"));
				//init  tree
				$.fn.zTree.init($("#branchUserTree"), setting, zNodes);
				branchUserTreeObject = $.fn.zTree.getZTreeObj("branchUserTree");
			});
			
		</script>
		

	</head>

	<body>
		<!--头部层开始 -->
		<head:head title="日报评价">

		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Search Begin-->

			<table width="100%" class="add_table" border="0" cellpadding="2"
				cellspacing="0">
				<tr>
					<td class="lable_100">
						开始日期：
					</td>
					<td>
						<input id="starttime" class="Wdate" type="text" value=""
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
							size="17">
					</td>
					<td class="lable_100">
						结束日期：
					</td>
					<td>
						<input id="endtime" class="Wdate" type="text" value="" size="17"
							onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})">
					</td>

					<td>
						
						<input type="button" value="查询" onclick="searchpage();"
							class="btn_black_61" />
						
					</td>
				</tr>
			</table>

			<!--Search End-->

			<table class="gv_table_2">
				<tr style="display: table-row;">
					<th style="width: 20px;">
						<img
							src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" />
					</th>
					<th style="text-align: left; font-weight: bold;">
						日报列表：
					</th>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td style="width: 12%" valign="top">
						<fieldset>
							<ul id="branchUserTree" class="ztree"></ul>
						</fieldset>
					</td>
					<td style="width: 88%" valign="top">
						<page:plugin pluginCode="page"
							isNumber="true"
							il8nName="admin" searchListActionpath="page_list_worklog_audit"
							searchCountActionpath="page_count_worklog_audit"
							columnsStr="createOn;createUser.fullName;content;#todo"
							customColumnValue="0,getDate(createOn);2,contentValue(content);3,todo(pingFen,showPingFen,records,status,id)"
							ontherOperatingWidth="80px"
							params="'worklog.userIds':getNodes(),'worklog.cuDepartmentId':cuDepartmentId,'worklog.createBy':userId,'worklog.statusIds':'1,2','starttime':$('#starttime').val(),'endtime':$('#endtime').val()"
							isZebraCrossing="true" 
							isonLoad="false"
							
							isOrder="false"
							/>
					</td>
				</tr>
			</table>
		</body:body>

	</body>
</html>
