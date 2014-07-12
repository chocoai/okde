<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		
    	<!-- 整体样式 -->
		<jc:plugin name="default" />
		
		<jc:plugin name="tab" />
		
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		
		<jc:plugin name="loading"/>
		
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		
    <title>院校招生批次设置</title>
    <script type="text/javascript">
    	<%--设置招生批次启用状态(修改启用/停用/结束状态) --%>
    	function ajax_update_enroll_status(data){
    			if(data.updaterltbool)
		  		{
		  			show('msgDiv','提示',200,100);
		  		}else{
		  			show('error_Msg','信息提示:',300,100);
		  		}	
		  		ajax_001_1(); ;
    	}
    	<%--设置是否是当前招生子批次--%>
    	function ajax_set_present_subbatch(data){
 			if(data.updaterltbool)
	  		{
	  			show('msgDiv','提示',200,100);
	  			
	  		}else{
	  			show('error_Msg','信息提示:',300,100);
	  		}	
	  		ajax_001_1(); ;		   	
    	}
    	<%--调用初始化 增加院校招生批次对话框(弹出层)--%>
    	function ajax_install_academy_batch(data){
    		if(data.globalbatchlist!=null&&data.globalbatchlist.length>0){
				$("#selectglobalbatch").empty();
				$.each(data.globalbatchlist, function(){
					$("#selectglobalbatch").append('<option value="'+this.id+'">'+this.batch+'</option>'); 
			 	});
			 	show('addBatchDiv','添加院校招生批次',300,140);
				//获取对象并加载清空内容
				var ui = jQuery('#addBatchDiv');
				jQuery('#addBatchForm', ui)[0].reset();
			}else{
				show('errorforglobalbatch','信息提示:',360,100);
			}	
    	}
    	<%--调用初始化 增加院校招生子批次对话框(弹出层)--%>
    	function ajax_install_sub_academy_batch(data){
    		if(data.academyBatchlist!=null&&data.academyBatchlist.length>0){
				$("#selectacademybatch").empty();
				$.each(data.academyBatchlist, function(){
					$("#selectacademybatch").append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
			 	});
			 	show('addSubBatchDiv','添加院校招生子批次',400,200);
				//获取对象并加载清空内容
				var ui = jQuery('#addSubBatchDiv');
				jQuery('#addSubBatchForm', ui)[0].reset();
			}else{
				show('errorforacademybatch','信息提示:',400,100)
			}		
    	}
    	<%--添加院校招生(学籍)批次 --%>
    	function ajax_add_academy_enrollBatch(data){
    		if(!data.addrltbool){
	  			closes('addBatchDiv');
				show('error_Msg','信息提示:',300,100);
    		}else if(data.errormsg){
    			closes('addBatchDiv');
				show('msgDiv','提示',200,100); 
	  		}else{
	  			show('add_error_Msg','信息提示:',300,100);
	  		}
	  		ajax_001_1(); ;
    	}
    	<%--添加院校招生子批次--%>
    	function ajax_add_sub_academy_enrollBatch(data){
    		if(!data.addrltbool)
	  		{
	  			closes('addSubBatchDiv');
	  			show('error_Msg','信息提示:',300,100);
	  		}else if(data.errormsg){
	  			closes('addSubBatchDiv');
	  			show('msgDiv','提示',200,100);
	  		}else{
	  			show('add_error_Msg','信息提示:',300,100);
	  		}
	  		ajax_001_1(); ;
    	}
    	<%-- 加载院校招生批次及其对应子批次列表 --%>
    	function ajax_load_enroll_batch(data){
    		var list='';
    		if(data.lstrltbool){
				if(data.academyEnrollBatchlist!=null&&data.academyEnrollBatchlist.length>0){
					$.each(data.academyEnrollBatchlist, function(){
						var subsize=(this.subAcademyEnrollBatchlist.length)>0?(this.subAcademyEnrollBatchlist.length):1;
						list+='<tr>';
						list+='<td align="center" rowspan="'+subsize+'">';
						if(this.globalEnrollBatch!=null){
							list+=this.globalEnrollBatch.batch;
						}
					    list+='</td>';
					    list+='<td align="center" rowspan="'+subsize+'">';
					    list+=this.enrollmentName;
					    list+='</td>';
					    list+='<td align="center" rowspan="'+subsize+'">';
					    list+=this.registerName;
					    list+='</td>';
					    list+='<td align="center" rowspan="'+subsize+'">';
					    if(this.isEnable==STATUS_DISABLE){
					    	list+='<input type="radio" name="is_enbale'+this.id+'" id="enabledstatus'+this.id+'" value="'+STATUS_ENABLED+'" onclick="is_enabledstatus('+this.id+')" />启用&nbsp;&nbsp;';
					    	list+='<input type="radio" name="is_enbale'+this.id+'" id="disablestatus'+this.id+'" value="'+STATUS_DISABLE+'" checked="checked" onclick="is_enabledstatus('+this.id+')" />停用';
					    	list+='<input type="radio" name="is_enbale'+this.id+'" id="disablestatus'+this.id+'" value="'+STATUS_FINISHED+'" onclick="is_enabledstatus('+this.id+')" />结束';
					    }else if(this.isEnable==STATUS_ENABLED){
					    	list+='<input type="radio" name="is_enbale'+this.id+'" id="enabledstatus'+this.id+'" value="'+STATUS_ENABLED+'"  checked="checked" onclick="is_enabledstatus('+this.id+')" />启用&nbsp;&nbsp;';
					    	list+='<input type="radio" name="is_enbale'+this.id+'" id="disablestatus'+this.id+'" value="'+STATUS_DISABLE+'" onclick="is_enabledstatus('+this.id+')" />停用';
					    	list+='<input type="radio" name="is_enbale'+this.id+'" id="disablestatus'+this.id+'" value="'+STATUS_FINISHED+'" onclick="is_enabledstatus('+this.id+')" />结束';
					    }else if(this.isEnable==STATUS_FINISHED){
					    	list+='<input type="radio" name="is_enbale'+this.id+'" id="enabledstatus'+this.id+'" value="'+STATUS_ENABLED+'" onclick="is_enabledstatus('+this.id+')" />启用&nbsp;&nbsp;';
					    	list+='<input type="radio" name="is_enbale'+this.id+'" id="disablestatus'+this.id+'" value="'+STATUS_DISABLE+'" onclick="is_enabledstatus('+this.id+')" />停用';
					    	list+='<input type="radio" name="is_enbale'+this.id+'" id="disablestatus'+this.id+'" value="'+STATUS_FINISHED+'" checked="checked" onclick="is_enabledstatus('+this.id+')" />结束';
					    }
					    list+='</td>';
					    if(this.subAcademyEnrollBatchlist.length>0){
					    	for(var i=0;i<this.subAcademyEnrollBatchlist.length;i++){
					    		if(i>0){
					    			list+='<tr>';
					    		}
					    		list+='<td align="center">';
					    		list+=this.subAcademyEnrollBatchlist[i].subEnrollmentName;
					    		list+='</td>';
					    		list+='<td align="center">';
					    		list+=this.subAcademyEnrollBatchlist[i].beginDate.toDate()+
					    		"--"+this.subAcademyEnrollBatchlist[i].endDate.toDate();
					    		list+='</td>';
					    		list+='<td align="center">';
					    		if(this.isEnable==STATUS_ENABLED){
					    			if(this.subAcademyEnrollBatchlist[i].isCurrent==1){
					    				list+='<input type="radio" name="status'+this.id+'" id="enabled_use'+this.subAcademyEnrollBatchlist[i].id+'" onclick="is_current('+this.subAcademyEnrollBatchlist[i].id+','+this.subAcademyEnrollBatchlist[i].academyEnrollBatchId+')" checked="checked"/>';
					    			}else{
					    				list+='<input type="radio" name="status'+this.id+'" id="enabled_use'+this.subAcademyEnrollBatchlist[i].id+'" onclick="is_current('+this.subAcademyEnrollBatchlist[i].id+','+this.subAcademyEnrollBatchlist[i].academyEnrollBatchId+')" />';
					    			}
							    }else{
							    	list+='--';
							    }
					    		list+='</td>';
					    		list+='</tr>';
					    	}
					    }else{
					    	list+='<td align="center">--</td>';
					    	list+='<td align="center">--</td>';
					    	list+='<td align="center"s>--</td>';
					    	list+='</tr>';
					    }
					   
			 });
				}else{
					list+='<tr class=error>';
					list+='<td colspan="7" align="center">';
					list+='<div>暂时没有相关信息</div>';
					list+='</td>';
					list+='</tr>';
				}
				$('#majortable > tbody').html(list);
			}else{
				show('error_Msg','信息提示:',300,100);
			}		
    	}
    </script>
    <%--设置招生批次启用状态(修改启用/停用/结束状态) --%>
    <a:ajax successCallbackFunctions="ajax_update_enroll_status" 
    		pluginCode="002" 
    		urls="/enrollment/update_academy_enroll_batch" 
    		parameters="{'academyEnrollBatch.isEnable':status, academyBatchId:enrollbatchid}"/>
    <%--设置是否是当前招生子批次--%>
     <a:ajax successCallbackFunctions="ajax_set_present_subbatch" 
    		 pluginCode="003" 
    		 urls="/enrollment/update_sub_academy_enroll_batch" 
    		 parameters="{subacademyBatchId:subbatchid,'subAcademyEnrollBatch.academyEnrollBatchId':enrollbatchid}"/>
    <%--调用初始化 增加院校招生批次对话框(弹出层)--%>
     <a:ajax successCallbackFunctions="ajax_install_academy_batch" 
    		 pluginCode="004" 
    		 urls="/enrollment/show_other_global_enroll_batchs" 
    		 parameters="{academyId:'${academyId}'}"/>
    <%--调用初始化 增加院校招生子批次对话框(弹出层)--%>
     <a:ajax successCallbackFunctions="ajax_install_sub_academy_batch" 
    		 pluginCode="005" 
    		 urls="/enrollment/list_academy_enroll_batch_by_academyId" 
    		 parameters="{academyId:'${academyId}'}"/>
    <%--添加院校招生(学籍)批次 --%>
     <a:ajax successCallbackFunctions="ajax_add_academy_enrollBatch" 
     		 pluginCode="006" 
     		 urls="/enrollment/add_academy_enroll_batch"
     		 parameters="{'academyEnrollBatch.academyId':'${academyId}',
     					'academyEnrollBatch.enrollmentName':$('#academybatch').val(),
     					'academyEnrollBatch.globalEnrollBatchId':globalid}"/>
    <%--添加院校招生子批次--%>
     <a:ajax successCallbackFunctions="ajax_add_sub_academy_enrollBatch" 
     		 pluginCode="007"
     		 urls="/enrollment/add_sub_academy_enroll_batch"
 		   	 parameters="{'subAcademyEnrollBatch.subEnrollmentName':$('#subacademybatch').val(),
 		 			     'subAcademyEnrollBatch.academyEnrollBatchId':academybatchid,
						 'subAcademyEnrollBatch.beginDate':$('#beginDate').val(),
						 'subAcademyEnrollBatch.endDate':$('#endDate').val()}"/>
	<%-- 加载院校招生批次及其对应子批次列表 --%>
	 <a:ajax successCallbackFunctions="ajax_load_enroll_batch" 
	 		 pluginCode="001" 
	 		 urls="/enrollment/list_academy_enroll_batch_by_deleteflag"
	 		 parameters="{academyId:'${academyId}'}"/>
    <script type="text/javascript">
    	//全局变量 
    	var batchnum=/(^[1-9]$)|(^\d{2,}$)/;
    	var enrollbatchid=0;//批次id
    	var status=0;//启用状态
    	var subbatchid=0;//子批次
    	var globalid=0;//全局招生批次id
    	var academybatchid=0;//院校招生批次
    	$(function(){
    		ajax_001_1(); 
    	});
    	
    	//设置招生批次启用状态(修改启用/停用/结束状态)
			function is_enabledstatus(id){
				status=$("input[name='is_enbale"+id+"']:checked").val();
				enrollbatchid=id;
				ajax_002_1();
			}
    			
		//设置是否是当前招生子批次
   			function is_current(subid,batchid){
   				enrollbatchid=batchid
   				subbatchid=subid
   				ajax_003_1();
   			}
			
		//添加院校招生(学籍)批次
			function addAcademyEnrollBatch(){
				
				if($('#academybatch').val()==null||$('#academybatch').val()==""||$.trim($('#academybatch').val())==""){
					show('fill_in_info_error','信息提示:',250,100); 
				}else if(!batchnum.test($.trim($("#academybatch").val()))){
    				show('num_error','信息提示:',350,100); 
    			}else{
    				if(document.getElementById("selectglobalbatch").value!=null&&document.getElementById("selectglobalbatch").value!=""&&document.getElementById("selectglobalbatch").value>0){
    					globalid=document.getElementById("selectglobalbatch").value;
    					ajax_006_1();
    				}else{
						closes('addBatchDiv');
					}
				}
			}
		//添加院校招生子批次
				function addSubAcademyEnrollBatch(){
					if($('#subacademybatch').val()==null||$('#subacademybatch').val()==""||$.trim($('#subacademybatch').val())==""){
						show('fill_in_info_error','信息提示:',250,100); 
					}else if($('#beginDate').val()==""||$('#beginDate').val()==null){
						show('begin_date_error','信息提示:',250,100); 
					}else if($('#endDate').val()==""||$('#endDate').val()==null){
						show('end_date_error','信息提示:',250,100); 
					}else{
						if(document.getElementById("selectacademybatch").value!=null&&document.getElementById("selectacademybatch").value!=""&&document.getElementById("selectacademybatch").value>0){
							academybatchid=document.getElementById("selectacademybatch").value;
							ajax_007_1();  
						}else{
							closes('addSubBatchDiv');
						}
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
							<th style="font-weight:bold;">
								<div id="conmenu">
									<a href="#" onclick="ajax_005_1()"><img src="<ui:img url="images/title_menu/icon_add.gif" />" />增加院校招生子批次</a>
								</div>	
								<div id="conmenu">
									<a href="#" onclick="ajax_004_1()"><img src="<ui:img url="images/title_menu/icon_add.gif" />" />增加院校招生批次</a>
								</div>									
							</th>
							<th style="font-weight:bold;">
									
							</th>
						</tr>
					</table>
						
					<table class="gv_table_2" id="majortable" border="0" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th >全局批次</th>
								<th >招生批次</th>
								<th >学籍批次</th>
								<th >招生批次状态</th>
								<th >招生子批次</th>
								<th >招生时间段</th>
								<th >设置当前批次</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
					<!--Left End-->	
		  		</body:body>
		  <!-- 添加院校招生批次 弹出层 -->
		  <div id="addBatchDiv" style="display:none">
		  		<form id="addBatchForm">
					 <table class="gv_table_2" id="batchTable" border="0" cellpadding="0" cellspacing="0">
					 	<tr>
							<td align="right">全局招生批次名称：</td>
							<td align="left">
								<select class="txt_box_130" id="selectglobalbatch" style="width: 154px;height: 23px;"></select>
							</td>
						</tr>
						<tr>
							<td align="right">院校招生批次名称：</td>
							<td align="left"><input id="academybatch"  type="text"  class="txt_box_150" /></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><input name="" type="button"  class="btn_gray_61" value="添加" onclick="addAcademyEnrollBatch()" /></td>
						</tr>
					 </table>
				 </form>
		</div>
		<!-- 添加院校招生子批次 弹出层 -->	
		<div id="addSubBatchDiv" style="display:none">
		  		<form id="addSubBatchForm">
					 <table class="gv_table_2" id="subBatchTable" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td align="right">院校招生批次名称：</td>
							<td align="left"><select class="txt_box_130" id="selectacademybatch"  style="width: 154px;height: 23px;" ></select></td>
						</tr>
						<tr>
							<td align="right">院校招生子批次名称：</td>
							<td align="left">
								<input id="subacademybatch"  type="text"  class="txt_box_150" />
							</td>
						</tr>
						<tr>
							<td align="right">起始时间：</td>
							<td align="left"><input id="beginDate" type="text"  class="txt_box_150" onfocus="WdatePicker({skin:'whyGreen'})"  readonly="readonly" /></td>
						</tr>
						<tr>
							<td align="right">结束时间：</td>
							<td align="left"><input id="endDate" type="text" class="txt_box_150" onfocus="WdatePicker({skin:'whyGreen'})"  readonly="readonly" /></td>
						</tr>
						<tr>
							<td align="center" colspan="2"><input type="button"  class="btn_gray_61" value="添加" onclick="addSubAcademyEnrollBatch()" /></td>
						</tr>
					 </table>
				 </form>
		</div>
		<div id="msgDiv" style="display:none" align="center">
				<br />操作成功！！<br />
		</div>
		 <div id="fill_in_info_error" style="display:none;font-weight:bold" align="center"><br/>请填写完整数据！<br/></div>
		 <div id="begin_date_error" style="display:none;font-weight:bold" align="center"><br/>请选择起始时间！<br/></div>
		 <div id="end_date_error" style="display:none;font-weight:bold" align="center"><br/>请选择结束时间！<br/></div>
	     <div id="num_error" style="display:none;font-weight:bold" align="center"><br/>请正确填写院校招生批次名称(只能是正整数)！<br/></div>
	     <div id="add_error_Msg" style="display:none;font-weight:bold" align="center"><br/>数据库存在重复数据，请重新执行本次操作！<br/></div>
	     <div id="error_Msg" style="display:none;font-weight:bold" align="center"><br/>服务器繁忙，请您稍后尝试操作！<br/></div>
		 <div id="errorforglobalbatch" style="display:none;font-weight:bold" align="center"><br/>暂无相关全局招生批次，请先添加全局招生批次！<br/></div>
		 <div id="errorforacademybatch" style="display:none;font-weight:bold" align="center"><br/>暂无相关院校招生批次，请先添加院校招生批次！<br/></div>
  </body>
</html>