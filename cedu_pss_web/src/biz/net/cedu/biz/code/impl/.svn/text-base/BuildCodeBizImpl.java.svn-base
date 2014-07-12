package net.cedu.biz.code.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.Constants;
import net.cedu.common.properties.Config;
import net.cedu.dao.code.BuildCodeDao;
import net.cedu.entity.code.BuildCode;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 编码生成器业务层实现类
 * @author Jack
 *
 */
@Service
public class BuildCodeBizImpl implements BuildCodeBiz 
{
	@Autowired
	private BuildCodeDao buildCodeDao;

	/**
	 * 添加编码表
	 * 
	 * @param UserGroup
	 * @throws Exception
	 */
	public void createNew(BuildCode buildCode) throws Exception {
		buildCodeDao.save(buildCode);

	}

	/**
	 * 根据ID删除编码表
	 * 
	 * @param UserGroup
	 * @throws Exception
	 */
	public void deleteConfigById(int id) throws Exception {
		buildCodeDao.deleteConfig(id);

	}

	/**
	 * 修改编码表
	 * 
	 * @param UserGroup
	 * @throws Exception
	 */
	public void modify(BuildCode buildCode) throws Exception {
		try
		{
			buildCodeDao.update(buildCode);
		}
		catch(Exception e)
		{e.printStackTrace();}

	}
	
	/**
	 * 根据表名查询
	 * @param tbName
	 * @return
	 * @throws Exception
	 */
	private BuildCode findBuildCodeByTbName(String tbName)throws Exception
	{
		BuildCode buildCode=new BuildCode();
		buildCode.setTbName(tbName);
		List<BuildCode> list=findBuildCodeByCondition(buildCode);
		if(null!=list&&0<list.size())
			return list.get(0);
		else
			return null;
	}
	
	/**
	 * 根据条件查询
	 * @param buildCode
	 * @return
	 * @throws Exception
	 */
	private List<BuildCode> findBuildCodeByCondition(BuildCode buildCode) throws Exception 
	{
		String hqlcon = "";
		List<BuildCode> list = null;
		List<Object> paramList = new ArrayList<Object>();

		//表名
		if (StringUtils.isNotBlank(buildCode.getTbName())) 
		{
			hqlcon += " and tbName=" + Constants.PLACEHOLDER;
			paramList.add(buildCode.getTbName());
		}
		//键名
		if (StringUtils.isNotBlank(buildCode.getKeyName())) 
		{
			hqlcon += " and keyName=" + Constants.PLACEHOLDER;
			paramList.add(buildCode.getKeyName());
		}
		list = buildCodeDao.getByProperty(hqlcon, paramList);
		return list;
	}
	
	/**
	 * 获取编码_供调用
	 * @param tbName
	 * @param codeKey
	 * @return
	 * @throws Exception
	 */
	public String getCodes(String tbName,String codeKey)throws Exception
	{
		BuildCode buildCode=findBuildCodeByTbName(tbName);
//		BuildCode buildCode=buildCodeDao.findById(1);
		String code=getCodes(buildCode.getCurrentValue());
//		buildCode.setCurrentValue(code);
//		modify(buildCode);
		buildCodeDao.update(" set currentValue=" + Constants.PLACEHOLDER,
				buildCode.getId()+"", new Object[] { code });
		return codeKey+code;
	}
	
	/**
	 * 获取短编码_供调用
	 * @param tbName
	 * @param codeKey
	 * @return
	 * @throws Exception
	 */
	public String getShortCodes(String tbName,String codeKey)throws Exception
	{
		BuildCode buildCode=findBuildCodeByTbName(tbName);
		String code=getShortCodes(buildCode.getShortValue());
		buildCodeDao.update(" set shortValue=" + Constants.PLACEHOLDER,
				buildCode.getId()+"", new Object[] { code });
		return codeKey+code;
	}
	
	/**
	 * 获取编码_供调用
	 * @param tbName
	 * @param codeKey
	 * @return
	 * @throws Exception
	 */
	public BuildCode getCodes2(String tbName)throws Exception
	{
		BuildCode buildCode=findBuildCodeByTbName(tbName);
		String code=getCodes(buildCode.getCurrentValue());
		buildCode.setCurrentValue(code);
		return buildCode;
	}
	
	/**
	 * 获取编码 _内部函数
	 * @param code
	 * @return
	 * @throws Exception
	 */
	private String getCodes(String code)throws Exception
	{
		String dateformat=Config.getProperty("code.dateformat");
		SimpleDateFormat sdf=new SimpleDateFormat(dateformat);
		Date dataDate=getCodeDate(code,sdf,dateformat.length());
		Date now=new Date();
		now=sdf.parse(sdf.format(now));
		String codeDate="";
		String codeNum="";
		if(now.equals(dataDate))
		{
			codeDate=sdf.format(dataDate);
			codeNum=getCodeNum(code,false,dateformat.length());
		}
		else
		{
			codeDate=sdf.format(now);
			codeNum=getCodeNum(code,true,dateformat.length());
		}
		return codeDate+codeNum;
	}
	
	/**
	 * 获取短编码 _内部函数
	 * @param code
	 * @return
	 * @throws Exception
	 */
	private String getShortCodes(String code)throws Exception
	{
		String codeNum="";
		codeNum=getShortCodeNum(code,false,0);
		
		return codeNum;
	}
	
	/**
	 * 获取当前编码日期
	 * @param strs
	 * @param sdf
	 * @return
	 * @throws Exception
	 */
	private Date getCodeDate(String strs,SimpleDateFormat sdf,int length)throws Exception
	{
		if(strs.length()>=length)
		{
			strs=strs.substring(0,length);
			return sdf.parse(strs);
		}
		else
			return new Date();
		
	}
	
	/**
	 * 获取编码号
	 * @param strs
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	private String getCodeNum(String strs,boolean flag,int length)throws Exception
	{
		String codeNum="";
		if(strs.length()>=length)
			strs=strs.substring(length);
		else 
			strs="0";
		int count=Integer.parseInt(Config.getProperty("code.length"));
		if(flag)
		{
			codeNum=getCodeNum("1",count);
		}
		else
		{
			codeNum=String.valueOf(Integer.parseInt(strs)+1);
			codeNum=getCodeNum(codeNum,count);
		}
		return codeNum;
	}
	
	/**
	 * 获取编码号
	 * @param strs
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	private String getShortCodeNum(String strs,boolean flag,int length)throws Exception
	{
		String codeNum="";
		if(strs.length()>=length)
			strs=strs.substring(length);
		else 
			strs="0";
		int count=Integer.parseInt(Config.getProperty("code.shortlength"));
		if(flag)
		{
			codeNum=getCodeNum("1",count);
		}
		else
		{
			codeNum=String.valueOf(Integer.parseInt(strs)+1);
			codeNum=getCodeNum(codeNum,count);
		}
		return codeNum;
	}
	
	/**
	 * 补零
	 * @param strs
	 * @param count
	 * @return
	 */
	private String getCodeNum(String strs,int count)
	{
		while(strs.length()<count)
		{
			strs="0"+strs;
		}
		return strs;
	}
}
