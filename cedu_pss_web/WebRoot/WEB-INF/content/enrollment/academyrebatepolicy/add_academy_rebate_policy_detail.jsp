<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>批量设置院校返款政策</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- Tab 分页样式 -->
	<jc:plugin name="tab" />
	<!--  分页 -->
	<jc:plugin name="page" />
	<jc:plugin name="loading" />
<script type="text/javascript">
function checkAll(ele, id)
{
	$('#'+id+' :checkbox').attr('checked', !!ele.checked);
}

function toggleConflictId(){
	var checked = !!this.checked;
	$('#conflictDialog :checkbox[name="policyDetailId"]').each(function(){
		if(!this.disabled)
			this.checked = checked;
	});
}

function parseConflictList(list){
	$(list).each(function(){
		var row = $('<tr/>');
		
		var chkbox = $('<input type="checkbox" name="policyDetailId" value="'+this.id+'"/>');
		
		if(this.auditStatus==POLICY_AUDIT_STATUS_GOOD){
			$(chkbox).attr('disabled', 'disabled');
		}
		
		row.append($('<td/>').append(chkbox));
		
		row.append('<td>'+(this.academyName ? this.academyName : '')+'</td>');
		
		row.append('<td>'+(this.branchName ? this.branchName : '')+'</td>');
		row.append('<td>'+(this.batchName ? this.batchName : '')+'</td>');
		row.append('<td>'+(this.levelName ? this.levelName :'')+'</td>');
		row.append('<td>'+(this.majorName ? this.majorName : '')+'</td>');
		
		var title = '';
		if(this.policy && this.policy.title)
			title = this.policy.title;
		row.append('<td>'+title+'</td>');
		
		var auditTd = $('<td/>').append(this.auditStatus==POLICY_AUDIT_STATUS_DEFAULT ? '未审批' : this.auditStatus==POLICY_AUDIT_STATUS_BAD ? '未通过' : this.auditStatus==POLICY_AUDIT_STATUS_GOOD ? '已通过' : '');
		row.append(auditTd);
		
		row.css('text-align', 'center');
		
		$('#conflictDialog table.gv_table_2 > tbody').append(row);
	});
}

SAVE_PARAM = {};
function saveListCallback(data)
{
	if(data.errno==0)
	{
		$.alert('添加成功');
	}
	else if(data.conflictList && data.conflictList.length>0){
		$('#conflictDialog table.gv_table_2 > tbody').empty();
		$('#conflictDialog').dialog('open');
		parseConflictList(data.conflictList);
	}
}
function prepareSavingList()
{
	var flag, errMsg=[];
	
	if($('#academyId').val()=='0'){
		errMsg.push('请选择院校！');
	}
	
	var branchIds = [], branchIdBoxes;
	if($(':checkbox[name="branchId"]:checked').size()>0){
		branchIdBoxes = $(':checkbox[name="branchId"]:checked');
	} else {
		branchIdBoxes = $(':checkbox[name="branchId"]');
		$(':checkbox[name="branchId"]').attr('checked', true);
	}
	branchIdBoxes.each(function(){
		branchIds.push(this.value);
	});
	
	if(branchIds.length==0) {
		if($(':radio[name=batchId]:checked').size()==1)
			errMsg.push('请在添加政策之前必须先授权学习中心！');
		else
			errMsg.push('请选择招生批次！');
	}
	
	if($(':radio[name="policyId"]:checked').size()==0) {
		errMsg.push('请选择返款标准！');
	}
	
	if(errMsg.length>0){
		$('#validDialog').empty().append('<ul class="validateErrorList"></ul>');
		for(var _i=0; _i<errMsg.length; _i++){
			$('#validDialog > ul').append('<li>'+errMsg[_i]+'</li>');
		}
		
		$('#validDialog').dialog('open');
		return;
	}
	
	var majorIds=[], majorIdBoxes;
	if($(':checkbox[name="majorId"]:checked').size()>0) {
		majorIdBoxes = $(':checkbox[name="majorId"]:checked');
	} else {
		majorIdBoxes = $(':checkbox[name="majorId"]');
		$(':checkbox[name="majorId"]').attr('checked', true);
	}
	majorIdBoxes.each(function(){
		majorIds.push(this.value);
	});
	
	SAVE_PARAM = {
		flag: flag,
		academyId: $('#academyId').val(),
		majorId: majorIds,
		branchId: branchIds,
		policyId: $(':radio[name=policyId]:checked').val(),
		batchId: $(':radio[name=batchId]:checked').val(),
		levelId: $(':radio[name=levelId]:checked').val(),
		feeSubjectId: $('#feeSubjectId').val()
	};
	
	filterBlankOfJSON(SAVE_PARAM);
	
	ajax_save_1();
}

UPDATE_PARAM ={};
function updateListCallback(data)
{
	$('#conflictDialog').dialog('close');
	$('#validDialog').html('操作成功！');
	$('#validDialog').dialog('option', 'title', '操作提示');
	$('#validDialog').dialog('open');
}
function prepareUpdatingList()
{
	var detailIds = [];
	$('#conflictDialog :checkbox[name="policyDetailId"]:checked').each(function(){
		detailIds.push(this.value);
	});
	
	if(detailIds.length==0){
		$('#validDialog').html('请选中要覆盖的政策！');
		$('#validDialog').dialog('open');
		return;
	}
	
	UPDATE_PARAM = {
		policyId: $(':radio[name=policyId]:checked').val(),
		detailIds: detailIds
	};
	
	filterBlankOfJSON(UPDATE_PARAM);
	
	$.confirm({msg: '您确认覆盖吗？', confirm: function(){
		ajax_update_1();
	}});
}
</script>
<script type="text/javascript">
function clearBatch()
{
	$('#batch > tbody').html('<tr><td align="center">请选择院校</td></tr>');
	
	clearLevel();
	clearBranch();
	clearFS();
	
	$('#branchTrigger').attr('checked', false);
}

function clearLevel()
{
	$('#level > tbody').html('<tr><td align="center">请选择批次</td></tr>');
	clearMajor();
	$('#majorTrigger').attr('checked', false);
}

function clearMajor()
{
	$('#major > tbody').html('<tr><td align="center">请选择层次</td></tr>');
}

function clearBranch()
{
	$('#branch > tbody').html('<tr><td align="center">请选择批次</td></tr>');
}

function clearPolicy()
{
	$('#policyList > tbody').html('<tr><td colspan="5" align="center">请重新查询</td></tr>');
}

function clearFS(){
	$('#feeSubjectId').html('<option value="-1">--请选择--</option>');
	$('#table001 > tbody').html('<tr><td colspan="5" align="center">请单击[查询]按钮以查询数据！</td></tr>');
}

function listBatch(academyId)
{
	clearBatch();
	if(academyId <= 0) return;
	
	$('#batch > tbody').html('<tr><td align="center"><img src="<ui:img url="/images/listloading.gif"/>" align="absmiddle"/>请稍等....</td></tr>');
	$.ajax({
		url: '<uu:url url="/enrollment/list_enroll_batch_for_academy"/>',
		async: true,
		type: 'POST',
		dataType: 'json',
		data: {
			academyId:academyId
		},
		success: function(data){
			$('#batch > tbody').empty();
			var tr = $('<tr/>');
			$(data.batches).each(function(index){
				var levelRadio = $('<input type="radio" name="batchId" value="'+this.id+'"/>');
				levelRadio.change(function(){
					listGrantedBranch(this.value);
		
					listLevel(this.value);
					listFeeSubject(academyId, this.value);
				});
				$('<td width="25%">'+(this.enrollmentName)+'</td>').prepend(levelRadio).appendTo(tr);

				if(index==data.batches.length-1){ //fully fill the last row with td
					var tn = 4 - tr.children().size(); // the number of rest empty tds
					for(;tn>0; tn--){
						tr.append('<td width="25%">&nbsp;</td>');
					}
				}
				
				if(tr.children().size()==4){
					$('#batch > tbody').append(tr);
					tr = $('<tr/>');
				}
			});
			
			if($('#batch > tbody > tr').size()==0){
				$('#batch > tbody').html('<tr><td align="center">无数据！</td></tr>');
			}
		},
		error: function(data){
			$.alert('访问服务器出错！');
		}
	});
}

function listGrantedBranch(batchId)
{
	clearBranch();
	
	$('#branch > tbody').html('<tr><td align="center"><img src="<s:url value="/images/listloading.gif"/>" align="absmiddle"/>请稍等....</td></tr>');
	$.ajax({
		url: '<s:url value="/enrollment/list_academy_branch"/>',
		async: true,
		type: 'POST',
		dataType: 'json',
		data: {
			batchId:batchId
		},
		success: function(data){
			if(!data){
				//alert(''); 
				return;
			}
			
			$('#branch > tbody').empty();
			var tr = $('<tr/>');
			$(data.grantedList).each(function(index){
				tr.append('<td width="25%"><input type="checkbox" name="branchId" value="'+this.id+'"/>'+this.name+'</td>');

				if(index==data.grantedList.length-1){
					var tn = 4 - tr.children().size(); // the rest td number
					for(;tn>0; tn--){
						tr.append('<td width="25%">&nbsp;</td>');
					}
				}
				
				if(tr.children().size()==4){
					$('#branch > tbody').append(tr);
					tr = $('<tr/>');
				}
			});
			
			if($('#branch > tbody > tr').size()==0){
				$('#branch > tbody').html('<tr><td align="center">无数据！</td></tr>');
			}
		},
		error: function(data){
			$.alert('访问服务器出错！');
		}
	});
}

function listLevel(batchId)
{
	clearLevel();
	
	$('#level > tbody').html('<tr><td align="center"><img src="<s:url value="/images/listloading.gif"/>" align="absmiddle"/>请稍等....</td></tr>');
	
	$.ajax({
		url: '<s:url value="/enrollment/academylevel/list_academy_level_for_batch"/>',
		async: true,
		type: 'POST',
		dataType: 'json',
		data: {
			//academyId:'${param.academyId}',
			batchId:batchId
		},
		success: function(data){
			$('#level > tbody').empty();
			var tr = $('<tr/>');
			$(data.list).each(function(index){
				var levelRadio = $('<input type="radio" name="levelId" value="'+this.level.id+'" al="'+this.id+'"/>');
				levelRadio.change(function(){
					listMajor(this);
				});
				
				$('<td width="25%">'+(this.level ? this.level.name : '')+'</td>').prepend(levelRadio).appendTo(tr);

				if(index==data.list.length-1){ //fully fill the last row with td
					var tn = 4 - tr.children().size(); // the number of rest empty tds
					for(;tn>0; tn--){
						tr.append('<td width="25%">&nbsp;</td>');
					}
				}
				
				if(tr.children().size()==4){
					$('#level > tbody').append(tr);
					tr = $('<tr/>');
				}
			});
			
			if($('#level > tbody > tr').size()==0){
				$('#level > tbody').html('<tr><td align="center">无数据！</td></tr>');
			}
		},
		error: function(data){
			$.alert('访问服务器出错！');
		}
	});
}

function listMajor(level)
{
	var levelId = $(level).attr('al');
	$('#major > tbody').html('<tr><td align="center"><img src="<s:url value="/images/listloading.gif"/>" align="absmiddle"/>请稍等....</td></tr>');
	$.ajax({
		url: '<s:url value="/enrollment/academymajor/list_academy_major_for_academy_level"/>',
		async: true,
		type: 'POST',
		dataType: 'json',
		data: {
			academyLevelId:levelId
		},
		success: function(data){
			if(!data) return;
			
			$('#major > tbody').empty();
			var tr = $('<tr/>');
			$(data.list).each(function(index){
				tr.append('<td width="25%"><input type="checkbox" name="majorId" value="'+this.id+'"/>'+this.name+'</td>');

				if(index==data.list.length-1){ //fully fill the last row with empty table cell
					var tn = 4 - tr.children().size(); // number of the rest empty tds
					for(;tn>0; tn--){
						tr.append('<td width="25%">&nbsp;</td>');
					}
				}
				
				if(tr.children().size()==4){
					$('#major > tbody').append(tr);
					tr = $('<tr/>');
				}
			});
			
			if($('#major > tbody > tr').size()==0){
				$('#major > tbody').html('<tr><td align="center">无数据！</td></tr>');
			}
		},
		error: function(data){
			$.alert('访问服务器出错！');
			$('#major > tbody').html('<tr><td align="center">无数据！</td></tr>');
		}
	});
}

FEE_SUBJECT_PARAM = null;
function fsCallback(data){
	if(!data.list || data.list.length<1){
		$('#feeSubjectId').html('<option value="-1">--请选择--</option>');
	}
	
	$('#feeSubjectId').empty();
	$(data.list).each(function(){
		$('#feeSubjectId').append('<option value="'+this.id+'">'+this.name+'</option>');
	});
}

function listFeeSubject(academyId, batchId){
	clearFS();
	FEE_SUBJECT_PARAM = {academyId: academyId, batchId: batchId};
	ajax_feesubject_1();
}
</script>
<script>
function parseStd(standards){
	var stdInfo = [];
	var len = standards ? standards.length : 0;
	for(var i=0; i<len; i++){
		var std = standards[i];
		
		var form = '';
		if(std.valueForm==MONEY_FORM_RATIO)
			form = '%';
		else if(std.valueForm == MONEY_FORM_AMOUNT)
			form = '元';
		else if(std.valueForm == MONEY_FORM_SCORE)
			form = '分';
		
		stdInfo.push(std.floor + '&nbsp;人&nbsp;&nbsp;&mdash;&nbsp;&nbsp;' + std.ceil+ '&nbsp;人&nbsp;&nbsp;&nbsp;'+ std.value+'&nbsp;&nbsp;&nbsp;'+form);
	}
	
	return stdInfo.join('<br/>');
}
function buildOperation(id){
	var radio = '';
	radio += '<input type="radio" name="policyId" value="'+id+'"/>';
	return radio;
}
</script>
<script type="text/javascript">
jQuery(function(){
	$('#academyId').change(function(){
		clearPolicy();
		listBatch(this.value);
	});
	
	$('#searchPolicy').click(function(){
		search001();
	});
	
	document.getElementById('form').reset();
	
	$('#validDialog').dialog({
		autoOpen: false,
		modal: true,
		shadow: true,
		title:'验证提示',
		width: 300,
		height: 160
	});
	
	$('#conflictDialog').dialog({
		autoOpen: false,
		modal: true,
		shadow: true,
		title:'发生冲突的政策',
		beforeClose: function(){
			$(':checkbox', this).attr('checked', false);
		},
		width: 530,
		height: 260,
		zIndex: 90
	});
	
	$('#saveBtn').click(prepareSavingList);
	
	$('#updateBtn').click(prepareUpdatingList);
	
	$('#cflctToggle').change(toggleConflictId);
	
	$('#feeSubjectId').change(function(){
		$('#table001 > tbody').empty();
		$('#table001 > tbody').append('<tr><td colspan="5" align="center">请点击[查询]</td></tr>');
	});
});
</script>
<a:ajax parameters="SAVE_PARAM" successCallbackFunctions="saveListCallback" traditional="true" pluginCode="save" urls="/enrollment/academyrebatepolicy/save_academy_rbt_plcy_dtl"/>
<a:ajax parameters="UPDATE_PARAM" successCallbackFunctions="updateListCallback" traditional="true" pluginCode="update" urls="/enrollment/academyrebatepolicy/update_academy_rbt_plcy_dtl_list"/>
<a:ajax parameters="FEE_SUBJECT_PARAM" successCallbackFunctions="fsCallback" traditional="true" pluginCode="feesubject" urls="/enrollment/feesubject/json_list_fee_subject_for_academy_batch"/>
  </head>
  
  <body>
<form id="form">
	<!--头部层开始 -->
		<head:head title="院校返款政策">
			<html:a text="返回" icon="return" href="/enrollment/academyrebatepolicy/index_academy_rebate_policy_detail"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		  <table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">院校</th>
			</tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2">
		  	<tr>
		  		<td width="58">院校：</td>
		  		<td width="160"><s:select list="academies" listKey="id" listValue="name" headerKey="0" headerValue="---请选择---" cssClass="txt_box_150" name="academyId" id="academyId"></s:select></td>
		  		<td></td>
		  	</tr>
		  </table>
		  <table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">招生批次</th>
			</tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="batch">
		  	<tbody><tr><td align="center">请选择院校</td></tr></tbody>
		  </table>
			
		  <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">学习中心<input type="checkbox" id="branchTrigger" onclick="checkAll(this, 'branch')"/></th>
			</tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="branch">
			<tbody>
				<tr><td align="center">请选择批次</td></tr>
			</tbody>
		  </table>
		
		 
		   <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">层次
			    </th>
			 </tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="level">
			<tbody>
				<tr><td align="center">请选择批次</td></tr>
			</tbody>
		  </table>
		  
		  <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">专业
				<input name="checkbox3" type="checkbox" id="majorTrigger" onclick="checkAll(this, 'major')"/></th>
		    </tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="major">
		  	<tbody>
				<tr><td align="center">请选择层次</td></tr>
			</tbody>
		  </table>
		  
		  <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">政策标准</th>
		    </tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="feeSubject">
		  	<tbody>
				<tr>
					<td align="right">费用项目：</td>
					<td><select id="feeSubjectId" name="feeSubjectId" class="txt_box_100"><option>--请选择--</option></select></td>
					<td><input type="button" class="btn_black_61" value="查询" id="searchPolicy"/></td>
				</tr>
			</tbody>
		  </table>
		  
		<page:plugin 
			pluginCode="001"
			il8nName="enrollment"
			subStringLength="23"
			searchListActionpath="/enrollment/academyrebatestd/list_academy_rebate_policy_available_to_academy"
			columnsStr="#operate;academyName;title;feeSubjectName;#standardlist"
			customColumnValue="0,buildOperation(id);4,parseStd(standards)"
			pageSize="30"
			isPage="false"
			params="academyId:$('#academyId').val(),feeSubjectId:$('#feeSubjectId').val()"
			isonLoad="false"
			titleBar="title"
			isPackage="false"
			isOrder="true"
		/>
		  
		  <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="button" value="保存" id="saveBtn"/></td></tr></table>
 </body:body>
</form>

<div id="validDialog"  style="display:none"></div>
<div id="conflictDialog" style="display:none">
	<div>
		<table class="gv_table_2">
			<thead>
				<th><input type="checkbox" id="cflctToggle" style="vertical-align: middle;" />覆盖</th>
				<th>院校</th>
				<th>学习中心</th>
				<th>批次</th>
				<th>层次</th>
				<th>专业</th>
				<th>政策标准</th>
				<th>审批状态</th>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	<div style="text-align:center;">
		<input type="button" value="确认覆盖" id="updateBtn" class="btn_black_61"/>
	</div>
</div>
  </body>
</html>
