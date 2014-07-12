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
		$(document).ready(function(){
		$("input[type=radio][name=examroom.isSignable][value=${examroom.isSignable}]").attr("checked",'checked');
		$("input[type=radio][name=examroom.isLongTerm][value=${examroom.isLongTerm}]").attr("checked",'checked');
		$("input[type=radio][name=examroom.hasInvigilator][value=${examroom.hasInvigilator}]").attr("checked",'checked');
		if($('.examroomisSignable:checked').val()==0){
		$("#readioxieyi").hide();}else{
		$("#readioxieyi").show();
		}
		if($('.examroomisLongTerm:checked').val()==0){
		$("#radiozuoyong").hide();
		}else{
		$("#radiozuoyong").show();
		}
		if($('.examroomhasInvigilator:checked').val()==0){
		$("#radioteacher").hide();
		}else{
		$("#radioteacher").show();
		}
	 });
	// if($('.examroomisLongTerm:checked').val()==0){}
	
	    </script>

  </head>
  <body>
 <form id="form1" action="update_examroom" method="post">
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
		            <th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">基本信息</th>
			  </tr>
			</table>
			<input type="hidden" name="examroom.id" value="${examroom.id}">
				<table class="add_table">  
				    <tr>
				      	<td  class="lable_150">考场名称：</td>
				        <td>
				        	<input type="text" class="txt_box_150"  id="examroomname" name="examroom.name" value="${examroom.name}" />
				        </td>
						
					</tr>
					<tr>	
						<td class="lable_150">考场地址：</td>
				        <td><input type="text" class="txt_box_150" id="examroomaddress" name="examroom.address" value="${examroom.address}" />
				        </td>
				     </tr>
					 <tr>
					    <td class="lable_150">联系人：</td>
						<td>
						<input type="text" class="txt_box_150" id="examroomlinkman" name="examroom.linkman" value="${examroom.linkman}"/>
						</td>
					 </tr>
					 <tr>
					    <td class="lable_150">电话：</td>
						<td>
						<input id="examroom.phone" name="examroom.phone" class="txt_box_150" type="text" value="${examroom.phone}"/>
						</td>
					 </tr>
					 <tr>
					    <td class="lable_150">Email：</td>
						<td>
						<input id="examroom.email" class="txt_box_150" name="examroom.email" type="text" value="${examroom.email}" />
						</td>
					 </tr>
				</table>	
		 <table class="gv_table_2">
		    	<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">其他信息</th>
			   </tr>
		</table>
			 <table class="add_table">
			         <tr>
					  <td class="lable_150">能否签订协议：</td>
						<td>
						  <input type="radio"  name="examroom.isSignable" class="examroomisSignable" value="0" >是&nbsp;&nbsp;
						  <input type="radio"  name="examroom.isSignable" class="examroomisSignable" value="1" > 否
						</td>
					 </tr>
					  <tr  id="readioxieyi" style="display:none;">
						<td class="lable_150" align="center">原因：</td>
						<td><textarea class="txt_box_400" cols="8" rows="6" id="examroomsignableSeason" name="examroom.signableSeason">${examroom.signableSeason}</textarea></td>
					 </tr>
					 <tr>
					    <td class="lable_150">能否长期租用：</td>
						<td>
						 <input type="radio"  name="examroom.isLongTerm" class="examroomisLongTerm" value="0" >是&nbsp;&nbsp;
						 <input type="radio"  name="examroom.isLongTerm" class="examroomisLongTerm" value="1"> 否
						</td>
						</tr>
 
						<tr id="radiozuoyong" style="display:none;">
							<td class="lable_100" align="center">原因：</td>
							<td><textarea class="txt_box_400" cols="8" rows="6" id="examroomlongTermSeason" name="examroom.longTermSeason">${examroom.longTermSeason}</textarea></td>
					     </tr>
					 <tr>
					    <td class="lable_150">能否提供监考老师：</td>
						<td>
						 <input type="radio"  name="examroom.hasInvigilator" class="examroomhasInvigilator" value="0">是&nbsp;&nbsp;
						 <input type="radio"  name="examroom.hasInvigilator" class="examroomhasInvigilator" value="1"> 否
						</td>
					 </tr>
					 <tr id="radioteacher" style="display:none">
						<td class="lable_100" align="center">原因：</td>
						<td><textarea class="txt_box_400" cols="8" rows="6" id="examroomhasInvigilatorSeason" name="examroom.hasInvigilatorSeason">${examroom.hasInvigilatorSeason}</textarea></td>
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

