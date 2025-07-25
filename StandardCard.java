public class StandardCard {
    private int value;
    private String suit;
    private String color;

    public StandardCard(int value, String suit) {
        this.value = value;
        this.suit = suit;

        // Determine card color based on suit
        if (suit.equals("Hearts") || suit.equals("Diamonds")) {
            this.color = "Red";
        } else if (suit.equals("Clubs") || suit.equals("Spades")) {
            this.color = "Black";
        } else {
            this.color = "Unknown";
        }
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getColor() {
        return color;
    }

    public String convertValueToName() {
        switch (value) {
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            case 10: return "Ten";
            case 11: return "Jack";
            case 12: return "Queen";
            case 13: return "King";
            case 14: return "Ace";
            default: return "Invalid";
        }
    }

    public String toString() {
        return convertValueToName() + " of " + suit;
    }
}
