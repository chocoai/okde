<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>中心申购</title>			
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
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		
		<script type="text/javascript">
		 jQuery(function(){	
			//查询院校
			jQuery('#academyId').change(function(){
	 		searchpage();
			});					
			//查询申购类型
			jQuery('#types').change(function(){
	 		searchpage();
			});	
		});	
	
	
	
		//全选/反选
		function SelectAll() {
 			var checkboxs=document.getElementsByName("check");
 			for (var i=0;i<checkboxs.length;i++) {
 			 var e=checkboxs[i];
 			 e.checked=!e.checked;
 			}
 			batch();
 			
		}
		
		
			//被选中的checkbox
		var　array="";
		function batch()
		{
		     var rusult=[];
		    
           var check_array=document.getElementsByName("check");
           for(var i=0;i<check_array.length;i++)
           {
               if(check_array[i].checked==true)
               {         
                  rusult.push(check_array[i].value);
               }
           }
           array=rusult.join(",");    
           $("#check").val(array);    
		}

		//显示复选框
		function checkall(id)
		{
			return '<input type="checkbox" name="check" value="'+id+'" id="'+id+'"/>'
		}
			
		//判断类型
		function zhuangtai(types)
		{
			if(types==PRE_PURCHASE_CENTER_STATUS_STUDENTS_PURCHASE)
			{
				return "学生申购";
			}			
			if(types==PRE_PURCHASE_CENTER_STATUS_PREDICTION_OF_PURCHASE)
			{
				return "预估申购";
			}			
 		
		}
		
		//判断状态
		function status(status)
		{
			if(status==-1)
			{
				return "预申购";
			}			
		 	
		}
		
		//预申购单明细
		function caozuo(id,types)
		{	
			if(types==PRE_PURCHASE_CENTER_STATUS_STUDENTS_PURCHASE)
			{
				return '<a href="#" onclick="showstudent('+id+')"  >预申购单明细</a>'	
			}else
			{
				return '<a href="#" onclick="showstudents('+id+')"  >预申购单明细</a>'	
			}
		}
		//加载学生申购单明细
		function showstudent(id)
		{
			purchaseReDetailId=id;
			$('#detailDiv').attr('style','display:none;');
			$('#bookDiv').attr('style','display:block;');
			$('#bookDiv2').attr('style','display:none;');
			$('#detailDiv').attr('style','display:none;');
			ajax_001_1();
			 
		}
		//加载预估申购单明细
		function showstudents(id)
		{
			purchaseReDetailId=id;
			$('#detailDiv').attr('style','display:none;');
			$('#bookDiv2').attr('style','display:block;');
			$('#bookDiv').attr('style','display:none;');
			ajax_002_1();
		}
		
		
		//加载学生申购单学生
		function showbook(id)
		{
			purchaseRequisitionDetailId=id;
			$('#detailDiv').attr('style','display:block;');
			ajax_003_1();
		}
 
		
		//加载预估申购单明细列表
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
    	
    	
    	//加载学生申购单明细列表
		 
		function ajax_load_purchaserequisition(data)
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
						list+='<a href="#" onclick="showbook('+this.id+')">学生明细</a>';
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
				$('#purchaseTable > tbody').html(list);
				 
			}else{
				alert("数据异常，请稍后尝试操作");
			}
    	}	
    	
    	
    	
   		//加载申购单学生明细列表	
		var purchaseRequisitionDetailId=0; 
		function ajax_purchaserequisitionerDetail_student(data)
    	{
    		var list='';	
			if(data.purchaseList)
			{
				if(data.purchaseList.length>0)
				{
						$.each(data.purchaseList, function()
						{
						list+='<tr>';
						list+='<td align="center">';
						list+=this.studentname;
						list+='</td>';
						list+='<td align="center">';
						list+=this.studentcode;
						list+='</td>';
						list+='<td align="center">';
						list+=this.batchname;
						list+='</td>';
						list+='<td align="center">';
						list+=this.levelname;
						list+='</td>';
						list+='<td align="center">';
						list+=this.majorname;
						list+='</td>';
						list+='<td align="center">';
						list+=this.studentstatus;
						list+='</td>';
						list+='<td align="center">';
						list+=this.price;
						list+='</td>';
						list+='<td align="center">';
						list+=this.purchasePrice;
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
				$('#purchaserequisitionerDetailTable > tbody').html(list);
				 
			}else{
				alert("数据异常，请稍后尝试操作");
			}
    	}
		
		//非空验证
		function yanzheng()
		{	
		var redio =document.getElementsByName("check");
			 var address=$("#address").val();
			 if(address.trim()=="")
			 {
			  alert("请输入发货地址");
			 }else if(redio!="")
				{
					for(var i=0; i <redio.length;i++)
     			 	{
	              		if(redio[i].checked)
						{     
							return addRelationships();
						}  
   				    }
                     alert( "对不起,一项都没选中! "); 				 
				 } 
			 
		}
		
	//修改Action
		function addRelationships(){
		 var array=[];
 		var address=$('#address').val();
 
			$("input[name='check']").each(function(){
		
				if($(this).attr("checked")==true){
				array+=$(this).attr("value")+",";
				}
			})
			 //通过表单实现js post提交
			 document.write("<form action='../purchaserequisition/update_purchase_requisition?array="+array+"&address="+address+"' method='post' name='lecturerForm' style='display:none'>");
			 document.write("</form>");
			 document.lecturerForm.submit();
		}
		
		
		
	</script>
	 <%--加载学生申购单明细列表 --%>
    <a:ajax successCallbackFunctions="ajax_load_purchaserequisition" 
    		pluginCode="001" 
    		urls="/book/purchaserequisitiondetail/purchase_requisitionby_detail_orderid_ajax"
    		parameters="{purchaseReDetailId:purchaseReDetailId}"/>
	
	 <%--加载预估申购单明细列表 --%>
    <a:ajax successCallbackFunctions="ajax_load_purchaserequisition_detail" 
    		pluginCode="002" 
    		urls="/book/purchaserequisitiondetail/purchase_requisitionby_detail_orderid_ajax"
    		parameters="{purchaseReDetailId:purchaseReDetailId}"/>
	
	 <%--加载预估申购单学生明细列表 --%>
    <a:ajax successCallbackFunctions="ajax_purchaserequisitionerDetail_student" 
    		pluginCode="003" 
    		urls="/book/purchaserequisitionerdetail/find_purchaserequisitionerdetail_student_ajax"
    		parameters="{purchaseRequisitionDetailId:purchaseRequisitionDetailId}"/>
	
</head>
  <body>

  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a class="icon">中心申购</a> </li>
		</ul>
	</div>
	
</div>
<div class="block">

	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
			<table class="add_table">
				<tr>
			<td class="lable_80">院校名称：</td>
              	<td><s:select cssClass="txt_box_150" list="academies" listKey="id" listValue="name" id="academyId" name="student.academyId" headerKey="0" headerValue="=====全选======"></s:select></td>			
					
					<td align="left"><div style="float:left;" >申购类型:&nbsp;&nbsp;&nbsp;&nbsp;</div><div style="float:left;"><select id="types"  class="selectborder_150" >
									<option  value="-1">=====全选======</option>
									<option  value="<%=Constants.PRE_PURCHASE_CENTER_STATUS_STUDENTS_PURCHASE%> ">学生申购</option>
									<option value="<%=Constants.PRE_PURCHASE_CENTER_STATUS_PREDICTION_OF_PURCHASE%>">预估申购</option>
									
									
								</select></div></td>			
					
				</tr>
		
			</table>
			<table class="gv_table_2">
		  		<tr>

				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">预申购单列表</th>
					
					
				</tr>
					</table>
			<table class="gv_table_2">
	 				<tr><td><a href="javascript:void(0)" onclick="SelectAll()">全选/反选</a></td></tr>
							<page:plugin pluginCode="page" il8nName="bookpurchaserequisition"
								searchListActionpath="page_list_purchaserequisition"
								searchCountActionpath="page_count_purchaserequisition"
								columnsStr="quanxuan;code;amount;requisitionername;requisitiontime;status;types;handle"
								customColumnValue="0,checkall(id);5,status(status);6,zhuangtai(types);7,caozuo(id,types)"														
								pageSize="3" ontherOperatingWidth="80px" isPageSize="3"
								params="'purchaseRequisition.academyId':$('#academyId').val(),'purchaseRequisition.types':$('#types').val()" 
								/>	
			</table>
			<!--  isChecked="true"
								checkboxValue="id"-->	
     
	
	
	
	
	<div id="bookDiv" style="display:none;" >
		<table class="gv_table_2">

		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">教材列表</th>
				</tr>
					</table>
				<table class="gv_table_2" id="purchaseTable">
					<thead>
					<tr>
					<th>教材编号</th>
		    		<th>教材名称</th>
					<th>出版社</th>

					<th>作者</th>
					<th>ISBN</th>
					<th>版次</th>
					<th>定价</th>
					<th>教材数量</th>
					<th>操作</th>
					</tr>
					</thead>
					<tbody></tbody>  	
					</table>
		
		</div>
		
		
		<div id="bookDiv2" style="display:none;" >
		<table class="gv_table_2">
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
					<th>定价</th>
					<th>教材数量</th>
					</tr>
					</thead>
					<tbody></tbody>  	
					</table>
		
		</div>
	<div id="detailDiv" style="display:none;">
			
			
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">学生列表</th>

				</tr>
					</table>
			<table class="gv_table_2" id="purchaserequisitionerDetailTable">
					<thead>
					<tr>
					<th>姓名</th>
		    		<th>学号</th>
					<th>批次</th>

					<th>层次</th>
					<th>专业</th>
					<th>学生状态</th>
					<th>学费余额</th>
					<th>教材费余额</th>
					</tr>
 					</thead>
					<tbody></tbody>  	
			</table>
		</div>
		<table class="add_table">
				<tr>
					<td align="left">发货地址:<input type="text" id="address" name="address" class="txt_box_300"/></td>
				</tr>
				<tr>
					<td align="center"><input name="" type="button" onclick="yanzheng()" class="btn_black_130" value="汇总生成申购单" /></td>
					
				</tr>
		
			</table>

		
		
		
		
	
	
	
        
   </div>
  </div>
 </div>
	
	
	<div id="msgDiv" style="display:none">
		操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />
	</div>
	
	
</body>
</html>