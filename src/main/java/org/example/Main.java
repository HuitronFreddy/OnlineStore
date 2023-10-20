package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //I think we need 2 array list one for the inventory and one for the cart- bimal
        // i wasnt sure whos creating the arraylist for items so ij= just made it- bimal
        ArrayList<Product> inventoryList = new ArrayList<>();
        ArrayList<Product> cartItems = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream("src/main/resources/products.csv");

            Scanner scanner = new Scanner(fis);
            scanner.nextLine();//this will skip the next line
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] inventory = line.split("\\|");
                Product newInventoryList = new Product(inventory[0],inventory[1],Double.parseDouble(inventory[2]),inventory[3]);
                inventoryList.add(newInventoryList);
            }


        }
        catch (FileNotFoundException exe){
            System.out.println("your file is not there");
        }
        //this is the code for the home screen
        boolean isMakingSelection = true;
        while (isMakingSelection) {
            System.out.printf("Hello! Welcome to the Online Store. %n What would you like to do? " +
                    "Please select a number. %n");
            System.out.println("1. Display Products.");
            System.out.println("2. Display Cart.");
            System.out.println("3. Exit Store.");
            try {
                Scanner scanner = new Scanner(System.in);
                int homeScanner = scanner.nextInt();

                switch(homeScanner){
                    case 1:
                        displayProduct(inventoryList, cartItems);
                        //displayProducts here
                        break;
                    case 2:
                        displayCart(inventoryList);
                        //displayCart here
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }
                isMakingSelection = false;
            }
            catch(Exception ex){

                System.out.println("Please enter a valid choice.");
            }
        }
        
    }
    //Display Product Method
    public static void displayProduct(ArrayList<Product> inventory, ArrayList<Product> cartItems) {
        for (Product list : inventory) {
            System.out.println(list);
        }
        Scanner scanner = new Scanner(System.in);
        boolean isMakingSelection = true;
        while (isMakingSelection) {
            for (Product list : inventory) {
                System.out.println(list);
            }
            System.out.println("Please enter a number \n 1> Search or Filter \n 2> Add product to cart \n 3> Go Back to the home Screen");
            int displayProductOptions = scanner.nextInt();
            Product addProduct = null;
            switch (displayProductOptions) {
                case 1:
                    //search or filter method here
                    break;
                case 2:
                    //add product to cart method
                    scanner.nextLine(); 
                    System.out.println("Enter the name of the product you want to add to the cart:");
                    String userInput = scanner.nextLine();
                    boolean productFound = false; //setting product found variable will change to true if userInput matches product by using if statement 

                    for (Product product : inventory) {
                        //if userInput matches a product, display information and prompt next set of questions
                        if (product != null && product.getProductName().equalsIgnoreCase(userInput)) {
                            System.out.println("\nHere is the product matching the information given:");
                            System.out.println(product);
                            System.out.println("1> Add item to cart\n2> Main menu");
                            productFound = true;
                            break; // Exit the loop after finding a matching product
                        }
                    }
                    if (!productFound) {
                        System.out.println("Product not found in the inventory.");
                    } else {
                        int choice = scanner.nextInt();
                        if (choice == 1) {
                            cartItems.add(addProduct); // Add the product to the cart
                            System.out.println("Product added to the cart. \n");
                        }
                        else{
                            System.out.println("You are returning to the main menu.");
                            return;
                        }
                    }
                    break;
                case 3:
                    System.out.println("You are returning to the main menu.");
                    return;
            }
        }
    }
    //method for display cart - bimal
    public static void displayCart(ArrayList<Product> cartItem) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter \n 1> Check Out \n 2> Remove Product from the cart \n 3> Go Back to the home Screen");
        int userInput = scanner.nextInt();
        switch (userInput) {
            case 1:
                double sum = 0;
                for (Product items : cartItem) {
                    sum += items.getPrice();
                }
                System.out.println("Total Amount: " + sum);
                cartItem.clear();
                break;
            case 2:
                String userItemName = scanner.nextLine();
                for (Product items : cartItem) {
                    if (userItemName.equalsIgnoreCase(items.getProductName())) {
                        System.out.println("Removing " + items.getProductName() + " from the cart");
                        cartItem.remove(items);
                    }

                }
                System.out.println("Your item name is not there enter the correct item");
                break;
            case 3:
                System.out.println("You are returning to the main menu.");
                return;
        }
    }
}
