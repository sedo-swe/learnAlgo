package main.ltcode;

@FunctionalInterface
interface ReverseNumber {
    int reverse(int number);
}

public class ReverseInteger {
    public ReverseInteger() {

    }

    ReverseNumber reverseNumber = (number -> {
        int rev = 0;
        int pop = 0;
        while (number != 0) {
            pop = number % 10;

            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }

            rev = rev * 10 + pop;
            number = number / 10;
        }

        return rev;
    });

    public void test(ReverseNumber func) {
        System.out.println(func.reverse(1234));
        System.out.println(func.reverse(-1234));
        System.out.println(func.reverse(120));
        System.out.println(func.reverse(Integer.MAX_VALUE));
        System.out.println(func.reverse(Integer.MIN_VALUE));
    }

    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        ri.test(ri.reverseNumber);
    }
}
