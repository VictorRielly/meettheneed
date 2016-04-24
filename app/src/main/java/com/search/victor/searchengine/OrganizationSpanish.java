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

public class OrganizationSpanish extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization_spanish);
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
        Log.e("Yoo","HoooSpanish");
        String data = inputs.getString("Org ID");
        Log.e("Yo","Wassup");
        final SpanishDatabaseHandler dbHandler = new SpanishDatabaseHandler(this);
        String results[] = dbHandler.searchOrg(data);
        Log.e("!","1");
        TextView mtResults = (TextView) findViewById(R.id.textView);
        TextView mtLink = (TextView) findViewById(R.id.hyperlink);
        Log.e("2", "2");
        if (results[0].length() != 0) {
            mtResults.setText("Resultados:\n\nNombre de la organización: " + results[0]
                    + "\n\nEl Costo: " + results[1] +
                    "\n\nOtros puntos importantes: " + results[2] + "\n\nCorreo Electrónico: " + results[3] +
                    "\n\nAsistencia: " + results[5] +
                    "\n\nServicios Adicionales: " + results[6]);
        }
        if (Long.parseLong(results[7],10) > 0)
        {
            mtResults.setText(mtResults.getText() + "\n\nTeléfono: " + results[7]);
        }
        if (results[8].length() != 0)
        {
            mtResults.setText(mtResults.getText() + "\n\nDirección: " + results[8] + ", " + results[10] + ", " + results[11] + ", " + results[9]);
        }
        if (results[4].length() != 0)
        {
            mtResults.setText(mtResults.getText() + "\n\nPágina Web: ");
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
