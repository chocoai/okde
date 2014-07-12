<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>监考审批</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<jc:plugin name="loading" />
		<jc:plugin name="validator" />
		<a:ajax successCallbackFunctions="updatestatus_callback"
			parameters="{'id':invigilator_id,'status':invigilator_status};"
			urls="/examination/invigilator/update_invigilator_status"
			pluginCode="002" />
		<script type="text/javascript">
		function updatestatus(id,status)
		{
			invigilator_id=id;
			invigilator_status=status;
			ajax_002_1();
		}
		function updatestatus_callback(data)
		{
			if(data.results)
			{
				show('showDialog','修改成功',150,100);
				search123();
			}
			else
			{
				show('showDialog','修改失败',150,100);
			}	
		}
	function operatingTwo(id,status)
		{
			if(status==1)
			{
				return '<a href="javascript:updatestatus('+id+',3)">冻结</a> &nbsp; &nbsp; &nbsp;<a href="javascript:updatestatus('+id+',0)">解聘</a>'
			}
			if(status==0)
			{
				return '<a href="javascript:updatestatus('+id+',1)">聘用</a> &nbsp; &nbsp; &nbsp;<a href="javascript:updatestatus('+id+',2)">不聘用</a>'
			}
			if(status==3)
			{
				return '<a href="javascript:updatestatus('+id+',0)">解冻</a>'
			}
			if(status==2)
			{
				return '<a href="javascript:updatestatus('+id+',0)">待聘用</a>'
			}
			
		}
		function ap(op)
		{
			jQuery("#status").val(op);
			search123();
		}
		
		function showDegree(data){
		if(data==1){
		return '博士后';
		}
		if(data==2){
		return '博士';
		}
		if(data==3){
		return '硕士';
		}
		if(data==4){
		return '研究生';
		}
		if(data==5){
		return '本科';
		}
		}
		function replay(status)
		{
			if(status==0)			
				return '待聘用'		
			if(status==1)
				return '已聘用'
			if(status==2)
				return '未聘用'
			if(status==3)
				return '已冻结'
			
		}
		$(document).ready(function(){
				$("#menu>li>a").click(function(){
						$(this).attr("class","current");
						var selObj=this;
							$("#menu>li>a").each(function(){
									if(this!=selObj){
										$(this).attr("class","");
									}
							});
					});
			});
	</script>
	</head>
	<body>
		<div id="title_new">
			<div id="contitle">
				<ul id="tags">
					<li class="selectTag">
						<a>监考审批</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="block">
			<ul id="menu">
				<li>
					<a href="javascript:ap(0)" title="待聘用" class="current"}>待聘用</a>
				</li>
				<li>
					<a href="javascript:ap(1)" title="已聘用">已聘用</a>
				</li>
				<li>
					<a href="javascript:ap(2)" title="未聘用">未聘用</a>
				</li>
			</ul>
		</div>
		<div class="block">
			<div class="public_table_bg_766">
				<div class="tb_table_default_2">
					<input type="hidden" name="status" id="status" value="<%=Constants. AUDIT_STATUS_FALSE %>"/>
					<table class="add_table">
						<tr>
							<td class="lable_100">
								学习中心：
							</td>
							<td>
								<select name="branch" id="branch" class="txt_box_150"></select>
							</td>
							<td class="lable_100">
								姓名：
							</td>
							<td>
								<input type="text" name="name" id="name" class="txt_box_150" />
							</td>
							<td class="lable_100">
								工作单位:
							</td>
							<td>
								<input type="text" name="workUnit" id="workUnit"
									class="txt_box_150" />
							</td>
							<td>
								<input type="button" name="" id="" value="查询"
									class="btn_black_61" onclick="search123();"/>
							</td>
						</tr>
					</table>
					<table class="gv_table_2" id="resTable" border="0" cellpadding="0"
						cellspacing="0">
						<tbody>
							<page:plugin pluginCode="123" 
							   il8nName="examination"
								searchListActionpath="findByconditions_invigilator"
								searchCountActionpath="count_invigilator"
								columnsStr="name;degree;creatorname;workUnit;feeStandard;invigilationExperience;status;#operating"
								customColumnValue="1,showDegree(degree);6,replay(status);7,operatingTwo(id,status)"
								view="http,examination/invigilator/view_invigilator,id,id,_blank"
								pageSize="5" ontherOperatingWidth="80px"
								params="'workUnit':$('#workUnit').val(),'name':$('#name').val(),'status':$('#status').val()" />
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>
</html>
