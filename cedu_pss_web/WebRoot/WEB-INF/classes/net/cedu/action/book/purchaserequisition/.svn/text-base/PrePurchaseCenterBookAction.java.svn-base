package net.cedu.action.book.purchaserequisition;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.StockBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.Stock;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * 中心预申购教材信息
 * @author YY 
 *
 */
public class PrePurchaseCenterBookAction extends BaseAction {

	private static final long serialVersionUID = -7715443293116237280L;
	@Autowired
	private BookBiz bookBiz; //教材业务层
	private String isbn;  //教材isbn
	private int academyId; //院校
	private String bookname; //教材名称
	
	@Autowired
	private StockBiz stockBiz; //库存	
	
	@Autowired
	private AcademyBiz academyBiz; //院校业务层
	private String Academyname;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz; //批次业务层	
	private  int batchId; //批次id
	private String Batchname;//批次名称
	
	@Autowired
	private  LevelBiz levelBiz; //层次业务层
	private  int levelId; //层次id
	private String levelname;//层次名称
	
	@Autowired
	private MajorBiz majorBiz; //专业业务层
	private  int majorId; //专业id
	private String Majorname;//专业名称
	private List<Book> bookList=new ArrayList<Book>();  //教材集合

	private String stuids; //学生字符串id
	private int studentNumber; //学生数量	
 
	//显示全部教材信息
	public String execute() throws Exception {
		if(stuids!=null){
			//把学生id字符串转成成数组
			String [] ary=stuids.split(",");
			studentNumber=ary.length;	
			 levelname=(levelBiz.findLevelById(levelId).getName());
			 Majorname=(majorBiz.findMajorById(majorId).getName());		
			 Batchname=(academyEnrollBatchBiz.findAcademyEnrollBatchById(batchId).getEnrollmentName());
			//查询院校名称
			Academy  academy= academyBiz.findAcademyById(academyId);
			if(academy!=null)
			{
			 Academyname=academy.getName();
			}
		}
		bookList=bookBiz.findnameorisbnByBook(bookname,isbn);
 
		 
		//为库存赋值
		for (Book book : bookList) {
			Stock stock=stockBiz.findStockByBookId(book.getId());
			if(null!=stock)
			book.setTotal(stock.getTotal());
		}
		return SUCCESS;
	}
 
	public List<Book> getBookList() {
		return bookList;
	}


	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
 
	public String getBookname() {
		return bookname;
	}
 
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
 
	public String getIsbn() {
		return isbn;
	}
 
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	 

	public String getStuids() {
		return stuids;
	}

	public void setStuids(String stuids) {
		this.stuids = stuids;
	}

	 
	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public String getAcademyname() {
		return Academyname;
	}

	public void setAcademyname(String academyname) {
		Academyname = academyname;
	}

	public String getBatchname() {
		return Batchname;
	}

	public void setBatchname(String batchname) {
		Batchname = batchname;
	}

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public String getMajorname() {
		return Majorname;
	}

	public void setMajorname(String majorname) {
		Majorname = majorname;
	}
	
}
