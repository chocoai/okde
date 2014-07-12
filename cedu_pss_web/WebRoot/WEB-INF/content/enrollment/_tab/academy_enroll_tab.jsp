<%@ page language="java" pageEncoding="UTF-8"%>

<div>
	<ul id="menu">
		<li><a href="<s:url value="/enrollment/academy_enroll_batch_list"/>?academyId=${param.academyId}&tab=batch" ${param.tab=='batch' ? "class=\"current\"" : ""}>招生批次</a></li>
		<li><a href="<s:url value="/enrollment/academy_level_and_major_list"/>?academyId=${param.academyId}&tab=majorandlevel" ${param.tab=='majorandlevel' ? "class=\"current\"" : ""}>层次/专业</a></li>
		<li><a href="<s:url value="view_academy_branch"/>?academyId=${param.academyId}&tab=branch" ${param.tab=='branch' ? "class=\"current\"" : ""}>授权中心</a></li>
		<!-- <a href="<s:url value="/enrollment/view_academy_payment_frequency"/>?academyId=${param.academyId}&tab=4" ${param.tab==4 ? "class=\"current\"" : ""}>缴费次数设置</a> -->
		<li><a href="<s:url value="/enrollment/view_academy_payment_way"/>?academyId=${param.academyId}&tab=payway"  ${param.tab == 'payway' ? "class=\"current\"" : ""}>缴费方式</a></li>
		<li><a href="<s:url value="view_academy_rebate_fee_subject"/>?academyId=${param.academyId}&tab=arfs" ${param.tab=='arfs' ? "class=\"current\"" : ""}>返款费用科目</a></li>
		<!-- <a href="<s:url value="/enrollment/list_policy_fee"/>?academyId=${param.academyId}&tab=6"  ${param.tab==6 ? "class=\"current\"" : ""}>院校收费政策</a> -->
		<!-- <li><a href="<s:url value="view_academy_rebate_policy"/>?academyId=${param.academyId}&tab=rp" ${param.tab=='rp' ? "class=\"current\"" : ""}>院校返款政策</a></li> -->
	</ul>
</div>
