<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
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
			 jQuery(function(){	 
		 		cooRN();
			});	
					
			function addshow()
			{
				show('msgDiv','提示',200,100);
			}	
			
			function cooRN() //校正 序号
			{
	 		$('#standardTable > tbody > tr').each(function(index){
			$('.tfQuantity', this).attr('class', 'tfQuantity'+index+'');
			});	
			}
		//测试数据方法
		 function seach()
 		{
			var a=[];
			for(var i=1;i<10;i++)
				{
				if($("input[class='tfQuantity"+i+"']").attr("value")!=null)
			    a.push($("input[class='tfQuantity"+i+"']").attr("value"));
				}
		 	alert(a);
 		}
 		
		function insertRelationships(){
 		 
 			var quntion=[];//派发数量数组		 
 			var quntionIds="";//填写的派发数量数组拼接起来的字符串	
 			var meterid=$("#meterid").attr("value");	 
 			//循环获取数组中的派发数量
				for(var i=1;i<10;i++){
				 if($("input[class='tfQuantity"+i+"']").attr("value")!=null){
 				quntion.push($("input[class='tfQuantity"+i+"']").attr("value"));
 				 }		 
					//选中的拼接成字符串
					quntionIds=quntion.join(",");
					//通过表单实现js post提交
					}
				document.write("<form action='update_meterial_application_detail?quntion="+quntionIds+"&meterid="+meterid+"'  method='post' name='lecturerForm' style='display:none'>");
				document.write("</form>");
				document.lecturerForm.submit();			 		 
 		}
	</script>
	</head>
	<body>
		<div id="title_new">
			<div id="contitle">
				<ul id="tags">
					<li class="selectTag">
						<a class="icon">申请单派发</a>
					</li>
				</ul>
			</div>
			<div id="conmenu">
				<img src="../images/icon_title_return.jpg" width="15" height="15" />
				<a href="javascript:history.go(-1);">返回</a>
			</div>
		</div>
		<div class="block">
			<div class="public_table_bg_766">
				<div class="tb_table_default_2">
					<table class="gv_table_2">
						<tr>
							<th style="width: 20px;">
								<img src="../images/title_left.gif" />
							</th>
							<th style="text-align: left; font-weight: bold;">
								申请单派发
							</th>
						</tr>
					</table>
					<form action="update_meterial_application_detail" method="post">
						<table class="add_table">
							<tr>
								<td style="font-size: 36px; text-align: center;">
									申请单派发
								</td>
							</tr>
						</table>
						<table class="add_table">
							<tr>
								<td width="15%" align="right">
									申请单编号:
								</td>
								<td width="85%" align="left">
									<input type="text" name="meterid" id="meterid"
										value="${meterid}" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									申请人:
								</td>
								<td width="85%" align="left">
									<s:property value="applicationname" />
								</td>
							</tr>
						</table>
						<table class="gv_table_2">
							<tr>
								<th style="width: 20px;">
									<img src="../images/title_left.gif" />
								</th>
								<th style="text-align: left; font-weight: bold;">
									申请单派发
								</th>
							</tr>
						</table>
						<table class="gv_table_2" id="standardTable">
							<tr>
								<th>
									序号
								</th>
								<th>
									物料名称
								</th>
								<th>
									单价
								</th>
								<th>
									申请数量
								</th>
								<th>
									已派发数量
								</th>
								<th>
									派发数量
								</th>
							</tr>
							<tbody></tbody>
							<s:iterator var="m" value="list" status="x">
								<tr>
									<td align="center">
										<s:property value="#x.count" />
									</td>
									<td align="center">
										<s:property value="#m.meterialId" />
									</td>
									<td align="center">
										<s:property value="#m.price" />
									</td>
									<td align="center">
										<s:property value="#m.appliedCount" />
									</td>
									<td align="center">
										<s:property value="#m.suppliedCount" />
									</td>
									<td align="center">
										<input class="tfQuantity" type="text" name="supplied"
											id="supplied" />
									</td>
								</tr>
							</s:iterator>
						</table>
						<table class="gv_table_2">
							<tr>
								<th>
									<input name="" type="button" class="btn_black_61"
										onclick="insertRelationships()" value="派发" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a
										href="../meterialapplication/distribute_meterial_application.action"><input
											name="" type="button" class="btn_black_61" onclick=""
											value="取消" />
									</a>
								</th>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div id="msgDiv" style="display: none">
			操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<br />
			<br />
			<br />
		</div>
	</body>
</html>