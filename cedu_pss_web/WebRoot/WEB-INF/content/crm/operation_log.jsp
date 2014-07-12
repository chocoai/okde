<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>操作日志</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 分页 -->
		<jc:plugin name="page" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
	<script type="text/javascript">
		function createNameValue(createName){
			return createName;
		}
	</script>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="operation.log" il8nName="crm">
		</head:head>
		<!--主体层开始 -->
		<body:body>
					<table class="add_table">
						   <tr>
								<td align="right" width="300px">关键字：</td>
				                <td align="left" width="400px">
										<input id="content" type="text" class="txt_box_200"/>(搜索关键字为：<font color="red">内容</font>，<font color="red">IP</font>)
								</td>
				                <td align="left">
									<input type="button" class="btn_black_61" onclick="search001();" value="查询"/>
								</td>
								<td></td>
			              </tr>
            		</table>
					<page:plugin 
						pluginCode="001"
						il8nName="crm"
						searchListActionpath="crm_operationlog_list"
						searchCountActionpath="crm_operationlog_count"
						columnsStr="content;ip;#createId;createTime"
						customColumnValue="2,createNameValue(createName)"
						pageSize="10"
						isNumber="true"
						params="'operationLog.content':$('#content').val()"
					/>
					
		</body:body>
	</body>

</html>