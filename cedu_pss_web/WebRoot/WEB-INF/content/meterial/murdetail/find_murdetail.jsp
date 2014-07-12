<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	 <title>个人领用明细</title>
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
		function addshow() {
			show('msgDiv', '提示', 200, 100);
		}
		</script>
	</head>
	<body>
		<div id="title_new">
			<div id="contitle">
				<ul id="tags">
					<li class="selectTag">
						<a class="icon">领用单</a>
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
								领用单
							</th>
						</tr>
					</table>
					<table class="add_table">
						<tr>
							<td style="font-size: 36px; text-align: center;">
								领用单
							</td>
						</tr>
					</table>
					<table class="add_table">
						<tr>
							<td width="15%" align="right">
								领用编号:
							</td>
							<td width="85%" align="left">
								<s:property value="application" />
							</td>
						</tr>
						<tr>
							<td width="15%" align="right">
								领用人:
							</td>
							<td width="85%" align="left">
								<s:property value="username" />
							</td>
						</tr>
					</table>
					<table class="gv_table_2">
						<tr>
							<th style="width: 20px;">
								<img src="../images/title_left.gif" />
							</th>
							<th style="text-align: left; font-weight: bold;">
								领用单明细
							</th>
						</tr>
					</table>
					<table class="gv_table_2">

						<tr>
							<th>
								物料名称
							</th>
							<th>
								单价
							</th>
							<th>
								领用数量
							</th>
						</tr>
						<s:iterator var="m" value="list">
							<tr>
								<td align="center">
									<s:property value="#m.meterialId" />
								</td>
								<td align="center">
									<s:property value="#m.price" />
								</td>
								<td align="center">
									<s:property value="#m.amount" />
								</td>
							</tr>
						</s:iterator>
					</table>
					<table class="gv_table_2">
						<tr>
							<th>
								<div style="float: right">
									申请金额总计:
									<b style="color: #FF0000">${avg}</b>元
								</div>
							</th>
						</tr>
					</table>
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