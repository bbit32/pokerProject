import acm.program.ConsoleProgram;
import java.util.ArrayList;

public class GameManager extends ConsoleProgram {

    private ArrayList<Player> players;
    private StandardDeck deck;
    private int dealerPosition = 0;
    private int totalPot = 0;
    private ArrayList<StandardCard> communityCards;

    public void run() {
        println("=== Poker Game Started ===");

        int numPlayers = readInt("Enter number of players: ");
        players = new ArrayList<Player>();

        for (int i = 1; i <= numPlayers; i++) {
            String name = readLine("Enter name for Player " + i + ": ");
            int balance = readInt("Enter starting balance for " + name + ": ");
            players.add(new Player(name, balance));
        }

        while (true) {
            println("\n=== New Round Starting ===");
            deck = new StandardDeck();
            totalPot = 0;
            communityCards = new ArrayList<>();
            dealPocketCards();

            for (Player p : players) {
                showCardsWhenReady(p);
            }

            for (Player p : players) {
                println(p.getStatus());
            }

            println("Dealer is: " + players.get(dealerPosition).getName());
            println("Small Blind: " + players.get(getNextPosition(dealerPosition)).getName());
            println("Big Blind: " + players.get(getNextPosition(getNextPosition(dealerPosition))).getName());

            bettingRound("Pre-Flop");

            communityCards.add(deck.getNextCard());
            communityCards.add(deck.getNextCard());
            communityCards.add(deck.getNextCard());
            displayCommunityCards("Flop");
            bettingRound("Flop");

            communityCards.add(deck.getNextCard());
            displayCommunityCards("Turn");
            bettingRound("Turn");

            communityCards.add(deck.getNextCard());
            displayCommunityCards("River");
            bettingRound("River");

            dealerPosition = getNextPosition(dealerPosition);

            String next = readLine("Press [Enter] to continue or type 'quit' to end game: ");
            if (next.equalsIgnoreCase("quit")) break;
        }

        println("=== Game Over ===");
    }

    private void dealPocketCards() {
        for (Player p : players) {
            ArrayList<StandardCard> cards = new ArrayList<StandardCard>();
            cards.add(deck.getNextCard());
            cards.add(deck.getNextCard());
            p.resetHand();
            p.getPocketCards().addAll(cards);
        }
    }

    public void showCardsWhenReady(Player player) {
        readLine("\n" + player.getName() + ", press [Enter] when you're ready to see your cards...");
        for (int i = 0; i < 100; i++) println("");

        println("Your cards:");
        for (StandardCard card : player.getPocketCards()) {
            println(card.toString());
        }

        readLine("Press [Enter] to end your turn and hide your cards...");
        for (int i = 0; i < 100; i++) println("");
    }

    private void bettingRound(String stage) {
        println("\n-- " + stage + " Betting Round --");
        for (Player p : players) {
            if (!p.isInGame()) continue;
            String action = readLine(p.getName() + " (Balance: $" + p.getBalance() + "), Bet or Fold? (b/f): ");
            if (action.equalsIgnoreCase("f")) {
                p.setIsInGame(false);
                println(p.getName() + " folds.");
            } else {
                int bet = readInt("Enter bet amount: ");
                p.payCash(bet);
                p.setBet(bet);
                totalPot += bet;
                println(p.getName() + " bets $" + bet);
            }
        }
        println("Total Pot after " + stage + ": $" + totalPot);
    }

    private void displayCommunityCards(String stage) {
        println("\n== " + stage + " Community Cards ==");
        for (StandardCard card : communityCards) {
            println(card);
        }
    }

    private int getNextPosition(int currentPos) {
        return (currentPos + 1) % players.size();
    }
}
