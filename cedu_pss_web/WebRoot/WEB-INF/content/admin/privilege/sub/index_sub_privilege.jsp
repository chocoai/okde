<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>权限子项管理</title>
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
		<!--  tree控件 -->
		<jc:plugin name="tree" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<jc:plugin name="validator" />
		<script type="text/javascript">
			//删除权限子项
			function del(id)
			{
				sub_privilegeid=id;
				ajax_001_1();	
			}
			//删除权限子项_回调
			function del_callback(data){
				if(data.results)
				{
			    	alert_default_dialog(3);
			    	searchpage();
			    }else alert_default_dialog(-3);
			}
			
			//修改权限子项
			function modifystatus(id)
			{
				user_id=id;
				user_status=jQuery('#chstatus'+id).val();
				ajax_002_1();
			}
			//修改权限子项_回调
			function modifystatus_callback(data){
				if(data.results)
				{
					closes('updateDialog');
			    	alert_default_dialog(2);
			    	searchpage();
			    }
			    else alert_default_dialog(-2);
			}
			
			//修改前初始化
			function showupdate(id)
			{
				sub_privilegeid=id;
				ajax_004_1();
			}
			
			//修改前初始化
			function showupdate_callback(data)
			{
				if(null!=data.subPrivilege)
			    {
			    	jQuery('#upId').val(data.subPrivilege.id);
			    	jQuery('#upName').val(data.subPrivilege.name);
			    	jQuery('#upOrder').val(data.subPrivilege.orders);
			    	jQuery("#upPrivilegeSet option[value='"+data.subPrivilege.setId+"']").attr("selected","selected");
			    	jQuery("#upModular option[value='"+jQuery('#modular_id').find('option:selected').val()+"']").attr("selected","selected");
			    	jQuery("#upIsPopUp").val(data.subPrivilege.isPopUp);
			    	jQuery("#upIsShow").val(data.subPrivilege.isShow);
			    	show('updateDialog','修改',380,260);
			    }
			    else alert_default_dialog(0);
			}
			
			//级联模块下拉菜单
			function reload_modular(id,type)
			{
				reload_modular_type=type;
				sub_system_id=jQuery('#'+id).val();
				jQuery("#upSubSystem option[value='"+sub_system_id+"']").attr("selected","selected");
				ajax_005_1();
			
			}
			
			
			//级联模块下拉菜单_回调
			function reload_modular_callback(data)
			{
				var lists="";
			    $.each(data.mslist, function()
		    	{		    			
     		    	lists+="<option value='"+this.id+"'>"+this.name+"</option>";
		    	});
		    	if(null==data.mslist||0==data.mslist.length)
		    	{
		    		lists=getnodate();
		    	}
		    	if(0<reload_modular_type)
		    	{
		    		$('#upModular').html(lists);
		    		reload_privielgeset('upModular',2);
		    	}	
		    	if(1==reload_modular_type)
		    	{
		    		$('#modular_id').html(lists);
		    		reload_privielgeset('modular_id',1);
		    	}	    			    	
			}
			
			//级联权限集下拉菜单
			function reload_privielgeset(id,type)
			{
				reload_privilege_set_type=type;
				modular_id=jQuery('#'+id).val();
				jQuery("#upModular option[value='"+modular_id+"']").attr("selected","selected");
				ajax_006_1();
			}
			
			//级联权限集下拉菜单_回调
			function reload_privielgeset_callback(data)
			{
				var lists="";
				
				lists+=getplease(lists);
			    $.each(data.pslist, function()
		    	{		    			
     		    	lists+="<option value='"+this.id+"'>"+this.name+"</option>";
		    	});
		    	if(null==data.pslist||0==data.pslist.length)
		    	{
		    		lists=getnodate();
		    	}
		    	if(1==reload_privilege_set_type)
		    		$('#privilegeset_id').html(lists);
		    	if(0<reload_privilege_set_type)
		    		$('#upPrivilegeSet').html(lists);
			}
			
			//级联权限下拉菜单
			function reload_privielge(id,type)
			{
				reload_privilege_type=type;
				set_id=jQuery('#'+id).val();
				jQuery("#privilegeset_id option[value='"+set_id+"']").attr("selected","selected");
				ajax_007_1();
			}
			
			//级联权限下拉菜单_回调
			function reload_privielge_callback(data)
			{
				var lists="";
				
				lists+=getplease(lists);
			    $.each(data.plist, function()
		    	{		    			
     		    	lists+="<option value='"+this.id+"'>"+this.name+"</option>";
		    	});
		    	if(null==data.plist||0==data.plist.length)
		    	{
		    		lists=getnodate();
		    	}
		    	if(1==reload_privilege_type)
		    		$('#privilege_id').html(lists);
		    	if(0<reload_privilege_type)
		    		$('#upPrivilege').html(lists);
			}
			
			function getnodate()
			{
				var strings="<option value='0'>--无数据--</option>";
				return strings;
			}
			function getplease(strings)
			{
				strings+="<option value='-1'>--请选择--</option>";
				return strings;
			}
			
			//初始化权限子项
			function initsubprivilege_callback(data){
				if(data.results)
			    {
			    	alert_dialog("初始化完成!");
			    	searchpage();
			    }
			    else alert_dialog("初始化失败!");
			}	
		</script>
		<!-- 删除 -->
		<a:ajax pluginCode="001" successCallbackFunctions="del_callback" parameters="{'id':sub_privilegeid}" urls="/admin/privilege/sub/delete_sub_privilege" />
		<!-- 修改 -->
		<a:ajax pluginCode="002" successCallbackFunctions="modifystatus_callback" parameters="$('#up_form').serializeObject()" urls="/admin/privilege/sub/update_sub_privilege" />
		<!-- 初始化 -->
		<a:ajax pluginCode="003" successCallbackFunctions="initsubprivilege_callback" urls="/admin/privilege/sub/init_sub_privilege" />
		<!-- 修改前查询 -->
		<a:ajax pluginCode="004" successCallbackFunctions="showupdate_callback" parameters="{'id':sub_privilegeid}" urls="/admin/privilege/sub/show_sub_privilege" />
		<!-- 级联查询  --> 
		<a:ajax pluginCode="005" successCallbackFunctions="reload_modular_callback" parameters="{'subSystemId':sub_system_id}" urls="/admin/privilege/modular/list_modular" />
		<a:ajax pluginCode="006" successCallbackFunctions="reload_privielgeset_callback" parameters="{'modularId':modular_id}" urls="/admin/privilege/privilegeset/list_privilegeset" />
		<a:ajax pluginCode="007" successCallbackFunctions="reload_privielge_callback" parameters="{'setId':set_id}" urls="/admin/privilege/list_privilege" />
		<!-- 级联查询  -->
		
		<script type="text/javascript">
		
		$(document).ready(function(){
			init_result_dialog();	
			
			//第一组校验组，默认组号为"1"
        	$.formValidator.initConfig({submitButtonID:"up_sub",debug:false,
        		onSuccess:function()
        		{	
        			ajax_002_1();
        			return false;
        		}
        		,onError:function(msg,obj,errorlist){alert_dialog(msg);}
        	});
			$("#upName").formValidator().inputValidator({min:1,onError:"请输入名称!"});		
			$("#upOrder").formValidator().inputValidator({min:1,onError:"请输入排序!"}).regexValidator({regExp:"intege",dataType:"enum",onError:"请输入整数!"});
			$("#privilegeId").formValidator().inputValidator({min:1,onError:"请选择权限集!"}).regexValidator({regExp:"intege1",dataType:"enum",onError:"请选择权限集!"});		
		});
		
		function view_isshow(data)
		{
			if(0==data)
				return '不显示';
			if(1==data)
				return '显示';
		}
			
		function view_isPopUp(data)
		{
			if(0==data)
				return '不弹出';
			if(1==data)
				return '弹出';
		}
		
		function view_privilege_name(privilege)
		{	
			if(null==privilege)
				return "无绑定";
			return privilege.name;
		}
		
		function change_view(value)
		{
			$('#sisShow').val(value);
		}
		
		</script>
	</head>
  
  <body>	
		<!--头部层开始 -->
		<head:head title="权限子项管理">
			<html:a text="初始化权限子项" onclick="ajax_003_1();" icon="return" />
			<html:a text="返回" href="admin/privilege/index_privilege" icon="return" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
		
				<!--Search Begin-->
				<div>
					<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">所属子系统:</td>
							<td><s:select cssClass="txt_box_150" id="sub_sytem_id" onchange="reload_modular(this.id,1);" list="slist" listKey="id" listValue="name" theme="simple"></s:select></td>
							<td class="lable_100">所属模块:</td>
							<td><s:select cssClass="txt_box_150" id="modular_id" onchange="reload_privielgeset(this.id,1)" list="mlist" listKey="id" listValue="name" theme="simple" ></s:select></td>
							<td class="lable_100">所属权限集:</td>
							<td><s:select cssClass="txt_box_150" id="privilegeset_id" onchange="reload_privielge(this.id,1);" list="pslist" listKey="id" listValue="name" theme="simple"></s:select></td>
							<td class="lable_100">所属权限:</td>
							<td><s:select cssClass="txt_box_150" id="privilege_id" list="plist" listKey="id" listValue="name" theme="simple"></s:select></td>
						</tr>
						<tr>
							<td class="lable_100">权限子项名称:</td>
							<td><input type="text" class="txt_box_150" id="name" maxlength="60" /></td>
							<td class="lable_100">全路径:</td>
							<td><input type="text" class="txt_box_150" id="fullPath" maxlength="60" /></td>
							<td class="lable_100">权限子项代码:</td>
							<td><input type="text" class="txt_box_150" id="code" maxlength="60" /></td>
							<td class="lable_100">排序:</td>
							<td><input type="text" class="txt_box_150" id="orders" maxlength="3" /></td>
						</tr>
						<tr>
							<td class="lable_100">是否显示:</td>
							<td>
								<input type="radio" name="sisShow" onclick="change_view(this.value)" id="isShowAll" value="-1" checked="checked"/>全部
								<input type="radio" name="sisShow" onclick="change_view(this.value)" id="isShowYes" value="1" />显示
								<input type="radio" name="sisShow" onclick="change_view(this.value)" id="isShowNo" value="0" />不显示
								<input type="hidden" id="sisShow" value="-1"/>
							</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="button" value="查询" onclick="searchpage();" class="btn_black_61" /></td>
						</tr>
					</table>
				</div>
				<!--Search End-->
				<div id="dataDiv1" style="display:block;">
				
					<table class="gv_table_2">
						<tr>
							<th style="width:20px;"><img src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" /></th>
							<th style="text-align:left; font-weight:bold;">权限集列表：</th>					
						</tr>
					</table>
				
					<page:plugin 
						pluginCode="page"
						il8nName="admin"
						subStringLength="60"
						searchListActionpath="page_list_sub_privilege"
						searchCountActionpath="page_count_sub_privilege"
						columnsStr="name;privilege;orders;fullPath;code;isShow;isPopUp;"
						customColumnValue="1,view_privilege_name(privilege);5,view_isshow(isShow);6,view_isPopUp(isPopUp)"
						pageSize="10"
						delete="json,del,id"
						update="json,showupdate,id"
						ontherOperatingWidth="80px"
						params="'privilegeId':jQuery('#privilege_id').find('option:selected').val(),'fullPath':jQuery('#fullPath').val(),'name':jQuery('#name').val(),'code':jQuery('#code').val(),'orders':jQuery('#orders').val(),'isShow':$('#sisShow').val()"
					/>
				</div>			
			</body:body>
	
	<!-- 修改 -->
	<div id="updateDialog" style="display:none">
		<form id="up_form">
		<table class="add_table">
			<tr>
				<td class="lable_100">权限子项名称：</td>
				<td><input type="txt" class="txt_box_150" id="upName" name="name" maxlength="60" /></td>
			</tr>
			<tr>
				<td class="lable_100">排序：</td>
				<td><input type="txt" class="txt_box_150" id="upOrder" name="orders" maxlength="3" /></td>
			</tr>
			<tr>
				<td class="lable_100">所属模块：</td>
				<td><s:select cssClass="txt_box_150" onchange="reload_privielgeset(this.id,2);" id="upModular" list="mlist" listKey="id" listValue="name" theme="simple"></s:select></td>
			</tr>
			<tr>
				<td class="lable_100">所属权限集：</td>
				<td><s:select cssClass="txt_box_150" onchange="reload_privielge(this.id,2);" id="upPrivilegeSet" list="pslist" listKey="id" listValue="name" theme="simple"></s:select></td>
			</tr>
			<tr>
				<td class="lable_100">所属权限:</td>
				<td><s:select cssClass="txt_box_150" id="upPrivilege" name="privilegeId" list="plist" listKey="id" listValue="name" theme="simple"></s:select></td>
			</tr>
			<tr>
				<td class="lable_100">是否显示:</td>
				<td><s:radio list="#{1:'显示',0:'不显示'}" id="upIsShow" name="isShow" /></td>
			</tr>
			<tr>
				<td class="lable_100">是否弹出:</td>
				<td><s:radio list="#{1:'弹出',0:'不弹出'}" id="upIsPopUp" name="isPopUp" /></td>
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
