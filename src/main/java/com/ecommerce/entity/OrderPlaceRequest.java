package com.ecommerce.entity;

import java.util.List;

public class OrderPlaceRequest {
    private String fullName;
    private String fullAddress;
    private String contactNumber;
    private String altContactNumber;
    private String status;
    private Double amount;
    private List<OrderProductQuantity> orderProductQuantityList;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAltContactNumber() {
        return altContactNumber;
    }

    public void setAltContactNumber(String altContactNumber) {
        this.altContactNumber = altContactNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<OrderProductQuantity> getOrderProductQuantityList() {
        return orderProductQuantityList;
    }

    public void setOrderProductQuantityList(List<OrderProductQuantity> orderProductQuantityList) {
        this.orderProductQuantityList = orderProductQuantityList;
    }
}
