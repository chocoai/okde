package net.cedu.action.orgstructure.department;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.orgstructure.DepartmentBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.orgstructure.Department;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Json数据返回
 * @author Jack
 *
 */ 
@ParentPackage("json-default")
public class JsonListDepartmentAction extends BaseAction implements ModelDriven<Department>
{
	private static final long serialVersionUID = -9007096419088908044L;
	
	@Autowired
	private DepartmentBiz departmentBiz;
	private List<Department> list=new ArrayList<Department>();
	
	private Department department=new Department();
	
	/**
	 * 获取部门集合
	 * @return
	 * @throws Exception
	 */
	@Action(value="list_department", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"excludeProperties",
					"list.*.parentNode,"+
					"list.*.logicNode,"+
					"list.*.createBy,"+
					"list.*.createOn,"+
					"list.*.officeId,"+
					"list.*.orders,"+
					"results,branch,result"
					} )})
	public String execute()throws Exception
	{
		list=departmentBiz.findListComposite(department,null,BranchEnum.Default);
		if(list!=null && list.size()>0)
		{
			Collections.sort(list, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Department) arg0).getName();
					String name2 = ((Department) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
		}
		return SUCCESS;
	}

	public List<Department> getList() {
		return list;
	}

	public Department getModel() {
		// TODO Auto-generated method stub
		return department;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
