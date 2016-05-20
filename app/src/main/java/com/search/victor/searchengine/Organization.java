package com.search.victor.searchengine;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
        final String results[] = dbHandler.searchOrg(data);
        Log.e("!","1");
        TextView mtResults = (TextView) findViewById(R.id.textView);
        TextView mtLink = (TextView) findViewById(R.id.hyperlink);
        Log.e("2", "2");
        if (results[0].length() != 0) {
            mtResults.setText("\n\n\nResults:\n\nOrganization Name: " + results[0]
                    + "\n\nCost: " + results[1] +
                    "\n\nOther info: " + results[2] + "\n\nEmail: " + results[3] +
                    "\n\nAssistance: " + results[5] +
                    "\n\nAdditional Notables: " + results[6]);
        }
        if (Long.parseLong(results[7],10) > 0)
        {
            mtResults.setText(mtResults.getText() + "\n\nPhone: " + results[7]);
            Button phoneButton = new Button(this);
            phoneButton.setText("Call: " + results[7]);
            phoneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + results[7]));
                        startActivity(callIntent);
                    } catch (ActivityNotFoundException activityException) {
                        Log.e("Dialing Example", "Call Failed");
                    }
                }
            });
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            p.addRule(RelativeLayout.BELOW,R.id.exit);
            phoneButton.setLayoutParams(p);
            phoneButton.setTextAppearance(this,android.R.style.TextAppearance_Large);
            RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.ResultsLayout);
            myLayout.addView(phoneButton);
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
