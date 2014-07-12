<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<div>
	<ul id="menu">
		<li><a href="<uu:url url="/finance/studentaccountmanagement/view_student_account_management"/>?id=${param.id}&tab=1" title="" ${param.tab==1 ? "class=\"current\"" : ""}>充值记录</a></li>
		<li><a href="<uu:url url="/finance/studentaccountmanagement/view_student_account_management"/>?id=${param.id}&tab=2" title="" ${param.tab==2 ? "class=\"current\"" : ""}>消费记录</a></li>
	</ul>
</div>