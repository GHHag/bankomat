import java.util.ArrayList;

public class Customer{
    private int id;
    private ArrayList<Account> accounts;

    /*
    Konstruktorn skapar Arraylist för att förvara objekt av klassen Account.
    Variabeln id tilldelas givet värde för int id.
     */
    public Customer(int id){
        this.id = id;
        this.accounts = new ArrayList<Account>();
        System.out.println("Customer created. Customer ID: " + this.id + "\n");
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", accounts=" + accounts +
                '}';
    }

    /*
    Anropas för att skapa ett konto.
     */
    public void createAccount(){
        this.accounts.add(new Account());
    }

    /*
    Anropas för att radera kontot med given kontonummer.
    Loopar igenom alla kunden konton och undersöker om det finns
    ett med kontoid som matchar givet kontoid och tar isf bort det.
    Returnerar true om det lyckas, annars false.
     */
    public boolean deleteAccount(int accountId){
        for (Account account : this.accounts){
            if (account.getAccountId() == accountId && account.getBalance() == 0){
                this.accounts.remove(account);
                System.out.println("\n" + accountId + " borttaget\n");
                return true;
            }
        }
        return false;
    }

    /*
    Anropas för att göra en insättning på kontot med givet kontonummer.
    Loopar igenom alla kunden konton och undersöker om det finns
    ett med kontoid som matchar givet kontoid och sätter isf in given
    summa på det kontot. Returnerar true om det lyckas, annars false.
     */
    public boolean deposit(int accountId, double amount){
        for (Account account : this.accounts){
            if (account.getAccountId() == accountId){
                account.deposit(amount);
                return true;
            }
        }
        return false;
    }

    /*
    Anropas för att göra ett uttag från kontot med givet kontonummer.
    Loopar igenom alla kunden konton och undersöker om det finns
    ett med kontoid som matchar givet kontoid och försöker isf göra ett
    uttag på given summa från det kontot.
    Returnerar true om det lyckas, annars false.
     */
    public boolean withdraw(int accountId, double amount){
        for (Account account : this.accounts){
            if (account.getAccountId() == accountId){
                return account.withdraw(amount);
            }
        }
        return false;
    }
}