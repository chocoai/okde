<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>院校列表</title>
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

		<script type="text/javascript">

		/*
		  构建超链接
		*/
		function return_communication_record(id)
		{
			return '<a href="'+WEB_PATH+'/academy/view_academy_communication?id='+id+'">院校沟通</a>';
		}
		
		</script>
	
  </head>
  
  <body>
   <!--头部层开始 -->
		<head:head title="院校沟通">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<page:plugin 
					pluginCode="123"
					il8nName="academy"
					searchListActionpath="listacademy"
					searchCountActionpath="countacademy"
					columnsStr="code;name;public.operating;"
					customColumnValue="2,return_communication_record(id);"
					pageSize="10"
					ontherOperatingWidth="80px"	
			/>
		</body:body>
	
	
  </body>
</html>
