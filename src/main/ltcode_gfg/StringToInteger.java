package main.ltcode_gfg;

@FunctionalInterface
interface MyAtoI {
    int convert(String s);
}

public class StringToInteger {
    public StringToInteger() {}

    MyAtoI myAtoI = ((s) -> {
        double result = 0.0;

        // Trim string
        String temp = s.trim();

        // Read first character
        char firstChar = s.charAt(0);

        // Check whether it is either '+' or '-'
        int sign = 1, power = 0, startIdx = 0;
        boolean isDecimal = false;
        if (firstChar == '-') {
            sign = -1;
            startIdx = 1;
        }
        // If it is neither of them, then assume it is positive
        else if (firstChar == '+') {
            startIdx = 1;
        } else if (firstChar == '.') {
            startIdx = 1;
            isDecimal = true;
        }

        // Read remaining characters until either having non digit or reaching the end of string
        int pop = 0;
        for (int i = startIdx; i < s.length(); i++) {

            if (s.charAt(i) == '.') {
                // if this '.' is the second one
                if (isDecimal) {
                    break;
                }
                isDecimal = true;
            } else {
                pop = Character.digit(s.charAt(i), 10);
                if (pop < 0) {
                    break;
                }

                pop *= sign;
                // If number is greater than or less than integer's range, then clamp into its boundary
                if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && pop > 7)) {
                    return Integer.MAX_VALUE;
                }
                if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && pop < -8)) {
                    return Integer.MIN_VALUE;
                }

                if (isDecimal) {
                    result = result + pop * Math.pow(10, --power);
                    System.out.println("result: "+result + ", power: " + Math.pow(10, power));
                } else {
                    result = result * 10 + pop;
                }
            }
        }

        // Return
        return isDecimal ? (int) result : (int) result;
    });

    public void test(MyAtoI func) {
        System.out.println(func.convert("123") + " / " + (func.convert("123") == 123));
        System.out.println(func.convert("+123") + " / " + (func.convert("+123") == 123));
        System.out.println(func.convert("-123") + " / " + (func.convert("-123") == -123));
        System.out.println(func.convert("000123") + " / " + (func.convert("000123") == 123));
        System.out.println(func.convert("2147483649") + " / " + (func.convert("2147483649") == 2147483647));
        System.out.println(func.convert("-2147483649") + " / " + (func.convert("-2147483649") == -2147483648));
        System.out.println(func.convert("-214abc") + " / " + (func.convert("-214abc") == -214));
        System.out.println(func.convert("0.12") + " / " + (func.convert("0.12") == 0.12));
        System.out.println(func.convert("-0.12") + " / " + (func.convert("-0.12") == -0.12));
        System.out.println(func.convert(".13") + " / " + (func.convert(".13") == 0.13));
//        System.out.println(func.convert("-.13") + " / " + (func.convert("-.13") == -0.13));
    }

    public static void main(String[] args) {
        System.out.println(0 + '9' - '0');
        char c = 'a';
        System.out.println(Character.digit(c, 10));
        System.out.println(c > '0');
        System.out.println();

        StringToInteger stringToInteger = new StringToInteger();
        stringToInteger.test(stringToInteger.myAtoI);
    }
}
