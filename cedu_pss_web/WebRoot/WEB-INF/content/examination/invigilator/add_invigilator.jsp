<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>增加监考老师</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		 <script type="text/javascript">
	    	//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;//email
	    	$(document).ready(function(){
				//if(!${addrlt}){
					//alert("数据库存在重复数据,请重新填写数据");
				//}
				$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
					onError:function(msg,obj,errorlist){
						alert(msg);
					}
				});
					$("#invigilatorname").formValidator({onShow:"请输入名称",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"名称不能为空,请确认"});
					$("#invigilatorgender").formValidator({onShow:"请选择性别",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"性别不能为空,请确认"});
					$("#invigilatortelephone").formValidator({onShow:"请输入电话",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"电话不能为空,请确认"});
					$("#invigilatoremail").formValidator({onShow:"请输入邮件",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"邮件不能为空,请确认"});
					$("#invigilatorcertType").formValidator({onShow:"请选择证件类别",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"证件类别不能为空,请确认"});
					$("#invigilatorcertNo").formValidator({onShow:"请输入证件",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"证件不能为空,请确认"});
					$("#invigilatordegree").formValidator({onShow:"请选择学历",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"学历不能为空,请确认"});
					$("#invigilatormobile").formValidator({onShow:"请输入手机号",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"手机号不能为空,请确认"});
					$("#invigilatorworkUnit").formValidator({onShow:"请输入工作单位",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"工作单位不能为空,请确认"});
					$("#invigilatorinvigilationExperience").formValidator({onShow:"请输入经历",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"经历不能为空,请确认"});
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
				 	<th style="width:20px;"><img src="<ui:img url="images/title_left.gif" />" /></th>
				 	<th style="text-align:left; font-weight:bold;">基本资料</th>
			  </tr>
		  
			</table>
			<form id="form1" action="add_invigilator" method="post" enctype="multipart/form-data">
				<table class="add_table">  
				    <tr>
				      	<td class="lable_100">姓名：</td>
				        <td>
				        	<input type="text" class="txt_box_150" name="invigilator.name" id="invigilatorname" />
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
						<img  src="<ui:img url="../images/touxiang.jpg"/>" class="img_person" /><br />
						<input name="invigilator.photo" id="invigilatorphoto" type="file" style="width:214px;background-color:#cccccc;" />
						</td>
				     </tr>
					 <tr>
					    <td class="lable_100">电话：</td>
						<td><input id="invigilatortelephone" name="invigilator.telephone" class="txt_box_150"/></td>
                        <td class="lable_100">邮箱：</td>
						<td><input id="invigilatoremail" name="invigilator.email" class="txt_box_150"/></td>
					 </tr>
					 <tr>
					    <td class="lable_100">证件类型：</td>
						<td><select name="invigilator.certType" id="invigilatorcertType" class="txt_box_150">
						    <option value="0">--请选择--</option>
							<option  value="1">证件号</option>
							<option  value="2">学生证</option>
							<option  value="3">驾驶证</option>
							<option  value="4">教师资格证</option>
						</select></td>
                        <td class="lable_100">证件号码：</td>
						<td><input id="invigilatorcertNo" name="invigilator.certNo" class="txt_box_150"/></td>
					 </tr>
					 <tr>
					    <td class="lable_100">最高学历：</td>
						<td colspan="1"><select name="invigilator.degree" id="invigilatordegree" class="txt_box_150">
						 <option value="0">--请选择--</option>
						<option value="1">博士后</option>
						<option value="2">博士</option>
						<option value="3">硕士</option>
						<option value="4">研究生</option>
						<option value="5">本科</option>
						</select></td>
						<td class="lable_100">手机：</td>
						<td colspan="1"><input id="invigilatormobile" name="invigilator.mobile" class="txt_box_150"/></td>
					 </tr>
					 <tr>
					    <td class="lable_100">工作单位：</td>
						<td colspan="3"><input id="invigilatorworkUnit" name="invigilator.workUnit" class="txt_box_150" /></td>
					 </tr>
					 <tr>
					    <td class="lable_100">收费标准：</td>
						<td colspan="3"><input id="invigilatorfeeStandard" name="invigilator.feeStandard" class="txt_box_150" />&nbsp;元 &nbsp;/ &nbsp;
						<input type="radio"  name="invigilator.feeType" id="invigilatorfeeType" value="0" />场 &nbsp;
						<input type="radio" name="invigilator.feeType" id="invigilatorfeeType" value="1" /> 天
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
						   <textarea name="invigilator.invigilationExperience" id="invigilatorinvigilationExperience" class="txt_box_460" rows="6" cols="12"></textarea>
						</td>
					 </tr>
					 <tr>
					    <td class="lable_100">备注：</td>
						<td>
						   <textarea name="invigilator.note" id="invigilatornote" class="txt_box_460" rows="6" cols="12"></textarea>
						</td>
					 </tr>
					 <tr><td align="center" colspan="2">
					 <input type="submit" name="" id="" value="添加" class="btn_black_61"/>
					 </td></tr>
			    </table>
			   </form>
        </div>
     </div>
  </div>

  </body>
  </html>

		

