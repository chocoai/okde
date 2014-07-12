<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>个人日报明细表</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui_org" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<jc:plugin name="loading" />
		<jc:plugin name="calendar" />
		<script type="text/javascript">	
		
			
			//删除日志
			function del(id)
			{
				worklog_id=id;
				ajax_002_1();	
			}
			//删除日志_回调
			function del_callback(data){
				if(data.results){
			    	alert_default_dialog(3);
			    	searchpage();
			    }else alert_default_dialog(-3);
			}
			
			//提交日志
			function up(id)
			{
				worklog_id=id;
				ajax_001_1();	
			}
			//提交日志
			function up_callback(data){
				if(data.results){
			    	alert_default_dialog(3);
			    	searchpage();
			    }else alert_default_dialog(-3);
			}
			
			
		</script>
		
		<a:ajax successCallbackFunctions="up_callback" parameters="{'id':worklog_id}" urls="/worklog/submit_worklog" pluginCode="001"/>
		<a:ajax successCallbackFunctions="del_callback" parameters="{'id':worklog_id}" urls="/worklog/delete_worklog" pluginCode="002"/>
		
		<script type="text/javascript">		
			function getDate(date)
			{
				if(date!=null && date.length>=10){
					return date.substring(0,10);
				}
				return date;
			}
			
			function getStatus(status)
			{
				switch(status)
				{
					case 0:return "未提交";
					case 1:return "待审批";
					case 2:return "已审批";
				}
			}
			
			function todo(id,status)
			{
				var s="";
				if(0==status)
				{
					s+='&nbsp;<a href="<s:url value="update_worklog" />?id='+id+'" >修改</a>';
					s+='&nbsp;<a href="javascript:void(0)" onclick="up('+id+')">提交审批</a>';
					s+='&nbsp;<a href="javascript:void(0)" onclick="del('+id+')">删除</a>';
				}
				return s;
			}
			
			
			function todo(records,status,id)
			{
				var str="";
				str += '&nbsp;<a target="_blank" href="<s:url value="/worklog/view_worklog" />?id=' + id
					+ '" >查看</a>';
				//已审批
				if(status==2){
					if (records != null&&records.length!=0) {
							str += '&nbsp;<a id="a_' + id
								+ '" href="javascript:showAuditRecord(' + id
								+ ')" >评价记录(展开)</a>';
						$(records).each(function() {
								str += "<tr style='display:none;' class='tr_record_"+id+"'><td colspan='6' style='padding-left:10px;background:#FFFFE0;'>审批人："
									+ (this.auditUser==null?"未知":this.auditUser.fullName)
									+ "<br/>评分："
									+ this.score
									+ "<br/>意见：<div style='padding-left:40px'>"
									+ this.content + "</div></td></tr>"
						});
					}
				}else{
					if(0==status)
					{
						str+='&nbsp;<a href="<s:url value="update_worklog" />?id='+id+'" >修改</a>';
						str+='&nbsp;<a href="javascript:void(0)" onclick="up('+id+')">提交审批</a>';
						str+='&nbsp;<a href="javascript:void(0)" onclick="del('+id+')">删除</a>';
					}
				}			
				
				
				return str;
			}
			
			function getYuanjian(original)
			{
				if(null!=original)
				{
					return original.title;
				}
				return "无";
			}
			//显示审批记录
			function showAuditRecord(id){
				if($(".tr_record_"+id).css('display')=='table-row'){
					$(".tr_record_"+id).css({'display':'none'});
					$("#a_"+id).html("评价记录(展开)");
				}else{
					$(".tr_record_"+id).css({'display':'table-row'});
					$("#a_"+id).html("评价记录(收缩)");
				}
				
				
			}
			function contentValue(content){
				return '<div style="text-align: left; padding-left:5px;overflow:auto;">'+content+'</div>';
			}
			
			$(document).ready(function(){
					$("#starttime").val(getFirstDay());
					$("#endtime").val(new Date().pattern("yyyy-MM-dd")); 
			});
			
		</script>
		
	</head>
  
  <body>	
		<!--头部层开始 -->
		<head:head title="个人日报明细表">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div>
					<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">开始日期：</td>
							<td><input id="starttime" class="Wdate" type="text" value="" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" size="17"></td>
							<td class="lable_100">结束日期：</td>
							<td><input id="endtime" class="Wdate" type="text" value="" size="17" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"></td>
							<!-- 
							<td><input type="button" id="add_sub" value="新增" class="btn_black_61" onclick="javascript:location.href='<s:url value="add_worklog" />'" /></td>
							 -->
							<td><input type="button" value="查询" onclick="searchpage();" class="btn_black_61" /></td>
						</tr>
					</table>
				</div>
				<!--Search End-->
				<div id="dataDiv1" style="display:block;">
				
					<table class="gv_table_2">
						<tr>
							<th style="width:20px;"><img src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" /></th>
							<th style="text-align:left; font-weight:bold;">日报列表：</th>					
						</tr>
					</table>
				
					<page:plugin 
						
						pluginCode="page"
						il8nName="admin"
						searchListActionpath="page_list_worklog_person"
						searchCountActionpath="page_count_worklog_person"
						columnsStr="createOn;createUser.fullName;content;status;#todo"
						customColumnValue="0,getDate(createOn);2,contentValue(content);3,getStatus(status);4,todo(records,status,id)"
						pageSize="13"
						ontherOperatingWidth="80px"
						params="'starttime':$('#starttime').val(),'endtime':$('#endtime').val(),'worklog.createBy':${userTicket.userId }"
						isOrder="false"
					/>
				</div>			
			</body:body>
  </body>
</html>
