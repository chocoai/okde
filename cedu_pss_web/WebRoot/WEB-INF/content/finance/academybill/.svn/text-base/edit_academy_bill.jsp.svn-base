<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>财务管理 &nbsp; 修改应收学校款</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 表单验证 -->
	<jc:plugin name="validator" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />

	<script type="text/javascript">
	jQuery(function(){
		jQuery("#academyId").val('${model.academyId}');
		jQuery("#branchId").val('${model.branchId}');
		$.formValidator.initConfig({
			formID:'form',
			//mode: 'AutoTip',
			errorFocus: true,
			// onSuccess: function(){
				// alert('SUCCESS');
				
				// return true; 
			// },
			submitOnce: true
			//debug: true
		});
		
		$('#academyId').formValidator({
			onShow: '请选择院校',
			onEmpty: '您必须院校',
			empty: false,
			onError: '您必须院校'
		}).functionValidator({
			fun: function(v,e){
				if(v=='-1'){
					return false;
				}
				
				return true;
			},
			onError: '您必须选择一个院校'
		});
		
		$('#branchId').formValidator({
			onShow: '请选择学习中心',
			onEmpty: '您必须选择学习中心',
			empty: false,
			onError: '您必须选择学习中心'
		}).functionValidator({
			fun: function(v,e){
				if(v=='-1'){
					return false;
				}
				
				return true;
			},
			onError: '您必须选择一个学习中心'
		});
		
		$('#receivedWay').formValidator({
			onShow: '请选择收款类型',
			onEmpty: '您必须收款类型',
			empty: false,
			onError: '您必须收款类型'
		}).functionValidator({
			fun: function(v,e){
				if(v=='-1'){
					return false;
				}
				
				return true;
			},
			onError: '您必须选择一个收款类型'
		});
		
		$('#amount').formValidator({
			onShow: '请填写应收金额',
			onEmpty: '应收金额不能为空',
			empty: false,
			onError: '您必须填写应收金额'
		}).regexValidator({
			regExp: '^[-+]?[0-9]+(\.[0-9]+)?$',
			onError:'应收金额填写不正确，只能填写数字'
		});
		
		/*
		$('#note').formValidator({
			onShow: '请填写备注',
			onCorrect: '您没有填写备注',
			onEmpty: '填写错误',
			onEmpty: '您没有填写备注',
			empty: true
		});
		*/
		/*
		$('#upload').formValidator({
			onShow: '请选择要上传的附件',
			onEmpty: '请您没有选择要上传的附件',
			empty: true
		});
		*/
	});
	</script>
  </head>
  
  <body>
  
	<head:head title="修改应收学校款">
		<html:a text="返回" icon="return" href="/finance/academybill/view_academy_bill?billId=${param.billId}"/>
	</head:head>
	<body:body>
<form id="form" action="<uu:url url="/finance/academybill/edit_academy_bill"/>" method="post" enctype="multipart/form-data">
	<table class="gv_table_2">
		<tr>
			<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
			<th style="text-align:left; font-weight:bold;">修改应收学校款</th>
		</tr>
	</table>
	
	<table class="add_table">
		<tr>
			<td class="lable_100">院校：</td>
			<td><s:select id="academyId" name="academyId" list="academies" listKey="id" listValue="name" value="model.academhyId" cssClass="txt_box_150"></s:select></td>
			<td width="30%"><span id="academyIdTip"></span></td>
		</tr>
		<tr>
			<td class="lable_100"><span>*</span>学习中心：</td>
			<td>
				<s:if test="%{branchlist!=null}">
					<s:select list="%{branchlist}" listKey="id" theme="simple" listValue="name" name="branchId" id="branchId" cssClass="txt_box_150"/>
				</s:if>
				<s:else>
					<select name="branchId" id="branchId" class="txt_box_150">
						<option value="-1">--请选择--</option>
					</select>
				</s:else>						
			</td>
			<td width="30%"><span id="branchIdTip"></span></td>
		</tr>
		<tr>
			<td class="lable_100">收款类型：</td>
			<td>
				<s:select id="receivedWay" name="receivedWay" list="billWays" listKey="id" listValue="name" value="receivedWay" cssClass="txt_box_150"></s:select>
			</td>
			<td><span id="receivedWayTip"></span></td>
		</tr>
		<tr>
			<td class="lable_100">应收金额：</td>
			<td><input type="text" id="amount" name="amount" value="<s:property value="model.amount"/>" class="txt_box_150"/></td>
			<td><span id="amountTip"></span></td>
		</tr>
		<tr>
			<td class="lable_100">备注：</td>
			<td>
				<textarea name="note" id="note" cols="40" rows="5"><s:property value="model.note"/></textarea>
			</td>
			<td><span id="noteTip"></span></td>
		</tr>
		<tr>
			<td class="lable_100">附件：</td>
			<td><input type="file" id="upload" name="upload"/></td>
			<td><span id="uploadTip"></span></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input class="btn_black_61" type="submit" value="保存"/></td>
		</tr>
	</table>
	
	<input type="hidden" name="id" id="id" value="${param.billId}" />
</form>
	</body:body>
  </body>
</html>
