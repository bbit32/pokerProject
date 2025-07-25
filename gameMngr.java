import acm.program.ConsoleProgram;
import java.util.ArrayList;

public class GameManager extends ConsoleProgram {

    private ArrayList<Player> players;
    private StandardDeck deck;

    public void run() {
        println("=== Poker Game Started ===");

        deck = new StandardDeck();

        int numPlayers = readInt("Enter number of players: ");
        players = new ArrayList<Player>();

       
        for (int i = 1; i <= numPlayers; i++) {
            String name = readLine("Enter name for Player " + i + ": ");
            int balance = readInt("Enter starting balance for " + name + ": ");
            players.add(new Player(name, balance));
        }

        // Step 4: Deal all cards first
        dealPocketCards();

        // Step 5: Show each player's cards one at a time
        for (Player p : players) {
            showCardsWhenReady(p);
        }

        // Step 6: Print each player's status (without revealing cards)
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

            p.resetHand();
            p.getPocketCards().addAll(cards);
        }
    }
    
    public void showCardsWhenReady(Player player) {
        readLine("\n" + player.getName() + ", press [Enter] when you're ready to see your cards...");
        for (int i = 0; i < 100; i++) {
            println("");
        }

        println("Your cards:");
        for (StandardCard card : player.getPocketCards()) {
            println(card.toString());
            
           
        }
        
        
        


        readLine("Press [Enter] to end your turn and hide your cards...");
        for (int i = 0; i < 100; i++) {
            println("");
        }


     // Simulate clearing the screen to hide cards
        
    }

}
