<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户删除</title>
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
			var module_id=0;//用户id
			//删除用户
			function delete_user(id){
				module_id=id;
				ajax_001_1();	
			}
			// 删除用户回调
			function del_callback(data){
				if(data.errorList == null){
			    	alert('删除成功！');
			    	searchpage();
			    }else{
			    	var msg = '';
			    	$(data.errorList).each(function(){
			    		msg+=this+'\n';
			    	})
			    	alert(msg);
			    }
			}
			// 还原用户
			function reduction_user(id){
				module_id=id;
				ajax_003_1();
			}
			// 还原用户回调
			function red_callback(data){
				if(data.isback){
			    	alert('还原成功！');
			    	searchpage();
			    }else{
			    	alert('还原失败！');
			    	searchpage();
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
			    	alert('修改成功！');
			    	searchpage();
			    }else{
			    	 alert('修改失败！可能有一下原因之一：\n 1.该用户存在被分配的指标\n 2.该用户存在分配的学生\n 3.该用户为院校项目经理\n 4.该用户为系统内置用户禁止修改\n 5.异常错误');
			    	 jQuery('#chstatus'+user_id).val(user_status==0?1:0);
			    }
			}
		</script>
		<a:ajax successCallbackFunctions="del_callback" parameters="{'users.id':module_id}" urls="/admin/user/delete_all_user" pluginCode="001"/>
		<a:ajax successCallbackFunctions="modifystatus_callback" parameters="{'id':user_id,'status':user_status};" urls="/admin/user/update_user_status" pluginCode="002"/>
		<a:ajax successCallbackFunctions="red_callback" parameters="{'users.id':module_id}" urls="/admin/user/reduction_all_user" pluginCode="003"/>
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
		
		function changestatus(status,id,deleteFlag)
		{
			var selects="<select id='chstatus"+id+"' onchange='modifystatus("+id+")'>";
			// 如果用户已删除 则不可操作状态
			if(deleteFlag==DELETE_TRUE){
				selects="<select id='chstatus"+id+"' onchange='modifystatus("+id+")' disabled='disabled'>";
			}
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
		
		function todo(id,deleteFlag)
		{
			if(deleteFlag==DELETE_FALSE){
				return '<a href="#" onclick="delete_user('+id+')">删除</a>';
			}
			else{
				return '<a href="#" onclick="reduction_user('+id+')">还原</a>';
			}
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
		<head:head title="用户删除">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<form id="search_form">
				<div>
					<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">学习中心:</td>
							<td>
								<s:select id="branch_id" name="users.orgId" cssClass="txt_box_100" list="plist" headerKey="-2"  headerValue="--请选择--" listKey="id" listValue="name" />
							</td>
							<td class="lable_100">用户名:</td>
							<td><input type="text" id="userName" name="users.userName" maxlength="60" class="txt_box_100"/></td>
							<td class="lable_100">姓名:</td>
							<td><input type="text" id="fullName" name="users.fullName" maxlength="3" class="txt_box_100"/></td>
							<td class="lable_100">删除状态:</td>
							<td>
								<select name="users.deleteFlag" class="txt_box_100">
									<option value="-1">全部</option>
									<option value="<%=Constants.DELETE_FALSE %>">未删除</option>
									<option value="<%=Constants.DELETE_TRUE %>">已删除</option>
								</select>
							</td>
							<td><input type="button" value="查询" onclick="searchpage();" class="btn_black_61" /></td>
						</tr>
					</table>
				</div>
				</form>
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
						searchListActionpath="list_all_user"
						searchCountActionpath="count_all_user"
						columnsStr="photoUrl;userName;fullName;telephone;mobile;email;department.name;job.name;status;#todo;"
						customColumnValue="0,showphoto(photoUrl);6,departmentName(department);7,jobValue(job);8,changestatus(status,id,deleteFlag);9,todo(id,deleteFlag)"
						pageSize="10"
						view="http,admin/user/view_user,id,id,_blank"
						update="http,admin/user/update_user,id,id,_blank"
						ontherOperatingWidth="80px"
						searchFormId="search_form"
					/>
				</div>			
			</body:body>
  </body>
</html>
