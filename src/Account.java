public class Account {
    private int accountId;
    private double balance;
    private static int idGen = 100000;
    private double debtLimit;

    /*
    Konstruktor. Tilldelar värden för accountId och balance.
     */
    public Account(){
        this.accountId = idGen;
        idGen++;
        this.balance = 0;
        this.debtLimit = -100;
        System.out.println("Account created. ID: " + this.accountId + "\n");
    }

    public double getBalance() {
        return this.balance;
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountId() {
        return this.accountId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                '}';
    }

    /*
    Anropas för att ta ut pengar från kontot. Kontrollerar
    med en if else sats om det finns tillräckligt med
    täckning på kontot. Returnerar true om det finns
    täckning och uttaget kan göras, annars false.
     */
    public boolean withdraw(double amount){
        if ((this.getBalance() - this.debtLimit) < amount){
            return false;
        }
        else{
            setBalance(getBalance() - amount);
            System.out.println("Uttag/överföring: " + amount + " kr");
            System.out.println("Balans: " + getBalance() + " kr\n");
            return true;
        }
    }

    /*
    Anropas för att sätta in given summa på kontot.
     */
    public void deposit(double amount){
        setBalance(getBalance() + amount);
        System.out.println("Insättning/avbruten överföring: " + amount + " kr");
        System.out.println("Ny balans: " + getBalance() + " kr\n");
    }
}
