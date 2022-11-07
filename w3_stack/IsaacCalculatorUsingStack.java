package week03;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class IsaacCalculatorUsingStack {
    private final Stack<String> outstack = new Stack<>();
    private final Stack<String> opstack = new Stack<>();
    private final List<String> operators = List.of("+", "-", "*", "/", "(", ")");
    private final Map<String, Integer> priorityMap= Map.of("+", 1, "-", 1, "*", 2, "/", 2, "(", 0,")", 0);
    private static final List<String> testData = List.of("2*(3+5)","2*3+5","2+3*5","(2+3+5))*1");

    public static void main(String[] args) {
        int testNum = 0;
        IsaacCalculatorUsingStack calculator = new IsaacCalculatorUsingStack();
        String value = testData.get(testNum).trim();
        String[] tokens = value.split("");

        if(!calculator.validateBrackets(tokens)) {
            throw new IllegalArgumentException("Invalid Expression");
        }

        Stack<String> postFix = calculator.convertToPostfix(value.split(""));
        Integer result = calculator.calculate(postFix);
        System.out.println("postfix is : "+ postFix );
        System.out.println("result : " + result);
    }

    private boolean validateBrackets(String[] tokens){
        int openCount = 0, closeCount = 0;
        for (String token : tokens) {
            if (token.equals("(")) {
                openCount++;
            } else if (token.equals(")")){
                closeCount++;
            }
        }

        return openCount == closeCount;
    }

    private Integer calculate(Stack<String> outstack){
        Stack<Integer> resultStack = new Stack<>();
        for(String token : outstack){
            if (operators.contains(token)) {
                int a = resultStack.pop();
                int b = resultStack.pop();
                Integer result = operate(a, b, token);
                resultStack.push(result);
            } else {
                resultStack.push(Integer.parseInt(token));
            }
        }
        return resultStack.pop();
    }

    public Integer operate(int a, int b, String operator){
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };
    }

    public Stack<String> convertToPostfix(String[] tokens){
        for(String token : tokens){
            if (!operators.contains(token)) {
                outstack.push(token);
            } else {
                if (token.equals("(")) {
                    opstack.push(token);
                } else if (token.equals(")")) {
                    while(opstack.size() != 0 && !opstack.peek().equals("(")){
                        outstack.push(opstack.pop());
                    }
                    opstack.pop();
                } else {
                    if(opstack.isEmpty() || priorityMap.get(token) >= priorityMap.get(opstack.peek()) ){
                        opstack.push(token);
                    } else {
                        while(!opstack.isEmpty() && priorityMap.get(opstack.peek()) > priorityMap.get(token)) {
                            outstack.push(opstack.pop());
                        }
                        opstack.push(token);
                    }
                }
            }
        }
        while(opstack.size() != 0) {
            outstack.push(opstack.pop());
        }

        return outstack;
    }
}
