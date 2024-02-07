package main.ltcode_gfg._01_arrays_hashing;

public class FilloutZeros {

    public void filloutRecursive(int[][] matrix) {

        this.filloutRecursiveInternal(matrix, 0, 0);
    }

    private void filloutRecursiveInternal(int[][] matrix, int x, int y) {
        if (x >= matrix.length || y >= matrix[0].length) {
            return;
        }
        for (int i = x; i < matrix.length; i++) {
            for (int j = y; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    if (j + 1 == matrix[0].length) {
                        this.filloutRecursiveInternal(matrix, i+1, 0);
                    } else {
                        this.filloutRecursiveInternal(matrix, i, j+1);
                    }

                    // Set column
                    for (int r = 0; r < matrix.length; r++) {
                        matrix[r][j] = 0;
                    }
                    // Set row
                    for (int c = 0; c < matrix[i].length; c++) {
                        matrix[i][c] = 0;
                    }
                    return;
                }
            }
            y = 0;
        }
        return;
    }

    public void testSelf() {
        System.out.println("\nCase 0");
        int[][] matrix0 = {{0, 1}};
        this.filloutRecursive(matrix0);

        for (int i=0; i<matrix0.length; i++) {
            for (int j = 0; j < matrix0[i].length; j++) {
                System.out.print(matrix0[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nCase 1");
        int[][] matrix1 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        this.filloutRecursive(matrix1);

        for (int i=0; i<matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                System.out.print(matrix1[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nCase 1_1");
        int[][] matrix1_1 = {{0, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        this.filloutRecursive(matrix1_1);

        for (int i=0; i<matrix1_1.length; i++) {
            for (int j = 0; j < matrix1_1[i].length; j++) {
                System.out.print(matrix1_1[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nCase 1_2");
        int[][] matrix1_2 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 0}};
        this.filloutRecursive(matrix1_2);

        for (int i=0; i<matrix1_2.length; i++) {
            for (int j = 0; j < matrix1_2[i].length; j++) {
                System.out.print(matrix1_2[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nCase 1_3");
        int[][] matrix1_3 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 0}};
        this.filloutRecursive(matrix1_3);

        for (int i=0; i<matrix1_3.length; i++) {
            for (int j = 0; j < matrix1_3[i].length; j++) {
                System.out.print(matrix1_3[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nCase 2");
        int[][] matrix2 = {{1, 1, 0, 1}, {1, 0, 1, 1}, {1, 1, 1, 0}, {1, 1, 0, 1}};
        this.filloutRecursive(matrix2);

        for (int i=0; i<matrix2.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                System.out.print(matrix2[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nCase 2_1");
        int[][] matrix2_1 = {{0, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 0, 1}};
        this.filloutRecursive(matrix2_1);

        for (int i=0; i<matrix2_1.length; i++) {
            for (int j = 0; j < matrix2_1[i].length; j++) {
                System.out.print(matrix2_1[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nCase 3");
        int[][] matrix3 = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        this.filloutRecursive(matrix3);

        for (int i=0; i<matrix3.length; i++) {
            for (int j = 0; j < matrix3[i].length; j++) {
                System.out.print(matrix3[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nCase 4");
        int[][] matrix4 = {{2147483647, 2, 9}, {2, 6, 7}, {1, 8, 8}, {5, 0, 1}, {9, 6, 0}};
        this.filloutRecursive(matrix4);

        for (int i=0; i<matrix4.length; i++) {
            for (int j = 0; j < matrix4[i].length; j++) {
                System.out.print(matrix4[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void filloutUsingMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = matrix.length-1; i >=0 ; i--) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = matrix[0].length-1; j >=0 ; j--) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < matrix[0].length-1; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public void testSelfUsingExisting() {
        System.out.println("\nCase 0");
        int[][] matrix0 = {{0, 1}};
        this.filloutUsingMatrix(matrix0);

        for (int i=0; i<matrix0.length; i++) {
            for (int j = 0; j < matrix0[i].length; j++) {
                System.out.print(matrix0[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nCase 1");
        int[][] matrix1 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        this.filloutUsingMatrix(matrix1);

        for (int i=0; i<matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                System.out.print(matrix1[i][j] + " ");
            }
            System.out.println();
        }
//
//        System.out.println("\nCase 1_1");
//        int[][] matrix1_1 = {{0, 1, 1}, {1, 1, 1}, {1, 1, 1}};
//        this.filloutUsingMatrix(matrix1_1);
//
//        for (int i=0; i<matrix1_1.length; i++) {
//            for (int j = 0; j < matrix1_1[i].length; j++) {
//                System.out.print(matrix1_1[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("\nCase 1_2");
//        int[][] matrix1_2 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 0}};
//        this.filloutUsingMatrix(matrix1_2);
//
//        for (int i=0; i<matrix1_2.length; i++) {
//            for (int j = 0; j < matrix1_2[i].length; j++) {
//                System.out.print(matrix1_2[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("\nCase 1_3");
//        int[][] matrix1_3 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 0}};
//        this.filloutUsingMatrix(matrix1_3);
//
//        for (int i=0; i<matrix1_3.length; i++) {
//            for (int j = 0; j < matrix1_3[i].length; j++) {
//                System.out.print(matrix1_3[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("\nCase 2");
//        int[][] matrix2 = {{1, 1, 0, 1}, {1, 0, 1, 1}, {1, 1, 1, 0}, {1, 1, 0, 1}};
//        this.filloutUsingMatrix(matrix2);
//
//        for (int i=0; i<matrix2.length; i++) {
//            for (int j = 0; j < matrix2[i].length; j++) {
//                System.out.print(matrix2[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("\nCase 2_1");
//        int[][] matrix2_1 = {{0, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 0, 1}};
//        this.filloutUsingMatrix(matrix2_1);
//
//        for (int i=0; i<matrix2_1.length; i++) {
//            for (int j = 0; j < matrix2_1[i].length; j++) {
//                System.out.print(matrix2_1[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("\nCase 3");
//        int[][] matrix3 = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
//        this.filloutUsingMatrix(matrix3);
//
//        for (int i=0; i<matrix3.length; i++) {
//            for (int j = 0; j < matrix3[i].length; j++) {
//                System.out.print(matrix3[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println("\nCase 4");
//        int[][] matrix4 = {{2147483647, 2, 9}, {2, 6, 7}, {1, 8, 8}, {5, 0, 1}, {9, 6, 0}};
//        this.filloutUsingMatrix(matrix4);
//
//        for (int i=0; i<matrix4.length; i++) {
//            for (int j = 0; j < matrix4[i].length; j++) {
//                System.out.print(matrix4[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    public static void main(String[] args) {
        FilloutZeros fz = new FilloutZeros();

//        fz.testSelf();
        fz.testSelfUsingExisting();
    }
}
