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
		
    <title>基础设置</title>
 	   <script type="text/javascript">
	    	//全局变量 
	    	var count=0;
	    	
	    	//增加一行输入框，用于填写学制信息
	    	function addEnrollmentChangeType(){
	    		var list='';
	    		count=++count;
	    		list=$('#enrollmentchangetypetable > tbody').html();
				list+='<tr id="enrollmentchangetypetr'+count+'">';
			    list+='<td align="center">'; 
			    list+='<input id="enrollmentchangetypename'+count+'" class="txt_box_150" />';
			    list+='</td>'; 
			    list+='<td align="center">'; 
			    list+='<input id="enrollmentchangetypecode'+count+'" class="txt_box_150" />';
			    list+='</td>'; 
			    list+='<td align="center">';  
			    list+='<a href="#" onclick="saveEnrollmentChangeType('+count+')">保存</a>';	 
			    list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelEnrollmentChangeType('+count+')">取消</a>'; 
			    list+='</td>';
			    list+='</tr>';
			    $('#enrollmentchangetypetable > tbody').html(list);
			   
	    	}
	    	//取消一行尚未保存的输入框
	    	function cancelEnrollmentChangeType(count){
	    		$("#enrollmentchangetypetr"+count).remove();
	    	}
	    	//保存当前一行输入的学制信息
	    	function saveEnrollmentChangeType(count){
	    		if($("#enrollmentchangetypename"+count).val()==null||$("#enrollmentchangetypename"+count).val()==""||$.trim($("#enrollmentchangetypename"+count).val())==""){
	    			alert("请录入完整数据");
	    		}else if($("#enrollmentchangetypecode"+count).val()==null||$("#enrollmentchangetypecode"+count).val()==""||$.trim($("#enrollmentchangetypecode"+count).val())==""){
	    			alert("请录入完整数据");
	    		}else{
	    			$.post('/cedu/basesetting/enrollmentchangetype/addenrollmentchangetype', {enrollmentchangetypename:$("#enrollmentchangetypename"+count).val(),enrollmentchangetypecode:$("#enrollmentchangetypecode"+count).val()},
				  	function(data) 
				  	{
				  		if(data.rltbool)
				  		{
				  			//loadEnrollmentChangeType();
				  		}else{
				  			alert("数据异常，请稍后尝试添加操作");
				  			//loadEnrollmentChangeType();
				  		}
				  		
				  	},"json");  
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
				 	<th style="width:20px;"><img src="../../images/title_menu/title_left.gif" /></th>
				 	<th style="text-align:left; font-weight:bold;">学籍异动类别设置</th>
					<th style="text-align:right; font-weight:bold;"><div id="conmenu"><a href="#" onclick="addEnrollmentChangeType()"><img src="../../images/title_menu/icon_add.gif" />添加学籍异动类别</a></div></th>
				</tr>
			</table>
				
			<table class="gv_table_2" id="enrollmentchangetypetable" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th >异动编码</th>
						<th >编异动名称</th>
						<th >操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
			<!--Left End-->	
  		</body:body>
  </body>
</html>
