import java.util.ArrayList;

public class Bank {
    private String name;
    private ArrayList<Customer> customers;
    private static int idGen;

    /*
    Konstruktorn skapar ArrayList för att förvara objekt av klassen Customer.
     */
    public Bank(String name){
        this.name = name;
        idGen = 1000;
        this.customers = new ArrayList<Customer>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", customers=" + customers +
                '}';
    }

    /*
    Anropas för att skapa ny kund.
     */
    public void createNewCustomer(){
        this.customers.add(new Customer(idGen));
        idGen++;
    }

    /*
    Skapar nytt konto om en kund med matchande kundid hittas.
    Returnerar då true, annars false.
     */
    public boolean createCustomerAccount(int customerId){
        for(Customer customer : this.customers){
            if (customer.getId() == customerId){
                customer.createAccount();
                return true;
            }
        }
        return false;
    }

    /*
    Anropar metod för att radera konto hos kunden med givet kundid.
    Returnerar true/false beroende på om raderingen lyckas eller inte.
     */
    public boolean deleteCustomerAccount(int customerId, int accountId){
        for(Customer customer : this.customers){
            if (customer.getId() == customerId){
                return customer.deleteAccount(accountId);
            }
        }
        return false;
    }

    /*
    Anropar metod för att sätta in pengar på kontot med givet kontoid
    tillhörandes kunden med givet kundid. Returnerar true/false
    beroende på om insättningen lyckas eller inte.
     */
    public boolean deposit(int customerId, int accountId, double amount){
        for (Customer customer : this.customers){
            if (customer.getId() == customerId){
                return customer.deposit(accountId, amount);
            }
        }
        return false;
    }

    /*
    Anropar metod för att ta ut pengar från kontot med givet kontoid
    tillhörandes kunden med givet kundid. Returnerar true/false
    beroende på om insättningen lyckas eller inte.
     */
    public boolean withdraw(int customerId, int accountId, double amount){
        for (Customer customer : this.customers){
            if (customer.getId() == customerId){
                return customer.withdraw(accountId, amount);
            }
        }
        return false;
    }
}
