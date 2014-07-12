package net.cedu.action.template;

import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;

import net.cedu.action.BaseAction;

import org.apache.struts2.ServletActionContext;

public class DownLoadAction extends BaseAction {
	private String filePath;// 文件路径
	private String fileName;// 文件名称
	public String execute() throws Exception {
		FileInputStream in = null; // 输入流
		ServletOutputStream out = null; // 输出流
		try {
			in = new FileInputStream(ServletActionContext.getServletContext()
					.getRealPath(filePath)); // 读入文件
			out = response.getOutputStream();
			response.reset();// 清空输出流
			response.setHeader(
					"Content-disposition",
					"attachment; filename="
							+ new String((fileName == null ? "" : fileName)
									.getBytes("UTF-8"), "ISO-8859-1"));// 设定输出文件头
			out.flush();
			int aRead = 0;
			while ((aRead = in.read()) != -1 & in != null) {
				out.write(aRead);
			}
			out.flush();
		} catch (Throwable e) {
		} finally {
			try {
				in.close();
				out.close();
			} catch (Throwable e) {
			}
		}
		return null;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
