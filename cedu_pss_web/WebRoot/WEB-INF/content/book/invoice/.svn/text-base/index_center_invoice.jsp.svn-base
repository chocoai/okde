<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>中心签收</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<jc:plugin name="tab" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
 <script type="text/javascript" >
		
		function caozuo(id)
		{
			var n='<s:url value="/book/invoicedetail/index_center_invoice_detail?invoiceid=" />'; 
			return '<a href="'+n+id+'">明细</a>'	
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
			 <li class="selectTag"><a class="icon">中心签收</a> </li>
		</ul>
	</div>
	<div id="conmenu">	 
	<img src="../../images/title_menu/icon_add.gif" />
	<a href="../invoicedetail/add_center_invoice_detail" target="_blank">添加发货单</a>	
	</div>
	
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    

		<table class="add_table">
			<tr>
				<td align="left">发货单编号:<input name="code" id="code" type="text" class="txt_box_100" value="" /></td>

				<td align="left">书商名称:<input name="suppliername" id="suppliername" type="text" class="txt_box_100" /></td>
					<td align="left"><input name="" type="button" onclick="searchpage()" class="btn_black_61" value="查询" /></td>
			</tr>
		</table>
			 <table class="gv_table_2">
					 
							<page:plugin pluginCode="page" il8nName="invoice"
								searchListActionpath="list_invoice"
								searchCountActionpath="count_invoice"
								columnsStr="code;suppliername;ordercode;amount;handle"
								customColumnValue="4,caozuo(id)"								
								pageSize="3" ontherOperatingWidth="80px" isPageSize="3"
								params="'code':$('#code').val(),'suppliername':$('#suppliername').val()" 
								/>
			</table>
        
   </div>
  </div>

 </div>
</body>
</html>