/** Represents a bank account. Account data is encapsulated. */
public class Account {
    private final String owner;
    private double balance;

    public Account(String owner, double startBalance) {
        if (owner == null || owner.trim().isEmpty()) {
            throw new IllegalArgumentException("Ägarens namn får inte vara tomt.");
        }
        if (!Double.isFinite(startBalance) || startBalance < 0) {
            throw new IllegalArgumentException("Startbeloppet måste vara minst 0.");
        }
        this.owner = owner.trim();
        this.balance = startBalance;
    }

    public String getOwner() { return owner; }
    public double getBalance() { return balance; }

    public boolean deposit(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Insättningen måste vara större än 0.");
            return false;
        }
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            return false;
        }

        if (amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }

    public void printInfo() {
        System.out.printf("Ägare: %s | Saldo: %.2f kr%n", owner, balance);
    }
}
