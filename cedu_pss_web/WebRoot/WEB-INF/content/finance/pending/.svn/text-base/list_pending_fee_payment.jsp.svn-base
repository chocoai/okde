<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>生成待缴费单</title>
		
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

			//加载事件
			jQuery(function(){
				
				//初始化信息弹出框
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
				//确认操作
				jQuery('#message_confirm').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'生成待缴费单',
					buttons: {
						'确认': function() { 
							ajax_110_1();//生成待缴费单
						}, 
						'取消': function() { 
							jQuery(this).dialog("close"); 
						} 
					}
				});
							
			});
			
			//生成待缴费单
			function addpendingfee(aId,fId)
			{
				academyId=aId;
				feeSubjectId=fId;
				jQuery('#message_confirm').dialog("open");
			}
		
			
			//ajax回调函数  院校_费用科目
			function ajax_academy_feeSubject(data)
			{		
				$('#pendingtab > tbody').empty();
				var list='';
				var academyId=0;	
				if(data.feeSubjectList!=null && data.feeSubjectList.length>0 && data.academyList!=null && data.academyList.length>0)
			   	{	
			   		list+='<tr>';
					list+='<th>院校/费用科目</th>'
			   		$.each(data.feeSubjectList,function()
				    {	
					    list+='<th>'+this.name+'</th>'					    
					});
					list+='<th>操作</th>'
					list+='</tr>';
					
					$('#pendingtab >thead').html(list);
					list="";
					$.each(data.academyList,function()
					{	
						list+='<tr>';
						list+='<td align="center">'+this.name+'</td>'	
						academyId=this.id;
						$.each(data.feeSubjectList,function()
				    	{
				    		list+='<td align="center"><a href="javascript:addpendingfee('+academyId+','+this.id+')">生成</a></td>'
				    	});
				    	list+='<td align="center"><a href="javascript:addpendingfee('+academyId+','+'-1)">生成本院校所有待缴费单</a></td>'
						list+='</tr>';
					});					
					
				}
				else
				{
					list+='<tr><td align="center">没有找到相关数据!</td></tr>';
				}
				$('#pendingtab > tbody').html(list);
			}
			//ajax回调函数  生成待缴费单
			var academyId=0;
			var feeSubjectId=0;
			function ajax_add_pending(data)
			{	
				jQuery('#message_confirm').dialog("close");
				if(data.isfail)
			    {
			    	jQuery("#showDialog").html('<b>生成成功！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }
			    else
			    {
			    	jQuery("#showDialog").html('<b>生成失败(可能是已经生成过或没有要生成的数据)！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }			
			}
					
		</script>
		<!--加载院校和费用科目列表-->
		<a:ajax isOnload="all" successCallbackFunctions="ajax_academy_feeSubject" parameters="{}" urls="/finance/pending/find_academy_feesubject_list_ajax" pluginCode="100"/>
		
		<!--生成待缴费单-->
		<a:ajax successCallbackFunctions="ajax_add_pending" parameters="{academyId:academyId,feeSubjectId:feeSubjectId}" urls="/finance/pending/add_batch_pending_payment_ajax" pluginCode="110"/>
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="生成待缴费单">
			<!--<html:a text="生成所有待缴费单" icon="add" onclick="addpendingfee(-1,-1)"/>-->
		</head:head>
		<!--主体层开始 -->
		<body:body>
				
			<table class="gv_table_2">
			  	<tr>
					 <th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 <th style="text-align:left; font-weight:bold;" class="feehtml">生成待缴费单</th>						
				</tr>
			</table>
			<table class="gv_table_2" id="pendingtab">
				<thead>
				</thead>
		       <tbody>
		       		
		       </tbody>         
			</table>
							
		</body:body>
	
	<!-- 弹出层 -->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
 	<div id="message_confirm" style="display:none">
		<div align="center">确认生成么？</div>
	</div>
  </body>
</html>
