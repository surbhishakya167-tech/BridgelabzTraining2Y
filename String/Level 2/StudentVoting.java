import java.util.Scanner;

public class StudentVoting {
    static int[] generateAges(int number) {
        int[] ages = new int[number];

        for (int i = 0; i < number; i++) {
            ages[i] = (int) (Math.random() * 90) + 10;
        }

        return ages;
    }

    static String[][] checkVoting(int[] ages) {
        String[][] result = new String[ages.length][2];

        for (int i = 0; i < ages.length; i++) {
            result[i][0] = String.valueOf(ages[i]);

            if (ages[i] < 0) {
                result[i][1] = "false";
            } else if (ages[i] >= 18) {
                result[i][1] = "true";
            } else {
                result[i][1] = "false";
            }
        }

        return result;
    }

    static void display(String[][] result) {
        System.out.println("Age\tCan Vote");

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i][0] + "\t" + result[i][1]);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int number = input.nextInt();

        int[] ages = generateAges(number);
        String[][] result = checkVoting(ages);

        display(result);

        input.close();
    }
}