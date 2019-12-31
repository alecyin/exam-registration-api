package com.exam.registration.service;

import com.exam.registration.model.Order;

import java.util.List;
import java.util.Map;

/**
 * @author yhf
 * @classname OrderService
 * @description TODO
 * @date 2019/12/18
 **/
public interface OrderService {
    long countOrders(Map<String, Object> map);

    int deleteOrderByPrimaryKey(Long id);

    int deleteOrderByPrimaryKeys(String ids);

    int insertOrder(Order record);

    int insertOrderSelective(Order record);

    Order getOrderByPrimaryKey(Long id);

    List<Order> listOrders();

    List<Order> listOrdersByPage(Map<String, Object> map);

    int updateOrderByPrimaryKeySelective(Order record);

    int updateOrderByPrimaryKey(Order record);
}
