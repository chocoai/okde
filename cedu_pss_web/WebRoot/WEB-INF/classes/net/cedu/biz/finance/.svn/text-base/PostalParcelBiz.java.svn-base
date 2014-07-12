package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.finance.PostalParcel;
import net.cedu.model.page.PageResult;



/**
 * 邮寄包  业务逻辑接口
 * 
 * @author gaole
 *
 */
public interface PostalParcelBiz {
	
	
	/**
	 * 添加邮寄单
	 * @return
	 * @throws Exception
	 */
	public boolean addPostalParcel(PostalParcel postalparcel)throws Exception;
	
	
	/**
	 * 添加邮寄单
	 * @return
	 * @throws Exception
	 */
	public boolean updatePostalParcel(PostalParcel postalparcel)throws Exception;
	
	
	/**
	 * 查询邮寄单按Id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PostalParcel findPostalParcelById(int id)throws Exception;
	
	
	/**
	 * 查询邮寄单按Id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<PostalParcel> findPostalParcelsByCode(String code)throws Exception;
	
	
	
	
	
	
	/**
	 * 查询邮寄单(分页数量)
	 * @param branchId
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int countPostalParcelByBranchId(int branchId,String statusIds,PageResult<PostalParcel> pr)throws Exception;
	
	
	/**
	 * 查询邮寄单(分页集合)
	 * @param branchId
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<PostalParcel> findPostalParcelByBranchId(int branchId,String statusIds,PageResult<PostalParcel> pr)throws Exception;
	
	
	/**
	 * 
	 * @功能：通过学习中心ID获取邮寄单邮寄过来的发票IDs（邮寄单已签收）
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-16 下午06:40:38
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public String findPostalParcelInvoiceIdsByBranchId(int branchId)throws Exception;
	
	
	
	
	
	
}
