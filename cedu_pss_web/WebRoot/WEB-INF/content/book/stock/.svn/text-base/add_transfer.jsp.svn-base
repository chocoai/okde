<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="net.cedu.entity.admin.User"%>
<%@ include file="../../template/common/import.jsp"%>
<%@ taglib  prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<!-- 分页插件 -->
		<jc:plugin name="page" />
		<script type="text/javascript">
		 jQuery(function(){
	 
	 			addStandardItem();
			  //移出库房级联
				jQuery('#fromId').change(function(){
		 		ajax_140_1();
		 		 
				});	
				
					//移入库房级联
				jQuery('#toId').change(function(){
		 		ajax_138_1();
				});	
		});	
		
		 //ajax回调函数  教材名称
			function ajax_categoryajax(data)
			{
				// 教材名称
				jQuery("select[name='bookname']").empty();
			    jQuery("select[name='bookname']").append('<option value="0">--请选择--</option>');
			    if(data.booklist!=null && data.booklist.length>0)
			    {
			    	$.each(data.booklist,function(){	
			    		jQuery("select[name='bookname']").append('<option value="'+this.id+'">'+this.name+'</option>'); 
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
	<!-- 教材名称 -->
		<a:ajax 
			pluginCode="136"
			successCallbackFunctions="ajax_categoryajax" 
			urls="/book/stock/find_bookall_ajax"
			isOnload="all" 			
		/>
	 
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
	
	<script type="text/javascript">
  		var number=1;
  		//添加标准项
		function addStandardItem()
		{
		var meterial = $('#standardTemplate tr').clone();
		meterial.appendTo('#standardTable > tbody');	
		cooRN();
		number++;
		}
 		
 		
		//删除方法
			function removemeterial(a)
			{
				if($('#rebate_table > tbody > tr').size()==1)
				{
				alert('您至少要填写一条！');
				return;
			}
			var tr = $(a).parent().parent();
			tr.remove();
	
			cooRN();
			}
 		
		function cooRN() //重新校正返款标准 行 序号
		{
		$('#standardTable > tbody > tr').each(function(index){
		$('.stdNo',this).html(index+1);
		$('.tfPrice', this).attr('class', 'tfPrice'+index+'');
		$('.tfQuantity', this).attr('class', 'tfQuantity'+index+'');
		$('.tfBookId', this).attr('class', 'tfBookId'+index+'');
	});	
	}
		
 
 
		function insertRelationships(){
 			var array=[];//选中的物料id数组
 			var quntion=[];//申请数量数组
 			var pric=[]; //单价数组
 			var meterialIds = "";//选中的物料id拼接起来的字符串
 			var quntionIds="";//填写的物料申请数量拼接起来的字符串
 			var arrayprics="";//填写的单价拼接起来的字符串
 			var fromid=$("#fromId").val();
 			var toid=$("#toId").val();
 			//循环获取数组中的物料Id
 			if(number>0){
 				  
				for(var i=0;i<number;i++){
				array.push($("select[class='tfBookId"+i+"']").val());
 				quntion.push($("input[class='tfQuantity"+i+"']").attr("value"));
 				pric.push($("input[class='tfPrice"+i+"']").attr("value"));
 				}
				if(array.length==0){
						//未选中任何一个，直接点击选取按钮
						alert("至少选择一项");
				}else{
					//选中的拼接成字符串
					meterialIds=array.join(",");
					quntionIds=quntion.join(",");
					arrayprics=pric.join(",");
					//通过表单实现js post提交
				document.write("<form action='add_transfer?array="+meterialIds+"&quntion="+quntionIds+"&fromid="+fromid+"&toid="+toid+"' method='post' name='lecturerForm' style='display:none'>");
				document.write("</form>");
				document.lecturerForm.submit();
 
				}
			}else{
				alert('错误');
			}
 		}
 		//提交前的验证
 		function yanzheng()
 			{
				if(jQuery("#quantity").val()=="")
				{
					alert('请输入数量！');
				}
				else if(jQuery("#fromId").val()==0)
				{
					alert('请选择移出库房位置！');
				}
					else if(jQuery("#fromname").val()==0)
				{
					alert('请选择移出库房名称！');
				}else if(jQuery("#toId").val()==0)
				{
					alert('请选择移入库房位置！');
				}
					else if(jQuery("#toname").val()==0)
				{
					alert('请选择移入库房名称！');
				}
				else if(jQuery("select[name='bookname']").val()==0 )
				{
					alert('请选教材名称！');
					}
				else if(jQuery("#banci").val()==""  )
				{
					alert('请输入版次！');				 
				}else{
				insertRelationships();
					 
				}
 			}
	</script>
  </head>
  
  <body>
  
<div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>新增移库申请单</a></li>
		</ul>
	</div>
	
</div>

<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2">
		<body:body>
		 <form id="form" action="add_transfer" method="post"   >  
		  <table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">添加移库申请单</th>
 
			 </tr>
		  </table>
			<table  class="add_table" >
				<tr>
					  <td align="right">移除库房位置：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="fromIdd" id="fromId">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>	
								
					<td align="right">移除库房名称：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="fromnames" id="fromname">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>	
			</tr>
			
			<tr>
				<td align="right">移入库房位置：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="toIdds" id="toId">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>	
					
				<td align="right">移入库房名称：</td>
				<td align="left">
							  	<select class="txt_box_130" name="tonames" id="toname">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>	
			</tr>
		</table>
		<table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold; width:80px;">移库批量增加</th>
					<th></th>
					<th width="70" align="center"><a href="javascript:void(0);" onclick="addStandardItem()">添加标准项</a></th>
			 </tr>
		</table>
		<table class="add_table" border="0" cellpadding="2" cellspacing="2" id="standardTable">
		  <thead>
		<tr>
					<th> 编号</th>
					<th>教材名称</th>
					<th>版次</th>
					<th>库存数量</th>
					<th>移库数量</th>
					<th>操作</th>
				</tr>
		  </thead>
		  <tbody></tbody>
		</table>

		<table class="add_table"><tr><td align="center"><input class="btn_black_61"   value="添加"   type="button"   onclick="yanzheng();"/></td></tr></table>
		
</form>
  
		</body:body>

		<table style="display:none" id="standardTemplate">
			<tbody>
				<tr>
				 <td class="stdNo" > </td >
				
				<td align="left">
							  	<select class="tfBookId" name="bookname" id="bookid">
							  		<option value="0">--请选择--</option>
							  	</select>
					 </td>	
					 <td align="left">
							  	<input type="text" class="tfPric" id="banci"  />
					 </td>		
					<td><input type="text" class="tfPrice" id="shuliang"  /></td>
					<td><input type="text" class="tfQuantity" name="" id="quantity" /></td>
					<td><a href="javascript:void(0);" onclick="removemeterial(this)">删除</a></td>
				</tr>
				 
			</tbody>
			 
		  </table>
		  
		
		  <div id="msgDiv" style="display:none">
		操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />
	</div>
  </div>
  </div>
  </div>


	


  </body>
</html>
 
