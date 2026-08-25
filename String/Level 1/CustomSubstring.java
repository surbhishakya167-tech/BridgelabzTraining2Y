import java.util.Scanner;

public class CustomSubstring {
    static String createSubstring(String text, int start, int end) {
        String result = "";

        for (int i = start; i < end; i++) {
            result += text.charAt(i);
        }

        return result;
    }

    static boolean compare(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String text = input.next();
        int start = input.nextInt();
        int end = input.nextInt();

        String result1 = createSubstring(text, start, end);
        String result2 = text.substring(start, end);

        System.out.println("User-defined substring: " + result1);
        System.out.println("Built-in substring: " + result2);
        System.out.println("Are both same? " + compare(result1, result2));

        input.close();
    }
}