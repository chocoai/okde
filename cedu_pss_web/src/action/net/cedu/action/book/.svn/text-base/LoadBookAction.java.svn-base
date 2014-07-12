package net.cedu.action.book;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookCategoryBiz;
import net.cedu.biz.book.MeasuringUnitBiz;
import net.cedu.entity.book.BookCategory;
import net.cedu.entity.book.MeasuringUnit;
/**
 *教材增加 
 * @author XFY
 *
 */
public class LoadBookAction extends BaseAction {
 
	private static final long serialVersionUID = -1394306529397774155L;
	@Autowired
	private BookCategoryBiz bookcategorybiz;
	@Autowired
	private MeasuringUnitBiz measuringunitbiz;
	
	List<BookCategory> categorys=new ArrayList<BookCategory>();
	
	List<MeasuringUnit> units=new ArrayList<MeasuringUnit>();
	@Action(value="load_book",results={@Result(location="add_books.jsp")})
	public String execute()
	{
		try {
			categorys=bookcategorybiz.findBookCategoryAll();
			units=measuringunitbiz.findMeasuringUnitAll();
		} catch (Exception e) {
		 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<BookCategory> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<BookCategory> categorys) {
		this.categorys = categorys;
	}

	public List<MeasuringUnit> getUnits() {
		return units;
	}

	public void setUnits(List<MeasuringUnit> units) {
		this.units = units;
	}
	
	
}
