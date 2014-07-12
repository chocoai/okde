<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生报名</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		
		<jc:plugin name="page" />
		
		<jc:plugin name="loading" />
		<!--打印-->
		<jc:plugin name="print" />
		<object id="LODOP"
			classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0 >
			<embed id="LODOP_EM" type="application/x-print-lodop" width=0
				height=0 pluginspage="<ua:attachment url="/upload/cedu_print/install_lodop.exe" />"></embed>
		</object>
	<script type="text/javascript">
			var student_academyId=0;//院校
			var student_enrollmentBatchId=0;//批次
			var student_levelId=0;//层次
			var student_majorId=0;//专业
			var sValue=0;
			function initSelect(doc){
					var school=$("#school");
					var pici=$("#pici");
					var cengci=$("#cengci");
					var zhuanye=$("#zhuanye");
					var status=$("#stuStatus");
					var branch=$("#branch");
				
					school.html("");
               		$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
               		pici.html("");
               		$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
               		cengci.html("");
               		$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               		zhuanye.html("");
               		$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
               		status.html("");
               		$("<option value='" + 0 + "'>请选择学生状态</option>").appendTo(status);
               		branch.html("");
               		$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo(branch);
               				
					$(doc.academysAcademies).each(function(){
						if(student_academyId==this.id){
							sValue=this.id;
							$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
						}else{
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
						}
					});
					ajax_002_2();//批次
					
					
				//注册事件
				school.change(function(){
					sValue=$(this).val();
					if(sValue==0){
							//school.html("");
               				//$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
               				pici.html("");
               				$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
               				branch.html("");
               				$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo(branch);
               				
               				//加载院校
               				//ajax_002_1();
							return;
					}
					//招生批次
					ajax_002_2();
					
				});
				
				pici.change(function(){
					sValue=$(this).val();
					if(sValue==0){
               				//pici.html("");
               				//$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
               				branch.html("");
               				$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo(branch);
               				//招生批次
							//ajax_002_2();
							return;
					}
					//层次
					ajax_002_3();
				});
				cengci.change(function(){
					sValue=$(this).val();
					if(sValue==0){
               				//cengci.html("");
               				//$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
               				//层次
							//ajax_002_3();
							return;
					}
					//查询专业
					ajax_002_4();
				});
			}
			//批次
			function pici(doc){
				$("#pici").html("");
               	$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo($("#pici"));
				$(doc.academyEnrollBatchs).each(function(){
					
					if(student_enrollmentBatchId==this.id){
						sValue=this.id;
						$("<option selected='selected' value='" + this.id + "'>" + this.enrollmentName + "</option>").appendTo($("#pici"));
					}else{
						$("<option value='" + this.id + "'>" + this.enrollmentName + "</option>").appendTo($("#pici"));
					}
				});
				ajax_002_3();//层次，中心
			}
			//层次学习中心
			function cengci(doc){
				$("#cengci").html("");
               	$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo($("#cengci"));
               	$(doc.academyLevels).each(function(){
					if(student_levelId==this.level.id){
						sValue=this.id;
						$("<option  selected='selected' value='" + this.id + "'>" + this.level.name + "</option>").appendTo($("#cengci"));
					}else{
						$("<option value='" + this.id + "'>" + this.level.name + "</option>").appendTo($("#cengci"));
					}
				});
				ajax_002_4();//专业
			}
			//专业
			function zhuanye(doc){
				$("#zhuanye").html("");
               	$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo($("#zhuanye"));
				$(doc.academyMajors).each(function(){
					if(student_majorId==this.majorId){
					 	$("<option selected='selected' value='" + this.majorId + "' title='" + this.majorName + "'   >" + this.majorName + "</option>").appendTo($("#zhuanye"));
					 }else{
					 	$("<option value='" + this.majorId + "' title='" + this.majorName + "' >" + this.majorName + "</option>").appendTo($("#zhuanye"));
					 }
				});
			}
			//判断信息是否全面
			function checkyiyuan()
			{
				if($("#school").val()==0)				
				{
					jQuery("#showDialog").html('<b>该学生意向院校不匹配！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if($("#pici").val()==0)				
				{
					jQuery("#showDialog").html('<b>该学生批次不匹配！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if($("#cengci").val()==0)				
				{
					jQuery("#showDialog").html('<b>该学生意向层次不匹配！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if($("#zhuanye").val()==0)
				{
					jQuery("#showDialog").html('<b>该学生意向专业不匹配！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else
				{
					ajax_003_1();//意愿
					ajax_290_1();//刷新优惠券
				}
			}
			//意愿
			function yiyuanCallback(data){
				$("#feediv").hide();
				
				if(data.isbatch==true)
				{
					jQuery("#showDialog").html('<b>本季招生已结束，无法操作报名！</b>');
																			
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(data.ishascertno==true)
				{
					jQuery("#showDialog").html('<b>该学生无身份证号或联系方式！</b><br/><br/>(<a href="'+WEB_PATH+'/crm/call?studentId='+data.student.id+'&ischecked=1" ><b>修改学生基本资料</b></a>)');
																			
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(data.isture==true && data.student!=null)
				{
					jQuery("#showDialog").html('<b>已经报名缴费过！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(data.isback==true && data.student!=null)
				{	
					jQuery("#showDialog").html('<b>报名缴费成功！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				//else if(data.student!=null && data.pendinglist!=null && data.pendinglist.length>0)
				else if(data.student!=null)
				{
					$('#studentId').val(data.student.id);
					$('#payment_items > tbody').empty();
					$("#batchId").val(data.student.enrollmentBatchId);
					$("#branchId").val(data.student.branchId);
					ajax_100_1();//显示缴费方式
					$("#subtable").show();
						 
		    	 	var list="";
			    	 if(data.pendinglist!=null && data.pendinglist.length>0)
			    	 {
			    	 	 
				    	 $.each(data.pendinglist,function(){	
				    	 	list+='<tr>';								
							//list+='<td>'+this.modeName+'</td>';
							list+='<td  align="center">'+this.feeSubjectName+'</td>';
							list+='<td  align="center"><span style="color:black !important" id="allmoney'+this.id+'">'+this.amountSurplus+'</span></td>';
							list+='<td  align="center"><span style="color:black !important" id="discountfee'+this.id+'">0</span></td>';
							list+='<td  align="center"><span style="color:black !important" id="paymentfee'+this.id+'">'+this.amountSurplus+'</span></td>';	
							list+='<td  align="center">';
							list+='<a href="javascript:usingdiscount('+this.id+');" title="使用优惠卷"><img src="<ui:img url="/images/icon_discount.gif" />" /><font size="-2">(使用优惠卷)</font></a>';
							list+='<input type="hidden" name="discountIds'+this.id+'" id="discountIds'+this.id+'" value=""/>';
							list+='<input type="hidden" id="discountAmount'+this.id+'" name="discountAmount'+this.id+'" value="0"/>';
							list+='<input type="hidden" id="amountPaied'+this.id+'" name="amountPaied'+this.id+'" value="'+this.amountSurplus+'"/>';
							list+='</td>';							
							list+='</tr>';		
				    	 });

			    	 }
			    	 else
			    	 {
			    	 	list+='<tr><td align="center" colspan="5"><span>没有缴纳的费用!</span></td></tr>';
			    	 }	
			    	 $('#payment_items > tbody').html(list);	
					
					$("#feediv").show();
				}
				//else if(data.isfail==false && data.student!=null && (data.pendinglist==null || data.pendinglist.length==0))
				//{
				//	$('#studentId').val(data.student.id);
				//	jQuery("#showDialog").html('<b>未设置相关报名费和测试费的收费政策或者已报名过。</b>');
				//	jQuery('#message_returns_tips').dialog("open");
				//}
				else
				{
					jQuery("#showDialog").html('<b>报名失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
			}
			//隐藏事件
			function showapplyfee()
			{
				$("#feediv").hide();
				$("#subtable").hide();
				
			}
		</script>
		
		<a:ajax 
			pluginCode="002"
			urls="/crm/academys_academie_list;/crm/academy_enroll_batch_list;/crm/level_list;/crm/base_majors_list" 
			parameters="null;{'id':sValue};{'id':sValue,'academyId':parseInt($('#school').val())};{'id':sValue}" 
			successCallbackFunctions="initSelect;pici;cengci;zhuanye" 
		/>
		<a:ajax 
			pluginCode="003"
			urls="/crm/update_student_yiyuan_lc" 
			parameters="$('#stuInfo_form').serializeObject()" 
			successCallbackFunctions="yiyuanCallback" 
		/>
		
		
		
	<script type="text/javascript">
		var sex=-1;
		var status=0;
		function sexValue(sex){
			return sex.getSex();
		}
		
		function setForm(name,certNo,mobile){
			$("#name").val(name);
			$("#certNo").val(certNo);
			$("#mobile").val(mobile);
			search001();
		}
		
		function operatingValue(name,certNo,mobile){
			var onclickStr="setForm('"+name+"','"+certNo+"','"+mobile+"')";
			return '<a href="#" onclick="'+onclickStr+'">报名</a>';
		}
		
		function degreeValue(degree){
			return degree.getStuDegree();
		}
		function search(){
			showapplyfee();
			var isSubmit=false;
			var objs=$("#search_form").serializeObject();
			$.each(objs,function(k,v){
				if(v!=null&&v!=""){
					isSubmit=true;
					return false;
				}
				
			});
			if(isSubmit){
				search001();
			}else{
				$('#message_confirm').dialog("open");
			}
		}
		//查询回调函数
		function search_callback001(data){
			if(data.result.list==null){
				return;
			}
			//多个学生
			if(data.result.list.length!=1){
				
			}else{
				$.each(data.result.list,function(){
					student_academyId=this.academyId;//院校
					student_enrollmentBatchId=this.enrollmentBatchId;//批次
					student_levelId=this.levelId;//层次
					student_majorId=this.majorId;//专业
					$("#hidenId").val(this.id);
				});
				ajax_002_1();
				$("#baoming").css({"display":"block"});
			}
		}
		$(document).ready(function(){
			$('#message_confirm').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'提示',
					width: 260,
					height: 80
					
			});
			
			<c:if test="${student!=null}">
				search();
			</c:if>
		});
	</script>
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
							jQuery('#discountfee'+pendFeePaymentId).html(jQuery('#reducemoney').html());
							jQuery('#discountAmount'+pendFeePaymentId).val(jQuery('#reducemoney').html());
							//优惠后金额
							jQuery('#paymentfee'+pendFeePaymentId).html(jQuery('#reduceaftermoney').html());
							jQuery('#amountPaied'+pendFeePaymentId).val(jQuery('#reduceaftermoney').html());
							//优惠券Ids集合
							$("#discountIds"+pendFeePaymentId).val(disids);
							jQuery(this).dialog("close"); 
						}, 
						'清空': function() { 
							jQuery('#reduceaftermoney').html(jQuery('#allmoney'+pendFeePaymentId).html());
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
				//添加优惠卷
				jQuery('#add_for_discount').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'申请优惠卷',
					width: 600,
					buttons: {
						'添加': function() { 
							addapplicationed();
						}, 
						'取消': function() { 
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
				
				if(jQuery('#indexcount').val()==1)
				{
					jQuery("#showDialog").html('<b>已报名过，不能重复添加！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(jQuery('#createdTime').val()=="")
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
					ajax_130_1();//添加报名费和测试费
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
			
			//显示优惠券
			function usingdiscount(id)
			{
				pendFeePaymentId=id;
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
			var pendFeePaymentId=0;//待缴费单Id
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
					jQuery('#allmoney').html(jQuery('#allmoney'+pendFeePaymentId).html());
					jQuery('#reduceaftermoney').html(jQuery('#paymentfee'+pendFeePaymentId).html());
					jQuery('#reducemoney').html(jQuery('#discountfee'+pendFeePaymentId).html());	
					if(jQuery("#discountIds"+pendFeePaymentId).val()!="")
					{
						var discountIds=(jQuery("#discountIds"+pendFeePaymentId).val()).split(",");
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
			    disids=$("#discountIds"+pendFeePaymentId).val();
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
				if(data.isback==true)
				{	
					//jQuery("#showDialog").html('<b>报名成功！</b>');
					//jQuery('#message_returns_tips').dialog("open");
					$('#message_confirm_discount').dialog({
						title:'确认操作',
						buttons: {
							'确认': function() { 
									ajax_350_1();//显示申请的优惠券	
									$(this).dialog("close"); 			
								}, 
							'取消': function() { 
									$(this).dialog("close"); 
								} 
							}
						});
					$('#message_confirm_discount').dialog("open");
				}
				else if(data.replayadd)
				{
					jQuery("#showDialog").html('<b>已报名缴费过，不要重复添加！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(data.notfeepolice)
				{
					jQuery("#showDialog").html('<b>未设置相关的报名费政策！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(data.notceshipolice)
				{
					jQuery("#showDialog").html('<b>未设置相关的测试费政策！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(data.count==1)
				{
					jQuery("#showDialog").html('<b>收据号已使用过或不存在！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(data.count==0)
				{
					jQuery('#indexcount').val(1);
					if(data.feePayment.isPrint==1)
					{	
						var allmoney=(data.baomingfei-0)+(data.ceshifei-0);
						//报名费
						jQuery("#baomingfei").val(data.baomingfei);
						//测试费
						jQuery("#ceshifei").val(data.ceshifei);
						//总金额
						jQuery("#allfei").val(allmoney);
						jQuery("#daxieallfei").val(nst(allmoney+""));
						//手机
						jQuery("#stumobile").val(data.studentprint.mobile);
						//姓名
						jQuery("#stuname").val(data.studentprint.name);
						//证件号
						jQuery("#stunum").val(data.studentprint.certNo);
						//收费人
						jQuery("#namefee").val(data.namefee);
						//缴费单号
						jQuery("#jfcode").val(data.feePayment.code);
						
						$('#message_confirm_print').dialog({
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
						$('#message_confirm_print').dialog("open");
					}
					else
					{
						//jQuery("#showDialog").html('<b>缴费成功！</b>');
						//jQuery('#message_returns_tips').dialog("open");
						$('#message_confirm_discount').dialog({
						title:'确认操作',
						buttons: {
							'确认': function() { 
									ajax_350_1();//显示申请的优惠券	
									$(this).dialog("close"); 			
								}, 
							'取消': function() { 
									$(this).dialog("close"); 
								} 
							}
						});
						$('#message_confirm_discount').dialog("open");
					}
				}
				else
				{
					jQuery("#showDialog").html('<b>缴费失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
			}
			//ajax回调函数  刷新不需要申请的学生优惠
			function ajax_refreshdiscount(data)
			{				
			    
			}
			//ajax回调函数  学生优惠申请
			function ajax_adddiscount(data)
			{				
				$('#adddiscount > tbody').empty();
			   	var list='';
			    if(data.discountPolicyList!=null && data.discountPolicyList.length>0)
			    {
			    	$.each(data.discountPolicyList,function(){	
			    		list+='<tr>';
			    		list+='<td align="center"><input type="radio" name="policyradio" value="'+this.id+'" /></td>'
			    		list+='<td align="center">'+this.title+'</td>';
			    		list+='<td align="center">'+this.feesubjectname+'</td>';
			    		//list+='<td align="center">第'+this.feePaymentId+'次缴费</td>';
			    		if(this.isApplicationNeeded==STUDENT_DISCOUNT_AUDIT_CEDU)
			    		{
			    			list+='<td align="center">总部审批</td>';
			    		}
			    		else if(this.isApplicationNeeded==STUDENT_DISCOUNT_AUDIT_BRANCH)
			    		{
			    			list+='<td align="center">中心审批</td>';
			    		}
			    		else
			    		{
			    			list+='<td align="center">无需审批</td>';
			    		}
			    		list+='<td align="center">'+this.useBeginDate+"~"+this.useEndDate+'</td>';
			    		list+='<td align="center">';
			    		if(this.discountWayId==MONEY_FORM_AMOUNT)
			    		{
			    			list+="优惠金额："+this.money+"元";
			    		}
			    		else
			    		{
			    			list+="优惠比例："+this.money+"%";
			    		}
			    		if(this.mutable==MONEY_FORM_GRADIENT)
			    		{
			    			if(this.discountWayId==MONEY_FORM_AMOUNT)
			    			{
			    				list+='<br/>渐变金额：'+this.delta+'元';
			    			}
			    			else
			    			{
			    				list+='<br/>渐变比例：'+this.delta+'%';
			    			}
			    		}
			    		list+='</td>';
			    		list+='</tr>';
			    	});
			    }
			    else
			    {
			    	list+='<tr><td colspan="6" align="center">没有可以申请的优惠政策！</td></tr>';
			    }
			    $('#adddiscount > tbody').html(list);
			    $("#remark").val("");	
			    $('#add_for_discount').dialog('open');
			}
			//添加优惠申请
			function addapplicationed()
			{
				if(jQuery("input[name='policyradio']").length==0 || jQuery("input[name='policyradio']:checked").length==0)
				{
					jQuery("#showDialog").html('<b>请选择要申请的优惠！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else
				{
					 ajax_360_1();//添加优惠政策
				}
			}
			//ajax回调函数  添加学生优惠申请
			function ajax_addstudentapp(data)
			{				
			    if(data.isfailcount)
			    {
			    	jQuery("#showDialog").html('<b>已申请过该优惠！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }	
			    else if(data.isfail)
			    {
			    	jQuery("#showDialog").html('<b>添加成功！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }
			    else
			    {
			    	jQuery("#showDialog").html('<b>添加失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }			  
			}
			
	</script>
	
		<!--缴费方式下拉框-->
		<a:ajax 
			pluginCode="100"
			parameters="jQuery('#myform').serializeObject()" 
			successCallbackFunctions="paymentWayCallback"
			urls="finance/payment/finance_payment_way_list"
		/>
		<!--显示可以使用优惠券-->
		<a:ajax 
			pluginCode="110"
			successCallbackFunctions="ajax_showdiscount" 
			parameters="{studentId:jQuery('#studentId').val(),pendingFeePaymentId:pendFeePaymentId}" 
			urls="/finance/payment/list_student_can_using_application_ajax" 
		/>
		
		<!--使用优惠券-->
		<a:ajax 
			pluginCode="120"
			successCallbackFunctions="ajax_usingdiscount" 
			parameters="{discountpolicyId:discountpolicyId,allmoney:jQuery('#allmoney').html(),reducemoney:jQuery('#reducemoney').html()}" 
			urls="/finance/payment/using_student_feesubject_discount_application_ajax" 
		/>
		
		<!--添加报名费和测试费-->
		<a:ajax 
			pluginCode="130"
			successCallbackFunctions="ajax_addtestpayment" 
			parameters="jQuery('#feemyform').serializeObject()" 
			urls="/finance/payment/add_test_apply_payment_ajax" 			
		/>
		<!--刷新不需要申请的学生优惠-->
		<a:ajax 
			successCallbackFunctions="ajax_refreshdiscount" 
			parameters="{studentId:jQuery('#hidenId').val()}" 
			urls="/enrollment/refresh_student_application_unapply_ajax" 
			pluginCode="290"
			
		/>
		<!--学生优惠申请-->
		<a:ajax 
			successCallbackFunctions="ajax_adddiscount" 
			parameters="{studentId:jQuery('#hidenId').val()}" 
			urls="/enrollment/list_branch_discount_student_apply_ajax" 
			pluginCode="350"
		/>
		<!--添加学生优惠申请-->
		<a:ajax 
			successCallbackFunctions="ajax_addstudentapp" 
			parameters="{studentId:jQuery('#hidenId').val(),remark:jQuery('#remark').val(),discountPolicyId:jQuery(\"input[name='policyradio']:checked\").val()}" 
			urls="/enrollment/add_student_application_ajax" 
			pluginCode="360"
		/>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="学生报名">
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<div id="message_confirm" style="display:none">
						<div>姓名，证件号，手机号码必添一！</div>
			</div>
			<form id="search_form">
				<table class="add_table">
					   <tr>
					   	 <td align="right">姓名：</td>
		                <td align="right" style="width:100px;">
							<input id="name" type="text" name="student.name" class="txt_box_100" value="${student.name }"/>
						</td>
		
		                <td align="right">证件号：</td>
		                <td align="right" style="width:150px;">
							<input id="certNo" type="text" name="student.certNo" class="txt_box_150" value="${student.certNo }"/>
						</td>
						 <td align="right">手机：</td>
		                <td align="right" style="width:100px;">
							<input id="mobile" type="text" class="txt_box_150" name="student.mobile" value="${student.mobile }"/>
						</td>
		                <td align="right">
							<input type="button" class="btn_black_61" value="查询" onclick="search()"/>
						</td>
						<td></td>
		              </tr>
		            </table>
		      </form>
				<page:plugin 
						pluginCode="001"
						il8nName="crm"
						searchListActionpath="student_reg"
						columnsStr="name;certNo;mobile;degree;gender;#public.operating"
						customColumnValue="3,degreeValue(degree);4,sexValue(gender);5,operatingValue(name,certNo,mobile)"
						params="'student.callStatus':1,'student.gender':-1,'student.status':parseInt(status)"
						isPage="false"
						searchFormId="search_form"
						isOrder="false"
						isonLoad="false"
						listCallback="search_callback"
				/>
				<form id="stuInfo_form" name="stuInfo_form">
					<input type="hidden" id="hidenId" name="student.id" value="0" />
					<div id="baoming" style="display: none;">
					
						<table class="add_table">
								<tr>
								  <td align="center" colspan="8">&nbsp;</td>
								</tr>
								<tr>
								  <td align="right">意向院校：</td><td align="left"><select class="txt_box_130" name="student.academyId" id="school"></select></td>
								  <td align="right">批次：</td><td align="left"><select class="txt_box_130" name="student.enrollmentBatchId" id="pici"></select></td>
								  <td align="right">意向层次：</td><td align="left"><select class="txt_box_130" name="student.levelId" id="cengci"></select></td>
								  <td align="right">意向专业：</td><td align="left"><select class="txt_box_130" name="student.majorId" id="zhuanye"></select></td>
								</tr>
								
								<tr>
								  <td align="center" colspan="8">
								  	
								  	<input class="btn_black_130" type="button" onclick="checkyiyuan();" value="开始报名缴费" />
								  </td>
								</tr>
						</table>
					</div>
				</form>
				
				<form id="myform" name="myform">
					<input type="hidden" id="branchId" name="student.branchId" value="0"/>
					<input type="hidden" id="batchId" name="student.enrollmentBatchId" value="0"/>
				</form>
				<form id="feemyform" name="feemyform">
					<input type="hidden" id="studentId" name="feePayment.studentId" value="0"/>
					
					<!-- 学生数据   手机、姓名、收款人、证件号-->
					<input type="hidden" id="stumobile" name="stumobile" value=""/>
					<input type="hidden" id="stuname" name="stuname" value=""/>
					<input type="hidden" id="stunum" name="stunum" value=""/>
					<input type="hidden" id="namefee" name="namefee" value=""/>
					<!-- 判断是否重复添加-->
					<input type="hidden" id="indexcount" name="indexcount" value="0"/>
					<!-- 打印 -->
					<input type="hidden" id="baomingfei" name="baomingfei" value="0"/>
					<input type="hidden" id="ceshifei" name="ceshifei" value="0"/>
					<input type="hidden" id="jfcode" name="jfcode" value=""/>
					<input type="hidden" id="allfei" name="allfei" value="0"/>
					<input type="hidden" id="daxieallfei" name="daxieallfei" value="0"/>
					<div id="feediv" style="display: none;">				
						  <table class="add_table" id="payment_items">
							<thead>
								<tr>
									
									<th align="center">费用项目</th>
									<th align="center">应收金额</th>
									<th align="center">优惠金额  </th>
									<th align="center">实收金额</th>
									<th align="center">操作</th>
								</tr>
							</thead>
							
							<tbody>	
								
							</tbody>
							
						  </table>
		
				  
						  <table class="add_table" id="subtable" style="display:none">
							<tr>
								<td align="center" colspan="3">
									<input type="checkbox" name="stu.isExemption" id="isExemption" value="<%=Constants.STUDENT_IS_EXEMPTION_TRUE %>" />无测试费
								  	<input type="checkbox" name="stu.notApplyFee" id="notApplyFee" value="<%=Constants.STUDENT_IS_EXEMPTION_TRUE %>" />无报名费
								</td>
							</tr>
							<tr>
								<td align="right" width="60%">
									<span>*</span>缴费时间：
									<input type="text" name="feePayment.createdTime" value="${feedate}" id="createdTime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen'})" readonly="readonly"/>
								</td>
								<td align="center" width="5%">
									
								</td>
								<td align="left" width="35%">
									<input type="radio" value="1" name="isFee" id="isFee" checked="checked"/><label>现场收费</label>
									<input type="radio" value="0" name="isFee" id="isFee"/><label>上门收费</label>
								</td>
							</tr>
						  	<tr>
								<td align="right" width="60%">
									缴费方式(仅显示该院校该中心的缴费方式)：<select id="paymentway" name="feePayment.feeWayId" class="txt_box_150"></select>
								</td>
								<td align="center" width="5%">
									
								</td>
								<td align="left" width="35%">
									<input type="checkbox" value="1" name="feePayment.isPrint" id="isPrint"/><label>是否打印</label>
									<span id="receipdiv" style="display:none"><span>*</span>收据号：<input type="text" name="feePayment.barCode" id="barCode" class="txt_box_150" /></span>
								</td>
				
							</tr>
						  	<tr>
								<td align="center" colspan="3">
									<input class="btn_black_130" type="button" value="完成报名缴费" onclick="showsubmit();" id="submit"/>
									<input class="btn_black_61" type="button" value="取消" onclick="javascript:history.go(-1);"/>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="3">
									<span>备注：</span>选择上门收费:如果收费不成功,该学生的缴费单应该手动作废;如果收费成功，该学生的缴费单还需手动确认收费成功！
								</td>
							</tr>
						</table>					
					</div>
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
						<th>优惠金额/比例</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<table class="add_table" id="showdiscount1">
				<tfoot>
					<tr>
						<td align="center" colspan="4">
							缴费金额：<span style="" id="allmoney"></span>
							优惠金额：<span style="" id="reducemoney">0</span>
							优惠后金额：<span style="" id="reduceaftermoney"></span>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		<!-- 申请优惠券 -->
		<div id="add_for_discount" style="display:none">
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					<th style="text-align:left; font-weight:bold;" class="feehtml">申请的优惠标准</th>					
				</tr>
			</table>
			<table class="gv_table_2" id="adddiscount">
				<thead>
					<th></th>
					<th>优惠标题</th>
					<th>费用科目</th>
					<!-- th>缴费次数</th> -->
					<th>审批部门</th>
					<th>有效期</th>
					<th>优惠标准</th>
				</thead>
				<tbody>
					
				</tbody>
			</table>
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					<th style="text-align:left; font-weight:bold;" class="feehtml">申请备注</th>					
				</tr>
			</table>
			<table class="add_table" id="">					
				<tfoot>
		            <tr>
		            	<th class="lable_100">优惠备注：</th>
		                <th colspan="4"><textarea  id="remark" name="remark"  cols="35" rows="8" style="height:100px" class="txt_box_350"></textarea></th>
		            </tr>
		        </tfoot>    
			</table>
		</div>	
		
		<div id="message_confirm_discount" style="display:none">
			<div align="center" id="showconfirmdiscount">
				<b>缴费成功，是否为学生申请中心随机使用优惠卷？</b>
			</div>
		</div>
		
		<div id="message_confirm_discount1" style="display:none">
			<div align="center" id="showconfirmdiscount">
				<b>是否为学生申请中心随机使用优惠卷？</b>
			</div>
		</div>		
		
		<div id="message_confirm_print" style="display:none">
			<div align="center" id="showconfirm">
				<b>缴费成功，是否现在打印收据？</b>
			</div>
		</div>	
		<!--  打印           -->
		<div id="message_print_tips" style="display:none">
			<div align="center" id="showdiv">
				<!--  a href="" onclick="javascript:Design1();"><b>模板设计</b></a><br/>-->
				<a href="javascript:void(0)" onclick="javascript:Preview1();"><b>打印收据预览</b></a><br/>
				<!--a href="javascript:void(0)" onclick="javascript:ajax_350_1();"><font size="-2">(为学生申请中心自定义政策)</font></a>
				<a href="javascript:void(0)" onclick="javascript:RealPrint();"><b>打印收据</b></a> -->
			</div>
		</div>
		<!--  打印事件           -->
		<script language="javascript" type="text/javascript">
			var LODOP; //声明为全局变量   
			function Preview1() {
				CreateFullBill();
				
				LODOP.PREVIEW();
				$('#message_confirm_discount1').dialog({
					title:'确认操作',
					buttons: {
						'确认': function() { 
							ajax_350_1();//显示申请的优惠券	
							$(this).dialog("close"); 			
						}, 
						'取消': function() { 
							$(this).dialog("close"); 
						} 
					}
				});
				$('#message_confirm_discount1').dialog("open");
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
				
				LODOP.ADD_PRINT_TEXT(98,603,125,21, jQuery('#stumobile').val());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(99,184,103,21, jQuery('#stuname').val());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(125,184,155,21, jQuery("#school").find("option:selected").text());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(125,396,88,20, jQuery("#pici").find("option:selected").text());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(124,675,113,20, jQuery("#zhuanye").find("option:selected").text());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(159,396,128,21, "0");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(160,185,129,21, "0");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(187,396,131,20, "0");
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
				LODOP.ADD_PRINT_TEXT(125,533,81,20,jQuery("#cengci").find("option:selected").text());
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
				LODOP.ADD_PRINT_TEXT(99,395,186,21, jQuery('#stunum').val());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(186,625,132,20, "0");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(254,406,131,20, jQuery('#namefee').val());
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(253,611,127,20, jQuery('#stuname').val());
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				
				LODOP.ADD_PRINT_TEXT(295,86,208,20,jQuery("#jfcode").val());
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);	

			};
		</script>	
	</body>

</html>