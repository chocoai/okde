<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <title>新增书商</title>
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
			 <li class="selectTag"><a>新增书商</a> </li>
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
				 	<th style="text-align:left; font-weight:bold;">书商信息</th>
				</tr>
			</table>
			<form action="add_booksupplier" method="post" name="frm" id="frm">
			<table  class="add_table" >
				<tr>
					<td align="right" style="width:10%" ><b style="color:#FF0000">*</b>书商名称：</td>
					<td align="left"><input name="bs.name" type="text" class="txt_box_150"  /></td>
					
				</tr>
				
				<tr>
					<td align="right">简称：</td>
					<td align="left"><input name="bs.shortName" type="text" class="txt_box_150"  /></td>
					
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>分类：</td>
					<td align="left">
					<s:select list="sclist" listKey="id" listValue="name" name="bs.category" cssClass="txt_box_150"></s:select>
					</td>
					
				</tr>
				
				<tr>
					<td align="right">地址：</td>
					<td align="left"><input name="bs.address" type="text" class="txt_box_150" size="40"  /></td>
					
				</tr>
				
				<tr>
					<td align="right">邮政编码：</td>
					<td align="left"><input name="bs.postalCode" type="text" class="txt_box_150"  /></td>	
				</tr>
				
				<tr>
					<td align="right">网址：</td>
					<td align="left"><input name="bs.website" type="text" class="txt_box_150"  />例如:www.163.com</td>	
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>联系人(中文)：</td>
					<td align="left"><input name="bs.linkman" type="text" class="txt_box_150"  /></td>	
				</tr>
				
				<tr>
					<td align="right">电话：</td>
					<td align="left"><input name="bs.telephone" type="text" class="txt_box_150"  /></td>	
				</tr>
			
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>手机：</td>
					<td align="left"><input name="bs.mobile" type="text" class="txt_box_150"  /></td>	
				</tr>
				
				<tr>
					<td align="right">传真：</td>
					<td align="left"><input name="bs.fax" type="text" class="txt_box_150"  /></td>	
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>邮箱：</td>
					<td align="left"><input name="bs.email" type="text" class="txt_box_150"  />例如:zhangsan@163.com</td>	
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>开户银行：</td>
					<td align="left">
					<select id="sel1" class="selectborder_150" name="bs.accountBank">
						<option value="0" >--请选择--</option>
						<option value="中国银行" selected="selected">中国银行</option>
						<option value="建设银行" >建设银行</option>
						<option value="工商银行" >工商银行</option>
						<option value="工商银行" >农业银行</option>
						<option value="招商银行" >招商银行</option>
						<option value="交通银行" >交通银行</option>
						<option value="邮政储蓄" >邮政储蓄</option>
					</select>
					</td>	
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>账户：</td>
					<td align="left"><input name="bs.account" type="text" class="txt_box_150"  /></td>	
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>结算方式：</td>
					<td align="left">
					<s:select list="swlist" listKey="id" listValue="name" name="bs.clearingForm" cssClass="txt_box_150"></s:select>
					</td>	
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>合同比例：</td>
					<td align="left"><input name="bs.code" type="text" class="txt_box_150" size="40"  />%</td>
					
				</tr>
				
				<tr>
					<td align="right">备注：</td>
					<td align="left"><textarea name="bs.note" cols="30" rows="5" ></textarea></td>	
				</tr>
				
				
				<tr>
				<td></td>
					<td align="left" ><input name="" type="submit" class="btn_black_61"  value="新增" />					                    &nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" class="btn_black_61" value="取消" />

					</td>
			 
			</table>
   </form>
        
   </div>
  </div>
 </div>

</body>
</html>