<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../template/common/import.jsp" %>
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
				modular_id=id;
				ajax_003_1();
			}			
			//修改前初始化_回调
			function view_update_callback(data)
			{			
				if(null!=data.privilegeSet)
			    {
			    	jQuery('#upId').val(data.privilegeSet.id);
			    	jQuery('#upName').val(data.privilegeSet.name);
			    	jQuery('#upOrder').val(data.privilegeSet.orders);
			    	jQuery("#upModular option[value='"+data.privilegeSet.modularId+"']").attr("selected","selected");
			    	show('updateDialog','修改',300,180);
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
			function reload(id,type)
			{
				reload_type=type;
				sub_system_id=jQuery('#'+id).val();
				jQuery("#upSubSystem option[value='"+sub_system_id+"']").attr("selected","selected");
				ajax_005_1();
			}
			
			
			//级联模块下拉菜单_回调
			function reload_callback(data)
			{
				var lists="";
			    $.each(data.mslist, function()
		    	{		    			
     		    	lists+="<option value='"+this.id+"'>"+this.name+"</option>";
		    	});
		    	if(null==data.mslist||0==data.mslist.length)
		    	{
		    		lists+="<option value='0'>--无数据--</option>";
		    	}
		    	if(1==reload_type)
		    		$('#modular_id').html(lists);
		    	if(0<reload_type)
		    		$('#upModular').html(lists);
			}
		</script>
		<a:ajax successCallbackFunctions="add_callback" parameters="$('#add_form').serializeObject()" urls="/admin/privilege/privilegeset/add_privilegeset" pluginCode="001"/>
		<a:ajax successCallbackFunctions="del_callback" parameters="{'id':set_id}" urls="/admin/privilege/privilegeset/delete_privilegeset" pluginCode="002"/>
		<a:ajax successCallbackFunctions="view_update_callback" parameters="{'id':modular_id};" urls="/admin/privilege/privilegeset/view_privilegeset" pluginCode="003"/>
		<a:ajax successCallbackFunctions="update_callback" parameters="$('#up_form').serializeObject()" urls="/admin/privilege/privilegeset/update_privilegeset" pluginCode="004"/>	
		<a:ajax successCallbackFunctions="reload_callback" parameters="{'subSystemId':sub_system_id}" urls="/admin/privilege/modular/list_modular" pluginCode="005"/>
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
			});		
			
			function showname(modular)
			{
				if(null==modular)
				{
					return "";
				}
				else return modular.name;
			}
		</script>
		
	</head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="权限集设置">
			<html:a text="模块管理" href="/admin/privilege/modular/index_modular" icon="return" />
			<html:a text="返回" href="/admin/privilege/index_privilege" icon="return" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div>
					<form id="add_form">
					<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">权限集名称:</td>
							<td><input type="text" class="txt_box_150" id="name" name="name" maxlength="60" /></td>
							<td class="lable_100">排序:</td>
							<td><input type="text" class="txt_box_150" id="orders" name="orders" maxlength="3" /></td>
							<td class="lable_100">所属子系统:</td>
							<td><s:select cssClass="txt_box_150" id="sub_sytem_id" onchange="reload(this.id,1);" list="slist" listKey="id" listValue="name" theme="simple"></s:select></td>
							<td class="lable_100">所属模块:</td>
							<td><s:select cssClass="txt_box_150" id="modular_id" name="modularId" list="mlist" listKey="id" listValue="name" theme="simple"></s:select></td>
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
							<th style="text-align:left; font-weight:bold;">权限集列表：</th>					
						</tr>
					</table>
				
					<page:plugin 
						pluginCode="page"
						il8nName="admin"
						searchListActionpath="page_list_privilegeset"
						searchCountActionpath="page_count_privilegeset"
						columnsStr="name;modular;orders;"
						customColumnValue="1,showname(modular)"
						pageSize="12"
						delete="json,del,id"
						update="json,view_update,id,name,orders"
						ontherOperatingWidth="80px"
						params="'modularId':jQuery('#modular_id').find('option:selected').val()"
					/>
				</div>			
		</body:body>
	
	<!-- 修改 -->
	<div id="updateDialog" style="display:none">
		<form id="up_form">
		<table class="add_table">
			<tr>
				<td class="lable_100">权限集名称：</td>
				<td><input type="txt" class="txt_box_150" id="upName" name="name" maxlength="60" /></td>
			</tr>
			<tr>
				<td class="lable_100">排序：</td>
				<td><input type="txt" class="txt_box_150" id="upOrder" name="orders" maxlength="3" /></td>
			</tr>
			<tr>
				<td class="lable_100">所属子系统:</td>
				<td><s:select cssClass="txt_box_150" id="upSubSystem" onchange="reload(this.id,2);" list="slist" listKey="id" listValue="name" theme="simple"></s:select></td>
			</tr>
			<tr>
				<td class="lable_100">所属模块：</td>
				<td><s:select cssClass="txt_box_150" id="upModular" name="modularId" list="mlist" listKey="id" listValue="name" theme="simple"></s:select></td>
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
