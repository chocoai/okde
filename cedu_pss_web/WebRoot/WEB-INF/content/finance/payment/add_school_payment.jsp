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
		<!--缴费方式-->
		<script type="text/javascript">
			//加载事件
			jQuery(function(){
				//性别
				if(${student.gender}==0)
				{$('#sexSp').html('女');}
				else if(${student.gender}==1)
				{$('#sexSp').html('男');}
				else
				{$('#sexSp').html('未知');}	
							
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
				//优惠金额是否使用优惠券和手动录入
				jQuery('[name=isDisc]').click(function(){
					if(this.value==0)
					{
						jQuery("#usingDisc").hide();
						jQuery("#moneyDisPut").val(0);
						jQuery("#moneyDisPut").attr("disabled",false);
						//清空优惠卷ids
						jQuery("#discountIds").val("");
					}
					else if(this.value==1)
					{
						jQuery('#usingDisc').show();
						jQuery("#moneyDisPut").val(0);
						jQuery("#moneyDisPut").attr("disabled",true);
						
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
							jQuery('#moneyDisPut').val(jQuery('#reducemoney').html());
							
							//优惠后金额
							if((jQuery('#reduceaftermoney').html()-0)-(jQuery('#moneyHasPut').val()-0)<0)
							{
								//充值金额
								jQuery('#moneyHasPut').val(jQuery('#reduceaftermoney').html());
							}
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
				
				//使用充值账户金额
				jQuery('#show_for_account').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'学生账户',
					width: 500,
					buttons: {
						'确定': function() { 
							var allmoneys=jQuery('#moneyForPut').val()-0;//账面金额（账面金额大于应缴金额时不让用充值金额）
							var discountmoneys=jQuery('#moneyDisPut').val()-0;//优惠金额
							var stuaccountmoney=jQuery("#stuaccountmoney").html()-0;//账户剩余金额
							if(!((/^\d{1,10}(\.\d{1,2})?$/).test(jQuery("#useaccount").val())))
							{
								jQuery("#showDialog").html('<b>充值金额填写不正确，只能填写小数或整数！</b>');
								$('#message_returns_tips').dialog("open");
							}
							else if((allmoneys-discountmoneys)<(jQuery("#useaccount").val()-0))
							{
								jQuery("#showDialog").html('<b>使用的充值金额和优惠金额超过本次预缴金额！</b>');
								jQuery('#message_returns_tips').dialog("open");								
							}
							else if(stuaccountmoney<(jQuery("#useaccount").val()-0))
							{
								jQuery("#useaccount").val(stuaccountmoney);
								jQuery("#showDialog").html('<b>该费用科目账户余额不足！</b>');
								jQuery('#message_returns_tips').dialog("open");		
							}
							else
							{
								//充值金额
								jQuery('#moneyHasPut').val(jQuery("#useaccount").val());
								$(this).dialog("close");  
							}
							
						}, 
						'关闭': function() { 
							
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
				//是否是修改完后的页面
				$('#null_for_close').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示',
					buttons: {
						'确定': function() { 
							window.close();
							} 
					}
				});	
				
			});
			//刷新学生新政策
			function showpendingfeepayment()
			{
				ajax_260_1();
			}
			//提交前的验证
			function showsubmit()
			{
				if(golbalIds=="")
				{
					jQuery("#showDialog").html('<b>缴费单明细不能为空！</b>');
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
					//alert(1);
					if(golbalIds.length>0)
					{
						jQuery("#feeSubjectIds").val(golbalIds.substring(0,(golbalIds.length-1)));
					}
					//alert(jQuery("#feeSubjectIds").val());
					
					dealprint();//打印数据
					ajax_130_1();//添加除报名费和测试费之外的其他所有费用
				}
			}
			//打印数据
			function dealprint()
			{
				
				var allmoney=0;//总金额
				//缴费科目
				var golids=golbalIds.substring(0,(golbalIds.length-1));
			    var gids =golids.split(",");
			    for(var i=0;i<gids.length;i++)
			    {
			    	//alert(gids[i]);
					if((gids[i]-0)==FEE_SUBJECT_TYPE_TUITION)
					{
						jQuery("#xuefei").val((jQuery("#xuefei").val()-0)+(jQuery("#moneyToPut"+gids[i]).val()-0));
						//alert(jQuery("#moneyToPut"+gids[i]).val());
					}
					else if((gids[i]-0)==FEE_SUBJECT_TYPE_BOOK)
					{
						jQuery("#jiaocaifei").val((jQuery("#jiaocaifei").val()-0)+(jQuery("#moneyToPut"+gids[i]).val()-0));
					}
					else if((gids[i]-0)==FEE_SUBJECT_TYPE_MAKEUP)
					{
						jQuery("#bukaofei").val((jQuery("#bukaofei").val()-0)+(jQuery("#moneyToPut"+gids[i]).val()-0));
					}
					else
					{
						jQuery("#qitafei").val((jQuery("#qitafei").val()-0)+(jQuery("#moneyToPut"+gids[i]).val()-0));
					}
					allmoney+=jQuery("#moneyToPut"+gids[i]).val()-0;
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
			//显示优惠券
			function usingdiscount()
			{
				//处理钱的小数点问题dealwithmoney
				jQuery("#moneyForPut").val(dealwithmoney(jQuery("#moneyForPut").val()));
				jQuery("#moneyDisPut").val(dealwithmoney(jQuery("#moneyDisPut").val()));
				ajax_110_1();//显示优惠券
			}
			
			//显示充值金额
			function usingaccount()
			{
				//处理钱的小数点问题dealwithmoney
				jQuery("#moneyForPut").val(dealwithmoney(jQuery("#moneyForPut").val()));
				jQuery("#moneyDisPut").val(dealwithmoney(jQuery("#moneyDisPut").val()));
				
				//1.充值金额参考基数取应缴金额和账面金额两个之间最小的那个金额
				if((jQuery('#moneyAllPut').val()-0)<(jQuery("#moneyForPut").val()-0))
				{
					//当优惠金额
					if((jQuery("#moneyDisPut").val()-0)>=(jQuery("#moneyAllPut").val()-0))
					{
						jQuery('#moneyHasPut').val(0);
						jQuery("#showDialog").html('<b>优惠金额大于等于应缴金额，不需要用充值账户的金额！</b>');
						jQuery('#message_returns_tips').dialog("open");
					}
					else
					{
						jQuery('#moneyHasPut').val(0);
						jQuery("#showDialog").html('<b>本次预缴金额大于等于应缴金额，不需要用充值账户的金额（多余的钱将被放入充值账户）！</b>');
						jQuery('#message_returns_tips').dialog("open");
					}
				}
				else
				{
					if((jQuery("#moneyDisPut").val()-0)>=(jQuery("#moneyForPut").val()-0))
					{
						jQuery('#moneyHasPut').val(0);
						jQuery("#showDialog").html('<b>优惠金额大于等于本次预缴金额，不需要用充值账户的金额！</b>');
						jQuery('#message_returns_tips').dialog("open");
					}
					else
					{
						jQuery("#feeSubjectName").html(jQuery("#feeSubSpan").html());
						jQuery('#allmoneyacc').html(jQuery('#moneyForPut').val());
						jQuery('#reducemoneyacc').html(jQuery('#moneyDisPut').val());
						jQuery('#useaccount').val(jQuery('#moneyHasPut').val());
						//alert(jQuery('#rechargeAmount'+pendFeePaymentId).html());
						ajax_140_1();//显示充值账户
					}
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
			//强制缴费增加的判断
			function addcheckaddfeepment()
			{
				
				//处理钱的小数点问题dealwithmoney
				jQuery("#moneyForPut").val(dealwithmoney(jQuery("#moneyForPut").val()));
				jQuery("#moneyDisPut").val(dealwithmoney(jQuery("#moneyDisPut").val()));
				
				//强制缴费功能判断
				if(jQuery("#isForceFee").val()==ISNEED_REBATES_TRUE && (jQuery("#moneyAllPut").val()-0)>(jQuery("#moneyForPut").val()-0))
				{
					jQuery("#showDialog").html('<b>由于院校为强制缴费，本次预缴金额必须大于等于应缴金额！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else if(jQuery("#isForceFee").val()==ISNEED_REBATES_TRUE && cindex>0 && (jQuery("#moneyAllPut").val()-0)<=0)
				{
					$('#message_confirm').dialog({
						title:'确认操作',
						buttons: {
							'确认': function() { 
									$(this).dialog("close"); 
									checkaddfeepment();
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
					checkaddfeepment();
				}
			}
			
			//添加缴费单明细之前先计算下优惠,防止账面金额变动
			function checkaddfeepment()
			{
				//alert(jQuery("#discountIds").val());
				if((jQuery("#moneyForPut").val()-0)<=0)//非强制的判断
				{
					jQuery("#showDialog").html('<b>本次预缴金额必须大于0！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else if((jQuery("#moneyDisPut").val()-0)>(jQuery("#moneyAllPut").val()-0))//当优惠金额
				{
					jQuery("#showDialog").html('<b>优惠金额不能大于应缴金额！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else if((jQuery("#moneyDisPut").val()-0)>(jQuery("#moneyForPut").val()-0))//当优惠金额
				{
					jQuery("#showDialog").html('<b>优惠金额不能大于本次预缴金额！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else if((jQuery("#moneyForPut").val()-0)>(jQuery("#moneyAllPut").val()-0) && (jQuery("#moneyHasPut").val()-0)>0)
				{
					jQuery("#moneyHasPut").val(0);
					jQuery("#showDialog").html('<b>本次预缴金额大于应缴金额，则不能使用充值金额！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else if(jQuery("#discountIds").val()!="")
				{
					rewritediscountpolicyId=jQuery("#discountIds").val();
					if((jQuery('#moneyAllPut').val()-0)<(jQuery("#moneyForPut").val()-0))
					{
						rewriteallmoney=jQuery('#moneyAllPut').val();
					}
					else
					{
						rewriteallmoney=jQuery('#moneyForPut').val();
					}
					ajax_220_1();//重新计算优惠
				}
				else
				{
					addfeepaymentdetail();//添加缴费单明细
				}
			}
			
			//添加缴费单明细
			var golbalIds="";//费用科目id字符串集合
			function addfeepaymentdetail()
			{
				//缴费科目id
				var fsId=jQuery("#feeSubjectId").val();
				var moneyToPay=0.0;//实缴金额
				moneyToPay=((jQuery("#moneyForPut").val()-0)-(jQuery("#moneyDisPut").val()-0)-(jQuery("#moneyHasPut").val()-0)+"").toMoney()-0;
				//判断账面金额是否修改
				if(moneyToPay<0)
				{
					jQuery("#showDialog").html('<b>本次预缴金额不能小于优惠金额和充值金额之和！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else
				{
					var list="";
					list+='<tr id="ttr'+fsId+'">';
					list+='<td align="center">'+jQuery("#feeSubSpan").html()+'</td>';				
					list+='<td align="center">'+jQuery("#moneyAllPut").val()+'</td>';
					list+='<td align="center">'+jQuery("#moneyForPut").val()+'</td>';
					list+='<td align="center">'+jQuery("#moneyDisPut").val()+'</td>';
					list+='<td align="center">'+jQuery("#moneyHasPut").val()+'</td>';
					list+='<td align="center"><span id="moneyToSpan'+fsId+'" style="color:black !important">'+moneyToPay+'</span></td>';
					list+='<td align="center">';
					list+='<a href="javascript:removetr('+fsId+');" title="删除">删除</a>';
					list+='<input type="hidden" name="moneyAllPut'+fsId+'" id="moneyAllPut'+fsId+'" value="'+jQuery("#moneyAllPut").val()+'"/>';
					list+='<input type="hidden" name="moneyForPut'+fsId+'" id="moneyForPut'+fsId+'" value="'+jQuery("#moneyForPut").val()+'"/>';
					
					list+='<input type="hidden" name="moneyHasPut'+fsId+'" id="moneyHasPut'+fsId+'" value="'+jQuery("#moneyHasPut").val()+'"/>';
					list+='<input type="hidden" name="discountIds'+fsId+'" id="discountIds'+fsId+'" value="'+jQuery("#discountIds").val()+'"/>';
					list+='<input type="hidden" name="moneyToPut'+fsId+'" id="moneyToPut'+fsId+'" value="'+moneyToPay+'"/>';
					if(jQuery("input[name='isDisc']:checked").val()==0)
					{
						list+='<input type="hidden" name="cedumoneyDisPut'+fsId+'" id="cedumoneyDisPut'+fsId+'" value="'+jQuery("#moneyDisPut").val()+'"/>';
						list+='<input type="hidden" name="moneyDisPut'+fsId+'" id="moneyDisPut'+fsId+'" value="0"/>';
					}
					else
					{
						list+='<input type="hidden" name="cedumoneyDisPut'+fsId+'" id="cedumoneyDisPut'+fsId+'" value="0"/>';
						list+='<input type="hidden" name="moneyDisPut'+fsId+'" id="moneyDisPut'+fsId+'" value="'+jQuery("#moneyDisPut").val()+'"/>';
					}
					
					list+='</td>';
					list+='</tr>';	
									
					jQuery(list).appendTo(jQuery('#payment_items > tbody'));
					//隐藏添加缴费单明细隐藏域
					jQuery("#adddetaildiv").hide();
					//清空使用的优惠卷ids
					disids="";
					jQuery("#discountIds").val("");
					//合计总缴费金额
					jQuery("#moneyAllSpan").html((jQuery("#moneyAllSpan").html()-0)+moneyToPay);
					////费用科目id字符串集合
					golbalIds+=fsId+",";
					//alert(golbalIds);
				}
			}
			//删除确定的缴费明细
			function removetr(op)
		    {
		    	if(confirm("确认删除么？"))
		    	{
			    	//删除相应的费用科目id
			    	//alert(golbalIds);
			    	if(golbalIds!="" && golbalIds.length>0)
			    	{
			    		var golids=golbalIds.substring(0,(golbalIds.length-1));
			    		var gids =golids.split(",");
			    		golbalIds="";
			    		for(var i=0;i<gids.length;i++)
			    		{
			    			if(op!=gids[i])
			    			{
			    				golbalIds+=gids[i]+",";
			    			}
			    		}
			    	}
			    	//合计缴费金额相应减少
			    	jQuery("#moneyAllSpan").html((jQuery("#moneyAllSpan").html()-0)-(jQuery("#moneyToSpan"+op).html()-0));
			    	//删除行
			    	jQuery('#ttr'+op).remove();
			    	//alert(golbalIds);
			    }	
		    }
			
			//查询费用科目当前的缴费信息
			function showfeeSubjectFee()
			{
				var isback=false;
				//判断该费用科目是否已存在缴费单明细里
				if(golbalIds!="" && golbalIds.length>0)
		    	{
		    		var golids=golbalIds.substring(0,(golbalIds.length-1));
		    		var gids =golids.split(",");
		    		for(var i=0;i<gids.length;i++)
		    		{
		    			if(jQuery("#feesubjectId").val()==gids[i])
		    			{
		    				isback=true;
		    				break;
		    			}
		    		}
		    	}
				//alert(isback+"111"+golbalIds);
				if(jQuery("#feesubjectId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择费用科目！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else if(isback==true)
				{
					jQuery("#showDialog").html('<b>该费用科目已添加过缴费，不能重复添加！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else
				{
					//显示添加缴费单明细隐藏域
					jQuery("#adddetaildiv").show();
					//清空优惠卷ids
					jQuery("#discountIds").val("");
					//清空优惠金额
					jQuery("#moneyDisPut").val(0);
					//清空充值金额
					jQuery("#moneyHasPut").val(0);
					//费用科目赋值
					jQuery("#feeSubjectId").val(jQuery("#feesubjectId").val());
					jQuery("#feeSubSpan").html(jQuery("#feesubjectId").find("option:selected").text());
					ajax_150_1();//显示该学生相关的充值金额(查询科目的时候自动加载显示出来)
					
					ajax_210_1();//显示缴费信息
				}
			}
			
			//强制缴费勾选下拉框时显示金额
			function showforcemoney(id)
			{
				var moneyput=0.0
				if(jQuery("#forcefeebox"+id).attr("checked")==true)
				{
					if(id>1)
					{
						jQuery("#forcefeebox"+(id-1)).attr("disabled",true);
					}
					if(id<cindex)
					{
						jQuery("#forcefeebox"+(id+1)).attr("disabled",false);
					}
					
					for(var i=1;i<=id;i++)
					{
						moneyput+=(jQuery("#forcefeebox"+i).val()-0);
					}
					//var chae=(jQuery("#moneyForPut").val()-0)-(jQuery("#moneyAllPut").val()-0);
					//给应缴金额赋值
					if(moneyput-historyallmoney<=0)
					{
						jQuery("#moneyAllPut").val(0);
						
						//给账面金额赋值	
						jQuery("#moneyForPut").val(0);
						//if(chae<0)
						//{
						//	jQuery("#moneyForPut").val(0);
						//}
						//else
						//{
						//	jQuery("#moneyForPut").val(chae);
						//}
					}
					else
					{
				    	jQuery("#moneyAllPut").val(moneyput-historyallmoney);
				    	//给账面金额赋值	
				    	jQuery("#moneyForPut").val(moneyput-historyallmoney);
				    }
				    
				    //给应缴金额赋值
				    //jQuery("#moneyAllPut").val((jQuery("#moneyAllPut").val()-0)+(jQuery("#forcefeebox"+id).val()-0));
				    //给本次预缴金额赋值			    
				    //jQuery("#moneyForPut").val((jQuery("#moneyForPut").val()-0)+(jQuery("#forcefeebox"+id).val()-0));
				}
				else
				{
					if(id>1)
					{
						jQuery("#forcefeebox"+(id-1)).attr("disabled",false);
					}
					if(id<cindex)
					{
						jQuery("#forcefeebox"+(id+1)).attr("disabled",true);
					}
					//给应缴金额赋值
				    //jQuery("#moneyAllPut").val((jQuery("#moneyAllPut").val()-0)-(jQuery("#forcefeebox"+id).val()-0));
				    //给本次预缴金额赋值			    
				   // jQuery("#moneyForPut").val((jQuery("#moneyForPut").val()-0)-(jQuery("#forcefeebox"+id).val()-0));
				   for(var i=1;i<=id-1;i++)
					{
						moneyput+=(jQuery("#forcefeebox"+i).val()-0);
					}
					//给应缴金额赋值
					if(moneyput-historyallmoney<=0)
					{
						jQuery("#moneyAllPut").val(0);
						//给账面金额赋值	
						jQuery("#moneyForPut").val(0);
					}
					else
					{
				    	jQuery("#moneyAllPut").val(moneyput-historyallmoney);
				    	//给账面金额赋值	
				    	jQuery("#moneyForPut").val(moneyput-historyallmoney);
				    }
				}
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
					////1.由于开始使用了再点的时候给它复原回去
					////2.优惠基数取应缴金额和账面金额两个之间最小的那个金额
					if((jQuery('#moneyAllPut').val()-0)<(jQuery("#moneyForPut").val()-0))
					{
						jQuery('#allmoney').html(jQuery('#moneyAllPut').val());
					}
					else
					{
						jQuery('#allmoney').html(jQuery('#moneyForPut').val());
					}
					jQuery('#reduceaftermoney').html((jQuery('#allmoney').html()-0)-(jQuery('#moneyDisPut').val()-0));
					jQuery('#reducemoney').html(jQuery('#moneyDisPut').val());	
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
			
			//ajax回调函数  使用优惠券2(防止账面金额变动，重新计算优惠金额总数)
			var rewritediscountpolicyId="";//优惠卷ids字符串
			var rewriteallmoney="";
			var rewritereducemoney="0";
			function ajax_usingdiscount_rewrite(data)
			{
				if(data.reduceaftermoney.length>0 && data.reducemoney.length>0)
				{
					jQuery("#moneyDisPut").val(data.reducemoney);
				}
				
				addfeepaymentdetail();//添加缴费单明细
			}
			
			//ajax回调函数   添加除报名费和测试费之外的其他所有费用
			function ajax_addtestpayment(data)
			{
				
				if(data.replayadd)
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
					//jQuery("#showDialog").html('<b>缴费成功！</b>');
					//jQuery('#message_returns_tips').dialog("open");
					
					if(data.feePayment.isPrint==1)
					{	
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
												window.close(); 
											} 
										}
									});	
									$('#message_print_tips').dialog("open");				
								}, 
							'取消': function() { 
									//$(this).dialog("close"); 
									window.close(); 
								} 
							}
						});
						$('#message_confirm_print').dialog("open");
					}
					else
					{
						jQuery("#showclose").html('<b>缴费成功！点击确定关闭当前页。</b>');
						$('#null_for_close').dialog("open");
					}
				}
				else
				{
					jQuery("#showDialog").html('<b>缴费失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
			}
			
			//ajax回调函数   显示该学生相关的充值金额
			function ajax_showaccount(data)
			{			
				if(data.studentAccount=="" || data.studentAccount==null)
				{
					jQuery("#stuaccountmoney").html(0);		
				}
				else
				{
					jQuery("#stuaccountmoney").html(data.studentAccount);		
				}
				if(data.studentAllAccount=="" || data.studentAllAccount==null)
				{
					jQuery("#stuallaccount").html(0);
				}
				else
				{
					jQuery("#stuallaccount").html(data.studentAllAccount);
				}
				
				$('#show_for_account').dialog('open');
			}
			
			//ajax回调函数   显示该学生相关的充值金额(查询科目的时候自动加载显示出来)
			function ajax_showaccount2(data)
			{			
				if(data.studentAccount=="" || data.studentAccount==null)
				{
					jQuery("#showmonspan").html(0);		
				}
				else
				{
					jQuery("#showmonspan").html(data.studentAccount);		
				}
			}
			
			//ajax回调函数   显示缴费信息
			var cindex=0;//控制强制收费的checkbox
			var historyallmoney=0.0;//历史缴费总金额，强制收费的院校用
			function ajax_showshcoolpayment(data)
			{
				//待缴费单
				$('#policyreferencetab > tbody').empty();
			   	var list='';
			   	var allmoney=0.0;
			   	var forceallmoney=0.0;
			   	cindex=0;
				if(data.pendingList!=null && data.pendingList.length>0)
				{
			    	$.each(data.pendingList,function(){	
			    		//为强制缴费服务
			    		var totime = new Date("<%=new Date()%>");
			    		//var histime=new Date(this.startTime.substring(0,10));
			    			//获取时间的值
							var timeend=this.startTime.substring(0,10);
							//把时间按"_"切成数组
							var ssbegin=timeend.split("-");
							//结束日期
							var histime=new Date(parseInt(ssbegin[0]),(parseInt(ssbegin[1],10)-1),parseInt(ssbegin[2],10));
			    		
			    		list+='<tr>';
			    		//list+='<td align="center"><input type="checkbox" name="discountbox" value="'+this.id+'"></td>';
			    		if(jQuery("#isForceFee").val()==ISNEED_REBATES_TRUE && totime>histime)
			    		{
			    			cindex++;
			    			list+='<td align="center"><input type="checkbox" checked="checked" onclick="showforcemoney('+cindex+')" id="forcefeebox'+cindex+'" name="forcefeebox" value="'+this.moneyToPay+'">'+this.feeSubjectName+'</td>';
			    			forceallmoney+=this.moneyToPay;
			    		}
			    		else
			    		{
			    			list+='<td align="center">'+this.feeSubjectName+'</td>';
			    		}		    		
			    		list+='<td align="center">第'+this.feeBatchId+'次缴费</td>';
			    		list+='<td align="center">'+this.moneyToPay+'</td>';
			    		list+='<td align="center">'+this.startTime.substring(0,10)+'</td>';
			    		list+='</tr>';
			    		allmoney+=this.moneyToPay;
			    	});
			    	list+='<tr><td align="center"><span>合计<span></td><td></td><td align="center"><span>'+allmoney+'</span></td><td></td></tr>';
			    }
			    else
			    {
			    	list+='<tr><td colspan="4" align="center">没有相关数据！</td></tr>';
			    }
			    $('#policyreferencetab > tbody').html(list);
			    
			    //历史缴费
				$('#historyfeetab > tbody').empty();
			   	var lists='';
			   	var moneytoall=0.0;
				if(data.feepDetailList!=null && data.feepDetailList.length>0)
				{
			    	$.each(data.feepDetailList,function(){	
			    		lists+='<tr>';
			    		//list+='<td align="center"><input type="checkbox" name="discountbox" value="'+this.id+'"></td>';
			    		lists+='<td align="center">'+this.paymentSubjectName+'</td>';
			    		lists+='<td align="center">'+this.moneyToPay+'</td>';
			    		lists+='<td align="center">'+this.createdTime.substring(0,10)+'</td>';
			    		lists+='</tr>';
			    		moneytoall+=this.moneyToPay;
			    	});
			    	lists+='<tr><td align="center"><span>合计<span></td><td align="center"><span>'+moneytoall+'</span></td><td></td></tr>';
			    }
			    else
			    {
			    	lists+='<tr><td colspan="3" align="center">没有相关数据！</td></tr>';
			    }
			    $('#historyfeetab > tbody').html(lists);
			    if(jQuery("#isForceFee").val()!=ISNEED_REBATES_TRUE)
			    {
				    if(allmoney-moneytoall<0)
				    {
				    	//给应缴金额赋值
					    jQuery("#moneyAllPut").val(0);
					    //给账面金额赋值			    
					    jQuery("#moneyForPut").val(0);
				    }
				    else
				    {
					    //给应缴金额赋值
					    jQuery("#moneyAllPut").val(allmoney-moneytoall);
					    //给账面金额赋值			    
					    jQuery("#moneyForPut").val(allmoney-moneytoall);
					}
				}
				else
				{
					if(forceallmoney-moneytoall<0)
				    {
				    	//给应缴金额赋值
					    jQuery("#moneyAllPut").val(0);
					    //给账面金额赋值			    
					    jQuery("#moneyForPut").val(0);
				    }
				    else
				    {
					    //给应缴金额赋值
					    jQuery("#moneyAllPut").val(forceallmoney-moneytoall);
					    //给账面金额赋值			    
					    jQuery("#moneyForPut").val(forceallmoney-moneytoall);
					}
					if(cindex>1)
					{
						for(var i=1;i<=cindex-1;i++)
						{
							jQuery("#forcefeebox"+i).attr("disabled",true);
						}
					}	
				}
			    historyallmoney=moneytoall;
			}
			
			
			//ajax回调函数   刷新新收费政策
			function ajax_showpendingfeepayment(data)
			{			
				if(data.isfail)
				{
					//隐藏添加缴费单明细隐藏域
					jQuery("#adddetaildiv").hide();
					//清空使用的优惠卷ids
					disids="";
					jQuery("#discountIds").val("");
					jQuery("#showDialog").html('<b>刷新成功！</b>');
					jQuery('#message_returns_tips').dialog("open");	
				}
				else
				{
					jQuery("#showDialog").html('<b>刷新失败！没有新的收费政策！</b>');
					jQuery('#message_returns_tips').dialog("open");	
				}
			}
			//ajax回调函数  刷新不需要申请的学生优惠
			function ajax_refreshdiscount(data)
			{				
			    
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
		
		<!--显示可以使用优惠券-->
		<a:ajax 
			pluginCode="110"
			successCallbackFunctions="ajax_showdiscount" 
			parameters="{studentId:jQuery('#studentId').val(),feeSubjectId:jQuery('#feeSubjectId').val()}" 
			urls="/finance/payment/list_student_can_using_application2_ajax" 
		/>
		
		<!--使用优惠券-->
		<a:ajax 
			pluginCode="120"
			successCallbackFunctions="ajax_usingdiscount" 
			parameters="{discountpolicyId:discountpolicyId,allmoney:jQuery('#allmoney').html(),reducemoney:jQuery('#reducemoney').html()}" 
			urls="/finance/payment/using_student_feesubject_discount_application_ajax" 
		/>
		
		<!--使用优惠券2(防止账面金额变动，重新计算优惠金额总数)-->
		<a:ajax 
			pluginCode="220"
			successCallbackFunctions="ajax_usingdiscount_rewrite" 
			parameters="{discountpolicyIds:rewritediscountpolicyId,allmoney:rewriteallmoney,reducemoney:rewritereducemoney}" 
			urls="/finance/payment/using_student_feesubject_discount_application_rewrite_ajax" 
		/>
		
		<!--添加除报名费和测试费之外的其他所有费用-->
		<a:ajax 
			pluginCode="130"
			successCallbackFunctions="ajax_addtestpayment" 
			parameters="jQuery('#feemyform').serializeObject()" 
			urls="/finance/payment/add_other_fee_payment_rewrite_ajax" 			
		/>
		
		<!--显示该学生相关的充值金额-->
		<a:ajax 
			pluginCode="140"
			successCallbackFunctions="ajax_showaccount" 
			parameters="{studentId:jQuery('#studentId').val(),feeSubjectId:jQuery('#feeSubjectId').val()}" 
			urls="/finance/payment/show_student_using_account_rewrite_ajax" 
		/>
		
		<!--显示该学生相关的充值金额(查询科目的时候自动加载显示出来)-->
		<a:ajax 
			pluginCode="150"
			successCallbackFunctions="ajax_showaccount2" 
			parameters="{studentId:jQuery('#studentId').val(),feeSubjectId:jQuery('#feeSubjectId').val()}" 
			urls="/finance/payment/show_student_using_account_rewrite_ajax" 
		/>
		
		<!--显示缴费信息-->
		<a:ajax 
			pluginCode="210"
			successCallbackFunctions="ajax_showshcoolpayment" 
			parameters="{feeSubjectId:jQuery('#feeSubjectId').val(),studentId:jQuery('#studentId').val()}" 
			urls="/finance/payment/show_student_school_feepayment_ajax" 
		/>
		
		<!--刷新新收费政策-->
		<a:ajax 
			pluginCode="260"
			successCallbackFunctions="ajax_showpendingfeepayment" 
			parameters="{studentId:jQuery('#studentId').val()}" 
			urls="/finance/payment/update_student_penging_fee_payments_ajax" 
		/>
		
		<!--刷新不需要申请的学生优惠-->
		<a:ajax 
			successCallbackFunctions="ajax_refreshdiscount" 
			parameters="{studentId:jQuery('#studentId').val()}" 
			urls="/enrollment/refresh_student_application_unapply_ajax" 
			pluginCode="290"
			isOnload="all"
		/>
	</head>
  
  <body>
  		
		<!-- 头开始 -->
		<head:head title="缴费管理">
			<html:a text="学生充值" icon="add" href="/finance/studentaccountmanagement/list_student_account_management" target="_blank"/>
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				<form id="myform" name="myform">
					<input type="hidden" id="branchId" name="student.branchId" value="${student.branchId}"/>
					<input type="hidden" id="batchId" name="student.enrollmentBatchId" value="${student.enrollmentBatchId}"/>
				</form>
				<form id="feemyform" name="feemyform">
					<input type="hidden" id="studentId" name="feePayment.studentId" value="${student.id}"/>	
					<input type="hidden" id="indexcount" name="indexcount" value="0"/>
					<input type="hidden" id="academyId" name="academyId" value="${student.academyId}"/>
					<input type="hidden" id="isForceFee" name="isForceFee" value="${isForceFee}"/>
					
					<input type="hidden" id="feeSubjectIds" name="feeSubjectIds" value=""/>
					
					<!-- 打印 -->
					<input type="hidden" id="xuefei" name="xuefei" value="0"/>
					<input type="hidden" id="jiaocaifei" name="jiaocaifei" value="0"/>
					<input type="hidden" id="qitafei" name="qitafei" value="0"/>
					<input type="hidden" id="bukaofei" name="bukaofei" value="0"/>
					<input type="hidden" id="allfei" name="allfei" value="0"/>
					<input type="hidden" id="daxieallfei" name="daxieallfei" value="0"/>
					<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">缴费单</th>
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
							<td ><span style="color:black;" id="sexSp" ></span>	</td>
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
					  <table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;" class="feehtml">缴费单明细</th>
						</tr>
						</table>	
						  <table class="add_table" id="payment_items" style="background: none repeat scroll 0 0 #FFFF99;">
							<thead>
								<tr>
									<th align="center">费用项目</th>
									<th align="center">收费政策参考金额</th>
									<th align="center">本次预缴金额  </th>
									<th align="center">优惠金额  </th>
									<th align="center">使用充值金额</th>
									<th align="center">实收金额</th>
									<th align="center">操作</th>
								</tr>
							</thead>
							
							<tbody>	
								
							</tbody>
							<tfoot><tr><td align="center"><span>合计金额</span></td><td></td><td></td><td></td><td></td><td align="center"><span id="moneyAllSpan">0</span></td><td></td></tr></tfoot>
						  </table>			  
						  <table class="add_table" id="subtable">
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
									<input class="btn_black_61" type="button" value="完成缴费" onclick="showsubmit();" id="submit"/>
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
					
					<table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;" class="feehtml">选择缴费的费用科目</th>
						</tr>
					</table>	
					<table class="add_table" id="payment_add">
						<thead>
							<tr>
								<td style="width: 20%;" align="right">费用科目：</td>
								<td style="width: 20%;">
									<s:if test="%{feesubjectlist!=null}">
					                	<s:select list="%{feesubjectlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="feesubjectId" id="feesubjectId" cssClass="txt_box_150"/>
					                </s:if>
					                <s:else>
					                	<select name="feesubjectId" id="feesubjectId" class="txt_box_150">
											<option value="0">--请选择--</option>
										</select>
					                </s:else>
					                <input type="hidden" name="feeSubjectId" id="feeSubjectId" value="0"/>	
		                		</td>
								<td style="width: 10%;"><input class="btn_black_61" type="button" value="查询" onclick="javascript:showfeeSubjectFee();"/></td>
								<td style="width: 50%;"></td>
							</tr>
						</thead>
					</table>
				<div id="adddetaildiv" style="display:none">			
					<table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;" class="feehtml">新增缴费</th>
						</tr>
					</table>
					<table class="add_table" id="payment_add">
						<thead>	
							<tr>
								<td>费用科目</td>
								<td>应缴金额(收费政策参考总金额)</td>		
								<td>本次预缴金额</td>
								<td>
									优惠金额(
									<!-- <input type="radio" value="0" name="isDisc" id="isDisc" />按金额 -->
									<input type="radio" value="1" name="isDisc" id="isDisc" checked="checked"/>按政策<a id="usingDisc" href="javascript:usingdiscount();" title="使用优惠券"><img src="<ui:img url="/images/icon_discount.gif" />" /><font size="-2">(使用)</font></a>
									)
								</td>
								<td>充值金额(<span id="showmonspan">0</span>)<a href="javascript:usingaccount();" title="使用充值金额 "><img src="<ui:img url="/images/icon_discount.gif" />" /><font size="-2">(使用充值金额)</font></a></td>
								<td>操作</td>								
							</tr>
							<tr>
								<td>
									<span id="feeSubSpan" style="color:black !important"></span>
								</td>
								<td>
									<input type="text" disabled="disabled" class="txt_box_100" name="moneyAllPut" id="moneyAllPut" value="0"/>
								</td>		
								<td>
									<input type="text" class="txt_box_100" name="moneyForPut" id="moneyForPut" onkeyup="javascript:showcheckmoney('moneyForPut');"/>
								</td>
								<td>
									<input type="text" disabled="disabled" class="txt_box_100" name="moneyDisPut" id="moneyDisPut" onkeyup="javascript:showcheckmoney('moneyDisPut');" value="0"/>
									<input type="hidden" name="discountIds" id="discountIds" value="" />
								</td>
								<td>
									<input type="text" disabled="disabled" class="txt_box_100" name="moneyHasPut" id="moneyHasPut" value="0"/>
								</td>
								
								<td><input class="btn_black_61" type="button" value="新增缴费" onclick="javascript:addcheckaddfeepment();"/></td>
								
							</tr>
							<tr>
								<td align="center" colspan="2"></td>
								<td align="left" colspan="4">
									<span>备注：</span>1.优惠券基数：取应缴金额和本次预缴金额之间的最小金额作为优惠券基数金额！<br/>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.如果本次预缴金额大于应缴金额，则多余的钱放入该学生的充值账户！<br/>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.先确定本次预缴金额后再使用优惠券和充值金额！<br/>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.优惠金额：按金额时，默认为弘成总部优惠！<br/>
								</td>
							</tr>
						</thead>
					</table>
					<table style="width: 100%; border: 0px;">
						<tr>
							<td style="width: 56%;" valign="top">
								<table class="gv_table_2">
							  		<tr>
									 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
									 	<th style="text-align:left; font-weight:bold;" class="feehtml">收费政策参考标准</th>
									</tr>
								</table>				  
								<table class="add_table" id="policyreferencetab" style="background: none repeat scroll 0 0 #CCFFCC;">
									<thead>
										<tr>
											<th align="center">费用项目</th>
											<th align="center">缴费次数</th>
											<th align="center">应缴金额  </th>
											<th align="center">开始时间  </th>
										</tr>
									</thead>					
									<tbody>	
											
									</tbody>						
								</table>
							</td>
							<td style="width: 44%;" valign="top">
								<table class="gv_table_2">
							  		<tr>
									 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
									 	<th style="text-align:left; font-weight:bold;" class="feehtml">历史缴费记录</th>
									</tr>
								</table>				  
								<table class="add_table" id="historyfeetab" style="background: none repeat scroll 0 0 #CCFFCC;">
									<thead>
										<tr>
											<th align="center">费用项目</th>
											<th align="center">缴费金额</th>
											<th align="center">缴费时间  </th>
										</tr>
									</thead>					
									<tbody>	
											
									</tbody>						
								</table>
							</td>
						</tr>
					</table>
				</div>			
			</body:body>
			
		<!--弹出层-->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<div id="message_confirm" style="display:none">
			<div align="center" id="showconfirm">
				如果不选择政策参考标准，则本次预缴金额将直接放入充值账户！，确认不选择么？
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
			<table class="add_table">
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
		
		<div id="show_for_account" style="display:none">
			<table class="gv_table_2" id="showaccount">
				<thead>
					<tr>
						<th>学生</th>
						<th>费用科目</th>
						<th>该科目剩余金额</th>
						<th>账户总金额</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">${student.name}</td>
						<td align="center"><span style="color:black !important" id="feeSubjectName"></span></td>
						<td align="center"><span style="color:black !important" id="stuaccountmoney"></span></td>
						<td align="center"><span style="color:black !important" id="stuallaccount"></span></td>
					</tr>
				</tbody>
			</table>
			<table class="add_table" id="">
				<tfoot>
					<tr>
						<td align="center" colspan="4">
							本次预缴金额：<span style="" id="allmoneyacc"></span>
							优惠金额：<span style="" id="reducemoneyacc">0</span>
							使用充值金额：<input class="txt_box_100" id="useaccount" value="0" />
						</td>
					</tr>
				</tfoot>
			</table>
		</div>	
		<div id="null_for_close" style="display:none">
			<div align="center" id="showclose">
			
			</div>
		</div>
		
		<div id="message_confirm_print" style="display:none">
			<div align="center" id="showconfirm">
				<b>缴费成功，是否现在打印收据？</b>
				<br/>（点击”取消“关闭当前页！）
			</div>
		</div>	
		<!--  打印           -->
		<div id="message_print_tips" style="display:none">
			<div align="center" id="showdiv">
				<!--  a href="" onclick="javascript:Design1();"><b>模板设计</b></a><br/>-->
				<a href="javascript:void(0)" onclick="javascript:Preview1();"><b>打印收据预览</b></a><br/>
				<!-- a href="javascript:void(0)" onclick="javascript:RealPrint();"><b>打印收据</b></a><br/> -->
				（点击”关闭“关闭当前页！）
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
				LODOP.ADD_PRINT_TEXT(155,615,140,21,"0");
				LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
				LODOP.SET_PRINT_STYLEA(0,"Bold",1);
				LODOP.ADD_PRINT_TEXT(186,186,130,20, "0");
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
