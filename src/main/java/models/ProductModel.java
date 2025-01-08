package models;

import java.util.Objects;

/**
 * Модель товара.
 */
public class ProductModel {
    private final int id;
    private String name;
    private double price;
    private ProductCategory category;

    /**
     * Конструктор товара.
     *
     * @param id       Идентификатор товара.
     * @param name     Название товара.
     * @param price    Цена товара.
     * @param category Категория товара.
     */
    public ProductModel(int id, String name, double price, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel that = (ProductModel) o;
        return id == that.id && Double.compare(that.price, price) == 0 &&
                name.equals(that.name) && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, category);
    }

    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', price=%.2f, category='%s'}", id, name, price, category);
    }
}
