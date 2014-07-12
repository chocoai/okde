package net.cedu.common.hibernate;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;

/**
 * hibernate注册mysql convert 函数
 * 
 * @author yangdongdong
 * 
 */
public class MySQL5LocalDialect extends MySQLDialect {
	public MySQL5LocalDialect() {
		super();
		registerFunction("convert", new SQLFunctionTemplate(Hibernate.STRING,
				"convert(?1 using ?2)"));

	}
}
