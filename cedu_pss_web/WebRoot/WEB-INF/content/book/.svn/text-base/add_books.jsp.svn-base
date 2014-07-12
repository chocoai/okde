<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
  <title>新增教材</title>
  	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
	

	<script type="text/javascript">
		
		function addshow()
		{
			show('msgDiv','提示',200,100);
		}
		
		function yanzheng()
		{
		 if($('#name').val()=="")
		 {
		 alert("教材姓名不能为空");
		 }
		 else if($('#isbn').val()=="")
		 {
		 alert("ISBN不能为空");
		 }
		 else if($('#edition').val()=="")
		 {
		 alert("版次不能为空");
		 }
		 else if($('#press').val()=="")
		 {
		 alert("出版社不能为空");
		 }
		 else if($('#press').val()=="")
		 {
		 alert("出版社不能为空");
		 }
		 else if($('#price').val()=="")
		 {
		 alert("定价不能为空");
		 }
		 else
		 {
		 	$('#frm').submit();
		 }
		}
	
	</script>
</head>
  <body>
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag" ><a>新增教材</a> </li>
		</ul>
	</div>
	<div id="conmenu">	 
	
	 <img src="../images/icon_title_return.jpg" width="15" height="15" />
	 <a href="javascript:history.go(-1);">返回</a>
	</div>
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" /></th>
				 	<th style="text-align:left; font-weight:bold;"">教材信息</th>
				</tr>
			</table>
			
			<form action="add_book" method="post" name="frm" id="frm" enctype="multipart/form-data">
			<table  class="add_table" >
				<tr>
					<td align="right" style="width:10%" ><b style="color:#FF0000">*</b>教材名称：</td>
					<td align="left"><input name="book.name" id="name" type="text" class="txt_box_150"  /></td>
					
				</tr>
				
				<tr>
					<td align="right" style="width:10%" >教材图片：</td>
					<td align="left"><input name="picture" type="file"  /></td>
				</tr>
				
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>ISBN：</td>
					<td align="left"><input name="book.isbn" id="isbn" type="text" class="txt_box_150"  /></td>
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>版次：</td>
					<td align="left"><input name="book.edition" id="edition" type="text" class="txt_box_150"  /></td>
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>出版社：</td>
					<td align="left"><input name="book.press" id="press" type="text" class="txt_box_150"  /></td>
					
				</tr>
				
				<tr>
					<td align="right">作者：</td>
					<td align="left"><input name="book.author" type="text" class="txt_box_150"  /></td>
					
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>分类：</td>
					<td align="left">
					<s:select list="categorys" listKey="id" listValue="name" name="book.category" cssClass="txt_box_150"></s:select>
					</td>
					
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>计量单位：</td>
					<td align="left">
					<s:select list="units" listKey="id" listValue="name" name="book.unit" cssClass="txt_box_150"></s:select>
					</td>
					
				</tr>
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>定价：</td>
					<td align="left"><input name="book.price" id="price" type="text" class="txt_box_150"  />单位:(元)</td>
					
				</tr>
				
				
				
				<tr>
					<td align="right">备注：</td>
					<td align="left"><textarea name="book.note" cols="30" rows="5"></textarea></td>	
				</tr>
				
				
				<tr>
				<td><br></td>
					<td align="left" ><input name=""  type="button" class="btn_black_61"  value="新增" onclick="yanzheng();" />					                    &nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" class="btn_black_61" value="取消" />

					</td>
				 
			</table>
   	</form>
        
   </div>
  </div>
 </div>
	
	
	<div id="msgDiv" style="display:none">
		新增成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />
	</div>
	
	
</body>
</html>