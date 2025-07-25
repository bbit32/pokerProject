import java.util.ArrayList;

public class Player {
    private String name;
    private int balance;
    private ArrayList<StandardCard> pockCards;
    private boolean isInGame;
    private boolean hasCards;
    private int bet;
    private boolean action;
    private int cash;

    // ✅ Correct constructor with ArrayList parameter
    public Player(String name, int balance, ArrayList<StandardCard> pockCards) {
        this.name = name;
        this.balance = balance;
        this.pockCards = pockCards;
        this.isInGame = true;
        this.hasCards = !pockCards.isEmpty();
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

    public ArrayList<StandardCard> getPockCards() {
        return pockCards;
    }

    public boolean isInGame() {
        return isInGame;
    }

    public void setIsInGame(boolean isInGame) {
        this.isInGame = isInGame;
    }

    public boolean hasCards() {
        return hasCards;
    }

    public void resetHand() {
        hasCards = false;
        pockCards.clear();
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
            throw new IllegalStateException("Player asked to pay more cash than they own!");
        }
        balance -= amount;
    }

    public String getStatus() {
        String cards = (pockCards.size() >= 2)
            ? pockCards.get(0) + " and " + pockCards.get(1)
            : "Not enough cards";
        return "Player " + name + " has a balance of $" + balance +
               ", pocket cards: " + cards + ", In game: " + isInGame;
    }

    public static void run(String[] args) {
        ArrayList<StandardCard> cards = new ArrayList<>();
        cards.add(new StandardCard(1, "Hearts"));
        cards.add(new StandardCard(13, "Spades"));

        Player player = new Player("Alice", 1000, cards);
        System.out.println(player.getStatus());
    }
}
