<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>发票管理</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
	
		<script type="text/javascript">


		/*
		 发票详情
		*/
		function linkinvoice(id,invoiceCode)
		{
			return '<a href="'+WEB_PATH+'/finance/managerinvoice/view_management_invoice?id='+id+'"  target="_blank" >'+invoiceCode+'</a>';
		}

		function amountPaiedValue(amountPaied) {
			return (amountPaied+"").toMoney();
		}
		function registrationInvoiceTypeValue(registrationInvoiceType){
			return registrationInvoiceType==1?"总部登记":"中心登记";
		}
		function isSignValue(registrationInvoiceType,isPost,isSign){
			var str="";
			if(registrationInvoiceType==1){
				if(isPost==1){
					str+="已邮寄,";
				}else{
					str+="未邮寄,";
				}
			}
			if(isSign==0){
				str+="未签领";
			}else{
				str+="已签领";
			}
			return str;
		}
		
		</script>
	
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="发票管理">
			<html:a text="登记发票" href="/finance/managerinvoice/add_manager_invoice_cedu" icon="add"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
		
			<!--Menu Begin-->
			<%@ include file="../_tab_title/manager_invoice_cedu_tab.jsp" %>
			<!--Menu End-->
			
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">发票列表</th>
					
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
				searchListActionpath="listmanagementinvoicecedu"
				searchCountActionpath="countmanagementinvoicecedu"
				columnsStr="issuedTime;invoiceCode;studentName;academyName;branchName;academyenrollbatchName;levelName;majorName;amountPaied;registrationInvoiceType;isSign"
				customColumnValue="1,linkinvoice(id,invoiceCode);8,amountPaiedValue(amountPaied);9,registrationInvoiceTypeValue(registrationInvoiceType);10,isSignValue(registrationInvoiceType,isPost,isSign)"
				isPage="true"
				pageSize="10"
				ontherOperatingWidth="80px"	
				params="'branchId':$('#branchId').val(),'isPost':-1"
				
		/>
		
		
		
	  </body:body>
	
	
  </body>
</html>
