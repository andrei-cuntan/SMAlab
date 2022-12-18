package lifecycle.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lab1.R;

public class IntentFilterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_filter);
        TextView textView = findViewById(R.id.textView6);
        Uri uri = getIntent().getData();
        textView.setText(uri.toString());
    }
}