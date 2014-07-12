<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>新增院校联系人</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		
		<script type="text/javascript">
		$(document).ready(function(){
			 $.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
					onError:function(msg,obj,errorlist){
						//$.map(errorlist,function(msg1){alert(msg1)});
						alert(msg);
					},
					onSuccess:function(msg,obj,errorlist){
						return true;
					}
				});
				
			$("#academylinkmanname").formValidator({onShow:"请输入姓名",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"姓名不能为空,请确认"});
			$("#academylinkmanmobile").formValidator({empty:true,onShow:"请输入你的手机号码，可以为空",onFocus:"你要是输入了，必须输入正确",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留手机号码啊？"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"你输入的手机号码格式不正确"});
		});
		</script>
	
  </head>
  
  <body>
     
     <form id="form1" name="form1" action="add_academy_link_man" method="post" enctype="multipart/form-data">
	 <input type="hidden" name="academylinkman.academyId" value="${academy.id}"/>
		 <!--头部层开始 -->
		<head:head title="addacademylinkman" il8nName="academy">
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
					<th style="text-align:left; font-weight:bold;">联系人信息</th>
				</tr>
			</table>
			
			<table class="add_table">
				<tr>
					<td class="lable_100">姓名：</td>
					<td>
						<input type="text" class="txt_box_150" name="academylinkman.name" id="academylinkmanname" />
					</td>
					 <td><div id="academylinkmannameTip" style="width:250px"></div></td>
				</tr>
				<tr>
					<td class="lable_100">院校：</td>
					<td>${academy.name}</td>
				</tr>
				<tr>
					<td class="lable_100">职务：</td>
					<td>
						<input type="text" class="txt_box_150" name="academylinkman.position" id="academylinkman.position" />
					</td>
				</tr>
				<tr>
					<td class="lable_100">职责：</td>
					<td><input class="txt_box_150" name="academylinkman.duty" type="text" id="academylinkman.duty" /></td>
				</tr>
					<tr>
					<td class="lable_100">手机：</td>
					<td><input type="text" id="academylinkmanmobile" name="academylinkman.mobile" class="txt_box_150"/></td>
					 <td><div id="academylinkmanmobileTip" style="width:250px"></div></td>
				</tr>
				<tr>
					<td class="lable_100">电话：</td>
					<td><input type="text" id="academylinkman.telephone" name="academylinkman.telephone" class="txt_box_150"/></td>
				</tr>
				<tr>
					<td class="lable_100">E-Mail：</td>
					<td><input type="text" id="academylinkman.email" name="academylinkman.email" class="txt_box_150"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn_black_61" id="add_sub" type="submit" value="保存"/></td>
				</tr>
			</table>

		</body:body>
</form>
  </body>
</html>
