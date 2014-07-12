<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp"%>

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
</script>
	</head>
	<body>

		<div id="title_new">
			<div id="contitle">
				<ul id="tags">
					<li class="selectTag">
						<a class="icon">中心申请</a>
					</li>
				</ul>
			</div>

			<div id="conmenu">

				<img class="img_icon" src="../images/cedu/icon/icon_add.gif" />

				<a href="addmeaterialapplicationdeta.jsp" target="_blank">添加申请单</a>
			</div>

		</div>
		<div class="block">
			<div class="public_table_bg_766">
				<div class="tb_table_default_2">


					<table class="add_table">
						<tr>
							<td align="left">
								申请单编号:
								<input name="" type="text" class="txt_box_100" />
							</td>

							<td align="left">
								申请人:
								<input name="" type="text" class="txt_box_100" />
							</td>
							<td align="left">
								<input name="" type="button" class="btn_black_61" value="查询" />
							</td>
						</tr>
					</table>


					<table class="gv_table_2">

						<tr>
							<th>
								申请单编号
							</th>
							<th>
								申请人
							</th>

							<th>
								申请总金额
							</th>
							<th>
								申请时间
							</th>
							<th>
								状态
							</th>
							<th>
								操作
							</th>


						</tr>
						<s:iterator var="mad" value="list">
							<tr>

								<td>
									<s:property value="#mad.code" />
								</td>
								<td>
									<s:property value="#mad.applicant" />
								</td>
								<td>
									<s:property value="#mad.amount" />
								</td>
								<td>
									<s:property value="#mad.submit_time" />
								</td>
								<td>
									<s:property value="#mad.status" />
								</td>
								<td>
									<a href="add_meterialapplication">明细</a>
								</td>
							</tr>
						</s:iterator>









					</table>

					<div id="candidaes_pager" class="pager2">
					<table>
						<tr>
							<td colspan="8">
								<div class="imgalign" align="right">
									<span class="disabled"><label>
											当前1页/总1页
										</label> </span>
									<img border='0' src='../images/navLeft.gif' />

									<img border='0' src='../images/page_left.gif' />
									<span class="csspage">1</span>
									<img border='0' src='../images/page_right.gif' />
									<img border='0' src='../images/navRight.gif' />
									<span class="disabled"><input type="text" class="topage"
											value="1" /> <input class="btnto" type="button" value="GO" />
									</span>
								</div>
							</td>
						</tr>
					</table>
					</div>

				</div>
			</div>
		</div>


    	<a href="../../meterial/meterial/list_meterialbycatrgory.jsp">显示物料信息</a>

<a href='../../meterial/meterial/add_meterial'>明细</a>
	</body>
</html>