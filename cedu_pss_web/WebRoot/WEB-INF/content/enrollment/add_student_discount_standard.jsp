<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加学生优惠政策标准</title>
		
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
				showdiscountway();//优惠方式下拉框
				showtargetway();//优惠主体下拉框
				//优惠方式下拉框改变事件
				jQuery('#discountWayId').change(function(){
					if(this.value==MONEY_FORM_RATIO)
					{
						if(jQuery("input[name='mutable']:checked").val()==1)
						{
							jQuery("#rebates_way_name").html("<span>*</span>比例：");
							jQuery("#rebates_way_unit").html("%");
						}
						else
						{
							jQuery("#rebates_way_name").html("<span>*</span>起始比例：");
							jQuery("#rebates_way_unit").html("%");
							jQuery("#rebates_way_name1").html("<span>*</span>渐变比例：");
							jQuery("#rebates_way_unit1").html("%");
						}
					}
					else if(this.value==MONEY_FORM_AMOUNT)
					{
						if(jQuery("input[name='mutable']:checked").val()==1)
						{
							jQuery("#rebates_way_name").html("<span>*</span>金额：");
							jQuery("#rebates_way_unit").html("元");
						}
						else
						{
							jQuery("#rebates_way_name").html("<span>*</span>起始金额：");
							jQuery("#rebates_way_unit").html("元");
							jQuery("#rebates_way_name1").html("<span>*</span>渐变金额：");
							jQuery("#rebates_way_unit1").html("元");
						}
					}
					
				});			
				//点击固定、渐变单选框事件
				jQuery('[name=mutable]').click(function(){
					if(this.value==1)
					{
						jQuery('.hid1').hide();
						if(jQuery('#discountWayId').val()==MONEY_FORM_RATIO)
						{
							jQuery("#rebates_way_name").html("<span>*</span>比例：");
							jQuery("#rebates_way_unit").html("%");
						}
						else
						{
							jQuery("#rebates_way_name").html("<span>*</span>金额：");
							jQuery("#rebates_way_unit").html("元");
							
						}
					}
					else if(this.value==2)
					{
						jQuery('.hid1').show();
						if(jQuery('#discountWayId').val()==MONEY_FORM_RATIO)
						{
							jQuery("#rebates_way_name").html("<span>*</span>起始比例：");
							jQuery("#rebates_way_unit").html("%");
							jQuery("#rebates_way_name1").html("<span>*</span>渐变比例：");
							jQuery("#rebates_way_unit1").html("%");
						}
						else
						{
							jQuery("#rebates_way_name").html("<span>*</span>起始金额：");
							jQuery("#rebates_way_unit").html("元");
							jQuery("#rebates_way_name1").html("<span>*</span>渐变金额：");
							jQuery("#rebates_way_unit1").html("元");
						}
					}	
				});
				//优惠主体下拉框改变事件
				jQuery('#targetObjectId').change(function(){
					if(this.value==POLICY_TARGET_OBJECT_ACADEMY)
					{
						$("#issharespan").show();
					}
					else
					{
						$("#issharespan").hide();
					}
				});		
				
				//初始化弹出框
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
			
			//优惠方式下拉框
			function showdiscountway()
			{
				$('#discountWayId').empty();
			    $('#discountWayId').append('<option value="'+MONEY_FORM_RATIO+'">比例</option>');
			    $('#discountWayId').append('<option value="'+MONEY_FORM_AMOUNT+'">金额</option>');
			}
			//优惠主体下拉框
			function showtargetway()
			{
				$('#targetObjectId').empty();
			    $('#targetObjectId').append('<option value="'+POLICY_TARGET_OBJECT_CEDU+'">弘成优惠</option>');
			    $('#targetObjectId').append('<option value="'+POLICY_TARGET_OBJECT_BRANCH+'">中心优惠</option>');
			    $('#targetObjectId').append('<option value="'+POLICY_TARGET_OBJECT_ACADEMY+'">院校优惠</option>');
			    $('#targetObjectId').append('<option value="'+POLICY_TARGET_OBJECT_CHANNEL+'">渠道优惠</option>');
			}
			//form提交验证
			function showsubmit()
			{
				if(jQuery("#academyId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择院校！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#feesubjectId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择费用科目！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#feePaymentId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择缴费次数！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#discountWayId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择优惠方式！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#title").val()=="")
				{
					jQuery("#showDialog").html('<b>请填写标题！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#targetObjectId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择优惠主体！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#activityBeginDate").val()=="")
				{
					jQuery("#showDialog").html('<b>请选择活动起始时间！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#activityEndDate").val()=="")
				{
					jQuery("#showDialog").html('<b>请选择活动结束时间！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#useBeginDate").val()=="")
				{
					jQuery("#showDialog").html('<b>请选择使用开始时间！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#useEndDate").val()=="")
				{
					jQuery("#showDialog").html('<b>请选择使用结束时间！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#money").val()=="")
				{
					jQuery("#showDialog").html('<b>请填写金额/比例！</b>');
					$('#null_for_academy').dialog("open");
					return false;
					
				}
				//if(!((/^\d{1,10}(\.\d{1,2})?$/).test(jQuery("#money").val())))
				if(!((/^\-?[0-9]+(.[0-9]+)?$/).test(jQuery("#money").val())))
				{
					jQuery("#showDialog").html('<b>金额/比例填写不正确，只能填写小数或整数！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("input[name='mutable']:checked").length>0 && jQuery("input[name='mutable']:checked").val()==2 && jQuery("#delta").val()=="")
				{
					jQuery("#showDialog").html('<b>请填写渐变差量！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("input[name='mutable']:checked").length>0 && jQuery("input[name='mutable']:checked").val()==2 && !((/^\-?[0-9]+(.[0-9]+)?$/).test(jQuery("#delta").val())))
				{
					jQuery("#showDialog").html('<b>渐变差量填写不正确，只能填写小数或整数！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				else
				{
					//固定、渐变
					if(jQuery("input[name='mutable']:checked").val()==1)
					{
						jQuery("#mutableId").val(MONEY_FORM_MUTABLE);
					}
					else
					{
						jQuery("#mutableId").val(MONEY_FORM_GRADIENT);
					}
					//优惠审批(申请)
					jQuery("#isan").val(jQuery("input[name='isApplicationNeeded']:checked").val());
					return true;
				}
			}
			
		</script>
		<script type="text/javascript">
			//加载事件
			jQuery(function(){
				//费用科目
				jQuery('#feesubjectId').change(function(){
					if(this.value!=0)
					{
						ajax_119_1();
					}
				});	
					 
			});
			//ajax回调函数
			function ajax_feesubject(data){
				if(data.feeSubject!=null)
			    {
				    if(data.feeSubject.isMultiplePayment==IS_MULTIPLE_PAYMENT_TRUE)
				    {
				    	$('#feePaymentId').empty();
						$('#feePaymentId').append('<option value="0">--请选择--</option>');
						$('#feePaymentId').append('<option value="1">第1次缴费</option>');
						$('#feePaymentId').append('<option value="2">第2次缴费</option>');
						$('#feePaymentId').append('<option value="3">第3次缴费</option>');
						$('#feePaymentId').append('<option value="4">第4次缴费</option>');
						$('#feePaymentId').append('<option value="5">第5次缴费</option>');
				    }
				   	else
				    {
				    	$('#feePaymentId').empty();
						$('#feePaymentId').append('<option value="0">--请选择--</option>');
						$('#feePaymentId').append('<option value="1">第1次缴费</option>');
				    }
				    	 	
			    }	
			}			
		</script>
		<!--ajax插件-->
		<a:ajax successCallbackFunctions="ajax_feesubject" parameters="{id:jQuery('#feesubjectId').val()}" urls="/enrollment/find_fee_subject_ajax" pluginCode="119"/>
	</head>
  
  <body>

    <!--头部层开始 -->
		<head:head title="添加学生优惠政策标准">
			<html:a text="返回" icon="return" onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="myform" method="post" action="add_student_discount_standard" onsubmit="return showsubmit();">
				 <input type="hidden" name="studentDiscount.mutable" id="mutableId" value="1"/>
				 <input type="hidden" name="studentDiscount.isApplicationNeeded" id="isan" value="1"/>
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
					  			<s:if test="%{academylist!=null}">
									<s:select list="%{academylist}" listKey="id" headerKey="-1" headerValue="--请选择--" theme="simple" listValue="name" name="studentDiscount.academyId" id="academyId" cssClass="txt_box_150"/>
								</s:if>
			                	<s:else>
			                		<select name="studentDiscount.academyId" id="academyId" class="txt_box_150">
										<option value="0">--请选择--</option>
									</select>
			                	</s:else>	
							</td>
						</tr>
					  	<tr>
							<td class="lable_100"><span>*</span>费用科目：</td>					
							<td >
					  			 <s:if test="%{feesubjectlist!=null}">
			                		<s:select list="%{feesubjectlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="studentDiscount.feeSubjectId" id="feesubjectId" cssClass="txt_box_150"/>
			                	</s:if>
			                	<s:else>
			                		<select name="studentDiscount.feeSubjectId" id="feesubjectId" class="txt_box_150">
										<option value="0">--请选择--</option>
									</select>
			                	</s:else>
							</td>
						</tr>

						<tr>
							<td class="lable_100"><span>*</span>缴费次数：</td>
			                <td align="left">
			                	<select name="studentDiscount.feePaymentId" id="feePaymentId" class="txt_box_150">
									<option value="0">--请选择--</option>
									<option value="1">第1次缴费</option>
								</select>
							
							</td>
						</tr>
						<tr>
							<td class="lable_100"><span>*</span>优惠方式：</td>					
							<td >
								<select name="studentDiscount.discountWayId" id="discountWayId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="lable_100"><span>*</span>标题：</td>					
							<td >
					  			<input type="text" name="studentDiscount.title" id="title" class="txt_box_150"/>
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
					  			<span>*</span>优惠主体：
							</td>
					  		<td align="left">
					  			<select name="studentDiscount.targetObjectId" id="targetObjectId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>(由优惠的主体承担优惠费用的结算成本)
								<span style="display:none;" id="issharespan">
									特殊承担方式：<select name="studentDiscount.isShared" id="isShared" class="txt_box_150">
										<option value="<%=Constants.POLICY_IS_SHARED_FLASE%>">院校独立承担</option>
										<option value="<%=Constants.POLICY_IS_SHARED_TRUE%>">院校与弘成共同承担</option>
									</select>
								</span>
							</td>
							
					  	</tr>
						<tr>
					  		<td width="25%" align="right">
					  			<span>*</span>优惠审批：
							</td>
					  		<td align="left">
					  			<!--input type="checkbox" name="studentDiscount.isApplicationNeeded" id="isApplicationNeeded" value="1"/>是否需要申请 -->
					  			<input type="radio" name="isApplicationNeeded" id="isApplicationNeeded" value="<%=Constants.STUDENT_DISCOUNT_AUDIT_CEDU%>" checked="checked"/>总部审批
					  			<input type="radio" name="isApplicationNeeded" id="isApplicationNeeded" value="<%=Constants.STUDENT_DISCOUNT_AUDIT_FALSE%>"/>无需审批
					  			<input type="radio" name="isApplicationNeeded" id="isApplicationNeeded" value="<%=Constants.STUDENT_DISCOUNT_AUDIT_BRANCH%>"/>中心审批
					  			<input type="radio" name="isApplicationNeeded" id="isApplicationNeeded" value="<%=Constants.STUDENT_DISCOUNT_CUSTOM_BRANCH%>"/>中心随机使用
							</td>
							
					  	</tr>
					  	<tr>
					  		<td width="25%" align="right">
					  			<span>*</span>活动时间：
							</td>
					  		<td align="left">
					  			起始时间：<input type="text" name="studentDiscount.activityBeginDate" id="activityBeginDate" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'activityEndDate\',{d:-1});}'})" readonly="readonly"/>
					  			~~结束时间：<input type="text" name="studentDiscount.activityEndDate" id="activityEndDate" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'activityBeginDate\',{d:1});}'})" readonly="readonly"/>
								 
							</td>
							
					  	</tr>
						<tr>
					  		<td width="25%" align="right">
					  			<span>*</span>使用时间：
							</td>
					  		<td align="left">
					  			起始时间：<input type="text" name="studentDiscount.useBeginDate" id="useBeginDate" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'useEndDate\',{d:-1});}'})" readonly="readonly"/>
					  			~~结束时间：<input type="text" name="studentDiscount.useEndDate" id="useEndDate" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'useBeginDate\',{d:1});}'})" readonly="readonly"/>
							</td>
							
					  	</tr>
						<tr>
					  		<td width="25%" align="right">
					  			<div id="rebates_way_name"><span>*</span>比例：</div>
							</td>
					  		<td align="left">
					  			<input type="text" name="studentDiscount.money" id="money" class="txt_box_150"/><span id="rebates_way_unit" class="rebates_way_unit" style="color: black !important;">%</span>
								&nbsp;&nbsp;&nbsp;
								<span id="hid1" class="hid1" style="display:none"></span>
								<span id="hid1" class="hid1" style="display:none"><span id="rebates_way_name1"></span><input type="text" name="studentDiscount.delta" id="delta" class="txt_box_150"/><span id="rebates_way_unit1" class="rebates_way_unit" style="color: black !important;"></span></span>
							</td>
							
					  	</tr>
					  	
						<tr>
							<td width="25%" align="right">
					  			<span>*</span>类别：
							</td>
							<td align="left">
								<input type="radio" name="mutable" checked="checked" value="1" />固定(金额/比例)<input type="radio" name="mutable"  value="2" />渐变(金额/比例)
							</td>
							
						</tr>
			        </tbody>
				  </table>			
				  <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="submit" value="保存" /></td></tr></table>
				</form>
			</body:body>
	<div id="null_for_academy" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
  </body>
</html>
