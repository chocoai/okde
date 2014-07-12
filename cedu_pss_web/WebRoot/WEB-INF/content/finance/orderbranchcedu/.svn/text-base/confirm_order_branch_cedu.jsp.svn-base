<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>汇款总部（中心） 汇款单审批</title>
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
	<!-- 选项卡 -->
	<jc:plugin name="tab" />
	<!-- 表单验证 -->
	<jc:plugin name="validator" />
	<!-- 基础JS -->
	<jc:plugin name="base_js" />
	<!-- 操作等待插件 -->
	<jc:plugin name="loading" />
	<script type="text/javascript">
	function beforeConfirm(){
		$.confirm({
			title: '确认操作',
			msg: '是否确认？'
		});
	}
	
	$(function(){
		util.select.initOption('[name="branch"]', get_branch());
	});
	</script>
</head>
<body>
	<head:head title="汇款总部  汇款单确认">
		<html:a text="返回" icon="return" onclick="history.go(-1);"/>
	</head:head>
	<body:body>
		<table class="gv_table_2">
			<tr>
				<th style="width:20px;"><img src="<ui:img url="/images/title_menu/title_left.gif" />" /></th>
				<th style="text-align:left; font-weight:bold;">汇款总部 &gt;&gt; 汇款单确认</th>
				<th></th>
			</tr>
		</table>
		
		<table class="add_table">
			<tr>
				<td class="lable_100">汇款单位：</td>
				<td>北京学习中心<!--select name="branch" class="txt_box_100"--></select></td>
				<td class="lable_100">收款单位：</td>
				<td>弘成总部</td>
			</tr>
			<tr>
				<td class="lable_100">汇款账号：</td>
				<td><input type="text" class="txt_box_150" value="3002893939999988"/></td>
				<td class="lable_100">收款账号：</td>
				<td><input type="text" class="txt_box_150" value="2030999382888820030"/></td>
			</tr>
			<tr>
				<td class="lable_100">汇款日期起：</td>
				<td><input type="text" name="" id="" class="txt_box_150"/></td>
				<td class="lable_100">汇款日期止：</td>
				<td><input type="text" name="" id="" class="txt_box_150"/></td>
			</tr>
			<tr>
				<td class="lable_100">汇款单号：</td>
				<td><input type="text" name="" id="" class="txt_box_150"/></td>
				<td class="lable_100">汇款单状态：</td>
				<td>
					<select>
						<option>全部状态</option>
						<option >已填汇款单</option>
						<option>已汇款总部</option>
						<option>总部确认</option>

					</select>
				</td>
			</tr>
		</table>

		<table class="add_table"><tr><td align="right"><form action="pay_branch_cedu.html" target="_blank"><input type="button" class="btn_black_61" value="查询"/>
		</form></td></tr></table>

		<table class="gv_table_2" width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tbody>
				<tr>
				  <th>操作</th>
				  <th>汇款单位</th>
				  <th>收款单位</th>
				  <th>付款账号</th>
				  <th>收款账号</th>
				  <th>费用金额</th>
				  <th>备注</th>
				  <th>汇款单号</th>
				  <th>汇款日期</th>
				  <th>状态</th>
			      <th>确认汇款</th>
				</tr>
				<tr>
				  <td align="center"><a href="view_branch_cedu_payment2.html" target="_blank"><img src="../images/cedu/icon/icon_view.gif" border="0" class="img_icon"/></a></td>
				  <td align="center">北京学习中心</td>
				  <td align="center">弘成学苑</td>
				  <td align="center">6302093993929380002</td>
				  <td align="center">6233929380029430</td>
				  <td align="center">355500</td>
				  <td align="center">缴费方式：现金汇总部</td>
				  <td align="center">HK0099283</td>
				  <td align="center">2011-03-24</td>
				  <td align="center">已填汇款单</td>
			      <td align="center"><a href="javascript:beforeConfirm()">中心确认</a></td>
				</tr>
				<tr>
				  <td align="center"><a href="view_branch_cedu_payment2.html"><img src="../images/cedu/icon/icon_view.gif" class="img_icon"/></a></td>
				  <td align="center">北京学习中心</td>
				  <td align="center">弘成学苑</td>
				  <td align="center">6302093993929380002</td>
				  <td align="center">6233929380029430</td>
				  <td align="center">26900</td>
				 <td align="center">缴费方式：现金汇总部</td>
				  <td align="center">&nbsp;</td>
				  <td align="center">&nbsp;</td>
				  <td align="center">已填汇款单</td>
			      <td align="center"><a href="javascript:beforeConfirm()">中心确认</a></td>
				</tr>
				<tr>
				  <td align="center"><a href="view_branch_cedu_payment.html"><img src="../images/cedu/icon/icon_view.gif" class="img_icon"/></a></td>
				  <td align="center">北京学习中心</td>
				  <td align="center">弘成学苑</td>
				  <td align="center">6302093993929380002</td>
				  <td align="center">6233929380029430</td>
				  <td align="center">26900</td>
				  <td align="center">缴费方式：现金汇总部</td>
				  <td align="center">HK0099283</td>
				  <td align="center">2011-07-07</td>
				  <td align="center">已汇款总部</td>
			      <td align="center"></td>
				</tr>
				<tr>
				  <td align="center"><a href="view_branch_cedu_payment1.html"><img src="../images/cedu/icon/icon_view.gif" class="img_icon"/></a></td>
				  <td align="center">北京学习中心</td>
				  <td align="center">弘成学苑</td>
				  <td align="center">6302093993929380002</td>
				  <td align="center">6233929380029430</td>
				  <td align="center">26900</td>
				  <td align="center">缴费方式：现金汇总部</td>
				  <td align="center">HK0099283</td>
				  <td align="center">2011-07-07</td>
				  <td align="center">总部确认</td>
			      <td align="center"></td>
				</tr>
				<tr>
				  <td align="center"><a href="view_branch_cedu_payment1.html"><img src="../images/cedu/icon/icon_view.gif" class="img_icon"/></a></td>
				  <td align="center">北京学习中心</td>
				  <td align="center">弘成学苑</td>
				  <td align="center">6302093993929380002</td>
				  <td align="center">6233929380029430</td>
				  <td align="center">26900</td>
				  <td align="center">缴费方式：现金汇总部</td>
				  <td align="center">HK0099283</td>
				  <td align="center">2011-07-07</td>
				  <td align="center">总部确认</td>
			      <td align="center"></td>
				</tr>
			  </tbody>
			</table>

	</body:body>
</body>
</html>
