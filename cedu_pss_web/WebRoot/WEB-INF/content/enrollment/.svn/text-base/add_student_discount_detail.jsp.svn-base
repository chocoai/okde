<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>添加学生优惠政策</title>
		
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
								
				//选择院校事件
				jQuery('#academyId').change(function(){
					selectbatch();
					jQuery("#batchId").show();
					//院校变化的时候 全部滞空
					selectbranch(0);
					jQuery("#branchId").hide();
					selectlevel(0);
					jQuery("#levelId").hide();
					selectmajor(0);
					jQuery("#majorId").hide();
					
					if(this.selectedIndex!=0 && $('#feesubjectId').val()!=0)
					{
						jQuery('#hidacademyId').val(jQuery('#academyId').val());
						search007();
					}
					
				});
				
				//点击招生批次单选框事件
				//jQuery('[name=batchitem]').click(function(){
				//	var batchId = this.value;
				//	selectbranch(batchId);//招生批次与学习中心级联
				//	selectlevel(batchId);//招生批次与层次级联
				//	jQuery("#branchId").show();
				//	jQuery("#levelId").show();
					
					//刷新专业(选层次后，变了批次， 重新刷新)
				//	selectmajor(0);
				//	jQuery("#majorId").hide();
					//刷新缴费次数      选择批次就是不默认的，不选择就是默认的
					//if(jQuery("input[name='feesitem']").length!=0 && jQuery("input[name='feesitem']:checked").length!=0)
					//{	
					//	selectfeesubject(jQuery("input[name='feesitem']:checked").val());
					//}				
				//});
				//点击层次单选框事件
				//jQuery('[name=levelitem]').click(function(){
				//	var levelId = this.value;
				//	alert(1);
				//	selectmajor(levelId);//层次与专业级联
				//	jQuery("#majorId").show();
				//});	
				
				//合作方级联
				//jQuery('#channelType').change(function(){
				//	ajax_119_1();
				//});	
				//报名前后政策
				jQuery('[name=rayou]').click(function(){
					var index = this.value;
					if(index==POLICY_REGISTRATION_BEFORE)
					{
						jQuery('.divclass').hide();
						$('#academyId').append('<option value="-1">无院校(报名前)</option>');
						jQuery('#academyId').val(-1);
						jQuery('#hidacademyId').val(-1);
						search007();
						selectallbranch();//所有学习中心
						jQuery("#branchId").show();
					}
					else
					{
						//jQuery('.divclass').show();
						//jQuery('#academyId').val(0);
						//jQuery('#hidacademyId').val(0);
						//jQuery('#academyId').change();//选择院校事件    全部滞空
						window.location.reload();
					}
				});
				
				//选择合作方事件
				jQuery('#channelId').change(function(){
					var index = this.value;
					if(index!=0)
					{
						$.ajax({
				    		url:'<uu:url url="/enrollment/find_channel_detail_ajax" />',
				    		data:{channelId:index},
				    		dataType:'json',
				    	 	type:'post',
				    	 	success:function(data){
					    	 	if(data.channel!=null && data.channel.branchId!=0)
					    	 	{
					    	 		if(data.channel.isAll==IS_ALL_FALSE)
					    	 		{
					    	 			jQuery('#hidbranchId').val(0);	  
					    	 		}
					    	 		else
					    	 		{
					    	 			jQuery('#hidbranchId').val(data.channel.branchId);	  
					    	 		}	  	 		
					    	 	}	
					    	 	else
					    	 	{
					    	 		jQuery('#hidbranchId').val(-1);
					    	 	}
					    	 }	
				    	});
					}
					else
					{
						jQuery('#hidbranchId').val(index);
					}
					if(jQuery("input[name='batchitem']").length!=0 && jQuery("input[name='batchitem']:checked").length!=0)
					{
						selectbranch(jQuery("input[name='batchitem']:checked").val());
						//alert("学习中心对应");
					}
				});
				
				//初始化弹出框
				$('#show_for_academy').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'重复的政策(可以选择覆盖)',
					width: 550,
					buttons: {
						'覆盖': function() { 
							$('#message_confirm').dialog({
								title:'确认操作',
								buttons: {
									'确认': function() { 
										//
										coverData(1);
									}, 
									'取消': function() { 
										$(this).dialog("close"); 
									} 
								}
							});
							$('#message_confirm').dialog("open"); 
						}, 
						'追加': function() { 
							$('#message_confirm').dialog({
								title:'确认操作',
								buttons: {
									'确认': function() { 
										//
										coverData(2);
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
				
			});
			
			/////////      /级联/    /////////////////
			 //读取批次
		    function selectbatch()
		    {
		    	$.ajax({
		    		url:'<uu:url url="/enrollment/cascade_academy_batch_ajax" />',
		    		data:{academyId:jQuery('#academyId').val()},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
			    	 	$('#batchId > tbody').empty();
		    	 		var list="";
				    	var index=1;
			    	 	if(data.batchlist!=null&&data.batchlist.length>0)
			    	 	{
				    	 	list+='<tr>';
				    	 	$.each(data.batchlist,function(){	
				    	 		 list+='<td style="width:25%"><input type="radio" name="batchitem" class="batchitem" value="'+this.id+'"/>'+this.enrollmentName+'</td>';
			    	 			 if(index % 4==0)
			    	 			 {
			    	 			 	list+='</tr><tr>';
			    	 			 }
			    	 			 index++;	
				    	 	});
				    	 	list+='</tr>';
			    	 	}
			    	 	else
			    	 	{
			    	 		list+='<tr><td align="center"><span>没有设置院校招生批次!</span></td></tr>';
			    	 	}	
			    	 	$('#batchId > tbody').html(list);	
			    	 	
			    	 	//点击招生批次单选框事件
						jQuery('[name=batchitem]').click(function(){
							var batchId = this.value;
							selectbranch(batchId);//招生批次与学习中心级联
							selectlevel(batchId);//招生批次与层次级联
							jQuery("#branchId").show();
							jQuery("#levelId").show();
							
							//刷新专业(选层次后，变了批次， 重新刷新)
							selectmajor(0);
							jQuery("#majorId").hide();
						})
			    	 }	
		    	});
		    }
			 //读取学习中心
		    function selectbranch(batchId)
		    {
		   		//var batchId=$('#batchId').val();
		    	$.ajax({
		    		url:'<uu:url url="/enrollment/cascade_batch_branch_ajax" />',
		    		data:{batchId:batchId,academyId:jQuery('#academyId').val()},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#branchId > tbody').empty();
		    	 		var list="";
				    	var index=1;
				    	var hidbranchId=jQuery('#hidbranchId').val();
				    	var boolbranch=true;
			    	 	if(data.branchlist!=null&&data.branchlist.length>0)
			    	 	{
				    	 	list+='<tr>';
				    	 	if(hidbranchId==0)
				    	 	{
					    	 	$.each(data.branchlist,function(){	
						    	 	list+='<td style="width:25%"><input type="checkbox" name="branchitem" class="branchitem" value="'+this.id+'"/>'+this.name+'</td>';
					    	 		if(index % 4==0)
					    	 		{
					    	 			list+='</tr><tr>';
					    	 		}
					    	 		index++;					    	 		
					    	 	});
					    	 }
				    	 	else if(hidbranchId==-1)
				    	 	{
				    	 		list+='<tr><td align="center"><span>渠道没有学习中心!</span></td></tr>';
				    	 	}
				    	 	else
				    	 	{
				    	 		boolbranch=false;
				    	 		$.each(data.branchlist,function(){	
				    	 			if(hidbranchId==this.id)
				    	 			{
				    	 				boolbranch=true;
						    	 		list+='<td style="width:25%"><input type="checkbox" name="branchitem" class="branchitem" value="'+this.id+'"/>'+this.name+'</td>';					    	 		
					    	 		}
					    	 	});
					    	 	if(!boolbranch)
					    	 	{
					    	 		list+='<tr><td align="center"><span>渠道与授权学习中心不匹配!</span></td></tr>';
					    	 	}
				    	 	}
				    	 	list+='</tr>';
			    	 	}
			    	 	else
			    	 	{
			    	 		list+='<tr><td align="center"><span>没有授权的学习中心!</span></td></tr>';
			    	 	}	
			    	 	$('#branchId > tbody').html(list);
			    	 }	
		    	});
		    }
		    
		     //读取所有学习中心（报名前政策）
		    function selectallbranch()
		    {
		    	$.ajax({
		    		url:'<uu:url url="/enrollment/find_all_branch_ajax" />',
		    		data:{},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#branchId > tbody').empty();
		    	 		var list="";
				    	var index=1;
			    	 	if(data.branchlist!=null&&data.branchlist.length>0)
			    	 	{
				    	 	list+='<tr>';
					    	$.each(data.branchlist,function(){	
						    	list+='<td style="width:25%"><input type="checkbox" name="branchitem" class="branchitem" value="'+this.id+'"/>'+this.name+'</td>';
					    	 	if(index % 4==0)
					    	 	{
					    	 		list+='</tr><tr>';
					    	 	}
					    	 	index++;					    	 		
					    	 });					    	 
				    	 	
				    	 	list+='</tr>';
			    	 	}
			    	 	else
			    	 	{
			    	 		list+='<tr><td align="center"><span>没有学习中心!</span></td></tr>';
			    	 	}	
			    	 	$('#branchId > tbody').html(list);
			    	 }	
		    	});
		    }
			 //读取层次
		    function selectlevel(batchId)
		    {
		   		//var batchId=$('#batchId').val();
		    	$.ajax({
		    		url:'<uu:url url="/enrollment/cascade_batch_level_ajax" />',
		    		data:{batchId:batchId},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#levelId > tbody').empty();
			    	 	var list="";
			    	 	var index=1;
			    	 	if(data.levellist!=null&&data.levellist.length>0)
			    	 	{
			    	 		list+='<tr>';
			    	 		$.each(data.levellist,function(){	
			    	 			 list+='<td style="width:25%"><input type="radio" name="levelitem" class="levelitem" value="'+this.id+'"/>'+this.level.name+'</td>';
			    	 			 if(index % 4==0)
			    	 			 {
			    	 			 	list+='</tr><tr>';
			    	 			 }
			    	 			 index++;
			    	 		});
			    	 		list+='</tr>';
			    	 	}	
			    	 	else
			    	 	{
			    	 		list+='<tr><td align="center"><span>未设置相关层次!</span></td></tr>';
			    	 	}
			    	 	$('#levelId > tbody').html(list);
			    	 	 //点击层次单选框事件
						jQuery('[name=levelitem]').click(function(){
							var levelId = this.value;
							selectmajor(levelId);//层次与专业级联
							jQuery("#majorId").show();
						});
			    	 }				    	
		    	});
		    }
			 //读取专业
		    function selectmajor(levelId)
		    {
		   		//var levelId=$('#levelId').val();
		    	$.ajax({
		    		url:'<uu:url url="/enrollment/cascade_level_major_ajax" />',
		    		data:{levelId:levelId},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#majorId > tbody').empty();
				    	var list="";
			    	 	var index=1;
			    	 	if(data.majorlist!=null&&data.majorlist.length>0)
			    	 	{
			    	 		list+='<tr>';
				    	 	$.each(data.majorlist,function(){	
				    	 		list+='<td style="width:25%"><input type="checkbox" name="majoritem" class="majoritem" value="'+this.id+'"/>'+this.name+'</td>';
			    	 			 if(index % 4==0)
			    	 			 {
			    	 			 	list+='</tr><tr>';
			    	 			 }
			    	 			 index++;
				    	 	});
				    	 	list+='</tr>';			    	 		
			    	 	}	
			    	 	else
			    	 	{
			    	 		list+='<tr><td align="center"><span>未设置相关专业!</span></td></tr>';
			    	 	}
			    	 	$('#majorId > tbody').html(list);
			    	 }	
		    	});
		    }
		    
			/////////      /ajax提交/    /////////////////	
			//政策提交
			function adddetail()
			{
				if(jQuery("input[name='rayou']").length==0 || jQuery("input[name='rayou']:checked").length==0)
				{
					jQuery("#showDialog").html('<b>保存失败！</b>');
					$('#null_for_academy').dialog("open");
				}
				else
				{
					if(jQuery("input[name='rayou']:checked").val()==POLICY_REGISTRATION_BEFORE)
					{
						adddetailbefore();
					}
					else
					{
						adddetailafter();
					}
				}
			}
			//报名后政策	
			function adddetailafter()
			{
				if(jQuery("#channelType").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择招生途径！</b>');
					$('#null_for_academy').dialog("open");
				}
				//else if(jQuery("#channelId").val()==0 && jQuery('#channelType').val()!=WEB_STU_SOURCE_DEFAULT)
				//{
				//	jQuery("#showDialog").html('<b>请选择合作方！</b>');
				//	$('#null_for_academy').dialog("open");
				//}
				else if(jQuery("#academyId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择院校！</b>');
					$('#null_for_academy').dialog("open");
				}
				//else if(jQuery("input[name='batchitem']").length==0 || jQuery("input[name='batchitem']:checked").length==0)
				//{
				//	jQuery("#showDialog").html('<b>请选择招生批次！</b>');
				//	$('#null_for_academy').dialog("open");
				//}
				//else if(jQuery("input[name='branchitem']").length==0)
				//{
				//	jQuery("#showDialog").html('<b>该批次下没有授权学习中心！</b>');
				//	$('#null_for_academy').dialog("open");
				//}
				//else if(jQuery("input[name='levelitem']").length==0)
				//{
				//	jQuery("#showDialog").html('<b>该批次下没有设置层次！</b>');
				//	$('#null_for_academy').dialog("open");
				//}
				else if(jQuery("#feesubjectId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择费用科目！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(jQuery("input[name='pfeeradio']").length==0 || jQuery("input[name='pfeeradio']:checked").length==0)
				{
					jQuery("#showDialog").html('<b>请选择政策标准！</b>');
					$('#null_for_academy').dialog("open");
				}
				else
				{
					var batchId=-1;//批次id
					if(jQuery("input[name='batchitem']").length!=0 && jQuery("input[name='batchitem']:checked").length!=0)
					{
						batchId=jQuery("input[name='batchitem']:checked").val();//批次id
					}
					
					var branchIds='';//学习中心Ids
					if(jQuery("input[name='branchitem']").length!=0 && jQuery("input[name='branchitem']:checked").length!=0)
					{
						jQuery("input[name='branchitem']").each(function(){
			            	if(jQuery(this).attr("checked")==true)
			            	{
			            		branchIds+=jQuery(this).val()+",";
			                }
						});
					}
					else
					{
						branchIds="-1";
					}
					var levelId=-1;//层次ID
					if(jQuery("input[name='levelitem']").length!=0 && jQuery("input[name='levelitem']:checked").length!=0)
					{
						levelId=jQuery("input[name='levelitem']:checked").val();
					}
					var majorIds='';//批次id
					if(jQuery("input[name='majoritem']").length!=0 && jQuery("input[name='majoritem']:checked").length!=0)
					{
						jQuery("input[name='majoritem']").each(function(){
			            	if(jQuery(this).attr("checked")==true)
			            	{
			            		majorIds+=jQuery(this).val()+",";
			                }
						});
					}
					else
					{
						majorIds="-1";
					}
					var policyFeeId=jQuery("input[name='pfeeradio']:checked").val();//优惠标准id
					
					//添加政策
					addbatchId=batchId;//批次Id
					addbranchIds=branchIds;//学习中心Ids集合
					addlevelId=levelId;//层次Id
					addmajorIds=majorIds;//专业Ids集合
					addpolicyFeeId=policyFeeId;//政策Id
					ajax_130_1();//添加政策
					
				//	 //添加政策
			   // 	$.ajax({
			   // 		url:'<uu:url url="/enrollment/add_student_discount_detail_ajax" />',
			   // 		data:{channelType:jQuery('#channelType').val(),channelId:jQuery('#channelId').val(),academyId:jQuery('#academyId').val(),batchId:batchId,branchIds:branchIds,levelId:levelId,majorIds:majorIds,feesubjectId:jQuery('#feesubjectId').val(),policyFeeId:policyFeeId},
			   // 		dataType:'json',
			  // 	 	type:'post',
			  //  	 	success:function(data){
			  //  	 		var list='';
			  //  	 		list+='<tr>';
			//	    	 	list+='<th><input type="checkbox" onclick="jQuery(\'[name=coverpolicy]\').attr(\'checked\', this.checked)"/></th>';
				//    	 	list+='<th>合作方类别</th>';
			//	    	 	list+='<th>合作方</th>';
			//	    	 	list+='<th>院校</th>';
			//	    	 	list+='<th>学习中心</th>';
			//	    	 	list+='<th>批次</th>';
			//	    	 	list+='<th>层次</th>';
			//	    	 	list+='<th>专业</th>';
			//	    	 	list+='<th>状态</th>';
			//	    	 	list+='</tr>';
			//	    	 	if(data.discountlist!=null&&data.discountlist.length>0)
			//	    	 	{
			//	    	 		$.each(data.discountlist,function(){	
			//		    	 		list+='<tr>';
			//		    	 		if(this.aduitStatus==AUDIT_STATUS_TRUE)
			//		    	 		{
			//		    	 			list+='<td align="center"><input type="checkbox" name="coverpolicy1" disabled="disabled" id="coverpolicy" value="'+this.deleteFlag+'"/></td>';
			//		    	 		}
			//		    	 		else
			//		    	 		{
			//		    	 			list+='<td align="center"><input type="checkbox" name="coverpolicy" id="coverpolicy" value="'+this.deleteFlag+'"/></td>';
			//		    	 		}		
			//		    	 		list+='<td align="center">'+this.channeltypename+'</td>';
			//			    	 	list+='<td align="center">'+this.channelname+'</th>';		    	 	
			//			    	 	list+='<td align="center">'+this.academyname+'</td>';
			//			    	 	list+='<td align="center">'+this.branchname+'</th>';
			//			    	 	list+='<td align="center">'+this.batchname+'</td>';
			//			    	 	list+='<td align="center">'+this.levelname+'</td>';
			//			    	 	list+='<td align="center">'+this.majorname+'</td>';
			//			    	 	if(this.aduitStatus==AUDIT_STATUS_FALSE)
			//			    	 	{
			//			    	 		list+='<td align="center">未审批</td>';
			//			    	 	}
			//			    	 	else if(this.aduitStatus==AUDIT_STATUS_FAIL)
			//			    	 	{
			//			    	 		list+='<td align="center">审批不通过</td>';
			//			    	 	}
			//			    	 	else
			//			    	 	{
			//			    	 		list+='<td align="center">审批通过</td>';
			//			    	 	}
			//			    	 	list+='</tr>';
			//		    	 	});	
			//		    	 	jQuery("#showcover > tbody").html(list);
			//		    	 	
			//		    	 	$('#show_for_academy').dialog('open');	
			//		    	 	   	 		
			//	    	 	}	
			//	    	 	else if(data.backtracks)
			//	    	 	{
			//	    	 		jQuery("#showDialog").html('<b>添加政策成功！</b>');
			//					$('#null_for_academy').dialog("open");
			//	    	 	}
			//	    	 	else
			//	    	 	{
			//	    	 		jQuery("#showDialog").html('<b>添加失败！</b>(可能是层次下没有设置专业)');
			//					$('#null_for_academy').dialog("open");
			//	    	 	}
			//    	 	
			//	    	 }	
			//    	});
		
				}
			}	
			//覆盖原有的数据
			function coverData(operate)
			{
				$('#message_confirm').dialog("close");
				if(jQuery("input[name='coverpolicy']").length==0 || jQuery("input[name='coverpolicy']:checked").length==0)
				{
					if(operate==1)
					{
						jQuery("#showDialog").html('<b>请选择要覆盖的政策！</b>');
					}
					else
					{
						jQuery("#showDialog").html('<b>请选择要追加的政策！</b>');
					}	
					$('#null_for_academy').dialog("open");
				}
				else
				{
				
					var deleteflags='';
					jQuery("input[name='coverpolicy']").each(function(){
			            if(jQuery(this).attr("checked")==true)
			            {
			            	deleteflags+=jQuery(this).val()+",";
			            }
					});
					//覆盖政策
					adddeleteflags=deleteflags;//覆盖的id集合
					addoperate=operate;//操作Id
					ajax_110_1();//覆盖政策
					
			//		 //覆盖政策
			//    	$.ajax({
			//    		url:'<uu:url url="/enrollment/cover_student_discount_detail_ajax" />',
			//    		data:{deleteflags:deleteflags,operate:operate},
			//    		dataType:'json',
			//    	 	type:'post',
			//    	 	success:function(data){
			//    	 		if(operate==1)
			//    	 		{
			//		    	 	if(data.backtracks)
			//		    	 	{	
			//		    	 		$('#show_for_academy').dialog("close");		    	 		
			//			    	 	jQuery("#showDialog").html('<b>覆盖成功！</b>');
			//						$('#null_for_academy').dialog("open"); 	 										
			//		    	 	}	
			//		    	 	
			//		    	 	else
			//		    	 	{
			//		    	 		jQuery("#showDialog").html('<b>覆盖失败！</b>');
			//						$('#null_for_academy').dialog("open");
			//		    	 	}
			//    	 		}
			//    	 		else
			//    	 		{
			//    	 			if(data.backtracks)
			//		    	 	{	
			//		    	 		$('#show_for_academy').dialog("close");		    	 		
			//			    	 	jQuery("#showDialog").html('<b>追加成功(重复的政策不叠加)！</b>');
			//						$('#null_for_academy').dialog("open"); 	 										
			//		    	 	}	
			//		    	 	
			//		    	 	else
			//		    	 	{
			//		    	 		jQuery("#showDialog").html('<b>追加失败！</b>');
			//						$('#null_for_academy').dialog("open");
			//		    	 	}
			//    	 		}
			//	    	 }	
			//    	});
		
				}
			}
			
			//报名前得政策		
			function adddetailbefore()
			{
							
				if(jQuery("input[name='branchitem']").length==0)
				{
					jQuery("#showDialog").html('<b>没有学习中心！</b>');
					$('#null_for_academy').dialog("open");
				}
				
				else if(jQuery("#feesubjectId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择费用科目！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(jQuery("input[name='pfeeradio']").length==0 || jQuery("input[name='pfeeradio']:checked").length==0)
				{
					jQuery("#showDialog").html('<b>请选择政策标准！</b>');
					$('#null_for_academy').dialog("open");
				}
				else
				{
					var branchIds='';//学习中心Ids
					if(jQuery("input[name='branchitem']").length!=0 && jQuery("input[name='branchitem']:checked").length!=0)
					{
						jQuery("input[name='branchitem']").each(function(){
			            	if(jQuery(this).attr("checked")==true)
			            	{
			            		branchIds+=jQuery(this).val()+",";
			                }
						});
					}
					else
					{
						branchIds="-1";
					}
					var policyFeeId=jQuery("input[name='pfeeradio']:checked").val();//优惠标准id
					
					//添加报名前政策
					beforebranchIds=branchIds;//学习中心Ids集合
					beforepolicyFeeId=policyFeeId;//政策Id
					ajax_100_1();//添加报名前政策
					
			//		 //添加报名前政策
			//    	$.ajax({
			//    		url:'<uu:url url="/enrollment/add_before_student_discount_detail_ajax" />',
			//    		data:{branchIds:branchIds,feesubjectId:jQuery('#feesubjectId').val(),policyFeeId:policyFeeId},
			//    		dataType:'json',
			//    	 	type:'post',
			//    	 	success:function(data){
			//    	 		var list='';
			//    	 		list+='<tr>';
			//	    	 	list+='<th><input type="checkbox" onclick="jQuery(\'[name=coverpolicy]\').attr(\'checked\', this.checked)"/></th>';
			//	    	 	list+='<th>学习中心</th>';
			//	    	 	list+='<th>费用科目</th>';
			//	    	 	list+='<th>状态</th>';
			//	    	 	list+='</tr>';
			//	    	 	if(data.discountlist!=null&&data.discountlist.length>0)
			//	    	 	{
			//	    	 		$.each(data.discountlist,function(){	
			//		    	 		list+='<tr>';
			//		    	 		if(this.aduitStatus==AUDIT_STATUS_TRUE)
			//		    	 		{
			//		    	 			list+='<td align="center"><input type="checkbox" name="coverpolicy1" disabled="disabled" id="coverpolicy" value="'+this.deleteFlag+'"/></td>';
			//		    	 		}
			//		    	 		else
			//		    	 		{
			//		    	 			list+='<td align="center"><input type="checkbox" name="coverpolicy" id="coverpolicy" value="'+this.deleteFlag+'"/></td>';
			//		    	 		}		
			//			    	 	list+='<td align="center">'+this.branchname+'</th>';
			//			    	 	list+='<td align="center">'+this.feesubjectname+'</td>';
			//			    	 	if(this.aduitStatus==AUDIT_STATUS_FALSE)
			//			    	 	{
			//			    	 		list+='<td align="center">未审批</td>';
			//			    	 	}
			//			    	 	else if(this.aduitStatus==AUDIT_STATUS_FAIL)
			//			    	 	{
			//			    	 		list+='<td align="center">审批不通过</td>';
			//			    	 	}
			//			    	 	else
			//			    	 	{
			//			    	 		list+='<td align="center">审批通过</td>';
			//			    	 	}
			//			    	 	list+='</tr>';
			//		    	 	});	
			///		    	 	jQuery("#showcover > tbody").html(list);
			//		    	 	
			//		    	 	$('#show_for_academy').dialog('open');	
			//		    	 	   	 		
			//	    	 	}	
			//	    	 	else if(data.backtracks)
			//	    	 	{
			//	    	 		jQuery("#showDialog").html('<b>添加政策成功！</b>');
			//					$('#null_for_academy').dialog("open");
			//	    	 	}
			//	    	 	else
			//	    	 	{
			//	    	 		jQuery("#showDialog").html('<b>添加失败！</b>');
			//					$('#null_for_academy').dialog("open");
			//	    	 	}
			//    	 	
			//	    	 }	
			//    	});
		
				}
			}	
			
		///////////////////////////////收费标准查询
			//查询相关的收费标准
			function showpolicyfee()
			{
				if(jQuery("#academyId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择院校！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(jQuery("#feesubjectId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择费用科目！</b>');
					$('#null_for_academy').dialog("open");
				}
				else 
				{
					jQuery('#hidacademyId').val(jQuery('#academyId').val());
					search007();
				}
			}			
		    	
		    /////////      /优惠标准分页列表/    /////////////////			
			//列表自定义字段显示
			//显示缴费次数
			function showpayment(feePaymentId)
			{
				return "第"+feePaymentId+"次缴费";
			}
			//优惠方式
			function showdiscout(discountWayId)
			{
				if(parseInt(discountWayId)==MONEY_FORM_RATIO)
				{
					return "比例";
				}
				else
				{
					return "金额";
				}
			}
			//优惠主体
			function showtarget(targetObjectId)
			{
				if(parseInt(targetObjectId)==POLICY_TARGET_OBJECT_CEDU)
				{
					return "弘成优惠";
				}
				else if (parseInt(targetObjectId)==POLICY_TARGET_OBJECT_ACADEMY)
				{
					return "院校优惠";
				}
				else if (parseInt(targetObjectId)==POLICY_TARGET_OBJECT_BRANCH)
				{
					return "中心优惠";
				}
				else
				{
					return "渠道优惠";
				}
			}
			//优惠标准
			function showstandard(discountstandard)
			{
				return discountstandard;
			}
			function operat(id)
			{
				return '<input type="radio" name="pfeeradio" value="'+id+'"/>';
			}
			
			
			//ajax回调函数
			function ajax_channel(data){
				
				$('#channelId').empty();
			    $('#channelId').append('<option value="-1">--请选择--</option>');
			    if(data.channellist!=null&&data.channellist.length>0)
			    {
			    	$.each(data.channellist,function(){	
			    		$('#channelId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	});
			   	}	
			   	if(jQuery('#channelType').val()==WEB_STU_SOURCE_DEFAULT)
			   	{
			   		$("#channelId").attr("disabled",true);	
			   	}
			   	else
			   	{
			   		$("#channelId").attr("disabled",false);	
			   	}
			}
			
			//ajax回调函数      添加政策（报名后政策）
			var addbatchId=0;//批次Id
			var addbranchIds="";//学习中心Ids集合
			var addlevelId=0;//层次Id
			var addmajorIds="";//专业Ids集合
			var addpolicyFeeId=0;//政策Id
			function ajax_addafterdiscount(data)
			{
			    var list='';
			    list+='<tr>';
				list+='<th><input type="checkbox" onclick="jQuery(\'[name=coverpolicy]\').attr(\'checked\', this.checked)"/></th>';
				list+='<th>合作方类别</th>';
				list+='<th>合作方</th>';
				list+='<th>院校</th>';
				list+='<th>学习中心</th>';
				list+='<th>批次</th>';
				list+='<th>层次</th>';
				list+='<th>专业</th>';
				list+='<th>状态</th>';
				list+='</tr>';
				if(data.discountlist!=null&&data.discountlist.length>0)
				{
				    $.each(data.discountlist,function(){	
					    list+='<tr>';
					    if(this.aduitStatus==AUDIT_STATUS_TRUE)
					    {
					    	list+='<td align="center"><input type="checkbox" name="coverpolicy1" disabled="disabled" id="coverpolicy" value="'+this.deleteFlag+'"/></td>';
					    }
					    else
					    {
					    	list+='<td align="center"><input type="checkbox" name="coverpolicy" id="coverpolicy" value="'+this.deleteFlag+'"/></td>';
					    }		
					    list+='<td align="center">'+this.channeltypename+'</td>';
						list+='<td align="center">'+this.channelname+'</th>';		    	 	
						list+='<td align="center">'+this.academyname+'</td>';
						list+='<td align="center">'+this.branchname+'</th>';
						list+='<td align="center">'+this.batchname+'</td>';
						list+='<td align="center">'+this.levelname+'</td>';
						list+='<td align="center">'+this.majorname+'</td>';
						if(this.aduitStatus==AUDIT_STATUS_FALSE)
						{
						    list+='<td align="center">未审批</td>';
						}
						else if(this.aduitStatus==AUDIT_STATUS_FAIL)
						{
						    list+='<td align="center">审批不通过</td>';
						}
						else
						{
						    list+='<td align="center">审批通过</td>';
						}
						list+='</tr>';
					});	
					jQuery("#showcover > tbody").html(list);
					    	 	
					$('#show_for_academy').dialog('open');	
					    	 	   	 		
				}	
				else if(data.backtracks)
				{
				    jQuery("#showDialog").html('<b>添加政策成功！</b>');
					$('#null_for_academy').dialog("open");
				}
				else
				{
				    jQuery("#showDialog").html('<b>添加失败！</b>(可能是层次下没有设置专业)');
					$('#null_for_academy').dialog("open");
				}
			}
			//ajax回调函数      覆盖政策
			var adddeleteflags="";//覆盖的id集合
			var addoperate=0;//操作Id
			function ajax_updatediscount(data)
			{
				 //覆盖政策
			    if(addoperate==1)
			    {
					if(data.backtracks)
					{	
					    $('#show_for_academy').dialog("close");		    	 		
						jQuery("#showDialog").html('<b>覆盖成功！</b>');
						$('#null_for_academy').dialog("open"); 	 										
					}						    	 	
					else
					{
					    jQuery("#showDialog").html('<b>覆盖失败！</b>');
						$('#null_for_academy').dialog("open");
					}
			    }
			    else
			    {
			    	if(data.backtracks)
					{	
					    $('#show_for_academy').dialog("close");		    	 		
						jQuery("#showDialog").html('<b>追加成功(重复的政策不叠加)！</b>');
						$('#null_for_academy').dialog("open"); 	 										
					}		
					else
					{
					    jQuery("#showDialog").html('<b>追加失败！</b>');
						$('#null_for_academy').dialog("open");
					}
			    }
			}
			
			//ajax回调函数      添加政策（报名前政策）
			var beforebranchIds="";//学习中心Ids集合
			var beforepolicyFeeId=0;//政策Id
			function ajax_addbeforediscount(data)
			{
				 //添加报名前政策
			    
			    var list='';
			    list+='<tr>';
				list+='<th><input type="checkbox" onclick="jQuery(\'[name=coverpolicy]\').attr(\'checked\', this.checked)"/></th>';
				list+='<th>学习中心</th>';
				list+='<th>费用科目</th>';
				list+='<th>状态</th>';
				list+='</tr>';
				if(data.discountlist!=null&&data.discountlist.length>0)
				{
				    $.each(data.discountlist,function(){	
					    list+='<tr>';
					    if(this.aduitStatus==AUDIT_STATUS_TRUE)
					    {
					    	list+='<td align="center"><input type="checkbox" name="coverpolicy1" disabled="disabled" id="coverpolicy" value="'+this.deleteFlag+'"/></td>';
					    }
					    else
					    {
					    	list+='<td align="center"><input type="checkbox" name="coverpolicy" id="coverpolicy" value="'+this.deleteFlag+'"/></td>';
					    }		
					    list+='<td align="center">'+this.branchname+'</th>';
						list+='<td align="center">'+this.feesubjectname+'</td>';
						if(this.aduitStatus==AUDIT_STATUS_FALSE)
						{
						    list+='<td align="center">未审批</td>';
						}
						else if(this.aduitStatus==AUDIT_STATUS_FAIL)
						{
						    list+='<td align="center">审批不通过</td>';
						}
						else
						{
						    list+='<td align="center">审批通过</td>';
						}
						list+='</tr>';
					 });	
					 jQuery("#showcover > tbody").html(list);
					    	 	
					 $('#show_for_academy').dialog('open');	
					    	 	   	 		
				 }	
				 else if(data.backtracks)
				 {
				     jQuery("#showDialog").html('<b>添加政策成功！</b>');
					 $('#null_for_academy').dialog("open");
				 }
				 else
				 {
				     jQuery("#showDialog").html('<b>添加失败！</b>');
					 $('#null_for_academy').dialog("open");
				 }
			    
			}
			
			//招生途径和市场途径回调函数
			function wayAndSourceCallback(doc){
				$("#channelType").html("");
	            $("<option value='0'>--请选择--</option>").appendTo($("#channelType"));
								
				$(doc.enrollmentSources).each(function(){
					$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#channelType"));
				});
								
				$("#enrollmentWay").html("");
	            var wayStr="<option value='-1'>--请选择--</option>";
				$.each(doc.enrollmentWaysMap,function(key,value){
					if(key!="大客户"&&key!="渠道"&&key!="老带新"&&key!="加盟"&&key!="共建")
					{
							wayStr+="<optgroup label='"+key+"'>";
							$(this).each(function(){
								wayStr+="<option value='" + this.id + "'>" + this.name + "</option>";
							});
							wayStr+="</optgroup>";
					}
				});
				$("#enrollmentWay").html(wayStr);								
				$("#channelId").html("");
	            $("<option value='-1'>--请选择--</option>").appendTo($("#channelId"));
	               				
	            $("#channelId").attr("disabled",true);
	            $("#enrollmentWay").attr("disabled",true);
	               				
				$("#channelType").change(function(){
					if($('#channelType').val()==0)
					{
						$("#channelId").html("");
	               		$("<option value='-1'>--请选择--</option>").appendTo($("#channelId"));
											
						$('#enrollmentWay option:selected').val(-1);
						$('#enrollmentWay option:selected').text('--请选择--')
											
						$("#channelId").attr("disabled",true);
	               		$("#enrollmentWay").attr("disabled",true);
	               							
						return;
					}
					if($('#channelType').val()==WEB_STU_SOURCE_DEFAULT)
					{
											
						//社招
						$("#channelId").val(-1);
						$("#channelId").attr("disabled",true);
						$("#enrollmentWay").attr("disabled",false);    
						$('#enrollmentWay option:selected').val(-1);   
						$('#enrollmentWay option:selected').text('--请选择--'); 
																			
					}
					else
					{
						
						$('#enrollmentWay option:selected').text($('#channelType option:selected').text());
						$('#enrollmentWay option:selected').val(1);
											
						$('#enrollmentWayName').val($('#channelType option:selected').text());
											
						$("#enrollmentWay").attr("disabled",true);
											
					}
					ajax_119_1();
				});
								
			}
		</script>
		<!--ajax插件-->
		<a:ajax successCallbackFunctions="ajax_channel" parameters="{channelType:jQuery('#channelType').val()}" urls="/enrollment/list_channeltype_channel_ajax" pluginCode="119"/>
		
		<!--ajax 添加政策（报名前政策）-->
		<a:ajax 
			pluginCode="100"
			parameters="{branchIds:beforebranchIds,feesubjectId:jQuery('#feesubjectId').val(),policyFeeId:beforepolicyFeeId}" 
			successCallbackFunctions="ajax_addbeforediscount"
			urls="/enrollment/add_before_student_discount_detail_ajax"
		/>
		<!--ajax 覆盖政策-->
		<a:ajax 
			pluginCode="110"
			successCallbackFunctions="ajax_updatediscount" 
			parameters="{deleteflags:adddeleteflags,operate:addoperate}" 
			urls="/enrollment/cover_student_discount_detail_ajax" 
		/>
		<!--ajax 添加政策（报名后政策）-->
		<a:ajax 
			pluginCode="130"
			parameters="{enrollmentWayName:jQuery('#enrollmentWayName').val(),enrollmentWay:jQuery('#enrollmentWay').val(),studentDataSource:jQuery('#studentDataSource').val(),channelType:jQuery('#channelType').val(),channelId:jQuery('#channelId').val(),academyId:jQuery('#academyId').val(),batchId:addbatchId,branchIds:addbranchIds,levelId:addlevelId,majorIds:addmajorIds,feesubjectId:jQuery('#feesubjectId').val(),policyFeeId:addpolicyFeeId}" 
			successCallbackFunctions="ajax_addafterdiscount"
			urls="/enrollment/add_student_discount_detail_ajax"
		/>
		<!--ajax 招生途径-->
		<a:ajax successCallbackFunctions="wayAndSourceCallback"
			pluginCode="003" urls="/enrollment/student_discount_enrollment_way_ajax" isOnload="all" />
	</head>
  
  <body>
	<!--头部层开始 -->
		<head:head title="添加学生优惠政策">
			<html:a text="返回" icon="return" href="/enrollment/list_student_discount_detail"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				 <input type="hidden" name="hidacademyId" id="hidacademyId" value="0"/>	
				 <table class="gv_table_2">
				  	<tr>
						<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						<th style="text-align:left; font-weight:bold;"><input type="radio"  id="rayou" name="rayou" value="<%=Constants.POLICY_REGISTRATION_BEFORE %>"/>报名前政策
						<input type="radio"  id="rayou" name="rayou" checked="checked" value="<%=Constants.POLICY_REGISTRATION_AFTER %>"/>报名后政策</th>
					</tr>
				 </table>
				 <div class="divclass">
				 <table class="gv_table_2">
				  	<tr>
						 <th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 <th style="text-align:left; font-weight:bold;">来源设置</th>							
					</tr>
				  </table>
				  
				  <table class="add_table" id="brach" class="brach" border="0" cellpadding="2" cellspacing="2">
				  	<tr>
		                <td class="lable_100"><span>*</span>招生途径：</td>
		                <td align="left">
		                	<select name="channelType" id="channelType" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
						<td class="lable_100">合作方：</td>
		                <td align="left">
		                	<select name="channelId" id="channelId" class="txt_box_150">
								<option value="-1">--请选择--</option>
							</select>
							<input type="hidden" name="hidbranchId" id="hidbranchId" value="0"/>
						</td>
					</tr>
					<tr>
		                <td class="lable_100">市场途径：</td>
		                <td align="left">
		                	<select name="enrollmentWay" id="enrollmentWay" class="txt_box_150">
								<option value="-1">--请选择--</option>
							</select>	
							<input type="hidden" name="enrollmentWayName" id="enrollmentWayName" value="" />
						</td>
						<td class="lable_100">数据来源：</td>
		                <td align="left">
							<s:if test="%{studslist!=null}">
								<s:select list="%{studslist}" listKey="id" headerKey="-1" headerValue="--请选择--" theme="simple" listValue="name" name="studentDataSource" id="studentDataSource" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="studentDataSource" id="studentDataSource" class="txt_box_150">
									<option value="-1">--请选择--</option>
								</select>
		                	</s:else>
						</td>
					</tr>	
				  </table>
				 <table class="gv_table_2">
				  	<tr>
						 <th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 <th style="text-align:left; font-weight:bold;">院校</th>							
					</tr>
				  </table>
				  <table class="add_table" id="brach" class="brach" border="0" cellpadding="2" cellspacing="2">
				  	<tr>
		                <td class="lable_100"><span>*</span>院校：</td>
		                <td align="left">
		                	<s:if test="%{academylist!=null}">
								<s:select list="%{academylist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="academyId" id="academyId" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="academyId" id="academyId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>	
						</td>
					</tr>	
				  </table>
				 <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">招生批次</th>
							
					</tr>
				  </table>
				  <table class="add_table" id="batchId" class="batchId" border="0" cellpadding="2" cellspacing="2" style="display:none">
				  	<tbody>
				  		
				  	</tbody>
				  </table>
				  </div>
					<table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">学习中心</th>
					  </tr>
					</table>
				
				  <table class="add_table" id="branchId"  border="0" cellpadding="2" cellspacing="2" style="display:none">
				  	<tbody>
					  
				  	</tbody>	
				  </table>
				  <div class="divclass">	
				   <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">层次</th>
					 </tr>
				  </table>
				  <table class="add_table" id="levelId" border="0" cellpadding="2" cellspacing="2" style="display:none">
					<tbody>					  	
					  	
					</tbody>
				  </table>
				  
				   <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">专业</th>
					 </tr>
				  </table>
				  <table class="add_table" id="majorId" border="0" cellpadding="2" cellspacing="2" style="display:none">
				  	<tbody>
					</tbody>
				  </table>
				  </div>
				   <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;" class="feehtml">政策标准
					 	   </th>
				    </tr>
				  </table>
				  <table class="add_table" border="0" cellpadding="2" cellspacing="2" id="fee_subjects">
				  	 <tr>
					  	<td align="right">费用科目：</td>
		                <td align="left">
							<s:if test="%{feesubjectlist!=null}">
		                		<s:select list="%{feesubjectlist}" listKey="id" theme="simple" listValue="name" name="feesubjectId" id="feesubjectId" cssClass="txt_box_150"/>
		                	</s:if>
		                	<s:else>
		                		<select name="feesubjectId" id="feesubjectId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>	
						</td>
						<td align="right">缴费次数：</td>
		                <td align="left">
							<select id="feePaymentId" class="txt_box_150">
								<option value="0">--请选择--</option>
								<option value="1">第1次缴费</option>
								<option value="2">第2次缴费</option>
								<option value="3">第3次缴费</option>
								<option value="4">第4次缴费</option>
								<option value="5">第5次缴费</option>
							</select>
						</td>
		                <td align="left">							
						</td>

					  	 <td colspan="" align="left">
							<input type="button" class="btn_black_61" onclick="showpolicyfee();" value="查询"/>
						</td>
		              </tr>
				  </table>
				  
				  <page:plugin 
						pluginCode="007"
						il8nName="enrollment"
						searchListActionpath="list_student_discount_policy_ajax"
						searchCountActionpath="count_student_discount_policy_ajax"
						columnsStr="#operating;academyname;title;feesubjectname;#feepayment;#discountway;#targetobject;discountstandard"
						customColumnValue="0,operat(id);4,showpayment(feePaymentId);5,showdiscout(discountWayId);6,showtarget(targetObjectId);7,showstandard(discountstandard)"
						pageSize="5"
						isPage="false"
						isNumber="false"
						isChecked="false"								
						params="'studentDiscountPolicy.academyId':$('#hidacademyId').val(),'studentDiscountPolicy.feeSubjectId':$('#feesubjectId').val(),'studentDiscountPolicy.feePaymentId':$('#feePaymentId').val()"
						isonLoad="false"
					/>
				 
				  <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="button" onclick="adddetail()" value="保存" /></td></tr></table>

			</body:body>
	
	<!--div id="null_for_academy"  style="display:none;font-weight:bold" align="center"></div-->
	<div id="null_for_academy" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
	<div id="show_for_academy" style="display:none">
		<table class="gv_table_2" id="showcover">
			<tbody></tbody>
			<tfoot></tfoot>
		</table>
	</div>
 	<div id="message_confirm" style="display:none">
		<div align="center">确认执行该操作！</div>
	</div>
  </body>
</html>
