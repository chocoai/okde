<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <title>物料设置</title>
	<link href="../styles/jquery.pager.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="../styles/default.css"/>
	<link rel="stylesheet" type="text/css" href="../styles/jquery.pager.css" />
	<link rel="stylesheet" type="text/css" href="../styles/jquery_ui/themes/orange/jquery.ui.all.css" />
	<link href="../styles/menu/menu_style.css" type="text/css" rel="stylesheet" />

	<script type="text/javascript" src="../scripts/jquery-1.4.js" ></script>		
	<script type="text/javascript" src="../scripts/jquery.pager.js"></script>
	<script type="text/javascript" src="../scripts/jquery-ui-1.8.js"></script>
	<script type="text/javascript" src="../scripts/common/commonDialog.js"></script>
	

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
			 <li class="selectTag"><a class="icon">物料设置</a> </li>
		</ul>
	</div>
	
</div>
 

	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
		
			
		
		
		
		<!--Line Begin-->
			<div style="width:100%; height:4px; background-color:#3394C1;">
			</div>
			<!--Line End-->
		
			<div id="leftDiv"  style="float:left; width:10%;">
	<table class="gv_table_2">
					<tr>

						<th style="width:20px;"><img class="img_icon" src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">分类</th>
					</tr>
					<tr>
						<td align="left" colspan="2"  >
					
						<div style="background-color:#CCCCCC">*<a class="icon" href="list_meterialbycatrgory? catrgory=1">纸</a></div>
						
						</td>

					</tr>
					<tr>
						<td align="left"  colspan="2"><a  class="icon" href="list_meterialbycatrgory?catrgory=2">笔</a> </td>
					</tr>
					<tr>
						<td align="left"  colspan="2"><a class="icon"  href="list_meterialbycatrgory?catrgory=3">水</a></td>
					</tr>
			</table></div>

			
				<div style="float:left;width:4px; height:500px; background-color:#3394C1; margin-left:2px; margin-right:2px;">
			</div>
				
				<div id="leftDiv"  style="float:left;  width:88%;">
				<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img class="img_icon" src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">物料</th>
					<th><div style="float:right;"><img class="img_icon" src="../images/cedu/icon/icon_add.gif" /><a class="icon" href="add_material.html" >增加</a></div></th>

					
				</tr>
				</table>
			<table class="gv_table_2">
					<tr>
						<th>操作</th>
						<th>物料名称</th>
						<th>规格型号</th>

						<th>单位</th>
						<th>单价</th>
					</tr>
					
					
					<tr>
						<td align="center">
								<a class="icon"  target="_blank" href="view_materialinfo.html"><img class="img_icon" src="../images/cedu/icon/icon_find.gif" title="查看" border="0" /></a>&nbsp;&nbsp;
								<a class="icon" href="update_material.html"><img class="img_icon" src="../images/cedu/icon/icon_edit.gif" title="修改" border="0"  /></a>&nbsp;&nbsp;
								<img class="img_icon" src="../images/cedu/icon/icon_del.gif"  title="删除" border="0"  />

								</td>
								<s:iterator var="meterial" value="list">
									<td align="center">
										<s:property value="#meterial.name" />
									</td>
									<td align="center">
										<s:property value="#meterial.specification" />
									</td>
									<td align="center">
										<s:property value="#meterial.categoryId" />
									</td>
									<td align="center">
										<s:property value="#meterial.price" />
									</td>
								</s:iterator>
							</tr>
			</table>
			
    <div id="candidaes_pager" class="pager2">
    	 

    </div>
        
   </div>
  </div>
 </div>
	
	
	<div id="msgDiv" style="display:none">
		操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />
	</div>
	
	
</body>

</html>