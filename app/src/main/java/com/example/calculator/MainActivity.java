package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button eq, ac, plusmin, delete, div, mul, plus, min, jedan, dva, tri, cetiri, pet, sest, sedam, osam, devet, nula, point, slideNorm;
    ImageButton hist;
    TextView tv, res;
    GridLayout standard;
    DataManager DataMan;

    String exp = "", resStr;
    double result;
    DecimalFormat format = new DecimalFormat("0.##");
    boolean standardDown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Functional Buttons
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
                    result = Brain.evaluate(exp);
                    resStr = String.valueOf(format.format(result));
                    res.setText(resStr);
                    tv.setText(resStr);
                    AddToDB(exp, resStr);
                    exp = "(" + exp + ")";
                } catch(Exception e){
                    // Working on it!
                }
            }
        });

        // Buttons
        jedan.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("1");
                exp = exp + "1";
                quickEval(exp);
            }
        });

        dva.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("2");
                exp = exp + "2";
                quickEval(exp);
            }
        });

        tri.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("3");
                exp = exp + "3";
                quickEval(exp);
            }
        });

        cetiri.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("4");
                exp = exp + "4";
                quickEval(exp);
            }
        });

        pet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("5");
                exp = exp + "5";
                quickEval(exp);
            }
        });

        sest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("6");
                exp = exp + "6";
                quickEval(exp);
            }
        });

        sedam.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("7");
                exp = exp + "7";
                quickEval(exp);
            }
        });

        osam.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("8");
                exp = exp + "8";
                quickEval(exp);
            }
        });

        devet.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("9");
                exp = exp + "9";
                quickEval(exp);
            }
        });

        nula.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
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
            }
        });

        min.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("-");
                exp = exp + "-";
            }
        });

        div.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("/");
                exp = exp + "/";
            }
        });

        mul.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tv.append("×");
                exp = exp + "*";
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

        hist.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                openHist();
            }
        });

        slideNorm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(!standardDown){
                    standard.setTranslationY(1200);
                    standardDown = true;
                } else {
                    standard.setTranslationY(0);
                    standardDown = false;
                }
            }
        });

    }

    private void quickEval(String expression){
        try{
            res.setText(String.valueOf(format.format(Brain.evaluate(expression))));
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

    private void msg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}