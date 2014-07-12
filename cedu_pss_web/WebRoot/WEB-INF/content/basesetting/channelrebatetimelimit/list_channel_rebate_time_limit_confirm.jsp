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
				title:'修改到账确认时间',
				buttons: {
					'确认': function() { 
						if(jQuery("#endTime").val()=="" || jQuery.trim(jQuery("#endTime").val())=="")
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
										ajax_100_1();//修改时间
										jQuery(this).dialog("close"); 
										jQuery('#message_update_time').dialog("close"); 
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
		});
		
		//修改
		function updatetime()
		{
			jQuery('#message_update_time').dialog("open");
		}
		
		//ajax回调函数     
		function ajax_update(data)
		{
			if(data.isback)
			{								
				jQuery("#spanendtime").html(data.channelRebateTimeLimit.endTime);
				jQuery("#showDialog").html('<b>修改成功！</b>');
				jQuery('#message_returns_tips').dialog("open"); 	
			}		    
			else
			{
			    jQuery("#showDialog").html('<b>修改失败！</b>');
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
				 	<th style="text-align:left; font-weight:bold;">招生返款到账确认时间</th>
					
				</tr>
			</table>
			<table class="gv_table_2" id="resultable" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th align="center">编号</th>
						<th align="center">到账确认时间</th>
						<th align="center">操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">1</td>
						<td align="center">
							<span id="spanendtime">
								<s:date name="channelRebateTimeLimit.endTime" format="yyyy-MM-dd"/>
							</span>
						</td>
						<td align="center"><a href="javascript:updatetime()">修改</a></td>
					</tr>
				</tbody>
			</table>
  		</body:body>
 	    <!--  弹出层           -->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="message_confirm" style="display:none">
			<div align="center"><b>确认修改到账确认时间么？</b></div>
		</div>
		<div id="message_update_time" style="display:none">
			<form id="myform">
				<table class="add_table">
					<tr>
						<td align="right">编号：</td>
						<td>
							<input type="text" value="1" name="jfcod" id="jfcod" readonly="readonly"/>
							<input type="hidden" value="${channelRebateTimeLimit.id}" name="channelRebateTimeLimit.id" id="jfcod" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td align="right"><span style="color:red">*</span>到账确认时间：</td>
						<td><input type="text" name="channelRebateTimeLimit.endTime" id="endTime" value="<s:date name="channelRebateTimeLimit.endTime" format="yyyy-MM-dd"/>" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" class="Wdate" readonly="readonly"/></td>
					</tr>	
				</table>
			</form>
		</div>
  </body>
</html>
