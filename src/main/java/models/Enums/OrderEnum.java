package models.Enums;

public enum OrderEnum {
    NEW("Заказ успешно создан! Скоро начнём собирать..."),
    PROCESSING("Ваш заказ в пути!"),
    COMPLETED("Ваш заказ доставлен!"),
    CANCELLED("Вы отменили заказ.");

    private final String orderEnum;

    OrderEnum(String orderEnum) {
        this.orderEnum = orderEnum;
    }
}
