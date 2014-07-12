<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>合作方政策 政策列表</title>
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
	function noop(v){ if(!v) v=''; return v;}
	
	function initOrderCol(){
		if($('#table001 > tbody > tr').size()==1 
			&& $('#table001 > tbody > tr > td').size()==1)
		return;
		
		$('#table001 > tbody > tr').each(function(){
			var td = $(this).children(':last');
			var pannel = td.find('.pannel');
			var txt = pannel.text();
			var reg = new RegExp('\\s|\\r|\\n','ig');
			var order = txt.replace(reg, '');
			pannel.empty();
			pannel.append($('<input type="text" style="width:36px;"/>').val(order).attr('_order_', order));
		});
		
		$('#initHandler').hide();
		$('#saveHandler').show();
	}
	
	/**
	 *检查数组中是否有重复的值
	 */
	function checkDup(arr){
		var c,i,j;
		for(i=0; i<arr.length; i++){
			for(j=i+1; j<arr.length; j++){
				if(arr[j]==arr[i]){
					c = arr[i];
					break;
				}
			}
		}
		
		return c;
	}
	
	ORDER_PARAM = null;
	function saveOrderCol(){
		var ids=[], orders=[];
		
		$('#table001 > tbody > tr').each(function(){
			var td = $(this).children(':last');
			var pannel = td.find('.pannel');
			
			var order = pannel.find('input').val();
			orders.push(order);
			
			var id = td.children('input[type=hidden]').val();
			ids.push(id);

			//pannel.empty();
			//pannel.append(order);
		});
		
		if(ids.length==0 || orders.length==0){
			$.warn({msg: '不需要保存'});
			return;
		}
		
		var dup = checkDup(orders);
		if(typeof dup != 'undefined'){
			$.warn({msg: '序号 <b>"'+dup+'"</b> 重复！'});
			return;
		}
			
		ORDER_PARAM = {
			detailIds: ids,
			orders: orders
		};
		
		ajax_order_1();
		
		//$('#initHandler').show();
		//$('#saveHandler').hide();
	}
	
	function orderCallback(data){
		$.warn({msg: '保存成功！'});
		
		$('#table001 > tbody > tr').each(function(){
			var td = $(this).children(':last');
			var pannel = td.find('.pannel');
			
			var order = pannel.find('input').val();

			pannel.empty();
			pannel.append(order);
			
			$('#initHandler').show();
			$('#saveHandler').hide();
		});
	}
	
	function cancelOrderCol(){
		$('#table001 > tbody > tr').each(function(){
			var td = $(this).children(':last');
			var pannel = td.find('.pannel');
			var order = pannel.find('input').attr('_order_');
			pannel.empty();
			pannel.append(order);
		});
		
		$('#initHandler').show();
		$('#saveHandler').hide();
	}
	
	function buildOrderTd(id, order){
		var html = '';
		
		html += '<div class="pannel">'+order+'</div>';
		html += '<input type="hidden" value="'+id+'"/>';
		
		return html;
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
	
	DELETE_PARAM = null;
	function preDelete(id)
	{
		$.confirm({
			title:'修改提示',
			msg:'您真的要删除吗？',
			confirm:function(){
				DELETE_PARAM = {detailId: id};
				ajax_delete_1();
			}
		});
	}
	function deleteCallback(data)
	{
		if(!data || data.errno!=0){
			$.warn({title:'删除提示', msg:'删除政策时发生了错误！操作已被取消！'});
		} else {
			search001();
		}
	}
	
	function parseAuditStatus(auditStatus,id){
		if(auditStatus==POLICY_AUDIT_STATUS_DEFAULT)
		{
			return '未审批';
		}
		else if(auditStatus == POLICY_AUDIT_STATUS_BAD)
		{
			return '未通过';
		}
		else if(auditStatus == POLICY_AUDIT_STATUS_GOOD)
		{
			isPageOperating(id,"001","update");
			isPageOperating(id,"001","delete");
			return '已通过';
		}
			
		return '';
	}
	
	jQuery(function(){
		var handler = '<a href="javascript:initOrderCol();" id="initHandler">设置顺序</a><div id="saveHandler"><a href="javascript:saveOrderCol();">保存</a>&nbsp;<a href="javascript:cancelOrderCol()" id="cancHandler">取消</a></div>';
		$('#table001 > thead > tr > th:last').html(handler);
		$('#saveHandler').hide();
		
		//$('#searchParam').get(0).reset();
	});
	</script>
	
	<%@ include file="../_inc/_cascading_bblm.jsp" %>
	
	<a:ajax successCallbackFunctions="deleteCallback" parameters="DELETE_PARAM" pluginCode="delete" urls="/enrollment/chnlplcy/delete_chnl_plcy_dtl"/>
	<a:ajax successCallbackFunctions="orderCallback" parameters="ORDER_PARAM" traditional="true" pluginCode="order" urls="/enrollment/chnlplcy/save_chnl_plc_dtl_order"/>
</head>
<body>
	<!--头部层开始 -->
	<head:head title="合作方政策">
		<html:a text="批量设置" icon="update" href="/enrollment/chnlplcy/add_chnl_plcy_dtl?channelId=${param.channelId}" target="_self"/>
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
              	<td><s:select cssClass="txt_box_150" list="academies" listKey="id" listValue="name" id="academyId" name="academyId" headerKey="-1" headerValue="--请选择--"></s:select></td>
					<td class="lable_80">招生批次：</td>
                <td><select class="txt_box_150" id="batchId" name="batchId"><option value="-1">--请选择--</option></select></td>
				<td class="lable_80">学习中心：</td>
                <td align="left"><select class="txt_box_150" id="branchId" name="branchId"><option value="-1">--请选择--</option></select></td>
                <td class="lable_80">审批状态：</td>
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
				<td></td>
				<td align="left">
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
			searchCountActionpath="/enrollment/chnlplcy/count_chnl_plc_dtl_for_chnl"
			columnsStr="#policy_code;#policy_title;academyName;batchName;levelName;majorName;feeSubjectName;#channel_policy_standard;#auditstatus;#orderHandler;"
			customColumnValue="0,noop(channelpolicy.code);1,noop(channelpolicy.title);7,parseStdList(channelpolicy.standards);8,parseAuditStatus(auditStatus,id);9,buildOrderTd(id,order)"
			pageSize="10"
			isPage="true"
			view="http,/enrollment/chnlplcy/view_chnl_plcy_dtl,detailId,id,_blank"
			update="http,/enrollment/chnlplcy/edit_chnl_plcy_dtl,detailId,id,_self"
			delete="json,preDelete,id"
			searchFormId="searchParam"
			isonLoad="true"
			isPackage="false"
			isOrder="false"
		/>
		
		</div>
	</div>
</div>
</body>
</html>
