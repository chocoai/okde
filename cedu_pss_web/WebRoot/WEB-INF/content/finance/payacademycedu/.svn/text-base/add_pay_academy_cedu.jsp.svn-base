<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>院校返款 添加返款单</title>
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
	function noop(v){
		if(typeof(v) != 'undefined')
			return v;
			
		return '';
	}
	function buildAttachment(id){
		return '<a target="_blank" href="<uu:url url="/finance/academybill/download_academy_bill_attachment"/>?billId='+id+'">下载附件</a>';
	}
	
	function toBillStatus(isRebate){
		if(isRebate == TRUE){
			return '已返款';
		}
		
		return '未返款';
	}
	
	function toStatus(status){
		if(status==PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO){
			return '已打款院校';
		}
		
		return '';
	}
	
	function billAmount(amount){
		if(typeof(amount) != 'undefined'){
			return '<div>'+amount+'</div>';
		}
		
		return '[无]';
	}
	
	PAY_CEDU_ACADEMY_ID = -1;
	function showBillDetail(billId){
		PAY_CEDU_ACADEMY_ID = billId;
		$('#fpdList').show();
		search003();
	}
	
	function buildRemittanceNo(id, no, money){
		var click = '';
		//if(money){
			click = 'onclick="showBillDetail('+id+')"';
		//} else {
		//	$('#fpdList').hide();
		//}
		var link = '<a href="javascript:void(0);" '+click+' >'+no+'</a>';

		return link;
	}
	
	function buildRebateWay(id, way, value){
		var unit = '';
		if(way==<s:property value="@net.cedu.common.Constants@MONEY_FORM_RATIO"/>){
			unit= '%';
		} else if(way==<s:property value="@net.cedu.common.Constants@MONEY_FORM_AMOUNT"/>){
			unit = '元';
		}
		
		return value + unit;
	}
	
	function processCheckbox001(){
		processCheckbox('checkbox001',6);
	}
	function processCheckbox002(){
		processCheckbox('checkbox002',3);
	}
	
	function processCheckbox(checkbox,index){
		$(':checkbox[name='+checkbox+']').change(function(){
			var paysum = $('#paysum').text();
			paysum = parseFloat(paysum);
			
			var money = $(this).parent().parent().find('td:nth('+index+')').find('div').text();
			if(!isNaN(money)){
				money = parseFloat(money);
			} else {
				money = 0;
			}
				
			if(this.checked){
				paysum += money;
			} else {
				paysum -= money;
			}
			
			$('#paysum').text(paysum);
		});
	}
	
	function loadPayCeduAcademy(academyId){
		alert(academyId);
		return;
	}
	
	function getDataToSave(){
		var data = {};
		data.academyId = $('#remitteeId').val();
		
		var fpdIds = [];
		
		$(':checkbox[name=checkbox001]:checked').each(function(){
			fpdIds.push(this.value);
		});
		
		data.paymentDetailIds = fpdIds;
		
		var otherPaymentIds = [];
		
		$(':checkbox[name=checkbox002]:checked').each(function(){
			otherPaymentIds.push(this.value);
		});
		
		data.otherPaymentIds = otherPaymentIds;
		
		data.note = $('#note').val();
		
		filterBlankOfJSON(data);
		
		return data;
	}
	
	function saveCallback(data){
		$.alert({
			msg:"保存成功！",
			close: function(){
				window.location.href="<uu:url url="/finance/payacademycedu/view_pay_academy_cedu"/>?payAcademyCeduId="+data.payAcademyCeduId;
			}
		});
	}
	
	function doSave(){
		var msg = [];
		var data = getDataToSave();
		if(!data.academyId)
			msg.push('请选择返款院校');
			
		if(!(data.fpdIds && data.fpdIds.length || data.otherPaymentIds && data.otherPaymentIds.length))
			msg.push('请选择需要返款的缴费单明细或其他应返院校款');
			
		if(msg.length > 0) {
			$.alert(msg.join('<br/>'));
			
			return;
		}
		
		ajax_save_1();
	}
	
	jQuery(function(){
		$('#remitteeId').change(function(){
			if(this.value != '-1'){
				search001();
				search002();
				$('#rebateContent').show();
				
				$('#table003 > tbody').empty();
				$('#fpdList').hide();
			}
			else{
				$('#rebateContent').hide();
				//alert('CLEAR');
			}
		});
		
		$('#checkbox001').change(function(){
			$(':checkbox[name=checkbox001]').change();
		});
		
		$('#checkbox002').change(function(){
			$(':checkbox[name=checkbox002]').change();
		});
		
		$('#form').get(0).reset();
	});
	</script>
	
	<a:ajax parameters="getDataToSave()" traditional="true" successCallbackFunctions="saveCallback" pluginCode="save" urls="/finance/payacademycedu/add_pay_academy_cedu"/>
</head>
<body>
	<head:head title="院校返款">
		<html:a text="返回" icon="return" href="/finance/payacademycedu/list_pay_academy_cedu"/>
	</head:head>
	<body:body>

<form id="form" action="#" method="post">
	<table class="gv_table_2">
		<tr>
			<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
			<th style="text-align:left; font-weight:bold;">院校返款 &gt;&gt; 添加返款单</th>
			<th></th>
		</tr>
	</table>
			
	<table class="add_table">
	  <tbody id="rebateCondition">
		<tr>
			<td class="lable_100">收款单位：</td>
			<td><s:property value="cedu.name"/></td>
		</tr>
		<tr>
			<td class="lable_100">返款院校：</td>
			<td>
				<s:select list="academies" listKey="id" listValue="name"
				headerKey="-1" headerValue="--- 请选择 ---"
				id="remitteeId" name="remitteeId"></s:select>
			</td>
		</tr>
	  </tbody>
	  <tbody id="rebateContent" style="display:none;">
		<tr>
		  <td class="lable_100" style="vertical-align:top">待返款打款单：</td>
		  <td>
			<page:plugin 
				pluginCode="001"
				il8nName="finance"
				subStringLength="50"
				searchListActionpath="list_unrebated_pay_cedu_academy"
				columnsStr="remitterName;remitteeName;remittanceDate;#remittanceno;amount;moneyToCedu;#status"
				customColumnValue="3,buildRemittanceNo(id,remittanceNo,moneyToCedu);6,toStatus(status)"
				listCallback="processCheckbox"
				isPage="false"
				isChecked="true"
				checkboxValue="id"
				params="'remitteeId':$('#remitteeId').val(),searchCase:'AddPayAcademyCedu'"
				isonLoad="false"
				isPackage="true"
				isOrder="false"
			/>
			<div id="fpdList" style="display:none">
				<page:plugin 
					pluginCode="003"
					il8nName="finance"
					subStringLength="50"
					searchListActionpath="list_unrebated_fee_payment_detail"
					columnsStr="code;studentName;academyenrollbatchName;#payceduacademy1;#rebateway;moneyToCedu"
					customColumnValue="3,noop(payCeduAcademy);4,buildRebateWay(id,rebateWay,rebateValue)"
					isPage="false"
					params="'payCeduAcademyId':PAY_CEDU_ACADEMY_ID"
					isonLoad="false"
					isPackage="true"
					isOrder="false"
				/>
			</div>
		  </td>
		</tr>
		<tr>
		  <td class="lable_100" style="vertical-align:top">其他应收学校款：</td>
		  <td>
			<page:plugin
				pluginCode="002"
				il8nName="finance"
				subStringLength="50"
				searchListActionpath="/finance/academybill/list_academy_bill_data"
				columnsStr="academyName;receivedWayName;#billamount;note;#status;#attachment"
				customColumnValue="2,billAmount(amount);4,toBillStatus(isRebate);5,buildAttachment(id)"
				params="academyId:$('#remitteeId').val(),status:PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO"
				listCallback="processCheckbox"
				isChecked="true"
				isPage="false"
				isonLoad="false"
				isPackage="false"
				isOrder="false"
			/>
		  </td>
		</tr>
		<tr>
			<td class="lable_100">应返款额度：</td>
			<td><span id="paysum">0</span></td>
		</tr>
		<tr>
			<td class="lable_100">备注：</td>
			<td><textarea cols="60" rows="6" id="note"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button" class="btn_black_61" value="保存" onclick="doSave()"/>&nbsp;&nbsp;<input type="button" class="btn_black_61" value="取消"/></td>
		</tr>
	  </tbody>
	</table>
</form>
	</body:body>
</body>
</html>
