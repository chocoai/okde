<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>院校返款 返款单详情</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 表单验证 -->
	<jc:plugin name="validator" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- 分页 -->
	<jc:plugin name="page" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	
	<script type="text/javascript">
	
		function buildAttachment(id){
			return '<a target="_blank" href="<uu:url url="/finance/academybill/download_academy_bill_attachment"/>?billId='+id+'">下载附件</a>';
		}
		
		function toBillStatus(isRebate){
			if(isRebate == TRUE){
				return '已返款';
			}
			
			return '未返款';
		}
		
		function billAmount(amount){
			if(typeof(amount) != 'undefined'){
				return '<div>'+amount+'</div>';
			}
			
			return '[无]';
		}
		//显示缴费单状态
		function showstatus(status)
		{
			return status.getPaymentStatus();
	 	}
		
		jQuery(function(){		
			
		});
	</script>
	
	
</head>
<body>
	<head:head title="院校返款">
		<html:a text="关闭" icon="return" onclick="window.close()" target="_self"/>
	</head:head>
	<body:body>
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">院校返款单详情</th>
				<th></th>
			</tr>
		</table>
		<input type="hidden" id="payAcademyCeduId" name="payAcademyCeduId" value="${payAcademyCedu.id}" />	
		<table class="add_table">
			<tbody id="rebateCondition">
				<tr>
					<td class="lable_100">收款单位：</td>
					<td><s:property value="cedu.name"/></td>
				</tr>
				<tr>
					<td class="lable_100">返款院校：</td>
					<td>
						${payAcademyCedu.remitterName}
						<input type="hidden" id="remitteeId" name="remitteeId" value="${payAcademyCedu.remitterId}" />
					</td>
				</tr>
				<tr>
					<td class="lable_100">缴费单返款金额：</td>
					<td><span id="paysum"><s:property value="payAcademyCedu.paymentAmount"/></span></td>
				</tr>
				<tr>
					<td class="lable_100">追加金额：</td>
					<td><span id="paysum"><s:property value="payAcademyCedu.addPaied"/></span></td>
				</tr>
				<tr>
					<td class="lable_100">其他应收金额：</td>
					<td><span id="paysum"><s:property value="payAcademyCedu.academyAmount"/></span></td>
				</tr>
				<tr>
					<td class="lable_100">调整金额：</td>
					<td><span id="paysum"><s:property value="payAcademyCedu.adjustPaied"/></span></td>
				</tr>
				<tr>
					<td class="lable_100">实返总金额：</td>
					<td><span id="paysum"><s:property value="payAcademyCedu.amountPaied"/></span></td>
				</tr>
				<tr>
					<td class="lable_100">备注：</td>
					<td><s:property value="payAcademyCedu.note"/></td>
				</tr>
				
		  	</tbody>
		 </table>
	  	 <table class="gv_table_2">
		 	<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">院校返款缴费单明细</th>
				<th></th>
			</tr>
		</table>
			<page:plugin 
				pluginCode="001"
				il8nName="finance_payment"
				subStringLength="20"
				searchListActionpath="view_academy_rebate_review_list_ajax"
				searchCountActionpath="view_academy_rebate_review_count_ajax"
				columnsStr="paymentCode;schoolName;branchName;academyenrollbatchName;levelName;majorName;studentName;paymentSubjectName;jiaofeiValue;payAcademyCedu;#status"
				customColumnValue="10,showstatus(status)"
				isPage="true"
				isChecked="false"				
				params="payAcademyCeduId:$('#payAcademyCeduId').val()"
				isonLoad="true"
				isPackage="true"
				
			/>
			
		 <table class="gv_table_2">
		 	<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">其他应收学校款</th>
				<th></th>
			</tr>
		</table>
			<page:plugin
				pluginCode="002"
				il8nName="finance"
				subStringLength="50"
				searchListActionpath="/finance/academybill/list_academy_bill_data"
				columnsStr="academyName;receivedWayName;#billamount;note;#status;#attachment"
				customColumnValue="2,billAmount(amount);4,toBillStatus(isRebate);5,buildAttachment(id)"
				params="academyId:$('#remitteeId').val(),'academyRebateId':'${param.payAcademyCeduId}',isRebate:1"
				isPage="false"
				isonLoad="true"
				isPackage="false"
				isOrder="false"
			/>
		 <table class="add_table">
			<tbody>
				<tr>
					<td colspan="2" align="center"><input type="button" class="btn_black_61" value="关闭" onclick="self.close()"/></td>
				</tr>
		  	</tbody>
		 </table>
	</body:body>
</body>
</html>
