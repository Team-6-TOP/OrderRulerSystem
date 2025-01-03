package repositories;
//Для сдачи дз
import models.ProductModel;
import models.ProductCategory;
import exceptions.ProductNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для работы с данными товаров.
 */
public class ProductRepository {
    private final String fileName = "products.txt";

    /**
     * Сохраняет товар в файл.
     *
     * @param product Товар для сохранения.
     */
    public void save(ProductModel product) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            String productData = product.getId() + ";" +
                    product.getName() + ";" +
                    product.getPrice() + ";" +
                    product.getCategory().name() + System.lineSeparator();
            writer.write(productData);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении продукта: " + e.getMessage());
        }
    }

    /**
     * Загружает все товары из файла.
     *
     * @return Список всех товаров.
     */
    public List<ProductModel> loadAll() {
        List<ProductModel> products = new ArrayList<>();
        try {
            Path path = Paths.get(fileName);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    ProductCategory category = ProductCategory.valueOf(parts[3]);
                    products.add(new ProductModel(id, name, price, category));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке продуктов: " + e.getMessage());
        }
        return products;
    }

    /**
     * Находит товар по его ID.
     *
     * @param id Идентификатор товара.
     * @return Найденный товар.
     */
    public ProductModel findById(int id) {
        return loadAll().stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Товар с ID: " + id + " не найден."));
    }
}
