<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<%@ include file="../../template/common/download_excel.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>学习中心招生指标</title>
    	<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!--  jquery-loading-->
		<jc:plugin name="loading" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<style type="text/css">
    	   div.bborder {border-bottom: #cccccc 1px solid !important;}
    	   div.bbborder{border-bottom:white 1px solid !important;}
		</style>
		<script type="text/javascript">
		var bId=0;
		var aId=0;
		var target=0;
		var ids='${param.batchId}';
		var param='';
		var paramtarget='';
		var uobj=0;
		var btargetNum=0;
		var btargetStr='';
		var hbidStr='';
		
		
		
		/*
		查询所有全局批次
		*/
		function ajax_listbatch(data)
		{
			var lists='';
			$.each(data.enrollquotabatchlst,function(){
				lists+='<option value="'+this.batchId+'">'+this.batchName+'</option>';
			});
			$('#selbatch').html("");
			$('#selbatch').html(lists);
			// ie6下ids空值报错
			if(ids==''){
				document.getElementById("selbatch").selectedIndex=0;
			}else{
				$('#selbatch').val(ids);
			}
			ajax_123_2();
			search123();
		}
		
		function ajax_target(data)
		{
			$('#target').html(data.enrollquotabatch.target);
		}
		
		function ajax_addacademyenrollquota(data)
		{
			show('assignsuccessDiv','提示',250,150);
		}
		
		function ajax_update_branch_enroll_quota(data)
		{
			if(data.bol)
			{
				show('assignsuccessDiv','提示',250,150);
			}
			search123();
		}

		</script>
		
		<a:ajax parameters="null;{'batchId':$('#selbatch').val()};{'batchId':$('#selbatch').val(),'branchId':bId,'academyId':aId,'target':target};{'branchIds':hbidStr,'branchTarget':btargetStr}"
		successCallbackFunctions="ajax_listbatch;ajax_target;ajax_addacademyenrollquota;ajax_update_branch_enroll_quota" 
		urls="/enrollment/enrollquota/searchglobalbatch;/enrollment/enrollquota/searchtarget;/enrollment/enrollquota/addacademyenrollquota;/enrollment/enrollquota/update_branch_enroll_quota"
		pluginCode="123" 
		/>
		<!-- 下载地址 -->
		<a:ajax 
			parameters="{'batchId':$('#selbatch').val()}" 
			successCallbackFunctions="excel_export_callback" 
			pluginCode="download_zip" 
			urls="/enrollment/enrollquota/export/excel_export_enrollquota_recruit_student"
		/>
		<script type="text/javascript">
		function download(){
			if(confirm("您确定要导出数据吗？")){
				ajax_download_zip_1();
			}
		}
		
		function selbatchchange()
		{
			ajax_123_2();
			search123();
		}

		function getTargetValue(id,branchid,target)
		{
			return '<div class="branchspan"  ><span  id="'+branchid+'">'+target+'</span></div><div class="branchtext" style="display:none" ><input   type="hidden" name="hbid" value="'+id+'"   class="txt_box_100"  /><input   type="text" name="btarget" value="'+target+'"   class="txt_box_100"  /></div>';
		}
		
		function updatebranchtarget(obj)
		{
			if(obj==1)
			{
				$(".branchspan").attr("style","display:none");
				$(".branchtext").attr("style","display:");
				uobj=1
			}else
			{
				$(".branchspan").attr("style","display:");
				$(".branchtext").attr("style","display:none");
				uobj=0;
			}
		}
		
		function updatebt()
		{
			if(uobj==0)
			{
				alert("请先操作编辑学习中心！！");
			}else
			{
				var targettotal=$('#target').html();
				btargetNum=0;
				hbidStr='';
				btargetStr='';
				$(".branchtext input[name='hbid']").each(function(){
					hbidStr+=","+this.value;
				});
				$(".branchtext input[name='btarget']").each(function(){
					btargetNum+=parseInt(this.value);
					btargetStr+=","+this.value;
				});
				if(btargetNum!=targettotal)
				{
					if(btargetNum>targettotal)
					{
						alert("学习中心指标为"+btargetNum+",超出"+(btargetNum-targettotal)+"个指标,请重新分配");
					}else
					{
						alert("学习中心指标为"+btargetNum+",未分配"+(targettotal-btargetNum)+"个指标,请重新分配");
					}
					return;
				}else
				{
					ajax_123_4();
				}
				
				
				
			}
			
		}
		
		
		
		function getAcademylst(academylst)
		{
			var lists='';
		   	var count=1;
			if(academylst!=null && academylst.length>0)
			{
				$.each(academylst,function(){
				if(count==academylst.length)
				{
					lists+='<div class="bbborder">';
					lists+=this.name;
					lists+='</div>';
					return;
				}
				lists+='<div class="bborder">';
				lists+=this.name;
				lists+='</div>';
				count++;
				});
			}
			return lists;
			
		}
		
		function getTargetNum(id,academylst)
		{
			var lists='';
		   	var count=1;
			if(academylst!=null && academylst.length>0)
			{
				$.each(academylst,function(){
				if(count==academylst.length)
				{
					lists+='<div id="div'+id+'"  class="bbborder">';
					lists+='<input type="text"  id="txt'+id+'_'+this.id+'" name="txt'+id+'" value="'+this.target+'" class="txt_box_100" >';
					lists+='<span id="sp'+id+'_'+this.id+'" ></span></div>';
					return;
				}
				lists+='<div id="div'+id+'" class="bborder">';
				lists+='<input type="text" id="txt'+id+'_'+this.id+'" name="txt'+id+'" value="'+this.target+'" class="txt_box_100" >';
				lists+='<span id="sp'+id+'_'+this.id+'" ></span></div>';
				count++;
				});
			}
			return lists;
		}
		
		function getoperating(id,academylst)
		{
			var lists='';
		   
			if(academylst!=null && academylst.length>0)
			{
				lists+='<div class="bbborder">';
				var academyId='_';
				$.each(academylst,function(){
				academyId+=this.id+"_";
				});
				lists+='<a id="'+id+academyId+'" href="javascript:void(0)" onclick="btnclick(this.id)"  >确认</a>';
				lists+='</div>';
			}
			return lists;
		}
		
		function btnclick(id)
		{
			target="";
			//获取参数
			bId=id.substring(0,id.indexOf('_'));
			aId=id.substring(id.indexOf('_')+1,id.length);
			var branchtarget=$('#'+bId).html();
			var totaltarget=0;
			var atext=$('#'+id).html();
			if(atext=="确认")
			{
				//jQuery("#div"+bId+" input[type='text']").each(function(){
				//为了兼容IE6的选择器问题
				jQuery("input[name='txt"+bId+"']").each(function(){
					totaltarget+=parseInt(this.value);
					target+=parseInt(this.value)+"_";
				});
				if(isNaN(totaltarget))
				{
					show('numDiv','提示',250,150);
					return;
				}
				
				if(totaltarget!=branchtarget)
				{
					jQuery('#assignquotaDiv').html('分配的指标(<span style="color:red;">'+totaltarget+'</span>)必须等于中心指标(<span style="color:red;">'+branchtarget+'</span>)');
					show('assignquotaDiv','提示',250,150);
					return;
				}
				//if(totaltarget!=branchtarget)
				//{
				//	alert(totaltarget+'_'+branchtarget);
				//	show('assignquotasDiv','提示',250,150);
				//	return;
				//}
				
				/**param=aId.split('_');
				paramtarget=target.split('_');
				if(param!=null)
				{
					for(var i=0;i<param.length-1;i++)
					{
						$('#txt'+bId+"_"+param[i]).css({"display":"none"});
						$('#sp'+bId+"_"+param[i]).css({"display":""});
						if(null!=paramtarget && paramtarget.length>0)
						{
							if(paramtarget[i].substring(0,1)=="0")
							{
								paramtarget[i]=paramtarget[i].substring(1,paramtarget[i].length);
							}
							$('#sp'+bId+"_"+param[i]).html(paramtarget[i]);
						}
						
					}
				}
				$('#'+id).html("修改");**/
				ajax_123_3();
				
			}
			if(atext=="修改")
			{
				
				if(param!=null)
				{
					for(var i=0;i<param.length-1;i++)
					{
						$('#txt'+bId+"_"+param[i]).css({"display":""});
						$('#sp'+bId+"_"+param[i]).css({"display":"none"});
						if(null!=paramtarget && paramtarget.length>0)
						{
							if(paramtarget[i].substring(0,1)=="0")
							{
								paramtarget[i]=paramtarget[i].substring(1,paramtarget[i].length);
							}
							$('#sp'+bId+"_"+param[i]).html(paramtarget[i]);
						}
						$('#txt'+bId+"_"+param[i]).val($('#sp'+bId+"_"+param[i]).html());
						
					}
				}
				$('#'+id).html("确认");
				
			}
			
			
			
		}
		
		function getBranchName(batchId,branchId,branchName)
		{
			return '<a href="'+WEB_PATH+'/enrollment/enrollquota/list_branch_recruit_peoples?batchId='+batchId+'&branchId='+branchId+'">'+branchName+'</a>'; 
		}
		
		function getcompletedValue(academylst)
		{
			var lists='';
		   	var count=1;
			if(academylst!=null && academylst.length>0)
			{
				$.each(academylst,function(){
				if(count==academylst.length)
				{
					lists+='<div class="bbborder">';
					lists+=this.complete;
					lists+='</div>';
					return;
				}
				lists+='<div class="bborder">';
				lists+=this.complete;
				lists+='</div>';
				count++;
				});
			}
			return lists;
		}	
		
		
		/*
		  页面首次加载
		*/
		$(function(){
			ajax_123_1();
			
			//信息提示
			jQuery('#message_returns_tips').dialog({
				autoOpen:false,
				modal:true,
				draggable:false,
				resizable:false,
				title:'信息提示',
				buttons: {
					'关闭': function() { 
						jQuery(this).dialog("close"); 
					} 
				}
			});		
		});
	
			//刷新
			function refreshbranch()
			{
				if(jQuery('#selbatch').html()=="" || jQuery("#selbatch").val()==0)
				{
					jQuery("#showDialog").html('<b>请选择批次！</b>');
					jQuery('#message_returns_tips').dialog("open");
				}
				else
				{
					$('#message_confirm').dialog({
						title:'确认操作',
						buttons: {
							'确认': function() { 
									$(this).dialog("close"); 
									ajax_110_1();
									
								}, 
							'取消': function() { 
									$(this).dialog("close"); 
								} 
						}
					});
					$('#message_confirm').dialog("open"); 
				}	
			}
			
			//刷新其他学习中心			
			function ajax_refresh(data)
			{		
				if(data.count==0)
				{
					jQuery("#showDialog").html('<b>该批次还未分配学习中心指标！</b>');
					jQuery('#message_returns_tips').dialog('open');			
					
				}
				else if(data.count==1)
				{
					jQuery("#showDialog").html('<b>无需刷新添加的学习中心！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
				else
				{
					ajax_123_2();
					search123();
					jQuery("#showDialog").html('<b>刷新并添加成功！</b>');
					jQuery('#message_returns_tips').dialog('open');
				}
			}
		</script>
		
		<!--刷新其他学习中心-->
		<a:ajax 
			successCallbackFunctions="ajax_refresh" 
			parameters="{batchId:jQuery('#selbatch').val()}" 
			urls="/enrollment/enrollquota/refresh_branch_enroll_quota_ajax" 
			pluginCode="110"
		/>
  </head>
  
  <body>
   <!--头部层开始 -->
		<head:head title="学习中心招生指标">
		</head:head>
		<!--主体层开始 -->
		<body:body> 
		
		<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">学习中心招生指标  </th>	
				</tr>
		</table>	
		
		<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
		   <tr>
					<td align="right" width="15%">批次:</td>
					<td align="left" width="15%">
						<select id="selbatch" onchange="selbatchchange()"  class="txt_box_100" >
																	
					  </select>
					</td>
					<td align="right" width="15%">指标数:</td>
					<td align="left" width="15%">
						<span id="target"></span>
					</td>
					<td align="left" width="40%">
					<input type="button" onclick="updatebranchtarget(1)"  name="" id="" value="编辑中心指标" class="btn_black_130" />
					<input type="button" onclick="updatebt()"  name="" id="" value="保存" class="btn_black_61" />
					<input type="button" onclick="updatebranchtarget(2)"  name="" id="" value="取消" class="btn_black_61" />
					&nbsp;&nbsp;
					<input type="button" onclick="refreshbranch()"  name="" id="" value="刷新" class="btn_black_61" />
					<!-- 二期功能，暂时屏蔽 -->
					<!-- 
					<input type="button" onclick="download();" value="导出" class="btn_black_61" />
					 -->
					</td>
				</tr>
		 </table>	
		
		 <page:plugin 
				pluginCode="123"
				il8nName="enrollment"
				searchListActionpath="listbranchenrollquotabatchid"
				columnsStr="branchName;#target;academylst;targetNum;#completed;#operating"
				customColumnValue="0,getBranchName(batchId,branchId,branchName);1,getTargetValue(id,branchId,target);2,getAcademylst(academylst);3,getTargetNum(branchId,academylst);4,getcompletedValue(academylst);5,getoperating(branchId,academylst)"
				pageSize="10"
				isPage="false"
				isonLoad="false"
				ontherOperatingWidth="80px"	
				params="'batchId':$('#selbatch').val()"
	     />	
	
		
		
		<div id="valDiv" style="display:none">
			请输入要分配的指标数！！
		</div>	
		
		
		<div id="numDiv" style="display:none">
			指标数只能为数字！！
		</div>	
		
		<div id="assignsuccessDiv" style="display:none">
			指标分配成功！！
		</div>
		
		<div id="assignquotaDiv" style="display:none">
			
		</div>
		<!-- 
		<div id="assignquotasDiv" style="display:none">
			指标需一次性完全分配,请重新分配
		</div>
		 -->
		
   		</body:body>
   		<!--  弹出层           -->
		<div id="message_returns_tips" style="display:none">
			<div align="center" id="showDialog">
			
			</div>
		</div>
		<!--弹出层    确认操作-->
		<div id="message_confirm" style="display:none">
			<div align="center" >
				<b>确认刷新并添加该批次下没有的学习中心么？</b>
			</div>
		</div>
  </body>
</html>
