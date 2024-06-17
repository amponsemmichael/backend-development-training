
import java.util.Scanner;
public class PeakColumns {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //Accept inputs from the user for his/her array elements
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();

        int[][] A = new int[rows][cols];
        System.out.println("Enter the matrix elements:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                A[i][j] = scanner.nextInt();
            }
        }

        

        findPeakColumns(A);
    }
    // method to find the peak-columns
    public static void findPeakColumns(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            int rowMax = Integer.MIN_VALUE;
            int rowMaxIndex = -1;
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] > rowMax) {
                    rowMax = A[i][j];
                    rowMaxIndex = j;
                }
            }
            boolean isColMin = true;
            for (int k = 0; k < A.length; k++) {
                if (A[k][rowMaxIndex] < rowMax) {
                    isColMin = false;
                    break;
                }
            }
            if (isColMin) {
                System.out.println("(" + i + ", " + rowMaxIndex + ") = " + rowMax);
            }
        }
    }
}
