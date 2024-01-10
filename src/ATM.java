import java.awt.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class ATM {
    private Scanner scan;
    private Customer customer;
    private Account bank;
    private TransactionHistory history;

    public ATM() {
        scan = new Scanner(System.in);
        history = new TransactionHistory();
        System.out.println(ConsoleUtility.GREEN + "Welcome to The APCSA Atm! \uD83C\uDFE7" + ConsoleUtility.RESET);
    }

    public void start() {
        accountSetUp();
        pinSetUp();
        menu();
        goodBye();
    }

    private void accountSetUp() {
        System.out.print(ConsoleUtility.GREEN + "Please Enter your first name \uD83D\uDCB0: " + ConsoleUtility.RESET);
        String fname = scan.nextLine();
        System.out.print(ConsoleUtility.GREEN + "Please Enter your last name \uD83D\uDCB0: " + ConsoleUtility.RESET);
        String lname = scan.nextLine();
        customer = new Customer(fname, lname);
        System.out.println();
    }

    private void pinSetUp() {
        System.out.print("You currently do not have a pin, please enter a 4 digit pin \uD83D\uDCB3: ");
        int pin = scan.nextInt();
        scan.nextLine();
        customer.setPin(pin);
        bank = new Account(customer);
        System.out.println("Thank you! and welcome.");
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }

        ConsoleUtility.clearScreen();
    }

    private void menu() {
        System.out.println();
        int correctPin = 0;
        int selection = 0;
        System.out.print(ConsoleUtility.RED + "Please enter your pin \uD83D\uDCB3: " + ConsoleUtility.RESET);
        correctPin = scan.nextInt();
        scan.nextLine();
        while (!customer.correctPin(correctPin)) {
            System.out.println(ConsoleUtility.RED + "Incorrect Pin!" + ConsoleUtility.RESET);
            System.out.print(ConsoleUtility.RED + "Please enter your pin \uD83D\uDCB3: " + ConsoleUtility.RESET);
            correctPin = scan.nextInt();
            scan.nextLine();
        }
        if (customer.correctPin(correctPin)) {
            try {
                Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
            } catch (Exception e) {
                System.out.println("error");
            }

            ConsoleUtility.clearScreen();
            String str = (ConsoleUtility.BLUE + "1. Withdraw money" + "\n" + "2. Deposit money" + "\n" + "3. Transfer money between accounts " + "\n" + "4. Get account balances" + "\n" + "5. Get transaction history" + "\n" + "6. Change PIN" + "\n" + "7. Exit " + ConsoleUtility.RESET);
            while (selection != 7) {
                System.out.println(str);
                System.out.print(ConsoleUtility.CYAN + "Please pick one: " + ConsoleUtility.RESET);
                selection = scan.nextInt();
                scan.nextLine();
                if (selection == 1) {
                    withdrawMoney();
                    contiune();
                }
                if (selection == 2) {
                    depositMoney();
                    contiune();
                }
                if (selection == 3) {
                    transferMoney();
                    contiune();
                }
                if (selection == 4) {
                    accountBalance();
                    contiune();
                }
                if (selection == 5) {
                    transactionHistory();
                    contiune();
                }
                if (selection == 6) {
                    changePin();
                    contiune();
                }
            }
        }
    }

    private void withdrawMoney() {
        int money = 0;
        String account = "";
        System.out.print("Please enter an amount: ");
        money = scan.nextInt();
        scan.nextLine();
        int amount = money;
        if (money % 5 == 0) {
            System.out.print("Checkings or Savings?: ");
            account = scan.nextLine();
            if (account.equals("checkings")){
                if (bank.getCheckingsAccount() < money){
                    System.out.println("insufficient funds!");
                }else{
                    bank.withdrawMoney(account, money);
                    System.out.print("How many 5$ bills would you like to withdraw?: ");
                    int fives = scan.nextInt();
                    scan.nextLine();
                    amount = amount - (fives * 5);
                    System.out.print("How many 20$ bills would you like to withdraw?: ");
                    int twenty = scan.nextInt();
                    scan.nextLine();
                    while (amount < (twenty * 20)){
                        System.out.print("You can't withdraw that amount in 20s. Please try again: ");
                        twenty = scan.nextInt();
                        scan.nextLine();
                    }
                    history.accountTransaction("Withdrew " + money + " dollars from " + account + " account");
                }
            }
            if (account.equals("savings")){
                if (bank.getSavingsAccount() < money){
                    System.out.println("insufficient funds!");
                }else{
                    bank.withdrawMoney(account, money);
                    System.out.print("How many 5$ bills would you like to withdraw?: ");
                    int fives = scan.nextInt();
                    scan.nextLine();
                    amount = amount - (fives * 5);
                    System.out.print("How many 20$ bills would you like to withdraw?: ");
                    int twenty = scan.nextInt();
                    scan.nextLine();
                    if (amount % 20 == 0){
                        amount = amount - (twenty * 20);
                    }else{
                        System.out.println("You can't withdraw that amount in 20s.");
                    }
                    history.accountTransaction("Withdrew " + money + " dollars from " + account + " account");
                }
            }
        }
        System.out.println();
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }

        ConsoleUtility.clearScreen();
    }
    private void depositMoney(){
        double money = 0;
        String account = "";
        System.out.print("Please enter an amount: ");
        money = scan.nextDouble();
        scan.nextLine();
        System.out.print("Checkings or Savings?: ");
        account = scan.nextLine();
        while (!account.equals("checkings") && !account.equals("savings")) {
            System.out.print("Invalid Option! Please try again: ");
            account = scan.nextLine();
        }
        bank.addMoney(account, money);
        history.accountTransaction("Added " + money + " dollars from " + account + " account");
        System.out.println( "$" + money + " successfully deposited to " + account +  " account");

        System.out.println();
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }

        ConsoleUtility.clearScreen();
    }
    private void transferMoney(){
        int money = 0;
        String account = "";
        System.out.print("Please enter an amount: ");
        money = scan.nextInt();
        scan.nextLine();
        if (money % 5 == 0) {
            System.out.print("Checkings or Savings?: ");
            account = scan.nextLine();
            if (account.equals("checkings")){
                if (bank.getCheckingsAccount() < money){
                    System.out.println("insufficient funds!");
                }else{
                    bank.withdrawMoney(account, money);
                    bank.addMoney("savings", money);
                    history.accountTransaction("Transfer " + money + " dollars from " + account + " account to savings account");
                    System.out.println( "$" + money + " successfully transferred from savings to checkings account");
                }
            }
            if (account.equals("savings")){
                if (bank.getSavingsAccount() < money){
                    System.out.println("insufficient funds!");
                }else{
                    bank.withdrawMoney(account, money);
                    bank.addMoney("checkings", money);
                    history.accountTransaction("Transfer " + money + " dollars from " + account + " account to checkings account");
                    System.out.println( "$" + money + " successfully transferred from checkings to savings account");
                }
            }
        }
        System.out.println();
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }

        ConsoleUtility.clearScreen();
    }
    private void accountBalance(){
        String str = (ConsoleUtility.WHITE + "Account Owner: " + customer.fullName() + "\n" + ConsoleUtility.RESET);
        str += (ConsoleUtility.WHITE +"Checkings Account: $" + bank.getCheckingsAccount() + "\n"+ ConsoleUtility.RESET);
        str += (ConsoleUtility.WHITE +"Savings Account: $" + bank.getSavingsAccount()+ ConsoleUtility.RESET);
        System.out.println(str);
        System.out.println();
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }

        ConsoleUtility.clearScreen();    }
    private void transactionHistory(){
        ArrayList<String> transaction = history.getTransaction();
        int count = 1;
        for (int i = 0; i < transaction.size(); i++){
            System.out.println(ConsoleUtility.PURPLE + count + ". " + transaction.get(i) + ConsoleUtility.RESET);
            count++;
        }
        System.out.println();
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }

        ConsoleUtility.clearScreen();
    }
    private void changePin(){
        System.out.print(ConsoleUtility.RED + "Please enter a new 4 digit pin: " + ConsoleUtility.RESET);
        int pin = scan.nextInt();
        scan.nextLine();
        customer.setPin(pin);
        System.out.println(ConsoleUtility.BLUE + "Pin Updated" + ConsoleUtility.RESET);
        history.securityTransaction("Changed Account Pin.");

        System.out.println();
        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }

        try {
            Thread.sleep(2000);  // 2000 milliseconds, or 2 seconds
        } catch (Exception e) {
            System.out.println("error");
        }

        ConsoleUtility.clearScreen();
    }
    private void goodBye(){
        System.out.println(ConsoleUtility.WHITE + "Goodbye and Thank you for using my ATM ❤\uFE0F" + ConsoleUtility.RESET);
    }
    private void contiune(){
        String ans = "";
        System.out.print("Would you like to do anything else?: ");
         ans = scan.nextLine();
        while (!ans.equals("yes") && !ans.equals("no")){
            System.out.println("Invalid Option! Try again: ");
            ans = scan.nextLine();
        }
        if (ans.equals("yes")){
            System.out.print(ConsoleUtility.RED + "Please enter your pin: " + ConsoleUtility.RESET);
           int correctPin = scan.nextInt();
            scan.nextLine();
            while (!customer.correctPin(correctPin)) {
                System.out.println(ConsoleUtility.RED + "Incorrect Pin!" + ConsoleUtility.RESET);
                System.out.print(ConsoleUtility.RED + "Please enter your pin: " + ConsoleUtility.RESET);
                correctPin = scan.nextInt();
                scan.nextLine();
            }
        }if (ans.equals("no")){
            goodBye();
            System.exit(0);
        }
    }
}