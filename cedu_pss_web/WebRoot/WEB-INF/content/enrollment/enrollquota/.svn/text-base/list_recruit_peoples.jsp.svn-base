<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>招生指标分配</title>
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
		var uIdStr = '';
		var aIdStr = '';
		var targetStr = '';
		var completedStr ='';
		
		var academyArray = null; // 院校数组
		
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
			ajax_123_2();
		}
		
		function ajax_searchenrollqoutabranch(data)
		{
			if(data.branchenrollquota!=null)
			{
				$('#branch').html("");
				$('#target').html("");
				$('#branch').html(data.branchenrollquota.branchName);
				$('#target').html(data.branchenrollquota.target);
				$('#branchId').val(data.branchenrollquota.branchId);
				$('#tablepage').css({"display":""});
			}else
			{
				$('#branch').html("");
				$('#branchId').val(0);
				$('#target').html("");
				$('#tablepage').css({"display":"none"});
			}
			if(data.academyenrollquotalst!=null)
			{
				var lists='';
				lists+='<tr>';
				$.each(data.academyenrollquotalst,function(){
					lists+='<td align="center"><span id="acadename'+this.academyId+'" style="color:black;">'+this.academyName+'</span>(<span id="academy'+this.academyId+'">'+this.target+'</span>)</td>';	
				
				});
				lists+='</tr>';
				$('#academy_table').html("");
				$('#academy_table').html(lists);
			}else
			{
				$('#academy_table').html("");
				$('#academy_table').html('<tr><td align="center" >该中心未分配院校指标</td></tr>');
				$('#tablepage').css({"display":"none"});
			}
			search123();
			
		}
		
		
		function ajax_adduserenrollqouta(data)
		{
			jQuery('#loadingDiv').dialog("close"); 
			$('#assignDiv').html('指标分配成功！！');
			show('assignDiv','提示',250,150);
		}
		
		</script>
		<a:ajax parameters="null;{'batchId':$('#selbatch').val()};{'batchId':$('#selbatch').val(),'branchId':$('#branchId').val(),'academyIdStr':aIdStr,'userIdStr':uIdStr,'targetStr':targetStr,'completedStr':completedStr}"
		successCallbackFunctions="ajax_listbatch;ajax_searchenrollqoutabranch;ajax_adduserenrollqouta"
		urls="/enrollment/enrollquota/searchglobalbatch;/enrollment/enrollquota/searchenrollqoutabranch;/enrollment/enrollquota/adduserenrollqoutas"
		pluginCode="123" 
		/>
		<script type="text/javascript">
		
		function selbatchchange()
		{
			ajax_123_2();
		}

		function getacademyValue(userId,academylst)
		{
			var lists='';
		   	var count=1;
			if(academylst!=null && academylst.length>0)
			{
				$.each(academylst,function(){
				if(count==academylst.length)
				{
					lists+='<div class="bborder yonghuming" id="'+userId+'">';
					lists+='<a id="'+this.id+'" class="yuanxiao">'+this.name+'</a>';
					lists+='</div>';
					return;
				}
				lists+='<div class="bborder yonghuming" id="'+userId+'">';
				lists+='<a id="'+this.id+'" class="yuanxiao" id="'+userId+'">'+this.name+'</a>';
				lists+='</div>';
				count++;
				});
			}
			return lists;
		}
		
		function getTarget(id,academylst)
		{
			var lists='';
		   	var count=1;
			if(academylst!=null && academylst.length>0)
			{
				$.each(academylst,function(){
				if(count==academylst.length)
				{
					lists+='<div id="divtxt'+id+'"  class="bborder">';
					lists+='<input type="text"  id="txt'+id+'_'+this.id+'" value="'+this.target+'" class="txt_box_100 zhibiaoshu academy_zhibiao_'+this.id+'" >';
					lists+='<span id="sptxt'+id+'_'+this.id+'" style="display:none;">'+this.target+'</span></div>';
					return;
				}
				lists+='<div id="divtxt'+id+'"  class="bborder">';
				lists+='<input type="text"  id="txt'+id+'_'+this.id+'" value="'+this.target+'" class="txt_box_100 zhibiaoshu academy_zhibiao_'+this.id+'" >';
				lists+='<span id="sptxt'+id+'_'+this.id+'" style="display:none;">'+this.target+'</span></div>';
				count++;
				});
			}
			return lists;
		}

		function getCompletedNum(id,academylst)
		{
			var lists='';
		   	var count=1;
			if(academylst!=null && academylst.length>0)
			{
				$.each(academylst,function(){
				if(count==academylst.length)
				{
					lists+='<div id="divcom'+id+'" class="bborder">';
					lists+='<input type="text" id="txtcom'+id+'_'+this.id+'" value="'+this.expectTarget+'" class="txt_box_100 qiwangzhibiao academy_qiwang_'+this.id+'" >';
					lists+='<span id="spcom'+id+'_'+this.id+'" style="display:none;">'+this.expectTarget+'</span></div>';
					return;
				}
				lists+='<div id="divcom'+id+'" class="bborder">';
				lists+='<input type="text" id="txtcom'+id+'_'+this.id+'" value="'+this.expectTarget+'" class="txt_box_100 qiwangzhibiao academy_qiwang_'+this.id+'" >';
				lists+='<span id="spcom'+id+'_'+this.id+'" style="display:none;">'+this.expectTarget+'</span></div>';
				count++;
				});
			}
			return lists;
		}	
			
		function fullNameValue(id,fullName)
		{
			return '<span>'+fullName+'</span>';
		}
		
		
		function btnsubmit(){
			var targetsum = parseInt($('#target').html());		// 总指标数
			var academysum = 0;					// 院校叠加后的总指标数
			var academy = 0;							// 中心总指标
			var branchsum = 0;							// 累加各个中心总指标
			var expectbranchsum = 0;					// 累加各个中心期望总指标
			
			var bool = true;
			var isnumber=true;
			
			var r = /^\+?[0-9][0-9]*$/;　　//正整数
			
			// 重置
			uIdStr = '';
			aIdStr = '';
			targetStr = '';
			completedStr ='';
			
//			验证院校指标为二期功能(此功能已完成 暂时屏蔽此功能)
			var isErQi = false;
			if(isErQi){
			// 指标、期望指标（院校）
			if(academyArray!=null && academyArray.length>0){
				// 遍历全部授权院校
				$(academyArray).each(function(){
					var academyTarget = $('#academy'+this.id).html(); // 院校指标
					var academyTargetSum = 0; // 累加院校指标
					var academyExpectSum = 0; // 累加院校期望指标
					if(academyTarget!=null){
						// 遍历该院校全部指标
						$('.academy_zhibiao_'+this.id).each(function(){
							if(!r.test(this.value)){
								isnumber=false;
								return false;
							}
							academyTargetSum += parseInt(this.value);
						})
						// 遍历该院校全部期望指标
						$('.academy_qiwang_'+this.id).each(function(){
							if(!r.test(this.value)){
								isnumber=false;
								return false;
							}
							academyExpectSum += parseInt(this.value);
						})
						if(!isnumber){
							return false;
						}
						else{
							// 获取院校名称
							var academyName = $('#'+this.id).html();
							academyTarget = parseInt(academyTarget);
							// 叠加院校总指标
							academysum += academyTarget;
							// 判断院校指标数
							if(academyTargetSum!=academyTarget){
								$('#assignDiv').html('院校('+academyName+')<span style="color:red;">指标数('+academyTargetSum+')</span>总和数值必须<span style="color:red;">等于</span>该院校总指标('+academyTarget+')');
								show('assignDiv','提示',250,150);
								bool = false;
								return false;
							}
							// 判断院校指期望标数
							if(academyTarget!=0 && academyExpectSum<=academyTarget){
								$('#assignDiv').html('院校('+academyName+')<span style="color:red;">期望指标数('+academyExpectSum+')</span>总和数值必须<span style="color:red;">大于</span>该院校总指标('+academyTarget+')');
								show('assignDiv','提示',250,150);
								bool = false;
								return false;
							}
						}
					}
				})
			}
			}
			// 累加判断各个中心总指标和期望总指标
			if(bool)
			{
				// 指标（总和）
				$('.zhibiaoshu').each(function(){
					if(!r.test(this.value)){
						isnumber=false;
						return false;
					}
					branchsum += parseInt(this.value);
					targetStr+=","+this.value;
				});
				// 期望指标（总和）
				$('.qiwangzhibiao').each(function(){
					if(!r.test(this.value)){
						isnumber=false;
						return false;
					}
					expectbranchsum += parseInt(this.value);
					completedStr+=","+this.value;
				});
				// 用户
				$('.yonghuming').each(function(){
					uIdStr += ","+this.id;
				});
				// 院校
				$('.yuanxiao').each(function(){
					aIdStr += ","+this.id;
				});
			}
			
			// 查看数值
			//alert(targetsum+'_'+branchsum+'_'+expectbranchsum);
			if(!isnumber){
				$('#assignDiv').html('请输入数字');
					show('assignDiv','提示',250,150);
				return;
			}
			if(bool){
//				验证院校指标为二期功能(此功能已完成 暂时屏蔽此功能)
				if(isErQi){
				// 判断总指标和院校叠加后的总指标 是否一致
				if(academysum!=targetsum){
					$('#assignDiv').html('<span style="color:red;">总指标和院校指标之和不匹配，请联系总部操作指标的人员核对</span>');
					show('assignDiv','提示',250,150);
					bool = false;
					return false;
				}
				}
				// 判断中心总指标数
				if(branchsum!=targetsum){
					$('#assignDiv').html('<span style="color:red;">指标数('+branchsum+')</span>总和数值必须<span style="color:red;">等于</span>总指标('+targetsum+')');
					show('assignDiv','提示',250,150);
					bool = false;
					return false;
				}
				// 判断中心期望总指标数
				if(targetsum!=0 && expectbranchsum<=targetsum){
					$('#assignDiv').html('<span style="color:red;">期望指标数('+expectbranchsum+')</span>总和数值必须<span style="color:red;">大于</span>总指标('+targetsum+')');
					show('assignDiv','提示',250,150);
					bool = false;
					return false;
				}
			}
			// 不符合条件跳出
			if(!bool){
				return false;
			}
			$('#loadingDiv').html('数据较多，正在保存，请耐心等待...');
			show('loadingDiv','提示',250,150);
			ajax_123_3();
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
					lists+='<div class="bborder">';
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
		
		// 查询回调函数
		function search_callback123(data){
			if(data!=null && data.result!=null && data.result.list!=null && data.result.list.length>0){
				if(data.result.list[0]!=null && data.result.list[0].academylst!=null && data.result.list[0].academylst.length>0){
					academyArray = data.result.list[0].academylst;
				}
			}
		}
			
		/*
		页面首次加载
		*/
		$(function(){
			ajax_123_1();
			
			jQuery('#assignDiv').dialog({
				autoOpen:false,
				modal:true,
				draggable:false,
				resizable:false,
				title:'提示',
				buttons: {
				'关闭': function() { 
					jQuery(this).dialog("close"); 
					} 
				}
			});
			jQuery('#loadingDiv').dialog({
				autoOpen:false,
				modal:true,
				draggable:false,
				resizable:false,
				title:'提示'
			});
		});
		</script>
	
  </head>
  
  <body>
   <!--头部层开始 -->
		<head:head title="招生指标分配">
		</head:head>
		<!--主体层开始 -->
		<body:body> 
		
		<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">学习中心人员指标  </th>	
				</tr>
		</table>	
		
		
		
		<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
			<input type="hidden" id="branchId" name="branchId"/>
			<tr>
					<td width="15%" align="right">中心：</td>
					<td width="20%"><span id="branch"></span></td>
					<td width="15%" align="right">批次：</td>
					<td><select id="selbatch" onchange="selbatchchange()"  class="txt_box_100" >
																	
					  </select>
					</td>
					<td width="15%" align="right">总指标数:</td>
					<td width="20%"><span id="target"></span></td>
				</tr>	
				
		 </table>	
		 <table id="academy_table" class="add_table">
				
		 </table>
		
		<div id="tablepage" >
		 <page:plugin 
				pluginCode="123"
				il8nName="enrollment"
				searchListActionpath="listacademyenrollqouta"
				columnsStr="#username;academylst;targetnum;#expecttarget;#completed"
				customColumnValue="0,fullNameValue(id,fullName);1,getacademyValue(id,academylst);2,getTarget(id,academylst);3,getCompletedNum(id,academylst);4,getcompletedValue(academylst)"
				pageSize="10"
				isPage="false"
				isonLoad="false"
				ontherOperatingWidth="80px"	
				params="'batchId':$('#selbatch').val(),'branchId':$('#branchId').val()==null?0:$('#branchId').val()"
				listCallback="search_callback"
				/>	
				<div style="text-align:center;font-size: 20px;">
					<input type="button" value="提交数据" onclick="btnsubmit()" /><br/>
		 			
				</div>
		</div>
		
		<div id="assignDiv" style="display:none"></div>
		<div id="loadingDiv" style="display:none"></div>
		
   		</body:body>
  </body>
</html>
