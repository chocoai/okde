<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
			 <li class="selectTag"><a>考试计划详情</a> </li>
		</ul>
	</div>
	<div id="conmenu">
	 <img src="<ui:img url="images/icon_title_return.jpg"/>" />
	 <a href="#" onclick="history.go(-1);">返回</a>
	</div>

</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">考试计划信息</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
			    <tr>
					<td class="lable_150">学习中心：</td>
					<td>${examplan.branchname}</td>
				</tr>
				<tr>
					<td class="lable_150">考试计划：</td>
					<td>${examplan.plan}</td>
				</tr>
				<tr>

					<td class="lable_150">层次：</td>
					<td>${examplan.levelname}</td>
				</tr>
				<tr>
					<td class="lable_150">专业：</td>
					<td>${examplan.majorname}</td>
				</tr>

				<tr>
					<td class="lable_150">批次：</td>
					<td>${examplan.batchname}</td>
				</tr>
				<tr>
					<td class="lable_150">考试人数：</td>
					<td>${examplan.planCount}</td>

				</tr>
				<tr>
					<td class="lable_150">考试时间：</td>
					<td>${examplan.examBeginTime}<br/>${examplan.examEndTime}</td>
				</tr>
			</table>
  		</div>

  	</div>
  </div>
</body>
</html>

  
