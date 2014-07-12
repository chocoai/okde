<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>汇款总部(中心)</title>
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
				
				//不符合的缴费单
				jQuery('#show_for_feepaymentdetail').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'缴费单明细',
					width: 650,
					buttons: {
						'关闭': function() { 
							jQuery(this).dialog("close"); 
						} 
					}
				});	
				
			});
			
			//查询
			function showSearch()
			{
				search601();
			}
			
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
					jQuery("#showDialog").html('<b>收款单位不能为空！</b>');
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
					jQuery("#showDialog").html('<b>请确认选择要汇款的缴费单明细！</b>');
					jQuery('#message_returns_tips').dialog('open');	
				}
				else
				{
					//去除空格提交
					jQuery("#remitterAccounts").val(jQuery.trim(jQuery("#remitterAccount").val()));
					jQuery("#remitteeAccounts").val(jQuery.trim(jQuery("#remitteeAccount").val()));
					
					
					//备注
					jQuery("#note").val(jQuery("#notes").val());
					ajax_110_1();//生成汇款单
				}
			}
			//显示充值时间
			function showtime(createdTime)
			{
				if(createdTime.length>10)
				{
					return  createdTime.substring(0,10);
				}
				return createdTime;
			}
			//显示缴费单状态
			function showstatus(status)
			{
				return status.getPaymentStatus();
			}
			
			//选择需要汇款的待缴费单  获取缴费单明细ids集合
			function selectfpd()
			{
				if(getCheckedValues601()==null || getCheckedValues601()=="")
				{
					jQuery("#showDialog").html('<b>请选择要汇款的缴费单明细！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else
				{
					ajax_120_1();//判断选择	
				}
			}
			
			//选择显示金额和统计效果
			function showplaymoney()
			{
				jQuery("#countdiv").hide();
			
				if(jQuery("#remitteeId").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择收款单位！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else if(jQuery("#feepaymentIds").val()==null || jQuery("#feepaymentIds").val()=="")
				{
					jQuery("#showDialog").html('<b>请选择要汇款的缴费单明细！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else
				{
					feePaymentDetailIds=jQuery("#feepaymentIds").val();
					ajax_100_1();//统计汇款
				}
			}
			
			//移除不需要汇款的待缴费单  获取缴费单明细ids集合
			function deletefpd()
			{
				if(getCheckedValues602()==null || getCheckedValues602()=="")
				{
					jQuery("#showDialog").html('<b>请选择要移除的缴费单明细！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else
				{
					$('#message_confirm').dialog({
						title:'确认操作',
						buttons: {
							'确认': function() { 
									$(this).dialog("close"); 
									ajax_130_1();//移除
								}, 
							'取消': function() { 
									$(this).dialog("close"); 
								} 
						}
					});
					$('#message_confirm').dialog("open"); 
				}
			}
		
			
			//ajax 回调函数    统计汇款
			var feePaymentDetailIds="";
			function ajax_showcountplaymoney(data)
			{
				//统计
				$('#showcounttab > tbody').empty();
			   	var list='';
			   	var index=1;
			   
				if(data.feepdList!=null && data.feepdList.length>0)
				{
			    	$.each(data.feepdList,function(){	
			    		list+='<tr>';
				    		list+='<td align="center">'+index+'</td>';
				    		list+='<td align="center">'+this.schoolName+'</td>';
				    		list+='<td align="center">'+this.branchName+'</td>';
				    		list+='<td align="center">'+this.academyenrollbatchName+'</td>';
				    		list+='<td align="center">'+this.paymentSubjectName+'</td>';
				    		list+='<td align="center">'+this.payBranchCedu+'</td>';
			    		list+='</tr>';
			    		
			    		index++;
			    	});	
			    	//缴费金额赋值	
			    	//jQuery("#allamount").html(allmoney);
			    	//jQuery("#amount").val(allmoney);   
			    	jQuery("#countdiv").show(); 
			    }
			    else
			    {
			    	list+='<tr><td colspan="4" align="center">没有相关数据！</td></tr>';
			    }
			    $('#showcounttab > tbody').html(list);
			}
			
			//ajax 回调函数    生成汇款单
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
					jQuery("#showclose").html('<b>添加汇款单成功！点击确定关闭当前页。</b>');
					$('#null_for_close').dialog("open");
				}
				else
				{
					jQuery("#showDialog").html('<b>添加失败！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
			}
			
			//ajax回调函数  选取缴费单明细时的验证
			function ajax_test_fpd(data)
			{				
				if(data.isback)
				{
					jQuery("#feepaymentIds").val(data.newFeepdIds);
					//alert(jQuery("#feepdIds").val());
					//jQuery("#allamount").html(allmoney);
			    	//jQuery("#amount").val(allmoney);  
					var paysum = jQuery('#allamount').text();
					paysum = parseFloat(paysum);
					jQuery('#allamount').text(paysum+parseFloat(data.allRebateMoney));	
					jQuery('#amount').val(paysum+parseFloat(data.allRebateMoney));
					search602();
					jQuery("#countdiv").hide(); 
					//showplaymoney();
				}
				else
				{
					//已经存在的数据
					$('#hasfpdtab > tbody').empty();
				   	var haslist='';
					if(data.hasFpdList!=null && data.hasFpdList.length>0)
					{
						$.each(data.hasFpdList,function(){	
							haslist+='<tr>';
							haslist+='<td align="center">'+this.paymentCode+'</td>';
							haslist+='<td align="center">'+this.studentName+'</td>';
							haslist+='<td align="center">'+this.paymentSubjectName+'</td>';
							haslist+='<td align="center">'+this.moneyToPay+'</td>';
							haslist+='</tr>';
						});
					}
					else
					{
						haslist+='<tr><td align="center" colspan="4">无相关数据！</td></tr>';
					}
					$('#hasfpdtab > tbody').html(haslist);
					
					
					jQuery("#feepaymentIds").val(data.newFeepdIds);
					//alert(jQuery("#feepdIds").val());
					//jQuery("#allamount").html(allmoney);
			    	//jQuery("#amount").val(allmoney);  
					var paysum = jQuery('#allamount').text();
					paysum = parseFloat(paysum);
					jQuery('#allamount').text(paysum+parseFloat(data.allRebateMoney));	
					jQuery('#amount').val(paysum+parseFloat(data.allRebateMoney));
					search602();
					jQuery("#countdiv").hide(); 
					//showplaymoney();
					jQuery('#show_for_feepaymentdetail').dialog('open');
				}		
			}
			
			//ajax回调函数  移除缴费单明细
			function ajax_del_fpd(data)
			{
				if(data.isfail)
				{
					jQuery("#feepaymentIds").val(data.newdelFeepdIds);			
					jQuery('#allamount').text(data.alldelRebateMoney);
					jQuery('#amount').val(data.alldelRebateMoney);
					search602();
					jQuery("#countdiv").hide(); 
					//showplaymoney();
				}
				else
				{
					jQuery("#showDialog").html('<b>移除失败！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
			}
	</script>
		<!--统计汇款-->
		<a:ajax 
			pluginCode="100"
			successCallbackFunctions="ajax_showcountplaymoney" 
			parameters="{feePaymentDetailIds:feePaymentDetailIds}" 
			urls="/finance/orderbranchcedu/feepaymentdetail_count_all_orderbranchcedu_ajax" 
		/>
		<!--生成汇款单-->
		<a:ajax 
			pluginCode="110"
			successCallbackFunctions="ajax_addplaymoneyacdemy" 
			parameters="jQuery('#myform').serializeObject()" 
			urls="/finance/orderbranchcedu/add_order_branch_cedu_ajax" 			
		/>
		
		<!--选取缴费单明细时的验证-->
		<a:ajax 
			successCallbackFunctions="ajax_test_fpd" 
			parameters="{oldFeepdIds:jQuery('#feepaymentIds').val(),addFeepdIds:getCheckedValues601()}" 
			urls="/finance/orderbranchcedu/test_feepaymentdetail_for_order_branch_cedu_ajax" 
			pluginCode="120"
		/>
		
		<!--移除缴费单明细-->
		<a:ajax 
			successCallbackFunctions="ajax_del_fpd" 
			parameters="{allFeepdIds:jQuery('#feepaymentIds').val(),delFeepdIds:getCheckedValues602(),allmoney:$('#allamount').text()}" 
			urls="/finance/orderbranchcedu/del_feepaymentdetail_for_order_branch_cedu_ajax" 
			pluginCode="130"
		/>
	</head>
  
  <body>
  		
		<!-- 头开始 -->
		<head:head title="汇款总部(中心)">
			<html:a text="关闭" icon="add" onclick="window.close()" target="_self"/>	
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="myform">
				<input type="hidden" name="feepaymentIds" id="feepaymentIds" value=""/>
				<input type="hidden" name="indexcount" id="indexcount" value="0"/>
				
				<input type="hidden" name="orderBranchCedu.remitterId" id="remitterId" value="${branch.id}" />
				<input type="hidden" name="orderBranchCedu.remitteeId" id="remitteeId" value="${cedu.id}" />
				<input type="hidden" name="orderBranchCedu.amount" id="amount" value="0"/>
				<input type="hidden" name="orderBranchCedu.note" id="note" value=""/>
				<input type="hidden" name="orderBranchCedu.remitterAccount" id="remitterAccounts" value=""/>
				<input type="hidden" name="orderBranchCedu.remitteeAccount" id="remitteeAccounts" value=""/>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">添加汇款单</th>
					</tr>
				</table>
			</form>	
				<table class="add_table">
					<tr>
						<td class="lable_100"><span>*</span>汇款单位：</td>
						<td>${branch.name}</td>
						<td class="lable_100"><span>*</span>收款单位：</td>
						<td>
							${cedu.name}
						</td>
					</tr>
					<tr>
						<td class="lable_100"><span>*</span>汇款账号：</td>
						<td>
							<input name="remitterAccount" id="remitterAccount" class="txt_box_150"/>
						</td>
						<td class="lable_100"><span>*</span>收款账号：</td>
						<td>
							<input name="remitteeAccount" id="remitteeAccount" class="txt_box_150"/>
						</td>
					</tr>
					<tr>
						<td class="lable_100">姓名：</td>
						<td>
							<input name="name" id="name" class="txt_box_150"/>
						</td>
						<td class="lable_100">证件号：</td>
						<td>
							<input name="certNo" id="certNo" class="txt_box_150"/>
						</td>
					</tr>
					<tr>
						<td class="lable_100">手机/座机</td>
						<td>
							<input name="phone" id="phone" class="txt_box_150"/>
						</td>
						<td class="lable_100"></td>
						<td>
							<input type="button" class="btn_black_61" value="查询" onclick="showSearch()"/>
						</td>
					</tr>
				</table>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">待汇款的缴费单明细</th>
					</tr>
				</table>	
							<page:plugin 
								pluginCode="601"
								il8nName="finance_payment"
								searchListActionpath="list_feepaymentdetail_orderbranchcedu_ajax"
								searchCountActionpath="count_feepaymentdetail_orderbranchcedu_ajax"
								columnsStr="createdTime;paymentCode;schoolName;branchName;academyenrollbatchName;levelName;majorName;studentName;paymentSubjectName;jiaofeiValue;payBranchCedu;#status"
								customColumnValue="0,showtime(createdTime);11,showstatus(status)"
								isChecked="true"
								checkboxValue="id"	
								isPage="true"		
								isonLoad="true"					
								params="'student.name':$('#name').val(),'student.certNo':$('#certNo').val(),'student.phone':$('#phone').val(),'branchId':$('#remitterId').val(),'result.order':'createdTime','result.sort':'asc'"							
							/>
				<table class="add_table">
				  <tbody >
					<tr>
						<td align="left">
							(确认选择需要汇款的缴费单明细)
							<input type="button" class="btn_black_61" value="选择" onclick="selectfpd()"/>
							
						</td>
					</tr>
					</tbody>
				</table>
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">现需汇款的缴费单明细</th>
					</tr>
				</table>	
							<page:plugin 
								pluginCode="602"
								il8nName="finance_payment"
								searchListActionpath="list_order_branch_cedu_for_now_ajax"
								searchCountActionpath="count_order_branch_cedu_for_now_ajax"
								columnsStr="createdTime;paymentCode;schoolName;branchName;academyenrollbatchName;levelName;majorName;studentName;paymentSubjectName;moneyToPay;payBranchCedu;#status"
								customColumnValue="0,showtime(createdTime);11,showstatus(status)"
								isChecked="true"
								checkboxValue="id"	
								isPage="true"		
								isonLoad="false"					
								params="'feepdIds':$('#feepaymentIds').val(),'result.order':'createdTime','result.sort':'asc'"							
							/>
				<table class="add_table">
				  <tbody >
					<tr>
						<td align="left">
							(确认移除不需要汇款的缴费单明细)
							<input type="button" class="btn_black_61" value="移除" onclick="deletefpd()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="btn_black_130" style="text-align:center" onclick="showplaymoney()" value="汇款统计"/>
						</td>
					</tr>
					</tbody>
				</table>
				
				<div id="countdiv" style="display:none">
					<table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;" class="feehtml">现需汇款统计</th>
						</tr>
					</table>
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
			
					
				<table class="add_table">	
					<tr>
						<td class="lable_100"><span>*</span>汇款总额：</td>
						<td>
							<span id="allamount">0</span> 元
							
						</td>
					</tr>
					<tr>
						<td class="lable_100">备注：</td>
						<td><textarea cols="60" rows="6" name="notes" id="notes"></textarea></td>
					</tr>
					<tr>
						<td class="lable_100"></td>
						<td>
							<input class="btn_black_61" type="button" value="保存" onclick="showchecked()" id="submit"/>
							<input class="btn_black_61" type="button" value="关闭" onclick="window.colse();"/>
						</td>
					</tr>
				</table>
			
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
		
		<!--弹出层    确认操作-->
		<div id="message_confirm" style="display:none">
			<div align="center" >
				<b>确认移除选中的缴费单明细？</b>
			</div>
		</div>
		<div id="show_for_feepaymentdetail" style="display:none">
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					<th style="text-align:left; font-weight:bold;" class="feehtml">已选择过的缴费单明细</th>
				</tr>
			</table>				  
			<table class="gv_table_2" id="hasfpdtab">
				<thead>
					<tr>
						<th align="center">缴费单编号</th>
						<th align="center">学生姓名</th>
						<th align="center">费用科目</th>
						<th align="center">应缴金额  </th>
					</tr>
				</thead>					
				<tbody>						
				</tbody>						
			</table>
			
		</div>	
  </body>
</html>
