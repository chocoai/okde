<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>日报填报</title>
    	
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
		<jc:plugin name="calendar" />
		<script type="text/javascript">

		$(document).ready(function(){
			$("#starttime").val(new Date().pattern("yyyy-MM-dd")); 
			$("#title").val(new Date().pattern("yyyy-MM-dd")+"的日报"); 
				//第一组校验组，默认组号为"1"
        		//$.formValidator.initConfig({submitButtonID:"add_sub",debug:false,
        		//	onSuccess:function()
        		//	{	
        		//		return true;
        		//	}
        		//	,onError:function(msg,obj,errorlist){alert_dialog(msg);}
        		//});
				//$("#title").formValidator().inputValidator({min:1,onError:"请输入名称!"});		
				
				
		 });
		function setTitle( object){
			if(object!=null){
				$("#title").val($(object).val()+"的日报"); 
			}
		}
		function checked(){
			if($("#starttime").val()==""){
				alert('请输入时间!');
				return false;
			}
			if($("#title").val()==""){
				alert('请输入名称!');
				return false;
			}
			return true;
		}
		</script>
	
  </head>
  
  <body>
     <form id="form1" name="form1" action="add_worklog" method="post" onsubmit="return checked();">		
		<!--头部层开始 -->
		<head:head title="日报填报" il8nName="admin">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>		
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					<th style="text-align:left; font-weight:bold;">日报填报</th>
				</tr>
			</table>
			<c:if test="${ERROR!=null&&ERROR=='DATE' }">
				<div id="showDialog" style="display:none"><table class="add_table"><tr><td colspan="2" align="center"><input type="button" class="btn_black_61" value="关闭" onclick="closes('showDialog');" /></td></tr></table></div>
	
				<script type="text/javascript">
					show('showDialog','日报已存在!',150,100);
				</script>
				
			</c:if>
			
			<table class="add_table">  
				
				<tr>
					<td class="lable_100"><span>*</span>日报时间:</td>
					<td>
						<input id="starttime" name="createOn" class="Wdate" type="text" onblur="setTitle(this)" value="" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" size="17">
					</td>
				</tr>
				<tr>
					<td class="lable_100"><span>*</span>日报标题：</td>
					<td>
						<input type="text" class="txt_box_150" name="title" id="title" value="${title }" /> 
					</td>
				</tr>
				<tr>
					<td class="lable_100">日报内容：</td>
					<td>
			         	<textarea id="content" name="content">${content }</textarea>
			         	<script type="text/javascript">CKEDITOR.replace( 'content' );</script>
					</td>
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
