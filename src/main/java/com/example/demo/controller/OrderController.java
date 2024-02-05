package com.example.demo.controller;

import com.example.demo.exceptions.CustomExceptions;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.OrderItemRequest;
import com.example.demo.model.Product;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    @Autowired

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/allproducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = orderService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = orderService.createProduct(product);
        return ResponseEntity.ok(newProduct);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody List<OrderItemRequest> orderItemsRequest) {
        try {
            orderService.createOrder(orderItemsRequest);
            return ResponseEntity.ok("Order created successfully.");
        } catch (CustomExceptions.OrderCreationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{orderId}/products/{productId}/quantity")
    public ResponseEntity<String> updateProductQuantityInOrder(
            @PathVariable Long orderId,
            @PathVariable Long productId,
            @RequestParam int newQuantity) {

        try {
            orderService.updateProductQuantityInOrder(orderId, productId, newQuantity);
            return ResponseEntity.ok("Product quantity updated successfully");
        } catch (CustomExceptions.OrderNotFoundException e) {
            return ResponseEntity.badRequest().body("Order not found: " + e.getMessage());
        } catch (CustomExceptions.ProductNotFoundExceptionInOrder e) {
            return ResponseEntity.badRequest().body("Product not found in the order: " + e.getMessage());
        } catch (CustomExceptions.InvalidQuantityException e) {
            return ResponseEntity.badRequest().body("Invalid quantity: " + e.getMessage());
        } catch (CustomExceptions.ExceededQuantityException e) {
            return ResponseEntity.badRequest().body("Exceeded quantity: " + e.getMessage());
        } catch (CustomExceptions.UpdateFailedException e) {
            return ResponseEntity.status(500).body("Failed to update product quantity: " + e.getMessage());
        }
    }
}
