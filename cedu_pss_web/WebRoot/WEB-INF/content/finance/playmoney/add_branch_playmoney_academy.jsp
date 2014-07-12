<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>汇款院校</title>
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
						search601();
						jQuery("#allamount").html(0);
				    	jQuery("#amount").val(0);   
				    	jQuery("#countdiv").hide();
				    	jQuery("#feepaymentIds").val(""); //选中的打款单明细
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
					jQuery("#remitterAccounts").val(jQuery.trim(jQuery("#remitterAccount").val()));
					jQuery("#remitteeAccounts").val(jQuery.trim(jQuery("#remitteeAccount").val()));
					
					jQuery("#remitteeIds").val(jQuery("#remitteeId").val());
					
					//备注
					jQuery("#note").val(jQuery("#notes").val());
					ajax_110_1();//生成打款单
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
				else if(getCheckedValues601()==null || getCheckedValues601()=="")
				{
					jQuery("#showDialog").html('<b>请选择要打款的缴费单明细！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else
				{
					feePaymentDetailIds=getCheckedValues601();
					jQuery("#feepaymentIds").val(feePaymentDetailIds);
					ajax_100_1();//统计打款
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
	</script>
		<!--统计打款-->
		<a:ajax 
			pluginCode="100"
			successCallbackFunctions="ajax_showcountplaymoney" 
			parameters="{feePaymentDetailIds:feePaymentDetailIds}" 
			urls="/finance/playmoney/feepaymentdetail_count_all_branchpalymoneyacademy_ajax" 
		/>
		<!--生成打款单-->
		<a:ajax 
			pluginCode="110"
			successCallbackFunctions="ajax_addplaymoneyacdemy" 
			parameters="jQuery('#myform').serializeObject()" 
			urls="/finance/playmoney/add_branch_playmoney_academy_ajax" 			
		/>
	</head>
  
  <body>
  		
		<!-- 头开始 -->
		<head:head title="汇款院校">
			<html:a text="关闭" icon="add" onclick="window.close()" target="_self"/>	
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="myform">
				<input type="hidden" name="feepaymentIds" id="feepaymentIds" value=""/>
				<input type="hidden" name="indexcount" id="indexcount" value="0"/>
				
				<input type="hidden" name="payCeduAcademy.remitterId" id="remitterId" value="${branch.id}" />
				<input type="hidden" name="payCeduAcademy.amount" id="amount" value="0"/>
				<input type="hidden" name="payCeduAcademy.note" id="note" value=""/>
				<input type="hidden" name="payCeduAcademy.remitteeId" id="remitteeIds" value="0"/>
				<input type="hidden" name="payCeduAcademy.remitterAccount" id="remitterAccounts" value=""/>
				<input type="hidden" name="payCeduAcademy.remitteeAccount" id="remitteeAccounts" value=""/>
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
					</tr>
					<tr>
						<td class="lable_100"><span>*</span>收款单位：</td>
						<td>
							<s:if test="%{academylist!=null}">
								<s:select list="%{academylist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="remitteeId" id="remitteeId" cssClass="txt_box_150"/>
							</s:if>
			                <s:else>
			                	<select name="remitteeId" id="remitteeId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
			                </s:else>	
						</td>
					</tr>
					<tr>
						<td class="lable_100"><span>*</span>汇款账号：</td>
						<td>
							<input name="remitterAccount" id="remitterAccount" class="txt_box_150"/>
						</td>
					</tr>
					<tr>
						<td class="lable_100"><span>*</span>收款账号：</td>
						<td>
							<input name="remitteeAccount" id="remitteeAccount" class="txt_box_150"/>
						</td>
					</tr>
					<tr>
						<td class="lable_100"><span>*</span>显示缴费单：</td>
						<td>
							<page:plugin 
								pluginCode="601"
								il8nName="finance_payment"
								searchListActionpath="list_feepaymentdetail_branchpalymoneyacademy_ajax"
								searchCountActionpath="count_feepaymentdetail_branchpalymoneyacademy_ajax"
								columnsStr="createdTime;paymentCode;schoolName;branchName;academyenrollbatchName;levelName;majorName;studentName;paymentSubjectName;moneyToPay;payCeduAcademy;#status"
								customColumnValue="0,showtime(createdTime);11,showstatus(status)"
								isChecked="true"
								checkboxValue="id"	
								isPage="false"		
								isonLoad="false"					
								params="'academyId':$('#remitteeId').val(),'branchId':$('#remitterId').val(),'result.order':'createdTime','result.sort':'asc'"							
							/>
							<table class="gv_table_2">
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
						</td>
					</tr>
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
  </body>
</html>
