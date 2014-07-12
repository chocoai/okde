<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title> 入庫方式</title>
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
			
			
			//删除参数
			var deleteid=0;
			//删除判断
			function delpanduan(id)
			{
			deleteid=id;
			show("isdeleteDiv",'提示',250,150);
			}
			function delguanbi()
			{
			closes("isdeleteDiv");
			}		
			
			//删除 		
			function delbc() {
				
				var id=0;
				id=deleteid;
				jQuery.post('<s:url value="delete_meterialstorage"/>', {
					"id" : id
				}, function(data) {
					if (data.results) {
						show('showDialog', '删除成功!', 150, 100);
						window.location.reload();
					} else
						show('showDialog', '删除失败!', 150, 100);
				}, "json");
			}
			
			//修改 
			function updbc() {
				var id = $('#id').val();
				var code = $('#code').val();
				var name = $('#name').val();
				jQuery.post('<s:url value="update_meterialstorage"/>', {
					"meterialstorage.id" : id,
					"meterialstorage.name" : name,
					"meterialstorage.code" : code
				}, function(data) {
					if (data.results) {
						show('showDialog', '修改成功!', 150, 100);
						//close('updateDiv');
						window.location.reload();
					} else
						show('showDialog', '修改失败!', 150, 100);
				}, "json");
			}
			 
			function addmeterialstorage() {
				show('addDiv', '提示', 250, 300);
			}
			
			function updatemeterialstorage(id, code, name) {
				$('#id').val(id);
				$('#code').val(code);
				$('#name').val(name);
				show('updateDiv', '提示', 250, 300);
			}
			
			//验证
			function yanzheng()
			{
				 var code=$('#addcode').val();
				 var name=$("#addname").val();
				 
			 	if(code.trim()=="")
				 {
				 	alert("请输入编号");
				 }else if(name.trim()=="")
				 {
				 	alert("请输入名称");
				 }else
				 {
				 jQuery("#submits").submit();
				 }
			}
			
			</script>

	</head>

	<body>


		<!--头部层开始 -->
 
		<head:head title=" 入庫方式">
			<html:a text="新增" onclick="addmeterialstorage()" icon="add" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Search Begin-->
			<div>
				<!--标题导航-->
				<%@include file="list.jsp"%>
			</div>
			<!--Search End-->
			<div id="dataDiv1" style="display: block;">

				<table class="gv_table_2"  align="center">
					<tr>
						<th style="width: 20px;">
							<img
								src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" />
						</th>
						<th style="text-align: left; font-weight: bold;">
							入库方式：
						</th>
					</tr>
				</table>
				
					<table class="gv_table_2">
					<tr style="text-align: left; font-weight: bold;width: 20px;">
						 	<td  align="center">编码</td><td  align="center">入库方式</td><td  align="center">操作</td>
					</tr>
					<s:iterator var="met" value="list">
					<tr>
					<td  align="center"><s:property value="#met.code"/></td>
					<td  align="center"><s:property value="#met.name"/></td>
					<td  align="center"><input type="button" value="修改" class="btn_black_61"  onclick="updatemeterialstorage(<s:property value='#met.id'/>,'<s:property value="#met.code"/>','<s:property value='#met.name'/>')"/> <input type="button" value="删除" class="btn_black_61"  onclick="delpanduan(<s:property value='#met.id'/>)"/></td>
					</tr>
					</s:iterator>
				</table>
 
					 
			</div>
		</body:body>


		<!-- 添加结果 -->
		<div id="showDialog" style="display: none">
			<table class="add_table">
				<tr>
					<td colspan="2" align="center">
						<input type="button" class="btn_black_61" value="关闭"
							onclick="closes('showDialog');" />
					</td>
				</tr>
			</table>
		</div>


		<div id="addDiv" style="display: none;">
			<form action="add_meterialstorage" method="post" id="submits">
				<table class="add_table">
					<tr>
						<td class="lable_100">
							编码：
						</td>
						<td>
							<input type="text" class="txt_box_130" name="meterialstorage.code" id="addcode"/>
						</td>
					</tr>
					<tr>
						<td class="lable_100">
							入库方式
						</td>
						<td>
							<input type="text" class="txt_box_130" name="meterialstorage.name"  id="addname"/>
							<br />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input class="btn_black_61" type="button" onclick="yanzheng()" value="保存" />
						</td>
					</tr>
				</table>
			</form>
		</div>


		<div id="updateDiv" style="display: none;">
			<form>
				<table class="add_table">
					<tr>
						<td class="lable_100">
							编码
						</td>
						<td>
							<input type="hidden" name="meterialstorage.id" id="id" />
							<input type="text" class="txt_box_130" name="meterialstorage.code"
								id="code" />
						</td>
					</tr>
					<tr>
						<td class="lable_100">
							入库方式
						</td>
						<td>
							<input type="text" class="txt_box_130" name="meterialstorage.name"
								id="name" />
							<br />
							<span id="valquota"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input class="btn_black_61" type="button" onclick="updbc()"
								value="保存" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div id="isdeleteDiv" style="display:none">
		<div style="float:inherit">确定要删除吗？</div><br/>
		<input type="button" value="确定" id="btnok" name="btnok" onclick="delbc()" class="btn_black_61"/> 
		<input type="button" value="取消" id="tt" name="tt" onclick="delguanbi()" class="btn_black_61"/> 
		</div>
	</body>
</html>
