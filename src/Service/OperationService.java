package Service;

import Entity.Product;

import java.util.List;
import java.util.Optional;

public interface OperationService {
    public void createProduct(Product product);
    public void removeProduct(String id);
    public void getAllProducts();
    public void getProductById(String id);
    public Optional<List<Product>> getProductByCategory(String categoryName);
    public int getQuantity(String id);
    public void updateQuantity(String id, int quantity);

}
