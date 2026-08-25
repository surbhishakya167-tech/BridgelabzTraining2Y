import java.util.Scanner;

public class CharacterFrequency {
    static String[][] findFrequency(String text) {
        int[] frequency = new int[256];

        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }

        int count = 0;

        for (int i = 0; i < 256; i++) {
            if (frequency[i] > 0) {
                count++;
            }
        }

        String[][] result = new String[count][2];
        int index = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (frequency[ch] > 0) {
                boolean found = false;

                for (int j = 0; j < index; j++) {
                    if (result[j][0].charAt(0) == ch) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    result[index][0] = String.valueOf(ch);
                    result[index][1] = String.valueOf(frequency[ch]);
                    index++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String text = input.nextLine();
        String[][] result = findFrequency(text);

        System.out.println("Character\tFrequency");

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0] + "\t\t" + result[i][1]);
        }

        input.close();
    }
}