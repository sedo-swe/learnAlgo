package main.ltcode.bit_manipulation;

@FunctionalInterface
interface IntMissingNumber {
    int missingNumber(int[] nums);
}
public class MissingNumber {
    IntMissingNumber intMissingNumber1st = ((nums) -> {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int total = nums.length * (1 + nums.length) / 2;
        return total - sum;
    });

    public void test(IntMissingNumber func) {
        System.out.println("Expected: 2, Actual: " + func.missingNumber(new int[]{3, 0, 1}));
        System.out.println("Expected: 2, Actual: " + func.missingNumber(new int[]{0, 1}));
        System.out.println("Expected: 8, Actual: " + func.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    public static void main(String[] args) {
        MissingNumber missingNumber = new MissingNumber();
        missingNumber.test(missingNumber.intMissingNumber1st);
    }
}
