<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>招生返款政策  审批</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!--  分页 -->
	<jc:plugin name="page" />
	<jc:plugin name="loading" />
	<script type="text/javascript">
	function prepareBatchAudit(){
		if($(':checkbox[name=checkbox001]:checked').size()==0){
			$.warn({msg:'请选择将要审批的政策！'});
			return;
		}
		
		$('#batchDialog').dialog('open');
	}
	
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
			url: '<uu:url url="/enrollment/chnlplcy/update_channel_policy_detail_enable_status"/>',
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
			
		return '';
	}
	
	function buildAudit(id, auditStatus){
		if(auditStatus==POLICY_AUDIT_STATUS_DEFAULT){
			return '<a href="<uu:url url="/enrollment/chnlplcy/audit_view_chnl_plcy_dtl"/>?detailId='+ id +'"  target="_blank">审批</a>';
		}
		isPageOperating(id,"001","checkbox");
		return '';
	}
	
	function parseEnable(id, enable, auditStatus){
		if(auditStatus==POLICY_AUDIT_STATUS_DEFAULT || auditStatus==POLICY_AUDIT_STATUS_BAD){
			if(enable==TRUE)
				return '启用';
			else if(enable==FALSE)
				return '停用';
			//return '停用';
		} else {
			var opts = '<select class="txt_box_50" name="dtl_'+id+'" onchange="enableStatusChangeListener(this)">';
			opts += '<option value="'+TRUE+'" '+(enable==TRUE ? 'selected="selected"' : '')+'>启用</option>';
			opts += '<option value="'+FALSE+'" '+(enable==FALSE ? 'selected="selected"' : '')+'>停用</option>';
			opts += '</select>';
			
			return opts;
		}
	}
	
	function parseStdList(stds)
	{
		/** 金额/比例 **/
		var stdInfo = [];
		var len = stds ? stds.length : 0;
		for(var i=0; i<len; i++){
			var std = stds[i];
			
			var form = '';
			if(std.rebatesId==MONEY_FORM_RATIO)
				form = '%';
			else if(std.rebatesId == MONEY_FORM_AMOUNT)
				form = '元';
			else if(std.rebatesId == MONEY_FORM_SCORE)
				form = '分';
			
			stdInfo.push(std.enrollmentFloor + '&nbsp;人&nbsp;&nbsp;&mdash;&nbsp;&nbsp;' + std.enrollmentCeil+ '&nbsp;人&nbsp;&nbsp;&nbsp;'+ std.value+'&nbsp;&nbsp;&nbsp;'+form);
		}
		
		return stdInfo.join('<br/>');
	}
	
	UPDATE_PARAM = null;
	function updateCallback(data)
	{
		search001();
	}
	
	jQuery(function(){
		var handler = '<a href="javascript:initOrderCol();" id="initHandler">设置顺序</a><a href="javascript:saveOrderCol();" id="saveHandler">保存顺序</a>';
		//$('#table001 > thead > tr > th:last').html(handler);
		//$('#saveHandler').hide();
		$('#tip').dialog({
			autoOpen: false,
			modal: true,
			shadow: true,
			title: '操作提示',
			width: 270,
			height: 160
		});
		
		$('#batchDialog').hide();
		$('#batchDialog').dialog({
			title: '批量审批',
			autoOpen: false,
			modal: true,
			shadow: true,
			width: 310,
			height: 200,
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
		
		$('#batchHandler').click(function(){
			$('#batchDialog').dialog('open');
		});
		
		//$('#searchParam').get(0).reset();
	});
	</script>
	
	<%@ include file="../_inc/_cascading_bblm.jsp" %>
	
	<a:ajax successCallbackFunctions="updateCallback" parameters="UPDATE_PARAM" traditional="true" pluginCode="update" urls="/enrollment/chnlplcy/batch_update_channel_policy_detail_enable_status"/>
</head>
<body>
	<!--头部层开始 -->
	<head:head title="合作方政策审批">
		<html:a text="批量审批" icon="update" onclick="prepareBatchAudit()" target=""/>
	</head:head>

<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2">

		  <table class="gv_table_2">
		  	<tr>
				<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">合作方（${ channel.name}）政策明细</th>
		    </tr>
		  </table>
		  
		<form id="searchParam">
			<table class="add_table">
              <tr>
              	<td class="lable_80">院校：</td>
              	<td><s:select cssClass="txt_box_150" list="academies" listKey="id" listValue="name" id="academyId" name="academyId" headerKey="0" headerValue="--请选择--"></s:select></td>
				<td class="lable_80">招生批次：</td>
                <td><select class="txt_box_150" id="batchId" name="batchId"><option value="-1">--请选择--</option></select></td>
				<td class="lable_80">学习中心：</td>
                <td align="left"><select class="txt_box_150" id="branchId" name="branchId"><option value="-1">--请选择--</option></select></td>
                <td class="lable_80">
                	审批状态：</td>
                <td align="left">
                	<select class="txt_box_150" id="auditStatus" name="auditStatus">
                		<option value="-1">--请选择--</option>
                		<option value="<%=Constants.POLICY_AUDIT_STATUS_DEFAULT %>">未审批</option>
                		<option value="<%=Constants.POLICY_AUDIT_STATUS_BAD %>">未通过</option>
                		<option value="<%=Constants.POLICY_AUDIT_STATUS_GOOD %>">已通过</option>
                	</select>
                </td>
			  </tr>
			  <tr>
				<td class="lable_80">层次：</td>
                <td align="left">
					<select class="txt_box_150" name="levelId" id="levelId"><option value="-1">--请选择--</option></select>
				</td>
                <td class="lable_80">专业：</td>
                <td align="left">
					<select name="majorId" id="majorId" class="txt_box_150"><option value="-1">--请选择--</option></select>
				</td>
				<td  class="lable_80">费用科目：</td>
                <td align="left">
						<s:select name="feeSubjectId" id="feeSubjectId" cssClass="txt_box_150" list="feeSubjects" listKey="id" listValue="name" headerKey="-1" headerValue="--请选择--"></s:select>
				</td>
				<td class="lable_80"></td>
				<td>
					<input name="button" id="dosearch" type="button" class="btn_black_61" onclick="search001()" value="查询"/>
				</td>
			  </tr>
            </table>
			<input type="hidden" name="channelId" value="${param.channelId}"/>
		</form>
		
		<page:plugin 
			pluginCode="001"
			il8nName="enrollment"
			subStringLength="23"
			searchListActionpath="/enrollment/chnlplcy/list_chnl_plc_dtl_for_chnl"
			columnsStr="academyName;batchName;levelName;majorName;feeSubjectName;#channel_policy_standard;#isenable;#auditstatus;#audit"
			customColumnValue="5,parseStdList(channelpolicy.standards);6,parseEnable(id,enable,auditStatus);7,parseAuditStatus(auditStatus);8,buildAudit(id,auditStatus)"
			pageSize="30"
			isPage="false"
			view="http,/enrollment/chnlplcy/view_chnl_plcy_dtl,detailId,id,_blank"
			searchFormId="searchParam"
			isonLoad="true"
			isPackage="false"
			isOrder="false"
			checkboxValue="id"
			isChecked="true"
		/>
		
		</div>
	</div>
</div>
<div id="tip"><div class="hint"></div></div>
<div id="batchDialog">
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
