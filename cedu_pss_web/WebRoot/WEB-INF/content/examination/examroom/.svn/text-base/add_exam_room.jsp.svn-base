<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>新增考场信息</title>
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
				$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
					onError:function(msg,obj,errorlist){
						alert(msg);
					}
				});
					$("#examroomname").formValidator({onShow:"请输入名称",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"名称不能为空,请确认"});
					$("#examroomaddress").formValidator({onShow:"请输入地址",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"地址不能为空,请确认"});
					$("#examroomlinkman").formValidator({onShow:"请输入联系人",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"联系人不能为空,请确认"});
					$("#examroomemail").formValidator({onShow:"请输入邮件",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"邮件不能为空,请确认"});
					//$("#examroomphone").formValidator({empty:true,onShow:"请输入你的手机号码",onFocus:"你要是输入了，必须输入正确",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留手机号码啊？"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"你输入的手机号码格式不正确"})
			});
			
	function showradio(obj){
		   $("#"+obj).show();
	  }
	  function heideradio(obj){
		  $("#"+obj).hide();
	  }
 </script>

  </head>
  
  <body>
   <form id="form1" action="add_exam_room" method="post">
<div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>新增考场信息</a> </li>
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
				 	<th style="text-align:left; font-weight:bold;">基本信息</th>
			  </tr>
			</table>
				<table class="add_table">  
				    <tr>
				      	<td  class="lable_150">考场名称：</td>
				        <td>
				        	<input type="text" class="txt_box_150" name="examroom.name" id="examroomname" />
				        </td>
						
					</tr>
					<tr>	
						<td class="lable_150">考场地址：</td>
				        <td><input type="text" class="txt_box_150" name="examroom.address" id="examroomaddress" />
				        </td>
				     </tr>
					 <tr>
					    <td class="lable_150">联系人：</td>
						<td>
						<input type="text" class="txt_box_150" name="examroom.linkman" id="examroomlinkman"/>
						</td>
					 </tr>
					 <tr>
					    <td class="lable_150">电话：</td>
						<td>
						<input id="examroomphone" name="examroom.phone" class="txt_box_150" type="text"/>
						</td>
					 </tr>
					 <tr>
					    <td class="lable_150">Email：</td>
						<td>
						<input id="examroomemail" name="examroom.email" class="txt_box_150" type="text" />
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
						  <input type="radio" id="examroomisSignable"  name="examroom.isSignable" value="0"  onclick="heideradio('radioxieyi')">是&nbsp;&nbsp;
						  <input type="radio" id="examroomisSignable" name="examroom.isSignable" value="1" onclick="showradio('radioxieyi')" > 否
						</td>
					 </tr>
					  <tr  id="radioxieyi" style="display:none;">
						<td class="lable_150" align="center">原因：</td>
						<td><textarea class="txt_box_400" cols="8" rows="6" id="examroom.signableSeason" name="examroom.signableSeason"></textarea></td>
					 </tr>
					 <tr>
					    <td class="lable_150">能否长期租用：</td>
						<td>
						 <input type="radio"  name="examroom.isLongTerm" id="examroomisLongTerm" value="0" onclick="heideradio('radiozuyong')">是&nbsp;&nbsp;
						 <input type="radio"  name="examroom.isLongTerm" id="examroomisLongTerm" value="1" onclick="showradio('radiozuyong')" > 否
						</td>
						</tr>
						<tr id="radiozuyong" style="display:none;">
							<td class="lable_100" align="center">原因：</td>
							<td><textarea class="txt_box_400" cols="8" rows="6" id="examroom.longTermSeason" name="examroom.longTermSeason"></textarea></td>
					     </tr>
					 <tr>
					    <td class="lable_150">能否提供监考老师：</td>
						<td>
						 <input type="radio"  name="examroom.hasInvigilator" id="examroomhasInvigilator" value="0" onclick="heideradio('radioteacher')">是&nbsp;&nbsp;
						 <input type="radio"  name="examroom.hasInvigilator" id="examroomhasInvigilator" value="1" onclick="showradio('radioteacher')">否
						</td>
					 </tr>
					 <tr id="radioteacher" style="display:none">
						<td class="lable_100" align="center">原因：</td>
						<td><textarea class="txt_box_400" cols="8" rows="6" id="examroom.hasInvigilatorSeason" name="examroom.hasInvigilatorSeason"></textarea></td>
					</tr>
					 <tr>
					 <td align="center" colspan="2">
					 <input type="submit" id="mysubmit" value="添加" class="btn_black_61" />
					 </td></tr>
			    </table>
 
        </div>
     </div>
  </div>
</form>
  </body>
</html>

