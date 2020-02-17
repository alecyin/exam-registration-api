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

    long countOrdersByExamId(Long id);

    int deleteOrderByPrimaryKey(Long id);

    int deleteOrderByPrimaryKeys(String ids);

    int insertOrder(Order record);

    int insertOrderSelective(Order record);

    Order getOrderByPrimaryKey(Long id);

    Order getOrderByOrderNumber(String orderNumber);

    void getExamNumber(Order order);

    List<Order> listOrders();

    List<Order> listOrdersByPage(Map<String, Object> map);

    List<Order> listOrdersByStudentId(Long studentId);

    int updateOrderByPrimaryKeySelective(Order record);

    int updateOrderByPrimaryKey(Order record);
}
