<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>返款费用科目</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!--  分页 -->
	<jc:plugin name="page" />
	<!-- 选项卡 -->
	<jc:plugin name="tab" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	
<script type="text/javascript">
var DataTable ={};
DataTable[BATCH_TYPE_ENROLL] = '#EnrollTable';
DataTable[BATCH_TYPE_NON_ENROLL] = '#NonEnrollTable';

var SaveBtn = ['saveNonEnroll', 'saveEnroll'];
</script>
	
<script type="text/javascript">

function saveFeeSubjects(batch, flag)
{
	var dialogId = (flag == BATCH_TYPE_ENROLL ? '#fsEnrollDialog' : '#fsNonEnrollDialog');
	var param = {academyId: '${param.academyId}', batchId: batch, stage: flag };
	var fsIds = [];
	$(dialogId + ' :checkbox[name="feeSubject"]').each(function(){
		if(this.checked)
			fsIds.push(this.value);
	});
	
	if(fsIds.length != 0){
		param.feeSubjectIds = fsIds;
	}

	$.ajax({
		url: '<s:url value="/enrollment/save_academy_batch_fee_subject"/>',
		type: 'POST',
		traditional: true,
		data: param,
		dataType: 'json',
		success: function(data){
			$.alert(data.errMsg);
			$(dialogId).dialog('close');
			listFeeSubject(flag);
		},
		error: function(){
			$.alert('访问服务器时发生错误!');
		}
	});
}

function parseFeeSubject(e, flag)
{
	var dialogId = (flag == BATCH_TYPE_ENROLL ? '#fsEnrollDialog' : '#fsNonEnrollDialog');
	var $row = $('<tr/>').attr('id', 'e_batch_'+e.batch.id);
	
	var _name_p_ =  (flag == BATCH_TYPE_ENROLL ? 'enrollmentName' : 'registerName');
	$('<td/>').append(e.batch[_name_p_]).appendTo($row);
	
	var fslist = [];
	$(e.fslist).each(function(){
		fslist.push(this.name);
	});
	
	$('<td/>').append(fslist.join('，')).appendTo($row);
	
	var handler = $('<a/>').append('设置');
	handler.attr('href', 'javascript:void(0);');
	handler.click(function(){
		var batchId = e.batch.id;
		$(dialogId).dialog('open');
		document.getElementById(SaveBtn[flag]).onclick = function(){
			saveFeeSubjects(batchId, flag);
		};
	});
	
	$('<td/>').append(handler).appendTo($row);
	
	$row.css('text-align', 'center');
	
	return $row;
}

function listFeeSubject(flag){
	var tabId = DataTable[flag];
	$(tabId + ' > tbody').html('<tr><td colspan="3" align="center"><img src="<s:url value="/images/listloading.gif"/>"/></td></tr>');
	
	$.ajax({
		url: '<s:url value="/enrollment/list_academy_batch_fee_subject"/>',
		type: 'POST',
		async: true,
		data: {academyId: '${param.academyId}', stage: flag},
		dataType: 'json',
		success: function(data){
			$(tabId + ' > tbody').empty();
			
			$(data.list).each(function(){
				var row = parseFeeSubject(this, flag);
				$(tabId + ' > tbody').append(row);	
			});
			
			if($(tabId + ' > tbody tr').size()==0){
				$(tabId + ' > tbody').append('<tr><td colspan="3" align="center">无数据！</td></tr>');
			}
		},
		error: function(xhr){
			var c = xhr.responseText;
			
			var a = arguments;
			var msg = '加载费用项目(非招生)失败！';
			if(flag==BATCH_TYPE_ENROLL){
				msg = '加载费用项目(招生)失败！';
			}
			
			$.alert(msg);
		}
	});
}

$(function(){
	$('#fsNonEnrollDialog').dialog({
		autoOpen: false,
		width: 370,
		modal: true,
		title: '费用科目(非招生阶段)',
		shadow: true,
		beforeClose: function(){
			$(':checkbox', this).attr('checked', false);
		}
	});
	
	$('#fsEnrollDialog').dialog({
		autoOpen: false,
		width: 370,
		modal: true,
		title: '费用科目(招生阶段)',
		shadow: true,
		beforeClose: function(){
			$(':checkbox', this).attr('checked', false);
		}
	});
	
	listFeeSubject(BATCH_TYPE_ENROLL);
	listFeeSubject(BATCH_TYPE_NON_ENROLL);
});
</script>

</head>  
<body>
<!-- 头开始 -->
		<head:head title="招生计划">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<!--Menu Begin-->
			<%@ include file="_tab/academy_enroll_tab.jsp" %>
			<!--Menu End-->
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
					<th style="text-align:left; font-weight:bold;">院校名称：${request.academy.name}</th>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td style="vertical-align: top; width: 50%">
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
								<th style="text-align:left; font-weight:bold;">招生阶段</th>
							</tr>
						</table>
						<table class="gv_table_2"  id="EnrollTable">
						  <thead>
							<tr>
								<th width="20%">招生批次</th>
								<th>费用科目</th>
								<th width="20%">设置</th>
							</tr>
						  </thead>
						  <tbody></tbody>
						</table>
					</td>
					<td width="10"></td>
					<td style="vertical-align: top;">
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
								<th style="text-align:left; font-weight:bold;">非招生阶段</th>
							</tr>
						</table>
						<table class="gv_table_2" id="NonEnrollTable">
						  <thead>
							<tr>
								<th width="20%">招生批次</th>
								<th>费用科目</th>
								<th width="20%">设置</th>
							</tr>
						  </thead>
						  <tbody></tbody>
						</table>
					</td>
				</tr>
			</table>
  		</body:body>
<div style="clear:both; float: none;"></div>

<div id="fsEnrollDialog" style="display:none;">
	<table class="gv_table_2">
		<tr>
			<th >编号</th>
			<th >费用科目</th>
			<th >是否院校返款</th>
		</tr>
	<s:iterator value="allEnrollFS" var="feeSubject">
		<tr fsid="<s:property value="id"/>">
			<td align="center"><s:property value="code"/></td>
			<td align="center"><s:property value="name"/></td>
			<td align="center">
				<input type="checkbox" name="feeSubject" value="<s:property value="id"/>" />
			</td>
		</tr>
	</s:iterator>
	</table>
	<div style="text-align:center">
		<input type="button" value="保存" class="btn_black_61" id="saveEnroll"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="取消" class="btn_black_61" onclick="$('#fsEnrollDialog').dialog('close')"/>
	</div>
</div>

<div style="clear:both; float: none;"></div>

<table ><tr><td>&nbsp;</td></tr></table>

<div style="display:none;" id="fsNonEnrollDialog">
	<table class="gv_table_2">
		<tr>
			<th >编号</th>
			<th >费用科目</th>
			<th >是否院校返款</th>
		</tr>
	<s:iterator value="allRegisterFS" var="feeSubject">
		<tr fsid="<s:property value="id"/>">
			<td align="center"><s:property value="code"/></td>
			<td align="center"><s:property value="name"/></td>
			<td align="center">
				<input type="checkbox" name="feeSubject" value="<s:property value="id"/>" />
			</td>
		</tr>
	</s:iterator>
	</table>
	<div style="text-align:center">
		<input type="button" value="保存" class="btn_black_61" id="saveNonEnroll"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="取消" class="btn_black_61" onclick="$('#fsNonEnrollDialog').dialog('close')"/>
	</div>
</div>

  </body>
</html>
