package net.cedu.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import net.cedu.common.Constants;

/**
 * web路径
 * 
 * @author yangdongdong
 * 
 */
public class UrlPathTag extends SimpleTagSupport {

	private String url;

	public void doTag() throws JspException, IOException {
	
		JspWriter out = this.getJspContext().getOut();
		if (url.startsWith("/")) {
			out.print(Constants.WEB_PATH+url);
		}else{
			out.print(Constants.WEB_PATH+"/"+url);
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
