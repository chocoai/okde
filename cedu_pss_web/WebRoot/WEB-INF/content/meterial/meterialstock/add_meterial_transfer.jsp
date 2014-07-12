<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>移库添加</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<jc:plugin name="loading" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- Tab 分页样式 -->
	<jc:plugin name="tab" />
	<!--  分页 -->
	<jc:plugin name="page" />
<style type="text/css">
	.stdCeil, .stdFloor { width: 70px; }
</style>

<script type="text/javascript">
 
	
	 jQuery(function(){
	 
	 addStandardItem();
			   //物料级联
			 	jQuery("select[name='meterialid']").change(function(){
		 		ajax_135_1();	
				});	
				
				//移出库房级联
				jQuery('#fromId').change(function(){
		 		ajax_140_1();
		 		 
				});	
				
					//移入库房级联
				jQuery('#toId').change(function(){
		 		ajax_138_1();
				});	
	
			});	
		 		 
			//标准项的添加
			function addStandardItem()
    	   {
	 	    var std = $('#standardTemplate tr').clone();
		     std.appendTo('#standardTable > tbody');
		     //chgUnit($('.stdValueForm', std));	
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
			
		 //ajax回调函数  物料分類
			function ajax_categoryajax(data)
			{
				// 物料分類
				jQuery("select[name='meterialid']").empty();
			    jQuery("select[name='meterialid']").append('<option value="0">--请选择--</option>');
			    if(data.catrgorylist!=null && data.catrgorylist.length>0)
			    {
			    	$.each(data.catrgorylist,function(){	
			    		jQuery("select[name='meterialid']").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			}
		 //ajax回调函数  物料名称
					function ajax_meterialajax(data)
 					{
		    	 		$("select[name='meterialname']").empty();
			    	 	$("select[name='meterialname']").append('<option value="0">--请选择--</option>');	
			    	 	if(data.meteriallist!=null&&data.meteriallist.length>0)
			    	 	{
				    	 	$.each(data.meteriallist,function(){	
				    	 		$("select[name='meterialname']").append('<option value="'+this.id+'">'+this.name+'</option>'); 
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
		 
			//ajax回调函数  移出库房Id查询库存
			//function ajax_roomquntion(data)
			//{
				// 库存
			//	jQuery("#kucun").empty();
			//    if(meterialStock!=null)
			//    {
			//    	$.each(meterialStock,function(){	
			//    		jQuery("#kucun").append('<input type="text" value="'+this.id+'"/>'); 
			//    	});
			//   	}
		//	} 
			
			//提交前的验证
			function show()
			{
				 if(jQuery("#fromId").val()==0)
				{
					alert('请选择移除库房位置！');
 
				}
				else if(jQuery("#fromname").val()==0)
				{
					alert('请选择移除库房名称！');
				}
				
				else if(jQuery("#toId").val()==0)
				{
					alert('请选择移入库房位置！');
				}
				
				else if(jQuery("#toname").val()==0)
				{
					alert('请选择移入库房名称！');
				}
				else if(jQuery("#meterialId").val()==0)
				{
					alert('请选择物料分类！');
				}
				else if(jQuery("#meterialname").val()==0)
				{
					alert('请选择物料名称！');
				}
				else if(jQuery("#quantity").val()==""  )
				{
					alert('请输入移库数量！');
					
					
				 
				}else{
				insertRelationships();
						jQuery("#submits").submit();
						}
			}
			
			
	</script>	
 
	<!-- 移出库房名称 -->
	    <a:ajax 
			pluginCode="140"
			successCallbackFunctions="ajax_roomnameajax" 
			parameters="{fromId:jQuery('#fromId').val()}"
			urls="/meterial/meterialstock/find_meterialstoreroom_fromid_ajax"
 		
		/>
		<!-- 移出库房位置 -->
		<a:ajax 
			pluginCode="139"
			successCallbackFunctions="ajax_postionajax" 
			urls="/meterial/meterialstock/find_meterialstoreroom_ajax"
			isOnload="all" 			
		/>
		<!-- 移入库房名称 -->
		<a:ajax 
			pluginCode="138"
			successCallbackFunctions="ajax_toroomnameajax" 
			parameters="{toId:jQuery('#toId').val()}"
			urls="/meterial/meterialstock/find_meterialstoreroom_toid_ajax"
 		
		/>
		<!-- 移入库房位置 -->
		<a:ajax 
			pluginCode="137"
			successCallbackFunctions="ajax_toroompositionajax" 
			urls="/meterial/meterialstock/find_meterialstoreroom_ajax"
			isOnload="all" 			
		/>
		<!-- 物料分类 -->
		<a:ajax 
			pluginCode="136"
			successCallbackFunctions="ajax_categoryajax" 
			urls="/meterial/meterialstock/find_meterialcatrgory_ajax"
			isOnload="all" 			
		/>
		<!-- 物料名称 -->
		<a:ajax 
			pluginCode="135"
			successCallbackFunctions="ajax_meterialajax" 
			parameters="{meterialId:jQuery('#meterialId').val()}"
			urls="/meterial/meterialstock/find_meterial_ajax"
		/>
		
		<!-- 移出id查询库存 
	    <a:ajax 
			pluginCode="134"
			successCallbackFunctions="ajax_roomquntion" 
			parameters="{fromId:jQuery('#fromId').val()}"
			urls="/meterial/meterialstock/find_stock_qunation" 		
		/> -->
 
<script type="text/javascript">
		 //添加标准项
		function addStandardItem()
		{
			var meterial = $('#standardTemplate tr').clone();
			meterial.appendTo('#standardTable > tbody');
			
			cooRN();
			number++;
		}
		var number = 1;
		
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
				$('.tfFromId', this).attr('name', 'meterialTransfer.fromId');
				$('.tfToId', this).attr('name', 'meterialTransfer.toId');
				$('.tfMeterialId', this).attr('class', 'tfMeterialId'+index+'');
				$('.tfQuantity', this).attr('class', 'tfQuantity'+index+'');
			});
		}
		 //测试方法 测试 数组取值是否正常
		 function seach()
		 {
		 			 
					var meterialid=[];
					var a=[];
					for(var i=0;i<$("select[class='tfMeterialId']").toArray().length-1;i++)
						{
						meterialid.push($("select[class='tfMeterialId'] option:selected").toArray()[i].text);
					    a.push($("input[class='tfQuantity"+i+"']").attr("value"));
						}
								alert(meterialid);
								alert(a);
				
				 
		 }
 
 function insertRelationships(){
 			var array=[];//选中的专题id数组
 			var quntion=[];//移库数量数组
 			var meterialIds = "";//选中的专题id拼接起来的字符串
 			var quntionIds="";//填写的物料移库数量拼接起来的字符串
 			var fromid=$("#fromId").val();
 			var toid=$("#toId").val();
 			//循环获取数组中的物料Id
 			if (number > 0) {
			for ( var i = 0; i < number; i++) {
				array.push($("select[class='tfMeterialId" + i + "']").attr("value"));
				quntion.push($("input[class='tfQuantity" + i + "']").attr("value"));
				}
				if(array.length==0){
						//未选中任何一个专题，直接点击选取按钮
						alert("至少选择一项");
				}else{
					//选中一个以上专题,选中的专题id拼接成字符串
					meterialIds=array.join(",");
					quntionIds=quntion.join(",");
					//通过表单实现js post提交
				document.write("<form action='add_meterial_transfer?array="+meterialIds+"&quntion="+quntionIds+"&fromid="+fromid+"&toid="+toid+"' method='post' name='lecturerForm' style='display:none'>");
				document.write("</form>");
				document.lecturerForm.submit();
 
				}
			}else{
				alert('错误');
			}
 		}
</script>

 
	<script type="text/javascript">
		
	</script>
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="移库增加">
			<html:a text="返回" icon="return" onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>

<form   action="add_meterial_transfer"    method="post"  >

<table  class="add_table" >
			 
				<tr>
					  <td align="right">移除库房位置：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="meterialTransfer.fromId" id="fromId">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>	
								
					<td align="right">移除库房名称：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="meterialTransfer.fromname" id="fromname">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>	
			</tr>
			
			<tr>
				<td align="right">移入库房位置：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="meterialTransfer.toId" id="toId">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>	
					
				<td align="right">移入库房名称：</td>
				<td align="left">
							  	<select class="txt_box_130" name="meterialTransfer.toname" id="toname">
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
					<th>物料分类</th>
					<th>物料名称</th>
					<th>库存数量</th>
					<th>移库数量</th>
					<th>操作</th>
				</tr>
		  </thead>
		  <tbody></tbody>
		</table>

		<table class="add_table"><tr><td align="center"><input class="btn_black_61"   value="添加"   type="button"   onclick="show();"/></td></tr></table>
		
</form>

		</body:body>

		<table style="display:none" id="standardTemplate">
			<tbody>
				<tr>
				 <td class="stdNo" > </td >
				
					<td align="left">
							  	<select class="tfMeterialId" name="meterialid" id="meterialId">
							  		<option value="0">--请选择--</option>
							  	</select>
					 </td>	
					 <td align="left">
							  	<select   name="meterialname" id="meterialnames">
							  		<option value="0">--请选择--</option>
							  	</select>
					 </td>	
					<td><input type="text" class="txt_box_130" id="kucun"  /></td>
					<td><input type="text" class="tfQuantity" name="" id="quantity" /></td>
					<td><a href="javascript:void(0);" onclick="removemeterial(this)">删除</a></td>
				</tr>
				 
			</tbody>
			 
		  </table>
  </body>
</html>
	