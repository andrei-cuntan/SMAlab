package lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lab1.R;

public class ActivityA extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        setTitle("A");
        Log.d(TAG, "onCreate A");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume A");
    }
    public void clicky(View view){
        switch(view.getId()){
            case R.id.button1:
                startActivity(new Intent(this, ActivityA.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this, ActivityB.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this, ActivityC.class));
                break;
            default:break;
        }
    }
}