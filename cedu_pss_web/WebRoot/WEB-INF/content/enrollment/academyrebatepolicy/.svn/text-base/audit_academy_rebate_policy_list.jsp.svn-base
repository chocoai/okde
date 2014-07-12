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
	UPDATE_PARAM = null;
	function prepareBatchAudit(){
		var ids = [];
		$('#table001 :checkbox[name=checkbox001]:checked').each(function(){
			var td = $(this).parent().parent().find('td:last');
			var a = td.find('a');
			if(a.size()==1){
				ids.push(this.value);
			}
		});
		
		if(ids.length>0){
			UPDATE_PARAM = {detailIds: ids};
			$('#batchDialog').dialog('open');
		}
	}
	
	function updateCallback(data){
		$.alert('审批成功！');
		//search001();
		refresh001();
	}
	</script>
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
				}, 1100);
				refresh001();
			},
			error: function(xhr){
				$('#tip .hint').empty().append('保存失败！');
				setTimeout(function(){
					$('#tip').dialog('close');
				}, 1100);
				refresh001();
			}
		});
	}
	
	function parseAuditStatus(auditStatus,id){
		if(auditStatus==POLICY_AUDIT_STATUS_DEFAULT){
			return '未审批';
		}
		else if(auditStatus == POLICY_AUDIT_STATUS_BAD){
			isPageOperating(id,'001',"checkbox");
			return '未通过';
		}
		else if(auditStatus == POLICY_AUDIT_STATUS_GOOD){
			isPageOperating(id,'001',"checkbox");
			return '已通过';
		}
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
		// IE6下不兼容 删除此代码 改为直接写在页面上
		//util.select.initOption('#auditStatus', get_policy_audit_status());
		
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
		
		$('#batchDialog').dialog({
			title: '批量审批',
			autoOpen: false,
			modal: true,
			shadow: true,
			width: 310,
			height: 200,
			open: function(){
				$(this).parent().find('.ui-dialog-buttonset').css({cssFloat:'none', clear:'both', textAlign: 'center'});
			},
			buttons: {
				'确定':function(){
					var detailIds = [];
					
					$(':checkbox[name=checkbox001]:checked').each(function(){
						if($(this).parent().parent().children(':last').children('a').size()==1)
							detailIds.push(this.value);
					});
					
					if(detailIds.length == 0){
						$(this).dialog('close');
						return;
					}
					
					var auditStatus = $('#batchDialog :radio:checked').val();
					
					UPDATE_PARAM = { detailIds: detailIds, auditStatus: auditStatus };
					
					ajax_update_1();
					$(this).dialog('close');
				},
				'取消':function(){ $(this).dialog('close'); }
			},
			beforeClose: function(){
				$(':checkbox', this).attr('checked', false);
			}
		});
	});
	</script>
	<%@ include file="../_inc/_cascading_bblm.jsp" %>
	
	<a:ajax successCallbackFunctions="updateCallback" parameters="UPDATE_PARAM" traditional="true" pluginCode="update" urls="/enrollment/academyrebatepolicy/batch_audit_acdm_rbt_plc_dtl"/>
</head>
<body>
	<!--头部层开始 -->
	<head:head title="院校返款政策审批">
		<html:a text="批量审批" icon="update" onclick="prepareBatchAudit()" target="_self"/>
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
			customColumnValue="6,standardListParser(standards);7,parseEnable(id,enable,auditStatus);8,parseAuditStatus(auditStatus,id);9,buildAudit(id,auditStatus)"
			pageSize="30"
			isPage="true"
			view="http,/enrollment/academyrebatepolicy/audit_view_academy_rebate_policy_detail,detailId,id,_blank"
			searchFormId="searchForm"
			isonLoad="true"
			isPackage="false"
			isOrder="false"
			isChecked="true"
		/>

	</body:body>
<div id="tip">
	<div style="align:center;"><img src="<ui:img url="/images/listloading.gif" />" align="absmiddle" /><font class="hint"></font></div>
</div>

<div id="batchDialog" style="display:none;">
	<table width="100%">
		<tr>
			<td colspan="4"><h2 align="center">审批结果</h2></td>
		</tr>
		<tr>
			<td></td>
			<td width="90"><input type="radio" name="auditStatus" value="<s:property value="@net.cedu.common.Constants@POLICY_AUDIT_STATUS_GOOD"/>" id="approve_t"/><label for="approve_t">通过</label></td>
			<td width="90"><input type="radio" name="auditStatus" value="<s:property value="@net.cedu.common.Constants@POLICY_AUDIT_STATUS_BAD"/>" id="approve_f"/><label for="approve_f">未通过</label></td>
			<td></td>
		</tr>
	</table>
</div>
</body>
</html>
