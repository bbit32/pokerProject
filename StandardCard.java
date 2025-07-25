public class StandardCard {
    private int value;
    private String suit;
    private String color;

    public StandardCard(int value, String suit) {
        this.value = value;
        this.suit = suit;
        this.color = (suit.equals("Hearts") || suit.equals("Diamonds")) ? "Red" : "Black";
    }

    public int getValue() { return value; }
    public String getSuit() { return suit; }
    public String getColor() { return color; }

    public String convertValueToName() {
        switch (value) {
            case 11: return "Jack";
            case 12: return "Queen";
            case 13: return "King";
            case 14: return "Ace";
            default: return String.valueOf(value);
        }
    }

    public String toString() {
        return convertValueToName() + " of " + suit;
    }
}
