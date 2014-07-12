<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<div>
	<ul id="menu">
			
		<li><a href="<uu:url url="/meterial/meterialcategory/index_meterialcategory"/>?viewtype=1"  title="" <s:if test="viewtype==1">class='current'</s:if>>物料分类</a></li>
	
		<li><a href="<uu:url url="/meterial/meterialcategory/index_meterialstorage"/>?viewtype=2" title="" <s:if test="viewtype==2">class="current"</s:if>>入库方式</a></li>
		
		<li><a href="<uu:url url="/meterial/meterialcategory/index_meterialstoreroom"/>?viewtype=3" title="" <s:if test="viewtype==3">class="current"</s:if>>库房设置</a></li>
		
		 
		
		
	</ul>
</div>