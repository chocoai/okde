package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.entity.book.BookSupplier;
/**
 * 书商删除
 * @author XFY
 *
 */
/**
 * @author Administrator
 *
 */
public class UpdateBookSupplierAction extends BaseAction {
 
	private static final long serialVersionUID = -10684575259121061L;

	@Autowired
	private BookSupplierBiz bsbiz;
	
	private BookSupplier bs;
	
	private boolean results=false;
	
	@Action(value="update_booksupplier",results={@Result(type="redirect",location="index_book_supplier")})
	public String execute()
	{
		try {
			if(bs!=null)
			{
				if(bs.getId()!=0)
				{
					BookSupplier b=bsbiz.findBookSupplierById(bs.getId());
					b.setUpdatedTime(new Date());
					b.setUpdater_id(this.getUser().getUserId());
					b.setName(bs.getName());
					b.setShortName(bs.getShortName());
					b.setCategory(bs.getCategory());
					b.setAddress(bs.getAddress());
					b.setPostalCode(bs.getPostalCode());
					b.setWebsite(bs.getWebsite());
					b.setLinkman(bs.getLinkman());
					b.setTelephone(bs.getTelephone());
					b.setMobile(bs.getMobile());
					b.setFax(bs.getFax());
					b.setEmail(bs.getEmail());
					b.setAccountBank(bs.getAccountBank());
					b.setAccount(bs.getAccount());
					b.setClearingForm(bs.getClearingForm());
					b.setNote(bs.getNote());
					bsbiz.updateBookSupplier(b);
					results=true;
				}
			}
			
		} catch (Exception e) {
		 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public BookSupplier getBs() {
		return bs;
	}

	public void setBs(BookSupplier bs) {
		this.bs = bs;
	}

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}

	
	
}
