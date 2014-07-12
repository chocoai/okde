<%@ page language="java" pageEncoding="UTF-8"%>
<div>
	<ul id="menu">
		<li><a href="list_cedu_confirm_branch_playmoney_academy?tab=1" title="现金汇院校" ${ param.tab==1 ? "class=\"current\"" : ""} ${ param.tab == null ? "class=\"current\"" : ""}>现金汇院校</a></li>
		<li><a href="confirm_pos_academy_fpd?tab=2" title="POS直汇院校" ${param.tab==2 ? "class=\"current\"" : ""}>POS直汇院校</a></li>
		<li><a href="confirm_ebank_academy_fpd?tab=3" title="网银院校" ${param.tab==3 ? "class=\"current\"" : ""}>网银院校</a></li>
	</ul>
</div>