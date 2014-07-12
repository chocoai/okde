package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.book.HeadquartersToDistributeDetailBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.HeadquartersToDistributeDetailDao;
import net.cedu.entity.book.HeadquartersToDistributeDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 总部派发明细业务层
 * @author YY
 *
 */
@Service
public class HeadquartersToDistributeDetailBizImpl  implements HeadquartersToDistributeDetailBiz{

	@Autowired
	private HeadquartersToDistributeDetailDao headquartersToDistributeDetailDao; //总部派发数据层
		
	/**
	 * 增加方法
	 */
	public void addHeadquartersToDistributeDetail(
			HeadquartersToDistributeDetail headquartersToDistributeDetail) {
		headquartersToDistributeDetailDao.save(headquartersToDistributeDetail);		
	}
	
	/**
	 * 根据派发单id查询派发单明细
	 */
	public List<HeadquartersToDistributeDetail> findHeadquartersToDistributeDetail(
			int disteributeId) throws Exception {
		 String sql="";
		 List<HeadquartersToDistributeDetail> disteributelist=new ArrayList<HeadquartersToDistributeDetail>();
		 List<Object> list=new ArrayList<Object>();
		 if(disteributeId!=0)
		 {
			 sql+="and distributeId="+Constants.PLACEHOLDER;
			 list.add(disteributeId);
		 }	 
		 disteributelist=headquartersToDistributeDetailDao.getByProperty(sql, list);
		 return disteributelist;
	}
	

}
