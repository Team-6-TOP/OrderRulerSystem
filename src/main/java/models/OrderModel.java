package models;

import Enums.OrderCategory;

import java.util.List;
import java.util.Objects;

public class OrderModel {
    private final int orderID;
    private String orderCustomer;
    private List orderProduct;
    private OrderCategory orderCategory;

    public OrderModel(int orderID, String orderCustomer, List orderProduct, OrderCategory orderCategory) {
        this.orderID = orderID;
        this.orderCustomer = orderCustomer;
        this.orderProduct = orderProduct;
        this.orderCategory = orderCategory;
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

    public OrderCategory getOrderCategory() {
        return orderCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel that = (OrderModel) o;
        return orderID == that.orderID && Objects.equals(orderCustomer, that.orderCustomer)
                && Objects.equals(orderProduct, that.orderProduct) && orderCategory == that.orderCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, orderCustomer, orderProduct, orderCategory);
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
