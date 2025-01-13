package models.Enums;

public enum CustomerType {
    NEW ("Новый покупатель"),
    REGULAR ("Постоянный покупатель"),
    VIP ("VIP-покупатель");

    private final String type;

    CustomerType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public static CustomerType fromType (String type) {
        for (CustomerType types : CustomerType.values()) {
            if (types.getType().equalsIgnoreCase(type)) {
                return types;
            }
        } throw new IllegalArgumentException("Такого типа не существует");
    }
}
