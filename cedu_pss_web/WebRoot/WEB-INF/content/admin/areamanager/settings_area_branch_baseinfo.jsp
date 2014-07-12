<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>区域管理 区域经理设置</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!--  jquery-loading-->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<script type="text/javascript">
		var userId=0;
		var ismanager=0;
		
		
		/*
		查询非区域经理用户
		*/
		function ajax_listareamanager(data)
		{
			var lists='';
			lists+='<option value="0">--请选择--</option>';
			$.each(data.userlst,function(){
				lists+='<option value="'+this.id+'">'+this.fullName+'</option>';
			});
			$('#area_manager').html("");
			$('#area_manager').html(lists);
			
		}
		/*
		设置用户为区域经理
		*/
		function ajax_updateareamanager(data)
		{
			closes('add_area_dialog');
			closes('isdeleteDiv');
			ajax_123_1();
			search123();
		}
		
		</script>
		
		
	
		<a:ajax parameters="null;{'userId':userId,'isManager':ismanager}"
		successCallbackFunctions="ajax_listareamanager;ajax_updateareamanager" 
		urls="/admin/areamanager/listareamanager;/admin/areamanager/updateareamanager"
		pluginCode="123" 
		/>
		<script type="text/javascript">
		function prepare_add_area()
		{
			show('add_area_dialog','新增区域经理',300,150);
		}
		
		function addmanager()
		{
			var user=$('#area_manager').val();
			if(user==0)
			{
				$('#managerValue').html('请选择一个用户！！');
				return;
			}
			userId=user
			ismanager=IS_MANAGER_TRUE;
			ajax_123_2();
		}
		
		/*
		删除院校
		*/
		function deleteFun(id)
		{
			userId=id;
			ismanager=IS_MANAGER_FALSE;
			show('isdeleteDiv','提示',250,150);
			
		}	
		
		/*
		取消删除
		*/
		function cdiv()
		{
			closes('isdeleteDiv');
		}	
		
		/*
		  页面首次加载
		*/
		$(function(){
		
		ajax_123_1();
	
	});
	
	
	
	
	
		</script>
	
  </head>
  
  <body>
   <!--头部层开始 -->
		<head:head title="区域管理 ">
		</head:head>
		<!--主体层开始 -->
		<body:body> 
		
			<!--Menu Begin-->
			<%@ include file="admin_tab_title/admin_areamanager_tab.jsp" %>
			<!--Menu End-->
		
		<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">区域经理设置</th>
					<th>
						<div style="text-align:right;">
							<img src="<ui:img url="images/icon_add.gif"/>" width="16" height="16" align="absmiddle"/>&nbsp;<a href="javascript:void(0);" onclick="prepare_add_area()" >新增区域经理</a>
						</div>
					</th>
				</tr>
		</table>	
			
			
			
			
		<page:plugin 
				pluginCode="123"
				il8nName="admin"
				searchListActionpath="listareamanagerpage"
				searchCountActionpath="countareamanagerpage"
				columnsStr="fullName"
				customColumnValue=""
				delete="json,deleteFun,id"
				pageSize="10"
				ontherOperatingWidth="80px"	
				params=""
		/>
		
		<div id="add_area_dialog" style="display: none;">
		<form>
			<table class="add_table">  
				<tr>
					<td class="lable_100">区域经理：</td>
					<td>
						<select class="txt_box_100" name="" id="area_manager" >
						</select>
					</td>
				</tr>
				<tr>
					<td class="lable_100"></td>
					<td>
						<span id="managerValue"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input onclick="addmanager()" class="btn_black_61" type="button"  value="保存"/></td>
				</tr>
			</table>
		</form>
		</div>
				
				
		<div id="isdeleteDiv" style="display:none">
		<div style="float:inherit">确定要删除吗？</div><br/>
		<input type="button" value="确定" id="btnok" name="btnok" onclick="ajax_123_2()" class="btn_black_61"/> 
		<input type="button" value="取消" id="tt" name="tt" onclick="cdiv()" class="btn_black_61"/> 
		
		</div>			
				
		
   		</body:body>
  </body>
</html>
