<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生强制收费调整</title>
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
		
		<%@ include file="common.jsp" %>
		<script type="text/javascript">
		
			//加载事件
			jQuery(function(){
				//学习中心改变事件
				jQuery('#branchId').change(function(){
					ajax_100_1();//全局批次					
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
				//审批
				$('#show_for_academy').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'设置结果',
					width: 400,
					buttons: {
						'确定': function() { 
							ajax_160_1();//设置
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
				});	
				
			});
			function operatingValue(sid)
			{
				return "<a href='javascript:updateforce("+sid+");'>设置</a>";				
			}
			function showforce(op)
			{
				if(op==STUDENT_IS_EXEMPTION_FALSE)
				{
					return "强制收费";
				}
				else
				{
					return "非强制"
				}
			}
			
			//审批优惠卷
			function updateforce(id)
			{
				studentId=id;
				jQuery('#show_for_academy').dialog("open");
			}
			//ajax回调函数  审批已申请的学生优惠
			var studentId=0;
			function ajax_auditdiscount(data)
			{			
				jQuery('#show_for_academy').dialog("close");	
			    jQuery("#showDialog").html('<b>设置成功！</b>');
				jQuery('#message_returns_tips').dialog("open");
				//search001();//刷新列表		 
				refresh001();
			}
		</script>
		<!--设置学生强制收费-->
		<a:ajax 
			successCallbackFunctions="ajax_auditdiscount" 
			parameters="{studentId:studentId,isForceFee:jQuery(\"input[name='confrimrad']:checked\").val()}" 
			urls="/finance/payment/update_student_force_fee_ajax" 
			pluginCode="160"
		/>
	</head>
  <body>
	
	<!-- 头开始 -->
		<head:head title="学生强制收费调整">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">学生强制收费调整</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">
				<table class="add_table">
					<tr>
						<td>学习中心：</td>
		                <td align="left">
		                	
		                	<s:if test="%{branchlist!=null}">
								<s:select list="%{branchlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="student.branchId" id="branchId" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="student.branchId" id="branchId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>
							<!-- ${branch.name}
							<input type="hidden" id="branchId" name="student.branchId" value="${branch.id}"/>-->
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
						<td>证件号：</td>
		                <td align="left">
		                	<input type="text" name="student.certNo" id="certNo" class="txt_box_150"/>					
						</td>
					</tr>
					<tr>	
						<td colspan="7"></td>
		                <td align="left" >
							<input type="button" class="btn_black_61"  onclick="search001();" value="查询"/>
						</td>
	             	</tr>
				</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="crm"
										searchListActionpath="list_student_payment_main_ajax"
										searchCountActionpath="count_student_payment_main_ajax"
										columnsStr="branchName;name;certNo;schoolName;academyenrollbatchName;levelName;majorName;status;#public.operating"
										customColumnValue="7,showforce(isForceFee);8,operatingValue(id)"
										pageSize="10"
										params="'student.gender':-1,'student.academyIds':'${academyIds}'"
										searchFormId="myform"
									/>
				
		</body:body>
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
	<div id="show_for_academy" style="display:none">
		<div align="center">
			<input type="radio" name="confrimrad" checked="checked" value="<%=Constants.STUDENT_IS_EXEMPTION_FALSE %>"/>强制收费
			<input type="radio" name="confrimrad" value="<%=Constants.STUDENT_IS_EXEMPTION_TRUE %>"/>非强制收费
		</div>
		<div align="center" id="">
		<span>备注：</span>只允许对设置为强制收费院校的学生进行强制与非强制的切换。
		</div>
	</div>
  </body>
</html>
