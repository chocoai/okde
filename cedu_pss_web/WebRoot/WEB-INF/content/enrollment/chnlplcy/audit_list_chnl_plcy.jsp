<%@ page language="java" pageEncoding="UTF-8" import="net.cedu.common.*" %>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>院校返款政策审批</title>
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
	/** 修改启用状态 **/
	function enableStatusChangeListener(select)
	{
		var enableStatus = $(select).val();
		var detailId = $(select).attr('name');
		detailId = detailId.substring(detailId.indexOf('_')+1);
		$('#tip').dialog('open');
		$('#tip').dialog('option', 'title', '修改政策启用状态');
		$('#tip .hint').html('正在保存设置...');
		$.ajax({
			url: '<uu:url url="/enrollment/academyrebatepolicy/modify_academy_rebate_policy_detail_enable_status"/>',
			type: 'POST',
			async: true,
			data: {
				detailId: detailId,
				enable: enableStatus
			},
			dataType: 'json',
			success: function(data){
				$('#tip .hint').empty().append('保存成功！');
				setTimeout(function(){
					$('#tip').dialog('close');
				}, 2100);
			},
			error: function(xhr){
				$('#tip .hint').empty().append('保存失败！');
				setTimeout(function(){
					$('#tip').dialog('close');
				}, 2100);
			}
		});
	}
	
	function parseAuditStatus(auditStatus){
		if(auditStatus==POLICY_AUDIT_STATUS_DEFAULT)
			return '未审批';
		else if(auditStatus == POLICY_AUDIT_STATUS_BAD)
			return '未通过';
		else if(auditStatus == POLICY_AUDIT_STATUS_GOOD)
			return '已通过';
	}
	
	function buildAudit(id, auditStatus){
		if(auditStatus==POLICY_AUDIT_STATUS_DEFAULT){
			return '<a href="<uu:url url="/enrollment/academyrebatepolicy/audit_view_academy_rebate_policy_detail"/>?detailId='+ id +'"  target="_blank">审批</a>';
		}
		
		return '';
	}
	
	function parseEnable(id, enable, auditStatus){
		if(auditStatus==POLICY_AUDIT_STATUS_DEFAULT || auditStatus==POLICY_AUDIT_STATUS_BAD){
			if(enable==TRUE)
				return '启用';
			else if(enable==FALSE)
				return '禁用';
			//return '禁用';
		} else {
			var opts = '<select class="txt_box_50" name="dtl_'+id+'" onchange="enableStatusChangeListener(this)">';
			opts += '<option value="'+TRUE+'" '+(enable==TRUE ? 'selected="selected"' : '')+'>启用</option>';
			opts += '<option value="'+FALSE+'" '+(enable==FALSE ? 'selected="selected"' : '')+'>禁用</option>';
			opts += '</select>';
			
			return opts;
		}
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
	
	jQuery(function(){
		util.select.initOption('#auditStatus', get_policy_audit_status());
		
		$('#dosearch').click(function(){
			//gridview.draw();
			search001();
		});
		
		$('#tip').dialog({
			autoOpen: false,
			modal: true,
			shadow: true,
			title: '操作提示',
			width: 270,
			height: 160
		});
	});
	</script>
	<%@ include file="../_inc/_cascading_bblm.jsp" %>
</head>
<body>
	<!--头部层开始 -->
	<head:head title="院校返款政策审批">
		<html:a text="返回" icon="return" onclick="history.go(-1);"/>
	</head:head>
	<!--主体层开始 -->
	<body:body>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">院校返款政策</th>
				</tr>
			</table>
			
		<form id="searchForm">
			<table class="add_table">
              <tr>
              	<td class="lable_80">院校：</td>
              	<td><s:select cssClass="txt_box_150" list="academies" listKey="id" listValue="name" id="academyId" name="academyId" headerKey="0" headerValue="===请选择===="></s:select></td>
					<td class="lable_80">招生批次：</td>
                <td><select class="txt_box_150" id="batchId" name="batchId"><option value="0">===请选择===</option></select></td>
                <td class="lable_80">学习中心：</td>
                <td align="left">
					<select name="branchId" id="branchId" class="txt_box_150"><option value="0">===请选择===</option></select>
					</td>
					<td class="lable_80">层次：</td>
                <td align="left">
					<select class="txt_box_150" name="levelId" id="levelId"><option value="0">===请选择===</option></select>
					</td>
				</tr>
				<tr>
                <td class="lable_80">专业：</td>
                <td align="left">
					<select name="majorId" id="majorId" class="txt_box_150"><option value="0">===请选择===</option></select>
					</td>
					<td  class="lable_80">费用科目：</td>
                <td align="left">
						<s:select name="feeSubjectId" id="feeSubjectId" cssClass="txt_box_150" list="feeSubjects" listKey="id" listValue="name" headerKey="0" headerValue="====请选择===="></s:select>
					</td>
                <td class="lable_80">审批状态：</td>
                <td>
                	<select class="txt_box_150" id="auditStatus" name="auditStatus"></select>
                </td>
                <td align="right">&nbsp;</td>
                <td><input name="button" id="dosearch" type="button" class="btn_black_61" value="查询"/></td>
			  </tr>
            </table>
		</form>

		<page:plugin 
			pluginCode="001"
			il8nName="enrollment"
			subStringLength="23"
			searchListActionpath="/enrollment/academyrebatepolicy/list_data_academy_rebate_policy_detail"
			searchCountActionpath="/enrollment/academyrebatepolicy/list_count_academy_rebate_policy_detail"
			columnsStr="academyName;branchName;batchName;levelName;majorName;feeSubjectName;#standardlist;#isenable;#auditstatus;#audit"
			customColumnValue="6,standardListParser(standards);7,parseEnable(id,enable,auditStatus);8,parseAuditStatus(auditStatus);9,buildAudit(id,auditStatus)"
			pageSize="30"
			isPage="true"
			view="http,/enrollment/academyrebatepolicy/audit_view_academy_rebate_policy_detail,detailId,id,_blank"
			searchFormId="searchForm"
			isonLoad="true"
			isPackage="false"
			isOrder="true"
		/>

	</body:body>
<div id="tip">
	<div style="align:center;"><img src="<ui:img url="/images/listloading.gif" />" align="absmiddle" /><font class="hint"></font></div>
</div>
</body>
</html>
