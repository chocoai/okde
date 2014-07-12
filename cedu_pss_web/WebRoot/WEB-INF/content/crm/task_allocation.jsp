<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>任务分配</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 分页 -->
		<jc:plugin name="page" />
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<script type="text/javascript">
			function fei_pei_success_callback(data){
				//alert("分配成功！");
				$('#message_confirm').dialog("close");
				$('#fenpeixuesheng').dialog("close");
				refresh001();
			}
			var no_fei_pei_count=0;
			function no_fei_pei_success_callback(data){
				no_fei_pei_count=data.count;
				$("#no_fei_pei").html(data.count);
				jQuery("#stucountall").html("未分配人数：<font style='color:red'><b>"+data.count+"</b></font>");
			}
		</script>
		<a:ajax successCallbackFunctions="fei_pei_success_callback" parameters="$('#fenpeixuesheng_from').serializeObject()" pluginCode="001" urls="/crm/crm_task_random_distribute_student" />
		<a:ajax successCallbackFunctions="no_fei_pei_success_callback"  pluginCode="002" urls="/crm/crm_task_no_distribute_student" />
		
		<script type="text/javascript">
			//操作
			function operating(userId) {
				return "<a target='_blank' href='"
						+ WEB_PATH
						+ "/admin/user/view_user?id="
						+ userId
						+ "'><img src='"+WEB_IMAGES+"/images/icon_view.gif' title='查看'/></a>";
			}
			//跟进数
			function followUpAllCount(followUpAllCount) {
				return "<font color='red'>" + followUpAllCount + "</font>"
			}
			//已分配
			function assignedCountValue(assignedCount) {
				return "<font color='blue'>" + assignedCount + "</font>";
			}
			function createdTimeValue(createdTime) {
				return createdTime;
			}
			//分配学生
			function operatingFenPeiStudent(userId, fullName, assignedCount) {
				return "<a href='#' onclick='javascript:suiJiFenPeiStudent(" + userId+ ",\"" + fullName + "\"," + assignedCount + ")'>随机分配</a>"+"&nbsp;&nbsp;<a target='_blank' href='"
					+ WEB_PATH
					+ "/crm/specify_student?userId="
					+ userId
					+ "'>指定分配</a>"
			}
			//随机分配学生
			function suiJiFenPeiStudent(userId, fullName, assignedCount) {
				//查询未分配数量
				ajax_002_1();
				$("#userId").val(userId);
				$("#name").html(fullName);
				$("#yi_fei_pei").html(assignedCount);
				$('#fenpeixuesheng').dialog("open");
			}
			$(document).ready(function() {
				
				$('#fenpeixuesheng').dialog({
					autoOpen : false,
					modal : true,
					draggable : false,
					resizable : false,
					title : '随机分配学生',
					width : 300,
					height : 200,
					buttons : {
						'分配' : function() {
							$('#message_confirm').dialog({
								buttons : {
									'确认' : function() {
										var value = jQuery("#count").val();
										if((/^(\+|-)?\d+$/.test( value ))&&value>0 &&value<=no_fei_pei_count){
											ajax_001_1();
											return true;
										}else{
											alert("请输入0－"+no_fei_pei_count+"的正整数！");
											return false;
										}
										
										
									},
									'取消' : function() {
										$(this).dialog("close");
									}
								}
							});
							$('#message_confirm').dialog("open");
						},
						'取消' : function() {
							$(this).dialog("close");
						}
					}
				});
				$('#message_confirm').dialog({
					autoOpen : false,
					modal : true,
					draggable : false,
					resizable : false,
					title : '提示',
					width : 260,
					height : 125
		
				});
			});
			//统计未分配人数
			function countCallback001(data)
			{
				//查询未分配数量
				ajax_002_1();
			}
		</script>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="任务分配">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<page:plugin pluginCode="001" il8nName="crm"
				searchListActionpath="crm_task_list"
				searchCountActionpath="crm_task_count"
				columnsStr="#public.operating;fullName;assignedCount;yiyuanCount;baomingCount;followUpAllCount;followUpACount;followUpBCount;followUpCCount;followUpDCount;followUpECount;followUpFCount;#createdTime;#public.operating"
				customColumnValue="0,operating(userId);2,assignedCountValue(assignedCount);5,followUpAllCount(followUpAllCount);12,createdTimeValue(createdTime);13,operatingFenPeiStudent(userId,fullName,assignedCount)"
				pageSize="15" isNumber="false" 
				customToolbarID="stucountall"
				listCallback="countCallback"
			/>
			<div id="message_confirm" style="display: none">
				<div>
					确认执行该操作！
				</div>
			</div>

			<div id="fenpeixuesheng" style="display: none">
				<form id="fenpeixuesheng_from">
					<input type="hidden" name="userId" id="userId" value="0" />
					<table class="add_table">
						<tr>
							<td align="right">
								姓名：
							</td>
							<td>
								<font id="name"></font>
							</td>
						</tr>
						<tr>
							<td align="right">
								已分配学生数：
							</td>
							<td>
								<font id="yi_fei_pei">0</font>
							</td>
						</tr>
						<tr>
							<td align="right">
								未分配学生数：
							</td>
							<td>
								<font id="no_fei_pei">0</font>
							</td>
						</tr>
						<tr>
							<td align="right">
								需分配人数：
							</td>
							<td>
								<input type="text" class="txt_box_100" id="count" name="count" value="0" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</body:body>
	</body>

</html>