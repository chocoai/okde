package net.cedu.dao.book.impl;

import net.cedu.dao.book.InvoiceDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.book.Invoice;

import org.springframework.stereotype.Repository;

/**
 * 总部发货单数据层实现类
 * @author YY
 *
 */
@Repository
public class InvoiceDaoImpl extends BaseMDDaoImpl<Invoice> implements InvoiceDao{

}
