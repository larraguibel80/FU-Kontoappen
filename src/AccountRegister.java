import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Owns and manages all accounts; account creation goes through this factory. */
public class AccountRegister {

    private final List<Account> accounts = new ArrayList<>();

    public Account createAccount(String owner, double startBalance) {

        if (findAccountByOwner(owner) != null) {
            throw new IllegalArgumentException(
                    "Det finns redan ett konto med detta namn.");
        }

        Account account = new Account(owner, startBalance);
        accounts.add(account);
        return account;
    }

    public SavingsAccount createSavingsAccount(
            String owner,
            double startBalance,
            double interestRate) {

        if (findAccountByOwner(owner) != null) {
            throw new IllegalArgumentException(
                    "Det finns redan ett konto med detta namn.");
        }

        SavingsAccount account =
                new SavingsAccount(owner, startBalance, interestRate);

        accounts.add(account);
        return account;
    }

    public void printAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("Det finns inga konton ännu.");
            return;
        }

        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("%d. ", i + 1);
            accounts.get(i).printInfo();
        }
    }

    public Account findAccountByOwner(String owner) {
        if (owner == null) {
            return null;
        }

        for (Account account : accounts) {
            if (account.getOwner()
                    .equalsIgnoreCase(owner.trim())) {
                return account;
            }
        }

        return null;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }
}