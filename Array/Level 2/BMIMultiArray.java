import java.util.Scanner;

public class BMIMultiArray {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int number = input.nextInt();

        double[][] personData = new double[number][3];
        String[] weightStatus = new String[number];

        for (int i = 0; i < number; i++) {
            do {
                System.out.print("Enter weight: ");
                personData[i][0] = input.nextDouble();

                if (personData[i][0] <= 0) {
                    System.out.println("Enter a positive weight.");
                }
            } while (personData[i][0] <= 0);

            do {
                System.out.print("Enter height: ");
                personData[i][1] = input.nextDouble();

                if (personData[i][1] <= 0) {
                    System.out.println("Enter a positive height.");
                }
            } while (personData[i][1] <= 0);

            personData[i][2] = personData[i][0] / 
                               (personData[i][1] * personData[i][1]);

            if (personData[i][2] < 18.5) {
                weightStatus[i] = "Underweight";
            } else if (personData[i][2] < 25) {
                weightStatus[i] = "Normal";
            } else if (personData[i][2] < 30) {
                weightStatus[i] = "Overweight";
            } else {
                weightStatus[i] = "Obese";
            }
        }

        for (int i = 0; i < number; i++) {
            System.out.println("Person " + (i + 1));
            System.out.println("Weight = " + personData[i][0]);
            System.out.println("Height = " + personData[i][1]);
            System.out.println("BMI = " + personData[i][2]);
            System.out.println("Status = " + weightStatus[i]);
        }

        input.close();
    }
}