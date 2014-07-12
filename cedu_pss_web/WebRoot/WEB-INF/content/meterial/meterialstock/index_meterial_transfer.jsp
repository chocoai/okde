<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>移库</title>

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
		<script type="text/javascript">
		 
		
		 jQuery(function(){
			    //物料级联
			 	jQuery('#meterialId').change(function(){
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
		 
		
	</script>
		<!-- 移出库房名称 -->
		<a:ajax pluginCode="140" successCallbackFunctions="ajax_roomnameajax"
			parameters="{fromId:jQuery('#fromId').val()}"
			urls="/meterial/meterialstock/find_meterialstoreroom_fromid_ajax" />
		<!-- 移出库房位置 -->
		<a:ajax pluginCode="139" successCallbackFunctions="ajax_postionajax"
			urls="/meterial/meterialstock/find_meterialstoreroom_ajax"
			isOnload="all" />
		<!-- 移入库房名称 -->
		<a:ajax pluginCode="138"
			successCallbackFunctions="ajax_toroomnameajax"
			parameters="{toId:jQuery('#toId').val()}"
			urls="/meterial/meterialstock/find_meterialstoreroom_toid_ajax" />
		<!-- 移入库房位置 -->
		<a:ajax pluginCode="137"
			successCallbackFunctions="ajax_toroompositionajax"
			urls="/meterial/meterialstock/find_meterialstoreroom_ajax"
			isOnload="all" />
		<!-- 物料分类 -->
		<a:ajax pluginCode="136" successCallbackFunctions="ajax_categoryajax"
			urls="/meterial/meterialstock/find_meterialcatrgory_ajax"
			isOnload="all" />
		<!-- 物料名称 -->
		<a:ajax pluginCode="135" successCallbackFunctions="ajax_meterialajax"
			parameters="{meterialId:jQuery('#meterialId').val()}"
			urls="/meterial/meterialstock/find_meterial_ajax" />

	</head>
	<body>
		<head:head title="移库">
			<html:a text="添加移库单" icon="add"
				href="/meterial/meterialstock/add_meterial_transfer" />
		</head:head>
		<div class="block">
			<div class="public_table_bg_766">
				<div class="tb_table_default_2">
					<!--Menu Begin-->
					<div>
						<div>
							<ul id="menu">
								<li>
									<a href="../meterialstock/index_meterialstock" id="first"
										title="">库存</a>
								</li>
								<li>
									<a href="../meterialstock/index_meterial_transfer" id="second"
										title="" class="current">移库</a>
								</li>
							</ul>
						</div>
					</div>
					<div style="height: 5px;"></div>
					<table class="add_table">
						<tr>
							<td align="right">
								移除库房位置：
							</td>
							<td align="left">
								<select class="txt_box_130" name="meterialtransfer.fromId"
									id="fromId">
									<option value="0">
										--请选择--
									</option>
								</select>
							</td>
							<td align="right">
								移除库房名称：
							</td>
							<td align="left">
								<select class="txt_box_130" name="meterialtransfer.fromname"
									id="fromname">
									<option value="0">
										--请选择--
									</option>
								</select>
							</td>
							<td align="right">
								物料分類：
							</td>
							<td align="left">
								<select class="txt_box_130" name="meterialid" id="meterialId">
							  		<option value="0">--请选择--</option>
							  	</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								移入库房位置：
							</td>
							<td align="left">
								<select class="txt_box_130" name="meterialtransfer.toId"
									id="toId">
									<option value="0">
										--请选择--
									</option>
								</select>
							</td>

							<td align="right">
								移入库房名称：
							</td>
							<td align="left">
								<select class="txt_box_130" name="meterialtransfer.name"
									id="toname">
									<option value="0">
										--请选择--
									</option>
								</select>
							</td>
							<td align="right">
								物料名称：
							</td>
							<td align="left">
								<select  name="meterialname" id="meterialnames" class="txt_box_130">
							  		<option value="0">--请选择--</option>
							  	</select>
							</td>
							<td align="left">
								<input name="" type="submit" class="btn_black_61" value="查询"
									onclick="searchpage();" />
							</td>
						</tr>
					</table>
					<table class="gv_table_2">
						<page:plugin pluginCode="page" il8nName="meterialtransfer"
							searchListActionpath="list_meterialtransfer"
							searchCountActionpath="count_meterialtransfer"
							columnsStr="fromplace;fromname;toplace;toname;meterialfen;meterialname;quantity"
							pageSize="3" ontherOperatingWidth="80px" isPageSize="3"
							params="'fromId':$('#fromId').val(),'fromname':$('#fromname').val(),'toId':$('#toId').val(),'meterialId':$('#meterialId').val(),'meterialname':$('#meterialnames').val()" />
					</table>
				</div>
			</div>
		</div>
	</body>
</html>


