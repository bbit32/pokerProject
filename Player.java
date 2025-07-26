import java.util.List;

public class Player {
    private String name;
    private List<StandardCard> hand;
    private boolean folded;
    private int chips;

    public Player(String name, int chips) {
        this.name = name;
        this.chips = chips;
        this.folded = false;
    }

    public String getName() {
        return name;
    }

    public List<StandardCard> getHand() {
        return hand;
    }

    public void setHand(List<StandardCard> hand) {
        this.hand = hand;
    }

    public boolean isFolded() {
        return folded;
    }

    public void setFolded(boolean folded) {
        this.folded = folded;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public void adjustChips(int amount) {
        this.chips += amount;
    }
}
