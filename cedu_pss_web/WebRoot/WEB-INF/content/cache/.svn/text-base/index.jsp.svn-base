<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Cache</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<script type="text/javascript">
	$(function() {
		var tb_e_="";
		var tb_p_e_="";
		var tb_p_r="";
		var tb_r_="";
		var tb_hr_e="";
		var tb_s="";
		$("#searchEntry >option").each(function(){
			if($(this).text().indexOf("tb_e_")>=0){
				tb_e_+="<option value='"+$(this).val()+"'>"+$(this).text().replaceAll("tb_e_","")+"</option>";
			}
			if($(this).text().indexOf("tb_p_e_")>=0){
				tb_p_e_+="<option value='"+$(this).val()+"'>"+$(this).text().replaceAll("tb_p_e_","")+"</option>";
			}
			if($(this).text().indexOf("tb_p_r_")>=0){
				tb_p_r+="<option value='"+$(this).val()+"'>"+$(this).text().replaceAll("tb_p_r_","")+"</option>";
			}
			if($(this).text().indexOf("tb_r_")>=0){
				tb_r_+="<option value='"+$(this).val()+"'>"+$(this).text().replaceAll("tb_r_","")+"</option>";
			}
			
			if($(this).text().indexOf("tb_hr_e_")>=0){
				tb_hr_e+="<option value='"+$(this).val()+"'>"+$(this).text().replaceAll("tb_hr_e_","")+"</option>";
			}
			if($(this).text().indexOf("tb_s_")>=0){
				tb_s+="<option value='"+$(this).val()+"'>"+$(this).text().replaceAll("tb_s_","")+"</option>";
			}
		});
		tb_e_="<optgroup label='tb_e_'>"+tb_e_+"</optgroup>";
		tb_p_e_="<optgroup label='tb_p_e_'>"+tb_p_e_+"</optgroup>";
		tb_p_r="<optgroup label='tb_p_r_'>"+tb_p_r+"</optgroup>";
		tb_r_="<optgroup label='tb_r_'>"+tb_r_+"</optgroup>";
		
		tb_hr_e="<optgroup label='tb_hr_e_'>"+tb_hr_e+"</optgroup>";
		tb_s="<optgroup label='tb_s_'>"+tb_s+"</optgroup>";
		$("#searchEntry").html("<option selected='selected' value=''>--请选择要查询的缓存对象--</option>"+tb_e_+tb_p_e_+tb_p_r+tb_r_+tb_hr_e+tb_s);
		//$("#searchEntry").val("${requestScope.searchEntry}");
		$("#searchEntry option[value='${requestScope.searchEntry}']").attr("selected", true);
		var labelTable="";
		$("#searchEntry optgroup").each(function(){
			
			if($(this).text().indexOf($.trim($("#searchEntry option[value='${requestScope.searchEntry}']").text()))>0){
				//alert($(this).attr("label"));
				labelTable=$(this).attr("label");
				return true;
			}
		});
		$("#tab_span").html(labelTable);
	});
	function subForm(type) {
		$("#type").val(type);
		document.getElementById('cacheForm').submit();
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
				<form id="cacheForm" action="<%=Constants.WEB_PATH%>/cache/index"
					method="post">
					<input type="hidden" value="" name="type" id="type" />
					<table width="100%" class="seach_table" border="0" cellpadding="2"
						cellspacing="0">
						<tr>

							<td class="lable_100">
								缓存对象：
							</td>
							<td>
								<span id="tab_span"></span>
								<select class="txt_box_300" id="searchEntry" name="searchEntry"
									onchange="subForm('search')">
									
									<c:forEach items="${cacheEntitys }" var="entity">
										<option value="${entity.className}">
											${entity.classTextName }
										</option>
									</c:forEach>
								</select>
								<a href="#"><input value="删除当前选中实体缓存" type="button"
										onclick="subForm('p_delete')" />
								</a>
								
								<a href="#"><input value="清空所有缓存" type="button"
										onclick="subForm('p_clear')" />
								</a>
							</td>
							<td>
								<span style="color: red;">${requestScope.message}</span>
							</td>

						</tr>
					</table>
				</form>
			</fieldset>
			<table class="gv_table_2" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tr>
					<th>
						序号
					</th>
					<th>
						对象ID
					</th>
					<th>
						操作
					</th>
				</tr>
				<%
					int j = 1;
				%>
				<c:forEach items="${requestScope.cacheDetails}" var="cacheDetails"
					varStatus="i">
					<tr>

						<td align="center">
							<%=j++%>
						</td>
						<td align="left" style="padding-left: 10px;">
							${cacheDetails.key }
							<%--${fn:substring(cacheDetails.key,fn:indexOf(cacheDetails.key,"_")+1,fn:length(cacheDetails.key)) }  --%>
						</td>
						<td align="center">
							<a
								href="<%=Constants.WEB_PATH %>/cache/index?type=delete&key=${cacheDetails.key}&searchEntry=${requestScope.searchEntry}">删除</a>
						</td>

					</tr>
				</c:forEach>
			</table>
		</body:body>

	</body>
</html>

