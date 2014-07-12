<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>学生优惠申请</title>
		
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
				
				//初始化弹出框
				//所有优惠券
				jQuery('#show_for_prompt').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'所有优惠卷',
					width: 650,
					buttons: {
						'关闭': function() { 
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
				//确认操作
				jQuery('#message_confirm').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'确认操作',
					buttons: {
						'确认': function() { 
							//coverData();
						}, 
						'取消': function() { 
							jQuery(this).dialog("close"); 
						} 
					}
				});
				
			});
			
			//分页列表
			function sexValue(sex)
			{
				return sex.getSex();
			}
			function operated(id)
			{
				//return '<a href="javascript:refreshdiscount('+id+')" title="刷新不需要申请的优惠卷"><img src="<ui:img url="/images/icon_discount.gif" />" /></a>';
				return '<a href="javascript:showdiscount('+id+')" title="查看优惠"><img src="<ui:img url="/images/icon_discount.gif" />" /></a>';
			}
			
			function comoperated(id)
			{
				return '<a href="javascript:addapplicationdiscount('+id+')" >优惠申请</a>'+' '+'<a href="javascript:showdiscount('+id+')">查看优惠</a>';
			}
			
			//显示可以申请的优惠ajax
			function addapplicationdiscount(id)
			{
				studentId=id;
				
				ajax_150_1();//显示可以添加的优惠申请	
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
					 ajax_160_1();//添加优惠政策
				}
			}
			
			//显示已经申请的优惠ajax
			function showdiscount(id)
			{
				$('#usageFlag').empty();
			    $('#usageFlag').append('<option value="'+POLICY_USING_STATUS_ALL+'">--全部--</option>');
			    $('#usageFlag').append('<option value="'+POLICY_USING_STATUS_APPLY+'">--已申请--</option>');
			    $('#usageFlag').append('<option value="'+POLICY_USING_STATUS_FALSE+'">--未使用--</option>');
			    $('#usageFlag').append('<option value="'+POLICY_USING_STATUS_TRUE+'">--已使用--</option>');
			   
				studentId=id;
				
				ajax_170_1();//显示已经申请的优惠
			}
			
			//查询按钮事件
			function showusageflag()
			{
				ajax_170_1();//显示已经申请的优惠
			}
			
			//删除已申请的优惠卷
			function deldiscountapplied(id)
			{
				discountApplicationId=id;
				ajax_180_1();//删除优惠
			}
			
			//刷新不需要申请的学生优惠
			function refreshdiscount(id)
			{
				studentId=id;
				ajax_190_1();//刷新优惠
			}
			
		</script>
			
		<script type="text/javascript">
			
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
			   	
			   	$('#batch').html('');
			   	$('#batchId').val(0);
			   	
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
			    	$('#batch').html('');
			   		$('#batchId').val(0);
			    }
			    
			    ajax_120_1();//层次	
			}
			
			//ajax回调函数  学生优惠申请
			var studentId=0;
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
			//ajax回调函数   显示已申请的学生优惠
			function ajax_showdiscount(data)
			{				
				$('#showdiscount > tbody').empty();
			   	var list='';
			    if(data.discountApplicationList!=null && data.discountApplicationList.length>0)
			    {
			    	$.each(data.discountApplicationList,function(){	
			    		list+='<tr>';
			    		list+='<td align="center">'+this.code+'</td>';
			    		list+='<td align="center">'+this.feeSubjectName+'</td>';
			    		list+='<td align="center">'+this.startTime.substring(0,10)+"~"+this.endTime.substring(0,10)+'</td>';
			    		if(this.discountWay==MONEY_FORM_RATIO)
			    		{
			    			list+='<td align="center">'+this.money+'%</td>';
			    		}
			    		else
			    		{
			    			list+='<td align="center">'+this.money+'元</td>';
			    		}
			    		list+='<td align="center">';
			    		if(this.usageFlag==POLICY_USING_STATUS_APPLY)
			    		{
			    			list+='已申请';
			    		}
			    		else if(this.usageFlag==POLICY_USING_STATUS_FALSE)
			    		{
			    			list+='未使用';
			    		}
			    		else
			    		{	
			    			list+='已使用';
			    		}
			    		list+='</td>';
			    		//list+='<td align="center">';
			    		//if(this.usageFlag==POLICY_USING_STATUS_APPLY)
			    		//{
			    			
			    		//	list+='<a href="javascript:deldiscountapplied('+this.id+')">删除</a>';
			    		//}
			    		//else
			    		//{
			    		//	list+='';
			    		//}
			    		//list+='</td>';
			    		list+='</tr>';
			    	});
			    }
			    else
			    {
			    	list+='<tr><td colspan="6" align="center">没有已申请的优惠政策！</td></tr>';
			    }
			    $('#showdiscount > tbody').html(list);	
			    $('#show_for_prompt').dialog('open');
			}
			//ajax回调函数  删除已申请的学生优惠
			var discountApplicationId=0;
			function ajax_deldiscount(data)
			{				
			    if(data.isfail)
			    {
			    	jQuery("#showDialog").html('<b>删除成功！</b>');
					jQuery('#message_returns_tips').dialog("open");
					ajax_170_1();//显示已经申请的优惠
			    }
			    else
			    {
			    	jQuery("#showDialog").html('<b>删除失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }			  
			}
			
			//ajax回调函数  刷新不需要申请的学生优惠
			function ajax_refreshdiscount(data)
			{				
			    if(data.isfail)
			    {
			    	jQuery("#showDialog").html('<b>操作成功！</b>');
					jQuery('#message_returns_tips').dialog("open");
			    }
			    else
			    {
			    	jQuery("#showDialog").html('<b>操作失败！</b>');
					jQuery('#message_returns_tips').dialog("open");
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
		
		<!--学生优惠申请-->
		<a:ajax successCallbackFunctions="ajax_adddiscount" parameters="{studentId:studentId}" urls="/enrollment/list_all_student_apply_ajax" pluginCode="150"/>
		
		<!--添加学生优惠申请-->
		<a:ajax successCallbackFunctions="ajax_addstudentapp" parameters="{studentId:studentId,remark:jQuery('#remark').val(),discountPolicyId:jQuery(\"input[name='policyradio']:checked\").val()}" urls="/enrollment/add_student_application_ajax" pluginCode="160"/>

		<!--显示已申请的学生优惠-->
		<a:ajax successCallbackFunctions="ajax_showdiscount" parameters="{studentId:studentId,usageFlag:jQuery('#usageFlag').val()}" urls="/enrollment/list_all_student_applied_ajax" pluginCode="170"/>
		
		<!--删除已申请的学生优惠-->
		<a:ajax successCallbackFunctions="ajax_deldiscount" parameters="{discountApplicationId:discountApplicationId}" urls="/enrollment/delete_student_application_apply_ajax" pluginCode="180"/>
		
		<!--刷新不需要申请的学生优惠-->
		<a:ajax successCallbackFunctions="ajax_refreshdiscount" parameters="{studentId:studentId}" urls="/enrollment/refresh_student_application_unapply_ajax" pluginCode="190"/>
		
	</head>
  
  <body>

	<!-- 头开始 -->
		<head:head title="学生优惠申请">
			
		</head:head>
		<!--主体层开始 -->
		<body:body>
				
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">学生优惠申请</th>
						
					</tr>
				</table>
				<form id="myform" name="myform">
				<table class="add_table">
					<tr>
						<td>学习中心：</td>
		                <td align="left">
							${branch.name}
							<input type="hidden" id="branchId" name="student.branchId" value="${branch.id}"/>
						</td>
		                
		                <td>全局批次：</td>
		                <td align="left">
							<select name="globalBatchId" id="globalBatchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td>院校：</td>
		                <td align="left">
		                	<select name="student.academyId" id="academyId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>	
						</td>
		                <td>招生批次：</td>
		                <td align="left">
							<span style="color: black !important;" id="batch" name="batch"></span>
							<input type="hidden" id="batchId" name="student.enrollmentBatchId" value="0"/>
						</td>
						
	              	</tr>
				  	<tr>
				  		<td>层次：</td>
		                <td>
							<select name="student.levelId" id="levelId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
		                <td>专业：</td>
		                <td align="left">
							<select name="student.majorId" id="majorId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
						<td>姓名：</td>
		                <td align="left">
		                	<input type="text" name="student.name" id="name" class="txt_box_150"/>					
						</td>
						<td></td>
		                <td align="left">
							<input type="button" class="btn_black_61"  onclick="search601();" value="查询"/>
						</td>
	             	</tr>
				</table>
				</form>
				<page:plugin 
						pluginCode="601"
						il8nName="crm"
						searchListActionpath="list_student_discount_application_ajax"
						searchCountActionpath="count_student_discount_application_ajax"
						columnsStr="public.operating;name;gender;schoolName;academyenrollbatchName;levelName;majorName;public.operating"
						customColumnValue="0,operated(id);2,sexValue(gender);7,comoperated(id)"
						params="'student.gender':-1,'student.callStatus':-1"						
						searchFormId="myform"
					/>
		</body:body>
	
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
	<div id="show_for_prompt" style="display:none">
		<table class="add_table" cellpadding="2" cellspacing="2">
			<tr>
				<td align="right">优惠券状态</td>
				<td align="left">

					<select id="usageFlag" name="usageFlag" class="txt_box_100">
						<option value="<%=Constants.POLICY_USING_STATUS_ALL %>">--全部--</option>
						<option value="<%=Constants.POLICY_USING_STATUS_APPLY %>">已申请</option>
						<option value="<%=Constants.POLICY_USING_STATUS_FALSE %>">未使用</option>
						<option value="<%=Constants.POLICY_USING_STATUS_TRUE %>">已使用</option>
					</select>
				</td>

				<td><input type="button" onclick="showusageflag()" value="查询"/></td>
			</tr>
		</table>
		<table class="gv_table_2" id="showdiscount">
			<thead>
				<th>优惠编号</th>
				<th>费用科目</th>
				<th>有效期</th>
				<th>优惠金额/比例</th>
				<th>状态</th>
				
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>	
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
	                <th colspan="4"><textarea  id="remark" name="remark"  cols="35" rows="8" class="txt_box_350"></textarea></th>
	            </tr>
	        </tfoot>    
		</table>
	</div>	
	<div id="message_confirm" style="display:none">
		<div align="center">确认执行该操作！</div>
	</div>
  </body>
</html>
