import acm.program.ConsoleProgram;
import java.util.ArrayList;

public class GameManager extends ConsoleProgram {

	private ArrayList<Player> players;
	private StandardDeck deck;

	public void run() {
		testPokerHands();
	}
	/*
	 * println("=== Poker Game Started ===");
	 * 
	 * deck = new StandardDeck();
	 * 
	 * int numPlayers = readInt("Enter number of players: "); players = new
	 * ArrayList<Player>();
	 * 
	 * 
	 * for (int i = 1; i <= numPlayers; i++) { String name =
	 * readLine("Enter name for Player " + i + ": "); int balance =
	 * readInt("Enter starting balance for " + name + ": "); players.add(new
	 * Player(name, balance)); }
	 * 
	 * // Step 4: Deal all cards first dealPocketCards();
	 * 
	 * // Step 5: Show each player's cards one at a time for (Player p : players) {
	 * showCardsWhenReady(p); }
	 * 
	 * // Step 6: Print each player's status (without revealing cards) for (Player p
	 * : players) { println(p.getStatus()); }
	 * 
	 * println("=== Game Ready ==="); }
	 */

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

	public void testPokerHands() {
		ArrayList<StandardCard> testHand = new ArrayList<>();

		// Example: Taest for a Straight Flush (5♠ 6♠ 7♠ 8♠ 9♠)
		testHand.add(new StandardCard(5, "Spades"));
		testHand.add(new StandardCard(7, "Spades"));
		testHand.add(new StandardCard(7, "Hearts"));
		testHand.add(new StandardCard(8, "Spades"));
		testHand.add(new StandardCard(10, "Spades"));
		testHand.add(new StandardCard(2, "Hearts"));
		testHand.add(new StandardCard(14, "Diamonds"));

		println("Testing hand: " + testHand);

		if (HandEvaluator.isRoyalFlush(testHand)) {
			println(" Royal Flush detected!");
		} else if (HandEvaluator.isStraightFlush(testHand)) {
			println("Straight Flush detected!");
		} else if (HandEvaluator.isFlush(testHand)) {
			println(" Flush detected!");
		} else if (HandEvaluator.isStraight(testHand)) {
		} else if (HandEvaluator.isFourOfAKind(testHand)) {
			println(" Four of a kind detected!");
		} else if (HandEvaluator.isThreeOfAKind(testHand)) {
			println(" Three of a kind detected!");
		}else if (HandEvaluator.isTwoPair(testHand)) {
			println(" Two Pair detected!");
		} else if (HandEvaluator.isPair(testHand)) {
			println(" Pair detected!");

		}
	}

}
