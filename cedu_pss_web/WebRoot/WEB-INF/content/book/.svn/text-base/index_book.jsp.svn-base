<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>教材分类</title>
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
		<jc:plugin name="loading" />
		<jc:plugin name="validator" />
				
		<script type="text/javascript">
		
			function del(id)
			{
				book_id=id;
				ajax_001_1();	
			}
		
			function del_callback(data){
				if(data.results){
			    	show('showDialog','删除成功!',150,100);
			    	search123();
			    }else{
			    	 show('showDialog','删除失败!',150,100);	
			    }
			}			
			//状态			
			function modifystatus(id)
			{
				book_id=id;
				book_status=$('#chstatus'+id).val();
				ajax_002_1();
			}
			
			function changestatus(status,id)
			{
				var selects="<select id='chstatus"+id+"' onchange='modifystatus("+id+")'>";
				if(0==status)
					selects+="<option value='0' selected='select'>启用</option>";
				else
					selects+="<option value='0'>启用</option>";
				if(1==status)
					selects+="<option value='1' selected='select'>停用</option>";
				else
					selects+="<option value='1'>停用</option>";
				selects+="</select>";
				return selects;
			}
			
			function modifystatus_callback(data){
				if(data.results){
			    	show('showDialog','修改成功!',150,100);
			    	search123();
			    }else{
			    	 show('showDialog','修改失败!',150,100);	
			    }
			}
			
			//条件
			function onabook(id)
			{	
				$('#bid').val(id);
				search123();
			}
		</script>
		<a:ajax successCallbackFunctions="del_callback" parameters="{'id':book_id}" urls="/book/delete_book" pluginCode="001"/>
		<a:ajax successCallbackFunctions="modifystatus_callback" parameters="{'id':book_id,'status':book_status};" urls="/book/update_book_status" pluginCode="002"/>
	</head>
    	<!--头部层开始 -->
		<head:head title="用户组设置">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<div style="width:100%; height:4px; background-color:#3394C1;"></div>
			<div id="leftDiv"  style="float:left; width:13%;">
				<table class="gv_table_2">
						<tr>
							<th style="width:20px;"><img src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" /></th>
							<th style="text-align:left; font-weight:bold;">分类：</th>					
						</tr>
				</table>			
				<table id="group_list" class="add_table">
						<s:iterator value="categorylist" var="category">
							<tr>
								<td><a href="#" onclick="javascript:onabook(<s:property value="#category.id"/>)"><s:property value="#category.name"/></a></td>
							</tr>
						</s:iterator>
					</table>
			</div>		
			<div style="float:left;width:4px; height:500px; background-color:#3394C1; margin-left:2px; margin-right:2px;">
			</div>		
			<div id="leftDiv"  style="float:left;  width:85%;">
				<table class="gv_table_2">
						<tr>
							<th style="width:20px;"><img src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" /></th>
							<th style="text-align:left; font-weight:bold;">教材：</th><th style="text-align:right"><html:a  text="新增" href="book/load_book" icon="add"  /></th>					
						</tr>
					</table>
				<page:plugin 
						pluginCode="123"
						il8nName="book"
						searchListActionpath="page_list_book"
						searchCountActionpath="page_count_book"
						columnsStr="name;press;author;isbn;edition;unit;price;status"
						customColumnValue="7,changestatus(status,id)"
						pageSize="10"
						view="http,book/view_book,id,id,_blank"
						delete="json,del,id"
						update="http,book/do_update_book,id,id,_self"
						params="'book.category':$('#bid').val() "						
					/>
					<input type="hidden" id="bid" name="bid"  />
				 
			</div>		
			<!-- 添加结果 -->
	<div id="showDialog" style="display:none"><table class="add_table"><tr><td colspan="2" align="center"><input type="button" class="btn_black_61" value="关闭" onclick="closes('showDialog');" /></td></tr></table></div>
		</body:body>
</html>
