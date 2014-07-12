<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>查看学生优惠政策标准</title>
		
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
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		
		<script type="text/javascript">

			//加载事件
			jQuery(function(){								
				//赋值
				showdiscountway();				
			});
			
			//赋值
			function showdiscountway()
			{
				//是否需要申请
				if(${studentDiscount.isApplicationNeeded}==STUDENT_DISCOUNT_AUDIT_CEDU)
				{
					jQuery("#stushen").html("总部审批");
				}
				else if(${studentDiscount.isApplicationNeeded}==STUDENT_DISCOUNT_AUDIT_BRANCH)
				{
					jQuery("#stushen").html("中心审批");
				}
				else if(${studentDiscount.isApplicationNeeded}==STUDENT_DISCOUNT_CUSTOM_BRANCH)
				{
					jQuery("#stushen").html("中心随机使用");
				}
				else
				{
					jQuery("#stushen").html("无需审批");
				}
				//固定、渐变
				var nolevel=$("input[name='mutable']")
					nolevel.each(function(i){
						if('${studentDiscount.mutable}'==$(this).val())
						{
							$(this).attr("checked",true);
						}				
					}); 
				//显示的字体
				if(${studentDiscount.mutable}==1)
					{
						jQuery('.hid1').hide();
						if(${studentDiscount.discountWayId}==MONEY_FORM_RATIO)
						{
							jQuery("#rebates_way_name").html("比例：");
							jQuery("#rebates_way_unit").html("%");
						}
						else
						{
							jQuery("#rebates_way_name").html("金额：");
							jQuery("#rebates_way_unit").html("元");
							
						}
					}
					else if(${studentDiscount.mutable}==2)
					{
						jQuery('.hid1').show();
						if(${studentDiscount.discountWayId}==MONEY_FORM_RATIO)
						{
							jQuery("#rebates_way_name").html("起始比例：");
							jQuery("#rebates_way_unit").html("%");
							jQuery("#rebates_way_name1").html("渐变比例：");
							jQuery("#rebates_way_unit1").html("%");
						}
						else
						{
							jQuery("#rebates_way_name").html("起始金额：");
							jQuery("#rebates_way_unit").html("元");
							jQuery("#rebates_way_name1").html("渐变金额：");
							jQuery("#rebates_way_unit1").html("元");
						}
					}
				//优惠主体
				if(${studentDiscount.targetObjectId}==POLICY_TARGET_OBJECT_CEDU)
				{	
					jQuery("#targetObjectId").html("弘成优惠");
				}
				else if(${studentDiscount.targetObjectId}==POLICY_TARGET_OBJECT_BRANCH)
				{	
					jQuery("#targetObjectId").html("中心优惠");
				}	
				else if(${studentDiscount.targetObjectId}==POLICY_TARGET_OBJECT_ACADEMY)
				{	
					jQuery("#targetObjectId").html("院校优惠");
				}	
				else
				{	
					jQuery("#targetObjectId").html("渠道优惠");
				}	
			}
			
		</script>
		
	</head>
  
  <body>
		<!-- 头开始 -->
		<head:head title="查看学生优惠政策标准">
			<html:a text="关闭" icon="return" onclick="window.close();" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
				<form id="myform" method="post" action="add_student_discount_standard" onsubmit="return showsubmit();">
				 <input type="hidden" name="studentDiscount.mutable" id="mutableId" value="1"/>
				 <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">基本数据</th>
							
					</tr>
				  </table>
				  <table class="add_table" id="enrollbatch" class="enrollbatch" border="0" cellpadding="2" cellspacing="2">
				  	<tbody>
				  		<tr>
							<td class="lable_100">院校：</td>					
							<td >
					  			${studentDiscount.academyname}
							</td>
						</tr>
					  	<tr>
							<td class="lable_100">费用科目：</td>					
							<td >
					  			 ${studentDiscount.feesubjectname}
							</td>
						</tr>

						<tr>
							<td class="lable_100">缴费次数：</td>
			                <td align="left">
			                	第${studentDiscount.feePaymentId}次缴费
							</td>
						</tr>
						<tr>
							<td class="lable_100">优惠方式：</td>					
							<td >
								${studentDiscount.discountWayId==1?"比例":"金额"}								
							</td>
						</tr>
						<tr>
							<td class="lable_100">标题：</td>					
							<td >
					  			${studentDiscount.title}
							</td>
						</tr>
				  	</tbody>
				  </table>
				  
				   <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">优惠标准</th>
							
				  	</tr>
				  </table>
				 
				  <table class="add_table" id="feebatch" border="0" cellpadding="2" cellspacing="2" >
					 <tbody>
						 <tr>
					  		<td width="25%" align="right">
					  			优惠主体：
							</td>
					  		<td align="left">
					  			<span style="color:black" id="targetObjectId"></span>
									(由优惠的主体承担优惠费用的结算成本)
							</td>
							
					  	</tr>
						<tr>
					  		<td width="25%" align="right">
					  			优惠审批：
							</td>
					  		<td align="left">
					  			<span style="color:black" id="stushen"></span>
					  			<!--input type="checkbox" disabled="disabled" name="studentDiscount.isApplicationNeeded" id="isApplicationNeeded" value="1"/>是否需要申请 -->
							</td>
							
					  	</tr>
					  	<tr>
					  		<td width="25%" align="right">
					  			活动时间：
							</td>
					  		<td align="left">
					  			<s:date name="studentDiscount.activityBeginDate" format="yyyy-MM-dd"/>
					  			~~<s:date name="studentDiscount.activityEndDate" format="yyyy-MM-dd"/>
								 
							</td>
							
					  	</tr>
						<tr>
					  		<td width="25%" align="right">
					  			使用时间：
							</td>
					  		<td align="left">
					  			<s:date name="studentDiscount.useBeginDate" format="yyyy-MM-dd"/>
					  			~~<s:date name="studentDiscount.useEndDate" format="yyyy-MM-dd"/>
							</td>
							
					  	</tr>
						<tr>
					  		<td width="25%" align="right">
					  			<div id="rebates_way_name">比例：</div>
							</td>
					  		<td align="left">
					  			${studentDiscount.money}<span id="rebates_way_unit" class="rebates_way_unit" style="color: black !important;">%</span>
								&nbsp;&nbsp;&nbsp;
								<span id="hid1" class="hid1" style="display:none"></span>
								<span id="hid1" class="hid1" style="display:none"><span id="rebates_way_name1"></span>${studentDiscount.delta}<span id="rebates_way_unit1" class="rebates_way_unit" style="color: black !important;"></span></span>
							</td>
							
					  	</tr>
					  	
						<tr>
							<td width="25%" align="right">
					  			类别：
							</td>
							<td align="left">
								<input type="radio" name="mutable" checked="checked" disabled="disabled" value="1" />固定(金额/比例)<input type="radio" name="mutable" disabled="disabled" value="2" />渐变(金额/比例)
							</td>
							
						</tr>
			        </tbody>
				  </table>			
				  <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="button" onclick="javascript:window.close();" value="关闭" /></td></tr></table>
				</form>
			</body:body>
	
	<div id="null_for_academy" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
  </body>
</html>
