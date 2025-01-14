package models.Enums;

public enum CustomerType {
    NEW,
    REGULAR,
    VIP;

    /**
     * Проверяет правильно ли выбран тип покупателя
     *
     * @param customerTypeName
     * @return истина, если категория верная, а ложь, если неправильно выбранная
     */
    public static boolean isCorrectType(String customerTypeName) {
        try {
            CustomerType.valueOf(customerTypeName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
