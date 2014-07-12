<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>收据授权</title>
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
		var ids=0;
		
		//收据登记
		function ajax_add(data)
		{
			if(data.bol)
			{
				show('addDiv','提示',250,150);
				search123();
				ajax_123_4();
			}else
			{
				show('addfalseDiv','提示',250,150);
				search123();
				ajax_123_4();
			}

			
		}
		
		//授权
		function ajax_update(data)
		{
			show('updateDiv','提示',250,150);
			search123();
			ajax_123_4();
			
		}
		
		//使用机构
		function ajax_listbranchinvoice(data)
		{
			  var lists='';
				$.each(data.branchlst,function(){
				lists+='<option value="'+this.id+'">'+this.name+'</option>';
				});
				$('#branchlst').val(lists);	
				search123();
		}
		
		/*
		合计数据
		*/
		function ajax_alllistinvoicebookno(data)
		{
			$('#allInvoiceBook').empty();
					$('#allInvoiceBook').html(data.invoiceBookNumber);
					$('#allTotal').empty();
					$('#allTotal').html(data.allTotal);
		}
		</script>
		
		<a:ajax parameters="{'startNo':$('#startNo').val(),'endNo':$('#endNo').val(),'total':$('#total').val()};{'id':ids,'branchId':$('#branch'+ids).val()};null;{'status':0}"
				successCallbackFunctions="ajax_add;ajax_update;ajax_listbranchinvoice;ajax_alllistinvoicebookno"
				urls="/finance/invoicebook/addinvoicebookno;/finance/invoicebook/updateinvoicebookno;/finance/invoicebook/listbranchinvoice;/finance/invoicebook/alllistinvoicebookno"
		 pluginCode="123"
		  />
	
		<script type="text/javascript">

		
		/*
		授权状态
		*/
		function getstatus()
		{
			return '未授权'
		}
		
		/*
		构造使用机构
		*/
		function getbranch(id)
		{
			
			var lists='';
			lists+='<select id="branch'+id+'" name="branch'+id+'">';
			lists+=$('#branchlst').val();
			lists+='</select>';
			return lists;
		}
		
		
		
		
		
		
		/*
		授权操作
		*/
		function operation(id)
		{
			return '<a href="javascript:void(0)"   onclick="updatestatus('+id+')"  >授权</a>';
		}
		
		
		function updatestatus(id)
		{
			ids=id;
			ajax_123_2();
			
			
		}
		
		function addinvoicebook()
		{
			var startNo=$('#startNo').val();
			var endNo=$('#endNo').val();
			var total=$('#total').val();
			//if($.trim(startNo) =="")
			//{
			//	show('startNoDiv','提示',250,150);
			//	return;
			//}
			if($.trim(endNo) =="")
			{
				show('endNoDiv','提示',250,150);
				return;
			}
			if($.trim(total) =="")
			{
				show('totalDiv','提示',250,150);
				return;
			}
			if(isNaN(endNo))
			{
				show('totalNumDiv','提示',250,150);
				return;
			}
			if(isNaN(total))
			{
				show('totalNumDiv','提示',250,150);
				return;
			}
			if(endNo-total>0)
			{
				alert(endNo>=total);
				show('NumToDiv','提示',250,150);
				return;
			}
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
		/*
		获取使用机构
		*/
		ajax_123_3();
		ajax_123_4();
	});
	

		</script>
	
  </head>
  
  <body>
   		<!--头部层开始 -->
		<head:head title="收据授权">
		</head:head>
		<!--主体层开始 -->
		<body:body> 
		<input type="hidden" id="branchlst" name="branchlst" ></input>
			<!--Menu Begin-->
			<%@ include file="../_tab_title/InvoiceBook_tab.jsp" %>
			<!--Menu End-->
		
				<table class="gv_table_2">
					<tr>
						<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
						<th style="text-align:left; font-weight:bold;">未授权列表</th>					
					</tr>
				</table>
				
			<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
						<tr>
							<td class="lable_100">收据册数:</td>
							<td><span id="allInvoiceBook"></span></td>
							<td class="lable_100">收据总数:</td>
							<td><span id="allTotal"></span>张</td>
							<td class="lable_100"></td>
							<td><input type="button" value="收据登记"  class="btn_black_61" onclick="show('addDialog','收据登记',500,200);" /></td>
						</tr>
					</table>
				
			<page:plugin 
					pluginCode="123"
					il8nName="finance"
					searchListActionpath="listinvoicebookno"
					searchCountActionpath="countinvoicebookno"
					columnsStr="#code;total;status;#branch;#operation"
					customColumnValue="0,receiptcode(startNo,endNo);2,getstatus();3,getbranch(id);4,operation(id);"
					isonLoad="false"
					pageSize="18"
					ontherOperatingWidth="80px"	
					params="'branchId':$('#branchId').val(),'status':IS_POST_FALSE"
			/>
		
		
		<div id="addDialog" style="display:none">
			<input type="hidden" id="comCode" name="comCode" value="${comCode}" />
			<input type="hidden" id="remarkid" name="remarkid"/>
			<table class="add_table">
				<tr>
					<td class="lable_100">票本号段前缀：</td>
					<td><input type="text" class="txt_box_150" id="startNo" name="startNo" /></td>
					<td>例如：00000</td>
				</tr>
				<tr>
					<td class="lable_100"><span style="color:red">*</span>起始数：</td>
					<td><input type="text" class="txt_box_150" id="endNo" name="endNo" /></td>
					<td>例如：001</td>
				</tr>
				<tr>
					<td class="lable_100"><span style="color:red">*</span>结束数：</td>
					<td><input type="text" class="txt_box_150" id="total" name="total" /></td>
					<td>例如：100</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" class="btn_black_61" name="save" onclick="addinvoicebook()" value="保存"/>
						<input type="button" class="btn_black_61" value="关闭" onclick="closes('addDialog');" />
					</td>
				</tr>
			</table>
	</div>
		
		
		
		<div id="addDiv" style="display:none">
			添加成功！！
		</div>
		
		<div id="addfalseDiv" style="display:none">
			该票本已存在！！
		</div>
		
		<div id="updateDiv" style="display:none">
			修改成功！！
		</div>
		
		<div id="startNoDiv" style="display:none">
			票本号段不能为空
		</div>
		
		<div id="endNoDiv" style="display:none">
			起始号段不能为空
		</div>
		
		<div id="totalDiv" style="display:none">
			结束号段不能为空
		</div>		
		
		<div id="totalNumDiv" style="display:none">
			发票号段数只能为数字
		</div>	
		
		<div id="totalNumToDiv" style="display:none">
			发票张数不能小于1
		</div>
		
		<div id="NumToDiv" style="display:none">
			起始号段不能大于或等于结束号段
		</div>
	
	   </body:body>
	
	
  </body>
</html>
