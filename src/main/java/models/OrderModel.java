package models;

import Enums.OrderCategory;

import java.util.List;
import java.util.Objects;

public class OrderModel {
    private final int orderID;
    private final int orderCustomer;
    private final List<Integer> productId;
    private final OrderCategory orderCategory;

    public OrderModel(int orderID, int orderCustomer,
                      List<Integer> productId, OrderCategory orderCategory) {
        this.orderID = orderID;
        this.orderCustomer = orderCustomer;
        this.productId = productId;
        this.orderCategory = orderCategory;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getOrderCustomer() {
        return orderCustomer;
    }

    public List<Integer> getProductId() {
        return productId;
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
                && productId.equals(that.productId) && orderCategory == that.orderCategory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, orderCustomer, productId, orderCategory);
    }


    /**
     * Метод возвращающий созданный заказ
     * @return
     */

    @Override
    public String toString() {
        return "\nOrder ID: " + orderID + "\nCustomer ID: " + orderCustomer + "\nOrder type: "
                + orderCategory + "\nProduct ID: " + productId
                + "\n------------------------------------------";
    }
}
