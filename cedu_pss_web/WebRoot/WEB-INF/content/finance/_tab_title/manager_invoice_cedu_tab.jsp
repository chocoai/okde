<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<div>
	<ul id="menu">
		<li><a href="<uu:url url="/finance/managerinvoice/list_manager_invoice_cedu"/>?tab=1" title="" ${param.tab==1 ? "class=\"current\"" : ""}>收发票</a></li>
		<li><a href="<uu:url url="/finance/managerinvoice/list_postal_parcel_cedu"/>?tab=2" title="" ${param.tab==2 ? "class=\"current\"" : ""}>邮寄单</a></li>
	</ul>
</div>