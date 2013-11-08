package ru.unn.agile.calculating;

import java.util.HashMap;
import java.util.Stack;

public class StringCalculator {

    private static final String numericSymbols = "0123456789.";
    private static final String opSymbols = "+-*/";
    private static final String allowedSymbols = numericSymbols + opSymbols + "()\t\r\n ";
    private static final HashMap<Character, Integer> opPriors = new HashMap<Character, Integer>();

    static {
        opPriors.put('+', 1);
        opPriors.put('-', 1);
        opPriors.put('*', 2);
        opPriors.put('/', 2);
        opPriors.put('(', 0);
        opPriors.put(')', 0);
    }

    public static double calculate(String expr) {
        if (expr.isEmpty()) return 0;
        checkSymbols(expr);
        checkParens(expr);
        checkOps(expr);
        Stack<Double> stackArgs = new Stack<Double>();
        Stack<Character> stackOps = new Stack<Character>();
        while (!expr.isEmpty()) {
            char firstSym = expr.charAt(0);
            if (isNumericSymbol(firstSym)) {
                expr = pushNumAndTruncateString(expr, stackArgs);
            } else if (isOpSymbol(firstSym) || firstSym == '(' || firstSym == ')') {
                expr = processOpAndTruncateString(expr, stackArgs, stackOps);
            } else {
                expr = expr.substring(1);
            }
        }
        while (!stackOps.isEmpty()) {
            performOneOp(stackArgs, stackOps);
        }
        if (stackArgs.size() > 1)
            throw new IllegalArgumentException("Not enough operators in expression");
        return stackArgs.pop();
    }

    private static void checkSymbols(String expr) {
        for (int i = 0; i < expr.length(); i++) {
            if (!isAllowedSymbol(expr.charAt(i))) {
                throw new IllegalArgumentException("Undefined symbol in expression");
            }
        }
    }

    private static void checkParens(String expr) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < expr.length(); i++) {
            if ('(' == expr.charAt(i)) {
                stack.push('(');
            }
            if (')' == expr.charAt(i)) {
                if (!stack.isEmpty())
                    stack.pop();
                else
                    throw new IllegalArgumentException("Wrong parens in expression");
            }
        }
        if (!stack.isEmpty())
            throw new IllegalArgumentException("Wrong parens in expression");
    }

    private static void checkOps(String expr) {
        for (int i = 0; i < expr.length(); i++)
            if (isOpSymbol(expr.charAt(i))) {
                if (i == 0 || i == expr.length())
                    throw new IllegalArgumentException("Wrong operator position in expression");
                int index = i - 1;
                while (index >= 0 && !isNumericSymbol(expr.charAt(index))
                        && !isOpSymbol(expr.charAt(index)))
                    index--;
                if (index < 0 || isOpSymbol(expr.charAt(index)))
                    throw new IllegalArgumentException("Wrong operator position in expression");
                index = i + 1;
                while (index < expr.length() && !isNumericSymbol(expr.charAt(index))
                        && !isOpSymbol(expr.charAt(index)))
                    index++;
                if (index >= expr.length() || isOpSymbol(expr.charAt(index)))
                    throw new IllegalArgumentException("Wrong operator position in expression");
            }
    }

    private static String pushNumAndTruncateString(String expr, Stack<Double> stackArgs) {
        int numLength = 1;
        while (numLength < expr.length()
                && isNumericSymbol(expr.charAt(numLength))) {
            numLength++;
        }
        String strNum = expr.substring(0, numLength);
        double num = Double.parseDouble(strNum);
        stackArgs.push(num);
        return expr.substring(numLength);
    }

    private static String processOpAndTruncateString(String expr, Stack<Double> stackArgs, Stack<Character> stackOps) {
        char op = expr.charAt(0);
        switch (op) {
            case '+':
            case '-':
            case '*':
            case '/':
                while (!stackOps.isEmpty()
                        && opPriors.get(op) <= opPriors.get(stackOps.peek())) {
                    performOneOp(stackArgs, stackOps);
                }
                stackOps.push(op);
                break;
            case '(':
                stackOps.push(op);
                break;
            case ')':
                while ('(' != stackOps.peek()) {
                    performOneOp(stackArgs, stackOps);
                }
                stackOps.pop();
                break;
        }
        return expr.substring(1);
    }

    private static void performOneOp(Stack<Double> stackArgs, Stack<Character> stackOps) {
        switch (stackOps.pop()) {
            case '+':
                stackArgs.push(stackArgs.pop() + stackArgs.pop());
                break;
            case '-':
                stackArgs.push(0 - stackArgs.pop() + stackArgs.pop());
                break;
            case '*':
                stackArgs.push(stackArgs.pop() * stackArgs.pop());
                break;
            case '/':
                double arg2 = stackArgs.pop();
                if (arg2 == 0)
                    throw new ArithmeticException("Division by zero");
                stackArgs.push(stackArgs.pop() / arg2);
                break;
        }
    }

    private static boolean isNumericSymbol(char ch) {
        return numericSymbols.indexOf(ch) >= 0;
    }

    private static boolean isOpSymbol(char ch) {
        return opSymbols.indexOf(ch) >= 0;
    }

    private static boolean isAllowedSymbol(char ch) {
        return allowedSymbols.indexOf(ch) >= 0;
    }
}
