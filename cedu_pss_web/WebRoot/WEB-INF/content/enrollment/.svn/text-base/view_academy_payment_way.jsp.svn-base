<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>缴费方式</title>

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
<script type="text/javascript">
function parseBranchPayWay(e, batchId,indexc)
{
	var row = $('<tr/>');
	var xuhao=$('<td/>');
	xuhao.append(indexc);
	var branch = $('<td/>');
	branch.append(e.branch.name);
	
	var pay = $('<td/>');
	var wayNames = new Array(e.ways ? e.ways.length : 0);
	for(var i=0; i<wayNames.length; i++){
		wayNames[i] = e.ways[i].name;
	}
	pay.append(wayNames.join('<br/>'));
	
	row.append(xuhao).append(branch).append(pay);
	row.append('<td><a href="<uu:url url="/enrollment/view_academy_payment_branch"/>?academyId=${param.academyId}&tab=${param.tab}&branchId='+e.branch.id+'&batchId='+batchId+'">设置</a></td>');
	
	row.css({textAlign: 'center'});
	
	return row;
}
function listBranchPaymentWay(batchId)
{
	$('#batchingAnchor').attr('href', 'javascript:void(0);');
	
	$.ajax({
		type: 'POST',
		url: '<uu:url url="/enrollment/list_branch_payment_way"/>',
		data: {academyId:'${param.academyId}', batchId: batchId},
		dataType: 'json',
		success: function(data){
			$('#branchPayways > tbody').empty();
			var indexc=1;
			$(data.branchPaymentWay).each(function(){
				var row = parseBranchPayWay(this, batchId,indexc);
				$('#branchPayways > tbody').append(row);
				indexc++;
			});
			if($('#branchPayways > tbody tr').size()==0)
			{
				$('#branchPayways > tbody').append('<tr><td colspan="3">无数据!</td></tr>');
			}
			else
			{
				var url = '<uu:url url="/enrollment/view_academy_payment_batching"/>?academyId=${param.academyId}&tab=${param.tab}&batchId='+batchId;
				$('#batchingAnchor').attr('href', url);
			}
		}
	});
}

function select_batch()
{
	var year = $('#year_').val();
	$('#way_tab>tbody').each(function(){
		if($(this).attr('id')=='year_'+year)
			$(this).show();
		else
			$(this).hide();
	});
}

$(function(){
	$('[name=batch]').change(function(){
		$('#branchPayways > tbody').empty().append('<tr><td colspan="3" align="center"><img src="<ui:img url="/images/listloading.gif"/>" align="absmiddle" /加载中。。。</td></tr>');
		listBranchPaymentWay(this.value);
	});
	
	$(':radio[name=batch]:nth(0)').attr('checked', true).change();
});

</script>
</head>  
<body>
	<!-- 头开始 -->
	<head:head title="招生计划">
	</head:head>
	<!-- 主体层开始 -->
	<body:body>
		<!-- Menu Begin -->
		<%@ include file="_tab/academy_enroll_tab.jsp" %>
		<!-- Menu End -->
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
				<th style="text-align:left; font-weight:bold;">院校名称：${request.academy.name}</th>
			</tr>
		</table>
		<!-- Left Begin -->
		<div style="float:left; width:35%; margin-right:1%;">
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">招生批次</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2" id="way_tab">
				<s:iterator var="batch" value="batches">
					<tr>
						<td width="30"><input type="radio" name="batch" value="<s:property value="#batch.id"/>" /></td>
						<td><s:property value="#batch.enrollmentName"/></td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<!--Left End-->

		<!--Line Begin-->
		<div style="float:left;width:3px; height:210px; background-color:#3394C1;">
		</div>
		<!--Line End-->
		
		<div style="float: right; width: 63%;">
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">已分配学习中心</th>
					<th style="text-align:right; font-weight:bold;"><a href="javascript:void(0);" id="batchingAnchor">批量设置</a> &nbsp;&nbsp;</th>
				</tr>
			</table>
			<table  class="gv_table_2" id="branchPayways">
			  <thead>
				<tr>
					<th>序号</th>
					<th>学习中心</th>
					<th>缴费方式</th>
					<th>操作</th>
				</tr>
			  </thead>
			  <tbody></tbody>
			</table>
		</div>
	</body:body>
</body>
</html>
