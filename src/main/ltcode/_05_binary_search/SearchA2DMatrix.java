package main.ltcode._05_binary_search;

/**
 * 74. Search a 2D Matrix (Medium)
 */
@FunctionalInterface
interface IntSearchA2DMatrix {
    boolean searchMatrix(int[][] matrix, int target);
}
public class SearchA2DMatrix {

    /*
        1st idea
            - Go through all elements in the matrix, check if target is in matrix

        2nd idea
            - Go through matrix like binary search
                - Calculate middle like this is single array.
     */
    IntSearchA2DMatrix intSearchA2DMatrix = ((matrix, target) -> {
        return false;
    });

    IntSearchA2DMatrix intSearchA2DMatrixBinary = ((matrix, target) -> {
        return binarySearchMatrix(matrix, target, 0, matrix.length * matrix[0].length - 1);
    });

    // {{1,3},{10,11},{23,30}}  start: 1, end: 1, mid[0, 1, 1] target: 3
    private boolean binarySearchMatrix(int[][] matrix, int target, int start, int end) {
//        System.out.println("\tStart: "+start+", End: "+end);
        if (start > end) {
            return false;
        }
        int[] midElem = this.findMiddle(matrix, start, end);
//        System.out.println("\t  midElem[0]: " + midElem[0] + ", [1]: " + midElem[1] + ", [2]: " + midElem[2] + ", val: " + matrix[midElem[0]][midElem[1]]);
        if (matrix[midElem[0]][midElem[1]] == target) {
            return true;
        } else if (matrix[midElem[0]][midElem[1]] > target) {
            return binarySearchMatrix(matrix, target, start, midElem[2] - 1);
        } else {
            return binarySearchMatrix(matrix, target, midElem[2] + 1, end);
        }
    }

    private int[] findMiddle(int[][] matrix, int start, int end) {  // s: 0, e: 5, mid: 2, aI: 1, eI: 1
        int[] middle = new int[3];
        int rows = matrix.length;
        int cols = matrix[0].length;

        int mid = (end + start) / 2;
//        System.out.println("mid: "+mid+", rows: "+rows+", cols: "+cols);
        int arrayIndex = 0;
        if (cols == 1) {
            arrayIndex = mid;
        } else {
            arrayIndex = mid / cols;
        }
        int elemIndex = 0;
        if (cols > 1) {
            elemIndex = mid % cols;
        }

        middle[0] = arrayIndex;
        middle[1] = elemIndex;
        middle[2] = mid;
        return middle;
    }

    IntSearchA2DMatrix intSearchA2DMatrixSol = ((matrix, target) -> {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int row = 0;
        int col = cols -1;

        while(row < rows && col >=0){
            int cur = matrix[row][col];
            if(cur == target){
                return true;
            }
            else if(target > cur){
                row++;
            }
            else{
                col--;
            }
        }
        return false;
    });

    public void test(IntSearchA2DMatrix func) {
        System.out.println("Expected: true, Actual: " + func.searchMatrix(new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3));
        System.out.println("Expected: false, Actual: " + func.searchMatrix(new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 13));
        System.out.println("Expected: true, Actual: " + func.searchMatrix(new int[][] {{1,3,5,7}}, 3));
        System.out.println("Expected: false, Actual: " + func.searchMatrix(new int[][] {{1,3,5,7}}, 2));
        System.out.println("Expected: false, Actual: " + func.searchMatrix(new int[][] {{1,1}}, 2));
        System.out.println("Expected: true, Actual: " + func.searchMatrix(new int[][] {{1}}, 1));
        System.out.println("Expected: false, Actual: " + func.searchMatrix(new int[][] {{1}}, 2));
        System.out.println("Expected: true, Actual: " + func.searchMatrix(new int[][] {{1},{10},{23}}, 23));
        System.out.println("Expected: false, Actual: " + func.searchMatrix(new int[][] {{1},{10},{23}}, 13));
        System.out.println("Expected: true, Actual: " + func.searchMatrix(new int[][] {{1,3},{10,11},{23,30}}, 3));
        System.out.println("Expected: false, Actual: " + func.searchMatrix(new int[][] {{1,3},{10,11},{23,30}}, 13));
    }

    public static void main(String[] args) {
        SearchA2DMatrix searchA2DMatrix = new SearchA2DMatrix();
        searchA2DMatrix.test(searchA2DMatrix.intSearchA2DMatrixBinary);
    }
}
