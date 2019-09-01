package io.testpro.deens.practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Stack;

public class BalanceTest {

    String str1 = "()[]{}";
    String str2 = "([]{})";
    String str3 = "([sdf])df{sdf.,})"; // Not valid

    String str4 = "))((";

//    public static void main(String[] args) {
//        balancedParenthensis();
//    }

    public static boolean balancedParenthensis(String s) {

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }
            else if (c==')') {
                if(stack.isEmpty() || stack.pop() != '(') {
                    return false;
                } }
            else if (c==']') {
                if(stack.isEmpty() || stack.pop() != '[') {
                    return false;
                } }
            else if (c=='}') {
                if(stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            }

        }
        return stack.isEmpty();

    }

    @Test
    public void test1() {
        Assert.assertTrue(balancedParenthensis("()[]{}"));
    }

    @Test
    public void negativeTest() {
        Assert.assertFalse(balancedParenthensis("()[]{}("));
    }



//        stack.push('5');
//        stack.push('4');
//        stack.push('3');
//
//        System.out.println(stack);



}
