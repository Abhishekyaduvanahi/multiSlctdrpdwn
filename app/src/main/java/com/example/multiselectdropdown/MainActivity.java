package com.example.multiselectdropdown;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    //here we are Initializing the variable
    TextView tvDay;
    // declared boolean array
    boolean[] selectDay;

    ArrayList<Integer> dayList =new ArrayList<>();
    String [] dayArray ={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //here I assigned Variable

        tvDay=findViewById(R.id.tv_day);

        // Initialize selected day array
        tvDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize alert dialog
                AlertDialog.Builder builder=new AlertDialog.Builder(
                        MainActivity.this
                );
                //Set title

                builder.setTitle("Select Day");
                // Set dialog non cancelable
                builder.setCancelable(false);

                builder.setMultiChoiceItems(dayArray, selectDay, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        // Check condition

                        if(b){
                            //when check Box is selected
                            //Add position in dayList
                            dayList.add(i);
                            //Sort day List;
                            Collections.sort(dayList);
                        }
                        else{
                            //when checkbox unselected
                            //Remove position from day list
                            dayList.remove(i);

                        }
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Initialize string builder

                        StringBuilder stringBuilder = new StringBuilder();
                        //use for loop
                        for(int j=0;j<dayList.size();j++){
                            // Concat array value
                            stringBuilder.append(dayArray[dayList.get(j)]);
                            //Check Condition
                            if(j!=dayList.size()-1){
                                // when j value not equal to day list size -1
                                //Add comma
                                stringBuilder.append(" , ");
                            }
                        }
                        // Set text On text View

                        tvDay.setText(stringBuilder.toString());

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       //Dismiss dialog
                       dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("ClearAll", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //use for loop

                        for(int j=0;j<selectDay.length;j++){
                            //Remove all selection
                            selectDay[j]=false;
                            //Clear day List
                            dayList.clear();
                            // Clear text view value
                            tvDay.setText(" ");
                        }
                    }
                });
                //Show dialog
                builder.show();

            }
        });


    }
}