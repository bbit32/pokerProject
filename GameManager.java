// =======================
// File: GameManager.java
// =======================

import java.util.*;

public class GameManager {
    private List<Player> players;
    private StandardDeck deck;
    private List<StandardCard> communityCards;
    private int pot;
    private int currentBet;
    private int dealerPosition;

    public GameManager(List<Player> players) {
        this.players = players;
        this.deck = new StandardDeck();
        this.communityCards = new ArrayList<>();
        this.pot = 0;
        this.currentBet = 0;
        this.dealerPosition = 0;
    }

    public void playRound() {
        deck.shuffleDeck();
        communityCards.clear();
        pot = 0;
        currentBet = 0;

        for (Player player : players) {
            player.setHand(Arrays.asList(deck.getNextCard(), deck.getNextCard()));
            player.setFolded(false);
        }

        printAllHands();
        bettingRound();
        if (activePlayers().size() == 1) {
            declareWinner();
            return;
        }

        dealCommunityCards(3);
        System.out.println("Flop: " + communityCards);
        bettingRound();
        if (activePlayers().size() == 1) {
            declareWinner();
            return;
        }

        dealCommunityCards(1);
        System.out.println("Turn: " + communityCards);
        bettingRound();
        if (activePlayers().size() == 1) {
            declareWinner();
            return;
        }

        dealCommunityCards(1);
        System.out.println("River: " + communityCards);
        bettingRound();

        declareWinner();
    }

    private void dealCommunityCards(int count) {
        for (int i = 0; i < count; i++) {
            communityCards.add(deck.getNextCard());
        }
    }

    private void bettingRound() {
        currentBet = 0;
        Scanner scanner = new Scanner(System.in);
        for (Player player : players) {
            if (player.isFolded()) continue;

            System.out.println("\nPlayer " + player.getName() + "'s turn");
            System.out.println("Your cards: " + player.getHand());
            System.out.println("Community: " + communityCards);
            System.out.println("Chips: " + player.getChips());
            System.out.println("Current bet to call: " + currentBet);

            while (true) {
                System.out.print("Choose action (check/call/raise/fold): ");
                String action = scanner.nextLine().toLowerCase();

                if (action.equals("fold")) {
                    player.setFolded(true);
                    break;
                } else if (action.equals("check")) {
                    if (currentBet == 0) break;
                    else System.out.println("You can't check. You must call or raise.");
                } else if (action.equals("call")) {
                    if (player.getChips() >= currentBet) {
                        player.setChips(player.getChips() - currentBet);
                        pot += currentBet;
                        break;
                    } else {
                        System.out.println("Not enough chips. Folding.");
                        player.setFolded(true);
                        break;
                    }
                } else if (action.equals("raise")) {
                    System.out.print("Enter raise amount: ");
                    int raise;
                    try {
                        raise = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Try again.");
                        continue;
                    }
                    if (raise > 0 && player.getChips() >= currentBet + raise) {
                        currentBet += raise;
                        player.setChips(player.getChips() - currentBet);
                        pot += currentBet;
                        break;
                    } else {
                        System.out.println("Invalid raise. Try again.");
                    }
                } else {
                    System.out.println("Invalid action.");
                }
            }
        }
        System.out.println("Total pot: " + pot);
    }

    private void declareWinner() {
        List<Player> active = activePlayers();

        if (active.size() == 1) {
            Player winner = active.get(0);
            winner.setChips(winner.getChips() + pot);
            System.out.println("\n" + winner.getName() + " wins the pot of " + pot + " (everyone else folded).\n");
            return;
        }

        Player bestPlayer = null;
        EvaluatedHand bestHand = null;

        for (Player player : active) {
            ArrayList<StandardCard> totalCards = new ArrayList<>();
            totalCards.addAll(player.getHand());
            totalCards.addAll(communityCards);

            EvaluatedHand eval = HandEvaluator.evaluateFullHand(totalCards);
            System.out.println(player.getName() + " has " + eval.getDescription());

            if (bestHand == null || eval.compareTo(bestHand) > 0) {
                bestHand = eval;
                bestPlayer = player;
            }
        }

        if (bestPlayer != null) {
            bestPlayer.setChips(bestPlayer.getChips() + pot);
            System.out.println("\n" + bestPlayer.getName() + " wins the pot of " + pot + " with " + bestHand.getDescription() + "!\n");
        }
    }

    private List<Player> activePlayers() {
        List<Player> active = new ArrayList<>();
        for (Player p : players) {
            if (!p.isFolded()) active.add(p);
        }
        return active;
    }

    private void printAllHands() {
        System.out.println("\n--- Pocket Cards ---");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getHand());
        }
    }
}
