package Lab1;

////// EXERCISE 2 ///////

public class MatrixOperations {
    public static void main(String[] args) {

        // Initializing the matrices
        int[][] matrix1 = {{2, 3, 1}, {7, 1, 6}, {9, 2, 4}};
        int[][] matrix2 = {{8, 5, 3}, {3, 9, 2}, {2, 7, 3}};

        // Calculate the sum of the two matrices
        int[][] sumMatrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        // Print the sum of the two matrices
        System.out.println("Sum of the two matrices:");
        printMatrix(sumMatrix);

        // Calculate the product of the two matrices
        int[][] productMatrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                productMatrix[i][j] = 0;
                for (int k = 0; k < 3; k++) {
                    productMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        // Print the product of the two matrices
        System.out.println("Product of the two matrices:");
        printMatrix(productMatrix);
    }

    // Method to print a matrix
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
