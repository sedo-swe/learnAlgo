package main.ltcode_gfg._04_stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@FunctionalInterface
interface IntValidParentheses {
    boolean isValid(String s);
}

public class ValidParentheses {
    /*
        First idea
            - Put open parentheses, check when meet close parentheses whether the last element was its opening pair.
            ==> 2ms (82.27%), 40.73 MB (20.05%)
     */
    IntValidParentheses isValid1st = ((s) -> {
        String openParantheses = "({[";
        Stack<Character> parentheses = new Stack<>();

        for (int i=0; i<s.length(); i++) {
            if (openParantheses.contains(String.valueOf(s.charAt(i)))) {
                parentheses.push(s.charAt(i));
            } else {
                if (!parentheses.isEmpty()) {
                    char top = parentheses.pop();
                    if (top != getOtherPair(s.charAt(i))) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return parentheses.isEmpty();
    });

    private char getOtherPair(char one) {
        char result = '_';
        switch(one) {
            case ')':
                result = '(';
                break;
            case ']':
                result = '[';
                break;
            case '}':
                result = '{';
                break;
        }
        return result;
    }


    IntValidParentheses isValidSolution = ((s) -> {
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() && (s.charAt(i) == ')' || s.charAt(i) == '}' || s.charAt(i) == ']')) {
                return false;
            } else {
                if (s.charAt(i) == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (s.charAt(i) == '}' && stack.peek() == '{') {
                    stack.pop();
                } else if (s.charAt(i) == ']' && stack.peek() == '[') {
                    stack.pop();
                } else {
                    stack.add(s.charAt(i));
                }
            }
        }
        return stack.isEmpty();
    });

    IntValidParentheses isValidSolutionWithHashmap = ((s) -> {
        Stack<Character> brackets = new Stack<>();
        Map<Character, Character> bracketLookup = new HashMap<>(3);

        bracketLookup.put(')', '(');
        bracketLookup.put(']', '[');
        bracketLookup.put('}', '{');

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (bracketLookup.containsKey(c)) {
                if (!brackets.isEmpty() && bracketLookup.get(c).equals(brackets.peek())) {
                    brackets.pop();
                } else {
                    return false;
                }
            } else {
                brackets.push(c);
            }
        }
        return brackets.isEmpty();
    });

    public void test (IntValidParentheses func) {
        System.out.println(func.isValid("()"));
        System.out.println(func.isValid("()[]{}"));
        System.out.println(func.isValid("(]"));
    }

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        validParentheses.test(validParentheses.isValid1st);
        validParentheses.test(validParentheses.isValidSolution);
        validParentheses.test(validParentheses.isValidSolutionWithHashmap);
    }
}
