<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>收据查询</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!--  jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
	
		
		<script type="text/javascript">
		
		function ajax_alllist(data)
		{
			$('#allTotal').empty();
			$('#allTotal').html(data.allTotal);
			$('#allUseNumber').empty();
			$('#allUseNumber').html(data.allUseNumber);
			$('#allCancelNumber').empty();
			$('#allCancelNumber').html(data.allCancelNumber);
			$('#allInvalidNumber').empty();
			$('#allInvalidNumber').html(data.allInvalidNumber);
			$('#unsurplus').empty();
			$('#unsurplus').html(data.unsurplus);
		}
		
		</script>
		
		
	
		<a:ajax parameters="{status:1,branchId:$('#branchId').val()}"
		successCallbackFunctions="ajax_alllist" 
		urls="/finance/invoicebook/alllistinvoicebook"
		pluginCode="123" 
		/>
		<script type="text/javascript">
	
		
		/*
		查看所有收据
		*/
		function  linktotal(id,num)
		{
			if(num!=0)
			{
				return '<a href="'+WEB_PATH+'/finance/invoicebook/list_invoice_all?id='+id+'"  >'+num+'</a>';
			}
			return num;
		}
		
		
		/*
		查看已使用收据
		*/
		function linkuseNnumber(id,num)
		{
			if(num!=0)
			{
				return '<a href="'+WEB_PATH+'/finance/invoicebook/list_invoice_used?id='+id+'"  >'+num+'</a>';
			}
			return num;
		}
		
		/*
		剩余数
		*/
		function surplus(total,usenum,invnum)
		{
			return total-usenum-invnum;
		}
		/*
		授权状态
		*/
		function getstatus()
		{
			
			return '已授权'
		}
		
		function init()
		{
			
			search123();
			ajax_123_1();
			
		}
		
		/*
		收据号段
		*/
		function receiptcode(startno,endno)
		{
			return startno+'~'+endno;
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
		<head:head title="收据查询">
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
			
		
			<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
				   <tr>
					   <td class="lable_100">使用机构：</td>
					     <td>
					     	<s:select list="%{branchlst}" listKey="id" theme="simple" listValue="name" name="branchId" id="branchId" cssClass="txt_box_150"/>
					     </td>	
						<td class="lable_100"><input type="button" name="" id="" onclick="init()" value="查询" class="btn_black_61" /></td>  
					 </tr>
				</table>
		<page:plugin 
				pluginCode="123"
				il8nName="finance"
				searchListActionpath="listinvoicebook"
				searchCountActionpath="countinvoicebook"
				columnsStr="#code;status;total;useNumber;invalidNumber;cancelNumber;#surplus"
				customColumnValue="0,receiptcode(startNo,endNo);2,linktotal(id,total);1,getstatus();3,linkuseNnumber(id,useNumber);6,surplus(total,useNumber,invalidNumber);"
				pageSize="10"
				ontherOperatingWidth="80px"	
				params="'branchId':$('#branchId').val(),'status':Fee_STATUS_TRUE"
		/>
		
		<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
				   <tr>
					   <td class="lable_100">合计：</td>
					     <td></td>	
					     <td class="lable_100">数据总数：</td>
					     <td><span id="allTotal"></span></td>	
					     <td class="lable_100">使用总数：</td>
					     <td><span id="allUseNumber"></span></td>	
					     <td class="lable_100">核销总数：</td>
					     <td><span id="allCancelNumber"></span></td>
					      <td class="lable_100">作废总数：</td>
					     <td><span id="allInvalidNumber"></span></td>	
					     <td class="lable_100">剩余总数：</td>
					     <td><span id="unsurplus"></span></td>	
						
					 </tr>
				</table>
		
   		</body:body>
	
	
  </body>
</html>
