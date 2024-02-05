package com.example.demo.exceptions;

public class CustomExceptions {

    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    public static class OrderCreationException extends Exception {
        public OrderCreationException(String message) {super(message);}
    }

    public static class ProductNotFoundExceptionInOrder extends RuntimeException {
        public ProductNotFoundExceptionInOrder(String message) {
            super(message);
        }
    }

    public static class InvalidQuantityException extends RuntimeException {
        public InvalidQuantityException(String message) {
            super(message);
        }
    }

    public static class ExceededQuantityException extends RuntimeException {
        public ExceededQuantityException(String message) {
            super(message);
        }
    }

    public static class UpdateFailedException extends RuntimeException {
        public UpdateFailedException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
