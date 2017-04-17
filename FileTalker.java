package orgin.v02;
import android.util.Log;

import java.io.*;
import java.util.Scanner;
/**
 * Created by Kutza on 4/14/2017.
 */
public class FileTalker {
    Scanner file;
    BufferedWriter outputConcat;
    BufferedWriter outputDestruct;
    boolean FileLoaded = false;
    int FileLineCount = 0;
    String Text[];
    String Name = "";
    File Directory;
    static  char FieldDelim ='=';
    public FileTalker(){
        //does nothing
    }
    public boolean CreateFile(File directory,String name){
        Boolean Result = false;
        try{
            File file = new File(directory,name);
            FileOutputStream a = new FileOutputStream(file);
            a.close();
            Result = true;
        }catch(Exception E){
            Result = false;
        }
        Result = FileExist(directory,name);
        return Result;
    }
    public void DeleteFile(File directory,String name){
        File temp = new File(directory,name);
        temp.delete();
    }
    public boolean WrightFile(String[] text){
       boolean Result = false;
        if(FileLoaded){
            Text = text;
            WrightReload();
            Result = true;
        }
        return Result;
    }
    public boolean OpenFile(File directory,String name){
      boolean Result = false;
        if(FileLoaded){CloseFile();}//closes the currently oppen file before attmpting to open a new one
        try{
            File temp = new File(directory,name);
            file = new Scanner(temp);
            outputConcat = new BufferedWriter(new FileWriter(temp,true));
            outputDestruct = new BufferedWriter(new FileWriter(temp,false));
            Name = name;
            Directory=directory;
            FileLoaded = true;
            FileLineCount = GetLineCount();
            Text = getText();
            Result = true;//indicates that the file has been sucsessfully loaded
        }catch(Exception e){
            Result = false;//indicates and passes back the the opperation failed
        }
        return Result;
    }
    public boolean CloseFile(){
      boolean Result = false;
        if(FileLoaded){
            try {//closes the file
                file.close();
                outputConcat.close();
                outputDestruct.close();
            }catch(Exception e){
                Result = false;//indicates and passes back the the opperation failed
            }
            //resets all of the variables and values to null
            file = null;
            outputConcat = null;
            outputDestruct = null;
            FileLoaded = false;
            FileLineCount = 0;
            Directory=null;
            Text = null;
            Result = true;
        }
        return Result;
    }
    public String ReadFile(){
        String Result="";
       if(FileLoaded){
           for(int i=0;i<Text.length;i++){
               Result += Text[i];
           }
       }else{//in the event that there is not a file loaded returns a error
           Log.d("V0.2Debug","File Talker GetField is Returning a null value line:96");
           Result = null;
       }
        return Result;
    }
    public String GetField(String name){//assumes that the file is formated as fieldname=fielddata;
        String Result="";
        if(FileLoaded){
            for(int i=0;i<Text.length;i++){
                if(Text[i]!=null){
                    String FieldName = Text[i].substring(0,Text[i].indexOf(FieldDelim)).trim().toLowerCase();

                    if(FieldName.equals(name.trim().toLowerCase())){
                        String FieldData = Text[i].substring(Text[i].indexOf(FieldDelim+1)).trim().toLowerCase();
                        Result = FieldData;
                        break;//ends the loop the data has been found
                    }else{
                        continue;//moves onto the next line;
                    }
                }

            }
            if(Result.equals("")){//in the event that the method dose not find anything
                Log.d("V0.2Debug","File Talker GetField is Returning a null value line:116");
                Result = null;
            }
        }else{
            Log.d("V0.2Debug","File Talker GetField is Returning a null value line:120");
            Result = null;
        }
        return Result;
    }
    public Boolean WrightField(String name, String data){
        Log.d("V0.2Debug","Break Point Four: the value of data is => "+data);
        boolean Result = false;
        if(FileLoaded&&name!=null&&data!=null){
            name = name.trim().toLowerCase();
            data = data.trim().toLowerCase();
            int line = FileFieldExist(name);
            String Field = name+FieldDelim+data;
            Log.d("V0.2Debug","Break Point Five: the value of Field is => "+Field);
            if(line>=0){
                Log.d("V0.2Debug","Break Point Six");
                Replace(Field,line);
                WrightReload();
                Result = true;
            }else if(line==-1){
                Log.d("V0.2Debug","Break Point Seven");
                WrightLine(Field);
               // Reload();
                Result = true;
            }else{
               Result = false;
            }
        }
        return Result;
    }
    public Boolean FileExist(File directory,String name){
        boolean Result = false;
        try{
            File temp = new File(directory,name);
            Result = temp.exists();
        }catch(Exception e){
            Result = false;
        }
        return Result;
    }
    private String[] getText(){
        Log.d("V0.2Debug","~~~");
        String Result[];
        if(FileLoaded){
            Log.d("V0.2Debug","~A~");
            if(FileLineCount<=10){
                Log.d("V0.2Debug","~B~");
                Result = new String[10];
            }else{
                Log.d("V0.2Debug","~C~");
                Result = new String[FileLineCount];
            }
            for(int i=0;i<FileLineCount;i++){
                Result[i]=file.nextLine();
                Log.d("V0.2Debug","~D~"+Result[i]);
            }
        }else{
            Log.d("V0.2Debug","~E~");
            Result = new String[10];
        }

        return Result;
    }
    private int GetLineCount(){
        int Result=0;
       if(FileLoaded){
           for(int i=0;file.hasNextLine();i++){
               Result=i;
           }
       }
        return Result;
    }
    private int FileFieldExist(String name){

        int Result = -2;//-2 idicates that the file is not loaded
        if(FileLoaded){
            for(int i=0;i<Text.length;i++){
                if(Text[i]!=null){
                    String FieldName = Text[i].substring(0,Text[i].indexOf(FieldDelim)).trim().toLowerCase();
                    if(FieldName.equals(name.trim().toLowerCase())){
                        Result = i;
                        break;//ends the loop the data has been found
                    }else{
                        continue;//moves onto the next line;
                    }
                }

            }
            if(Result==-2){//in the event that the method dose not find anything
                Result = -1;//indicates that the field dose not exist
            }
        }
        return Result;
    }
    private boolean WrightLine(String text){
        boolean Result = false;
        if(FileLoaded){

            try{
                Log.d("V0.2Debug","Break Point Eight");
                outputConcat.write(text);
                Log.d("V0.2Debug","Break Point Nine");
                Result = true;
            }catch(Exception e){
                Log.d("V0.2Debug","Break Point Ten");
               Result = false;
            }
        }
        return Result;
    }
    private boolean Replace(String text,int line){

        boolean Result = false;
        if(FileLoaded){
            Text[line] = text;
            Result = true;
        }
        return Result;
    }
    private boolean WrightReload(){
        Log.d("V0.2Debug","Debug Point Five");
        boolean Result = false;
        if(FileLoaded){
            if(Wright()){
                if(Reload()){
                    Result = true;
                }
            }
        }
        return Result;
    }
    private boolean Reload(){
        boolean Result = false;
        if(FileLoaded){
            String name = Name;
            File directory = Directory;
            if(CloseFile()){
                if(OpenFile(directory,name)){
                    Result = true;
                }
            }
        }
        return Result;
    }
    private boolean Wright(){
        boolean Result = false;
        if(FileLoaded){
            if(Text.length==FileLineCount){
                for(int i=0;i<Text.length;i++){
                    try{
                        outputDestruct.write(Text[i]);
                        Result = true;
                    }catch (Exception e){
                        Result = false;
                        break;
                    }
                }
            }
        }
        return Result;
    }
}
