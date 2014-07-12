<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
  <head>
  <!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 分页 -->
		<jc:plugin name="page" />
		<jc:plugin name="loading" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />	
		
		<script type="text/javascript">
		function del(id)
		{
			jQuery.post('<s:url value="delete_invigilatorcommunicationrecord"/>',{"id":id},
			        function(data)
			    	{
			    		if(data.results)
			    		{
			    			show('showDialog','删除成功!',150,100);
			    			search0909();
			    		}
			    		else {show('showDialog','删除失败!',150,100);}	
			    	},
			 "json");	
		}
		function show(createTime){
		return createTime.substring(0,10);
		}
		
		</script>
  </head>
  
  <body>
   <head:head title="监考老师:${invigilator.name}">
		</head:head>
  <!--头部层开始 -->
		<%@include file="_cedu_invigilator_tab.jsp" %>
		<input type="hidden" name="invigilatorId" id="invigilatorId" value="${id}"/>
		<!--主体层开始 -->
		<body:body>
		  <table class="gv_table_2" id="resTable" border="0" cellpadding="0" cellspacing="0">
	    	<tbody>
			 <page:plugin 
				pluginCode="0909"
				il8nName="examplan"
				searchListActionpath="list_communicationrecord"
				searchCountActionpath="count_communicationrecord"
				columnsStr="createTime;content"
				customColumnValue="0,show(createTime)"
				view="http,examination/invigilator/view_invigilatorcommunicationrecord,id,id,_blank"
				delete="json,del,id"
				pageSize="5"
				ontherOperatingWidth="80px"	
				params="'invigilatorId':$('#invigilatorId').val()"
		     />  	 
	    	</tbody>
	    </table>
		
		</body:body>

<div id="showDialog" style="display:none">

</div>
  </body>
</html>
