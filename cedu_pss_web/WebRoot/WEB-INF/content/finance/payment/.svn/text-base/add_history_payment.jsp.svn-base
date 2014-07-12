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
		<!--打印-->
		<jc:plugin name="print" />
		<object id="LODOP"
			classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0 >
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0
				height=0 pluginspage="<ua:attachment url="/upload/cedu_print/install_lodop.exe" />"></embed>
		</object>
		<script type="text/javascript">
			//加载事件
			jQuery(function(){
								
				//选择打印事件
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
				//性别
				if(${student.gender}==0)
				{
					$('#sexSp').html('女');
				}
				else if(${student.gender}==1)
				{
					$('#sexSp').html('男');
				}
				else
				{
					$('#sexSp').html('未知');
				}	
				
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
				
			});
			//提交前的验证
			function showsubmit()
			{
				if(globalcount>0)
				{
					for(var i=1;i<globalcount;i++)
					{
						//alert(jQuery("#feeSubjectId"+i).val());
						if(jQuery("#feeSubjectId"+i).val()==0 || jQuery("#feeSubjectId"+i).val()=="" || jQuery("#feeSubjectId"+i).val()==null)
						{
							jQuery("#showDialog").html('<b>请选择费用科目！</b>');
							$('#message_returns_tips').dialog("open");
							return false;
						}
						//else if(jQuery("#batchId"+i).val()=="" || jQuery("#batchId"+i).val()==null)
						//{
						//	jQuery("#showDialog").html('<b>请选择缴费次数！</b>');
						//	$('#message_returns_tips').dialog("open");
						//	return false;
						//}
						else if(dealwithmoney(jQuery("#academyDiscount"+i).val())==0 && dealwithmoney(jQuery("#academyCeduDiscount"+i).val())==0 && dealwithmoney(jQuery("#ceduDiscount"+i).val())==0 && dealwithmoney(jQuery("#branchDiscount"+i).val())==0 && dealwithmoney(jQuery("#channelDiscount"+i).val())==0 && dealwithmoney(jQuery("#amountPaied"+i).val())==0)
						{
							jQuery("#showDialog").html('<b>'+jQuery("#feeSubjectId"+i).find("option:selected").text()+'：缴纳的所有费用必须大于0！</b>');
							$('#message_returns_tips').dialog("open");
							return false;
						}
					}
				}
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
				//else if(jQuery('#paymentway').val()==PAYMENT_METHOD_WANG_YIN_ZONG_BU || jQuery('#paymentway').val()==PAYMENT_METHOD_WANG_YIN_YUAN_XIAO)
				//{
				//	jQuery("#showDialog").html('<b>暂时不提供网银院校和网银总部这两种缴费方式！</b>');
				//	jQuery('#message_returns_tips').dialog("open");
				//}
				else if(jQuery("input[name='feePayment.isPrint']:checked").length>0 && jQuery('#barCode').val()=="" && $.trim($('#barCode').val())=="")
				{
					jQuery("#showDialog").html('<b>请输入收据号！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else
				{
					dealmoney();//处理钱的小数点问题
					dealprint();//打印数据
					jQuery("#globalIndex").val((globalcount-1));
					ajax_110_1();//添加历史缴费单
				}
			}
			//处理钱的小数点问题
			function dealmoney()
			{
				//缴费次数
				for(var i=1;i<globalcount;i++)
				{
					jQuery("#academyDiscount"+i).val(dealwithmoney(jQuery("#academyDiscount"+i).val()));
					jQuery("#academyCeduDiscount"+i).val(dealwithmoney(jQuery("#academyCeduDiscount"+i).val()));
					jQuery("#ceduDiscount"+i).val(dealwithmoney(jQuery("#ceduDiscount"+i).val()));
					jQuery("#branchDiscount"+i).val(dealwithmoney(jQuery("#branchDiscount"+i).val()));
					jQuery("#channelDiscount"+i).val(dealwithmoney(jQuery("#channelDiscount"+i).val()));
					jQuery("#amountPaied"+i).val(dealwithmoney(jQuery("#amountPaied"+i).val()));
				}
			}
			
			//打印数据
			function dealprint()
			{
				//缴费次数
				var allmoney=0;//总金额
				for(var i=1;i<globalcount;i++)
				{
					if(jQuery("#feeSubjectId"+i).val()==FEE_SUBJECT_TYPE_REGISTRATION)
					{
						jQuery("#baomingfei").val((jQuery("#baomingfei").val()-0)+(jQuery("#amountPaied"+i).val()-0));
					}
					else if(jQuery("#feeSubjectId"+i).val()==FEE_SUBJECT_TYPE_TEST)
					{
						jQuery("#ceshifei").val((jQuery("#ceshifei").val()-0)+(jQuery("#amountPaied"+i).val()-0));
					}
					else if(jQuery("#feeSubjectId"+i).val()==FEE_SUBJECT_TYPE_TUITION)
					{
						jQuery("#xuefei").val((jQuery("#xuefei").val()-0)+(jQuery("#amountPaied"+i).val()-0));
					}
					else if(jQuery("#feeSubjectId"+i).val()==FEE_SUBJECT_TYPE_BOOK)
					{
						jQuery("#jiaocaifei").val((jQuery("#jiaocaifei").val()-0)+(jQuery("#amountPaied"+i).val()-0));
					}
					else if(jQuery("#feeSubjectId"+i).val()==FEE_SUBJECT_TYPE_MAKEUP)
					{
						jQuery("#bukaofei").val((jQuery("#bukaofei").val()-0)+(jQuery("#amountPaied"+i).val()-0));
					}
					else
					{
						jQuery("#qitafei").val((jQuery("#qitafei").val()-0)+(jQuery("#amountPaied"+i).val()-0));
					}
					allmoney+=jQuery("#amountPaied"+i).val()-0;
				}
				jQuery("#allfei").val(allmoney);
				jQuery("#daxieallfei").val(nst(allmoney+""));
				//alert(nst(allmoney+""));
			}
			
			//小写金额编程大写金额
			function nst(op)
			{
			   if(op=="")
			   {
			   		return 0;
			   }
			   var ms = op.replace(/[^\d\.]/g,"").replace(/(\.\d{2}).+$/,"$1").replace(/^0+([1-9])/,"$1").replace(/^0+$/,"0");
			   var txt = ms.split(".");
			   //while(/\d{4}(,|$)/.test(txt[0]))
			   //txt[0] = txt[0].replace(/(\d)(\d{3}(,|$))/,"$1,$2");
			   //t.value = stmp = txt[0]+(txt.length>1?"."+txt[1]:"");
			   return number2num1(ms-0);
			}
			function number2num1(strg)
			{
			   var number = Math.round(strg*100)/100;
			   number = number.toString(10).split('.');
			   var a = number[0];
			   if (a.length > 12)
			   {
			     return "数据过大";//"数值超出范围！支持的最大数值为 999999999999.99";
			   }
			   var e = "零壹贰叁肆伍陆柒捌玖";
			   var num1 = "";
			   var len = a.length-1;
			   for (var i=0 ; i<=len; i++)
			    num1 += e.charAt(parseInt(a.charAt(i))) + [["圆","万","亿"][Math.floor((len-i)/4)],"拾","佰","仟"][(len-i)%4];
			   if(number.length==2 && number[1]!="")
			   {
			     var a = number[1];
			     for (var i=0 ; i<a.length; i++)
			      num1 += e.charAt(parseInt(a.charAt(i))) + ["角","分"][i]; 
			   }
			   num1 = num1.replace(/零佰|零拾|零仟|零角/g,"零");
			   num1 = num1.replace(/零{2,}/g,"零");
			   num1 = num1.replace(/零(?=圆|万|亿)/g,"");
			   num1 = num1.replace(/亿万/,"亿");
			   num1 = num1.replace(/^圆零?/,"");
			   if(num1!="" && !/分$/.test(num1))
			     num1 += "整";
			   return num1;
			}
			
			//测试
			function ceshi()
			{
				//$('#batchId1').empty();
				
				//$("select[name='feeSubjectId1'] option").each(function(){
				//	$('#batchId1').append('<option value="'+jQuery(this).attr("value")+'">'+jQuery(this).text()+'</option>');		
				//	alert(jQuery(this).val());
				//});	
				 //给科目下拉框赋值
			    $("select[name='feeSubjectId1'] option").each(function(){
					$('#feeSubjectId'+(globalcount-1)).append('<option value="'+jQuery(this).attr("value")+'">'+jQuery(this).text()+'</option>');		
					
				});	
				
			}
			
			var globalcount=2;//全局变量	
			//添加缴费次数
			function addfeebatch()
		    {	    	
				var list="";
				list+='<tr id="ttr'+globalcount+'">';
				list+='<td><select name="feeSubjectId'+globalcount+'" id="feeSubjectId'+globalcount+'" class="txt_box_100" >';
				$("select[name='feeSubjectId1'] option").each(function(){
					list+='<option value="'+jQuery(this).attr("value")+'">'+jQuery(this).text()+'</option>';		
				});	
				list+='</select></td>';
				//list+='<td><select name="batchId'+globalcount+'" id="batchId'+globalcount+'"><option value="1">第1次缴费</option></select></td>';
				
				list+='<td><input type="text" name="academyDiscount'+globalcount+'" id="academyDiscount'+globalcount+'" class="txt_box_100" onkeyup="javascript:showcheckmoney(\'academyDiscount'+globalcount+'\');"/></td>';
				list+='<td><input type="text" name="academyCeduDiscount'+globalcount+'" id="academyCeduDiscount'+globalcount+'" class="txt_box_100" onkeyup="javascript:showcheckmoney(\'academyCeduDiscount'+globalcount+'\');"/></td>';
				list+='<td><input type="text" name="ceduDiscount'+globalcount+'" id="ceduDiscount'+globalcount+'" class="txt_box_100" onkeyup="javascript:showcheckmoney(\'ceduDiscount'+globalcount+'\');"/></td>';
				list+='<td><input type="text" name="branchDiscount'+globalcount+'" id="branchDiscount'+globalcount+'" class="txt_box_100" onkeyup="javascript:showcheckmoney(\'branchDiscount'+globalcount+'\');"/></td>';
				list+='<td><input type="text" name="channelDiscount'+globalcount+'" id="channelDiscount'+globalcount+'" class="txt_box_100" onkeyup="javascript:showcheckmoney(\'channelDiscount'+globalcount+'\');"/></td>';
				list+='<td><input type="text" name="amountPaied'+globalcount+'" id="amountPaied'+globalcount+'" class="txt_box_100" onkeyup="javascript:showcheckmoney(\'amountPaied'+globalcount+'\');"/></td>';
				
				list+='<td>';
				list+='<a id="a'+globalcount+'" href="javascript:removetr('+globalcount+');">删除</a>'
				list+='</td>'
				list+='</tr>';	
								
				jQuery(list).appendTo(jQuery('#payment_items > tbody'));
		
				if(globalcount-1>1)
		    	{
		    		jQuery("#a"+(globalcount-1)).hide();
		    	}
		    	globalcount++;  
		    }	
		    function removetr(op)
		    {
		    	jQuery('#ttr'+op).remove();
		    	globalcount--
		    	if(globalcount-1>1)
		    	{
		    		jQuery("#a"+(globalcount-1)).show();
		    	}
		    }
		    //处理输入的钱是否正确
		    function showcheckmoney(id)
		    {
				if(dealwithmoney(jQuery("#"+id).val())==-1)
				{
					jQuery("#"+id).val("");
					jQuery("#showDialog").html('<b>输入格式不正确，只能输入整数和小数！</b>');
					$('#message_returns_tips').dialog("open");
				}
			}
			//费用科目改变事件
			function fubjectchagebatch(id)
			{
				bindex=id;
				feesubjectId=jQuery("#feeSubjectId"+id).val();
				//alert(feesubjectId);
				ajax_119_1();//
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
			
			//ajax回调函数   添加历史缴费单
			function ajax_addhistorypayment(data)
			{
				if(data.isback)
				{
					jQuery("#showDialog").html('<b>已添加过，不要重复添加！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(data.count==1)
				{
					jQuery("#showDialog").html('<b>收据号已使用过或不存在！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(data.count==0)
				{
					jQuery("#indexcount").val(1);
					if(data.feePayment.isPrint==1)
					{	
						$('#message_confirm').dialog({
						title:'确认操作',
						buttons: {
							'确认': function() { 
									$(this).dialog("close");//关闭当前提示
									//打印显示层
									jQuery('#message_print_tips').dialog({
										autoOpen:false,
										modal:true,
										draggable:false,
										resizable:false,
										title:'打印提示',
										buttons: {
											'关闭': function() { 
												jQuery(this).dialog("close"); 
											} 
										}
									});	
									$('#message_print_tips').dialog("open");				
								}, 
							'取消': function() { 
									$(this).dialog("close"); 
								} 
							}
						});
						$('#message_confirm').dialog("open");
					}
					else
					{
						jQuery("#showDialog").html('<b>缴费成功！</b>');
						jQuery('#message_returns_tips').dialog("open");
					}
					
				}
				else
				{
					jQuery("#showDialog").html('<b>缴费失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
			}
			
			//ajax回调函数 判断费用科目的缴费次数是单次还是多次
			var bindex=0;//判断是第几行
			var feesubjectId=0;//费用科目Id
			function ajax_feesubject(data){
				if(data.feeSubject!=null)
			    {
			    	//alert(1);
				    if(data.feeSubject.isMultiplePayment==IS_MULTIPLE_PAYMENT_TRUE)
				    {
				    	$('#batchId'+bindex).empty();
						$('#batchId'+bindex).append('<option value="1">第1次缴费</option>');
						$('#batchId'+bindex).append('<option value="2">第2次缴费</option>');
						$('#batchId'+bindex).append('<option value="3">第3次缴费</option>');
						$('#batchId'+bindex).append('<option value="4">第4次缴费</option>');
						$('#batchId'+bindex).append('<option value="5">第5次缴费</option>');
				    }
				   	else
				    {
				    	$('#batchId'+bindex).empty();
						$('#batchId'+bindex).append('<option value="1">第1次缴费</option>');
				    }
				    	 	
			    }	
			}			
	</script>
	
		<!--缴费方式下拉框-->
		<a:ajax 
			pluginCode="100"
			isOnload="all"
			parameters="jQuery('#myform').serializeObject()" 
			successCallbackFunctions="paymentWayCallback"
			urls="finance/payment/finance_payment_way_list"
		/>
		
		<!--添加历史缴费单-->
		<a:ajax 
			pluginCode="110"
			successCallbackFunctions="ajax_addhistorypayment" 
			parameters="jQuery('#feemyform').serializeObject()" 
			urls="/finance/payment/add_history_fee_payment_ajax" 			
		/>
		
		<!--ajax插件-->
		<a:ajax 
			pluginCode="119"
			successCallbackFunctions="ajax_feesubject" 
			parameters="{id:feesubjectId}" 
			urls="/enrollment/find_fee_subject_ajax" 
		/>
		
	</head>
  
  <body>
  		
		<!-- 头开始 -->
		<head:head title="添加历史缴费单">
			<html:a text="关闭" icon="add" onclick="window.close()" target="_self"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="myform" name="myform">
					<input type="hidden" id="branchId" name="student.branchId" value="${student.branchId}"/>
					<input type="hidden" id="batchId" name="student.enrollmentBatchId" value="${student.enrollmentBatchId}"/>
				</form>
				<form id="feemyform" name="feemyform">
					<input type="hidden" id="studentId" name="feePayment.studentId" value="${student.id}"/>	
					<input type="hidden" id="academyId" name="academyId" value="${student.academyId}"/>
					<input type="hidden" id="globalIndex" name="globalIndex" value="1"/>
					<input type="hidden" id="indexcount" name="indexcount" value="0"/>
					
					<!-- 打印 -->
					<input type="hidden" id="baomingfei" name="baomingfei" value="0"/>
					<input type="hidden" id="ceshifei" name="ceshifei" value="0"/>
					<input type="hidden" id="xuefei" name="xuefei" value="0"/>
					<input type="hidden" id="jiaocaifei" name="jiaocaifei" value="0"/>
					<input type="hidden" id="qitafei" name="qitafei" value="0"/>
					<input type="hidden" id="bukaofei" name="bukaofei" value="0"/>
					<input type="hidden" id="allfei" name="allfei" value="0"/>
					<input type="hidden" id="daxieallfei" name="daxieallfei" value="0"/>
					
					<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">历史缴费单</th>
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
							<td ><span style="color:black;" id="sexSp" ></span></td>
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
						  <table class="add_table" id="payment_items">
							<thead>
								<tr>
									<th>费用项目</th>
									
									<th>院校优惠</th>
									<th>院校弘成共同优惠 </th>
									<th>弘成优惠</th>
									<th>中心优惠</th>
									<th>渠道优惠</th>
									<th>实收金额</th>
									<th><a id="addfee" href="javascript:addfeebatch();">添加</a></th>
								</tr>
							</thead>
							<tbody>	
								<tr>
									<td>
										<s:if test="%{feesubjectlist!=null}">
					                		<s:select list="%{feesubjectlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="feeSubjectId1" id="feeSubjectId1" cssClass="txt_box_100"/>
					                	</s:if>
					                	<s:else>
					                		<select name="feeSubjectId1" id="feeSubjectId1" class="txt_box_100" onchange="javascript:fubjectchagebatch(1);">
												<option value="0">--请选择--</option>
											</select>
					                	</s:else>
									</td>
									
									<td><input type="text" name="academyDiscount1" id="academyDiscount1" onkeyup="javascript:showcheckmoney('academyDiscount1');" class="txt_box_100"/></td>
									<td><input type="text" name="academyCeduDiscount1" id="academyCeduDiscount1" class="txt_box_100" onkeyup="javascript:showcheckmoney('academyCeduDiscount1');"/></td>
									<td><input type="text" name="ceduDiscount1" id="ceduDiscount1" class="txt_box_100" onkeyup="javascript:showcheckmoney('ceduDiscount1');"/></td>
									<td><input type="text" name="branchDiscount1" id="branchDiscount1" class="txt_box_100" onkeyup="javascript:showcheckmoney('branchDiscount1');"/></td>
									<td><input type="text" name="channelDiscount1" id="channelDiscount1" class="txt_box_100" onkeyup="javascript:showcheckmoney('channelDiscount1');"/></td>
									<td><input type="text" name="amountPaied1" id="amountPaied1" class="txt_box_100" onkeyup="javascript:showcheckmoney('amountPaied1');"/></td>
									<td></td>
								</tr>
							</tbody>
						  </table>
				  
						  <table class="add_table" id="subtable" >
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
									<input class="btn_black_61" type="button" value="关闭" onclick="javascript:window.close();"/>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="3">
									<span>备注：</span>选择上门收费:如果收费不成功,该学生的缴费单应该手动作废;如果收费成功，该学生的缴费单还需手动确认收费成功！
								</td>
							</tr>
						</table>				
				</form>
			</body:body>
			
		<!--弹出层-->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="message_confirm" style="display:none">
			<div align="center" id="showconfirm">
				<b>缴费成功，是否现在打印收据？</b>
			</div>
		</div>	
		<!--  打印           -->
		<div id="message_print_tips" style="display:none">
			<div align="center" id="showdiv">
				<!--  a href="" onclick="javascript:Design1();"><b>模板设计</b></a><br/>-->
				<a href="javascript:void(0)" onclick="javascript:Preview1();"><b>打印收据预览</b></a><br/>
				<!-- a href="javascript:void(0)" onclick="javascript:RealPrint();"><b>打印收据</b></a> -->
			</div>
		</div>
		<!--  打印事件           -->
		<script language="javascript" type="text/javascript">
			var LODOP; //声明为全局变量   
			function Preview1() {
				CreateFullBill();
				LODOP.PREVIEW();
			};
			function Design1() {
				CreateFullBill();
				LODOP.PRINT_DESIGN();
			};
			function RealPrint() {		
				CreateFullBill();
				if (LODOP.PRINTA()) 
				   alert("已发出实际打印命令！"); 
				else 
				   alert("放弃打印！"); 
			};
		
			function CreateFullBill() {
				LODOP = getLodop(document.getElementById('LODOP'), document
						.getElementById('LODOP_EM'));
		
				LODOP.PRINT_INITA(-9,-22,834,328, "弘成学习中心专用收据");
				
				LODOP.ADD_PRINT_TEXT(98,603,125,21, "${student.mobile}");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(99,184,103,21, "${student.name}");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(125,184,155,21, "${student.schoolName}");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(125,396,88,20, "${student.academyenrollbatchName}");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);				
				LODOP.ADD_PRINT_TEXT(124,675,113,20, "${student.majorName}");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(159,396,128,21, jQuery('#jiaocaifei').val());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(160,185,129,21, jQuery('#xuefei').val());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(187,396,131,20, jQuery('#bukaofei').val());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(155,615,140,21,jQuery('#baomingfei').val());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(186,186,130,20, jQuery('#ceshifei').val());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
				LODOP.ADD_PRINT_TEXT(213,259,257,20, jQuery('#daxieallfei').val());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(125,533,81,20,"${student.levelName}");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(296,657,38,21, jQuery('#createdTime').val().substring(0,4));
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(295,707,28,21, jQuery('#createdTime').val().substring(5,7));
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(295,746,28,21, jQuery('#createdTime').val().substring(8,10));
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(214,556,169,20, jQuery('#allfei').val());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(99,395,186,21, "${student.certNo}");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(186,625,132,20, jQuery('#qitafei').val());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(254,406,131,20, "${namefee}");
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(253,611,127,20, "${student.name}");
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				
				LODOP.ADD_PRINT_TEXT(295,86,208,20,"${code}");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
			};
		</script>
  </body>
</html>
