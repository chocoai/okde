<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>总部派发</title>			
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
		
		
		function addshow()
		{
			show('msgDiv','提示',200,100);
		}
		
		
		//派发和明细
		function caozuo(id,status,branchName,code,requisitionername)
		{		
			var mx='<s:url value="/book/headquarterstodistribute/find_headquarters_to_distribute.action?purchaseRequisitionId=" />'; 
			var pf='<s:url value="/book/headquarterstodistribute/update_headquarters_to_distribute?purchaseRequisitionId=" />';		
			if(status == 1)
			{
				return '<a href="'+pf+id+'&branchName='+branchName+'&code='+code+'&requiredName='+requisitionername+'">派发</a>'+'&nbsp;&nbsp;'+'<a href="'+mx+id+'&branchName='+branchName+'&code='+code+'&requiredName='+requisitionername+'">明细</a>'	
			}else
			{
			return '<a href="'+mx+id+'">明细</a>'	
			}
		}
	</script>
</head>
  <body>
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a class="icon">总部派发</a> </li>
		</ul>
	</div>
	<div id="conmenu">
	<img src="../images/icon_title_return.jpg" width="15" height="15" />
	 <a href="javascript:history.go(-1);">返回</a>
	</div>
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">申购单列表</th>
				</tr>
			</table>
			<table class="gv_table_2">
				<page:plugin pluginCode="page" il8nName="headquarterstodistributepurchase"
								searchListActionpath="page_list_headquarterstodistribute_purchase"
								searchCountActionpath="page_count_headquarterstodistribute_purchase"
								columnsStr="branchName;code;amount;requisitionername;requisitiontime;status;handle"
								customColumnValue="6,caozuo(id,status,branchName,code,requisitionername)"								
								pageSize="3" ontherOperatingWidth="80px" isPageSize="3"
								params="'purchaseRequisition.academyId':$('#academyId').val(),'purchaseRequisition.types':$('#types').val()" 
								/>	
			</table>
   </div>
  </div>
 </div>
	<div id="msgDiv" style="display:none">
		操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />
	</div>
</body>
</html>