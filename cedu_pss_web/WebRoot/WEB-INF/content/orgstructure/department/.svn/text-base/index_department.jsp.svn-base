<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>部门设置</title>
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
			//增加部门_回调
			function add_callback(data){
				if(data.results){
			    	alert_default_dialog(1);
			    	searchpage();
			    	reloadparent(1);
			    }else alert_default_dialog(-1);
			}
			
			//删除部门
			function del(id)
			{
				department_id=id;
				ajax_002_1();	
			}
			//删除部门_回调
			function del_callback(data){
				if(data.results){
			    	alert_default_dialog(3);
			    	searchpage();
			    	reloadparent(1);
			    }else alert_default_dialog(-3);
			}
			
			//修改前初始化
			function view_update(id)
			{
				department_id=id;
				ajax_003_1();
			}			
			//修改前初始化_回调
			function view_update_callback(data)
			{
				var dep=data.department;
				if(null!=dep)
			    {
			    	jQuery('#upName').val(dep.name);
			    	jQuery('#upOrder').val(dep.orders);
			    	jQuery('#upId').val(dep.id);
			    	jQuery('#upOfficeId').val(dep.officeId);
			    	$("#upParent_Id").attr("value",dep.parent_Node);
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
			
			function ischecknu()
			{
				var orders=document.getElementById('orders');
				if(isNaN(orders.value))
				{
					orders.value="";
					orders.focus();
				}
			}
		</script>
		<a:ajax successCallbackFunctions="add_callback" parameters="$('#add_form').serializeObject()" urls="/orgstructure/department/add_department" pluginCode="001"/>
		<a:ajax successCallbackFunctions="del_callback" parameters="{'id':department_id}" urls="/orgstructure/department/delete_department" pluginCode="002"/>
		<a:ajax successCallbackFunctions="view_update_callback" parameters="{'id':department_id};" urls="/orgstructure/department/view_department" pluginCode="003"/>
		<a:ajax successCallbackFunctions="update_callback" parameters="$('#up_form').serializeObject()" urls="/orgstructure/department/update_department" pluginCode="004"/>	
		
		<script type="text/javascript">		
			jQuery(document).ready(function(){
				init_result_dialog();	
				reloadparent(1);
				
				//第一组校验组，默认组号为"1"
        		$.formValidator.initConfig({submitButtonID:"add_sub",debug:false,
        			onSuccess:function()
        			{	
        				ajax_001_1();
        				
        				return false;
        			}
        			,onError:function(msg,obj,errorlist){alert_dialog(msg);}
        		});
				$("#depname").formValidator().inputValidator({min:1,onError:"请输入名称!"});		
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
			
		
		//更新父机构下拉菜单
		function reloadparent(type)
		{
			var office_Id=0;
			switch(type)
			{
				case 1:office_Id=$('#office_Id').val();
			}
			jQuery.post('<s:url value="list_department"/>',{'department.officeId':office_Id},
			        function(data)
			    	{
			    		var lists="";
			    		lists+='<option value="0">---请选择---</option>';
			    		if(null!=data.list)
			    		jQuery.each(data.list, function()
		    			{		    			
     		    			 lists+="<option value='"+this.id+"'>"+this.name+"</option>";
		    			});
		    			jQuery('#parentId').html(lists);
		    			jQuery('#upParent_Id').html(lists);
			    	},
			 "json");	
		}
		
		function getOrders()
		{
			var orders=$('#orders').val();
			if(null==orders||""==orders||0==orders.length)
				return 0;
			return orders;
		}
		function parentName(obj){
			if(obj!=null){
				return obj.name;
			}
			return "";
		}
		
		</script>
		
	</head>
  
  <body>	
		<!--头部层开始 -->
		<head:head title="部门设置">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div>
					<form id="add_form" method="post">
					<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">学习中心:</td>
							<td><s:select id="office_Id"  name="officeId" onchange="reloadparent(1);" cssClass="txt_box_150" list="blist" listKey="id" listValue="name" theme="simple"></s:select></td>
							<td class="lable_100">部门名称:</td>
							<td><input id="depname" name="name" maxlength="64" /></td>
							<td class="lable_100">排序:</td>
							<td><input id="orders" name="orders" maxlength="3" onblur="ischecknu()" /></td>
							<td class="lable_100">上级部门:</td>
							<td>
								<select id="parentId" name="parent_Node" class="txt_box_150">
									<option value="0">---请选择---</option>
								</select>
							</td>
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
							<th style="text-align:left; font-weight:bold;">部门列表：</th>					
						</tr>
					</table>
				
					<page:plugin 
						pluginCode="page"
						il8nName="admin"
						searchListActionpath="page_list_department"
						searchCountActionpath="page_count_department"
						columnsStr="name;parent.name;orders;"
						customColumnValue="1,parentName(parent)"
						pageSize="13"
						update="json,view_update,id"
						ontherOperatingWidth="80px"
						params="'department.officeId':$('#office_Id').val(),'department.parent_Node':$('#parentId').val(),'department.name':$('#depname').val(),'department.orders':getOrders()"
						isOrder="false"
					/>
				</div>			
			</body:body>	
	
	<!-- 修改 -->
	<div id="updateDialog" style="display:none">
		<form id="up_form">
			<table class="add_table">
				<tr>
					<td class="lable_100">部门名称：</td>
					<td><input type="text" class="txt_box_150" id="upName" name="name" maxlength="60" /></td>
				</tr>
				<tr>
					<td class="lable_100">排序：</td>
					<td><input type="text" class="txt_box_150" id="upOrder" name="orders" maxlength="3" /></td>
				</tr>
				<tr>
					<td class="lable_100">上级部门：</td>
					<td>
						<select id="upParent_Id" name="parent_Node" class="txt_box_150">
							<option value="0">---请选择---</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input id="upId" name="id" type="hidden" />
						<input id="upOfficeId" name="officeId" type="hidden" />
						<input type="button" id="up_sub" class="btn_black_61" value="修改"/>
						<input type="button" class="btn_black_61" value="关闭" onclick="closes('updateDialog');" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	
	
  </body>
</html>
