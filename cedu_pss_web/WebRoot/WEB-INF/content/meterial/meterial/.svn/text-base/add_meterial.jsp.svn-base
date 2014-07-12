<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>新增物料</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- jquery-loading -->
	<jc:plugin name="loading" />
	<!-- 表单验证 -->
	<jc:plugin name="validator" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!--  分页 -->
	<jc:plugin name="page" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
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
	
	 //ajax回调函数  物料分類
			function ajax_categoryajax(data)
			{
				// 物料分類
				jQuery("#meterialId").empty();
			    jQuery("#meterialId").append('<option value="0">--请选择--</option>');
			    if(data.catrgorylist!=null && data.catrgorylist.length>0)
			    {
			    	$.each(data.catrgorylist,function(){	
			    		jQuery("#meterialId").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}
			}
	
	</script>
	<!-- 物料分类 -->
		<a:ajax 
			pluginCode="136"
			successCallbackFunctions="ajax_categoryajax" 
			urls="/meterial/meterialstock/find_meterialcatrgory_ajax"
			isOnload="all" 			
		/>
</head>
  <body>

  
  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag" ><a>新增物料</a> </li>
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
			<form action="add_meterial" method="post" id="submitss" enctype="multipart/form-data">
			<table  class="add_table" >
				<tr>
					<td align="right" style="width:10%" ><b style="color:#FF0000">*</b>物料名称：</td>
					<td align="left"><input name="meterial.name" id="name" type="text" class="txt_box_150"  /></td>
					
				</tr>
				
				<tr>

					<td align="right" style="width:10%" >物料图片：</td>
					<td align="left"><input name="img" type="file"  /></td>
					
				</tr>
				
				<tr>
					<td align="right" style="width:10%" ><b style="color:#FF0000">*</b>物料分类：</td>
					<td align="left">
							 
							  	<select class="tfMeterialId" name="meterial.categoryId" id="meterialId">
							  		<option value="0">--请选择--</option>
							  	</select>
					 </td>	
					 
				</tr>	
				<tr>

					<td align="right">规格：</td>
					<td align="left"><input name="meterial.specification" type="text" class="txt_box_150"  /></td>
				</tr>
				
				<tr>
					<td align="right"><b style="color:#FF0000">*</b>单价：</td>
					<td align="left"><input name="meterial.price" type="text" id="price" class="txt_box_150"  />元</td>
					
				</tr>

				<tr>
					<td align="right">备注：</td>
					<td align="left"><textarea name="meterial.note" cols="30" rows="5"></textarea></td>	
				</tr>
				
				
				<tr>
				<td></td>
					<td align="left" ><input name="" type="button" class="btn_black_61"  onclick="show();" value="新增" />					                    &nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="button" class="btn_black_61" onclick="history.go(-1);"  value="取消" />

					</td>
				</tr>
			</table>
   </form>
        
   </div>
  </div>
 </div>
	
	
	<div id="msgDiv" style="display:none">
		新增成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />

	</div>
	<div id="msgnDiv" style="display:none">
		新增失败！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />

	</div>
	
	
</body>
</html>
