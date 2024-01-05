public class Customer {
    private String firstName;
    private String lastName;
    private int pin;


    public Customer(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        pin = 0;
    }

    public int getPin(){
        return pin;
    }
    public void setPin(int newPin){
        pin = newPin;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

    public boolean correctPin(int enteredPin){
        return pin == enteredPin;
    }
    public String fullName(){
        String name1 = getFirstName();
        String name2 = getLastName();
        return (name1 + " " + name2);
    }
}
