package org.example;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> inventoryList = new ArrayList<>();
        ArrayList<Product>  cartItems   = new ArrayList<>();

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
        boolean isMakingSelection = true;
        mainMenu: while (isMakingSelection) {
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
                        displayProduct(inventoryList,cartItems);
                        continue mainMenu;
                    case 2:
                        displayCart(cartItems);
                        continue mainMenu;
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
    public static void displayProduct(ArrayList<Product> inventory,ArrayList<Product> cartItems){
        Scanner scanner = new Scanner(System.in);

        for(Product list: inventory){
            System.out.println(list);
        }
        while(true){
            System.out.println("Enter the name, department of the product displayed or exit to go back to the main menu");
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("exit")){
                return;
            }
            boolean itemAdded = false;

            for(Product item: inventory) {

                if (item.getProductName().equalsIgnoreCase(userInput)) {
                    System.out.println("Here is the product matching the information given");
                    System.out.println(item);
                    System.out.println("1> add item \n2> Main menu");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            cartItems.add(item);
                            System.out.println("Added: " + item);
                            itemAdded = true;
                            return;

                        case 2:
                            return;
                    }
                }
            }
            if(!itemAdded){
                System.out.println("The item given does not match. ");
            }


        }

    }
    public static void displayCart(ArrayList<Product> cartItem){

        Scanner scanner = new Scanner(System.in);
        if (cartItem.isEmpty()){
            System.out.println("you don't have any items to be displayed");
            return;
        }
        else {
            while (true) {
                System.out.println("Enter \n 1> Check Out \n 2> Remove Product from the cart \n 3> Go Back to the home Screen");
                int userInput = scanner.nextInt();
                scanner.nextLine();


                switch (userInput) {
                    case 1:
                        double sum = 0;
                        for (Product items : cartItem) {
                            System.out.println(items);
                            sum += items.getPrice();
                        }
                        System.out.println("Total Amount: " + sum);
                        cartItem.clear();
                        System.exit(0);
                        break;
                    case 2:
                        Scanner cartScanner= new Scanner(System.in);
                        boolean isValid = false;
                        while(!isValid) {
                            System.out.println("Enter the product name you want to remove or exit to display cart");
                            String userItemName = cartScanner.nextLine();
                            for (Product items : cartItem) {
                                if (userItemName.equalsIgnoreCase(items.getProductName())) {
                                    System.out.println("Removing " + items.getProductName() + " from the cart");
                                    cartItem.remove(items);
                                    isValid = true;
                                    break;
                                }

                            }

                            if (userItemName.equalsIgnoreCase("exit")){
                                System.out.println("you are returning to the cart menu");
                                isValid=true;
                            }
                            if(!isValid) {
                                System.out.println("Your item name is not there enter the correct item");
                            }
                        }
                        break;
                    case 3:
                        System.out.println("You are returning to the main menu.");
                        return;
                }
            }
        }




    }



}