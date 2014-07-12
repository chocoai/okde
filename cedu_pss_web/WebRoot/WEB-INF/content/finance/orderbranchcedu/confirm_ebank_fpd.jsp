<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>汇款总部(中心) 总部确认</title>
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
	function buildOp(id, status){
		var str = '';
		
		if(status==PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU){
			str+= '<a href="javascript:confirmOrder('+id+')">总部确认</a>';
		}
		
		return str;
	}
	
	function getStatus(status){
		var str = '';
		
		switch(status){
		case PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN:
			str = '已填汇款单';
			break;
		case PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU:
			str = '已汇款总部';
			break;
		case PAYMENT_STATUS_ZONG_BU_QUE_REN:
			str = '总部确认';
			break;
		}
		
		return str;
	}
	
	//显示缴费单状态
	function showstatus(status)
	{
		return status.getPaymentStatus();
	}
	
	CONFIRM_DATA = null;
	function confirmOrder(id){
		CONFIRM_DATA = {fpdId: id};
		$.confirm({
			msg: '您确认已收到汇款吗？',
			confirm: function(){
				ajax_confirm_1();
			}
		});
	}
	function confirmCallback(data){
		$.alert('操作成功！');
		search001();
	}
	
	$(function(){
		$('#table001 > tbody').html('<tr><td colspan="11" align="center">请单击[查询]！</td></tr>');
	});
	</script>
	<a:ajax successCallbackFunctions="confirmCallback" parameters="CONFIRM_DATA" pluginCode="confirm" urls="/finance/orderbranchcedu/json_confirm_fpd_by_cedu"/>
</head>
<body>
	<head:head title="汇款总部 总部确认">
		<html:a text="返回" icon="return" onclick="history.go(-1);"/>
	</head:head>
	<body:body>
	<%@include file="_cedu_confirm_tab.jsp" %>
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">汇款总部 &gt;&gt; 总部确认</th>
				<th></th>
			</tr>
		</table>
			
	<form id="searchParam">
		<table class="add_table">
			<tr>
				<td class="lable_100">汇款单位：</td>
				<td><s:select headerKey="0" headerValue="--请选择--" list="branches" listKey="id" listValue="name" name="branchId" cssClass="txt_box_150"></s:select></td>
				<td class="lable_100">收款单位：</td>
				<td><s:property value="cedu.name"/></td>
			</tr>
			<tr>
				<!-- td class="lable_100">缴费单明细号：</td>
				<td><input type="text" name="fpdNo" class="txt_box_150"/></td -->
				<td class="lable_100">汇款单状态：</td>
				<td>
					<select name="status" class="txt_box_150">
						<option value="0">--请选择--</option>
						<option value="<s:property value="@net.cedu.common.Constants@PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU"/>">已汇款总部</option>
						<option value="<s:property value="@net.cedu.common.Constants@PAYMENT_STATUS_ZONG_BU_QUE_REN"/>">总部确认</option>
					</select>
				</td>
				<td></td>
				<td ><input type="button" class="btn_black_61" value="查询" onclick="search001()"/></td>
			</tr>
		</table>
	</form>

		

		<page:plugin 
			pluginCode="001"
			il8nName="finance_payment"
			subStringLength="10"
			searchListActionpath="/finance/orderbranchcedu/json_list_e_bank_fpd"
			searchCountActionpath="/finance/orderbranchcedu/json_count_e_bank_fpd"
			columnsStr="code;schoolName;academyenrollbatchName;levelName;majorName;paymentSubjectName;studentName;amountPaied;#status;#operation"
			customColumnValue="8,showstatus(status);9,buildOp(id,status)"
			isPage="true"
			searchFormId="searchParam"
			isonLoad="true"
			isPackage="false"
			isOrder="false"
		/>

	</body:body>
</body>
</html>
