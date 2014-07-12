package net.cedu.common.servlet;

import javax.servlet.ServletContext;

/**
 * 
 * @功能：单例ServletContext的对象
 * 
 * @作者： 杨栋栋
 * @作成时间：2011-11-17 下午05:34:46
 * 
 * @修改者：杨栋栋
 * @修改内容：补注释
 * @修改时间：2011-11-17 下午05:34:46
 * 
 */
public class SingletonServletContext {

	private static ServletContext servletContext = null;

	private SingletonServletContext() {
	}

	public static void init(ServletContext sc) {
		if (servletContext == null) {
			servletContext = sc;
		}
	}

	public static ServletContext newInstance() {
		return servletContext;
	}
}
