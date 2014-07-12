package net.cedu.action.book;

import java.io.File;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.enums.CodeEnum;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.book.Book;
/**
 * 教材增加
 * @author XFY
 *
 */
public class AddBookAction extends BaseAction {
 
	private static final long serialVersionUID = 1627691102040626578L;

	@Autowired
	private BookBiz bookbiz;
	private Book book=new Book();;
	private boolean result=false;
	private File picture; //图片路径
	private String pictureFileName; //图片
	private String savePath; //服务器路径
	
	@Autowired
	private BuildCodeBiz buildCodeBiz; // code生成器
	@Action(value="add_book",results={@Result(type="redirect",location="index_book")})
	public String execute()
	{
		try {
			String code=(buildCodeBiz.getCodes(CodeEnum.BookTB
					.getCode(), CodeEnum.Book.getCode()));
			book.setCode(code);
			book.setCreatedTime(new Date());
			book.setCreatorId(this.getUser().getUserId());
			book.setUpdatedTime(new Date());
			
			//是否上传图片
			if(picture!=null)
			{  	
				//保存图片引用路径
				book.setSnapshot(uploadFile(pictureFileName));
			}
			bookbiz.addBook(book);
			result=true;
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 附件上传_返回
	 * @param name 实际文件名
	 * @return	存储的相对路径
	 */
	private String uploadFile(String name)
	{
		try 
		{
			savePath = ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("book","uploadpath"));
			return FileUtil.FileUploads(savePath,name,picture);
		}
		catch (Exception e) 
		{			
			e.printStackTrace();
			return null;
		}
	}
	

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	 

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	
	
}
