<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生直接充值记录</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<script type="text/javascript">
			//统计缴费单金额 
			function countallmoney(data)
			{
				//alert(data.allFeePaymentMoney);
				jQuery("#moneyall").html("合计：<font style='color:red'><b>"+data.allStudentSimpleMoney+"</b></font>元");
			}
		</script>
		<!-- 统计缴费单金额 -->
		<a:ajax 
			parameters="jQuery('#myform').serializeObject()" 
			successCallbackFunctions="countallmoney" 
			pluginCode="1110" 
			urls="finance/studentaccountmanagement/count_student_simple_account_all_money_ajax"
		/>
		<script type="text/javascript">
			
			
			$(document).ready(function(){
				
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
				search001();
			}
			//显示列表金额
			function feePaymentValue(accountMoney){
				return (accountMoney+"").toMoney();
			}
			//显示充值时间
			function showtime(createdTime)
			{
				if(createdTime.length>10)
				{
					return  createdTime.substring(0,10);
				}
				return createdTime;
			}
			function countCallback001(data){
				ajax_1110_1();//统计缴费单金额
			}
			
			//ajax回调函数   学生共有的查询条件
				function ajax_stusearchallajax(data)
				{
					//院校
					jQuery("#academyId").empty();
				    jQuery("#academyId").append('<option value="0">--请选择--</option>');
				    if(data.academyList!=null && data.academyList.length>0)
				    {
				    	$.each(data.academyList,function(){	
				    		jQuery("#academyId").append('<option value="'+this.id+'">'+this.name+'</option>'); 
				    	});
				   	}
				   	//全局批次
				   	jQuery("#glopici").empty();
				    jQuery("#glopici").append('<option value="0">--请选择--</option>');
				    if(data.globalBatchList!=null && data.globalBatchList.length>0)
				    {
				    	$.each(data.globalBatchList,function(){	
				    		jQuery("#glopici").append('<option value="'+this.id+'">'+this.title+'</option>'); 
				    	});
				   	}
				   	//层次
				   	jQuery("#cengci").empty();
				    jQuery("#cengci").append('<option value="0">--请选择--</option>');
				    if(data.levelList!=null && data.levelList.length>0)
				    {
				    	$.each(data.levelList,function(){	
				    		jQuery("#cengci").append('<option value="'+this.id+'">'+this.name+'</option>'); 
				    	});
				   	}
				   	//基础专业
				   	jQuery("#jczhuanye").empty();
				    jQuery("#jczhuanye").append('<option value="0">--请选择--</option>');
				    if(data.baseMajorList!=null && data.baseMajorList.length>0)
				    {
				    	$.each(data.baseMajorList,function(){	
				    		jQuery("#jczhuanye").append('<option value="'+this.id+'">'+this.name+'</option>'); 
				    	});
				   	}
				}	
				//ajax回调函数   学习中心
				function ajax_stubranchajax(data)
				{
					// 学习中心
					jQuery("#branchId").empty();
				    jQuery("#branchId").append('<option value="0">--请选择--</option>');
				    if(data.branchList!=null && data.branchList.length>0)
				    {
				    	$.each(data.branchList,function(){	
				    		jQuery("#branchId").append('<option value="'+this.id+'">'+this.name+'</option>'); 
				    	});
				   	}
				}
		</script>		
			<!-- 学习中心-->
			<a:ajax 
				pluginCode="110"
				successCallbackFunctions="ajax_stubranchajax" 
				urls="/crm/branch_all_list_for_stu_search_ajax"
				isOnload="all" 			
			/>
			<!-- 学生共有的查询条件-->
			<a:ajax 
				pluginCode="120"
				successCallbackFunctions="ajax_stusearchallajax" 
				urls="/crm/for_stu_recharge_search_half_ajax"
				isOnload="all" 			
			/>
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="学生直接充值查询">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">学生直接充值查询</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">
					<table class="add_table" border="0">
						<tr>
							  <td align="right">学习中心：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.branchId" id="branchId">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>	
							  <td align="right">院校：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.academyId" id="academyId">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
							  <td align="right">全局批次：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.glbtach" id="glopici">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
							  
							  
						</tr>
						<tr>	
							<td align="right">层次：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.levelId" id="cengci">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
							<td align="right">基础专业：</td>
							  <td align="left">
							  	<select class="txt_box_130" name="student.glmajor" id="jczhuanye">
							  		<option value="0">--请选择--</option>
							  	</select>
							  </td>
							<td align="right">姓名：</td>
								<td align="left">
									<input  name="student.name" class="txt_box_130" type="text" value="" />
								</td>
		             	</tr>
		             	<tr>
		             		<td align="right">证件号：</td>
							<td align="left">
								<input  name="student.certNo" class="txt_box_130" type="text" />
							</td>	  	
		             		<td align="right">缴费日期起：</td>
			                <td align="left">
								<input type="text" name="starttime" id="starttime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'endtime\',{d:-1});}'})" readonly="readonly"/>					  			
							</td>
			                <td align="right">缴费日期止：</td>
			                <td align="left">
								<input type="text" name="endtime" id="endtime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'starttime\',{d:1});}'})" readonly="readonly"/>
							</td>	
						</tr>
						<tr>	
							<td colspan="5"></td>
			                <td align="left">
								<input type="button" class="btn_black_61"  onclick="showsearch();" value="查询"/>
							</td>
		             	</tr>
					</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="finance_payment"
										searchCountActionpath="finance/studentaccountmanagement/count_student_simple_account_ajax"
										searchListActionpath="finance/studentaccountmanagement/list_student_simple_account_ajax"
										columnsStr="createdTime;studentName;branchName;schoolName;academyenrollbatchName;levelName;majorName;feeSubjectName;accountMoney"
										customColumnValue="0,showtime(createdTime);8,feePaymentValue(accountMoney)"
										pageSize="10"
										isPackage="false"
										params="'result.order':'createdTime','result.sort':'desc'"
										searchFormId="myform"
										customToolbarID="moneyall"
										listCallback="countCallback"
									/>
				
		</body:body>
		<!--  弹出层           -->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
  </body>
</html>
