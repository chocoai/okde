<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>新增考场信息</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		
		<script type="text/javascript">
	    </script>

  </head>
  
  <body>
   <form id="form1" action="update_classroom" method="post">
<div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>修改考场信息</a> </li>
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
		            <th style="width:20px;"><img src="<ui:img url="images/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">基本信息</th>
			  </tr>
			</table>
			<input type="hidden" name="id" id="id" value="${id}">
				<table class="add_table">  
				    <tr>
				      	<td  class="lable_150">教室名称：</td>
				        <td>
				        	<input type="text" class="txt_box_150"  id="classroomname" name="classroom.name" value="${classroom.name}" />
				        </td>
					</tr>
					<tr>	
						<td class="lable_150">数量：</td>
				        <td><input type="text" class="txt_box_150" id="classroomcapacity" name="classroom.capacity" value="${classroom.capacity}" />
				        </td>
				     </tr>
					 <tr>
					    <td class="lable_150">费用标准：</td>
						<td>
						<input type="text" class="txt_box_150" id="classroomfeeStandard" name="classroom.feeStandard" value="${classroom.feeStandard}"/>
						</td>
					 </tr>
					 <tr>
			    <td class="lable_100">费用状况：</td>
				<td>
					<input type="radio" name="classroom.feeType" class="classroom.feeType" checked="checked" value="0"/>场 
					<input type="radio" name="classroom.feeType" class="classroom.feeType" value="1"/> 天
				   </td>
			</tr>
					 <tr><td align="center" colspan="2">
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