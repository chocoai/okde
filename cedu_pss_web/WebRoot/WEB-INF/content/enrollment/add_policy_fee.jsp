<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新增院校收费标准</title>
		
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!--  分页 -->
		<jc:plugin name="page" />
		<!-- 选项卡 -->
		<jc:plugin name="tab" />
		<!-- 时间控件 -->
		<jc:plugin name="calendar" />
		<!-- 操作等待插件 -->
		<jc:plugin name="loading" />
		
		<script type="text/javascript">

			//加载事件
			jQuery(function(){
			
				//费用科目
				jQuery('#feesubjectId').change(function(){
					if(this.value==0)
					{
						jQuery("#addfee").hide();
						if(globalcount>2)//控制先选学费在改选杂费的缴费设置
						{
							for(var i=globalcount-1;i>1;i--)
							{
							 	removetr(i);
							}
						}
					}
					else
					{
						selectbatch(this.value);
					}
				});	
								
				//学制
				jQuery('#modeId').change(function(){
					var text = '';				
					if(this.selectedIndex==0)
					{
						jQuery("#feebatch").hide();
					}
					else
					{
						jQuery("#feebatch").show();
						
						if(jQuery("#modeId").val()==MODE_ACADEMIC_YEAR || jQuery("#iscontrol").val()==1)
						{
							text = '缴费';
							document.getElementById("myform").reset();//清空表单
							jQuery(".hidfee").html("");							
							jQuery("#feebatch > tbody").hide();
							
							jQuery("#hiddiv1").val(0);
							jQuery("#xuefen").val(0);//为了通过验证
							
						}
						else if(jQuery("#modeId").val()==MODE_CREDIT)
						{
							text = '学分';
							document.getElementById("myform").reset();//清空表单
							
							jQuery("#feebatch > tbody").show();
							
						}
						jQuery('.mode_name').each(function(){
							jQuery(this).text(text);
						});
					}
				});	
				jQuery('#feesubjectId').change();	
				
				//初始化信息提示
				$('#null_for_academy').dialog({
					autoOpen:false,
					modal:true,
					draggable:false,
					resizable:false,
					title:'信息提示',
					buttons: {
						'关闭': function() { 
							$(this).dialog("close"); 
							} 
					}
				});	
				
			});
			
			var globalcount=2;//全局变量	
			//添加缴费次数
			function addfeebatch()
		    {	    	
				//var list=jQuery('#feebatch > tfoot').html();
				var list="";
				list+='<tr id="ttr'+globalcount+'"><td></td>';
				list+='<td class="lable_100">第'+globalcount+'次缴费：</td>'
				list+='<td><input type="text" name="fee'+globalcount+'" id="fee'+globalcount+'" class="fee'+globalcount+'" value="" width="15px" onkeyup="javascript:showmoney(\'fee'+globalcount+'\');" />';
				list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				list+='<span id="hidfee'+globalcount+'" class="hidfee" style=""></span></td>';
				//list+='<td>';
				//list+='<input type="text" name="fee2'+globalcount+'" id="fee2'+globalcount+'" class="fee2'+globalcount+'" value="" width="15px" onkeyup="javascript:showmoney(\'fee2'+globalcount+'\');" />';
				//list+='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
				//list+='<span id="hidfee2'+globalcount+'" class="hidfee" style=""></span>';		  		
				//list+='</td>';
				list+='<td>';
				list+='<input class="Wdate" type="text" name="starttime'+globalcount+'" id="starttime'+globalcount+'" onclick="WdatePicker({skin:\'whyGreen\'})" readonly="readonly"/>';
				list+='</td>';
				list+='<td>';
				list+='<input class="Wdate" type="text" name="endtime'+globalcount+'" id="endtime'+globalcount+'" onclick="WdatePicker({skin:\'whyGreen\'})" readonly="readonly"/>';
				list+='</td>';
				list+='<td>';
				list+='<a id="a'+globalcount+'" href="javascript:removetr('+globalcount+');">删除</a>'
				list+='</td>'
				list+='</tr>';	
								
				jQuery(list).appendTo(jQuery('#feebatch > tfoot'));
				if(globalcount-1>1)
		    	{
		    		jQuery("#a"+(globalcount-1)).hide();
		    	}
		    	globalcount++;  
			    	
		    }	
		    function removetr(op)
		    {
		    	jQuery('#ttr'+op).remove();
		    	globalcount--
		    	if(globalcount-1>1)
		    	{
		    		jQuery("#a"+(globalcount-1)).show();
		    	}
		    }
		    
			//学分对应的钱数
			function showmoney(id)
			{
				var modename=jQuery("#modeId").find("option:selected").text();
				var modeId=jQuery("#modeId").val();
				if(modeId!=MODE_CREDIT || jQuery("#iscontrol").val()==1)
				{
					//if(!((/^\d{1,10}(\.\d{1,2})?$/).test(jQuery("#"+id).val())))
					if(dealwithmoney(jQuery("#"+id).val())==-1)
					{
						jQuery("#"+id).val("");
						//alert("输入格式不正确，只能输入整数");
						jQuery("#showDialog").html('<b>输入格式不正确，只能输入整数和小数！</b>');
						$('#null_for_academy').dialog("open");
					}
					else
					{
						jQuery("#hiddiv1").val(0);
						jQuery("#xuefen").val(0);//为了通过验证
					}
				}
				else
				{
				
					if((id!="xuefen" && id!="hiddiv1") && (jQuery("#xuefen").val()=="" || jQuery("#hiddiv1").val()==""))
					{
						jQuery("#"+id).val("");
						//alert("请先输入应修学分及其对应的金额");
						jQuery("#showDialog").html('<b>请先输入应修学分及其对应的金额！</b>');
						$('#null_for_academy').dialog("open");
					}
					//else if(!((/^\d{1,10}(\.\d{1,2})?$/).test(jQuery("#"+id).val())))
					else if(dealwithmoney(jQuery("#"+id).val())==-1)
					{
						jQuery("#"+id).val("");
						jQuery("#hid"+id).html("")
						if(id=="hiddiv1")
						{
							jQuery(".hidfee").html("");
						}
						//alert("输入格式不正确，只能输入整数");
						jQuery("#showDialog").html('<b>输入格式不正确，只能输入整数和小数！</b>');
						$('#null_for_academy').dialog("open");
					}
					else if(id=="xuefen" && !((/^\d{1,10}(\.\d{1,2})?$/).test(jQuery("#"+id).val())))
					{
						jQuery("#"+id).val("");
						jQuery("#hid"+id).html("")
						if(id=="hiddiv1")
						{
							jQuery(".hidfee").html("");
						}
						jQuery("#showDialog").html('<b>学分只能输入整数！</b>');
						$('#null_for_academy').dialog("open");
					}
					else if(jQuery("#xuefen").val()!="" && jQuery("#hiddiv1").val()!="")
					{
							
						if(id!="xuefen" && modeId==MODE_CREDIT && id!="hiddiv1")
						{
							var mon=dealwithmoney(jQuery("#hiddiv1").val());							
							var xuefen=dealwithmoney(jQuery("#"+id).val());
							//jQuery("#hid"+id).html(((mon-0)*(xuefen-0))+"元");
							jQuery("#hid"+id).html(accMul(mon,xuefen)+"元");
							
						}
						else
						{
							if(jQuery("#hiddiv1").val()!="" && jQuery("#xuefen").val()!="")
							{
								if(id=="hiddiv1")
								{
									jQuery(".hidfee").html("");
								}
								//jQuery("#hidxuefen").html(((dealwithmoney(jQuery("#hiddiv1").val())-0)*(dealwithmoney(jQuery("#xuefen").val())-0))+"元");
								jQuery("#hidxuefen").html(accMul(dealwithmoney(jQuery("#hiddiv1").val()),dealwithmoney(jQuery("#xuefen").val()))+"元");
							}
						}
					}
				}
			}
			
			//乘法精确成果
			function accMul(arg1, arg2) {
		        var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
		        try { m += s1.split(".")[1].length } catch (e) { }
		        try { m += s2.split(".")[1].length } catch (e) { }
		        return Number(s1.replace(".", ""))*Number(s2.replace(".", ""))/Math.pow(10, m);
		    }
			// 读取缴费科目是多次缴费还是单次缴费
			function selectbatch(id)
		    {
		    	feeSubjectId=id;
		    	ajax_100_1();//读取缴费科目是多次缴费还是单次缴费
		    }
			
			/////////      /表单提交/    /////////////////		
			function showsubmit()
			{
				if(jQuery("#academyId").val()==0)
				{
					//alert("请选择院校");
					jQuery("#showDialog").html('<b>请选择院校！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#feesubjectId").val()==0)
				{
					//alert("请选择费用科目");
					jQuery("#showDialog").html('<b>请选择费用科目！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#modeId").val()==0)
				{
					//alert("请选择学制");
					jQuery("#showDialog").html('<b>请选择学制！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#title").val()=="")
				{
					//alert("请填写标题");
					jQuery("#showDialog").html('<b>请填写标题！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(jQuery("#hiddiv1").val()=="" || jQuery("#xuefen").val()=="")
				{
					//alert("应修学分及其对应的金额不能为空");
					jQuery("#showDialog").html('<b>应修学分及其对应的金额不能为空！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(!checkway())
				{
					//alert("收费标准数据填写不完整");
					jQuery("#showDialog").html('<b>收费标准数据填写不完整！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				if(!checkedsame())
				{
					jQuery("#showDialog").html('<b>学分下限和总学分应该相等！</b>');
					$('#null_for_academy').dialog("open");
					return false;
				}
				else
				{
					dealmoney();//处理钱的小数点问题
					jQuery("#globalid").val(globalcount);
					jQuery("#academyid").val(jQuery("#academyId").val());
					jQuery("#feesubjectid").val(jQuery("#feesubjectId").val());
					jQuery("#modeid").val(jQuery("#modeId").val());
					jQuery("#titles").val(jQuery("#title").val());
					jQuery("#isControl").val(jQuery("#iscontrol").val());
					
					return true;
				}
			}	
			//缴费次数是否为空
			function checkway()
			{
				var boo=true;
				for(var i=1;i<globalcount;i++)
				{
					if(jQuery("#fee"+i).val()=="" || jQuery("#starttime"+i).val()=="" || jQuery("#endtime"+i).val()=="")
					{
						boo=false;
						break;
					}
				}
				return boo;
			}
			//判断下限的和是否超过总的学分
			function checkedsame()
			{
				var boo=true;
				var modeId=jQuery("#modeId").val();
				if(modeId!=MODE_CREDIT || jQuery("#iscontrol").val()==1)
				{
					boo=true;
				}
				else
				{
					var allxf=jQuery("#xuefen").val();
					var otherxf=0;
					for(var i=1;i<globalcount;i++)
					{
						otherxf+=dealwithmoney(jQuery("#fee"+i).val())-0;
					}
					if((otherxf-0)-(allxf-0)!=0)
					{
						boo=false;
					}
				}
				return boo;
			}
			//处理钱的小数点问题
			function dealmoney()
			{
				//应修学分及其对应的金额
				jQuery("#hiddiv1").val(dealwithmoney(jQuery("#hiddiv1").val()));
				jQuery("#xuefen").val(dealwithmoney(jQuery("#xuefen").val()));
				//缴费次数
				for(var i=1;i<globalcount;i++)
				{
					jQuery("#fee"+i).val(dealwithmoney(jQuery("#fee"+i).val()));
					//jQuery("#fee2"+i).val(dealwithmoney(jQuery("#fee2"+i).val()));
				}
			}
			
			//ajax回调函数   读取缴费科目是多次缴费还是单次缴费
			var feeSubjectId=0;//费用科目Id
			function ajax_showfeesubject(data)
			{
				if(data.feeSubject!=null)
			    {
				    if(data.feeSubject.isMultiplePayment==IS_MULTIPLE_PAYMENT_TRUE)
				    {
				    	 jQuery("#addfee").show();
				    }
				    else
				    {
				    	jQuery("#addfee").hide();
				    	if(globalcount>2)
						{
							for(var i=globalcount-1;i>1;i--)
							{
								removetr(i);
							}
						}
				    }
				    
				    jQuery("#iscontrol").val(data.feeSubject.isControl);//是否受学年学分控制参数	 	
			    }
			    else
			    {
			    	jQuery("#addfee").hide();
			    	if(globalcount>2)
					{
						for(var i=globalcount-1;i>1;i--)
						{
							removetr(i);
						}
					}
					
					jQuery("#iscontrol").val(0);//是否受学年学分控制参数	 
			   	}
			   	
			   	jQuery('#modeId').change();//学制改变事件
			}
		</script>
		<!--读取缴费科目是多次缴费还是单次缴费-->
		<a:ajax 
			pluginCode="100"
			successCallbackFunctions="ajax_showfeesubject" 
			parameters="{id:feeSubjectId}" 
			urls="/enrollment/find_fee_subject_ajax" 
		/>
	</head>
  
  <body>
		<!--头部层开始 -->
		<head:head title="新增院校收费标准">
			<html:a text="返回" icon="return" onclick="history.go(-1);"/>
		</head:head>
		<!--主体层开始 -->
		<body:body>
				 <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">基本数据</th>
							
					</tr>
				  </table>
				  <table class="add_table" id="enrollbatch" class="enrollbatch" border="0" cellpadding="2" cellspacing="2">
				  	<tbody>
				  		<tr>
							<td class="lable_100">院校:</td>					
							<td >
					  			<s:if test="%{academylist!=null}">
									<s:select list="%{academylist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="academyId" id="academyId" cssClass="txt_box_150"/>
								</s:if>
			                	<s:else>
			                		<select name="academyId" id="academyId" class="txt_box_150">
										<option value="0">--请选择--</option>
									</select>
			                	</s:else>	
							</td>
						</tr>
					  	<tr>
							<td class="lable_100">费用项目:</td>					
							<td >
					  			 <s:if test="%{feesubjectlist!=null}">
			                		<s:select list="%{feesubjectlist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="feesubjectId" id="feesubjectId" cssClass="txt_box_150"/>
			                	</s:if>
			                	<s:else>
			                		<select name="feesubjectId" id="feesubjectId" class="txt_box_150">
										<option value="0">--请选择--</option>
									</select>
			                	</s:else>
							</td>
						</tr>
						<tr>
							<td class="lable_100">学制:</td>					
							<td >
								<s:if test="%{modelist!=null}">
					  				<s:select list="%{modelist}" listKey="id" headerKey="0" headerValue="--请选择--" theme="simple" listValue="name" name="modeId" id="modeId" cssClass="txt_box_150"/>
								</s:if>
			                	<s:else>
			                		<select name="modeId" id="modeId" class="txt_box_150">
										<option value="0">--请选择--</option>
									</select>
			                	</s:else>
							</td>
						</tr>
						<tr>
							<td class="lable_100">标题:</td>					
							<td >
					  			<input type="text" name="title" id="title" class="txt_box_150"/>
							</td>
						</tr>
				  	</tbody>
				  </table>
				  
				   <table class="gv_table_2">
				  	<tr>
						 	<th style="width:20px;"><img src="<ui:img url="images/title_menu/title_left.gif" />" /></th>
						 	<th style="text-align:left; font-weight:bold;">收费方式</th>
							
				  	</tr>
				  </table>
				   <input type="hidden" name="iscontrol" id="iscontrol" value="0"/>
				 <form id="myform" method="post" onsubmit="return showsubmit();" action="add_policy_fee">
				 <input type="hidden" name="globalid" id="globalid" value="1"/>
				 <input type="hidden" name="academyid" id="academyid" value="0"/>
				 <input type="hidden" name="feesubjectid" id="feesubjectid" value="0"/>
				 <input type="hidden" name="modeid" id="modeid" value="0"/>
				 <input type="hidden" name="titles" id="titles" value=""/>
				 
				 <input type="hidden" name="isControl" id="isControl" value="0"/>
				  <table class="add_table" id="feebatch" border="0" cellpadding="2" cellspacing="2" style="display:none">
					 <tbody>
						 <tr>
						      <td class="lable_100">学分标准</td>
						      <td class="lable_100">应修学分：</td>
						      <td>
						        <input type="text" class="txt_box_40" name="xuefen" id="xuefen" onkeyup="javascript:showmoney('xuefen');"/>&nbsp;&nbsp;&nbsp;&nbsp;
								每学分金额：<input id="hiddiv1" name="hiddiv1" class="hiddiv1 txt_box_40" type="text" onkeyup="javascript:showmoney('hiddiv1');"/>元/分
							  </td>
						      <td>应缴金额：<span id="hidxuefen" name="hidxuefen" class="hidfee" style=""></span></td>
				       		  <td></td>	
				       		  <td></td>	
				       		  <td></td>	
				        </tr>
			        </tbody>
				  	<tfoot>
					    <tr>
					      <td class="lable_100">收费标准</td>
					  		<td class="lable_100">
					  			缴费次数	：	  		
					  		</td>
					  		<td>
					  			<span class="mode_name"></span>下限		  		
					  		</td>
					  		<!-- td>
					  			<span class="mode_name"></span>上限		  		
					  		</td> -->
					  		<td>开始时间</td>	
				       		<td>结束时间</td>
				       		<td><a id="addfee" href="javascript:addfeebatch();">添加</a></td>	
					  	</tr>
					  	<tr>
					  		<td></td>	
					  	  	<td class="lable_100">
				  			  	第1次缴费：		  		
				  			</td>
					  		<td>
					  			<input type="text" name="fee1" id="fee1" class="fee1" value="" width="15px" onkeyup="javascript:showmoney('fee1');" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span id="hidfee1" class="hidfee" style=""></span>
					  		</td>
					  		<!--td>
					  			<input type="text" name="fee21" id="fee21" class="fee21" value="" width="15px" onkeyup="javascript:showmoney('fee21');" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span id="hidfee21" class="hidfee" style=""></span>		  		
							</td> -->
							<td>
								<input class="Wdate" type="text" name="starttime1" id="starttime1" onclick="WdatePicker({skin:'whyGreen'})" readonly="readonly"/>
							</td>
							<td>
								<input class="Wdate" type="text" name="endtime1" id="endtime1" onclick="WdatePicker({skin:'whyGreen'})" readonly="readonly"/>
							</td>
							<td>
								
							</td>
					  	</tr>
					
				  	</tfoot>

				  </table>			
				  <table class="add_table"><tr><td align="center"><input class="btn_black_61" type="submit" value="保存" /></td></tr></table>
				   </form>
			</body:body>
			
	<div id="null_for_academy" style="display:none">
		<div align="center" id="showDialog">
		
		</div>
	</div>
  </body>
</html>
