import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StandardDeck {
    private List<StandardCard> deck;

    public StandardDeck() {
        deck = new ArrayList<>();
        reset();
        shuffleDeck();
    }

    public void reset() {
        deck.clear();
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };

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
        if (deck.isEmpty()) return null;
        return deck.remove(deck.size() - 1);
    }

    public int getRemainingCardCount() {
        return deck.size();
    }
}
