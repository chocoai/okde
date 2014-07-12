<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp" %>

	<!--  jquery库 -->
	document.write('<jc:plugin name="jquery"/><jc:plugin name="loading"/>');
	<!--  AJAX  开始 -->
	function init(){
		jQuery("#${submitButtonId}").click(function(){submitStudentInfo();});
		/**
		 * 把form元素转换为json
		 * @memberOf {TypeName} 
		 * @return {TypeName} 
		 */
		jQuery.fn.serializeObject = function() {
		    var o = {};
		    var a = this.serializeArray();
		    $.each(a, function() {
		        if (o[this.name]) {
		            if (!o[this.name].push) {
		                o[this.name] = [ o[this.name] ];
		            }
		            o[this.name].push(this.value || '');
		        } else {
		            o[this.name] = this.value || '';
		           
		        }
		    });
		   
		    return o;
		}
		
	}
	<!--  提交学生信息   开始 -->
	function submitStudentInfo(){
	
		//检测域名
		var hrefValue = window.location.href; //获取当前页面的地址
 		var alertUrls = ['baoming.chinaedu.net','zhuanye.chinaedu.net','xueyuan.chinaedu.net','gd.chinaedu.net','gd.zhuanye.chinaedu.net','baoming.xueyuan.chinaedu.net','www.xuexi.com.cn'];  
 		var isEx=false;
 		for(key in alertUrls){ 
  			if(String(hrefValue).indexOf(alertUrls[key]) >= 0){
  				//指定的字符串值alertUrls[key]在字符串href中出现则>=0，否则为-1
   				isEx=true;
   				break; //直接跳出循环
   			}
  		}
  		if(!isEx){
  			alert('没有授权！');
  			return false;
		}
		showAjaxLoad();
		var others = jQuery("#${formId} [name='other']");
		jQuery.each(others,function(){
				if(jQuery(this).attr('title')=='层次'||jQuery(this).attr('title')=='请选择城市名'){
					var op="<option selected='selected' value='"+jQuery(this).attr("title")+":"+jQuery(this).find("option:selected").text()+"'>"+jQuery(this).find("option:selected").text()+"</option>";
					jQuery(this).empty();
					jQuery(this).append(op);
				}else{
					jQuery(this).val(jQuery(this).attr("title")+":"+jQuery(this).val());
				}
		});
		jQuery.ajax({
		  	url: "<%=Constants.WEB_PATH %>/crm/add_student_np?callback=?",
		    data: jQuery("#${formId}").serializeObject(),
		  	traditional:true,
		  	dataType: "json",
		    success: function(data) { 
		   	 	closeAjaxLoad();
		    	//没有填写任何一项必填信息 错误代码:0001
				//学生姓名不能为空   错误代号:1001
				if(data.code=="1001"){
					jQuery("#${formId} [name='student.name']").attr("class","error");
					jQuery("#${formId} [name='student.name.strong']").attr("class","error");
					alert("请填写您的姓名，并选择称谓");
					return false;
		    	}else{
		    		jQuery("#${formId} [name='student.name']").attr("class","");
					jQuery("#${formId} [name='student.name.strong']").attr("class","");
					jQuery("#${formId} [name='student.name.em']").html('<img src="images/correct.gif" width="15" height="13" alt="" />');
		    	}
				
				//手机和座机必须填一项  错误代号:1003
				if(data.code=="1003"){
					jQuery("#${formId} [name='student.mobile']").attr("class","error");
					jQuery("#${formId} [name='student.mobile.strong']").attr("class","error");
					jQuery("#${formId} [name='student.phone']").attr("class","error");
					jQuery("#${formId} [name='student.phone.strong']").attr("class","error");
					alert("请填写您的联系电话，参考格式：13912345678 \n若无手机号码，请填写您的或座机号码参考格式：010-66666666-1234");
					return false;
		    	}else if(data.code=="1005"){//手机和座机验证失败 错误代码:1005
					jQuery("#${formId} [name='student.mobile']").attr("class","error");
					jQuery("#${formId} [name='student.mobile.strong']").attr("class","error");
					jQuery("#${formId} [name='student.phone']").attr("class","error");
					jQuery("#${formId} [name='student.phone.strong']").attr("class","error");
					alert("手机或座机不合法，请参考格式：13912345678 \n若无手机号码，请填写您的或座机号码参考格式：010-66666666-1234");
					return false;
		    	}else{
		    		jQuery("#${formId} [name='student.mobile']").attr("class","");
					jQuery("#${formId} [name='student.mobile.strong']").attr("class","");
					jQuery("#${formId} [name='student.phone']").attr("class","");
					jQuery("#${formId} [name='student.phone.strong']").attr("class","");
					
					
					jQuery("#${formId} [name='student.mobile.em']").html('<img src="images/correct.gif" width="15" height="13" alt="" />');
					jQuery("#${formId} [name='student.phone.em']").html('<img src="images/correct.gif" width="15" height="13" alt="" />');
		    	}
		    	
		    	//证件号不能为空     错误代号:1002
				if(data.code=="1002"){
					jQuery("#${formId} [name='student.certNo']").attr("class","error");
					jQuery("#${formId} [name='student.certNo.strong']").attr("class","error");
					alert("证件号码将与您的整个求学过程紧密相连，请慎重填写。\n如您持除证件号以外其他证件，请咨询客服400-680-4299");
					return false;
		    	}else if(data.code=="1006"){//验证身份证合法，错误代码：1006
					jQuery("#${formId} [name='student.certNo']").attr("class","error");
					jQuery("#${formId} [name='student.certNo.strong']").attr("class","error");
					alert("您的证件号码不合法，请重新填写。\n如您持除证件号以外其他证件，请咨询客服400-680-4299");
					return false;
		    	}else{
		    		jQuery("#${formId} [name='student.certNo']").attr("class","");
					jQuery("#${formId} [name='student.certNo.strong']").attr("class","");
					jQuery("#${formId} [name='student.certNo.em']").html('<img src="images/correct.gif" width="15" height="13" alt="" />');
					
		    	}
		    	
				//学生存在           错误代号:1004
				if(data.code=="1004"){
					jQuery("#${formId} [name='student.name']").attr("class","error");
					jQuery("#${formId} [name='student.name.strong']").attr("class","error");
					jQuery("#${formId} [name='student.certNo']").attr("class","error");
					jQuery("#${formId} [name='student.certNo.strong']").attr("class","error");
					jQuery("#${formId} [name='student.mobile']").attr("class","error");
					jQuery("#${formId} [name='student.mobile.strong']").attr("class","error");
					jQuery("#${formId} [name='student.phone']").attr("class","error");
					jQuery("#${formId} [name='student.phone.strong']").attr("class","error");
					
					jQuery("#${formId} [name='student.name.em']").html('');
					jQuery("#${formId} [name='student.certNo.em']").html('');
					jQuery("#${formId} [name='student.mobile.em']").html('');
					jQuery("#${formId} [name='student.phone.em']").html('');
					
					alert("学生已存在！");
					return false;
		    	}else{
		    		jQuery("#${formId} [name='student.name']").attr("class","");
					jQuery("#${formId} [name='student.name.strong']").attr("class","");
					jQuery("#${formId} [name='student.certNo']").attr("class","");
					jQuery("#${formId} [name='student.certNo.strong']").attr("class","");
					jQuery("#${formId} [name='student.mobile']").attr("class","");
					jQuery("#${formId} [name='student.mobile.strong']").attr("class","");
					jQuery("#${formId} [name='student.phone']").attr("class","");
					jQuery("#${formId} [name='student.phone.strong']").attr("class","");
					
					jQuery("#${formId} [name='student.name.em']").html('<img src="images/correct.gif" width="15" height="13" alt="" />');
					jQuery("#${formId} [name='student.certNo.em']").html('<img src="images/correct.gif" width="15" height="13" alt="" />');
					jQuery("#${formId} [name='student.mobile.em']").html('<img src="images/correct.gif" width="15" height="13" alt="" />');
					jQuery("#${formId} [name='student.phone.em']").html('<img src="images/correct.gif" width="15" height="13" alt="" />');
		    	}
				//成功              成功代号:2001
				if(data.code=="2001"){
					jQuery("#${formId} [name='student.name']").attr("class","");
					jQuery("#${formId} [name='student.name.strong']").attr("class","");
					jQuery("#${formId} [name='student.certNo']").attr("class","");
					jQuery("#${formId} [name='student.certNo.strong']").attr("class","");
					jQuery("#${formId} [name='student.mobile']").attr("class","");
					jQuery("#${formId} [name='student.mobile.strong']").attr("class","");
					jQuery("#${formId} [name='student.phone']").attr("class","");
					jQuery("#${formId} [name='student.phone.strong']").attr("class","");
					jQuery("#${formId} [name='student.name.em']").html('<img src="images/correct.gif" width="15" height="13" alt="" />');
					jQuery("#${formId} [name='student.certNo.em']").html('<img src="images/correct.gif" width="15" height="13" alt="" />');
					jQuery("#${formId} [name='student.mobile.em']").html('<img src="images/correct.gif" width="15" height="13" alt="" />');
					jQuery("#${formId} [name='student.phone.em']").html('<img src="images/correct.gif" width="15" height="13" alt="" />');
					//alert("预报名成功！稍后会有客服人员与您联系，谢谢！");
					parent.parent.location.href='http://baoming.chinaedu.net/register_register/register_success'
					return false;
		    	}
			}
		});
		jQuery.each(others,function(){
				jQuery(this).val(jQuery(this).val().replace(jQuery(this).attr("title")+":",""));
		});
	}
	<!--  提交学生信息   结束-->
	document.write("<script type='text/javascript'>jQuery(document).ready(function(){init();});</script>");