<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>总部订购</title>
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
 	<style type="text/css"> 
		.checkbox_style{vertical-align:middle}  
		.checkbox_style input{vertical-align:middle;}  
    </style>
	<script type="text/javascript" >
 
		function SelectAll() {
 			var checkboxs=document.getElementsByName("branch");
 			for (var i=0;i<checkboxs.length;i++) {
 			 var e=checkboxs[i];
 			 e.checked=!e.checked;
 			}
		}
		
		function showbook()
		{
			$('#bookDiv').attr('style','display:block;');
		}
		
		function addshow()
		{
			show('msgDiv','提示',200,100);
		}
		jQuery(function(){
	 
	});
 
	
	</script>
</head>
  <body>
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a class="icon">总部订购</a> </li>
		</ul>

	</div>
	<!--
	<div id="conmenu">	 
	<img src="../images/cedu/icon/icon_add.gif" />
	<a href="view_cedu_order.html" target="_blank">代中心订购</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="../images/cedu/icon/icon_add.gif" 
	<a href="view_cedu_fee_payment.html" target="_blank">直接订购单</a>
	
	</div>
	-->
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    

		
			 
			 
			 
			
			 
	 
	
	
	
	
	<div id="bookDiv"   >
		<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">教材列表</th>
				</tr>
					</table>
			<table class="gv_table_2">

			
					<tr>
					<th>教材编号</th>
		    		<th>教材名称</th>
					<th>出版社</th>
					<th>作者</th>
					<th>ISBN</th>

					<th>版次</th>
					<th>定价</th>
					<th>订购总数</th>
					<th>金额</th>
					<th>书商</th>
		    		
						
					</tr>

					
					 
					    <s:iterator var="book" value="purchaseDetaillist">
					    <tr>
						<td align="center"><s:property value="#book.bookcode"/></td>
						<td align="center"><s:property value="#book.bookname"/></td>
						<td align="center"><s:property value="#book.bookpress"/></td>
						<td align="center"><s:property value="#book.bookauthor"/></td>
						<td align="center"><s:property value="#book.bookisbn"/></td>

						<td align="center"><s:property value="#book.bookedition"/></td>
						<td align="center"><s:property value="#book.bookprice"/></td>
						<td align="center"><s:property value="#book.requiredAmount"/></td>
						<td align="center"><s:property value="#book.purchasePrice"/></td>
						<td align="center">
						
						</td>
					</tr>
						</s:iterator>
					 

					
					
			</table>
		
		<table class="add_table">
		<tr>
			<td align="center"><input name="" onclick='javascript:location.href="add_cedu_order.html" ' type="button" class="btn_black_130" value="生成订购单" /></td>
		</tr>
		
		</table>
		
		
		</div>
	
	
	
	
	<div id="msgDiv" style="display:none">
		操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />

	</div>
	
	
	
        
   </div>
  </div>
 </div>
	
	
	
	
</body>
</html>