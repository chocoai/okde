<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>物料设置</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<jc:plugin name="loading" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- Tab 分页样式 -->
	<jc:plugin name="tab" />
	<!--  分页 -->
	<jc:plugin name="page" />
<style type="text/css">
	.stdCeil, .stdFloor { width: 70px; }
</style>

<script type="text/javascript">
 
        //删除用户
			function del(id)
			{
				meterial_id=id;
			show('isdeleteDiv','提示',250,150);
			}
			/*
		取消删除
		*/
		function cdiv()
		{
			closes('isdeleteDiv');
		}	
		//分类查询
		function dianji(id)
		{
		
			$('#meterialId').val(id);
			searchpage();
		}
			
			function del_meterial(data){
				closes('isdeleteDiv');
			searchpage();
			}
			
			//修改用户状态
			function update_meterial(id)
			{
				meterial_id=id;
				ajax_002_1();
			}
			
			function modifystatus_callback(data){
				if(data.results){
			    	show('showDialog','修改成功!',150,100);
			    	refreshpage();
			    }else{
			    	 show('showDialog','修改失败!',150,100);	
			    }
			}
	 
	</script>
 
	 
		<a:ajax successCallbackFunctions="del_meterial" parameters="{'id':meterial_id};" urls="/meterial/meterial/delete_meterial" pluginCode="001"/>
		<a:ajax successCallbackFunctions="update_meterial" parameters="{'id':meterial_id};" urls="/meterial/meterial/update_meterial" pluginCode="002"/>
		<script type="text/javascript">
		
 
		 
	 
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
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
		<!--Line Begin-->
			<div style="width:100%; height:4px; background-color:#3394C1;">
			</div>
			<!--Line End-->
		
			<div id="leftDiv"  style="float:left; width:10%;">
			<table class="gv_table_2" >
					<tr>
						<th style="width:20px;">
						<input type="hidden" id="meterialId"/>
						<img class="img_icon" src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">分类</th>
					</tr>
					  <s:iterator var="meterialCategory" value="list">				 	
					<tr>
						<td colspan="2"  align="center"  onmouseover="this.style.background='#8080c0';" onmouseout="this.style.background='#FFFFFF'" >
							<a href="#" onclick="javascript:dianji(<s:property value="#meterialCategory.id"/>)"><s:property value="#meterialCategory.name"/></a>						 
						</td>
					</tr>	
				 </s:iterator>  				
			</table></div>
			
				<div style="float:left;width:4px; height:500px; background-color:#3394C1; margin-left:2px; margin-right:2px;">
			</div>
				
				<div id="leftDiv"  style="float:left;  width:88%;">
				<table class="gv_table_2">
		  		<tr>
				  	<th style="width:20px;"><img class="img_icon" src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">物料</th>
					<th><div style="float:right;"><img class="img_icon" src="../images/cedu/icon/icon_add.gif" /><a class="icon" href="add_meterial" >增加</a></div></th>
				
				</tr>
				</table>
			<table class="gv_table_2">
					 
							<page:plugin pluginCode="page" il8nName="meterial"
								searchListActionpath="list_meterial"
								searchCountActionpath="count_meterial"
								columnsStr="name;specification;categoryname;price;"
								view="http,meterial/meterial/find_meterial,id,id,_blank"
								delete="json,del,id"
								update="http,meterial/meterial/update_meterial,id,id,_self"
								pageSize="3" ontherOperatingWidth="80px" isPageSize="3"
								params="'categoryId':$('#meterialId').val() " 
								/>

						 

			</table>
			
    
        
   </div>
  </div>
 </div>
 </div>
	<div id="isdeleteDiv" style="display:none">
		<div style="float:inherit">确定要删除吗？</div><br/>
		<input type="button" value="确定" id="btnok" name="btnok" onclick="ajax_001_1();" class="btn_black_61"/> 
		<input type="button" value="取消" id="tt" name="tt" onclick="cdiv();" class="btn_black_61"/> 
		
		</div>	
	
	<div id="msgDiv" style="display:none">
		操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />
	</div>
	
	
</body>
</html>