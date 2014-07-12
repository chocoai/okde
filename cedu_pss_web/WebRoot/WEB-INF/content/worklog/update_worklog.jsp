<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>修改日报</title>
    	
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
		<jc:plugin name="ckeditor"/>		
		
		<script type="text/javascript">

		$(document).ready(function(){
				//第一组校验组，默认组号为"1"
        		$.formValidator.initConfig({submitButtonID:"add_sub",debug:false,
        			onSuccess:function()
        			{	
        				return true;
        			}
        			,onError:function(msg,obj,errorlist){alert_dialog(msg);}
        		});
				$("#title").formValidator().inputValidator({min:1,onError:"请输入名称!"});		
				
				
		 });
		</script>
	
  </head>
  
  <body>
     <form id="form1" name="form1" action="update_worklog" method="post">		
		<!--头部层开始 -->
		<head:head title="修改个人日报" >
			<html:a text="返回" onclick="history.go(-1);" icon="return"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>		
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					<th style="text-align:left; font-weight:bold;">修改日报</th>
				</tr>
			</table>
			
			<table class="add_table">  
				<tr>
					<td class="lable_100"><span>*</span>日报标题：</td>
					<td>
						<input type="text" class="txt_box_150" name="title" id="title" value="${worklog.title }"/> 
					</td>
				</tr>
				<tr>
					<td class="lable_100">日报内容：</td>
					<td>
			         	<textarea id="content" name="content">${worklog.content }</textarea>
			         	<script type="text/javascript">CKEDITOR.replace( 'content' );</script>
					</td>
				</tr>
				
				<tr>
					<td><input type="hidden" name="id" value="${worklog.id }"/></td>
					<td><input class="btn_black_61" id="add_sub" type="submit" value="保存"/></td>
				</tr>
			</table>
		</body:body>
	</form>
  </body>
</html>
