<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="net.cedu.entity.admin.User"%>
<%@ include file="../../template/common/import.jsp"%>
<%@ taglib  prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	 <title>新增中心申请</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- 表单验证 -->
		<jc:plugin name="validator" />
		<!-- 基础JS -->
		<jc:plugin name="base_js" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		<!-- 分页插件 -->
		<jc:plugin name="page" />
		<script type="text/javascript">
		 jQuery(function(){
	 
 			addStandardItem();
		    //物料级联
		 	jQuery("select[name='meterialid']").change(function(){
	 		meterialName();
			});	
		});	
 			
 
		//ajax回调函数  物料名称		 
		function meterialName()
	    {
	    	$.ajax({
	    		url:'<s:url value="/meterial/meterialstock/find_meterial_ajax" />',
	    		data:{meterialId:jQuery('#meterialId').val()},
	    		dataType:'json',
	    	 	type:'post',
	    	 	success:function ajax_meterialajax(data)
				 {
	    	 		$("select[name='meterialname']").empty();
		    	 	$("select[name='meterialname']").append('<option value="0">--请选择--</option>');	
		    	 	if(data.meteriallist!=null&&data.meteriallist.length>0)
		    	 	{
			    	 	$.each(data.meteriallist,function(){	
			    	 		$("select[name='meterialname']").append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	 	});
		    	 	}			    	 	
		    	 }			
	    	});
	    }
		
		//提交前的验证
		function show()
		{
			if(jQuery("#applicationId").val()=="")
			{
				alert('申请单编号不能为空！');
			}
			else if(jQuery("#meterialId").val()==0)
			{
				alert('请选择物品分类！');
			}
				else if(jQuery("#meterialname").val()==0)
			{
				alert('请选择物料名称！');
			}
			else if(jQuery("#shuliang").val()==""  )
			{
				alert('请输入申请数量！');
			}
			else if(jQuery("#quantity").val()==""  )
			{
				alert('请输入价格！');
			 
			}else{
			insertRelationships();
					jQuery("#submits").submit();
			}
		}
 
  		//添加标准项		
		function addStandardItem()
		{
			var meterial = $('#standardTemplate tr').clone();
			meterial.appendTo('#standardTable > tbody');	
			cooRN();
			number++;
		}
 		var number=1;
		
 		
		//删除方法
		function removemeterial(a)
		{
			if($('#rebate_table > tbody > tr').size()==1)
			{
			alert('您至少要填写一条！');
			return;
		    }
			var tr = $(a).parent().parent();
			tr.remove();	
			cooRN();
		}
 		
		function cooRN() //重新校正返款标准 行 序号
		{
			$('#standardTable > tbody > tr').each(function(index){
			$('.stdNo',this).html(index+1);
			$('.tfPrice', this).attr('class', 'tfPrice'+index+'');
			$('.tfQuantity', this).attr('class', 'tfQuantity'+index+'');
			$('.tfMeterialId', this).attr('class', 'tfMeterialId'+index+'');
		  });	
	    }
 
 
		function insertRelationships(){
 			var array=[];//选中的物料id数组
 			var quntion=[];//申请数量数组
 			var pric=[]; //单价数组
 			var meterialIds = "";//选中的物料id拼接起来的字符串
 			var quntionIds="";//填写的物料申请数量拼接起来的字符串
 			var arrayprics="";//填写的单价拼接起来的字符串
 			var application=$("#applicationId").attr("value");
 			//循环获取数组中的物料Id
 			if(number>0){ 				  
				for(var i=0;i<number;i++){
				array.push($("select[class='tfMeterialId"+i+"']").attr("value"));
 				quntion.push($("input[class='tfQuantity"+i+"']").attr("value"));
 				pric.push($("input[class='tfPrice"+i+"']").attr("value"));
 				}
				if(array.length==0){
					//未选中任何一个，直接点击选取按钮
					alert("至少选择一项");
				}else{
					//选中的拼接成字符串
					meterialIds=array.join(",");
					quntionIds=quntion.join(",");
					arrayprics=pric.join(",");
					//通过表单实现js post提交
				document.write("<form action='add_meterial_application_deta?array="+meterialIds+"&quntion="+quntionIds+"&pric="+arrayprics+"&application="+application+"' method='post' name='lecturerForm'    id='submits'  style='display:none'>");
				document.write("</form>");
				document.lecturerForm.submit();
				}
			}else{
				alert('错误');
			}
 		}
	</script>
  </head> 
	  <body> 
	<div id="title_new">
		<div id="contitle">
			<ul id="tags">
				 <li class="selectTag"><a>新增中心申请单</a></li>
			</ul>
		</div>	
	</div>
	<div class="block">
		<div class="public_table_bg_766">
			<div class="tb_table_default_2">
			<body:body>
			 <form id="form" action="add_meterial_application_deta" method="post"   >  
			  <table class="gv_table_2">
			  	<tr>
					 	<th style="width:20px;"><img src="../images/title_left.gif" /></th>
					 	<th style="text-align:left; font-weight:bold;">中心申请单信息</th>
				 </tr>
			  </table>
				<table  class="add_table" >
				<tr>
					<td width="15%"  align="right">申请单编号:</td>
					<td width="85%" align="left"><input type="text" name="applicationId" id="applicationId"/><br /></td>
				</tr>	
			</table>
			<table class="gv_table_2">
			  	<tr>
				 	<th style="width:20px;"><img src="<s:url value="/images/title_menu/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold; width:80px;">移库批量增加</th>
					<th></th>
					<th width="70" align="center"><a href="javascript:void(0);" onclick="addStandardItem()">添加标准项</a></th>
				</tr>
			</table>
			<table class="add_table" border="0" cellpadding="2" cellspacing="2" id="standardTable">
			 <thead>
			  <tr>
				<th> 编号</th>
				<th>物料分类</th>
				<th>物料名称</th>
				<th>单价</th>
				<th>申请数量</th>
				<th>操作</th>
			  </tr>
			 </thead>
			 <tbody></tbody>
			</table>
			<table class="add_table"><tr><td align="center"><input class="btn_black_61"   value="添加"   type="button"   onclick="show();"/></td></tr></table>		
		</form> 
			</body:body>
			<table style="display:none" id="standardTemplate">
				<tbody>
					<tr>
					 <td class="stdNo" > </td >				
					<td align="left">	 
					  	<s:select cssClass="tfMeterialId" list="categoryList" listKey="id" listValue="name" id="meterialId" name="meterialid" headerKey="0" headerValue="--请选择--"></s:select>
						 </td>	
						 <td align="left">
					  	<select  name="meterialname" id="meterialnames">
					  		<option value="0">--请选择--</option>
					  	</select>
						 </td>		
						<td><input type="text" class="tfPrice" id="shuliang"  /></td>
						<td><input type="text" class="tfQuantity" name="" id="quantity" /></td>
						<td><a href="javascript:void(0);" onclick="removemeterial(this)">删除</a></td>
					</tr>					 
				</tbody>				 
			  </table>
			  <div id="msgDiv" style="display:none">
			操作成功！！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /><br /><br />
		</div>
	   </div>
	  </div>
  </div>
  </body>
</html>
 
