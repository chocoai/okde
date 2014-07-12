<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<div>
	<ul id="menu">
		<li><a href="<uu:url url="/finance/invoicebook/list_invoice_book"/>?tab=1" title="" ${param.tab==1 ? "class=\"current\"" : ""}>已授权</a></li>
		<li><a href="<uu:url url="/finance/invoicebook/list_invoice_book"/>?tab=2" title="" ${param.tab==2 ? "class=\"current\"" : ""}>未授权</a></li>
	</ul>
</div>