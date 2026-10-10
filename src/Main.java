import java.util.Scanner;

/** Console user interface for Kontoappen. */
public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final AccountRegister register = new AccountRegister();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Välj ett alternativ: ");
            switch (choice) {
                case 1 -> createAccount(false);
                case 2 -> register.printAllAccounts();
                case 3 -> changeBalance(true);
                case 4 -> changeBalance(false);
                case 5 -> createAccount(true);
                case 6 -> applySavingsInterest();
                case 7 -> { running = false; System.out.println("Programmet avslutas. Hej då!"); }
                default -> System.out.println("Ogiltigt val. Välj 1–7.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("=== KONTOAPPEN ===");
        System.out.println("1. Skapa vanligt konto");
        System.out.println("2. Lista konton");
        System.out.println("3. Sätt in pengar");
        System.out.println("4. Ta ut pengar");
        System.out.println("5. Skapa sparkonto");
        System.out.println("6. Beräkna ränta på sparkonton");
        System.out.println("7. Avsluta");
    }

    private void createAccount(boolean savings) {
        String owner = readNonEmpty("Ange kontoinnehavarens namn: ");
        double balance = readDouble("Ange startbelopp (kr): ");
        if (balance < 0) {
            System.out.println("Startbeloppet kan inte vara negativt.");
            return;
        }
        try {
            if (savings) {
                double rate = readDouble("Ange årlig ränta i procent (t.ex. 2 för 2%): ") / 100.0;
                register.createSavingsAccount(owner, balance, rate);
                System.out.println("Sparkontot skapades.");
            } else {
                register.createAccount(owner, balance);
                System.out.println("Kontot skapades.");
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Kunde inte skapa kontot: " + ex.getMessage());
        }
    }

    private void changeBalance(boolean deposit) {
        if (register.getAccounts().isEmpty()) {
            System.out.println("Skapa ett konto först.");
            return;
        }
        String owner = readNonEmpty("Ange kontoinnehavarens namn: ");
        Account account = register.findAccountByOwner(owner);
        if (account == null) {
            System.out.println("Hittade inget konto med den ägaren.");
            return;
        }
        double amount = readDouble("Ange belopp (kr): ");
        boolean success = deposit ? account.deposit(amount) : account.withdraw(amount);
        if (success) System.out.println(deposit ? "Insättningen genomfördes." : "Uttaget genomfördes.");
    }

    private void applySavingsInterest() {
        int count = 0;
        for (Account account : register.getAccounts()) {
            if (account instanceof SavingsAccount savings) {
                savings.applyInterest();
                count++;
            }
        }
        System.out.println("Ränta beräknad för " + count + " sparkonto(n).");
    }

    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.out.println("Fältet får inte vara tomt.");
                continue;
            }

            if (!value.matches("[a-zA-ZåäöÅÄÖ ]+")) {
                System.out.println("Ogiltigt namn. Ange endast bokstäver.");
                continue;
            }

            return value;
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try { return Integer.parseInt(value); }
            catch (NumberFormatException ex) { System.out.println("Ange ett heltal."); }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim().replace(',', '.');
            try {
                double number = Double.parseDouble(value);
                if (Double.isFinite(number)) return number;
            } catch (NumberFormatException ignored) { /* show validation below */ }
            System.out.println("Ange ett giltigt numeriskt belopp.");
        }
    }
}
