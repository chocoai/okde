<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>

		<title>登记发票</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<script type="text/javascript">
			var batchId=0;
			function all_branch_list_call_back(doc){
				if(doc.branchlist==null||doc.branchlist.length==0){
					return;
				}
				school=$("#academyId");
				pici=$("#globalBatchId");
				cengci=$("#levelId");
				zhuanye=$("#majorId");
				branch=$("#branch");
				
				jQuery("#branch").html("");
				school.html("");
				pici.html("");
				cengci.html("");
				zhuanye.html("");
				branch.html("");
				if('${userTicket.orgId}'==1){
					$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo(branch);
				}
				$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
           		$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
           		$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
           		$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
           		
				jQuery(doc.branchlist).each(function(){
					if('${userTicket.orgId}'!=1){
						if('${userTicket.orgId}'==this.id){
							$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
							return false;
						}
					}else{
						$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
						
					}
				});
				//移出所有事件
			   	branch.unbind();
				branch.change(function(){
					if($(this).val()==0){
							pici.html("");
           					$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
               				school.html("");
               				$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
							return;
					}
					//全局招生批次
					ajax_100_1();	
				});
				//是否编辑
				if(${student!=null}){
					//全局招生批次
					ajax_100_1();
				}
				//中心加载事件
				if('${userTicket.orgId}'!=1)
				{
					branch.change();
				}
			}
			//ajax回调函数   全局批次(学习中心)
			function ajax_global_batch(data)
			{		
				jQuery('#globalBatchId').empty();
			    jQuery('#globalBatchId').append('<option value="0">请选择院校批次</option>');
			    if(data.globalBatchList!=null && data.globalBatchList.length>0)
			    {
			    	jQuery.each(data.globalBatchList,function(){	
			    		if(this.id=="${student.globalBatch}"){
			    			jQuery('#globalBatchId').append('<option selected="selected" value="'+this.id+'">'+this.batch+'</option>');
			    		}else{
			    			jQuery('#globalBatchId').append('<option  value="'+this.id+'">'+this.batch+'</option>');
			    		}
			    		 
			    	});
			   	}
			  	//移出所有事件
			   	pici.unbind();
			    pici.change(function(){
					if($(this).val()==0){
               				school.html("");
               				$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
							return;
					}
					ajax_110_1();//院校
				});
			  //是否编辑
				if(${student!=null}){
					ajax_110_1();//院校
				}
			}
			//ajax回调函数   院校(学习中心、全局批次)
			function ajax_academy(data)
			{				
				jQuery('#academyId').empty();
			    jQuery('#academyId').append('<option value="0">请选择院校</option>');
			    if(data.academyList!=null && data.academyList.length>0)
			    {
			    	jQuery.each(data.academyList,function(){	
			    		if('${student.academyId}'==this.id){
							$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
						}else{
							$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo(school);
						}
			    	});
			   	}
			    jQuery('#batchName').html('');
			   	jQuery('#batchId').val(0);
			   	//移出所有事件
			   	school.unbind();
			    school.change(function(){
					if($(this).val()==0){
               				cengci.html("");
               				$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
							return;
					}	
					ajax_140_1();//招生批次	
				});	
			  //是否编辑
				if(${student!=null}){
					ajax_140_1();//招生批次
				}
			    
			}
			//ajax回调函数  层次(招生批次)
			function ajax_level(data)
			{				
				jQuery('#levelId').empty();
			    jQuery('#levelId').append('<option value="0">请选择院校层次</option>');
			    if(data.levellist!=null && data.levellist.length>0)
			    {
			    	jQuery.each(data.levellist,function(){	
			    		if('${student.levelId}'==this.level.id){
							$("<option  selected='selected' value='" + this.id + "'>" + this.level.name + "</option>").appendTo(cengci);
						}else{
							$("<option value='" + this.id + "'>" + this.level.name + "</option>").appendTo(cengci);
						}
			    	});
			   	}	
			  	//移出所有事件
			   	cengci.unbind();	
			   	cengci.change(function(){
					if($(this).val()==0){
               				zhuanye.html("");
               				$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
							return;
					}	
					ajax_130_1();//专业
				});	
			  	//是否编辑
				if(${student!=null}){
					ajax_130_1();//专业
				}
			   		
			}
			//ajax回调函数  专业(层次)
			function ajax_major(data)
			{				
				jQuery('#majorId').empty();
			    jQuery('#majorId').append('<option value="0">请选择院校专业</option>');
			    if(data.majorlist!=null && data.majorlist.length>0)
			    {
			    	jQuery.each(data.majorlist,function(){	
			    		 
			    		if('${student.majorId}'==this.id){
			    			jQuery('#majorId').append('<option selected="selected" value="'+this.id+'" title="' + this.name + '" >'+this.name+'</option>');
			    		}else{
			    			jQuery('#majorId').append('<option value="'+this.id+'" title="' + this.name + '" >'+this.name+'</option>');
						 }
			    	});
			    }	
			}
			//ajax回调函数  招生批次(院校、全局批次)
			function ajax_batch(data)
			{				
			    if(data.batch!=null)
			    {
			    	jQuery('#batchName').html(data.batch.enrollmentName);
			   		jQuery('#batchId').val(data.batch.id);
			    }	
			    else
			    {
			    	jQuery('#batchName').html('');
			   		jQuery('#batchId').val(0);
			    }
			    //batchId=parseInt(jQuery('#batchId').val());
			    
			    ajax_120_1();//层次	
			}
		</script>
		<a:ajax successCallbackFunctions="all_branch_list_call_back"
			pluginCode="000" urls="/crm/all_branch_list" isOnload="all" />

		<!--全局批次(学习中心)-->
		<a:ajax successCallbackFunctions="ajax_global_batch"
			parameters="{branchId:jQuery('#branch').val()}"
			urls="/enrollment/cascade_global_batch_branch_ajax" pluginCode="100" />

		<!--院校(学习中心、全局批次)-->
		<a:ajax successCallbackFunctions="ajax_academy"
			parameters="{branchId:jQuery('#branch').val(),globalBatchId:jQuery('#globalBatchId').val()}"
			urls="/enrollment/cascade_branch_global_batch_academy_ajax"
			pluginCode="110" />

		<!--层次(招生批次)-->
		<a:ajax successCallbackFunctions="ajax_level"
			parameters="{batchId:jQuery('#batchId').val()}"
			urls="/enrollment/cascade_batch_level_ajax" pluginCode="120" />

		<!--专业(层次)-->
		<a:ajax successCallbackFunctions="ajax_major"
			parameters="{levelId:jQuery('#levelId').val()}"
			urls="/enrollment/cascade_level_major_ajax" pluginCode="130" />

		<!--招生批次(院校、全局批次)-->
		<a:ajax successCallbackFunctions="ajax_batch"
			parameters="{academyId:jQuery('#academyId').val(),globalBatchId:jQuery('#globalBatchId').val()}"
			urls="/enrollment/cascade_global_batch_academy_batch_ajax"
			pluginCode="140" />

		<script type="text/javascript">
			
			var rdo=0;
			var chkcount=0;
			function ajax_student(data){
				var lists='';
				if(data.studentlst==null||data.studentlst.length==0){
					lists+='<tr><td colspan="7" align="center">没有找到符合条件的记录</td></tr>';	
				}else{
					$.each(data.studentlst,function(){
						lists+='<tr>';
						lists+='<td align="center"><input type="radio" name="sid" value="'+this.id+'" onclick="sercharfeepayment()"  /></td>';
						lists+='<td align="center">'+this.number+'</td>';
						lists+='<td align="center">'+this.schoolName+'</td>';
						lists+='<td align="center">'+this.academyenrollbatchName+'</td>';
						lists+='<td align="center">'+this.levelName+'</td>';
						lists+='<td align="center">'+this.majorName+'</td>';
						lists+='<td align="center">'+this.name+'</td>';
						lists+='</tr>';
					});
				}
				$('#sentable > tbody').html(lists.replaceAll("null",""));
			}
			function ajax_feepayment(data)
			{
				var lists='';
				if(data.feePayments==null)
				{
					lists+='<tr><td colspan="7" align="center">没有找到符合条件的记录</td></tr>';	
					
				}else{
					chkcount=1;
					$.each(data.feePayments,function(){
						var feePayment=this;
						if(feePayment.feePaymentDetailList!=null){
							$.each(feePayment.feePaymentDetailList,function(){
								lists+='<tr>';
								lists+='<td align="center"><input type="checkbox" id="chk" name="chk" value="'+this.id+'"/></td>';
								lists+='<td align="center">'+feePayment.code+'</td>';
								lists+='<td align="center">'+this.code+'</td>';
								//lists+='<td align="center">'+getstatus(this.status)+'</td>';
								lists+='<td align="center">'+this.status.getPaymentStatus()+'</td>';
								lists+='<td align="center">'+this.invoiceCodes+'</td>';
								lists+='<td align="center">'+this.paymentSubjectName+'</td>';
								lists+='<td align="center">'+this.amountPaied+'</td>';
								lists+='</tr>';
							});
						}
					});
				}
				lists.replaceAll("undefined","");
				$('#sentable_fee > tbody').html(lists.replaceAll("null",""));
				
				
			}
			function is_exist_invoicen_number_callback_function(data){
				if(!data.result){
					document.getElementById("form1").submit();
				}else{
					alert("发票号已存在！");
				}
			}
		</script>
		<a:ajax
			parameters="{'branchId':$('#branch').val(),name:$('#name').val(),academyId:$('#academyId').val(),batchId:$('#batchId').val(),levelId:$('#levelId').val(),majorId:$('#majorId').val(),globalBatchId:$('#globalBatchId').val()};{'id':rdo};"
			successCallbackFunctions="ajax_student;ajax_feepayment"
			urls="/finance/managerinvoice/liststudents;/finance/managerinvoice/listfeepayment"
			pluginCode="123" isOnload="" />
		
		<a:ajax parameters="{invoiceCode:$('#invoiceCode').val()}" successCallbackFunctions="is_exist_invoicen_number_callback_function" pluginCode="is_exist_invoicen_number" urls="/finance/managerinvoice/is_exist_invoicen_number" />
		

		<script type="text/javascript">
	
		
		
			
		/*
		加载学生列表
		*/
		function sercharstudent()
		{
			$('#studentlistDiv').attr('style','display:block');
			$('#feepaymentlistDiv').attr('style','display:none');
			ajax_123_1();	
		}	
			
			
		/*
		*加载缴费单列表
		*/
		function sercharfeepayment()
		{
				$('#feepaymentlistDiv').attr('style','display:block');
			    rdo= $("input[name='sid']:checked").val();
				$('#studentId').val(rdo);
				ajax_123_2();	
		}	
		function validator()
		{
			var invoiceCode=$('#invoiceCode').val();
			var issuedTime=$('#issuedTime').val();
			var ischk=$("input[name='chk']:checked").size();
			if(chkcount==0)
			{
				show('chkcountDiv','提示',250,150);
				return false;
			}
			if(ischk==0)
			{
			    show('ischkDiv','提示',250,150);
				return false;
			}
			if($.trim(invoiceCode) =="")
			{
				show('invoiceDiv','提示',250,150);
				return false;
			}
			if(issuedTime =="")
			{
				show('timeDiv','提示',250,150);
				return false;
			}
			//验证重复
			ajax_is_exist_invoicen_number_1();
			
		}
		//查询
		function search (){
			if( $.trim($('#name').val())==""){
				alert("请输入姓名！");
				return false;
			}
			sercharstudent();
		}

		</script>

	</head>

	<body>

		<form id="form1"  name="form1"
			action="<uu:url url="/finance/managerinvoice/add_manager_invoice_cedu" />"
			method="post" enctype="multipart/form-data">
			<input type="hidden" id="studentId" name="studentId" />
			<!--头部层开始 -->
			<head:head title="登记发票">
				
			</head:head>
			<!--主体层开始 -->
			<body:body>
				<table class="gv_table_2">
					<tr>
						<th style="width: 20px;">
							<img src="<ui:img url="images/title_menu/title_left.gif"/>" />
						</th>
						<th style="text-align: left; font-weight: bold;">
							发票明细
						</th>
					</tr>
				</table>
				<table class="add_table" border="0">
					<tr>
						<td align="right" width="100px;">
							学习中心：
						</td>
						<td align="left">
							
							<select class="txt_box_150" id="branch" ></select>
						</td>


						<td align="right" width="100px;">
							全局批次：
						</td>
						<td align="left">
							<select class="txt_box_150" id="globalBatchId"></select>
						</td>

						<td align="right" width="100px;">
							院校：
						</td>
						<td align="left">

							<select class="txt_box_150" id="academyId"
								></select>
						</td>

						<td align="right" width="100px;">
							招生批次：
						</td>
						<td align="left">
							<span id="batchName" style="color: black">&nbsp;</span>
							<input id="batchId" type="hidden" value="0"/>
						</td>
					</tr>

					<tr>
						<td align="right" width="100px;">
							层次：
						</td>
						<td align="left">

							<select class="txt_box_150" id="levelId" name="student.levelId"></select>
						</td>

						<td align="right" width="100px;">
							专业：
						</td>
						<td align="left">

							<select class="txt_box_150" id="majorId" name="student.majorId"></select>
						</td>

						<td align="right">
							<span>*</span>姓名：
						</td>
						<td>
							<input name="name" id="name" class="txt_box_130" value="${name }"/>
						</td>

						<td align="right"></td>
						<td>
							<input class="btn_black_61" type="button" value="查询"
								onclick="search();" />
						</td>

					</tr>
				</table>

				<div id="studentlistDiv" style="display: none">

					<table id="sentable" class="gv_table_2" border="0" cellpadding="2"
						cellspacing="2">
						<thead>
							<th>
								操作
							</th>
							<th>
								学号
							</th>
							<th>
								院校
							</th>
							<th>
								招生批次
							</th>
							<th>
								层次
							</th>
							<th>
								专业
							</th>
							<th>
								姓名
							</th>
						</thead>
						<tbody>
						</tbody>
					</table>



				</div>



				<div id="feepaymentlistDiv" style="display: none">
					<table class="gv_table_2">
						<tr>
							<th style="width: 20px;">
								<img src="<ui:img url="images/title_menu/title_left.gif"/>" />
							</th>
							<th style="text-align: left; font-weight: bold;">
								缴费单列表
							</th>

						</tr>
					</table>

					<table id="sentable_fee" class="gv_table_2" border="0"
						cellpadding="2" cellspacing="2">
						<thead>
							<th>
								操作
							</th>
							<th>
								缴费单单号
							</th>
							<th>
								缴费单明细号
							</th>
							<th>
								缴费单状态
							</th>
							<th>
								已开发票号
							</th>
							<th>
								费用项目
							</th>
							<th>
								实收金额
							</th>

						</thead>
						<tbody>
						</tbody>
					</table>

					<table class="add_table">
						<tr>
							<td style="width: 30%" align="right">
								<span>*</span>发票号：
							</td>
							<td style="width: 20%">
								<input type="text" name="invoiceCode" id="invoiceCode" />
							</td>
							<td style="width: 20%" align="right">
								<span>*</span>开票日期：
							</td>
							<td style="width: 30%">
								<input class="Wdate" name="issuedTime" type="text"
									id="issuedTime" onclick="WdatePicker()" />
							</td>
						</tr>

						<tr>
							<td align="center" colspan="4">
								<input class="btn_black_61" type="button" onclick="validator();" value="保存" />
								<input class="btn_black_61" type="button"  onclick="javascript:history.go(-1);" value="取消" />
							</td>

						</tr>
					</table>

				</div>

				<div id="invoiceDiv" style="display: none">
					发票号不能为空！！
				</div>

				<div id="timeDiv" style="display: none">
					开票时间不能为空！！
				</div>

				<div id="ischkDiv" style="display: none">
					至少选择一个缴费单！！
				</div>

				<div id="chkcountDiv" style="display: none">
					没有缴费单，不能登记发票
				</div>




			</body:body>

		</form>
	</body>
</html>
