import java.util.Scanner;

public class StringLength {
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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String text = input.next();

        System.out.println("User-defined length = " + findLength(text));
        System.out.println("Built-in length = " + text.length());

        input.close();
    }
}