<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="net.cedu.entity.admin.User"%>
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

function addshow() {
	show('msgDiv', '提示', 200, 100);
}
function create_payment_row() {
	var cells = new Array();

	 
	cells.push(jQuery('<td/>')
			.append(jQuery('<input/>').addClass('txt_box_90')));
	if (arguments[0])
		jQuery('input', cells[0]).val(arguments[0]);

	cells.push(jQuery('<td/>')
			.append(jQuery('<input/>').addClass('txt_box_90')));
	if (arguments[1])
		jQuery('input', cells[1]).val(arguments[1]);

	cells.push(jQuery('<td/>')
			.append(jQuery('<input/>').addClass('txt_box_90')));
	if (arguments[2])
		jQuery('input', cells[2]).val(arguments[2]);

	cells.push(jQuery('<td/>')
			.append(jQuery('<input/>').addClass('txt_box_90')));
	if (arguments[3])
		jQuery('input', cells[3]).val(arguments[3]);

	cells.push(jQuery('<td/>').attr('align', 'left'));
	if (!arguments[4])
		cells[4]
				.append('<input type="button" class="btn_black_61" value="删除" onclick="remove_payment_row(this)"/>');

	var row = jQuery('<tr/>');
	for ( var j = 0; j < cells.length; j++) {
		row.append(cells[j]);
	}

	jQuery('#payment_items').append(row);
}
</script>
		<script type="text/javascript">
jQuery(function() {
	util.select.initOption('*[name="fee_subject"]', get_fee_subject());
	util.select.initOption('*[name="fee_batch"]', get_fee_batch());
	util.select.initOption('*[name="fee_way"]', get_fee_way());
});
</script>
	</head>
	<body>

		<div id="title_new">
			<div id="contitle">
				<ul id="tags">
					<li class="selectTag">
						<a>基础设置</a>
					</li>
				</ul>
			</div>

		</div>
		<div class="block">

			<div class="public_table_bg_766">
				<div class="tb_table_default_2">

					<!--Menu Begin-->
					<div>
						<ul id="menu">
							<li>
								<a href="view_set_material.html" title="" class="current">物料分类</a>
							</li>
							<li>
								<a href="view_material_store_way.html" title="">入库方式</a>
							</li>
							<li>
								<a href="view_material_store_house.html" title="">库房设置</a>
							</li>

						</ul>
					</div>
					<!--Menu End-->

					<table class="gv_table_2">
						<tr>
							<th style="width: 20px;">
								<img src="../images/title_left.gif" />
							</th>
							<th style="text-align: left; font-weight: bold;">
								物料分类
							</th>
							<th>
								<div style="float: right;">
									<img src="../images/cedu/icon/icon_add.gif" />
									<a href="javascript:void(0);" onclick="create_payment_row()">增加</a>
								</div>
							</th>


						</tr>
					</table>


					<table class="gv_table_2">
						<tr>

							<th>
								编码
							</th>
							<th>
								物料分类类名称
							</th>
							<th>
								操作
							</th>

						</tr>
						<s:iterator var="m" value="list">
							<tr>

								<td align="center">
									<s:property value="#m.code" />
								</td>
								<td align="center">
									<s:property value="#m.name" />
								</td>
								<td align="center">
									<input name="" type="button" class="btn_gray_61" value="修改" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="delete_meterialcategory?id=<s:property value="#m.id" />"><input
											name="" type="button" class="btn_gray_61" value="删除" onclick="addshow()" />
									</a>
								</td>
							</tr>
						</s:iterator>

						<tr>

							<td align="center">
								2
							</td>
							<td align="center">
								笔
							</td>
							<td align="center">
								<input name="" type="button" class="btn_gray_61" value="修改" />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="" type="button" class="btn_gray_61" value="删除" />
							</td>
						</tr>


						<tr>


							<td align="center">
								3
							</td>
							<td align="center">
								水
							</td>
							<td align="center">
								<input name="" type="button" class="btn_gray_61" value="修改" />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="" type="button" class="btn_gray_61" value="删除" />
							</td>
						</tr>



						<tr>

							<td align="center">
								<input name="" type="text" class="txt_box_150" value="1" />
							</td>

							<td align="center">
								<input name="" type="text" class="txt_box_150" value="纸" />
							</td>
							<td align="center">
								<input name="" type="button" class="btn_gray_61" value="保存" />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="" type="button" class="btn_gray_61" value="取消" />

							</td>
						</tr>

						<tr>
							<td align="center">
								<input name="" type="text" class="txt_box_150" />
							</td>
							<td align="center">
								<input name="" type="text" class="txt_box_150" />
							</td>
							<td align="center">
								<input name="" type="button" class="btn_gray_61" value="保存" />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="" type="button" class="btn_gray_61" value="取消" />

							</td>
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
	  <div id="msgDiv" style="display:none">
		操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />
	</div>

	</body>
</html>