package com.search.victor.searchengine;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.search.victor.searchengine.adapters.AutoCompleteCustomArrayAdapter;

/**
 * Created by alcoranb on 4/14/16.
 */
public class CustomAutoCompleteTextChangedListener implements TextWatcher
{

  public static final String TAG = "CustomAutoCompleteTextChangedListener.java";
  Context context;
  boolean bIsSpanish;

  public CustomAutoCompleteTextChangedListener(Context context, boolean bSpanish){
    this.context = context;
    this.bIsSpanish = bSpanish;
  }

  @Override
  public void afterTextChanged(Editable s) {
    // TODO Auto-generated method stub

  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count,
                                int after) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onTextChanged(CharSequence userInput, int start, int before, int count) {

    try{

      // if you want to see in the logcat what the user types
      Log.e("TextChangeListener", "User input: " + userInput);

      if (bIsSpanish)
      {
        SearchNewSpanish searchActivity = ((SearchNewSpanish) context);

        Log.e("TextChangeListener", "got context");
        //fill a string array based on the user input... search the correct things..
        searchActivity.currentMenu = searchActivity.getItems(userInput.toString());

        searchActivity.mArrayAdapter.notifyDataSetChanged();
        searchActivity.mArrayAdapter = new ArrayAdapter<String>(searchActivity,
            android.R.layout.simple_dropdown_item_1line, searchActivity.currentMenu);

        switch (searchActivity.whatMenu)
        {
          case NAME:
            searchActivity.inputTextName.setAdapter(searchActivity.mArrayAdapter);
            break;
          case CATEGORY:
            searchActivity.inputTextCategory.setAdapter(searchActivity.mArrayAdapter);
            break;
          case ZIPCODE:
            searchActivity.inputTextZip.setAdapter(searchActivity.mArrayAdapter);
            break;
          case CITY:
            searchActivity.inputTextCity.setAdapter(searchActivity.mArrayAdapter);
            break;
          case STATE:
            searchActivity.inputTextState.setAdapter(searchActivity.mArrayAdapter);
            break;
          case COST:
            searchActivity.inputTextCost.setAdapter(searchActivity.mArrayAdapter);
            break;
          case LANGUAGE:
            searchActivity.inputTextLanguage.setAdapter(searchActivity.mArrayAdapter);
            break;
          case AGE_GROUP:
            searchActivity.inputTextPopulation.setAdapter(searchActivity.mArrayAdapter);
            break;
          default:
            searchActivity.inputTextName.setAdapter(searchActivity.mArrayAdapter);
            break;
        }
      }
      else
      {
        SearchNew searchActivity = ((SearchNew) context);

        Log.e("TextChangeListener", "got context");
        //fill a string array based on the user input... search the correct things..
        searchActivity.currentMenu = searchActivity.getItems(userInput.toString());

        searchActivity.mArrayAdapter.notifyDataSetChanged();
        searchActivity.mArrayAdapter = new ArrayAdapter<String>(searchActivity,
            android.R.layout.simple_dropdown_item_1line, searchActivity.currentMenu);

        switch (searchActivity.whatMenu)
        {
          case NAME:
            searchActivity.inputTextName.setAdapter(searchActivity.mArrayAdapter);
            break;
          case CATEGORY:
            searchActivity.inputTextCategory.setAdapter(searchActivity.mArrayAdapter);
            break;
          case ZIPCODE:
            searchActivity.inputTextZip.setAdapter(searchActivity.mArrayAdapter);
            break;
          case CITY:
            searchActivity.inputTextCity.setAdapter(searchActivity.mArrayAdapter);
            break;
          case STATE:
            searchActivity.inputTextState.setAdapter(searchActivity.mArrayAdapter);
            break;
          case COST:
            searchActivity.inputTextCost.setAdapter(searchActivity.mArrayAdapter);
            break;
          case LANGUAGE:
            searchActivity.inputTextLanguage.setAdapter(searchActivity.mArrayAdapter);
            break;
          case AGE_GROUP:
            searchActivity.inputTextPopulation.setAdapter(searchActivity.mArrayAdapter);
            break;
          default:
            searchActivity.inputTextName.setAdapter(searchActivity.mArrayAdapter);
            break;
        }
      }

    } catch (NullPointerException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }






}