<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title> 移库</title>			
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
	<script type="text/javascript" >

		 jQuery(function(){				
				//移出库房级联
				jQuery('#fromId').change(function(){
		 		ajax_140_1();
				});				
					//移入库房级联
				jQuery('#toId').change(function(){
		 		ajax_138_1();
				});	
		 });	
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
			
		
			//ajax回调函数  移除库房名称
			function ajax_roomnameajax(data)
			{
				// 移除库房名称
				jQuery("#fromname").empty();
			    jQuery("#fromname").append('<option value="0">--请选择--</option>');
			    if(data.fromlist!=null && data.fromlist.length>0)
			    {
			    	$.each(data.fromlist,function(){	
			    		jQuery("#fromname").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			}
			
		 
			
			//ajax回调函数  移入库房位置
			function ajax_toroompositionajax(data)
			{
				// 移入库房
				jQuery("#toId").empty();
			    jQuery("#toId").append('<option value="0">--请选择--</option>');
			    if(data.storerlist!=null && data.storerlist.length>0)
			    {
			    	$.each(data.storerlist,function(){	
			    		jQuery("#toId").append('<option value="'+this.position+'">'+this.positionName+'</option>'); 
			    	});
			   	}
			}
		
			//ajax回调函数  移入库房名称
			function ajax_toroomnameajax(data)
			{
				// 移入库房名称
				jQuery("#toname").empty();
			    jQuery("#toname").append('<option value="0">--请选择--</option>');
			    if(data.tolist!=null && data.tolist.length>0)
			    {
			    	$.each(data.tolist,function(){	
			    		jQuery("#toname").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			}	
	</script>	
	<!-- 移出库房名称 -->
	    <a:ajax 
			pluginCode="140"
			successCallbackFunctions="ajax_roomnameajax" 
			parameters="{fromId:jQuery('#fromId').val()}"
			urls="/book/stock/find_storeroom_fromid_ajax"		
		/>
		<!-- 移出库房位置 -->
		<a:ajax 
			pluginCode="139"
			successCallbackFunctions="ajax_postionajax" 
			urls="/book/stock/find_storeroom_ajax"
			isOnload="all" 			
		/>
		<!-- 移入库房名称 -->
		<a:ajax 
			pluginCode="138"
			successCallbackFunctions="ajax_toroomnameajax" 
			parameters="{toId:jQuery('#toId').val()}"
			urls="/book/stock/find_storeroom_toid_ajax"		
		/>
		<!-- 移入库房位置 -->
		<a:ajax 
			pluginCode="137"
			successCallbackFunctions="ajax_toroompositionajax" 
			urls="/book/stock/find_storeroom_ajax"
			isOnload="all" 			
		/>	
</head>  
<body>
<head:head title="移库">
			<html:a text="添加移库单" icon="add"
				href="/book/stock/add_transfer" />
		</head:head>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2">
		<!--Menu Begin-->
			<div>
			<div>
				<ul id="menu">
				<li><a href="../stock/index_stock" id="first" title="" >库存</a></li>
				<li><a href="../stock/index_transfer" id="second" title=""  class="current" >移库</a></li>		
				</ul>
			</div>
			</div>
			<div  style="height:5px;"></div>
			<table class="add_table">
				<tr>
					  <td align="right">移除库房位置：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="meterialtransfer.fromId" id="fromId">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>								
					<td align="right">移除库房名称：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="meterialtransfer.fromname" id="fromname">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>					
					<td align="right">教材名称：</td>
							  <td align="left">
							  	<input type="text" class="txt_box_130" name="meterialtransfer.meterialname" id="bookId"/>	 
							  </td>	
								</tr>
					<tr>								
					  <td align="right">移入库房位置：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="meterialtransfer.toId" id="toId">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>						
					<td align="right">移入库房名称：</td>
					<td align="left">
							  	<select class="txt_box_130" name="meterialtransfer.name" id="toname">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>				
					<td align="right">版次：</td>
							  <td align="left">
							  	<input type="text" class="txt_box_130" name="meterialtransfer.name" id="bookedition" />
							  	 
							  </td>		
						<td align="left">
											<input name="" type="submit" class="btn_black_61" value="查询"
											onclick="searchpage();"	  />
										</td>
					</tr>				
			</table>			
			<table class="gv_table_2">
					 
							<page:plugin pluginCode="page" il8nName="stocktransfer"
								searchListActionpath="list_stocktransfer"
								searchCountActionpath="count_stocktransfer"
								columnsStr="fromweizhi;fromname;toweizhi;toname;bookname;bookedition;amount"
								
								pageSize="3" ontherOperatingWidth="80px" isPageSize="3"
								params="'fromId':$('#fromId').val(),'fromname':$('#fromname').val(),'toId':$('#toId').val(),'bookname':$('#bookId').val(),'bookedition':$('#bookedition').val()" 
								/>

						 

			</table>
			
			
			</div>
			
		
			
			
			
			
			
			
  		</div>
  	</div>
  
  </body>
</html>


