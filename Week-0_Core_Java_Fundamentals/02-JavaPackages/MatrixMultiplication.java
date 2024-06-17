import java.util.Scanner;

public class MatrixMultiplication {

    // Function to input matrix from the user
    public static int[][] inputMatrix(Scanner input, int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        System.out.println("Enter the elements for the matrix (each row should have " + cols + " elements):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = input.nextInt();
            }
        }
        return matrix;
    }

    // Function to multiply two matrices
    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int m = A[0].length;
        int p = B[0].length;

        int[][] C = new int[n][p];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < m; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    // Function to print the matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Input dimensions for Matrix A
        System.out.print("Enter the number of rows for Matrix A: ");
        int n = input.nextInt();
        System.out.print("Enter the number of columns for Matrix A: ");
        int m = input.nextInt();
        int[][] A = inputMatrix(input, n, m);

        // Input dimensions for Matrix B
        System.out.print("Enter the number of rows for Matrix B : ");
        int m2 = input.nextInt();
        if (m != m2) {
            throw new IllegalArgumentException("Number of columns in Matrix A must be equal to number of rows in Matrix B.");
        }
        System.out.print("Enter the number of columns for Matrix B: ");
        int p = input.nextInt();
        int[][] B = inputMatrix(input, m, p);

        // Multiply matrices A and B to get matrix C
        int[][] C = multiplyMatrices(A, B);

        // Print matrix C
        System.out.println("Matrix C:");
        printMatrix(C);

        input.close();
    }
}
