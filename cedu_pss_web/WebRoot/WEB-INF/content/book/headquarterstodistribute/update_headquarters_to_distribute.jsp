<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>总部派发</title>			
		    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- jquery-loading -->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		
		<script type="text/javascript">
		
		
		
		function addshow()
		{
			show('msgDiv','提示',200,100);
		}
		
		function asd()
		{
			var aa=[];
			for(var i=0;i<10;i++)
			{
			if($("#pf"+i).val()!="")
			{
			aa.push($("#pf"+i).val());
			}
			else
			{
			aa.push(0);
			}
			}
			alert(aa);
		}
		
			function updatePurchaseRequisition(){
 		 
 			var array=[];//派发数量数组		 
 			var arrayIds="";//填写的派发数量数组拼接起来的字符串	
 			var purchaseRequisitionId=$("#purchaseRequisitionId").val();	 
 			var branchId=$("#branchId").val();
 			//循环获取数组中的派发数量
					for(var i=0;i<10;i++)
					{
						if($("#pf"+i).val()!="")
						{
							array.push($("#pf"+i).val());
						}
						else
						{
							array.push(0);
						}
						//选中的拼接成字符串
						arrayIds=array.join(",");
						//通过表单实现js post提交
					}
				document.write("<form action='update_headquarters_to_distribute?array="+arrayIds+"&purchaseRequisitionId="+purchaseRequisitionId+"&branchId="+branchId+"'  method='post' name='lecturerForm' style='display:none'>");
				document.write("</form>");
				document.lecturerForm.submit();
 
				 }
		
	
	</script>
</head>
  <body>

  <div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a class="icon">总部派发</a> </li>
		</ul>
	</div>
	<!--<div id="conmenu">
	 <img class="img_icon" src="../images/icon_title_return.jpg" />
	 <a href="javascript:history.go(-1);">返回</a>
	</div>-->
</div>

<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2"> 	    
	<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">申购单明细</th>
				</tr>
					</table>

					
					
					<table  class="add_table" >
			<tr>
				<td width="15%"  align="right">申购单编号:</td>
				<td width="85%" align="left">${code }</td>
			</tr>
		
			<tr>
				<td width="15%"  align="right">申购单创建人:</td>

				<td width="85%" align="left">${requiredName }</td>
			</tr>
			
			
		</table>
					
		
	
		

		<table class="gv_table_2">
			<tr>
				<th>申购单编号</th>
				<th>中心名称</th>

				<th>教材名称</th>
				<th>版次</th>
				<th>采购单价</th>
				<th>申购数量</th>
				<th>已订购数量</th>
				<th>已派发数量</th>

				<th>现派发数量</th>
				<th>状态</th>
			</tr>
			<input type="hidden" id="purchaseRequisitionId" value="${purchaseRequisitionId}"/>
			 <input type="hidden" id="branchId" value="${branchId}"/>
			
			<s:iterator var="purchase" value="purchaseList" status="x">
			<tr>
				<td align="center">${code }	</td>
				<td align="center">${branchName }</td>
				<td align="center"><s:property value="#purchase.bookname"/></td>
				<td align="center"><s:property value="#purchase.bookedition"/></td>
				
				<td align="center"><s:property value="#purchase.purchasePrice"/>元</td>
				<td align="center"><s:property value="#purchase.requiredAmount"/></td>
				<td align="center"><s:property value="#purchase.orderedAmount"/></td>
				<td align="center"><s:property value="#purchase.hasDistributed"/></td>
 
				<c:if test="${purchase.status==5}">	
				<td align="center"><input type="hidden" value="0" id="pf<s:property value="#x.index"/>"  /></td>
				<td align="center">订购完<img src="../images/cedu/icon/icon_confirm.gif" /></td>
				
				</c:if>
				<c:if test="${purchase.status!=5}">	
				<td align="center"><input type="text" value="" id="pf<s:property value="#x.index"/>"  /></td>
				<td align="center">订购中<img src="../../images/title_menu/icon_confirm.gif" /></td>
				</c:if>
			</tr>
		 	</s:iterator>
			
		 
			
				
			
		</table>
		
		
		<table class="gv_table_2">
		  		<tr>
				
				<th><input name="" type="button" class="btn_black_130" value="生成派发单" onclick="updatePurchaseRequisition()" />

			
				</th>
				</tr>
				
			
		</table>
					
					
		
	
        
   </div>
  </div>
 </div>
	
	
	<div id="msgDiv" style="display:none">
		操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />

	</div>
	
	
</body>
</html>