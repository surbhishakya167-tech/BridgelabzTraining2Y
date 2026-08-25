import java.util.Scanner;

public class CustomTrim {
    static int[] trimIndex(String text) {
        int start = 0;
        int end = text.length() - 1;

        while (start <= end && text.charAt(start) == ' ') {
            start++;
        }

        while (end >= start && text.charAt(end) == ' ') {
            end--;
        }

        return new int[]{start, end + 1};
    }

    static String createSubstring(String text, int start, int end) {
        String result = "";

        for (int i = start; i < end; i++) {
            result += text.charAt(i);
        }

        return result;
    }

    static boolean compare(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String text = input.nextLine();

        int[] index = trimIndex(text);

        String result1 = createSubstring(text, index[0], index[1]);
        String result2 = text.trim();

        System.out.println("User-defined: " + result1);
        System.out.println("Built-in: " + result2);
        System.out.println("Are both same? " + compare(result1, result2));

        input.close();
    }
}