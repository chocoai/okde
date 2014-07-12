package net.cedu.dao.book.impl;

import java.io.Serializable;
import java.util.List;

import javax.servlet.jsp.jstl.sql.Result;

import org.springframework.stereotype.Repository;

import net.cedu.dao.book.BookCategoryDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.book.BookCategory;
import net.cedu.model.page.PageParame;
/**
 * 教材分类数据层实现类
 * @author XFY
 *
 */
@Repository
public class BookCategoryDaoImpl extends BaseMDDaoImpl<BookCategory> implements
		BookCategoryDao {

}
