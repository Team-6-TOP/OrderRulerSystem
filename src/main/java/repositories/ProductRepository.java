package repositories;

import models.ProductModel;
import exceptions.ProductNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final String filePath = "products.txt";

    public void save(ProductModel product) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(product.getId() + ";" + product.getName() + ";" + product.getPrice() + ";" + product.getCategory());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProductModel> loadAll() {
        List<ProductModel> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                String category = parts[3];
                products.add(new ProductModel(id, name, price, category));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public ProductModel findById(int id) {
        for (ProductModel product : loadAll()) {
            if (product.getId() == id) {
                return product;
            }
        }
        throw new ProductNotFoundException("Товар с ID: " + id + " не найден.");
    }
}
