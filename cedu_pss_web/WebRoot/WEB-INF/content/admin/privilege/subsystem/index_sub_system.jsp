<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>子系统设置</title>
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
				sub_systemid=id;
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
				sub_system_id=id;
				ajax_003_1();
			}			
			//修改前初始化_回调
			function view_update_callback(data)
			{
				if(null!=data.subSystem)
			    {
			    	jQuery('#upName').val(data.subSystem.name);
			    	jQuery('#upOrder').val(data.subSystem.orders);
			    	jQuery('#upId').val(data.subSystem.id);
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
		<a:ajax successCallbackFunctions="add_callback" parameters="$('#add_form').serializeObject()" urls="/admin/privilege/subsystem/add_sub_system" pluginCode="001"/>
		<a:ajax successCallbackFunctions="del_callback" parameters="{'id':sub_systemid}" urls="/admin/privilege/subsystem/delete_sub_system" pluginCode="002"/>
		<a:ajax successCallbackFunctions="view_update_callback" parameters="{'id':sub_system_id};" urls="/admin/privilege/subsystem/view_sub_system" pluginCode="003"/>
		<a:ajax successCallbackFunctions="update_callback" parameters="$('#up_form').serializeObject()" urls="/admin/privilege/subsystem/update_sub_system" pluginCode="004"/>	
		
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
				$("#sub_name").formValidator().inputValidator({min:1,onError:"请输入名称!"});		
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
			
			
		</script>
		
	</head>
  
  <body>	
		<!--头部层开始 -->
		<head:head title="子系统设置">
			<html:a text="返回" href="/admin/privilege/modular/index_modular" icon="return" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div>
					<form id="add_form" method="post">
					<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">子系统名称:</td>
							<td><input type="text" id="sub_name" name="name" maxlength="60" /></td>
							<td class="lable_100">排序:</td>
							<td><input type="text" id="orders" name="orders" maxlength="3" /></td>
							<td><input type="button" id="add_sub" value="新增" class="btn_black_61" /></td>
							<td><input type="button" value="刷新列表" onclick="searchpage();" class="btn_black_61" /></td>
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
						searchListActionpath="page_list_sub_system"
						searchCountActionpath="page_count_sub_system"
						columnsStr="name;orders;"
						pageSize="13"
						isPage="false"
						delete="json,del,id"
						update="json,view_update,id"
						ontherOperatingWidth="80px"
						isNumber="true"
					/>
				</div>			
			</body:body>	
	
	<!-- 修改 -->
	<div id="updateDialog" style="display:none">
		<form id="up_form">
		<table class="add_table">
			<tr>
				<td class="lable_100">子系统名称：</td>
				<td><input type="txt" class="txt_box_150" id="upName" name="name" maxlength="60" /></td>
			</tr>
			<tr>
				<td class="lable_100">排序：</td>
				<td><input type="txt" class="txt_box_150" id="upOrder" name="orders" maxlength="3" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input id="upId" name="id" type="hidden" />
					<input type="button" id="up_sub" class="btn_black_61" value="修改"/>
					<input type="button" class="btn_black_61" value="关闭" onclick="closes('updateDialog');" />
				</td>
			</tr>
		</table>
		</form>
	</div>
  </body>
</html>
