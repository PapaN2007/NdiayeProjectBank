import java.sql.SQLOutput;
import java.util.Scanner;
public class ATM {
    private Scanner scan;
    private Customer customer;
    private Account bank;

    public ATM() {
        scan = new Scanner(System.in);
        System.out.println("Welcome to The APCSA Atm!");
    }

    public void start() {
        accountSetUp();
        pinSetUp();
        menu();
        goodBye();
    }

    private void accountSetUp() {
        System.out.print("Please Enter your first name: ");
        String fname = scan.nextLine();
        System.out.print("Please Enter your last name: ");
        String lname = scan.nextLine();
        customer = new Customer(fname, lname);
        System.out.println();
    }

    private void pinSetUp() {
        System.out.print("You currently do not have a pin, please enter a 4 digit pin: ");
        int pin = scan.nextInt();
        scan.nextLine();
        customer.setPin(pin);
        bank = new Account(customer);
        System.out.println("Thank you! and welcome.");
    }

    private void menu() {
        System.out.println();
        int selection = 0;
        int correctPin = 0;
        System.out.print("Please enter your pin: ");
        correctPin = scan.nextInt();
        scan.nextLine();
        while (!customer.correctPin(correctPin)) {
            System.out.println("Incorrect Pin!");
            System.out.print("Please enter your pin: ");
            correctPin = scan.nextInt();
            scan.nextLine();
        }
        if (customer.correctPin(correctPin)) {
            String str = ("1. Withdraw money" + "\n" + "2. Deposit money" + "\n" + "3. Transfer money between accounts " + "\n" + "4. Get account balances" + "\n" + "5. Get transaction history" + "\n" + "6. Change PIN" + "\n" + "7. Exit ");
            while (selection != 7) {
                System.out.println(str);
                System.out.print("Please pick one: ");
                selection = scan.nextInt();
                scan.nextLine();
                if (selection == 1) {
                    withdrawMoney();
                }
                if (selection == 2) {
                    depositMoney();
                }
                if (selection == 3) {
                    transferMoney();
                }
                if (selection == 4) {
                    accountBalance();
                }
                if (selection == 5) {
                    transactionHistory();
                }
                if (selection == 6) {
                    changePin();
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
        if (money % 5 == 0) {
            System.out.print("Checkings or Savings?: ");
            account = scan.nextLine();
            if (account.equals("checkings")){
                if (bank.getCheckingsAccount() < money){
                    System.out.println("insufficient funds!");
                }else{
                    bank.withdrawMoney(account, money);
                }
            }
            if (account.equals("savings")){
                if (bank.getSavingsAccount() < money){
                    System.out.println("insufficient funds!");
                }else{
                    bank.withdrawMoney(account, money);
                }
            }
        }
        System.out.println();
    }
    private void depositMoney(){
        int money = 0;
        String account = "";
        System.out.print("Please enter an amount: ");
        money = scan.nextInt();
        scan.nextLine();
        System.out.print("Checkings or Savings?: ");
        account = scan.nextLine();
        bank.addMoney(account, money);
        System.out.println();
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
                }
            }
            if (account.equals("savings")){
                if (bank.getSavingsAccount() < money){
                    System.out.println("insufficient funds!");
                }else{
                    bank.withdrawMoney(account, money);
                    bank.addMoney("checkings", money);
                }
            }
        }
        System.out.println();
    }
    private void accountBalance(){
        String str = ("Account Owner: " + customer.fullName() + "\n");
        str += ("Checkings Account: $" + bank.getCheckingsAccount() + "\n");
        str += ("Savings Account: $" + bank.getSavingsAccount());
        System.out.println(str);
        System.out.println();
    }
    private void transactionHistory(){
    }
    private void changePin(){
        System.out.print("Please enter a new 4 digit pin: ");
        int pin = scan.nextInt();
        scan.nextLine();
        customer.setPin(pin);
        System.out.println();
    }
    private void goodBye(){
        System.out.println("Goodbye and Thank you for using my ATM");
    }
}