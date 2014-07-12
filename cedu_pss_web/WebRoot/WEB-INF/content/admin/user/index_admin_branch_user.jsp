<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>总部用户</title>
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
		<script type="text/javascript">
			//删除用户
			function del(id)
			{
				module_id=id;
				ajax_001_1();	
			}
		
			function del_callback(data){
				if(data.results){
			    	show('showDialog','删除成功!',150,100);
			    	searchpage();
			    }else{
			    	 show('showDialog','删除失败!',150,100);	
			    }
			}
			
			//修改用户状态
			function modifystatus(id)
			{
				user_id=id;
				user_status=jQuery('#chstatus'+id).val();
				ajax_002_1();
			}
			
			function modifystatus_callback(data){
				if(data.results){
			    	show('showDialog','修改成功!',150,100);
			    	searchpage();
			    }else{
			    	 show('showDialog','修改失败!该用户存在被分配的指标或分配的学生!',150,100);	
			    	 jQuery('#chstatus'+user_id).val(user_status==0?1:0);
			    }
			}
		</script>
		<a:ajax successCallbackFunctions="del_callback" parameters="{'id':module_id}" urls="/admin/user/delete_user" pluginCode="001"/>
		<a:ajax successCallbackFunctions="modifystatus_callback" parameters="{'id':user_id,'status':user_status};" urls="/admin/user/update_user_status" pluginCode="002"/>
		<script type="text/javascript">
		var type=${type};
		
		function showphoto(url)
		{
			if(null==url)
			{
				url='<m:il8n key="photo.default.search" il8nName="admin"/>';
			}
			var image='<img class="user_list_iamge" src="<ui:img url="'+url+'"/>" />';
			return image;
		}
		
		function changestatus(status,id)
		{
			var selects="<select id='chstatus"+id+"' onchange='modifystatus("+id+")'>";
			if(0==status)
				selects+="<option value='0' selected='select'>启用</option>";
			else
				selects+="<option value='0'>启用</option>";
			if(1==status)
				selects+="<option value='1' selected='select'>停用</option>";
			else
				selects+="<option value='1'>停用</option>";
			selects+="</select>";
			return selects;
		}	
		
		
		$(document).ready(function(){
			init_result_dialog();	
		})
		
		function toadd()
		{
			this.location.href="add_user?type="+${type}+"&orgId="+jQuery('#branch_id').val();
		}
		
		function todo(id)
		{
			var href='<a href="<s:url value="/orgstructure/jurisdiction/update_jurisdiction" />?userId='+id+'">设置管辖范围</a>';
			return href;
		}
		function departmentName(department){
			if(department!=null){
				return department.name;
			}
			return "";
		}
		function jobValue(job){
			if(job!=null){
				return job.name;
			}else{
				return "--";
			}
		}
		</script>
	</head>
  
  <body>
	
		<!--头部层开始 -->
		<head:head title="总部用户">
			<s:if test="0!=plist[0].id">
			<html:a text="新增" onclick="toadd();" icon="add" />
			</s:if>
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div>
					<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">学习中心:</td>
							<td>
								<s:select headerKey="-2" headerValue="--请选择--" id="branch_id" list="plist"  listKey="id" listValue="name" cssClass="txt_box"/>
							</td>
							<td class="lable_100">用户名:</td>
							<td><input type="text" id="userName" maxlength="60" /></td>
							<td class="lable_100">姓名:</td>
							<td><input type="text" id="fullName" maxlength="3" /></td>
							<td><input type="button" value="查询" onclick="searchpage();" class="btn_black_61" /></td>
						</tr>
					</table>
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
						searchListActionpath="page_list_user"
						searchCountActionpath="page_count_user"
						columnsStr="photoUrl;userName;fullName;telephone;mobile;email;department.name;job.name;status;#todo;"
						customColumnValue="0,showphoto(photoUrl);6,departmentName(department);7,jobValue(job);8,changestatus(status,id);9,todo(id)"
						pageSize="10"
						view="http,admin/user/view_user,id,id,_blank"
						
						update="http,admin/user/update_user,id,id,_blank"
						ontherOperatingWidth="80px"
						params="'type':type,'orgId':jQuery('#branch_id').val(),'userName':$('#userName').val(),'fullName':$('#fullName').val()"
					/>
				</div>			
			</body:body>
	
	<!-- 修改 -->
	<div id="updateDialog" style="display:none">
		<table class="add_table">
			<tr>
				<td class="lable_100">模块名称：</td>
				<td><input type="text" class="txt_box_150" id="modularName" name="modularName" maxlength="60" /></td>
			</tr>
			<tr>
				<td class="lable_100">排序：</td>
				<td><input type="text" class="txt_box_150" id="modularOrder" name="modularOrder" maxlength="3" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input id="modularId" type="hidden" />
					<input type="button" class="btn_black_61" name="save" onclick="update();" value="修改"/>
					<input type="button" class="btn_black_61" value="关闭" onclick="closes('updateDialog');" />
				</td>
			</tr>
		</table>
	</div>
  </body>
</html>
