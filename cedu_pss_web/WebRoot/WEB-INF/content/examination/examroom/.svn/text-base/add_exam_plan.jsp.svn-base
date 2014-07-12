<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>添加考试计划</title>
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
		<jc:plugin name="loading" />
		<jc:plugin name="validator" />
			<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		 <script type="text/javascript">
		 jQuery(function(){
				//院校相关级联(批次、学习中心-层次-专业(防止刷新时其他不刷新))
				jQuery('#academyId').change(function(){
					
					selectbatch();
					selectbranch();
					//selectlevel();ajax延时放到批次里执行
				    //selectmajor();
				});	
				//招生批次相关级联(学习中心、层次、专业(防止刷新层次，专业不刷新))
				jQuery('#batchId').change(function(){
					selectbranch();
					selectlevel();
					//selectmajor();
				});	
				//层次专业级联
				jQuery('#levelId').change(function(){
					selectmajor();
				});	
			});
			
			
			 //读取批次
		    function selectbatch()
		    {
		    	$.ajax({
		    		url:'<s:url value="/enrollment/cascade_academy_batch_all_ajax" />',
		    		data:{academyId:jQuery('#academyId').val()},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#batchId').empty();
			    	    $('#batchId').append('<option value="0">--请选择--</option>');	
			    	 	if(data.batchlist!=null&&data.batchlist.length>0)
			    	 	{
			    	 	
				    	 	$.each(data.batchlist,function(){	
				    	 		$('#batchId').append('<option value="'+this.id+'">'+this.enrollmentName+'</option>'); 
				    	 	});
			    	 	}
			    	 	//selectlevel();//读取层次  	
			    	 }	
		    	});
		    }
			 //读取学习中心
		    function selectbranch()
		    {
		   		var batchId=$('#batchId').val();
		    	$.ajax({
		    		url:'<s:url value="/enrollment/cascade_batch_branch_ajax" />',
		    		data:{batchId:batchId,academyId:jQuery('#academyId').val()},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#branchId').empty();
			    	 	$('#branchId').append('<option value="0">--请选择--</option>');	
			    	 	if(data.branchlist!=null&&data.branchlist.length>0)
			    	 	{
				    	 	$.each(data.branchlist,function(){	
				    	 		$('#branchId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
				    	 	});
			    	 	}	
			    	 }	
		    	});
		    }
			 //读取层次
		    function selectlevel()
		    {
		   		var batchId=$('#batchId').val();
		    	$.ajax({
		    		url:'<s:url value="/enrollment/cascade_batch_level_ajax" />',
		    		data:{batchId:batchId},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#levelId').empty();
				    	$('#levelId').append('<option value="0">--请选择--</option>');
			    	 	if(data.levellist!=null&&data.levellist.length>0)
			    	 	{	
				    	 	$.each(data.levellist,function(){	
				    	 		$('#levelId').append('<option value="'+this.id+'">'+this.level.name+'</option>'); 
				    	 	});
				    	 }	
				    	 selectmajor();//读取专业
			    	 }	
		    	});
		    	
		    }
			 //读取专业
		    function selectmajor()
		    {
		   		var levelId=$('#levelId').val();
		    	$.ajax({
		    		url:'<s:url value="/enrollment/cascade_level_major_ajax" />',
		    		data:{levelId:levelId},
		    		dataType:'json',
		    	 	type:'post',
		    	 	success:function(data){
		    	 		$('#majorId').empty();
			    	 	$('#majorId').append('<option value="0">--请选择--</option>');
			    	 	if(data.majorlist!=null&&data.majorlist.length>0)
			    	 	{
			    	 		$.each(data.majorlist,function(){	
			    	 		$('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>'); 
			    	 		});
			    	 	}	
			    	 }	
		    	});
		    }
	    	//var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;//email
	    	$(document).ready(function(){
				//if(!${addrlt}){
					//alert("数据库存在重复数据,请重新填写数据");
				//}
				$.formValidator.initConfig({formID:"form1",debug:false,submitOnce:true,
					onError:function(msg,obj,errorlist){
						alert(msg);
					}
				});
					$("#examSubject").formValidator({onShow:"请输入考试科目",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"科目不能为空,请确认"});
					$("#planCount").formValidator({onShow:"请输入考试人数",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"考试人数不能为空,请确认"});
					$("#plan").formValidator({onShow:"请输入考试计划",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"考试计划不能为空,请确认"});
					$("#examBeginTime").formValidator({onShow:"请输入考试时间",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"时间不能为空,请确认"});
					$("#examEndTime").formValidator({onShow:"请输入考试时间",onFocus:"至少1个长度",onCorrect:"输入正确"}).inputValidator({min:1,empty:{leftEmpty:false,rightEmpty:false},onError:"时间不能为空,请确认"});
					//$("#endTime").formValidator({empty:true,onShow:"请输入考试时间",onFocus:"你要是输入了，必须输入正确",onCorrect:"谢谢你的合作",onEmpty:"你真的不想留手机号码啊？"}).inputValidator({min:11,max:11,onError:"手机号码必须是11位的,请确认"}).regexValidator({regExp:"mobile",dataType:"enum",onError:"你输入的手机号码格式不正确"})
			});
	    </script>
	   
  </head>
  
  <body>
   <form id="form1" action="add_exam_plan" method="post">
<div id="title_new">
	<div id="contitle">
		<ul id="tags">
			 <li class="selectTag"><a>添加考试计划</a> </li>
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
			<table class="gv_table_2">
		  	<tr>
				 	<th style="width:20px;"><img src="<ui:img url="images/title_left.gif"/>" /></th>
				 	<th style="text-align:left; font-weight:bold;">场次信息</th>
			  </tr>
			</table>
				<table class="add_table">  
				    <tr>
		                <td class="lable_150">院校：</td>
		                <td align="left">
		                	<s:if test="%{academylist!=null}">
								<s:select list="%{academylist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="examplan.academyId" id="academyId" cssClass="txt_box_150"/>
							</s:if>
		                	<s:else>
		                		<select name="examplan.academyId" id="academyId" class="txt_box_150">
									<option value="0">--请选择--</option>
								</select>
		                	</s:else>	
						</td>
						</tr>
						<tr>
		                <td class="lable_150">招生批次：</td>
		                <td align="left">
							<select name="examplan.batchId" id="batchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
						</tr>
						<tr>
						<td class="lable_150">学习中心：</td>
		                <td align="left">
							<select name="examplan.branchId" id="branchId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
							
						</td>
						</tr>
						<tr>
		                <td class="lable_150">层次：</td>
		                <td>
							<select name="examplan.levelId" id="levelId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
	              	</tr>
				  	<tr>
		                <td class="lable_150">专业：</td>
		                <td align="left">
							<select name="examplan.majorId" id="majorId" class="txt_box_150">
								<option value="0">--请选择--</option>
							</select>
						</td>
                    </tr>
					<tr>
						<td class="lable_150">考试批次：</td>
						<td><s:select list="%{exambatchlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="code" name="examplan.examBatchId" id="examBatchId" cssClass="txt_box_130"/></td>
                    </tr>
				    <tr>

				      	<td  class="lable_150">考场计划：</td>
				        <td>
				        	<input type="text" class="txt_box_200" name="examplan.plan" id="plan" />
				        </td>
						
					</tr>
					<tr>	
						<td class="lable_150">考场时间：</td>
				        <td>
						<input name="examplan.examDate" id="examDate" type="text"  class="txt_box_200" onfocus="WdatePicker({skin:'whyGreen'})"  readonly="readonly" />
						<select class="txt_box_100" name="examplan.examBeginTime" id="examBeginTime">
						     <option value="0">--请选择--</option>
							 <option value="08:30">08:30</option>
							 <option value="09:00">09:00</option>
							 <option value="09:30">09:30</option>
							 <option value="10:00">10:00</option>

							 <option value="10:30">10:30</option>
							 <option value="11:00">11:00</option>
							 <option value="11:30">11:30</option>
							 <option value="12:00">12:00</option>
							 <option value="12:30">12:30</option>
							 <option value="13:00">13:00</option>
							 <option value="13:30">13:30</option>
							 <option value="14:00">14:00</option>
							 <option value="14:30">14:30</option>
							 <option value="15:00">15:00</option>
							 <option value="15:30">15:30</option>
							 <option value="16:00">16:00</option>

							 <option value="16:30">16:30</option>
							 <option value="17:00">17:00</option>
							 <option value="17:30">17:30</option>
							 <option value="18:00">18:00</option>
						</select>
						~ <select class="txt_box_100" id="examEndTime" name="examplan.examEndTime">
						     <option value="0">--请选择--</option>
							 <option value="08:30">08:30</option>
							 <option value="09:00">09:00</option>
							 <option value="09:30">09:30</option>
							 <option value="10:00">10:00</option>

							 <option value="10:30">10:30</option>
							 <option value="11:00">11:00</option>
							 <option value="11:30">11:30</option>
							 <option value="12:00">12:00</option>
							 <option value="12:30">12:30</option>
							 <option value="13:00">13:00</option>
							 <option value="13:30">13:30</option>
							 <option value="14:00">14:00</option>
							 <option value="14:30">14:30</option>
							 <option value="15:00">15:00</option>
							 <option value="15:30">15:30</option>
							 <option value="16:00">16:00</option>

							 <option value="16:30">16:30</option>
							 <option value="17:00">17:00</option>
							 <option value="17:30">17:30</option>
							 <option value="18:00">18:00</option></select></td>
				     </tr>
					  <tr>

					    <td class="lable_150">考试科目：</td>
						<td>
						<input type="text" name="examplan.examSubject" id="examSubject" class="txt_box_200"/>
						</td>
					 </tr>
					  <tr>
					    <td class="lable_150">考试人数：</td>
						<td>

						<input type="text" name="examplan.planCount" id="planCount" class="txt_box_200"/>&nbsp;人
						</td>
					 </tr>
					 <tr>
					    <td class="lable_150">备注：</td>
						<td>
						<textarea cols="38" rows="6" id="remark" name="examplan.remark"></textarea>
						</td>

					 </tr>
					 <tr>
						<td colspan="2" align="center">
						 <input type="submit" value="添加" name="" id="" class="btn_black_61"/>
						</td>
					 </tr>
				</table>	
        </div>
     </div>

  </div>
</form>
  </body>
</html>
