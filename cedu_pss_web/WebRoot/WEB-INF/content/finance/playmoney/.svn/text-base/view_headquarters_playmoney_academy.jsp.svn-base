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
				
				
			});
			
			//显示缴费单状态
			function showstatus(status)
			{
				return status.getPaymentStatus();
			}
			
	</script>
		
	</head>
  
  <body>
  		
		<!-- 头开始 -->
		<head:head title="院校打款(总部)">
			<html:a text="关闭" icon="add" onclick="window.close()" target="_self"/>	
		</head:head>
		<!--主体层开始 -->
		<body:body>
			<form id="myform">
				<table class="gv_table_2">
			  		<tr>
					 	<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
					 	<th style="text-align:left; font-weight:bold;" class="feehtml">查看打款单</th>
					</tr>
				</table>
			</form>	
				<table class="add_table">
					<tr>
						<td class="lable_100">汇款单位：</td>
						<td>${payCeduAcademy.remitterName}</td>
					</tr>
					<tr>
						<td class="lable_100">收款单位：</td>
						<td>
							${payCeduAcademy.remitteeName}
						</td>
					</tr>
					<tr>
						<td class="lable_100">汇款账号：</td>
						<td>
							${payCeduAcademy.remitterAccount}
						</td>
					</tr>
					<tr>
						<td class="lable_100">收款账号：</td>
						<td>
							${payCeduAcademy.remitteeAccount}
						</td>
					</tr>
					<tr>
						<td class="lable_100">缴费单：</td>
						<td>
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
									<s:iterator id="item" status="status" value="feepdlist" >
										<tr>
											<td align="center">
												<s:property value="#status.count"/>
											</td>
											<td align="center">
												${item.schoolName}
											</td>
											<td align="center">
												${item.branchName}
											</td>
											<td align="center">
												${item.academyenrollbatchName}
											</td>
											<td align="center">
												${item.paymentSubjectName}
											</td>
											<td align="center">
												${item.payCeduAcademy}
											</td>
										</tr>											
									</s:iterator>
								</tbody>
							</table>
							<table class="gv_table_2">
								<tr>
									<td align="left">
										<input type="button" class="btn_black_130" style="text-align:center" onclick="javascript:jQuery('#countdiv').show();" value="查看明细"/>
									</td>
								</tr>
							</table>
							<div id="countdiv" style="display:none">
								<table class="gv_table_2" id="showcounttab">
									<thead>
										<tr>
											<th align="center">院校</th>
											<th align="center">学习中心</th>
											<th align="center">批次</th>
											<th align="center">层次</th>
											<th align="center">专业</th>
											<th align="center">姓名</th>
											<th align="center">费用科目</th>
											<th align="center">缴费金额</th>
											<th align="center">打款金额</th>
											<th align="center">状态</th>
										</tr>
									</thead>	
									<tbody>
										<s:iterator id="item" status="status" value="feePaymentDetailList" >
											<tr>
												<td align="center">
													${item.schoolName}
												</td>
												<td align="center">
													${item.branchName}
												</td>
												<td align="center">
													${item.academyenrollbatchName}
												</td>
												<td align="center">
													${item.levelName}
												</td>
												<td align="center">
													${item.majorName}
												</td>
												<td align="center">
													${item.studentName}
												</td>
												<td align="center">
													${item.paymentSubjectName}
												</td>
												<td align="center">
													${item.moneyToPay}
												</td>
												<td align="center">
													${item.payCeduAcademy}
												</td>
												<td align="center">
													${item.statusName}
												</td>
											</tr>	
										</s:iterator>			
									</tbody>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td class="lable_100">汇款总额：</td>
						<td>
							<span id="allamount">${payCeduAcademy.amount}</span> 元
							
						</td>
					</tr>
					<!-- tr>
						  <td class="lable_100">票据：</td>
						  <td>
							<s:if test="@org.apache.commons.lang.StringUtils@isNotBlank(payCeduAcademy.uploadedBillPath)"><img src="<s:url value="/%{payCeduAcademy.uploadedBillPath}"/>" width="580" height="369" /></s:if>
							<s:else>(没有票据图片)</s:else>
						  </td>
				    </tr> -->
					<tr>
						<td class="lable_100">备注：</td>
						<td><textarea cols="60" rows="6" name="note" id="note" readonly="readonly">${payCeduAcademy.note}</textarea></td>
					</tr>
					<tr>
						<td class="lable_100"></td>
						<td>
							<input class="btn_black_61" type="button" value="关闭" onclick="window.close();"/>
						</td>
					</tr>
				</table>
			
		</body:body>
			
  </body>
</html>
