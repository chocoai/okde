<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="../template/common/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		
		<title>院历设置</title>
		<!--  jquery库 -->
		<jc:plugin name="jquery" />
		<!-- 整体样式 -->
		<jc:plugin name="default" />
		<!-- jquery-UI -->
		<jc:plugin name="jquery_ui" />
		<!-- 日程插件-->
		<jc:plugin name="schedule" />
		<!-- 日期插件-->
		<jc:plugin name="calendar" />
		<!-- 颜色插件-->
		<jc:plugin name="colorpicker" />
		<!-- 加载等待图表 -->
		<jc:plugin name="loading" />
		<script type='text/javascript'>
				//显示重复类型以及事件
				function showRepeat(val){
					var value=parseInt(val);
					if(value<0){
						//$("#repeatSpanAll").css({'dispaly':'none'});
						document.getElementById("repeatSpanAll").style.display="none";
					}else{
						//$("#repeatSpanAll").css({'dispaly':'block'});
						document.getElementById("repeatSpanAll").style.display="";
					}
				}
				function showRepeatLeft(val){
					var value=parseInt(val);
					if(value<0){
						//$("#repeatSpanAll").css({'dispaly':'none'});
						document.getElementById("repeatSpanLeft").style.display="none";
					}else{
						//$("#repeatSpanAll").css({'dispaly':'block'});
						document.getElementById("repeatSpanLeft").style.display="";
					}
				}
				//查询年视图
				function getYearView(s,e){
						$.ajax({
							url: 'calendarsetuplist',
							dataType: 'json',
							data: {
								'start': s,
								'end': e
							},
							success: function(doc) {
								$(doc.schedules).each(function() {
									var startT=new Date(this.start.replace(/-/g,"/"));
									var endT = new Date(this.end.replace(/-/g,"/"));
									var yS=startT.getFullYear();
									var mS=(startT.getMonth()+1);
									var dS=startT.getDate();
									
									
									var yE=endT.getFullYear();
									var mE=(endT.getMonth()+1);
									var dE=endT.getDate();
									var hE=endT.getHours();
									var minE=endT.getMinutes();
									var ssE=endT.getMilliseconds();
									
									//alert(yS+"_"+mS+"_"+dS);
									//alert(yE+"_"+mE+"_"+dE);
									
									
									if(yS==yE&&mS==mE&&dS==dE){
										
										var lid="li_"+yS+"_"+mS+"_"+dS;
										if($("#"+lid)!=null){
											$("#"+lid).css({"background":"#FF99FF"});
										}
									}else if(yS==yE&&mS==mE&&dS!=dE){
									  //是否全天日程
									  if(this.allDay){//不是全天
	
										for(var i=0;i<((dE-dS)+((hE==0&&minE==0&&ssE==0) ?1:0));i++){
											
											var lid="li_"+yS+"_"+mS+"_"+(dS+i);
											
											if($("#"+lid)!=null){
												$("#"+lid).css({"background":"#FF99FF"});
											}
										}
									  }else{
									  	for(var i=0;i<=((dE-dS)-((hE==0&&minE==0&&ssE==0) ?1:0));i++){
											
											var lid="li_"+yS+"_"+mS+"_"+(dS+i);
											if($("#"+lid)!=null){
												$("#"+lid).css({"background":"#FF99FF"});
											}
										}
									  }
									}else if(yS==yE&&mS!=mE){
										var months=0;
											months=mE-mS;
											
											var monthData=new Array();
											for(var y=0,m=mS;y<=months;y++,m++){
												monthData[y]=m;
												
											}
											
											//获取时间间隔数
											var days=parseInt(Math.abs(startT - endT) / 1000 / 60 / 60 /24);
											//月数组
											var dayData =Array();
											
											//获取开始时间所在月的天数
											var sMonthDays=getDate(yS,mS);
											//获取结束时间所在月的天数
											var eMonthDays=getDate(yE,mE);	
											
											for(var y=0;y<monthData.length;y++){
												if(monthData[y]==mS){
													
													for(var x=0;x<=(sMonthDays-dS);x++){
														var lid="li_"+yS+"_"+(mS)+"_"+(dS+x);
														if($("#"+lid)!=null){
															$("#"+lid).css({"background":"#FF99FF"});
														}
													}
												}else if(monthData[y]==mE){
													for(var x=0;x<=dE;x++){
														var lid="li_"+yE+"_"+(mE)+"_"+(x);
														
														if($("#"+lid)!=null){
															$("#"+lid).css({"background":"#FF99FF"});
														}
													}
												}else{
													var mDays=getDate(yS,monthData[y]);
													
													for(var x=0;x<=mDays;x++){
														var lid="li_"+yS+"_"+(monthData[y])+"_"+(x);
														if($("#"+lid)!=null){
															$("#"+lid).css({"background":"#FF99FF"});
														}
													}
												}
											}
									}else{
										//间隔年数
										var years=yE-yS;
										//间隔月数
										var months=0;
											months=((mE+12*years)-mS);
											//离12月还有几个月
											/*if(years!=0){
												months=12-mS;
											}else{
												months=mE-mS;
											}*/
											
										//获取时间间隔数
										var days=parseInt(Math.abs(startT - endT) / 1000 / 60 / 60 /24);
										
										//alert(months);
										
										//月数组
										var monthData =Array();
										
										//获取开始时间所在月的天数
										var sMonthDays=getDate(yS,mS);
										
										//获取结束时间所在月的天数
										var eMonthDays=getDate(yE,mE);
										
										for(var i=0,m=mS;i<=months;i++,m++){
											
											if(m==mS){//开始
												for(var x=0;x<=(sMonthDays-dS);x++){
													var lid="li_"+yS+"_"+(mS)+"_"+(dS+x);
													if($("#"+lid)!=null){
														$("#"+lid).css({"background":"#FF99FF"});
													}
												}
											}else if((yS+(parseInt(m/12))==yE)&&m%12==mE){//结束
												for(var x=0;x<=dE;x++){
													var lid="li_"+yE+"_"+(mE)+"_"+(x);
													
													if($("#"+lid)!=null){
														$("#"+lid).css({"background":"#FF99FF"});
													}
												}
												
											}else{
												var mDays=0;
												
												if(m>12){
													
													if(m%12==0){
														mDays=getDate((yS+(parseInt(m/12)))-1,m%12==0?12:m%12);
														
													}else{
														mDays=getDate(yS+(parseInt(m/12)),m%12==0?12:m%12);
														
													}
													
												}else{
													mDays=getDate(yS,m);
												}
												
												for(var x=1;x<=mDays;x++){
													var lid="";
													
													if(m>12){
														
														if(m%12==0){
															lid="li_"+((yS+(parseInt(m/12)))-1)+"_"+(m%12==0?12:m%12)+"_"+(x);
														}else{
															lid="li_"+(yS+(parseInt(m/12)))+"_"+(m%12==0?12:m%12)+"_"+(x);
														}
														
													}else{
														lid="li_"+yS+"_"+m+"_"+(x);
													}
													
													if($("#"+lid)!=null){
														$("#"+lid).css({"background":"#FF99FF"});
													}
												}
											}
											
											
										}
										
										
										
									}
													
												});
												
												//关闭加载
												closeAjaxLoad();		               
											}
										});
				}
				//创建层
				function createDialog(){
					$('#dialog').dialog({
						showTitle:false,
						draggable:false,
						autoOpen:false,
						modal:true,
						title:'添加院历',
						width: 520,
						height: 440
						
					});
				}
				//创建颜色选项卡插件
				function createColorPlugin(){
					$('#colorSelector').ColorPicker({
							color: '#0000ff',
							onShow: function (colpkr) {
								$(colpkr).fadeIn(500);
								return false;
							},
							onHide: function (colpkr) {
								$(colpkr).fadeOut(500);
								return false;
							},
							onChange: function (hsb, hex, rgb) {
								$('#colorSelector div').css('backgroundColor', '#' + hex);
							}
					});
				}
				//增加日程
				function addCalendar(title,start,end,allDay){
					
						$('#dialog').dialog({ 
								title:"添加院历",
								buttons: {
									"保存": function() { 
										var remindTime;
									
										//院校ID
										var schoolId=$("#schoolId").val();
										//标题
										var title=$("#title").val();
										//位置
										var location=$("#location").val();
										//是否全天
										var allDay=$("#allDayCheckbox").get(0).checked;
										
										//开始时间
										var starttime=$("#starttime").val();
										//结束时间
										var endtime=$("#endtime").val();
										//开始日期
										var startdate=$("#startdate").val();
										//结束日期
										var enddate=$("#enddate").val();
									
										//重复类型
										var repeatType=$("#repeatType").val();
										//重复结束类型
										var repeatEndType=$("#repeatEndType").val();
										//重复结束时间
										var enddate1=$("#enddate1").val();
										//颜色
										var color=$("#color").css('backgroundColor');
										//提醒类型
										var remindType=$("#remindType").val();
										//提醒时间
										var remindDate=$("#remindDate").val();
										//提醒时间类型
										var remindDateType=$("#remindDateType").val();
										//提醒详细时间
										var enddate2=$("#enddate2").val();
										//邀请人
										var invite=$("#invite").val();
										//备注
										var remark=$("#remark").val();
										
										
										//院校
										
										
										if(schoolId==null||schoolId==""){
											alert("请添加院校");
											return;
										}
										//title
										if(title==null||title==""){
											alert("标题不能为空");
											return;
										}else if(title.length>128){
											alert("标题不能超过128个字符！");
											return;
										}
										//时间
										if(starttime==null||starttime==""){
											alert("开始时间不能为空");
											return;
										}
										if(endtime==null||endtime==""){
											alert("结束时间不能为空");
											return;
										}
										
									
										
										
										
										//参数
										var parameter={
											"schedule.title":title,
											"schedule.content":remark,
											"schedule.startDate":starttime,
											"schedule.endDate":endtime,
											"schedule.schoolId":schoolId,
											"schedule.repeatType":repeatType,
											"schedule.repeatEndType":repeatEndType,
											"schedule.repeatEndDate":enddate1,
											"schedule.location":location,
											"schedule.isAllDay":(allDay==true?1:0),
											"schedule.invitee":invite,
											"schedule.color":color,
											"remind.type":remindType,
											"remind.remindTimeType":remindDateType,
											"remind.remindTime":remindTime,
											"remind.schoolId":schoolId
										}	
										//增加院历
										$.post('<s:url value="addcalendar"/>',parameter,
												function(data){
														//获取当前视图对象
														var view = $('#calendar').fullCalendar('getView');
														//如果当前视图未年视图刷新院历
														if(view.name=='year'){
																getYearView();
																
														}
														//增加院历到视图当中
														$('#calendar').fullCalendar('renderEvent',{
																id:data.schedule.id,  //id
																title: data.schedule.title,//标题
																start: data.schedule.startDate,//开始时间
																end: data.schedule.endDate,//结束时间
																allDay: data.schedule.isAllDay==0?false:true,//是否为全天院历
																color:data.schedule.color//院历背景颜色
															},
															false // make the event "stick"
														);
														
														//关闭dialog
														$('#dialog').dialog("close"); 
														
												}
											,"json");
									
										//
									}, 
									"取消": function() { 
										$(this).dialog("close"); 
									} 
								}
						});
						$('#dialog').dialog('open');
						//清空表单数据
						$("#allDayCheckbox").get(0).checked=true;
						//默认值
						$("#location").val("");
						$("#repeatType").val(-1);
						$("#repeatEndType").val(-1);
						document.getElementById("repeatSpanAll").style.display="none";
						document.getElementById("repeatSpanLeft").style.display="none";
						$("#enddate1").val("");
						$("#color").css({"background-color":"rgb(217, 224, 219)"});
						$("#invite").val("");
						$("#remark").val("");
						$("#title").val(title);
						$("#starttime").val((start).pattern("yyyy-MM-dd HH:mm:00"));
						$("#endtime").val((end).pattern("yyyy-MM-dd HH:mm:00"));
				}
				//获取院历按钮模板
				function getTem(text){
					return '<span class="fc-button-inner"><span class="fc-button-content">'+text+'</span><span class="fc-button-effect"><span></span></span></span>';
				}
				//去往具体时间
				function goToDate(year,month,day){
					$('#calendar').fullCalendar( 'gotoDate', year,month-1,day);
					$('#calendar').fullCalendar( 'changeView', "agendaDay" );
				}
				//去往月
				function gotoD(year,month){
					$('#calendar').fullCalendar( 'gotoDate', year,(month-1));
					$('#calendar').fullCalendar( 'changeView', "month" );
				}
				//创建更新院历Div
				function createUpdateCalendarDiv(id){
					$('#dialog').dialog({ 
							title:"院历详情",
							buttons: {
											"保存": function() { 
														var remindTime;
									
														//院校ID
														var schoolId=$("#schoolId").val();
														//标题
														var title=$("#title").val();
														//位置
														var location=$("#location").val();
														//是否全天
														var allDay=$("#allDayCheckbox").get(0).checked;
														
														//开始时间
														var starttime=$("#starttime").val();
														//结束时间
														var endtime=$("#endtime").val();
														//开始日期
														var startdate=$("#startdate").val();
														//结束日期
														var enddate=$("#enddate").val();
													
														//重复类型
														var repeatType=$("#repeatType").val();
														//重复结束类型
														var repeatEndType=$("#repeatEndType").val();
														//重复结束时间
														var enddate1=$("#enddate1").val();
														//颜色
														var color=$("#color").css('backgroundColor');
														//提醒类型
														var remindType=$("#remindType").val();
														//提醒时间
														var remindDate=$("#remindDate").val();
														//提醒时间类型
														var remindDateType=$("#remindDateType").val();
														//提醒详细时间
														var enddate2=$("#enddate2").val();
														//邀请人
														var invite=$("#invite").val();
														//备注
														var remark=$("#remark").val();
														
														
														//院校
														
														
														if(schoolId==null||schoolId==""){
															alert("请添加院校");
															return;
														}
														//title
														if(title==null||title==""){
															alert("标题不能为空");
															return;
														}else if(title.length>128){
															alert("标题不能超过128个字符！");
															return;
														}
														//时间
														if(starttime==null||starttime==""){
															alert("开始时间不能为空");
															return;
														}
														if(endtime==null||endtime==""){
															alert("结束时间不能为空");
															return;
														}
														
														
														//参数
														var parameter={
															"schedule.id":id,
															"schedule.title":title,
															"schedule.content":remark,
															"schedule.startDate":starttime,
															"schedule.endDate":endtime,
															"schedule.schoolId":schoolId,
															"schedule.repeatType":repeatType,
															"schedule.repeatEndType":repeatEndType,
															"schedule.repeatEndDate":enddate1,
															"schedule.location":location,
															"schedule.isAllDay":(allDay==true?1:0),
															"schedule.invitee":invite,
															"schedule.color":color,
															"remind.type":remindType,
															"remind.remindTimeType":remindDateType,
															"remind.remindTime":remindTime,
															"remind.schoolId":schoolId
														}	
														//增加院历
														$.post('<s:url value="updatecalendar"/>',parameter,
																function(data){
																		//获取当前视图对象
																		var view = $('#calendar').fullCalendar('getView');
																		//如果当前视图未年视图刷新院历
																		if(view.name=='year'){
																			//	getYearView();
																		}
																		$('#calendar').fullCalendar( 'removeEvents',data.schedule.id);
																		
																		//增加院历到视图当中
																		$('#calendar').fullCalendar('renderEvent',{
																				id:data.schedule.id,  //id
																				title: data.schedule.title,//标题
																				start: data.schedule.startDate,//开始时间
																				end: data.schedule.endDate,//结束时间
																				allDay: data.schedule.isAllDay==0?false:true,//是否为全天院历
																				color:data.schedule.color//院历北京颜色
																			},
																			false // make the event "stick"
																		);
																		
																		//关闭dialog
																		$('#dialog').dialog("close"); 
																		
																}
															,"json");
													
														//
													}, 
													"删除": function() { 
														if(confirm("确定要删除该院历吗？此操作不可逆！")){
																//删除院历
																$.post('<s:url value="deletecalendar"/>',{"schedule.id":id},
																	function(data){
																		$('#calendar').fullCalendar( 'removeEvents',id);
																	}
																,"json");
														}
														//关闭dialog
														$('#dialog').dialog("close");
														
													} , 
													"取消": function() { 
														$(this).dialog("close"); 
													}
												}
										});
				}
				//单击院历
				function clickSchoolCalendar(id){
					$.post('<s:url value="findcalendar"/>',{"s.id":id},
								function(data){
									if(data.schedule!=null){
											//加载更新院历Div
											createUpdateCalendarDiv(id);
											//院校ID
											$("#schoolId").val(data.schedule.schoolId);
														//标题
											$("#title").val(data.schedule.title);
														//位置
											$("#location").val(data.schedule.location);
														//是否全天
											$("#allDayCheckbox").get(0).checked=data.schedule.isAllDay==0?false:true;
														
														//开始时间
											$("#starttime").val(data.schedule.startDate);
														//结束时间
										    $("#endtime").val(data.schedule.endDate);
											//重复类型
											$("#repeatType").val(data.schedule.repeatType);
														//重复结束类型
											$("#repeatEndType").val(data.schedule.repeatType);
														//重复结束时间
											$("#enddate1").val(data.schedule.repeatEndDate);
														//颜色
											$("#color").css({"background-color":data.schedule.color});
														//邀请人
											$("#invite").val(data.schedule.invitee);
														//备注
											$("#remark").val(data.schedule.content);
											
											if(data.schedule.repeatType>0){
												$("#repeatSpanAll").css({"display":""});
												$("#repeatEndType").val(data.schedule.repeatEndType);
												if(data.schedule.repeatEndType>0){
													$("#repeatSpanLeft").css({"display":""});
													$("#repeatEndType").val(data.schedule.repeatEndDate);
													
												}
												
											}
											
											
										$('#dialog').dialog('open');
									}else{//院历被别人删除
										alert("院历已经被删除！");
									}
								}
							,"json");
					        
				}
				//获取年详细视图
				function getYearDetailsView(start,end,year){
						//修复年视图不兼容问题
								$(".fc-border-separate tbody tr").css({'display':''});
								$.ajax({
								            url: 'calendarsetuplist',
								            dataType: 'json',
								            data: {
								                'start': start,
								                'end': end
								            },
								            error:function(datas){
								            	alert("服务器异常");
								            	 //关闭加载
												closeAjaxLoad();
								            },success: function(doc) {
								            	var yStr=year;
								            	//清空
								            	for(var i=1;i<=12;i++){
								            		$(".dt_"+yStr+"_"+i).html("");
								            	}
								                $(doc.schedules).each(function(){
								                	var st=this.start.replace("-","#").replace("-","##");
								                	var et=this.end.replace("-","#").replace("-","##");
								                	var sy=st.substring(0,st.indexOf("#"));
								                	var sm=parseInt(st.substring(st.indexOf("#")+1,st.indexOf("##")),10);
								                	var ey=et.substring(0,et.indexOf("#"));
								                	var em=parseInt(et.substring(et.indexOf("#")+1,et.indexOf("##")),10);
								                	//if(sy==yStr&&ey==yStr&&sy==ey&&sm==em){
								                	if(sy==yStr){
								                		var repeatStr="";
								                		//重复类型
															switch(this.repeatType){
																case CAL_REPEAT_NONE:// 无
																	repeatStr+="不重复";
																	break;
																case CAL_REPEAT_DAY:// 每天
																	repeatStr+="每天重复";
																	if(this.repeatEndType==CAL_REPEAT_END_NEVER){
																		repeatStr+="-永不结束";
																	}else{
																		repeatStr+="-结束于"+this.repeatEndDate;
																	}
																	break;
																case CAL_REPEAT_WEEK:// 每周
																	repeatStr+="每周重复";
																	if(this.repeatEndType==CAL_REPEAT_END_NEVER){
																		repeatStr+="-永不结束";
																	}else{
																		repeatStr+="-结束于"+this.repeatEndDate;
																	}
																	break;
																	break;
																case CAL_REPEAT_MONTH:// 每月
																	repeatStr+="每月重复";
																	if(this.repeatEndType==CAL_REPEAT_END_NEVER){
																		repeatStr+="-永不结束";
																	}else{
																		repeatStr+="-结束于"+this.repeatEndDate;
																	}
																	break;
																	break;
																case CAL_REPEAT_YEAR:// 每年
																	repeatStr+="每年重复";
																	if(this.repeatEndType==CAL_REPEAT_END_NEVER){
																		repeatStr+="-永不结束";
																	}else{
																		repeatStr+="-结束于"+this.repeatEndDate;
																	}
																	break;
																	break;
															}
									                		var year=new Date(this.start.replace(/-/g,'/')).pattern('yyyy');
									                		var month=new Date(this.start.replace(/-/g,'/')).pattern('M');
									                		//var gotoStr="gotoD("+year+","+month+")";
									                		var gotoStr="clickSchoolCalendar("+this.id+")";
									                		if($(".dt_"+yStr+"_"+sm+"_"+this.id).html()==null){
										                		$(".dt_"+yStr+"_"+sm).append((
										                		"<dl class='"+"dt_"+yStr+"_"+sm+"_"+this.id+"' onclick='"+gotoStr+"' style='cursor: pointer;background-color:"+this.color+";border-color:"+this.color+"'>["+$("#schoolId").find("option[value="+this.schoolId+"]").text()+"]["
										                		+new Date(this.start.replace(/-/g,"/")).pattern("MM月dd日")+"-"+new Date(this.end.replace(/-/g,"/")).pattern("MM月dd日")+"]"
										                		+"["+repeatStr+"]"
										                		+(this.location==""?"":("[地点："+this.location+"]"))
										                		+(this.invitee==""?"":("[参加人:"+this.invitee+"]"))
										                		+this.title+"</dl>").replaceAll("null","无"));
									                		}
									                		
									                		if($(".dt_"+yStr+"_"+em+"_"+this.id).html()==null){
									                			$(".dt_"+yStr+"_"+em).append((
												                		"<dl class='"+"dt_"+yStr+"_"+em+"_"+this.id+"' onclick='"+gotoStr+"' style='cursor: pointer;background-color:"+this.color+";border-color:"+this.color+"'>["+$("#schoolId").find("option[value="+this.schoolId+"]").text()+"]["
												                		+new Date(this.start.replace(/-/g,"/")).pattern("MM月dd日")+"-"+new Date(this.end.replace(/-/g,"/")).pattern("MM月dd日")+"]"
												                		+"["+repeatStr+"]"
												                		+(this.location==""?"":("[地点："+this.location+"]"))
												                		+(this.invitee==""?"":("[参加人:"+this.invitee+"]"))
												                		+this.title+"</dl>").replaceAll("null","无"));
									                		}
								                	}
								                	
								                		/*id:this.id,  //id
														title: this.title,//标题
														start: this.start,//开始时间
														end: this.end,//结束时间
														allDay: this.allDay==0?false:true,//是否为全天院历
														color:this.color//院历北京颜色*/
								               
								                });
								                 //关闭加载
												closeAjaxLoad();
								            }
						         });
				
				}
				//除了年视图的其他视图
				function anotherView(view){
						$.ajax({
								            url: 'calendarsetuplist',
								            dataType: 'json',
								            data: {
								                'start': (view.start).pattern("yyyy-MM-dd HH:mm:ss"),
								                'end': (view.end).pattern("yyyy-MM-dd HH:mm:ss")
								            },
								            success: function(doc) {
								            	$(doc.schedules).each(function(){
								                		$('#calendar').fullCalendar( 'removeEvents',this.id);
								                });
								                $(doc.schedules).each(function(){
								                		//增加院历到视图当中
														$('#calendar').fullCalendar('renderEvent',{
																id:this.id,  //id
																title: "["+$("#schoolId").find("option[value="+this.schoolId+"]").text()+"]"+this.title,//标题
																start: this.start,//开始时间
																end: this.end,//结束时间
																allDay: this.allDay==0?false:true,//是否为全天院历
																color:this.color//院历北京颜色
															},
															true // make the event "stick"
														);
								                
								                });
								                
								                //关闭加载
												closeAjaxLoad();
								                
								            }
						         });
				}
				
				//加载
				$(document).ready(function() {
					//加载dialog
					createDialog();
					//加载颜色插件
					createColorPlugin();
					$('#calendar').fullCalendar({
						//theme:true,//样式
						header: {
							left: 'prevYear,prev,today,next,nextYear',
							center: 'title',
							right: 'year,yearDetails,month,basicWeek,basicDay,agendaWeek,agendaDay'
						},
						defaultView:"yearDetails",
						weekends:true,//布尔类型, 默认为true, 标识是否显示周六和周日的列. 
						editable: false,
						selectable: true,
						selectHelper: true,
						allDayText:'今天的任务',
						axisFormat:'tth时',
						timeFormat:'tth时(mm分)至{tth时(mm分)}',
						buttonText:{
							 prev:     '昨天',
							 next:     '明天',
							 prevYear: '去年',	
							 nextYear: '明年',
							 today:    '今天',
							 month:    '月',
							 week:     '周',
							 basicWeek:'周',
							 agendaWeek:'周(详)',
							 basicDay:'日',
							 agendaDay:'日(详)',
							 year:'年',
							 yearDetails:'年(详)'
						},
					    eventClick: function(calEvent, jsEvent, view) {
					    	//单击院历
					    	clickSchoolCalendar(calEvent.id);
					    	return false ;
					    },
						viewDisplay:function(view){
							
							//显示加载图表
							showAjaxLoad();
							//切换视图
							if(view.name=="year"||view.name=="yearDetails"){//年
								//本年
								$(".fc-button-today").html(getTem("今年"));
								$(".fc-button-prev").html(getTem("上月"));
								$(".fc-button-next").html(getTem("下月"));
							}else if(view.name=="month"){//月
								$(".fc-button-today").html(getTem("本月"));
								$(".fc-button-prev").html(getTem("上月"));
								$(".fc-button-next").html(getTem("下月"));
							}else if(view.name=="basicWeek"||view.name=="agendaWeek"){//周
								$(".fc-button-today").html(getTem("本周"));
								$(".fc-button-prev").html(getTem("上周"));
								$(".fc-button-next").html(getTem("下周"));
							}else if(view.name=="basicDay"||view.name=="agendaDay"){//天
								$(".fc-button-today").html(getTem("今天"));
								$(".fc-button-prev").html(getTem("昨天"));
								$(".fc-button-next").html(getTem("明天"));
							}
							//获取院历数据
							if(view.name=="year"){
								//设置最小高度
								//$(".main").css({"min-height":"180px"});
								$("#yearViewDiv table tbody tr").css({"display":""});
								var yStr=view.title.substring(0,view.title.indexOf('年'));
								var startDateTime=yStr+"-01-01 00:00:00";
								var endDateTime=yStr+"-12-31 23:59:59";
								getYearView(startDateTime,endDateTime);
								
								//修复年视图不兼容问题
								$("td").removeClass("fc-state-highlight");
								
							}else if(view.name=="yearDetails"){
							
								
								
								var yStr=view.title.substring(0,view.title.indexOf('年'));
								var startDateTime=yStr+"-01-01 00:00:00";
								var endDateTime=yStr+"-12-31 23:59:59";
								getYearDetailsView(startDateTime,endDateTime,yStr);
							}else{				
								//其他视图				
								anotherView(view);
							}
							
						},
						select: function(start, end, allDay) {
								var title="新建院历事件";
								//增加日程
								addCalendar(title,start,end,allDay);
								$('#calendar').fullCalendar('unselect');
						},
						eventDrop: function(event, delta){
							//修改院历时间
							event.className=["deepRed"];
							var start=(event.start==null)?"":((event.start).pattern(DATE_TIME_FORMAT));
							var end=(event.end==null)?"":((event.end).pattern(DATE_TIME_FORMAT));
							$.post('<s:url value="updatecalendardate"/>',{"s.id":event.id,"s.start":start,"s.end":end},
								function(data){
									
								}
							,"json");
				            //handlCalendar.quickdropUpdate(event);
				        },
				        eventResize: function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view){
				        	var start=(event.start==null)?"":((event.start).pattern(DATE_TIME_FORMAT));
							var end=(event.end==null)?"":((event.end).pattern(DATE_TIME_FORMAT));
							$.post('<s:url value="updatecalendardate"/>',{"s.id":event.id,"s.start":start,"s.end":end},
								function(data){
									
								}
							,"json");
				            //handlCalendar.quickdropUpdate(event);
				        },
						events: function(start, end, callback) {
							
					    }
					});
					
				});
				
		function addc(){
			addCalendar('新建院历事件',new Date(),new Date(),false);
		}
		</script>
	</head>
	<body style="overflow-x:hidden; ">
		<!--头部层开始 -->
		<head:head title="院历设置">
		 	<html:a text="添加院历" icon="add" onclick="addc();" />
		</head:head>
		<!--主体层开始 -->
		<body:body>
						<div id='calendar'></div>
						
						<div id="dialog" style="display:none">
							<table  width="100%" border="0" cellpadding="2" cellspacing="0" >
								<tbody>
									<tr>
										<td width="70px" align="right"><span style="color: red;">*</span>院校：</td>
										<td align="left">
										
										<s:if test="#request.academys!=null">
											<s:select list="#request.academys" name="schoolId" id="schoolId" cssClass="txt_box_200" listKey="id" listValue="name"  theme="simple"/>
										</s:if>
										</td>
									</tr>
									<tr>
										<td width="70px" align="right"><span style="color: red;">*</span>标题：</td>
										<td align="left">
											<input type="text" id="title" class="txt_box_300" id="title"/>
										</td>
									</tr>
									<!-- 
									<tr>
										<td width="70px" align="right">位置：</td>
										<td align="left">
											<input type="text" id="location" class="txt_box_300"/>
										</td>
									</tr>
									-->
									<tr>
										<td width="70px" align="right">全天：</td>
										<td align="left">
											<input type="checkbox" id="allDayCheckbox"/>
										</td>
									</tr>
									<tr>
										<td width="70px" align="right">从</td>
										<td align="left">
											<input id="starttime" class="Wdate" type="text" size="22" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endtime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="" name="starttime" />
										</td>
									</tr>
									<tr>
										<td width="70px" align="right">到</td>
										<td align="left">
											<input id="endtime" class="Wdate" type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime\')}',skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})" size="22" value="" name="endtime" />
										</td>
									</tr>
									
									<tr>
										<td width="70px" align="right">重复：</td>
										<td align="left">
											<select id="repeatType" onchange="showRepeat(this.value);">
												<option selected="selected" value="-1">无</option>
												<option value="1">每天</option>
												<option value="2">每周</option>
												<option value="3">每月</option>
												<option value="4">每年</option>
												
											</select>
											<span  id="repeatSpanAll" style="display: none;">
												<select id="repeatEndType" onchange="showRepeatLeft(this.value);">
													<option selected="selected" value="-1">永不结束</option>
													<option value="1">结束于日期</option>
												</select>
												<span id="repeatSpanLeft" style="display: none;">
													<input id="enddate1" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" size="12" value="" name="enddate1"/>
												</span>
											</span>
										</td>
									</tr>
									
									<tr>
										<td width="70px" align="right">颜色：</td>
										<td align="left">
											  <div	 id="colorSelector" style="z-index: 1000000;">
											  		<div id="color" style="background-color: #0000ff"></div>
											  </div>
										</td>
									</tr>
								<!-- 
									<tr>
										<td width="70px" align="right">提醒：</td>
										<td align="left">
											<select id="remindType" onchange="showRemind(this.value);">
												<option selected="selected" value="-1">无</option>
												<option value="1">信息</option>
												<option value="2">内部邮件</option>
											</select>
											<span id="remindSpanAll" style="display: none;">
												<input type="text" class="txt_box_20" value="15" id="remindDate"/>
												<select id="remindDateType" onchange="showRemindLeft(this.value);">
													<option selected="selected" value="1">分钟前</option>
													<option value="2">小时前</option>
													<option value="3">天前</option>
													<option value="4">分钟后</option>
													<option value="5">小时后</option>
													<option value="6">天后</option>
													<option value="7">于日期</option>
												</select>
												<span id="remindSpanLeft" style="display: none;">
													<input id="enddate2" class="Wdate" type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm'})" size="12" value="" name="enddate2"/>
												</span>
											</span>
										</td>
									</tr>

									<tr>
										<td width="70px" align="right">邀请人：</td>
										<td align="left">
											<input type="text" class="txt_box_200" id="invite"/> 
										</td>
									</tr>
									-->
									<tr>
										<td width="70px" align="right">备注：</td>
										<td align="left">
											<textarea class="txt_box_400" rows="4" cols="50" id="remark"></textarea>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
				</body:body>
	</body>

</html>