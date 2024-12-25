package exceptions;
//Для сдачи дз
/**
 * Исключение, выбрасываемое при отсутствии товара.
 */
public class ProductNotFoundException extends RuntimeException {
    /**
     * Конструктор исключения.
     *
     * @param message Сообщение об ошибке.
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}