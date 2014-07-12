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
		
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		
		<!-- ajax 载入页面特效 -->
		<jc:plugin name="loading"/> 
		
    	<title>基础设置</title>
    	<style type="text/css">
    		*{ margin:0; padding:0;}  
	        .checkbox_style{vertical-align:middle}  
	        .checkbox_style input{vertical-align:middle;}  
	        body{font-family:tahoma;font-size:12px;}  
    	</style>
    	<script type="text/javascript">
    	var subjectcount=0;
    	var chksubjectcount=0;
    	//加载全部招生途径列表
    	function ajax_load_enrollment_source(data){
    		var list='';
			if(data.lstrltbool){
				if(data.enrollmentsourcelist!=null&&data.enrollmentsourcelist.length>0){
					$.each(data.enrollmentsourcelist, function(){
						list+='<tr>';
						list+='<td align="center">';
						list+='<div id="now'+this.id+'">';
						list+=this.name;
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<input id="name'+this.id+'" value="" maxlength="32"/>';
						list+='</div>';
						list+='</td>';
					    list+='<td align="center">';
						list+='<div id="now'+this.id+'">';
						if(this.code==null||this.code==""){
							list+='';
						}else{
							list+=this.code;
						}
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<input id="code'+this.id+'" value="" disabled="disabled"/>';
						list+='</div>';
						list+='</td>';
						list+='<td align="center">';
						list+='<div id="now'+this.id+'">';
						if(this.type==CHANNEL_TYPE_COMPANY){
							list+='公司';
						}else if(this.type==CHANNEL_TYPE_PERSONAL){
							list+='个人';
						}else{
							list+='--';
						}
						list+='<input id="typeinput'+this.id+'" value="'+this.type+'" type="hidden"/>';
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<select id="type'+this.id+'"><option value="'+CHANNEL_TYPE_NULL+'">--</option><option value="'+CHANNEL_TYPE_PERSONAL+'">个人</option><option value="'+CHANNEL_TYPE_COMPANY+'">公司</option></select>';
						list+='</div>';
						list+='</td>';
						list+='<td align="center">';
						list+='<div id="now'+this.id+'">';
						if(this.isneedRebates==ISNEED_REBATES_TRUE){
							list+='是';
						}else if(this.isneedRebates==ISNEED_REBATES_FALSE){
							list+='否';
						}	
						list+='<input id="isneedRebatesinput'+this.id+'" value="'+this.isneedRebates+'" type="hidden"/>';
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<select id="isneedRebates'+this.id+'"><option value="'+ISNEED_REBATES_TRUE+'">是</option><option value="'+ISNEED_REBATES_FALSE+'">否</option></select>';
						list+='</div>';
						list+='</td>';	
						list+='<td align="center">';
						list+='<div id="now'+this.id+'">';	
						if(this.isneedRebates==1&&this.subjectnames!=null){
							list+=this.subjectnames;
						}else{
							list+="--";
						}
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='</div>';
						list+='</td>';
					    list+='<td align="center">';
					    if(this.isneedRebates==ISNEED_REBATES_TRUE){
					    	 list+='<a href="#" id="install'+this.id+'" onclick="installSubject('+this.id+')" >设置</a>&nbsp;&nbsp;&nbsp;&nbsp;';
					    }
					    if(this.id>INIT_ENROLLMENTSOURCE_ID){
							list+='<a href="#" id="updateenrollmentsource'+this.id+'" onclick="changeBtnValue('+this.id+')">修改</a>';
						    list+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="deleteenrollmentsource'+this.id+'" onclick="deleteOrCancelEnrollmentSource('+this.id+')">删除</a>';
					    }
					    list+='</td>';
					    list+='</tr>';
					
			 });
				}else{
						list+='<tr class="error">';
						list+='<td colspan="7" align="center">';
						list+='<div>暂时没有相关信息</div>';
						list+='</td>';
						list+='</tr>';
				}
				$('#enrollmentsourcetable > tbody').html(list);
				$('#add_source').show();
			}else{
				show('error_Msg','信息提示:',400,130);
			}				
    	}
    	//加载费用科目列表
    	function ajax_load_fee_subject(data){
    		var list='';
    		var count=0;	
			if(data.subjectlstrltbool){
				if(data.feesubjectlist!=null&&data.feesubjectlist.length>0){
					subjectcount = data.feesubjectlist.length;
					chksubjectcount = data.feeSubjectIds!=null?data.feeSubjectIds.length:0;
					if(subjectcount==chksubjectcount){
						$("#check_all_fee_subject").attr("checked",true);
					}else{
						$("#check_all_fee_subject").attr("checked",false);
					}
					$.each(data.feesubjectlist, function(){
						if((count+1)%3==1){
		    				list+='<tr align="left">';
		    			}
						list+='<td align="left">';
						if(data.feeSubjectIds!=null&&data.feeSubjectIds.length>0){
							 //data.academylevelarrayjQuery.inArray(value, array)==>确定第一个参数在数组中的位置，从0开始计数(如果没有找到则返回 -1 )。
							 if(jQuery.inArray(this.id,data.feeSubjectIds)>=0){ 
								list+='<div class="checkbox_style"><input type="checkbox" value="'+this.id+'" checked="checked"/>&nbsp;'+this.name+'</div>';
							 }else{
								list+='<div class="checkbox_style"><input type="checkbox" value="'+this.id+'"/>&nbsp;'+this.name+'</div>';
							 }
						}else{
							 list+='<div class="checkbox_style"><input type="checkbox" value="'+this.id+'"/>&nbsp;'+this.name+'</div>';
						}
						list+='</td>';
						if((count+1)%3==0){
			    			list+='</tr>';
			    		}
			    		count++;
				 });
						list+='<tr>';
						list+='<td colspan="6" align="center">';
						list+='<input type="button" class="btn_black_61" value="添加" onclick="saveFeeSubject();"/>';
						list+='&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn_black_61" value="关闭" onclick="$('+"'#createRemarkBox'"+').dialog('+"'close'"+')" />';
						list+='</td>';
						list+='</tr>';
				}else{
						list+='<tr>';
						list+='<td colspan="2" align="center">';
						list+='<div>暂时没有相关信息</div>';
						list+='</td>';
						list+='</tr>';
				}
				$('#feesubjecttable > tbody').html(list);
				$('#conmenu',document).show();
			}else{
				show('error_Msg','信息提示:',400,130);
			}				
    	}
    	//接收添加方法回调函数，进行相关操作(添加招生途径信息)
    	function ajax_save_enrollment_source(data){
    		if(!data.addrltbol){
				show('error_Msg','信息提示:',320,100);
    		}else if(data.errormsg){
	  			show('success_Msg','信息提示:',250,100); 
	  		}else{
	  			show('add_error_Msg','信息提示:',320,100);
	  		}
    		ajax_001_1();	
    	}
    	//接收添加方法回调函数，进行相关操作(添加费用科目)
    	function ajax_save_fee_subject(data){
    		if(!data.updaterltbol){
				show('error_Msg','信息提示:',320,100);
			}else if(data.sameinfobool){
				$('#createRemarkBox').dialog('close');
				show('success_Msg','信息提示:',250,100);
	  		}else{
	  			show('add_error_Msg','信息提示:',320,100);
	  		}
	  		ajax_001_1();
    	}
    	//接收更新方法回调函数，进行相关操作
    	function ajax_update_enrollment_source(data){
    		if(!data.updaterltbol){
				show('error_Msg','信息提示:',320,100);
			}else if(data.sameinfobool){
				show('success_Msg','信息提示:',250,100);
	  		}else{
	  			show('add_error_Msg','信息提示:',320,100);
	  		}
	  		ajax_001_1();
    	}
    	//接收删除方法回调函数，进行相关操作
    	function ajax_delete_enrollment_source(data){
    		if(data.delrltbool){
	  			show('success_Msg','信息提示:',250,100); 
	  		}else{
	  			cancelEnrollmentWay();
	  			show('error_Msg','信息提示:',320,100);
	  		}
	  		ajax_001_1();
    	}
    	
    	</script>
    	<a:ajax parameters="null" 
    			successCallbackFunctions="ajax_load_enrollment_source" 
    			pluginCode="001" 
    			urls="/basesetting/enrollmentsource/listenrollmentsourcebydeleteflag"/>
   		<a:ajax parameters="{sourceid:CUR_ID}" 
	   			successCallbackFunctions="ajax_load_fee_subject" 
	   			pluginCode="002" 
	   			urls="/basesetting/enrollmentsource/listfeesubjectbyflag"/>		
   		<a:ajax parameters="{enrollmentSourceName:$('#enrollmentsourcename').val(),enrollmentSourceType:$('#enrollmentsourcetype').val(),enrollmentSourceIsneedRebates:$('#enrollmentsourceisneedRebates').val()}" 
	   			successCallbackFunctions="ajax_save_enrollment_source" 
	   			pluginCode="003" 
	   			urls="/basesetting/enrollmentsource/addenrollmentsource"/>			
   		<a:ajax parameters="{enrollmentSourceid:CUR_ID,enrollmentSourceSourceRebatesFeesubject:str}" 
	   			successCallbackFunctions="ajax_save_fee_subject" 
	   			pluginCode="004" 
	   			urls="/basesetting/enrollmentsource/updateenrollmentsource"/>	
	   	<a:ajax parameters="{enrollmentSourceName:$('#name'+enrollmentSourceid).val(),enrollmentSourceType:$('#type'+enrollmentSourceid).val(),enrollmentSourceIsneedRebates:$('#isneedRebates'+enrollmentSourceid).val(),enrollmentSourceid:enrollmentSourceid}" 
	   			successCallbackFunctions="ajax_update_enrollment_source" 
	   			pluginCode="005" 
	   			urls="/basesetting/enrollmentsource/updateenrollmentsource"/>	
	   	<a:ajax parameters="{enrollmentSourceId:enrollmentSourceid}" 
	   			successCallbackFunctions="ajax_delete_enrollment_source" 
	   			pluginCode="006" 
	   			urls="/basesetting/enrollmentsource/deletenrollmentsource"/>	
		<script type="text/javascript">
			//全局变量 
		    var CUR_ID = 0;
		    var enrollmentSourceid=0;
		    var str="";
	 	
	    	$(function(){	
	    		ajax_001_1();
	    		//loadFeeSubject();
	    		//jquery-ui设置渠道返款费用科目层
				$('#createRemarkBox').dialog({
					autoOpen:false,
					modal:true,
					title:'渠道返款费用科目设置',
					width: 500,
					resizable:false
				});
	    	});
		    //添加费用科目
			function saveFeeSubject(){
				var chkarray=[];
				//循环添加表单ID为dialogform 里所有被选中的 input的值 到chkarray里
				$("#dialogform input:checked").each(function () { 
					chkarray.push(this.value);
				});
				//判断chkarray是否为空，即是否有复选框被选中
				if(chkarray.length==0){
					show('select_at_least_one','信息提示:',250,120);
				}else{
					//将chkarray里的数据拼接成一个字符串 传递到action中
					str=chkarray.join('_');
					ajax_004_1();
				}
			}
		    	
		    //调用设置渠道返款费用科目对话框
			function installSubject(id){
				CUR_ID=id;//全局变量保存当前ID号 传递给设置费用科目使用
				ajax_002_1();
				if(subjectcount==chksubjectcount){
					$("#check_all_fee_subject").attr("checked",true);
				}else{
					$("#check_all_fee_subject").attr("checked",false);
				}
				$('#createRemarkBox').dialog('open');
				//获取对象并加载清空内容
				var ui = jQuery('#createRemarkBox');
				jQuery('#dialogform', ui)[0].reset();
			}
			
			
			//增加一行输入框，用于输入招生途径信息
			function addEnrollmentSource(){
				$("#enrollmentsourcetable > tbody tr.error").remove();
				$('#add_source').hide();
	    		var list='';
	    		list+=$('#enrollmentsourcetable > tbody').html();
				list+='<tr id="enrollmentsourcetr">';		
			    list+='<td align="center" >'; 
			    list+='<input id="enrollmentsourcename" class="txt_box_150" maxlength="32" />';
			    list+='</td>'; 
			    list+='<td align="center" >'; 
			    list+='';
			    list+='</td>'; 
				list+='<td align="center" >'; 
			    list+='<select id="enrollmentsourcetype"><option value="0">--</option><option value="1">个人</option><option value="2">公司</option></select>';
			    list+='</td>';
				list+='<td align="center" >'; 
			    list+='<select id="enrollmentsourceisneedRebates"><option value="1">是</option><option value="0">否</option></select>';
			    list+='</td>'; 
				list+='<td align="center" >'; 
			    list+='</td>'; 
			    list+='<td align="center" >'; 
			    list+='<a href="#" onclick="saveEnrollmentSource()">保存</a>';	 
			    list+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelEnrollmentSource()">取消</a>'; 
			    list+='</td>';
			    list+='</tr>';
			    $('#enrollmentsourcetable > tbody').html(list);
			}
			//取消一行尚未保存的输入框
    		function cancelEnrollmentSource(){
    			$("#enrollmentsourcetr").remove();
    			$('#add_source').show();
    		}
    		
    		//保存当前一行输入的招生途径信息
    		function saveEnrollmentSource(){
	    		if($("#enrollmentsourcename").val()==null||$("#enrollmentsourcename").val()==""||$.trim($("#enrollmentsourcename").val())==""){
	    			show('fill_in_info_error','信息提示:',250,100); 
	    		}else{
	    			ajax_003_1();
	    		}
          }
			
		  //删除当前选定的一条招生途径或是取消当前选定招生途径的可编辑状态
			function deleteOrCancelEnrollmentSource(id){
				var deletebtn=$('#deleteenrollmentsource'+id).html();//获取取消/删除对象
				var updatebtn=$('#updateenrollmentsource'+id).html();//获取保存/修改对象
				
				if(deletebtn=="取消"){
					$('#install'+id).show();	
					$('#updateenrollmentsource'+id).html("修改");
					$('#deleteenrollmentsource'+id).html("删除");
					$("div[id='now"+id+"']").show();
					$("div[id='click"+id+"']").hide();
				}else{
					var isdel=confirm("您确定要删除吗？");
					if(isdel){
						enrollmentSourceid=id;
						ajax_006_1();
					}
				}
			}
			
			//切换是否可以修改信息,保存修改信息
			function changeBtnValue(id){
				var updatebtn=$('#updateenrollmentsource'+id).html();//获取保存/修改对象
				var deletebtn=$('#deleteenrollmentsource'+id).html();//获取取消/删除对象
				
				if(updatebtn=="修改"){  	
					//nth(0)取id为"now"+id的第一个div的值，1为第二个div的值,2为第三个div的值
					
					$('#name'+id).val($("div[id='now"+id+"']:nth(0)").html());
					$('#code'+id).val($("div[id='now"+id+"']:nth(1)").html());
					 
					$('#type'+id).val($("div[id='now"+id+"']:nth(2) input[id='typeinput"+id+"']").val());
					$('#isneedRebates'+id).val($("div[id='now"+id+"']:nth(3) input[id='isneedRebatesinput"+id+"']").val());	
					$("div[id='click"+id+"']:nth(4)").html($("div[id='now"+id+"']:nth(4)").html());
					
					$('#install'+id).hide();	
					$('#updateenrollmentsource'+id).html("保存");
					$('#deleteenrollmentsource'+id).html("取消");
					$("div[id='now"+id+"']").hide();
					$("div[id='click"+id+"']").show();
				}else{
					if($("#name"+id).val()==null||$("#name"+id).val()==""||$.trim($("#name"+id).val())==""){
	    				show('fill_in_info_error','信息提示:',250,100); 
					}else{
			    		enrollmentSourceid=id;
						ajax_005_1();
						$('#updateenrollmentsource'+id).html("修改");
						$('#deleteenrollmentsource'+id).html("删除");
						$("div[id='now"+id+"']").show();
						$("div[id='click"+id+"']").hide();
		    		}
				}
			}
			
		//全选/取消(for_copy)
		   function checkAllOrCancel(){
    			if($("#check_all_fee_subject").attr("checked")){
    				 $("#dialogform input[type='checkbox']").attr("checked",true);
    			}else{ 
    				 $("#dialogform input[type='checkbox']").attr("checked",false);
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
					 	<th style="text-align:left; font-weight:bold;">招生途径设置</th>
						<th style="text-align:right; font-weight:bold;"><div id="conmenu"><a id="add_source" href="javascript:void(0)" onclick="addEnrollmentSource()"><img src="<ui:img url="images/title_menu/icon_add.gif" />" />增加</a></div></th>
					</tr>
				</table>
					
				<table class="gv_table_2" id="enrollmentsourcetable" border="0" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th>招生途径名称</th>
							<th>编码</th>
							<th>类别</th>
							<th>是否合作方返款</th>
							<th>合作方返款费用科目</th>
							<th>操作 </th>
						</tr>
					</thead>
					<tbody></tbody>
					<tfoot></tfoot>
				</table>
				<!--Left End-->	
	  		</body:body>
	  <!-- 弹出层添加渠道返款费用科目 -->
	  <div id="createRemarkBox" style="display:none;">
  		<table>
  			<tr align="right">
  				<td align="right" colspan="3">
  					<input id="check_all_fee_subject" style="vertical-align:middle;" type="checkbox" onclick="checkAllOrCancel()"/>&nbsp;全选
  				</td>
  			</tr>
  		</table>	
	  	<form id="dialogform">
	    	<table class="add_table" id="feesubjecttable">
			  <tbody align="center"></tbody>
	        </table>
        </form>
	</div>
	   <div id="select_at_least_one" style="display:none;font-weight:bold" align="center"><br/>操作失败，请至少选择一项数据！</div>
	   <div id="fill_in_info_error" style="display:none;font-weight:bold" align="center"><br/>请填写完整数据！<br/></div>
	   <div id="success_Msg" style="display:none;font-weight:bold" align="center"><br/>操作成功！<br/></div>
	   <div id="add_error_Msg" style="display:none;font-weight:bold" align="center"><br/>数据库存在重复数据，请重新执行本次操作！<br/></div>
	   <div id="error_Msg" style="display:none;font-weight:bold" align="center"><br/>服务器繁忙，请您稍后尝试操作！<br/></div>
  </body>
</html>
