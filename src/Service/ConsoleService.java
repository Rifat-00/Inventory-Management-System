package Service;

import Entity.Product;

public interface ConsoleService {
    public void addNewProduct();
    public void loadProductsFromCSV();
    public void appendProductToCSV(Product product);
    public void run();
    public void showMenu();
}
