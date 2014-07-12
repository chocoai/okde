<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>修改收费政策</title>
		
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
		
		<script type="text/javascript">

			//加载事件
			jQuery(function(){
			
				////////////////////////赋值\\\\\\\\\\\\\\\\\\\\\
				//院校赋值
				jQuery("#academyId").val(${policyFeeDetail.academyId});
				//费用科目赋值
				jQuery("#feesubjectId").val(${policyFeeDetail.feeSubjectId});
				//批次赋值
				var nocheck=$("input[name='batchitem']")
				nocheck.each(function(i){
					if('${policyFeeDetail.batchId}'==$(this).val())
					{
						$(this).attr("checked",true);
					}			
				}); 
				
				//学习中心赋值			
				var nobranch=$("input[name='branchitem']")
				nobranch.each(function(i){
					if('${policyFeeDetail.branchId}'==$(this).val())
					{
						$(this).attr("checked",true);
					}				
				}); 
				//层次赋值
				if('${levelgx}'!=0)
				{	        								
					var nolevel=$("input[name='levelitem']")
					nolevel.each(function(i){
						if('${levelgx}'==$(this).val())
						{
							$(this).attr("checked",true);
						}				
					}); 
	
					//专业赋值					
					var nomajor=$("input[name='majoritem']")
					nomajor.each(function(i){
						if('${policyFeeDetail.majorId}'==$(this).val())
						{
							$(this).attr("checked",true);
						}			
					}); 	
				}	
				//收费标准赋值
				search007();
				//////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
								
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
						//jQuery("#policyFeeId").val(0);
						search007();
					}
					
				});
				
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
				
				 //点击层次单选框事件
				jQuery('[name=levelitem]').click(function(){
					var levelId = this.value;
					selectmajor(levelId);//层次与专业级联
					jQuery("#majorId").show();
				});
				
				//初始化弹出框
				$('#show_for_academy').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'重复的政策(可以选择覆盖)',
					width: 500,
					buttons: {
						'覆盖': function() { 
							$('#message_confirm').dialog({
								title:'确认操作',
								buttons: {
									'确认': function() { 
										//
										coverData();
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
			    	 		list+='<tr><td align="center"><span>没有授权的学习中心!</span></td></tr>';
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
			function adddetail()
			{
				if(jQuery("#academyId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择院校！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(jQuery("input[name='batchitem']").length==0 || jQuery("input[name='batchitem']:checked").length==0)
				{
					jQuery("#showDialog").html('<b>请选择招生批次！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(jQuery("input[name='branchitem']").length==0)
				{
					jQuery("#showDialog").html('<b>该批次下没有授权学习中心！</b>');
					$('#null_for_academy').dialog("open");
				}
				else if(jQuery("input[name='levelitem']").length==0)
				{
					jQuery("#showDialog").html('<b>该批次下没有设置层次！</b>');
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
					var batchId=jQuery("input[name='batchitem']:checked").val();//批次id
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
					if(jQuery("input[name='levelitem']:checked").length!=0)
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
					var policyFeeId=jQuery("input[name='pfeeradio']:checked").val();//收费标准id
					
					 //添加政策
			    	$.ajax({
			    		url:'<uu:url url="/enrollment/modify_polify_fee_detail_ajax" />',
			    		data:{academyId:jQuery('#academyId').val(),batchId:batchId,branchIds:branchIds,levelId:levelId,majorIds:majorIds,feesubjectId:jQuery('#feesubjectId').val(),policyFeeId:policyFeeId,policyFeeDetailId:jQuery('#policyFeeDetailId').val()},
			    		dataType:'json',
			    	 	type:'post',
			    	 	success:function(data){
			    	 		var list='';
			    	 		list+='<tr>';
				    	 	list+='<th></th>';
				    	 	list+='<th>院校</th>';
				    	 	list+='<th>学习中心</th>';
				    	 	list+='<th>批次</th>';
				    	 	list+='<th>层次</th>';
				    	 	list+='<th>专业</th>';
				    	 	list+='</tr>';
				    	 	if(data.policylist!=null&&data.policylist.length>0)
				    	 	{
				    	 		$.each(data.policylist,function(){	
					    	 		list+='<tr>';
						    	 	list+='<td align="center"><input type="checkbox" name="coverpolicy" id="coverpolicy" value="'+this.deleteFlag+'"/></td>';
						    	 	list+='<td align="center">'+this.academyname+'</td>';
						    	 	list+='<td align="center">'+this.branchname+'</th>';
						    	 	list+='<td align="center">'+this.batchname+'</td>';
						    	 	list+='<td align="center">'+this.levelname+'</td>';
						    	 	list+='<td align="center">'+this.majorname+'</td>';
						    	 	list+='</tr>';
					    	 	});	
					    	 	jQuery("#showcover > tbody").html(list);
					    	 	
					    	 	$('#show_for_academy').dialog('open');	
					    	 	   	 		
				    	 	}	
				    	 	else if(data.backtracks)
				    	 	{
				    	 		jQuery("#showDialog").html('<b>修改政策成功！</b>');
								$('#null_for_academy').dialog("open");
				    	 	}
				    	 	else
				    	 	{
				    	 		jQuery("#showDialog").html('<b>修改失败！</b>(可能是层次下没有设置专业)');
								$('#null_for_academy').dialog("open");
				    	 	}
			    	 	
				    	 }	
			    	});
		
				}
			}	
			//覆盖原有的数据
			function coverData()
			{
				$('#message_confirm').dialog("close");
				if(jQuery("input[name='coverpolicy']").length==0 || jQuery("input[name='coverpolicy']:checked").length==0)
				{
					
					jQuery("#showDialog").html('<b>请选择要覆盖的政策！</b>');
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
			    	$.ajax({
			    		url:'<uu:url url="/enrollment/cover_polify_fee_detail_ajax" />',
			    		data:{deleteflags:deleteflags},
			    		dataType:'json',
			    	 	type:'post',
			    	 	success:function(data){
			    	 		
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
			    	});
		
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
					search007();
				}
			}			
		    	
		    /////////      /授粉标准分页列表/    /////////////////			
			//列表自定义字段显示
			function feesubject(op)
			{
				return op;
			}	
			
			function showname(name)
			{
				return name;
			}
			function adda(title,id)
			{
				return '<a href="view_policy_fee?id='+id+'" target="_blank">'+title+'</a>';
			}
			function operat(id)
			{
				if(id==parseInt(jQuery("#policyFeeId").val()))
				{
					return '<input type="radio"  checked="checked" name="pfeeradio" value="'+id+'"/>';
				}
				else
				{
					return '<input type="radio" name="pfeeradio" value="'+id+'"/>';
				}
			}
		</script>
	</head>
  
  <body>

   <!-- 头开始 -->
		<head:head title="修改收费政策">
			<html:a text="返回" icon="return" onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<div class="tb_table_default_2">
				<input type="hidden" name="policyFeeId" id="policyFeeId" value="${policyFeeDetail.policyFeeId}"/>
				<input type="hidden" name="policyFeeDetailId" id="policyFeeDetailId" value="${policyFeeDetail.id}"/>
				 <table class="gv_table_2">
				  	<tr>
						 <th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 <th style="text-align:left; font-weight:bold;">院校</th>							
					</tr>
				  </table>
				  <table class="add_table" id="brach" class="brach" border="0" cellpadding="2" cellspacing="2">
				  	<tr>
		                <td class="lable_100">院校：</td>
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
				  </table>
				 <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">招生批次</th>
							
					</tr>
				  </table>
				  <table class="add_table" id="batchId" class="batchId" border="0" cellpadding="2" cellspacing="2" style="">
				  	<tbody>
				  		<tr>
				  			<s:iterator id="items" value="batchlist" status="bta"> 
								<td style="width:25%">
						  			<input type="radio" name="batchitem" class="batchitem" value="${items.id}"/>${items.enrollmentName}
								</td>
								<s:if test="#bta.count % 4==0">
									</tr>
									<tr>
								</s:if>						
							</s:iterator>
						</tr>
				  	</tbody>
				  </table>
					<table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">学习中心</th>
					  </tr>
					</table>
				  <table class="add_table" id="branchId"  border="0" cellpadding="2" cellspacing="2" style="">
				  	<tbody>
						<tr>
				  			<s:iterator id="items" value="branchlist" status="bta"> 
								<td style="width:25%">
						  			<input type="checkbox" name="branchitem" class="branchitem" value="${items.id}"/>${items.name}
								</td>
								<s:if test="#bta.count % 4==0">
									</tr>
									<tr>
								</s:if>						
							</s:iterator>
						</tr>
				  	</tbody>	
				  </table>
				 
				   <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">层次</th>
					 </tr>
				  </table>
				  <table class="add_table" id="levelId" border="0" cellpadding="2" cellspacing="2" style="">
					<tbody>					  	
						<tr>
				  			<s:iterator id="items" value="levellist" status="bta"> 
								<td style="width:25%">
						  			<input type="radio" name="levelitem" class="levelitem" value="${items.id}"/>${items.level.name}
								</td>
								
								<s:if test="#bta.count % 4==0">
									</tr>
									<tr>
								</s:if>						
							</s:iterator>
						</tr> 	
					</tbody>
				  </table>
				  
				   <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">专业</th>
					 </tr>
				  </table>
				  <table class="add_table" id="majorId" border="0" cellpadding="2" cellspacing="2" style="">
				  	<tbody>
				  		<tr>
				  			<s:iterator id="items" value="majorlist" status="bta"> 
								<td style="width:25%">
						  			<input type="checkbox" name="majoritem" class="majoritem" value="${items.id}"/>${items.name}
								</td>
								<s:if test="#bta.count % 4==0">
									</tr>
									<tr>
								</s:if>						
							</s:iterator>
						</tr>
					</tbody>
				  </table>
				  
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
		                		<s:select list="%{feesubjectlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="feesubjectId" id="feesubjectId" cssClass="txt_box_150"/>
		                	</s:if>
		                	<s:else>
		                		<select name="feesubjectId" id="feesubjectId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>	
						</td>
						<td width="5%"></td>
		                <td align="left">b							
						</td>

					  	 <td colspan="" align="left">
							<input type="button" class="btn_black_61" onclick="showpolicyfee();" value="查询"/>
						</td>
		              </tr>
				  </table>
				  
				  <page:plugin 
						pluginCode="007"
						il8nName="enrollment"
						searchListActionpath="list_policy_fee_ajax"
						searchCountActionpath="count_policy_fee_ajax"
						columnsStr="#operating;title;#feesubject;feestandardes"
						customColumnValue="0,operat(id);1,adda(title,id);2,feesubject(feeSubjectname);3,showname(feestandardes)"
						pageSize="5"
						isPage="false"
						isNumber="false"
						isChecked="false"								
						params="'academyId':$('#academyId').val(),'feesubjectId':$('#feesubjectId').val()"
						isonLoad="false"
					/>
				 
				  <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="button" onclick="adddetail()" value="保存" /></td></tr></table>

			</div>
		</div>
	</div>
	
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
  </body:body>
</html>
