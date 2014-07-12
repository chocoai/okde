<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
		<title>修改用户</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<script type="text/javascript">
		$(document).ready(function(){
				$('#branch_id').val('${users.orgId}');
				init_result_dialog();
				reloadparent();
				
				
				
				//第一组校验组，默认组号为"1"
        		$.formValidator.initConfig({submitButtonID:"up_sub",debug:false,submitOnce:true,
        			onSuccess:function()
        			{	
        				jQuery('#upform').submit();
        			}
        			,onError:function(msg,obj,errorlist){alert_dialog(msg);return false;}
        		});
				$("#fullName").formValidator({onShow:"请输入姓名",onFocus:"用户名至少1个字符,最多20个字符",onCorrect:"该姓名可以注册"}).inputValidator({min:1,max:20,onError:"你输入的姓名错误,请确认"});
				$("#password").formValidator({empty:true,onShow:"不输入密码，将维持原密码",onFocus:"以数字或字母开头",onCorrect:"密码合法"}).inputValidator({min:8,empty:{leftEmpty:true,rightEmpty:true,emptyError:"密码两边不能有空符号"},onError:"密码不能为空,请确认"}).regexValidator({regExp:regexEnum.password,onError:"你输入的密码强度不够"});;
				$("#reppassword").formValidator({empty:true,onShow:"输再次输入密码",onFocus:"以数字或字母开头",onCorrect:"密码一致"}).inputValidator({min:8,empty:{leftEmpty:true,rightEmpty:true,emptyError:"重复密码两边不能有空符号"},onError:"重复密码不能为空,请确认"}).compareValidator({desID:"password",operateor:"=",onError:"2次密码不一致,请确认"}).regexValidator({regExp:regexEnum.password,onError:"你输入的密码强度不够"});
				$("#email").formValidator({onShow:"请输入邮箱",onFocus:"邮箱6-100个字符,输入正确了才能离开焦点",onCorrect:"恭喜你,你输对了"}).inputValidator({min:6,max:100,onError:"你输入的邮箱长度错误,请确认"}).regexValidator({regExp:regexEnum.email,onError:"你输入的邮箱格式不正确"});
			});
			
			
			//部门
			function reloadparent()
			{
				jQuery.post('<s:url value="/orgstructure/department/list_department"/>',{'department.officeId':jQuery('#branch_id').val()},
				        function(data)
				    	{
				    		var lists="";
				    		//lists+='<option value="0">---请选择---</option>';
				    		if(null!=data.list)
				    		jQuery.each(data.list, function()
			    			{		    			
	     		    			 lists+="<option value='"+this.id+"'>"+this.name+"</option>";
			    			});
			    			jQuery('#departmentId').html(lists);
			    			$("#departmentId").attr("value",${users.departmentId });
				 			reloadjob(true);
				    	},
				 "json");	
				 
			}
			
			//岗位
			function reloadjob(flag)
			{
				jQuery.post('<s:url value="/orgstructure/job/list_job"/>',{'job.departmentId':jQuery('#departmentId').val()},
				        function(data)
				    	{
				    		var lists="";
				    		//lists+='<option value="0">---请选择---</option>';
				    		if(null!=data.list)
				    		jQuery.each(data.list, function()
			    			{		    			
	     		    			 lists+="<option value='"+this.id+"'>"+this.name+"</option>";
			    			});
			    			jQuery('#jobId').html(lists);
			    			if(flag)
			    			{
			    				$("#jobId").attr("value",${users.jobId });
			    			}
				    	},
				 "json");	
			}
		</script>
  </head>
  
  <body>
    	<!--头部层开始 -->
		<head:head title="修改用户">
			<html:a text="返回" onclick="history.go(-1);" icon="return" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<s:actionmessage/>
				<div>
					<form id="upform" action="update_user" method="post" enctype="multipart/form-data" >
					<table width="100%" class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">学习中心:</td>
							<td><s:select  id="branch_id" name="orgId" list="plist"  listKey="id" listValue="name" onchange="reloadparent();" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="lable_100">用户名:</td>
							<td><input type="text" class="txt_box_150" readonly="readonly" name="userName" id="userName" value="${users.userName }" /></td>
							<td><div id="userNameTip" class="user_form_validator"></div></td>
						</tr>
						<tr>
							<td class="lable_100">密码:</td>
							<td><input type="password" class="txt_box_150" name="password" id="password" /></td>
							<td><div id="passwordTip" class="user_form_validator"></div></td>
						</tr>
						<tr>
							<td class="lable_100">确认密码:</td>
							<td><input type="password" class="txt_box_150" name="reppassword" id="reppassword"/></td>
							<td><div id="reppasswordTip" class="user_form_validator"></div></td>
						</tr>
						<tr>
							<td class="lable_100">姓名:</td>
							<td><input type="text" class="txt_box_150" name="fullName" id="fullName" value="${users.fullName }"/></td>
							<td><div id="fullNameTip" class="user_form_validator"></div></td>
						</tr>
						<tr>
							<td class="lable_100">座机:</td>
							<td><input type="text" class="txt_box_150" name="telephone" id="telephone" value="${users.telephone }"/></td>
							<td><div id="telephoneTip" class="user_form_validator"></div></td>
						</tr>
						<tr>
							<td class="lable_100">手机:</td>
							<td><input type="text" class="txt_box_150" name="mobile" id="mobile" value="${users.mobile }"/></td>
							<td><div id="mobileTip" class="user_form_validator"></div></td>
						</tr>
						<tr>
							<td class="lable_100">部门:</td>
							<td>
								<select id="departmentId" name="departmentId" class="txt_box_150" onchange="reloadjob(false)">
									<option value="0">---请选择---</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="lable_100">岗位:</td>
							<td>
								<select id="jobId" name="jobId" class="txt_box_150">
									<option value="1">---请选择---</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="lable_100">邮箱:</td>
							<td><input type="text" class="txt_box_150" name="email" id="email" value="${users.email}"/></td>
							<td><div id="emailTip" class="user_form_validator"></div></td>
						</tr>
						<!-- 
						<tr>
							<td class="lable_100">启用状态:</td>
							<td>
								<s:radio id="status" name="status" list="#{0:'启用',1:'停用'}" value="users.status"></s:radio>
							</td>

						</tr>
						 -->
						<tr>
							<td class="lable_100">头像图片:</td>
							<td><input type="file" class="txt_box_150" name="files" id="files"/></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>
								<input type="hidden" id="users.id" name="users.id" value="${users.id}">
								<input type="submit" value="保存" id="up_sub" class="btn_black_61" />
								<input type="button" value="取消" onclick="history.go(-1);" class="btn_black_61" />
							</td>
						</tr>
					</table>
					</form>
				</div>
				<!--Search End-->
			</body:body>	
  </body>
</html>
