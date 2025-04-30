package ch15.sec06.exam01;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Coin> stack = new Stack<>();
        stack.push(new Coin(100));
        stack.push(new Coin(50));
        stack.push(new Coin(500));
        stack.push(new Coin(10));

        while (!stack.isEmpty()) {
            Coin coin = stack.pop();
            System.out.println("꺼내온 동전 : " + coin.getValue() + "원");
        }
    }
}