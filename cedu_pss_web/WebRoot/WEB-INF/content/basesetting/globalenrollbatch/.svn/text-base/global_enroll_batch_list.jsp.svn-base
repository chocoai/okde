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
    
    <title>全局招生批次</title>
    <script type="text/javascript">
    	//加载全部全局招生批次列表
    	function ajax_load_global_batch(data){
    		var list='';
			if(data.lstrltbool){
				if(data.globalbatchlist.length>0){
					$.each(data.globalbatchlist, function(){
						list+='<tr>';
						list+='<td align="center">';
						list+='<div id="now'+this.id+'">';
						list+=this.belongYear;
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<select id="belongyear'+this.id+'" class="txt_box_130" ></select>';
						list+='</div>';
						list+='</td>';
						list+='<td align="center">';
						list+='<div id="now'+this.id+'">';
						list+=this.batch;
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<input id="batch'+this.id+'" value="" maxlength="16"/>';
						list+='</div>';
						list+='</td>';
					    list+='<td align="center">';
						list+='<div id="now'+this.id+'">';
						list+=this.title;
						list+='</div>';
						list+='<div id="click'+this.id+'" style="display:none;">';
						list+='<input id="title'+this.id+'" value="" maxlength="64"/>';
						list+='</div>';
						list+='</td>';
					    list+='<td align="center" width="20%">';
					    list+='<a href="#"  id="updateglobalbatch'+this.id+'" onclick="changeBtnValue('+this.id+')">修改</a>';
					    list+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="deleteglobalbatch'+this.id+'" onclick="deleteOrCancelGlobalBatch('+this.id+')">删除</a>';
					    list+='</td>';
					    list+='</tr>';
			});
				}else{
						list+='<tr class="error">';
						list+='<td colspan="4" align="center">';
						list+='<div>暂时没有相关信息</div>';
						list+='</td>';
						list+='</tr>';
				}
				$('#globalbatchtable > tbody').html(list);	
				$('#add_batch').css({"display":"block"});
			}else{
				show('error_Msg','信息提示:',400,130);
			}
    	}
    	//接收添加方法回调函数，进行相关操作
    	function ajax_save_global_batch(data){
    		if(!data.addrltbol){
				show('error_Msg','信息提示:',350,100);
    		}else if(data.errormsg){
    			cancelGlobalBatch();
	  			show('success_Msg','信息提示:',250,100); 
	  		}else{
	  			show('add_error_Msg','信息提示:',350,100);
	  		}
    		ajax_001_1();	
    	}
    	//接收更新方法回调函数，进行相关操作
    	function ajax_update_global_batch(data){
    			if(!data.updaterltbol){
					show('error_Msg','信息提示:',350,100);
				}else if(data.sameinfobool){
					show('success_Msg','信息提示:',250,100);
		  		}else{
		  			show('add_error_Msg','信息提示:',350,100);
		  		}
		  		ajax_001_1();	
    	}
		//接收删除方法回调函数，进行相关操作
		function ajax_delete_global_batch(data){
    			if(data.delrltbool){
		  			show('success_Msg','信息提示:',400,130); 
		  		}else{
		  			show('error_Msg','信息提示:',400,130);;
		  		}
		  		ajax_001_1();	
    	}
    </script>
	<a:ajax parameters="null;{'globalEnrollBatch.batch':$('#globalbatchbatch').val(),'globalEnrollBatch.title':$('#globalbatchtitle').val(),'globalEnrollBatch.belongYear':$('#globalbatchbelongyear').val()};{globalenrollbatchid:globalenrollbatchid,'globalEnrollBatch.batch':$('#batch'+globalenrollbatchid).val(),'globalEnrollBatch.title':$('#title'+globalenrollbatchid).val(),'globalEnrollBatch.belongYear':$('#belongyear'+globalenrollbatchid).val()};{globalenrollbatchid:globalenrollbatchid}"
			successCallbackFunctions="ajax_load_global_batch;ajax_save_global_batch;ajax_update_global_batch;ajax_delete_global_batch"
			urls="/basesetting/globalenrollbatch/listglobalbatchbydeleteflag;/basesetting/globalenrollbatch/addglobalenrollbatch;/basesetting/globalenrollbatch/updateglobalbatch;/basesetting/globalenrollbatch/deleteglobalenrollbatch"
			pluginCode="001" />
    <script type="text/javascript">
		//全局变量 
   		var globalenrollbatchid=0;
   		var batchnum=/^[1-9][0-9]*$/;
    	$(function(){
    		ajax_001_1();		
    	});
     
     	//获取当前年份 前后10年的年份  加载到所属年份下拉框中
     	function LoadPeriodYears(name,id){
     		var nowyear=(new Date()).getFullYear();//当前年份
     		var tenyearsbefore=nowyear-3;//10年前的年份(改成3年)
     		var tenyearsafter=nowyear+3;//10年后的年份（改成3年）
     		for(var i=tenyearsbefore;i<=tenyearsafter;i++){
     			$("#"+name+id).append('<option value="'+i+'">'+i+"年"+'</option>'); 
     		}
     		if(name=="globalbatchbelongyear"){
     			$("#globalbatchbelongyear"+id).val(nowyear);
     		}
     	}
     	
     	//获取当前年份 前后3年的年份  加载到所属年份下拉框中(修改用到)
     	function upLoadPeriodYears(name,id){
     		var nowyear=$("div[id='now"+id+"']:nth(0)").html()-0;//当前年份
     		var tenyearsbefore=nowyear-3;//10年前的年份(改成3年)
     		var tenyearsafter=nowyear+3;//10年后的年份（改成3年）
     		for(var i=tenyearsbefore;i<=tenyearsafter;i++){
     			$("#"+name+id).append('<option value="'+i+'">'+i+"年"+'</option>'); 
     		}
     		if(name=="globalbatchbelongyear"){
     			$("#globalbatchbelongyear"+id).val(nowyear);
     		}
     	}
   
		//增加一行输入框，用于输入全局招生批次信息
    	function addGlobalBatch(astyle){
    		$("#globalbatchtable > tbody tr.error").remove();
    		$('#add_batch').hide();
    		var list='';
    		list+=$('#globalbatchtable > tbody').html();
			list+='<tr id="globalbatchtr">';
			list+='<td align="center" >'; 
		    list+='<select id="globalbatchbelongyear" class="txt_box_130"></select>';
		    list+='</td>'; 
		    list+='<td align="center" >'; 
		    list+='<input id="globalbatchbatch" class="txt_box_150" maxlength="16"/>';
		    list+='</td>'; 
		    list+='<td align="center" >'; 
		    list+='<input id="globalbatchtitle" class="txt_box_150" maxlength="64" />';
		    list+='</td>';  
		    list+='<td align="center" >'; 
		    list+='<a href="#" onclick="saveGlobalBatch()">保存</a>';	 
		    list+='&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="cancelGlobalBatch()">取消</a>'; 
		    list+='</td>';
		    list+='</tr>';
		    $('#globalbatchtable > tbody').html(list);
		 	LoadPeriodYears('globalbatchbelongyear',"");//加载所属年份下拉框value
    	}
    	
    	//取消一行尚未保存的输入框
    	function cancelGlobalBatch(){
    		$("#globalbatchtr").remove();
    		$('#add_batch').show();
    	}
    	
		//保存当前一行输入的全局招生批次信息
    		function saveGlobalBatch(){
	    		if($("#globalbatchbatch").val()==null||$("#globalbatchbatch").val()==""||$.trim($("#globalbatchbatch").val())==""){
	    			show('fill_in_info_error','信息提示:',250,100); 
	    		}else if(!batchnum.test($.trim($("#globalbatchbatch").val()))){
	    			show('num_error','信息提示:',320,100); 
	    		}else if($("#globalbatchtitle").val()==null||$("#globalbatchtitle").val()==""||$.trim($("#globalbatchtitle").val())==""){
	    			show('fill_in_info_error','信息提示:',250,100); 
	    		}else{
	    			ajax_001_2();
	    		}
    	}

    	//切换是否可以修改信息,保存修改信息
			function changeBtnValue(id){
				var updatebtn=$('#updateglobalbatch'+id).html();//获取保存/修改对象
				var deletebtn=$('#deleteglobalbatch'+id).html();//获取取消/删除对象
				if(updatebtn=="修改"){  	
					//nth(0)取id为"now"+id的第一个div的值，1为第二个div的值,2为第三个div的值
					//LoadPeriodYears('belongyear',id);//加载所属年份下拉框value
					upLoadPeriodYears('belongyear',id);//加载所属年份下拉框value
					
					$('#belongyear'+id).val($("div[id='now"+id+"']:nth(0)").html());
					$('#batch'+id).val($("div[id='now"+id+"']:nth(1)").html());
					$('#title'+id).val($("div[id='now"+id+"']:nth(2)").html());
					$('#updateglobalbatch'+id).html("保存");
					$('#deleteglobalbatch'+id).html("取消");
					$("div[id='now"+id+"']").hide();
					$("div[id='click"+id+"']").show();
				}else{
					if($("#batch"+id).val()==null||$("#batch"+id).val()==""||$.trim($("#batch"+id).val())==""){
		    			show('fill_in_info_error','信息提示:',250,100);
		    		}else if(!batchnum.test($.trim($("#batch"+id).val()))){
	    				show('num_error','信息提示:',320,100); 
	    			}else if($("#title"+id).val()==null||$("#title"+id).val()==""||$.trim($("#title"+id).val())==""){
		    			show('fill_in_info_error','信息提示:',250,100);
		    		}else{
		    			globalenrollbatchid=id;
		    			ajax_001_3();	
						$('#updateglobalbatch'+id).html("修改");
						$('#deleteglobalbatch'+id).html("删除");
						$("div[id='now"+id+"']").show();
						$("div[id='click"+id+"']").hide();
		    		}
				}
			}
			
		//删除当前选定的一条全局招生批次或是取消当前选定全局招生批次的可编辑状态
			function deleteOrCancelGlobalBatch(id){
				var deletebtn=$('#deleteglobalbatch'+id).html();//获取取消/删除对象
				var updatebtn=$('#updateglobalbatch'+id).html();//获取保存/修改对象
				
				if(deletebtn=="取消"){
					$('#updateglobalbatch'+id).html("修改");
					$('#deleteglobalbatch'+id).html("删除");
					$("div[id='now"+id+"']").show();
					$("div[id='click"+id+"']").hide();
				}else{
					var isdel=confirm("您确定要删除吗？");
					if(isdel){
						globalenrollbatchid=id;	
						ajax_001_4();
					}
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
				 	<th style="text-align:left; font-weight:bold;">全局招生批次设置</th>
					<th style="text-align:right; font-weight:bold;"><div id="conmenu"><a href="javascript:void(0)" onclick="addGlobalBatch(this)" id="add_batch"><img src="<ui:img url="images/title_menu/icon_add.gif" />" />增加</a></div></th>
				</tr>
		  </table>
		  <table class="gv_table_2" id="globalbatchtable" border="0" cellpadding="0" cellspacing="0">
				  <thead>
					<tr>
						<th >所属年份</th>
						<th >全局招生批次</th>
						<th >标题</th>
						<th >操作</th>
					</tr>
				  </thead>
				<tbody></tbody>
			</table>					
			<!--Left End-->		
  	</body:body>
  <div id="num_error" style="display:none;font-weight:bold" align="center"><br/>请正确填写全局批次(只能是正整数)！<br/></div>
  <div id="fill_in_info_error" style="display:none;font-weight:bold" align="center"><br/>请填写完整数据！<br/></div>
  <div id="success_Msg" style="display:none;font-weight:bold" align="center"><br/>操作成功！<br/></div>
  <div id="add_error_Msg" style="display:none;font-weight:bold" align="center"><br/>数据库存在重复数据，请重新执行本次操作！<br/></div>
  <div id="error_Msg" style="display:none;font-weight:bold" align="center"><br/>服务器繁忙，请您稍后尝试操作！<br/></div>
  </body>
</html>
