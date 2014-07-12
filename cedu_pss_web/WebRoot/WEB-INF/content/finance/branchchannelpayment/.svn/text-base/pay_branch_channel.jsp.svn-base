<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>招生返款 添加返款单</title>
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
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	<!-- 分页插件 -->
	<jc:plugin name="page" />
<script type="text/javascript">
function clearBatch()
{
	$('#batchId').empty().append('<option value="0" selected="selected">---请选择---</option>');
	clearLevel();
}

function batchCallback(data){
	$('#batchId').empty();
	$(data.batches).each(function(index){
		var batchOpt = $('<option value="'+this.id+'">'+(this.enrollmentName ? this.enrollmentName : '')+'</option>');
		batchOpt.appendTo('#batchId');
	});
	$('#batchId').prepend('<option value="0" selected="selected">---请选择---</option>');
}
BATCH_PARAM = null;
function listBatch(academyId)
{
	clearBatch();
	
	if(academyId <= 0) return;
	
	$('#batchId').html('<option value="0" selected="selected">---请选择---</option>');
	BATCH_PARAM = { academyId: academyId };
	ajax_batch_1();
}

function clearLevel()
{
	$('#levelId').html('<option value="0" selected="selected">---请选择---</option>');
	$('#majorId').html('<option value="0" selected="selected">---请选择---</option>');
}

function levelCallback(data){
	$('#levelId').empty();
	
	if(!data || !data.list || data.list.length<=0){
		$('#levelId').append('<option value="0" selected="selected">----请选择----</option>');
		return;
	}
	
	$('#levelId').append('<option value="0" selected="selected">----请选择----</option>');
	
	$(data.list).each(function(index){
		$('<option  value="'+this.level.id+'" al="'+this.id+'">'+(this.level ? this.level.name : '')+'</option>').appendTo('#levelId');
	});
}

LEVEL_PARAM = null;
function listLevel(batchId)
{
	clearLevel();
	
	if(batchId==0) return;
	LEVEL_PARAM = {batchId: batchId};
	
	$('#levelId').html('<option value="0" selected="selected">----请选择----</option>');
	ajax_level_1();
}

function majorCallback(data){
	$('#majorId').empty();
	
	if(!data || !data.list || data.list.length<=0)
	{
		$('#majorId').append('<option value="0" selected="selected">----请选择----</option>');
		return;
	}
	
	$('#majorId').append('<option value="0" selected="selected">----请选择----</option>');
	
	$(data.list).each(function(index){
		$('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>');
	});
}

MAJOR_PARAM = null;
function listMajor(levelId)
{
	if(!levelId)
	{
		$('#majorId').html('<option value="0" selected="selected">----请选择----</option>');
		return;
	}
	MAJOR_PARAM = {academyLevelId: levelId};
	$('#majorId').html('<option value="0" selected="selected">----请选择----</option>');
	ajax_major_1();
}

CHANNEL_PARMA = null;
function channelCallback(data){
	$('#channelId').append('<option value="-1">---请选择---</option>');
	$(data.list).each(function(){
		$('#channelId').append('<option value="'+this.id+'">'+this.name+'</option>');
	});
}

function clearChannel(){
	$('#channelId').empty();
}
function listChannel(channelType){
	CHANNEL_PARAM = {channelType: channelType,branchId:jQuery("#branchId").val()};
	clearChannel();
	
	if(channelType==-1) {
		$('#channelId').append('<option value="-1">---请选择---</option>');
		return;
	}
	
	ajax_channel_1();
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

function toStatus(status){
	if(status==PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO){
		return '已打款院校';
	}
	
	return '';
}

function _sum_up(ele,checkbox,index, isctl){
	var amount = 0, amountPaied=0;
	$(':checkbox[name=checkbox'+checkbox+']').each(function(){
		if(isctl){
			this.checked = ele.checked;
		}
		
		if(this.checked){
			var money = $(this).parent().parent().find('td:nth('+index+')').find('div').text();
			if(!isNaN(money)){
				money = parseFloat(money);
			} else {
				money = 0;
			}
			
			amount += money;
			
			var money2 = $(this).parent().parent().find('td:nth('+(index-1)+')').find('div').text();
			if(!isNaN(money2)){
				money2 = parseFloat(money2);
			} else {
				money2 = 0;
			}
			
			amountPaied += money2;
		}
	});
	
	$('#sum').text(amount);
	$('#amountPaied').text(amountPaied);
	$('#adjAmount').val(0);
	$('#realAmount').val(amount);
	$('#count').text($(':checkbox[name=checkbox'+checkbox+']:checked').size());
}
function processCheckbox001(){
	processCheckbox('001', 10);
}

function processCheckbox(checkbox,index){
	var ele = $('#checkbox'+checkbox).get(0);
	ele.onclick = function(){ _sum_up(ele,checkbox,index, true); };
	
	$(':checkbox[name=checkbox'+checkbox+']').change(function(){
		_sum_up(ele,checkbox,index, false);
	});
}

function adj_amount_on_focus(_this){
	if(!_this.value || _this.value=='0'){
		_this.value = '';
	}
}

function adj_amount_on_blur(_this){
	if(!/^-?([1-9]\d*|0)$/.test(_this.value)){
		_this.value=0;
	}
	
	var amountPaied = $('#sum').text();
	amountPaied -= parseFloat(_this.value);
	
	$('#realAmount').val(amountPaied);
}

ADD_DATA = null;
function doAdd()
{
	var data = {};
	
	data['condition.channelId']=$('#channelId').val();
	data['condition.academyId'] = $('#academyId').val();
	data['condition.levelId'] = $('#levelId').val();
	data['condition.majorId'] = $('#majorId').val();
	data['condition.batchId'] = $('#batchId').val();
	
	if($('#majorId').val()=='-1'){
		$.alert('您必须选择专业后才能进行反款！');
		return;
	}
	
	var fpdIds = [];
	$(':checkbox[name=checkbox001]').each(function(){
		if(this.checked){
			fpdIds.push(this.value);
		}
	});
	
	if(fpdIds.length == 0){
		$.alert('您没有选择缴费单，返款时需要确定哪些缴费单需要返款！');
		return;
	}
	
	data['fpdIds'] = fpdIds;
	
	data['order.channelType'] = $('#channelTypeIds').val();
	data['order.channelId'] = $('#channelId').val();
	data['adjustedAmount'] = $('#adjAmount').val();
	
	ADD_DATA = data;
	ajax_add_1();
}
function addCb(data)
{
	if(!data.errno){
		$.alert('添加失败！');
	}
	$.alert({msg:'添加成功！', close:function(){ location.href='<uu:url url="/finance/branchchannelpayment/list_branch_channel_payment"/>'; }});
}
	
$(document).ready(function(){
	$('#batchId').change(function(){
		var batch = this.value;
		listLevel(batch);
	});
	
	$('#levelId').change(function(){
		var levelId = this.options[this.selectedIndex].getAttribute('al');
		listMajor(levelId);
	});
	$('#academyId').change(function(){
		listBatch(this.value);
		//search001();
	});
	
	$('#majorId').change(function(){
		if(this.value== '-1'){
			$('#table001 > tbody').empty();
		} else {
			search001();
		}
	});
	
	$('#channelType').change(function(){
		listChannel(this.value);
		//ajax_channel_1();
	});
	
	$('#channelId').change(function(){
		if(this.value != -1 && this.value){
			$('#contentBody').show();
		} else {
			$('#contentBody').hide();
		}
	});
	
	document.getElementById('form').reset();
});
</script>
<a:ajax successCallbackFunctions="levelCallback" parameters="LEVEL_PARAM" pluginCode="level"
	urls="/enrollment/academylevel/list_academy_level_for_batch"/>
<a:ajax successCallbackFunctions="majorCallback" parameters="MAJOR_PARAM" pluginCode="major"
	urls="/enrollment/academymajor/list_academy_major_for_academy_level"/>
<a:ajax successCallbackFunctions="batchCallback" parameters="BATCH_PARAM" pluginCode="batch"
	urls="/enrollment/list_enroll_batch_for_academy"/>

<a:ajax successCallbackFunctions="channelCallback" parameters="CHANNEL_PARAM" pluginCode="channel" urls="/finance/branchchannelpayment/find_branch_channel_list_ajax"/>
<a:ajax successCallbackFunctions="addCb" traditional="true" parameters="ADD_DATA" pluginCode="add" urls="/finance/branchchannelpayment/pay_branch_channel"/>
</head>
<body>

	<head:head title="招生返款">
		<html:a text="返回" icon="return" onclick="history.go(-1)"/>
	</head:head>
	<body:body>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">招生返款 &gt;&gt; 添加招生返款单</th>
					<th></th>
				</tr>
			</table>
<form id="form">
		<table class="add_table">
			<tr>
				<td class="lable_100">学习中心：</td>
				<td>
					${branch.name}
					<input type="hidden" name="branchId" id="branchId" value="${branch.id}"/>
				</td>
			</tr>
			<tr>
				<td class="lable_100">合作方类型：</td>
				<td><s:select cssClass="txt_box_130" id="channelType" name="channelType"
					list="channelTypes" listKey="id" listValue="name"
					headerKey="-1" headerValue="---请选择---"
				></s:select>
				</td>
			</tr>
			<tr>
				<td class="lable_100">合作方：</td>
				<td><select id="channelId" name="channelId" class="txt_box_130"></select></td>
			</tr>
			<tbody id="contentBody" style="display:none;">
				<tr>
				  <td class="lable_100">缴费单明细：</td>
				  <td>
					<table class="add_table">
						<tr>
							<td width="17%" align="right">院校：</td>
							<td align="left" width="17%">
								<s:select id="academyId" name="academyId"
									list="academies" listKey="id" listValue="name"
									headerKey="-1" headerValue="---全部---"
									cssClass="txt_box_130"
								></s:select>
							</td>
							<td align="right">批次：</td>
							<td align="left">
								<select name="batchId" id="batchId" class="txt_box_130"><option value="-1">---请选择---</option></select></td>
							<td align="right">层次：</td>
							<td><select name="levelId" id="levelId" class="txt_box_130"><option value="-1">---请选择---</option></select></td>
						</tr>
						<tr>
							<td align="right">专业：</td>
							<td align="left">
								<select name="majorId" id="majorId" class="txt_box_130"><option value="-1">---请选择---</option></select>
							</td>
							<td align="right">姓名：</td>
							<td align="left"><input type="text" name="student.name" id="studentName" class="txt_box_130"/></td>
							<td align="right">证件号：</td>
							<td align="left"><input type="text" id="studentIDNo" class="txt_box_130"/></td>
						</tr>
						<tr>
							<td colspan="6" align="right">
								<input type="button" class="btn_black_61" value="查询" onclick="search001()"/>
							</td>
						</tr>
					</table>
					<page:plugin 
						pluginCode="001"
						il8nName="finance"
						subStringLength="50"
						searchListActionpath="json_list_fee_payment_detail_for_pbc"
						columnsStr="code;schoolName;branchName;academyenrollbatchName;levelName;majorName;studentName;feeSubjectName;amountPaied;moneyToChannel;status"
						customColumnValue=""
						isPage="false"
						isChecked="true"
						checkboxValue="id"
						params="academyId:$('#academyId').val(),batchId:$('#batchId').val(),levelId:$('#levelId').val(),majorId:$('#majorId').val(),name:$('#studentName').val(),certNo:$('#studentIDNo').val()"
						listCallback="processCheckbox"
						isonLoad="false"
						isPackage="true"
						isOrder="false"
					/>
				  </td>
				</tr>
				<tr>
					<td class="lable_100">返款总人数：</td>
					<td><span id="count">0</span></td>
				</tr>
				<tr>
					<td class="lable_100">缴款金额：</td>
					<td><span id="amountPaied">0</span></td>
				</tr>
				<tr>
					<td class="lable_100">返款总额：</td>
					<td><span style="color:red;" id="sum">0</span></td>
				</tr>
				<tr>
					<td class="lable_100">调整金额：</td>
					<td><span style="color:red;"><input type="text" id="adjAmount" name="adjAmount" onfocus="adj_amount_on_focus(this)" onblur="adj_amount_on_blur(this)" class="txt_box_200" value="0"/></span></td>
				</tr>
				<tr>
					<td class="lable_100">实返款总额：</td>
					<td><input type="text" class="txt_box_200" id="realAmount" name="realAmount" readonly="readonly" value="0" /></td>
				</tr>
				<tr>
					<td class="lable_100">批件号：</td>
					<td><span style="color:red;"><input type="text" class="txt_box_200" /></span></td>
				</tr>
				<tr>
					<td class="lable_100">调整原因：</td>
					<td><textarea cols="60" rows="6"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" class="btn_black_61" value="保存" onclick="doAdd()"/>&nbsp;&nbsp;<input type="button" class="btn_black_61" value="取消"/></td>
				</tr>
			</tbody>
		</table>
</form>
	</body:body>
</body>
</html>
