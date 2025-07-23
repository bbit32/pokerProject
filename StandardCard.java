
import acm.program.*;
import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class StandardCard extends GraphicsProgram {

	
    private static final String CARD_FOLDER = "cards/"; 
    private static final int CARD_WIDTH = 80;
    private static final int CARD_HEIGHT = 120;

    private ArrayList<String> deck;
    private GImage[] cardImages = new GImage[5];
    private JButton dealButton;

    public void init() {
        setSize(600, 300); // Set canvas size
        dealButton = new JButton("Deal");
        add(dealButton, SOUTH);
        addActionListeners();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dealButton) {
            dealCards();
        }
    }

    private void dealCards() {
        generateDeck();
        Collections.shuffle(deck);

        // Remove any previously drawn cards
        for (int i = 0; i < cardImages.length; i++) {
            if (cardImages[i] != null) {
                remove(cardImages[i]);
            }
        }

        // Show 5 new cards
        for (int i = 0; i < 5; i++) {
            String cardName = deck.get(i); // gives the card 
            String path = CARD_FOLDER + cardName + ".png";

            try {
                GImage card = new GImage(path);
                card.setSize(CARD_WIDTH, CARD_HEIGHT);
                card.setLocation(40 + i * (CARD_WIDTH + 10), 80);
                add(card);
                cardImages[i] = card;
            } catch (Exception ex) {
                println("Error loading: " + path);
            }
        }
    }

    private void generateDeck() {
        String[] suits = {"H", "D", "C", "S"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        deck = new ArrayList<>();

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + suit);
            }
        }
    }
}
