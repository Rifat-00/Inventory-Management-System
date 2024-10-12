package Service;

import Entity.Product;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleServiceImpl implements ConsoleService {
    private static final String CSV_FILE_PATH = "./src/DB/db.csv";
    private  final OperationService operationService;
    private static Scanner scanner = new Scanner(System.in);

    public ConsoleServiceImpl(OperationService operationService) {
        this.operationService = operationService;
    }


    @Override
    public void loadProductsFromCSV() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if(data.length == 5){
                    String productId = data[0];
                    String productName = data[1];
                    String productPrice = data[2];
                    String productQuantity = data[3];
                    String productCategory = data[4];

                    // create a new product from these data
                    Product product = new Product();
                    product.setId(productId);
                    product.setName(productName);
                    product.setPrice(Double.parseDouble(productPrice));
                    product.setQuantity(Integer.parseInt(productQuantity));
                    product.setCategory(productCategory);
                    operationService.createProduct(product);
                }
                System.out.println("Product loaded from csv");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void appendProductToCSV(Product product){
        try(FileWriter fileWriter = new FileWriter(CSV_FILE_PATH,true)){
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);

            // append the product in CSV format
            printWriter.println(product.getId()+","+product.getName()+","+product.getPrice()+","+product.getCategory());
            printWriter.flush();
            System.out.println("Added product to CSV successfully");
        }catch (IOException e){
            e.printStackTrace();
        }
    }




    @Override
    public void addNewProduct(){
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.println("Enter product quantity: ");
        int quantity = scanner.nextInt();
        System.out.println("Enter product category: ");
        String category = scanner.nextLine();
        scanner.close();
        Product product = new Product(name,price,quantity,category);
        operationService.createProduct(product);
        appendProductToCSV(product);

    }






    @Override
    public void run() {

        boolean keepRunning = true; // Control variable for the loop

        while (keepRunning) {

            // Load the menu items
            showMenu();

            // Get the operation from user
            int operationNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (operationNumber) {
                case 1:
                    addNewProduct();
                    break;
                // Add other cases for other operations here
                case 2:
                    System.out.println("Enter product id:");
                    String productId = scanner.nextLine();
                    operationService.removeProduct(productId);
                    break;
                case 3:
                    operationService.getAllProducts();
                    break;
                case 4:
                    System.out.println("Enter product id:");
                    String id = scanner.nextLine();
                    operationService.getProductById(id);
                    break;
                case 5:
                    System.out.println("Enter product id:");
                    String id_ = scanner.nextLine();
                    System.out.println("CurrentQuantity: " + operationService.getQuantity(id_));
                    System.out.println("Enter new Quantity");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    operationService.updateQuantity(id_, quantity);
                    break;
                case 6:
                    System.out.println("Enter product category:");
                    String category = scanner.nextLine();
                    Optional<List<Product>> productList = operationService.getProductByCategory(category);
                    if (productList.isEmpty()) {
                        System.out.println("No product found");
                    } else {
                        for (Product product : productList.get()) {
                            System.out.println(product);
                        }
                    }
                    break;
                case 7:
                    keepRunning = false; // Exit the loop and terminate the program
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid operation number. Please try again.");
            }

            // After each operation, ask the user if they want to return to the menu or exit
            if (keepRunning) {
                keepRunning = handlePostOperationMenu();
            }
        }
    }

    @Override
    public void showMenu() {
        System.out.println("Press the respective key to manage operations:");
        System.out.println("1. Add product");
        System.out.println("2. Delete product");
        System.out.println("3. Show all products");
        System.out.println("4. Get product by ID");
        System.out.println("5. Update product quantity");
        System.out.println("6. Get products by category");
        System.out.println("7. Exit the application");
        System.out.print("Select Operation: ");
    }

    private boolean handlePostOperationMenu() {
        System.out.println("\nPress 'M' to return to the menu or 'E' to exit the application: ");
        String choice = scanner.nextLine().trim().toUpperCase();

        return switch (choice) {
            case "M" -> true; // Go back to the menu
            case "E" -> {
                System.out.println("Exiting the application...");
                yield false;
            }
            default -> {
                System.out.println("Invalid input. Returning to the menu by default.");
                yield true;
            }
        };
    }
}
