import java.util.Scanner;

public class NumberFormatDemo {
    static void generateException(String text) {
        int number = Integer.parseInt(text);
        System.out.println(number);
    }

    static void handleException(String text) {
        try {
            int number = Integer.parseInt(text);
            System.out.println(number);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException handled");
        } catch (RuntimeException e) {
            System.out.println("RuntimeException handled");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String text = input.next();

        handleException(text);

        input.close();
    }
}