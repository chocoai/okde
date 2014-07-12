<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<head>
		<title>角色设置</title>
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
		<jc:plugin name="loading" />
		<jc:plugin name="validator" />
		<script type="text/javascript">	
			//增加子系统_回调
			function add_callback(data){
				if(data.results){
			    	alert_default_dialog(1);
			    	searchpage();
			    	jQuery('#name').val("");
			    }else alert_default_dialog(-1);
			}
			
			//删除子系统
			function del(id)
			{
				modularid=id;
				ajax_002_1();	
			}
			//删除子系统_回调
			function del_callback(data){
				if(data.results){
			    	alert_default_dialog(3);
			    	searchpage();
			    }else alert_default_dialog(-3);
			}
			
			//修改前初始化
			function view_update(id)
			{
				user_group_id=id;
				ajax_003_1();
			}			
			//修改前初始化_回调
			function view_update_callback(data)
			{
				if(null!=data.role)
			    {
			    	jQuery('#upName').val(data.role.name);
			    	jQuery('#upId').val(data.role.id);
			    	show('updateDialog','修改',300,150);
			    }
			    else alert_default_dialog(0);
			}

			//修改_回调
			function update_callback(data)
			{
				if(data.results)
			    {
			    	closes('updateDialog');
			    	alert_default_dialog(2);
			    	searchpage();
			    }
			    else alert_default_dialog(-2);
			}
		</script>
		<a:ajax successCallbackFunctions="add_callback" parameters="$('#add_form').serializeObject()" urls="/admin/role/add_role" pluginCode="001"/>
		<a:ajax successCallbackFunctions="del_callback" parameters="{'id':modularid}" urls="/admin/role/delete_role" pluginCode="002"/>
		<a:ajax successCallbackFunctions="view_update_callback" parameters="{'id':user_group_id};" urls="/admin/role/view_role" pluginCode="003"/>
		<a:ajax successCallbackFunctions="update_callback" parameters="$('#up_form').serializeObject()" urls="/admin/role/update_role" pluginCode="004"/>	
			
		<script type="text/javascript">	
			jQuery(document).ready(function(){
				init_result_dialog();	
				
				//第一组校验组，默认组号为"1"
        		$.formValidator.initConfig({submitButtonID:"add_sub",debug:false,
        			onSuccess:function()
        			{	
        				ajax_001_1();
        				return false;
        			}
        			,onError:function(msg,obj,errorlist){alert_dialog(msg);}
        		});
				$("#name").formValidator().inputValidator({min:1,onError:"请输入名称!"});		
				
				//第二组校验组，组号为"2"
		        $.formValidator.initConfig({validatorGroup:"2",submitButtonID:"up_sub",debug:false,
		        	onSuccess:function()
		        	{
		        		ajax_004_1();
		        		return false;
		        	}
		        	,onError:function(msg,obj,errorlist)
		        	{alert_dialog(msg);}
		        });
				$("#upName").formValidator({validatorGroup:"2"}).inputValidator({min:1,onError:"请输入名称!"});
			});		
			
		</script>
	</head>
  
  <body>
    	<!--头部层开始 -->
		<head:head title="角色设置">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Search Begin-->
			<div>
				<form id="add_form">
				<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
					<tr>
						<td class="lable_100">角色名称:</td>
						<td><input type="text" id="name" name="name" maxlength="60" /></td>
						<td><input type="button" value="新增" id="add_sub" class="btn_black_61" /></td>
						<td><input type="button" value="查询" onclick="searchpage();" class="btn_black_61" /></td>
					</tr>
				</table>
				</form>
			</div>
			<!--Search End-->
			<div id="dataDiv1" style="display:block;">
				<table class="gv_table_2">
					<tr>
						<th style="width:20px;"><img src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" /></th>
						<th style="text-align:left; font-weight:bold;">模块列表：</th>					
					</tr>
				</table>
				<page:plugin 
					pluginCode="page"
					il8nName="admin"
					searchListActionpath="page_list_role"
					searchCountActionpath="page_count_role"
					columnsStr="name"
					pageSize="10"
					delete="json,del,id"
					update="json,view_update,id"
					ontherOperatingWidth="80px"
					params="'role.name':jQuery('#name').val()"
				/>
			</div>
		</body:body>
		<!-- 修改 -->
		<div id="updateDialog" style="display:none">
			<form id="up_form">
			<table class="add_table">
				<tr>
					<td class="lable_100">角色名称:</td>
					<td><input type="text" id="upName" name="name" maxlength="60" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input id="upId" name="id" type="hidden" />
						<input type="button" class="btn_black_61" id="up_sub" value="修改"/>
						<input type="button" class="btn_black_61" value="关闭" onclick="closes('updateDialog');" />
					</td>
				</tr>
			</table>
			</form>
		</div>
  </body>
</html>
