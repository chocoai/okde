<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>邮寄包详细</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!--  Ajax操作等待 -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<script type="text/javascript">
		
		function amountPaiedValue(amountPaied){
			return (amountPaied+"").toMoney();
		}
		</script>
	
  </head>
  
  <body>
  
   <form id="form" action="<uu:url url="/finance/managerinvoice/add_postal_parcel_cedu" />" method="post" enctype="multipart/form-data">	
   <input type="hidden" id="studentId" name="studentId"  />
  <!--头部层开始 -->
		<head:head title="邮寄包详情">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
		<table class="add_table">
			  <tr>

				<td width="26%" align="right">邮寄单号：</td>
                <td align="left">
					${postalparcel.postSerialNo}
				</td>
               
             
				<td width="26%" align="right">邮包号：</td>
                <td align="left">

					${postalparcel.code}
				</td>
               
              </tr>
			    <tr>
				<td width="26%" align="right">货运公司：</td>
                <td align="left">
					${postalparcel.forwarder}
				</td>  
              
				<td width="26%" align="right">学习中心：</td>
                <td align="left">
					${postalparcel.branchName}
				</td>  
              </tr>
              
              
            </table>
		
			
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">发票列表</th>			
				</tr>
			</table>
	 
	 	<input type="hidden" id="invoiceIds" name="invoiceIds" value="${postalparcel.invoiceIds}" />
		<page:plugin 
				pluginCode="123"
				il8nName="finance"
				searchListActionpath="listmanagementinvoicepost"
				columnsStr="issuedTime;invoiceCode;studentName;academyenrollbatchName;levelName;majorName;amountPaied"
				customColumnValue="6,amountPaiedValue(amountPaied)"
				isPage="false"
				ontherOperatingWidth="80px"	
				
				params="'invoiceIds':$('#invoiceIds').val()"
		/>

   </body:body>
	
	</form>
  </body>
</html>
