package net.cedu.biz.examination;
import java.util.List;
import net.cedu.entity.examination.Invigilator;
import net.cedu.model.page.PageResult;

/**
 * 
 * 监考老师业务层接口
 * @author panyuheng
 * 
 * */
public interface InvigilatorBiz {

	/**
	 * 分页查询
	 * 
	 * @param condition
	 *            条件
	 * @param result
	 *            分页参数
	 * @return
	 */
	public List<Invigilator> page(PageResult<Invigilator> result)throws Exception;

	/**
	 * 分页数量
	 * 
	 * @param condition
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public int countInvigilators(PageResult<Invigilator> result) throws Exception;
	
	/**
	 * 删除
	 * 
	 * */
	public Object deleteInvigilator(int id)throws Exception;
	public List<Invigilator> findAllInvigilator() throws Exception;
	/**
	 * 多条件查询
	 * 
	 * */
	public List<Invigilator> findByConditions(Invigilator invigilator, PageResult<Invigilator> pr)
	throws Exception;
	
	
	/**
	 * 多条件查询结果数量
	 * 
	 * */
	 public int findInvigilatorPageCount(Invigilator invigilator, PageResult<Invigilator> pr)
	  throws Exception;
	 
	 /**
	  * 
	  * 增加新教师
	  * 
	  * */
	 public Object saveInvgilator(Invigilator invigilator)throws Exception;
	 /**
	  * 
	  * 根据Id查找监考老师
	  * 
	  * */
	 public Invigilator findByInvigilatorId(int id)throws Exception;
	 /**
	  * 
	  * 修改监考老师
	  * 
	  * */
	 public Object updateInvigilate(Invigilator invigilator)throws Exception;
	 /**
	  * 
	  * 按监考老师家名字查询
	  * */
	 public Invigilator findByName(String name) throws Exception;
	 
	 
	 public boolean createNew(Invigilator invigilator) throws Exception ;
}
