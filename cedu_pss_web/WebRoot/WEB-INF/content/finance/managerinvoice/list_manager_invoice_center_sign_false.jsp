<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>

		<title>发票管理</title>
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
				//$("<option value='" + 0 + "'>请选择学习中心</option>").appendTo(branch);
				$("<option value='" + 0 + "'>请选择院校批次</option>").appendTo(pici);
           		$("<option value='" + 0 + "'>请选择院校</option>").appendTo(school);
           		$("<option value='" + 0 + "'>请选择院校层次</option>").appendTo(cengci);
           		$("<option value='" + 0 + "'>请选择院校专业</option>").appendTo(zhuanye);
           		
				jQuery(doc.branchlist).each(function(){
					if('${userTicket.orgId}'==this.id){
						$("<option selected='selected' value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
					}else{
						//$("<option value='" + this.id + "'>" + this.name + "</option>").appendTo($("#branch"));
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
				//全局招生批次
				ajax_100_1();
				search123();
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
			function ajax_update(data) {
				show('updDiv', '提示', 200, 150);
				//search123();
				refresh123();
			}
		</script>
		<a:ajax parameters="{'id':$('#id').val()}"
			successCallbackFunctions="ajax_update"
			urls="/finance/managerinvoice/updatemanagementinvoicesign"
			pluginCode="123" isOnload="" />






		<script type="text/javascript">
			/*
			邮寄单签收
			 */
			function updatesign(id) {
				$('#id').val(id);
				ajax_123_1();
		
			}
			/*
			 发票详情
			 */
			function linkinvoice(id, invoiceCode) {
				return '<a href="' + WEB_PATH
						+ '/finance/managerinvoice/view_management_invoice?id=' + id
						+ '"  target="_blank" >' + invoiceCode + '</a>';
			}
		
			/*
			状态显示
			 */
			function isSignValue(isSign) {
				return isSign == 0 ? "未签领" : "已签领"
			}
		
			/*
			操作修改状态
			 */
			function operation(id) {
				return '<a href="javascript:void(0)"  onclick="updatesign(' + id
						+ ')">签领</a>';
			}
			function amountPaiedValue(amountPaied){
				return (amountPaied+"").toMoney();
			}
		</script>

	</head>

	<body>
		<!--头部层开始 -->
		<head:head title="发票签领">
			<html:a text="登记发票" href="/finance/managerinvoice/add_manager_invoice_cedu" icon="add"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>

			<!--Menu Begin-->
			<%@ include file="../_tab_title/manager_invoice_center_tab.jsp"%>
			<!--Menu End-->

			<table class="gv_table_2">
				<tr>
					<th style="width: 20px;">
						<img src="<ui:img url="images/title_menu/title_left.gif"/>" />
					</th>
					<th style="text-align: left; font-weight: bold;">
						${branch.name}
					</th>

				</tr>
			</table>
			<form id="searchForm">
				<table class="add_table" cellpadding="0" cellspacing="0" border="0"
					width="100%">
					<tr>
						<td class="lable_100">
							邮寄包号：
						</td>
						<td>
							<input type="text" id="postalNo" name="postalNo"
								class="txt_box_130" />
						</td>
						<td class="lable_100">
							发票号：
						</td>
						<td>
							<input type="text" id="invoiceNo" name="invoiceNo"
								class="txt_box_130" />
						</td>


						<td class="lable_100">
							缴费单号：
						</td>
						<td>
							<input type="text" id="feePaymentNo" name="feePaymentNo"
								class="txt_box_130" />
						</td>

					</tr>
					<tr>

						<td align="right" width="100px;">
							学习中心：
						</td>
						<td align="left">

							<select class="txt_box_150" id="branch" name="student.branchId"></select>
						</td>


						<td align="right" width="100px;">
							全局批次：
						</td>
						<td align="left">
							<select class="txt_box_150" id="globalBatchId" name="student.globalBatch"></select>
						</td>

						<td align="right" width="100px;">
							院校：
						</td>
						<td align="left">
							<select class="txt_box_150" id="academyId" name="student.academyId"></select>
						</td>

						<td align="right" width="100px;">
							招生批次：
						</td>
						<td align="left">
							<span id="batchName" name="batch" style="color: black">&nbsp;</span>
							<input id="batchId" type="hidden" value="0" name="student.enrollmentBatchId"/>
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
							<input id="name" class="txt_box_130" name="student.name"/>
						</td>

						<td align="right"></td>
						<td>
							<input class="btn_black_61" type="button" value="查询"
								onclick="search123()" />
						</td>

					</tr>

				</table>
			</form>
			<page:plugin pluginCode="123" il8nName="finance"
				searchListActionpath="listmanagementinvoicecenterbyparams"
				searchCountActionpath="countmanagementinvoicecenterbyparams"
				columnsStr="invoiceCode;studentName;academyenrollbatchName;levelName;majorName;amountPaied;isSign;#operation"
				customColumnValue="0,linkinvoice(id,invoiceCode);5,amountPaiedValue(amountPaied);6,isSignValue(isSign);7,operation(id)"
				isPage="true" pageSize="10" ontherOperatingWidth="80px"
				isonLoad="false"
				params="'branchId':'${userTicket.orgId }','isSign':0"
				searchFormId="searchForm" />
			<input type="hidden" id="id" name="id"/>
			<div id="updDiv" style="display: none">
				签领成功！！
			</div>

		</body:body>


	</body>
</html>
