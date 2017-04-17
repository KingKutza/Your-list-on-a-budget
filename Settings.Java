package orgin.v02;

import android.util.Log;
import java.io.File;


/**
 * Created by Kutza on 4/14/2017.
 */

public class Settings {
    FileTalker jake;//used to read and wright to files
    File Directroy;
    String Name = "Settings.sett";
    String apiKeyName = "api key";
    String apiLocationName = "api location";
    String zipCodeName = "zip code";
    String budgetDefaultName = "budget default";
    String budgetRemindersName = "budget reminders";
    String budgetStrictName = "budget strict";
    String apiKey;//containes the key to the api
    String apiLocation;//containes the address the api resides at
    String zipCode;//contains the location of the perfered walmart
    double budgetDefault;//the assumed budget of any new list
    boolean budgetReminders;//wether or not to remind the user when they are close to there budget
    boolean budgetStrict;//wether or not to force users to addhere to there budget
    public Settings(File directroy){//sets up the settings class
        jake = new FileTalker();
        Directroy=directroy;
        readSettings();
    }
    public void readSettings(){
        jake.OpenFile(Directroy,Name);
       // String temp = jake.ReadFile();
        setApiKey(jake.GetField(apiKeyName));
        setApiLocation(jake.GetField(apiLocationName));
        setZipCode(jake.GetField(zipCodeName));
        setBudgetDefault(jake.GetField(budgetDefaultName));
        setBudgetReminders(jake.GetField(budgetRemindersName));
        setBudgetStrict(jake.GetField(budgetStrictName));
    }
    public void saveSettings(){
        jake.WrightField(apiKeyName,apiKey);
        jake.WrightField(apiLocationName,apiLocation);
        jake.WrightField(zipCodeName,zipCode);
        Log.d("V0.2Debug","Break Point Three: the value of zipCode is => "+zipCode);
        jake.WrightField(budgetDefaultName,String.valueOf(budgetDefault));
        jake.WrightField(budgetRemindersName, String.valueOf(budgetReminders));
        jake.WrightField(budgetStrictName, String.valueOf(budgetStrict));
        jake.CloseFile();
    }
    public String getApiKey(){
        return apiKey;
    }
    public String getApiLocation(){
        return apiLocation;
    }
    public String getZipCode(){
        return zipCode;
    }
    public String getBudgetDefault(){
        String x = String.valueOf(budgetDefault);
        return x;
    }
    public boolean getBudgetReminders(){
        return budgetReminders;
    }
    public boolean getBudgetStrict(){
        return budgetStrict;
    }
    public void setApiKey(String x){
        if(x!=null) {
            apiKey = x;
        }
    }
    public void setApiLocation(String x){
        if(x!=null) {
            apiLocation = x;
        }
    }
    public void setZipCode(String x){
        if(x!=null) {
            zipCode = x;
        }
    }
    public void setBudgetDefault(double x){
        if(x>0){
           budgetDefault=x;
        }
    }
    private void setBudgetDefault(String x){
        double temp = 100.0;
        try{
            if(x!=null){
                temp = Double.valueOf(x);
            }else{
              //  Log.d("V0.2Debug","Settings retrived a null or bad value from FileTalker for budget default line:94");
            }
            setBudgetDefault(temp);
        }catch (Exception e){
            Log.d("V0.2Debug","Settings Exception line:99");
        }
    }
    public void setBudgetReminders(boolean x){
        budgetReminders=x;
    }
    private void setBudgetReminders(String x) {
        if (x != null){
            if (x.equals("true")) {
                setBudgetReminders(true);
            } else {
                setBudgetReminders(false);
            }
        }else{
            setBudgetReminders(false);
          //  Log.d("V0.2Debug","Settings retrived a null or bad value from FileTalker for budget reminders line:113");
        }
    }
    public void setBudgetStrict(boolean x){
        budgetStrict=x;
    }
    private void setBudgetStrict(String x){
        if(x!=null){
            if (x.equals("true")) {
                setBudgetStrict(true);
            } else {
                setBudgetStrict(false);
            }
        }else{
            setBudgetStrict(false);
           // Log.d("V0.2Debug","Settings retrived a null or bad value from FileTalker for budget strict line:128");
        }
    }
}
