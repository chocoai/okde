<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>巡考详细信息</title>
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
  </head>
  <body>
    <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>巡考详情</a> </li>
		</ul>
	</div>
	<div id="conmenu">
	 <img src="../images/icon_title_return.jpg" />
	 <a href="#" onclick="history.go(-1);">返回</a>
	</div>

</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">巡考信息</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
			   
				<tr>
					<td class="lable_150">考试批次：</td>

					<td>${examschedule.examBatchname}</td>
				</tr>
				<tr>
					<td class="lable_150">考点：</td>
					<td>${examschedule.examAreaname}</td>
				</tr>
				<tr>

					<td class="lable_150">巡考老师：</td>
					<td>${examschedule.inspector}</td>
				</tr>
				<tr>
					<td class="lable_150">计划费用：</td>
					<td>${examschedule.planedCost}</td>
				</tr>

				<tr>
					<td class="lable_150">实际费用：</td>
					<td>${examschedule.actualCost}</td>
				</tr>
				<tr>
					<td class="lable_150">评分：</td>
					<td>${examschedule.inspectorMark}</td>
				</tr>
			</table>
  		</div>
  	</div>
  </div>
</body>
</html>

  
