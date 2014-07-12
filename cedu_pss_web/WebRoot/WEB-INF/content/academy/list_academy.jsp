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
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		
		
		
		
		<script type="text/javascript">
		var ids=0;
		var using_status=0;
		
		//删除院校
		function ajax_delete(data)
		{
			closes('isdeleteDiv');
			//search123();
			refresh123();
		}
		
		//院校审批
		function ajax_update(data)
		{
			closes('isdeleteDiv');
			//search123();
			refresh123();
		}
		
		//院校审批
		function ajax_using(data)
		{
			show('updateDiv','提示',150,100)
			//search123();
			refresh123();
		}
		
		
		</script>
		
		<a:ajax parameters="{'id':ids};{'id':ids};{'id':ids,'usingstatus':using_status}"
				successCallbackFunctions="ajax_delete;ajax_update;ajax_using;"
				urls="/academy/deleteacademy;/academy/updateacademyauditstatus;/academy/updateacademyusingstatus"
		 pluginCode="123"
		  />
		
		<script type="text/javascript">
		
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

		/*
		删除院校
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
		设置审核状态
		*/
		function auditStatus(id,auditStatus)
		{
			if(auditStatus==AUDIT_STATUS_INIT)
			{
				
				/*
				提交审核操作未实现，需要提供登录员工信息
				*/
				return '<a href="javascript:void(0)"   onclick="updateauditStatus('+id+')"   >提交审核</a>';
				
			}else if(auditStatus==AUDIT_STATUS_FALSE)
			{
				return "未审核";
			}
			else if(auditStatus==AUDIT_STATUS_TRUE)
			{
				isPageOperating(id,"123","delete");
				return "审核通过";
			}else
			{
				return "审核未通过";
			}
		}
		
		/*
		修改审核状态
		*/
		function updateauditStatus(id)
		{
			ids=id;
			ajax_123_2();	
		}
		
		
		
		
		/*
		  设置接口状态
		*/
		function interfaceSettingStatus(interfaceSettingStatus)
		{
				if(interfaceSettingStatus==STATUS_AUTHOR_TRUE)
				{
					return "已设置";
				}else
				{
					return "未设置";
				}
		}
		/*
		  设置启用状况
		*/
		function usingStatus(id,usingStatus,interfaceSettingStatus,auditStatus)
		{
			
			if(usingStatus==STATUS_AUTHOR_TRUE)
			{
				return '<select id="sel'+id+'" onchange="uschange('+id+','+interfaceSettingStatus+')"  ><option selected=selected value="1" >启用</option><option value="0" >停用</option></select>';
			}else
			{
				return '<select id="sel'+id+'" onchange="uschange('+id+','+interfaceSettingStatus+')"  ><option value="1" >启用</option><option selected="selected" value="0" >停用</option></select>';
			}
		}

		/*
		  修改启用状况
		*/
		function uschange(id,interfaceSettingStatus)
		{
			// 屏蔽此代码（不知接口是什么，也没有对应修改接口，导致院校无法启用/禁用）
			//if(interfaceSettingStatus==STATUS_AUTHOR_FALSE)
			if(false)
			{
				
				show('results','提示',260,180);
				if($('#sel'+id).val()==0)
				{
					$('#sel'+id).val(1);
				}else
				{
					$('#sel'+id).val(0);
				}
				return ;
			}
			
			ids=id;
			using_status=$('#sel'+id).val();
			ajax_123_3();
			
		}
		function isForceFeePolicyValue(isForceFeePolicy){
			return isForceFeePolicy==0?"非强制":"强制收费";
		}
		
		</script>
	
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="院校信息管理 &gt;&gt; 院校列表">
		 <html:a text="新增院校" icon="add" href="/academy/add_academy" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
		<page:plugin 
				pluginCode="123"
				il8nName="academy"
				searchListActionpath="listacademy"
				searchCountActionpath="countacademy"
				columnsStr="name;contractEndtime;projectManagerName;telephone;interfaceSettingStatus;usingStatus;auditStatus;isForceFeePolicy"
				customColumnValue="0,purlAndname(id,name,purl);4,interfaceSettingStatus(interfaceSettingStatus);5,usingStatus(id,usingStatus,interfaceSettingStatus,auditStatus);6,auditStatus(id,auditStatus);7,isForceFeePolicyValue(isForceFeePolicy)"
				pageSize="20"
				delete="json,deleteFun,id"
				update="http,/academy/update_academy,id,id,_blank"
				view="http,/academy/view_academy,id,id,_blank"
				ontherOperatingWidth="80px"	
		/>
  </body:body>
	<div id="results" style="display:none">
		<div style="text-align:center; width:220px; height:120px;">接口尚未设置，不能启用!</div>
	</div>
	
	<div id="ustatus" style="display:none">
		<div style="text-align:center; width:220px; height:120px;">设置成功!</div>
	</div>
	
			  
	<div id="isdeleteDiv" style="display:none">
		<div style="float:inherit">确定要删除吗？</div><br/>
		<input type="button" value="确定" id="btnok" name="btnok" onclick="ajax_123_1()" class="btn_black_61"/> 
		<input type="button" value="取消" id="tt" name="tt" onclick="cdiv()" class="btn_black_61"/> 
		
		</div>	
	<div id="updateDiv" style="display:none">
	操作成功！！
	</div>	
	
	
  </body>
</html>
