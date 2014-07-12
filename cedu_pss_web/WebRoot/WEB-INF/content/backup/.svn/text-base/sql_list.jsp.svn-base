<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>备份数据</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<script type="text/javascript">
			function deleteSql(filename){
				if(confirm("确认要删除吗？")){
					location.href="<%=Constants.WEB_PATH%>/backup/delete_database?sql="+filename;
				}
			}
		</script>
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="缓存管理">
		</head:head>
		<!--主体层开始 -->
		<body:body>

			<fieldset>
				<form id="cacheForm" action="<%=Constants.WEB_PATH%>/backup/backup_database" method="post">
					<textarea class="txt_box_400" name="sqlRemark" maxlength="200" rows="4" style="height: 100px;"></textarea>
					<br/>
					<input type="submit" value="备份数据库" />
				</form>
			<table class="gv_table_2" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tr>
					<th>
						序号
					</th>
					<th>
						sql文件压缩包
					</th>
					<th>
						操作
					</th>
				</tr>
				<%
					int j = 1;
				%>
				<c:forEach items="${requestScope.fileList}" var="map"
					varStatus="i">
					<tr>
						<td align="center">
							<%=j++%>
						</td>
						<td align="left" style="padding-left: 10px;">
							${map.sqlFileName }
						</td>
						<td align="center">
							<a href="${map.sqlFilePath }">下载</a>
							<a target="_blank" href="${map.sqlFileRemarkPath }">查看备注</a>
							<a href="javascript:deleteSql('${map.sqlFileName }');">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</body:body>

	</body>
</html>

