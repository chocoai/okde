<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>发票管理</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!--  jquery库 -->
		<jc:plugin name="jquery_ui" />
		<!-- jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		
		<script type="text/javascript">
		var chks="";
		//配送
		function ajax_update(data)
		{
			
			show('updateDiv','提示',300,150);
			search123();
		}
		
		
		</script>
		
		<a:ajax parameters="{'chkName':chks}"
				successCallbackFunctions="ajax_update"
				urls="/finance/managerinvoice/updtepostalparcelstatus"
		 pluginCode="123"
		  />


		<script type="text/javascript">
	
	
		
		function postcode(id,code)
		{
			return '<a href="'+WEB_PATH+'/finance/managerinvoice/view_postal_parcel_cedu?id='+id+'" target="_blank" >'+code+'</a>';
		}
		function branchName(branchName)
		{
			return branchName;
		}
		function statusValue(id,status){
			
			switch(status){
				case 0:
					return "未配送";
				case 1:
					isPageOperating(id,"123","checkbox");
					return "已配送，未签收";
				case 2:
					isPageOperating(id,"123","checkbox");
					return "已配送，已签收";
				default:
					isPageOperating(id,"123","checkbox");
					return "--";
			}
		}
		
		function postalparcel()
		{
			if(getCheckedValues123()=="" || getCheckedValues123()==null)
			{
				show('errorDiv','提示',300,150);	
				return;
			}
			chks=getCheckedValues123();
			ajax_123_1();
		}
		
	
		</script>
	
  </head>
  
  <body>		
		<!--头部层开始 -->
		<head:head title="发票管理">
			<html:a text="新增邮寄单" href="/finance/managerinvoice/add_postal_parcel_cedu" icon="add"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
			<!--Menu Begin-->
			<%@ include file="../_tab_title/manager_invoice_cedu_tab.jsp" %>
			<!--Menu End-->
			
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">邮寄单</th>
				</tr>
			</table>
	 
			<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
				   <tr>
					   <td class="lable_100">学习中心：</td>
					     <td>
					     	<s:select list="%{branchlst}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="branchId" id="branchId" cssClass="txt_box_150"/>
					     </td>	
						<td class="lable_100"><input type="button" name="" id="" onclick="search123()" value="查询" class="btn_black_61" /></td>  
					 </tr>
				</table>
		<page:plugin 
				pluginCode="123"
				il8nName="finance"
				searchListActionpath="listpostalparcel"
				searchCountActionpath="countpostalparcel"
				columnsStr="postSerialNo;#postcode;forwarder;#branchName;createdTime;status"
				customColumnValue="1,postcode(id,code);3,branchName(branchName);5,statusValue(id,status)"
				pageSize="10"
				isChecked="true"
				ontherOperatingWidth="80px"	
				params="'statusIds':'0,1,2','branchId':$('#branchId').val()"
		/>
		<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
				   <tr>
						<td align="center">
						<input type="button" name="" id="" onclick="postalparcel()" value="邮寄" class="btn_black_61" />
						</td>  
					 
					 </tr>
		</table>
		
		
		<div  id="updateDiv" class="update_success"  style="display:none">
			  配送成功！！
		</div>
		
		<div  id="errorDiv" class="update_error"  style="display:none">
			  请选择要配送的邮寄单！！
		</div>
		
		
	   </body:body>
	
	
  </body>
</html>
