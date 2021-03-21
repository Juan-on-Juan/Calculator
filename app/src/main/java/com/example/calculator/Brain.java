package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.Function;
import com.fathzer.soft.javaluator.Parameters;

import java.util.Iterator;

public class Brain extends DoubleEvaluator {

    private static final Function SQRT = new Function("sqrt", 1);
    private static final Function FACT = new Function("fact", 1);
    private static final Parameters PARAMS;

    static {
        PARAMS = DoubleEvaluator.getDefaultParameters();
        PARAMS.add(SQRT);
        PARAMS.add(FACT);
    }

    public Brain(){
        super(PARAMS);
    }

    private double getFact(double n) {
        try{
            if (n <= 2) {
                return n;
            }
            return n * getFact(n - 1);
        } catch (Error e) {
            return 0;
        }
    }

    @Override
    protected Double evaluate(Function function, Iterator<Double> arguments, Object evaluationContext){
        if(function == SQRT){
            return Math.sqrt(arguments.next());
        } else if(function == FACT){
            return getFact(arguments.next());
        } else {
            return super.evaluate(function, arguments, evaluationContext);
        }
    }
}
