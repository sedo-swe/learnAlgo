package main.ltcode_gfg._04_stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 *  22. Generate Parentheses (Medium)
 */

@FunctionalInterface
interface IntGenerateParentheses {
    List<String> generateParenthesis(int n);
}
public class GenerateParentheses {
    IntGenerateParentheses intGenerateParentheses = (n -> {
        /* When appending "(" to string, push ")" into stack and decrease n by 1
           If n = 0, keep popping until stack is empty and return
           Do either appending "(" or popping
        */
        List<String> answers = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        StringBuffer sb = new StringBuffer();
        recursive(n, sb, stack, answers);
        return answers;
    });

    private void recursive(int n, StringBuffer sb, Stack<String> stack, List<String> answers) {
        // Base case
        if (n == 0) {
            int cnt = 0;
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
                cnt++;
            }
            answers.add(sb.toString());
            while (cnt-1>0) {
                stack.push(")");
                cnt--;
            }
            return;
        }

        // Recursive case
        int length = sb.length();
        sb.append("(");
        stack.push(")");
        recursive(n - 1, sb, stack, answers);
        sb.setLength(length);

        if (!stack.isEmpty()) {
            sb.append(stack.pop());
            recursive(n, sb, stack, answers);
        }
        return;
    }

    IntGenerateParentheses intGenerateParenthesesWithStack = (n -> {
        List<String> answers = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        backtrackingWithStack(0, 0, n, stack, answers);
        return answers;
    });

    IntGenerateParentheses intGenerateParenthesesWithoutStack = (n -> {
        List<String> answers = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        backtrackingNoStack(0, 0, n, sb, answers);
        return answers;
    });

    private void backtrackingWithStack(int openN, int closedN, int n, Stack<String> stack, List<String> answers) {
        // base case
        if (openN == closedN && closedN == n) {
            Iterator<String> iter = stack.iterator();
            String temp = "";
            while (iter.hasNext()) {
                temp += iter.next();
            }
            answers.add(temp);
        }

        if (openN < n) {
            stack.push("(");
            backtrackingWithStack(openN + 1, closedN, n, stack, answers);
            stack.pop();
        }

        if (closedN < openN) {
            stack.push(")");
            backtrackingWithStack(openN, closedN + 1, n, stack, answers);
            stack.pop();
        }
    }

    private void backtrackingNoStack(int openN, int closedN, int n, StringBuffer sb, List<String> answers) {
        /*
            1. valid IIF oepn == closed == n
            2. only add open paranthesis if open < n
            3. only add closing paranthesis if closed < open
         */

        // base case
        if (openN == closedN && closedN == n) {
            answers.add(sb.toString());
        }

        if (openN < n) {
            int length = sb.length();
            backtrackingNoStack(openN + 1, closedN, n, sb.append("("), answers);
            sb.setLength(length);
            // or
            // backtrackingNoStack(openN + 1, closedN, n, sb.append("("), answers);
            // sb.deleteCharAt(sb.length() - 1);
        }

        if (closedN < openN) {
            int length = sb.length();
            backtrackingNoStack(openN, closedN + 1, n, sb.append(")"), answers);
            sb.setLength(length);
        }
    }

    public void test(IntGenerateParentheses func) {
        System.out.println("Expected: [\"((()))\",\"(()())\",\"(())()\",\"()(())\",\"()()()\"], Actual: " + func.generateParenthesis(4));
    }

    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        gp.test(gp.intGenerateParenthesesWithStack);
        gp.test(gp.intGenerateParenthesesWithoutStack);
    }
}
