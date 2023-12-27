package main.ltcode.bit_manipulation;

/**
 *  190. Reverse Bits (Easy)
 */
@FunctionalInterface
interface IntReverseBits {
    int reverseBits(int n);
}
public class ReverseBits {
    IntReverseBits intReverseBits1st = ((n) -> {
        int reversed = 0;
        for (int i = 0; i < 32; i++) {
            reversed <<= 1;
            reversed |= (n & 1);
            n >>= 1;
        }
        return reversed;
    });

    public void test(IntReverseBits func) {
        System.out.println("Expected: 964176192, Actual: " + func.reverseBits(43261596));
        System.out.println("Expected: -1073741825, Actual: " + func.reverseBits(-3));
    }

    public static void main(String[] args) {
        ReverseBits reverseBits = new ReverseBits();
        reverseBits.test(reverseBits.intReverseBits1st);
    }
}
