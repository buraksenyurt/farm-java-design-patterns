package Bonus.ActorModelBasic;

class BankAccountActor extends Actor {

    private double balance = 0.0;

    @Override
    protected void onReceive(Message message) {
        if (message instanceof Deposit deposit) {
            balance += deposit.amount();
        } else if (message instanceof Withdraw withdraw) {
            if (balance >= withdraw.amount()) {
                balance -= withdraw.amount();
            }
        } else if (message instanceof Balance) {
            System.out.println("Current balance: " + balance);
        }
    }
}
