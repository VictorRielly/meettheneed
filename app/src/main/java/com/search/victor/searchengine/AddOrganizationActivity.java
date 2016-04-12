package com.search.victor.searchengine;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddOrganizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_organization_layout);
        final DatabaseHandler dbHandler = new DatabaseHandler(this);
        final EditText inTextOrgName = (EditText)findViewById(R.id.OrgInName);
        final EditText inTextOrgCat = (EditText)findViewById(R.id.OrgInCategory);
        final EditText inTextOrgStreet = (EditText)findViewById(R.id.OrgInAddressStreet);
        final EditText inTextOrgZip = (EditText)findViewById(R.id.OrgInAddressZip);
        final EditText inTextOrgCity = (EditText)findViewById(R.id.OrgInAddressCity);
        final EditText inTextOrgState = (EditText)findViewById(R.id.OrgInAddressState);
        final EditText inTextOrgPhone = (EditText)findViewById(R.id.OrgInPhone);
        final EditText inTextOrgEmail = (EditText)findViewById(R.id.OrgInEmail);
        final EditText inTextOrgWebsite = (EditText)findViewById(R.id.OrgInWeb);
        final EditText inTextOrgCost = (EditText)findViewById(R.id.OrgInCost);
        final EditText inTextOrgAdditionalServ = (EditText)findViewById(R.id.OrgInAddServices);
        final EditText inTextOrgPop = (EditText)findViewById(R.id.OrgInPop);
        final EditText inTextOrgLang = (EditText)findViewById(R.id.OrgInLang);
        final EditText inTextOrgAssistance = (EditText)findViewById(R.id.OrgInAssistance);
        final EditText inTextOrgOther = (EditText)findViewById(R.id.OrgInOther);
        Button mbSubmit = (Button)findViewById(R.id.Submit);
        mbSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String data[] = new String[15];
                data[0] = inTextOrgName.getText().toString();
                data[1] = inTextOrgEmail.getText().toString();
                data[2] = inTextOrgWebsite.getText().toString();
                data[3] = inTextOrgCost.getText().toString();
                data[4] = inTextOrgAdditionalServ.getText().toString();
                data[5] = inTextOrgAssistance.getText().toString();
                data[6] = inTextOrgOther.getText().toString();
                data[7] = inTextOrgCat.getText().toString();
                data[8] = inTextOrgStreet.getText().toString();
                data[9] = inTextOrgZip.getText().toString();
                data[10] = inTextOrgCity.getText().toString();
                data[11] = inTextOrgState.getText().toString();
                data[12] = inTextOrgPhone.getText().toString();
                data[13] = inTextOrgPop.getText().toString();
                data[14] = inTextOrgLang.getText().toString();
                dbHandler.addOrganization(data);
                dbHandler.getOrganizations();
                inTextOrgName.setText("");
                inTextOrgEmail.setText("");
                inTextOrgWebsite.setText("");
                inTextOrgCost.setText("");
                inTextOrgAdditionalServ.setText("");
                inTextOrgAssistance.setText("");
                inTextOrgOther.setText("");
                inTextOrgCat.setText("");
                inTextOrgStreet.setText("");
                inTextOrgZip.setText("");
                inTextOrgCity.setText("");
                inTextOrgState.setText("");
                inTextOrgPhone.setText("");
                inTextOrgPop.setText("");
                inTextOrgLang.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
