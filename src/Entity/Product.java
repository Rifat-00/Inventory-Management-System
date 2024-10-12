package Entity;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String categoryName;
    public Product() {}
    public Product(String name, double price, int quantity, String categoryName) {
        if (name == null || name.isEmpty() || name.length() < 2) {
            throw new IllegalArgumentException("Invalid product name");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryName = categoryName;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty() || name.length() < 2) {
            throw new IllegalArgumentException("Invalid product name");
        }
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    public String getCategory() {
        return categoryName;
    }

    public void setCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
