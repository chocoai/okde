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
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<script type="text/javascript">
		var ids=0;
		var auditstatus=0;
		
		//院校审批
		function ajax_update(data)
		{
			//search123();
			refresh123();
		}
		
		</script>
		
		<a:ajax parameters="{'id':ids,'auditstatus':auditstatus}"
				successCallbackFunctions="ajax_update"
				urls="/academy/updateacademyaudit"
		 pluginCode="123"
		  />
		
		<script type="text/javascript">
		function uschange(id)
		{
			ids=id;
			auditstatus=$('#sel'+id).val();
			ajax_123_1();
		}
		
		/*
		显示图片名称
		*/
		function purlAndname(id,name,purl)
		{
			if(purl==null)
			{
			return '<a href="'+WEB_PATH+'/academy/view_academy?id='+id+'" target="_blank" >'+name+'</a>';
			}
			return '<a href="'+WEB_PATH+'/academy/view_academy?id='+id+'" target="_blank" ><img src="'+WEB_IMAGES+purl+'" border="0"  /></a><br /><a href="'+WEB_PATH+'/academy/view_academy?id='+id+'" target="_blank" >'+name+'</a>';
		}
		
		function auditStatusValue(id,auditStatus)
		{
			if(auditStatus==AUDIT_STATUS_FALSE)
			{
				return '<select id="sel'+id+'" onchange="uschange('+id+')"  ><option selected="selected" value="'+AUDIT_STATUS_FALSE+'" >未审核</option><option  value="'+AUDIT_STATUS_TRUE+'" >审核通过</option><option value="'+AUDIT_STATUS_FAIL+'" >审核未通过</option></select>';
			}
			else if(auditStatus==AUDIT_STATUS_TRUE)
			{
				return '<select id="sel'+id+'" onchange="uschange('+id+')"  ><option value="'+AUDIT_STATUS_FALSE+'" >未审核</option><option  selected="selected" value="'+AUDIT_STATUS_TRUE+'" >审核通过</option><option  value="'+AUDIT_STATUS_FAIL+'" >审核未通过</option></select>';
			}
			else if(auditStatus==AUDIT_STATUS_FAIL)
			{
				return '<select id="sel'+id+'" onchange="uschange('+id+')"  ><option value="'+AUDIT_STATUS_FALSE+'" >未审核</option><option   value="'+AUDIT_STATUS_TRUE+'" >审核通过</option><option selected="selected"  value="'+AUDIT_STATUS_FAIL+'" >审核未通过</option></select>';
			}
		}
		</script>
	
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="院校审核 &gt;&gt; 院校列表">
		</head:head>
		<!--主体层开始 -->
		<body:body>
		<page:plugin 
				pluginCode="123"
				il8nName="academy"
				searchListActionpath="listacademyaudit"
				searchCountActionpath="countacademyaudit"
				columnsStr="name;auditStatus;contractEndtime;projectManagerName;telephone"
				customColumnValue="0,purlAndname(id,name,purl);1,auditStatusValue(id,auditStatus)"
				pageSize="20"
				view="http,/academy/view_academy,id,id,_blank"
				ontherOperatingWidth="80px"	
		/>
  		</body:body>
  </body>
</html>
