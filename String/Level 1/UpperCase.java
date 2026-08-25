import java.util.Scanner;

public class UpperCase {
    static String convertToUpperCase(String text) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                ch = (char) (ch - 32);
            }

            result += ch;
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

        String text = input.nextLine();

        String result1 = convertToUpperCase(text);
        String result2 = text.toUpperCase();

        System.out.println("User-defined: " + result1);
        System.out.println("Built-in: " + result2);
        System.out.println("Are both same? " + compare(result1, result2));

        input.close();
    }
}