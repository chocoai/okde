<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../template/common/import.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>考试计划</title>
    <!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<jc:plugin name="loading" />
		<jc:plugin name="validator" />
		<script  type="text/javascript">
		function shows(id,name)
		{
			var image='<a href="list_examplan?id='+id+'&&name='+name+'">设置考试计划</a>';
			return image;
		}	
		</script>

  </head>
  <body>
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>考试计划</a> </li>
		</ul>
	</div>
	</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
	    <table class="gv_table_2" id="resTable" border="0" cellpadding="0" cellspacing="0">
	    	<tbody>
			    <page:plugin 
				pluginCode="123"
				il8nName="examplan"
				searchListActionpath="findByconditions_academy"
				searchCountActionpath="count_academy"
				columnsStr="name;#operating"
				customColumnValue="1,shows(id,name)"
				pageSize="5"
				ontherOperatingWidth="80px"	
		     />  	 
	    	</tbody>
	    </table>
   </div>
  </div>
 </div>
</body>
</html>

