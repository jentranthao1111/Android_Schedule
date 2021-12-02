package com.example.schedule1;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class ChangeSchedule extends AppCompatActivity implements View.OnClickListener {
    EditText eDescription;
    Button btnReturn;
    String data;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_change_schedule);
        initialize();
    } // func onCreate


    private void initialize(){
        eDescription = findViewById(R.id.edDescription);
        btnReturn = findViewById(R.id.btnReturn);

        data = getIntent().getStringExtra("schedule"); // intent from main activity
        eDescription.setText(data);
        btnReturn.setOnClickListener(this);
    } // func initialize


    @Override
    public void onClick(View view) {
        String newData = eDescription.getText().toString();
        Intent intent = new Intent();

        if(data.equals(newData)){
            setResult(RESULT_CANCELED, intent); // an automatic function
        }else{
            intent.putExtra("new_schedule",newData);
            setResult(RESULT_OK, intent);
        } // end if


        finish(); // automatic function - to close the page after intent

    } // func onClick

} // end class
