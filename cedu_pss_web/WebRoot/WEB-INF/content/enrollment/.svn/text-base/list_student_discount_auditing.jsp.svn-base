<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生优惠政策审批</title>
		
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
				
				//初始化弹出的提示层
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
				//初始化批量删除层
				$('#show_for_academy').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'批量审批结果',
					width: 500,
					buttons: {
						'确定': function() { 
							$('#message_confirm').dialog({
								title:'确认操作',
								buttons: {
									'确认': function() { 
										//
										batchApprovalAjax();
									}, 
									'取消': function() { 
										$(this).dialog("close"); 
									} 
								}
							});
							$('#message_confirm').dialog("open"); 
						}, 
						'取消': function() { 
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
					isPageOperating(id,601,"checkbox");
					return "审批通过"
				}
				else
				{
					//isPageOperating(id,601,"update");
					return "审批不通过";
				}
				
			}	
			
			function showfee(discountstandard)
			{
				return discountstandard;
			}
			
			function confirmfee(aduitStatus,id)
			{
				if(parseInt(aduitStatus)!=AUDIT_STATUS_TRUE)
				{
					return '<a href="confirm_student_discount_detail?id='+id+'" >审批</a>';
				}
				else
				{
					return "";
				}
			}
			
			//启用、停用状态
			function isusing(isUsing,id,aduitStatus)
			{
				if(parseInt(aduitStatus)==AUDIT_STATUS_TRUE)
				{
					if(parseInt(isUsing)==STATUS_ENABLED)
					{
						return "<select name='status' id='status"+id+"' onchange='changestatus("+id+")'><option selected='selected' value='"+STATUS_ENABLED+"'>启用</option><option  value='"+STATUS_DISABLE+"'>停用</option></select>";
					}
					else
					{
						return "<select name='status' id='status"+id+"' onchange='changestatus("+id+")' ><option  value='"+STATUS_ENABLED+"'>启用</option><option selected='selected' value='"+STATUS_DISABLE+"'>停用</option></select>";
					}
				}
				else
				{
					return "停用";
				}
				
			}
			//ajax修改 启用、停用
			function changestatus(id)
			{
				var status=$("#status"+id).val();
			    $.ajax({
		    		url:'<s:url value="isusing_student_discount_detail_ajax" />',
		    		data:{studentDetailId:id,isUsing:status},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
			    	 	if(data.backtracks)
			    	 	{
			    			jQuery("#showDialog").html('<b>操作成功！</b>');
							jQuery('#null_for_academy').dialog('open');
			    	 	}	
			    	 	else
			    	 	{
			    	 		jQuery("#showDialog").html('<b>操作失败！</b>');
							jQuery('#null_for_academy').dialog('open');
			    	 	}
			    	 	refresh601();
			    	 }	
		    	});
			}
			
			//判断批量审批
			function batchApproval()
			{
				if(getCheckedValues601()==null || getCheckedValues601()=="")
				{
					jQuery("#showDialog").html('<b>请选择要批量审批的政策！</b>');
					jQuery('#null_for_academy').dialog('open');
				}
				else
				{
					jQuery('#show_for_academy').dialog('open');
				}
			}
			//ajax批量审批
			function batchApprovalAjax()
			{
				 $.ajax({
		    		url:'<s:url value="aduit_student_discount_detail_ajax" />',
		    		data:{ids:getCheckedValues601(),isUsing:jQuery("input[name='confrimrad']:checked").val()},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
			    	 	if(data.backtracks)
			    	 	{
			    	 		jQuery('#message_confirm').dialog('close');
			    	 		jQuery('#show_for_academy').dialog('close');
			    			jQuery("#showDialog").html('<b>操作成功！</b>');
							jQuery('#null_for_academy').dialog('open');
							//search601();
			    	 	}	
			    	 	else
			    	 	{
			    	 		jQuery('#message_confirm').dialog('close');
			    	 		jQuery('#show_for_academy').dialog('close');
			    	 		jQuery("#showDialog").html('<b>操作失败！</b>');
							jQuery('#null_for_academy').dialog('open');
							//search601();
			    	 	}
			    	 	refresh601();
			    	 }	
		    	});
			}
			
			
			//ajax回调函数   合作方类型与合作方级联
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
		</script>
		<!--ajax插件-->
		<a:ajax successCallbackFunctions="ajax_channel" parameters="{channelType:jQuery('#channelType').val()}" urls="/enrollment/list_channeltype_channel_ajax" pluginCode="119"/>
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="学生优惠政策审批">
			<html:a text="批量审批" icon="update" onclick="batchApproval()"/>
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
		                <td>院校：</td>
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
		                <td>招生批次：</td>
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
		                <td>费用科目：</td>
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
						<td>审批：</td>
		                <td align="left">
		                	<select name="studentDiscount.aduitStatus" id="aduitStatus" class="txt_box_150">
								<option value="3">--请选择--</option>
							</select>						
						</td>
						<td></td>
		                <td align="left">
							<input type="button" class="btn_black_61"  onclick="search601();" value="查询"/>
						</td>
	             	</tr>
				</table>
				</form>
				<page:plugin 
						pluginCode="601"
						il8nName="enrollment"
						searchListActionpath="list_student_discount_detail_ajax"
						searchCountActionpath="count_student_discount_detail_ajax"
						columnsStr="channeltypename;channelname;academyname;batchname;branchname;levelname;majorname;feesubjectname;#feepayment;discountstandard;#aduitstatus;#isusing;public.operating"
						customColumnValue="8,showpayment(feeCountId);9,showfee(discountstandard);10,aduitstatus(aduitStatus,id);11,isusing(isUsing,id,aduitStatus);12,confirmfee(aduitStatus,id)"
						pageSize="5"
						isNumber="false"
						isChecked="true"
						checkboxValue="id"
						
						view="http,/enrollment/view_student_discount_detail,id,id,_blank"								
						searchFormId="myform"
					/>
	
		</body:body>
	
	<!-- 弹出层 -->
	<div id="null_for_academy" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
	<div id="show_for_academy" style="display:none">
		<div align="center">
			<input type="radio" name="confrimrad" checked="checked" value="1"/>审批通过
			<input type="radio" name="confrimrad" value="2"/>审批不通过
		</div>
	</div>
 	<div id="message_confirm" style="display:none">
		<div align="center">确认执行审批？</div>
	</div>
  </body>
</html>
