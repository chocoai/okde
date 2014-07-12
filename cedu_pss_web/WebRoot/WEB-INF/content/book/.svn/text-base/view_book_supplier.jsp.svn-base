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
		
		function showphoto(id)
		{
			var image='<a href="view_book?id='+id+'">细明</a>';
			return image;
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
				 	<th style="text-align:left; font-weight:bold;">编号:${bs.id}</th>
		  </tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="0" cellspacing="0">
		  	<tr>
				
			
		  		<td class="lable_100">
		  			书商名称:		  		</td>
				
		  		<td id="cbs" style="width:200px;">
		  			${bs.name}		  		</td>
				
		  		<td class="lable_100">
		  			简称:		  		</td>
				
		  		<td style="width:200px;">
		  			${bs.shortName}			  		</td>
		  	</tr>
		  	<tr>
				<td class="lable_100">
		  			分类:		  		</td>
		  		<td style="width:200px;">${bs.category}</td>
			
		  		<td class="lable_100">
		  			地址:		  		</td>
		  		<td>
		  			${bs.address}</td>
		  	</tr>		  
		  	<tr>
		  		<td class="lable_100">邮政编码:		  		</td>
		  		<td>
		  			${bs.postalCode}</td>
		  		<td class="lable_100">网址:		  		</td>
		  		<td>${bs.website}</td>		      
		  	</tr>		  	
		    <tr>
			  <td class="lable_100"><strong></strong>联系人(中文):</td>
		      <td>${bs.linkman}</td>
		      <td class="lable_100">电话:</td>
		      <td>${bs.telephone}</td>
		    </tr>
			<tr>
				  <td class="lable_100">手机:</td>
		      <td>${bs.mobile}</td>
			  
			   <td class="lable_100">传真:</td>
		      <td>${bs.fax}</td>
			</tr>
			
			<tr>
				  <td class="lable_100"><strong></strong>邮箱:</td>
		      <td>${bs.email}</td>
			  
			   <td class="lable_100">开户银行:</td>
		      <td>${bs.accountBank}</td>
			</tr>
			
			<tr>
				  <td rowspan="2" class="lable_100">备注::</td>
		          <td rowspan="2">${bs.note}</td>
			  
			   <td class="lable_100">账户:</td>
		      <td>${bs.note}</td>
			</tr>
			
			<tr>
			  <td class="lable_100">结算方式:</td>
		      <td>${bs.clearingForm}</td>
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
						columnsStr="id;name;info"
						customColumnValue="2,showphoto(id)"
						pageSize="10"
						params="'book.category':$('#bid').val()"
						
					/>
					<input type="hidden" id="bid" name="bid" value="0" />
   </div>
  </div>
 </div>
	
	
	
	
</body>
</html>