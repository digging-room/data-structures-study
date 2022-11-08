package my.study.algorithms.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class 계산기Test extends 계산기 {
    @MethodSource("generateInfixToPostfixData")
    @ParameterizedTest
    void convertInfixToPostfix(String infixExpression, List<Character> expectedPostfixExpression) {
        // when/then
        assertThat(계산기.convertInfixToPostfix(infixExpression)).isEqualTo(expectedPostfixExpression);
    }

    @MethodSource("generateInfixToPostfixData")
    @ParameterizedTest
    void calculate(String infixExpression, List<Character> expectedPostfixExpression, Double expectedValue) {
        // when/then
        assertThat(계산기.calculate(infixExpression)).isEqualTo(expectedValue);
    }

    static Stream<Arguments> generateInfixToPostfixData() {
        return Stream.of(
                Arguments.of("2*(3+5)", List.of('2', '3', '5', '+', '*'), 16d),
                Arguments.of("2*3+5", List.of('2', '3', '*', '5', '+'), 11d),
                Arguments.of("2+3*5", List.of('2', '3', '5', '*', '+'), 17d),
                Arguments.of("(2+(3+5))*1", List.of('2', '3', '5', '+', '+', '1', '*'), 10d)
        );
    }
}