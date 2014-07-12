<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>新增院校年度返款</title>
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
	
	<script type="text/javascript">
		//显示缴费单状态
		function showstatus(status)
		{
			return status.getPaymentStatus();
	 	}
	 	//显示金额
		function feePaymentValue(feePayment)
		{
			return (feePayment+"").toMoney();
		}
		//显示年份
		function showyear()
		{
			var Today = new Date("<%=new Date()%>");  //new Date();  	
			var tY = Today.getFullYear(); 
			var tmin=parseInt(tY)-5;
			var tmax=parseInt(tY)+5;
			jQuery('#year').empty();
			jQuery('#year').append('<option value="0">--请选择--</option>');
			for(var i=tmax;i>=tmin;i--)
			{
				if(i==parseInt(tY))
				{
					jQuery('#year').append('<option value="'+i+'">'+i+'</option>');
				}
				else
				{
					jQuery('#year').append('<option value="'+i+'">'+i+'</option>');
				}
			} 	
		} 
		function doSave()
		{
			if(jQuery("#indexcount").val()==1)
			{
				jQuery("#showDialog").html('<b>已添加过，请选择院校重新查询！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#academyId").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择返款院校！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#yearId").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择返款年份！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#payzhuijia").text()-0<=0)
			{
				jQuery("#showDialog").html('<b>追加金额为0，不需要添加！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(dealwithnumber(jQuery('#payadd').val())-0>0 && jQuery('#fenpeibool').val()==0)
			{
				jQuery("#showDialog").html('<b>请分配调整金额！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{		
				jQuery("#adjustPaied").val(dealwithnumber(jQuery("#payadd").val()));
				jQuery("#amountPaied").val(jQuery('#payall').text());
				jQuery("#addPaied").val(jQuery('#payzhuijia').text());				
				jQuery("#notes").val(jQuery("#note").val());
				ajax_140_1();//添加
			}
			
		}
		
		//查询
		function findfeepayment()
		{
			if(jQuery("#remitteeId").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择返款院校！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#year").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择年份！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#remitteeId").val()==jQuery("#academyId").val() && jQuery("#year").val()==jQuery("#yearId").val() && jQuery('#sfeeSubjectId').val()==jQuery('#feeSubjectId').val())
			{
				search001();			
			}
			else
			{
				jQuery("#academyId").val(jQuery("#remitteeId").val());
				jQuery("#yearId").val(jQuery("#year").val());
				jQuery('#sfeeSubjectId').val(jQuery('#feeSubjectId').val());
				jQuery('#fenpeibool').val(0);//控制调整金额重新分配
				jQuery("#payadd").val("");//追加金额置为空
				ajax_150_1();//统计
				jQuery('#rebateContent').show();	
				jQuery("#indexcount").val(0);	
			}
		}
		
		 //处理输入的钱是否正确（调整金额）
		function showcheckmoney()
		{
			jQuery('#fenpeibool').val(0);//控制调整金额重新分配
			if(dealwithnumber(jQuery("#payadd").val())=="--")
			{
				jQuery("#payadd").val("");
				jQuery('#payall').text(parseFloat(jQuery('#payzhuijia').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
				jQuery("#showDialog").html('<b>输入格式不正确，只能输入整数和小数！</b>');
				jQuery('#message_returns_tips').dialog("open");
				
			}
			else if((+parseFloat(jQuery('#payzhuijia').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())))-0<0)
			{
				jQuery("#payadd").val("");
				jQuery('#payall').text(parseFloat(jQuery('#payzhuijia').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
				jQuery("#showDialog").html('<b>返款总金额不能为负数！</b>');
				jQuery('#message_returns_tips').dialog("open");
			}
			else
			{
				jQuery('#payall').text(parseFloat(jQuery('#payzhuijia').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
			}
		}
		//处理输入的钱是否正确（调整金额分配给学习中心）
		function checkfenpeimoney(op)
		{
			if(dealwithnumber(jQuery("#fpbt"+op).val())=="--")
			{
				jQuery("#fpbt"+op).val("");
				jQuery("#showDialog").html('<b>输入格式不正确，只能输入整数和小数！</b>');
				jQuery('#message_returns_tips').dialog("open");
				
			}
		}
			
		//给学习中心分配调整金额
		function feipeibranch()
		{
			if(dealwithnumber(jQuery('#payadd').val())-0==0)
			{
				jQuery("#showDialog").html('<b>没有调整金额，不需分配！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery('#fenpeibool').val()==1)
			{
				jQuery('#feipei_branch_div').dialog("open"); 
			}
			else
			{
				//通过缴费单查询Id学习中心再分配
				ajax_160_1();//
			}
		}
		
		jQuery(function(){
		
			//showyear();//显示年份
					
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
			
			//给学习中心分配调整金额
			jQuery('#feipei_branch_div').dialog({
				autoOpen:false,
				modal:true,
				draggable:false,
				resizable:false,
				title:'分配调整金额',
				buttons: {
					'确定': function() { 
						if(branchFpIds!="")
						{
							var allfpbmoney=0.0;
							var bfpid=branchFpIds.split(",");
							var branchhejipj="";
							for(var i=0;i<bfpid.length;i++)
							{
								if(dealwithnumber(jQuery("#fpbt"+bfpid[i]).val())-0!=0)
								{
									allfpbmoney=parseFloat(allfpbmoney)+parseFloat(dealwithnumber(jQuery("#fpbt"+bfpid[i]).val()))
									branchhejipj+=","+bfpid[i]+"#"+dealwithnumber(jQuery("#fpbt"+bfpid[i]).val());
								}
							}
							if(allfpbmoney-0-dealwithnumber(jQuery("#payadd").val())!=0)
							{
								jQuery("#showDialog").html('<b>分配的钱必须等于调整金额:'+dealwithnumber(jQuery("#payadd").val())+'！</b>');
								jQuery('#message_returns_tips').dialog("open");
							}
							else
							{
								if(branchhejipj!="" && branchhejipj.length>0)
								{
									jQuery("#branchtzmoney").val(branchhejipj.substring(1));
								}
								jQuery('#fenpeibool').val(1);//控制调整金额重新分配
								jQuery(this).dialog("close");
							}
						}
						else
						{
							jQuery(this).dialog("close");
						}			 
					}, 
					'关闭': function() { 
						jQuery(this).dialog("close"); 
					} 
				}
			});			
		});

		//添加院校返款
		function ajax_pac(data)
		{		
			if(data.isfail)
			{
				jQuery("#showDialog").html('<b>该年度已追加返款，不能重复追加！</b>');
				jQuery('#message_returns_tips').dialog('open');	
			}
			else if(data.isback)
			{
				jQuery("#indexcount").val(1);
				jQuery("#showDialog").html('<b>添加成功！</b>');
				jQuery('#message_returns_tips').dialog('open');			
				
			}
			else
			{
				jQuery("#showDialog").html('<b>添加失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
		
		//统计院校返款
		function ajax_count(data)
		{	
			jQuery('#payzhuijia').text(data.allAddMoney)
			jQuery('#payall').text(parseFloat(jQuery('#payzhuijia').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
			search001();
		}

		//ajax回调函数 根据缴费单Ids统计其所在的学习中心
		var branchFpIds="";//预读学习中心Ids
		function ajax_branch(data)
		{				
			jQuery('#branchtab > tbody').empty();
			var list="";
			var index=0;
			branchFpIds="";
			if(data.branchList!=null&&data.branchList.length>0)
			{
				
				jQuery.each(data.branchList,function(){	
					 index++;
					 list+='<tr>';					 
					 list+='<td align="center">'+index+'</td>';
					 list+='<td align="center">'+this.name+'</td>';
					 list+='<td align="center"><input type="text" name="fpbt'+this.id+'" id="fpbt'+this.id+'" onkeyup="javascript:checkfenpeimoney('+this.id+')" class="txt_box_100"/></td>';
					 list+='</tr>';
					 if(branchFpIds=="")
					 {
					 	branchFpIds=this.id;
					 }
					 else
					 {
					 	branchFpIds+=","+this.id;
					 }
				});
			}
			else
			{
				list+='<tr><td colspan="3">无相关数据</td></tr>';
			}
			jQuery('#branchtab > tbody').html(list);
			jQuery('#feipei_branch_div').dialog("open"); 	
		}
	</script>	
	
	<!--新增院校年度返款-->
	<a:ajax 
		successCallbackFunctions="ajax_pac" 
		parameters="jQuery('#myform').serializeObject()" 
		urls="/finance/academyrebatereview/add_academy_year_rebate_review_ajax" 
		pluginCode="140"
	/>	
	
	<!--统计院校年度返款-->
	<a:ajax 
		successCallbackFunctions="ajax_count" 
		parameters="{'student.academyId':jQuery('#academyId').val(),'year':jQuery('#yearId').val(),'feePaymentDetail.feeSubjectId':jQuery('#sfeeSubjectId').val()}" 
		urls="/finance/academyrebatereview/count_add_money_for_academy_year_rebate_ajax" 
		pluginCode="150"
	/>	
	
	<!--根据缴费单Ids统计其所在的学习中心-->
	<a:ajax 
		successCallbackFunctions="ajax_branch" 
		parameters="{'student.academyId':jQuery('#academyId').val(),'year':jQuery('#yearId').val(),'feePaymentDetail.feeSubjectId':jQuery('#sfeeSubjectId').val()}" 
		urls="/finance/academyrebatereview/find_list_branch_by_year_fpdIds_ajax" 
		pluginCode="160"
	/>
		
</head>
<body>
	<head:head title="新增院校年度返款">
		<html:a text="关闭" icon="add" onclick="window.close()"/>
	</head:head>
	<body:body>
		<form id="myform" method="post">
			<!--学习中心分配调整金额-->
			<input type="hidden" name="branchtzmoney" id="branchtzmoney" value=""/>
			
			<input type="hidden" id="addPaied" name="payAcademyCedu.addPaied" value="0"/>
			<input type="hidden" id="adjustPaied" name="payAcademyCedu.adjustPaied" value="0"/>
			<input type="hidden" id="amountPaied" name="payAcademyCedu.amountPaied" value="0"/>
			<input type="hidden" id="notes" name="payAcademyCedu.note" value=""/>
			<input type="hidden" name="indexcount" id="indexcount" value="0"/>
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">缴费单明细查询</th>
					<th></th>
				</tr>
			</table>
					
			<table class="add_table">
			  <tbody id="rebateCondition">
				<tr>					
					<td class="lable_100"><span>*</span>返款院校：</td>
					<td align="left">
						<s:select list="academies" listKey="id" listValue="name"
						headerKey="0" headerValue="--请选择--"
						id="remitteeId" name="remitteeId" cssClass="txt_box_150"></s:select>
						<input type="hidden" name="payAcademyCedu.remitterId" id="academyId" value="0"/>
					</td>
					<td class="lable_100"><span>*</span>年份：</td>
					<td align="left">
						<s:if test="%{geblist!=null}">
							<s:select list="%{geblist}" headerKey="0" headerValue="--请选择--" listKey="belongYear" theme="simple" listValue="belongYear" name="year" id="year" cssClass="txt_box_150"/>
						</s:if>
						<s:else>
							<select name="year" id="year" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>	
						</s:else>	
						<input type="hidden" name="yearId" id="yearId" value="0"/>
					</td>
					<td class="lable_100">费用科目:</td>
					<td align="left">
						<s:if test="%{feesubjectlist!=null}">
							<s:select list="%{feesubjectlist}" headerKey="0" headerValue="--请选择--" listKey="id" theme="simple" listValue="name" name="feeSubjectId" id="feeSubjectId" cssClass="txt_box_150"/>
						</s:if>
						<s:else>
							<select name="feeSubjectId" id="feeSubjectId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</s:else>
						<input type="hidden" name="sfeeSubjectId" id="sfeeSubjectId" value="0"/>		
					</td>
				</tr>
				<tr>	
					<td class="lable_100">收款单位：</td>
					<td align="left">
						<s:property value="cedu.name"/>
					</td>	
					<td></td>
					<td></td>
					<td class="lable_100" ></td>
					<td align="left">
						<input type="button" onclick="findfeepayment()" class="btn_black_61" value="查询"/>	
					</td>
				</tr>
			  </tbody>
			 </table>
			</form> 
			<div id="rebateContent" style="display:none;" >
			 <table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">年度返款缴费单明细</th>
					<th></th>
				</tr>
			</table>
			 
					<page:plugin 
						pluginCode="001"
						il8nName="finance_payment"
						subStringLength="20"
						searchListActionpath="list_academy_year_rebate_review_fpd_ajax"
						searchCountActionpath="count_academy_year_rebate_review_fpd_ajax"
						columnsStr="createdTime;paymentCode;studentName;schoolName;branchName;academyenrollbatchName;levelName;paymentSubjectName;jiaofeiValue;moneyToCedu;academyYearRebateAddMoney;#status"
						customColumnValue="8,feePaymentValue(jiaofeiValue);9,feePaymentValue(moneyToCedu);10,feePaymentValue(academyYearRebateAddMoney);11,showstatus(status)"
						isPage="true"
						params="'student.academyId':jQuery('#academyId').val(),'year':jQuery('#yearId').val(),'feePaymentDetail.feeSubjectId':jQuery('#sfeeSubjectId').val()"
						isonLoad="false"
						isPackage="true"
						isOrder="false"
					/>
			<table class="add_table">
			 <tbody>
				<tr>
					<td class="lable_100">追加金额：</td>
					<td>
						<span id="payzhuijia">0</span>
					</td>
					<td colspan="6"></td>
				</tr>
			 	<tr>
					<td class="lable_100">调整金额：</td>
					<td>
						<input type="text" name="payadd" id="payadd" onkeyup="javascript:showcheckmoney()" class="txt_box_100"/>						
						&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:feipeibranch();">分配</a>
						<input type="hidden" name="fenpeibool" id="fenpeibool" value="0"/>
					</td>
					<td colspan="6"></td>
				</tr> 
				<tr>
					<td class="lable_100">返款总金额：</td>
					<td>
						<span id="payall">0</span>
					</td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td class="lable_100">备注：</td>
					<td colspan="7"><textarea class="txt_box_460" cols="60" rows="6" id="note" style="height:80px"></textarea></td>
				</tr>
				<tr>
					<td colspan="8" align="center"><input type="button" class="btn_black_61" value="保存" onclick="doSave()"/>&nbsp;&nbsp;<input type="button" class="btn_black_61" onclick="javascript:window.close();" value="关闭"/></td>
				</tr>
			  </tbody>
			</table>
		</div>	
	</body:body>
	<!--弹出层-->
	<div id="message_returns_tips" style="display:none">
		<div align="center" id="showDialog">
			
		</div>
	</div>
	
	<!--弹出层    给中心分配调整金额-->
	<div id="feipei_branch_div" style="display:none">
		<table class="gv_table_2" id="branchtab">
			<thead>
				<tr>
					<th align="center">序号</th>
			  		<th align="center">学习中心</th>
			  		<th align="center">分配金额</th>
				</tr>
			</thead>					
			<tbody>	
								
			</tbody>						
		</table>
	</div>
</body>
</html>
