package models.Enums;

public enum OrderCategory {
    NEW,
    PROCESSING,
    COMPLETED,
    CANCELLED;

    public static boolean isCorrectCategory(String orderCategoryName) {
        try {
            OrderCategory.valueOf(orderCategoryName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
