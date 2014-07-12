<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>权限集设置</title>
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
			    		//添加子系统根节点	    
			    		newNode = [ {id:"_s_"+this.subSystem.id,name:this.subSystem.name,open:true,isParent:true}];
						zTree1.addNodes(null, newNode);

	     		    	var ml=this.mlist;
	     		    	$.each(ml, function()
			    		{
			    			//添加模块节点
			    			newNode = [ {id:"_m_"+this.modular.id,name:this.modular.name,open:true}];
	     		    		pnode = zTree1.getNodeByParam("id","_s_"+this.modular.subSystemId);
							zTree1.addNodes(pnode, newNode);
			    			
			    			var psl=this.pslist;
			    			$.each(psl, function()
				    		{
				    			//添加权限集节点
				    			newNode = [ {id:"_ps_"+this.privilegeSet.id,name:this.privilegeSet.name,open:true}];
		     		    		pnode = zTree1.getNodeByParam("id","_m_"+this.privilegeSet.modularId);
								zTree1.addNodes(pnode, newNode);
				    		
				    			var pl=this.plist;
				    			$.each(pl, function()
					   			{
					   				//添加权限节点
					    			newNode = [ {id:"_p_"+this.id,name:this.name,open:true}];
		     		    			pnode = zTree1.getNodeByParam("id","_ps_"+this.setId);
									zTree1.addNodes(pnode, newNode);
					    		});	
				    		});	
			    		});	
			    	});
				}
				change_group();
			}
			
			//获取选中节点ID
			function update()
			{
				zTree1=$.fn.zTree.getZTreeObj("treeDemo");
				var nodes=zTree1.getCheckedNodes();
				s_ids="_";
				m_ids="_";
				ps_ids="_";
				p_ids="_";
				g_id=${userGroup.id};
				$.each(nodes, function()
				{
					if(0==this.id.indexOf("_s_"))
					{
						s_ids+=this.id.replace("_s_","")+"_";
					}
					if(0==this.id.indexOf("_m_"))
					{
						m_ids+=this.id.replace("_m_","")+"_";
					}
					if(0==this.id.indexOf("_ps_"))
					{
						ps_ids+=this.id.replace("_ps_","")+"_";
					}
					if(0==this.id.indexOf("_p_"))
					{
						p_ids+=this.id.replace("_p_","")+"_";
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
				$.each(data.plist, function()
				{
		     		node = zTree1.getNodeByParam("id",this);
		     		if(node!=null){
		     			zTree1.checkNode(node, true,true);
		     		}
					
				});	
			}
			
			function change_group()
			{
				zTree1=$.fn.zTree.getZTreeObj("treeDemo");
				zTree1.checkAllNodes(false);
				g_id=${userGroup.id};
				ajax_003_1();
			}
			
			function copy()
			{
				g_id=${userGroup.id};
				ajax_004_1();
			}
			
			function copy_callback(data)
			{
				if(data.results)
			    {
			    	alert_default_dialog(4);
			    }
			    else alert_default_dialog(-4);
			}
		</script>
		<a:ajax pluginCode="001" successCallbackFunctions="tree_callback" parameters="{'roleId':roleId}" urls="/admin/privilege/groupprivilege/tree_group_privilege" />
		<a:ajax pluginCode="002" successCallbackFunctions="update_callback" parameters="{'groupId':g_id,'subSystemIds':s_ids,'modularIds':m_ids,'setIds':ps_ids,'privilegeIds':p_ids}" urls="/admin/privilege/groupprivilege/update_group_privilege" />
		<a:ajax pluginCode="003" successCallbackFunctions="view_callback" parameters="{'groupId':g_id}" urls="/admin/privilege/groupprivilege/view_group_privilege" />
		<a:ajax pluginCode="004" successCallbackFunctions="copy_callback" parameters="{'id':g_id}" urls="/admin/privilege/groupprivilege/copy_to_all_group_privilege" />
		
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
				roleId=${userGroup.roleId};
				ajax_001_1();	
			});		
		</script>
	</head>
  
  <body>
	<!--头部层开始 -->
	<head:head title="组权限设置">
		<html:a text="返回" href="/admin/user/group/index_user_group" icon="return" />
	</head:head>
	<!--主体层开始 -->
	<body:body>
		<div>
			<table width="100%"class="add_table" border="0" cellpadding="2" cellspacing="0">
				<tr>
					<td class="lable_100">机构:</td>
					<td>${userGroup.org.name }</td>
					<td class="lable_100">用户组:</td>
					<td>${userGroup.name}</td>
					<td>
						<input type="hidden" id="groupId" value="${userGroup.id}" />
						<s:if test="#session.userTicket.orgId<=1&&userGroup.org.id>1">
							<input type="button" value="复制到所有学习中心" id="copy_sub" onclick="copy();" class="btn_black_130" />
						</s:if>
						<input type="button" value="保存" id="add_sub" onclick="update();" class="btn_black_61" />
					</td>
				</tr>
			</table>
		</div>
		<div>
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</body:body>
  </body>
</html>
