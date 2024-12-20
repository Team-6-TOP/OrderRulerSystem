package exceptions;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(String orderException) {
        super(orderException);
    }
}
