<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>总部采购</title>
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
			
		function addshow()
		{
			show('msgDiv','提示',200,100);
		}
		function caozuo(detail,id)
		{	 	 
			if(detail==null)
			{
				var s='<s:url value="/meterial/meterialpurchasedetail/find_meterialpurchasedetail?meterid=" />';
				return '<a href="'+s+id+'">明细</a>'
			//return "<a href='/meterial/meterialpurchasedetail/find_meterialapplicationdetail'>明细</a>"
			}		
		}
		
		function shijian(purchaseTime)
		{
			return purchaseTime.substring(0,10);
		}
		
	</script>
	</head>
	<body>

		<div id="title_new">
			<div id="contitle">
				<ul id="tags">
					<li class="selectTag">
						<a class="icon">总部采购</a>
					</li>
				</ul>
			</div>
			<div id="conmenu">
				<img class="img_icon" src="../images/cedu/icon/icon_add.gif" />
				<html:a text="添加采购单" icon="add"
					href="/meterial/meterialpurchasedetail/add_meterialpurchasedetail" />
			</div>
		</div>
		<div class="block">
			<div class="public_table_bg_766">
				<div class="tb_table_default_2">
					<table class="add_table">
						<tr>
							<td align="left">
								采购单编号:
								<input name="" type="text" id="code" class="txt_box_100" />
							</td>
							<td align="left">
								采购人:
								<input name="" type="text" id="operatorsname"
									class="txt_box_100" />
							</td>
							<td align="left">
								<input name="" type="button" class="btn_black_61" value="查询"
									onclick="searchpage();" />
							</td>
						</tr>
					</table>
					<div>
						<page:plugin pluginCode="page" il8nName="meterialpurchase"
							searchListActionpath="list_meterialpurchase"
							searchCountActionpath="count_meterialpurchase"
							columnsStr="code;operatorsname;amount;purchaseTime;detail;"
							customColumnValue="3,shijian(purchaseTime);4,caozuo(detail,id)" 
							pageSize="3"
							ontherOperatingWidth="80px" isPageSize="3"
							params="'code':$('#code').val(),'operatorsname':$('#operatorsname').val()" />
					</div>
				</div>
			</div>
		</div>
	</body>
</html>