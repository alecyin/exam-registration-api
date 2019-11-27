package com.exam.registration.service.impl;

import com.exam.registration.model.Order;
import com.exam.registration.model.OrderExample;
import com.exam.registration.service.OrderService;

import java.util.List;

/**
 * @author yhf
 * @classname OrderServiceImpl
 * @description TODO
 * @date 2019/11/27
 **/
public class OrderServiceImpl implements OrderService {
    @Override
    public long countByExample(OrderExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(OrderExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Order record) {
        return 0;
    }

    @Override
    public int insertSelective(Order record) {
        return 0;
    }

    @Override
    public List<Order> selectByExample(OrderExample example) {
        return null;
    }

    @Override
    public Order selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByExampleSelective(Order record, OrderExample example) {
        return 0;
    }

    @Override
    public int updateByExample(Order record, OrderExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Order record) {
        return 0;
    }
}
