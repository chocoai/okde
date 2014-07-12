<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>财务管理  应收院校款</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 表单验证 -->
	<jc:plugin name="validator" />
	<!-- 分页 -->
	<jc:plugin name="page" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	<script type="text/javascript">
	function buildAttachment(id){
		return '<a target="_blank" href="<uu:url url="/finance/academybill/download_academy_bill_attachment"/>?billId='+id+'">下载附件</a>';
	}
	
	function buildStatus(status){
		if(status == TRUE){
			return '已返款';
		}
		
		return '未返款';
	}
	
	function buildOperation(id, isRebate){
		var str = '';
		
		str += '<a href="<uu:url url="/finance/academybill/view_academy_bill"/>?billId='+id+'"><img src="<ui:img url="/images/icon_view.gif"/>" /></a>';
		
		if(!isRebate){
			str += '<a href="<uu:url url="/finance/academybill/edit_academy_bill"/>?billId='+id+'"><img src="<ui:img url="/images/icon_edit.gif"/>" /></a>';
			str += '<a href="javascript:deleteBill('+id+')"><img src="<ui:img url="/images/icon_del.gif"/>" /></a>';
		}
		
		return str;
	}
	
	DELETE_PARAM = null;
	function deleteBill(id){
		DELETE_PARAM = {billId: id};
		$.confirm({
			msg: '您确认要删除吗？',
			confirm: function(){
				ajax_delete_1();
			}
		});
	}
	function deleteCallback(data){
		var msg = '删除成功！';
		if(data.errno != 0){
			msg = '删除失败！';
		}
		$.alert(msg);
		//search001();
		refresh001();
	}
	
	jQuery(function(){
	});
	</script>
	
	<a:ajax parameters="DELETE_PARAM" successCallbackFunctions="deleteCallback" pluginCode="delete" urls="/finance/academybill/delete_academy_bill"/>
</head>
<body>
	<head:head title="应收院校款">
		<html:a text="新增应收院校款" icon="add" href="/finance/academybill/add_academy_bill"/>
	</head:head>
	<body:body>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
				 	<th style="text-align:left; font-weight:bold;">应收院校款</th>
					<th></th>
				</tr>
			</table>
		<form id="searchForm">
			<table class="add_table">
			  <tr>			
                <td class="lable_100">院校：</td>
                <td>
                	<s:select id="academyId" name="academyId" headerKey="0" headerValue="--请选择--" list="academies" listKey="id" listValue="name" cssClass="txt_box_150"></s:select>
                </td>
                 <td class="lable_100">学习中心：</td>
                <td>
                	<s:if test="%{branchlist!=null}">
						<s:select list="%{branchlist}" listKey="id" theme="simple" headerKey="0" headerValue="--请选择--" listValue="name" name="branchId" id="branchId" cssClass="txt_box_150"/>
					</s:if>
					<s:else>
						<select name="branchId" id="branchId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>
					</s:else>
                </td>
                 <td class="lable_100">收款类型：</td>
                <td >
                	<s:select id="receivedWay" name="receivedWay" headerKey="0" headerValue="--请选择--" list="receivedWays" listKey="id" listValue="name" cssClass="txt_box_100"></s:select>
                </td>
				 <td class="lable_100">收款类型：</td>
				 <td>
					<input type="button" class="btn_black_61" value="查询" onclick="search001()"/>
				</td>
              </tr>
            </table>
			<input type="hidden" name="isRebate" value="-1"/>
		</form>
			
			<page:plugin
				pluginCode="001"
				il8nName="finance"
				subStringLength="10"
				searchListActionpath="/finance/academybill/list_academy_bill_data"
				searchCountActionpath="/finance/academybill/list_academy_bill_count"
				columnsStr="#operation;academyName;branchName;receivedWayName;amount;note;#status;#attachment"
				customColumnValue="0,buildOperation(id,isRebate);6,buildStatus(isRebate);7,buildAttachment(id)"
				pageSize="20"
				isPage="true"
				searchFormId="searchForm"
				isonLoad="true"
				isPackage="false"
				isOrder="false"
			/>

	</body:body>
</body>
</html>
