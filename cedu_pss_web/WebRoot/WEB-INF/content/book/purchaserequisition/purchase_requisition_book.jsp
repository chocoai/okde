<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>总部申购</title>			
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
		
		//删除行
		function del(id)
		{
			$('#tr'+id).remove();			
		}
	
		//添加
	    function closebook()
		{
				batch();
				$('#bookDiv').attr('style','display:none');
				$('#bookDiv2').attr('style','display:block');
				$('#sbmDiv').attr('style','display:block');
				ajax_001_1(); 
		}	 
		//被选中的checkbox
		var　array="";
		function batch()
		{
		     var rusult=[];
		    
           var check_array=document.getElementsByName("chk");
           for(var i=0;i<check_array.length;i++)
           {
               if(check_array[i].checked==true)
               {         
                  rusult.push(check_array[i].value);
               }
           }
           array=rusult.join(",");         
		}
		
		//点击添加后显示的列表
		var bookids=[];
		function ajax_load_purchaserequisition_book(data)
    	{
    		var indexid=1;	
    		var list='';    		
			if(data.bookLists)
			{
				if(data.bookLists.length>0)
				{
						$.each(data.bookLists, function()
						{
						list+='<tr id="tr'+indexid+'">';
						list+='<td align="center">';
						list+=this.code;
						list+='</td>';
						list+='<td align="center">';
						list+=this.name;
						list+='</td>';
						list+='<td align="center">';
						list+=this.press;
						list+='</td>';
						list+='<td align="center">';
						list+=this.author;
						list+='</td>';
						list+='<td align="center">';
						list+=this.isbn;
						list+='</td>';
						list+='<td align="center">';
						list+=this.edition;
						list+='</td>';		
						list+='<td align="center">';
						list+=this.price;
						list+='</td>';	
						list+='<td align="center">';
						list+=this.total;
						list+='</td>';		 
						list+='<td align="center">';
						list+='<input type="text" id="number'+indexid+'"/>';
						list+='</td>';
						list+='<td align="center">';
						list+='<a href="javascript:void(0)" onclick="del('+indexid+')" ><img src="../../images/icon_del.gif" border="0" /></a>';
						list+='</td>';
				 		list+='</tr>';
				 		indexid++;
				 		bookids.push(this.id);
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
		
		
		//非空验证
		function yanzheng()
		{	
		var redio =document.getElementsByName("check");
			 var address=$("#address").val();
			 if(address.trim()=="")
			 {
			  alert("请输入发货地址");
			 }else if(bookids=="")
			 {
			   alert( "对不起,请选择教材! "); 			 
			 }else
			 {
			  addPurchase();
			 }
			 
		}
		
		
		
		//添加教材
		function  showbook()
		{
			$('#bookDiv').attr('style','display:block');
		}
 
		//增加Action
		function addPurchase(){
			var address=$('#address').val() ;
			var amount=[];
			var amounttotal="";
			var booktotal="";
			for(var i=1;i<bookids.length+1;i++)
			{
			   	 amount.push($("#number"+i+"").attr("value"));
			}
			amounttotal=amount.join(",");
			booktotal=bookids.join(",");
			//通过表单实现js post提交
			document.write("<form action='../purchaserequisition/add_purchase_requisition?address="+address+"&array="+amounttotal+"&bookids="+booktotal+"' method='post' name='lecturerForm' style='display:none'>");
			document.write("</form>");
			document.lecturerForm.submit();
		}
				
			//查询Action
	 	function findPurchase(){
		 	var bookname=$('#bookname').val() ;
			var isbn=$('#isbn').val();
			//通过表单实现js post提交
			document.write("<form action='../purchaserequisition/purchase_requisition_book?bookname="+bookname+"&isbn="+isbn+"' method='post' name='addForm' style='display:none'>");
			document.write("</form>");
			document.addForm.submit();
		}
	
	
	</script>
	 
    <a:ajax successCallbackFunctions="ajax_load_purchaserequisition_book" 
    		pluginCode="001" 
    		urls="/book/purchaserequisition/ajax_load_purchaserequisition_book"
    		parameters="{array:array}"/>
</head>
  <body>

  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a class="icon">总部申购</a> </li>
		</ul>
	</div>
	
</div>
<div class="block">

	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img class="img_icon" src="../images/title_menu/icon_add.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">教材信息</th>
					<th><div style="float:right;"><img class="img_icon" src="../../images/title_menu/icon_add.gif" /><a href="javascript:void(0);" onclick="showbook()" >添加教材</a></div></th>				
				</tr>

					</table>	
		<div id="bookDiv2"  style="display:none;" >				
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
					<th>库存量</th>
					<th>申购数</th>
					<th>操作</th>
					</tr>
					</thead>
				<tbody></tbody>  	
					</table>						
		</div>	
		
		<div id="bookDiv"  >	
		<table class="add_table">
			<tr>

				<td align="right">教材名称：</td>
				<td align="left"><input id="bookname"  type="text" name="" /></td>
				
				<td align="right">ISBN：</td>
				<td align="left"><input  id="isbn" type="text" name="" /></td>
				
				<td align="right"></td>
						<td align="left"><input name="" type="button" onclick="findPurchase()" class="btn_black_61" value="查询" /></td>				
			</tr>
		
		</table>
			<table class="gv_table_2">
			
					<tr>
					<th>教材编号</th>
		    		<th>教材名称</th>
					<th>出版社</th>
					<th>作者</th>
					<th>ISBN</th>

					<th>版次</th>
					<th>定价</th>
					<th>库存量</th>
					<th>已锁定库存数</th>
					<th>操作</th>		    		
					</tr>
					<s:iterator var="book" value="bookList">
					<tr>
						<td align="center"><s:property value="#book.code"/></td>
						<td align="center"><s:property value="#book.name"/></td>
						<td align="center"><s:property value="#book.press"/></td>
						<td align="center"><s:property value="#book.author"/></td>
						<td align="center"><s:property value="#book.isbn"/></td>

						<td align="center"><s:property value="#book.edition"/></td>
						<td align="center"><s:property value="#book.price"/></td>
						<td align="center"><s:property value="#book.total"/></td>
						<td align="center">192</td>
						<td align="center"><input type="checkbox" name="chk" value="<s:property value="#book.id"/>"  /></td>				  
					</tr>
					 </s:iterator>
			</table>
			
			<table class="add_table">
			
			<tr>
				<td align="right"><input name=""  type="button" onclick="closebook()" class="btn_black_61" value="添加" /></td>
			</tr>
			
			</table>			
			</div>
			
			<div id="sbmDiv" style="display:none;" >
			<table class="add_table">
			<tr>
					<td align="left">发货地址:<input type="text" name="" id="address" class="txt_box_300"/></td>
				</tr>
			<tr>
				<td align="center"><input name=""  type="button" onclick="yanzheng()" class="btn_black_130" value="生成申购单" /></td>
			</tr>
			
			</table>
			</div>
    </div>       
   </div>
  </div>
 
</body>
</html>