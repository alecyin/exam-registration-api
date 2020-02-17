package com.exam.registration.mapper;

import com.exam.registration.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OrderMapper {
    long countOrders(Map<String, Object> map);

    long countOrdersByExamId(Long id);

    int deleteOrderByPrimaryKey(Long id);

    int deleteOrderByPrimaryKeys(String ids);

    int insertOrder(Order record);

    int insertOrderSelective(Order record);

    Order getOrderByPrimaryKey(Long id);

    Order getOrderByOrderNumber(String orderNumber);

    List<Order> listOrders();

    List<Order> listOrdersByPage(Map<String, Object> map);

    List<Order> listOrdersByStudentId(Long studentId);

    List<Order> listPaidOrdersByStudentId(Long studentId);

    int updateOrderByPrimaryKeySelective(Order record);

    int updateOrderByPrimaryKey(Order record);
}