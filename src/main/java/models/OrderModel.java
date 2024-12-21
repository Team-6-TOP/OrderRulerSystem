package models;

import java.util.List;
import java.util.Objects;

public class OrderModel {
    private final int orderID;
    private String orderCustomer;
    private List orderProduct;

    public OrderModel(int orderID, String orderCustomer, List orderProduct) {
        this.orderID = orderID;
        this.orderCustomer = orderCustomer;
        this.orderProduct = orderProduct;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getOrderCustomer() {
        return orderCustomer;
    }

    public List getOrderProduct() {
        return orderProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel order = (OrderModel) o;
        return orderID == order.orderID && Objects.equals(orderCustomer, order.orderCustomer)
                && Objects.equals(orderProduct, order.orderProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, orderCustomer, orderProduct);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", orderCustomer='" + orderCustomer + '\'' +
                ", orderProduct=" + orderProduct +
                '}';
    }
}
