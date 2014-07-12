<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>设置院校LoGo</title>
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
 			$.formValidator.initConfig({formid:"form1",debug:false,submitonce:true,
					onError:function(msg,obj,errorlist){
						//$.map(errorlist,function(msg1){alert(msg1)});
						alert(msg);
					}
				});
				
				
		$("#img").formValidator({onShow:"请选择图片",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftempty:false,rightempty:false},onError:"图片不能为空,请确认"});

				
				
				
				
				
				
		});
		
		
		
		
		
		
		
		
		
		
		</script>
	
  </head>
  
  <body>
     
     <form id="form1" action="update_academy_logo" method="post" enctype="multipart/form-data">
	 <input type="hidden" name="id" value="${academy.id}"/>
		<!--头部层开始 -->
		<head:head title="设置院校LoGo">
			<html:a text="返回" icon="return" onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
			<table class="add_table">
				<tr>
					<td class="lable_100">图片：</td>
					<td>
						<img src="<ui:img url="%{academy.purl}" />" border="0" />
					</td>
				</tr>
				
				<tr>
					<td class="lable_100">请选择图片：</td>
					<td><input name="img" id="img" type="file" /></td>
					<td><div id="imgTip" style="width:250px"></div></td>
				</tr>
				
				<tr>
					<td></td>
					<td><input class="btn_black_61" type="submit"  value="保存"/></td>
				</tr>
			</table>

		</body:body>
</form>
  </body>
</html>
