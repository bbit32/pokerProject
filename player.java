import java.util.ArrayList;

public class Player {
    private String name;
    private int balance;
    private ArrayList<StandardCard> pocketCards;
    private boolean isInGame;
    private boolean hasCards;
    private int bet;
    private boolean action;
    private int cash;

    public Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.pocketCards = new ArrayList<StandardCard>();
        this.isInGame = true;
        this.hasCards = !pocketCards.isEmpty();
        this.cash = balance;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void addToBalance(int amount) {
        balance += amount;
    }

    public void subtractFromBalance(int amount) {
        balance -= amount;
    }

    public ArrayList<StandardCard> getPocketCards() {
        return pocketCards;
    }

    public boolean isInGame() {
        return isInGame;
    }

    public void setIsInGame(boolean inGame) {
        isInGame = inGame;
    }

    public boolean hasCards() {
        return hasCards;
    }

    public void resetHand() {
        pocketCards.clear();
        hasCards = false;
        resetBet();
    }

    public void resetBet() {
        bet = 0;
        action = hasCards && cash == 0;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public boolean isAllIn() {
        return hasCards && (balance == 0);
    }

    public void payCash(int amount) {
        if (amount > balance) {
            throw new IllegalStateException("Player asked to pay more than they have!");
        }
        balance -= amount;
    }

    public String getStatus() {
        String cards;
        if (pocketCards.size() >= 2) {
            cards = pocketCards.get(0).toString() + " and " + pocketCards.get(1).toString();
        } else {
            cards = "Not enough cards";
        }

        return "Player " + name + " has $" + balance + ", In game: " + isInGame;
    }
}
