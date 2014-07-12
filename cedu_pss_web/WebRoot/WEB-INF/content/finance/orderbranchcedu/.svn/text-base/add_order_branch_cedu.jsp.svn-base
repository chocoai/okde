<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>汇款总部 添加汇款单</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!--  分页 -->
	<jc:plugin name="page" />
	<!-- 时间控件 -->
	<jc:plugin name="calendar" />
	<!-- 选项卡 -->
	<jc:plugin name="tab" />
	<!-- 表单验证 -->
	<jc:plugin name="validator" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	<script type="text/javascript">
	function show_new_feeitem()
	{
		//switch_search_type();
		jQuery('#fee_item_search_div').show();
	}
	function show_added_fee_items()
	{
		jQuery('#added_feeItem_table').show();
	}
	</script>
	<script type="text/javascript">
	function noop(v){
		if(v) return v;
		
		return '';
	}
	
	function _status(){
		return '已收费确认';
	}
	
	function extractMoney(){
		var money = 0;
		
		$('#table001 > tbody > tr').each(function(){
			var checked = $(this).find(':checkbox:checked').size()==1;
			if(checked){
				var mt = $(this).children(':nth(10)').find('div').text();
				if(!/^\s*$/.test(mt) && !isNaN(mt)){
					money += parseFloat(mt);
				}
			}
		});
		
		return money;
	}
///////// 生成统计效果 ////////
/*
	function sortOut(){
		var result = [];
		
		$('#table001 > tbody > tr').each(function(){
			var checked = $(this).find(':checkbox:checked').size()==1;
			var acdm = $(this).children(':nth(3)').text();
			
			var academy;
			for(var i=0; i<result.length; i++){
				if(result[i].academy == acdm){
					academy = result[i];
					break;
				}
			}
			
			if(!academy){
				academy = {academy: acdm};
				result.push(academy);
			}
			
			var bat = $(this).children(':nth(5)').text();
			
			if(!result[academy][batch])
				result[academy][batch] = {};
		});
	}
*/
	
	function feebatch(id){
		return '第'+id+'缴费期';
	}
	
	function caculate(){
		//show_new_feeitem();
		//jQuery('#feeItemListDiv').show();
		$('#remittances').html('&yen;&nbsp;'+extractMoney()+'&nbsp;&nbsp;元');
	}
	
	SAVE_PARAM = null;
	function validAndSave(){
		var err = [];
		
		var remitterAccount = $('#remitterAccount').val();
		if(!remitterAccount || /(^\s+.*)|(.*\s+$)/.test(remitterAccount)){
			//$.alert({msg:'请填写汇款账号，（注意：两边不能有空格）'});
			err.push('请填写汇款账号，（注意：两边不能有空格）');
		}
		
		var remitteeAccount = $('#remitteeAccount').val();
		if(!remitteeAccount || /(^\s+.*)|(.*\s+$)/.test(remitteeAccount)){
			//$.alert({msg:'请填写收款账号，（注意：两边不能有空格）'});
			//return;
			err.push('请填写收款账号，（注意：两边不能有空格）');
		}
		
		var detailIds = [];
		
		$('#table001 > tbody > tr :checkbox:checked').each(function(){
			detailIds.push(this.value);
		});
		
		if(detailIds.length==0){
			//$.alert({msg: '请选择要汇款缴费单！'});
			//return;
			err.push('请选择要汇款缴费单！');
		}
		
		if(err.length>0){
			var ul = $('<ul/>').addClass('validateErrorList');
			
			for(var i=0; i<err.length; i++){
				ul.append('<li>'+err[i]+'</li>');
			}
			
			$.alert({msg: ul});
			
			return false;
		}
		
		var note = $('#note').val();
		SAVE_PARAM = {
			branchId: '${param.branchId}',
			detailIds: detailIds,
			remitterAccount: remitterAccount,
			remitteeAccount: remitteeAccount,
			note: note
		};
		
		ajax_save_1();
	}
	
	function saveCallback(data){
		$.alert({
			msg:'保存成功！',
			ok: function(){ //成功添加时跳转到列表/详情页
				window.location.href='<uu:url url="/finance/orderbranchcedu/view_order_branch_cedu"/>?orderId='+data.orderId;
			}
		});
	}
	
	jQuery(function(){
		util.select.initOption('[name="branch"]', get_branch(), '', {text:'弘成总部',value:''});
	});
	</script>
	
	<a:ajax successCallbackFunctions="saveCallback" parameters="SAVE_PARAM" traditional="true" pluginCode="save" urls="/finance/orderbranchcedu/save_order_branch_cedu"/>
</head>
<body>
	<head:head title="汇款总部">
		<html:a text="关闭" icon="return" onclick="window.close()" target="_self"/>
	</head:head>
	<body:body>
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				<th style="text-align:left; font-weight:bold;">汇款总部 &gt;&gt; 添加汇款单</th>
				<th></th>
			</tr>
		</table>
		
		<table class="add_table">
			<tr>
				<td class="lable_100">汇款单位：</td>
				<td>${branch.name}<input type="hidden" value="${param.branchId}" name="branchId" id="branchId"/></td>
			</tr>
			<tr>
				<td class="lable_100">收款单位：</td>
				<td>弘成总部<!--<select name="branch" id="" class="txt_box_150"></select--></td>
			</tr>
			<tr>
				<td class="lable_100">汇款账号：</td>
				<td><input type="text" name="remitterAccount" id="remitterAccount" class="txt_box_150"/></td>
			</tr>
			<tr>
				<td class="lable_100">收款账号：</td>
				<td><input type="text" name="remitteeAccount" id="remitteeAccount" class="txt_box_150" /></td>
			</tr>
			<tr>
				<td class="lable_100" style="vertical-align:top">选择缴费单：</td>
				<td>

			<page:plugin 
				pluginCode="001"
				il8nName="finance"
				subStringLength="10"
				searchListActionpath="/finance/orderbranchcedu/list_fee_payment_detail_for_order_branch_cedu"
				columnsStr="paymentCode;id;schoolName;#branchname;academyenrollbatchName;levelName;majorName;studentName;paymentSubjectName;amountPaied;#status"
				customColumnValue="3,noop(branchName);10,_status()"
				isPage="false"
				isChecked="true"
				checkboxValue="id"
				params="'branchId':'${param.branchId}'"
				isonLoad="true"
				isPackage="false"
				isOrder="true"
			/>
	
	<table class="gv_table_2"><tr><td align="left"><input type="button" class="btn_black_130" style="text-align:center" onclick="caculate()" value="确认选择"/></td></tr></table>
			
		<div id="fee_item_search_div" style="display:none;">
	
			<table class="add_table">
				<tr>
					<td>
<!----------------------------------     Search Result      ---------------------------------------->
<div id="feeItemListDiv" style="display:none;">
	
	
	<!-------------------------------  START 缴费单处理 START  ------------------------------->
			<table class="gv_table_2" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tbody>
				<tr>
				  <th>序号</th>
				  <th>院校</th>
				  <th>学习中心</th>
				  <th>批次</th>
				  <th>费用项目</th>
				  <th>费用金额</th>
				  <th>查看缴费单</th>
				</tr>
				<tr>
				  <td align="center">1</td>
				  <td align="center">华南师范大学网院</td>
				  <td align="center">北京学习中心</td>
				  <td align="center">0901</td>
				  <td align="center">补考费</td>
				  <td align="center">9003</td>
				  <td align="center"><input type="button" class="btn_gray_130" value="查看选择的缴费单" onclick="show_added_fee_items()"/></td>
				</tr>
				<tr>
				  <td align="center">2</td>
				  <td align="center">厦门大学网院</td>
				  <td align="center">北京学习中心</td>
				  <td align="center">0901</td>
				  <td align="center">学费</td>
				  <td align="center">35000</td>
				  <td align="center"><input type="button" class="btn_gray_130" value="查看选择的缴费单" onclick="show_added_fee_items()"/></td>
				</tr>
			  </tbody>
			</table>
			
			<table id="added_feeItem_table" class="gv_table_2" width="100%" border="0" cellpadding="0" cellspacing="0" style="display:none;">
				<tbody>
					<tr>
						<th>缴费单号</th>
						<th>明细号</th>
						<th>院校</th>
						<th>学习中心</th>
						<th>批次</th>
						<th>层次</th>
						<th>专业</th>
						<th>姓名</th>
						<th>费用科目</th>
						<th>缴费次数</th>
						<th>缴费额</th>
						<th>状态</th>
					</tr>
					<tr>
						<td align="center">JF201106220001</td>
						<td align="center">1</td>
						<td align="center">华南师范大学网院</td>
						<td align="center">北京学习中心</td>
						<td align="center">20110901</td>
						<td align="center">专升本</td>
						<td align="center">工商管理</td>
						<td align="center">吴玉婷</td>
						<td align="center">补考费</td>
						<td align="center">第2次缴费</td>
						<td align="center">5200</td>
						<td align="center">已收费确认</td>
					</tr>
					<tr>
						<td align="center">JF201106220021</td>
						<td align="center">1</td>
						<td align="center">厦门大学网院</td>
						<td align="center">北京学习中心</td>
						<td align="center">20110901</td>
						<td align="center">高起专</td>
						<td align="center">计算机应用与技术</td>
						<td align="center">刘竹婷</td>
						<td align="center">补考费</td>
						<td align="center">第2次缴费</td>
						<td align="center">3803</td>
						<td align="center">已收费确认</td>
					</tr>
				</tbody>
			</table>
</div>
<!-----------------------------      Search Result End          ------------------------------------------>
					</td>
				</tr>
			</table>
		</div>
<!-------------------------------    END 缴费单处理 END  ------------------------------->
					</td>
				</tr>
				<tr>
					<td class="lable_100">汇款总额：</td>
					<td><span style="color:red;" id="remittances">&yen; 0 元</span></td>
				</tr>
				<tr>
					<td class="lable_100">备注：</td>
					<td><textarea cols="60" rows="6" name="note" id="note"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" class="btn_black_61" value="保存" onclick="validAndSave()"/>&nbsp;&nbsp;<input type="button" class="btn_black_61" value="取消"/></td>
				</tr>
			</table>
	</body:body>
</body>
</html>
