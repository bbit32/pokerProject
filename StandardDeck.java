import java.util.ArrayList;
import java.util.Collections;

public class StandardDeck {
    private ArrayList<StandardCard> deck;

    public StandardDeck() {
        deck = new ArrayList<StandardCard>();
        reset();
        shuffleDeck();
    }

    public void reset() {
        deck.clear();
        String[] suits = { "Hearts", "Diamonds", "Spades", "Clubs" };

        for (String suit : suits) {
            for (int value = 2; value <= 14; value++) {
                deck.add(new StandardCard(value, suit));
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public StandardCard getNextCard() {
        if (deck.isEmpty()) {
            return null;
        }
        return deck.remove(deck.size() - 1);
    }

    public int getRemainingCardCount() {
        return deck.size();
    }

    public String showDeck() {
        return deck.toString();
    }
}
