package com.example.demo.service;

import com.example.demo.exceptions.CustomExceptions;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.OrderItemRequest;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void createOrder(List<OrderItemRequest> orderItemsRequest) throws CustomExceptions.OrderCreationException {
        // Validar disponibilidad de productos y construir la lista de OrderItem
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest itemRequest : orderItemsRequest) {
            Long productId = itemRequest.getProductId();
            int requestedQuantity = itemRequest.getQuantity();

            Product product = this.getProductById(productId);

            if (product.getAvailableQuantity() < requestedQuantity) {
                throw new CustomExceptions.OrderCreationException("Insufficient stock for product with ID: " + productId);
            }

            // Crear OrderItem y agregar a la lista
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(requestedQuantity);

            orderItems.add(orderItem);

            // Actualizar el stock del producto
            product.setAvailableQuantity(product.getAvailableQuantity() - requestedQuantity);
            this.updateProductQuantity(product);
        }

        // Construir la orden con la lista de OrderItem
        Order order = new Order();
        order.setOrderItems(orderItems);

        orderRepository.save(order);
    }

    public void updateProductQuantity(Product updatedProduct) throws CustomExceptions.ProductNotFoundExceptionInOrder {
        Long productId = updatedProduct.getId();
        Product existingProduct = getProductById(productId);

        if (existingProduct != null) {
            // Actualizar la cantidad del producto
            existingProduct.setAvailableQuantity(updatedProduct.getAvailableQuantity());
            productRepository.save(existingProduct);
        } else {
            throw new CustomExceptions.ProductNotFoundExceptionInOrder("Product not found with ID: " + productId);
        }
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
    }


    public void updateProductQuantityInOrder(Long orderId, Long productId, int newQuantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomExceptions.OrderNotFoundException("Order with ID " + orderId + " not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomExceptions.ProductNotFoundExceptionInOrder("Product with ID " + productId + " not found in the order"));

        if (newQuantity < 0) {
            throw new CustomExceptions.InvalidQuantityException("New quantity cannot be less than zero");
        }

        if (newQuantity > product.getAvailableQuantity()) {
            throw new CustomExceptions.ExceededQuantityException("New quantity exceeds available quantity of the product");
        }

        // Realizar la actualización
        try {
            product.setAvailableQuantity(product.getAvailableQuantity() - newQuantity);
            orderRepository.save(order);
            productRepository.save(product);
        } catch (Exception e) {
            throw new CustomExceptions.UpdateFailedException("Failed to update product quantity in the order", e);
        }
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
