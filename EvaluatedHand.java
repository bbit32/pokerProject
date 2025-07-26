public class EvaluatedHand implements Comparable<EvaluatedHand> {
    private final HandRank rank;
    private final int highCardValue;
    private final String description;

    public EvaluatedHand(HandRank rank, int highCardValue, String description) {
        this.rank = rank;
        this.highCardValue = highCardValue;
        this.description = description;
    }

    public HandRank getRank() {
        return rank;
    }

    public int getHighCardValue() {
        return highCardValue;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int compareTo(EvaluatedHand other) {
        if (this.rank != other.rank) {
            return this.rank.ordinal() - other.rank.ordinal();
        }
        return this.highCardValue - other.highCardValue;
    }
}
