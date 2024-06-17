
import java.util.Scanner;
import java.util.stream.IntStream;

public class StatisticsOfGrades {
    private static final String CHARACTERS = "#######";
    private static final String EMPTY = "       ";
    private static final int[] RANGE_LIMITS = {20, 40, 60, 80, 100};
    private static final int RANGE_COUNT = RANGE_LIMITS.length;

    public static int highestMark(int[] marks) {
        return IntStream.of(marks).max().orElseThrow();
    }

    public static int lowestMark(int[] marks) {
        return IntStream.of(marks).min().orElseThrow();
    }

    public static double average(int[] marks) {
        return IntStream.of(marks).average().orElseThrow();
    }

    public static void main(String[] args) {
        int[] marks = getMarksFromUser();

        int highest = highestMark(marks);
        int lowest = lowestMark(marks);
        double avg = average(marks);

        int[] rangeFrequency = calculateRangeFrequency(marks);
        printStatistics(highest, lowest, avg);
        printHistogram(rangeFrequency);

    }

    private static int[] getMarksFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter students marks separated by spaces:");
        String[] input = scanner.nextLine().split(" ");
        scanner.close();

        return IntStream.range(0, input.length)
                .map(i -> Integer.parseInt(input[i]))
                .toArray();
    }

    private static int[] calculateRangeFrequency(int[] marks) {
        int[] rangeFrequency = new int[RANGE_COUNT];

        for (int mark : marks) {
            for (int i = 0; i < RANGE_COUNT; i++) {
                if (mark <= RANGE_LIMITS[i]) {
                    rangeFrequency[i]++;
                    break;
                }
            }
        }

        return rangeFrequency;
    }

    private static void printStatistics(int highest, int lowest, double avg) {
        System.out.println("Values:");
        System.out.println();
        System.out.println("Maximum: " + highest);
        System.out.println("Minimum: " + lowest);
        System.out.println("Average: " + avg);
        System.out.println();
        System.out.println();
    }

    private static void printHistogram(int[] rangeFrequency) {
        System.out.println("Graph: ");
        System.out.println();
        int maxFrequency = IntStream.of(rangeFrequency).max().orElseThrow();
        while (maxFrequency > 0) {

            System.out.print(maxFrequency + "\t> ");
            for (int i = 0; i < RANGE_COUNT; i++) {
                if (rangeFrequency[i] >= maxFrequency) {
                    System.out.print(" " +  CHARACTERS  + " ");
                } else {
                    System.out.print(" " + EMPTY + " ");
                }
            }
            System.out.println();
            maxFrequency--;
        }

       System.out.println("\t+--------+---------+---------+--------+---------+");
       System.out.println("\tI  0-20  I  21-40  I  41-60  I  61-80  I 81-100 I");


    }

}
