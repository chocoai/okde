<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>查看收费政策标准</title>
		
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
		
		<script type="text/javascript">
			//加载事件
			jQuery(function(){
			
			});

		</script>
	</head>
  
  <body>
		<!-- 头开始 -->
		<head:head title="招生返款政策标准设置">
			<html:a text="关闭" icon="return" target="_self" onclick="window.close();"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				 <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">基本数据</th>
							
					</tr>
				  </table>
				  <table class="add_table" id="enrollbatch" class="enrollbatch" border="0" cellpadding="2" cellspacing="2">
				  	<tbody>
				  		<tr>
							<td class="lable_100">院校:</td>					
							<td >
					  			${academy.name}	
							</td>
						</tr>
					  	<tr>
							<td class="lable_100">费用项目:</td>					
							<td >
					  			 ${feeSubject.name}
							</td>
						</tr>
						<tr>
							<td class="lable_100">学制:</td>					
							<td >
								${baseDict.name}
							</td>
						</tr>
						<tr>
							<td class="lable_100">标题:</td>					
							<td >
					  			${policyFee.title}
							</td>
						</tr>
				  	</tbody>
				  </table>
				  
				   <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">收费方式</th>
							
				  	</tr>
				  </table>
				 
				  <table class="add_table" id="feebatch" border="0" cellpadding="2" cellspacing="2" style="">
					 <s:if test="%{feelist!=null}">		                	
						 <tbody>
						 	<s:if test='%{policyFee.modeId==1 && feeSubject.isControl!=1}'>	
								 <tr>
								      <td class="lable_100">学分标准</td>
								      <td class="lable_100">应修学分：</td>
								      <td>
								        ${revisedCredit}分&nbsp;&nbsp;&nbsp;&nbsp;
										每学分金额：${creditFee}元/分
									  </td>
								      <td>应缴金额： ${revisedCredit*creditFee}元</td>
						       		  <td></td>	
						       		  <td></td>	
						       		  <td></td>	
						        </tr>
						    </s:if>
				        </tbody>
					  	<tfoot>
						    <tr>
						      <td class="lable_100">收费标准</td>
						  		<td class="lable_100">
						  			缴费次数	：	  		
						  		</td>
						  		<s:if test='%{policyFee.modeId==1 && feeSubject.isControl!=1}'>	
							  		<td>
							  			<span class="mode_name">学分</span>下限		  		
							  		</td>
							  		<!-- td>
							  			<span class="mode_name">学分</span>上限		  		
							  		</td> -->
							  	</s:if>
							  	<s:else>
							  		<td>
							  			<span class="mode_name">缴费</span>下限		  		
							  		</td>
							  		<!-- td>
							  			<span class="mode_name">缴费</span>上限		  		
							  		</td> -->
							  	</s:else>	
						  		<td>开始时间</td>	
					       		<td>结束时间</td>
					       	
						  	</tr>
						  	<s:iterator id="item" value="feelist" status="sta"> 
						  		<s:if test='%{policyFee.modeId==1 && feeSubject.isControl!=1}'>	
									<tr>
										<td></td>
										<td class="lable_100">${item.feeBatchName}：</td>
										<td>
											<fmt:formatNumber type="number" value="${item.creditFee-0==0?0:item.chargingFloor/creditFee}" maxFractionDigits="2"/>分
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<span id="" class="hidfee" style="">${item.chargingFloor}元</span>
										</td>
										<!-- td>
											
											<fmt:formatNumber type="number" value="${item.creditFee-0==0?0:item.chargingCeil/creditFee}" maxFractionDigits="2"/>  分
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<span id="" class="hidfee" style="">${item.chargingCeil}元</span>		  		
										</td> -->
										<td>
											<s:date name="startTime" format="yyyy-MM-dd"/>							
										</td>
										<td>
											<s:date name="endTime" format="yyyy-MM-dd"/>
										</td>
									</tr>
								</s:if>	
								<s:else>
									<tr>
										<td></td>
										<td class="lable_100">${item.feeBatchName}：</td>
										<td>
											${item.chargingFloor}元
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<span id="" class="hidfee" style=""></span>
										</td>
										<td>
											${item.chargingCeil}元
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<span id="" class="hidfee" style=""></span>		  		
										</td>
										<td>
											<s:date name="startTime" format="yyyy-MM-dd"/>							
										</td>
										<td>
											<s:date name="endTime" format="yyyy-MM-dd"/>
										</td>
									</tr>
								</s:else>
							</s:iterator>
					  	</tfoot>
				   	</s:if>
			       	<s:else>
			           <tr><td align="center">无相关数据！</td></tr>
			      	</s:else>
				  </table>			
			</body:body>
	
  </body>
</html>
