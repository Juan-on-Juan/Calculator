package com.example.calculator;

import com.fathzer.soft.javaluator.DoubleEvaluator;

public class Brain {

    public static double evaluate(String expression){
        DoubleEvaluator evaluator = new DoubleEvaluator();
        double result = evaluator.evaluate(expression);
        return result;
    }

    public static void typoCheck(String expression){
        /* Ova funkcija biti će korištena ukoliko evaluator ne uspije dati rezultati i ona će nas
        upozoriti gdje se nalazi greška */
    }
}
