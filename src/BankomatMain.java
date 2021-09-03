/*
Du ska skapa ett system för OOP-bankens olika bankkontor där kunder kan starta konton, avsluta konton som är
tomma och skuldfria. Kunder ska kunna sätta in pengar, ta ut pengar. Men inte mer än 100 kr mindre än man har.
Skapa klasser för banken, konton, kunder. Skapa klassvariabler och metoder. Komponera klasserna så att varje
bankkontor har sina konton och sina kunder. Så att konton kan öppnas och stängas. Så att kunder kan ta ut och
sätta in pengar. Eller flytta pengar mellan konton.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class BankomatMain {

    public static void launch(ArrayList<Bank> banks){
        Scanner input = new Scanner(System.in);

        System.out.println("Välj din bank");
        // Loopar igenom alla banker i listan banks.
        for (int i = 0; i < banks.size(); i++) {
            System.out.println(i + ". " + banks.get(i).getName());
        }
        // Användaren matar in vilken bank att hantera konton hos.
        int userChosenBank = input.nextInt();
        System.out.println();

        boolean run = true;
        while(run){
            System.out.println(banks.get(userChosenBank));
            System.out.println("1. Skapa kund");
            System.out.println("2. Skapa konto");
            System.out.println("3. Ta bort konto");
            System.out.println("4. Sätt in pengar");
            System.out.println("5. Ta ut pengar");
            System.out.println("6. Överför pengar");
            System.out.println("7. Återgå till startmeny");
            System.out.println("8. Avsluta");
            int userChosenAction = input.nextInt();

            int id;
            int accountId;
            // switch med cases 1-5 styrs av vad användaren har valt att utföra
            switch (userChosenAction){
                case 1:
                    // Skapar ny kund hos den valda banken.
                    banks.get(userChosenBank).createNewCustomer();
                    break;
                case 2:
                    System.out.println("\nAnge kundid: ");
                    id = input.nextInt();
                    // Kontrollerar att kunden med givet kundid finns hos banken.
                    if(!banks.get(userChosenBank).createCustomerAccount(id)){
                        System.out.println("Kontot kunde inte skapas. Kontrollera kundid och kontots id.\n");
                    }
                    break;
                case 3:
                    System.out.println("\nAnge kundid: ");
                    id = input.nextInt();
                    System.out.println("Ange kontots id: ");
                    accountId = input.nextInt();
                    // Försöker radera kontot med givet kontoid hos kunden givet kundid.
                    if(!banks.get(userChosenBank).deleteCustomerAccount(id, accountId)){
                        System.out.println("Kontot kunde inte raderas. Kontrollera kundid, kontots id och att " +
                                           "saldot är tomt och skuldfritt.\n");
                    }
                    break;
                case 4:
                    System.out.println("\nAnge kundid: ");
                    id = input.nextInt();
                    System.out.println("Ange kontots id: ");
                    accountId = input.nextInt();
                    System.out.println("Ange summa att sätta in: ");
                    double depositAmount = input.nextDouble();
                    // Sätter in given summa på kontot med givet kontoid hos kunden med given kundid om det finns.
                    if(!banks.get(userChosenBank).deposit(id, accountId, depositAmount)){
                        System.out.println("Kontot kunde inte hittas. Kontrollera kundid och kontots id.\n");
                    }
                    break;
                case 5:
                    System.out.println("\nAnge kundid: ");
                    id = input.nextInt();
                    System.out.println("Ange kontots id: ");
                    accountId = input.nextInt();
                    System.out.println("Ange summa att ta ut: ");
                    double withdrawAmount = input.nextDouble();
                    // Tar ut given summa från kontot med givet kontoid hos kunden med given kundid om kontot
                    // och täckning finns.
                    if(!banks.get(userChosenBank).withdraw(id, accountId, withdrawAmount)){
                        System.out.println("Kontot kunde inte hittas eller saknar täckning. " +
                                           "Kontrollera kundid, kontots id och saldo.\n");
                    }
                    break;
                case 6:
                    System.out.println("\nAnge kundid: ");
                    id = input.nextInt();
                    System.out.println("Ange kontots id: ");
                    accountId = input.nextInt();
                    System.out.println("Ange summa: ");
                    double transferAmount = input.nextDouble();
                    // Kontrollerar att kund, konto och täckning finns.
                    if(!banks.get(userChosenBank).withdraw(id, accountId, transferAmount)){
                        System.out.println("Kontot kunde inte hittas eller saknar täckning. Kontrollera" +
                                           "kundid, kontots id och saldo.\n");
                    }
                    else{
                        // Listan med banker presenteras, användaren anger vilken bank pengarna ska överföras till
                        System.out.println("Välj bank att överföra till: ");
                        for (int i = 0; i < banks.size(); i++) {
                            System.out.println(i + ". " + banks.get(i).getName());
                        }
                        int transferTargetBank = input.nextInt();
                        System.out.println("Ange mottagarens kundid");
                        int transferTargetId = input.nextInt();
                        System.out.println("Ange mottagarens kontoid");
                        int transferTargetAccountId = input.nextInt();
                        // Kontrollerar att mottagaren och mottagarkonto finns.
                        if(!banks.get(transferTargetBank).deposit(transferTargetId, transferTargetAccountId,
                                                                  transferAmount)){
                            System.out.println("Kontot kunde inte hittas. Kontrollera kundid och kontots id.\n");
                            // Återför pengarna om inte mottagaren kunde hittas.
                            banks.get(userChosenBank).deposit(id, accountId, transferAmount);
                        }
                        else{
                            System.out.println("Överförningen lyckades.\n");
                        }
                    }
                    break;
                case 7:
                    launch(banks);
                    break;
                case 8:
                    run = false;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        // Skapar och lägger till objekt av klassen Bank i lista
        ArrayList<Bank> banks = new ArrayList<Bank>();
        banks.add(new Bank("Swedbank"));
        banks.add(new Bank("Nordea"));
        banks.add(new Bank("Avanza"));
        launch(banks);
    }
}