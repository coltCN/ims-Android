package com.coltcn.generator;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by majfc on 2015/10/24.
 */
public class ModelDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1,"com.coltcn.ims.dao");

        addStock(schema);
        addOrder(schema);

        new DaoGenerator().generateAll(schema, "../app/src/main/java");
    }

    private static void addStock(Schema schema){
        Entity stock = schema.addEntity("Stock");
        stock.implementsInterface("java.io.Serializable");
        stock.addIdProperty();
        stock.addStringProperty("name").notNull();
        stock.addDoubleProperty("price");
        stock.addIntProperty("quantity");
    }

    private static void addOrder(Schema schema) {
        Entity order = schema.addEntity("Order");
        order.addIdProperty();
        order.addStringProperty("orderNo");
        order.addStringProperty("buyer");
        order.addStringProperty("addresss");

        Entity orderDt = schema.addEntity("OrderDt");
        orderDt.addIdProperty();
        Property orderId =  orderDt.addLongProperty("blid").getProperty();
        orderDt.addStringProperty("stockName");
        orderDt.addIntProperty("quantity");
        orderDt.addDateProperty("price");

        order.addToMany(orderDt,orderId).setName("stocks");
    }

}
