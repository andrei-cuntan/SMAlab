package lifecycle.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.lab1.R;

import lifecycle.ActivityA;
import lifecycle.ActivityB;
import lifecycle.ActivityC;

public class MainIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intent);
    }
    public void clicky(View view){
        switch(view.getId()){
            case R.id.button10:
                Uri uri = Uri.parse("http://www.google.com");
                Intent searchIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(searchIntent);
                break;
            case R.id.button11:
                uri = Uri.parse("tel:00401213456");
                searchIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(searchIntent);
                break;
            case R.id.button12:
                uri = Uri.parse("http://www.google.com");
                searchIntent = new Intent("MSA.LAUNCH", uri);
                startActivity(searchIntent);
                break;
            case R.id.button13:
                uri = Uri.parse("tel:00401213456");
                searchIntent = new Intent("MSA.LAUNCH", uri);
                startActivity(searchIntent);
                break;
            default:break;
        }
    }
}