package my.study.algorithms.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 계산기 {
    // fixme 한계 : 1의 자리만 계산 가능ㅎ 계산 타입을 캐릭터로 해버려서ㅋ...
    public static double calculate(String infixExpression) {
        List<Character> postfix = convertInfixToPostfix(infixExpression);
        return calculatePostfix(postfix);
    }

    protected static List<Character> convertInfixToPostfix(String expression) {
        List<Character> postfix = new ArrayList<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);

            if (isNumber(token)) {
                postfix.add(token);
            } else if (isLeftBracket(token)) {
                operators.push(token);
            } else if (isRightBracket(token)) {
                char topOperator = operators.pop();
                while (!isLeftBracket(topOperator)) {
                    postfix.add(topOperator);
                    topOperator = operators.pop();
                }
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && isHigherPriorityThanLeft(token, operators.peek())) {
                    postfix.add(operators.pop());
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            postfix.add(operators.pop());
        }

        return postfix;
    }

    private static double calculatePostfix(List<Character> postfix) {
        Stack<Double> operands = new Stack<>();
        double num2, num1;

        for (int i = 0; i < postfix.size(); i++) {
            char token = postfix.get(i);
            if (isNumber(token)) {
                operands.push((double)Character.getNumericValue(token));
            } else {
                num2 = operands.pop();
                num1 = operands.pop();
                operands.push(calculateInfix(num1, token, num2));
            }
        }
        return operands.pop();
    }

    private static boolean isOperator(char token) {
        return token == '+' || token == '-' || token == '*' || token == '/';
    }

    private static boolean isLeftBracket(char token) {
        return token == '(';
    }

    private static boolean isRightBracket(char token) {
        return token == ')';
    }

    private static boolean isNumber(char token) {
        return Character.isDigit(token);
    }

    private static boolean isHigherPriorityThanLeft(char token, char operator) {
        return getOperatorPriority(token) < getOperatorPriority(operator);
    }

    private static int getOperatorPriority(char operator) {
        switch (operator) {
            case '(':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 99;
        }
    }

    private static double calculateInfix(double num1, char operator, double num2) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                return 0d; //예외처리 생략
        }
    }
}
