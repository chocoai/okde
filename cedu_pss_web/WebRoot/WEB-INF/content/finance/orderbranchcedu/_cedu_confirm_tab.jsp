<%@ page language="java" pageEncoding="UTF-8"%>
<div>
	<ul id="menu">
		<li><a href="confirm_cedu_order_branch_cedu?tab=1" title="多笔现金汇总部" ${ param.tab==1 ? "class=\"current\"" : ""} ${ param.tab == null ? "class=\"current\"" : ""}>现金汇款总部</a></li>
		<li><a href="confirm_pos_fee_payment_detail?tab=2" title="POS直汇总部" ${param.tab==2 ? "class=\"current\"" : ""}>POS直汇总部</a></li>
		<li><a href="confirm_ebank_fee_payment_detail?tab=3" title="网银总部" ${param.tab==3 ? "class=\"current\"" : ""}>网银总部</a></li>
		<li><a href="confirm_third_fee_payment_detail?tab=4" title="第三方支付" ${param.tab==4 ? "class=\"current\"" : ""}>第三方支付</a></li>
		<!-- <li><a href="confirm_ebank_fpd?tab=4" title="网银" ${param.tab==4 ? "class=\"current\"" : ""}>网银</a></li> -->
	</ul>
</div>