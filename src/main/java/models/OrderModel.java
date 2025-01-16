package models;

import Enums.OrderCategory;

import java.util.List;
import java.util.Objects;

public class OrderModel {
    private final int orderID;
    private int orderCustomer;
    private List<ProductModel> orderProduct;
    private OrderCategory orderCategory;

    public OrderModel(int orderID, int orderCustomer,
                      List<ProductModel> orderProduct, OrderCategory orderCategory) {
        this.orderID = orderID;

        this.orderProduct = orderProduct;
        this.orderCategory = orderCategory;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getOrderCustomer() {
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
        return orderID == that.orderID && orderCustomer == that.orderCustomer
                && Objects.equals(orderProduct, that.orderProduct) && orderCategory == that.orderCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, orderCustomer, orderProduct, orderCategory);
    }

    @Override
    public String toString() {
        return "New order: " + "\nOrder ID: " + orderID + "\nCustomer ID: " + orderCustomer + "\nOrder type: "
                + orderCategory + "\nProduct ID: " + orderProduct
                + "\n------------------------------------------";
    }
}
