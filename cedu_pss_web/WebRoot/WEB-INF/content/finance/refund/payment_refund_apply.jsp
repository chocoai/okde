<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>退费申请</title>
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
		
		<%@ include file="common_jilian.jsp" %>
		<script type="text/javascript">
		
			//加载事件
			jQuery(function(){
		
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
				
			});
			function operatingValue(sid)
			{
				return "<a href='"+WEB_PATH+"/finance/refund/add_refund_payment?student.id="+sid+"' target='_blank'>退费</a>";
			}
			
		</script>
		
	</head>
  <body>
	
	<!-- 头开始 -->
		<head:head title="退费申请">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">退费申请</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">
				<table class="add_table">
					<tr>
						<td>学习中心：</td>
		                <td align="left">
							${branch.name}
							<input type="hidden" id="branchId" name="student.branchId" value="${branch.id}"/>
							<input type="hidden" id="startStatusId" name="student.startStatusId" value="<%=Constants.STU_CALL_STATUS_GENG_JIN_ZHONG %>"/>
							<input type="hidden" name="student.callStatus" value="-1"/>
						
						</td>
		                
		                <td>全局批次：</td>
		                <td align="left">
							<select name="" id="globalBatchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td>院校：</td>
		                <td align="left">
		                	<select name="student.academyId" id="academyId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>	
						</td>
		                <td>招生批次：</td>
		                <td align="left">
							<span style="color: black !important;" id="batch" name="batch"></span>
							<input type="hidden" id="batchId" name="student.enrollmentBatchId" value="0"/>
						</td>
						
	              	</tr>
				  	<tr>
				  		<td>层次：</td>
		                <td>
							<select name="student.levelId" id="levelId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td>专业：</td>
		                <td align="left">
							<select name="student.majorId" id="majorId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
						<td>姓名：</td>
		                <td align="left">
		                	<input type="text" name="student.name" id="name" class="txt_box_150"/>					
						</td>
						<td>身份证：</td>
		                <td align="left">
		                	<input type="text" name="student.certNo" id="certNo" class="txt_box_150"/>					
						</td>
						
	             	</tr>
	             	<tr>
	             		<td colspan="7"></td>
		                <td align="left">
							<input type="button" class="btn_black_61"  onclick="search001();" value="查询"/>
						</td>
	             	</tr>
				</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="/cedu/finance/payment/list_student_payment_main_ajax"
										searchCountActionpath="/cedu/finance/payment/count_student_payment_main_ajax"
										columnsStr="#public.operating;name;certNo;schoolName;academyenrollbatchName;levelName;majorName"
										customColumnValue="0,operatingValue(id)"
										pageSize="10"
										params="'student.gender':-1,'result.order':'name','result.sort':'asc'"
										searchFormId="myform"
									/>
				
		</body:body>
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
	
	<div id="show_for_prompt" style="display:none">
		<table class="add_table" cellpadding="2" cellspacing="2">
			<tr>
				<td align="right">优惠券状态</td>
				<td align="left">

					<select id="usageFlag" name="usageFlag" class="txt_box_100">
						<option value="<%=Constants.POLICY_USING_STATUS_ALL %>">--全部--</option>
						<option value="<%=Constants.POLICY_USING_STATUS_APPLY %>">已申请</option>
						<option value="<%=Constants.POLICY_USING_STATUS_FALSE %>">未使用</option>
						<option value="<%=Constants.POLICY_USING_STATUS_TRUE %>">已使用</option>
					</select>
				</td>

				<td><input type="button" onclick="showusageflag()" value="查询"/></td>
			</tr>
		</table>
		<table class="gv_table_2" id="showdiscount">
			<thead>
				<th>优惠编号</th>
				<th>费用科目</th>
				<th>有效期</th>
				<th>优惠金额/比例</th>
				<th>状态</th>
				
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>	
  </body>
</html>
