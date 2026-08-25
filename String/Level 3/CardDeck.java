import java.util.Scanner;

public class CardDeck {
    static String[] initializeDeck(String[] suits, String[] ranks) {
        String[] deck = new String[suits.length * ranks.length];
        int index = 0;

        for (String suit : suits) {
            for (String rank : ranks) {
                deck[index] = rank + " of " + suit;
                index++;
            }
        }

        return deck;
    }

    static String[] shuffleDeck(String[] deck) {
        int n = deck.length;

        for (int i = 0; i < n; i++) {
            int randomCardNumber =
                    i + (int) (Math.random() * (n - i));

            String temp = deck[i];
            deck[i] = deck[randomCardNumber];
            deck[randomCardNumber] = temp;
        }

        return deck;
    }

    static String[][] distribute(String[] deck, int cards, int players) {
        if (cards * players > deck.length) {
            return null;
        }

        String[][] playerCards = new String[players][cards];

        int index = 0;

        for (int i = 0; i < players; i++) {
            for (int j = 0; j < cards; j++) {
                playerCards[i][j] = deck[index];
                index++;
            }
        }

        return playerCards;
    }

    static void printPlayers(String[][] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.println("Player " + (i + 1) + ":");

            for (int j = 0; j < players[i].length; j++) {
                System.out.println(players[i][j]);
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] suits = {
            "Hearts", "Diamonds", "Clubs", "Spades"
        };

        String[] ranks = {
            "2", "3", "4", "5", "6", "7", "8", "9",
            "10", "Jack", "Queen", "King", "Ace"
        };

        System.out.print("Enter number of cards per player: ");
        int cards = input.nextInt();

        System.out.print("Enter number of players: ");
        int players = input.nextInt();

        String[] deck = initializeDeck(suits, ranks);
        deck = shuffleDeck(deck);

        String[][] playerCards = distribute(deck, cards, players);

        if (playerCards == null) {
            System.out.println("Cards cannot be equally distributed.");
        } else {
            printPlayers(playerCards);
        }

        input.close();
    }
}