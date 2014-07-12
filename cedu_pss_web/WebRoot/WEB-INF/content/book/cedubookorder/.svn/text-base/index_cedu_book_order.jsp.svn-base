<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>总部订购 </title>
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
 	<style type="text/css"> 
		.checkbox_style{vertical-align:middle}  
		.checkbox_style input{vertical-align:middle;}  
    </style>
	<script type="text/javascript" >
		 jQuery(function(){
			   
			 showStatus();
				 
			});			
		function showbranch()
		{
			$('#branchDiv').attr('style','display:block');		
		}
				
		function closebranch()
		{
			$('#branchDiv').attr('style','display:none');			
		}
		//订单状态下拉框
		function showStatus() 
		{
			$('#sel').empty();
		    $('#sel').append('<option value="6">--请选择--</option>');
		    $('#sel').append('<option value="'+BOOK_STATUS_CANCEL+'">已取消</option>');
		    $('#sel').append('<option value="'+BOOK_STATUS_PURCHASE+'">已申购</option>');
		    $('#sel').append('<option value="'+BOOK_STATUS_ORDER+'">已订购</option>');
		    $('#sel').append('<option value="'+BOOK_STATUS_DELIVER+'">已发货</option>');
		    $('#sel').append('<option value="'+BOOK_STATUS_IN_DELIVERY+'">发货中</option>');
		    $('#sel').append('<option value="'+BOOK_STATUS_ORDER_END+'">订购完</option>');
		}
 
			function asd()
		{
			var x=[];
			$("input[name='chk']").each(function(){
		
				if($(this).attr("checked")==true){
				x+=$(this).attr("value")+",";
				}
			})
			alert(x);
		}	
 		
 		
 		//验证
 		function show()
 		{
 			var x=[];
			$("input[name='chk']").each(function(){
		
				if($(this).attr("checked")==true){
				x+=$(this).attr("value")+",";
				}
			})
			
			if(x.trim!=null)
			{
			 addCeduBookOrder();
			}else
			{
			alert("请选择申购单！");
			}
 			
 		}
 		
 		
		 //增加Action
		function addCeduBookOrder(){
 			var x=[];
			$("input[name='chk']").each(function(){
		
				if($(this).attr("checked")==true){
				x+=$(this).attr("value")+",";
				}
			})
				//通过表单实现js post提交
				document.write("<form action='../cedubookorder/add_cedu_book_order?purchaseId="+x+"' method='post' name='addForm' style='display:none'>");
				document.write("</form>");
				document.addForm.submit();
		}
					 
		//全选/反选
		function SelectAll() {
 			var checkboxs=document.getElementsByName("branchId");
 			for (var i=0;i<checkboxs.length;i++) {
 			 var e=checkboxs[i];
 			 e.checked=!e.checked;
 			}
		}
		
		//明细
		function mingxi(id)
		{
			showbook(id);
		}
		//显示明细
		function showbook(id)
		{	
			purchaseReDetailId=id;
			$('#bookDiv').attr('style','display:block;');
			ajax_001_1(); 
		}
 
		//加载申购单明细列表
		var purchaseReDetailId=0;
		var indexid=1;
		function ajax_load_purchaserequisition_detail(data)
    	{
    		var list='';	
			if(data.purchaseDetaillist)
			{
				if(data.purchaseDetaillist.length>0)
				{
						$.each(data.purchaseDetaillist, function()
						{
						list+='<tr>';
						list+='<td align="center">';
						list+=this.bookcode;
						list+='</td>';
						list+='<td align="center">';
						list+=this.bookname;
						list+='</td>';
						list+='<td align="center">';
						list+=this.bookpress;
						list+='</td>';
						list+='<td align="center">';
						list+=this.bookauthor;
						list+='</td>';
						list+='<td align="center">';
						list+=this.bookisbn;
						list+='</td>';
						list+='<td align="center">';
						list+=this.bookedition;
						list+='</td>';
						list+='<td align="center">';
						list+=this.price;
						list+='</td>';
						list+='<td align="center">';
						list+=this.requiredAmount;
						list+='</td>';
						list+='<td align="center">';
						list+=this.amount;
						list+='</td>';
						list+='<td align="center">';
						list+=this.amount;
						list+='</td>';
				 		list+='</tr>';
				 		indexid++;
			 			});			 			
			 		}else{
						list+='<tr class=error>';
						list+='<td colspan="12" align="center">';
						list+='<暂时没有相关信息 >';
						list+='</td>';
						list+='</tr>';
					}			  
				$('#purchaseDetailTable > tbody').html(list);
				 
			}else{
				alert("数据异常，请稍后尝试操作");
			}
    	}	
    
		function addshow()
		{
			show('msgDiv','提示',200,100);
		}
		jQuery(function(){
		util.select.initOption('select[name=branchId]', get_branch(), 0, {value:0,text:'全部'});
		
	});
 
	
		//查询Action
	 	function selectceduBookOrder(){
		 	var status=$('#sel').val() ;
				var branchIds=[];
			$("input[name='branchId']").each(function(){
		
				if($(this).attr("checked")==true){
				branchIds+=$(this).attr("value")+",";
				}
			})
			//通过表单实现js post提交
			document.write("<form action='../cedubookorder/index_cedu_book_order?branchId="+branchIds+"&status="+status+"' method='post' name='addForm' style='display:none'>");
			document.write("</form>");
			document.addForm.submit();
		}
	
	
	
	</script>
	 <%--加载申购单明细列表 --%>
    <a:ajax successCallbackFunctions="ajax_load_purchaserequisition_detail" 
    		pluginCode="001" 
    		urls="/book/cedubookorderdetail/find_cedubookorderdetail_ajax"
    		parameters="{purchaseReDetailId:purchaseReDetailId}"/>
</head>
  <body>
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a class="icon">总部订购</a> </li>
		</ul>

	</div>
	<!--
	<div id="conmenu">	 
	<img src="../images/cedu/icon/icon_add.gif" />
	<a href="view_cedu_order.html" target="_blank">代中心订购</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<img src="../images/cedu/icon/icon_add.gif" 
	<a href="view_cedu_fee_payment.html" target="_blank">直接订购单</a>
	
	</div>
	-->
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    	
			<table class="add_table">			
			 <tr>
			 <td align="left">订单状态：
				
					<select id="sel" name="status">
					<option value="4">--全部--</option>
 
					</select>
					<a href="javascript:void(0)" onclick="showbranch()" >选择学习中心</a>
				</td>			 
			 <td align="left"><input type="button" onclick="selectceduBookOrder()" value="查询" class="btn_black_61"/>
			 </td>
			 </tr>
			 </table>
			 
			<div id="branchDiv" style="display:none;"> 
		  	
			<table class="add_table">
			<tr>
				<td colspan="4" align="left"><a href="javascript:void(0)" onclick="SelectAll()">全选/反选</a>

				<a href="javascript:void(0)" onclick="closebranch()" >确定</a>
				</td>
			</tr>			
				<tr>
			<td>		 
		  		<s:iterator var="branch" value="branchList" status="sts">  
			<input class="checkbox_style" type="checkbox" name="branchId" value="<s:property value="#branch.id"/>"/>&nbsp;<s:property value="#branch.name"/>
			 &nbsp;&nbsp; 
		</s:iterator>	
		   
			</td>
			</tr>
			</table>

			</div>			
			<table class="gv_table_2">
			
					<tr>
						<th>操作</th>
						<th>申购单编号</th>
						<th>学习中心</th>
						<th>金额</th>

						<th>申购人</th>
						<th>申购时间</th>
						<th>状态</th>
						<th>明细</th>
					</tr>
					<s:iterator var="purchase" value="purchaselist" status="x">
					<tr>
					<td align="center" id="id" >
						<c:if test="${purchase.status==1}">
							<div id="div" style="display: block;">
								<input type="checkbox" name="chk" checked="checked" value="<s:property value="#purchase.id"/>"  />
							</div>
						</c:if>	
					</td>
						<td align="center"><s:property value="#purchase.code"/></td>
						<td align="center"><s:property value="#purchase.branchName"/></td>
						<td align="center"><s:property value="#purchase.amount"/></td>
						<td align="center"><s:property value="#purchase.requisitionername"/></td>
						<td align="center"><s:property value="#purchase.requisitiontime"/></td>
						<td align="center" > <span id="span<s:property value="#x.index+1"/>"> <s:property value="#purchase.statusname"/></span> </td>
						<td align="center"><a href="#" onclick="mingxi(<s:property value="#purchase.id"/>)">明细</a></td>										
					</tr>
					</s:iterator>
			</table>
	<table class="add_table">
				
				<tr>
					<td align="center"><input name="" onclick="show()" type="button" class="btn_black_130" value="汇总生成申购单" /></td>			
				</tr>
		
			</table>

		<div id="bookDiv" style="display:none;">
					<table class="gv_table_2" >
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">教材列表</th>

				</tr>
					</table>
					
					<table class="gv_table_2" id="purchaseDetailTable">
					<thead>
					<tr>
					<th>教材编号</th>
		    		<th>教材名称</th>

					<th>出版社</th>
					<th>作者</th>
					<th>ISBN</th>
					<th>版次</th>
					<th>定价(单位:元)</th>

					<th>订购数量</th>
					<th>金额 </th>
					<th>书商 </th>		
					</tr>
					</thead>
					<tbody></tbody>  	
					</table>
		
		<table class="add_table">
		<tr>
			<td align="center"> <input  type="button" onclick="show()" class="btn_black_130" value="生成订购单" /> </td>
		</tr>		
		</table>		
		</div>
 
	<div id="msgDiv" style="display:none">
		操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />

	</div>
   
   </div>
  </div>
 </div>
 
</body>
</html>