package orgin.v02;

/**
 * Created by Kutza on 4/14/2017.
 */

public class Settings {
    FileTalker jake;//used to read and wright to files
    String apiKey;//containes the key to the api
    String apiLocation;//containes the address the api resides at
    String zipCode;//contains the location of the perfered walmart
    double budgetDefault;//the assumed budget of any new list
    boolean budgetReminders;//wether or not to remind the user when they are close to there budget
    boolean budgetStrict;//wether or not to force users to addhere to there budget
    public void Settings(){//sets up the settings class
        FileTalker temp = new FileTalker();
        jake=temp;
    }
    public void readSettings(){
            if(jake.OpenFile("Settings.sett")){
                String temp = jake.ReadFile();
                setApiKey(jake.GetField("api key"));
                setApiLocation(jake.GetField("api location"));
                setZipCode(jake.GetField("zip code"));
                setBudgetDefault(jake.GetField("budget default"));
                setBudgetReminders(jake.GetField("budget reminders"));
                setBudgetStrict(jake.GetField("budget strict"));
            }else{
                //TODO create new Settings.sett file
            }

    }
    public void saveSettings(){

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
    public double getBudgetDefault(){
        return budgetDefault;
    }
    public boolean getBudgetReminders(){
        return budgetReminders;
    }
    public boolean getBudgetStrict(){
        return budgetStrict;
    }
    public boolean setApiKey(String x){
        //TODO verify valid key
        apiKey=x;
        return true;
    }
    public boolean setApiLocation(String x){
        //TODO verify valid Location
        apiLocation=x;
        return true;
    }
    public boolean setZipCode(String x){
        //TODO verify valid zip
        zipCode=x;
        return true;
    }
    public boolean setBudgetDefault(double x){
        if(x>0){
           budgetDefault=x;
            return true;
        }else{
            return false;
        }
    }
    private boolean setBudgetDefault(String x){

    }
    public boolean setBudgetReminders(boolean x){
        budgetReminders=x;
        return true;
    }
    private boolean setBudgetReminders(String x){

    }
    public boolean setBudgetStrict(boolean x){
        budgetStrict=x;
        return true;
    }
    private boolean setBudgetStrict(String x){

    }
}
