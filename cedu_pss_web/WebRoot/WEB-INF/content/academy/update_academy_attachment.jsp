<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>修改附件</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		
		<script type="text/javascript">
		
		
		
		
		$(document).ready(function(){
			 $.formValidator.initConfig({submitButtonID:"update_sub",debug:false,submitOnce:true,
					onError:function(msg,obj,errorlist){
						//$.map(errorlist,function(msg1){alert(msg1)});
						alert(msg);
					}
				});
				
			$("#title").formValidator({onShow:"请输入附件标题",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"标题不能为空,请确认"});
			$("#files").formValidator({onShow:"请选择附件",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"附件不能为空,请确认"});
				
			
		});
		
		
		
		
		
		</script>
	
  </head>
  
  <body>
     
     <form id="form1" action="update_academy_attachment" method="post" enctype="multipart/form-data">
	 <input type="hidden" name="id" value="${param.id}"/>
		<!--头部层开始 -->
		<head:head title="修改附件">
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
			
			
			<table class="add_table">
			
			
				<tr>
					<td class="lable_100">标题：</td>
					<td>
						<input type="text" id="title" name="title" value="${academyattachment.title}"  />
					</td>
					<td><div id="titleTip" style="width:250px"></div></td>
				</tr>
				
				<tr>
					<td class="lable_100">更改附件：</td>
					<td><input name="files" id="files" type="file" /></td>
					<td><div id="filesTip" style="width:250px"></div></td>
				</tr>
				
				<tr>
					<td class="lable_100">附件类型：</td>
					<td><select name="type" id="type" class="txt_box_150">
						<option value="1">普通附件</option>
						</select></td>
				</tr>
				
				<tr>
					<td></td>
					<td><input class="btn_black_61" id="update_sub" type="submit"  value="保存"/></td>
				</tr>
			</table>

		</body:body>
</form>
  </body>
</html>
