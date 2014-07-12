<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>总部签收</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<jc:plugin name="tab" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
 	<script type="text/javascript" >
 
		function addshow()
		{
			show('msgDiv','提示',200,100);
		}
		
		//function showdiv()
		//{
		//$('#hidediv').show();
		//	findRelationships()
		//}
		function sss()
		{
			var a=[];
			//代改进
			for(var i=1;i<10;i++)
				{
			    a.push($(".txtBegin"+i+"").attr("value"));
				}
						 
						alert(a);
			}
 		
 		 function show()
 		 {	
 		 	var ord=$("#orderid").val();
 		 	if(ord.trim()!="")
 		 	{
 		 	findRelationships();
 			}else
 			{
 			alert("请输入查询条件");
 			}
 		
 		 }
		//查询Action
		function findRelationships(){
 				var order=$("#orderid").val();
				//通过表单实现js post提交
				document.write("<form action='find_cedubook_detail_sel?orderCode="+order+"' method='post' name='findForm' style='display:none'>");
				document.write("</form>");
				document.findForm.submit();
 			 
				}
		//增加Action
		function insertRelationships(){
 				var code=$("#invoicecode").val();
 				var order=$("#ordercode").val();
				var begin=[];
				var begintotal="";
			//代改进
			for(var i=1;i<10;i++)
				{
			    begin.push($(".txtBegin"+i+"").attr("value"));
				}
				begintotal=begin.join(",");
				//通过表单实现js post提交
				document.write("<form action='add_invoice_detail?orderCode="+order+"&invoiceCode="+code+"&begintotal="+begintotal+"' method='post' name='lecturerForm' style='display:none'>");
				document.write("</form>");
				document.lecturerForm.submit();
				}
	 
		
	</script>
</head>
  <body>

  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a class="icon">添加发货单</a> </li>
		</ul>
	</div>
	
</div>
<div class="block">

	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
		
		
		 
		<table class="add_table">
			<tr>
				<td align="left">订购单编号：<input name="" type="text" id="orderid" class="txt_box_100" /></td>
				
					<td align="left"><input name="" type="button" class="btn_black_61"  onclick="show()" value="查询" /></td>
			</tr>
			
		</table>
		<div id="hidediv">

			<table class="add_table">
			<tr>
				<td align="left">发货单编号：${invoiceCode}
				<input type="hidden" id="invoicecode" value="${invoiceCode}" />
				<input type="hidden" id="ordercode" value="${ceduBookOrder.code}" />
				</td>				
			</tr>
			<tr>
				<td align="left">书商名称：<s:property value="ceduBookOrder.suppilername"/></td>
			</tr>
			<tr>

				<td align="left">收货中心：<s:property value="ceduBookOrder.branchname"/> </td>
			</tr>
			<tr>
				<td align="left">创建时间：<s:property value="ceduBookOrder.createdTime"/> </td>
			</tr>
			
		</table>
			<table class="gv_table_2" id="invoiceTable">
			
					<tr>

						<th>订购单明细号</th>
						<th>书商名称</th>
						<th>教材名称</th>
						<th>订购数量</th>
						<th>已发货数量</th>
						<th>现发货数量</th>

						<th>状态</th>
						
					</tr>
					<s:iterator var="detail" value="ceduBookOrderDetailList" status="x">
					<tr>
						<td align="center"><s:property value="#x.index+1"/></td>
						<td align="center"><s:property value="#detail.suppilername"/></td>
						<td align="center"><s:property value="#detail.bookname"/></td>
						<td align="center"><s:property value="#detail.bookedTotal"/></td>	
						<td align="center"><s:property value="#detail.sendedTotal"/></td>
						<td align="center"><input name="detail.bookname"  type="text" class="txtBegin<s:property value="#x.index+1"/>" /></td>
						<td align="center"><s:property value="#detail.statusname"/></td>
						
					</tr>
 					</s:iterator>
		 
 
				
					
					<tr><td colspan="8" align="center"><input name="" onclick="insertRelationships();" type="button" class="btn_black_130"  value="生成发货单"/></td></tr>	
			</table>
			</div>
 	 
   </div>

  </div>
 </div>
	
	
	
	
</body>
</html>