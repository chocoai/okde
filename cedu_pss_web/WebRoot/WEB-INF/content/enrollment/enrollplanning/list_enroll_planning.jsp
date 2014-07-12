<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>院校招生计划</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<script type="text/javascript">
			function operating(id){
				return '<a href="'+WEB_PATH+'/enrollment/academy_enroll_batch_list?academyId='+id+'&tab=batch">设置</a>'+'&nbsp;&nbsp;'+'<a href="'+WEB_PATH+'/enrollment/down_load_view_academy_level_and_major?academyId='+id+'&tab=batch">下载(层次/专业)</a>';
			}
			function academyInfo(id,code){
				return '<a href="'+WEB_PATH+'/academy/view_academy?id='+id+'" target="_blank">'+code+'</a>';
			}
		</script>
  </head>
  <body>
		<!--头部层开始 -->
		<head:head title="院校招生计划">
		 
		</head:head>
		<!--主体层开始 -->
		<body:body>
		<page:plugin 
				pluginCode="001"
				il8nName="academy"
				searchListActionpath="list_academy_by_status"
				searchCountActionpath="count_academy_by_status"
				columnsStr="#academyinfo;name;public.operating"
				customColumnValue="0,academyInfo(id,code);2,operating(id)"
				columnsWidth="[0,30%][1,35%][2,30%]"
				pageSize="20"
		/>
  </body:body>	
  </body>
</html>
