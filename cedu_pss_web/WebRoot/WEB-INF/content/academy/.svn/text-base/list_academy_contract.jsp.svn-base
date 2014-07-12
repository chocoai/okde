<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>院校合同</title>
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
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		
		<script type="text/javascript">
		var ids=0;
		
		//删除合同
		function ajax_delete(data)
		{
			closes('isdeleteDiv');
			search123();
		}
		
		
		</script>
		
		<a:ajax parameters="{'id':ids}"
				successCallbackFunctions="ajax_delete"
				urls="/academy/deleteacademycontract"
		 pluginCode="123"
		  />
		
		<script type="text/javascript">
		
		
			
		/*
		提醒结束日期
		*/
		function remindTime(endDate)
		{
			var myDate = new Date();
			var nowDate=myDate.toLocaleDateString();
			var OneMonth = nowDate.substring(5,nowDate.lastIndexOf ('月'));  
   			var OneDay = myDate.getDay();
    		var OneYear = nowDate.substring(0,nowDate.indexOf ('年'));  
  			var nowd=OneYear+'-'+OneMonth+'-'+OneDay;
  			var sDate1=nowd.replace(/-/g, "/");
  			var sDate2=endDate.replace(/-/g, "/");
  			var d1=new Date(sDate1);
  			var d2=new Date(sDate2);
  			var time= d2.getTime() - d1.getTime();
			var days = parseInt(time / (1000 * 60 * 60 * 24));
    		if(days<30)
    		{
    			return '<span style="color:red" >'+endDate+'</span>';
    		}else
    		{
    			 return endDate;
    		}
    		
    		
           
		}
		
		
		
		
		/*
		取消删除
		*/
		function cdiv()
		{
			closes('isdeleteDiv');
		}
		
		
		/*
		删除合同
		*/
		function deleteFun(id)
		{
			ids=id;
			show('isdeleteDiv','提示',250,150);
			
		}
		
		function getcontractname(name)
		{
			return name;
		}
		
		/*
		显示签约人
		*/
		function userNameValue(name)
		{
			return name;
		}
		
	
			</script>
	
  </head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="院校名称：${academy.name}">
		</head:head>
		<!--主体层开始 -->
		<body:body>
		
			<!--Menu Begin-->
			<%@ include file="_tab_title/academy_tab.jsp" %>
			<!--Menu End-->
		<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">院校合同</th>
					<th>
						<div style="text-align:right;">
							<img src="<ui:img url="images/icon_add.gif"/>" width="16" height="16" align="absmiddle"  />&nbsp;<a href="<uu:url url="/academy/add_academy_contract" />?id=${param.id}"  >新增院校合同</a>
						</div>
					</th>
				</tr>
			</table>
		<page:plugin 
				pluginCode="123"
				il8nName="academy"
				searchListActionpath="listacademycontract"
				searchCountActionpath="countacademycontract"
				columnsStr="#contractname;#userName;signupDate;beginDate;endDate;note;"
				customColumnValue="0,getcontractname(name);1,userNameValue(userName);4,remindTime(endDate)"
				pageSize="10"
				delete="json,deleteFun,id"
				update="http,/academy/update_academy_contract,id,id,_blank"
				view="http,/academy/view_academy_contract,id,id,_blank"
				ontherOperatingWidth="80px"	
				params="'academyId':'${param.id}'"
		/>
		
		
		<div id="isdeleteDiv" style="display:none">
		<div style="float:inherit">确定要删除吗？</div><br/>
		<input type="button" value="确定" id="btnok" name="btnok" onclick="ajax_123_1()" class="btn_black_61"/> 
		<input type="button" value="取消" id="tt" name="tt" onclick="cdiv()" class="btn_black_61"/> 
		
		</div>			  
	</body:body>
	
	
  </body>
</html>
