<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<head>
		<title>权限管理</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<script type="text/javascript">
			
			function findGroupByBranchId_callback(data)
			{
				var lists="";
				if(null==data.glist||0==data.glist.length)
				{
			    	lists="<tr><td>无用户组!</td></tr>";
			    }
			    else
			    {
			    	$.each(data.glist, function()
			    	{		    		
	     		    	lists+='<tr>';
						lists+=		'<td><input type="radio" onclick="findGroupUserByGroupId(this.value)" name="rgroup" id="g'+this.id+'" value="'+this.id+'" />'+this.name+'</input></td>';
						lists+='</tr>';
			    	});
			    }
			    jQuery('#group_list').html(lists);
			}
			
			function findGroupUserByGroupId(value)
			{
				$("input[name='uidlist']").attr("checked",false);
				group_id=value;
				$('#gId').val(value);				
				ajax_003_1();
			}
			
			function findGroupUserByGroupId_callback(data)
			{
				if(null!=data.ugulist)
				{
					$.each(data.ugulist, function()
			    	{		    		
	     		    	jQuery('#u'+this.userId).attr("checked",true);
			    	});
				}
			}
			
			function saveGroupUser()
			{
				if(-1==$('#gId').val())
				{
					alert_dialog("请选择用户组!");
				}
				else ajax_004_1();
			}
			
			function saveGroupUser_callback(data)
			{
				if(data.results) alert_dialog("保存成功");
				else alert_dialog("保存失败");
				
			}
		</script>
		<a:ajax successCallbackFunctions="findGroupUserByGroupId_callback" pluginCode="003" parameters="{'groupId':group_id}" urls="/admin/user/groupuser/list_group_user"/>
		<a:ajax successCallbackFunctions="saveGroupUser_callback" pluginCode="004" traditional="true" parameters="$('#up_form').serializeObject()" urls="/admin/user/groupuser/update_group_user"/>
		
		<script type="text/javascript">
		
		$(document).ready(function(){
			init_result_dialog();
		})	
		
		function checkAll(chec)
		{
			$("input[name='uidlist']").each(function()
			{
				this.checked=chec.checked;
			});
		}	
		</script>
	</head>
  
  <body>
    	<!--头部层开始 -->
		<head:head title="权限管理">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<div style="width:100%; height:4px; background-color:#3394C1;"></div>
			<div id="leftDiv"  style="float:left; width:23%;">
				<table class="gv_table_2">
					<tr>
						<th style="width:38px;">机构：</th>
						<th style="text-align:left; font-weight:bold;">${branch.name }</th>
					</tr>
					<tr>
						<th style="width:20px;"><img src="<ui:img url="/images/icon_title_return.jpg"/>" /></th>
						<th style="text-align:left; font-weight:bold;">用户组</th>
					</tr>
					<tr>
						<table id="group_list" class="add_table">
							<s:iterator value="uglist" var="group" status="i">
								<tr>
									<td><input type="radio" onclick="findGroupUserByGroupId(this.value)" name="rgroup" id="g${group.id}" value="${group.id }" />${group.name }</input></td>
								</tr>
							</s:iterator>
						</table>
					</tr>					
				</table>
			</div>

			
			<div style="float:left;width:4px; height:500px; background-color:#3394C1; margin-left:2px; margin-right:2px;">
			</div>
				
			<div id="leftDiv"  style="float:left;  width:75%;">
				<table class="gv_table_2">
					<tr>
						<th style="width:20px;"><img src="<ui:img url="/images/icon_title_return.jpg"/>" /></th>
						<th style="text-align:left; font-weight:bold;">用户</th>
						<th>
							<div style="float:right;">
								<input type="checkbox" id="checkall" onclick="checkAll(this)" />
								全选
							</div>
						</th>						
					</tr>
				</table>
				<form id="up_form">
				<table id="user_list" class="add_table">
				<tr>
					<s:set id="ucount" value="0" />
					<s:iterator value="ulist" var="user">
						<s:if test='#ucount==6'>
							</tr><tr>
							<s:set id="ucount" value="0" />
						</s:if>
						<td><input type="checkbox" id="u${id}" name="uidlist" value="${id}"/>${fullName }</td>
						<s:set id="ucount" value="#ucount+1" />
					</s:iterator>	
				</tr>
				<tr>
					<td colspan='<m:il8n key="check.user.count" il8nName="admin"/>' align="center">
						<input type="button" id="up_sub" value="保存" onclick="saveGroupUser()" />
					</td>
				</tr>
				</table>
				<input type="hidden" id="gId" name="gId" value="-1"/>
				</form>
			</div>
		</body:body>
  </body>
</html>
