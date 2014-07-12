<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>增加监考老师</title>
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
		
		<script type="text/javascript">
		$(document).ready(function(){
		//alert(${invigilator.certType});
	   			$('#invigilatorcertType').attr("value",${invigilator.certType});
	   			$('#invigilatordegree').attr("value",${invigilator.degree});
	   			$('#invigilatorgender').attr("value",$(invigilator.gender));
				});
	</script>	
	</head>
	
	  <body>
  
<div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>添加监考老师信息</a> </li>
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
				 	<th style="text-align:left; font-weight:bold;">基本资料</th>
			  </tr>
		  
			</table>
			<form id="form" action="update_invigilator" method="post" enctype="multipart/form-data">
		    <input type="hidden" name="invigilator.id" value="${invigilator.id}" />
				<table class="add_table">  
				    <tr>
				      	<td class="lable_100">姓名:</td>
				        <td>
				        	<input type="text" class="txt_box_150" name="invigilator.name" id="invigilator.name" value="${invigilator.name}"/>
				        </td>
						<td class="lable_100">性别：</td>
				        <td><select name="invigilator.gender" id="invigilatorgender" class="txt_box_150">
						    <option value="0">--请选择--</option>
							<option  value="1">男</option>
							<option  value="2">女</option>
						</select>
				        </td>
						<td rowspan="5" class="lable_100">照片：</td>
						<td rowspan="5">
						<img  src="<ui:img url="images/touxiang.jpg"/>" class="img_person" /><br />
						<input name="invigilator.photo" id="invigilator.photo" type="file" style="width:214px;background-color:#cccccc;"value="${invigilator.photo}"  />
						</td>
				     </tr>
					 <tr>
					    <td class="lable_100">电话：</td>
						<td><input id="invigilator.telephone" name="invigilator.telephone" class="txt_box_150" value="${invigilator.telephone}"/></td>
                        <td class="lable_100">邮箱：</td>
						<td><input id="invigilator.email" name="invigilator.email" class="txt_box_150" value="${invigilator.email}"/></td>
					 </tr>
					 <tr>
					    <td class="lable_100">证件类型：</td>
						<td><select name="invigilator.certType" id="invigilatorcertType" class="txt_box_150">
						    <option  value="0">--请选择--</option>
							<option  value="1">证件号</option>
							<option  value="2">学生证</option>
							<option  value="3">驾驶证</option>
							<option  value="4">教师资格证</option>
						</select></td>
                        <td class="lable_100">证件号码：</td>
						<td><input id="invigilator.certNo" name="invigilator.certNo" class="txt_box_150" value="${invigilator.certNo}"/></td>
					 </tr>
					 <tr>
					    <td class="lable_100">最高学历：</td>
						<td colspan="1"><select name="invigilator.degree" id="invigilatordegree" class="txt_box_150">
						  <option value="0">--请选择--</option>
							<option  value="1">博士</option>
							<option  value="2">研究生</option>
							<option  value="3">博士后</option>
							<option  value="4">硕士</option>
							 <option value="5">本科</option>
						</select></td>
						<td class="lable_100">手机：</td>
						<td colspan="1"><input id="invigilator.mobile" name="invigilator.mobile" class="txt_box_150" value="${invigilator.mobile}"/></td>
					 </tr>
					 <tr>
					    <td class="lable_100">工作单位：</td>
						<td colspan="3"><input id="invigilator.workUnit" name="invigilator.workUnit" class="txt_box_150" value="${invigilator.workUnit}"/></td>
					 </tr>
					 <tr>
					    <td class="lable_100">收费标准：</td>
						<td colspan="3"><input id="" name="invigilator.feeStandard" class="txt_box_150" value="${invigilator.feeStandard}"  />&nbsp;元 &nbsp;/ &nbsp;
						<input type="radio" checked="checked" name="invigilator.feeType" value="0" />场 &nbsp;
						<input type="radio" name="invigilator.feeType" value="1" /> 天
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
					    <td class="lable_100">监考经历：</td>
						<td>
						   <textarea name="invigilator.invigilationExperience" id="invigilator.invigilationExperience" class="txt_box_460" rows="6" cols="12">${invigilator.invigilationExperience}</textarea>
						</td>
					 </tr>
					 <tr>
					    <td class="lable_100">备注：</td>
						<td>
						   <textarea name="invigilator.note" id="invigilator.note" class="txt_box_460" rows="6" cols="12">${invigilator.note}</textarea>
						</td>
					 </tr>
					 <tr><td align="center" colspan="2">
					 <input type="submit" name="" id="" value="修改" class="btn_black_61"/>
					 </td></tr>
			    </table>
			   </form>
        </div>
     </div>
  </div>

  </body>
  </html>

		

