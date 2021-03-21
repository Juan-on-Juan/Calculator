package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.MotionEventCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.LocationListener;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button eq, ac, plusmin, delete, div, mul, plus, min, jedan, dva, tri, cetiri, pet, sest, sedam, osam, devet, nula, point, slideNorm;
    Button leftpar, rightpar, sqr, pow, sqrt, tenpow, fact, abs, pi, e, log, ln, sin, cos, tan, ctn;
    ImageButton hist;
    TextView tv, res;
    GridLayout standard;
    DataManager DataMan;

    float y1, y2;

    String exp = "", resStr;
    double result;
    DecimalFormat format = new DecimalFormat("0.##");
    boolean standardDown = false;
    boolean noOperatorAfteEq = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Standard Buttons
        eq = (Button) findViewById(R.id.jednako);
        tv = (TextView) findViewById(R.id.expression_field);
        res = (TextView) findViewById(R.id.result_field);
        ac = (Button) findViewById(R.id.AC);
        plusmin = (Button) findViewById(R.id.plusminus);
        delete = (Button) findViewById(R.id.del);
        div = (Button) findViewById(R.id.djeljenje);
        mul = (Button) findViewById(R.id.mnozenje);
        plus = (Button) findViewById(R.id.plus);
        min = (Button) findViewById(R.id.minus);
        point = (Button) findViewById(R.id.tocka);
        slideNorm = (Button) findViewById(R.id.slide);
        hist = (ImageButton) findViewById(R.id.history_icon);
        standard = (GridLayout) findViewById(R.id.standard);

        // Scientific Buttons
        leftpar = (Button)findViewById(R.id.lft_par);
        rightpar = (Button)findViewById(R.id.rght_par);
        sqr = (Button)findViewById(R.id.sqr);
        pow = (Button)findViewById(R.id.exp);
        sqrt = (Button)findViewById(R.id.sqrt);
        tenpow = (Button)findViewById(R.id.ten_pow);
        fact = (Button)findViewById(R.id.fact);
        abs = (Button)findViewById(R.id.abs);
        pi = (Button)findViewById(R.id.pi);
        e = (Button)findViewById(R.id.e);
        log = (Button)findViewById(R.id.log);
        ln = (Button)findViewById(R.id.ln);
        sin = (Button)findViewById(R.id.sin);
        cos = (Button)findViewById(R.id.cos);
        tan = (Button)findViewById(R.id.tan);
        ctn = (Button)findViewById(R.id.ctn);

        // Number Buttons
        jedan = (Button) findViewById(R.id.jedan);
        dva = (Button) findViewById(R.id.dva);
        tri = (Button) findViewById(R.id.tri);
        cetiri = (Button) findViewById(R.id.cetri);
        pet = (Button) findViewById(R.id.pet);
        sest = (Button) findViewById(R.id.sest);
        sedam = (Button) findViewById(R.id.sedam);
        osam = (Button) findViewById(R.id.osam);
        devet = (Button) findViewById(R.id.devet);
        nula = (Button) findViewById(R.id.nula);

        // Database
        DataMan = new DataManager(this);

        eq.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try {
                    result = new Brain().evaluate(exp);
                    resStr = String.valueOf(format.format(result));
                    res.setText(resStr);
                    tv.setText(resStr);
                    AddToDB(exp, resStr);
                    exp = "(" + exp + ")";
                    noOperatorAfteEq = true;
                } catch(Exception e){
                    // Working on it!
                }
            }
        });

        // Buttons Normal
        jedan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkForOperator();
                tv.append("1");
                exp = exp + "1";
                quickEval(exp);
            }
        });

        dva.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkForOperator();
                tv.append("2");
                exp = exp + "2";
                quickEval(exp);
            }
        });

        tri.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkForOperator();
                tv.append("3");
                exp = exp + "3";
                quickEval(exp);
            }
        });

        cetiri.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkForOperator();
                tv.append("4");
                exp = exp + "4";
                quickEval(exp);
            }
        });

        pet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkForOperator();
                tv.append("5");
                exp = exp + "5";
                quickEval(exp);
            }
        });

        sest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkForOperator();
                tv.append("6");
                exp = exp + "6";
                quickEval(exp);
            }
        });

        sedam.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkForOperator();
                tv.append("7");
                exp = exp + "7";
                quickEval(exp);
            }
        });

        osam.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkForOperator();
                tv.append("8");
                exp = exp + "8";
                quickEval(exp);
            }
        });

        devet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkForOperator();
                tv.append("9");
                exp = exp + "9";
                quickEval(exp);
            }
        });

        nula.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                checkForOperator();
                tv.append("0");
                exp = exp + "0";
                quickEval(exp);
            }
        });

        // Operations

        plus.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("+");
                exp = exp + "+";
                noOperatorAfteEq = false;
            }
        });

        min.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("-");
                exp = exp + "-";
                noOperatorAfteEq = false;
            }
        });

        div.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("/");
                exp = exp + "/";
                noOperatorAfteEq = false;
            }
        });

        mul.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("×");
                exp = exp + "*";
                noOperatorAfteEq = false;
            }
        });

        point.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append(".");
                exp = exp + ".";
            }
        });

        // Other

        ac.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.setText("");
                res.setText("0");
                exp = "";
                noOperatorAfteEq = false;
            }
        });

        plusmin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(res.getText().charAt(0) == '-'){
                    res.setText(res.getText().subSequence(1, res.getText().length()));
                } else {
                    res.setText("-" + res.getText());
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(tv.getText().length() >= 1){
                    tv.setText(tv.getText().subSequence(0, tv.getText().length()-1));
                    exp = exp.substring(0, exp.length()-1);
                }
                if (tv.getText().length() < 1){
                    exp = "";
                    res.setText("0");
                }
                quickEval(exp);
            }
        });

        // Buttons Scientific

        leftpar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("(");
                exp = exp + "(";
            }
        });

        rightpar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append(")");
                exp = exp + ")";
            }
        });

        sqr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("^2");
                exp = exp + "^2";
            }
        });

        pow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("^");
                exp = exp + "^";
            }
        });

        sqrt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("√(");
                exp = exp + "sqrt(";
            }
        });

        tenpow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("10^");
                exp = exp + "10^";
            }
        });

        fact.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("fact(");
                exp = exp + "fact(";
            }
        });

        abs.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("abs(");
                exp = exp + "abs(";
            }
        });

        pi.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("π");
                exp = exp + "pi";
            }
        });

        e.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("e");
                exp = exp + "e";
            }
        });

        log.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("log(");
                exp = exp + "log(";
            }
        });

        ln.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("ln(");
                exp = exp + "ln(";
            }
        });

        sin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("sin(");
                exp = exp + "sin(";
            }
        });

        cos.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("cos(");
                exp = exp + "cos(";
            }
        });

        tan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("tan(");
                exp = exp + "tan(";
            }
        });

        ctn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("ctn(");
                exp = exp + "ctn(";
            }
        });

        hist.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                openHist();
            }
        });

        slideNorm.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case(MotionEvent.ACTION_DOWN):
                        y1 = event.getY();
                        break;
                    case(MotionEvent.ACTION_MOVE):
                        standard.setTranslationY(event.getRawY() - 600);
                        break;
                    case(MotionEvent.ACTION_UP):
                        y2 = event.getY();
                        float deltaY = y2 - y1;
                        if(Math.abs(deltaY) > 250){
                            if(deltaY < 0 && standardDown){
                                standard.setTranslationY(0);
                                standardDown = false;
                            } else {
                                standard.setTranslationY(1200);
                                standardDown = true;
                            }
                        }
                        break;
                }
                return true;
            }
        });

    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case(MotionEvent.ACTION_DOWN):
                y1 = event.getY();
                break;
            case(MotionEvent.ACTION_UP):
                y2 = event.getY();
                float deltaY = y2 - y1;
                if(Math.abs(deltaY) > 250){
                    if(deltaY < 0){
                        msg("UP!");
                    } else {
                        msg("DOWN!");
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }*/

    private void checkForOperator(){
        if(noOperatorAfteEq){
            tv.setText("");
            res.setText("0");
            exp = "";
            noOperatorAfteEq = false;
        }
    }

    private void quickEval(String expression){
        try{
            res.setText(String.valueOf(format.format(new Brain().evaluate(expression))));
        } catch (Exception e){

        }
    }

    public void AddToDB(String expression, String result){
        boolean insertData = DataMan.addData(expression, result);
        if(insertData){
            msg("Uspjesno spremljeno!");
        } else {
            msg("Nesto ne radi!");
        }
    }

    public void openHist(){
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }

    public void msg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}