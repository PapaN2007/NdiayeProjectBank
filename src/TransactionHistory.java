import java.util.ArrayList;
public class TransactionHistory {
    ArrayList<String> transaction = new ArrayList<String>();
    private static int accCount = 1;
    private static int secCount = 1;


    public TransactionHistory(){
    }

    public void accountTransaction(String transactions){
        String str = "";
        if (accCount < 10){
            str = ("Transaction A000" + accCount + " " + transactions);
        } else {
            str = ("Transaction A00" + accCount + " " + transactions);
        }
        transaction.add(str);
        accCount++;
    }
    public void securityTransaction(String transactions){
        String str = "";
        if (secCount < 10){
            str = ("Transaction S000" + secCount + " " + transactions);
        } else {
            str = ("Transaction A00" + secCount + " " + transactions);
        }
        transaction.add(str);
        secCount++;
    }

    public ArrayList<String> getTransaction() {
        return transaction;
    }
}
