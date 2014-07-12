<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>院校打款(总部)</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<script type="text/javascript">
			//加载事件
			jQuery(function(){
				
				//选择收款单位事件
				jQuery('#remitteeId').change(function(){
					if(this.value==0)
					{
						jQuery("#showDialog").html('<b>请选择收款单位！</b>');
						jQuery('#message_returns_tips').dialog('open');
					}
					else
					{
						jQuery("#allamount").html(0);
				    	jQuery("#amount").val(0);   
				    	jQuery("#countdiv").hide();
				    	jQuery("#feepaymentIds").val(""); //选中的打款单明细
				    	jQuery("#checkeddiv").hide();
				    	jQuery("#acaspan").html(jQuery("#remitteeId").find("option:selected").text());//
				    	ajax_120_1();//批次级联
				    }
				    jQuery("#picicb").attr("checked",false);
				});
				//招生批次相关级联(学习中心、层次、专业(防止刷新层次，专业不刷新))
				jQuery('#batchId').change(function(){
					if(this.value==0)
					{
						$('#levelId').empty();
						$('#levelId').append('<option value="0">--请选择--</option>');
						$('#majorId').empty();
					   	$('#majorId').append('<option value="0">--请选择--</option>');
					}
					else
					{
						ajax_130_1();//层次级联
					}
				});	
				//层次专业级联
				jQuery('#levelId').change(function(){
					if(this.value==0)
					{
						$('#majorId').empty();
					   	$('#majorId').append('<option value="0">--请选择--</option>');
					}
					else
					{
						ajax_140_1();//专业级联
					}
				});	
				
				//点击查询条件单选框事件
				jQuery('[name=search_type]').click(function(){
					if(jQuery("#remitteeId").val()==0)
					{
						jQuery("#showDialog").html('<b>请选择收款单位！</b>');
						jQuery('#message_returns_tips').dialog('open');
					}
					else if(this.value=="quick")
					{
						jQuery("#search_type_quick").show();
						jQuery("#search_type_normal").hide();
					}
					else
					{
						jQuery("#search_type_quick").hide();
						jQuery("#search_type_normal").show();
					}
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
			function showchecked()
			{
				if(jQuery("#remitterId").val()==0)
				{
					jQuery("#showDialog").html('<b>汇款单位不能为空！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else if(jQuery("#remitteeId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择收款单位！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else if(jQuery("#remitterAccount").val()=="" || jQuery.trim(jQuery("#remitterAccount").val())=="")
				{
					jQuery("#showDialog").html('<b>汇款账号不能为空！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else if(jQuery("#remitteeAccount").val()=="" || jQuery.trim(jQuery("#remitteeAccount").val())=="")
				{
					jQuery("#showDialog").html('<b>收款账号不能为空！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else if(jQuery("#feepaymentIds").val()=="")
				{
					jQuery("#showDialog").html('<b>请确认选择要打款的缴费单明细！</b>');
					jQuery('#message_returns_tips').dialog('open');	
				}
				else
				{
					//去除空格提交
					jQuery("#remitterAccount").val(jQuery.trim(jQuery("#remitterAccount").val()));
					jQuery("#remitteeAccount").val(jQuery.trim(jQuery("#remitteeAccount").val()));
					
					ajax_110_1();//生成打款单
				}
			}
			
			//核对
			function checkedresult()
			{
				var isfail=false;
				batchids="";
				batchid=0;//批次Id
		   	 	levelid=0;//层次Id
		    	majorid=0;//专业Id
		    	branchid=0;//中心Id
		    	feesubjectid=0;//费用科目Id
		    	starttime="";//开始时间
		   		endtime="";//结束时间
				jQuery("#jiaofeicb").attr("checked",false);//不选中
				//隐藏其他的
				jQuery("#allamount").html(0);
				jQuery("#amount").val(0);   
				jQuery("#countdiv").hide();
				jQuery("#feepaymentIds").val(""); //选中的打款单明细 
				
				if(jQuery("input[name='search_type']:checked").val()=="quick")
				{
					if(jQuery("input[name='batchitem']").length==0 || jQuery("input[name='batchitem']:checked").length==0)
					{
						jQuery("#showDialog").html('<b>请选择批次！</b>');
						$('#message_returns_tips').dialog("open");
					}
					else
					{
						jQuery("input[name='batchitem']").each(function(){
			            	if(jQuery(this).attr("checked")==true)
			            	{
			            		batchids+=jQuery(this).val()+",";
			                }
						});
						isfail=true;
					}
					if(batchids!="" && batchids.length>0)
					{
						batchids=batchids.substring(0,(batchids.length-1));
					}
				}
				else
				{				
		    		batchid=jQuery("#batchId").val();//批次Id
		   	 		levelid=jQuery("#levelId").val();//层次Id
		    		majorid=jQuery("#majorId").val();//专业Id
		    		branchid=jQuery("#branchId").val();//中心Id
		    		feesubjectid=jQuery("#feeSubjectId").val();//费用科目Id
		    		starttime=jQuery("#starttime").val();//开始时间
		   			endtime=jQuery("#endtime").val();//结束时间
		    		isfail=true;
				}
				if(isfail)
				{
					ajax_150_1();//显示缴费单明细
					jQuery("#checkeddiv").show();
				}
			}
			//取消核对
			function notcheckedresult()
			{
				jQuery("#jiaofeicb").attr("checked",false);//不选中
				//隐藏其他的
				jQuery("#allamount").html(0);
				jQuery("#amount").val(0);   
				jQuery("#countdiv").hide();
				jQuery("#feepaymentIds").val(""); //选中的打款单明细 
				
				jQuery("#checkeddiv").hide();
			}
			
			//选择显示金额和统计效果
			function showplaymoney()
			{
				jQuery("#allamount").html(0);
				jQuery("#amount").val(0);   
				jQuery("#countdiv").hide();
				jQuery("#feepaymentIds").val(""); //选中的打款单明细 
				if(jQuery("#remitteeId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择收款单位！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else if(jQuery("input[name='quanxfee']").length==0 || jQuery("input[name='quanxfee']:checked").length==0)
				{
					jQuery("#showDialog").html('<b>请选择缴费单明细！</b>');
					$('#message_returns_tips').dialog("open");
				}					
				else
				{
					feePaymentDetailIds="";
					jQuery("input[name='quanxfee']").each(function(){
			        	if(jQuery(this).attr("checked")==true)
			            {
			            	feePaymentDetailIds+=jQuery(this).val()+",";
			            }
					});
					if(feePaymentDetailIds!="" && feePaymentDetailIds.length>0)
					{
						feePaymentDetailIds=feePaymentDetailIds.substring(0,(feePaymentDetailIds.length-1));
					}
					jQuery("#feepaymentIds").val(feePaymentDetailIds);
					ajax_100_1();//统计打款
					jQuery("#countdiv").show();
				}
			}
			
			//ajax 回调函数    统计打款
			var feePaymentDetailIds="";
			function ajax_showcountplaymoney(data)
			{
				//统计
				$('#showcounttab > tbody').empty();
			   	var list='';
			   	var index=1;
			    var allmoney=0.0;
				if(data.feepdList!=null && data.feepdList.length>0)
				{
			    	$.each(data.feepdList,function(){	
			    		list+='<tr>';
				    		list+='<td align="center">'+index+'</td>';
				    		list+='<td align="center">'+this.schoolName+'</td>';
				    		list+='<td align="center">'+this.branchName+'</td>';
				    		list+='<td align="center">'+this.academyenrollbatchName+'</td>';
				    		list+='<td align="center">'+this.paymentSubjectName+'</td>';
				    		list+='<td align="center">'+this.payCeduAcademy+'</td>';
			    		list+='</tr>';
			    		allmoney+=this.payCeduAcademy;
			    		index++;
			    	});	
			    	//缴费金额赋值	
			    	jQuery("#allamount").html(allmoney);
			    	jQuery("#amount").val(allmoney);   
			    	jQuery("#countdiv").show(); 
			    }
			    else
			    {
			    	list+='<tr><td colspan="4" align="center">没有相关数据！</td></tr>';
			    }
			    $('#showcounttab > tbody').html(list);
			}
			
			//ajax 回调函数    生成打款单
			function ajax_addplaymoneyacdemy(data)
			{
				if(data.replayadd)
				{
					jQuery("#showDialog").html('<b>已添加过，不要重复添加！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else if(data.isback)
				{
					jQuery("#indexcount").val(1);
					jQuery("#showclose").html('<b>添加打款单成功！点击确定关闭当前页。</b>');
					$('#null_for_close').dialog("open");
				}
				else
				{
					jQuery("#showDialog").html('<b>添加失败！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
			}
			
			//ajax 回调函数    院校批次级联
			function ajax_academy_to_batch(data)
		    {
			    $('#quick_tab > tbody').empty();
		    	var list="";
				var index=1;
				//批次下拉框	
				$('#batchId').empty();
			    $('#batchId').append('<option value="0">--请选择--</option>');
			    $('#levelId').empty();
				$('#levelId').append('<option value="0">--请选择--</option>');
				$('#majorId').empty();
				$('#majorId').append('<option value="0">--请选择--</option>');
			    if(data.batchlist!=null&&data.batchlist.length>0)
			    {
				    list+='<tr><td align="center">'+jQuery("#remitteeId").find("option:selected").text()+'</td><td align="center">';
				    $.each(data.batchlist,function(){	
					    list+='<input type="checkbox" name="batchitem" class="batchitem" value="'+this.id+'"/>'+this.enrollmentName+"&nbsp;&nbsp;&nbsp;&nbsp;";
				    	if(index % 6==0)
				    	{
				    	 	list+='<br/>';
				    	}
				    	index++;
				    	
				    	//批次下拉框	
				    	$('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				    });
				    list+='</td></tr>';			    
			    }
			   	else
			    {
			    	 list+='<tr><td align="center"><span>没有设置院校招生批次!</span></td></tr>';
			    }	
			    $('#quick_tab > tbody').html(list);	
			    	 	
		    }
		    
		     //ajax 回调函数     院校层次级联
		    function ajax_batch_to_level(data)
		    {
		    	$('#levelId').empty();
				$('#levelId').append('<option value="0">--请选择--</option>');
			    if(data.levellist!=null&&data.levellist.length>0)
			    {	
				    $.each(data.levellist,function(){	
				    	$('#levelId').append('<option value="'+this.id+'">'+this.level.name+'</option>'); 
				    });
				 }	
				 //读取专业
				$('#majorId').empty();
			   	$('#majorId').append('<option value="0">--请选择--</option>');
			 
		    }
		    
		    //ajax 回调函数     院校专业级联
		    function ajax_level_to_major(data)
		    {
		    	$('#majorId').empty();
			   	$('#majorId').append('<option value="0">--请选择--</option>');
			    if(data.majorlist!=null&&data.majorlist.length>0)
			    {
			    	 $.each(data.majorlist,function(){	
			    	 	$('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	 });
			    }	
		    }
		    
		    //ajax 回调函数     查询缴费单明细集合
		    var batchids="";//批次Id集合
		    var batchid=0;//批次Id
		    var levelid=0;//层次Id
		    var majorid=0;//专业Id
		    var branchid=0;//中心Id
		    var feesubjectid=0;//费用科目Id
		    var starttime="";//开始时间
		    var endtime="";//结束时间
		    function ajax_feepayment_list(data)
		    {
		    	$('#showffdetailtab > tbody').empty();
		    	var list="";
			    if(data.feePaymentDetailList!=null&&data.feePaymentDetailList.length>0)
			    {
			    	 $.each(data.feePaymentDetailList,function(){
			    	 	list+='<tr>'
			    	 		list+='<td align="center"><input name="quanxfee" id="quanxfee" class="quanxfee" type="checkbox" value="'+this.id+'" /></td>';
			    	 		//list+='<td align="center"></td>';
			    	 		list+='<td align="center">'+this.createdTime.substring(0,10)+'</td>';
			    	 		list+='<td align="center">'+this.paymentCode+'</td>';
			    	 		list+='<td align="center">'+this.branchName+'</td>';
			    	 		list+='<td align="center">'+this.studentName+'</td>';
			    	 		list+='<td align="center">'+this.academyenrollbatchName+'</td>';
			    	 		list+='<td align="center">'+this.levelName+'</td>';
			    	 		list+='<td align="center">'+this.majorName+'</td>';
			    	 		list+='<td align="center">'+this.paymentSubjectName+'</td>';
			    	 		list+='<td align="center">'+this.jiaofeiValue+'</td>';
			    	 		//list+='<td></td><td></td><td></td><td></td><td></td><td></td><td></td>';
			    	 	list+='</tr>'				    	 	
			    	 });
			    }
			    else
			    {
			    	 list+='<tr><td></td><td></td><td align="center" colspan="8"><span>没有相关缴费单数据!</span></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>';
			    }	
			    $('#showffdetailtab > tbody').html(list);	
		    }
	</script>
		<!--统计打款-->
		<a:ajax 
			pluginCode="100"
			successCallbackFunctions="ajax_showcountplaymoney" 
			parameters="{feePaymentDetailIds:feePaymentDetailIds}" 
			urls="/finance/playmoney/feepaymentdetail_count_all_cedupalymoneyacademy_ajax" 
		/>
		<!--生成打款单-->
		<a:ajax 
			pluginCode="110"
			successCallbackFunctions="ajax_addplaymoneyacdemy" 
			parameters="jQuery('#myform').serializeObject()" 
			urls="/finance/playmoney/add_cedu_playmoney_academy_ajax" 			
		/>
		
		<!--院校批次级联-->
		<a:ajax 
			pluginCode="120"
			successCallbackFunctions="ajax_academy_to_batch" 
			parameters="{academyId:jQuery('#remitteeId').val()}" 
			urls="/enrollment/cascade_academy_batch_ajax" 			
		/>
		<!--院校层次级联-->
		<a:ajax 
			pluginCode="130"
			successCallbackFunctions="ajax_batch_to_level" 
			parameters="{batchId:jQuery('#batchId').val()}" 
			urls="/enrollment/cascade_batch_level_ajax" 			
		/>
		<!--院校专业级联-->
		<a:ajax 
			pluginCode="140"
			successCallbackFunctions="ajax_level_to_major" 
			parameters="{levelId:jQuery('#levelId').val()}" 
			urls="/enrollment/cascade_level_major_ajax" 			
		/>
		<!--查询缴费单明细集合-->
		<a:ajax 
			pluginCode="150"
			successCallbackFunctions="ajax_feepayment_list" 
			parameters="{'student.academyId':jQuery('#remitteeId').val(),'student.enrollmentBatchId':batchid,'student.levelId':levelid,'student.majorId':majorid,batchIds:batchids,'student.branchId':branchid,feeSubjectId:feesubjectid,starttime:starttime,endtime:endtime}" 
			urls="/finance/playmoney/list_feepaymentdetail_headpalymoneyacademy_ajax" 			
		/>
	</head>
  
  <body>
  		
		<!-- 头开始 -->
		<head:head title="院校打款(总部)">
			<html:a text="关闭" icon="add" onclick="window.close()" target="_self"/>	
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="myform">
				<input type="hidden" name="feepaymentIds" id="feepaymentIds" value=""/>
				<input type="hidden" name="indexcount" id="indexcount" value="0"/>
				
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">添加打款单</th>
					</tr>
				</table>
				<table class="add_table">
					<tr>
						<td class="lable_100"><span>*</span>汇款单位：</td>
						<td>
							${branch.name}
							<input type="hidden" name="payCeduAcademy.remitterId" id="remitterId" value="${branch.id}" />
						</td>
					</tr>
					<tr>
						<td class="lable_100"><span>*</span>收款单位：</td>
						<td>
							<s:if test="%{academylist!=null}">
								<s:select list="%{academylist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="payCeduAcademy.remitteeId" id="remitteeId" cssClass="txt_box_150"/>
							</s:if>
			                <s:else>
			                	<select name="payCeduAcademy.remitteeId" id="remitteeId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
			                </s:else>	
						</td>
					</tr>
					<tr>
						<td class="lable_100"><span>*</span>汇款账号：</td>
						<td>
							<input name="payCeduAcademy.remitterAccount" id="remitterAccount" class="txt_box_150"/>
						</td>
					</tr>
					<tr>
						<td class="lable_100"><span>*</span>收款账号：</td>
						<td>
							<input name="payCeduAcademy.remitteeAccount" id="remitteeAccount" class="txt_box_150"/>
						</td>
					</tr>
					<tr>
						<td class="lable_100"><span>*</span>显示缴费单：</td>
						<td>
							<table class="gv_table_2">
						  		<tr>
									<td>
										<input type="radio" name="search_type" value="quick"/><label>快速查询</label>
										<input type="radio" name="search_type" value="normal"/><label>普通查询</label>
									</td>
								</tr>
							</table>
							<div id="search_type_quick" style="display:none">	
								<table class="gv_table_2" id="quick_tab">
							  		<thead>
								  		<tr>
										 	<th align="center" width="40%">
										 		院校
											</th>
										 	<th align="center">
										 		批次<input type="checkbox" id="picicb" onclick="jQuery('[name=batchitem]').attr('checked', this.checked)"/>
										 	</th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>	
								</table>
								<table class="add_table">
									<tr>
										<td align="right">
											<input type="button" class="btn_black_61" style="text-align:center" onclick="checkedresult()" value="核对"/>
											<input type="button" class="btn_black_61" style="text-align:center" onclick="notcheckedresult()" value="取消"/>
										</td>
									</tr>
								</table>
							</div>
							<div id="search_type_normal" style="display:none">
								<table class="add_table" >
										<tr>
											<td align="right">院校:</td>
											<td align="left"><span style="color:black !important" id="acaspan"></span></td>
											
											<td align="right">学习中心:</td>
											<td align="left">
												<s:if test="%{branchlist!=null}">
													<s:select list="%{branchlist}" headerKey="0" headerValue="--请选择--" listKey="id" theme="simple" listValue="name" name="branchId" id="branchId" cssClass="txt_box_150"/>
												</s:if>
												<s:else>
												<select name="branchId" id="branchId" class="txt_box_150">
													<option value="0">--请选择--</option>
												</select>
												</s:else>		
											</td>
										</tr>	
										<tr>
											<td align="right">批次:</td>
											<td align="left">
												<select name="batchId" id="batchId" class="txt_box_150">
													<option value="0">--请选择--</option>
												</select>			
											</td>
									
											<td align="right">层次:</td>
											<td align="left">
												<select name="levelId" id="levelId" class="txt_box_150">
													<option value="0">--请选择--</option>
												</select>
											</td>
										</tr>
										
										<tr>
											<td align="right">专业:</td>
											<td align="left">
												<select name="majorId" id="majorId" class="txt_box_150">
													<option value="0">--请选择--</option>
												</select>
											</td>
										
											<td align="right">费用科目:</td>
											<td align="left">
												<s:if test="%{feesubjectlist!=null}">
													<s:select list="%{feesubjectlist}" headerKey="0" headerValue="--请选择--" listKey="id" theme="simple" listValue="name" name="feeSubjectId" id="feeSubjectId" cssClass="txt_box_150"/>
												</s:if>
												<s:else>
												<select name="feeSubjectId" id="feeSubjectId" class="txt_box_150">
													<option value="0">--请选择--</option>
												</select>
												</s:else>		
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
										</tr>
								</table>
								<table class="add_table">
									<tr>
										<td align="right">
											<input type="button" class="btn_black_61" style="text-align:center" onclick="checkedresult()" value="核对"/>
											<input type="button" class="btn_black_61" style="text-align:center" onclick="notcheckedresult()" value="取消"/>
										</td>
									</tr>
								</table>
							</div>
							<div id="checkeddiv" style="display:none">
								<table class="gv_table_2" id="showffdetailtab">
									<thead>
										<!--tr>
											<td align="center" width="3%" ><input name="jiaofeicb" id="jiaofeicb" type="checkbox" onclick="jQuery('[name=quanxfee]').attr('checked', this.checked)" /></td>
											<td align="center" >比对结果</td>
											<td align="center" colspan="8">平台数据 </td>
											<td align="center" colspan="7">院校数据</td>
											
										</tr>
										<tr>
											<td align="center"></td>
											<td align="center"></td>
											<td align="center">缴费单号</td>
											<td align="center">学习中心</td>
											<td align="center">姓名</td>
											<td align="center">批次</td>
											<td align="center">层次</td>
											<td align="center">专业</td>
											<td align="center">费用项目</td>
											<td align="center">金额</td>
											<td align="center">单号</td>
											<td align="center">姓名</td>
											<td align="center">批次</td>
											<td align="center">层次</td>
											<td align="center">专业</td>
											<td align="center">费用项目</td>
											<td align="center">缴费金额</td>	
										</tr>  -->
										<tr>
											<td align="center" colspan="10">平台数据 </td>

										</tr>
										<tr>
											<td align="center" width="3%" ><input name="jiaofeicb" id="jiaofeicb" type="checkbox" onclick="jQuery('[name=quanxfee]').attr('checked', this.checked)" /></td>
											<td align="center">缴费日期</td>
											<td align="center">缴费单号</td>
											<td align="center">学习中心</td>
											<td align="center">姓名</td>
											<td align="center">批次</td>
											<td align="center">层次</td>
											<td align="center">专业</td>
											<td align="center">费用项目</td>
											<td align="center">实缴金额</td>	
										</tr>
									</thead>	
									<tbody>
										
									</tbody>
								</table>
								<table class="add_table">
									<tr>
										<td align="left">
											<input type="button" class="btn_black_130" style="text-align:center" onclick="showplaymoney()" value="确认选择"/>
											<span>备注：</span>打款单的生成是以点“确认选择”后的统计金额为准。
										</td>
									</tr>
								</table>
								<div id="countdiv" style="display:none">
									<table class="gv_table_2" id="showcounttab">
										<thead>
											<tr>
												<th align="center">序号</th>
												<th align="center">院校</th>
												<th align="center">学习中心</th>
												<th align="center">批次</th>
												<th align="center">费用项目</th>
												<th align="center">打款金额</th>
											</tr>
										</thead>	
										<tbody>
											
										</tbody>
									</table>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="lable_100"><span>*</span>汇款总额：</td>
						<td>
							<span id="allamount">0</span> 元
							<input type="hidden" name="payCeduAcademy.amount" id="amount" value="0"/>
						</td>
					</tr>
					<tr>
						<td class="lable_100">备注：</td>
						<td><textarea cols="60" rows="6" name="payCeduAcademy.note" id="note"></textarea></td>
					</tr>
					<tr>
						<td class="lable_100"></td>
						<td>
							<input class="btn_black_61" type="button" value="保存" onclick="showchecked()" id="submit"/>
							<input class="btn_black_61" type="button" value="关闭" onclick="window.colse();"/>
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
		<div id="null_for_close" style="display:none">
			<div align="center" id="showclose">
			
			</div>
		</div>
  </body>
</html>
