package com.coltcn.ims.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.coltcn.ims.dao.Stock;
import com.coltcn.ims.dao.Order;
import com.coltcn.ims.dao.OrderDt;

import com.coltcn.ims.dao.StockDao;
import com.coltcn.ims.dao.OrderDao;
import com.coltcn.ims.dao.OrderDtDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig stockDaoConfig;
    private final DaoConfig orderDaoConfig;
    private final DaoConfig orderDtDaoConfig;

    private final StockDao stockDao;
    private final OrderDao orderDao;
    private final OrderDtDao orderDtDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        stockDaoConfig = daoConfigMap.get(StockDao.class).clone();
        stockDaoConfig.initIdentityScope(type);

        orderDaoConfig = daoConfigMap.get(OrderDao.class).clone();
        orderDaoConfig.initIdentityScope(type);

        orderDtDaoConfig = daoConfigMap.get(OrderDtDao.class).clone();
        orderDtDaoConfig.initIdentityScope(type);

        stockDao = new StockDao(stockDaoConfig, this);
        orderDao = new OrderDao(orderDaoConfig, this);
        orderDtDao = new OrderDtDao(orderDtDaoConfig, this);

        registerDao(Stock.class, stockDao);
        registerDao(Order.class, orderDao);
        registerDao(OrderDt.class, orderDtDao);
    }
    
    public void clear() {
        stockDaoConfig.getIdentityScope().clear();
        orderDaoConfig.getIdentityScope().clear();
        orderDtDaoConfig.getIdentityScope().clear();
    }

    public StockDao getStockDao() {
        return stockDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public OrderDtDao getOrderDtDao() {
        return orderDtDao;
    }

}