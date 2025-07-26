import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Poker Game!");
        System.out.print("Enter number of players (2-6): ");
        int playerCount;
        while (true) {
            try {
                playerCount = Integer.parseInt(scanner.nextLine());
                if (playerCount >= 2 && playerCount <= 6) break;
                else System.out.print("Please enter a number between 2 and 6: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine().trim();
            players.add(new Player(name.isEmpty() ? "Player" + i : name, 1000)); // Default 1000 chips
        }

        GameManager game = new GameManager(players);

        String choice;
        do {
            game.playRound();

            System.out.print("Play another round? (yes/no): ");
            choice = scanner.nextLine().toLowerCase();
        } while (choice.equals("yes") || choice.equals("y"));

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
