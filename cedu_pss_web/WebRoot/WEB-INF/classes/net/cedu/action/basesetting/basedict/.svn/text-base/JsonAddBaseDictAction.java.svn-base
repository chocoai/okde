package net.cedu.action.basesetting.basedict;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.basesetting.BaseDict;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonAddBaseDictAction extends BaseAction{

	private static final long serialVersionUID = 5799317411018846828L;

	@Autowired
	private BaseDictBiz basedictbiz;
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	
	private BaseDict basedict;//基础字典实体
	public String code;//code生成器生成的code(4位的)
	private boolean addrltbool=true;//判断添加操作是否成功
	private boolean errormsg=true;//有重复数据时的判断
	
	/**
	 * 添加基础字典信息  BY HXJ
	 */
	@Action(value="add_base_dict",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		
		try {
			if (basedict!=null) {
				int userid = super.getUser().getUserId();
				code=buildCodeBiz.getShortCodes(CodeEnum.DictTB.getCode(),CodeEnum.Dict.getCode());
				basedict.setCode(code);
				basedict.setCreatedTime(new Date());
				basedict.setUpdatedTime(new Date());
				basedict.setCreatorId(userid);
				basedict.setUpdaterId(userid);
				basedict.setStatus(Constants.STATUS_ENABLED);
				basedict.setDeleteFlag(Constants.DELETE_FALSE);
				errormsg = basedictbiz.addBaseDict(basedict);
				BaseDict bd=basedictbiz.findBaseDictById(basedict.getId());
				bd.setLogicNode(getLogicNode(bd));
				basedictbiz.modifyBaseDict(bd);
			}else{
				addrltbool = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addrltbool = false;
		}
		
		return SUCCESS;
	}

	//生成逻辑节点
	private String getLogicNode(BaseDict basedict)throws Exception
	{
		
		
		String logicNode="";
		BaseDict p=basedictbiz.findBaseDictById(basedict.getParentNode());
		
		if(null!=p)
			logicNode=p.getLogicNode();		
		
		if(0==basedict.getParentNode())
			return "_"+basedict.getParentNode()+"_"+basedict.getId()+"_";
		else
			return logicNode+basedict.getId()+"_";
	}
	
	//---------------------------------------------get and set methods--------------------------------------------
	
	public BaseDict getBasedict() {
		return basedict;
	}

	public void setBasedict(BaseDict basedict) {
		this.basedict = basedict;
	}

	public boolean isAddrltbool() {
		return addrltbool;
	}

	public void setAddrltbool(boolean addrltbool) {
		this.addrltbool = addrltbool;
	}

	public boolean isErrormsg() {
		return errormsg;
	}

	public void setErrormsg(boolean errormsg) {
		this.errormsg = errormsg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
