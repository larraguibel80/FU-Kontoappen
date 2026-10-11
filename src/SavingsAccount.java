/** A savings account that can earn interest. */
public class SavingsAccount extends Account {
    private final double interestRate; // 0.02 means 2 percent

    public SavingsAccount(String owner, double startBalance, double interestRate) {
        super(owner, startBalance);
        if (!Double.isFinite(interestRate) || interestRate < 0 || interestRate > 1) {
            throw new IllegalArgumentException("Räntan måste vara mellan 0% och 100%.");
        }
        this.interestRate = interestRate;
    }

    public double getInterestRate() { return interestRate; }

    public void applyInterest() {
        deposit(getBalance() * interestRate);
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.printf("  Kontotyp: Sparkonto | Ränta: %.2f %% %n", interestRate * 100);
    }
}
