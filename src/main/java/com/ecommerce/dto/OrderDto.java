package com.ecommerce.dto;

import java.util.List;

public class OrderDto {
    private Integer orderId;
    private String orderStatus;
    private Double orderAmount;
    private List<ItemDto> orderItems;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public List<ItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<ItemDto> orderItems) {
        this.orderItems = orderItems;
    }
}
