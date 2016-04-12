package com.search.victor.searchengine;
import android.support.v7.app.AppCompatActivity;



import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.search.victor.searchengine.adapters.ArrayWheelAdapter;


public class Search extends AppCompatActivity {

    DatabaseHandler dbContext;
    SQLiteDatabase database;
    boolean wheelScrolled = false;
    EditText inputTextName;
    EditText inputTextCategory;
    EditText inputTextZip;
    EditText inputTextCity;
    EditText inputTextState;
    EditText inputTextCost;
    EditText inputTextPopulation;
    EditText inputTextLanguage;
    String CategoryMenu[];
    String ZipMenu[];
    String CityMenu[];
    String StateMenu[];
    String CostMenu[];
    String LanguageMenu[];
    String AgeGroupMenu[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        dbContext = new DatabaseHandler(this);

        database = dbContext.getWritableDatabase();
        dbContext.onUpgrade(database, 2,1);
        inputTextName = (EditText)findViewById(R.id.OrgInName);
        inputTextName.setHint("Organization Name");
        inputTextCategory = (EditText)findViewById(R.id.OrgInCategory);
        inputTextCategory.setHint("Category");
        inputTextZip = (EditText)findViewById(R.id.OrgInAddressZip);
        inputTextZip.setHint("Zip Code");
        inputTextCity = (EditText)findViewById(R.id.OrgInAddressCity);
        inputTextCity.setHint("City");
        inputTextState = (EditText)findViewById(R.id.OrgInAddressState);
        inputTextState.setHint("State");
        inputTextCost = (EditText)findViewById(R.id.OrgInCost);
        inputTextCost.setHint("Cost");
        inputTextPopulation = (EditText)findViewById(R.id.OrgInPop);
        inputTextPopulation.setHint("Age Group/Group Type");
        inputTextLanguage = (EditText)findViewById(R.id.OrgInLang);
        inputTextLanguage.setHint("Language");
        CategoryMenu = dbContext.allCategories();
        ZipMenu = dbContext.allZips();
        CityMenu = dbContext.allCity();
        StateMenu = dbContext.allState();
        CostMenu = dbContext.allCost();
        LanguageMenu = dbContext.allLanguage();
        AgeGroupMenu = dbContext.allAges();
        initCatGroup(R.id.CatWheel);
        initAgeGroup(R.id.AgeWheel);
        initCityGroup(R.id.CityWheel);
        initCostGroup(R.id.CostWheel);
        initLanguageGroup(R.id.LangWheel);
        initStateGroup(R.id.StateWheel);
        initZipGroup(R.id.ZipWheel);

        final Button mbSearch = (Button)findViewById(R.id.Search);
        mbSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle inputs = new Bundle();
                Log.e("data[0]",String.valueOf(inputTextName.getText().toString())+".");
                inputs.putStringArray("Querry Strings", new String[]{inputTextName.getText().toString(),
                        inputTextCategory.getText().toString(), inputTextZip.getText().toString(),
                        inputTextCity.getText().toString(), inputTextState.getText().toString(),
                        inputTextCost.getText().toString(), inputTextPopulation.getText().toString(),
                        inputTextLanguage.getText().toString()});
                Intent intentData = new Intent(Search.this, results.class);
                intentData.putExtras(inputs);
                startActivity(intentData);
            }
        });
//        dbContext.onUpgrade(database,1,2);
    }
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {
            wheelScrolled = true;
        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            wheelScrolled = false;
            updateStatus();
        }
    };

    private final OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            if (!wheelScrolled)
            {
                updateStatus();
            }
        }
    };

    private void updateStatus()
    {
        inputTextCity.setText(CityMenu[getWheel(R.id.CityWheel).getCurrentItem()]);
        inputTextCost.setText(CostMenu[getWheel(R.id.CostWheel).getCurrentItem()]);
        inputTextLanguage.setText(LanguageMenu[getWheel(R.id.LangWheel).getCurrentItem()]);
        inputTextState.setText(StateMenu[getWheel(R.id.StateWheel).getCurrentItem()]);
        inputTextZip.setText(ZipMenu[getWheel(R.id.ZipWheel).getCurrentItem()]);
        inputTextPopulation.setText(AgeGroupMenu[getWheel(R.id.AgeWheel).getCurrentItem()]);
        inputTextCategory.setText(CategoryMenu[getWheel(R.id.CatWheel).getCurrentItem()]);
    }

    private void initAgeGroup(int id)
    {
        int max = 0;
        for (int i = 0; i < AgeGroupMenu.length; ++i)
        {
            if (AgeGroupMenu[i].length() > max)
            {
                max = AgeGroupMenu[i].length();
            }
        }
        if (AgeGroupMenu.length != 0) {
            WheelView wheel = (WheelView) findViewById(id);
            wheel.setViewAdapter(new ArrayWheelAdapter<String>(this, AgeGroupMenu));
            wheel.setVisibleItems(2);
            wheel.setCurrentItem(0);
            wheel.setMinimumWidth(300);
            wheel.addChangingListener(changedListener);
            wheel.addScrollingListener(scrolledListener);
        }
        else
        {
            Log.e("No","No Age Group");
        }
    }

    private void initCatGroup(int id)
    {
        int max = 0;
        for (int i = 0; i < AgeGroupMenu.length; ++i)
        {
            if (AgeGroupMenu[i].length() > max)
            {
                max = AgeGroupMenu[i].length();
            }
        }
        if (CategoryMenu.length != 0) {
            WheelView wheel = (WheelView) findViewById(id);
            wheel.setViewAdapter(new ArrayWheelAdapter<String>(this, CategoryMenu));
            wheel.setVisibleItems(2);
            wheel.setCurrentItem(0);
            wheel.setMinimumWidth(300);
            wheel.addChangingListener(changedListener);
            wheel.addScrollingListener(scrolledListener);
        }
        else
        {
            Log.e("No","No Cat Group");
        }
    }

    private void initZipGroup(int id)
    {
        int max = 0;
        for (int i = 0; i < ZipMenu.length; ++i)
        {
            if (ZipMenu[i].length() > max)
            {
                max = ZipMenu[i].length();
            }
        }
        if (ZipMenu.length != 0) {
            WheelView wheel = (WheelView) findViewById(id);
            wheel.setViewAdapter(new ArrayWheelAdapter<String>(this, ZipMenu));
            wheel.setVisibleItems(2);
            wheel.setCurrentItem(0);
            wheel.setMinimumWidth(300);
            wheel.addChangingListener(changedListener);
            wheel.addScrollingListener(scrolledListener);
        }
        else
        {
            Log.e("No","No Zip Group");
        }
    }

    private void initCityGroup(int id)
    {
        int max = 0;
        for (int i = 0; i < CityMenu.length; ++i)
        {
            if (CityMenu[i].length() > max)
            {
                max = CityMenu[i].length();
            }
        }
        if (CityMenu.length != 0) {
            WheelView wheel = (WheelView) findViewById(id);
            wheel.setViewAdapter(new ArrayWheelAdapter<String>(this, CityMenu));
            wheel.setVisibleItems(2);
            wheel.setCurrentItem(0);
            wheel.setMinimumWidth(300);
            wheel.addChangingListener(changedListener);
            wheel.addScrollingListener(scrolledListener);
        }
        else
        {
            Log.e("No","No City Group");
        }
    }

    private void initStateGroup(int id)
    {
        int max = 0;
        for (int i = 0; i < StateMenu.length; ++i)
        {
            if (StateMenu[i].length() > max)
            {
                max = StateMenu[i].length();
            }
        }
        if (StateMenu.length != 0) {
            WheelView wheel = (WheelView) findViewById(id);
            wheel.setViewAdapter(new ArrayWheelAdapter<String>(this, StateMenu));
            wheel.setVisibleItems(2);
            wheel.setCurrentItem(0);
            wheel.setMinimumWidth(300);
            wheel.addChangingListener(changedListener);
            wheel.addScrollingListener(scrolledListener);
        }
        else
        {
            Log.e("No","No State Group");
        }
    }

    private void initCostGroup(int id)
    {
        int max = 0;
        for (int i = 0; i < CostMenu.length; ++i)
        {
            if (CostMenu[i].length() > max)
            {
                max = CostMenu[i].length();
            }
        }
        if (CostMenu.length != 0) {
            WheelView wheel = (WheelView) findViewById(id);
            wheel.setViewAdapter(new ArrayWheelAdapter<String>(this, CostMenu));
            wheel.setVisibleItems(2);
            wheel.setCurrentItem(0);
            wheel.setMinimumWidth(300);
            wheel.addChangingListener(changedListener);
            wheel.addScrollingListener(scrolledListener);
        }
        else
        {
            Log.e("No","No Cost Group");
        }
    }

    private void initLanguageGroup(int id)
    {
        int max = 0;
        for (int i = 0; i < LanguageMenu.length; ++i)
        {
            if (LanguageMenu[i].length() > max)
            {
                max = LanguageMenu[i].length();
            }
        }
        if (LanguageMenu.length != 0) {
            WheelView wheel = (WheelView) findViewById(id);
            wheel.setViewAdapter(new ArrayWheelAdapter<String>(this, LanguageMenu));
            wheel.setVisibleItems(2);
            wheel.setCurrentItem(0);
            wheel.setMinimumWidth(300);
            wheel.addChangingListener(changedListener);
            wheel.addScrollingListener(scrolledListener);
        }
        else
        {
            Log.e("No","No Language Group");
        }
    }

    private WheelView getWheel(int id)
    {
        return (WheelView)findViewById(id);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
