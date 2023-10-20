package org.example;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Reading the file and method for searching


public class Main {
    public static void main(String[] args) {

        try {
            FileInputStream fis = new FileInputStream("src/main/resources/products.csv");

            Scanner scanner = new Scanner(fis);

            ArrayList<Product> inventoryList = new ArrayList<>();

            //Skip the header line
            scanner.nextLine();

            String[] inventory;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                inventory = line.split("\\|");

                String sku = inventory[0];
                String productName = inventory[1];
                String department = inventory[3];

                double price = Double.parseDouble(inventory[2]);

                Product newProduct = new Product(sku, productName, price, department);


            }

            for (Product item : inventoryList) {
                System.out.printf("SKU: %s Product Name: %s Price: $%.2f Department: %s %n", item.getSku(), item.getProductName(), item.getPrice(), item.getDepartment());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

    // method for searching products- user input a product and the information prints out

    public static void searchProduct(ArrayList<Product> inventory, Scanner scanner) {
        System.out.println("Which product would you like to search?");
        String userInputProduct = scanner.nextLine();
        userInputProduct = userInputProduct.toLowerCase();

        boolean found = false;

        for (Product item : inventory) {
            if (item.getProductName().toLowerCase().equals(userInputProduct)) {

                System.out.println("The product is in the directory");
                System.out.printf("SKU: %s Product Name: %s Price: $%.2f Department: %s %n", item.getSku(), item.getProductName(), item.getPrice(), item.getDepartment());

                found = true;
                break;

            } else {
                System.out.println("The product is not in the directory");

            }

        }
    }
}

