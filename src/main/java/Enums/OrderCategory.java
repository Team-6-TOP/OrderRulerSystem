package Enums;

public enum OrderCategory {

    /**
     * Перечисление категорий заказов
     */

    NEW,
    PROCESSING,
    COMPLETED,
    CANCELLED;


    /**
     * Метод для проверки корректности заказа
     * @param orderCategoryName
     * @return
     */

    public static boolean isCorrectCategory(String orderCategoryName) {
        try {
            OrderCategory.valueOf(orderCategoryName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
