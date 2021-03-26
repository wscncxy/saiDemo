package com.sai.demo.design;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Interpreter {
    public static void main(String[] args) {
        String expStr= "a+b-c";
        Map<String, Integer> var = new HashMap<>();
        var.put("a",100);
        var.put("b",20);
        var.put("c",10);

        new Calculator(expStr).run(var);
    }
}

interface Expression{
    Integer interpreter(Map<String, Integer> ctx);
}

class VarExpression implements Expression{

    private String key;

    public VarExpression(String key){
        this.key = key;
    }

    @Override
    public Integer interpreter(Map<String, Integer> ctx) {
        return ctx.get(this.key);
    }
}

abstract class SymbolExpression implements Expression{

    protected  Expression left;
    protected  Expression right;

    public SymbolExpression(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }
}


class AddExpression extends SymbolExpression{
    public AddExpression(Expression left, Expression right){
        super(left,right);
    }
    @Override
    public Integer interpreter(Map<String, Integer> ctx) {
        return super.left.interpreter(ctx) + super.right.interpreter(ctx);
    }
}


class SubExpression extends SymbolExpression{
    public SubExpression(Expression left, Expression right){
        super(left,right);
    }
    @Override
    public Integer interpreter(Map<String, Integer> ctx) {
        return super.left.interpreter(ctx) + super.right.interpreter(ctx);
    }
}

class Calculator{
    private Expression expression;
    public Calculator(String expStr){
        Stack<Expression> stack = new Stack<>();
        char[] charArray = expStr.toCharArray();
        Expression left = null;
        Expression right = null;

        for(int i=0;i<charArray.length;i++){
            switch (charArray[i]){
                case '+':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new AddExpression(left,right));
                    break;
                case '-':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new SubExpression(left,right));
                default:
                    stack.push(new VarExpression(String.valueOf(charArray[i])));
            }
        }
        this.expression = stack.pop();
    }

    public int run(Map<String, Integer> ctx){
        return this.expression.interpreter(ctx);
    }
}
