<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<html>

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
				 	<th style="text-align:left; font-weight:bold;">教材信息</th>
				</tr>
			</table>
			
			<form action="find_update_book" method="post" name="frm" id="frm">
			<table  class="add_table" >
				<tr>
					<td align="right" style="width:10%" ><b style="color:#FF0000">*</b>教材名称：</td>
					<td align="left"><input name="book.name" type="text" class="txt_box_150" value="${book.name}"  /></td>
					
				</tr>
				
				<tr>
					<td align="right" style="width:10%" >教材图片：
					<input type="hidden" name="book.id" id="book.id" value="${book.id}" />
					</td>
					<td align="left">
					<img src="../images/cedu/book/${book.snapshot}">
					<input name="book.snapshot" type="file" value="${book.snapshot}" />
					</td>
					
				</tr>
				<tr>
				
					<td align="right"><b style="color:#FF0000">*</b>ISBN：</td>
					<td align="left"><input name="book.isbn" type="text" class="txt_box_150" value="${book.isbn}"/></td>
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>版次：</td>
					<td align="left"><input name="book.edition" type="text" class="txt_box_150"  value="${book.edition}"/>
									</td>
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>出版社：</td>
					<td align="left"><input name="book.press" type="text" class="txt_box_150" value="${book.press}" /></td>
					
				</tr>
				
				<tr>
					<td align="right">作者：</td>
					<td align="left"><input name="book.author" type="text" class="txt_box_150" value="${book.author}" /></td>
					
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
					<td align="left"><input name="book.price" type="text" class="txt_box_150"  value="${book.price}"  />单位:(元)</td>
					
				</tr>
				
				
				
				<tr>
					<td align="right">备注：</td>
					<td align="left"><textarea name="book.note" cols="30" rows="5">${book.note}</textarea></td>	
				</tr>
				
				
				<tr>
				<td></td>
					<td align="left" ><input name="" type="submit" class="btn_black_61"  value="修改" />					                    &nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" class="btn_black_61" value="取消" />

					</td>
				<tr>
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