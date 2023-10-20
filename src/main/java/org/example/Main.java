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
                        //displayProducts here
                        break;
                    case 2:
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
    //Freddy's method i just created this so i wont be confused while making mine you can delete this or write on this-Bimal
    public static void displayProduct(ArrayList<Product> inventory) {
        for (Product list : inventory) {
            System.out.println(list);
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
