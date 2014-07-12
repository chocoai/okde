<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>附件</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<script type="text/javascript">
		var ids=0;
		//删除附件
		function ajax_delete(data)
		{
			closes('isdeleteDiv');
			search123();
		}
		
		
		</script>
		
		<a:ajax parameters="{'id':ids}"
				successCallbackFunctions="ajax_delete"
				urls="/examination/invigilator/deleteinvigilatorattachment"
		 pluginCode="123"
		  />
		<script type="text/javascript">
		/*
		显示附件类型
		*/
		function filetype(type)
		{
			if(type==STATUS_AUTHOR_TRUE)
			{
				return '普通附件';
			}else
			{
				return '重要附件';
			}
		}
		/*
		下载附件
		*/
		function operating(id,name,attachmentUrl)
		{
			return '<a href="javascript:void(0)"><img src="'+WEB_IMAGES+'examination/images/icon_down.gif"   ></img></a> <a href="'+WEB_PATH+'/examination/invigilator/update_invigilator_attachment?id='+id+'" ><img src="'+WEB_IMAGES+'/images/icon_edit.gif"></img></a>  <a href="javascript:void(0)" onclick="deleteFun('+id+')" ><img src="'+WEB_IMAGES+'/images/icon_del.gif"></img></a>'
		}
		
		/*
		下载附件
		*/
		function uploadattachment(name,attachmentUrl)
		{
			$('#fileName').val(name);
			$('#filePath').val(attachmentUrl);
			$('#formatt').get(0).submit();
		}
		
		
		
		/*
		删除附件
		*/
		function deleteFun(id)
		{
			ids=id;
			show('isdeleteDiv','提示',250,150);
		}
	
		/*
		取消删除
		*/
	    function cdiv()
	    {
	    	closes('isdeleteDiv');
	    }
		/*
		表单验证
		*/
		function validator()
		{
			var title=$('#title').val();
			var files=$('#files').val();
			
			if($.trim(title)=="")
			{
				show('titleDiv','提示',250,150);
				return false;
			}
			if($.trim(files)=="")
			{
				show('filesDiv','提示',250,150);
				return false;
			}
			return true;
		}
		function showtime(createdTime){
		var time=createdTime
		return time ;
		}
		</script>
	
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="监考老师：${invigilator.name}" >
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Menu Begin-->
			<%@ include file="_cedu_invigilator_tab.jsp" %>
			<!--Menu End-->
		<table class="gv_table_2">
              <tr>
                <th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
                <th style="text-align:left; font-weight:bold;">附件信息</th>
                <th> </th>
              </tr>
            </table>
             <form id="form" action="upload_invigilator_attachment" onsubmit="return validator()" method="post" enctype="multipart/form-data">
             <input type="hidden"  name="id"  id="id" value="${id}" />
			 <table class="gv_table_2" border="0" cellpadding="0" cellspacing="0">
				<tr><td><span style="color: black">标题：</span>
				<input type="text" name="title" id="title"/>
				<input type="file" id="files" name="files"/>
				<select name="types" id="types" class="txt_box_150">
				<option value="0">--请选择--</option>
				<option value="1">普通附件</option>
				<option value="2">重要附件</option>
				</select>
				<input type="submit" value="上传" id="submit" name="submit" class="btn_black_61"/> 
				</td>
				</tr>
			</table>
			</form>
			
			 <page:plugin 
				pluginCode="123"
				il8nName="invigilator"
				searchListActionpath="list_invigilatorattachment"
				searchCountActionpath="count_invigilatorattachment"
				columnsStr="public.operating;title;name;types;times;userName"
				customColumnValue="0,operating(id,name,attachmentUrl);3,filetype(types);4,showtime(createdTime)"
				pageSize="10"
				ontherOperatingWidth="80px"	
				params="'invigilatorId':$('#id').val()"
		  />	
		 
		<div id="isdeleteDiv" style="display:none">
		<div style="float:inherit">确定要删除吗？</div><br/>
		<input type="button" value="确定" id="btnok" name="btnok" onclick="ajax_123_1()" class="btn_black_61"/> 
		<input type="button" value="取消" id="tt" name="tt" onclick="cdiv()" class="btn_black_61"/> 
		
		</div>			
					
					
		<div id="titleDiv" style="display:none">
		标题不能为空，请确认！！
		</div>		
		
		
		<div id="filesDiv" style="display:none">
		附件不能为空，请确认！！
		</div>			
								
		</body:body>
  </body>
</html>
