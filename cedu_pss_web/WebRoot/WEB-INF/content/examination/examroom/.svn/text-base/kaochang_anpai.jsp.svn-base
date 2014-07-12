<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<jc:plugin name="loading" />
		<jc:plugin name="validator" />
		<a:ajax 
		parameters="{'examroomId':$('#examroomId').val(),'classroomId':$('#classroomId').val(),'invigilatorIds':$('#invigilatorIds').val(),'studentNo':$('#studentNo').val(),'bid':$('#bid').val()}"
		successCallbackFunctions="ajax_add" 
		urls="/examination/examroom/add_examroomplan" 
		pluginCode="123" 
		/>
	<script type="text/javascript">
	function del(id)
		{
			jQuery.post('<s:url value="delete_examroomplan"/>',{"id":id},
			        function(data)
			    	{
			    		if(data.results)
			    		{
			    			show('showDialog','删除成功!',150,100);
			    			search0909();
			    		}
			    		else {show('showDialog','删除失败!',150,100);}	
			    	},
			 "json");	
		}
	jQuery(function(){
				//信息提示
				ajax_001_1();
				 $("#invigilatorIds").val("");
				jQuery('#createRemarkBox').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示'
				});	
				
		});
	function ajax_listinvigilator(data)
		{
		if(data.lstrltbool){
			var list='';
			var list1='';
			var i=1;
		if(data.invigilatorlist.length>0){
		$.each(data.invigilatorlist,function(){
			list+='<tr><td align="center"><a href="view_examination.html"></a>';
			list+='<div>'+this.name+'</div></td>'	
			list+='<td valign="top">姓名：'+this.name+'<br />工作单位：'+this.workUnit+'<br />报价：'+this.feeStandard+'元/'+this.feeType+'<br />';
			list+='参加弘成学苑监考：16场次<br />状态：'+this.status+'<br /><br />'	;	
			list+='<select class="txt_box_100" name="major"><option value="">--全部--</option><option value="1">监考</option></select></td></tr>';	
			
			//list1+='<tr><td>'+this.name+'</td><td><input type="checkbox" value="张三" name="check" id="check1"></td></tr>';	
			if(i>0&&i%4==0){list1+='<br/>';}else{
            list1+=this.name+'<input type="checkbox" value="'+this.name+'" name="check" id="check'+i+'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';	
            i++;
         }
					});
	list1+='<input type="button" class="btn_black_61" name="save" onclick="showcheck('+i+')" value="添加"  /><input type="button" class="btn_black_61" value="关闭" onclick="closes(createTeacherBxo);" />';
			
		}else{
		list+='<div>暂时没有相关信息</div>';
		}
		$('#settable > tbody').html(list);
		$('#setbr').html(list1);
		}else{
		alert('服务器异常！');
		}
		}
		 function addComm()
		{
		   var classroomId=$('#classroomId').val();
			var invigilatorIds=$('#invigilatorIds').val();
			var bid=$('#bid').val();
			var studentNo=$('#studentNo').val();
			alert(studentNo+","+bid);
			if($.trim(invigilatorIds) =="")
			{
			   alert('监考老师不能为空');
                return;
			}
			if($.trim(studentNo) =="")
			{
			alert('人数不能为空！');
				return;
				}
			ajax_123_1();
			search0909();
			closes('createRemarkBox');
		}
	function ajax_add(){}
	       function isShow(obj,aObj){
	 			if($("#"+aObj).html()=="隐藏"){
	 				document.getElementById(obj).style.display="none";
	 				$("#"+aObj).html("显示");
	 			}else{
	 				document.getElementById(obj).style.display="";
	 				$("#"+aObj).html("隐藏");
	 			}
	 		}
	//  function ajax_listinvigilator(){}
		function showcheck(ros){
		     var ros=ros;
			  var check="";
				 for(var i=1;i<=ros;i++){
				  if($("#check"+i).attr('checked')==true){
				     // check+=$("#check"+i).val();
				      check+=$("#check"+i).attr("value");
					}
				 }
				 $("#invigilatorIds").val(check);
				closes('createTeacherBxo');
		}
	 		
	</script>
	<a:ajax successCallbackFunctions="ajax_listinvigilator" 
    		urls="/examination/examroom/show_invigilator"
    		pluginCode="001" 
    		/>
	<!-- 添加备注 -->

</head>  
<body>
<div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>考场安排</a> </li>
		</ul>
	</div>
	<div id="conmenu">
	 <img src="<ui:img url="images/icon_title_return.jpg"/>" />
	 <a href="#" onclick="history.go(-1);">返回</a>
	</div>
</div>
<div class="block">
	<div class="public_table_bg_766">
		<div class="tb_table_default_2">
		<!--Menu Begin-->
			<div>
				<ul id="menu">
				<li><a href="view_test_class.html" title="" class="current">基本信息</a></li>
				</ul>
			</div>
			<!--Menu End-->
			<!--Left Begin-->
			<div style="float:left; width:700px;">
			<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">场次信息</th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td class="lable_150">场次：</td>
					<td>${examplan.plan}</td>
				</tr>
				<tr>
					<td class="lable_150">层次：</td>
					<td>${examplan.levelname}</td>
				</tr>
				<tr>
					<td class="lable_150">专业：</td>
					<td>${examplan.majorname}</td>
				</tr>
				<tr>
					<td class="lable_150">批次：</td>
					<td>${examplan.batchname}</td>
				</tr>
				<tr>
					<td class="lable_150">考试人数：</td>
					<td>${examplan.planCount}</td>
				</tr>
				<tr>
					<td class="lable_150">考试时间：</td>
					<td>${examplan.examDate}&nbsp;${examplan.examBeginTime}&nbsp;&nbsp;${examplan.examEndTime}</td>
				</tr>
			</table>
		   <table class="gv_table_2">
				      <tr>
						<th style="width:20px;"><img src="../images/title_left.gif" /></th>
						<th style="text-align:left; font-weight:bold;">安排情况</th>
						<th style="text-align: right; font-weight:bold;"> <a href="javascript:show('createRemarkBox','新增安排',450,350);" >安排</a></th>
					</tr>
			</table>
			<table class="gv_table_2" id="resTable" border="0" cellpadding="0" cellspacing="0">
	    	<tbody>
			 <page:plugin 
				pluginCode="0909"
				il8nName="examschedule"
				searchListActionpath="list_examroomplan"
				searchCountActionpath="count_examroomplan"
				columnsStr="examRoomname;classroomname;invigilatorIds;studentNo"
				customColumnValue=""
				update="http,examination/examroom/update_examroomplan,id,id,_self"
				delete="json,del,id"
				pageSize="5"
				ontherOperatingWidth="80px"	
		     />  	 
	    	</tbody>
	    </table>
			</div>
			<!--Left End-->
			
			<!--Line Begin-->
			<div style="float:left;width:4px; height:500px; background-color:#3394C1; margin-left:2px; margin-right:2px;">
			</div>
			<!--Right Begin-->
			<div style="margin-left:708px;">
			<table class="gv_table_2">
				<tr>
					<th style="width:20px;"><img src="<ui:img url="images/title_left.gif"/>" /></th>
					<th style="text-align:left; font-weight:bold;">监考老师</th>
				</tr>
			</table>
			<table  id="settable" class="add_table"  border="0" cellpadding="2" cellspacing="2">
			<tbody>
			</tbody>	
			</table>
			</div>	
  		</div>
  	</div>
  </div>
  <!-- 添加考场安排-->
<div id="createRemarkBox" style="display:none;">
<input type="hidden" name="bid" id="bid" value="${id}"/>

    	<table class="add_table">
			<tr>
            	<td class="lable_100">考场：</td>
                <td><s:select list="%{examroomlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="examroomId" id="examroomId" cssClass="txt_box_130"/></td>
            </tr>
			<tr>
            	<td class="lable_100">教室：</td>
                <td><s:select list="%{classroomlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="classroomId" id="classroomId" cssClass="txt_box_130"/>【教室容量是30人】</td>
            </tr>
			<tr>
            	<td class="lable_100">监考老师：</td>
                <td><input type="text" class="txt_box_130" name="examroomplan.invigilatorIds" id="invigilatorIds" onclick="show('createTeacherBxo','选择老师',620,320);"/></td>
            </tr>
			<tr>
            	<td class="lable_100">安排人数：</td>
                <td><input type="text" name="examroomplan.studentNo" id="studentNo" class="txt_box_130"/>【安排人数不能超过教室容量】</td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                	<input type="button" class="btn_black_61" name="save" onclick="addComm();" value="添加"  />
                	<input type="button" class="btn_black_61" value="关闭" onclick="closes('createRemarkBox');" />
                </td>
            </tr>
        </table>
</div>
 
  <!-- 选择老师-->
<div id="createTeacherBxo" style="display:none;">
<table  id="settable1" class="add_table"  border="0" cellpadding="2" cellspacing="2">
			<tr><td id="setbr"></td></tr>	
			</table>
</div>
<div id="showDialog" style="display:none">

</div>
</body>
</html>


