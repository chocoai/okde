<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改学生优惠政策</title>
		
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
				////////////////////////赋值\\\\\\\\\\\\\\\\\\\\\
				
				//收费标准赋值
				search007();
				
				
				$('#null_for_academy').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示',
					buttons: {
						'关闭': function() { 
							$(this).dialog("close"); 
							} 
					}
				});	
				
			});
			/////////      /ajax提交/    /////////////////		
			function adddetail()
			{
				
				if(jQuery("input[name='pfeeradio']").length==0 || jQuery("input[name='pfeeradio']:checked").length==0)
				{
					jQuery("#showDialog").html('<b>请选择政策标准！</b>');
					$('#null_for_academy').dialog("open");
				}
				else
				{
					var policyFeeId="";
					if(jQuery("input[name='pfeeradio']").length!=0 && jQuery("input[name='pfeeradio']:checked").length!=0)
					{
						jQuery("input[name='pfeeradio']").each(function(){
			            	if(jQuery(this).attr("checked")==true)
			            	{
			            		policyFeeId+=jQuery(this).val()+",";
			                }
						});
					}
					if(policyFeeId.length>0)
					{
						policyFeeId=policyFeeId.substring(0,(policyFeeId.length-1));
					}
					
					 //修改政策
			    	$.ajax({
			    		url:'<uu:url url="/enrollment/update_student_discount_detail_ajax" />',
			    		data:{discountPolicyId:policyFeeId,studentDetailId:jQuery('#studentDetailId').val()},
			    		dataType:'json',
			    	 	type:'post',
			    	 	success:function(data){
			    	 		
				    	 	if(data.backtracks)
				    	 	{
				    	 		jQuery("#showDialog").html('<b>修改政策成功！</b>');
								$('#null_for_academy').dialog("open");	   	 		
				    	 	}	
				    	 	else
				    	 	{
				    	 		jQuery("#showDialog").html('<b>修改政策失败！</b>');
								$('#null_for_academy').dialog("open");
				    	 	}
			    	 	
				    	 }	
			    	});
		
				}
			}	
			 /////////      /优惠标准分页列表/    /////////////////			
			//列表自定义字段显示
			//显示缴费次数
			function showpayment(feePaymentId)
			{
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
				if(parseInt(targetObjectId)==POLICY_TARGET_OBJECT_BRANCH)
				{
					return "弘成优惠";
				}
				else
				{
					return "院校优惠";
				}
			}
			//优惠标准
			function showstandard(discountstandard)
			{
				return discountstandard;
			}
			function operat(id)
			{
				var discountPolicyId=jQuery("#discountPolicyId").val();
				var pids=discountPolicyId.split(",");
				for(var i=0;i<pids.length;i++)
				{
					if(id==parseInt(pids[i]))
					{
						return '<input type="checkbox" checked="checked" name="pfeeradio" value="'+id+'"/>';
					}
				}
				return '<input type="checkbox" name="pfeeradio" value="'+id+'"/>';
			}
		</script>
	</head>
  
  <body>
<!-- 头开始 -->
		<head:head title="修改学生优惠政策">
			<html:a text="返回" icon="return" onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<input type="hidden" name="studentDetailId" id="studentDetailId" value="${studentDiscountDetail.id}"/>
				<input type="hidden" name="academyId" id="academyId" value="${studentDiscountDetail.academyId}"/>
				<input type="hidden" name="feeSubjectId" id="feeSubjectId" value="${studentDiscountDetail.feeSubjectId}"/>
				<input type="hidden" name="feePaymentId" id="feePaymentId" value="${studentDiscountDetail.feeCountId}"/>
				<input type="hidden" name="discountPolicyId" id="discountPolicyId" value="${studentDiscountDetail.discountPolicyId}"/>
				 <table class="gv_table_2">
				  	<tr>
						 <th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 <th style="text-align:left; font-weight:bold;">基本信息</th>							
					</tr>
				  </table>
				  <table class="add_table" id="brach" class="brach" border="0" cellpadding="2" cellspacing="2">
				  	<s:if test="%{studentDiscountDetail.academyId!=-1}">
					  	<tr>
			                <td class="lable_100">合作方类别：</td>
			                <td align="left">
			                	${studentDiscountDetail.channeltypename}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">合作方：</td>
			                <td align="left">
			                	${studentDiscountDetail.channelname}
							</td>
						</tr>
					  	<tr>
			                <td class="lable_100">院校：</td>
			                <td align="left">
			                	${studentDiscountDetail.academyname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">学习中心：</td>
			                <td align="left">
			                	${studentDiscountDetail.branchname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">批次：</td>
			                <td align="left">
			                	${studentDiscountDetail.batchname}
							</td>
						</tr>	
						<tr>
			                <td class="lable_100">层次：</td>
			                <td align="left">
			                	${studentDiscountDetail.levelname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">专业：</td>
			                <td align="left">
			                	${studentDiscountDetail.majorname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">费用科目：</td>
			                <td align="left">
			                	${studentDiscountDetail.feesubjectname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">缴费次数：</td>
			                <td align="left">
			                	第${studentDiscountDetail.feeCountId}次缴费
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
			                <td class="lable_100">学习中心：</td>
			                <td align="left">
			                	${studentDiscountDetail.branchname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">费用科目：</td>
			                <td align="left">
			                	${studentDiscountDetail.feesubjectname}
							</td>
						</tr>
						<tr>
			                <td class="lable_100">缴费次数：</td>
			                <td align="left">
			                	第${studentDiscountDetail.feeCountId}次缴费
							</td>
						</tr>
					</s:else>
				  </table>
								  
				   <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;" class="feehtml">政策标准
					 	   </th>
				    </tr>
				  </table>
				   <page:plugin 
						pluginCode="007"
						il8nName="enrollment"
						searchListActionpath="list_student_discount_policy_ajax"
						searchCountActionpath="count_student_discount_policy_ajax"
						columnsStr="#operating;academyname;title;feesubjectname;#feepayment;#discountway;#targetobject;discountstandard"
						customColumnValue="0,operat(id);4,showpayment(feePaymentId);5,showdiscout(discountWayId);6,showtarget(targetObjectId);7,showstandard(discountstandard)"
						pageSize="5"
						isPage="false"
						isNumber="false"
						isChecked="false"								
						params="'studentDiscountPolicy.academyId':$('#academyId').val(),'studentDiscountPolicy.feeSubjectId':$('#feeSubjectId').val(),'studentDiscountPolicy.feePaymentId':$('#feePaymentId').val()"
						
					/>
				  <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="button" onclick="adddetail()" value="修改" /></td></tr></table>
				  
			</body:body>
			
	<div id="null_for_academy" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
  </body>
</html>
