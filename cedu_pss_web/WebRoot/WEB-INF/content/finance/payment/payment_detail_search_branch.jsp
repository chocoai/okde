<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<%@ include file="../../template/common/download_excel.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>多费用科目查询</title>
	<!--  jquery库 -->
	<jc:plugin name="jquery" />
	<!-- jquery-UI -->
	<jc:plugin name="jquery_ui" />
	<!-- 整体样式 -->
	<jc:plugin name="default" />
	<!-- 表单验证 -->
	<jc:plugin name="validator" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- 分页 -->
	<jc:plugin name="page" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	<!-- 时间控件 -->
	<jc:plugin name="calendar" />
	
	<script type="text/javascript">

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
			
			//选择费用科目
			jQuery('#fee_subject_div').dialog({
				autoOpen:false,
				modal:true,
				draggable:false,
				resizable:false,
				title:'费用科目',
				buttons: {
					'确定': function() { 
						//获取选中的值
						var fsIds='';
						var fsNames='';
						jQuery("input[name='fscheck']").each(function(){
							if(jQuery(this).attr("checked")==true)
							{
							 	fsIds+=jQuery(this).val()+",";
							 	fsNames+=jQuery("#sp_"+jQuery(this).val()).text()+";";
							}
						});
						jQuery('#feeSubjectIds').val(fsIds);
						jQuery('#feeSubjectNames').val(fsNames);
						
						jQuery(this).dialog("close"); 
					}, 
					'关闭': function() { 
						jQuery(this).dialog("close"); 
					} 
				}
			});	
			
			
			
			ajax_1130_1();//统计所有金额
			
			//院校相关级联(批次、(防止刷新时其他不刷新))
			jQuery('#academyId').change(function(){
				ajax_130_1();
					
			});	
			//招生批次相关级联(层次、专业(防止刷新层次，专业不刷新))
			jQuery('#batchId').change(function(){
				ajax_140_1();
				
			});	
			//层次专业级联
			jQuery('#levelId').change(function(){
				ajax_150_1();
			});	
		});
		//全选
		function chkall(obj)
		{
			jQuery(".fsclass").attr('checked',obj.checked);
		}
		function showdiv()
		{
			jQuery('#fee_subject_div').dialog('open');
		}
		//查询
		function showsearch()
		{
			search001();
			search002();
			ajax_1130_1();//统计所有金额
		}
		//导出
		function download(op)
		{
			if($("#branchId").val()=="0")
			{
				alert("必须选择学习中心！");
				return false;
			}
			if(confirm("您确定要导出数据吗？"))
			{
				if(op==1)
				{
					ajax_download_zip_1();
				}
				else
				{
					ajax_download_saam_zip_1();
				}
			}
		}
		
		//显示缴费单状态
		function showstatus(status)
		{
			return status.getPaymentStatus();
		}
		//显示列表金额
		function feePaymentValue(accountMoney){
			return (accountMoney+"").toMoney();
		}
		//显示充值金额类型
		function showrechargemoney(accountMoney,types)
		{
			if(types==STATUS_RECHARGE)
			{
				return (accountMoney+"").toMoney();
			}
			if(types==STATUS_CONSUMPTION)
			{
				return '-'+(accountMoney+"").toMoney();
			}
			if(types==STATUS_APPLY_CONSUMPTION_TRUE)
			{
				return '-'+(accountMoney+"").toMoney();
			}
			return (accountMoney+"").toMoney();
		}
		//显示充值类别
		function showtypes(types)
		{
			if(types==STATUS_RECHARGE)
			{
				return '充值';
			}
			if(types==STATUS_CONSUMPTION)
			{
				return '消费';
			}
			if(types==STATUS_APPLY_CONSUMPTION_TRUE)
			{
				return '退费';
			}
			return '--';
		}
		//显示充值时间
		function showtime(createdTime)
		{
			if(createdTime==null)
			{
				return createdTime;
			}
			if(createdTime.length>10)
			{
				return  createdTime.substring(0,10);
			}
			return createdTime;
		}
		//显示缴费方式
		function feeWayIdValue(feeWayId)
		{
			return feeWayId.getPaymentWay();
		}
		//读取批次
		function selectbatch(data)
		{
		    $('#batchId').empty();
			$('#batchId').append('<option value="0">--请选择--</option>');	
			if(data.batchlist!=null&&data.batchlist.length>0)
			{
				$.each(data.batchlist,function(){	
				   $('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				});
			}
			ajax_140_1();
		 }
		 //读取层次
		 function selectlevel(data)
		 {
		    $('#levelId').empty();
			$('#levelId').append('<option value="0">--请选择--</option>');
			if(data.levellist!=null&&data.levellist.length>0)
			{	
				$.each(data.levellist,function(){	
				   $('#levelId').append('<option value="'+this.id+'">'+this.level.name+'</option>'); 
				});
			}	
			ajax_150_1();
			       	
		 }
		//读取专业
		 function selectmajor(data)
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
		//ajax回调函数   院校
		function ajax_stusearchallajax(data)
		{
			//院校
			jQuery("#academyId").empty();
			jQuery("#academyId").append('<option value="0">--请选择--</option>');
			if(data.academyList!=null && data.academyList.length>0)
			{
				$.each(data.academyList,function(){	
				    jQuery("#academyId").append('<option value="'+this.id+'">'+this.name+'</option>'); 
				});
			}
			
		}	
		
		
		//统计充值金额 
		function countallmoney(data)
		{
			//alert(data.allStuaaMoney);
			//jQuery("#moneyall").html("合计：<font style='color:red'><b>"+data.allStuaaMoney+"</b></font>元");
			jQuery("#moneyall").html("合计：未使用金额<font style='color:red'><b>"+data.stuNotUseMoney+"</b></font>元(充值总金额：<font style='color:red'>"+data.allStuaaMoney+"</font>元)");
		}
		//统计缴费单金额 
		function countfpdallmoney(data)
		{
			//alert(data.allStuFpdMoney);
			//jQuery("#fpdmoneyall").html("合计：<font style='color:red'><b>"+data.allStuFpdMoney+"</b></font>元(不含充值金额)");
			jQuery("#fpdmoneyall").html("合计：<font style='color:red'><b>"+data.allShiJIaoMoney+"</b></font>元(使用充值金额：<font style='color:red'>"+data.stuShiYongChongzhiMoney+"</font>元)");
		}
		//统计总金额 
		function countstuallmoney(data)
		{
			//alert(data.allFeePaymentMoney);
			jQuery("#paysum").html("总合计：<font style='color:red'><b>"+data.allStuAllMoney+"</b></font>元");
		
			//充值金额
			//jQuery("#moneyall").html("合计：未使用金额<font style='color:red'><b>"+data.stuNotUseMoney+"</b></font>元(充值总金额：<font style='color:red'>"+data.stuaaMoney+"</font>元)");
			jQuery("#moneyall").html("合计：充值金额<font style='color:red'><b>"+data.stuNotUseMoney+"</b></font>元");
			//缴费单金额
			jQuery("#fpdmoneyall").html("合计：<font style='color:red'><b>"+data.allShiJIaoMoney+"</b></font>元");
		}
		function countCallback002(data)
		{
			//ajax_1110_1();//统计充值金额
		}
		function countCallback001(data)
		{
			//ajax_1120_1();//统计缴费单明细金额
		}		
	</script>
	<!-- 院校-->
	<a:ajax 
		pluginCode="110"
		successCallbackFunctions="ajax_stusearchallajax" 
		urls="/crm/academy_all_list_for_stu_search_ajax"
		isOnload="all" 			
	/>
	<!-- 招生批次-->
	<a:ajax 
		pluginCode="130"
		successCallbackFunctions="selectbatch" 
		urls="/enrollment/cascade_academy_batch_all_ajax"
		parameters="{academyId:jQuery('#academyId').val()}"		
	/>
	<!-- 层次-->
	<a:ajax 
		pluginCode="140"
		successCallbackFunctions="selectlevel" 
		urls="/enrollment/cascade_batch_level_ajax"
		parameters="{batchId:jQuery('#batchId').val()}"		
	/>
	<!-- 专业-->
	<a:ajax 
		pluginCode="150"
		successCallbackFunctions="selectmajor" 
		urls="/enrollment/cascade_level_major_ajax"
		parameters="{levelId:jQuery('#levelId').val()}"		
	/>
	<!-- 统计充值金额 -->
	<a:ajax 
		parameters="jQuery('#myform').serializeObject()" 
		successCallbackFunctions="countallmoney" 
		pluginCode="1110" 		
		urls="finance/studentaccountmanagement/count_student_account_account_all_money_search_ajax"
	/>
	<!-- 统计缴费单金额 -->
	<a:ajax 
		parameters="jQuery('#myform').serializeObject()" 
		successCallbackFunctions="countfpdallmoney" 
		pluginCode="1120" 
		urls="finance/payment/count_student_fpd_money_search_ajax"
	/>
	<!-- 统计总金额 -->
	<a:ajax 
		parameters="jQuery('#myform').serializeObject()" 
		successCallbackFunctions="countstuallmoney" 
		pluginCode="1130" 
		urls="finance/payment/count_student_fpd_all_money_search_ajax"
	/>
	
	<!-- 下载地址1 -->
	<a:ajax 
		parameters="jQuery('#myform').serializeObject()" 
		successCallbackFunctions="excel_export_callback" 
		pluginCode="download_zip" 
		urls="finance/payment/excel_export_payment_detail_search_admin_ajax_branch"
	/>
	<!-- 下载地址2 -->
	<a:ajax 
		parameters="jQuery('#myform').serializeObject()" 
		successCallbackFunctions="excel_export_callback" 
		pluginCode="download_saam_zip" 
		urls="finance/studentaccountmanagement/excel_export_stu_account_amount_management_ajax_branch"
	/>
	
</head>
<body>
	<head:head title="多费用科目查询">
		
	</head:head>
	<body:body>
		<!--form id="myform" method="post">
			 <table class="add_table" style="width:100%;border:0px;">					
				<tr>
					<td colspan="2"> -->
					<form id="myform" method="post">
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
								<th style="text-align:left; font-weight:bold;">查询条件</th>
								<th></th>
							</tr>
						</table>
						<table class="add_table">
							<tbody id="rebateCondition">	
							   <tr>
								  <td align="right">学习中心：</td>
								  <td align="left">								  
								  	${branch.name}
								  	<input type="hidden" name="student.branchId" id="branchId" value="${branch.id}"/>								  		
								  </td>	
								  <td align="right">全局批次：</td>
								  <td align="left">
								  	<s:if test="%{globalBatchList!=null}">
					                	<s:select list="%{globalBatchList}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="title" name="student.glbtach" id="glbtachId" cssClass="txt_box_150"/>
					                </s:if>
					                <s:else>
					                	<select name="student.glbtach" id="glbtachId" class="txt_box_150">
											<option value="0">--请选择--</option>
										</select>
					                </s:else>
								  </td>	
								  <td align="right">院校：</td>
								  <td align="left">
								  	<select class="txt_box_150" name="student.academyId" id="academyId">
								  		<option value="0">--请选择--</option>
								  	</select>
								  </td>
								  
								</tr>
								<tr>
									<td align="right">招生批次：</td>
								    <td align="left">
								  		<select class="txt_box_150" name="student.enrollmentBatchId" id="batchId">
								  			<option value="0">--请选择--</option>
								  		</select>
								    </td>	
									<td align="right">层次：</td>
									  <td align="left">
									  	<select class="txt_box_150" name="levelId" id="levelId">
									  		<option value="0">--请选择--</option>
									  	</select>
									  </td>
									<td align="right">专业：</td>
									  <td align="left">
									  	<select class="txt_box_150" name="student.majorId" id="majorId">
									  		<option value="0">--请选择--</option>
									  	</select>
									  </td>
									
				             	</tr>
				             	<tr>
				             		<td align="right">姓名：</td>
									<td align="left">
										<input  name="student.name" class="txt_box_150" type="text" value="" />
									</td>
				             		<td align="right">证件号：</td>
									<td align="left">
										<input  name="student.certNo" class="txt_box_150" type="text" />
									</td>	
									<td align="right">缴费单状态：</td>
					                <td align="left">
										<select name="status" id="status" class="txt_box_150">
											<option value="0">--请选择--</option>
											<option value="<%=Constants.PAYMENT_STATUS_TUI_FEI_SUCCESS %>">退费成功</option>
											<option value="<%=Constants.PAYMENT_STATUS_KE_YI_ZHI_JIE_TUI_FEI %>">可直接退费</option>
											<option value="<%=Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN %>">退费审批通过已确认</option>
											<option value="<%=Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN %>">退费审批通过待确认</option>
											<option value="<%=Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN %>">已收费确认</option>						
											<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN %>">已填汇款单</option>
											<option value="<%=Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU %>">已汇款总部</option>				
											<option value="<%=Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN %>">总部确认</option>
											<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN %>">已填打款单</option>								
											<option value="<%=Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN %>">已确认待汇款</option>
											<option value="<%=Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO %>">已打款院校</option>
											<option value="<%=Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN %>">已填返款单</option>
											<option value="<%=Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN %>">返款确认</option>								 
										</select>
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
									<td align="right">缴费方式：</td>
									<td>
										<s:if test="%{feeWayList!=null}">
						                	<s:select list="%{feeWayList}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="feeWayId" id="feeWayId" cssClass="txt_box_150"/>
						                </s:if>
						                <s:else>
						                	<select name="feeWayId" id="feeWayId" class="txt_box_150">
												<option value="0">--请选择--</option>
											</select>
						                </s:else>
									</td>	
								</tr>
								<tr>	
									<td align="right">总部确认日期起：</td>
					                <td align="left">
										<input type="text" name="ccStartTime" id="ccStartTime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',maxDate:'#F{$dp.$D(\'ccEndTime\',{d:-0});}'})" readonly="readonly"/>					  			
									</td>
					                <td align="right">总部确认日期止：</td>
					                <td align="left">
										<input type="text" name="ccEndTime" id="ccEndTime" class="Wdate" onfocus="WdatePicker({skin:'whyGreen',minDate:'#F{$dp.$D(\'ccStartTime\',{d:0});}'})" readonly="readonly"/>
									</td>
									<td ></td>
									<td ></td>
				             	</tr>
								<tr>	
									<td align="right">费用科目：</td>
									<td align="left" colspan="3">
										<input name="feeSubjectNames" id="feeSubjectNames" class="txt_box_300" type="text" value="" readonly="readonly"/>&nbsp;&nbsp;<a href="javascript:showdiv();" style="text-decoration:underline">请选择</a>
										<input name="feeSubjectIds" id="feeSubjectIds" type="hidden" value="" />
									</td>
									<td ></td>
					                <td align="left">
										<input type="button" class="btn_black_61"  onclick="showsearch();" value="查询"/>
									</td>
				             	</tr>
				             	
							</tbody>
						</table>
					</form>	
					<!-- /td>
				</tr>
				<tr>
					<td style="50%"  valign="top"> -->
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
								<th style="text-align:left; font-weight:bold;">总金额合计</th>
								
							</tr>
						</table>
						<table class="add_table">
						 <tbody>	 
							<tr>
								<td class="lable_100">&nbsp;</td>
								<td align="left">
									<span id="paysum">0</span>
								</td>				
							</tr>
						  </tbody>
						</table>
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
								<th style="text-align:left; font-weight:bold;">缴费单明细</th>
								<th style="text-align:right; font-weight:bold;">
									<input type="button" class="btn_black_61"  onclick="download(1)" value="导出"/>&nbsp;&nbsp;
								</th>
							</tr>
						</table>
								 
								<page:plugin 
									pluginCode="001"
									il8nName="finance_payment"
									subStringLength="20"
									searchListActionpath="list_fee_payment_detail_search_ajax"
									searchCountActionpath="count_fee_payment_detail_search_ajax"
									columnsStr="createdTime;paymentCode;studentName;branchName;schoolName;academyenrollbatchName;levelName;majorName;paymentSubjectName;jiaofeiValue;feeWayId;ceduConfirmTime;#status"
									customColumnValue="0,showtime(createdTime);9,feePaymentValue(moneyToChannel);10,feeWayIdValue(feeWayId);11,showtime(ceduConfirmTime);12,showstatus(status)"
									isPage="true"
									params="'result.order':'createdTime','result.sort':'desc'"
									searchFormId="myform"
									customToolbarID="fpdmoneyall1"
									listCallback="countCallback"
								/>
						<table class="add_table">
						 <tbody>	 
							<tr>
								<td class="lable_100">&nbsp;</td>
								<td align="left">
									<span id="fpdmoneyall" style="color:black">0</span>
								</td>				
							</tr>
						  </tbody>
						</table>	
					<!-- /td>
					<td style="50%"  valign="top"> -->
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
								<th style="text-align:left; font-weight:bold;">充值金额</th>
								<th style="text-align:right; font-weight:bold;">
									<input type="button" class="btn_black_61"  onclick="download(2)" value="导出"/>&nbsp;&nbsp;
								</th>
							</tr>
						</table> 
								<page:plugin 
									pluginCode="002"
									il8nName="finance_payment"
									searchCountActionpath="finance/studentaccountmanagement/count_student_account_amount_ajax"
									searchListActionpath="finance/studentaccountmanagement/list_student_account_amount_ajax"
									columnsStr="createdTime;studentName;branchName;schoolName;academyenrollbatchName;levelName;majorName;feeSubjectName;#type;accountMoney"
									customColumnValue="0,showtime(createdTime);8,showtypes(types);9,showrechargemoney(accountMoney,types)"
									isPackage="false"
									params="'result.order':'createdTime','result.sort':'desc'"
									searchFormId="myform"	
									customToolbarID="moneyall1"
									listCallback="countCallback"	
								/>
							<table class="add_table">
						 <tbody>	 
							<tr>
								<td class="lable_100">&nbsp;</td>
								<td align="left">
									<span id="moneyall" style="color:black">0</span>
								</td>				
							</tr>
						  </tbody>
						</table>
					<!-- /td>
				</tr>
			</table> 							
			</form> 
			<table class="add_table">
			 <tbody>	 
				<tr>
					<td class="lable_100">总金额：</td>
					<td align="left">
						<span id="paysum">0</span>
					</td>				
				</tr>
			  </tbody>
			</table>-->	
	</body:body>
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
			
		</div>
	</div>
	<!--弹出层    确认操作-->
	<div id="message_confirm" style="display:none">
		<div align="center" >
			<b>确认移除选中的缴费单明细？</b>
		</div>
	</div>
	<!--弹出层    确认操作-->
	<div id="fee_subject_div" style="display:none">
		<table class="gv_table_2" id="">
			<thead>
				<tr>
					<th align="center"><input type="checkbox" onclick="chkall(this)" /></th>
			  		<th align="center">学习中心</th>
				</tr>
			</thead>					
			<tbody>	
				<s:if test="%{feesubjectlist!=null && feesubjectlist.size()>0}">
					<s:iterator id="item" value="feesubjectlist" >
						<tr>
							<td align="center"><input type="checkbox" id="fs_${item.id}" name="fscheck" class="fsclass" value="${item.id}" /></td>
					  		<td align="center"><span id="sp_${item.id}" style="color:black">${item.name}</span></td>
						</tr>
					</s:iterator>
		       </s:if>
		       <s:else>
			       <tr>
			       	 <td colspan="2" align="center">无费用科目</td>
			       </tr>
		      </s:else>							
			</tbody>						
		</table>
	</div>
	
</body>
</html>
