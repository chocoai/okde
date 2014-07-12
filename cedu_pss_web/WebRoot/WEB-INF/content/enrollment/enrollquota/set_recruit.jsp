<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>设置批次指标</title>
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
		<script type="text/javascript">
		var ids=0;
		var eqbtarget=0;
		var batchIds=0;
		var branchIds="";
		var branchTargets="";
		function ajax_findenrollquotabatch(data)
		{
			batchIds=data.enrollquotabatch.batchId;
			$('#enrollyear').html(data.enrollquotabatch.enrollYear);
			$('#enrollquota').html(data.enrollquotabatch.enrollQuota);
			$('#batch').html(data.enrollquotabatch.batchName);
			$('#assignquota').val(data.enrollquotabatch.target);
		}
		
		function ajax_assignquotabatch(data)
		{
			if(data.bol)
			{
				$('#quotaDiv').html('无法分配指标,年度总指标只剩余'+data.surplusquota+'个,请重新分配');
				show('quotaDiv','提示',300,150);
				return;
			}
			eqbtarget=data.enrollquotabatch.target;
			$('#signquota').html('(已分配'+data.enrollquotabatch.target+'个指标)');
			$('#bid').val(data.enrollquotabatch.batchId);
			$('#pageDiv').attr('style','display:block');
			search123();
		}
		
		function ajax_addbranchenrollquotas(data)
		{
			if(data.bol)
			{
				show('assignsuccessDiv','提示',250,150);
			}else
			{
				show('assignerrorDiv','提示',250,150);
			}	
			
		}
		
		</script>
		<a:ajax parameters="{'id':ids};{'id':ids,'quota':$('#assignquota').val()};{'batchId':batchIds,'branchIds':branchIds,'branchTarget':branchTargets}"
		successCallbackFunctions="ajax_findenrollquotabatch;ajax_assignquotabatch;ajax_addbranchenrollquotas" 
		urls="/enrollment/enrollquota/findenrollbatch;/enrollment/enrollquota/assignquotabatch;/enrollment/enrollquota/addbranchenrollquotas"
		pluginCode="123" 
		
		/>
		<script type="text/javascript">
		
		
		
		function signquota()
		{
			var quota=$.trim($('#assignquota').val());
			if(quota=="")
			{
				show('valDiv','提示',250,150);
				return;
			}
			if(isNaN(quota))
			{
				show('numDiv','提示',250,150);
				return;
			}
			ajax_123_2();
			
		}
		
		function getbranchnameValue(name)
		{
			return name;
		}
		
		function getlevelValue(level)
		{
			return level+"级";
		}
		
		function branchquota(id,currentBatchTarget)
		{
			return '<input class="branchquota" type="text" id="'+id+'" class="txt_box_100" onblur="onblurSet(this);" value="'+currentBatchTarget+'"/>';
		}
		
		function addbranchquota()
		{
			var count=0;
			branchIds="";
			branchTargets="";
			jQuery(".branchquota").each(function(){
				
				if(isNaN(this.value))
				{
					show('numDiv','提示',250,150);
					count=0;
					return;
				}else
				{
					if($.trim(this.value)!="")
					{
						count+=parseInt(this.value);
					}					
				}
				
				branchIds+=this.id+",";
				if(this.value=="")
				{
				branchTargets+=0+",";
				}else{
				branchTargets+=this.value+",";
				}
			});
			if(count>eqbtarget)
			{
				show('assignquotaDiv','提示',250,150);
				return;
			}
			
			if(count!=eqbtarget)
			{
				show('assignDiv','提示',250,150);
				return;
			}
			
			ajax_123_3();

		}
		
		function onblurSet(inputs)
		{
			var values=inputs.value;
			if(-1!=values.indexOf("."))
				values=values.substring(0,values.indexOf("."));
			inputs.value=values;
			showcount();
		}
		
		function showcount()
		{
			var count=0;
			branchIds="";
			branchTargets="";
			jQuery(".branchquota").each(function(){
				
				if(isNaN(this.value))
				{
					show('numDiv','提示',250,150);
					count=0;
					return;
				}else
				{
					if($.trim(this.value)!="")
					{
						count+=parseInt(this.value);
					}					
				}
				
				branchIds+=this.id+",";
				if(this.value=="")
				{
				branchTargets+=0+",";
				}else{
				branchTargets+=this.value+",";
				}
			});
			jQuery('#showcount').val("共录入:"+count);
		}
		
		function sumTarget_callback123(data)
		{
			showcount();
		}

		
		/*
		  页面首次加载
		*/
		$(function(){
		ids='${param.id}';
		ajax_123_1();
		
	
	});

		</script>
	
  </head>
  
  <body>
   <!--头部层开始 -->
		<head:head title="设置批次指标">
		</head:head>
		<!--主体层开始 -->
		<body:body> 
		<table class="gv_table_2">
		  		<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">设置招生指标 </th>	
				</tr>
		</table>	
			
			
		<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
		   
		   <tr>
		  	<td>年度：<span id="enrollyear"></span></td>
			<td>年度总指标：<span id="enrollquota"></span></td>
		   </tr>
		   <tr>
		  	<td>批次：
			  	<span id="batch"></span>
			  	<span id="signquota"></span>
			  	<input type="hidden" id="batchid" name="batchid" />
		  	</td>
			<td>批次总指标：<input type="text" id="assignquota" name="assignquota" class="txt_box_100" />
			<input type="button" name="" id="" onclick="signquota()" value="分配" class="btn_black_61"  /></td>
		   </tr>
		 </table>
		 
		 <input type="hidden" id="bid" name="bid"/>
		 
		 <div id="pageDiv" style="display:none">
		 <page:plugin 
				pluginCode="123"
				il8nName="enrollment"
				searchListActionpath="listbranchquota"
				columnsStr="branchname;level;userCount;batchTarget;batchComplete;#branchquota"
				customColumnValue="0,getbranchnameValue(name);1,getlevelValue(level);5,branchquota(id,currentBatchTarget)"
				pageSize="10"
				isPage="false"
				isonLoad="false"
				ontherOperatingWidth="80px"	
				params="'batchId':$('#bid').val()"
				listCallback="sumTarget_callback"
	/>	
		 <table class="gv_table_2">
		 	<tr>
		 	<th ><div style="float:right"></div></th>
			<th >
				<div style="float:right">
				<input type="button" name="" id="showcount" value="共录入：0" />
				<input type="button" onclick="addbranchquota()"  name="" id="" value="保存" class="btn_black_61" />
				</div>
			</th>
			</tr>
		 </table>
		 
		 </div>
		 
		 
		 
		 
		 
		 
		<div id="valDiv" style="display:none">
			请输入要分配的指标数！！
		</div>	 
		
		<div id="numDiv" style="display:none">
			指标数只能为数字！！
		</div>	
		
		<div id="quotaDiv" style="display:none">
			
		</div>	
		 
		<div id="assignquotaDiv" style="display:none">
			分配的指标不能大于批次总指标,请重新分配
		</div>
		
		<div id="assignDiv" style="display:none">
			请全部分配批次总指标,不能有剩余指标！！
		</div>
		
		<div id="assignsuccessDiv" style="display:none">
			指标分配成功！！
		</div>
		
		<div id="assignerrorDiv" style="display:none">
			指标分配失败！！
		</div>
		 
   		</body:body>
  </body>
</html>
