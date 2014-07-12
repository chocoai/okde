<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		
    	<!-- 整体样式 -->
		<jc:plugin name="default" />
		
		<jc:plugin name="tab" />
		
		<!-- 导入增删改查 加载的JS -->
		<jc:plugin name="basesetting_fee_subject"/>
		
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		
		<!--ajax 载入特效  -->
		<jc:plugin name="loading"/> 
		
    <title>基础设置</title>
    <script type="text/javascript">
    	function isShow(obj,aObj){
	 			if($("#"+aObj).html()=="隐藏"){
	 				$('#'+obj).css("display","none");
	 				$('#'+aObj+'img').hide();
	 				//隐藏列表时清空相对应table > tfoot
	 				$("div[id='"+obj+"'] table > tfoot").html("");
	 				$("#"+aObj).html("显示");
	 			}else{
	 				$('#'+obj).css("display","block");
	 				$('#'+aObj+'img').show();
	 				$("#"+aObj).html("隐藏");
	 			}
	 	}
    </script>
 </head>
  
 	<body>
 		<!--头部层开始 -->
		<head:head title="基础设置">
		</head:head>
		<!--主体层开始 -->
		<body:body>
					<!--Menu Begin-->
				<div>
					<s:include value="../common/menu.jsp" />
				</div>
					<!--Menu End-->
					
					<!--Left Begin-->
					 <table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">缴费方式设置</th>
							<th style="text-align:right; font-weight:bold;"><div id="conmenu">
									<a href="javascript:isShow('fee_wayDiv','showfee_type');" id="showfee_type">隐藏</a>
							</div></th>
						</tr>
					</table>
					<div id="fee_wayDiv" style="display:block;">
						<table class="gv_table_2" id="feewaytable">
							<thead>
								<tr>
									<th >缴费方式名称</th>
									<th >编码</th>
								</tr>
							</thead>
							<tbody></tbody>	
						</table>
					</div>
					<!-- 
					<table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">默认缴费次数设置</th>
								<th style="text-align:right; font-weight:bold;">
									<div id="conmenu">
										<a href="#" onclick="addFeeBatch()" id="showfee_batchimg" style="display: none;">
											<img src="<ui:img url="images/title_menu/icon_add.gif" />" />
										增加</a>
										<a href="javascript:isShow('fee_batchDiv','showfee_batch');" id="showfee_batch">显示</a>
								</div>
							</th>
						</tr>
					</table>
					<div id="fee_batchDiv" style="display:none;">
						<table class="gv_table_2" id="feebatchtable">
							<thead>
								<tr>
									<th >费用批次名称</th>
									<th >编码</th>
									<th >开始时间</th>
									<th >结束时间</th>
									<th >所属年份</th>
									<th >操作</th>	
								</tr>	
							</thead>
							<tbody></tbody>
							<tfoot></tfoot>
						</table>
					</div>
					 -->
				    <table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">费用科目设置</th>
							<th style="text-align:right; font-weight:bold;">
								<div id="conmenu">
									<a href="#" onclick="addFeeSubject()" id="showfee_subjectimg" style="display: none;">
										<img src="<ui:img url="images/title_menu/icon_add.gif" />" />
									增加</a>
									<a href="javascript:isShow('fee_subjectDiv','showfee_subject');" id="showfee_subject" >显示</a>
							</div>
							</th>
						</tr>
					</table>
							
					<div id="fee_subjectDiv" style="display:none;">
							<table class="gv_table_2" id="feesubjecttable">
								<thead>
									<tr>
										<th >费用科目名称</th>
										<th >编码</th>
										<th >批次类型</th>
										<th >单次/多次缴费</th>
										<th >操作</th>
									</tr>
								</thead>
								<tbody></tbody>
								<tfoot></tfoot>
							</table>
					 </div>
				<!--	 
				   <table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">费用性质设置</th>
							<th style="text-align:right; font-weight:bold;">
								<div id="conmenu">
									<a href="#" onclick="addFeeProperty()" id="showfee_propertyimg" style="display: none;">
										<img src="<ui:img url="images/title_menu/icon_add.gif" />" />
									增加</a>
									<a href="javascript:isShow('showfee_propertyDiv','showfee_property');" id="showfee_property" >显示</a>
							</div>
							</th>
						</tr>
					</table>
							
					<div id="showfee_propertyDiv" style="display:none;">
							<table class="gv_table_2" id="feepropertytable">
								<thead>
									<tr>
										<th >费用性质名称 </th>
										<th >编码</th>
										<th >操作</th>
									</tr>
								</thead>
								<tbody></tbody>
								<tfoot></tfoot>
							</table>
					 </div>
					 -->
					  <table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">其他应收类型设置</th>
							<th style="text-align:right; font-weight:bold;"> 
								<div id="conmenu">           
									<a href="#" onclick="addBillReceivedWay()" id="showbill_received_wayimg" style="display: none;">
										<img src="<ui:img url="images/title_menu/icon_add.gif" />" />
									增加</a>
									<a href="javascript:isShow('showbill_received_wayDiv','showbill_received_way');" id="showbill_received_way" >显示</a>
							</div>
							</th>
						</tr>
					</table>
							
					<div id="showbill_received_wayDiv" style="display:none;">
							<table class="gv_table_2" id="billreceivedwaytable">
								<thead>
									<tr>
										<th >应收类型名称 </th>
										<th >编码</th>
										<th >操作</th>
									</tr>
								</thead>
								<tbody></tbody>
								<tfoot></tfoot>
							</table>
					 </div>
					 
		  		</body:body>
		   <div id="fill_in_info_error" style="display:none;font-weight:bold" align="center"><br/>请填写完整数据！<br/></div>
		   <div id="success_Msg" style="display:none;font-weight:bold" align="center"><br/>操作成功！<br/></div>
		   <div id="add_error_Msg" style="display:none;font-weight:bold" align="center"><br/>数据库存在重复数据，请重新执行本次操作！<br/></div>
		   <div id="error_Msg" style="display:none;font-weight:bold" align="center"><br/>服务器繁忙，请您稍后尝试操作！<br/></div>
    </body>
</html>