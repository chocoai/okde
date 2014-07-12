<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><m:il8n key="student.import.record.title"
				il8nName="crm" />
		</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<jc:plugin name="loading" />
		<script type="text/javascript">
			//招生途径和市场途径回调函数
			function wayAndSourceCallback(doc) {
				$("#way").html("");
				var wayStr = "<option value='" + 0 + "'>请选择市场途径</option>";
				$
						.each(
								doc.enrollmentWaysMap,
								function(key, value) {
									if (key != "大客户" && key != "渠道" && key != "老带新"
											&& key != "加盟" && key != "共建") {
										wayStr += "<optgroup label='"+key+"'>";
										$(this).each(
														function() {
															if (this.id == '${student.enrollmentWay}') {
																wayStr += "<option selected='selected' value='" + this.id + "'>"
																		+ this.name
																		+ "</option>";
															} else {
																wayStr += "<option value='" + this.id + "'>"
																		+ this.name
																		+ "</option>";
															}
														});
										wayStr += "</optgroup>";
									}
								});
				$("#way").html(wayStr);
			}
			//验证
			function verification(){
				if($("#title").val()==null||$("#title").val()==""){
					alert("请填写数据名称");
					return false;
				}
				//市场途径
				if($("#way").val()=="0"){
					alert("请选择市场途径");
					return false;
				}
				if($("#file").val()==null||$("#file").val()==""){
					alert("请选择上传文件");
					return false;
				}
				if($("#file").val().indexOf(".xls")==-1){
					alert("请上传.xls文件");
					return false;
				}
				return true;
			}
		</script>
		<a:ajax pluginCode="0001" urls="/crm/student_way_list"
			successCallbackFunctions="wayAndSourceCallback" isOnload="all" />
	</head>
	<body>
		<!--头部层开始 -->
		<head:head title="呼叫中心学生信息导入" il8nName="crm">

		</head:head>

		<!--主体层开始 -->
		<body:body>
		   	<c:if test="${errorCode=='100'||errorCode=='500' }">
		   		<c:if test="${errorCode=='500' }">
		   			<script type="text/javascript">alert("上传失败，请重新上传！");</script>
		   		</c:if>
				<form id="create_studentImportRecord_form" onsubmit="return verification();" action="<uu:url url="crm/importdata/admin_upload_student_data" />" method="post" enctype="multipart/form-data">
					<table class="add_table">
						<tr>
							<td align="right">
								<span>*</span>名称：
							</td>
							<td>
								<input type="text" class="txt_box_200" id="title" value="${studentImportRecord.title }"
									name="studentImportRecord.title" />
							</td>
							<td align="right">
								数据来源：
							</td>
							<td>
								呼叫中心
								<input type="hidden" name="studentImportRecord.sourceId" value="<%=Constants.STU_SOURCE_CC %>"/>
							</td>
						</tr>
						<tr>
							<td align="right">
								<span>*</span>招生途径：
							</td>
							<td>
								社招
								<input type="hidden" name="studentImportRecord.enrollmentSourceId" value="<%=Constants.WEB_STU_SOURCE_DEFAULT %>"/>
							</td>
	
							<td align="right">
								<span>*</span>市场途径：
							</td>
							<td>
								<select id="way" class="txt_box_200" name="studentImportRecord.enrollmentWayId"></select>
							</td>
						</tr>
						<tr>
							<td align="right">
								<span>*</span>上传文件：
							</td>
							<td width="220px">
								<input class="txt_box_200" type="file" name="file" id="file" />
	
							</td>
							<td align="left">
								<a
									href="<ua:attachment url="/upload/crm/student_data_import_template/zongbu_import_template.xls" />">下载模板</a>
							</td>
							<td>
								<span>注:请上传.xls文件.</span>
							</td>
						</tr>
						<tr>
							<td align="right" valign="top">
								备注：
							</td>
							<td colspan="3">
								<textarea class="txt_box_335" rows="5"
									name="studentImportRecord.note"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input class="btn_black_61" id="add_sub" type="submit" value="导入" />
							</td>
						</tr>
					</table>
				</form>
			</c:if>
			<c:if test="${errorCode=='200'}">
				上传成功！
			</c:if>

		</body:body>
	</body>

</html>