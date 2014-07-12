<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>院校打款确认(总部)</title>
		
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
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		
		<script type="text/javascript">

			//加载事件
			jQuery(function(){
				
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
				
			});
			
			//查询数据
			function showsearch()
			{
				//alert(jQuery("#amount").val());
				if(jQuery("#amount").val()!="" && dealwithmoney(jQuery("#amount").val())==-1)
				{
					jQuery("#showDialog").html('<b>查询条件“汇款金额”输入格式不正确，只能输入整数和小数！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else
				{
					if(jQuery("#amount").val()!="")
					{
						jQuery("#amount").val(dealwithmoney(jQuery("#amount").val()));
					}
					search601();
				}
			}
			
			//分页列表
			//操作
			function comoperated(id,status,types)
			{
				var str="";
				if(status==PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN && types==FALLBACK_TYPES_SUBMIT)
				{
					str='<a href="javascript:ceduconfirm('+id+');">总部确认</a>&nbsp;&nbsp;<a href="javascript:fallBack('+id+')">回退</a>';
				}
				return str;
				
			}
			//状态
			function showstatus(status)
			{
				return status.getPaymentStatus();
			}
			//显示回退状态
			function showtypes(types)
			{
				if(types==FALLBACK_TYPES_SUBMIT)
				{
					return '已提交';
				}
				else if(types==FALLBACK_TYPES_ROLLED_BACK)
				{
					return '<span style="color:red">已回退</span>';
				}
				return '--';
					
			}
			//删除打款单
			function deleteFun(id)
			{
				alert("删除ajax");
			}
			//回退
			function fallBack(id)
			{
				$('#message_fall_back').dialog({
					title:'回退操作',
					buttons: {
						'确认': function() { 
								pcaId=id;
								ajax_110_1();//更新打款单回退状态	
								$(this).dialog("close");				
							}, 
						'取消': function() { 
								$(this).dialog("close"); 
							} 
						}
				});
				$('#message_fall_back').dialog("open");
			}
			//总部确认
			function ceduconfirm(id)
			{
				$('#message_confirm').dialog({
					title:'确认操作',
					buttons: {
						'确认': function() { 
								payCeduAcademyId=id;
								ajax_100_1();//更新打款单状态	
								$(this).dialog("close");				
							}, 
						'取消': function() { 
								$(this).dialog("close"); 
							} 
						}
				});
				$('#message_confirm').dialog("open");
			}
			
			//ajax回调函数   
			var payCeduAcademyId=0;
			var payCeduAcademyStatus=PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN;
			function ajax_updatestatus(data)
			{
				if(data.isfail)
				{
					//search601();
					jQuery("#showDialog").html('<b>操作成功！</b>');
					jQuery('#message_returns_tips').dialog("open");	
				}
				else
				{
					jQuery("#showDialog").html('<b>操作失败！</b>');
					jQuery('#message_returns_tips').dialog("open");	
				}
				refresh601();	
			}
			//ajax 回调函数     回退状态
			var pcaId=0;
			var pcaTypes=FALLBACK_TYPES_ROLLED_BACK;
			function ajax_updatetypes(data)
			{
				if(data.isback)
				{
					//search601();
					jQuery("#showDialog").html('<b>操作成功！</b>');
					jQuery('#message_returns_tips').dialog("open");	
				}	
				else
				{
					jQuery("#showDialog").html('<b>操作失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				refresh601();
			}
			//统计院校打款单金额 
			function countallmoney(data)
			{
				//alert(data.allFeePaymentMoney);
				jQuery("#moneyall").html("合计：<font style='color:red'><b>"+data.allplaymoney+"</b></font>元");
			}
			
			//统计金额
			function countCallback601(data)
			{
				ajax_1110_1();//统计汇款单金额
			}
		</script>
		<!--更新打款单状态-->
		<a:ajax 
			pluginCode="100"
			parameters="{payCeduAcademyId:payCeduAcademyId,payCeduAcademyStatus:payCeduAcademyStatus}" 
			successCallbackFunctions="ajax_updatestatus"
			urls="finance/playmoney/update_cedu_palymoney_academy_ajax"
		/>
		<!--更新汇款单回退状态-->
		<a:ajax 
			pluginCode="110"
			parameters="{pcaId:pcaId,pcaTypes:pcaTypes}" 
			successCallbackFunctions="ajax_updatetypes"
			urls="finance/playmoney/update_pay_cedu_academy_types_ajax"
		/>
		<!-- 统计院校打款单金额 -->
		<a:ajax 
			parameters="jQuery('#myform').serializeObject()" 
			successCallbackFunctions="countallmoney" 
			pluginCode="1110" 
			urls="finance/playmoney/count_pay_cedu_academy_all_money_ajax"
		/>
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="院校打款确认(总部)">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">院校打款确认(总部)</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">
				<table class="add_table">
					<tr>
						<td>汇款单位：</td>
		                <td align="left">
							${branch.name}
							<input type="hidden" id="branchId" name="payCeduAcademy.remitterId" value="${branch.id}"/>
						</td>
		                
		                <td>收款单位：</td>
		                <td align="left">
		                	<s:if test="%{academyList!=null}">
								<s:select list="%{academyList}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="payCeduAcademy.remitteeId" id="academyId" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="payCeduAcademy.remitteeId" id="academyId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>	
		                	</s:else>	
		                	
						</td>
						<td>汇款单号：</td>
		                <td align="left">
		                	<input type="text" name="payCeduAcademy.remittanceNo" id="remittanceNo" class="txt_box_150"/>					
						</td>
		               
	              	</tr>
	              	<tr>	              		
						<td>汇款账号：</td>
		                <td align="left">
		                	<input type="text" name="payCeduAcademy.remitterAccount" id="remitterAccount" class="txt_box_150"/>					
						</td>
						<td>收款账号：</td>
		                <td align="left">
		                	<input type="text" name="payCeduAcademy.remitteeAccount" id="remitteeAccount" class="txt_box_150"/>					
						</td>
						<td>汇款单状态：</td>
		                <td align="left">
		                	<select id="status" name="payCeduAcademy.status" class="txt_box_150">
								<option value="0">--请选择--</option>
								<option selected="selected" value="<%=Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN %>">已填打款单</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN %>">已确认待汇款</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO %>">已打款院校</option>
							</select>	
											
						</td>
	              	</tr>
				  	<tr>
				  		<td >汇款金额：</td>
		                <td align="left">
							<input type="text" name="amount" id="amount" class="txt_box_150" />
						</td>
				  		<td>汇款日期起：</td>
		                <td>
							<input type="text" name="starttime" id="starttime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'endtime\',{d:0});}'})" readonly="readonly"/>					  			
						</td>
		                <td>汇款日期止：</td>
		                <td align="left">
							<input type="text" name="endtime" id="endtime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'starttime\',{d:0});}'})" readonly="readonly"/>
						</td>
					</tr>
					<tr>	
						<td>回退状态：</td>
						<td>
							<select name="payCeduAcademy.types" class="txt_box_150">
								<option value="0">--请选择--</option>
								<option selected="selected" value="<s:property value="@net.cedu.common.Constants@FALLBACK_TYPES_SUBMIT"/>">已提交</option>
								<option value="<s:property value="@net.cedu.common.Constants@FALLBACK_TYPES_ROLLED_BACK"/>">已回退</option>
							</select>
						</td>
						<td colspan="3"></td>
		                <td align="left">
							<input type="button" class="btn_black_61"  onclick="showsearch()" value="查询"/>
						</td>
	             	</tr>
	             	
				</table>
				</form>
				<page:plugin 
						pluginCode="601"
						il8nName="finance_payment"
						searchListActionpath="list_branch_playmoney_academy_ajax"
						searchCountActionpath="count_branch_playmoney_academy_ajax"
						columnsStr="remitterName;remitteeName;remitterAccount;remitteeAccount;amount;note;remittanceNo;remittanceDate;status;#types;public.operating"
						customColumnValue="8,showstatus(status);9,showtypes(types);10,comoperated(id,status,types)"
						view="http,/finance/playmoney/view_headquarters_playmoney_academy,id,id,_blank"	
						
						params="'result.order':'id','result.sort':'desc'"					
						searchFormId="myform"
						customToolbarID="moneyall"
						listCallback="countCallback"
					/>
		</body:body>
		<!--弹出层-->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="message_confirm" style="display:none">
			<div align="center" id="showconfirm">
				<b>确认打款么？</b>
			</div>
		</div>	
		<div id="message_fall_back" style="display:none">
			<div align="center" id="showfallback">
				<b>确认回退打款单么？</b>
			</div>
		</div>
  </body>
</html>
