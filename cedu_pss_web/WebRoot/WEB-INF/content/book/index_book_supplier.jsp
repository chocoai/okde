<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>书商管理</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		
		<jc:plugin name="tab" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<script type="text/javascript">
			//删除			
			
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
				bs_id=id;
				bs_status=$('#chstatus'+id).val();
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
		</script>
		<a:ajax successCallbackFunctions="del_callback" parameters="{'id':book_id}" urls="/book/delete_booksupplier" pluginCode="001"/>
		<a:ajax successCallbackFunctions="modifystatus_callback" parameters="{'id':bs_id,'status':bs_status};" urls="/book/update_book_supplier_status" pluginCode="002"/>
		<script type="text/javascript">
		//模块ID
		var module_id=0;
		
		function showphoto(id)
		{
			var image='<a href="view_book_supplier_index?id='+id+'">设置教材目录</a>';
			return image;
		}	
		function addbookcategory()
		{
			show('addDiv','提示',250,300);
		}
		
		function updatebookcategory(id,code,name)
		{
			$('#id').val(id);
			$('#code').val(code);
			$('#name').val(name);
			show('updateDiv','提示',250,300);
		}
		</script>
		
	</head>
  
  <body>

	
		<!--头部层开始 --><!-- book/add_measuringunits -->
		<head:head title="${branchName }用户">
			<html:a text="新增" href="book/load_booksupplier" icon="add" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search End-->
				<div id="dataDiv1" style="display:block;">
				
					<table class="gv_table_2">
						<tr>
							<th style="width:20px;"><img src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" /></th>
							<th style="text-align:left; font-weight:bold;">教材：</th>					
						</tr>
					</table>
				
					<page:plugin 
						pluginCode="123"
						il8nName="booksupplier"
						searchListActionpath="page_list_booksupplier"
						searchCountActionpath="page_count_booksupplier"
						columnsStr="id;name;category;linkman;telephone;status;szjcml"
						customColumnValue="5,changestatus(status,id);6,showphoto(id)"
						delete="json,del,id"
						update="http,book/do_update_book_supplier,id,id,_self"
						view="http,book/view_book_supplier,id,id,_blank"
						pageSize="10"
					/>
				</div>			
			</body:body>
	
	
	<!-- 添加结果 -->
	<div id="showDialog" style="display:none"><table class="add_table"><tr><td colspan="2" align="center"><input type="button" class="btn_black_61" value="关闭" onclick="closes('showDialog');" /></td></tr></table></div>
	
	
	<div id="addDiv" style="display:none;">
		<form action="add_bookcategory" method="post">
			<table class="add_table">  
				<tr>
					<td class="lable_100">编码：</td>
					<td>
						<input type="text" class="txt_box_130" name="bookcategory.code"/>
					</td>
				</tr>
				<tr>
					<td class="lable_100">教材类型</td>
					<td>
					<input type="text" class="txt_box_130" name="bookcategory.name"/><br/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="btn_black_61" type="submit"  value="保存"/></td>
				</tr>
			</table>
		</form>
		</div>
		
		
		<div id="updateDiv" style="display:none;">
		<form>
			<table class="add_table">  
				<tr>
					<td class="lable_100">编码：</td>
					<td>
						<input type="hidden" name="bookcategory.id" id="id" />
						<input type="text" class="txt_box_130" name="bookcategory.code" id="code"/>
					</td>
				</tr>
				<tr>
					<td class="lable_100">教材类型</td>
					<td>
					<input type="text" class="txt_box_130" name="bookcategory.name" id="name"/><br/>
					<span id="valquota"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="btn_black_61" type="button" onclick="updbc()" value="保存"/></td>
				</tr>
			</table>
		</form>
		</div>
  </body>
</html>
