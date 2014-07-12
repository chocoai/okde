<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>总部代书商发货</title>			
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
	<script type="text/javascript" >
		//批量发货Action
		function batchUpdateRelationships(){
			 var batch="";
		     var rusult=[];
          	 var check_array=document.getElementsByName("check");
          	 for(var i=0;i<check_array.length;i++)
       		 {
               if(check_array[i].checked==true)
               {         
                  rusult.push(check_array[i].value);
               }
       		 }
				batch=rusult.join(",");
				//通过表单实现js post提交
				document.write("<form action='../purchaserequisitiondetail/batch_update_purchase_requisition_detail?batch="+batch+"' method='post' name='updateForm' style='display:none'>");
				document.write("</form>");
				document.updateForm.submit();
				}
		
		
		//显示申购单明细
		function showbook(id)
		{	 
		 purchaseReDetailId=id;
			$('#bookDiv').attr('style','display:block;');
			ajax_002_1(); 
		}
		//显示复选框
		function checkall(id)
		{
			return '<input type="checkbox" name="check" value="'+id+'" id="'+id+'"/>'
		}
		
		//订购单明细
		function caozuo(id)
		{
			return '<a href="#" onclick="showorder('+id+')"  >订购单明细</a>'	
		}
		//显示申购单
		function showorder(id)
		{	
			purchaseReId=id;
			$('#orderDiv').attr('style','display:block;');
			ajax_001_1(); 
		}
		//全选/反选
		function SelectAll() {
 			var checkboxs=document.getElementsByName("check");
 			for (var i=0;i<checkboxs.length;i++) {
 			 var e=checkboxs[i];
 			 e.checked=!e.checked;
 			}
		}
		
		//订购下拉框
		function showStatus() 
		{
			$('#status').empty();
		    $('#status').append('<option value="3">--请选择--</option>');
		    $('#status').append('<option value="'+HEADGUARTERS_ON_BEHALF_STATUS_ORDER+'">已订购</option>');
		    $('#status').append('<option value="'+HEADGUARTERS_ON_BEHALF_STATUS_SHIPMENT+'">已发货</option>');
		    $('#status').append('<option value="'+HEADGUARTERS_ON_BEHALF_STATUS_PARTIAL+'">部分发货</option>');
		}
		//判断状态
		function zhuangtai(status)
		{
			if(status==HEADGUARTERS_ON_BEHALF_STATUS_ORDER)
			{
				return "已订购";
			}			
			if(status==HEADGUARTERS_ON_BEHALF_STATUS_SHIPMENT)
			{
				return "已发货";
			}			
			if(status==HEADGUARTERS_ON_BEHALF_STATUS_PARTIAL)
			{
				return "部分发货";
			}
		}
		
		//测试方法
		function addshow()
		{			 
			 var batch="";
		     var rusult=[];
          	 var check_array=document.getElementsByName("check");
          	 for(var i=0;i<check_array.length;i++)
       		 {
               if(check_array[i].checked==true)
               {         
                  rusult.push(check_array[i].value);
               }
       		 }
       		 alert(rusult);
		}
		
		//发货Action
		function addRelationships(){
		 var purchaseId=purchaseReDetailId ;
		 var array=[];
		 var arraytotal="";
		 for(var i=1;i<indexid;i++)
		 {
		   	 array.push($("#number"+i+"").attr("value"));
		 }
			 arraytotal=array.join(",");
			 //通过表单实现js post提交
			 document.write("<form action='../purchaserequisitiondetail/update_purchase_requisition_detail?purchaseId="+purchaseId+"&array="+array+"' method='post' name='lecturerForm' style='display:none'>");
			 document.write("</form>");
			 document.lecturerForm.submit();
		}
		//加载申购单列表 
		var purchaseReId=0;	
    	function ajax_load_purchaserequisition(data)
    	{
    		var list='';
			if(data.purchaseRelist)
			{
				if(data.purchaseRelist.length>0)
				{
						$.each(data.purchaseRelist, function()
						{
						list+='<tr>';
						list+='<td align="center">';
						list+=this.code;
						list+='</td>';
						list+='<td align="center">';
						list+=this.amount;
						list+='</td>';
						list+='<td align="center">';
						list+=this.requisitionername;
						list+='</td>';
						list+='<td align="center">';
						list+=this.requisitiontime;
						list+='</td>';
						list+='<td align="center">';
						list+=this.status;
						list+='</td>';
						list+='<td align="center">';
						list+=this.types;
						list+='</td>';
						list+='<td align="center">';
						list+='<a href="#" onclick="showbook('+this.id+')">申购单明细</a>';
						list+='</td>';
				 		list+='</tr>';
			 			});
			 		}else{
						list+='<tr class=error>';
						list+='<td colspan="7" align="center">';
						list+='<暂时没有相关信息 >';
						list+='</td>';
						list+='</tr>';
					}				  
				$('#purchaseTable > tbody').html(list);		 
			}else{
			  alert("数据异常，请稍后尝试操作");
			}
    	}
		
		//加载申购单明细列表
		var purchaseReDetailId=0;
		var indexid=1;
		function ajax_load_purchaserequisition_detail(data)
    	{
    		var list='';	
			if(data.purchaseRedetaillist)
			{
				if(data.purchaseRedetaillist.length>0)
				{
						$.each(data.purchaseRedetaillist, function()
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
						list+=this.purchasePrice;
						list+='</td>';
						list+='<td align="center">';
						list+=this.requiredAmount;
						list+='</td>';
						list+='<td align="center">';
						list+=this.orderedAmount;
						list+='</td>';
						list+='<td align="center">';
						list+='<input type="text" id="number'+indexid+'"/>';
						list+='</td>';
						list+='<td align="center">';
						list+=this.status;
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
		jQuery(function(){
 
		showStatus();
 				 
	});
		
		
	
	</script>
	 <%--加载申购单列表 --%>
    <a:ajax successCallbackFunctions="ajax_load_purchaserequisition" 
    		pluginCode="001" 
    		urls="/book/purchaserequisition/purchase_requisitionby_id_ajax"
    		parameters="{purchaseReId:purchaseReId}"/>
    <%--加载申购单明细列表 --%>
    <a:ajax successCallbackFunctions="ajax_load_purchaserequisition_detail" 
    		pluginCode="002" 
    		urls="/book/purchaserequisitiondetail/purchase_requisitionby_detail_orderid_ajax"
    		parameters="{purchaseReDetailId:purchaseReDetailId}"/>
</head>
  <body>
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a class="icon">总部代书商发货</a> </li>
		</ul>

	</div>
	
	<div id="conmenu">	 
	<img src="../images/cedu/icon/icon_add.gif" />
	<a href="javascript:void(0)" onclick="batchUpdateRelationships()" >批量发货</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	
	</div>
	
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
			<table class="add_table">

			<tr>
				<td align="right">订购状态：</td>
				<td align="left">
				<select name="status" id="status">
					<option value="0">--请选择--</option>
 
				</select>
				</td>
		
				<td align="right"></td>
				<td align="left"><input name=""  type="button" class="btn_black_61" onclick="searchpage()" value="查询" /></td>
				
			</tr>
		
		</table>
	
			 <table class="gv_table_2">
					 <tr><td><a href="javascript:void(0)" onclick="SelectAll()">全选/反选</a></td></tr>
							<page:plugin pluginCode="page" il8nName="headquartersonbehalf"
								searchListActionpath="list_cedubookorder"
								searchCountActionpath="count_cedubookorder"
								columnsStr="check;code;orderoperatorname;orderTime;amount;status;handle"
								customColumnValue="0,checkall(id);5,zhuangtai(status);6,caozuo(id)"								
								pageSize="3" ontherOperatingWidth="80px" isPageSize="3"
								params="'status':$('#status').val()" 
								/>
			</table>

	<div id="orderDiv" style="display:none;" >
		<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">申购单列表</th>
				</tr>
					</table>
			<table class="gv_table_2" id="purchaseTable">
				<thead>
					<tr>
					<th>申购单编号</th>
					<th>金额</th>
					<th>申购人</th>
					<th>申购时间</th>
					<th>状态</th>
					<th>申购类型</th>
					<th>操作</th>						
					</tr>
				</thead>
				<tbody></tbody>
			</table>
 
					</div>
		
					<div id="bookDiv" style="display:none;"  >
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
					<th>采购价(单位:元)</th>

					<th>订购数量</th>
					<th>已发货数量</th>
					<th>现发货数量</th>
					<th>状态</th>	
					</tr>
					</thead>
					<tbody></tbody>  	
					</table>
		
		<table class="add_table">
		<tr>
			<td align="center"><input name="" onclick="addRelationships()" type="button" class="btn_black_61" value="发货" /></td>
		</tr>
		
		</table>
 
	<div id="msgDiv" style="display:none">
		操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />

	</div>
  
   </div>
  </div>
 </div>
	</div>

</body>
</html>