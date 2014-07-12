<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<div>
	<ul id="menu">
			
		<li><a href="<uu:url url="/book/index_category_book"/>?viewtype=1" title="" <s:if test="viewtype==1">class="current"</s:if>>教材分类</a></li>
	
		<li><a href="<uu:url url="/book/index_supplier_category"/>?viewtype=2" title="" <s:if test="viewtype==3">class="current"</s:if>>书商分类</a></li>
		
		<li><a href="<uu:url url="/book/index_measuring_unit"/>?viewtype=3" title="" <s:if test="viewtype==4">class="current"</s:if>>计量单位</a></li>
		
		<li><a href="<uu:url url="/book/index_settlement_way"/>?viewtype=4" title="" <s:if test="viewtype==5">class="current"</s:if>>结算方式</a></li>
		
		<li><a href="<uu:url url="/book/index_delivered_way"/>?viewtype=4" title="" <s:if test="viewtype==5">class="current"</s:if>>配送方式</a></li>
		
		<li><a href="<uu:url url="/book/index_storage_mode"/>?viewtype=4" title="" <s:if test="viewtype==5">class="current"</s:if>>入库方式</a></li>
		
		<li><a href="<uu:url url="/book/index_storeroom"/>?viewtype=4" title="" <s:if test="viewtype==5">class="current"</s:if>>库房设置</a></li>
		
		
	</ul>
</div>