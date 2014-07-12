<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>合作方审批</title>
		
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />	
		
		<script type="text/javascript">
		var ids=0;
		var using_status=0;
		var idsStr="";
		
		//删除合同
		function ajax_delete(data)
		{
			closes('isdeleteDiv');
			refresh123();
		}
		
		function ajax_update()
		{
			show('updateDiv','提示',250,150);
			refresh123();
		}
		
		
		function ajax_using()
		{
			show('usingDiv','提示',250,150);
			refresh123();
		}
		
		function ajax_batch_update()
		{
			$('#message_confirm').dialog('close');
			$("#showDialog").html('<b>操作成功！</b>');
			$('#null_for_academy').dialog('open');
			refresh123();
		}
		
		</script>
		
		<a:ajax parameters="{'id':ids};{'id':ids};{'id':ids,'isUsing':using_status}"
				successCallbackFunctions="ajax_delete;ajax_update;ajax_using"
				urls="/enrollment/deletechannel;/enrollment/channelconfirmon;/enrollment/channelusing"
		 pluginCode="123"
		  />

		<a:ajax parameters="{'ids':idsStr}" 
				successCallbackFunctions="ajax_batch_update" 
				urls="/enrollment/batchchannelconfirmon" 
		 pluginCode="001"
		  />
		
		<script type="text/javascript">
			/*
			删除
			*/
			function deleteFun(id){
				ids=id;
				show('isdeleteDiv','提示',250,150);
			}
			
			/*
			审批操作
			*/
			function channleaudit(id)
			{
				ids=id;
				ajax_123_2();
			}
			
			
			/*
			取消删除
			*/
			function cdiv()
			{
				closes('isdeleteDiv');
			}	
			
			//自定义显示列
			function custom(aduitStatus,id)
			{
				if(aduitStatus==AUDIT_STATUS_FALSE)
				{
					return "未审批";
				}
				else if(aduitStatus==AUDIT_STATUS_TRUE)
				{
					isPageOperating(id,123,"checkbox");
					return "已审批";
				}
			}
			
			/*
			自定义显示招生返款政策
			*/
			function channelaudit(id,aduitStatus)
			{
				if(aduitStatus==STATUS_AUTHOR_FALSE)
				{
					return '<a href="javascript:void(0)" onclick="channleaudit('+id+')" >合作方审批</a>';
				}else
				{
					return '<a href="'+WEB_PATH+'/enrollment/chnlplcy/audit_list_chnl_plc_dtl?channelId='+id+'"  target="_blank">招生政策审批</a>';
				}
				
			}
			
			/*
			自定义显示操作
			*/
			function operating(id,aduitstatus)
			{
				
				if(aduitstatus==STATUS_AUTHOR_FALSE)
				{
					return '<a href="'+WEB_PATH+'/enrollment/view_channel?id='+id+'" ><img src="'+WEB_IMAGES+'/images/icon_view.gif"/></a><a href="'+WEB_PATH+'/enrollment/modify_channel?actionResult=confirm&id='+id+'" ><img src="'+WEB_IMAGES+'/images/icon_edit.gif"/></a><a href="javascript:void(0)" onclick="deleteFun('+id+')" ><img src="'+WEB_IMAGES+'/images/icon_del.gif"/></a>';
				}else
				{
					return '<a href="'+WEB_PATH+'/enrollment/view_channel?id='+id+'" ><img src="'+WEB_IMAGES+'/images/icon_view.gif"/></a><a href="'+WEB_PATH+'/enrollment/modify_channel?actionResult=confirm&id='+id+'" ><img src="'+WEB_IMAGES+'/images/icon_edit.gif"/></a>';
					//return '<a href="'+WEB_PATH+'/enrollment/view_channel?id='+id+'" ><img src="'+WEB_IMAGES+'/images/icon_view.gif"/></a>';
				}
				
			}
		
			/*
			自定义显示启用状态
			*/
			function isusing(id,isUsing,aduitStatus)
			{
				if(aduitStatus==STATUS_AUTHOR_FALSE)
				{
					return '停用'
				}else
				{
					if(isUsing==STATUS_AUTHOR_FALSE)
					{
						return '<select id="sel'+id+'" onchange="updateusing('+id+')" ><option value="1">启用</option><option selected="selected" value="0">停用</option></select>'	
					}else
					{
						return '<select id="sel'+id+'" onchange="updateusing('+id+')" ><option selected="selected" value="1">启用</option><option  value="0">停用</option></select>'		
					}
					
				}
				
			}
			
			function updateusing(id)
			{
				ids=id;
				using_status=$('#sel'+id).val();
				ajax_123_3();
			}
			
			
			//超链接
			function a(id){
				return "<a href=\"javascript:alert('超链接')\">"+id+"</a>";
			}
			
			function channelApproval()
			{
				if(getCheckedValues123()==null || getCheckedValues123()=="")
				{
					jQuery("#showDialog").html('<b>请选择要批量审批的政策！</b>');
					jQuery('#null_for_academy').dialog('open');
				}
				else
				{
					$('#message_confirm').dialog('open');
				}
			}
			
			function channelApprovalAjax()
			{
				idsStr=getCheckedValues123();
				ajax_001_1();
			}
			
			
			
			$(document).ready(function(){
				$('#message_confirm').dialog({
					autoOpen:false,
					modal:true,
					title:'确认操作',
					buttons: {
						'确认': function() { 
							channelApprovalAjax();
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						}
					}
				});
				
				$('#null_for_academy').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示',
					buttons: {
						'关闭': function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				
			});
			
		</script>
	</head>
	<body>
		<!-- 头开始 -->
		<head:head title="合作方审批">
			<html:a text="批量审批" icon="update" onclick="channelApproval()"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
				   <tr>
					   <td class="lable_100">学习中心：</td>
					     <td>
					     	<s:select list="%{branchlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="branchId" id="branchId" cssClass="txt_box_150"/>
					     </td>	
					   <td class="lable_100">合作方名称：</td>
					   <td><input type="text" name="channelname" id="channelname" class="txt_box_130"/></td>
					   <td class="lable_100">合作方类别：</td>
					   <td>
					   		<s:select list="%{feelist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="type" id="type" cssClass="txt_box_150"/>
					   </td>
					   <td class="lable_100">审批状态：</td>
					   <td class="lable_100">
					   		<select id="isusing" name="isusing" class="txt_box_130">
					   			<option value="-1">--请选择--</option>
					   			<option value="<%=Constants.AUDIT_STATUS_FALSE %>">未审批</option>
					   			<option value="<%=Constants.AUDIT_STATUS_TRUE %>">已审批</option>
					   		</select>
					   </td> 
						<td class="lable_100"><input type="button" name="" id="" onclick="search123();" value="查询" class="btn_black_61" /></td>  
					 </tr>
				</table>
				
					<page:plugin 
						pluginCode="123"
						il8nName="enrollment"
						searchListActionpath="listchannel"
						searchCountActionpath="countchannel"
						columnsStr="#operating;branchName;channelTypeName;name;linkman;telephone;contractNo;isUsing;#aduitstatus;statistics;#audit;"
						customColumnValue="0,operating(id,aduitStatus);7,isusing(id,isUsing,aduitStatus);8,custom(aduitStatus,id);10,channelaudit(id,aduitStatus);"
						pageSize="20"
						isNumber="false"
						isChecked="true"
						checkboxValue="id"
						params="'type':$('#type').val(),'branchid':$('#branchId').val(),'channelname':$('#channelname').val(),'isusing':$('#isusing').val()"
					/>
				
				<div id="isdeleteDiv" style="display:none">
				<div style="float:inherit">确定要删除吗？</div><br/>
				<input type="button" value="确定" id="btnok" name="btnok" onclick="ajax_123_1()" class="btn_black_61"/> 
				<input type="button" value="取消" id="tt" name="tt" onclick="cdiv()" class="btn_black_61"/> 
		
				</div>			  	
					
					
				<div id="updateDiv" style="display:none">
				审批成功！！
				</div>	
				
				<div id="usingDiv" style="display:none">
				操作成功！！
				</div>	
				
				<div id="message_confirm" style="display:none">
					<div align="center">确认执行审批？</div>
				</div>
				
				<div id="null_for_academy" style="display:none">
					<div align="center" id="showDialog"></div>
				</div>
	
			</body:body>
	</body>

</html>