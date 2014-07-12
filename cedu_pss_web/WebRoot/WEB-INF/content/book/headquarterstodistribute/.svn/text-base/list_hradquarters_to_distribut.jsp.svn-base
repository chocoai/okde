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
		
		//明细
		function caozuo(id,branchName,code,distributename,amount)
		{
		
				var mx='<s:url value="/book/headquarterstodistributedetail/find_headquarters_to_distribute_detail?disteributeId=" />'; 
				 
			 
			return '<a href="'+mx+id+'&branchName='+branchName+'&code='+code+'&distributename='+distributename+'&avg='+amount+'">明细</a>'	
			 
			
		}
		
		function addshow()
		{
			show('msgDiv','提示',200,100);
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
	
	 <img class="img_icon" src="../images/icon_title_return.jpg" />

	 <a href="../headquarterstodistribute/index_headquarters_to_distribute" >派发</a>
	</div>
	
	
	
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    

			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>

				 	<th style="text-align:left; font-weight:bold;">派发单列表</th>
				</tr>
			</table>
			
			<table class="gv_table_2">
			
			 <page:plugin pluginCode="page" il8nName="headquarterstodistribute"
								searchListActionpath="page_list_headquarterstodistribute"
								searchCountActionpath="page_count_headquarterstodistribute"
								columnsStr="branchName;code;amount;distributename;distributetime;handle"
								customColumnValue="5,caozuo(id,branchName,code,distributename,amount)"								
								pageSize="3" ontherOperatingWidth="80px" isPageSize="3"
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