package lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lab1.R;

public class ActivityB extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        setTitle("B");
        Log.d(TAG, "onCreate B");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume B");
    }
    public void clicky(View view){
        switch(view.getId()){
            case R.id.button4:
                startActivity(new Intent(this, ActivityA.class));
                break;
            case R.id.button5:
                startActivity(new Intent(this, ActivityB.class));
                break;
            case R.id.button6:
                startActivity(new Intent(this, ActivityC.class));
                break;
            default:break;
        }
    }
}