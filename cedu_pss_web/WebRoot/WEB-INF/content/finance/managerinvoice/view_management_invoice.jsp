<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>

		<title>发票详细</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!--  Ajax操作等待 -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<script type="text/javascript">
	function feecodeValue(paymentCode) {
		return paymentCode;
	}

	function statusValue(status) {
		//return status==STATUS_AUTHOR_FALSE?"未缴费":"已缴费";
		return status.getPaymentStatus();
	}

	function amountPaiedValue(amountPaied) {
		return (amountPaied+"").toMoney();
	}
	function feePaymentDetailCodeValue(code) {
		return code;
	}
	//回调函数
	function list_callback123(data) {
		var money=parseFloat(0);
		if(data.result.list!=null){
			$(data.result.list).each(function(){
				money+=parseFloat(this.amountPaied);
			});
		}
		$("#money").html((money+"").toMoney());
	}
</script>




	</head>

	<body>

		<form id="form"
			action="<uu:url url="/finance/managerinvoice/add_postal_parcel_cedu" />"
			method="post" enctype="multipart/form-data">
			<input type="hidden" id="studentId" name="studentId" />
			<!--头部层开始 -->
			<head:head title="发票详情">
			</head:head>
			<!--主体层开始 -->
			<body:body>

				<table class="add_table">
					<tr>

						<td width="26%" align="right">
							发票号：
						</td>
						<td align="left">
							${invoicemanagement.invoiceCode}
						</td>


						<td width="26%" align="right">
							开票日期：
						</td>
						<td align="left">
							<s:date name="%{invoicemanagement.issuedTime}"
								format="yyyy-MM-dd" />
						</td>
					</tr>
					<tr>
						<td width="26%" align="right">
							学习中心：
						</td>
						<td align="left">
							${invoicemanagement.branchName}
						</td>

						<td width="26%" align="right">
							学生姓名：
						</td>
						<td align="left">
							${invoicemanagement.studentName}
						</td>
					</tr>
					<tr>
						<td width="26%" align="right">
							发票总金额：
						</td>
						<td align="left">
							<font id="money" color="red"></font>
						</td>

						<td width="26%" align="right"></td>
						<td align="left">
						</td>
					</tr>



				</table>


				<table class="gv_table_2">
					<tr>
						<th style="width: 20px;">
							<img src="<ui:img url="images/title_menu/title_left.gif"/>" />
						</th>
						<th style="text-align: left; font-weight: bold;">
							缴费单列表
						</th>
					</tr>
				</table>
				<input type="hidden" id="feePaymentId" name="feePaymentId"
					value="${invoicemanagement.feePaymentDetailId}" />



				<page:plugin pluginCode="123" il8nName="finance"
					searchListActionpath="/finance/feepayment/listfeepaymentids"
					columnsStr="#feecode;feePaymentDetailCode;#feestatus;paymentSubjectName;#feeamountPaied"
					customColumnValue="0,feecodeValue(paymentCode);1,feePaymentDetailCodeValue(code);2,statusValue(status);4,amountPaiedValue(amountPaied)"
					isPage="false" isPackage="false" isOrder="false"
					ontherOperatingWidth="80px" listCallback="list_callback"
					params="'feePaymentIds':$('#feePaymentId').val()" />



			</body:body>
		</form>
	</body>
</html>
