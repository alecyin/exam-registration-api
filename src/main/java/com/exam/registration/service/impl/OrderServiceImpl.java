package com.exam.registration.service.impl;

import com.exam.registration.mapper.OrderMapper;
import com.exam.registration.model.Exam;
import com.exam.registration.model.Order;
import com.exam.registration.service.ExamService;
import com.exam.registration.service.OrderService;
import com.exam.registration.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

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
    @Autowired
    private ExamService examService;

    @Override
    public long countOrders(Map<String, Object> map) {
        return orderMapper.countOrders(map);
    }

    @Override
    public int deleteOrderByPrimaryKey(Long id) {
        return orderMapper.deleteOrderByPrimaryKey(id);
    }

    @Override
    public int deleteOrderByPrimaryKeys(String ids) {
        return orderMapper.deleteOrderByPrimaryKeys(ids);
    }

    @Override
    public int insertOrder(Order order) {
        Date now = new Date();
        order.setCreateTime(now);
        order.setUpdateTime(now);
        if (Objects.isNull(order.getIsDeleted())) {
            order.setIsDeleted(false);
        }
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
    public Order getOrderByOrderNumber(String orderNumber) {
        return orderMapper.getOrderByOrderNumber(orderNumber);
    }

    @Override
    public void getExamNumber(Order order) {
        RedisTemplate redisTemplate = RedisUtils.getRedisTemplate();
        String examineeNumber = null;
        Exam exam = examService.getExamByPrimaryKey(order.getExamId());
        if (!redisTemplate.hasKey(RedisUtils.EXAM_NUMBER_PREFIX + order.getExamId())) {// 第一个考生报名
            // 去掉数字前面的0
            examineeNumber = exam.getStartExamineeNumber().replaceAll("^(0+)", "");
            redisTemplate.opsForValue().set(RedisUtils.EXAM_NUMBER_PREFIX + order.getExamId(), examineeNumber);
            // 再补上0...
        } else {// 非第一个考生，redis内的数字递增1，需重新获取，避免值已经更改
            examineeNumber = String.valueOf(redisTemplate.opsForValue()
                                        .increment(RedisUtils.EXAM_NUMBER_PREFIX + order.getExamId()));
        }
        exam.setCurrentExamineeNumber(examineeNumber);
        examService.updateExamByPrimaryKeySelective(exam);
        examineeNumber = String.format("%04d", Integer.valueOf(examineeNumber));
        order.setExamineeNumber(examineeNumber);
        updateOrderByPrimaryKeySelective(order);
    }

    @Override
    public List<Order> listOrders() {
        return orderMapper.listOrders();
    }

    @Override
    public List<Order> listOrdersByPage(Map<String, Object> map) {
        return orderMapper.listOrdersByPage(map);
    }

    @Override
    public List<Order> listOrdersByStudentId(Long studentId) {
        return orderMapper.listOrdersByStudentId(studentId);
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
