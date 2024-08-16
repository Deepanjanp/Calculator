package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class Demo extends AppCompatActivity implements View.OnClickListener {

    TextView type,result;
    Button allclear,dot,equals,fb,sb;
    Button divide,multiply,plus,minus,remainder;
    Button one,two,three,four,five,six,seven,eight,nine,zero;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        type=(TextView) findViewById(R.id.type);
        result=(TextView) findViewById(R.id.result);

        assignId(multiply,R.id.multiply);
        assignId(divide,R.id.divide);
        assignId(plus,R.id.plus);
        assignId(minus,R.id.minus);

        assignId(one,R.id.one);
        assignId(two,R.id.two);
        assignId(three,R.id.three);
        assignId(four,R.id.four);
        assignId(five,R.id.five);
        assignId(six,R.id.six);
        assignId(seven,R.id.seven);
        assignId(eight,R.id.eight);
        assignId(nine,R.id.nine);
        assignId(zero,R.id.zero);

        assignId(allclear,R.id.allclear);
        assignId(dot,R.id.dot);
        assignId(equals,R.id.equals);
        assignId(fb,R.id.firstbracket);
        assignId(sb,R.id.secondbracket);
        assignId(remainder,R.id.remainder);


    }

    void assignId(Button btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Button press=(Button) v;
        String textbutton = press.getText().toString();
        String cal = type.getText().toString();

        if (textbutton.equals("AC")){
            type.setText("");
            result.setText("0");
            return;
        }



        if (textbutton.equals("=")){
            type.setText(result.getText());
            return;
        }

        cal=cal+textbutton;
        type.setText(cal);

        String finalResult = Result(cal);

        if (!finalResult.equals("Error!!")){
            result.setText(finalResult);
        }

    }

    String Result(String data)
    {
        try {
            Context context =Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result= context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(result.endsWith(".0")){
                result=result.replace(".0","");
            }
            return result;
        }catch (Exception e){
            return "Error!!";
        }
    }

}