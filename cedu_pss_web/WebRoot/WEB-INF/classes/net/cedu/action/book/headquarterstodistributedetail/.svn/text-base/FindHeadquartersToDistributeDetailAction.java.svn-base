package net.cedu.action.book.headquarterstodistributedetail;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.HeadquartersToDistributeDetailBiz;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.HeadquartersToDistributeDetail;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * 派发明细列表
 * @author YY
 *
 */

public class FindHeadquartersToDistributeDetailAction extends BaseAction {
 
	private static final long serialVersionUID = 3310913464991031892L;
	@Autowired
	private HeadquartersToDistributeDetailBiz HeadquartersToDistributeDetailBiz; //派发单明细业务层
	@Autowired
	private BookBiz bookBiz; //教材业务层
	private List<HeadquartersToDistributeDetail> disteributeList=new ArrayList<HeadquartersToDistributeDetail>();//派发单集合
	private int disteributeId; //派发单id
	
	private String distributename; //派发人姓名
	private String branchName; //中心名称
	private String code; //派发单编号
	private double avg; //总金额
	
	public String execute()
	{	
		
		try {
			disteributeList=HeadquartersToDistributeDetailBiz.findHeadquartersToDistributeDetail(disteributeId);
			for (HeadquartersToDistributeDetail htdd : disteributeList) {
			 Book book=bookBiz.findBookById(htdd.getBookId());
			 htdd.setBookedition(book.getEdition());
			 htdd.setBookname(book.getName());
			 htdd.setBookprice(book.getPrice());
			 htdd.setBookpurchasePrice(book.getPurchasePrice());
			 
			}
		
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<HeadquartersToDistributeDetail> getDisteributeList() {
		return disteributeList;
	}

	public void setDisteributeList(
			List<HeadquartersToDistributeDetail> disteributeList) {
		this.disteributeList = disteributeList;
	}

	public int getDisteributeId() {
		return disteributeId;
	}

	public void setDisteributeId(int disteributeId) {
		this.disteributeId = disteributeId;
	}

	public String getDistributename() {
		return distributename;
	}

	public void setDistributename(String distributename) {
		this.distributename = distributename;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	
	
}
