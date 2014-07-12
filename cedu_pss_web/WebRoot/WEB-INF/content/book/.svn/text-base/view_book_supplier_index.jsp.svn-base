<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<html>

  <head>
    <title>书商详情</title>
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

	<script type="text/javascript">
		
		function updatedw(id,purchasePrice)
		{
		 
			$('#id').val(id);
			$('#purchasePrice').val(purchasePrice);
			show('updateDiv','提示',250,150); 
		}
		
		function showphoto(id,purchasePrice)
		{
			var image='<a href="javascript:updatedw('+id+',\''+purchasePrice+'\')">修改采购价格</a>';
			return image;
		}
		function addshow()
		{
			show('msgDiv','提示',200,100);
		}
		
		//修改用户状态
			function upddw()
			{
				var id=$('#id').val();
				var purchasePrice=$('#purchasePrice').val();
				jQuery.post('<s:url value="update_book"/>',{"book.id":id,"book.purchasePrice":purchasePrice,},
			      	  function(data)
			    		{
			    			if(data.results)
			    			{
			    				show('showDialog','修改成功!',150,100);
			    				window.location.reload();
			    			}
			    			else show('showDialog','修改失败!',150,100);	
			    		},
				 "json");	
			}
		
		//function show()
		//{
		//var x='<input type="text" id="purchasePrice"/>';
		//return x;
		//}
	</script>
</head>
  <body>
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>书商信息</a> </li>
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
				 	<th style="width:20px;"><img src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" /></th>
				 	<th style="text-align:left; font-weight:bold;">书商:${bs.name}</th>
		  </tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="0" cellspacing="0">
		  <tr>
				 	<td align="right">教材名称：</td>
					<td align="left"><input type="text" id="name"  /></td>
					
					<td align="right">ISBN：</td>
					<td align="left"><input type="text" id="isbn" /></td>

					
						<td align="right"></td>
					<td align="left"><input type="button" value="查询" class="btn_black_61" onclick="search123()" /></td>
				</tr>
		  </table>
		
		<table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" /></th>
				 	<th style="text-align:left; font-weight:bold;">商品目录</th>
		  </tr>
		</table>
        <page:plugin 
						pluginCode="123"
						il8nName="book"
						searchListActionpath="page_list_book"
						searchCountActionpath="page_count_book"
						columnsStr="code;name;press;author;isbn;edition;price;purchasePrice;handle"
						customColumnValue="8,showphoto(id,purchasePrice)"
						pageSize="10"
						params="'book.name':$('#name').val(),'book.isbn':$('#isbn').val()"						
					/>
					 
   </div>
  </div>
 </div>
	<div id="updateDiv" style="display:none;">
		<form>
			<table class="add_table">  
				<tr>
					<td class="lable_100">采购价格：</td>
					<td>
						<input type="hidden" name="book.id" id="id" />
						<input type="text" class="txt_box_130" name="book.purchasePrice" id="purchasePrice"/>
					</td>
				</tr>
				 
				<tr>
					<td colspan="2" align="center"><input class="btn_black_61" type="button" onclick="upddw()" value="保存"/></td>
				</tr>
			</table>
		</form>
		</div>
	
	
	
</body>
</html>