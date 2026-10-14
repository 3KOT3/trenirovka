package Java2;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    // добавляет продукт; если уже есть — увеличивает количество
    public void addProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            int current = products.get(product);
            products.put(product, current + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    // общая стоимость: цена * количество по каждой позиции, затем сумма
    public double getTotalPrice() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    // общее количество единиц товара в корзине
    public int getProductCount() {
        int count = 0;
        for (int quantity : products.values()) {
            count += quantity;
        }
        return count;
    }
}