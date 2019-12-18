package com.exam.registration.service.impl;

import com.exam.registration.mapper.OrderMapper;
import com.exam.registration.model.Order;
import com.exam.registration.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author yhf
 * @classname OrderServiceImpl
 * @description TODO
 * @date 2019/12/18
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public long countOrders() {
        return orderMapper.countOrders();
    }

    @Override
    public int deleteOrderByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insertOrder(Order order) {
        Date now = new Date();
        order.setCreateTime(now);
        order.setUpdateTime(now);
        order.setIsDeleted(false);
        order.setIsPaid(false);
        // 此处写订单生成代码。。
        return orderMapper.insertOrder(order);
    }

    @Override
    public int insertOrderSelective(Order order) {
        return 0;
    }

    @Override
    public Order getOrderByPrimaryKey(Long id) {
        return orderMapper.getOrderByPrimaryKey(id);
    }

    @Override
    public List<Order> listOrders() {
        return orderMapper.listOrders();
    }

    @Override
    public int updateOrderByPrimaryKeySelective(Order order) {
        order.setUpdateTime(new Date());
        return orderMapper.updateOrderByPrimaryKeySelective(order);
    }

    @Override
    public int updateOrderByPrimaryKey(Order order) {
        return 0;
    }
}
