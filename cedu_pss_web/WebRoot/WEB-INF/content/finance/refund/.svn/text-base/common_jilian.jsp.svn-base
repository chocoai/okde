<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
		
		<script type="text/javascript">
			isLoadPaymentWay=false;
			//加载事件
			jQuery(function(){
				//全局招生批次
				ajax_100_1();
				//全局批次级联
				jQuery('#globalBatchId').change(function(){
					ajax_110_1();//院校					
				});			
				//院校相关级联
				jQuery('#academyId').change(function(){
					ajax_140_1();//招生批次	
								
				});					
				//层次专业级联
				jQuery('#levelId').change(function(){
					ajax_130_1();//专业	
				});	
				
			});
		</script>
			
		<script type="text/javascript">
			var batchId=0;
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
			   	ajax_110_1();//院校
			}
			//ajax回调函数   院校(学习中心、全局批次)
			function ajax_academy(data)
			{				
				$('#academyId').empty();
			    $('#academyId').append('<option value="0">--请选择--</option>');
			    if(data.academyList!=null && data.academyList.length>0)
			    {
			    	$.each(data.academyList,function(){	
			    		$('#academyId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}	
			   	
			   	$('#batch').html('无');
			   	$('#batchId').val(0);
			   	//batchId=parseInt($('#batchId').val());
			   	ajax_120_1();//层次	
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
			   	ajax_130_1();//专业	
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
			//ajax回调函数  招生批次(院校、全局批次)
			function ajax_batch(data)
			{				
			    if(data.batch!=null)
			    {
			    	$('#batch').html(data.batch.enrollmentName);
			   		$('#batchId').val(data.batch.id);
			    }	
			    else
			    {
			    	$('#batch').html('无');
			   		$('#batchId').val(0);
			    }
			    //batchId=parseInt($('#batchId').val());
			    ajax_120_1();//层次	
			    if(isLoadPaymentWay){
						//缴费方式
						ajax_payment_1();
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
		</script>
		<!--全局批次(学习中心)-->
		<a:ajax successCallbackFunctions="ajax_global_batch" parameters="{branchId:jQuery('#branchId').val()}" urls="/enrollment/cascade_global_batch_branch_ajax" pluginCode="100"/>
		
		<!--院校(学习中心、全局批次)-->
		<a:ajax successCallbackFunctions="ajax_academy" parameters="{branchId:jQuery('#branchId').val(),globalBatchId:jQuery('#globalBatchId').val()}" urls="/enrollment/cascade_branch_global_batch_academy_ajax" pluginCode="110"/>
		
		<!--层次(招生批次)-->
		<a:ajax successCallbackFunctions="ajax_level" parameters="{batchId:jQuery('#batchId').val()}" urls="/enrollment/cascade_batch_level_ajax" pluginCode="120"/>
		
		<!--专业(层次)-->
		<a:ajax successCallbackFunctions="ajax_major" parameters="{levelId:jQuery('#levelId').val()}" urls="/enrollment/cascade_level_major_ajax" pluginCode="130"/>
		
		<!--招生批次(院校、全局批次)-->
		<a:ajax successCallbackFunctions="ajax_batch" parameters="{academyId:jQuery('#academyId').val(),globalBatchId:jQuery('#globalBatchId').val()}" urls="/enrollment/cascade_global_batch_academy_batch_ajax" pluginCode="140"/>
		
		<!--缴费方式-->
		<a:ajax parameters="jQuery('#myform').serializeObject()" successCallbackFunctions="paymentWayCallback" pluginCode="payment" urls="finance/payment/finance_payment_way_list"/>