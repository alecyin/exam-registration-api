package com.exam.registration.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * order
 *
 */
public class Order implements Serializable {
    /**
     *
     * order.id
     *
     */
    private Long id;

    /**
     * 考生id
     *
     * order.student_id
     *
     */
    private Long studentId;

    /**
     * 报名的考试id
     *
     * order.exam_id
     *
     */
    private Long examId;

    /**
     * 需要缴费
     *
     * order.cost
     *
     */
    private BigDecimal cost;

    /**
     * 分配的考号
     *
     * order.examinee_number
     *
     */
    private String examineeNumber;

    /**
     * 订单号
     *
     * order.order_number
     *
     */
    private String orderNumber;

    /**
     * 是否支付
     *
     * order.is_paid
     *
     */
    private Boolean isPaid;

    /**
     * 创建时间
     *
     * order.create_time
     *
     */
    private Date createTime;

    /**
     * 更新时间
     *
     * order.update_time
     *
     */
    private Date updateTime;

    /**
     * 启用状态
     *
     * order.is_deleted
     *
     */
    private Boolean isDeleted;

    /**
     */
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getExamineeNumber() {
        return examineeNumber;
    }

    public void setExamineeNumber(String examineeNumber) {
        this.examineeNumber = examineeNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentId=").append(studentId);
        sb.append(", examId=").append(examId);
        sb.append(", cost=").append(cost);
        sb.append(", examineeNumber=").append(examineeNumber);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", isPaid=").append(isPaid);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}