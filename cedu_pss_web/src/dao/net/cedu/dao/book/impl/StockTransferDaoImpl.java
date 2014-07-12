package net.cedu.dao.book.impl;

import net.cedu.dao.book.StockTransferDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.book.StockTransfer;

import org.springframework.stereotype.Repository;

/**
 * 移库数据层实现类
 * @author XFY
 *
 */
@Repository
public class StockTransferDaoImpl extends BaseMDDaoImpl<StockTransfer> implements StockTransferDao{

}
