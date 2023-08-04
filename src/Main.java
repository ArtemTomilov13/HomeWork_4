import java.util.Arrays;

class Customer {
    private String fullName;
    private int age;
    private String phoneNumber;

    public Customer(String fullName, int age, String phoneNumber) {
        this.fullName = fullName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Customer{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class Order {
    private Customer customer;
    private Product product;
    private int quantity;

    public Order(Customer customer, Product product, int quantity) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}

class CustomerException extends Exception {
    public CustomerException(String message) {
        super(message);
    }
}

class ProductException extends Exception {
    public ProductException(String message) {
        super(message);
    }
}

class AmountException extends Exception {
    public AmountException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        Customer[] customers = {
                new Customer("John Doe", 30, "1234567890"),
                new Customer("Jane Smith", 25, "9876543210")
        };

        Product[] products = {
                new Product("Product 1", 10.99),
                new Product("Product 2", 20.99),
                new Product("Product 3", 30.99),
                new Product("Product 4", 40.99),
                new Product("Product 5", 50.99)
        };

        Order[] orders = new Order[5];

        try {
            orders[0] = makePurchase(customers, products, 0, 0, 1);
            orders[1] = makePurchase(customers, products, 0, 10, 2);
            orders[2] = makePurchase(customers, products, 1, 3, 3);
            orders[3] = makePurchase(customers, products, 1, 6, -1);
            orders[4] = makePurchase(customers, products, 2, 4, 4);
        } catch (CustomerException | ProductException | AmountException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Total number of purchases: " + countPurchases(orders));
    }

    public static Order makePurchase(Customer[] customers, Product[] products, int customerIndex, int productIndex, int quantity)
            throws CustomerException, ProductException, AmountException {
        if (customerIndex < 0 || customerIndex >= customers.length) {
            throw new CustomerException("Invalid customer index");
        }

        if (productIndex < 0 || productIndex >= products.length) {
            throw new ProductException("Invalid product index");
        }

        if (quantity <= 0 || quantity > 100) {
            throw new AmountException("Invalid quantity");
        }

        Customer customer = customers[customerIndex];
        Product product = products[productIndex];

        return new Order(customer, product, quantity);
    }

    public static int countPurchases(Order[] orders) {
        int count = 0;
        for (Order order : orders) {
            if (order != null) {
                count++;
            }
        }
        return count;
    }
}