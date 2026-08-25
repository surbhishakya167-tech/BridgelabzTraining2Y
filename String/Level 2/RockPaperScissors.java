import java.util.Scanner;

public class RockPaperScissors {
    static String computerChoice() {
        int choice = (int) (Math.random() * 3);

        if (choice == 0) {
            return "Rock";
        } else if (choice == 1) {
            return "Paper";
        } else {
            return "Scissors";
        }
    }

    static String findWinner(String player, String computer) {
        if (player.equals(computer)) {
            return "Draw";
        }

        if ((player.equals("Rock") && computer.equals("Scissors")) ||
            (player.equals("Paper") && computer.equals("Rock")) ||
            (player.equals("Scissors") && computer.equals("Paper"))) {
            return "Player";
        }

        return "Computer";
    }

    static String[][] calculateStats(int playerWins, int computerWins, int games) {
        double playerPercentage = (playerWins * 100.0) / games;
        double computerPercentage = (computerWins * 100.0) / games;

        return new String[][]{
            {"Player", String.valueOf(playerWins), String.valueOf(playerPercentage)},
            {"Computer", String.valueOf(computerWins), String.valueOf(computerPercentage)}
        };
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int games = input.nextInt();
        int playerWins = 0;
        int computerWins = 0;

        for (int i = 1; i <= games; i++) {
            System.out.print("Enter Rock, Paper or Scissors: ");
            String player = input.next();

            String computer = computerChoice();
            String winner = findWinner(player, computer);

            if (winner.equals("Player")) {
                playerWins++;
            } else if (winner.equals("Computer")) {
                computerWins++;
            }

            System.out.println("Game " + i + ": Player = " + player +
                    ", Computer = " + computer +
                    ", Winner = " + winner);
        }

        String[][] stats = calculateStats(playerWins, computerWins, games);

        System.out.println("\nPlayer\tWins\tPercentage");

        for (int i = 0; i < stats.length; i++) {
            System.out.println(stats[i][0] + "\t" + stats[i][1] + "\t" + stats[i][2] + "%");
        }

        input.close();
    }
}