package main.ltcode_gfg;

import java.util.Arrays;


@FunctionalInterface
interface ConvertIntoZigzag {
    String convert(String s, int row);
}

class ManageZigzagIndex {
    int start, end, current, change;

    ManageZigzagIndex(int start, int end) {
        this.start = start ;
        this.end = end;
        this.change = 0;
        this.current = this.start;
    }

    /**
     * Assume starting to forward direction
     */
    public int getNextZigzagIndex() {
        if (this.start == this.end) {
            return 0;
        }

        this.current = this.current + this.change;

        if (this.current == this.end) {
            this.change = -1;
        }
        if (this.current == this.start) {
            this.change = 1;
        }

        return this.current;
    }
}

public class ZigzagConversion {

    ConvertIntoZigzag convertIntoZigzagInitial = (string, row) -> {
        String result = "";

        // if either length of string == 0 or row == 0, then return null
        if (string == null || string.length() == 0) {
            return null;
        }

        if (row == 0) {
            return null;
        }

        // if length of string is same with row, then return the original string
        if (string.length() == row) {
            return string;
        }

        // build string array as many as row
        String[] strings = new String[row];
//        strings = Arrays.stream(strings).map(x -> "").collect(Collectors.toList()).toArray(String[]::new);
        Arrays.setAll(strings, s -> "");

        // generate index sequence moving between left and right like 0, 1, 2, 3, 2, 1, 0, 1, ...
        ManageZigzagIndex manageZigzagIndex = new ManageZigzagIndex(0, row - 1);

        // allocate each string by index generated
        for (int i = 0; i < string.length(); i++) {
            strings[manageZigzagIndex.getNextZigzagIndex()] += string.charAt(i);
        }

        // concatenate all string array into one string
        result = Arrays.stream(strings).reduce("", String::concat);

        // return
        return result;
    };

    ConvertIntoZigzag zigzag2 = (string, numRows) -> {
        if (string == null || string.isEmpty() || numRows <= 0) {
            return null;
        }

        if (string.length() == numRows || numRows == 1) {
            return string;
        }

        StringBuilder result = new StringBuilder();

        int step = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < string.length(); j += step) {
                result.append(string.charAt(j));
                if (i != 0 && i != numRows - 1 && (j + step - 2 * i) < string.length()) {
                    result.append(string.charAt(j + step - 2 * i));
                }
            }
        }

        return result.toString();
    };

    public void test(ConvertIntoZigzag function) {
        String test1 = function.convert("PAYPALISHIRING", 3);
        System.out.println("'PAYPALISHIRING' --> " + test1 + " which is " + ("PAHNAPLSIIGYIR".equals(test1) ? "correct" : "wrong") + ".");
        String test2 = function.convert("PAYPALISHIRING", 4);
        System.out.println("'PAYPALISHIRING' --> " + test2 + " which is " + ("PINALSIGYAHRPI".equals(test2) ? "correct" : "wrong") + ".");
        String test3 = function.convert("PAYPALISHIRING", 5);
        System.out.println("'PAYPALISHIRING' --> " + test3 + " which is " + ("PHASIYIRPLIGAN".equals(test3) ? "correct" : "wrong") + ".");
        String test4 = function.convert("A", 1);
        System.out.println("'A' --> " + test4 + " which is " + ("A".equals(test4) ? "correct" : "wrong") + ".");
        String test5 = function.convert("AB", 1);
        System.out.println("'AB' --> " + test5 + " which is " + ("AB".equals(test5) ? "correct" : "wrong") + ".");
        String test6 = function.convert("AB", 2);
        System.out.println("'AB' --> " + test6 + " which is " + ("AB".equals(test6) ? "correct" : "wrong") + ".");
    }


    public static void main(String[] args) {
        ZigzagConversion zc = new ZigzagConversion();
//        zc.test(zc.convertIntoZigzagInitial);

        zc.test(zc.zigzag2);

    }
}
