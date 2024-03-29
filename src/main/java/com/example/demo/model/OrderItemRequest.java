package com.example.demo.model;

public class OrderItemRequest {
    private Long productId;
    private int quantity;

    // Constructores, getters y setters

    public OrderItemRequest() {
    }

    public OrderItemRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
