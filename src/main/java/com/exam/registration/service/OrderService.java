package com.exam.registration.service;

import com.exam.registration.model.Order;

import java.util.List;

/**
 * @author yhf
 * @classname OrderService
 * @description TODO
 * @date 2019/12/18
 **/
public interface OrderService {
    long countOrders();

    int deleteOrderByPrimaryKey(Long id);

    int insertOrder(Order record);

    int insertOrderSelective(Order record);

    Order getOrderByPrimaryKey(Long id);

    List<Order> listOrders();

    int updateOrderByPrimaryKeySelective(Order record);

    int updateOrderByPrimaryKey(Order record);
}
