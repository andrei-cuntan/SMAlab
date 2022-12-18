package lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lab1.R;

public class ActivityC extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        setTitle("C");
        Log.d(TAG, "onCreate C");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume C");
    }
    public void clicky(View view){
        switch(view.getId()){
            case R.id.button7:
                startActivity(new Intent(this, ActivityA.class));
                break;
            case R.id.button8:
                startActivity(new Intent(this, ActivityB.class));
                break;
            case R.id.button9:
                startActivity(new Intent(this, ActivityC.class));
                break;
            default:break;
        }
    }
}