<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加退费</title>
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
		
		<script type="text/javascript">
			//加载事件
			jQuery(function(){
									
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
			//提交前的验证
			function showsubmit()
			{
				if(golFeeSubjectIds=="" && golFeePaymentDetailIds=="")
				{
					jQuery("#showDialog").html('<b>退费单明细不能为空！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(jQuery('#createdTime').val()=="")
				{
					jQuery("#showDialog").html('<b>请选择退费时间！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else
				{
					//alert(1);
					if(golFeeSubjectIds.length>0)
					{
						jQuery("#feeSubjectIds").val(golFeeSubjectIds.substring(0,(golFeeSubjectIds.length-1)));
					}
					if(golFeePaymentDetailIds!="" && golFeePaymentDetailIds.length>0)
					{
						jQuery("#feePaymentDetailIds").val(golFeePaymentDetailIds.substring(0,(golFeePaymentDetailIds.length-1)));
					}
					//alert(jQuery("#feeSubjectIds").val());
					
					ajax_130_1();//添加除退费单
				}
			}
			
			 //处理输入的钱是否正确（学生充值账户）
		    function showcheckmoney(id)
		    {
				if(dealwithmoney(jQuery("#"+id).val())==-1)
				{
					jQuery("#"+id).val("");
					jQuery("#showDialog").html('<b>输入格式不正确，只能输入整数和小数！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else if((dealwithmoney(jQuery("#"+id).val())-0)>(jQuery("#"+id+"span").html()-0))
				{
					jQuery("#"+id).val(jQuery("#"+id+"span").html());
					jQuery("#showDialog").html('<b>输入充值退费金额不能大于该费用科目的账户剩余金额！</b>');
					$('#message_returns_tips').dialog("open");
				}
			}
			 //处理输入的钱是否正确（缴费单明细）
		    function showcheckmoney2(id)
		    {
		    	//alert((jQuery("#stuyitf"+id).html()-0));
				if(dealwithmoney(jQuery("#stuRefund"+id).val())==-1)
				{
					jQuery("#stuRefund"+id).val("");
					jQuery("#showDialog").html('<b>输入格式不正确，只能输入整数和小数！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else if((dealwithmoney(jQuery("#stuRefund"+id).val())-0)>((jQuery("#stushijiao"+id).html()-0)-(jQuery("#stuyitf"+id).html()-0)))
				{
					jQuery("#stuRefund"+id).val((jQuery("#stushijiao"+id).html()-0)-(jQuery("#stuyitf"+id).html()-0));
					jQuery("#showDialog").html('<b>输入退费金额不能大于缴费单实缴金额与已退费金额之差！</b>');
					$('#message_returns_tips').dialog("open");
				}
			}
			
			//添加退费单明细
			var golFeeSubjectIds="";//费用科目id字符串集合
			var golFeePaymentDetailIds="";//退费单明细id字符串集合
			function addRefundDetail()
			{
				
				//alert(jQuery("input[name='stuPayment']:checked").length);
				if((jQuery("input[name='stuPayment']").length==0 || jQuery("input[name='stuPayment']:checked").length==0) && (jQuery("#stuAccount").val()==null || jQuery("#stuAccount").val()==""))
				{
					jQuery("#showDialog").html('<b>请填写退费金额明细！</b>');
					$('#message_returns_tips').dialog("open");
				}
				else
				{

					if(jQuery("input[name='stuPayment']").length!=0 && jQuery("input[name='stuPayment']:checked").length!=0)
					{
						jQuery("input[name='stuPayment']").each(function()
						{
				            if(jQuery(this).attr("checked")==true)
				            {
				            	golFeePaymentDetailIds+=jQuery(this).val()+",";//退费单明细id字符串集合
				            	
				            	var fpId=jQuery(this).val();//退费单明细id
				            	//总基数
				            	var countall=((jQuery("#branchacc"+fpId).html()-0)+(jQuery("#ceduacc"+fpId).html()-0)+(jQuery("#academyacc"+fpId).html()-0));
				            	var branchall=accMul((jQuery("#branchacc"+fpId).html()-0),dealwithmoney(jQuery("#stuRefund"+fpId).val()));
				            	var ceduall=accMul((jQuery("#ceduacc"+fpId).html()-0),dealwithmoney(jQuery("#stuRefund"+fpId).val()));
				            	var academyall=accMul((jQuery("#academyacc"+fpId).html()-0),dealwithmoney(jQuery("#stuRefund"+fpId).val()));
				            	var channelall=accMul((jQuery("#channelcc"+fpId).html()-0),dealwithmoney(jQuery("#stuRefund"+fpId).val()));
								if((countall-0)==0)
								{
									countall=1;
								}
								var list="";
								list+='<tr id="ttp'+fpId+'">';
								list+='<td align="center">'+jQuery("#zhfeb").html()+'</td>';				
								list+='<td align="center"><span>'+jQuery("#stushijiao"+fpId).html()+'</span></td>';
								
								list+='<td align="center"><span>'+accDiv(branchall,countall).toFixed(2)+'</span></td>';
								list+='<td align="center"><span>'+accDiv(ceduall,countall).toFixed(2)+'</span></td>';
								list+='<td align="center"><span>'+accDiv(academyall,countall).toFixed(2)+'</span></td>';
								
								list+='<td align="center"><span>'+accDiv(channelall,countall).toFixed(2)+'</span></td>';
								list+='<td align="center"><span>'+dealwithmoney(jQuery("#stuRefund"+fpId).val())+'</span></td>';
								list+='<td align="center"><span>0</span></td>';
								list+='<td align="center">';
								list+='<a href="javascript:removettp('+fpId+');" title="删除">删除</a>';
								
								list+='<input type="hidden" name="amountPaiedshijiao'+fpId+'" id="amountPaiedshijiao'+fpId+'" value="'+jQuery("#stushijiao"+fpId).html()+'"/>';
								
								list+='<input type="hidden" name="amountPaied'+fpId+'" id="amountPaied'+fpId+'" value="'+dealwithmoney(jQuery("#stuRefund"+fpId).val())+'"/>';
								
								list+='<input type="hidden" name="payBranchCedu'+fpId+'" id="payBranchCedu'+fpId+'" value="'+accDiv(branchall,countall).toFixed(2)+'"/>';
								list+='<input type="hidden" name="payAcademyCedu'+fpId+'" id="payAcademyCedu'+fpId+'" value="'+accDiv(ceduall,countall).toFixed(2)+'"/>';
								list+='<input type="hidden" name="payCeduAcademy'+fpId+'" id="payCeduAcademy'+fpId+'" value="'+accDiv(academyall,countall).toFixed(2)+'"/>';
								list+='<input type="hidden" name="paymentByChannel'+fpId+'" id="paymentByChannel'+fpId+'" value="'+accDiv(channelall,countall).toFixed(2)+'"/>';
								
								list+='</td>';
								list+='</tr>';	
								
								jQuery(list).appendTo(jQuery('#payment_items > tbody'));
								//隐藏添加退费单明细隐藏域
								jQuery("#adddetaildiv").hide();
						
								//合计总退费金额
								jQuery("#moneyAllShijiao").html(((jQuery("#moneyAllShijiao").html()-0)+(jQuery("#stushijiao"+fpId).html()-0)).toFixed(2));
								jQuery("#moneyAllTuifei").html(((jQuery("#moneyAllTuifei").html()-0)+(dealwithmoney(jQuery("#stuRefund"+fpId).val())-0)).toFixed(2));
								jQuery("#moneyAllCeduTf").html(((jQuery("#moneyAllCeduTf").html()-0)+(accDiv(ceduall,countall).toFixed(2)-0)).toFixed(2));		
								jQuery("#moneyAllAcademyTf").html(((jQuery("#moneyAllAcademyTf").html()-0)+(accDiv(academyall,countall).toFixed(2)-0)).toFixed(2));		
								jQuery("#moneyAllChannelTf").html(((jQuery("#moneyAllChannelTf").html()-0)+(accDiv(channelall,countall).toFixed(2)-0)).toFixed(2));
								jQuery("#moneyAllBranchTf").html(((jQuery("#moneyAllBranchTf").html()-0)+(accDiv(branchall,countall).toFixed(2)-0)).toFixed(2));				
								
				            }
						});				
					}
					//alert(jQuery("#stuAccount").val());
					if(jQuery("#stuAccount").val()!=null && jQuery("#stuAccount").val()!="" &&　jQuery("#stuAccount").val()!=0)
					{			
						//退费科目id
						var fsId=jQuery("#feeSubjectId").val();
						var list="";
						list+='<tr id="ttr'+fsId+'">';
						list+='<td align="center">'+jQuery("#zhfeb").html()+'</td>';				
						list+='<td align="center"><span>0</span></td>';
						list+='<td align="center"><span>0</span></td>';
						list+='<td align="center"><span>0</span></td>';
						list+='<td align="center"><span>0</span></td>';
						list+='<td align="center"><span>0</span></td>';
						list+='<td align="center"><span>0</span></td>';
						list+='<td align="center"><span id="moneyToAcc'+fsId+'">'+jQuery("#stuAccount").val()+'</span></td>';
						list+='<td align="center">';
						list+='<a href="javascript:removettr('+fsId+');" title="删除">删除</a>';
						list+='<input type="hidden" name="moneyToAccount'+fsId+'" id="moneyToAccount'+fsId+'" value="'+jQuery("#stuAccount").val()+'"/>';
						list+='</td>';
						list+='</tr>';	
						//list+='<tr><td align="center"><span><b>合计</b></span></td><td align="center"><b>0</b></td><td align="center"><b>0</b></td><td align="center"><b>0</b></td><td align="center"><b>0</b></td><td align="center"><b>0</b></td><td align="center"><b>'+jQuery("#stuAccount").val()+'</b></td><td></td></tr>';				
						jQuery(list).appendTo(jQuery('#payment_items > tbody'));
						//隐藏添加退费单明细隐藏域
						jQuery("#adddetaildiv").hide();
						
						//合计金额
						jQuery("#moneyAllAccount").html(((jQuery("#moneyAllAccount").html()-0)+(jQuery("#stuAccount").val()-0)).toFixed(2));
						//费用科目id字符串集合
						golFeeSubjectIds+=fsId+",";
						
					}
				}	
			}
			//删除确定的退费明细(充值账户)
			function removettr(op)
		    {
		    	if(confirm("确认删除么？"))
		    	{
			    	//删除相应的费用科目id
			    	//alert(golbalIds);
			    	if(golFeeSubjectIds!="" && golFeeSubjectIds.length>0)
			    	{
			    		var golids=golFeeSubjectIds.substring(0,(golFeeSubjectIds.length-1));
			    		var gids =golids.split(",");
			    		golFeeSubjectIds="";
			    		for(var i=0;i<gids.length;i++)
			    		{
			    			if(op!=gids[i])
			    			{
			    				golFeeSubjectIds+=gids[i]+",";
			    			}
			    		}
			    	}
			    	//合计退费金额相应减少
			    	jQuery("#moneyAllAccount").html((jQuery("#moneyAllAccount").html()-0)-(jQuery("#moneyToAcc"+op).html()-0));
			    	//删除行
			    	jQuery('#ttr'+op).remove();
			    	//alert(golbalIds);
			    }	
		    }
		    //删除确定的退费明细(缴费单明细)
			function removettp(op)
		    {
		    	if(confirm("确认删除么？"))
		    	{
		    		//alert(op);
			    	//删除相应的缴费单明细id
			    	//alert(golFeePaymentDetailIds);
			    	if(golFeePaymentDetailIds!="" && golFeePaymentDetailIds.length>0)
			    	{
			    		var golids=golFeePaymentDetailIds.substring(0,(golFeePaymentDetailIds.length-1));
			    		var gids =golids.split(",");
			    		golFeePaymentDetailIds="";
			    		for(var i=0;i<gids.length;i++)
			    		{
			    			if(op!=gids[i])
			    			{
			    				golFeePaymentDetailIds+=gids[i]+",";
			    			}
			    		}
			    	}
			    	//alert(golFeePaymentDetailIds);
			    	//合计退费金额相应减少
			    	jQuery("#moneyAllShijiao").html((jQuery("#moneyAllShijiao").html()-0)-(jQuery("#amountPaiedshijiao"+op).val()-0));
					jQuery("#moneyAllTuifei").html((jQuery("#moneyAllTuifei").html()-0)-(jQuery("#amountPaied"+op).val()-0));
					jQuery("#moneyAllCeduTf").html((jQuery("#moneyAllCeduTf").html()-0)-(jQuery("#payAcademyCedu"+op).val()-0));		
					jQuery("#moneyAllAcademyTf").html((jQuery("#moneyAllAcademyTf").html()-0)-(jQuery("#payCeduAcademy"+op).val()-0));		
					jQuery("#moneyAllChannelTf").html((jQuery("#moneyAllChannelTf").html()-0)-(jQuery("#paymentByChannel"+op).val()-0));		
			    	jQuery("#moneyAllBranchTf").html((jQuery("#moneyAllBranchTf").html()-0)-(jQuery("#payBranchCedu"+op).val()-0));				
			    	//删除行
			    	jQuery('#ttp'+op).remove();
			    	
			    }	
		    }
			//除法函数，用来得到精确的乘法结
			function accDiv(arg1,arg2)
			{
			
				var t1=0,t2=0,r1,r2; 
				
				try{t1=arg1.toString().split(".")[1].length}catch(e){} 
				
				try{t2=arg2.toString().split(".")[1].length}catch(e){} 
				
				with(Math)
				{ 
				
					r1=Number(arg1.toString().replace(".","")) 
					
					r2=Number(arg2.toString().replace(".",""))
					
					return (r1/r2)*pow(10,t2-t1); 
				}
			
			} 
			
			//乘法函数，用来得到精确的乘法结果 
			
			function accMul(arg1,arg2)
			{ 
	
				var m=0,s1=arg1.toString(),s2=arg2.toString(); 
				
				try{m+=s1.split(".")[1].length}catch(e){} 
				
				try{m+=s2.split(".")[1].length}catch(e){} 
				
				return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m) 
			
			}
			
			//查询费用科目当前的缴费信息
			function showfeeSubjectFee()
			{
				var isback=false;
				//判断该费用科目是否已存在缴费单明细里
				if(golFeeSubjectIds!="" && golFeeSubjectIds.length>0)
		    	{
		    		var golids=golFeeSubjectIds.substring(0,(golFeeSubjectIds.length-1));
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
				//else if(isback==true)
				//{
				//	jQuery("#showDialog").html('<b>该费用科目已添加过缴费，不能重复添加！</b>');
				//	$('#message_returns_tips').dialog("open");
				//}
				else
				{
					//显示添加缴费单明细隐藏域
					jQuery("#adddetaildiv").show();
					
					//费用科目赋值
					jQuery("#feeSubjectId").val(jQuery("#feesubjectId").val());
					jQuery("#zhfeb").html(jQuery("#feesubjectId").find("option:selected").text());
					
					ajax_210_1();//显示缴费信息
				}
			}
			
			//历史缴费单的checkbox，控制退费金额的输入框
			function showcheckbox(id)
			{
				if(jQuery("#stuPayment"+id).attr("checked")==true)
				{
					jQuery("#stuRefund"+id).attr("disabled",false);
				}
				else
				{
					jQuery("#stuRefund"+id).attr("disabled",true);
				}
				
			}
			
			
			//ajax回调函数   添加退费
			function ajax_addrefundpayment(data)
			{			
				if(data.replayadd)
				{
					jQuery("#showDialog").html('<b>已添加过，不要重复添加！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else if(data.isback)
				{
					jQuery("#indexcount").val(1);
					//jQuery("#showDialog").html('<b>缴费成功！</b>');
					//jQuery('#message_returns_tips').dialog("open");
										
					jQuery("#showclose").html('<b>退费申请成功！点击确定关闭当前页。</b>');
					$('#null_for_close').dialog("open");
					
				}
				else
				{
					jQuery("#showDialog").html('<b>退费申请失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
			}
			
			//ajax回调函数   显示历史缴费和充值账户
			var cindex=0;//控制强制收费的checkbox
			function ajax_showaccountpayment(data)
			{
				
				//充值账户
				var isback=false;
				//判断该费用科目是否已存在缴费单明细里
				if(golFeeSubjectIds!="" && golFeeSubjectIds.length>0)
		    	{
		    		var golids=golFeeSubjectIds.substring(0,(golFeeSubjectIds.length-1));
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
				$('#policyreferencetab > tbody').empty();
			   	var list='';
			   	jQuery("#allaccountmon").html("(总金额："+data.studentAllAccount+")");
			   	list+='<tr>';
			    list+='<td align="center">'+jQuery("#feesubjectId").find("option:selected").text()+'</td>';
			    list+='<td align="center"><span id="stuAccountspan" style="color:black !important">'+data.studentAccount+'</td>';
			    //list+='<td align="center">'+data.studentAllAccount+'</td>';
			    if(isback==true)
			    {
			    	list+='<td align="center"><input type="text" name="stuAccount" id="stuAccount" onkeyup="javascript:showcheckmoney(\'stuAccount\');" class="txt_box_100" disabled="true"/></td>';
			   	}
			   	else
			   	{
			   		list+='<td align="center"><input type="text" name="stuAccount" id="stuAccount" onkeyup="javascript:showcheckmoney(\'stuAccount\');" class="txt_box_100" /></td>';
			   	}
			    list+='</tr>';
			    $('#policyreferencetab > tbody').html(list);
			    
			    //历史缴费
				$('#historyfeetab > tbody').empty();
			   	var lists='';
				if(data.feepDetailList!=null && data.feepDetailList.length>0)
				{
			    	$.each(data.feepDetailList,function(){	
			    		var isback1=false;
			    		
						//判断该缴费单明细是否已存在退费单明细里
						if(golFeePaymentDetailIds!="" && golFeePaymentDetailIds.length>0)
				    	{
				    		var golids=golFeePaymentDetailIds.substring(0,(golFeePaymentDetailIds.length-1));
				    		var gids =golids.split(",");
				    		for(var i=0;i<gids.length;i++)
				    		{
				    			if(this.id==gids[i])
				    			{
				    				isback1=true;
				    				break;
				    			}
				    		}
				    	}
				
			    		lists+='<tr>';
			    		if(isback1==true || this.refundLock==LOCKING_TRUE || (this.rebateStatus>PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN && this.rebateStatus<=PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO))
			    		{
			    			//lists+='<td align="center"><input type="checkbox" name="stuPayment" id="stuPayment'+this.id+'" value="'+this.id+'" disabled="true" /></td>';
			    			lists+='<td align="center"></td>';
			    		}
			    		else
			    		{
			    			lists+='<td align="center"><input type="checkbox" name="stuPayment" id="stuPayment'+this.id+'" value="'+this.id+'" onclick="showcheckbox('+this.id+')"/></td>';
			    		}	
			    		lists+='<td align="center">'+this.paymentSubjectName+'</td>';
			    		
			    		lists+='<td align="center"><span id="stushijiao'+this.id+'" style="color:black !important">'+((this.amountPaied-0)+(this.rechargeAmount-0))+'</td>';
			    		if(this.status>=PAYMENT_STATUS_ZONG_BU_QUE_REN)
			    		{
			    			//lists+='<td align="center"><span id="stuzxhuikuan'+this.id+'" style="color:black !important">'+((this.amountPaied-0)+(this.rechargeAmount-0))+'</td>';
			    			lists+='<td align="center"><span id="stuzxhuikuan'+this.id+'" style="color:black !important">'+this.payBranchCedu+'</td>';
			    		}
			    		else
			    		{
			    			lists+='<td align="center"><span id="stuzxhuikuan'+this.id+'" style="color:black !important">0</td>';
			    		}
			    		if(this.status>=PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO)
			    		{
			    			lists+='<td align="center"><span id="stuyxdakuan'+this.id+'" style="color:black !important">'+this.payCeduAcademy+'</sapn></td>';
			    		}
			    		else
			    		{
			    			lists+='<td align="center"><span id="stuyxdakuan'+this.id+'" style="color:black !important">0</sapn></td>';
			    		}
			    		if(this.status>=PAYMENT_STATUS_FAN_KUAN_QUE_REN)
			    		{
			    			lists+='<td align="center"><span id="stuyxfankuan'+this.id+'" style="color:black !important">'+this.payAcademyCedu+'</span></td>';
			    		}
			    		else
			    		{
			    			lists+='<td align="center"><span id="stuyxfankuan'+this.id+'" style="color:black !important">0</span></td>';
			    		}
			    		if(this.status>=PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO)
			    		{
			    			lists+='<td align="center"><span id="stuzsfankuan'+this.id+'" style="color:black !important">'+this.paymentByChannel+'</span></td>';
			    		}
			    		else
			    		{
			    			lists+='<td align="center"><span id="stuzsfankuan'+this.id+'" style="color:black !important">0</span></td>';
			    		}
			    		lists+='<td align="center" style="color:#FF3399"><span id="branchacc'+this.id+'" style="color:#FF3399 !important">'+this.branchAccount+'</span></td>';	
			    		lists+='<td align="center" style="color:#FF3399"><span id="ceduacc'+this.id+'" style="color:#FF3399 !important">'+this.ceduAccount+'</span></td>';	
			    		lists+='<td align="center" style="color:#FF3399"><span id="academyacc'+this.id+'" style="color:#FF3399 !important">'+this.academyAccount+'</span></td>';
			    		lists+='<td align="center" style="color:#FF3399"><span id="channelcc'+this.id+'" style="color:#FF3399 !important">'+this.channelAccount+'</span></td>';	    		
			    		lists+='<td align="center" style="color:#FF3399"><span id="stuyitf'+this.id+'" style="color:#FF3399 !important">'+this.refundAmount+'</span></td>';
			    		
			    		lists+='<td align="center">'+this.createdTime.substring(0,10)+'</td>';
			    		lists+='<td align="center">'+this.status.getPaymentStatus()+'</td>';
			    		if(this.refundLock==LOCKING_TRUE)
			    		{
			    			lists+='<td align="center"><span>退费中</span></td>';
			    		}
			    		else if(this.rebateStatus>PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN && this.rebateStatus<PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO)
			    		{
			    			lists+='<td align="center"><span>招生返款中</span></td>';
			    		}
			    		else if(this.rebateStatus==PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO)
			    		{
			    			lists+='<td align="center"><span>已招生返款</span></td>';
			    		}
			    		else
			    		{
			    			lists+='<td align="center"><input type="text" name="stuRefund'+this.id+'" id="stuRefund'+this.id+'" onkeyup="javascript:showcheckmoney2('+this.id+');" class="txt_box_100" disabled="true"/></td>';
			    		}
			    		lists+='</tr>';
			    	});
			    	
			    }
			    else
			    {
			    	lists+='<tr><td colspan="14" align="center">没有相关数据！</td></tr>';
			    }
			    $('#historyfeetab > tbody').html(lists);
			}
			
	</script>
	
		<!--添加退费单-->
		<a:ajax 
			pluginCode="130"
			successCallbackFunctions="ajax_addrefundpayment" 
			parameters="jQuery('#feemyform').serializeObject()" 
			urls="/finance/refund/add_student_refund_payment_ajax" 			
		/>
		
		<!--显示缴费信息和充值金额信息-->
		<a:ajax 
			pluginCode="210"
			successCallbackFunctions="ajax_showaccountpayment" 
			parameters="{feeSubjectId:jQuery('#feeSubjectId').val(),studentId:jQuery('#studentId').val()}" 
			urls="/finance/refund/show_student_account_and_feepayment_ajax" 
		/>
		
	</head>
  
  <body>
  		
		<!-- 头开始 -->
		<head:head title="添加退费">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				
				<form id="feemyform" name="feemyform">
					<input type="hidden" id="studentId" name="feePayment.studentId" value="${student.id}"/>	
					<input type="hidden" id="indexcount" name="indexcount" value="0"/>
					<input type="hidden" id="academyId" name="academyId" value="${student.academyId}"/>
					
					<input type="hidden" id="feeSubjectIds" name="feeSubjectIds" value=""/>
					<input type="hidden" id="feePaymentDetailIds" name="feePaymentDetailIds" value=""/>
					
					<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">退费单</th>
					</tr>
					</table>
					
					 <table class="add_table" border="0" cellpadding="2" cellspacing="2">
					  	<tr>
					  		<td style="width:15%" align="right">退费单号：</td>
							<td style="width:18%">${code}
							<input type="hidden" id="code" name="feePayment.code" value="${code}"/></td>
							<td style="width:15%" align="right"><span>*</span>退费时间：</td>
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
					  <table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;" class="feehtml">退费单明细</th>
						</tr>
						</table>	
						  <table class="add_table" id="payment_items" style="background: none repeat scroll 0 0 #FFFF99;">
							<thead>
								<tr>
									<th align="center">费用项目</th>
									<th align="center">实缴金额</th>
									<th align="center">学习中心退学生金额  </th>
									<th align="center">弘成退学生金额  </th>
									<th align="center">院校退学生金额</th>
									<th align="center">合作方退弘成金额</th>
									<th align="center">缴费单退学生金额  </th>
									<th align="center">充值账户退学生金额</th>
									<th align="center">操作</th>
								</tr>
							</thead>
							
							<tbody>	
								
							</tbody>
							<tfoot>
								<tr>
									<td align="center"><span><b>总合计</b></span></td>
									<td align="center"><span id="moneyAllShijiao">0</span></td>
									<td align="center"><span id="moneyAllBranchTf">0</span></td>
									<td align="center"><span id="moneyAllCeduTf">0</span></td>
									<td align="center"><span id="moneyAllAcademyTf">0</span></td>
									<td align="center"><span id="moneyAllChannelTf">0</span></td>
									<td align="center"><span id="moneyAllTuifei">0</span></td>
									<td align="center"><span id="moneyAllAccount">0</span></td>
									
									<td></td>
								</tr>
							</tfoot>
						  </table>			  
						  <table class="add_table" id="subtable">
						  	<tr>
						  		<td align="right" width="30%">
						  			退费原因：
						  		</td>
						  		<td align="left">
						  			<textarea name="feePayment.note" id="note" class="txt_box_460" class="" ></textarea>
						  		</td>
						  	</tr>
						  	<tr>
								<td align="center" colspan="2">
									<input class="btn_black_61" type="button" value="完成退费" onclick="showsubmit();" id="submit"/>
									<input class="btn_black_61" type="button" value="关闭" onclick="javascript:window.close();"/>
								</td>
							</tr>
						</table>				
					</form>
					
					<table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;" class="feehtml">选择退费的费用科目</th>
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
					                <input type="hidden" name="feeSubjectName" id="feeSubjectName" value=""/>	
		                		</td>
								<td style="width: 10%;"><input class="btn_black_61" type="button" value="查询" onclick="javascript:showfeeSubjectFee();"/></td>
								<td style="width: 50%;"></td>
							</tr>
						</thead>
					</table>
				<div id="adddetaildiv" style="display:none">			
					
					<table style="width: 100%; border: 0px;">
						<tr>
							<td style="width: 20%;" valign="top">
								<table class="gv_table_2">
							  		<tr>
									 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
									 	<th style="text-align:left; font-weight:bold;" class="feehtml">充值账户<span id="allaccountmon"></span></th>
									</tr>
								</table>				  
								<table class="add_table" id="policyreferencetab" style="background: none repeat scroll 0 0 #CCFFCC;">
									<thead>
										<tr>
											<th align="center">费用科目</th>
											<!-- th align="center">总账户金额  </th> -->
											<th align="center"><span style="color:blank" id="zhfeb"></span>账户金额</th>			
											<th align="center">退费金额  </th>
										</tr>
									</thead>					
									<tbody>	
											
									</tbody>						
								</table>
							</td>
							<td style="width: 80%;" valign="top">
								<table class="gv_table_2">
							  		<tr>
									 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
									 	<th style="text-align:left; font-weight:bold;" class="feehtml">历史缴费记录</th>
									 	<th style="text-align:right; font-weight:bold;" class="feehtml">
									 		<img width="12" height="12" border="0px"  src="<ui:img url="/images/icon_add.gif" />" />
									 		<a href="javascript:addRefundDetail();">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
									 	</th>
									</tr>
								</table>				  
								<table class="add_table" id="historyfeetab" style="background: none repeat scroll 0 0 #CCFFCC;">
									<thead>
										<tr>
											<th align="center">
												<!-- <input type="checkbox" name="stuPaymentAll" checked="checked" onclick="javascript:jQuery('[name=stuPayment]').attr('checked', this.checked);" /> -->
											</th>
											<th align="center">费用科目</th>
											<th align="center">中心收款</th>
											<th align="center">汇款总部</th>
											<th align="center">院校打款</th>
											<th align="center">院校返款</th>
											<th align="center">招生返款</th>
											<th align="center" style="color:#FF3399">中心账户</th>
											<th align="center" style="color:#FF3399">总部账户</th>
											<th align="center" style="color:#FF3399">院校账户</th>
											<th align="center" style="color:#FF3399">合作方账户</th>
											<th align="center" style="color:#FF3399">已退费金额</th>
											<th align="center">缴费时间  </th>
											<th align="center">缴费单状态  </th>
											<th align="center">现退费金额</th>
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
		
		<div id="null_for_close" style="display:none">
			<div align="center" id="showclose">
			
			</div>
		</div>
		
  </body>
</html>
