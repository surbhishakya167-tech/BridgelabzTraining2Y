import java.util.Scanner;

public class CompareStrings {
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

        String str1 = input.next();
        String str2 = input.next();

        boolean result1 = compare(str1, str2);
        boolean result2 = str1.equals(str2);

        System.out.println("User-defined result: " + result1);
        System.out.println("Built-in result: " + result2);

        input.close();
    }
}