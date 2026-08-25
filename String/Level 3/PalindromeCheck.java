import java.util.Scanner;

public class PalindromeCheck {
    static boolean palindromeLoop(String text) {
        int start = 0;
        int end = text.length() - 1;

        while (start < end) {
            if (text.charAt(start) != text.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }

    static boolean palindromeRecursive(String text, int start, int end) {
        if (start >= end) {
            return true;
        }

        if (text.charAt(start) != text.charAt(end)) {
            return false;
        }

        return palindromeRecursive(text, start + 1, end - 1);
    }

    static char[] reverse(String text) {
        char[] reverse = new char[text.length()];

        for (int i = 0; i < text.length(); i++) {
            reverse[i] = text.charAt(text.length() - 1 - i);
        }

        return reverse;
    }

    static boolean palindromeArray(String text) {
        char[] original = text.toCharArray();
        char[] reverse = reverse(text);

        for (int i = 0; i < original.length; i++) {
            if (original[i] != reverse[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String text = input.next();

        System.out.println("Using loop: " + palindromeLoop(text));
        System.out.println("Using recursion: " +
                palindromeRecursive(text, 0, text.length() - 1));
        System.out.println("Using arrays: " + palindromeArray(text));

        input.close();
    }
}