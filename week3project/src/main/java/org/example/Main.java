package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

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
}