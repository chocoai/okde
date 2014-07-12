<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>缴费单查询(中心)</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!--打印-->
		<jc:plugin name="print" />
		<object id="LODOP"
			classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0 >
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0
				height=0 pluginspage="<ua:attachment url="/upload/cedu_print/install_lodop.exe" />"></embed>
		</object>
		
		<%@ include file="common.jsp" %>
		<script type="text/javascript">
			function successCallbackFunction(data)
			{
				if(data.isback)
				{
					jQuery("#showDialog").html('<b>收据号已使用或不存在，请重新输入收据号！</b>');
					jQuery('#message_returns_tips').dialog("open");	
				}
				else if(data.isfail)
				{
					jQuery("#showDialog").html('<b>打印失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else
				{
					jQuery("#stumobile").val(data.student.mobile);
					jQuery("#stuname").val(data.student.name);
					jQuery("#stuschool").val(data.student.schoolName);
					
					jQuery("#stubatch").val(data.student.academyenrollbatchName);
					
					jQuery("#stulevel").val(data.student.levelName);
					
					jQuery("#stumajor").val(data.student.majorName);
					
					jQuery("#baomingfei").val(data.baomingfei);
					
					jQuery("#xuefei").val(data.xuefei);
					
					jQuery("#jiaocaifei").val(data.jiaocaifei);
					
					jQuery("#qitafei").val(data.qitafei);
					
					jQuery("#bukaofei").val(data.bukaofei);
					
					jQuery("#ceshifei").val(data.ceshifei);
					
					jQuery("#daxieallmoney").val(nst(data.allmoney+""));
					
					jQuery("#namefee").val(data.namefee);
					
					//jQuery("#stuname").val(data.student.name);
					
					jQuery("#time1").val(data.feePayment.createdTime.substring(0,4));
					
					jQuery("#time2").val(data.feePayment.createdTime.substring(5,7));
					
					jQuery("#time3").val(data.feePayment.createdTime.substring(8,10));
					
					jQuery("#allmoney").val(data.allmoney);
					
					jQuery("#stucertNO").val(data.student.certNo);
					
					jQuery("#jfcode").val(data.feePayment.code);
					//refresh001();
					//打印显示层
					jQuery('#message_print_tips').dialog({
						autoOpen:false,
						modal:true,
						draggable:false,
						resizable:false,
						title:'打印提示',
						buttons: {
							'关闭': function() { 
								$(this).dialog("close"); 
							} 
						}
					});	
					$('#message_print_tips').dialog("open");
				}
		
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
			//ajax回调函数   更改缴费单打印状态
			function statusFeePaymentPrint(data)
			{
				$('#message_print_tips').dialog("close");
				refresh001();//刷新列表
			}
			//统计缴费单金额 
			function countallmoney(data)
			{
				//alert(data.allFeePaymentMoney);
				jQuery("#moneyall").html("合计：<font style='color:red'><b>"+data.allFeePaymentMoney+"</b></font>元");
			}
		</script>
		<!-- 获取打印数据 -->
		<a:ajax parameters="jQuery('#myform1').serializeObject()" successCallbackFunctions="successCallbackFunction" pluginCode="0001" urls="finance/payment/update_payment_print_status_printing"/>
		<!-- 更改缴费单打印状态 -->
		<a:ajax parameters="jQuery('#myform1').serializeObject()" successCallbackFunctions="statusFeePaymentPrint" pluginCode="0002" urls="finance/payment/update_payment_print_status_ajax"/>
		<!-- 统计缴费单金额 -->
		<a:ajax 
			parameters="jQuery('#myform').serializeObject()" 
			successCallbackFunctions="countallmoney" 
			pluginCode="1110" 
			urls="finance/payment/count_payment_serach_all_money_ajax"
		/>
		<script type="text/javascript">
			
			//查询数据
			function showsearch()
			{
				if(jQuery("#feemoney").val()!="" && dealwithmoney(jQuery("#feemoney").val())==-1)
				{
					jQuery("#showDialog").html('<b>缴费金额查询条件输入格式不正确，只能输入整数和小数！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else
				{
					if(jQuery("#feemoney").val()!="")
					{
						jQuery("#feemoney").val(dealwithmoney(jQuery("#feemoney").val()));
					}
					search001();
				}
			}
			
			isLoadPaymentWay=true;
			function feePaymentValue(feePayment){
				return (feePayment+"").toMoney();
			}
			function pamentTypeValue(pamentType)
			{
				if(pamentType==FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE)
				{
					return "正式缴费单";
				}
				else if(pamentType==FEE_PAYMENT_TYPE_PRE_BILLING)
				{
					return "预缴费单";
				}
				else if(pamentType==FEE_PAYMENT_TYPE_REFUND_SINGLE)
				{
					return "退费单";
				}
				else
				{
					return "--";
				}
				
			}
			function operatingValue(id,isPrint,pamentType){
				//if(isPrint==1)
				//	return "--";
				if(pamentType==FEE_PAYMENT_TYPE_REFUND_SINGLE)
				{
					return "";
				}
				return "<a href='#' onclick='paymentConfirm("+id+")'>打印</a>";
			}
			function isPrintValue(isPrint){
				return isPrint==0?"未打印":"已打印";
			}
			function statusValue(status){
			
				return status.getPaymentStatus();
			}
			function feeWayIdValue(feeWayId){
				return feeWayId.getPaymentWay();
			}
			function showcode(code,id,pamentType)
			{
				if(pamentType==FEE_PAYMENT_TYPE_REFUND_SINGLE)
				{
					return "<a href='"+WEB_PATH+"/finance/refund/view_refund_payment?feePaymentId="+id+"' target='_blank'>"+code+"</a>";
				}
				return "<a href='"+WEB_PATH+"/finance/payment/payment_view?feePaymentId="+id+"' target='_blank'>"+code+"</a>";
			}
			function paymentConfirm(id){
				//alert(id);
				$("#fid").val(id);
				jQuery("#barCodes").val("");//清空收据号
				$('#message_confirm').dialog({
					title:'确认操作',
					buttons: {
						'确认': function() { 
							if(jQuery("#barCodes").val()=="" || jQuery.trim(jQuery("#barCodes").val())=="")
							{
								jQuery("#showDialog").html('<b>请输入收据号！</b>');
								jQuery('#message_returns_tips').dialog("open");	
							}
							else
							{
								$(this).dialog("close");//关闭当前提示
								$("#barCode").val(jQuery.trim(jQuery("#barCodes").val()));
								ajax_0001_1();
							}		
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				$('#message_confirm').dialog("open");
			}
			$(document).ready(function(){
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
			function countCallback001(data){
				ajax_1110_1();//统计缴费单金额
			}
		</script>
		
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="缴费单查询(中心)">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="myform1" name="myform1">
					<input type="hidden" name="feePayment.isPrint" value="1"/>
					<input type="hidden" id="fid" name="feePayment.id" value=""/>
					<input type="hidden" id="barCode" name="feePayment.barCode" value=""/>
				</form>
				
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">缴费单查询</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">
				<input type="hidden" name="stumobile" id="stumobile" value=""/>
				<input type="hidden" name="stuname" id="stuname" value=""/>
				<input type="hidden" name="stuschool" id="stuschool" value=""/>
				<input type="hidden" name="stubatch" id="stubatch" value=""/>
				<input type="hidden" name="stulevel" id="stulevel" value=""/>
				<input type="hidden" name="stumajor" id="stumajor" value=""/>
				<input type="hidden" name="baomingfei" id="baomingfei" value=""/>
				<input type="hidden" name="xuefei" id="xuefei" value=""/>
				<input type="hidden" name="jiaocaifei" id="jiaocaifei" value=""/>
				<input type="hidden" name="qitafei" id="qitafei" value=""/>
				<input type="hidden" name="bukaofei" id="bukaofei" value=""/>
				<input type="hidden" name="ceshifei" id="ceshifei" value=""/>
				<input type="hidden" name="daxieallmoney" id="daxieallmoney" value=""/>
				<input type="hidden" name="namefee" id="namefee" value=""/>
				<!-- input type="hidden" name="stuname" id="stuname" value=""/> -->
				<input type="hidden" name="time1" id="time1" value=""/>
				<input type="hidden" name="time2" id="time2" value=""/>
				<input type="hidden" name="time3" id="time3" value=""/>	
				<input type="hidden" name="allmoney" id="allmoney" value=""/>
				<input type="hidden" name="stucertNO" id="stucertNO" value=""/>
				
				<input type="hidden" name="jfcode" id="jfcode" value=""/>
					
				<table class="add_table" border="0">
					<tr>
						<td align="right">学习中心：</td>
		                <td align="left">
							${branch.name}
							<input type="hidden" id="branchId" name="student.branchId" value="${branch.id}"/>
						</td>
		                
		                <td align="right">全局批次：</td>
		                <td align="left">
							<select name="student.glbtach" id="globalBatchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td align="right">院校：</td>
		                <td align="left">
		                	<select name="student.academyId" id="academyId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>	
						</td>
		               
						
	              	</tr>
				  	<tr>
				  		<td align="right">招生批次：</td>
		                <td align="left">
							<span style="color: black !important;" id="batch" name="batch"></span>
							<input type="hidden" id="batchId" name="student.enrollmentBatchId" value="0"/>
						</td>
				  		<td align="right">层次：</td>
		                <td>
							<select name="student.levelId" id="levelId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td align="right">专业：</td>
		                <td align="left">
							<select name="student.majorId" id="majorId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
						
						
	             	</tr>
	             	<tr >
	             		<td align="right">姓名：</td>
		                <td align="left">
		                	<input type="text" name="student.name" id="name" class="txt_box_150"/>					
						</td>
						<td align="right">证件号：</td>
		                <td align="left">
		                	<input type="text" name="student.certNo" id="certNo" class="txt_box_150"/>					
						</td>
	             		<td align="right">缴费单号：</td>
		                <td align="left">
		                	<input type="text" name="feePayment.code" id="code" class="txt_box_150"/>					
						</td>
	             		<!-- td align="right">POS流水号：</td>
	             		<td><input type="text" name="feePayment.posSerialNo" id="pno" class="txt_box_150"/></td> -->
	             		
						
	             	</tr>
	             	<tr >
	             		<td align="right">缴费金额：</td>
		                <td align="left">
		                	<input type="text" name="feemoney" id="feemoney" class="txt_box_150"/>					
						</td>
						<td align="right">缴费单类别：</td>
		                <td align="left">
		                	<select name="feePayment.pamentType" id="pamentType" class="txt_box_150">
								<option value="0">--请选择--</option>
								<option value="<%=Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE %>">正式缴费单</option>
								<option value="<%=Constants.FEE_PAYMENT_TYPE_PRE_BILLING %>">预缴费单</option>						
								<option value="<%=Constants.FEE_PAYMENT_TYPE_REFUND_SINGLE %>">退费单</option>
							</select>					
						</td>
						<td align="right">是否打印：</td>
		                <td align="left">
		                	<select name="feePayment.isPrint" id="isPrint" class="txt_box_150">
								<option value="-1">--请选择--</option>
								<option value="0">未打印</option>
								<option value="1">已打印</option>						
							</select>					
						</td>
					</tr>
					<tr>	
	             		<td align="right">缴费方式：</td>
	             		<td>
	             			<!--select name="feePayment.feeWayId" id="paymentway" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>  -->
							<s:select name="feePayment.feeWayId" id="feeway" cssClass="txt_box_150" headerKey="0" headerValue="--请选择--" list="feeWayList" listKey="id" listValue="name"></s:select>
						</td>
	             		<td align="right">缴费单状态：</td>
	             		<td>
	             			<select name="feePayment.status" id="paymentStatus" class="txt_box_150">
								<option value="0">--请选择--</option>
								<option value="<%=Constants.PAYMENT_STATUS_ZUO_FEI %>">已作废</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_KAI_DAN %>">已开单</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN %>">已收费确认</option>						
								<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN %>">已填汇款单</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU %>">已汇款总部</option>				
								<option value="<%=Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN %>">总部确认</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN %>">已填打款单</option>								
								<option value="<%=Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN %>">已确认待汇款</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO %>">已打款院校</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN %>">已填返款单</option>
								<option value="<%=Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN %>">返款确认</option>								 
								<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN %>">已填招生返款</option>
								<option value="<%=Constants.PAYMENT_STATUS_SHANG_WU_YI_SHENG_HE %>">商务已审核</option> 
								<option value="<%=Constants.PAYMENT_STATUS_CAI_WU_YI_SHEN_HE %>">财务已审核</option>
								<option value="<%=Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO %>">已汇款渠道</option>  
							</select>
						</td>
 						
						<td align="right">收据号：</td>
		                <td align="left">
							<input type="text" name="feePayment.barCode" id="barCode" class="txt_box_150"/>
						</td>
	             	</tr>
	             	<tr>
				  		
				  		<td align="right">缴费日期起：</td>
		                <td align="left">
							<input type="text" name="starttime" id="starttime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'endtime\',{d:0});}'})" readonly="readonly"/>					  			
						</td>
		                <td align="right">缴费日期止：</td>
		                <td align="left">
							<input type="text" name="endtime" id="endtime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'starttime\',{d:0});}'})" readonly="readonly"/>
						</td>
						<td ></td>
		                <td align="left">
							<input type="button" class="btn_black_61"  onclick="showsearch();" value="查询"/>
						</td>
	             	</tr>
				</table>
				</form>
									<page:plugin 
										pluginCode="001"
										il8nName="finance_payment"
										searchCountActionpath="finance/payment/count_payment_serach_page_ajax"
										searchListActionpath="finance/payment/list_payment_serach_page_ajax"
										columnsStr="createdTime;paymentcode;studentName;schoolName;academyenrollbatchName;levelName;majorName;feeWayId;feePayment;rechargeAmount;discountAmount;totalAmount;pamentType;status;barCode;isPrint;#public.operating"
										customColumnValue="1,showcode(code,id,pamentType);7,feeWayIdValue(feeWayId);8,feePaymentValue(feePayment);9,feePaymentValue(rechargeAmount);10,feePaymentValue(discountAmount);11,feePaymentValue(totalAmount);12,pamentTypeValue(pamentType);13,statusValue(status);15,isPrintValue(isPrint);16,operatingValue(id,isPrint,pamentType)"
										pageSize="10"
										isPackage="false"
										params="'result.order':'createdTime','result.sort':'desc'"
										searchFormId="myform"
										customToolbarID="moneyall"
										listCallback="countCallback"
										columnsWidth="[6,120px]"
									/>
				
		</body:body>
		<!--  弹出层           -->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="message_confirm" style="display:none">
			<div>收据号：<input type="text" name="barCodes" id="barCodes" class="txt_box_150"/></div>
		</div>
		<!--  打印           -->
		<div id="message_print_tips" style="display:none">
			<div align="center" id="showdiv">
				<!--  a href="" onclick="javascript:Design1();"><b>模板设计</b></a><br/>-->
				<a href="javascript:void(0)" onclick="javascript:Preview1();"><b>打印收据预览</b></a><br/>
				<!-- a href="javascript:void(0)" onclick="javascript:RealPrint();"><b>打印收据</b></a><br/> -->
				<br/><a href="javascript:ajax_0002_1();"><b>手写收据</b></a><br/>
			</div>
		</div>
		<!--  打印事件           -->
		<script language="javascript" type="text/javascript">
			var LODOP; //声明为全局变量   
			function Preview1() {
				 ajax_0002_1();//更改缴费单状态
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
				{ 
				   //alert("已发出实际打印命令！"); 
				   
				   ajax_0002_1();//更改缴费单状态
				   jQuery("#showDialog").html('<b>已发出实际打印命令！</b>');
				   jQuery('#message_returns_tips').dialog("open");	
				   
				}
				else 
				{
				  	jQuery("#showDialog").html('<b>放弃打印！</b>');
				  	jQuery('#message_returns_tips').dialog("open");	
				}
			};
		
			function CreateFullBill() {
				LODOP = getLodop(document.getElementById('LODOP'), document
							.getElementById('LODOP_EM'));
			
					LODOP.PRINT_INITA(-9,-22,834,328, "弘成学习中心专用收据");
					
					LODOP.ADD_PRINT_TEXT(98,603,125,21, jQuery("#stumobile").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(99,184,103,21, jQuery("#stuname").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(125,184,155,21, jQuery("#stuschool").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(125,396,88,20, jQuery("#stubatch").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(124,675,113,20, jQuery("#stumajor").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(159,396,128,21, jQuery("#jiaocaifei").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(160,185,129,21, jQuery("#xuefei").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(187,396,131,20, jQuery("#bukaofei").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);					
					LODOP.ADD_PRINT_TEXT(155,615,140,21,jQuery("#baomingfei").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(186,186,130,20, jQuery("#ceshifei").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.SET_PRINT_STYLEA(0,"SpacePatch",1);
					LODOP.ADD_PRINT_TEXT(213,259,257,20,jQuery("#daxieallmoney").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(125,533,81,20,jQuery("#stulevel").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(296,657,38,21,jQuery("#time1").val());
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(295,707,28,21, jQuery("#time2").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(295,746,28,21, jQuery("#time3").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);	
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);				
					LODOP.ADD_PRINT_TEXT(214,556,169,20, jQuery("#allmoney").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(99,395,186,21, jQuery("#stucertNO").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);				
					LODOP.ADD_PRINT_TEXT(186,625,132,20, jQuery("#qitafei").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(254,406,131,20, jQuery("#namefee").val());
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					LODOP.ADD_PRINT_TEXT(253,611,127,20, jQuery("#stuname").val());
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);
					
					LODOP.ADD_PRINT_TEXT(295,86,208,20,jQuery("#jfcode").val());
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Bold",1);	
									
			};
		</script>
  </body>
</html>
