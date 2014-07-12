<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生优惠标准设置</title>
		
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
		
		<script type="text/javascript">

			//加载事件
			jQuery(function(){
			
				showfeeway();//缴费次数下拉框			
				$('#academyId').append('<option value="-1">无院校(报名前)</option>');
			});
			//缴费次数下拉框
			function showfeeway()
			{
				$('#feePaymentId').empty();
			    $('#feePaymentId').append('<option value="0">--请选择--</option>');
			    $('#feePaymentId').append('<option value="1">第1次缴费</option>');
			    $('#feePaymentId').append('<option value="2">第2次缴费</option>');
			    $('#feePaymentId').append('<option value="3">第3次缴费</option>');
			    $('#feePaymentId').append('<option value="4">第4次缴费</option>');
			    $('#feePaymentId').append('<option value="5">第5次缴费</option>');
			}
			
			/////////      /分页列表/    /////////////////
			
			//列表自定义字段显示
			//显示缴费次数
			function showpayment(feePaymentId,indexcount,id)
			{
				if(indexcount>0)
				{
					isPageOperating(id,"007","update");
				}
				return "第"+feePaymentId+"次缴费";
			}
			//优惠方式
			function showdiscout(discountWayId)
			{
				if(parseInt(discountWayId)==MONEY_FORM_RATIO)
				{
					return "比例";
				}
				else
				{
					return "金额";
				}
			}
			//优惠主体
			function showtarget(targetObjectId)
			{
				if(parseInt(targetObjectId)==POLICY_TARGET_OBJECT_CEDU)
				{
					return "弘成优惠";
				}
				else if (parseInt(targetObjectId)==POLICY_TARGET_OBJECT_ACADEMY)
				{
					return "院校优惠";
				}
				else if (parseInt(targetObjectId)==POLICY_TARGET_OBJECT_BRANCH)
				{
					return "中心优惠";
				}
				else
				{
					return "渠道优惠";
				}
			}
			//优惠标准
			function showstandard(discountstandard)
			{
				return discountstandard;
			}
		</script>
	</head>
  
  <body>

    <!-- 头开始 -->
		<head:head title="学生优惠标准设置">
			<html:a text="新增学生优惠标准" icon="add" href="/enrollment/add_student_discount_standard"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">学生优惠标准</th>
						
					</tr>
				</table>
	
				<table class="add_table">
					<tr>
		                <td>院校：</td>
		                <td align="left">
		                	<s:if test="%{academylist!=null}">
								<s:select list="%{academylist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="academyId" id="academyId" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="academyId" id="academyId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>	
						</td>
		            
						
		                <td>费用科目：</td>
		                <td align="left">
		                	<s:if test="%{feesubjectlist!=null}">
		                		<s:select list="%{feesubjectlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="feesubjectId" id="feesubjectId" cssClass="txt_box_150"/>
		                	</s:if>
		                	<s:else>
		                		<select name="feesubjectId" id="feesubjectId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
		                	</s:else>							
						</td>
						
						<td>缴费次数：</td>
		                <td align="left">
		                	<select name="feePaymentId" id="feePaymentId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						
						</td>
						
						<td></td>
		                <td align="left">
							<input type="button" class="btn_black_61"  onclick="search007();" value="查询"/>
						</td>
	             	</tr>
				</table>
				
				<page:plugin 
						pluginCode="007"
						il8nName="enrollment"
						searchListActionpath="list_student_discount_policy_ajax"
						searchCountActionpath="count_student_discount_policy_ajax"
						columnsStr="academyname;title;feesubjectname;#feepayment;#discountway;#targetobject;discountstandard"
						customColumnValue="3,showpayment(feePaymentId,indexcount,id);4,showdiscout(discountWayId);5,showtarget(targetObjectId);6,showstandard(discountstandard)"
						pageSize="5"
						isPage="true"
						isNumber="false"
						isChecked="false"
						update="http,/enrollment/modify_student_discount_standard,id,id,_self"
						view="http,/enrollment/view_student_discount_standard,id,id,_blank"								
						params="'studentDiscountPolicy.academyId':$('#academyId').val(),'studentDiscountPolicy.feeSubjectId':$('#feesubjectId').val(),'studentDiscountPolicy.feePaymentId':$('#feePaymentId').val()"
					/>
	
		</body:body>
	
  </body>
</html>
