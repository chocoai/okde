<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>新增院校</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		
		<script type="text/javascript">
		
		//项目经理列表
		function ajax_manager(data)
		{
			var lists='';
				$.each(data.userlist,function(){
				lists+='<option value="'+this.id+'">'+this.fullName+'</option>';
				});
				
				$('#projectManagerId').html(lists);
		}
		
		
		</script>
		
		<a:ajax parameters="null;"
				successCallbackFunctions="ajax_manager;"
				urls="/academy/listfindmanager;"
		 pluginCode="123"
		  />
		
		
		<script type="text/javascript">

		$(document).ready(function(){
			
			ajax_123_1();
			var msg='';
			msg=${msg};
			if(msg)
			{
			show('addDiv','提示',200,150);
			}
		 util.select.initOption('select[name=academy.scale]', get_academy_scale());
		  
		 $.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
					onError:function(msg,obj,errorlist){
						//$.map(errorlist,function(msg1){alert(msg1)});
						alert(msg);
					}
				});
		 
		 $("#academyname").formValidator({onShow:"请输入院校名称",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"院校名称不能为空,请确认"});
		 $("#academytelephone").formValidator({onShow:"请输入院校联系电话",onFocus:"至少8个长度",onCorrect:"输入正确"}).inputValidator({min:8,empty:{leftEmpty:false,rightEmpty:false},onError:"院校联系电话不能为空,请确认"});
		 $("#academyaccount").formValidator({onShow:"请输入院校账号",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"院校账号不能为空,请确认"});
		 $("#academylinkmanname").formValidator({onShow:"请输入联系人姓名",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"联系人姓名不能为空,请确认"});
		 $("#academylinkmantelephone").formValidator({onShow:"请输入电话号码",onFocus:"至少8个长度",onCorrect:"输入正确"}).inputValidator({min:8,empty:{leftEmpty:false,rightEmpty:false},onError:"电话号码不能为空,请确认"});
		 
		 });
		</script>
	
  </head>
  
  <body>
     <form id="form1" name="form1" action="add_academy" method="post" enctype="multipart/form-data">		
		<!--头部层开始 -->
		<head:head title="addacademy" il8nName="academy">
			<html:a text="返回" onclick="history.go(-1);" icon="return"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
		
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					<th style="text-align:left; font-weight:bold;">院校基本信息</th>
				</tr>
			</table>
			
			<table class="add_table">  
				<tr>
					<td class="lable_100"><span>*</span>项目经理：</td>
					<td>
						<select class="txt_box_150" name="projectManagerId" id="projectManagerId">
							
						</select>
					</td>
				</tr>
				<tr>
					<td class="lable_100">院校编号：</td>
					<td>
						<input type="text" class="txt_box_150" name="academy.code" id="academycode" />
					</td>
				</tr>
				<tr>
					<td class="lable_100"><span>*</span>院校名称：</td>
					<td>
						<input type="text" class="txt_box_150" name="academy.name" id="academyname" />
					</td>
					  <td><div id="academynameTip" style="width:250px"></div></td>
				</tr>
				<tr>
					<td class="lable_100">院校简称：</td>
					<td>
						<input type="text" class="txt_box_150" name="academy.shortName" id="academy.shortName" />
					</td>
				</tr>
				<tr>
					<td class="lable_100">成立年度：</td>
					<td><input class="txt_box_150" name="academy.foundedYear" type="text" id="academy.foundedYear" maxlength="4"/>(例:1990)</td>
				</tr>
				<tr>
					<td class="lable_100"><span>*</span>院校联系电话</td>
					<td><input class="txt_box_150" name="academy.telephone" type="text" id="academytelephone" maxlength="32"/>(例:010-66668888)</td>
					 <td><div id="academytelephoneTip" style="width:250px"></div></td>
				</tr>
				
				<tr>
					<td class="lable_100"><span>*</span>账号</td>
					<td><input class="txt_box_150" name="academy.account" type="text" id="academyaccount" maxlength="32" /></td>
					 <td><div id="academyaccountTip" style="width:250px"></div></td>
				</tr>
					<tr>
					<td class="lable_100"><span>*</span>联系人姓名：</td>
					<td><input type="text" id="academylinkmanname" name="academylinkman.name" class="txt_box_150"/></td>
					<td><div id="academylinkmannameTip" style="width:250px"></div></td>
				</tr>
				<tr>
					<td class="lable_100"><span>*</span>电话：</td>
					<td><input type="text" id="academylinkmantelephone" name="academylinkman.telephone" class="txt_box_150"/></td>
					<td><div id="academylinkmantelephoneTip" style="width:250px"></div></td>
				</tr>
				<tr>
					<td class="lable_100">E-Mail：</td>
					<td><input type="text" id="academylinkman.email" name="academylinkman.email" class="txt_box_150"/></td>
				</tr>
				<tr>
					<td class="lable_100">网址：</td>
					<td><input class="txt_box_150" name="academy.website" type="text" id="academy.website" value="" size="45"/></td>
				</tr>
				<tr>
					<td class="lable_100">院校规模：</td>
					<td><select class="txt_box_150" name="academy.scale" id="academy.scale" >
						
					</select></td>
				</tr>
				<tr>
					<td class="lable_100">强制执行收费政策：</td>
					<td><input type="checkbox" id="isForceFeePolicy" name="isForceFeePolicy" /></td>
				</tr>
				<tr>
					<td class="lable_100">院校Logo：</td>
					<td><input name="img" type="file" /></td>
				</tr>
				<tr>
					<td class="lable_100">院校简介：</td>
					<td><textarea name="academy.introduction" id="academy.introduction" cols="45" rows="5" class="txt_box_300" ></textarea></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn_black_61" id="add_sub" type="submit" value="保存"/></td>
				</tr>
			</table>

		<div id="addDiv" style="display:none">
			该院校已存在,请重新添加！！
			</div>


		</body:body>
</form>
  </body>
</html>
