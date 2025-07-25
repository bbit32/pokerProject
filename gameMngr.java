import acm.program.ConsoleProgram;
import java.util.ArrayList;

public class GameManager extends ConsoleProgram {

    private ArrayList<Player> players;
    private StandardDeck deck;

    public void run() {
        println("=== Poker Game Started ===");

        // Step 1: Create Deck
        deck = new StandardDeck();

        // Step 2: Ask how many players
        int numPlayers = readInt("Enter number of players: ");
        players = new ArrayList<Player>();

        // Step 3: Ask for each player's name and balance
        for (int i = 1; i <= numPlayers; i++) {
            String name = readLine("Enter name for Player " + i + ": ");
            int balance = readInt("Enter starting balance for " + name + ": ");
            players.add(new Player(name, balance, new ArrayList<StandardCard>()));
        }

        // Step 4: Deal pocket cards
        dealPocketCards();

        // Step 5: Print status for each player
        for (Player p : players) {
            println(p.getStatus());
        }

        println("=== Game Ready ===");
    }

    private void dealPocketCards() {
        for (Player p : players) {
            ArrayList<StandardCard> cards = new ArrayList<StandardCard>();
            cards.add(deck.getNextCard());
            cards.add(deck.getNextCard());

            p.resetHand();                     // clear any old cards
            p.getPocketCards().addAll(cards);  // add new cards
            for (Player p1 : players) {
                showCardsWhenReady(p1);
            }
        }
    }
    public void showCardsWhenReady(Player player) {
    	readLine("/n" +player.getName() +", press [Enter] when ready to see your cards");
    	println("Your cards:");
        for (StandardCard card : player.getPocketCards()) {
            println(card.toString());
        }
        readLine("Press [Enter] to end your turn and hide your cards...");
        
        for (int i = 0; i < 100; i++) {
            println("");
        }
    }
}
