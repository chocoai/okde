<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>学习中心、服务站合并计算返款</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 表单验证 -->
	<jc:plugin name="validator" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- 分页 -->
	<jc:plugin name="page" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	
	<script type="text/javascript">
	
		//显示缴费单状态
		function showstatus(status)
		{
			return status.getPaymentStatus();
		}
		 //处理输入的钱是否正确
		function showcheckmoney()
		{
			if(dealwithmoney(jQuery("#payadd").val())==-1)
			{
				jQuery("#payadd").val("");
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithmoney(jQuery("#payadd").val())));
				jQuery("#showDialog").html('<b>输入格式不正确，只能输入整数和小数！</b>');
				$('#message_returns_tips').dialog("open");
			}
			else
			{
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithmoney(jQuery("#payadd").val())));
			}
		}
		//保存
		function doSave(){
			if(jQuery("#indexcount").val()==1)
			{
				jQuery("#showDialog").html('<b>已添加过，请重新选择合作方！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}	
			else if(jQuery("#oldChannelType").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择招生途径！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#oldChannelId").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择合作方！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#feepdIds").val()=="")
			{
				jQuery("#showDialog").html('<b>请选择要招生返款的缴费单明细！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				jQuery("#amountToPay").val(jQuery('#paysum').text());
				jQuery("#amountPaied").val(jQuery('#payall').text());
				jQuery("#adjustReason").val(jQuery('#note').val());
				jQuery("#orderpsta").val(jQuery('#psta').val());
				//alert(jQuery("#orderpsta").val());
				ajax_140_1();
			}
			
		}
		//查询
		function findfeepayment()
		{
			if(jQuery("#channelType").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择主合作方里的招生途径！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#channelId").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择主合作方里的合作方！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#minorChannelType").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择次合作方里的招生途径！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#minorChannelId").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择次合作方里的合作方！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#oldChannelId").val()==jQuery("#channelId").val() && jQuery("#oldMinorChannelId").val()==jQuery("#minorChannelId").val() &&jQuery('#psta').val()!=jQuery("input[name='policeStatus']:checked").val())
			{
				$('#message_confirm_change_status').dialog({
					title:'确认操作',
					buttons: {
						'确认': function() { 
								search001();
								search002();
								jQuery('.rebateContent').show();	
								jQuery("#oldChannelId").val(jQuery("#channelId").val());	
								jQuery("#oldChannelType").val(jQuery("#channelType").val());	
								jQuery("#oldMinorChannelId").val(jQuery("#minorChannelId").val());	
								jQuery("#oldMinorChannelType").val(jQuery("#minorChannelType").val());
								jQuery("#feepdIds").val("");
								jQuery("#indexcount").val(0);
								jQuery('#paysum').text("0");
								jQuery('#payall').text("0");
								jQuery('#payadd').val("");
								jQuery('#psta').val(jQuery("input[name='policeStatus']:checked").val());
								//alert(jQuery("input[name='policeStatus']:checked").val());
								search003();
								$(this).dialog("close"); 
							}, 
						'取消': function() { 
								$(this).dialog("close"); 
							} 
					}
				});
				$('#message_confirm_change_status').dialog("open");
			}
			else if(jQuery("#oldChannelId").val()==jQuery("#channelId").val() && jQuery("#oldMinorChannelId").val()==jQuery("#minorChannelId").val())
			{
				search001();
				search002();
			}		
			else
			{
				search001();
				search002();
				jQuery('.rebateContent').show();	
				jQuery("#oldChannelId").val(jQuery("#channelId").val());	
				jQuery("#oldChannelType").val(jQuery("#channelType").val());	
				jQuery("#oldMinorChannelId").val(jQuery("#minorChannelId").val());	
				jQuery("#oldMinorChannelType").val(jQuery("#minorChannelType").val());
				jQuery("#feepdIds").val("");
				jQuery("#indexcount").val(0);
				jQuery('#paysum').text("0");
				jQuery('#payall').text("0");
				jQuery('#payadd').val("");
				jQuery('#psta').val(jQuery("input[name='policeStatus']:checked").val());
				//alert(jQuery("input[name='policeStatus']:checked").val());
				search003();
			}
		}
		//选择需要返款的待缴费单  获取缴费单明细ids集合
		function selectfpd()
		{
			if(getCheckedValues001()==null || getCheckedValues001()=="")
			{
				jQuery("#showDialog").html('<b>请选择要返款的缴费单明细！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				addFeepdIds=getCheckedValues001();
				ajax_120_1();//判断选择	
			}
		}
		//选择需要返款的待缴费单  获取缴费单明细ids集合
		function selectfpd2()
		{
			if(getCheckedValues002()==null || getCheckedValues002()=="")
			{
				jQuery("#showDialog").html('<b>请选择要返款的缴费单明细！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				addFeepdIds=getCheckedValues002();
				ajax_120_1();//判断选择	
			}
		}
		//移除不需要返款的待缴费单  获取缴费单明细ids集合
		function deletefpd()
		{
			//alert(getCheckedValues003());
			if(getCheckedValues003()==null || getCheckedValues003()=="")
			{
				jQuery("#showDialog").html('<b>请选择要移除的缴费单明细！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				$('#message_confirm').dialog({
					title:'确认操作',
					buttons: {
						'确认': function() { 
								$(this).dialog("close"); 
								ajax_130_1();//移除
							}, 
						'取消': function() { 
								$(this).dialog("close"); 
							} 
					}
				});
				$('#message_confirm').dialog("open"); 
			}
		}
	
		jQuery(function(){
			
			//主合作方级联
			jQuery('#channelType').change(function(){
				ajax_600_1();
			});	
			
			//次合作方级联
			jQuery('#minorChannelType').change(function(){
				if(jQuery("#minorBranchId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择学习中心！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else
				{
					ajax_650_1();
				}
			});	
			//次合作方级联  学习中心改变
			jQuery('#minorBranchId').change(function(){
				if(jQuery("#minorChannelType").val()!=0)
				{
					ajax_650_1();
				}
			});	
			//院校
			jQuery('#academyId').change(function(){
				ajax_610_1();//批次
			});	
					
			//批次
			jQuery('#batchId').change(function(){
				ajax_620_1();//层次
			});			
							
			//层次
			jQuery('#levelId').change(function(){
				ajax_630_1();//专业	
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
			//不符合的缴费单
			jQuery('#show_for_feepaymentdetail').dialog({
				autoOpen:false,
				modal:true,
				draggable:false,
				resizable:false,
				title:'缴费单明细',
				width: 650,
				buttons: {
					'关闭': function() { 
						jQuery(this).dialog("close"); 
					} 
				}
			});	
			
		});
	
	
		//ajax回调函数  选取缴费单明细时的验证
		var addFeepdIds="";
		function ajax_test_fpd(data)
		{				
			if(data.isback)
			{
				jQuery("#feepdIds").val(data.newFeepdIds);
				//alert(jQuery("#feepdIds").val());
				var paysum = jQuery('#paysum').text();
				paysum = parseFloat(paysum);
				jQuery('#paysum').text(paysum+parseFloat(data.allRebateMoney));	
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithmoney(jQuery('#payadd').val())));
				search003();
			}
			else
			{
				//已经存在的数据
				$('#hasfpdtab > tbody').empty();
			   	var haslist='';
				if(data.hasFpdList!=null && data.hasFpdList.length>0)
				{
					$.each(data.hasFpdList,function(){	
						haslist+='<tr>';
						haslist+='<td align="center">'+this.paymentCode+'</td>';
						haslist+='<td align="center">'+this.studentName+'</td>';
						haslist+='<td align="center">'+this.paymentSubjectName+'</td>';
						haslist+='<td align="center">'+this.moneyToPay+'</td>';
						haslist+='</tr>';
					});
				}
				else
				{
					haslist+='<tr><td align="center" colspan="4">无相关数据！</td></tr>';
				}
				$('#hasfpdtab > tbody').html(haslist);
				
				//无相关政策的数据
				$('#nopolicefpdtab > tbody').empty();
			   	var noplist='';
				if(data.notPoliceFpdList!=null && data.notPoliceFpdList.length>0)
				{
					$.each(data.notPoliceFpdList,function(){	
						noplist+='<tr>';
						noplist+='<td align="center">'+this.paymentCode+'</td>';
						noplist+='<td align="center">'+this.studentName+'</td>';
						noplist+='<td align="center">'+this.paymentSubjectName+'</td>';
						noplist+='<td align="center">'+this.moneyToPay+'</td>';
						noplist+='</tr>';
					});
				}
				else
				{
					noplist+='<tr><td align="center" colspan="4">无相关数据！</td></tr>';
				}
				$('#nopolicefpdtab > tbody').html(noplist);
				
				jQuery("#feepdIds").val(data.newFeepdIds);
				//alert(jQuery("#feepdIds").val());
				var paysum = $('#paysum').text();
				paysum = parseFloat(paysum);
				$('#paysum').text(paysum+parseFloat(data.allRebateMoney));
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithmoney(jQuery('#payadd').val())));
				search003();
				jQuery('#show_for_feepaymentdetail').dialog('open');
			}		
		}
		//ajax回调函数  移除缴费单明细
		function ajax_del_fpd(data)
		{
			if(data.isfail)
			{
				jQuery("#feepdIds").val(data.newdelFeepdIds);			
				jQuery('#paysum').text(data.alldelRebateMoney);
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithmoney(jQuery('#payadd').val())));
				search003();
			}
			else
			{
				jQuery("#showDialog").html('<b>移除失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
		
		//添加招生返款
		function ajax_pac(data)
		{
			
			if(data.isback)
			{
				jQuery("#indexcount").val(1);
				jQuery("#showDialog").html('<b>添加成功！</b>');
				jQuery('#message_returns_tips').dialog('open');			
				
			}
			else
			{
				jQuery("#showDialog").html('<b>添加失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
		
		//主合作方级联
		function ajax_channel_call_back(data)
		{
			$('#channelId').empty();
			$('#channelId').append('<option value="0">--请选择--</option>');
			if(data.list!=null && data.list.length>0)
			{
				$.each(data.list,function(){	
			    	$('#channelId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    });
			}		
		}
		
		//次合作方级联
		function ajax_channel2_call_back(data)
		{
			$('#minorChannelId').empty();
			$('#minorChannelId').append('<option value="0">--请选择--</option>');
			if(data.list!=null && data.list.length>0)
			{
				$.each(data.list,function(){	
			    	$('#minorChannelId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    });
			}		
		}
		
		//ajax回调函数  招生批次(院校)
		function ajax_batch(data)
		{				
			$('#batchId').empty();
			$('#batchId').append('<option value="0">--请选择--</option>');	
			if(data.batchlist!=null&&data.batchlist.length>0)
			{
				$.each(data.batchlist,function(){	
					$('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				});
			}	
			
			$('#levelId').empty();
			$('#levelId').append('<option value="0">--请选择--</option>');
			$('#majorId').empty();
			$('#majorId').append('<option value="0">--请选择--</option>');
		}
		
		//ajax回调函数  层次(招生批次)
		function ajax_level(data)
		{				
			$('#levelId').empty();
			$('#levelId').append('<option value="0">--请选择--</option>');
			if(data.levellist!=null && data.levellist.length>0)
			{
			   	$.each(data.levellist,function(){	
			   		$('#levelId').append('<option value="'+this.id+'">'+this.level.name+'</option>'); 
			 	});
			}	
			$('#majorId').empty();
			$('#majorId').append('<option value="0">--请选择--</option>');
		}
		
		//ajax回调函数  专业(层次)
		function ajax_major(data)
		{				
			$('#majorId').empty();
			$('#majorId').append('<option value="0">--请选择--</option>');
			if(data.majorlist!=null && data.majorlist.length>0)
			{
			    $.each(data.majorlist,function(){	
			    	$('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    });
			}	
		}
		
	</script>
	
	<!--选取缴费单明细时的验证-->
	<a:ajax 
		successCallbackFunctions="ajax_test_fpd" 
		parameters="{oldFeepdIds:jQuery('#feepdIds').val(),addFeepdIds:addFeepdIds,channelId:jQuery('#oldChannelId').val(),minorChannelId:jQuery('#oldMinorChannelId').val(),policeStatus:jQuery('#psta').val()}" 
		urls="/finance/branchchannelpayment/test_fpd_for_channel_rebate_special_ajax" 
		pluginCode="120"
	/>
	
	<!--移除缴费单明细-->
	<a:ajax 
		successCallbackFunctions="ajax_del_fpd" 
		parameters="{allFeepdIds:jQuery('#feepdIds').val(),delFeepdIds:getCheckedValues003(),allmoney:$('#paysum').text(),channelId:jQuery('#oldChannelId').val(),minorChannelId:jQuery('#oldMinorChannelId').val(),policeStatus:jQuery('#psta').val()}" 
		urls="/finance/branchchannelpayment/del_fpd_for_channel_rebate_special_ajax" 
		pluginCode="130"
	/>
	
	<!--添加招生返款-->
	<a:ajax 
		successCallbackFunctions="ajax_pac" 
		parameters="jQuery('#myform').serializeObject()" 
		urls="/finance/branchchannelpayment/add_pay_branch_channel_special_ajax" 
		pluginCode="140"
	/>
	
	<!--主合作方级联(通过合作方类别)-->
	<a:ajax 
		successCallbackFunctions="ajax_channel_call_back" 
		parameters="{branchId:jQuery('#branchId').val(),channelType:jQuery('#channelType').val()}" 
		urls="/finance/branchchannelpayment/find_branch_channel_list_ajax"
		pluginCode="600" 
	/>
	
	<!--次合作方级联2(通过合作方类别)-->
	<a:ajax 
		successCallbackFunctions="ajax_channel2_call_back" 
		parameters="{branchId:jQuery('#minorBranchId').val(),channelType:jQuery('#minorChannelType').val()}" 
		urls="/finance/branchchannelpayment/find_branch_channel_list_ajax"
		pluginCode="650" 
	/>
	
	<!--招生批次(院校)-->
	<a:ajax 
		successCallbackFunctions="ajax_batch" 
		parameters="{academyId:jQuery('#academyId').val()}" 
		urls="/enrollment/cascade_academy_batch_all_ajax" 
		pluginCode="610"
	/>
	
	<!--层次(招生批次)-->
	<a:ajax 
		successCallbackFunctions="ajax_level" 
		parameters="{batchId:jQuery('#batchId').val()}" 
		urls="/enrollment/cascade_batch_level_ajax" 
		pluginCode="620"
	/>
		
	<!--专业(层次)-->
	<a:ajax 
		successCallbackFunctions="ajax_major" 
		parameters="{levelId:jQuery('#levelId').val()}" 
		urls="/enrollment/cascade_level_major_ajax" 
		pluginCode="630"
	/>
		
		
</head>
<body>
	<head:head title="学习中心、服务站合并计算返款">
		
	</head:head>
	<body:body>
		<form id="myform" method="post">
		
			<input type="hidden" name="feepdIds" id="feepdIds" value=""/>
			
			<input type="hidden" name="orderCeduChannel.amountToPay" id="amountToPay" value="0"/>
			<input type="hidden" name="orderCeduChannel.amountPaied" id="amountPaied" value="0"/>
			<input type="hidden" name="orderCeduChannel.adjustReason" id="adjustReason" value=""/>
			<input type="hidden" name="orderCeduChannel.policeStatus" id="orderpsta" value="0"/>
			
			<input type="hidden" name="indexcount" id="indexcount" value="0"/>
			 <table class="add_table" style="width:100%;border:0px;">
					<tr>
						<td style="50%"  valign="top">
							<table class="gv_table_2">
						  		<tr>
								 	<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" /></th>
								 	<th style="text-align:left; font-weight:bold;">主合作方(返款政策选取当前合作方的政策)</th>
								</tr>
							</table>
						
							<table class="add_table">
								<tr>
									<td class="lable_100">学习中心：</td>
									<td>
										<s:property value="branch.name"/>
										<input type="hidden" name="student.branchId" id="branchId" value="${branch.id}" />
									</td>
									<td class="lable_100"><span>*</span>招生途径：</td>
									<td>
										<s:if test="%{channeltypelist!=null}">
											<s:select list="%{channeltypelist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="channelType" id="channelType" cssClass="txt_box_150"/>
										</s:if>
						                <s:else>
						                	<select name="channelType" id="channelType" class="txt_box_150">
												<option value="0">--请选择--</option>
											</select>
						                </s:else>	
										<input type="hidden" name="orderCeduChannel.channelType" id="oldChannelType" value="0"/>
									</td>
									
								</tr>
								<tr>	
									<td class="lable_100"><span>*</span>合作方：</td>
									<td>
										<select name="channelId" id="channelId" class="txt_box_150">
											<option value="0">--请选择--</option>
										</select>	
										<input type="hidden" name="orderCeduChannel.channelId" id="oldChannelId" value="0"/>
									</td>
									<td class="lable_100"><span>*</span>返款政策：</td>
									<td>
										<input type="radio" name="policeStatus" id="policeStatus" checked="checked" value="<%=Constants.CHANNEL_REBATE_POLICE_STATUS_ACADEMY %>"/>院校返款政策（按院校招生人数返款）<br/>
										<input type="radio" name="policeStatus" id="policeStatus" value="<%=Constants.CHANNEL_REBATE_POLICE_STATUS_COMMON %>"/>通用返款政策（按所有招生人数返款）
										<input  type="hidden" name="psta" id="psta" value="<%=Constants.CHANNEL_REBATE_POLICE_STATUS_ACADEMY %>"/> 
									</td>
									
								</tr>
							</table>
						</td>
						<td style="50%" valign="top">
	
							<table class="gv_table_2">
						  		<tr>
								 	<th style="width:20px;"><img src="<%=Constants.WEB_IMAGES %>/images/title_menu/title_left.gif" /></th>
								 	<th style="text-align:left; font-weight:bold;">次合作方</th>			
								</tr>
							</table>
							<table class="add_table">
							   <tr>
									<td class="lable_100">学习中心：</td>
									<td>
										<s:if test="%{branchlist!=null}">
											<s:select list="%{branchlist}" listKey="id" theme="simple" listValue="name" name="minorBranchId" id="minorBranchId" cssClass="txt_box_150"/>
										</s:if>
						                <s:else>
						                	<select name="minorBranchId" id="minorBranchId" class="txt_box_150">
												<option value="0">--请选择--</option>
											</select>
						                </s:else>						
									</td>
									<td class="lable_100"><span>*</span>招生途径：</td>
									<td>
										<s:if test="%{channeltypelist!=null}">
											<s:select list="%{channeltypelist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="minorChannelType" id="minorChannelType" cssClass="txt_box_150"/>
										</s:if>
						                <s:else>
						                	<select name="minorChannelType" id="minorChannelType" class="txt_box_150">
												<option value="0">--请选择--</option>
											</select>
						                </s:else>	
										<input type="hidden" name="orderCeduChannel.minorChannelType" id="oldMinorChannelType" value="0"/>
									</td>
									
								</tr>
								<tr>	
									<td class="lable_100"><span>*</span>合作方：</td>
									<td>
										<select name="minorChannelId" id="minorChannelId" class="txt_box_150">
											<option value="0">--请选择--</option>
										</select>	
										<input type="hidden" name="orderCeduChannel.minorChannelId" id="oldMinorChannelId" value="0"/>
									</td>
									<td class="lable_100"></td>
									<td>
										&nbsp;<br/>
										&nbsp;
									</td>
									
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<table class="add_table">
							  <tbody id="rebateCondition">	
								<tr>
									<td class="lable_100">院校：</td>
									<td>
										<s:if test="%{academylist!=null}">
											<s:select list="%{academylist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="student.academyId" id="academyId" cssClass="txt_box_150"/>
										</s:if>
						                <s:else>
						                	<select name="student.academyId" id="academyId" class="txt_box_150">
												<option value="0">--请选择--</option>
											</select>
						                </s:else>	
									</td>
									<td class="lable_100">批次：</td>
									<td>
										<select name="student.enrollmentBatchId" id="batchId" class="txt_box_150">
											<option value="0">--请选择--</option>
										</select>
									</td>
									<td class="lable_100">层次：</td>
									<td>
										<select name="student.levelId" id="levelId" class="txt_box_150">
											<option value="0">--请选择--</option>
										</select>	
									</td>
								</tr>
								<tr>
									<td class="lable_100">专业：</td>
									<td>
										<select name="student.majorId" id="majorId" class="txt_box_150">
											<option value="0">--请选择--</option>
										</select>
									</td>
									<td class="lable_100">姓名：</td>
									<td>
										<input type="text" name="student.name" id="name" class="txt_box_150"/>
									</td>
									<td class="lable_100">证件号：</td>
									<td>
										<input type="text" name="student.certNo" id="certNo" class="txt_box_150"/>	
									</td>
								</tr>
								<tr>
									<td class="lable_100" colspan="5"></td>
									<td>
										<input type="button" onclick="findfeepayment()" class="btn_black_61" value="查询"/>	
									</td>
								</tr>
							  </tbody>
							 </table>
						</td>
					</tr>
					<tr>
						<td style="50%"  valign="top">
							<div class="rebateContent" style="display:none;" >
								 <table class="gv_table_2">
									<tr>
										<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
										<th style="text-align:left; font-weight:bold;">主合作方待返款缴费单明细</th>
										<th></th>
									</tr>
								</table>
								 
										<page:plugin 
											pluginCode="001"
											il8nName="finance_payment"
											subStringLength="20"
											searchListActionpath="list_channel_rebate_feepaymentdetail_ajax"
											searchCountActionpath="count_channel_rebate_feepaymentdetail_ajax"
											columnsStr="paymentCode;schoolName;branchName;academyenrollbatchName;levelName;majorName;studentName;paymentSubjectName;moneyToPay;#status"
											customColumnValue="9,showstatus(status)"
											isPage="true"
											isChecked="true"
											checkboxValue="id"
											params="'student.enrollmentSource':jQuery('#channelType').val(),'student.channelId':jQuery('#channelId').val()"
											searchFormId="myform"
											isonLoad="false"
											isPackage="true"
											isOrder="false"
										/>
									 
								<table class="add_table">
								  <tbody >
									<tr>
										<td align="left">
											(确认选择需要返款的缴费单明细)
											<input type="button" class="btn_black_61" value="选择" onclick="selectfpd()"/>
											
										</td>
									</tr>
									</tbody>
								</table>
							</div>	
						</td>
						<td style="50%"  valign="top">
							<div class="rebateContent" style="display:none;" >
								 <table class="gv_table_2">
									<tr>
										<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
										<th style="text-align:left; font-weight:bold;">次合作方待返款缴费单明细</th>
										<th></th>
									</tr>
								</table>
								 
										<page:plugin 
											pluginCode="002"
											il8nName="finance_payment"
											subStringLength="20"
											searchListActionpath="list_channel_rebate_feepaymentdetail_ajax"
											searchCountActionpath="count_channel_rebate_feepaymentdetail_ajax"
											columnsStr="paymentCode;schoolName;branchName;academyenrollbatchName;levelName;majorName;studentName;paymentSubjectName;moneyToPay;#status"
											customColumnValue="9,showstatus(status)"
											isPage="true"
											isChecked="true"
											checkboxValue="id"
											params="'student.enrollmentSource':jQuery('#minorChannelType').val(),'student.channelId':jQuery('#minorChannelId').val()"
											searchFormId="myform"
											isonLoad="false"
											isPackage="true"
											isOrder="false"
										/>
									 
								<table class="add_table">
								  <tbody >
									<tr>
										<td align="left">
											(确认选择需要返款的缴费单明细)
											<input type="button" class="btn_black_61" value="选择" onclick="selectfpd2()"/>
											
										</td>
									</tr>
									</tbody>
								</table>
							</div>	
						</td>
					</tr>
			</table>				
			
			</form> 
			<div class="rebateContent" style="display:none;" >
				<table class="gv_table_2">
					<tr>
						<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
						<th style="text-align:left; font-weight:bold;">现需返款缴费单明细</th>
						<th></th>
					</tr>
				</table>
					<page:plugin 
						pluginCode="003"
						il8nName="finance_payment"
						subStringLength="20"
						searchListActionpath="list_channel_rebate_special_fpd_for_now_ajax"
						searchCountActionpath="count_channel_rebate_special_fpd_for_now_ajax"
						columnsStr="paymentCode;code;schoolName;branchName;academyenrollbatchName;levelName;majorName;studentName;paymentSubjectName;moneyToPay;paymentByChannel;#status"
						customColumnValue="11,showstatus(status)"
						isPage="true"
						isChecked="true"
						checkboxValue="id"
						params="'feepdIds':jQuery('#feepdIds').val(),channelId:jQuery('#oldChannelId').val(),minorChannelId:jQuery('#oldMinorChannelId').val(),policeStatus:jQuery('#psta').val()"
						isonLoad="false"
						isPackage="true"
						isOrder="false"
					/>
				<table class="add_table">
				  <tbody >
					<tr>
						<td align="left">
							(确认移除不需要返款的缴费单明细)
							<input type="button" class="btn_black_61" value="移除" onclick="deletefpd()"/>
							
						</td>
					</tr>
					</tbody>
				</table>
			
			<table class="add_table">
			 <tbody>	 
				<tr>
					<td class="lable_100">应返款金额：</td>
					<td><span id="paysum">0</span></td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td class="lable_100">调整金额：</td>
					<td>
						<input type="text" name="payadd" id="payadd" onkeyup="javascript:showcheckmoney()" class="txt_box_100"/>
					</td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td class="lable_100">实际返款总金额：</td>
					<td><span id="payall">0</span></td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td class="lable_100">调整原因：</td>
					<td colspan="7"><textarea cols="60" rows="6" id="note"></textarea></td>
				</tr>
				<tr>
					<td colspan="8" align="center"><input type="button" class="btn_black_61" value="保存" onclick="doSave()"/>&nbsp;&nbsp;<input type="button" class="btn_black_61" onclick="javascript:window.close();" value="关闭"/></td>
				</tr>
			  </tbody>
			</table>
		</div>	
	</body:body>
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
			
		</div>
	</div>
	<!--弹出层    确认操作-->
	<div id="message_confirm" style="display:none">
		<div align="center" >
			<b>确认移除选中的缴费单明细？</b>
		</div>
	</div>
	<!--弹出层    确认操作-->
	<div id="message_confirm_change_status" style="display:none">
		<div align="center" >
			<b>确认改变返款政策么？<br/>（改变返款政策缴费单明细必须重新选择）</b>
		</div>
	</div>
	<div id="show_for_feepaymentdetail" style="display:none">
		<table class="gv_table_2">
			<tbody>
				<tr>
					<td style="width: 50%;" valign="top">
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
								<th style="text-align:left; font-weight:bold;" class="feehtml">无院校返款政策的缴费单明细</th>
							</tr>
						</table>				  
						<table class="gv_table_2" id="nopolicefpdtab">
							<thead>
								<tr>
									<th align="center">缴费单编号</th>
									<th align="center">学生姓名</th>
									<th align="center">费用科目</th>
									<th align="center">应缴金额  </th>
								</tr>
							</thead>					
							<tbody>	
											
							</tbody>						
						</table>
					</td>
					<td style="width: 50%;" valign="top">
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
								<th style="text-align:left; font-weight:bold;" class="feehtml">已选择过的缴费单明细</th>
							</tr>
						</table>				  
						<table class="gv_table_2" id="hasfpdtab">
							<thead>
								<tr>
									<th align="center">缴费单编号</th>
									<th align="center">学生姓名</th>
									<th align="center">费用科目</th>
									<th align="center">应缴金额  </th>
								</tr>
							</thead>					
							<tbody>						
							</tbody>						
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</div>	
</body>
</html>
