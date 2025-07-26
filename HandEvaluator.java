import java.util.*;

public class HandEvaluator {

    public static EvaluatedHand evaluateFullHand(List<StandardCard> cards) {
        if (cards.size() < 5) throw new IllegalArgumentException("Need at least 5 cards");

        List<List<StandardCard>> combinations = getFiveCardCombinations(cards);
        EvaluatedHand bestHand = null;

        for (List<StandardCard> hand : combinations) {
            EvaluatedHand current = evaluateFiveCardHand(hand);
            if (bestHand == null || current.compareTo(bestHand) > 0) {
                bestHand = current;
            }
        }

        return bestHand;
    }

    private static List<List<StandardCard>> getFiveCardCombinations(List<StandardCard> cards) {
        List<List<StandardCard>> result = new ArrayList<>();
        int n = cards.size();

        int[] indices = new int[5];
        for (indices[0] = 0; indices[0] < n - 4; indices[0]++) {
            for (indices[1] = indices[0] + 1; indices[1] < n - 3; indices[1]++) {
                for (indices[2] = indices[1] + 1; indices[2] < n - 2; indices[2]++) {
                    for (indices[3] = indices[2] + 1; indices[3] < n - 1; indices[3]++) {
                        for (indices[4] = indices[3] + 1; indices[4] < n; indices[4]++) {
                            List<StandardCard> combo = new ArrayList<>();
                            for (int i : indices) combo.add(cards.get(i));
                            result.add(combo);
                        }
                    }
                }
            }
        }

        return result;
    }

    private static EvaluatedHand evaluateFiveCardHand(List<StandardCard> hand) {
        hand.sort((a, b) -> b.getValue() - a.getValue());
        Map<Integer, Integer> valueCount = new HashMap<>();
        Map<String, List<StandardCard>> suits = new HashMap<>();

        for (StandardCard card : hand) {
            valueCount.put(card.getValue(), valueCount.getOrDefault(card.getValue(), 0) + 1);
            suits.computeIfAbsent(card.getSuit(), k -> new ArrayList<>()).add(card);
        }

        boolean flush = suits.values().stream().anyMatch(list -> list.size() >= 5);
        List<Integer> values = new ArrayList<>(valueCount.keySet());
        Collections.sort(values, Collections.reverseOrder());

        // Straight and Straight Flush detection
        List<Integer> straightValues = new ArrayList<>(new TreeSet<>(values));
        if (straightValues.contains(14)) straightValues.add(1); // Ace-low straight

        int straightHigh = 0;
        for (int i = 0; i < straightValues.size() - 4; i++) {
            int start = straightValues.get(i);
            boolean isStraight = true;
            for (int j = 1; j < 5; j++) {
                if (!straightValues.contains(start - j)) {
                    isStraight = false;
                    break;
                }
            }
            if (isStraight) {
                straightHigh = start;
                break;
            }
        }

        // Check for flush cards
        List<StandardCard> flushCards = null;
        for (List<StandardCard> suitCards : suits.values()) {
            if (suitCards.size() >= 5) {
                flushCards = new ArrayList<>(suitCards);
                flushCards.sort((a, b) -> b.getValue() - a.getValue());
                break;
            }
        }

        if (flushCards != null && straightHigh > 0) {
            Set<Integer> flushValues = new HashSet<>();
            for (StandardCard card : flushCards) {
                flushValues.add(card.getValue());
                if (card.getValue() == 14) flushValues.add(1);
            }

            boolean straightFlush = true;
            for (int i = 0; i < 5; i++) {
                if (!flushValues.contains(straightHigh - i)) {
                    straightFlush = false;
                    break;
                }
            }

            if (straightFlush) {
                return new EvaluatedHand(HandRank.STRAIGHT_FLUSH, straightHigh, "Straight Flush to " + name(straightHigh));
            }
        }

        // Frequency counts
        int four = 0, three = 0, pair1 = 0, pair2 = 0;
        for (int val : values) {
            int count = valueCount.get(val);
            if (count == 4 && four == 0) four = val;
            else if (count == 3 && three == 0) three = val;
            else if (count == 2) {
                if (pair1 == 0) pair1 = val;
                else if (pair2 == 0) pair2 = val;
            }
        }

        if (four > 0) {
            return new EvaluatedHand(HandRank.FOUR_OF_A_KIND, four, "Four of a Kind: " + name(four));
        }
        if (three > 0 && pair1 > 0) {
            return new EvaluatedHand(HandRank.FULL_HOUSE, three, "Full House: " + name(three) + " over " + name(pair1));
        }
        if (flushCards != null) {
            return new EvaluatedHand(HandRank.FLUSH, flushCards.get(0).getValue(), "Flush: High card " + flushCards.get(0));
        }
        if (straightHigh > 0) {
            return new EvaluatedHand(HandRank.STRAIGHT, straightHigh, "Straight to " + name(straightHigh));
        }
        if (three > 0) {
            return new EvaluatedHand(HandRank.THREE_OF_A_KIND, three, "Three of a Kind: " + name(three));
        }
        if (pair1 > 0 && pair2 > 0) {
            int highPair = Math.max(pair1, pair2);
            int lowPair = Math.min(pair1, pair2);
            return new EvaluatedHand(HandRank.TWO_PAIR, highPair, "Two Pair: " + name(highPair) + " and " + name(lowPair));
        }
        if (pair1 > 0) {
            return new EvaluatedHand(HandRank.ONE_PAIR, pair1, "One Pair: " + name(pair1));
        }

        return new EvaluatedHand(HandRank.HIGH_CARD, hand.get(0).getValue(), "High Card: " + hand.get(0));
    }

    private static String name(int value) {
        switch (value) {
            case 14: return "Ace";
            case 13: return "King";
            case 12: return "Queen";
            case 11: return "Jack";
            default: return String.valueOf(value);
        }
    }
}
