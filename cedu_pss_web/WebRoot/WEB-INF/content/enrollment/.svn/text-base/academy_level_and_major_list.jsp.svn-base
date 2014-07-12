<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		
		<!-- 自己定义的ajax回调函数 -->
		<jc:plugin name="enrollment_academy_level_and_major"/>
		
		<jc:plugin name="loading"/>
		
    	<!-- 整体样式 -->
		<jc:plugin name="default" />
		
		<jc:plugin name="tab" />
		
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		
    <title>院校设置</title>
    
    <style type="text/css">
    .noborder { border: 0;rules=none; !important;}
    .noborder td, .noborder tr, .noborder tbody { border: 0 !important; }
    
    .showdialog{display: none;}
    *{ margin:0; padding:0;}  
	.checkbox_style{vertical-align:middle}  
	.checkbox_style input{vertical-align:middle;}  
	body{font-family:tahoma;font-size:12px;}  
    </style>
    <%-- 加载院校招生批次及其对应层次和专业 --%>
    <a:ajax parameters="{academyId:'${academyId}'}" 
    		successCallbackFunctions="ajax_load_academy_level_and_major" 
    		urls="/enrollment/list_academy_level_major_by_flag" 
    		pluginCode="001" isZebraCrossing="false"/>
    <%-- 加载层次复制需要的院校批次  同时弹出复制添加层 --%>
    <a:ajax parameters="{academyId:'${academyId}',batchid:presentbatchid}" 
    		successCallbackFunctions="ajax_load_copy_academy_batch" 
    		urls="/enrollment/list_other_academy_enroll_batch_by_academyId" 
    		pluginCode="002" isZebraCrossing="false"/>
    <%-- 加载院校全部院校层次,已设置过的置灰为不可选,未设置的可选 --%>		
    <a:ajax parameters="{academyBatchId:presentbatchid,copybatchid:$('#copy_academy_batch').val()}" 
    		successCallbackFunctions="ajax_load_copy_academy_level" 
    		urls="/enrollment/list_copy_level_by_deleteflag" 
    		pluginCode="003" isZebraCrossing="false"/>
    <%-- 复制层次和专业 --%>
    <a:ajax parameters="{allcopylevelids:allcopylevelids,presentbatchid:presentbatchid,copybatchid:$('#copy_academy_batch').val()}" 
    		traditional="true"
    		successCallbackFunctions="ajax_save_copy_academy_level_major" 
    		urls="/enrollment/copy_academy_level_and_major" 
    		pluginCode="004" isZebraCrossing="false"/>
    <%-- 加载所有基础数据--层次 --%>
    <a:ajax parameters="{academyBatchId:academyBatchId}" 
    		successCallbackFunctions="ajax_install_level" 
    		urls="/enrollment/list_all_level_by_deleteflag" 
    		pluginCode="005" isZebraCrossing="false"/>
    <%-- 批量添加院校Level --%>
    <a:ajax parameters="{alllevelid:alllevelids,'academyLevel.academyEnrollBatchId':academyBatchId}" 
    		successCallbackFunctions="ajax_save_academy_level" 
    		traditional="true"
    		urls="/enrollment/add_academy_level" 
    		pluginCode="006" isZebraCrossing="false"/>
    <%-- 加载所有院校基础数据--专业 --%>
    <a:ajax parameters="{academyId:'${academyId}',academyLevelId:levelid}" 
    		successCallbackFunctions="ajax_install_academy_major" 
    		urls="/enrollment/list_all_majors_by_flag" 
    		pluginCode="007" isZebraCrossing="false"/>
    <%-- 批量添加院校major --%>
    <a:ajax parameters="{allmajorids:allmajorids,'academyMajor.academyLevelId':levelid}" 
    		successCallbackFunctions="ajax_save_academy_major" 
    		traditional="true"
    		urls="/enrollment/add_academy_major" 
    		pluginCode="008" isZebraCrossing="false"/> 
    <%--加载专业复制需要的院校批次  同时弹出复制添加层 --%>
    <a:ajax parameters="{academyId:'${academyId}',batchid:presentbatchid}" 
    		successCallbackFunctions="ajax_load_copy_major_academy_batch" 
    		urls="/enrollment/list_other_academy_enroll_batch_by_academyId" 
    		pluginCode="009" isZebraCrossing="false"/> 
    <%--根据招生批次初始化 其他批次中 所有和当前层次相同的层次 --%>  		
    <a:ajax parameters="{copybatchid:$('#copy_major_academy_batch').val(),'level.id':academylevelid,'level.name':levelname}" 
    		successCallbackFunctions="ajax_load_level_by_batch" 
    		urls="/enrollment/list_levels_by_deleteflag" 
    		pluginCode="010" isZebraCrossing="false"/> 
   	<%--根据招生批次初始化除当前层次以外的所有已设置的层次 --%>  		
    <a:ajax parameters="{academyLevelId:academylevelcode,copyAcademyLevelId:$('#copy_major_academy_level').val()}" 
    		successCallbackFunctions="ajax_load_major_by_batch_and_level" 
    		urls="/enrollment/list_copy_major_by_deleteflag" 
    		pluginCode="011" isZebraCrossing="false"/> 
    <%--保存复制的层次到当前选中的层次下 --%>  		
    <a:ajax parameters="{'academyMajor.academyLevelId':academylevelcode,allcopymajorids:copymajorids}" 
    		successCallbackFunctions="ajax_copy_major" 
    		traditional="true"
    		urls="/enrollment/copy_academy_major" 
    		pluginCode="012" isZebraCrossing="false"/> 
    <script type="text/javascript">
    	var presentbatchid=0;//当前层次所属招生批次id
    	var allcopylevelids=[];//保存选中的被复制的levelid
    	var academyBatchId=0;//加载所有基础数据--层次函数中用到的院校招生批次ID
    	var banchname="";//招生批次名称
    	var alllevelids=[];//需要添加的levelid集合
    	var levelname="";//加载所有院校基础数据--专业 需要的levelname
    	var levelid=0;//加载所有院校基础数据--专业 需要的levelid
    	var allmajorids=[];//保存需要添加的院校专业ID
    	var academylevelid=0;//当前选中的基础层次id
    	var copymajorids=[];//保存选中的被复制的majorid
    	var academylevelcode=0;//当前选中的院校层次id
    	var levelname="";//基础层次名称
    	var isallcheck=false;
    	
       	$(function(){	
   			ajax_001_1(); 
   		});
   		//获取当前招生批次ID
   		function getPresentBatchId(id){
   			presentbatchid=id;
   			ajax_002_1();
   		}
   		//获取当前层次ID(复制专业时需要)
   		function getPresentLevelis(id,code,batchid,name){
   			academylevelid=id;
   			academylevelcode=code;
   			presentbatchid=batchid;
			levelname=name;
   			ajax_009_1();
   		}
   		//复制层次和专业
		function copyAcademyLevelMajor(){
			var chkarray=[];
	   		$("input[class='level_checkbox']:checked").each(function () { 
				chkarray.push(this.value);
			});
			allcopylevelids=chkarray;
			ajax_004_1();
		 }
		//复制专业
		function copyAcademyMajor(){
			var chkarray=[];
	   		$("input[class='major_checkbox']:checked").each(function () { 
				chkarray.push(this.value);
			});
				copymajorids=chkarray;
				ajax_012_1();
		 }
	   	 //加载所有基础数据--层次
	    	function installBaseLevel(batchid,name){
	    		jQuery('#addbatchid').val(batchid);//招生批次id
	    		academyBatchId=batchid;
	    		banchname=name;
	    		ajax_005_1();
	       } 
		    //全选/取消(for_copy)
		   function checkAllOrCancelForCopy(classname,checkboxid){
    			if($("#"+checkboxid).attr("checked")){
    				 $("input[class='"+classname+"']:checkbox").attr("checked",true);
    			}else{ 
    				 $("input[class='"+classname+"']:checkbox").attr("checked",false);
		   		}
		   }    
		       	
		   //全选/取消
		   function checkAllOrCancel(formid,checkboxid){
    			if($("#"+checkboxid).attr("checked")){
    				 $("#"+formid+" :checkbox").attr("checked",true); 
    			}else{ 
    				$("#"+formid+" :checkbox").attr("checked",false);
		   		}
		   }
		   //批量添加院校Level
		   function addAcademyLevel(){
		   		var chkarray=[];
		   		
		   		$("#addAcademyLevelForm input:checked").each(function () { 
					chkarray.push(this.value);
				});
				if(chkarray.length==0){
					show('select_at_least_one','信息提示:',250,120);
				}else{
					alllevelids = chkarray;
					ajax_006_1();
			   }
		   }
		 	//加载所有院校基础数据--专业
		    	function installMajor(id,name){
		    		$('#addacademylevelid').val(id);
		    		levelname=name;
		    		levelid=id;
		    		ajax_007_1();
		       } 
		  //批量添加院校major
		   function addAcademyMajor(){
		   		var chkarray=[];
		   		$("#addAcademyMajorForm input:checked").each(function () { 
							chkarray.push(this.value);
				});
				if(chkarray.length==0){
					show('select_at_least_one','信息提示:',250,120);
				}else{
					allmajorids=chkarray;
					ajax_008_1();   
			   }
		   }
		
    </script>

  </head>
  
  <body>
  		<!--头部层开始 -->
		<head:head title="招生计划">
		</head:head>
		<!--主体层开始 -->
		<body:body>
					<!--Menu Begin-->
						<%@ include file="_tab/academy_enroll_tab.jsp" %>
					<!--Menu End-->
					
					<!--Left Begin-->
					
					<table class="gv_table_2">
				  		<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">院校名称：${academyName}</th>
						</tr>
					</table>
						
					<table class="gv_table_2" id="levelandmajortable" border="0" cellpadding="0" cellspacing="0">
						<thead>
							<tr>								
								<th >学籍批次</th>
								<th >层次</th>
								<th >专业</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<!--Left End-->	
		  		</body:body>
		  
		  <!-- 添加层次 弹出层 -->
		  <div id="add_academy_Level_div" class="showdialog" >
		  	  <table class="gv_table_2" id="top_level_table">
			 	 <thead></thead>
			  </table>
			 <form id="addAcademyLevelForm">
			  	 <table  width="100%" id="academy_level_table" class="add_table">
			  	 	<thead></thead>
			  	 	<tbody>
			  	 	</tbody>
			  	 	<tfoot>
			  	 		<tr>
			  	 			<td colspan="6" align="center">
			  	 				<input id="addbatchid" type="hidden" value=""/>
			  	 				<input type="button" value="保存" class="btn_black_61" onclick="addAcademyLevel()"/>
			  	 			</td>
			  	 		</tr>
			  	 	</tfoot>
			  	 </table>	
		  	 </form>
		  </div>
		  
		  <!-- 添加院校专业 弹出层 -->
		  <div id="add_academy_major_div" class="showdialog">
		  	  <table class="gv_table_2" id="top_major_table">
			 	 <thead></thead>
			  </table>
			 <form id="addAcademyMajorForm">
			  	 <table  width="100%" id="academy_major_table" class="add_table">
			  	 	<thead></thead>
			  	 	<tbody>
			  	 	</tbody>
			  	 	<tfoot>
			  	 		<tr>
			  	 			<td colspan="6" align="center">
			  	 				<input id="addacademylevelid" type="hidden" value=""/>
			  	 				<input type="button" value="保存" class="btn_black_61" onclick="addAcademyMajor()"/>
			  	 			</td>
			  	 		</tr>
			  	 	</tfoot>
			  	 </table>	
		  	 </form>
		  </div>
		  <!-- 复制院校层次 弹出层 -->
		  <div id="copy_academy_level_div" class="showdialog">
			  	<table width="100%" class="add_table">
					<tr>
						<td colspan="2">来源批次：&nbsp;<select id="copy_academy_batch" class="txt_box_130" onchange="ajax_003_1()"></select></td>
					</tr>
				</table>
			<form id="copyAcademyLevelForm">
				<table width="100%" class="add_table" id="copy_academy_level_table" style="display: none;">
				  <thead>
				    <tr>
				       <td align="center" width="150px">名称</td>
				       <td><div class="checkbox_style"><input id="copy_level_chk" type="checkbox" style="vertical-align:middle" onclick="checkAllOrCancelForCopy('level_checkbox','copy_level_chk')"/>&nbsp;全选</div></td>
				    </tr>
				  </thead>
				  <tbody></tbody>
				  <tfoot>
					<tr>
						<td align="center" colspan="2">
							<input type="button" value="复制" class="btn_black_61" onclick="copyAcademyLevelMajor()"/>
						</td>
					</tr>
				 </tfoot>
				</table>
		  	</form>
		  </div>
		  <!-- 复制院校专业 弹出层 -->
		 <div id="copy_academy_major_div" class="showdialog">
			<table class="add_table">
				<tr>
					<td>批次：<select id="copy_major_academy_batch" class="txt_box_130" onchange="ajax_010_1()"></select></td>
					<td>层次：<select id="copy_major_academy_level" class="txt_box_130" onchange="ajax_011_1()"></select></td>
				</tr>
			</table>
			<form id="copyAcademyMajorForm">
				<table class="gv_table_2" id="copy_academy_major_table" style="display: none;">
				  <thead>
					<th>名称</th>
					<th width="63" align="center"><input id="copy_major_chk" type="checkbox" style="vertical-align:middle" onclick="checkAllOrCancelForCopy('major_checkbox','copy_major_chk')"/>&nbsp;全选</th>
				  </thead>
				  <tbody>
				  </tbody>
				  <tfoot>
					<tr>
						<td align="center" colspan="2">
							<input type="button" value="复制" class="btn_black_61" onclick="copyAcademyMajor()"/>
						</td>
					</tr>
				  </tfoot>
				</table>
				<div id="contentdiv" align="right" style="font-size: 6;font-weight: bold;display: none;">注:默认勾选的专业状态为已设置,不能执行复制操作</div>
			</form>
		 </div>
		  <div id="select_at_least_one" style="display:none;font-weight:bold" align="center"><br/>操作失败，请至少选择一项数据！</div>
		  <div id="error_for_academy_Level" style="display:none;font-weight:bold" align="center"><br/>暂无可供选择的层次！<br/></div>
		  <div id="error_for_academy_Major" style="display:none;font-weight:bold" align="center"><br/>暂无可供选择的专业！<br/></div>
		  <div id="success_Msg" style="display:none;font-weight:bold" align="center"><br/>添加成功！<br/></div>
		  <div id="error_Msg" style="display:none;font-weight:bold" align="center"><br/>服务器繁忙，请您稍后尝试本次操作！<br/></div>
		  <div id="major_have_students" style="display:none;font-weight:bold" align="center"></div>
  </body>
</html>