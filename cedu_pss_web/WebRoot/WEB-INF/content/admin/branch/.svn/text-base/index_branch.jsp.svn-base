<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学习中心设置</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />		
			
		<script type="text/javascript">
		
		
		//添加学习中心
		function add()
		{
			var parentId=jQuery('#parentId').val();
			var name=jQuery('#name').val();
			var shortName=jQuery('#short_name').val();
			jQuery.post('<s:url value="add_branch"/>', 
					{
						"parentId":parentId,
						"name":name,
						"shortName":shortName
					},
			        function(data)
			    	{
			    		if(data.results)
			    		{
			    			show('showDialog','添加成功!',150,100);	
			    			reloadparent();
			    			searchpage();
			    		}
			    		else
			    		{
			    			show('showDialog','添加失败!',150,100);	
			    		}	
			    	},
			 "json");		
		}
		
		//删除学习中心
		function del(id)
		{
			jQuery.post('<s:url value="delete_branch"/>',{"id":id},
			        function(data)
			    	{
			    		if(data.results)
			    		{
			    			show('showDialog','删除成功!',150,100);
			    			searchpage();
			    			reloadparent();
			    		}
			    		else show('showDialog','删除失败!',150,100);	
			    	},
			 "json");	
		}
		
		//显示修改权限窗体
		function showupdate(id)
		{
			jQuery.post('<s:url value="show_branch"/>', 
					{
						"id":id
					},
			        function(data)
			    	{
			    		if(null!=data.branch)
			    		{
			    			jQuery('#upId').val(id);
			    			jQuery('#upName').val(data.branch.name);
			    			jQuery('#upShortName').val(data.branch.shortName);
			    			jQuery("#upParentId option[value='"+data.branch.parentId+"']").attr("selected","selected");
			    			show('updateDialog','修改',320,180);
			    		}
			    		else alert("无此学习中心!");
			    	},
			 "json");	
		}
		
		//修改学习中心
		function update()
		{
			jQuery.post('<s:url value="update_branch"/>', 
					{
						"id":jQuery('#upId').val(),
						"name":jQuery('#upName').val(),
						"shortName":jQuery('#upShortName').val(),
						"parentId":jQuery('#upParentId').find('option:selected').val()
					},
			        function(data)
			    	{
			    		if(data.results)
			    		{
			    			closes('updateDialog');
			    			show('showDialog','修改成功!',150,100);
			    			searchpage();
			    			reloadparent();
			    		}
			    		else show('showDialog','修改失败!',150,100);	
			    	},
			 "json");	
		}
		
		
		//更新父机构下拉菜单
		function reloadparent()
		{
			jQuery.post('<s:url value="list_branch"/>',{type:${types}},
			        function(data)
			    	{
			    		var lists="";
			    		jQuery.each(data.list, function()
		    			{		    			
     		    			 lists+="<option value='"+this.id+"'>"+this.name+"</option>";
		    			});
		    			jQuery('#parentId').html(lists);
		    			jQuery('#upParentId').html(lists);
			    	},
			 "json");	
		}
		
		</script>
	</head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="学习中心设置">
			<html:a text="树状显示" href="admin/branch/tree_branch" icon="receive" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div>
					<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">学习中心名称:</td>
							<td><input type="text" id="name" /></td>
							<td class="lable_100">简称:</td>
							<td><input type="text" id="short_name" /></td>
							<td class="lable_100">上级机构: </td>
							<td><s:select id="parentId" list="plist" listKey="id" listValue="name" theme="simple" /></td>
							<td></td>
							<td>
								<input type="button" value="新增" onclick="add();" class="btn_black_61" />
								<input type="button" value="查询" onclick="searchpage();" class="btn_black_61" />
							</td>
						</tr>
					</table>

				</div>
				<!--Search End-->
				<div id="dataDiv1" style="display:block;">
				
					<table class="gv_table_2">
						<tr>
							<th style="width:20px;"><img src="<s:url value="/plugin/base_css/images/operating/title_left.gif" />" /></th>
							<th style="text-align:left; font-weight:bold;">学习中心列表：</th>					
						</tr>
					</table>
				
					<page:plugin 
						pluginCode="page"
						il8nName="admin"
						searchListActionpath="page_list_branch"
						searchCountActionpath="page_count_branch"
						columnsStr="name;shortName;parent.name;"
						pageSize="10"
						
						update="json,showupdate,id"
						ontherOperatingWidth="80px"
						params="'branch.parentId':$('#parentId').val(),'name':$('#name').val(),'shortName':$('#short_name').val()"
					/>
				</div>			
			
			<div class="tb_table_default_2" id="treeview" style="display:none">
				<ul id="treeDemo" class="tree"></ul>
			</div>
		</body:body>
	<!-- 修改 -->
	<div id="updateDialog" style="display:none">
		<table class="add_table">
			<tr>
				<td class="lable_100">学习中心名称：</td>
				<td><input type="txt" class="txt_box_150" id="upName" name="upName" /></td>
			</tr>
			<tr>
				<td class="lable_100">简称：</td>
				<td><input type="txt" class="txt_box_150" id="upShortName" name="upShortName" /></td>
			</tr>
			<tr>
				<td class="lable_100">上级机构：</td>
				<td><s:select id="upParentId" name="upParentId" list="plist" listKey="id" listValue="name" theme="simple" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input id="upId" type="hidden" />
					<input type="button" class="btn_black_61" name="save" onclick="update();" value="修改"/>
					<input type="button" class="btn_black_61" value="关闭" onclick="closes('updateDialog');" />
				</td>
			</tr>
		</table>
	</div>
	
	<!-- 添加结果 -->
	<div id="showDialog" style="display:none"><table class="add_table"><tr><td colspan="2" align="center"><input type="button" class="btn_black_61" value="关闭" onclick="closes('showDialog');" /></td></tr></table></div>
	
  </body>
</html>
