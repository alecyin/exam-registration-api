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