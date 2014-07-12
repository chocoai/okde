<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>批量修改学生优惠政策</title>
		
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
							
				//审核下拉框
				showaduitStatus();
				//院校下拉框追加
				$('#academyId').append('<option value="-1">无院校(报名前)</option>');
				
				//院校相关级联(批次、学习中心-层次-专业(防止刷新时其他不刷新))
				jQuery('#academyId').change(function(){
					selectbatch();
					selectbranch();
					//selectlevel();ajax延时放到批次里执行
					//selectmajor();
				});	
				//招生批次相关级联(学习中心、层次、专业(防止刷新层次，专业不刷新))
				jQuery('#batchId').change(function(){
					selectbranch();
					selectlevel();
					//selectmajor();
				});	
				//层次专业级联
				jQuery('#levelId').change(function(){
					selectmajor();
				});	
				
				//合作方级联
				jQuery('#channelType').change(function(){
					ajax_119_1();
				});	
				
				//初始化提示框
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
			//审核下拉框
			function showaduitStatus()
			{
				$('#aduitStatus').empty();
			    $('#aduitStatus').append('<option value="3">--请选择--</option>');
			    $('#aduitStatus').append('<option value="'+AUDIT_STATUS_FALSE+'">未审批</option>');
			    $('#aduitStatus').append('<option value="'+AUDIT_STATUS_TRUE+'">审批通过</option>');
			    $('#aduitStatus').append('<option value="'+AUDIT_STATUS_FAIL+'">审批不通过</option>');
			}
			/////////      /级联/    /////////////////
			
			 //读取批次
		    function selectbatch()
		    {
		    	$.ajax({
		    		url:'<s:url value="cascade_academy_batch_all_ajax" />',
		    		data:{academyId:jQuery('#academyId').val()},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#batchId').empty();
			    	 	$('#batchId').append('<option value="0">--请选择--</option>');	
			    	 	if(data.batchlist!=null&&data.batchlist.length>0)
			    	 	{
				    	 	$.each(data.batchlist,function(){	
				    	 		$('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				    	 	});
			    	 	}
			    	 	selectlevel();//读取层次  	
			    	 }	
		    	});
		    }
			 //读取学习中心
		    function selectbranch()
		    {
		   		var batchId=$('#batchId').val();
		    	$.ajax({
		    		url:'<s:url value="cascade_batch_branch_ajax" />',
		    		data:{batchId:batchId,academyId:jQuery('#academyId').val()},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#branchId').empty();
			    	 	$('#branchId').append('<option value="0">--请选择--</option>');	
			    	 	if(data.branchlist!=null&&data.branchlist.length>0)
			    	 	{
				    	 	$.each(data.branchlist,function(){	
				    	 		$('#branchId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
				    	 	});
			    	 	}	
			    	 }	
		    	});
		    }
			 //读取层次
		    function selectlevel()
		    {
		   		var batchId=$('#batchId').val();
		    	$.ajax({
		    		url:'<s:url value="cascade_batch_level_ajax" />',
		    		data:{batchId:batchId},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#levelId').empty();
				    	$('#levelId').append('<option value="0">--请选择--</option>');
			    	 	if(data.levellist!=null&&data.levellist.length>0)
			    	 	{	
				    	 	$.each(data.levellist,function(){	
				    	 		$('#levelId').append('<option value="'+this.id+'">'+this.level.name+'</option>'); 
				    	 	});
				    	 }	
				    	 selectmajor();//读取专业
			    	 }	
		    	});
		    	
		    }
			 //读取专业
		    function selectmajor()
		    {
		   		var levelId=$('#levelId').val();
		    	$.ajax({
		    		url:'<s:url value="cascade_level_major_ajax" />',
		    		data:{levelId:levelId},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#majorId').empty();
			    	 	$('#majorId').append('<option value="0">--请选择--</option>');
			    	 	if(data.majorlist!=null&&data.majorlist.length>0)
			    	 	{
			    	 		$.each(data.majorlist,function(){	
			    	 			$('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	 		});
			    	 	}	
			    	 }	
		    	});
		    }
			
			/////////      /分页列表/    /////////////////
			
			//列表自定义字段显示
			//显示缴费次数
			function showpayment(feePaymentId)
			{
				return "第"+feePaymentId+"次缴费";
			}
			function aduitstatus(aduitStatus,id)
			{
				if(parseInt(aduitStatus)==AUDIT_STATUS_FALSE)
				{
					return "未审批";
				}
				else if(parseInt(aduitStatus)==AUDIT_STATUS_TRUE)
				{
					isPageOperating(id,601,"update");
					return "审批通过"
				}
				else
				{
					//isPageOperating(id,601,"update");
					return "审批不通过";
				}
				
			}	
			function isusing(isUsing)
			{
				if(parseInt(isUsing)==STATUS_ENABLED)
				{
					return "启用";
				}
				return "停用";
			}
			function showfee(discountstandard)
			{
				return discountstandard;
			}
			
			 /////////      /优惠标准分页列表/    /////////////////			
			//列表自定义字段显示
			
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
		</script>
		<script type="text/javascript">
			//加载事件
			jQuery(function(){
				//费用科目
				jQuery('#feesubjectId').change(function(){
					if(this.value!=0)
					{
						ajax_120_1();
					}
				});	
					 
			});	
			
			//查询按钮
			function showpolicyfeedetail()
			{
				if(jQuery("#academyId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择院校！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(jQuery("#academyId").val()!=-1 && jQuery("#batchId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择批次！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(jQuery("#feesubjectId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择费用科目！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(jQuery("#feePaymentId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择缴费次数！</b>');
					$('#null_for_academy').dialog("open");
				}
				else
				{
					jQuery("#nextdiv").show();
					search601();
					search007();				
				}
			}
			
			//修改
			var ids="";
			var policyFeeId=0;
			function batchedit()
			{
				if(getCheckedValues601()=="" || getCheckedValues601()==null)
				{
					jQuery("#showDialog").html('<b>请选择要修改的政策！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(getCheckedValues007()=="" || getCheckedValues007()==null)
				{
					jQuery("#showDialog").html('<b>请选择政策标准！</b>');
					$('#null_for_academy').dialog("open");
				}
				else
				{
					//ids=getCheckedValues601();
					//alert("ids:"+getCheckedValues601()+","+"discountPolicyId:"+getCheckedValues007());
					ajax_121_1();
				}
				 
			}
			//ajax回调函数
			function ajax_batch_edit(data){
				if(data.backtracks)
				{
					jQuery("#showDialog").html('<b>修改成功！</b>');
					search601();
					$('#null_for_academy').dialog("open");
				}
				else
				{
					jQuery("#showDialog").html('<b>修改失败！</b>');
					$('#null_for_academy').dialog("open");
				}
			}
			
			//ajax回调函数   合作方类别与合作方
			function ajax_channel(data){
				
				$('#channelId').empty();
			    $('#channelId').append('<option value="0">--请选择--</option>');
			    if(data.channellist!=null&&data.channellist.length>0)
			    {
			    	$.each(data.channellist,function(){	
			    		$('#channelId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}		
			}
			
			//ajax回调函数  费用项目与缴费次数
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
		<!--ajax插件    合作方类别与合作方-->
		<a:ajax successCallbackFunctions="ajax_channel" parameters="{channelType:jQuery('#channelType').val()}" urls="/enrollment/list_channeltype_channel_ajax" pluginCode="119"/>
		<!--ajax插件     费用项目与缴费次数-->
		<a:ajax successCallbackFunctions="ajax_feesubject" parameters="{id:jQuery('#feesubjectId').val()}" urls="/enrollment/find_fee_subject_ajax" pluginCode="120"/>
		<!--ajax插件     批量审批-->
		<a:ajax successCallbackFunctions="ajax_batch_edit" parameters="{ids:getCheckedValues601(),discountPolicyId:getCheckedValues007()}" urls="/enrollment/update_batch_student_discount_detail_ajax" pluginCode="121"/>
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="批量修改学生优惠政策">
			<html:a text="返回" icon="return" href="/enrollment/list_student_discount_detail"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">学生优惠政策</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">
				<table class="add_table">
					<tr>
						<td>合作方类别：</td>
		                <td align="left">
		                	<s:if test="%{channeltypelist!=null}">
								<s:select list="%{channeltypelist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="studentDiscount.channelType" id="channelType" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="studentDiscount.channelType" id="channelType" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>	
						</td>
		                <td>合作方：</td>
		                <td align="left">
							<select name="studentDiscount.channelId" id="channelId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td><span>*</span>院校：</td>
		                <td align="left">
		                	<s:if test="%{academylist!=null}">
								<s:select list="%{academylist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="studentDiscount.academyId" id="academyId" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="studentDiscount.academyId" id="academyId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>	
						</td>
		                <td><span>*</span>招生批次：</td>
		                <td align="left">
							<select name="studentDiscount.batchId" id="batchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
						<td>学习中心：</td>
		                <td align="left">
							<select name="studentDiscount.branchId" id="branchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                
	              	</tr>
				  	<tr>
				  		<td>层次：</td>
		                <td>
							<select name="studentDiscount.levelId" id="levelId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td>专业：</td>
		                <td align="left">
							<select name="studentDiscount.majorId" id="majorId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td><span>*</span>费用科目：</td>
		                <td align="left">
		                	<s:if test="%{feesubjectlist!=null}">
		                		<s:select list="%{feesubjectlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="studentDiscount.feeSubjectId" id="feesubjectId" cssClass="txt_box_150"/>
		                	</s:if>
		                	<s:else>
		                		<select name="studentDiscount.feeSubjectId" id="feesubjectId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
		                	</s:else>							
						</td>
						<td ><span>*</span>缴费次数：</td>
			            <td align="left">
			            	<select name="studentDiscount.feeCountId" id="feePaymentId" class="txt_box_150">
								<option value="0">--请选择--</option>
								<option value="1">第1次缴费</option>
							</select>
						</td>

						<td>审批：</td>
		                <td align="left">
		                	<select name="studentDiscount.aduitStatus" id="aduitStatus" class="txt_box_150">
								<option value="3">--请选择--</option>
							</select>						
						</td>
						
	             	</tr>
	             	<tr>
	             		<td colspan="9"></td>
		                <td align="left">
							<input type="button" class="btn_black_61"  onclick="showpolicyfeedetail();" value="查询"/>
						</td>
	             	</tr>
				</table>
				</form>
				<page:plugin 
						pluginCode="601"
						il8nName="enrollment"
						searchListActionpath="list_student_discount_detail_ajax"
						searchCountActionpath="count_student_discount_detail_ajax"
						columnsStr="channeltypename;channelname;academyname;batchname;branchname;levelname;majorname;feesubjectname;#feepayment;discountstandard;#aduitstatus;#isusing"
						customColumnValue="8,showpayment(feeCountId);9,showfee(discountstandard);10,aduitstatus(aduitStatus,id);11,isusing(isUsing)"
						pageSize="5"
						isPage="false"
						isNumber="false"
						isChecked="true"
						checkboxValue="id"								
						searchFormId="myform"
						isonLoad="false"
					/>
				<div id="nextdiv" style="display:none">
					<table class="add_table">
						<tr>
							<td align="center">
								<input type="button" class="btn_black_61"  onclick="jQuery('#shownextdiv').show();" value="下一步"/>
							</td>
						</tr>
					</table>
				</div>
				<div id="shownextdiv" style="display:none">	
				<page:plugin 
						pluginCode="007"
						il8nName="enrollment"
						searchListActionpath="list_student_discount_policy_ajax"
						searchCountActionpath="count_student_discount_policy_ajax"
						columnsStr="academyname;title;feesubjectname;#feepayment;#discountway;#targetobject;discountstandard"
						customColumnValue="3,showpayment(feePaymentId);4,showdiscout(discountWayId);5,showtarget(targetObjectId);6,showstandard(discountstandard)"
						pageSize="5"
						isPage="false"
						isNumber="false"
						isChecked="true"
						checkboxValue="id"								
						params="'studentDiscountPolicy.academyId':$('#academyId').val(),'studentDiscountPolicy.feeSubjectId':$('#feesubjectId').val(),'studentDiscountPolicy.feePaymentId':$('#feePaymentId').val()"
						isonLoad="false"
					/>
				 <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="button" onclick="batchedit()" value="修改" /></td></tr></table>
				</div>
		</body:body>
	<div id="null_for_academy" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
  </body>
</html>
