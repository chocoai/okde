<%@ page language="java" pageEncoding="UTF-8" import="net.cedu.common.*" %>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>院校返款政策</title>
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

	<jc:plugin name="loading" />
	
	<script type="text/javascript">
	function clearBatch()
	{
		$('#batchId').empty().append('<option value="0" selected="selected">--请选择院校--</option>');
		clearLevel();
		clearBranch();
	}
	
	function batchCallback(data){
		$('#batchId').empty();
		if(data!=null && data.batches!=null && data.batches.length>0){
			$('#batchId').prepend('<option value="0" selected="selected">--请选择--</option>');
			$(data.batches).each(function(index){
				var batchOpt = $('<option value="'+this.id+'">'+(this.enrollmentName ? this.enrollmentName : '')+'</option>');
				batchOpt.appendTo('#batchId');
			});
		} else {
			$('#batchId').append('<option value="0" selected="selected">--无数据--</option>');
		}
	}
	BATCH_PARAM = null;
	function listBatch(academyId)
	{
		clearBatch();
		
		if(academyId <= 0) return;
		
		$('#batchId').html('<option value="0" selected="selected">--请稍等--</option>');
		BATCH_PARAM = { academyId: academyId };
		ajax_batch_1();
	}
	
	function clearBranch()
	{
		$('#branchId').empty().append('<option value="0" selected="selected">--请选择批次--</option>');
	}
	function branchCallback(data){
		$('#branchId').empty();
		
		if(!data || !data.grantedList || data.grantedList.length<=0){
			$('#branchId').append('<option value="0" selected="selected">--无数据--</option>');
			return;
		}
		
		$('#branchId').append('<option value="0" selected="selected">--请选择--</option>');
		$(data.grantedList).each(function(index){
			$('#branchId').append('<option value="'+this.id+'">'+this.name+'</option>');
		});
	}
	BRANCH_PARAM = null;
	function listGrantedBranch(batchId)
	{
		clearBranch();
		
		if(batchId < 1) return;
		
		$('#branchId').html('<option value="0" selected="selected">--请稍等--</option>');
		BRANCH_PARAM = {batchId: batchId};
		ajax_branch_1();
	}
	
	function clearLevel()
	{
		$('#levelId').html('<option value="0" selected="selected">--请选择批次--</option>');
		$('#majorId').html('<option value="0" selected="selected">--请选择层次--</option>');
	}
	
	function levelCallback(data){
		$('#levelId').empty();
		
		if(!data || !data.list || data.list.length<=0){
			$('#levelId').append('<option value="0" selected="selected">--无数据--</option>');
			return;
		}
		
		$('#levelId').append('<option value="0" selected="selected">--请选择--</option>');
		
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
		
		$('#levelId').html('<option value="0" selected="selected">--请稍等--</option>');
		ajax_level_1();
	}
	
	function majorCallback(data){
		$('#majorId').empty();
		
		if(!data || !data.list || data.list.length<=0)
		{
			$('#majorId').append('<option value="0" selected="selected">--无数据--</option>');
			return;
		}
		
		$('#majorId').append('<option value="0" selected="selected">--请选择--</option>');
		
		$(data.list).each(function(index){
			$('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>');
		});
	}
	
	MAJOR_PARAM = null;
	function listMajor(levelId)
	{
		if(!levelId)
		{
			$('#majorId').html('<option value="0" selected="selected">--请选择层次--</option>');
			return;
		}
		MAJOR_PARAM = {academyLevelId: levelId};
		$('#majorId').html('<option value="0" selected="selected">--请稍等--</option>');
		ajax_major_1();
	}
	
	DEL_PARAM = null;
	function prepareDel(id){
		$.confirm({msg:'您确定要删除吗？', confirm:function(){
			DEL_PARAM = {detailId: id};
			ajax_delete_1();
		}});
	}
	
	function deleteCallback(data){
		$.alert('删除成功！');
		search001();
	}
	
	function buildOperation(id, auditStatus){
		var viewIcon = '<a href="<uu:url url="/enrollment/academyrebatepolicy/view_academy_rebate_policy_detail"/>?detailId='
		viewIcon += id;
		viewIcon += '" target="_blank" >';
		viewIcon += '<img src="<ui:img url="/images/icon_view.gif"/>" class="img_icon" />';
		viewIcon += '</a>';
		var editIcon ='';
		if(auditStatus != POLICY_AUDIT_STATUS_GOOD){
			editIcon = '<a href="<uu:url url="/enrollment/academyrebatepolicy/edit_academy_rebate_policy_detail"/>?detailId=';
			editIcon += id;
			editIcon += '" target="_self" >';
			editIcon += '<img src="<s:url value="/images/icon_edit.gif"/>" class="img_icon" />';
			
			editIcon += '<a href="javascript:void(0);" onclick="prepareDel('+id+')"><img src="<s:url value="/images/icon_del.gif"/>" class="img_icon" /></a>';
		}
		
		if(editIcon) return viewIcon + editIcon;
		
		return viewIcon;
	}
	
	function standardListParser(standards)
	{
		/** 金额/比例 **/
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
	
	function parseAuditStatus(auditStatus){
		if(auditStatus==POLICY_AUDIT_STATUS_DEFAULT)
			return '未审批';
		else if(auditStatus == POLICY_AUDIT_STATUS_BAD)
			return '未通过';
		else if(auditStatus == POLICY_AUDIT_STATUS_GOOD)
			return '已通过';
	}
	
	function parseEnable(enable){
		if(enable==TRUE)
			return '启用';
		else if(enable==FALSE)
			return '禁用';
	}
	
	jQuery(function(){
		// IE6下不兼容 删除此代码 改为直接写在页面上
		//util.select.initOption('#auditStatus', get_policy_audit_status());
		$('#batchId').change(function(){
			var batch = this.value;
			listGrantedBranch(batch);
			listLevel(batch);
		});
		
		$('#levelId').change(function(){
			var levelId = this.options[this.selectedIndex].getAttribute('al');
			listMajor(levelId);
		});
		
		$('#academyId').change(function(){
			listBatch(this.value);
		});
	});
	</script>
	
	<a:ajax successCallbackFunctions="levelCallback" parameters="LEVEL_PARAM" pluginCode="level" urls="/enrollment/academylevel/list_academy_level_for_batch"/>
	<a:ajax successCallbackFunctions="majorCallback" parameters="MAJOR_PARAM" pluginCode="major" urls="/enrollment/academymajor/list_academy_major_for_academy_level"/>
	<a:ajax successCallbackFunctions="branchCallback" parameters="BRANCH_PARAM" pluginCode="branch" urls="/enrollment/list_academy_branch"/>
	<a:ajax successCallbackFunctions="batchCallback" parameters="BATCH_PARAM" pluginCode="batch" urls="/enrollment/list_enroll_batch_for_academy"/>
	
	<a:ajax successCallbackFunctions="deleteCallback" parameters="DEL_PARAM" pluginCode="delete" urls="/enrollment/academyrebatepolicy/delete_acdm_rbt_plc_dtl"/>
</head>
<body>
		<!--头部层开始 -->
		<head:head title="院校返款政策设置">
			<html:a text="新增院校返款政策" icon="add" href="/enrollment/academyrebatepolicy/add_academy_rebate_policy_detail" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">院校返款政策</th>
					<th>
						<div style="text-align:right;">
							<!-- <img src="<ui:img url="/images/title_menu/icon_update.gif"/>" align="absmiddle"/><a href="<uu:url url="/enrollment/academyrebatepolicy/add_academy_rebate_policy_detail"/>" target="_self">批量设置政策明细</a>-->
						</div>
					</th>
				</tr>
			</table>
		<form id="searchParam">
			<table class="add_table">
              <tr>
              	<td class="lable_80">院校：</td>
              	<td><s:select cssClass="txt_box_150" list="academies" listKey="id" listValue="name" id="academyId" name="academyId" headerKey="0" headerValue="--请选择--"></s:select></td>
					<td class="lable_80">招生批次：</td>
                <td><select class="txt_box_150" id="batchId" name="batchId"><option value="0">--请选择--</option></select></td>
                <td class="lable_80">学习中心：</td>
                <td align="left">
					<select name="branchId" id="branchId" class="txt_box_150"><option value="0">--请选择--</option></select>
					</td>
					<td class="lable_80">层次：</td>
                <td align="left">
					<select class="txt_box_150" name="levelId" id="levelId"><option value="0">--请选择--</option></select>
					</td>
				</tr>
				<tr>
                <td class="lable_80">专业：</td>
                <td align="left">
					<select name="majorId" id="majorId" class="txt_box_150"><option value="0">--请选择--</option></select>
					</td>
					<td  class="lable_80">费用科目：</td>
                <td align="left">
						<s:select name="feeSubjectId" id="feeSubjectId" cssClass="txt_box_150" list="feeSubjects" listKey="id" listValue="name" headerKey="0" headerValue="--请选择--"></s:select>
					</td>
                <td class="lable_80">审批状态：</td>
                <td>
                	<select class="txt_box_150" id="auditStatus" name="auditStatus">
                		<option value="<%=Constants.POLICY_AUDIT_STATUS_ALL %>">全部</option>
                		<option value="<%=Constants.POLICY_AUDIT_STATUS_DEFAULT %>">未审批</option>
                		<option value="<%=Constants.POLICY_AUDIT_STATUS_BAD %>">审批不通过</option>
                		<option value="<%=Constants.POLICY_AUDIT_STATUS_GOOD %>">审批通过</option>
                	</select>
                </td>
                <td align="right">&nbsp;</td>
                <td><input name="button" id="dosearch" type="button" class="btn_black_61" onclick="search001()" value="查询"/></td>
			  </tr>
            </table>
		</form>
           <%--
			<table id="policy_detail_gv" class="gv_table_2" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <thead>
				<tr>
					<th>操作</th>
					<th>院校</th>
					<th>学习中心</th>
					<th>招生批次</th>
					<th>层次</th>
					<th>专业</th>
					<th>费用科目</th>
					<th>政策标准</th>
					<th>审核状态</th>
					<th>是否启用</th>
				</tr>
			  </thead>
			  <tbody>
			  </tbody>
			</table>
			<div id="polciyDetailPager" class="pager2"></div>
			--%>
			
			<page:plugin
				searchListActionpath="list_data_academy_rebate_policy_detail"
				searchCountActionpath="list_count_academy_rebate_policy_detail"
				pluginCode="001" 
				columnsStr="#operate;academyName;branchName;batchName;levelName;majorName;feeSubjectName;#standardlist;#auditstatus;#isenable"
				customColumnValue="0,buildOperation(id,auditStatus);7,standardListParser(standards);8,parseAuditStatus(auditStatus);9,parseEnable(enable)"
				il8nName="enrollment"
				searchFormId="searchParam"
				isOrder="false"
			/>

		</body:body>
</body>
</html>
