package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void bclicked(View view){
        switch(view.getId()){
            case R.id.bClick:
                EditText editText = (EditText)findViewById(R.id.editText1);
                TextView texView = (TextView)findViewById(R.id.textView);
                texView.setText(editText.getText().toString());
                break;
            default:
                break;
        }
    }
}