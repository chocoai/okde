<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>缴费管理</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<!--缴费方式-->
		<script type="text/javascript">
			//加载事件
			jQuery(function(){
								
				//选择院校事件
				jQuery('#isPrint').change(function(){
					if(!this.checked)
					{
						jQuery('#receipdiv').hide();
					}
					else
					{
						jQuery('#receipdiv').show();
					}
				});
				
				//初始化弹出框
				//所有优惠券
				jQuery('#show_for_prompt').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'可使用优惠卷',
					width: 650,
					buttons: {
						'确定': function() { 
							//优惠金额
							jQuery('#discountfee').html(jQuery('#reducemoney').html());
							jQuery('#discountAmount').val(jQuery('#reducemoney').html());
							//优惠后金额
							jQuery('#paymentfee').html(jQuery('#reduceaftermoney').html());
							//优惠券Ids集合
							$("#discountIds").val(disids);
							jQuery(this).dialog("close"); 
						}, 
						'清空': function() { 
							jQuery('#reduceaftermoney').html(jQuery('#allmoney').html());
							jQuery('#reducemoney').html('0');
							//checkbox复原
							var nobranch=$("input[name='discountbox']")
							nobranch.each(function(i){
								$(this).attr("disabled",false);
								$(this).attr("checked",false);				
							}); 
							disids="";
							//$("#discountIds").val("");//清空使用优惠券的隐藏域ids集合
						}, 
						'关闭': function() { 
							//$("#discountIds").val("");//清空使用优惠券的隐藏域ids集合
							jQuery(this).dialog("close"); 
						} 
					}
				});	
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
			//提交前的验证
			function showsubmit()
			{
				if(jQuery('#createdTime').val()=="")
				{
					jQuery("#showDialog").html('<b>请选择缴费时间！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(jQuery('#paymentway').val()==0)
				{
					jQuery("#showDialog").html('<b>请选择缴费方式！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(jQuery("input[name='feePayment.isPrint']:checked").length>0 && jQuery('#barCode').val()=="" && $.trim($('#barCode').val())=="")
				{
					jQuery("#showDialog").html('<b>请输入收据号！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else
				{
					ajax_130_1();//添加测试费
				}
			}
			
			//显示优惠券
			function usingdiscount()
			{
				ajax_110_1();//显示优惠券
			}
			
			
			//缴费方式列表
			function paymentWayCallback(data){
				jQuery("#paymentway").empty();
			    jQuery("#paymentway").append('<option value="0">--请选择--</option>');
			    if(data.academyBranchFeeWays!=null && data.academyBranchFeeWays.length>0)
			    {
			    	$.each(data.academyBranchFeeWays,function(){	
			    		jQuery("#paymentway").append('<option value="'+this.feeWayId+'">'+this.feeWayId.getPaymentWay()+'</option>'); 
			    	});
			   	}
			}
			//ajax回调函数   显示可以使用的学生优惠
			var disids="";//要使用的优惠id集合  点确认才给隐藏域赋值
			function ajax_showdiscount(data)
			{				
				$('#showdiscount > tbody').empty();
			   	var list='';
			    if(data.applist!=null && data.applist.length>0)
			    {
			    	$.each(data.applist,function(){	
			    		list+='<tr>';
			    		list+='<td align="center"><input type="checkbox" name="discountbox" value="'+this.id+'"></td>';
			    		list+='<td align="center">'+this.code+'</td>';
			    		list+='<td align="center">'+this.feeSubjectName+'</td>';
			    		list+='<td align="center">'+this.startTime.substring(0,10)+"~"+this.endTime.substring(0,10)+'</td>';
			    		if(this.discountWay==MONEY_FORM_AMOUNT)
			    		{
			    			list+='<td align="center">'+this.money+'元</td>';
			    		}
			    		else
			    		{
			    			list+='<td align="center">'+this.money+'%</td>';
			    		}
			    		list+='</tr>';
			    	});
			    }
			    else
			    {
			    	list+='<tr><td colspan="6" align="center">没有已申请的优惠政策！</td></tr>';
			    }
			    $('#showdiscount > tbody').html(list);
			    	//初始化优惠卷的金额
			    	//jQuery('#reduceaftermoney').html(jQuery('#allmoney').html());
					//jQuery('#reducemoney').html('0');	
					//jQuery("#discountIds").val("");//清空使用优惠券的隐藏域ids集合
					////由于开始使用了再点的时候给它复原回去
					jQuery('#reduceaftermoney').html(jQuery('#paymentfee').html());
					jQuery('#reducemoney').html(jQuery('#discountfee').html());	
					if(jQuery("#discountIds").val()!="")
					{
						var discountIds=(jQuery("#discountIds").val()).split(",");
						//alert(jQuery("#discountIds").val());
						//复原选择的值
						var nobranch=$("input[name='discountbox']")
						nobranch.each(function(i){
							for(var i=0;i<discountIds.length;i++)
							{
								if(discountIds[i]==this.value)
								{
									$(this).attr("disabled",true);
									$(this).attr("checked",true);
								}	
							}			
						}); 
					}
			    $('#show_for_prompt').dialog('open');
			    disids="";
			    disids=$("#discountIds").val();
			    //加载checkbox点击事件
			    $('[name=discountbox]').change(function(){
					if(this.checked)
					{
						discountpolicyId=this.value;
						$(this).attr("disabled",true);
						//隐藏域存放使用的优惠券Ids
						//if($("#discountIds").val()=="")
						if(disids=="")
						{
							//$("#discountIds").val(this.value+"");
							disids=this.value+"";
						}
						else
						{
							//$("#discountIds").val($("#discountIds").val()+","+this.value);
							disids=disids+","+this.value;
						}
						ajax_120_1();//使用优惠券
					}
				});
			}
			
			//ajax回调函数   使用优惠券
			var discountpolicyId=0;//
			function ajax_usingdiscount(data)
			{
				if(data.reduceaftermoney.length>0 && data.reducemoney.length>0)
				{
					jQuery('#reduceaftermoney').html(data.reduceaftermoney);
					jQuery('#reducemoney').html(data.reducemoney);
				}
				else
				{
					jQuery('#show_for_prompt').dialog("close");
					jQuery("#showDialog").html('<b>使用优惠券失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
			}
			
			//ajax回调函数   添加测试费
			function ajax_addtestpayment(data)
			{
				if(data.replayadd)
				{
					jQuery("#showDialog").html('<b>已添加过，不要重复添加！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(data.barcodeadd)
				{
					jQuery("#showDialog").html('<b>收据号已使用过或不存在！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(data.isfail)
				{
					jQuery("#showDialog").html('<b>缴费成功！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else
				{
					jQuery("#showDialog").html('<b>缴费失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
			}
			
		</script>
		<!--缴费方式下拉框-->
		<a:ajax isOnload="all" parameters="jQuery('#myform').serializeObject()" successCallbackFunctions="paymentWayCallback" pluginCode="payment" urls="finance/payment/finance_payment_way_list"/>
		
		<!--优惠券-->
		<a:ajax successCallbackFunctions="ajax_showdiscount" parameters="{studentId:jQuery('#studentId').val(),feeSubjectId:jQuery('#feeSubjectId').val(),feePaymentId:jQuery('#feePaymentId').val()}" urls="/finance/payment/list_student_feesubject_discount_application_ajax" pluginCode="110"/>
		
		<!--使用优惠券-->
		<a:ajax successCallbackFunctions="ajax_usingdiscount" parameters="{discountpolicyId:discountpolicyId,allmoney:jQuery('#allmoney').html(),reducemoney:jQuery('#reducemoney').html()}" urls="/finance/payment/using_student_feesubject_discount_application_ajax" pluginCode="120"/>
		
		<!--添加测试费-->
		<a:ajax successCallbackFunctions="ajax_addtestpayment" parameters="jQuery('#myforms').serializeObject()" urls="/finance/payment/add_test_payment_ajax" pluginCode="130"/>
		
	</head>
  
  <body>
  		
		<!-- 头开始 -->
		<head:head title="缴费管理">
			<html:a text="生成待缴费单" icon="add" href="/finance/pending/list_pending_fee_payment"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="myform" name="myform">
					<input type="hidden" id="branchId" name="student.branchId" value="${student.branchId}"/>
					<input type="hidden" id="batchId" name="student.enrollmentBatchId" value="${student.enrollmentBatchId}"/>
				</form>
			<form id="myforms" name="myforms" action="add_test_payment" method="post" >
					
					<input type="hidden" id="studentId" name="feePayment.studentId" value="${student.id}"/>
					<input type="hidden" id="feeSubjectId" name="feePaymentDetail.feeSubjectId" value="${feeSubjectId}"/>
					<input type="hidden" id="feePaymentId" name="feePaymentDetail.batchId" value="${feeStandard.feeBatchId}"/>				
					<input type="hidden" id="feeStandartId" name="feePaymentDetail.policyStandardId" value="${feeStandard.id}"/>
					<input type="hidden" id="pendingPaymentId" name="feePaymentDetail.pendingPaymentId" value="${pendingFeePayment.id}"/>
					
					<input type="hidden" id="discountAmount" name="feePaymentDetail.discountAmount" value="0"/>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">缴费管理 >> 缴费单</th>
					</tr>
				</table>
				
				 <table class="add_table" border="0" cellpadding="2" cellspacing="2">
				  	<tr>
				  		<td style="width:15%" align="right">缴费单号：</td>
						<td style="width:18%">${code}
						<input type="hidden" id="code" name="feePayment.code" value="${code}"/></td>
						<td style="width:15%" align="right"><span>*</span>缴费时间：</td>
						<td style="width:18%">
							<input type="text" name="feePayment.createdTime" value="${feedate}" id="createdTime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen'})" readonly="readonly"/>
						</td>
		
						<td style="width:15%" align="right"></td>
						<td style="width:19%"></td>
				  	</tr>
					<tr>
				  		<td  align="right">院校：</td>
						<td >${student.schoolName}</td>
						<td  align="right">姓名：</td>
						<td >${student.name}</td>
						<td  align="right">性别：</td>
						<td >${student.gender==0?'女':'男'}</td>
				  	</tr>
					<tr>
				  		<td  align="right">批次：</td>
						<td >${student.academyenrollbatchName}</td>
		
						<td  align="right">层次：</td>
						<td >${student.levelName}</td>
				  		<td  align="right">专业：</td>
						<td >${student.majorName}</td>
		
				  	</tr>
				  </table>
				 <s:if test="%{pendingFeePayment!=null}">
					  <table class="add_table" id="payment_items">
						<thead>
							<tr>
								<th>学制</th>
								<th>费用项目</th>
								<th>应收金额</th>
								<th>优惠金额   <a href="javascript:usingdiscount();" title="使用优惠卷"><img src="<ui:img url="/images/icon_discount.gif" />" /><font size="-2">(使用优惠卷)</font></a></th>
			
								<th>实收金额</th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<td>
									${pendingFeePayment.modeName}
								</td>
								<td>
									${pendingFeePayment.feeSubjectName}
								</td>
								<td>
									${pendingFeePayment.amountSurplus}
								</td>
								<td>
									<span style="color:black !important" id="discountfee">0</span>
									<input type="hidden" name="feePaymentDetail.discountPolicyDetailId" id="discountIds" value=""/>
								</td>
								<td>
									<span style="color:black !important" id="paymentfee">${pendingFeePayment.amountSurplus}</span>
								</td>		
							</tr>
						</tbody>
						
					  </table>
	
			  
					  <table class="add_table">
					  	<tr>
							<td align="right" width="50%">
								缴费方式(仅显示该院校该中心的缴费方式)：<select id="paymentway" name="feePayment.feeWayId" class="txt_box_150"></select>
							</td>
							<td align="center" width="20%">
								<input type="radio" value="1" name="isFee" id="isFee" checked="checked"/><label>现场收费</label>
								<input type="radio" value="0" name="isFee" id="isFee"/><label>上门收费</label>
							</td>
							<td align="left" width="30%">
								<input type="checkbox" value="1" name="feePayment.isPrint" id="isPrint"/><label>是否打印</label>
								<span id="receipdiv" style="display:none"><span>*</span>收据号：<input type="text" name="feePayment.barCode" id="barCode" class="txt_box_150" /></span>
							</td>
			
						</tr>
					  	<tr>
							<td align="center" colspan="3">
								<input class="btn_black_130" type="button" value="保存" onclick="showsubmit();" id="submit"/>
								<input class="btn_black_61" type="button" value="取消" onclick="javascript:history.go(-1);"/>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="3">
								<span>备注：</span>选择上门收费:如果收费不成功,该学生的缴费单应该手动作废;如果收费成功，该学生的缴费单还需手动确认收费成功！
							</td>
						</tr>
					  </table>
					</s:if>
					<s:else>
						<table class="add_table">
							<tr>
								<td align="center"><span><h2>没有要交的测试费！</h2></span></td>
							</tr>
						</table>	
						<table class="add_table">
							<tr>
								<td align="center" >
									<input class="btn_black_61" type="button" value="返回" id="" onclick="javascript:history.go(-1);"/>									
								</td>
							</tr>
						</table>
					</s:else>
				</form>
		</body:body>
	
	
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
	<div id="show_for_prompt" style="display:none">
		<table class="gv_table_2" id="showdiscount">
			<thead>
				<tr>
					<th></th>
					<th>优惠编号</th>
					<th>费用科目</th>
					<th>有效期</th>
					<th>优惠标准</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<table class="add_table" id="showdiscount1">
			<tfoot>
				<tr>
					<td align="center" colspan="4">
						缴费金额：<span style="" id="allmoney">${pendingFeePayment.amountSurplus}</span>
						优惠金额：<span style="" id="reducemoney">0</span>
						优惠后金额：<span style="" id="reduceaftermoney">${pendingFeePayment.amountSurplus}</span>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>	
  </body>
</html>
