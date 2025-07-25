import java.util.ArrayList;
import java.util.Collections;

public class StandardDeck {
	private ArrayList<StandardCard> deck = new ArrayList<>();

	public StandardDeck() {
		reset();
		shuffleDeck();
	}

	public void reset() {
		this.deck.clear();
		String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
		for (String suit : suits) {
			for (int i = 2; i <= 14; i++) {
				deck.add(new StandardCard(i, suit));
			}
		}
	}

	public void shuffleDeck() {
		Collections.shuffle(this.deck);  // Uses built-in shuffle
	}

	public StandardCard getNextCard() {
		if (this.deck.isEmpty()) {
			return null;
		}
		return this.deck.remove(this.deck.size() - 1);
	}

	public int getRemainingCardCount() {
		return this.deck.size();
	}

	// Optional: display deck without exposing private field
	public String showDeck() {
		return this.deck.toString();
	}

	public static void main(String[] args) {
		StandardDeck sd = new StandardDeck();
		System.out.println("Full shuffled deck:");
		System.out.println(sd.showDeck());

		System.out.println("\nDrawing 4 cards:");
		System.out.println(sd.getNextCard());
		System.out.println(sd.getNextCard());
		System.out.println(sd.getNextCard());
		System.out.println(sd.getNextCard());

		System.out.println("\nRemaining cards in deck: " + sd.getRemainingCardCount());
	}
}
