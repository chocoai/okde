package net.cedu.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.cedu.common.file.excel.ExcelExport;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.model.base.AuthenticationTicket;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 所有action的基类
 * 
 * 
 * @author yangdongdong
 */
@Results({ @Result(name = "EXPORT_DATA_ZERO", location = "/WEB-INF/content/template/default/export_data_zero.jsp") })
@Controller
public class BaseAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 6120618114388672422L;

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected ServletContext application;
	// 资源文件名称
	private String il8nName;

	/**
	 * 判断当前方法是否为get方法
	 * 
	 * @return
	 */
	public boolean isGetRequest() {
		return request.getMethod().equalsIgnoreCase("get");
	}

	/**
	 * 获取当前用户
	 * 
	 * @return
	 */
	public AuthenticationTicket getUser() {
		return (AuthenticationTicket) session.getAttribute("userTicket");
	}

	/**
	 * 获取路径名称
	 * 
	 * @return
	 */
	public String getReferer() {
		int i = request.getHeader("Referer").indexOf("/cedu");
		return request.getHeader("Referer").substring(i);
	}

	/**
	 * 实现org.apache.struts2.interceptor.ServletRequestAware接口，
	 * 重写setServletRequest()方法 ,获取HttpServletRequest对象，
	 * 再通过HttpServletRequest对象取昨HttpSession和ServletContext对象
	 */
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		this.session = arg0.getSession();
		this.application = session.getServletContext();
		this.response = ServletActionContext.getResponse();
	}

	/**
	 * 获取国际化资源文件值
	 */
	@Override
	public String getText(String key) {
		return ResourcesTool.getText(il8nName, key, this.getLocale());
	}

	/**
	 * 获取国际化资源文件值 重载
	 */

	public String getText(String key, Object[] values) {
		return ResourcesTool.getText(il8nName, key, this.getLocale(), values);
	}

	/**
	 * 获取国际化资源文件值 重载
	 */
	@Override
	public String getText(String key, List values) {
		return ResourcesTool.getText(il8nName, key, this.getLocale(),
				values.toArray());
	}

	public void setIl8nName(String il8nName) {
		this.il8nName = il8nName;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 导出Excel
	 * 
	 * @param workbook
	 * @param downLoadType
	 * @param fileName
	 * @throws Exception
	 */
	public void downLoadFile(HSSFWorkbook workbook, String fileName)
			throws Exception {
		if (workbook != null) {
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader(
					"Content-disposition",
					"attachment; filename="
							+ new String((fileName == null ? "" : fileName)
									.getBytes(getSystemCodes()), "ISO-8859-1"));// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			workbook.write(os);
			os.close();
		}
	}

	/**
	 * 
	 * @功能：导出结果到Excel
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2012-2-2 下午03:16:43
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param <T>
	 * @param title
	 * @param list
	 * @return
	 * @throws IOException
	 */
	protected <T> String exportExcel(String title, List<T> list)
			throws IOException {

		if (list != null && list.size() != 0) {
			if (title == null || "".equals(title))
				title = "QueryResult";

			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition",
					"attachment; filename="
							+ new String((title + ".xls" == null ? "" : title
									+ ".xls").getBytes(getSystemCodes()),
									"ISO-8859-1"));// 设定输出文件头
			response.setContentType("application/msexcel");
			ExcelExport<T> ex = new ExcelExport<T>();
			ex.exportExcel(title, list, os);
			os.close();
			return null;
		} else {
			return "EXPORT_DATA_ZERO";
		}
	}

	private String getSystemCodes() {
		String r = request.getHeader("User-agent").toLowerCase();
		String u = "";
		if (r.indexOf("mac") != -1) {
			u = "UTF-8";
		} else if (r.indexOf("windows") != -1) {
			u = "gbk";
		} else {
			u = "UTF-8";
		}
		return u;
	}

}