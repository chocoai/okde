<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>库房设置</title>
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
			//删除用户
			
			function dels(id)
			{
				jQuery.post('<s:url value="delete_storeroom"/>',{"id":id},
			      	  function(data)
			    		{
			    			if(data.results)
			    			{
			    				show('showDialog','删除成功!',150,100);
			    				window.location.reload();
			    			}
			    			else show('showDialog','删除失败!',150,100);	
			    		},
				 "json");	
			}
			
			
			
				//修改验证
			function xiuyanzheng()
			{
				var code=$('#code').val();
				var name=$('#name').val();
				var types=$("#types").val();
				var position=$("#position").val();
				if(code.trim()=="")
				{
				 alert("请输入编号");
				}else if(name.trim()=="")
				{
				 alert("请输入名称");
				}else if(types.trim()=="")
				{
				alert("请选择库房所属");
				}else if(position.trim()=="")
				{
				alert("请输入库房地址");
				}else
				{
				upds();
				}
				
			}
			
			//修改用户状态
			function upds()
			{
				var id=$('#id').val();
				var code=$('#code').val();
				var name=$('#name').val();
				var types=$("#types").val();
				var position=$("#position").val();
				jQuery.post('<s:url value="update_storeroom"/>',{"storeroom.id":id,"storeroom.name":name,"storeroom.code":code,"storeroom.types":types,"storeroom.position":position},
			      	  function(data)
			    		{
			    			if(data.results)
			    			{
			    				show('showDialog','修改成功!',150,100);
			    				window.location.reload();
			    			}
			    			else show('showDialog','修改失败!编码或教材类型重复',200,100);	
			    			colse('updateDialog');
			    		},
				 "json");	
			}
			
			
			//增加验证
			function yanzheng()
			{
			 var code=$("#codes").val();
			 var name=$("#names").val();
			 var types=$("#typess").val();
			 var position=$("#positions").val();
				if(code.trim()=="")
				{
				 alert("请输入编号");
				}else if(name.trim()=="")
				{
				 alert("请输入名称");
				}else if(types.trim()=="")
				{
				alert("请选择库房所属");
				}else if(position.trim()=="")
				{
				alert("请输入库房地址");
				}else
				{
				jQuery("#submits").submit();
				}
				
			}
		</script>
		<a:ajax successCallbackFunctions="del_callback" parameters="{'id':module_id}" urls="/admin/user/delete_user" pluginCode="001"/>
		<a:ajax successCallbackFunctions="modifystatus_callback" parameters="{'id':user_id,'status':user_status};" urls="/admin/user/update_user_status" pluginCode="002"/>
		<script type="text/javascript">
		//模块ID
		var module_id=0;
		
		function showphoto(id,code,name,types,position)
		{
			var image='<a href="javascript:dels('+id+')">删除</a>  <a href="javascript:updates('+id+','+code+',\''+name+'\','+types+',\''+position+'\')">修改</a>';
			return image;
		}
		
		function adds()
		{
			show('addDiv','提示',250,300);
		}
		
		function updates(id,code,name,types,position)
		{
			$('#id').val(id);
			$('#code').val(code);
			$('#name').val(name);
			$('#types').val(types);
			$('#position').val(position);
			show('updateDiv','提示',250,300);
		}
		
		</script>
		
	</head>
  
  <body>

	
		<!--头部层开始 -->
		<head:head title="库房设置">
			<html:a text="新增" onclick="adds()" icon="add" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<!--Search Begin-->
				<div>
					<%@include file="list.jsp" %>
				</div>
				<!--Search End-->
				<div id="dataDiv1" style="display:block;">
				
					<table class="gv_table_2">
						<tr>
							<th style="width:20px;"><img src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" /></th>
							<th style="text-align:left; font-weight:bold;">库房设置：</th>					
						</tr>
					</table>
				
					<page:plugin 
						pluginCode="123"
						il8nName="storeroom"
						searchListActionpath="page_list_storeroom"
						searchCountActionpath="page_count_storeroom"
						columnsStr="code;name;typesname;position;handle"
						customColumnValue="4,showphoto(id,code,name,types,position)"
						pageSize="10"
					/>
				</div>			
			</body:body>
	
	<!-- 修改 -->
	<div id="updateDialog" style="display:none">
		<table class="add_table">
			<tr>
				<td class="lable_100">模块名称：</td>
				<td><input type="text" class="txt_box_150" id="modularName" name="modularName" maxlength="60" /></td>
			</tr>
			<tr>
				<td class="lable_100">排序：</td>
				<td><input type="text" class="txt_box_150" id="modularOrder" name="modularOrder" maxlength="3" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input id="modularId" type="hidden" />
					<input type="button" class="btn_black_61" name="save" onclick="update();" value="修改"/>
					<input type="button" class="btn_black_61" value="关闭" onclick="closes('updateDialog');" />
				</td>
			</tr>
		</table>
	</div>
	
	<!-- 添加结果 -->
	<div id="showDialog" style="display:none"><table class="add_table"><tr><td colspan="2" align="center"><input type="button" class="btn_black_61" value="关闭" onclick="closes('showDialog');" /></td></tr></table></div>
	
	<div id="addDiv" style="display:none;">
		<form action="add_storeroom" method="post" id="submits">
			<table class="add_table">  
				<tr>
					<td class="lable_100">编码：</td>
					<td>
						<input type="text" class="txt_box_130" name="storeroom.code" id="codes"/>
					</td>
				</tr>
				<tr>
					<td class="lable_100">库房名称</td>
					<td>
					<input type="text" class="txt_box_130" name="storeroom.name" id="names"/><br/>
					</td>
				</tr>
				<tr>
					<td class="lable_100">库房所属</td>
					<td>
					<s:select list="branches" listKey="id" listValue="name" name="storeroom.types" cssClass="txt_box_150" id="typess"></s:select>
					</td>
				</tr>
				<tr>
					<td class="lable_100">库房地址</td>
					<td>
					<input type="text" class="txt_box_130" name="storeroom.position" id="positions"/><br/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="btn_black_61" type="button"  onclick="yanzheng()"  value="保存"/></td>
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
						<input type="hidden" name="storeroom.id" id="id" />
						<input type="text" class="txt_box_130" name="storeroom.code" id="code"/>
					</td>
				</tr>
				<tr>
					<td class="lable_100">教材类型</td>
					<td>
					<input type="text" class="txt_box_130" name="storeroom.name" id="name"/><br/>
					<span id="valquota"></span>
					</td>
				</tr>
				<tr>
					<td class="lable_100">库房所属</td>
					<td>
					<s:select list="branches" listKey="id" listValue="name" name="storeroom.types" cssClass="txt_box_150" id="types"></s:select>
					<span id="valquota"></span>
					</td>
				</tr>
				<tr>
					<td class="lable_100">库房位置</td>
					<td>
					<input type="text" class="txt_box_130" name="storeroom.position" id="position"/><br/>
					<span id="valquota"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input class="btn_black_61" type="button" onclick="xiuyanzheng()" value="保存"/></td>
				</tr>
			</table>
		</form>
		</div>
  </body>
</html>
