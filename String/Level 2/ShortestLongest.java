import java.util.Scanner;

public class ShortestLongest {
    static int findLength(String text) {
        int count = 0;

        while (true) {
            try {
                text.charAt(count);
                count++;
            } catch (StringIndexOutOfBoundsException e) {
                break;
            }
        }

        return count;
    }

    static String[] splitWords(String text) {
        int wordCount = 1;

        for (int i = 0; i < findLength(text); i++) {
            if (text.charAt(i) == ' ') {
                wordCount++;
            }
        }

        String[] words = new String[wordCount];
        int index = 0;
        String word = "";

        for (int i = 0; i < findLength(text); i++) {
            if (text.charAt(i) != ' ') {
                word += text.charAt(i);
            } else {
                words[index++] = word;
                word = "";
            }
        }

        words[index] = word;

        return words;
    }

    static String[][] wordDetails(String[] words) {
        String[][] result = new String[words.length][2];

        for (int i = 0; i < words.length; i++) {
            result[i][0] = words[i];
            result[i][1] = String.valueOf(findLength(words[i]));
        }

        return result;
    }

    static int[] findShortestLongest(String[][] words) {
        int shortest = 0;
        int longest = 0;

        for (int i = 1; i < words.length; i++) {
            int current = Integer.parseInt(words[i][1]);

            if (current < Integer.parseInt(words[shortest][1])) {
                shortest = i;
            }

            if (current > Integer.parseInt(words[longest][1])) {
                longest = i;
            }
        }

        return new int[]{shortest, longest};
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String text = input.nextLine();

        String[] words = splitWords(text);
        String[][] details = wordDetails(words);
        int[] result = findShortestLongest(details);

        System.out.println("Shortest word = " + details[result[0]][0]);
        System.out.println("Longest word = " + details[result[1]][0]);

        input.close();
    }
}