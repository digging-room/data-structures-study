class Stack<T> {
  private items: T[] = []

  get len() { // O(1)
    return this.items.length
  }

  get top() { // O(1)
    // 빈 배열이면 undefined
    return this.items[this.len - 1]
  }

  push(item: T) { // O(1)
    this.items.push(item)
  }

  pop() { // O(1)
    // 빈 배열이면 undefined
    return this.items.pop()
  }
}

const toPostfixExpression = (expression: string) => {
  const operators = ['+', '-', '*', '/', '(', ')'] as const
  const operatorStack = new Stack<typeof operators[number]>()
  const operatorPriorityMap = {
    '(': 0,
    '+': 1,
    '-': 1,
    '*': 2,
    '/': 2,
    ')': 3,
  }

  let result = ''

  for (const token of expression.split('')) {
    const isOperand = !operators.includes(token as any)

    // 피연산자면 결과값에 이어붙임
    if (isOperand) {
      result += token

      // (인 경우 count를 늘리고 stack에 저장
    } else if (token === '('){
      operatorStack.push(token)

      // )인 경우 count를 줄이고 stack에서 )가 나올 때까지 pop
    } else if (token === ')') {
      let operator = operatorStack.pop()
      while(operator !== '(') {
        result += operator
        operator = operatorStack.pop()
      }
    } else {
      const currentOperator = token as keyof typeof operatorPriorityMap
      let topOperator = operatorStack.top // undefined

      // stack에 쌓인 연산자가 없거나 괄호가 열려있으면 항상 stack에 push
      if (operatorStack.len === 0) {
        operatorStack.push(currentOperator)
      } else {
        while(operatorPriorityMap[currentOperator] < operatorPriorityMap[topOperator]) {
          result += operatorStack.pop()
          topOperator = operatorStack.top
        }

        operatorStack.push(currentOperator)
      }
    }
  }

  // 순회가 끝난 뒤 stack에 남아있는 연산자 결과값에 이어붙임
  while(operatorStack.len > 0) {
    result += operatorStack.pop()
  }

  return result
}

const calculateExpression = (expression: string) => {
  const postfixExpression = toPostfixExpression(expression)
  const operators = ['+', '-', '*', '/'] as const
  const operandStack = new Stack<number>()

  for (const token of postfixExpression.split('')) {
    const isOperand = !operators.includes(token as any)

    // 피연산자면 stack에 push
    if (isOperand) {
      operandStack.push(Number(token))

      // 연산자면 앞에 2개의 피연산자에 해당 연산 수행한 뒤 결과값 stack에 push
    } else {
      const number1 = operandStack.pop()!
      const number2 = operandStack.pop()!

      switch(token) {
        case '+':
          operandStack.push(number2 + number1)
          break
        case '-':
          operandStack.push(number2 - number1) // 연산 시 number2가 앞에 와야 정상 동작함
          break
        case '*':
          operandStack.push(number2 * number1)
          break
        case '/':
          operandStack.push(number2 / number1) // 연산 시 number2가 앞에 와야 정상 동작함
          break
        default:드
          throw new Error('Invalid operator')
      }
    }
  }

  // 최종적으로 남아있는 결과값을 리턴
  return operandStack.pop()
}

console.log(calculateExpression('2*3'))
