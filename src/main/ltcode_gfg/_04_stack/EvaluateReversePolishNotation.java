package main.ltcode_gfg._04_stack;

import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation (Medium)
 */
@FunctionalInterface
interface IntEvaluateReversePolishNotation {
    int evalRPN(String[] tokens);
}

public class EvaluateReversePolishNotation {
    IntEvaluateReversePolishNotation intEvaluateRPN1st = (tokens -> {
        Stack<String> nums = new Stack<>();
        String operators = "+-*/";
        for(int i=0; i<tokens.length; i++) {
            if (!operators.contains(tokens[i])) {
                nums.push(tokens[i]);
            } else {
                int n2 = Integer.valueOf(nums.pop());
                int n1 = Integer.valueOf(nums.pop());
                int r = 0;
                switch (tokens[i]) {
                    case "+":
                        r = n1 + n2;
                        break;
                    case "-":
                        r = n1 - n2;
                        break;
                    case "*":
                        r = n1 * n2;
                        break;
                    case "/":
                        r = n1 / n2;
                        break;
                }
                nums.push(String.valueOf(r));
            }
        }
        return Integer.valueOf(nums.pop());
    });

    IntEvaluateReversePolishNotation intEvaluateRPN1stImproved = (tokens -> {
        int[] nums = new int[tokens.length];
        int top = 0;

        for(int i=0; i<tokens.length; i++) {

            if ("+".equals(tokens[i])) {
                int n2 = nums[--top];
                int n1 = nums[--top];
                nums[top++] = n1 + n2;
            } else if ("-".equals(tokens[i])) {
                int n2 = nums[--top];
                int n1 = nums[--top];
                nums[top++] = n1 - n2;
            } else if ("*".equals(tokens[i])) {
                int n2 = nums[--top];
                int n1 = nums[--top];
                nums[top++] = n1 * n2;
            } else if ("/".equals(tokens[i])) {
                int n2 = nums[--top];
                int n1 = nums[--top];
                nums[top++] = n1 / n2;
            } else {
                nums[top++] = Integer.valueOf(tokens[i]);
            }
        }
        return nums[0];
    });

    int idx;
    IntEvaluateReversePolishNotation intEvaluateRPNSol = (tokens -> {
        idx = tokens.length - 1;
        return dfs(tokens);
    });

    private int dfs(String[] tokens) {
        String token = tokens[idx--];
        if ("+-/*".indexOf(token) == -1) {
            return Integer.parseInt(token);
        }

        int left = dfs(tokens);
        int right = dfs(tokens);

        if ("+".equals(token)) return right + left;
        else if ("*".equals(token)) return right * left;
        else if ("/".equals(token)) return right / left;
        else return right - left;
    }

    int pos;
    IntEvaluateReversePolishNotation intEvaluateRPNSol2 = (tokens -> {
        this.pos = tokens.length-1;
        return evaluate(tokens);
    });

    public int evaluate(String[] t){
        String token = t[pos];
        pos--;
        if(token.equals("+")){
            return evaluate(t) + evaluate(t);
        }else
        if(token.equals("*")){
            return evaluate(t) * evaluate(t);
        }else
        if(token.equals("-")){
            return -evaluate(t) + evaluate(t);
        }else
        if(token.equals("/")){
            int right = evaluate(t);
            return evaluate(t) / right;
        }else{
            return Integer.parseInt(token);
        }
    }


    public void test(IntEvaluateReversePolishNotation func) {
        System.out.println("Expected: 9, Actual: " + func.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println("Expected: 6, Actual: " + func.evalRPN(new String[]{"4","13","5","/","+"}));
        System.out.println("Expected: 22, Actual: " + func.evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));
    }

    public static void main(String[] args) {
        EvaluateReversePolishNotation e = new EvaluateReversePolishNotation();
        e.test(e.intEvaluateRPN1st);
        System.out.println("=======");
        e.test(e.intEvaluateRPN1stImproved);
    }
}
