/*
 * Copyright (c) 2020 LIANYIN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.behavioral;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe: 解释器模式
 * 定义一种语言文法，并建立解释器来解释该语言中的句子。
 * example：
 * 定义英文的加减乘除，例如：
 *  1+1 表达式写成 one add one 或者 one one add 或者 add one one
 *  2-1 表达式写成 two sub one 或者 sub two one 或者 two one sub
 * @author: linjuanjuan
 * @date: 2020-05-19 15:39
 */
public class InterpreterPattern {

    public static void main(String[] args) {
        String exp = "one add two";

        Context context = new Context();
        context.put("one", 1);
        context.put("two", 2);
        ExpressionParse expressionParse = new ExpressionParse();
        int result = expressionParse.parse(exp, context);
        System.out.println(result);
    }

}

/**
 * 环境类
 */
class Context {

    private final Map<String, Integer> map = new HashMap<>();

    public void put(String key, Integer value) {
        map.put(key, value);
    }

    public Integer get(String key) {
        Integer value = map.get(key);
        if (value == null) {
            throw new RuntimeException("参数不存在");
        }
        return value;
    }

}

/**
 * 抽象解释器
 */
abstract class Interpreter {

    public abstract int interpret(Context context);

}

/**
 * 非终结符表达式  +
 */
class AddInterpreter extends Interpreter {

    // 左终结符
    private Interpreter left;
    // 右终结符
    private Interpreter right;

    public AddInterpreter(Interpreter left, Interpreter right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) + right.interpret(context);
    }
}

/**
 * 非终结符表达式  -
 */
class SubInterpreter extends Interpreter {

    // 左终结符
    private Interpreter left;
    // 右终结符
    private Interpreter right;

    public SubInterpreter(Interpreter left, Interpreter right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) - right.interpret(context);
    }
}

/**
 * 终结符表达式
 */
class Variable extends Interpreter {

    private String key;

    public Variable(String key) {
        this.key = key;
    }

    @Override
    public int interpret(Context context) {
        return context.get(key);
    }
}

/**
 * 语法解析器
 */
class ExpressionParse {

    public int parse(String expression, Context context) {
        List<String> exps = parseStr(expression);
        if (exps.size() != 3) {
            throw new RuntimeException("语法格式错误");
        }
        // 0-操作符 1/2为数值
        String[] exp = new String[3];
        for (String temp : exps) {
            if (isOperator(temp)) {
                exp[0] = temp;
            } else {
                if (exp[1] == null || exp[1].length() == 0) {
                    exp[1] = temp;
                } else {
                    exp[2] = temp;
                }
            }
        }

        return getExpression(exp[1], exp[2], exp[0]).interpret(context);
    }

    // 截取字符串
    private List<String> parseStr(String expression) {
        String[] exps = expression.trim().split(" ");
        return Arrays.asList(exps);
    }

    // 判断是否是非终结符
    private boolean isOperator(String symbol) {
        return "add".equals(symbol) || "sub".equals(symbol);
    }

    // 计算
    private Interpreter getExpression(String left, String right, String symbol) {
        if ("add".equals(symbol)) {
            return new AddInterpreter(new Variable(left), new Variable(right));
        } else if ("sub".equals(symbol)) {
            return new SubInterpreter(new Variable(left), new Variable(right));
        } else {
            throw new RuntimeException("操作符非法");
        }
    }

}