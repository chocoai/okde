<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>单院校计算返款</title>
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
	<jc:plugin name="calendar" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	
	<script type="text/javascript">
	
		//显示充值时间
		function showtime(createdTime)
		{
			if(createdTime==null)
			{
				return createdTime;
			}
			if(createdTime.length>10)
			{
				return  createdTime.substring(0,10);
			}
			return createdTime;
		}
		//显示开课状态
		function showIsStartCourse(isStartCourse,id)
		{
			if(isStartCourse==STU_IS_START_COURSE_FALSE)
			{
				isPageOperating(id,"001","checkbox");
				return "<span style='color:red'>未开课</span>";
			}
			else if(isStartCourse==STU_IS_START_COURSE_TRUE)
			{
				return "已开课";
			}
		}
		//显示招生途径复核状态
		function showIsChannelTypeChecked(isChannelTypeChecked,id)
		{
			if(isChannelTypeChecked==STUDENT_CHANNEL_TYPE_CHECKED_FALSE)
			{
				isPageOperating(id,"001","checkbox");
				return "<span style='color:red'>未复核</span>";
			}
			else if(isChannelTypeChecked==STUDENT_CHANNEL_TYPE_CHECKED_TRUE)
			{
				return "已复核";
			}
		}
		//显示金额
		function feePaymentValue(feePayment)
		{
			return (feePayment+"").toMoney();
		}
		//显示金额
		function feePaymentCheckedValue(feePayment,id)
		{
			if((feePayment-0)<(jQuery('#xueMoney').val()-0))
			{
				isPageOperating(id,"001","checkbox");
				return "<span style='color:red'>"+(feePayment+"").toMoney()+"</span>";
			}
			else
			{
				return (feePayment+"").toMoney();
			}
		}
		
		//显示缴费单状态
		function showstatus(status)
		{
			return status.getPaymentStatus();
		}
		//显示缴费方式
		function feeWayIdValue(feeWayId)
		{
			return feeWayId.getPaymentWay();
		}
		function showdescription(id,rebateStatus,refundLock,enrollmentSource,status)
		{
			if(rebateStatus<PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN)
			{
				isPageOperating(id,"001","checkbox");
				return "<span style='color:red'>总部未确认</span>";
			}
			if((enrollmentSource!=WEB_STU_ENROLLMENT_SOURCE && enrollmentSource!=WEB_STU_DA_KE_HU) && status<PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO )
			{
				isPageOperating(id,"001","checkbox");
				return "<span style='color:red'>未打款院校</span>";
			}
			if(refundLock==LOCKING_TRUE)
			{
				isPageOperating(id,"001","checkbox");
				return "<sapn style='color:red'>退费申请中</span>";
			}
			return "";
			
		}
		 //处理输入的钱是否正确
		function showcheckmoney()
		{
			if(dealwithnumber(jQuery("#payadd").val())=="--")
			{
				jQuery("#payadd").val("");
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
				jQuery("#showDialog").html('<b>输入格式不正确，只能输入整数和小数！</b>');
				jQuery('#message_returns_tips').dialog("open");
				
			}
			else if((parseFloat(jQuery('#paysum').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())))-0<0)
			{
				jQuery("#payadd").val("");
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
				jQuery("#showDialog").html('<b>实际返款金额不能为负数！</b>');
				jQuery('#message_returns_tips').dialog("open");
			}
			else
			{
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
			}
		}
		//保存
		var glcount=0;  //控制统计结算后是否添加
		function doSave()
		{
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
			else if(jQuery("#newNeedFpdIds").val()=="")
			{
				jQuery("#showDialog").html('<b>应返金额为0，请重新统计！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				var fpd=jQuery("#feepdIds").val().split(",");
				var nfp=jQuery("#newNeedFpdIds").val().split(",");
				
				var cfp=jQuery('#countAfterFpdIds').val().split(",");
				//jQuery("#amountToPay").val(jQuery('#paysum').text());
				//jQuery("#amountPaied").val(jQuery('#payall').text());
				//jQuery("#adjustReason").val(jQuery('#note').val());
				
				if(fpd.length!=nfp.length)
				{
					$('#message_confirm_add').dialog({
						title:'错误提示',
						buttons: {
							//'保存': function() { 
							//		ajax_140_1();//添加
							//		$(this).dialog("close"); 
							//		
							//	}, 
							//'统计结算': function() { 
							//		countfpd();//统计结算
							//		$(this).dialog("close"); 
							//}, 
							'确认': function() { 
									$(this).dialog("close"); 
								} 
						}
					});
					$('#message_confirm_add').dialog("open"); 
				}
				else
				{					
					if(cfp.length!=nfp.length)
					{
						glcount=1;
						countfpd();//统计结算
					}
					else
					{
						addtrue();
					}
				}
			}
			
		}
		//添加方法新增
		function addtrue()
		{
			jQuery("#amountToPay").val(jQuery('#paysum').text());
			jQuery("#amountPaied").val(jQuery('#payall').text());
			jQuery("#adjustReason").val(jQuery('#note').val());
			jQuery("#spanyfje").text(jQuery('#paysum').text());
			jQuery("#spansjje").text(jQuery('#payall').text());
			jQuery("#spantzje").text(dealwithnumber(jQuery('#payadd').val()));
			$('#message_confirm_add_true').dialog({
					title:'保存操作',
					buttons: {
						'确认保存': function() { 							
							ajax_140_1();//添加
							$(this).dialog("close"); 
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
				});
			$('#message_confirm_add_true').dialog("open"); 
		}
		//查询
		function findfeepayment()
		{
			if(jQuery("#channelType").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择招生途径！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#channelId").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择合作方！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#oldChannelId").val()==jQuery("#channelId").val())
			{
				glcount=0;
				search001();
			}
			else
			{
				jQuery("#oldChannelId").val(jQuery("#channelId").val());	
				jQuery("#oldChannelType").val(jQuery("#channelType").val());
				search001();
				jQuery('#rebateContent').show();	
					
				jQuery("#feepdIds").val("");
				jQuery("#newNeedFpdIds").val("");
				jQuery("#indexcount").val(0);
				jQuery('#paysum').text("0");
				jQuery('#payall').text("0");
				jQuery('#payadd').val("");
				glcount=0;
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
		
		//统计结算
		function countfpd()
		{
			jQuery('#countAfterFpdIds').val(jQuery('#feepdIds').val());//保持每次统计时有个参照
			ajax_150_1();//统计结算
		}
	
		jQuery(function(){
			
			ajax_640_1();//全局批次
			//合作方级联
			jQuery('#channelType').change(function(){
				ajax_600_1();
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
				title:'无法返款原因',
				width: 750,
				buttons: {
					'关闭': function() { 
						jQuery(this).dialog("close"); 
					} 
				}
			});	
			
		});
	
	
		//ajax回调函数  选取缴费单明细时的验证
		function ajax_test_fpd(data)
		{				
			if(data.isfail)
			{
				jQuery("#feepdIds").val(data.newFeepdIds);
				search003();
				search001();
			}
			else
			{
				jQuery("#showDialog").html('<b>选择失败，请重新选择！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
		//ajax回调函数  移除缴费单明细
		function ajax_del_fpd(data)
		{
			if(data.isfail)
			{
				jQuery("#feepdIds").val(data.newdelFeepdIds);			
				search003();
				search001();
				if(data.newDelNeedFeepdIds!=null && jQuery("#newNeedFpdIds").val()!="")
				{
					var needf=(data.newDelNeedFeepdIds).split(",");
					var oldf=jQuery("#newNeedFpdIds").val().split(",");
					if(needf.length!=oldf.length)
					{
						countfpd();//统计结算
					}
				}
			}
			else
			{
				jQuery("#showDialog").html('<b>移除失败，请重新移除</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
		
		//添加招生返款
		function ajax_pac(data)
		{
			if(data.isfail)
			{
				jQuery("#showDialog").html('<b>该合作方在这个招生返款期内已经添加过招生返款单，不能重复添加！</b>');
				jQuery('#message_returns_tips').dialog('open');	
			}
			else if(data.isback)
			{
				jQuery("#indexcount").val(1);
				glcount=0;
				jQuery("#showDialog").html('<b>保存成功！</b>');
				jQuery('#message_returns_tips').dialog('open');			
				
			}
			else
			{
				jQuery("#showDialog").html('<b>保存失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
		
		//统计招生返款金额
		function ajax_count(data)
		{	
			if(data.isback)
			{
				jQuery('#newNeedFpdIds').val(data.newNeedFpdIds);
				jQuery('#paysum').text(parseFloat(data.allMoney));
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithnumber(jQuery('#payadd').val())));
				search003();
				if(glcount==1)
				{
					addtrue();
				}
			}
			else
			{
				jQuery('#newNeedFpdIds').val(data.newNeedFpdIds);
				jQuery('#paysum').text(parseFloat(data.allMoney));
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(dealwithnumber(jQuery('#payadd').val())));
				
				//有政策人数太少的情况
				jQuery('#hasfpdtab > tbody').empty();
			   	var haslist='';
				if(data.noPeoplePoliceFpdList!=null && data.noPeoplePoliceFpdList.length>0)
				{
					jQuery.each(data.noPeoplePoliceFpdList,function(){	
						haslist+='<tr>';						
						haslist+='<td align="center">'+this.studentName+'</td>';
						haslist+='<td align="center">'+this.branchName+'</td>';
						haslist+='<td align="center">'+this.schoolName+'</td>';
						haslist+='<td align="center">'+this.academyenrollbatchName+'</td>';
						haslist+='<td align="center">'+this.levelName+'</td>';
						//haslist+='<td align="center">'+this.majorName+'</td>';
						haslist+='</tr>';
					});
				}
				else
				{
					haslist+='<tr><td align="center" colspan="5">无相关数据！</td></tr>';
				}
				jQuery('#hasfpdtab > tbody').html(haslist);
				
				//无相关政策的数据
				jQuery('#nopolicefpdtab > tbody').empty();
			   	var noplist='';
				if(data.notPoliceFpdList!=null && data.notPoliceFpdList.length>0)
				{
					jQuery.each(data.notPoliceFpdList,function(){	
						noplist+='<tr>';
						noplist+='<td align="center">'+this.studentName+'</td>';
						noplist+='<td align="center">'+this.branchName+'</td>';
						noplist+='<td align="center">'+this.schoolName+'</td>';
						noplist+='<td align="center">'+this.academyenrollbatchName+'</td>';
						noplist+='<td align="center">'+this.levelName+'</td>';
						//noplist+='<td align="center">'+this.majorName+'</td>';
						noplist+='</tr>';
					});
				}
				else
				{
					noplist+='<tr><td align="center" colspan="5">无相关数据！</td></tr>';
				}
				jQuery('#nopolicefpdtab > tbody').html(noplist);
				search003();
				jQuery('#show_for_feepaymentdetail').dialog('open');
			}		
		}
		
		//合作方级联
		function ajax_channel_call_back(data)
		{
			jQuery('#channelId').empty();
			jQuery('#channelId').append('<option value="0">--请选择--</option>');
			if(data.list!=null && data.list.length>0)
			{
				jQuery.each(data.list,function(){	
			    	jQuery('#channelId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    });
			}		
		}
		
		//ajax回调函数  招生批次(院校)
		function ajax_batch(data)
		{				
			jQuery('#batchId').empty();
			jQuery('#batchId').append('<option value="0">--请选择--</option>');	
			if(data.batchlist!=null&&data.batchlist.length>0)
			{
				jQuery.each(data.batchlist,function(){	
					jQuery('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				});
			}	
			
			jQuery('#levelId').empty();
			jQuery('#levelId').append('<option value="0">--请选择--</option>');
			jQuery('#majorId').empty();
			jQuery('#majorId').append('<option value="0">--请选择--</option>');
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
		
		//ajax回调函数   全局批次(学习中心)
		function ajax_global_batch(data)
		{		
			$('#globalBatchId').empty();
			$('#globalBatchId').append('<option value="0">--请选择--</option>');
			if(data.globalBatchList!=null && data.globalBatchList.length>0)
			{
				$.each(data.globalBatchList,function(){	
			    	$('#globalBatchId').append('<option value="'+this.id+'">'+this.batch+'</option>'); 
			    });
			}
		}
		
	</script>
	
	<!--选取缴费单明细时的验证-->
	<a:ajax 
		successCallbackFunctions="ajax_test_fpd" 
		parameters="{oldFeepdIds:jQuery('#feepdIds').val(),addFeepdIds:getCheckedValues001()}" 
		urls="/finance/channelrebatereview/sel_fpd_for_channel_rebate_academy_review_ajax" 
		pluginCode="120"
	/>
	
	<!--移除缴费单明细-->
	<a:ajax 
		successCallbackFunctions="ajax_del_fpd" 
		parameters="{allFeepdIds:jQuery('#feepdIds').val(),delFeepdIds:getCheckedValues003(),allNeedFpdIds:jQuery('#newNeedFpdIds').val()}" 
		urls="/finance/channelrebatereview/rem_fpd_for_channel_rebate_academy_review_ajax" 
		pluginCode="130"
	/>
	
	<!--添加招生返款-->
	<a:ajax 
		successCallbackFunctions="ajax_pac" 
		parameters="jQuery('#myform').serializeObject()" 
		urls="/finance/channelrebatereview/add_channel_rebate_academy_review_ajax" 
		pluginCode="140"
	/>
	<!--统计招生返款金额-->
	<a:ajax 
		successCallbackFunctions="ajax_count" 
		parameters="{feepdIds:jQuery('#feepdIds').val(),channelRebateTimeId:jQuery('#channelRebateTimeId').val()}" 
		urls="/finance/channelrebatereview/count_checked_channel_rebate_review_fpd_academy_review_ajax" 
		pluginCode="150"
	/>
	
	<!--合作方级联(通过合作方类别)-->
	<a:ajax 
		successCallbackFunctions="ajax_channel_call_back" 
		parameters="{branchId:jQuery('#branchId').val(),channelType:jQuery('#channelType').val()}" 
		urls="/finance/branchchannelpayment/find_branch_channel_list_ajax"
		pluginCode="600" 
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
	
	<!--全局批次(学习中心)-->
	<a:ajax 
		successCallbackFunctions="ajax_global_batch" 
		parameters="{branchId:jQuery('#branchId').val()}" 
		urls="/enrollment/cascade_global_batch_branch_ajax" 
		pluginCode="640"
	/>	
		
</head>
<body>
	<head:head title="单院校计算返款">
		
	</head:head>
	<body:body>
		<form id="myform" method="post">
		
			<input type="hidden" name="feepdIds" id="feepdIds" value=""/>
			<input type="hidden" name="newNeedFpdIds" id="newNeedFpdIds" value=""/>
			
			<input type="hidden" name="orderCeduChannel.amountToPay" id="amountToPay" value="0"/>
			<input type="hidden" name="orderCeduChannel.amountPaied" id="amountPaied" value="0"/>
			<input type="hidden" name="orderCeduChannel.adjustReason" id="adjustReason" value=""/>
			<input type="hidden" name="orderCeduChannel.channelRebateTimeId" id="channelRebateTimeId" value="${channelRebateTimeLimit.id}"/>
			
			<input type="hidden" name="countAfterFpdIds" id="countAfterFpdIds" value=""/>
			
			<input type="hidden" name="indexcount" id="indexcount" value="0"/>
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">单院校计算返款</th>
					<th></th>
				</tr>
			</table>
					
			<table class="add_table">
			  <tbody id="rebateCondition">
				<tr>
					<td class="lable_100">学习中心：</td>
					<td>
						<s:property value="branch.name"/>
						<input type="hidden" name="branchId" id="branchId" value="${branch.id}" />
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
					<td class="lable_100"><span>*</span>合作方：</td>
					<td>
						<select name="channelId" id="channelId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>	
						<input type="hidden" name="orderCeduChannel.channelId" id="oldChannelId" value="0"/>
					</td>
				</tr>	
				<tr>
					 <td class="lable_100">全局批次：</td>
		             <td align="left">
						<select name="student.glbtach" id="globalBatchId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>
					</td>
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
					<td class="lable_100">招生批次：</td>
					<td>
						<select name="student.enrollmentBatchId" id="batchId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>
					</td>
					
				</tr>
				<tr>
					<td class="lable_100">层次：</td>
					<td>
						<select name="student.levelId" id="levelId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>	
					</td>
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
					
				</tr>
				<tr>
					<td class="lable_100">证件号：</td>
					<td>
						<input type="text" name="student.certNo" id="certNo" class="txt_box_150"/>	
					</td>
					<td class="lable_100">到账确认时间：</td>
					<td >
						<s:date name="channelRebateTimeLimit.ceduConfirmTime" format="yyyy-MM-dd" />
						<input type="hidden" name="feePaymentDetail.ceduConfirmTime" id="feePaymentDetail.ceduConfirmTime" value="${channelRebateTimeLimit.ceduConfirmTime}" />
					</td>
					<td class="lable_100">学费下限：</td>
					<td >
						${channelRebateTimeLimit.xueMoney}
						<input type="hidden" name="xueMoney" id="xueMoney" value="${channelRebateTimeLimit.xueMoney}" />
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
			</form> 
			<div id="rebateContent" style="display:none;" >
			 <table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">待返款缴费单明细</th>
					<th></th>
				</tr>
			</table>
			 
					<page:plugin 
						pluginCode="001"
						il8nName="finance_payment"
						subStringLength="20"
						searchListActionpath="list_channel_rebate_review_fpd_academy_review_ajax"
						searchCountActionpath="count_channel_rebate_review_fpd_academy_review_ajax"
						columnsStr="createdTime;paymentCode;studentName;schoolName;academyenrollbatchName;levelName;paymentSubjectName;jiaofeiValue;feeWayId;#status;ceduConfirmTime;isStartCourse;isChannelTypeChecked;#description"
						customColumnValue="0,showtime(createdTime);7,feePaymentCheckedValue(jiaofeiValue,id);8,feeWayIdValue(feeWayId);9,showstatus(status);10,showtime(ceduConfirmTime);11,showIsStartCourse(isStartCourse,id);12,showIsChannelTypeChecked(isChannelTypeChecked,id);13,showdescription(id,rebateStatus,refundLock,enrollmentSource,status)"
						isPage="true"
						isChecked="true"
						checkboxValue="id"
						params="'student.enrollmentSource':jQuery('#oldChannelType').val(),'student.channelId':jQuery('#oldChannelId').val(),'result.order':'student_id','result.sort':'asc'"
						searchFormId="myform"
						isonLoad="false"
						isPackage="true"
						isOrder="false"
						mergeCells="4"
					/>
				 
			<table class="add_table">
			  <tbody >
				<tr>
					<td align="left">
						(选择需要返款的缴费单明细)
						<input type="button" class="btn_black_61" value="选择" onclick="selectfpd()"/>
						
					</td>
				</tr>
				</tbody>
			</table>
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">本次返款缴费单明细</th>
					<th></th>
				</tr>
			</table>
					<page:plugin 
						pluginCode="003"
						il8nName="finance_payment"
						subStringLength="20"
						searchListActionpath="list_channel_rebate_review_fpd_academy_for_now_review_ajax"
						searchCountActionpath="count_channel_rebate_review_fpd_academy_for_now_review_ajax"
						columnsStr="createdTime;paymentCode;studentName;schoolName;academyenrollbatchName;levelName;majorName;paymentSubjectName;jiaofeiValue;paymentByChannel;#status"
						customColumnValue="0,showtime(createdTime);8,feePaymentValue(jiaofeiValue);9,feePaymentValue(paymentByChannel);10,showstatus(status)"
						isPage="true"
						isChecked="true"
						checkboxValue="id"
						params="'feepdIds':$('#feepdIds').val(),'result.order':'student_id','result.sort':'asc'"
						isonLoad="false"
						isPackage="true"
						isOrder="false"
					/>
				<table class="add_table">
				  <tbody >
					<tr>
						<td align="left" width="30%">
							(移除不需要返款的缴费单明细)
							<input type="button" class="btn_black_61" value="移除" onclick="deletefpd()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input style="color:#FFFF66;font-weight:bold;" type="button" class="btn_black_61" value="统计结算" onclick="countfpd()"/>
							<span>(备注：请点击“统计结算”按钮，统计本次应返款金额)</span>
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
					<td colspan="7"><textarea class="txt_box_460" cols="60" rows="6" id="note" style="height:100px"></textarea></td>
				</tr>
				<tr>
					<td colspan="8" align="center">
						<input type="button" class="btn_black_61" value="提交" onclick="doSave()"/>&nbsp;&nbsp;
						<input type="button" class="btn_black_61" onclick="javascript:window.close();" value="关闭"/>
						<br/><span>(备注：请确认实际返款总金额，核实无误后点击“保存”按钮)</span>
					</td>
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
	<div id="message_confirm_add" style="display:none">
		<div align="left" >
			<b>请再次点击“统计结算”按钮，再移除招生返款金额为0的缴费单！</b>
		</div>
	</div>
	<!--弹出层    确认操作-->
	<div id="message_confirm_add_true" style="display:none">
		<div align="left" >
			应返金额：<span id="spanyfje">0</span><br/>
			调整金额：<span id="spantzje">0</span><br/>
			实际返款总金额：<span id="spansjje">0</span><br/>
			<b>是否确认保存？</b>
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
								<th style="text-align:left; font-weight:bold;" class="feehtml">未设置招生返款政策</th>
							</tr>
						</table>				  
						<table class="gv_table_2" id="nopolicefpdtab">
							<thead>
								<tr>
									<th align="center">学生姓名</th>
									<th align="center">学习中心</th>
									<th align="center">院校 </th>
									<th align="center">招生批次 </th>
									<th align="center">层次 </th>
									
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
								<th style="text-align:left; font-weight:bold;" class="feehtml">不满足招生返款标准</th>
							</tr>
						</table>				  
						<table class="gv_table_2" id="hasfpdtab">
							<thead>
								<tr>
									<th align="center">学生姓名</th>
									<th align="center">学习中心</th>
									<th align="center">院校 </th>
									<th align="center">招生批次 </th>
									<th align="center">层次 </th>
									
								</tr>
							</thead>					
							<tbody>						
							</tbody>						
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2"><span style="color:red">注：未设置招生返款政策的学生无法参与返款</span></td>
				</tr>
			</tbody>
		</table>
	</div>	
</body>
</html>
