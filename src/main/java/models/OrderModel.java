package models;

import Enums.OrderCategory;

import java.util.List;
import java.util.Objects;

public class OrderModel {
    private final int orderID;
    private CustomerModel orderCustomer;
    private List<ProductModel> orderProduct;
    private OrderCategory orderCategory;

    public OrderModel(int orderID, CustomerModel orderCustomer,
                      List<ProductModel> orderProduct, OrderCategory orderCategory) {
        this.orderID = orderID;

        this.orderProduct = orderProduct;
        this.orderCategory = orderCategory;
    }

    public int getOrderID() {
        return orderID;
    }

    public CustomerModel getOrderCustomer() {
        return orderCustomer;
    }

    public List<ProductModel> getOrderProduct() {
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
        return orderID == that.orderID && orderCustomer.equals(that.orderCustomer)
                && orderProduct.equals(that.orderProduct) && orderCategory == that.orderCategory;
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
