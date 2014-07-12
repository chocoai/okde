<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>密码修改</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<script type="text/javascript">
		
		$(document).ready(function(){
				//第一组校验组，默认组号为"1"
        		$.formValidator.initConfig({submitButtonID:"up_sub",debug:false,
        			onSuccess:function()
        			{	
        				return true;
        			}
        			,onError:function(msg,obj,errorlist){alert_dialog(msg);}
        		});
				$("#oldpwd").formValidator({onShow:"请输入原密码",onFocus:"至少8个长度",onCorrect:"密码合法"}).inputValidator({min:8,empty:{leftempty:false,rightempty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请确认"});
				$("#newpwd").formValidator({onShow:"请输入密码",onFocus:"以数字或字母开头，至少8个长度",onCorrect:"密码合法"}).inputValidator({min:8,empty:{leftEmpty:false,rightEmpty:false,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请确认"}).regexValidator({regExp:regexEnum.password,onError:"你输入的密码强度不够"});
				$("#reppwd").formValidator({onShow:"输再次输入密码",onFocus:"以数字或字母开头，至少8个长度",onCorrect:"密码一致"}).inputValidator({min:8,empty:{leftEmpty:false,rightEmpty:false,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"newpwd",operateor:"=",onError:"2次密码不一致,请确认"}).regexValidator({regExp:regexEnum.password,onError:"你输入的密码强度不够"});
			
			});
		
		</script>
		
  </head>
  
  <body>
   	<!--头部层开始 -->
	<head:head title="密码修改">
	</head:head>
	<!--主体层开始 -->
	<body:body>
		<div>
			<form id="addform" action="modify_password_admin" method="post">
				<s:actionmessage/>
				<table width="100%" class="add_table" border="0" cellpadding="2" cellspacing="0">
					<tr>
						<td class="lable_100">原密码:</td>
						<td><input type="password" class="txt_box_150" name="oldpwd" id="oldpwd" /></td>
						<td><div id="oldpwdTip" class="user_form_validator"></div></td>
					</tr>
					<tr>
						<td class="lable_100">新密码:</td>
						<td><input type="password" class="txt_box_150" name="newpwd" id="newpwd" /></td>
						<td><div id="newpwdTip" class="user_form_validator"></div></td>
					</tr>
					<tr>
						<td class="lable_100">确认新密码:</td>
						<td><input type="password" class="txt_box_150" name="reppwd" id="reppwd"/></td>
						<td><div id="reppwdTip" class="user_form_validator"></div></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>
							<input type="submit" value="保存" id="up_sub" class="btn_black_61" />
							<input type="reset" value="重置" class="btn_black_61" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body:body>	
  </body>
</html>
