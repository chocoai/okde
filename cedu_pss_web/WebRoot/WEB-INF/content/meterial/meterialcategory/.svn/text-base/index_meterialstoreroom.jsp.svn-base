<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title> 庫房設置</title>
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
		//删除库房位置			
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
			jQuery.post('<s:url value="delete_meterialstoreroom"/>', {
				"id" : id
			}, function(data) {
				if (data.results) {
					show('showDialog', '删除成功!', 150, 100);
					window.location.reload();
				} else
					show('showDialog', '删除失败!', 150, 100);
			}, "json");
		}

       function addmeterialstoreroom() {
	   show('addDiv', '提示', 250, 300);
       } 

		//修改库房
		function updbc() {
			var id = $('#id').val();
			var code = $('#code').val();
			var name = $('#name').val(); 
			var position=$('#position').val();
			jQuery.post('<s:url value="update_meterialstoreroom"/>', {
				"meterialstoreroom.id" : id,
				"meterialstoreroom.name" : name,
				"meterialstoreroom.code" : code,
				"meterialstoreroom.position" : position,
			}, function(data) {
				if (data.results) {
					show('showDialog', '修改成功!', 150, 100);
					window.location.reload();
					 
				} else
					show('showDialog', '修改失败!', 150, 100);
					window.location.reload();
			}, "json");
		}
		 
		 //修改
		   function updatemeterialstoreroom(id, code, name,position) {
			 $('#id').val(id);
			 $('#code').val(code);
			 $('#name').val(name);
			 $('#position').val(position);
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
				 }else if(jQuery("#selectposition").val()==0)
				 {
				 alert("请选择库房位置");
				 }else
				 {
				 jQuery("#submits").submit();
				 }
			}
</script>

	</head>

	<body>


		<!--头部层开始 -->
		<!-- book/add_measuringunits -->
		<head:head title="${branchName }庫房設置">
			<html:a text="新增" onclick="addmeterialstoreroom()" icon="add" />
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

				<table class="gv_table_2">
					<tr>
						<th style="width: 20px;">
							<img
								src="<ui:img url="/plugin/base_css/images/operating/title_left.gif" />" />
						</th>
						<th style="text-align: left; font-weight: bold;">
							库房设置：
						</th>
					</tr>
				</table>
				
					<table class="gv_table_2">
					<tr  align="center" >
						 	<td >编码</td><td>库房名称</td><td>库房位置</td><td>操作</td>
					</tr>
					<s:iterator var="met" value="list">
					<tr  align="center">
					<td><s:property value="#met.code"/></td>
					<td><s:property value="#met.name"/></td>
					<td style="width:30%"><s:property value="#met.positionName"/></td>
					<td><input type="button" value="修改" class="btn_black_61"  onclick="updatemeterialstoreroom(<s:property value='#met.id'/>,'<s:property value='#met.code'/>','<s:property value='#met.name'/>',<s:property value='#met.position'/>)"/> <input type="button" value="删除" class="btn_black_61"  onclick="delpanduan(<s:property value='#met.id'/>)"/></td>
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
			<form action="add_meterialstoreroom" method="post" id="submits">
				<table class="add_table">
					<tr>
						<td class="lable_100">
							编码：
						</td>
						<td>
							<input type="text" class="txt_box_130" name="meterialstoreroom.code" id="addcode"/>
						</td>
					</tr>
					<tr>
						<td class="lable_100">
							库房名称:
						</td>
						<td>
							<input type="text" class="txt_box_130" name="meterialstoreroom.name"  id="addname"/>
							<br />
						</td>
 
					</tr>
					<tr><td>库房位置:</td>
                       <td  class="lable_100">
 
						 <s:select cssClass="txt_box_150" list="branchlst" listKey="id" listValue="name"  id="selectposition"  name="meterialstoreroom.position" headerKey="0" headerValue="=====全选======">
							 </s:select>	  	
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
							编码：
						</td>
						<td>
							<input type="hidden" name="meterialcategory.id" id="id" />
							<input type="text" class="txt_box_130" name="meterialstoreroom.code"
								id="code" />
						</td>
					</tr>
					<tr>
						<td class="lable_100">
							库房名称
						</td>
			 
						<td>
							<input type="text" class="txt_box_130" name="meterialstoreroom.name"
								id="name" />
							<br />
							<span id="valquota"></span>
						</td>
					</tr>
					<tr><td>库房位置</td>
                       <td  class="lable_100">
						<s:select cssClass="txt_box_150" list="branchlst" listKey="id" listValue="name" id="position" name="meterialstoreroom.position" headerKey="0" headerValue="=====全选======">
							 </s:select>	
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
