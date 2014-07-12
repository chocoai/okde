<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>中心订购</title>
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
			 <li class="selectTag"><a class="icon">发货单详细</a> </li>
		</ul>
	</div>
	
</div>
<div class="block">

	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
		
		
			<table class="add_table">
			<tr>
				<td align="left">发货单编号:<s:property value="invoice.code"/></td>
				<td align="left">书商名称:<s:property value="invoice.suppliername"/></td>
					<td align="left">总金额：<s:property value="invoice.amount"/></td>
			</tr>

		</table>
		
		<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img class="img_icon" src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">发货单明细</th>					
				</tr>
					</table>
			<table class="gv_table_2">			
					<tr>
						<th>教材名称</th>						
						<th>发货数量</th>						
					</tr>		
					<s:iterator var="detail" value="invoicelist">
					<tr>
						<td align="center"><s:property value="#detail.bookname"/></td>
						<td align="center"><s:property value="#detail.sendedTotal"/></td>					
					</tr>
					</s:iterator>		
			</table>    
   </div>
  </div>
 </div>
</body>
</html>