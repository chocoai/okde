<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>院校返款 添加返款单</title>
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
	<!-- 分页 -->
	<jc:plugin name="page" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	
	<script type="text/javascript">
	function buildAttachment(id){
		return '<a target="_blank" href="<uu:url url="/finance/academybill/download_academy_bill_attachment"/>?billId='+id+'">下载附件</a>';
	}
	
	function toBillStatus(isRebate){
		if(isRebate == TRUE){
			return '已返款';
		}
		
		return '未返款';
	}
	//显示缴费单状态
	function showstatus(status)
	{
		return status.getPaymentStatus();
	 }
	
	function billAmount(amount){
		if(typeof(amount) != 'undefined'){
			return '<div>'+amount+'</div>';
		}
		
		return '[无]';
	}

	function processCheckbox002(){
		processCheckbox('checkbox002',3);
	}
	
	function processCheckbox(checkbox,index){
		$(':checkbox[name='+checkbox+']').change(function(){
			var paysum = $('#paysum').text();
			paysum = parseFloat(paysum);
			
			var money = $(this).parent().parent().find('td:nth('+index+')').find('div').text();
			if(!isNaN(money)){
				money = parseFloat(money);
			} else {
				money = 0;
			}
				
			if(this.checked){
				paysum += money;
			} else {
				paysum -= money;
			}
			
			$('#paysum').text(paysum);
		});
	}
	
	function doSave(){
		if(jQuery("#indexcount").val()==1)
		{
			jQuery("#showDialog").html('<b>已添加过，请选择院校重新查询！</b>');
			jQuery('#message_returns_tips').dialog('open');
		}
		else if(jQuery("#academyId").val()==0)
		{
			jQuery("#showDialog").html('<b>请选择返款院校！</b>');
			jQuery('#message_returns_tips').dialog('open');
		}
		else if((getCheckedValues002()==null || getCheckedValues002()=="") && jQuery("#feepdIds").val()=="")
		{
			jQuery("#showDialog").html('<b>请选择返款的缴费单明细或者应收学习款！</b>');
			jQuery('#message_returns_tips').dialog('open');
		}
		else
		{
			
			jQuery("#planedAcademyBillIds").val(getCheckedValues002());
			jQuery("#amount").val(jQuery("#paysum").text());
			jQuery("#notes").val(jQuery("#note").val());
			ajax_140_1();
		}
		
	}
	//查询
	function findfeepayment()
	{
		if(jQuery("#remitteeId").val()==0)
		{
			jQuery("#showDialog").html('<b>请选择返款院校！</b>');
			jQuery('#message_returns_tips').dialog('open');
		}
		else if(jQuery("#batchId").val()==0)
		{
			jQuery("#showDialog").html('<b>请选择招生批次！</b>');
			jQuery('#message_returns_tips').dialog('open');
		}
		else if(jQuery("#remitteeId").val()==jQuery("#academyId").val())
		{
			jQuery("#obatchId").val(jQuery("#batchId").val());
			search001();			
		}
		else
		{
			jQuery("#academyId").val(jQuery("#remitteeId").val());
			jQuery("#obatchId").val(jQuery("#batchId").val());
			search001();
			search002();
			$('#rebateContent').show();	
				
			jQuery("#feepdIds").val("");
			$('#paysum').text("0")
			jQuery("#indexcount").val(0);
			search003();
			
		}
	}
	//选择需要返款的待缴费单  获取缴费单明细ids集合
	function selectfpd()
	{
		if(getCheckedValues001()==null || getCheckedValues001()=="")
		{
			jQuery("#showDialog").html('<b>请选择要返款的缴费单明细！</b>');
			jQuery('#message_returns_tips').dialog('open');
		}
		else
		{
			ajax_120_1();//判断选择	
		}
	}
	//移除不需要返款的待缴费单  获取缴费单明细ids集合
	function deletefpd()
	{
		//alert(getCheckedValues003());
		if(getCheckedValues003()==null || getCheckedValues003()=="")
		{
			jQuery("#showDialog").html('<b>请选择要移除的缴费单明细！</b>');
			jQuery('#message_returns_tips').dialog('open');
		}
		else
		{
			$('#message_confirm').dialog({
				title:'确认操作',
				buttons: {
					'确认': function() { 
							$(this).dialog("close"); 
							ajax_130_1();//移除
						}, 
					'取消': function() { 
							$(this).dialog("close"); 
						} 
				}
			});
			$('#message_confirm').dialog("open"); 
		}
	}
	
	jQuery(function(){
		
		$('#checkbox001').change(function(){
			$(':checkbox[name=checkbox001]').change();
		});
		
		$('#checkbox002').change(function(){
			$(':checkbox[name=checkbox002]').change();
		});
		
		//院校相关级联
		jQuery('#remitteeId').change(function(){
			ajax_110_1();//招生批次	
								
		});	
				
		//初始化弹出框				
		//信息提示
		jQuery('#message_returns_tips').dialog({
			autoOpen:false,
			modal:true,
			draggable:false,
			resizable:false,
			title:'信息提示',
			buttons: {
				'关闭': function() { 
					jQuery(this).dialog("close"); 
				} 
			}
		});	
		//不符合的缴费单
		jQuery('#show_for_feepaymentdetail').dialog({
			autoOpen:false,
			modal:true,
			draggable:false,
			resizable:false,
			title:'缴费单明细',
			width: 650,
			buttons: {
				'关闭': function() { 
					jQuery(this).dialog("close"); 
				} 
			}
		});	
		
		//$('#myform').get(0).reset();
	});
	
		//ajax回调函数  招生批次(院校、全局批次)
		function ajax_batch(data)
		{				
			$('#batchId').empty();
			$('#batchId').append('<option value="0">--请选择--</option>');	
			if(data.batchlist!=null&&data.batchlist.length>0)
			{
				$.each(data.batchlist,function(){	
					$('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				});
			}	
		}
	
		//ajax回调函数  选取缴费单明细时的验证
		function ajax_test_fpd(data)
		{				
			if(data.isback)
			{
				jQuery("#feepdIds").val(data.newFeepdIds);
				//alert(jQuery("#feepdIds").val());
				var paysum = $('#paysum').text();
				paysum = parseFloat(paysum);
				$('#paysum').text(paysum+parseFloat(data.allRebateMoney));	
				search003();
				search001();
			}
			else
			{
				//已经存在的数据
				$('#hasfpdtab > tbody').empty();
			   	var haslist='';
				if(data.hasFpdList!=null && data.hasFpdList.length>0)
				{
					$.each(data.hasFpdList,function(){	
						haslist+='<tr>';
						haslist+='<td align="center">'+this.paymentCode+'</td>';
						haslist+='<td align="center">'+this.studentName+'</td>';
						haslist+='<td align="center">'+this.paymentSubjectName+'</td>';
						haslist+='<td align="center">'+this.moneyToPay+'</td>';
						haslist+='</tr>';
					});
				}
				else
				{
					haslist+='<tr><td align="center" colspan="4">无相关数据！</td></tr>';
				}
				$('#hasfpdtab > tbody').html(haslist);
				
				//无相关政策的数据
				$('#nopolicefpdtab > tbody').empty();
			   	var noplist='';
				if(data.notPoliceFpdList!=null && data.notPoliceFpdList.length>0)
				{
					$.each(data.notPoliceFpdList,function(){	
						noplist+='<tr>';
						noplist+='<td align="center">'+this.paymentCode+'</td>';
						noplist+='<td align="center">'+this.studentName+'</td>';
						noplist+='<td align="center">'+this.paymentSubjectName+'</td>';
						noplist+='<td align="center">'+this.moneyToPay+'</td>';
						noplist+='</tr>';
					});
				}
				else
				{
					noplist+='<tr><td align="center" colspan="4">无相关数据！</td></tr>';
				}
				$('#nopolicefpdtab > tbody').html(noplist);
				
				jQuery("#feepdIds").val(data.newFeepdIds);
				//alert(jQuery("#feepdIds").val());
				var paysum = $('#paysum').text();
				paysum = parseFloat(paysum);
				$('#paysum').text(paysum+parseFloat(data.allRebateMoney));
				search003();
				search001();
				jQuery('#show_for_feepaymentdetail').dialog('open');
			}		
		}
		//ajax回调函数  移除缴费单明细
		function ajax_del_fpd(data)
		{
			if(data.isfail)
			{
				jQuery("#feepdIds").val(data.newdelFeepdIds);			
				$('#paysum').text(data.alldelRebateMoney);
				search003();
				search001();
			}
			else
			{
				jQuery("#showDialog").html('<b>移除失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
		
		//添加院校返款
		function ajax_pac(data)
		{
			
			if(data.isback)
			{
				jQuery("#indexcount").val(1);
				jQuery("#showDialog").html('<b>添加成功！</b>');
				jQuery('#message_returns_tips').dialog('open');			
				
			}
			else
			{
				jQuery("#showDialog").html('<b>添加失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
	</script>
	<!--招生批次(院校)-->
	<a:ajax 
		successCallbackFunctions="ajax_batch" 
		parameters="{academyId:jQuery('#remitteeId').val()}" 
		urls="/enrollment/cascade_academy_batch_all_ajax" 
		pluginCode="110"
	/>
	
	<!--选取缴费单明细时的验证-->
	<a:ajax 
		successCallbackFunctions="ajax_test_fpd" 
		parameters="{oldFeepdIds:jQuery('#feepdIds').val(),addFeepdIds:getCheckedValues001()}" 
		urls="/finance/payacademycedu/test_feepaymentdetail_for_academy_rebate_ajax" 
		pluginCode="120"
	/>
	
	<!--移除缴费单明细-->
	<a:ajax 
		successCallbackFunctions="ajax_del_fpd" 
		parameters="{allFeepdIds:jQuery('#feepdIds').val(),delFeepdIds:getCheckedValues003(),allmoney:$('#paysum').text()}" 
		urls="/finance/payacademycedu/del_feepaymentdetail_for_academy_rebate_ajax" 
		pluginCode="130"
	/>
	
	<!--添加院校返款-->
	<a:ajax 
		successCallbackFunctions="ajax_pac" 
		parameters="jQuery('#myform').serializeObject()" 
		urls="/finance/payacademycedu/add_academy_rebate_cedu_ajax" 
		pluginCode="140"
	/>
		
</head>
<body>
	<head:head title="院校返款">
		<html:a text="关闭" icon="add" onclick="window.close();" target="_self"/>
	</head:head>
	<body:body>
		<form id="myform" method="post">
		
			<input type="hidden" name="feepdIds" id="feepdIds" value=""/>
			<input type="hidden" id="planedAcademyBillIds" name="planedAcademyBillIds" value=""/>
			<input type="hidden" id="amount" name="payAcademyCedu.amount" value="0"/>
			<input type="hidden" id="notes" name="payAcademyCedu.note" value=""/>
			<input type="hidden" name="indexcount" id="indexcount" value="0"/>
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">缴费单明细查询</th>
					<th></th>
				</tr>
			</table>
					
			<table class="add_table">
			  <tbody id="rebateCondition">
				<tr>
					<td class="lable_100">收款单位：</td>
					<td>
						<s:property value="cedu.name"/>
					</td>
					<td class="lable_100"><span>*</span>返款院校：</td>
					<td>
						<s:select list="academies" listKey="id" listValue="name"
						headerKey="0" headerValue="--请选择 --"
						id="remitteeId" name="remitteeId" cssClass="txt_box_150"></s:select>
						<input type="hidden" name="payAcademyCedu.remitterId" id="academyId" value="0"/>
					</td>
					<td class="lable_100"><span>*</span>招生批次：</td>
					<td>
						<select name="batchId" id="batchId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>	
						<input type="hidden" name="obatchId" id="obatchId" value="0"/>
					</td>
				</tr>
				<tr>	
					<td class="lable_100">学习中心：</td>
					<td>
						<s:if test="%{branchlist!=null}">
							<s:select list="%{branchlist}" headerKey="0" headerValue="--请选择--" listKey="id" theme="simple" listValue="name" name="branchId" id="branchId" cssClass="txt_box_150"/>
						</s:if>
						<s:else>
						<select name="branchId" id="branchId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>
						</s:else>			
					</td>
					<td></td>
					<td></td>
					<td class="lable_100"></td>
					<td>
						<input type="button" onclick="findfeepayment()" class="btn_black_61" value="查询"/>	
					</td>
				</tr>
			  </tbody>
			 </table>
			</form> 
			<div id="rebateContent" style="display:none;" >
			 <table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">待返款缴费单明细</th>
					<th></th>
				</tr>
			</table>
			 
					<page:plugin 
						pluginCode="001"
						il8nName="finance_payment"
						subStringLength="20"
						searchListActionpath="list_academy_rebate_feepaymentdetail_ajax"
						searchCountActionpath="count_academy_rebate_feepaymentdetail_ajax"
						columnsStr="paymentCode;code;schoolName;branchName;academyenrollbatchName;levelName;majorName;studentName;paymentSubjectName;moneyToPay;#status"
						customColumnValue="10,showstatus(status)"
						isPage="true"
						isChecked="true"
						checkboxValue="id"
						params="academyId:$('#academyId').val(),batchId:$('#obatchId').val(),branchId:$('#branchId').val(),feepdIds:$('#feepdIds').val()"
						isonLoad="false"
						isPackage="true"
						isOrder="false"
					/>
				 
			<table class="add_table">
			  <tbody >
				<tr>
					<td align="left">
						(确认选择需要返款的缴费单明细)
						<input type="button" class="btn_black_61" value="选择" onclick="selectfpd()"/>
						
					</td>
				</tr>
				</tbody>
			</table>
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">现需返款缴费单明细</th>
					<th></th>
				</tr>
			</table>
					<page:plugin 
						pluginCode="003"
						il8nName="finance_payment"
						subStringLength="20"
						searchListActionpath="list_academy_rebate_feepaymentdetail_for_now_ajax"
						searchCountActionpath="count_academy_rebate_feepaymentdetail_for_now_ajax"
						columnsStr="paymentCode;code;schoolName;branchName;academyenrollbatchName;levelName;majorName;studentName;paymentSubjectName;moneyToPay;payAcademyCedu;#status"
						customColumnValue="11,showstatus(status)"
						isPage="true"
						isChecked="true"
						checkboxValue="id"
						params="'feepdIds':$('#feepdIds').val()"
						isonLoad="true"
						isPackage="true"
						isOrder="false"
					/>
				<table class="add_table">
				  <tbody >
					<tr>
						<td align="left">
							(确认移除不需要返款的缴费单明细)
							<input type="button" class="btn_black_61" value="移除" onclick="deletefpd()"/>
							
						</td>
					</tr>
					</tbody>
				</table>
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">其他应收学校款</th>
					<th></th>
				</tr>
			</table>
				<page:plugin
						pluginCode="002"
						il8nName="finance"
						subStringLength="20"
						searchListActionpath="/finance/academybill/list_academy_bill_data"
						columnsStr="academyName;receivedWayName;#billamount;note;#status;#attachment"
						customColumnValue="2,billAmount(amount);4,toBillStatus(isRebate);5,buildAttachment(id)"
						params="academyId:$('#academyId').val(),status:PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO"
						listCallback="processCheckbox"
						isChecked="true"
						isPage="false"
						isonLoad="false"
						isPackage="false"
						isOrder="false"
					/>
			<table class="add_table">
			 <tbody>	 
				<tr>
					<td class="lable_100">应返款额度：</td>
					<td><span id="paysum">0</span></td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td class="lable_100">备注：</td>
					<td colspan="7"><textarea cols="60" rows="6" id="note" style="height:80px"></textarea></td>
				</tr>
				<tr>
					<td colspan="8" align="center"><input type="button" class="btn_black_61" value="保存" onclick="doSave()"/>&nbsp;&nbsp;<input type="button" class="btn_black_61" onclick="javascript:window.close();" value="关闭"/></td>
				</tr>
			  </tbody>
			</table>
		</div>	
	</body:body>
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
			
		</div>
	</div>
	<!--弹出层    确认操作-->
	<div id="message_confirm" style="display:none">
		<div align="center" >
			<b>确认移除选中的缴费单明细？</b>
		</div>
	</div>
	<div id="show_for_feepaymentdetail" style="display:none">
		<table class="gv_table_2">
			<tbody>
				<tr>
					<td style="width: 50%;" valign="top">
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
								<th style="text-align:left; font-weight:bold;" class="feehtml">无院校返款政策的缴费单明细</th>
							</tr>
						</table>				  
						<table class="gv_table_2" id="nopolicefpdtab">
							<thead>
								<tr>
									<th align="center">缴费单编号</th>
									<th align="center">学生姓名</th>
									<th align="center">费用科目</th>
									<th align="center">应缴金额  </th>
								</tr>
							</thead>					
							<tbody>	
											
							</tbody>						
						</table>
					</td>
					<td style="width: 50%;" valign="top">
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
								<th style="text-align:left; font-weight:bold;" class="feehtml">已选择过的缴费单明细</th>
							</tr>
						</table>				  
						<table class="gv_table_2" id="hasfpdtab">
							<thead>
								<tr>
									<th align="center">缴费单编号</th>
									<th align="center">学生姓名</th>
									<th align="center">费用科目</th>
									<th align="center">应缴金额  </th>
								</tr>
							</thead>					
							<tbody>						
							</tbody>						
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</div>	
</body>
</html>
