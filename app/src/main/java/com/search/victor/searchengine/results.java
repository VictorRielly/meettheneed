package com.search.victor.searchengine;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class results extends AppCompatActivity {

    public List<Button> myButtons = new ArrayList<Button>();
    Integer buttonnum = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button exitButton = (Button) findViewById(R.id.exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        setSupportActionBar(toolbar);
        Bundle inputs = this.getIntent().getExtras();
        String data[] = inputs.getStringArray("Querry Strings");
        Log.e("data",String.valueOf(data.length));
        Log.e("data[0]",String.valueOf(data[0].length()));
        final DatabaseHandler dbHandler = new DatabaseHandler(this);
//        data[0] = inputTextName.getText().toString();
//        data[1] = inputTextCategory.getText().toString();
//        data[2] = inputTextZip.getText().toString();
//        data[3] = inputTextCity.getText().toString();
//        data[4] = inputTextState.getText().toString();
//        data[5] = inputTextCost.getText().toString();
//        data[6] = inputTextPopulation.getText().toString();
//        data[7] = inputTextLanguage.getText().toString();
        final Cursor results[] = dbHandler.search(data);
        Log.e("Length:",String.valueOf(results.length));
        int output[] = new int[5];
        for (int i = 0; i < output.length; ++i)
        {
            output[i] = 0;
        }
        Log.e("Here","Yep");
        if (results[0].moveToFirst()) {
            // Creates a new button for this organization
            myButtons.add(new Button(this));
            Log.e("Here", "Here?");
            myButtons.get(myButtons.size() - 1).setText(results[0].getString(1));
            myButtons.get(myButtons.size() - 1).setTag(results[0].getString(0));
            //myButton.setId(0);

            myButtons.get(myButtons.size() - 1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle inputs = new Bundle();
                    inputs.putString("Org ID", v.getTag().toString());
                    Intent intentData = new Intent(results.this,Organization.class);
                    intentData.putExtras(inputs);
                    Log.e("The data:", ((Button) v).getText().toString() + ", ID: " + v.getTag().toString());
                    startActivity(intentData);
                }
            });


            LinearLayout myScrollLinearView = (LinearLayout)findViewById(R.id.theLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            myScrollLinearView.addView(myButtons.get(myButtons.size() - 1), lp);



            while (results[0].move(1)) {
                // Creates a new button for this organization
                myButtons.add(new Button(this));
                myButtons.get(myButtons.size() - 1).setText(results[0].getString(1));
                myButtons.get(myButtons.size() - 1).setTag(results[0].getString(0));
                //myButton.setId(v);

                myButtons.get(myButtons.size() - 1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle inputs = new Bundle();
                        inputs.putString("Org ID", v.getTag().toString());
                        Intent intentData = new Intent(results.this,Organization.class);
                        intentData.putExtras(inputs);
                        Log.e("The data:", ((Button)v).getText().toString() + ", ID: " + v.getTag().toString());
                        startActivity(intentData);
                    }
                });

                myScrollLinearView.addView(myButtons.get(myButtons.size() - 1), lp);

            }
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


}
