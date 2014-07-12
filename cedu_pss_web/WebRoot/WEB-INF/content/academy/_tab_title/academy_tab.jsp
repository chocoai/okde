<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<div>
	<ul id="menu">
		<li><a href="<uu:url url="/academy/view_academy"/>?id=${param.id}" title="" ${param.tab==null ? "class=\"current\"" : ""}>基本信息</a></li>
		<li><a href="<uu:url url="/academy/list_academy_contract"/>?id=${param.id}&tab=3" title="" ${param.tab==3 ? "class=\"current\"" : ""}>合同</a></li>
		<li><a href="<uu:url url="/academy/view_academy_attachment"/>?id=${param.id}&tab=4" title="" ${param.tab==4 ? "class=\"current\"" : ""}>附件</a></li>
		<li><a href="<uu:url url="/academy/school_calendar_view_home"/>?id=${param.id}&tab=5" title="" ${param.tab==5 ? "class=\"current\"" : ""}>院历</a></li>
		<li><a href="<uu:url url="/enrollment/list_major"/>?id=${param.id}&tab=major" ${param.tab=='major' ? "class=\"current\"" : ""}>专业设置</a></li>
	</ul>
</div>