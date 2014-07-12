<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>${branchName}库存</title>
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
	
		 jQuery(function(){
			   
				
				//移出库房级联
				jQuery('#fromId').change(function(){
		 		ajax_140_1();
				});	
			});	
			
		 	//ajax回调函数  移除库房名称
			function ajax_roomnameajax(data)
			{
				// 移除库房名称
				jQuery("#fromname").empty();
			    jQuery("#fromname").append('<option value="0">--请选择--</option>');
			    if(data.fromlist!=null && data.fromlist.length>0)
			    {
			    	$.each(data.fromlist,function(){	
			    		jQuery("#fromname").append('<option value="'+this.name+'">'+this.name+'</option>'); 
			    	});
			   	}
			}
			
			//ajax回调函数  移除库房位置
			function ajax_postionajax(data)
			{
				// 移除库房
				jQuery("#fromId").empty();
			    jQuery("#fromId").append('<option value="0">--请选择--</option>');
			    if(data.storerlist!=null && data.storerlist.length>0)
			    {
			    	$.each(data.storerlist,function(){	
			    		jQuery("#fromId").append('<option value="'+this.position+'">'+this.positionName+'</option>'); 
			    	});
			   	}
			}
		 	
		 	
		 	
	</script>	
	<!--库房名称 -->
	    <a:ajax 
			pluginCode="140"
			successCallbackFunctions="ajax_roomnameajax" 
			parameters="{fromId:jQuery('#fromId').val()}"
			urls="/book/stock/find_storeroom_fromid_ajax"
			isOnload="all" 			
		/>
		<!-- 库房位置 -->
		<a:ajax 
			pluginCode="139"
			successCallbackFunctions="ajax_postionajax" 
			urls="/book/stock/find_storeroom_ajax"
			isOnload="all" 			
		/>
		 
	
</head>  
<body>

<div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>库存</a> </li>
		</ul>
	</div>
	
</div>
<div class="block">
	<div class="public_table_bg_766">

		<div class="tb_table_default_2">
		<!--Menu Begin-->
			<div>
			<div>
				<ul id="menu">

				<li><a href="../stock/index_stock" id="first" title=""class="current" >库存</a></li>
				<li><a href="../stock/index_transfer" id="second" title=""   >移库</a></li>
				
				</ul>
			</div>
			</div>
				<div style="float:left; width:30%;">
				<table class="gv_table_2">
					<tr>
						<th style="width:20px;"><img class="img_icon" src="../images/title_left.gif" /></th>					<th style="text-align:left; font-weight:bold;">查询条件</th>
				 	
					</tr>
					</table>
		   <form action="index_stock" >
			<table class="add_table">
				<tr>

					<td align="right">库房位置：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="stock.storeroomId" id="fromId">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>	
							</tr>	
							<tr>
					<td align="right">库房名称：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="stock.storeroomname" id="fromname">
							  		<option value="0" >--请选择--</option>
							  	</select>
							  </td>	
					</tr>
				 
					<tr>	
						<td align="right">教材名称：</td>
							  <td align="left">
							  	 <input type="text" class="txt_box_100" name="stock.bookname"/>
							  </td>	
						 
					</tr>
					
					<tr>
					<td align="right">版次：</td>
							  <td align="left"><input type="text" class="txt_box_100" name="stock.bookedition"/></td>	
					</tr>
					
					<tr>	
						<td align="left"></td>

						<td align="left"><input name="" type="submit" class="btn_black_61" value="查询" />
										<!-- <input name="" type="button" onclick="search()" class="btn_black_61" value="查询2" /> -->
						</td>
					</tr>
					
					
			</table>
			</form>
			</div>
			
			
				<!--Line Begin-->
			<div style="float:left;width:4px; height:500px; background-color:#3394C1; margin-left:2px; margin-right:2px;">
			</div>
			<!--Line End-->
			
				<div  style="width:69%;">
 	
			<table class="gv_table_2">
							<tr>
				<th colspan="7" style="text-align:left;font-weight:bold;" ><div  style="font-size: 12px; margin: 0px 5px 0 0;width:40%; text-align:left"><img style="vertical-align:middle" class="img_icon" src="../images/title_left.gif" />&nbsp;&nbsp;库房物料清单</div></th>
				 	
							</tr>
					
							<tr>
								<th>库房位置</th>
								<th>库房名称</th>
							    <th>教材名称</th>
							    <th>库存数量</th>
								<th>版次</th>
								<th>定价</th>
								<th>总价</th>
								
								
							</tr>
							<s:iterator var="stock" value="list">
							<tr align="center"><td><s:property value="#stock.storeroomweizhi"/></td>
							<td><s:property value="#stock.storeroomname"/></td>
							<td><s:property value="#stock.bookname"/></td>
							<td><s:property value="#stock.total"/></td>
							<td><s:property value="#stock.bookedition"/></td>
							<td><s:property value="#stock.bookprice"/></td>
							<td><s:property value="#stock.price"/></td>
							</tr>
							</s:iterator>
							 
							<tr>
								<th colspan="7"><div style="float:right">
								金额总计:<b style="color:#FF0000">${com}</b> 元 </div></th>

							</tr>
 				
	  	  </table>
		 
   
			</div>
 
  		</div>
  	</div>
  </div>
  </body>
</html>
