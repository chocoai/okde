<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>申购单明细</title>			
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
	
	</script>
</head>
  <body>

  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a class="icon">申购单</a> </li>
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
				 	<th style="text-align:left; font-weight:bold;">申购单</th>

	
				</tr>
					</table>
			<table class="add_table">
				<tr>
					<td style="font-size:36px; text-align:center;">申购单</td>
				</tr>
			</table>
		<table  class="add_table" >

			<tr>
				<td width="15%"  align="right">申购单编号:</td>
				<td width="85%" align="left">${code}</td>
			</tr>
		
			<tr>
				<td width="15%"  align="right">申购院校名称:</td>
				<td width="85%" align="left">${academyName }</td>

			</tr>
			
			<tr>
				<td width="15%"  align="right">学习中心名称:</td>
				<td width="85%" align="left">${branchName }</td>
			</tr>
			
			
			
			<tr>
				<td width="15%"  align="right">申购人:</td>

				<td width="85%" align="left">${requiredName }</td>
			</tr>
			
			
		</table>
			
    	<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">申购单明细</th>	
				</tr>

		</table>
		
		
		<table class="gv_table_2">
			<tr>
				<th>教材名称</th>
				<th>版次</th>
				<th>申购数量</th>
				<th>定价(单价:元)</th>

				<th>采购价格(单价:元)</th>
			</tr>
		
			<s:iterator var="purchase" value="purchaseList">
			<tr>
				<td align="center"><s:property value="#purchase.bookname"/></td>
				<td align="center"><s:property value="#purchase.bookedition"/></td>
				<td align="center"><s:property value="#purchase.requiredAmount"/></td>
				<td align="center"><s:property value="#purchase.price"/></td>
				<td align="center"><s:property value="#purchase.purchasePrice"/>元</td>
				 
			</tr>
		 	</s:iterator>
		</table>
		
		
		<table class="gv_table_2">
		  		<tr>
				
				<th><input name="" type="button" class="btn_black_61" value="确定"
				 onclick="javascript:location.href='list_order.html'"
				 />&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="" type="button" class="btn_black_61" value="取消" />
			<div style="float:right">金额总计:<b style="color:#FF0000">2650.50 </b>元</div>

				</th>
				</tr>
				
			
		</table>
					
					
		
	
	
        
   </div>
  </div>
 </div>
	
	
	<div id="msgDiv" style="display:none">
		操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />

	</div>
	
	
</body>
</html>