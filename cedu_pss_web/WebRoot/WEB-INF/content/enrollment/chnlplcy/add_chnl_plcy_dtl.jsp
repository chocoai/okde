<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>新增招生返款政策</title>
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
function toggleConflictId(){
	var checked = !!this.checked;
	$('#conflictDialog :checkbox[name="policyDetailId"]').each(function(){
		if(!this.disabled)
			this.checked = checked;
	});
}
/* 解析冲突政策列表 */
function parseConflictList(list){
	$(list).each(function(){
		var row = $('<tr/>');
		
		var chkbox = $('<input type="checkbox" name="policyDetailId" value="'+this.id+'"/>');
		
		if(this.auditStatus==POLICY_AUDIT_STATUS_GOOD){
			$(chkbox).attr('disabled', 'disabled');
		}
		
		row.append($('<td/>').append(chkbox));
		
		if(this.academyId==-1){
			row.append('<td colspan="4">通用合作方政策</td>');
		} else {
			row.append('<td>'+(this.academyName ? this.academyName : '')+'</td>');
			
			row.append('<td>'+(this.batchName ? this.batchName : '')+'</td>');
			row.append('<td>'+(this.levelName ? this.levelName :'')+'</td>');
			row.append('<td>'+(this.majorName ? this.majorName : '')+'</td>');
		}
		
		var title = '';
		if(this.channelpolicy && this.channelpolicy.title)
			title = this.channelpolicy.title;
		row.append('<td>'+title+'</td>');
		
		var auditTd = $('<td/>').append(this.auditStatus==POLICY_AUDIT_STATUS_DEFAULT ? '未审批' : this.auditStatus==POLICY_AUDIT_STATUS_BAD ? '未通过' : this.auditStatus==POLICY_AUDIT_STATUS_GOOD ? '已通过' : '');
		row.append(auditTd);
		
		row.css('text-align', 'center');
		
		$('#conflictDialog table.gv_table_2 > tbody').append(row);
	});
}

/** 保存成功时的回调函数 **/
SAVE_PARAM = {};
function saveListCallback(data)
{
	if(data.conflictList && data.conflictList.length>0){
		$('#conflictDialog table.gv_table_2 > tbody').empty();
		$('#conflictDialog').dialog('open');
		parseConflictList(data.conflictList);
	} else {
		$.warn({msg: '添加成功！'});
	}
}

/** 保存前数据处理及验证 **/
function prepareSavingList()
{
	var flag, errMsg=[];
	
	flag = $(':radio[name=general]:checked').val();
	flag = flag == 'true' ? true : false;
	
	SAVE_PARAM = {
		general: flag,
		// academyId: $('#academyId').val(),
		// majorId: majorIds,
		// policyId: $(':radio[name=policyId]:checked').val(),
		// batchId: $(':radio[name=batchId]:checked').val(),
		// levelId: $(':radio[name=levelId]:checked').val(),
		channelId: '${param.channelId}',
		feeSubjectId: $('#feeSubjectId').val()
	};
	
	if(!flag){
		if($('#academyId').val()=='-1'){
			errMsg.push('请选择院校！');
		} else {
			SAVE_PARAM.academyId = $('#academyId').val();
		}
		
		if($(':radio[name=batchId]:checked').size()==0){
			errMsg.push('请选择批次');
		} else {
			SAVE_PARAM.batchId = $(':radio[name=batchId]:checked').val();
		}
		
		if($(':checkbox[name=branchId]').size()==0 && $(':radio[name=batchId]:checked').size()==1){
			errMsg.push('本批次没有授权，请选择其他批次');
		} else if($(':checkbox[name=branchId]:checked').size()==0){
			errMsg.push('请选择学习中心');
		} else {
			var branchIds = [];
			$(':checkbox[name=branchId]:checked').each(function(){
				branchIds.push(this.value);
			});
			
			SAVE_PARAM.branchIds = branchIds;
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

		
		SAVE_PARAM.majorId = majorIds;
		SAVE_PARAM.levelId = $(':radio[name=levelId]:checked').val();
	}
	
	if($(':radio[name="policyId"]:checked').size()==0) {
		errMsg.push('请选择返款标准！');
	} else {
		SAVE_PARAM.policyId = $(':radio[name=policyId]:checked').val();
	}

	if(errMsg.length>0){
		$('#validDialog').empty().append('<ul class="validateErrorList"></ul>');
		for(var _i=0; _i<errMsg.length; _i++){
			$('#validDialog > ul').append('<li>'+errMsg[_i]+'</li>');
		}

		$('#validDialog').dialog('open');
		return;
	}

	filterBlankOfJSON(SAVE_PARAM);
	
	ajax_save_1();
}

/** 更新冲突列表成功回调函数 **/
UPDATE_PARAM ={};
function updateListCallback(data)
{
	$('#conflictDialog').dialog('close');
	$('#validDialog').html('操作成功！');
	$('#validDialog').dialog('option', 'title', '操作提示');
	$('#validDialog').dialog('open');
}

/** 执行覆盖冲突政策前的数据准备与验证 **/
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
	
	$.confirm({msg:'您确认覆盖吗？', confirm:function(){
		ajax_update_1();
	}});
}
</script>
<script type="text/javascript">
function checkAll(ele, id)
{
	$('#'+id+' :checkbox').attr('checked', !!ele.checked);
}
</script>
<script type="text/javascript">
function clearBatch()
{
	$('#batch > tbody').html('<tr><td align="center">请选择院校</td></tr>');
	
	clearLevel();
	clearBranch();
	clearFS();
}

function clearLevel()
{
	$('#level > tbody').html('<tr><td align="center">请选择批次</td></tr>');
	clearMajor();
}

function clearMajor()
{
	$('#major > tbody').html('<tr><td align="center">请选择层次</td></tr>');
}
function clearPolicy()
{
	$('#policyList > tbody').html('<tr><td colspan="5" align="center">请重新查询</td></tr>');
}

/** 批次Ajax查询成功回调 **/
function batchCallback(data)
{
	$('#batch > tbody').empty();
	var tr = $('<tr/>');
	$(data.batches).each(function(index){
		var levelRadio = $('<input type="radio" name="batchId" value="'+this.id+'"/>');
		levelRadio.change(function(){
			listLevel(this.value);
			listBranch(this.value);
			listFeeSubject(this.value);
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
}

/** 批次Ajax查询前数据准备与验证 **/
BATCH_PARAM = null;

function listBatch(academyId)
{
	clearBatch();
	if(academyId <= 0) return;
	
	BATCH_PARAM = {academyId:academyId};
	ajax_batch_1();
}

/** 政策标准查询参数 **/
function getPolicySearchParam()
{
	var data = {};
	data.academyId = $('#academyId').val();
	data.feeSubjectId = $('#feeSubjectId').val();
	
	return data;
}

/** 政策标准查询Ajax回调函数 **/
function parseRebatePolicy(policy)
{
	var row = $('<tr/>');

	var radio = $('<input type="radio" name="policyId"/>').val(policy.id);
	$('<td/>').append(radio).appendTo(row);
	
	var academyName = policy.academyName ? policy.academyName : '';
	if(!academyName && policy.academyId== '<s:property value="@net.cedu.entity.enrollment.AcademyRebatePolicy@ACADEMY_ID_ALL"/>')
		{academyName = '所有院校';}
	
	
	$('<td/>').append(getbranchName(policy.branchName)).appendTo(row);
	
	$('<td/>').append(academyName).appendTo(row);

	$('<td/>').append(policy.title).appendTo(row);
	
	$('<td/>').append(policy.feeSubjectName).appendTo(row);
	
	var std = $('<td/>').appendTo(row);
	var stdLen = (policy.standards ? policy.standards.length : 0);
	for(var i=0; i<stdLen; i++){
		var vf = '';
		if(policy.standards[i].valueForm == MONEY_FORM_RATIO)
			vf = '%';
		else if(policy.standards[i].valueForm == MONEY_FORM_AMOUNT)
			vf = '元';
		else if(policy.standards[i].valueForm == MONEY_FORM_SCORE)
			vf = '分';
		std.append(policy.standards[i].floor+'人&nbsp;&mdash;'+policy.standards[i].ceil+'&nbsp;人&nbsp;&nbsp;'+policy.standards[i].value+'&nbsp;&nbsp;'+vf+'<br/>');
	}
	
	row.css({textAlign:'center'}); //让行中各个单元格居中显示
	
	return row;
}
</script>
<script type="text/javascript">
/********* 级联Ajax函数 **********/
////////////////////层次//////////////////////
function levelCallback(data)
{
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
}

LEVEL_PARAM = null;
function listLevel(batchId)
{
	clearLevel();
	LEVEL_PARAM = { batchId: batchId };
	ajax_level_1();
}

////////////////////专业//////////////////////
function majorCallback(data)
{
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
}

MAJOR_PARAM = null;
function listMajor(level)
{
	var levelId = $(level).attr('al');
	MAJOR_PARAM = { academyLevelId:levelId };
	ajax_major_1();
}

function clearFS(){
	$('#feeSubjectId').html('<option value="-1">--请选择--</option>');
	$('#policyList > tbody').html('<tr><td colspan="5" align="center">请单击[查询]按钮以查询数据！</td></tr>');
}

FEE_SUBJECT_PARAM = null;
function fsCallback(data){
	if(!data.list || data.list.length<1){
		clearFS();
	} else {
		$('#feeSubjectId').empty();
		$(data.list).each(function(){
			$('#feeSubjectId').append('<option value="'+this.id+'">'+this.name+'</option>');
		});
	}
}

function listFeeSubject(batchId){
	var academyId = $('#academyId').val();
	FEE_SUBJECT_PARAM = {academyId: academyId, batchId: batchId};
	ajax_feesubject_1();
}

/** 政策标准查询参数 **/
function getStdParam()
{
	var data = {};
	data.feeSubjectId = $('#feeSubjectId').val();
	data.channelId = '${param.channelId}';
	data.academyId = $('#academyId').val();
	
	return data;
}


/** 政策标准查询成功时回调函数 **/
function parse_standard(data)
{
	if(!data)
	{
		$.alert('无数据');
		return;
	}
	
	$('#policyList > tbody').empty();
	$(data.list).each(function(){
		var policy = this;
		var row = $('<tr/>');
		
		var radio = $('<input type="radio" name="policyId"/>').val(policy.id);
		
		$('<td/>').append(radio)/*.append(viewIcon).append(editIcon)*/
		.appendTo(row);
		
		var academyName;
		if(policy.academyId==-1 || policy.academyId==0){
			academyName = '所有院校';}
		else{
			academyName = policy.academyName ? policy.academyName : '';}
		
		$('<td/>').append(getbranchName(policy.branchName)).appendTo(row);
		
		$('<td/>').append(academyName).appendTo(row);

		$('<td/>').append(policy.title).appendTo(row);
		
		$('<td/>').append(policy.feeSubjectName).appendTo(row);
		
		var std = $('<td/>').appendTo(row);
		var stdLen = (policy.standards ? policy.standards.length : 0);
		for(var i=0; i<stdLen; i++){
			var vf = '';
			if(policy.standards[i].rebatesId == MONEY_FORM_RATIO)
				vf = '%';
			else if(policy.standards[i].rebatesId == MONEY_FORM_AMOUNT)
				vf = '元';
			else if(policy.standards[i].rebatesId == MONEY_FORM_SCORE)
				vf = '分';
			std.append(policy.standards[i].enrollmentFloor+'人&nbsp;&mdash;'+policy.standards[i].enrollmentCeil+'&nbsp;人&nbsp;&nbsp;'+policy.standards[i].value+'&nbsp;'+vf+'<br/>');
		}
		
		row.css({textAlign:'center'}); //让行中各个单元格居中显示
		
		$('#policyList > tbody').append(row);
	});
	
	if($('#policyList > tbody > tr').size()==0){
		$('#policyList > tbody').append('<tr><td colspan="'+$('#policyList > thead > th').size()+'" align="center">无数据。。。</td></tr>');
	}
}
	function getbranchName(branchName)
		{
			if(branchName!=null && branchName.length>15)
			{
				return '<span title='+branchName+'>'+branchName.substring(0,12)+"..."+'</span>';
			}else
			{
				return '<span title='+branchName+'>'+branchName+'</span>';
			}
		}

jQuery(function(){
	$('#academyId').change(function(){
		clearPolicy();
		listBatch(this.value);
	});
	
	$('#searchPolicy').click(function(){
		ajax_001_1();
	});
	
	document.getElementById('form').reset();
	
	$(':radio[name=general]').change(function(){
		if($(this).val()=='true'){
			$('#academyId').val('-1');
			$('#academyId').hide();
			$('#detailBlk').hide();
			ajax_fs_all_1();
		} else {
			$('#academyId').show();
			$('#detailBlk').show();
			clearFS();
		}
	});
	
	$(':radio[name=general]:nth(0)').change();
	
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
		$('#policyList > tbody').empty();
		$('#policyList > tbody').append('<tr><td colspan="5" align="center">请点击[查询]</td></tr>');
	});
});
</script>

<a:ajax parameters="getStdParam()" successCallbackFunctions="parse_standard"  pluginCode="001" urls="/enrollment/chnlplcystd/list_chnl_plcy_std_for_chnl"/>
<a:ajax successCallbackFunctions="levelCallback" parameters="LEVEL_PARAM" pluginCode="level" urls="/enrollment/academylevel/list_academy_level_for_batch"/>
<a:ajax successCallbackFunctions="majorCallback" parameters="MAJOR_PARAM" pluginCode="major" urls="/enrollment/academymajor/list_academy_major_for_academy_level"/>
<a:ajax successCallbackFunctions="batchCallback" parameters="BATCH_PARAM" pluginCode="batch" urls="/enrollment/list_enroll_batch_for_academy"/>
<a:ajax successCallbackFunctions="branchCallback" parameters="BRANCH_PARAM" pluginCode="branch" urls="/enrollment/chnlplcy/list_branch_for_channel_and_batch"/>

<a:ajax parameters="SAVE_PARAM" successCallbackFunctions="saveListCallback" traditional="true" pluginCode="save" urls="/enrollment/chnlplcy/save_chnl_plcy_dtl"/>
<a:ajax parameters="UPDATE_PARAM" successCallbackFunctions="updateListCallback" traditional="true" pluginCode="update" urls="/enrollment/chnlplcy/update_chnl_plcy_dtl_list"/>

<a:ajax parameters="FEE_SUBJECT_PARAM" successCallbackFunctions="fsCallback" traditional="true" pluginCode="feesubject" urls="/enrollment/feesubject/json_list_fee_subject_for_academy_batch"/>
<a:ajax parameters="FEE_SUBJECT_PARAM" successCallbackFunctions="fsCallback" pluginCode="fs_all" urls="/enrollment/chnlplcy/json_list_fee_subject"/>

  </head>
  <body>
<form id="form" method="post" onsubmit="return false">
		<!--头部层开始 -->
		<head:head title="招生返款政策设置">
			<html:a text="返回" icon="return" target="_self" href="/enrollment/chnlplcy/index_list_chnl_plc_dtl?channelId=${param.channelId}"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
		  <table class="gv_table_2">
			<tr>
					<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">合作方：<s:property value="channel.name"/></th>
					<th style=""></th>
			</tr>
		  </table>
			
		  <table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">院校</th>
					<th width="300">
						<input type="radio" value="true" name="general" checked="checked"/>多院校合并返款政策 &nbsp;&nbsp;
						<input type="radio" value="false" name="general" />单院校返款政策
					</th>
					<th width="160" align="right"><s:select list="academies" listKey="id" listValue="name" headerKey="-1" headerValue="---请选择---" cssClass="txt_box_150" name="academyId" id="academyId"></s:select></th>
			</tr>
		  </table>
	<div id="detailBlk">
		<!-- 招生批次 -->
		  <table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">招生批次</th>
			</tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="batch">
		  	<tbody><tr><td align="center">请选择院校</td></tr></tbody>
		  </table>
		  
		<!-- 学习中心 -->
		<s:if test="channel.isAll==1">
		  <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">学习中心</th>
			</tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="branch">
		  	<tbody><tr><td align="center">请选择招生批次</td></tr></tbody>
		  </table>
		  
		  <script type="text/javascript">
			function clearBranch(){
				$('#branch > tbody').empty().append('<tr><td align="center">请选择批次</td></tr>');;
			}
			
			BRANCH_PARAM = null;
			function getBranchParam(){
				var batchId=$(':radio[name=batchId]:checked').val();
				if(batchId) {
					BRANCH_PARAM={ batchId: batchId , channelId:'${param.channelId}'};
					return true;
				}
				
				return false;
			}
			
			function branchCallback(data){
				//if(!data) return;
				
				$('#branch > tbody').empty();
				var tr = $('<tr/>');
				$(data.list).each(function(index){
					tr.append('<td width="25%"><input type="checkbox" name="branchId" value="'+this.id+'"/>'+this.name+'</td>');

					if(index==data.list.length-1){
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
			}
			
			function listBranch(batchId)
			{
				clearBranch();
				if(getBranchParam()){
					ajax_branch_1();
					
					return;
				}
				
				//$.alert({msg: '请选择批次！'});
			}
		  </script>
		</s:if>
		<s:else>
			<input type="hidden" value="-1" name=""/>
		</s:else>

		<!-- 层次 -->
		  <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">层次</th>
			 </tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="level">
			<tbody>
				<tr><td align="center">请选择批次</td></tr>
			</tbody>
		  </table>
		  
		<!-- 专业 -->
		  <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">专业<input name="checkbox3" type="checkbox" onclick="checkAll(this, 'major')"/></th>
		    </tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="major">
		  	<tbody>
				<tr><td align="center">请选择层次</td></tr>
			</tbody>
		  </table>

	<!-- ----- 政策标准 ----- -->
	</div>
		  <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">政策标准</th>
		    </tr>
		  </table>
		  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="feeSubject">
		  	<tbody>
				<tr>
					<td class="lable_110">合作方类型：</td>
					<td align="left"><s:property value="channelType.name"/></td>
					<td class="lable_110">费用项目：</td>
					<td align="left"><select id="feeSubjectId" name="feeSubjectId" class="txt_box_100"><option>--请选择--</option></select></td>
					<td align="right"><input type="button" class="btn_black_61" value="查询" id="searchPolicy"/></td>
				</tr>
			</tbody>
		  </table>
		  
		  <table class="gv_table_2" border="0" cellpadding="2" cellspacing="2" id="policyList">
			  <thead>
			  	<tr>
			  		<th></th>
			  		<th>学习中心</th>
			  		<th>适合院校</th>
			  		<th>政策名称</th>
			  		<th>费用项目</th>
			  		<th>金额/比例</th>
			  	</tr>
			  </thead>
			  <tbody>
			  	<tr>
			  		<td colspan="5" align="center">请单击[查询]按钮以查询数据！</td>
				</tr>
			  </tbody>
		  </table>
		  
		  <input type="hidden" name="channelId" value="${param.channelId}"/>
		  
		  <table class="add_table"><tr><td align="center"><input class="btn_black_61" id="saveBtn" type="button" value="保存"/></td></tr></table>
  </body:body>
</form>

<!-- ----- 对话框 ----- -->
<div id="validDialog"  style="display:none"></div>
<div id="conflictDialog" style="display:none">
	<div>
		<table class="gv_table_2">
			<thead>
				<th><input type="checkbox" id="cflctToggle" style="vertical-align: middle;" />覆盖</th>
				<th>院校</th>
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
