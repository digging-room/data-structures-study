package week03;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;

class TimoParenthesisStringTest {

    @Test
    public void test() {
        assertThat(solution("(())())")).isEqualTo("NO");
        assertThat(solution("(((()())()")).isEqualTo("NO");
        assertThat(solution("(()())((()))")).isEqualTo("YES");
        assertThat(solution("((()()(()))(((())))()")).isEqualTo("NO");
        assertThat(solution("()()()()(()()())()")).isEqualTo("YES");
        assertThat(solution("(()((())()(")).isEqualTo("NO");
    }

    private String solution(String input) {
        Stack<Character> stack = new Stack<>();
        for (char ch : input.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else {
                if (stack.empty()) {
                    return "NO";
                }
                stack.pop();
            }
        }
        return stack.isEmpty() ? "YES" : "NO";
    }
}