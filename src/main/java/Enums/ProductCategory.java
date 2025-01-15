package Enums;

/**
 * Перечисление категорий продукта.
 */
public enum ProductCategory {
    FOOD,
    ELECTRONICS,
    CLOTHING;

    /**
     * Проверяет, является ли категория правильной.
     *
     * @param categoryName Название категории.
     * @return true, если категория совпадает, false в случае если нет.
     */
    public static boolean isValidCategory(String categoryName) {
        try {
            ProductCategory.valueOf(categoryName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}