package com.upt.cti.smartwallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import model.MonthlyExpenses;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private TextView tStatus;
    private EditText eSearch, eIncome, eExpenses;
    private DatabaseReference databaseReference;
    private String currentMonth;
    ValueEventListener databaseListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tStatus = (TextView) findViewById(R.id.tStatus);
        eSearch = (EditText) findViewById(R.id.eSearch);
        eIncome = (EditText) findViewById(R.id.eIncome);
        eExpenses = (EditText) findViewById(R.id.eExpenses);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
    }
    private void createNewDBListener() {
        // remove previous databaseListener

        if (databaseReference != null && currentMonth != null && databaseListener != null)
            databaseReference.child("calendar").child(currentMonth).removeEventListener(databaseListener);

        databaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                MonthlyExpenses monthlyExpense = dataSnapshot.getValue(MonthlyExpenses.class);
                // explicit mapping of month name from entry key
                monthlyExpense.month = dataSnapshot.getKey();

                eIncome.setText(String.valueOf(monthlyExpense.getIncome()));
                eExpenses.setText(String.valueOf(monthlyExpense.getExpenses()));
                tStatus.setText("Found entry for " + currentMonth);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        };

        // set new databaseListener
        databaseReference.child("calendar").child(currentMonth).addValueEventListener(databaseListener);
    }
    private void addstuffDB(){
        EditText mon = (EditText) findViewById(R.id.eSearch);
        EditText inc = (EditText) findViewById(R.id.eIncome);
        EditText exp = (EditText) findViewById(R.id.eExpenses);
        MonthlyExpenses monthlyExpenses = new MonthlyExpenses(
                mon.getText().toString(),
                Float.parseFloat(inc.getText().toString()),
                Float.parseFloat(exp.getText().toString())
        );
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("calendar").add(monthlyExpenses)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("firebase", "Error adding document", e);
                    }
                });
    }
    private void writestuffDB() {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference months = db.collection("calendar");
        Log.d("firebase","data1.toString()");
        /*MonthlyExpenses monthlyExpenses = new MonthlyExpenses(
                eSearch.getText().toString(),
                Float.parseFloat(eIncome.getText().toString()),
                Float.parseFloat(eExpenses.getText().toString())
        );*/
        Log.d("firebase","data1.toString()");
        Map<String, Object> data1 = new HashMap<>();
        data1.put("income", "20");
        data1.put("expenses", "20");
        data1.put("month", "USA");
        //Map<String, Object> postValues =monthlyExpenses.toMap();
        Log.d("firebase","data1.toString()");
        months.document("aaa").set(data1);
    }
    public void clicked(View view){
        switch(view.getId()){
            case R.id.buttonC:
                Log.d("firebase", "clicked buttonC");
                addstuffDB();
                //writestuffDB();

                break;
            case R.id.buttonS:
                if(!eSearch.getText().toString().isEmpty()){
                    currentMonth = eSearch.getText().toString().toLowerCase();
                    tStatus.setText("Searching...");
                    createNewDBListener();
                }
                else{
                    Toast.makeText(this, "Search field is empty!", Toast.LENGTH_SHORT);
                }
                break;
            default:break;
        }
    }
}