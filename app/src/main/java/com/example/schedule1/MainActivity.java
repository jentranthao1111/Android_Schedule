package com.example.schedule1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schedule1.model.Schedule;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView [] listOfTextView;
    int widget[] = {R.id.tvMondayMorning, R.id.tvMondayAfterEvening,
            R.id.tvTuesdayMornAfter, R.id.tvTuesdayEvening};

    Schedule[] listOfSchedule;
    TextView clickedTv;

    ActivityResultLauncher<Intent> activityResultLauncher ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    } // func onCreate


    private void initialize(){
        listOfSchedule = new Schedule[4];
        listOfSchedule[0] = new Schedule(1, "Android", Color.RED);
        listOfSchedule[1] = new Schedule(2, "Work", Color.BLUE);
        listOfSchedule[2] = new Schedule(3, "Training", Color.BLACK);
        listOfSchedule[3] = new Schedule(4, "SQL", Color.MAGENTA);

        listOfTextView = new TextView[4];
        for (int i = 0; i < 4; i++){
            listOfTextView[i] = findViewById(widget[i]);
            listOfTextView[i].setText(listOfSchedule[i].toString()); // why cannot return setDescription
            listOfTextView[i].setTextColor(listOfSchedule[i].getColor());
            listOfTextView[i].setOnClickListener(this); // has to implement listener

        }

        // Register to the Activity Result
        activityResultLauncher =  registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    // In this method, we will process the result sent by ChangeSchedule
                    @Override
                    public void onActivityResult(ActivityResult result){
                        if (result.getResultCode() == RESULT_OK && result.getData() != null){
                            String newData = result.getData().getStringExtra("new_schedule");
                            clickedTv.setText(newData);
                        }else{
                            if(result.getResultCode() == RESULT_CANCELED){
                               //Toast.makeText(MainActivity.this, "No change in this schedule", Toast.LENGTH_SHORT).show();
                                noResult();
                            }
                        }
                    } // func onActivityResult

                    private void noResult() {
                        Toast.makeText(MainActivity.this, "No Change in the schedule", Toast.LENGTH_SHORT).show();
                    } // func noResult

                } // para ActivityResultCallback<ActivityResult>()

        ); // registerForActivityResult()

    } // func initialize


    @Override
    public void onClick(View view) {
        // Launch the activity
        clickedTv = (TextView) view;
        Intent intent = new Intent(this, ChangeSchedule.class);
        intent.putExtra("schedule", clickedTv.getText().toString());
        activityResultLauncher.launch(intent);
        //finish();

    } // func onClick

} // end class