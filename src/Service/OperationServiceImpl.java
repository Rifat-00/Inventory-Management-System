package Service;

import Entity.Product;

import java.util.*;

public class OperationServiceImpl implements OperationService {
    private HashMap<String, Product> products;
    private HashSet<String> categories;
    private int productCounter;


    public OperationServiceImpl() {
        this.products = new HashMap<>();
        this.categories = new HashSet<>();
        this.productCounter = 0;
    }

    @Override
    public void createProduct(Product product) {
        categories.add(product.getCategory());
        productCounter = productCounter + 1;
        String id = String.format("PD%d",productCounter);
        product.setId(id);
        products.put(id, product);
        System.out.println("Created product " + product);
    }

    @Override
    public void removeProduct(String id) {
        // check if id exist in the hashmap
        if(!products.containsKey(id)){
            System.out.println("Product " + id + " not found");
        }
        products.remove(id);
        System.out.println("Removed product " + id);
    }


    @Override
    public void getAllProducts() {
        List<Product> productList = new ArrayList<>();
        if(products.isEmpty()){
            System.out.println("No products found");
        }
        else{
            System.out.println("Found " + products.size() + " products");
            System.out.println("---------------------------------------------------------------");
            System.out.printf("%-10s %-20s %-10s %-10s %-15s%n","ID","Name","Price","Quantity","Category");
            System.out.println("---------------------------------------------------------------");
            for(Product product : products.values()){
                System.out.printf("%-10s %-20s %-10.2f %-10s %-15s%n",
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getQuantity());
            }
            System.out.println("---------------------------------------------------------------");
        }
    }

    @Override
    public void getProductById(String id) {
        if(!products.containsKey(id)){
            System.out.println("Product " + id + " not found");
        }

        products.get(id);
    }

    @Override
    public void updateQuantity(String id, int quantity) {
        if(!products.containsKey(id)){
            System.out.println("Product " + id + " not found");
        }else {
            Product product = products.get(id);
            product.setQuantity(quantity);
            System.out.println("Updated product " + product);
        }
    }

    @Override
    public Optional<List<Product>> getProductByCategory(String categoryName){
        List<Product> productList = new ArrayList<>();
        if(!categories.contains(categoryName)){
            System.out.println("Category " + categoryName + " not found");
        }
        for(Product product : products.values()){
            if(product.getCategory().equals(categoryName)){
                productList.add(product);
            }
        }
        return Optional.of(productList);
    }

    @Override
    public int getQuantity(String id) {
        if(!products.containsKey(id)){
            System.out.println("Product " + id + " not found");
        }
        else{
           Product product = products.get(id);
           return product.getQuantity();
        }
        return 0;
    }


}
