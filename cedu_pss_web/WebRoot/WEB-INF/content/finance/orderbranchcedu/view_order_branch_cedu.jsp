<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>汇款总部(中心) 汇款单明细</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!--  分页 -->
	<jc:plugin name="page" />
	<!-- 时间控件 -->
	<jc:plugin name="calendar" />
	<!-- 选项卡 -->
	<jc:plugin name="tab" />
	<!-- 表单验证 -->
	<jc:plugin name="validator" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	<script type="text/javascript">
(function($){ // CODE_IN_JQUERY
	show_added_fee_items = function(index)
	{
		$('#added_feeItem_table').show();
		$('#added_feeItem_table > tbody').hide();
		$($('#added_feeItem_table > tbody').get(index)).show();
	}
	switch_search_type = function()
	{
		$(':radio[name="search_type"]').each(function(){
			if(this.checked){
				$('#search_type_'+this.value).show();
			}
			else{
				$('#search_type_'+this.value).hide();
			}
		});
		
		$('#feeItemListDiv').hide();
	}

	$(function(){
		//util.select.initOption('[name="branch"]', get_branch(), '', {text:'弘成总部',value:''});
		
		$('#new_feeItem_div').hide();
		$(':radio[name="search_type"]').change(switch_search_type);
	});
})(jQuery); // CODE_IN_JQUERY END
	</script>
</head>
<body>
	<head:head title="汇款总部  汇款单明细">
		
	</head:head>
	<body:body>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
				 	<th style="text-align:left; font-weight:bold;">汇款总部 &gt;&gt; 汇款单明细</th>
					<th></th>
				</tr>
			</table>
			
			<table class="add_table">
				<tr>
					<td class="lable_100">汇款单位：</td>
					<td><s:property value="branch.name"/></td>
				</tr>
				<tr>
					<td class="lable_100">收款单位：</td>
					<td><s:property value="cedu.name"/></td>
				</tr>
				<tr>
					<td class="lable_100">汇款账号：</td>
					<td><input type="text" class="txt_box_150" value="<s:property value="order.remitterAccount" />" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="lable_100">收款账号：</td>
					<td><input type="text" class="txt_box_150" value="<s:property value="order.remitteeAccount" />" disabled="disabled"/></td>
				</tr>
				<tr>
					<td class="lable_100">显示缴费单：</td>
					<td>
<!-------------------------------  START 缴费单处理 START  ------------------------------->
			<table id="added_feeItem_table" class="gv_table_2" width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th width="15%">院校</th>
						<th width="15%">学习中心</th>
						<th width="7%">批次</th>
						<th width="7%">层次</th>
						<th>专业</th>
						<th width="7%">姓名</th>
						<th width="6%">费用科目</th>
						
						<th width="7%">汇款金额</th>
						<th width="7%">状态</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="list" var="dtl">
					<tr>
						<td align="center"><s:property value="#dtl.schoolName"/></td>
						<td align="center"><s:property value="#dtl.branchName"/></td>
						<td align="center"><s:property value="#dtl.academyenrollbatchName"/></td>
						<td align="center"><s:property value="#dtl.levelName"/></td>
						<td align="center"><s:property value="#dtl.majorName"/></td>
						<td align="center"><s:property value="#dtl.studentName"/></td>
						<td align="center"><s:property value="#dtl.paymentSubjectName"/></td>
						
						<td align="center"><s:property value="#dtl.payBranchCedu"/></td>
						<td align="center"><s:property value="%{getText('detail.status.'+#dtl.status)}"/></td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
<!-------------------------------    END 缴费单处理 END  ------------------------------->	
					</td>
				</tr>
				<tr>
					<td class="lable_100">汇款总额：</td>
					<td><input type="text" name="" id="" class="txt_box_150" value="<s:property value="order.amount"/>" disabled="disabled"/></td>
				</tr>
				<!--<tr>
				  <td class="lable_100">票据：</td>
				  <td>
					<s:if test="@org.apache.commons.lang.StringUtils@isNotBlank(order.imgUrl)"><img src="<s:url value="/%{order.imgUrl}"/>" width="580" height="369" /></s:if>
					<s:else>(没有票据图片)</s:else>
				  </td>
			  </tr-->
				<tr>
					<td class="lable_100">备注：</td>
					<td><textarea cols="60" rows="6" disabled="disabled"><s:property value="order.note"/></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" class="btn_black_61" onclick="self.close()" value="关闭"/></td>
				</tr>
			</table>
	</body:body>
</body>
</html>
