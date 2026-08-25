import java.util.Scanner;

public class UniqueCharacters {
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

    static char[] findUnique(String text) {
        int length = findLength(text);
        char[] unique = new char[length];
        int index = 0;

        for (int i = 0; i < length; i++) {
            boolean found = false;

            for (int j = 0; j < i; j++) {
                if (text.charAt(i) == text.charAt(j)) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                unique[index] = text.charAt(i);
                index++;
            }
        }

        char[] result = new char[index];

        for (int i = 0; i < index; i++) {
            result[i] = unique[i];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String text = input.nextLine();
        char[] result = findUnique(text);

        System.out.print("Unique characters: ");

        for (char ch : result) {
            System.out.print(ch + " ");
        }

        input.close();
    }
}