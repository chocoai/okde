<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../../template/common/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>批次总指标</title>
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
		var year=0;
		var batchIds="";
		var id='${param.id}';
		/*
		查询所有年度
		*/
		function ajax_listenrollquotaall(data)
		{
			var lists='';
			lists+='<option value="0">全部</option>';
			$.each(data.enrollquotalst,function(){
				lists+='<option value="'+this.id+'">'+this.enrollYear+'</option>';
			});
			$('#year').html("");
			$('#year').html(lists);
			if(id!='')
			{
				$('#year').val(id);
			}
			
			ajax_123_2();
			
		}
		
		/*
		
		*/
		function ajax_listenrollbatch(data)
		{
			var listbatch='';
			var index = 0;
			// 原批次列表为复选框形式 屏蔽代码
			//$.each(data.globalenrollbatchlst,function(){
			//	if(index>0 && index%9==0){
			//		listbatch+='<br/>'
			//	}
			//	listbatch+='<input type="checkbox" name="chk" value="'+this.id+'" >'+this.batch+'</input>&nbsp;&nbsp;&nbsp;&nbsp';
			//	index++;
			//});
			
			// 需求变更为下拉框形式 
			listbatch+='<select id="batchId" class="txt_box_130">';
			listbatch+='<option value="0" >--请选择--</option>';
			$.each(data.globalenrollbatchlst,function(){
				listbatch+='<option value="'+this.id+'" >'+this.batch+'</option>';
				index++;
			});
			listbatch+='</select>';
			$('#batchDiv').html("");
			$('#batchDiv').html(listbatch);
			search123();
		}
		
		</script>
		
		<a:ajax parameters="null;{'id':$('#year').val()}"
		successCallbackFunctions="ajax_listenrollquotaall;ajax_listenrollbatch" 
		urls="/enrollment/enrollquota/listenrollquotaall;/enrollment/enrollquota/listenrollbatch"
		pluginCode="123" 
		/>
		<script type="text/javascript">
		

		function ckall(obj)
		{
			$('#batchDiv :checkbox').attr('checked',obj.checked);
		}
		
		function valuechk()
		{
			var bIds="";
			// 查询条件为复选框时的代码 屏蔽
			//jQuery("#batchDiv [name='chk']:checkbox").each(function(){
			//	if(this.checked)
			//	{
			//		bIds+=this.value+",";
			//	}
			//});
			
			// 改为下拉框(由于是从复选框条件改的所以需要特殊处理)
			bIds = jQuery("#batchId").val();
			if(bIds=='0'){
				bIds='';
			}else{
				bIds=bIds+',';
			}
			$('#batchids').val(bIds);
			search123();
			
		}
		
		/*
		  批次显示
		*/
		function batchNameValue(batchName,batchId)
		{
			return '<a href="'+WEB_PATH+'/enrollment/enrollquota/list_recruit_students?batchId='+batchId+'">'+batchName+'</a>';
		}

		/*
		  数据显示
		*/
		function types_false(types,number)
		{
			if(types==STATUS_AUTHOR_FALSE)
			{
				return '';
			}
			else
			{
				return number;
			}
		
		}

		/*
		  操作
		*/
		function operating(id,types)
		{
				return '<a href="'+WEB_PATH+'/enrollment/enrollquota/set_recruit?id='+id+'">指标设定</a>';
		}

		/*
		  页面首次加载
		*/
		$(function(){
		
		ajax_123_1();
		
	
	});
	

		</script>
	
  </head>
  
  <body>
   <!--头部层开始 -->
		<head:head title="批次总指标">
		</head:head>
		<!--主体层开始 -->
		<body:body> 
		
			<!--Menu Begin-->
			<%@ include file="../_tab/enroll_quota_tab.jsp" %>
			<!--Menu End-->
		<input type="hidden" id="batchids" name="batchids" />
		<table class="add_table" cellpadding="0" cellspacing="0" border="0" width="100%">
		   <tr>
		   <td align="right">年度：</td>
		   <td align="left">
		   <select name="year" id="year" onchange="ajax_123_2()" class="txt_box_130">			
				   </select>
		   </td>
		   
			</tr>
		   <tr>
				<td align="right">批次：</td>
				<td align="left">
				<!-- 
				<input type="checkbox" id="chkall" name="chkall" onclick="ckall(this)" />全选
				-->
				<div id="batchDiv" > 
					
				</div>
				</td>
				<td colspan="2" align="center"><input type="button" onclick="valuechk()" name="" id="" value="查询" class="btn_black_61" /></td>
		   </tr>
		 </table>	

		
		<page:plugin 
				pluginCode="123"
				il8nName="enrollment"
				searchListActionpath="listenrollquotabatch"
				columnsStr="batchName;target;registNumber;feePaymentNumber;admitNumber;admitfeePaymentProportion;targetfeePaymentProportion;registadmitProportion;targetadmitProportion;#operating"
				customColumnValue="0,batchNameValue(batchName,batchId);1,types_false(types,target);2,types_false(types,registNumber);3,types_false(types,feePaymentNumber);4,types_false(types,admitNumber);5,types_false(types,admitfeePaymentProportion);6,types_false(types,targetfeePaymentProportion);7,types_false(types,registadmitProportion);8,types_false(types,targetadmitProportion);9,operating(id,types)"
				pageSize="10"
				isPage="false"
				isonLoad="false"
				ontherOperatingWidth="80px"	
				params="'quotaId':$('#year').val(),'batchIds':$('#batchids').val()"
				isOrder="false"
	/>	

   		</body:body>
  </body>
</html>
