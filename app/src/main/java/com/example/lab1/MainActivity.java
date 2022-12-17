package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] colors = { "White", "Red", "Yellow", "Cyan"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // spinner stuff
        Spinner spin = findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, colors);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(ad);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Button btn = (Button)findViewById(R.id.bClick);
        switch (colors[i]){
            case "White":
                btn.setTextColor(Color.WHITE);
                break;
            case "Red":
                btn.setTextColor(Color.RED);
                break;
            case "Yellow":
                btn.setTextColor(Color.YELLOW);
                break;
            case "Cyan":
                btn.setTextColor(Color.CYAN);
                break;
            default:break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    public void windowpop(View view){
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
    public void bclicked(View view){
        switch(view.getId()){
            case R.id.bClick:
                EditText editText = (EditText)findViewById(R.id.editText1);
                TextView texView = (TextView)findViewById(R.id.textView);
                texView.setText(editText.getText().toString());
                windowpop(view);
                break;
            case R.id.bShare:
                EditText editText2 = (EditText)findViewById(R.id.editText1);
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = editText2.getText().toString();
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;
            case R.id.bSearch:
                EditText editText3 = (EditText)findViewById(R.id.editText1);
                String searchBody = editText3.getText().toString();
                //String escapedQuery = URLEncoder.encode(searchBody, "UTF-8");
                Uri uri = Uri.parse("http://www.google.com/search?q=" + searchBody);
                Intent searchIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(searchIntent);
                break;
            default:break;
        }
    }
    public void pop_bposclicked(View view){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        switch(view.getId()){
            case R.id.popbClicked:
                Toast toast = Toast.makeText(context, "Positive!", duration);
                toast.show();
                break;
            case R.id.popb2Clicked:
                toast = Toast.makeText(context, "Negative!", duration);
                toast.show();
                break;
            default:
                break;
        }
    }
}