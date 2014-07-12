<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>院校返款 添加返款单</title>
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
		function buildAttachment(id)
		{
			return '<a target="_blank" href="<uu:url url="/finance/academybill/download_academy_bill_attachment"/>?billId='+id+'">下载附件</a>';
		}
		
		function toBillStatus(isRebate)
		{
			if(isRebate == TRUE)
			{
				return '已返款';
			}			
			return '未返款';
		}
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
	
		function billAmount(amount,id)
		{
			if(typeof(amount) != 'undefined')
			{
				return '<div id="dv'+id+'">'+(amount+"").toMoney()+'</div>';
			}	
			return '<div id="dv'+id+'">0</div>';
		}
		//列表返回事件
		function processCheckbox002(){
			processCheckbox('checkbox002',4);
		}
		//给checkbox绑定事件
		function processCheckbox(checkbox,index){
			jQuery(':checkbox[name='+checkbox+']').change(function(){
				var money=0;
				if(getCheckedValues002()!=null && getCheckedValues002()!="")
				{
					var aids=getCheckedValues002().split(",");
					for(var i=0;i<aids.length;i++)
					{
						money+= parseFloat(jQuery("#dv"+aids[i]).text());
					}
				}
				jQuery('#payacademy').text(money)
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(jQuery('#payzhuijia').text())+parseFloat(jQuery('#payacademy').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
			});
		}
		
		//保存
		var glcount=0;  //控制统计结算后是否添加
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
			else if((getCheckedValues002()==null || getCheckedValues002()=="") && jQuery("#feepdIds").val()=="")
			{
				jQuery("#showDialog").html('<b>请选择返款的缴费单明细或者应收学校款！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#newNeedFpdIds").val()=="")
			{
				jQuery("#showDialog").html('<b>缴费单返款金额为0，请重新统计！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{		
				var fpd=jQuery("#feepdIds").val().split(",");
				var nfp=jQuery("#newNeedFpdIds").val().split(",");
				var cfp=jQuery('#countAfterFpdIds').val().split(",");
				if(fpd.length!=nfp.length)
				{
					$('#message_confirm_add').dialog({
						title:'错误提示',
						buttons: {
							'确认': function() { 
									$(this).dialog("close"); 
								} 
						}
					});
					$('#message_confirm_add').dialog("open"); 
				}
				else
				{					
					if(cfp.length!=nfp.length)
					{
						glcount=1;
						countfpd();//统计结算
					}
					else
					{
						addtrue();
					}
				}		
			}
			
		}
		
		//添加方法新增
		function addtrue()
		{
			if(dealwithnumber(jQuery('#payadd').val())-0>0 && jQuery('#fenpeibool').val()==0)
			{
				jQuery("#showDialog").html('<b>请分配调整金额！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				jQuery("#planedAcademyBillIds").val(getCheckedValues002());
				jQuery("#paymentAmount").val(jQuery("#paysum").text());
				jQuery("#academyAmount").val(jQuery("#payacademy").text());
				jQuery("#adjustPaied").val(dealwithnumber(jQuery("#payadd").val()));
				jQuery("#amountPaied").val(jQuery('#payall').text());
				jQuery("#addPaied").val(jQuery('#payzhuijia').text());				
				jQuery("#notes").val(jQuery("#note").val());
				
				jQuery("#spanjfdje").text(jQuery('#paysum').text());
				jQuery("#spanzjje").text(jQuery('#payzhuijia').text());
				jQuery("#spanqtje").text(jQuery('#payacademy').text());
				jQuery("#spantzje").text(dealwithnumber(jQuery('#payadd').val()));
				jQuery("#spanfkzje").text(jQuery('#payall').text());
				jQuery('#message_confirm_add_true').dialog({
						title:'保存操作',
						buttons: {
							'确认保存': function() { 							
								ajax_140_1();//添加
								//alert("添加稍等。。。");
								jQuery(this).dialog("close"); 
							}, 
							'取消': function() { 
								jQuery(this).dialog("close"); 
							} 
						}
					});
				jQuery('#message_confirm_add_true').dialog("open"); 
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
			else if(jQuery("#batchId").val()==0)
			{
				jQuery("#showDialog").html('<b>请选择招生批次！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#remitteeId").val()==jQuery("#academyId").val())
			{
				jQuery("#obatchId").val(jQuery("#batchId").val());
				glcount=0;
				search001();			
			}
			else
			{
				glcount=0;
				jQuery("#academyId").val(jQuery("#remitteeId").val());
				jQuery("#obatchId").val(jQuery("#batchId").val());
				jQuery("#feepdIds").val("");
				jQuery("#newNeedFpdIds").val("");
				search001();
				search002();
				jQuery('#rebateContent').show();	
				jQuery('#paysum').text("0");
				jQuery('#payzhuijia').text("0");
				jQuery('#payall').text("0");
				jQuery('#payadd').val("");
				jQuery('#payacademy').text("0");
				jQuery("#indexcount").val(0);
				search003();
				
			}
		}
		
		//统计结算
		function countfpd()
		{
			jQuery('#countAfterFpdIds').val(jQuery('#feepdIds').val());//保持每次统计时有个参照
			ajax_150_1();//统计结算
		}
		 //处理输入的钱是否正确（调整金额）
		function showcheckmoney()
		{
			jQuery('#fenpeibool').val(0);//控制调整金额重新分配
			if(dealwithnumber(jQuery("#payadd").val())=="--")
			{
				jQuery("#payadd").val("");
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(jQuery('#payzhuijia').text())+parseFloat(jQuery('#payacademy').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
				jQuery("#showDialog").html('<b>输入格式不正确，只能输入整数和小数！</b>');
				jQuery('#message_returns_tips').dialog("open");
				
			}
			else if((parseFloat(jQuery('#paysum').text())+parseFloat(jQuery('#payzhuijia').text())+parseFloat(jQuery('#payacademy').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())))-0<0)
			{
				jQuery("#payadd").val("");
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(jQuery('#payzhuijia').text())+parseFloat(jQuery('#payacademy').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
				jQuery("#showDialog").html('<b>返款总金额不能为负数！</b>');
				jQuery('#message_returns_tips').dialog("open");
			}
			else
			{
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(jQuery('#payzhuijia').text())+parseFloat(jQuery('#payacademy').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
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
		
		//选择需要返款的待缴费单  获取缴费单明细ids集合
		function selectfpd()
		{
			if(getCheckedValues001()==null || getCheckedValues001()=="")
			{
				jQuery("#showDialog").html('<b>请选择要返款的缴费单明细！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else
			{
				ajax_120_1();//判断选择	
			}
		}
		//移除不需要返款的待缴费单  获取缴费单明细ids集合
		function deletefpd()
		{
			//alert(getCheckedValues003());
			if(getCheckedValues003()==null || getCheckedValues003()=="")
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
		
		//给学习中心分配调整金额
		function feipeibranch()
		{
			if(dealwithnumber(jQuery('#payadd').val())-0==0)
			{
				jQuery("#showDialog").html('<b>没有调整金额，不需分配！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
			else if(jQuery("#newNeedFpdIds").val()=="")
			{
				jQuery("#showDialog").html('<b>缴费单返款金额为0，请重新统计，再分配调整金额！</b>');
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
			
			$('#checkbox001').change(function(){
				$(':checkbox[name=checkbox001]').change();
			});
			
			$('#checkbox002').change(function(){
				$(':checkbox[name=checkbox002]').change();
			});
			
			//院校相关级联
			jQuery('#remitteeId').change(function(){
				ajax_110_1();//招生批次	
									
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
			//$('#myform').get(0).reset();
		});
	
		//ajax回调函数  招生批次(院校、全局批次)
		function ajax_batch(data)
		{				
			$('#batchId').empty();
			$('#batchId').append('<option value="0">--请选择--</option>');	
			if(data.batchlist!=null&&data.batchlist.length>0)
			{
				$.each(data.batchlist,function(){	
					$('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				});
			}	
		}
	
		//ajax回调函数  选取缴费单明细时的验证
		function ajax_test_fpd(data)
		{				
			if(data.isback)
			{
				jQuery("#feepdIds").val(data.newFeepdIds);	
				search003();
				search001();
			}
			else
			{
				jQuery("#showDialog").html('<b>选择失败，请重新选择！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}		
		}
		//ajax回调函数  移除缴费单明细
		function ajax_del_fpd(data)
		{
			if(data.isfail)
			{
				jQuery("#feepdIds").val(data.newdelFeepdIds);			
				search003();
				search001();
				if(data.newDelNeedFeepdIds!=null && jQuery("#newNeedFpdIds").val()!="")
				{					
					var needf=(data.newDelNeedFeepdIds).split(",");
					var oldf=jQuery("#newNeedFpdIds").val().split(",");
					if(needf.length!=oldf.length)
					{
						jQuery('#fenpeibool').val(0);//控制调整金额重新分配
						countfpd();//统计结算
					}
				}
			}
			else
			{
				jQuery("#showDialog").html('<b>移除失败，请重新移除</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
		
		//添加院校返款
		function ajax_pac(data)
		{		
			if(data.isback)
			{
				jQuery("#indexcount").val(1);
				glcount=0;
				jQuery("#showDialog").html('<b>添加成功！</b>');
				jQuery('#message_returns_tips').dialog('open');			
				
			}
			else
			{
				jQuery("#showDialog").html('<b>添加失败！</b>');
				jQuery('#message_returns_tips').dialog('open');
			}
		}
		//统计返款金额
		function ajax_count(data)
		{
			if(data.isback)
			{
				jQuery('#newNeedFpdIds').val(data.newNeedFpdIds);
				jQuery('#paysum').text(parseFloat(data.allMoney));
				jQuery('#payzhuijia').text(parseFloat(data.addMoney));
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(jQuery('#payzhuijia').text())+parseFloat(jQuery('#payacademy').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
				search003();
				if(glcount==1)
				{
					addtrue();
				}
			}
			else
			{
				jQuery('#newNeedFpdIds').val(data.newNeedFpdIds);
				jQuery('#paysum').text(parseFloat(data.allMoney));
				jQuery('#payzhuijia').text(parseFloat(data.addMoney));
				jQuery('#payall').text(parseFloat(jQuery('#paysum').text())+parseFloat(jQuery('#payzhuijia').text())+parseFloat(jQuery('#payacademy').text())+parseFloat(dealwithnumber(jQuery("#payadd").val())));
				
				//有政策人数太少的情况
				jQuery('#hasfpdtab > tbody').empty();
			   	var haslist='';
				if(data.noPeoplePoliceFpdList!=null && data.noPeoplePoliceFpdList.length>0)
				{
					jQuery.each(data.noPeoplePoliceFpdList,function(){	
						haslist+='<tr>';						
						haslist+='<td align="center">'+this.studentName+'</td>';
						haslist+='<td align="center">'+this.branchName+'</td>';
						haslist+='<td align="center">'+this.schoolName+'</td>';
						haslist+='<td align="center">'+this.academyenrollbatchName+'</td>';
						haslist+='<td align="center">'+this.levelName+'</td>';
						//haslist+='<td align="center">'+this.majorName+'</td>';
						haslist+='</tr>';
					});
				}
				else
				{
					haslist+='<tr><td align="center" colspan="5">无相关数据！</td></tr>';
				}
				jQuery('#hasfpdtab > tbody').html(haslist);
				
				//无相关政策的数据
				jQuery('#nopolicefpdtab > tbody').empty();
			   	var noplist='';
				if(data.notPoliceFpdList!=null && data.notPoliceFpdList.length>0)
				{
					jQuery.each(data.notPoliceFpdList,function(){	
						noplist+='<tr>';
						noplist+='<td align="center">'+this.studentName+'</td>';
						noplist+='<td align="center">'+this.branchName+'</td>';
						noplist+='<td align="center">'+this.schoolName+'</td>';
						noplist+='<td align="center">'+this.academyenrollbatchName+'</td>';
						noplist+='<td align="center">'+this.levelName+'</td>';
						//noplist+='<td align="center">'+this.majorName+'</td>';
						noplist+='</tr>';
					});
				}
				else
				{
					noplist+='<tr><td align="center" colspan="5">无相关数据！</td></tr>';
				}
				jQuery('#nopolicefpdtab > tbody').html(noplist);
				search003();
				jQuery('#show_for_feepaymentdetail').dialog('open');
			}		
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
	<!--招生批次(院校)-->
	<a:ajax 
		successCallbackFunctions="ajax_batch" 
		parameters="{academyId:jQuery('#remitteeId').val()}" 
		urls="/enrollment/cascade_academy_batch_all_ajax" 
		pluginCode="110"
	/>
	
	<!--选取缴费单明细时的验证-->
	<a:ajax 
		successCallbackFunctions="ajax_test_fpd" 
		parameters="{oldFeepdIds:jQuery('#feepdIds').val(),addFeepdIds:getCheckedValues001()}" 
		urls="/finance/academyrebatereview/test_fpd_academy_rebate_review_need_ajax" 
		pluginCode="120"
	/>
	
	<!--移除缴费单明细-->
	<a:ajax 
		successCallbackFunctions="ajax_del_fpd" 
		parameters="{allFeepdIds:jQuery('#feepdIds').val(),delFeepdIds:getCheckedValues003(),allNeedFpdIds:jQuery('#newNeedFpdIds').val()}" 
		urls="/finance/academyrebatereview/del_fpd_academy_rebate_review_need_ajax" 
		pluginCode="130"
	/>
	
	<!--添加院校返款-->
	<a:ajax 
		successCallbackFunctions="ajax_pac" 
		parameters="jQuery('#myform').serializeObject()" 
		urls="/finance/academyrebatereview/add_academy_rebate_review_ajax" 
		pluginCode="140"
	/>
	
	<!--统计返款金额-->
	<a:ajax 
		successCallbackFunctions="ajax_count" 
		parameters="{feepdIds:jQuery('#feepdIds').val()}" 
		urls="/finance/academyrebatereview/count_academy_rebate_review_fpd_for_need_all_money_ajax" 
		pluginCode="150"
	/>
	
	<!--根据缴费单Ids统计其所在的学习中心-->
	<a:ajax 
		successCallbackFunctions="ajax_branch" 
		parameters="{fpdIds:jQuery('#newNeedFpdIds').val()}" 
		urls="/finance/academyrebatereview/find_list_branch_by_fpdIds_ajax" 
		pluginCode="160"
	/>
		
</head>
<body>
	<head:head title="新增院校返款单">
		<html:a text="关闭" icon="add" onclick="window.close()"/>
	</head:head>
	<body:body>
		<form id="myform" method="post">
		
			<input type="hidden" name="feepdIds" id="feepdIds" value=""/>
			<input type="hidden" name="newNeedFpdIds" id="newNeedFpdIds" value=""/>
			<input type="hidden" name="countAfterFpdIds" id="countAfterFpdIds" value=""/>
			<!--学习中心分配调整金额-->
			<input type="hidden" name="branchtzmoney" id="branchtzmoney" value=""/>
			
			<input type="hidden" id="planedAcademyBillIds" name="planedAcademyBillIds" value=""/>
			<input type="hidden" id="paymentAmount" name="payAcademyCedu.paymentAmount" value="0"/>
			<input type="hidden" id="academyAmount" name="payAcademyCedu.academyAmount" value="0"/>
			<input type="hidden" id="adjustPaied" name="payAcademyCedu.adjustPaied" value="0"/>
			<input type="hidden" id="amountPaied" name="payAcademyCedu.amountPaied" value="0"/>
			<input type="hidden" id="addPaied" name="payAcademyCedu.addPaied" value="0"/>
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
					<td class="lable_100">收款单位：</td>
					<td>
						<s:property value="cedu.name"/>
					</td>
					<td class="lable_100"><span>*</span>返款院校：</td>
					<td>
						<s:select list="academies" listKey="id" listValue="name"
						headerKey="0" headerValue="--请选择--"
						id="remitteeId" name="remitteeId" cssClass="txt_box_150"></s:select>
						<input type="hidden" name="payAcademyCedu.remitterId" id="academyId" value="0"/>
					</td>
					<td class="lable_100"><span>*</span>招生批次：</td>
					<td>
						<select name="batchId" id="batchId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>	
						<input type="hidden" name="obatchId" id="obatchId" value="0"/>
					</td>
				</tr>
				<tr>	
					<td class="lable_100">学习中心：</td>
					<td>
						<s:if test="%{branchlist!=null}">
							<s:select list="%{branchlist}" headerKey="0" headerValue="--请选择--" listKey="id" theme="simple" listValue="name" name="branchId" id="branchId" cssClass="txt_box_150"/>
						</s:if>
						<s:else>
						<select name="branchId" id="branchId" class="txt_box_150">
							<option value="0">--请选择--</option>
						</select>
						</s:else>			
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
					</td>
					<td class="lable_100"></td>
					<td>
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
					<th style="text-align:left; font-weight:bold;">待返款缴费单明细</th>
					<th></th>
				</tr>
			</table>
			 
					<page:plugin 
						pluginCode="001"
						il8nName="finance_payment"
						subStringLength="20"
						searchListActionpath="list_academy_rebate_review_fpd_show_ajax"
						searchCountActionpath="count_academy_rebate_review_fpd_show_ajax"
						columnsStr="createdTime;paymentCode;studentName;schoolName;branchName;academyenrollbatchName;levelName;majorName;paymentSubjectName;jiaofeiValue;#status"
						customColumnValue="9,feePaymentValue(jiaofeiValue);10,showstatus(status)"
						isPage="true"
						isChecked="true"
						checkboxValue="id"
						params="'student.academyId':jQuery('#academyId').val(),'student.enrollmentBatchId':jQuery('#obatchId').val(),'student.branchId':jQuery('#branchId').val(),feepdIds:jQuery('#feepdIds').val(),'feePaymentDetail.feeSubjectId':jQuery('#feeSubjectId').val()"
						isonLoad="false"
						isPackage="true"
						isOrder="false"
					/>
				 
			<table class="add_table">
			  <tbody >
				<tr>
					<td align="left">
						(选择需要返款的缴费单明细)
						<input type="button" class="btn_black_61" value="选择" onclick="selectfpd()"/>
						
					</td>
				</tr>
				</tbody>
			</table>
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">本次返款缴费单明细</th>
					<th></th>
				</tr>
			</table>
					<page:plugin 
						pluginCode="003"
						il8nName="finance_payment"
						subStringLength="20"
						searchListActionpath="list_academy_rebate_review_fpd_need_ajax"
						searchCountActionpath="count_academy_rebate_review_fpd_need_ajax"
						columnsStr="createdTime;paymentCode;studentName;schoolName;branchName;academyenrollbatchName;levelName;majorName;paymentSubjectName;jiaofeiValue;payAcademyCedu;#status"
						customColumnValue="9,feePaymentValue(jiaofeiValue);10,feePaymentValue(payAcademyCedu);11,showstatus(status)"
						isPage="true"
						isChecked="true"
						checkboxValue="id"
						params="'feepdIds':$('#feepdIds').val()"
						isonLoad="true"
						isPackage="true"
						isOrder="false"
					/>
				<table class="add_table">
				  <tbody >
					<tr>
						<td align="left">
							(移除不需要返款的缴费单明细)
							<input type="button" class="btn_black_61" value="移除" onclick="deletefpd()"/>							
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input style="color:#FFFF66;font-weight:bold;" type="button" class="btn_black_61" value="统计结算" onclick="countfpd()"/>
							<span>(备注：请点击“统计结算”按钮，统计本次缴费单返款金额)</span>
						</td>
					</tr>
					</tbody>
				</table>
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">其他应收学校款</th>
					<th></th>
				</tr>
			</table>
				<page:plugin
						pluginCode="002"
						il8nName="finance"
						subStringLength="20"
						searchListActionpath="/finance/academybill/list_academy_bill_data"
						columnsStr="academyName;receivedWayName;#billamount;note;#status;#attachment"
						customColumnValue="2,billAmount(amount,id);4,toBillStatus(isRebate);5,buildAttachment(id)"
						params="academyId:$('#academyId').val(),status:PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO"
						listCallback="processCheckbox"
						isChecked="true"
						isPage="false"
						isonLoad="false"
						isPackage="false"
						isOrder="false"
					/>
			<table class="add_table">
			 <tbody>
			 	<tr>
					<td class="lable_100">缴费单返款金额：</td>
					<td>
						<span id="paysum">0</span>
					</td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td class="lable_100">追加金额：</td>
					<td>
						<span id="payzhuijia">0</span>
					</td>
					<td colspan="6"></td>
				</tr>
				<tr>
					<td class="lable_100">其他应收金额：</td>
					<td>
						<span id="payacademy">0</span>
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
	<!--弹出层    确认操作-->
	<div id="message_confirm" style="display:none">
		<div align="center" >
			<b>确认移除选中的缴费单明细？</b><br/>(移除已经统计返款的需要重新分配调整金额)
		</div>
	</div>
	<!--弹出层    确认操作-->
	<div id="message_confirm_add" style="display:none">
		<div align="left" >
			<b>请再次点击“统计结算”按钮，再移除院校返款金额为0的缴费单！</b>
		</div>
	</div>
	<!--弹出层    确认操作-->
	<div id="message_confirm_add_true" style="display:none">
		<div align="left" >
			缴费单返款金额：<span id="spanjfdje">0</span><br/>
			追加金额：<span id="spanzjje">0</span><br/>
			其他应收金额：<span id="spanqtje">0</span><br/>
			调整金额：<span id="spantzje">0</span><br/>
			返款总金额：<span id="spanfkzje">0</span><br/>
			<b>是否确认保存？</b>
		</div>
	</div>
	<div id="show_for_feepaymentdetail" style="display:none">
		<table class="gv_table_2">
			<tbody>
				<tr>
					<td style="width: 50%;" valign="top">
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
								<th style="text-align:left; font-weight:bold;" class="feehtml">未设置院校返款政策</th>
							</tr>
						</table>				  
						<table class="gv_table_2" id="nopolicefpdtab">
							<thead>
								<tr>
									<th align="center">学生姓名</th>
									<th align="center">学习中心</th>
									<th align="center">院校 </th>
									<th align="center">招生批次 </th>
									<th align="center">层次 </th>
									
								</tr>
							</thead>					
							<tbody>	
											
							</tbody>						
						</table>
					</td>
					<td style="width: 50%;" valign="top">
						<table class="gv_table_2">
							<tr>
								<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
								<th style="text-align:left; font-weight:bold;" class="feehtml">不满足院校返款标准</th>
							</tr>
						</table>				  
						<table class="gv_table_2" id="hasfpdtab">
							<thead>
								<tr>
									<th align="center">学生姓名</th>
									<th align="center">学习中心</th>
									<th align="center">院校 </th>
									<th align="center">招生批次 </th>
									<th align="center">层次 </th>
									
								</tr>
							</thead>					
							<tbody>						
							</tbody>						
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2"><span style="color:red">注：未设置院校返款政策的学生无法参与返款</span></td>
				</tr>
			</tbody>
		</table>
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
