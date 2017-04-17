package orgin.v02;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.Log;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.CheckBox;
import java.io.File;

public class Setting_page extends AppCompatActivity {
    Settings Sett;
    String name;
    File directory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        start();
    }

    public void start(){
        Log.d("V0.2Debug","The Settings page has been opened");
        name = "Settings.sett";
        directory = getFilesDir();
        Sett = new Settings(directory);
        EditText zip = (EditText) findViewById(R.id.ZipcodeEtbox);
        EditText budget = (EditText) findViewById(R.id.BudgetdefaultEtbox);
        CheckBox reminder = (CheckBox) findViewById(R.id.BudgetRemindCkbox);
        CheckBox strict = (CheckBox) findViewById(R.id.BudgetstrictCkbox);
        zip.setText(Sett.getZipCode());
        budget.setText(Sett.getBudgetDefault());
        reminder.setActivated(Sett.getBudgetReminders());
        strict.setActivated(Sett.getBudgetStrict());
    }
    public void SaveBtnClick(View v){
        EditText zip = (EditText) findViewById(R.id.ZipcodeEtbox);
        EditText budget = (EditText) findViewById(R.id.BudgetdefaultEtbox);
        CheckBox reminder = (CheckBox) findViewById(R.id.BudgetRemindCkbox);
        CheckBox strict = (CheckBox) findViewById(R.id.BudgetstrictCkbox);
        EditText apikey = (EditText) findViewById(R.id.ApikeyEtbox);
        EditText apilocation = (EditText) findViewById(R.id.ApiLocationEtbox);
        Sett.setZipCode(zip.getText().toString());
        Log.d("V0.2Debug","zip code is:         "+zip.getText().toString());
        Sett.setBudgetDefault(Double.parseDouble(budget.getText().toString()));
        Log.d("V0.2Debug","Default Budget is:   "+budget.getText().toString());
        Sett.setBudgetReminders(reminder.isActivated());
        Log.d("V0.2Debug","Budget Reminders is: "+String.valueOf(reminder.isActivated()));
        Sett.setBudgetStrict(strict.isActivated());
        Log.d("V0.2Debug","Strict Budget is:    "+String.valueOf(strict.isActivated()));
        String apikeyvalue = apikey.getText().toString();
        String apilocationvalue = apilocation.getText().toString();
        Log.d("V0.2Debug","Api Key is:          "+apikeyvalue);
        Log.d("V0.2Debug","Api Location is:     "+apilocationvalue);
        if(!apikeyvalue.equals("Api Key")){Sett.setApiKey(apikeyvalue);}
        if(!apilocationvalue.equals("Api Location")){Sett.setApiLocation(apilocationvalue);}
        Sett.saveSettings();
        finish();
    }
    public void ApiLocationClick(View v){
        Log.d("V0.2Debug","The Api Location EditText has been clicked upon");
        EditText x = (EditText) findViewById(R.id.ApiLocationEtbox);
        x.setText(Sett.getApiLocation());
    }
    public void ApiKeyClick(View v){
        Log.d("V0.2Debug","The Api key EditText has been clicked upon");
        EditText x = (EditText) findViewById(R.id.ApikeyEtbox);
        x.setText(Sett.getApiKey());
    }
}
