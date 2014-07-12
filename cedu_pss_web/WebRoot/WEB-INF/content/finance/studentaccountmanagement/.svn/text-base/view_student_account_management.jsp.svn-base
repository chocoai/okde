<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>学生账户详情</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!--  分页 -->
		<jc:plugin name="page" />

		
		<script type="text/javascript">
		var type=STATUS_RECHARGE;
		//账户充值
		function ajax_add(data)
		{
			closes('AccountRechargeDiv');
			show('successDiv','提示',250,150);
			window.location.reload();
		}
		
		//账户充值
		function ajax_feesubject(data)
		{
			var lists='';
			$.each(data.fslist,function(){
				lists+='<option value="'+this.id+'">'+this.name+'</option>';
			});
			$('#feeSubjectId').html("");
			$('#feeSubjectId').html(lists);
		}
		</script>
		
		<a:ajax parameters="{'accountId':$('#id').val(),'accountRecharge':$('#accountRecharge').val(),'feeSubjectId':$('#feeSubjectId').val(),'description':$('#description').val(),'types':type,'studentId':jQuery('#studentId').val()};null"
				successCallbackFunctions="ajax_add;ajax_feesubject"
				urls="/finance/studentaccountmanagement/studentaccountrecharge;/finance/studentaccountmanagement/listfeesubjects"
		 pluginCode="123"
		  />

		<script type="text/javascript">

		function AccountRecharge()
		{
			ajax_123_2();
			show('AccountRechargeDiv','提示',500,300);
		}
		
		function addAccountRecharge()
		{
			var accountRecharge=$.trim($('#accountRecharge').val());
			if(accountRecharge=="")
			{
				
				show('rechargeDiv','提示',250,150);
				return;
			}
			if(isNaN(accountRecharge))
			{
				show('rechargeNumDiv','提示',250,150);
				return;
			}
			ajax_123_1();

		}
		
		function typesValue(types)
		{
			return types==STATUS_AUTHOR_FALSE?"充值":"消费";
		}
		

		</script>
	
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="学生账户详情">
			<html:a text="账户充值" onclick="AccountRecharge()" target="" icon="add"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">学生账户详情</th>	
				</tr>
			</table>
	 
			<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
					   <td class="lable_100">学习中心：</td>
					     <td>
					     	${sam.branchName} 
					     </td>	
					</tr>  	
					<tr>
					   <td class="lable_100">院校：</td>
					     <td>
					     	${sam.academyName} 
					     </td>	
					</tr>  
					<tr>
					   <td class="lable_100">批次：</td>
					     <td>
					     	${sam.batchName} 
					     </td>	
					</tr>  
					<tr>
					   <td class="lable_100">层次：</td>
					     <td>
					     	${sam.levelName} 
					     </td>	
					</tr>  
					<tr>
					   <td class="lable_100">专业：</td>
					     <td>
					     	${sam.majorName} 
					     </td>	
					</tr>  
				   <tr>
					   <td class="lable_100">学生账号：</td>
					     <td>
					     	${sam.code} 
					     </td>	
					</tr>  
					<tr>   
					   <td class="lable_100">学生姓名：</td>
					     <td>
					     	${sam.studentName} 
					     	<input type="hidden" name="studentId" id="studentId" value="${sam.studentId}"/>
					     </td>	
					</tr>  
					<tr>         
					    <td class="lable_100">账户余额：</td>
					     <td>
					     		${sam.accountBalance} 
					     </td>	  
					 </tr>
				</table>
	

			<!--Menu Begin-->
			<%@ include file="../_tab_title/student_account_amount_management_tab.jsp" %>
			<!--Menu End-->
		
		<page:plugin 
				pluginCode="123"
				il8nName="finance_payment"
				searchListActionpath="liststudentaccountamountmanagement"
				searchCountActionpath="countstudentaccountamountmanagement"
				columnsStr="accountMoney;types;feeSubjectName;createdTime;description;"
				customColumnValue="1,typesValue(types)"
				isPage="true"
				pageSize="10"
				ontherOperatingWidth="80px"	
				params="'accountId':'${param.id}','types':'${param.tab}'"
		/>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<div id="AccountRechargeDiv"  style="display:none">
		<form>
			<input type="hidden" id="id" name="id" value="${sam.id}" />
			<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
				   <tr>
					   <td class="lable_100">学生账号：</td>
					     <td>
					     	${sam.code} 
					     </td>	
					</tr>  
					<tr>   
					   <td class="lable_100">学生姓名：</td>
					     <td>
					     	${sam.studentName} 
					     </td>	
					</tr>  
					<tr>         
					    <td class="lable_100">充值金额：</td>
					     <td>
					     	<input type="text" id="accountRecharge" name="accountRecharge" class="txt_box_100"  />
					     </td>	  
					 </tr>
					 
					 <tr>         
					    <td class="lable_100">费用科目：</td>
					     <td>
					     		<select id="feeSubjectId" name="feeSubjectId" class="txt_box_100">	
								</select>
					     </td>	  
					 </tr>
					 
					 <tr>
					<td class="lable_100">备注：</td>
					<td><textarea name="description" id="description" cols="45" rows="5" class="txt_box_300" ></textarea></td>
					</tr>
					
					 <tr>
					<td class="lable_100"></td>
					<td>
					<input type="button" value="保存" id="btnok" name="btnok" onclick="addAccountRecharge()" class="btn_black_61"/> 
					<input type="reset" value="重置" id="tt" name="tt"  class="btn_black_61"/> 
					</td>
					</tr>
				</table>
				</form>
	
	</div>
	
	<div id="rechargeDiv" style="display:none">
			充值金额不能为空
	</div>		
		
	<div id="rechargeNumDiv" style="display:none">
			充值金额只能为数字
	</div>	
	
	<div id="successDiv" style="display:none">
			充值成功！！
	</div>	
	
   </body:body>
	
	
  </body>
</html>
