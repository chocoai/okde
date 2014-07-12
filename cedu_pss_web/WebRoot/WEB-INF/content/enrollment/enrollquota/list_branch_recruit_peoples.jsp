<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>中心人员招生指标</title>
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
		<style type="text/css">
    	   div.bborder {border-bottom: #cccccc 1px solid !important;}
    	   div.bbborder{border-bottom:white 1px solid !important;}
		</style>
		<script type="text/javascript">
		var batchIds='';
		var branchIds='';
		/*
		查询学习中心
		*/
		function ajax_listbequotabtachIdandbranchId(data)
		{
			var lists='';
			$.each(data.branchenrollquotalst,function(){
				lists+='<option value="'+this.branchId+'">'+this.branchName+'</option>';
			});
			$('#selbranch').html("");
			$('#selbranch').html(lists);
			$('#selbranch').val(branchIds);
			ajax_123_2();
			
		}
		
		
		function ajax_listbequotabIdandbranchId(data)
		{
			init(data);
		}

		</script>
		<a:ajax parameters="{'batchId':batchIds,'branchId':branchIds};{'batchId':batchIds,'branchId':$('#selbranch').val()}"
		successCallbackFunctions="ajax_listbequotabtachIdandbranchId;ajax_listbequotabIdandbranchId"
		urls="/enrollment/enrollquota/listbequotabtachIdandbranchId;/enrollment/enrollquota/listbequotabIdandbranchId"
		pluginCode="123" 
		/>
		<script type="text/javascript">



		
		function init(data)
		{
			$('#branch').html(data.branchenrollquota.branchName);
			$('#batchName').html(data.branchenrollquota.batchName);
			$('#target').html(data.branchenrollquota.target);
			
			if(data.academyenrollquotalst!=null)
			{
				var lists='';
				lists+='<tr>';
				$.each(data.academyenrollquotalst,function(){
					lists+='<td align="center">'+this.academyName+'(<span id="academy'+this.academyId+'">'+this.target+'</span>)</td>';	
				
				});
				lists+='</tr>';
				$('#academy_table').html("");
				$('#academy_table').html(lists);
			}else
			{
				$('#academy_table').html("");
				$('#academy_table').html('<tr><td align="center" >该中心未分配院校指标</td></tr>');
			}
			search123();
			
		}
		
		function fullNameValue(id,fullName)
		{
			return '<span  id="sp'+id+'">'+fullName+'</span>';
		}
		
		function getacademyValue(academylst)
		{
			var lists='';
		   	var count=1;
			if(academylst!=null && academylst.length>0)
			{
				$.each(academylst,function(){
				if(count==academylst.length)
				{
					lists+='<div class="bbborder">';
					lists+='<a>'+this.name+'</a>';
					lists+='</div>';
					return;
				}
				lists+='<div class="bborder">';
				lists+='<a>'+this.name+'</a>';
				lists+='</div>';
				count++;
				});
			}
			return lists;
		}
		
		function getTarget(academylst)
		{
			var lists='';
		   	var count=1;
			if(academylst!=null && academylst.length>0)
			{
				$.each(academylst,function(){
				if(count==academylst.length)
				{
					lists+='<div   class="bbborder">';
					lists+=this.target;
					lists+='</div>';
					return;
				}
				lists+='<div class="bborder">';
				lists+=this.target;
				lists+='</div>';
				count++;
				});
			}
			return lists;
		}
		
		function getExpectTargetNum(academylst)
		{
			var lists='';
		   	var count=1;
			if(academylst!=null && academylst.length>0)
			{
				$.each(academylst,function(){
				if(count==academylst.length)
				{
					lists+='<div  class="bbborder">';
					lists+=this.expectTarget;
					lists+='</div>';
					return;
				}
				lists+='<div  class="bborder">';
				lists+=this.expectTarget;
				lists+='</div>';
				count++;
				});
			}
			return lists;
		}
		
		
		function getCompletedNum(academylst)
		{
			var lists='';
		   	var count=1;
			if(academylst!=null && academylst.length>0)
			{
				$.each(academylst,function(){
				if(count==academylst.length)
				{
					lists+='<div  class="bbborder">';
					lists+=this.complete;
					lists+='</div>';
					return;
				}
				lists+='<div  class="bborder">';
				lists+=this.complete;
				lists+='</div>';
				count++;
				});
			}
			return lists;
		}	
		
		
		/*
		  页面首次加载
		*/
		$(function(){
		if('${param.batchId}'==null || '${param.batchId}'=='')
		{batchIds='0';}
		else
		{batchIds='${param.batchId}';}
		if('${param.branchId}'==null || '${param.branchId}'=='')
		{branchIds='0';}
		else
		{branchIds='${param.branchId}';}
		ajax_123_1();
	});
	

		</script>
	
  </head>
  
  <body>
   <!--头部层开始 -->
		<head:head title="中心人员招生指标">
		</head:head>
		<!--主体层开始 -->
		<body:body> 
		
		<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">人员指标  </th>	
				</tr>
		</table>	
		
		<table class="add_table">
			<tr>
				<td align="right">学习中心：</td>
				<td align="left">
					<select id="selbranch"  class="txt_box" >
						
					</select>
				</td>
				<td align="left"><input onclick="ajax_123_2()" type="button" class="btn_black_61" value="查看" /></td>
			</tr>
		</table>
		
		
		<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
			<input type="hidden" id="branchId" name="branchId" />
			<tr>
					<td width="15%" align="right">学习中心：</td>
					<td width="20%"><span id="branch"></span></td>
					<td width="15%" align="right">批次：</td>
					<td><span id="batchName"></span></td>
					<td width="15%" align="right">总指标数:</td>
					<td width="20%"><span id="target"></span></td>
				</tr>	
				
		 </table>	
		 <table id="academy_table" class="add_table">
				
		 </table>
		

		 <page:plugin 
				pluginCode="123"
				il8nName="enrollment"
				searchListActionpath="listacademyenrollqoutas"
				columnsStr="username;academylst;targetnum;#expectTarget;#completed"
				customColumnValue="0,fullNameValue(id,fullName);1,getacademyValue(academylst);2,getTarget(academylst);3,getExpectTargetNum(academylst);4,getCompletedNum(academylst)"
				pageSize="10"
				isPage="false"
				isonLoad="false"
				ontherOperatingWidth="80px"	
				params="'batchId':batchIds,'branchId':$('#selbranch').val()"
	/>	

		<div id="valDiv" style="display:none">
			请输入要分配的指标数！！
		</div>	
		
		
		<div id="numDiv" style="display:none">
			指标数只能为数字！！
		</div>	
		
		<div id="assignsuccessDiv" style="display:none">
			指标分配成功！！
		</div>
		
		<div id="assignquotaDiv" style="display:none">
			分配的指标不能大于总指标,请重新分配
		</div>
		
		
   		</body:body>
  </body>
</html>
