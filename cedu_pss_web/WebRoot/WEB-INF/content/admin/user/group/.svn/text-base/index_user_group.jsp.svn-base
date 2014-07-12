<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<head>
		<title>用户组设置</title>
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
				if(null!=data.userGroup)
			    {
			    	jQuery('#upName').val(data.userGroup.name);
			    	jQuery('#upId').val(data.userGroup.id);
			    	jQuery("#upOrgId option[value='"+data.userGroup.orgId+"']").attr("selected","selected");
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
		<a:ajax successCallbackFunctions="add_callback" parameters="$('#add_form').serializeObject()" urls="/admin/user/group/add_user_group" pluginCode="001"/>
		<a:ajax successCallbackFunctions="del_callback" parameters="{'id':modularid}" urls="/admin/user/group/delete_user_group" pluginCode="002"/>
		<a:ajax successCallbackFunctions="view_update_callback" parameters="{'id':user_group_id};" urls="/admin/user/group/view_user_group" pluginCode="003"/>
		<a:ajax successCallbackFunctions="update_callback" parameters="$('#up_form').serializeObject()" urls="/admin/user/group/update_user_group" pluginCode="004"/>	
			
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
				$("#org_id").formValidator().inputValidator({min:1,onError:"请选择机构!"}).regexValidator({regExp:"intege1",dataType:"enum",onError:"请选择机构!"});
				
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
				$("#upOrgId").formValidator().inputValidator({min:1,onError:"请选择机构!"}).regexValidator({regExp:"intege1",dataType:"enum",onError:"请选择机构!"});
			});		
			
			function showname(org)
			{
				if(null==org)
				{
					return "";
				}
				else return org.name;
			}
			
			function showdo(id)
			{
				var dolist="";
				dolist+='<a href=\'<s:url value="/admin/privilege/groupprivilege/index_group_privilege"/>?id='+id+'\'>';
				dolist+='<img src="<ui:img url="/images/icon_title_return.jpg"/>" title="分配权限" />';
				dolist+="</a>";
				return dolist;
			}
			
			function changeaddall(chec)
			{
				chec.value=chec.checked;		
				if(chec.checked)
				{
					jQuery("#org_id option[value='1']").attr("selected","selected");
				}	
				else
				{
					jQuery("#org_id option[value='-1']").attr("selected","selected");
				}	
			}
		</script>
	</head>
  
  <body>
    	<!--头部层开始 -->
		<head:head title="组权限设置">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Search Begin-->
			<div>
				<form id="add_form">
				<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
					<tr>
						<td class="lable_100">用户组名称:</td>
						<td><input type="text" id="name" name="name" maxlength="60" /></td>
						<td class="lable_100">所属机构:</td>
						<td><s:select id="org_id" name="orgId" cssClass="txt_box_150" list="blist" listKey="id" listValue="name" theme="simple"></s:select></td>
						<s:if test="#session.userTicket.orgId<=1">
						<td class="lable_100">所有学习中心:</td>
						<td><input type="checkbox" name="addAll" id="addAll" onclick="changeaddall(this)" /></td>
						</s:if>
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
					searchListActionpath="page_list_user_group"
					searchCountActionpath="page_count_user_group"
					columnsStr="name;org;todo"
					customColumnValue="1,showname(org);2,showdo(id)"
					pageSize="10"
					
					delete="json,del,id"
					update="json,view_update,id"
					ontherOperatingWidth="80px"
					params="'userGroup.orgId':jQuery('#org_id').val()"
					isOrder="false"
				/>
			</div>
		</body:body>
		<!-- 修改 -->
		<div id="updateDialog" style="display:none">
			<form id="up_form">
			<table class="add_table">
				<tr>
					<td class="lable_100">用户组名称:</td>
					<td><input type="text" id="upName" name="name" maxlength="60" /></td>
				</tr>
				<tr>
					<td class="lable_100">所属机构:</td>
					<td><s:select id="upOrgId" name="orgId" cssClass="txt_box_150" list="blist" listKey="id" listValue="name" theme="simple"></s:select></td>
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
