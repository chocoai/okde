<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<html>

  <head>
    <title>教材详情</title>
    <!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />

	<script type="text/javascript">
		$().ready(function()
		{
			var a=$('#status').attr('innerHTML');
			if(a==1)
				$('#status').attr('innerHTML','启用');
			else
				$('#status').attr('innerHTML','停用');
		})
		
	</script>
</head>
  <body>
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>计算机原理</a> </li>
		</ul>
	</div>
	
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
		
			
		<table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">教材编号:${book.id}</th>
				</tr>
			</table>
		  <table class="add_table" border="0" cellpadding="0" cellspacing="0">
		  	<tr>
				
			
		  		<td class="lable_100">
		  			教材名称:
		  		</td>
		  		<td style="width:200px;">
		  			${book.name}
		  		</td>
		  		<td class="lable_100">
		  			出版社:
		  		</td>
		  		<td style="width:200px;">
		  			${book.press}
		  		</td>
		  		
				
					<td rowspan="5"  > 
					<img src="<ui:img url='${book.snapshot}' />" height="50px" border="0" />
					</td>
				
		  	</tr>
		  	<tr>
		  		<td class="lable_100">
		  			作者:
		  		</td>
		  		<td>
		  			${book.author}
		  		</td>
		  		<td class="lable_100">
		  			ISBN:
		  		</td>
		  		<td>
		  			${book.isbn}
		  		</td>
		  	</tr>		  
		  	<tr>
		  		<td class="lable_100">
		  			版次:
		  		</td>
		  		<td>
		  			${book.edition}	
		  		</td>
		  		<td class="lable_100">
		  			定价:
		  		</td>
		  		<td>
		      	${book.price}	
		      </td>		      
		  	</tr>		  	
		    <tr>
			  <td class="lable_100">计量单位:</td>
		      <td>
		      	${book.unit}
		      </td>
		      <td class="lable_100">采购价:</td>
		      <td >
		           ${book.purchasePrice}		
		      </td>
			  
			 
		     
		    </tr>
			<tr>
				  <td class="lable_100">状态:</td>
		      <td id="status">
		      		${book.status}
		      </td>
			</tr>
		  </table>
		
		
		
		
				
	
   
        
   </div>
  </div>
 </div>
	
	
	
	
</body>
</html>