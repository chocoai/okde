<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>修改物料</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!--  tree控件 -->
		<jc:plugin name="tree" />
		<!-- 操作等待插件 -->

	<style type="text/css">
		.stdCeil, .stdFloor { width: 70px; }
	</style>

<script type="text/javascript">
 
		
			//提交前的验证
			function show()
			{
				if(jQuery("#name").val()=="")
				{
					alert('物料名称不能为空！');
 
				}
				else if(jQuery("#meterialId").val()==0)
				{
					alert('请选择物品分类！');
				}
				else if(jQuery("#price").val()==""  )
				{
					alert('请输入价格！');
				 
				}else{
						jQuery("#submitss").submit();
						}
			}
			
	</script>
 
</head>
  <body>

  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag" ><a>修改物料</a> </li>
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
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">物料信息</th>

				</tr>
			</table>
			<form action="update_meterial" method="post" id="submitss" >
			<table  class="add_table" >
				<tr>
					<td align="right" style="width:10%" ><b style="color:#FF0000">*</b>物料名称：</td>
					<td align="left"><input type="text" class="txt_box_150"   name="name" id="name" value="${meterial.name }" /></td>
					
				</tr>
				
				<tr>

					<td align="right" style="width:10%" >物料图片：</td>
					<!-- <td align="left"><img name="picture" src="<ui:img url="${meterial.picture}"  />" height="50px" border="0" /></td> -->
					
				</tr>
				
				<tr>
					<td align="right" style="width:10%" ><b style="color:#FF0000">*</b>物料分类：</td>
					<td align="left">
							 
							 <s:select cssClass="txt_box_150" list="categoryList" listKey="id" listValue="name" id="categoryId" name="meterial.categoryId" headerKey="0" headerValue="=====全选======">
							 </s:select>
					 </td>	
				</tr>	
				<tr>

					<td align="right">规格：</td>
					<td align="left"><input type="text" class="txt_box_150"   name="specification" id="specification" value="${meterial.specification }" /></td>
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>单价：</td>
					<td align="left"><input type="text" class="txt_box_150"   name="price" id="price" value="${meterial.price }" />元</td>
					
				</tr>

				<tr>
					<td align="right">备注：</td>
					<td align="left" style="width: 100px;"><textarea  rows="5" cols="20"     name="note" id="note"   >${meterial.note }</textarea><br/>
						<input type="hidden" id="id" name="id"  value="${meterial.id}"/>
					<input type="hidden" id="code" name="code"  value="${meterial.code}"/>
					</td>	
				</tr>
				
				
				<tr>
				 
				
					<td align="left" ><input name="" type="button"   class="btn_black_61" onclick="show()"   value="修改" />	
					</td>
					<td><input name="" type="button" onclick="history.go(-1);" class="btn_black_61" value="取消" /></td>
				</tr>
			</table>
   </form>
        
   </div>
  </div>
 </div>
	
	
	<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="null_for_close" style="display:none">
			<div align="center" id="showclose">
			
			</div>
		</div>
	
</body>
</html>
