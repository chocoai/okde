<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>市场途径设置</title>
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
		<jc:plugin name="loading" />
		<jc:plugin name="validator" />
		<script type="text/javascript">
			function tree_callback(data)
			{
				zTree1=$.fn.zTree.getZTreeObj("treeDemo");
				var lists="";
				if(null!=data.trslist)
				{
					$.each(data.trslist, function()
			    	{	
			    		//添加一级途径	
			    		if(0==this.parentNode)
			    		{  
			    			newNode = [ {id:"_s_"+this.id,name:this.name,open:true,isParent:true}];
							zTree1.addNodes(null, newNode);
						}	     		    	
			    	});
			    	$.each(data.trslist, function()
			    	{		
			    		if(0<this.parentNode)
			    		{  
			    			//添加二级途径
					    	newNode = [ {id:"_s_"+this.id,name:this.name,open:false}];
		     		    	pnode = zTree1.getNodeByParam("id","_s_"+this.parentNode);
							zTree1.addNodes(pnode, newNode);
						}     		    	
			    	});
				}
				change_role();
			}
			
			//获取选中节点ID
			function update()
			{
				zTree1=$.fn.zTree.getZTreeObj("treeDemo");
				var nodes=zTree1.getCheckedNodes();
				ids="@";
	
				b_id=jQuery('#branch_id').val();
				$.each(nodes, function()
				{
					if(0==this.id.indexOf("_s_"))
					{
						ids+=this.id.replace("_s_","")+"@";
					}
				});
				ajax_002_1();
			}
			
			function update_callback(data)
			{
				if(data.results)
			    {
			    	alert_default_dialog(4);
			    }
			    else alert_default_dialog(-4);
			}
			
			function view_callback(data)
			{
				zTree1=$.fn.zTree.getZTreeObj("treeDemo");
				$.each(data.ids, function()
				{
		     		node = zTree1.getNodeByParam("id",this);
					zTree1.checkNode(node, true,true);
				});	
			}
			
			function change_role()
			{
				zTree1=$.fn.zTree.getZTreeObj("treeDemo");
				zTree1.checkAllNodes(false);
				b_id=jQuery('#branch_id').val();
				ajax_003_1();
			}
		</script>
		<a:ajax pluginCode="001" successCallbackFunctions="tree_callback" urls="/basesetting/branchenrollmentway/tree_branch_enrollment_way" />
		<a:ajax pluginCode="002" successCallbackFunctions="update_callback" parameters="{'branchId':b_id,'ids':ids}" urls="/basesetting/branchenrollmentway/update_branch_enrollment_way" />
		<a:ajax pluginCode="003" successCallbackFunctions="view_callback" parameters="{'branchId':b_id}" urls="/basesetting/branchenrollmentway/view_branch_enrollment_way" />
		
		<script type="text/javascript">		
			var setting = {
				view: {
					showLine: true,
					showIcon: true,
					showTitle: true
				},
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};
	
			var zNodes =[];		
			
			var newNode;
			var pnode;
			var zTree1;
			
			jQuery(document).ready(function(){
				init_result_dialog();
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				ajax_001_1();	
			});		
		</script>
	</head>
  
  <body>
	<!--头部层开始 -->
	<head:head title="市场途径订制">
		
	</head:head>
	<!--主体层开始 -->
	<body:body>
		<div>
			<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
				<tr>
					<td class="lable_100">所属机构:</td>
					<td>
					<%-- <s:select cssClass="txt_box_150" onchange="change_role();" id="branch_id" list="blist" listKey="id" listValue="name" theme="simple"></s:select> --%>
					${requestScope.branchObject.name }
					<input type="hidden" id="branch_id" name="branch_id" value="${requestScope.branchObject.id }" />
					</td>
					<td><input type="button" value="保存" id="add_sub" onclick="update();" class="btn_black_61" /></td>
				</tr>
			</table>
		</div>
		<div>
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</body:body>
  </body>
</html>
