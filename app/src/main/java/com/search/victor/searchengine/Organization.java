package com.search.victor.searchengine;

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

public class Organization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);
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
        Log.e("Yoo","Hooo");
        String data = inputs.getString("Org ID");
        Log.e("Yo","Wassup");
        final DatabaseHandler dbHandler = new DatabaseHandler(this);
        String results[] = dbHandler.searchOrg(data);
        Log.e("!","1");
        TextView mtResults = (TextView) findViewById(R.id.textView);
        TextView mtLink = (TextView) findViewById(R.id.hyperlink);
        Log.e("2", "2");
        if (results[0].length() != 0) {
            mtResults.setText("Results:\n\nOrganization Name: " + results[0]
                    + "\n\nCost: " + results[1] +
                    "\n\nOther info: " + results[2] + "\n\nEmail: " + results[3] +
                    "\n\nAssistance: " + results[5] +
                    "\n\nAdditional Notables: " + results[6]);
        }
        if (Long.parseLong(results[7],10) > 0)
        {
            mtResults.setText(mtResults.getText() + "\n\nPhone: " + results[7]);
        }
        if (results[8].length() != 0)
        {
            mtResults.setText(mtResults.getText() + "\n\nAddress: " + results[8] + ", " + results[10] + ", " + results[11] + ", " + results[9]);
        }
        if (results[4].length() != 0)
        {
            mtResults.setText(mtResults.getText() + "\n\nWebsite:");
            mtLink.setText(results[4]);
        }
    }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        })

}
