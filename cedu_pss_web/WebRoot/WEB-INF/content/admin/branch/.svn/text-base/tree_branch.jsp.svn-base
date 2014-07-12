<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学习中心树</title>
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
		<script type="text/javascript">		
		var setting = {
			view: {
				showLine: true,
				showIcon: true,
				showTitle: true
			}
		};

		var zNodes =[];		
		
		var newNode;
		var pnode;
		var zTree1;
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$.post('<s:url value="list_branch"/>',{type:-1},
			function(data)
			{
			   var lists="";
			   zTree1=$.fn.zTree.getZTreeObj("treeDemo");
			   $.each(data.list, function()
		    	{		
		    		if(0==this.parentId)    			
     		    	{
     		    		newNode = [ {id:this.id,name:this.name,open:true,isParent:true}];
						zTree1.addNodes(null, newNode);
     		    	}
     		    	if(0<this.parentId)
     		    	{
     		    		newNode = [ {id:this.id,name:this.name,open:true}];
     		    		pnode = zTree1.getNodeByParam("id",this.parentId);
						zTree1.addNodes(pnode, newNode);
     		    	}
		    	});
			},"json");	
		});
		</script>
	</head>
  
  <body>
	<!--头部层开始 -->
	<head:head title="学习中心树">
		<html:a text="列表显示" href="admin/branch/index_branch" icon="receive" />
	</head:head>
	<!--主体层开始 -->
	<body:body>				
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="right">
	</div>
	</body:body>
  </body>
</html>
