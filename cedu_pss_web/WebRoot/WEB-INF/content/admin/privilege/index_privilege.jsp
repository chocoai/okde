<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>权限集设置</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		 
		 
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
			    }else alert_default_dialog(-1);
			}
			
			//删除子系统
			function del(id)
			{
				set_id=id;
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
				privilege_id=id;
				ajax_003_1();
			}			
			//修改前初始化_回调
			function view_update_callback(data)
			{			
				if(null!=data.privilege)
			    {			    	
			    	jQuery('#upId').val(data.privilege.id);
			    	jQuery('#upName').val(data.privilege.name);
			    	jQuery('#upOrder').val(data.privilege.orders);
			    	jQuery("#upPrivilegeSet option[value='"+data.privilege.setId+"']").attr("selected","selected");
			    	jQuery("#upModular option[value='"+jQuery('#modular_id').find('option:selected').val()+"']").attr("selected","selected");
			    
			    	show('updateDialog','修改',320,200);
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
		    		lists="<option value='0'>--无数据--</option>";
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
				
				lists+="<option value='-1'>--请选择--</option>";
			    $.each(data.pslist, function()
		    	{		    			
     		    	lists+="<option value='"+this.id+"'>"+this.name+"</option>";
		    	});
		    	if(null==data.pslist||0==data.pslist.length)
		    	{
		    		lists="<option value='-1'>--无数据--</option>";
		    	}
		    	if(1==reload_privilege_set_type)
		    		$('#privilegeset_id').html(lists);
		    	if(0<reload_privilege_set_type)
		    		$('#upPrivilegeSet').html(lists);
			}
			
			//校验修正权限显示项_回调
			function check_up_callback(data){
				if(data.results){
					if(0<data.doublePIds.length)
						alert_dialog(doublePIds+"以上权限拥有两条显示子项，请修改!");
			    	else
			    		alert_dialog("校验并修正成功!");
			    }else alert_dialog("校验并修正失败!");
			}
		</script>
		<a:ajax successCallbackFunctions="add_callback" parameters="$('#add_form').serializeObject()" urls="/admin/privilege/add_privilege" pluginCode="001"/>
		<a:ajax successCallbackFunctions="del_callback" parameters="{'id':set_id}" urls="/admin/privilege/delete_privilege" pluginCode="002"/>
		<a:ajax successCallbackFunctions="view_update_callback" parameters="{'id':privilege_id};" urls="/admin/privilege/show_privilege" pluginCode="003"/>
		<a:ajax successCallbackFunctions="update_callback" parameters="$('#up_form').serializeObject()" urls="/admin/privilege/update_privilege" pluginCode="004"/>	
		<a:ajax successCallbackFunctions="reload_modular_callback" parameters="{'subSystemId':sub_system_id}" urls="/admin/privilege/modular/list_modular" pluginCode="005"/>
		<a:ajax successCallbackFunctions="reload_privielgeset_callback" parameters="{'modularId':modular_id}" urls="/admin/privilege/privilegeset/list_privilegeset" pluginCode="006"/>
		<a:ajax successCallbackFunctions="check_up_callback" urls="/admin/privilege/check_up_privilege" pluginCode="007"/>
		
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
				$("#orders").formValidator().inputValidator({min:1,onError:"请输入排序!"}).regexValidator({regExp:"intege",dataType:"enum",onError:"请输入整数!"});
				$("#privilegeset_id").formValidator().inputValidator({min:1,onError:"请选择权限集!"}).regexValidator({regExp:"intege1",dataType:"enum",onError:"请选择权限集!"});
				
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
				$("#upOrder").formValidator({validatorGroup:"2"}).inputValidator({min:1,onError:"请输入排序!"}).regexValidator({regExp:"intege",dataType:"enum",onError:"请输入整数!"});
				$("#upPrivilegeSet").formValidator({validatorGroup:"2"}).inputValidator({min:1,onError:"请选择权限集!"}).regexValidator({regExp:"intege1",dataType:"enum",onError:"请选择权限集!"});
			});		
			
			function showname(privilegeset)
			{
				if(null==privilegeset)
				{
					return "";
				}
				else return privilegeset.name;
			}
			
			function view_isshow(data)
			{
				if(0==data)
					return '不显示';
				if(1==data)
					return '显示';
			}
			
			//自动把权限集的名称赋给权限名称文本框
			function changeValue(){
				$("#name").val($("#privilegeset_id option:selected").text());
				$("#orders").val("1");
			}
		</script>
		
	</head>
  
  <body> 
		<!--头部层开始 -->
		<head:head title="权限设置">
			<html:a text="权限集管理" href="admin/privilege/privilegeset/index_privilege_set" icon="return" />
			<html:a text="权限子项管理" href="admin/privilege/sub/index_sub_privilege" icon="return" />
			<html:a text="校验修正权限显示项" onclick="ajax_007_1();" icon="return" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div>
					<form id="add_form">
					<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">所属子系统:</td>
							<td><s:select cssClass="txt_box_150" id="sub_sytem_id" onchange="reload_modular(this.id,1);" list="slist" listKey="id" listValue="name" theme="simple"></s:select></td>
							<td class="lable_100">所属模块:</td>
							<td><s:select cssClass="txt_box_150" id="modular_id" list="mlist" listKey="id" listValue="name" theme="simple" onchange="reload_privielgeset(this.id,1)"></s:select></td>
							<td class="lable_100">所属权限集:</td>
							<td><s:select cssClass="txt_box_150" id="privilegeset_id" name="setId" list="pslist" listKey="id" listValue="name" theme="simple"  onchange="changeValue()"></s:select></td>
						</tr>
						<tr>
							<td class="lable_100">权限名称:</td>
							<td><input type="text" class="txt_box_150" id="name" name="name" maxlength="60" /></td>
							<td class="lable_100">排序:</td>
							<td><input type="text" class="txt_box_150" id="orders" name="orders" maxlength="3" /></td>
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
							<th style="text-align:left; font-weight:bold;">权限列表：</th>					
						</tr>
					</table>
				
					<page:plugin 
						pluginCode="page"
						il8nName="admin"
						searchListActionpath="page_list_privilege"
						searchCountActionpath="page_count_privilege"
						columnsStr="name;privilegeSet.name;isShow;orders;"
						customColumnValue="2,view_isshow(isShow)"
						pageSize="12"
						delete="json,del,id"
						update="json,view_update,id"
						ontherOperatingWidth="80px"
						params="'setId':jQuery('#privilegeset_id').find('option:selected').val()"
					/>
				</div>			
			</body:body>
	
	<!-- 修改 -->
	<div id="updateDialog" style="display:none">
		<form id="up_form">
		<table class="add_table">
			<tr>
				<td class="lable_100">权限名称：</td>
				<td><input type="txt" class="txt_box_150" id="upName" name="name" maxlength="60" /></td>
			</tr>
			<tr>
				<td class="lable_100">排序：</td>
				<td><input type="txt" class="txt_box_150" id="upOrder" name="orders" maxlength="3" /></td>
			</tr>
			<tr>
				<td class="lable_100">所属子系统:</td>
				<td><s:select cssClass="txt_box_150" id="upSubSystem" onchange="reload_modular(this.id,2);" list="slist" listKey="id" listValue="name" theme="simple"></s:select></td>
			</tr>
			<tr>
				<td class="lable_100">所属模块：</td>
				<td><s:select cssClass="txt_box_150" onchange="reload_privielgeset(this.id,2)" id="upModular" name="upModular" list="mlist" listKey="id" listValue="name" theme="simple"></s:select></td>
			</tr>
			<tr>
				<td class="lable_100">所属权限集：</td>
				<td><s:select cssClass="txt_box_150" id="upPrivilegeSet" name="setId" list="pslist" listKey="id" listValue="name" theme="simple"></s:select></td>
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
