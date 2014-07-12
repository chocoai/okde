package net.cedu.action.book;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.BookCategoryBiz;
import net.cedu.biz.book.MeasuringUnitBiz;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.BookCategory;
import net.cedu.entity.book.MeasuringUnit;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * 教材回填修改
 * @author XFY
 *
 */
public class DoUpdateBookAction extends BaseAction {
 
	private static final long serialVersionUID = -5860550945506821152L;
	@Autowired
	private BookBiz bookbiz;
	@Autowired
	private BookCategoryBiz bookcategorybiz;
	@Autowired
	private MeasuringUnitBiz measuringunitbiz;
	
	private int id;
	private Book book;
	private List<BookCategory> categorys=new ArrayList<BookCategory>();
	private List<MeasuringUnit> units=new ArrayList<MeasuringUnit>();
	
	
	public String execute()
	{
		try {
			if(isGetRequest())
			{
			book=bookbiz.findBookById(id);
			categorys=bookcategorybiz.findBookCategoryAll();
			units=measuringunitbiz.findMeasuringUnitAll();
			}
			 
			} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
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
