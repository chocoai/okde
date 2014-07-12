<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<div>
	<ul id="menu">
		<li><a href="<uu:url url="/finance/channelrebatereview/list_channel_rebate_all_fpd_branch"/>?viewtype=1" title="" <s:if test="viewtype==1">class="current"</s:if><s:elseif test="viewtype==0">class="current"</s:elseif>>不符合未返款</a></li>
		
		<li><a href="<uu:url url="/finance/channelrebatereview/list_channel_rebate_suffice_fpd_branch"/>?viewtype=2" title="" <s:if test="viewtype==2">class="current"</s:if>>符合未返款</a></li>
		
		<li><a href="<uu:url url="/finance/channelrebatereview/list_channel_rebate_has_fpd_branch"/>?viewtype=3" title="" <s:if test="viewtype==3">class="current"</s:if>>已返款</a></li>
	</ul>
</div>