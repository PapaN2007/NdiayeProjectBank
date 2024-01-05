public class Account {
    private double savingsAccount;
    private double checkingsAccount;
    private Customer customer;

    public Account() {
        savingsAccount = 0;
        checkingsAccount = 0;
        String accountName = customer.fullName();
    }
    public void addMoney(String account, double amount){
        if (account.equals("savings")){
            savingsAccount += amount;
        } else if(account.equals("checkings")){
            checkingsAccount += amount;
        }else{
            System.out.println("Invalid Option!");
        }

    }
    public void removeMoney(String account, double amount){
        if (account.equals("savings")){
            savingsAccount -= amount;
        } else if(account.equals("checkings")){
            checkingsAccount -= amount;
        }else{
            System.out.println("Invalid Option!");
        }
    }
    public double getSavingsAccount(){
        return savingsAccount;
    }
    public double getCheckingsAccount(){
        return checkingsAccount;
    }


}
