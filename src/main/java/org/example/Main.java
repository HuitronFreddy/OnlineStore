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

            inventoryList.add(new Product("AV1051", "JBL Bluetooth Speaker", 89.95, "Audio Video"));
            inventoryList.add(new Product("AV1312", "Mini 1000 Lumens Projector", 149.95, "Audio Video"));
            inventoryList.add(new Product("AV1412", "XLR Podcast Cardiod Mic", 44.99, "Audio Video"));
            inventoryList.add(new Product("CP2012", "Desktop PC Computer Intel Core i5", 139.00, "Computers"));
            inventoryList.add(new Product("CP2123", "Wired Backlit Keyboard", 21.99, "Computers"));
            inventoryList.add(new Product("CP2154", "RGB Wireless Gaming Mouse", 67.45, "Computers"));
            inventoryList.add(new Product("GM1032", "Telestrations Board Game", 17.99, "Games"));
            inventoryList.add(new Product("GM1075", "Battleship Board Game", 12.99, "Games"));
            inventoryList.add(new Product("GM1148", "Retro Handheld Arcade", 24.45, "Games"));
            inventoryList.add(new Product("PW1255", "USB C to A Cable", 13.95, "Electronics"));
            inventoryList.add(new Product("PW1341", "Solar Powered Battery Charger", 19.99, "Electronics"));
            inventoryList.add(new Product("PW1431", "Wireless Charger iPhone", 12.99, "Electronics"));

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

