<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>修改巡考信息</title>
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
		<script type="text/javascript">
		$(document).ready(function(){
		//alert(${invigilator.certType});
	   			$('#examBatchId').attr("value",${examschedule.examBatchId});
	   			$('#examAreaId').attr("value",${examschedule.examAreaId});
				});
	</script>	
  </head>
  <body>
  <form id="myform" action="update_examschedule" method="post">
    <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>巡考修改</a> </li>
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
			<input type="hidden" name="examschedule.id" value="${examschedule.id}">
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
			<tr>
			<td class="lable_100">考试批次：</td>
		   <td><s:select list="%{exambatchlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="code" name="examschedule.examBatchId" id="examBatchId" cssClass="txt_box_130"/></td>
		  </tr><tr><td class="lable_100">考点:</td>
		   <td><s:select list="%{examarealist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="examschedule.examAreaId" id="examAreaId" cssClass="txt_box_130"/></td>
			   </tr> 
				<tr>

					<td class="lable_150">巡考老师：</td>
					<td><input  type="text" class="txt_box_150"  name="examschedule.inspector" id="examschedule.inspector" value="${examschedule.inspector}"/></td>
				</tr>
				<tr>
					<td class="lable_150">计划费用：</td>
					<td><input  type="text" class="txt_box_150"  name="examschedule.planedCost" id="examschedule.planedCost" value="${examschedule.planedCost}"/></td>
				</tr>

				<tr>
					<td class="lable_150">实际费用：</td>
					<td><input  type="text" class="txt_box_150"  name="examschedule.actualCost" id="examschedule.actualCost" value="${examschedule.actualCost}"/></td>
				</tr>
				<tr>
					<td class="lable_150">评分：</td>
					<td><input  type="text" class="txt_box_150"  name="examschedule.inspectorMark" id="examschedule.inspectorMark" value="${examschedule.inspectorMark}"/></td>
				</tr>
				<tr>
				<td align="center" colspan="2">
					 <input type="submit" name="" id="mysubmit" value="修改" class="btn_black_61"/>
				</td>
			 </tr>
			</table>
  		</div>
  	</div>
  </div>
  </form>
</body>
</html>

  
