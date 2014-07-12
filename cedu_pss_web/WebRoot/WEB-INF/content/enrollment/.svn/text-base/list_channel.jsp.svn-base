<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>合作方申请</title>
		
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
		
		//删除合同
		function ajax_delete(data)
		{
			closes('isdeleteDiv');
			refresh123();
		}
		
		
		</script>
		
		<a:ajax parameters="{'id':ids}"
				successCallbackFunctions="ajax_delete"
				urls="/enrollment/deletechannel"
		 pluginCode="123"
		  />
		
		<script type="text/javascript">
			//删除
			function deleteFun(id){
				ids=id;
				show('isdeleteDiv','提示',250,150);
			}
			
			
			/*
			取消删除
			*/
			function cdiv()
			{
				closes('isdeleteDiv');
			}	
			
			//自定义显示列
			function custom(aduitStatus)
			{
				return aduitStatus==STATUS_AUTHOR_FALSE?"未审批":"已审批"
			}
			
			/*
			自定义显示招生返款政策
			*/
			function policychannel(id)
			{
				return '<a href="'+WEB_PATH+'/enrollment/chnlplcy/index_list_chnl_plc_dtl?channelId='+id+'" target="_blank">设置政策</a>';
			}
			
			/*
			自定义显示操作
			*/
			function operating(id,aduitstatus)
			{
				
				if(aduitstatus==STATUS_AUTHOR_FALSE)
				{
					return '<a href="'+WEB_PATH+'/enrollment/view_channel?id='+id+'" ><img src="'+WEB_IMAGES+'/images/icon_view.gif"/></a><a href="'+WEB_PATH+'/enrollment/modify_channel?id='+id+'" ><img src="'+WEB_IMAGES+'/images/icon_edit.gif"/></a><a href="javascript:void(0)" onclick="deleteFun('+id+')" ><img src="'+WEB_IMAGES+'/images/icon_del.gif"/></a>';
				}
				return '<a href="'+WEB_PATH+'/enrollment/view_channel?id='+id+'" ><img src="'+WEB_IMAGES+'/images/icon_view.gif"/></a>';
			}
		
			/*
			自定义显示启用状态
			*/
			function isusing(isUsing)
			{
				return isUsing==STATUS_AUTHOR_FALSE?"停用":"启用"
			}
			
			
			//超链接
			function a(id){
				return "<a href=\"javascript:alert('超链接')\">"+id+"</a>";
			}
			
			
			
			
			
		</script>
	</head>
	<body>
		<!-- 头开始 -->
		<head:head title="合作方申请">
			<html:a text="添加合作方" icon="add" href="/enrollment/add_channel"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
				   <tr>
					   <td class="lable_100">学习中心：</td>
					     <td>
					     	<s:select list="%{branchlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="branchId" id="branchId" cssClass="txt_box_150"/>
					     </td>	
					   <td class="lable_100">合作方名称：</td><td><input type="text" name="channelname" id="channelname" class="txt_box_130"/></td>
					   <td class="lable_100">合作方类别：</td>
					   <td>
					   		<s:select list="%{feelist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="type" id="type" cssClass="txt_box_150"/>
					   </td>	 
						<td class="lable_100"><input type="button" name="" id="" onclick="search123();" value="查询" class="btn_black_61" /></td>  
					 </tr>
				</table>
				
					<page:plugin 
						pluginCode="123"
						il8nName="enrollment"
						searchListActionpath="listchannel"
						searchCountActionpath="countchannel"
						columnsStr="#operating;branchName;channelTypeName;name;linkman;telephone;contractNo;isUsing;#aduitstatus;statistics;#policychannel;"
						customColumnValue="0,operating(id,aduitStatus);7,isusing(isUsing);8,custom(aduitStatus);10,policychannel(id);"
						pageSize="20"
						isNumber="false"
						isChecked="false"
						params="'type':$('#type').val(),'branchid':$('#branchId').val(),'channelname':$('#channelname').val()"
					/>
				
				<div id="isdeleteDiv" style="display:none">
				<div style="float:inherit">确定要删除吗？</div><br/>
				<input type="button" value="确定" id="btnok" name="btnok" onclick="ajax_123_1()" class="btn_black_61"/> 
				<input type="button" value="取消" id="tt" name="tt" onclick="cdiv()" class="btn_black_61"/> 
		
				</div>			  	
					
				</body:body>
	</body>

</html>