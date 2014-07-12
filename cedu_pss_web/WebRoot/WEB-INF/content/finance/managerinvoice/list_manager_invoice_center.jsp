<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>收邮寄单</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
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
			
			function ajax_update()
			{
				show('updDiv','提示',200,150);
				//search123();
				refresh123();
			}
		</script>
	
	
		<a:ajax parameters="{'id':$('#id').val()}" 
			        successCallbackFunctions="ajax_update" 
			        urls="/finance/managerinvoice/updtepostalparcel"         
			        pluginCode="123"
			        isOnload=""/>
		        
		        
		<script type="text/javascript">
		
		
		/*
		邮寄单签收
		*/
		function updatestatus(id)
		{
			$('#id').val(id);
			ajax_123_1();
			
		}
		
		/*
		邮包号
		*/
		function postcode(id,code)
		{
			return '<a href="'+WEB_PATH+'/finance/managerinvoice/view_postal_parcel_cedu?id='+id+'" target="_blank" >'+code+'</a>';
		}
		
		
		/*
		学生未签领数
		*/
		function getnoNumber(id,stuSignNoNumber)
		{
			if(stuSignNoNumber==0)
			{
				return stuSignNoNumber;
			}
			return '<a href="#">'+stuSignNoNumber+'</a>';
		}

		/*
		学生已签领数
		*/
		function getSignNumber(id,stuSignNumber)
		{
			if(stuSignNumber==0)
			{
				return stuSignNumber;
			}
			return '<a href="#">'+stuSignNumber+'</a>';
		}
		/*
		状态显示
		*/
		function statusValue(status)
		{
			switch(status){
				case 1:
					return "未签收";
				case 2:
					return "已签收";
				default:
					return "--";
			}
		}
		/*
		操作修改状态
		*/
		function operation(id,status)
		{
			switch(status){
			case 1:
				return '<a href="javascript:void(0)"  onclick="updatestatus('+id+')"   >签收</a>';
			case 2:
				return '';
			default:
				return '--';
		}
			
		}
	
		</script>
	
  </head>
  
  <body>
   		<!--头部层开始 -->
		<head:head title="收邮寄单">
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
			
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
					     	${branch.name}
					     </td>	
						
					 </tr>
				</table>
		<page:plugin 
				pluginCode="123"
				il8nName="finance"
				searchListActionpath="listpostalparcel"
				searchCountActionpath="countpostalparcel"
				columnsStr="postSerialNo;#postcode;forwarder;createdTime;invoiceNumber;stuSignNumber;stuSignNoNumber;status;#operation"
				customColumnValue="1,postcode(id,code);5,getSignNumber(id,stuSignNumber);6,getnoNumber(id,stuSignNoNumber);7,statusValue(status);8,operation(id,status)"
				pageSize="10"
				ontherOperatingWidth="80px"	
				params="'statusIds':'1,2','branchId':${branch.id}"
		/>
		<input type="hidden" id="id"  name="id" />
		<div id="updDiv" style="display:none">
		签收成功！！
		</div>
		
   </body:body>
	
	
  </body>
</html>
