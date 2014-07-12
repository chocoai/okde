<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<%@ include file="../../template/common/download_excel.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>招生返款 查看返款单</title>
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
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	<!-- 分页插件 -->
	<jc:plugin name="page" />
	<script type="text/javascript">
		//导出
		function download(op)
		{
			$('#message_confirm').dialog({
				title:'确认操作',
				buttons: {
					'确认': function() { 
							$(this).dialog("close"); 
							orderId=op;
							ajax_download_zip_1();//导出
						}, 
					'取消': function() { 
							$(this).dialog("close"); 
						} 
				}
			});
			$('#message_confirm').dialog("open"); 
		}
	</script>
	<!-- 下载地址 -->
	<a:ajax 
		parameters="{orderId:orderId}" 
		successCallbackFunctions="excel_export_callback" 
		pluginCode="download_zip" 
		urls="finance/channelrebatereview/excel_export_fpd_for_channel_rebate_review_ajax"
	/>
</head>
<body>
	<head:head title="招生返款 查看返款单">
		
	</head:head>
	<body:body>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">招生返款单详细</th>
					<th></th>
				</tr>
			</table>
			
			<table class="add_table">
				<tr>
					<td class="lable_100">学习中心：</td>
					<td><s:property value="branchName"/></td>
				</tr>
				<tr>
					<td class="lable_100">合作方类型：</td>
					<td><s:property value="channelTypeName"/></td>
				</tr>
				<tr>
					<td class="lable_100">合作方：</td>
					<td><s:property value="channelName"/></td>
				</tr>
				<s:if test="#request.minorChannelNames!=''">
					<tr>
						<td class="lable_100">次合作方：</td>
						<td><s:property value="minorChannelNames"/></td>
					</tr>
				</s:if>
				<tr>
					<td class="lable_100">返款总额：</td>
					<td><span style="color:red;"><s:property value="order.amountToPay"/></span></td>
				</tr>
				<tr>
					<td class="lable_100">调整金额：</td>
					<td><span style="color:red;"><s:property value="adjustedAmount"/></span></td>
				</tr>
				<tr>
					<td class="lable_100">实返款总额：</td>
					<td><span style="color:red;"><s:property value="order.amountPaied"/></span></td>
				</tr>
				<tr>
					<td class="lable_100">返款政策类型：</td>
					<td>${order.policeStatus==1?'招生返款政策（按院校招生人数返款）':'通用返款政策（按所有招生人数返款）'}</td>
				</tr>
				<tr>
					<td class="lable_100">调整原因：</td>
					<td>
						${order.adjustReason}
					</td>
				</tr>
				<tr>
					<td class="lable_100">回退原因：</td>
					<td>
						${order.rollBackReason}
					</td>
				</tr>
			</table>
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">招生返款单统计结果</th>					
				</tr>
			</table>
			<table class="add_table">
				<tr>
					<td class="lable_100">&nbsp;</td>
					<td>${countChannelCount}</td>
				</tr>
			</table>	
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">缴费单明细&nbsp;&nbsp;(实缴总金额:<font style='color:red'><b>${countFpdAllMoney}</b></font>)</th>
					<th style="text-align:right; font-weight:bold;">
						<input type="button" class="btn_black_61"  onclick="download(${id})" value="导出"/>&nbsp;&nbsp;
					</th>
				</tr>
			</table>
						<table class="gv_table_2">
							<thead>
								<tr>
									<th align="center">缴费时间</th>
									<th align="center">缴费单号</th>	
									<th align="center">合作方</th>								
									<th align="center">院校</th>
									<th align="center">学习中心</th>
									<th align="center">批次</th>
									<th align="center">层次</th>
									<th align="center">专业</th>
									<th align="center">姓名</th>
									<th align="center">费用科目</th>
									<th align="center">实缴金额</th>
									<th align="center">返款金额</th>
									<th align="center">缴费单状态</th>
									<th align="center">招生返款状态</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="fpdList" var="fpd">
								<tr>
									<td  align="center">										
										<s:date name="#fpd.createdTime" format="yyyy-MM-dd"/>
									</td>
									<td  align="center"><s:property value="#fpd.paymentCode"/></td>	
									<td  align="center"><s:property value="#fpd.channelName"/></td>								
									<td  align="center"><s:property value="#fpd.schoolName"/></td>
									<td  align="center"><s:property value="#fpd.branchName"/></td>
									<td  align="center"><s:property value="#fpd.academyenrollbatchName"/></td>
									<td  align="center"><s:property value="#fpd.levelName"/></td>
									<td  align="center"><s:property value="#fpd.majorName"/></td>
									<td  align="center"><s:property value="#fpd.studentName"/></td>
									<td  align="center"><s:property value="#fpd.paymentSubjectName"/></td>
									<td  align="center"><s:property value="#fpd.jiaofeiValue"/></td>
									<td  align="center"><s:property value="#fpd.paymentByChannel"/></td>
									<td  align="center"><s:property value="#fpd.statusName"/></td>
									<td  align="center"><s:property value="#fpd.rebateStatusName"/></td>
								</tr>
							</s:iterator>
							</tbody>
							
						</table>
			<table class="add_table">	
				<tr class="yincang" >
					<td colspan="2" align="center"><input type="button" class="btn_black_61" value="关闭" onclick="self.close()"/></td>
				</tr>
			</table>
	</body:body>
	<!--弹出层    确认操作-->
	<div id="message_confirm" style="display:none">
		<div align="center" >
			<b>确认导出招生返款单明细么？</b>
		</div>
	</div>
</body>
</html>
