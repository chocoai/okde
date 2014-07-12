<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>院校修改</title>
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
				$('#projectManagerId').val('${academy.projectManagerId}');
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
		 util.select.initOption('#scale',get_academy_scale(),${academy.scale});

		 if(${academy.isForceFeePolicy}!=null && ${academy.isForceFeePolicy}==1)
		 {
		 	$("#isForceFeePolicy").attr("checked",true);
		 }else
		 {
		 	$("#isForceFeePolicy").attr("checked",false);
		 }
		  $.formValidator.initConfig({formId:"form1",debug:false,submitOnce:true,
					onError:function(msg,obj,errorlist){
						//$.map(errorlist,function(msg1){alert(msg1)});
						alert(msg);
					}
				});
		 
		 $("#academyname").formValidator({onShow:"请输入院校名称",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"院校名称不能为空,请确认"});
		 $("#academytelephone").formValidator({onShow:"请输入院校联系电话",onFocus:"至少8个长度",onCorrect:"输入正确"}).inputValidator({min:8,empty:{leftEmpty:false,rightEmpty:false},onError:"院校联系电话不能为空,请确认"});
		 $("#academyaccount").formValidator({onShow:"请输入院校账号",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"院校账号不能为空,请确认"});
		});
		</script>
	
  </head>
  
  <body>
     <form id="form1" name="form1" action="update_academy" method="post" enctype="multipart/form-data">
     <input type="hidden" name="academy.id" value="${academy.id}" />
   
		<!--头部层开始 -->
		<head:head title="院校修改">
			<html:a text="返回" icon="return" onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">院校基本信息</th>
				</tr>
			</table>
			
			<table class="add_table">  
				<tr>
					<td class="lable_100">项目经理：</td>
					<td>
						<select class="txt_box_150" name="academy.projectManagerId" id="projectManagerId">
						
						</select>
					</td>
				</tr>
				<tr>
					<td class="lable_100"><span>*</span>院校名称：</td>
					<td>
						<input type="text" class="txt_box_150" name="academy.name" id="academyname" value="${academy.name}" />
					</td>
					<td><div id="academynameTip" style="width:250px"></div></td>
				</tr>
				<tr>
					<td class="lable_100">院校编号：</td>
					<td>
						<input type="text" class="txt_box_150" name="academy.code" id="academycode" value="${academy.code}" />
					</td>
				</tr>
				<tr>
					<td class="lable_100">院校简称：</td>
					<td>
						<input type="text" class="txt_box_150" name="academy.shortName" id="academy.shortName" value="${academy.shortName}" />
					</td>
					
				</tr>
				<tr>
					<td class="lable_100">成立年度：</td>
					<td><input class="txt_box_150" name="academy.foundedYear" type="text" id="academy.foundedYear" maxlength="4" value="${academy.foundedYear}"  />(例:1990)</td>
				</tr>
				<tr>
					<td class="lable_100"><span>*</span>院校联系电话</td>
					<td><input class="txt_box_150" name="academy.telephone" type="text" id="academytelephone" maxlength="32" value="${academy.telephone}" />(例:010-66668888)</td>
					<td><div id="academytelephoneTip" style="width:250px"></div></td>
				</tr>
				
				<tr>
					<td class="lable_100"><span>*</span>帐号</td>
					<td><input class="txt_box_150" name="academy.account" type="text" id="academyaccount" maxlength="32" value="${academy.account}" /></td>
					<td><div id="academyaccountTip" style="width:250px"></div></td>
				</tr>
					
				<tr>
					<td class="lable_100">网址：</td>
					<td><input class="txt_box_150" name="academy.website" type="text" id="academy.website" value="${academy.website}" size="45"/></td>
				</tr>
				<tr>
					<td class="lable_100">院校规模：</td>
					<td><select class="txt_box_150" name="scale" id="scale"  >
						
					</select></td>
				</tr>
				<tr>
					<td class="lable_100">强制执行收费政策：</td>
					<td><input type="checkbox" id="isForceFeePolicy" name="isForceFeePolicy"  /></td>
				</tr>
				
				<tr>
					<td class="lable_100">院校地址：</td>
					<td><input  name="academy.address" id="academy.address" class="txt_box_300" value="${academy.address}"   ></input></td>
				</tr>
				
				<tr>
					<td class="lable_100">院校简介：</td>
					<td><textarea name="academy.introduction" id="academy.introduction"  cols="45" rows="5" class="txt_box_300" >${academy.introduction}</textarea></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="btn_black_61" type="submit" value="修改"/></td>
				</tr>
			</table>
</body:body>
	
</form>
  </body>
</html>
