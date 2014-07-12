<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>显示物料</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!--  tree控件 -->
		<jc:plugin name="tree" />
		<!-- 操作等待插件 -->

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
						<a>物料详情</a>
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
								物料编号:
								<!--<s:property value="meterial.code" />-->
								${meterial.code}
							</th>

						</tr>
					</table>
					<table class="add_table" border="0" cellpadding="0" cellspacing="0">
						<tr>


							<td class="lable_100">
								物料名称:
							</td>
							<td style="width: 200px;">
								${meterial.name }
							</td>

							<td rowspan="5" class="lable_100">
								图片:
							</td>
							<td rowspan="5" style="width: 200px;">
								   
								 <img src="<ui:img url="${meterial.picture}" />" height="50px" border="0" />
							</td>

						</tr>
						<tr>
							<td class="lable_100">
								物料分类:
							</td>

							<td>
								<s:property value="meterial.categoryId" />
							</td>
						</tr>
						<tr>
							<td class="lable_100">
								物料规格:
							</td>
							<td>
								<s:property value="meterial.specification" />
							</td>
						</tr>
						<tr>


							<td class="lable_100">
								单价:
							</td>
							<td>
								<s:property value="meterial.price" />
							</td>
						</tr>

						<tr>
							<td class="lable_100">
								备注:
							</td>
							<td>
								<s:property value="meterial.note" />
							</td>
						</tr>

					</table>









				</div>
			</div>
		</div>




	</body>
</html>
