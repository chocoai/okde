<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>区域管理 学习中心设置</title>
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
		var count=0;
		var rdoValue=0;
		var bId="";
		/*
		查询区域经理用户
		*/
		function ajax_listareamanagertrue(data)
		{
			var lists='';
			$.each(data.userlst,function(){
			lists+='<tr>';
			if(count==0)
			{
			lists+='<td width="30"><input type="radio" onclick="init_2()" name="manager" checked="checked" value="'+this.id+'"/></td>';	
			}else
			{
			lists+='<td width="30"><input type="radio" onclick="init_2()" name="manager" value="'+this.id+'"/></td>';
			}
			lists+='<td >'+this.fullName+'</td>';
			lists+='</tr>';
			count++;
			});
			$('#manager_table > tbody').html("");
			$('#manager_table > tbody').html(lists);
			init_2();
		}
		
		/*
		查询区域经理已分配的学习中心
		*/
		function ajax_listmanagerwithbranchs(data)
		{
			var lists='';
			var listbranch='';
			var acount=1;
			var bcount=1;
			if(data.areaManagerBranchlst==null)
			{
				$('#add_branch > tbody').html("");
			}else{
				$.each(data.areaManagerBranchlst,function(){	
				
					lists+='<tr>';
					lists+='<td width="30"><input type="checkbox" name="chkbranch" value="'+this.branchId+'"/></td>';
					lists+='<td >'+acount+"."+this.branchName+'</td>';
					lists+='</tr>';
					acount++;
				});
				$('#add_branch > tbody').html("");
				$('#add_branch > tbody').html(lists);
			}
			$.each(data.branchlst,function(){	
				listbranch+='<tr>';
				listbranch+='<td width="30"><input type="checkbox" name="chkbranch" value="'+this.id+'"/></td>';
				listbranch+='<td >'+bcount+"."+this.name+'</td>';
				listbranch+='</tr>';
				bcount++;
			});
			$('#sel_branch > tbody').html("");
			$('#sel_branch > tbody').html(listbranch);
			
		}
		
		/*
		添加区域经理分配的学习中心
		*/
		function ajax_addmanagerbranch(data)
		{
			 show('addDiv','提示',200,150);
			 init_2();
			
		}

		</script>
	
		<a:ajax parameters="null;{'managerId':rdoValue};{'branchIds':bId,'managerId':rdoValue}"
		successCallbackFunctions="ajax_listareamanagertrue;ajax_listmanagerwithbranchs;ajax_addmanagerbranch" 
		urls="/admin/areamanager/listareamanagertrue;/admin/areamanager/listmanagerwithbranchs;/admin/areamanager/addmanagerbranch"
		pluginCode="123" 
		/>
		<script type="text/javascript">
		
		/*
		移除学习中心
		*/
		function remove_branch()
		{
			
			var branchs = new Array();
			jQuery('#add_branch :checkbox[name="chkbranch"]').each(function(){
				
				if(this.checked)
				{
					branchs.push(this.value);
					var parentRow = $(this).parent().parent();
					parentRow.appendTo('#sel_branch');
					this.checked = false;
				}
			});
			
			
		
		}
		
		/*
		添加学习中心
		*/
		function add_branch()
		{
			var branches = new Array();
			jQuery('#sel_branch :checkbox[name="chkbranch"]').each(function(){
				if(this.checked)
				{
					branches.push(this.value);
					var parentRow = $(this).parent().parent();
					parentRow.appendTo('#add_branch');
					this.checked = false;
				}
			});

		}
		
		
		
		
		/*
		区域经理添加学习中心
		*/
		function savebranch()
		{
			bId="";
			jQuery('#add_branch :checkbox').each(function(){
				if(this.checked)
				{
					bId+="_"+this.value;
				}
			});
			rdoValue=$("input[name='manager']:checked").val();
			if(rdoValue==null)
			{
			   show('selmanagerDiv','提示',200,150);
			   return;
			}
			if(bId=="")
			{
			   show('selDiv','提示',200,150);
			   return;
			}
			bId=bId+"_";
			ajax_123_3();
		}
		
		
		
		
		
		function init_2()
		{
			rdoValue=$("input[name='manager']:checked").val();
			ajax_123_2();
		}
		
		
		function init()
		{
			ajax_123_1();
		}
		
		function ckalladd_branch(obj)
		{
			$('#add_branch :checkbox').attr('checked',obj.checked);
		}
		
		function ckallsel_branch(obj)
		{
			$('#sel_branch :checkbox').attr('checked',obj.checked);
		}
	
		/*
		  页面首次加载
		*/
		$(function(){
		init();
		
		
		
	});
	
	
	
	
	
		</script>
	
  </head>
  
  <body>
   <!--头部层开始 -->
		<head:head title="区域管理">
		</head:head>
		<!--主体层开始 -->
		<body:body> 
		
			<!--Menu Begin-->
			<%@ include file="../areamanager/admin_tab_title/admin_areamanager_tab.jsp" %>
			<!--Menu End-->
			
		<!--Left Begin-->
		<div style="float:left; width:16%;">
		
		<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">区域经理</th>
					
				</tr>
		</table>	
		
		<table id="manager_table" class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tbody>
					
				</tbody>
			</table>	
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td><input id="btn1" onclick="savebranch()" class="btn_gray_130" type="button" value="保存更改" /></td>
				</tr>
			</table>
			</div>
			<!--Left End-->
				<!--Line Begin-->
			<div style="float:left;width:4px; height:500px; margin-left:2px; margin-right:2px;">
			</div>
			<!--Line End-->
			
			
			<!-- Center BEGIN -->
			<div style="float:left; width:38%;">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">已分配学习中心--全选<input type="checkbox" id="chkall" name="chkall" onclick="ckalladd_branch(this)" /></th>
					
				</tr>
			</table>	
	
			<table id="add_branch" class="add_table" border="0" cellpadding="2" cellspacing="2" >
				<tbody>
					
				</tbody>
			</table>
	
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td><input id="btn_remove" onclick="remove_branch()" class="btn_gray_130" type="button" value="移除选中中心"/></td>
				</tr>
			</table>
			</div>
	
			<div style="float:left;width:4px; height:300px; margin-left:2px; margin-right:2px;">
			</div>
			
			<!-- Center END -->
			
			
			<!--Right Begin-->
			<div style="margin-left:58%;width:38%;"  >
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">可选学习中心--全选<input type="checkbox" id="chkallsel" name="chkallsel" onclick="ckallsel_branch(this)" /></th>
					
				</tr>
			</table>	
			
			<table id="sel_branch" class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tbody>
					
				</tbody>
			</table>
			
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr><td><input id="btn_add" onclick="add_branch()" class="btn_gray_130" type="button" value="添加选中中心"/></td></tr>
			</table>
			</div>
			<!--Right End-->		
		
		
		<div id="addDiv" style="display:none">
			分配成功！！
		</div>	
		
		<div id="selDiv" style="display:none">
			至少选择一个学习中心！！
		</div>
		
		<div id="selmanagerDiv" style="display:none">
			请选择区域经理！！
		</div>
		
		
   		</body:body>
  </body>
</html>
