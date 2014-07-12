<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<!--  jquery库 -->
	<jc:plugin name="jquery" />		
    <!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 页签 -->	
	<jc:plugin name="tab" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />		
	<!-- ajax 载入页面特效 -->
	<jc:plugin name="loading"/> 
	<!-- 时间控件 -->
	<jc:plugin name="calendar" />
	<!--  分页 -->
	<jc:plugin name="page" />
    <script type="text/javascript">  
    	jQuery(document).ready(function(){
			//信息提示
			jQuery('#message_returns_tips').dialog({
				autoOpen:false,
				modal:true,
				draggable:false,
				resizable:false,
				title:'信息提示',
				buttons: {
					'关闭': function() { 
						jQuery(this).dialog("close"); 
					} 
				}
			});	
			//修改日期提示
			jQuery('#message_update_time').dialog({
				autoOpen:false,
				modal:true,
				draggable:false,
				resizable:false,
				title:'修改招生返款期',
				buttons: {
					'确认': function() { 
						if(jQuery("#rebateName").val()=="" || jQuery.trim(jQuery("#rebateName").val())=="")
						{
							jQuery("#showDialog").html('<b>请填写招生返款批次名称！</b>');
							jQuery('#message_returns_tips').dialog("open"); 	
						}
						else if(jQuery("#startTime").val()=="" || jQuery.trim(jQuery("#startTime").val())=="")
						{
							jQuery("#showDialog").html('<b>请选择返款期开始时间！</b>');
							jQuery('#message_returns_tips').dialog("open"); 	
						}
						else if(jQuery("#endTime").val()=="" || jQuery.trim(jQuery("#endTime").val())=="")
						{
							jQuery("#showDialog").html('<b>请选择返款期结束时间！</b>');
							jQuery('#message_returns_tips').dialog("open"); 	
						}
						else if(jQuery("#ceduConfirmTime").val()=="" || jQuery.trim(jQuery("#ceduConfirmTime").val())=="")
						{
							jQuery("#showDialog").html('<b>请选择到账确认时间！</b>');
							jQuery('#message_returns_tips').dialog("open"); 	
						}
						else
						{
							jQuery('#message_confirm').dialog({
								title:'确认操作',
								buttons: {
									'确认': function() { 
										jQuery("#xueMoney").val(dealwithmoney(jQuery("#xueMoney").val()));
										ajax_100_1();//修改时间
										jQuery(this).dialog("close"); 
										//jQuery('#message_update_time').dialog("close"); 
									}, 
									'取消': function() { 
										jQuery(this).dialog("close"); 
										jQuery('#message_update_time').dialog("close"); 
									} 
								}
							});
							jQuery('#message_confirm').dialog("open");
							
						}
							
					}, 
					'取消': function() { 
						jQuery(this).dialog("close"); 
					} 
				}
			});	
			//添加日期提示
			jQuery('#message_add_time').dialog({
				autoOpen:false,
				modal:true,
				draggable:false,
				resizable:false,
				title:'添加招生返款期',
				buttons: {
					'确认': function() { 
						if(jQuery("#addrebateName").val()=="" || jQuery.trim(jQuery("#addrebateName").val())=="")
						{
							jQuery("#showDialog").html('<b>请填写招生返款批次名称！</b>');
							jQuery('#message_returns_tips').dialog("open"); 	
						}
						else if(jQuery("#addstartTime").val()=="" || jQuery.trim(jQuery("#addstartTime").val())=="")
						{
							jQuery("#showDialog").html('<b>请选择返款期开始时间！</b>');
							jQuery('#message_returns_tips').dialog("open"); 	
						}
						else if(jQuery("#addendTime").val()=="" || jQuery.trim(jQuery("#addendTime").val())=="")
						{
							jQuery("#showDialog").html('<b>请选择返款期结束时间！</b>');
							jQuery('#message_returns_tips').dialog("open"); 	
						}
						else if(jQuery("#addceduConfirmTime").val()=="" || jQuery.trim(jQuery("#addceduConfirmTime").val())=="")
						{
							jQuery("#showDialog").html('<b>请选择到账确认时间！</b>');
							jQuery('#message_returns_tips').dialog("open"); 	
						}
						else
						{
							jQuery('#message_confirm_add').dialog({
								title:'确认操作',
								buttons: {
									'确认': function() { 
										jQuery("#addxueMoney").val(dealwithmoney(jQuery("#addxueMoney").val()));
										ajax_120_1();//添加时间
										jQuery(this).dialog("close"); 
										//jQuery('#message_add_time').dialog("close"); 
									}, 
									'取消': function() { 
										jQuery(this).dialog("close"); 
										jQuery('#message_add_time').dialog("close"); 
									} 
								}
							});
							jQuery('#message_confirm_add').dialog("open");
						}	
					}, 
					'取消': function() { 
						jQuery(this).dialog("close"); 
					} 
				}
			});	
			
		});
		
		//显示启用、停用
		function showisusing(isUsing)
		{
			if(isUsing==STATUS_ENABLED)
			{	
				return "启用";
			}
			else
			{
				return "停用";
			}
		}
		
		//处理输入的钱是否正确
		function showcheckmoney(id)
		{
			if(dealwithmoney(jQuery("#"+id).val())==-1)
			{
				jQuery("#"+id).val("");
				jQuery("#showDialog").html('<b>学费下限输入格式不正确，只能输入整数和小数！</b>');
				jQuery('#message_returns_tips').dialog("open");
			}
		}
		
		//操作
		function showoperating(id,isUsing,count)
		{
			if(isUsing==STATUS_DISABLE)
			{	
				return "<a href='javascript:updatestatus("+id+","+count+")'>启用</a>";
			}
			else
			{
				//isPageOperating(id,601,"update");
				return "";
			}
		}
		
		//启用
		function updatestatus(id,count)
		{
			//alert(count);
			if(count>0)
			{
				jQuery("#showDialog").html('<b>已经启用的招生返款期内还有'+count+'个招生返款单未汇款(或删除),不能启用新招生返款期！</b>');
				jQuery('#message_returns_tips').dialog("open");
			}
			else
			{
				jQuery('#message_confirm_using').dialog({
					title:'确认操作',
					buttons: {
						'确认': function() { 
							crtlId=id;
							ajax_130_1();
							jQuery(this).dialog("close"); 
						}, 
						'取消': function() { 
							jQuery(this).dialog("close"); 
						} 
					}
				});
				jQuery('#message_confirm_using').dialog("open");
			}
		}
		
		//修改
		function updatetime(id)
		{
			crtlId=id;
			ajax_110_1();
			//jQuery("#startTime").focus(function(){
			//	WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'});
			//	$("#startTime").trigger("blur");
			//});
			
		}
		
		//添加
		function addtime()
		{
			jQuery("#addrebateName").val("");
			jQuery("#addstartTime").val("");
			jQuery("#addendTime").val("");
			jQuery("#addceduConfirmTime").val(""); 
			jQuery("#addxueMoney").val(""); 
			jQuery('#message_add_time').dialog("open");
		}
		
		//ajax回调函数     修改
		function ajax_update(data)
		{
			if(data.ccount>0)
			{
				jQuery("#showDialog").html('<b>招生返款期时间段和其他返款期时间重复！</b>');
				jQuery('#message_returns_tips').dialog("open"); 
			}
			else if(data.isback)
			{	
				//alert(data.ccount);
				search601();							
				jQuery("#showDialog").html('<b>修改成功！</b>');
				jQuery('#message_returns_tips').dialog("open"); 	
				
				jQuery('#message_update_time').dialog("close");
			}		    
			else
			{
			    jQuery("#showDialog").html('<b>修改失败！</b>');
				jQuery('#message_returns_tips').dialog("open");
				
				jQuery('#message_update_time').dialog("close");
			}
		}			
		
		//ajax回调函数     查询
		var crtlId=0;
		function ajax_find(data)
		{
			if(data.channelRebateTimeLimit!=null)
			{				
				jQuery("#rebateName").val(data.channelRebateTimeLimit.rebateName);	
				jQuery("#crtlId").val(data.channelRebateTimeLimit.id);				
				jQuery("#startTime").val(data.channelRebateTimeLimit.startTime);
				jQuery("#endTime").val(data.channelRebateTimeLimit.endTime);
				jQuery("#ceduConfirmTime").val(data.channelRebateTimeLimit.ceduConfirmTime);
				jQuery("#xueMoney").val(data.channelRebateTimeLimit.xueMoney);	
				jQuery('#message_update_time').dialog("open");
			}		
			else
			{
				jQuery("#showDialog").html('<b>该招生返款期不能修改！</b>');
				jQuery('#message_returns_tips').dialog("open");
			}    	
		}	
		
		//ajax回调函数     添加
		function ajax_add(data)
		{
			if(data.ccount>0)
			{
				jQuery("#showDialog").html('<b>招生返款期时间段和其他返款期时间重复！</b>');
				jQuery('#message_returns_tips').dialog("open"); 
			}
			else if(data.isback)
			{	
				//alert(data.ccount);
				search601();							
				jQuery("#showDialog").html('<b>添加成功！</b>');
				jQuery('#message_returns_tips').dialog("open"); 
				
				jQuery('#message_add_time').dialog("close");	
			}		    
			else
			{
			    jQuery("#showDialog").html('<b>添加失败！</b>');
				jQuery('#message_returns_tips').dialog("open");
				
				jQuery('#message_add_time').dialog("close");
			}
		}
		
		//ajax回调函数     启用
		function ajax_using(data)
		{
			if(data.isback)
			{	
				search601();							
				jQuery("#showDialog").html('<b>操作成功！</b>');
				jQuery('#message_returns_tips').dialog("open"); 	
			}		    
			else
			{
			    jQuery("#showDialog").html('<b>操作失败！</b>');
				jQuery('#message_returns_tips').dialog("open");
			}
		}									
		
    </script>
    
    <!--修改-->
	<a:ajax 
		pluginCode="100"
		successCallbackFunctions="ajax_update" 
		parameters="jQuery('#myform').serializeObject()" 
		urls="/basesetting/channelrebatetimelimit/update_channel_rebate_limit_ajax" 			
	/>
	
	<!--查询-->
	<a:ajax 
		pluginCode="110"
		successCallbackFunctions="ajax_find" 
		parameters="{id:crtlId}" 
		urls="/basesetting/channelrebatetimelimit/find_channel_rebate_limit_by_id_ajax" 			
	/>
	
	<!--添加-->
	<a:ajax 
		pluginCode="120"
		successCallbackFunctions="ajax_add" 
		parameters="jQuery('#addmyform').serializeObject()" 
		urls="/basesetting/channelrebatetimelimit/add_channel_rebate_limit_ajax" 			
	/>
	
	<!--启用-->
	<a:ajax 
		pluginCode="130"
		successCallbackFunctions="ajax_using" 
		parameters="{id:crtlId}" 
		urls="/basesetting/channelrebatetimelimit/update_channel_rebate_limit_using_ajax" 			
	/>
	
  </head>
  
  <body>
  		<!--头部层开始 -->
		<head:head title="基础设置">
		</head:head>
		<body:body>
			<div>
				<s:include value="../common/menu.jsp" />
			</div>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
				 	<th style="text-align:left; font-weight:bold;">招生返款期设置</th>
					<th style="text-align:right; font-weight:bold;"><div id="conmenu"><a id="add_monitor_rlt" href="javascript:addtime()"><img src="<ui:img url="images/title_menu/icon_add.gif" />" />增加</a></div></th>
				</tr>
			</table>
			<page:plugin 
				pluginCode="601"
				il8nName="basesetting"
				searchListActionpath="list_channel_rebate_time_limit_page_ajax"
				searchCountActionpath="count_channel_rebate_time_limit_page_ajax"
				columnsStr="rebateName;startTime;endTime;ceduConfirmTime;xueMoney;isusing;#public.operating"
				customColumnValue="5,showisusing(isUsing);6,showoperating(id,isUsing,count)"
				pageSize="10"
				isPage="true"
				isNumber="false"
				isChecked="false"
				update="json,updatetime,id"													
				params="'result.order':'startTime','result.sort':'desc'"
			/>
	
  		</body:body>
 	    <!--  弹出层           -->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="message_confirm" style="display:none">
			<div align="center"><b>确认修改招生返款期么？</b></div>
		</div>
		<div id="message_update_time" style="display:none">
			<form id="myform">
				<table class="add_table">
					<tr>
						<td align="right"><span style="color:red">*</span>招生返款批次：</td>
						<td>
							<input type="text"  name="channelRebateTimeLimit.rebateName" id="rebateName" />
							<input type="hidden" name="channelRebateTimeLimit.id" id="crtlId"/>
						</td>
					</tr>
					<tr>
						<td align="right"><span style="color:red">*</span>返款期开始时间：</td>
						<td><input type="text" name="channelRebateTimeLimit.startTime" id="startTime"  onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly"/></td>
					</tr>
					<tr>
						<td align="right"><span style="color:red">*</span>返款期结束时间：</td>
						<td><input type="text" name="channelRebateTimeLimit.endTime" id="endTime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly"/></td>
					</tr>	
					<tr>
						<td align="right"><span style="color:red">*</span>到账确认时间：</td>
						<td><input type="text" name="channelRebateTimeLimit.ceduConfirmTime" id="ceduConfirmTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly"/></td>
					</tr>
					<tr>
						<td align="right">学费下限：</td>
						<td>
							<input type="text" onkeyup="javascript:showcheckmoney('xueMoney');" name="channelRebateTimeLimit.xueMoney" id="xueMoney" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="message_add_time" style="display:none">
			<form id="addmyform">
				<table class="add_table">
					<tr>
						<td align="right"><span style="color:red">*</span>招生返款批次：</td>
						<td>
							<input type="text" value="" name="channelRebateTimeLimit.rebateName" id="addrebateName" />
						</td>
					</tr>
					<tr>
						<td align="right"><span style="color:red">*</span>返款期开始时间：</td>
						<td><input type="text" name="channelRebateTimeLimit.startTime" id="addstartTime" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'addendTime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly"/></td>
					</tr>
					<tr>
						<td align="right"><span style="color:red">*</span>返款期结束时间：</td>
						<td><input type="text" name="channelRebateTimeLimit.endTime" id="addendTime" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'addstartTime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly"/></td>
					</tr>	
					<tr>
						<td align="right"><span style="color:red">*</span>到账确认时间：</td>
						<td><input type="text" name="channelRebateTimeLimit.ceduConfirmTime" id="addceduConfirmTime" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly"/></td>
					</tr>
					<tr>
						<td align="right">学费下限：</td>
						<td>
							<input type="text" onkeyup="javascript:showcheckmoney('addxueMoney');" name="channelRebateTimeLimit.xueMoney" id="addxueMoney" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="message_confirm_add" style="display:none">
			<div align="center"><b>确认添加招生返款期么？</b></div>
		</div>
		<div id="message_confirm_using" style="display:none">
			<div align="center"><b>确认启用该招生返款期么？</b><br/>(其他启用的招生返款期将停用)</div>
		</div>
  </body>
</html>
