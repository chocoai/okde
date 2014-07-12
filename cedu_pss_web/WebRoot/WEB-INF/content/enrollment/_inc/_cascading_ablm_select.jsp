<%@ page pageEncoding="UTF-8"%>
<script type="text/javascript">
function clearBatch()
{
	$('#batchId').empty().append('<option value="0" selected="selected">---请选择---</option>');
	clearLevel();
}

function batchCallback(data){
	$('#batchId').empty();
	$(data.batches).each(function(index){
		var batchOpt = $('<option value="'+this.id+'">'+(this.enrollmentName ? this.enrollmentName : '')+'</option>');
		batchOpt.appendTo('#batchId');
	});
	$('#batchId').prepend('<option value="0" selected="selected">---请选择---</option>');
}
BATCH_PARAM = null;
function listBatch(academyId)
{
	clearBatch();
	
	if(academyId <= 0) return;
	
	$('#batchId').html('<option value="0" selected="selected">---请选择---</option>');
	BATCH_PARAM = { academyId: academyId };
	ajax_batch_1();
}

function clearLevel()
{
	$('#levelId').html('<option value="0" selected="selected">===请选择==</option>');
	$('#majorId').html('<option value="0" selected="selected">==请选择==</option>');
}

function levelCallback(data){
	$('#levelId').empty();
	
	if(!data || !data.list || data.list.length<=0){
		$('#levelId').append('<option value="0" selected="selected">====请选择===</option>');
		return;
	}
	
	$('#levelId').append('<option value="0" selected="selected">====请选择====</option>');
	
	$(data.list).each(function(index){
		$('<option  value="'+this.level.id+'" al="'+this.id+'">'+(this.level ? this.level.name : '')+'</option>').appendTo('#levelId');
	});
}

LEVEL_PARAM = null;
function listLevel(batchId)
{
	clearLevel();
	
	if(batchId==0) return;
	LEVEL_PARAM = {batchId: batchId};
	
	$('#levelId').html('<option value="0" selected="selected">====请选择====</option>');
	ajax_level_1();
}

function majorCallback(data){
	$('#majorId').empty();
	
	if(!data || !data.list || data.list.length<=0)
	{
		$('#majorId').append('<option value="0" selected="selected">====请选择===</option>');
		return;
	}
	
	$('#majorId').append('<option value="0" selected="selected">====请选择====</option>');
	
	$(data.list).each(function(index){
		$('#majorId').append('<option value="'+this.id+'">'+this.name+'</option>');
	});
}

MAJOR_PARAM = null;
function listMajor(levelId)
{
	if(!levelId)
	{
		$('#majorId').html('<option value="0" selected="selected">===请选择=====</option>');
		return;
	}
	MAJOR_PARAM = {academyLevelId: levelId};
	$('#majorId').html('<option value="0" selected="selected">===请选择=====</option>');
	ajax_major_1();
}

$(document).ready(function(){
	$('#batchId').change(function(){
		var batch = this.value;
		listLevel(batch);
	});
	
	$('#levelId').change(function(){
		var levelId = this.options[this.selectedIndex].getAttribute('al');
		listMajor(levelId);
	});
	$('#academyId').change(function(){
		listBatch(this.value);
	});
});
</script>
<a:ajax successCallbackFunctions="levelCallback" parameters="LEVEL_PARAM" pluginCode="level" urls="/enrollment/academylevel/list_academy_level_for_batch"/>
<a:ajax successCallbackFunctions="majorCallback" parameters="MAJOR_PARAM" pluginCode="major" urls="/enrollment/academymajor/list_academy_major_for_academy_level"/>
<a:ajax successCallbackFunctions="batchCallback" parameters="BATCH_PARAM" pluginCode="batch" urls="/enrollment/list_enroll_batch_for_academy"/>