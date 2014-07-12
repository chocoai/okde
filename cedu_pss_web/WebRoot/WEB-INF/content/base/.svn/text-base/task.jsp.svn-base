<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>任务</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 分页 -->
		<jc:plugin name="page" />
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<script type="text/javascript">
		var taskId = 0;
		function deleteE(id)
		{
			if(confirm('确定要删除吗？'))
			{
				taskId = id;
				ajax_100_1();
			}
		}
		function delete_callback(data)
		{
			if(data.isback)
			{
				alert('删除成功');
			}
			else
			{
				alert('删除失败');
			}
			search001();
		}
		function costomStatus(status)
		{
			if(status==EXPORT_EXCEL_STATUS_BEING)
			{
				return "正在进行";
			}
			else if(status==EXPORT_EXCEL_STATUS_FINISH)
			{
				return "已完成";
			}
			else if(status==EXPORT_EXCEL_STATUS_FAILURE)
			{
				return "导出异常";
			}
			else
			{
				return "导出失败";
			}
		}
		//下载地址回调函数
			function excel_export_callback(data){
				if(data.downLoadPath!='error'){
					$("#down_load_url").html("<a href='"+WEB_ATTACHMENT+data.downLoadPath+"'>下载导出结果</a>");
					jQuery('#down_load_dialog').dialog("open");
				}else{
					$("#down_load_url").html("下载任务失败！");
					jQuery('#down_load_dialog').dialog("open");
				
				}//刷新
				refresh001();
				
			}
			function download(id){
				taskId=id;
				if(taskId!=0){
					ajax_download_zip_1();
				}
			}
			function costomPath(id,status){
				if(status==EXPORT_EXCEL_STATUS_FINISH)
				{
					return "<a href='javascript:download("+id+")'>下载</a>";
				}
				return "";
			}
			$(document).ready(function(){
				//下载导出文件
				jQuery('#down_load_dialog').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示',
					buttons: {
						'关闭': function() { 
							jQuery(this).dialog("close"); 
						} 
					}
				});	
			});
		</script>
		<a:ajax 
			successCallbackFunctions="delete_callback" 
			pluginCode="100" 
			parameters="{'taskId':taskId}"
			urls="/base/delete_base_task"
		/>
		<a:ajax 
			parameters="{'taskId':taskId}"
			successCallbackFunctions="excel_export_callback" 
			pluginCode="download_zip" 
			urls="/base/down_load_excel_file"
		/>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="任务列表">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<page:plugin 
				pluginCode="001"
				il8nName="task"
				subStringLength="20"
				searchListActionpath="base_task_list"
				searchCountActionpath="base_task_count" 
				columnsStr="title;status;downloadSumCount;createTime;#public.operating" 
				customColumnValue="1,costomStatus(status);4,costomPath(id,status)"
				delete="json,deleteE,id"
				pageSize="15" />
		</body:body>
		<!-- 下载地址 -->
		<div id="down_load_dialog" style="display:none">
			<div align="center" id="down_load_url">
			
			</div>
		</div>
	</body>

</html>