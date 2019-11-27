package com.exam.registration.service;

import com.exam.registration.model.Order;
import com.exam.registration.model.OrderExample;

import java.util.List;

public interface OrderService {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Long id);

    int updateByExampleSelective(Order record, OrderExample example);

    int updateByExample(Order record, OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}
