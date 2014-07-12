<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<div>
	<ul id="menu">
		<li><a href="<uu:url url="/enrollment/list_stu_discount_app_auditing_branch"/>" title="" ${param.tab==null ? "class=\"current\"" : ""}>待审批</a></li>
		<li><a href="<uu:url url="/enrollment/list_stu_discount_app_audited_branch"/>?tab=2" title="" ${param.tab==2 ? "class=\"current\"" : ""}>已审批</a></li>
		<li><a href="<uu:url url="/enrollment/list_stu_discount_app_using_branch"/>?tab=3" title="" ${param.tab==3 ? "class=\"current\"" : ""}>已使用</a></li>
	</ul>
</div>