package orgin.v02;
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
    static  char FieldDelim ='=';
    public void FileTalker(){
        //does nothing
    }
    public boolean CreateFile(String name){
        Boolean Result = false;
        File file = new File(name);
        if(FileExist(name)){
            //opens the file and then dose nothing else
            Result = true;
        }else {
            Result = false;//indicates and passes back the the opperation failed
        }
        return Result;
    }
    public boolean DeleteFile(String name){
        //dose nothing
        return false;//indicates and passes back the the opperation failed
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
    public boolean OpenFile(String name){
      boolean Result = false;
        if(FileLoaded){CloseFile();}//closes the currently oppen file before attmpting to open a new one
        try{
           if(FileExist(name)){
               file = new Scanner(new File(name));
               outputConcat = new BufferedWriter(new FileWriter(name,true));
               outputDestruct = new BufferedWriter(new FileWriter(name,false));
               Name = name;
               FileLoaded = true;
               GetLineCount();
               Text = getText();
               Result = true;//indicates that the file has been sucsessfully loaded
           }
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
           Result = "Null Data Error: There is no File loaded Right now";
       }
        return Result;
    }
    public String GetField(String name){//assumes that the file is formated as fieldname=fielddata
        String Result="";
        if(FileLoaded){
            for(int i=0;i<Text.length;i++){
                String FieldName = Text[i].substring(0,Text[i].indexOf(FieldDelim)).trim().toLowerCase();
                if(FieldName.equals(name.trim().toLowerCase())){
                    String FieldData = Text[i].substring(Text[i].indexOf(FieldDelim+1)).trim().toLowerCase();
                    Result = FieldData;
                    break;//ends the loop the data has been found
                }else{
                    continue;//moves onto the next line;
                }
            }
            if(Result.equals("")){//in the event that the method dose not find anything
                Result = "Null Data Error: the Field that you are looking for does not exist or is null";
            }
        }else{
            Result = "Null Data Error: There is not currently a file loaded";
        }
        return Result;
    }
    public Boolean WrightField(String name, String data){
        boolean Result = false;
        if(FileLoaded){
            name = name.trim().toLowerCase();
            data = data.trim().toLowerCase();
            int line = FileFieldExist(name);
            String Field = name+FieldDelim+data;
            if(line>=0){
                Replace(Field,line);
                WrightReload();
                Result = true;
            }else if(line==-1){
                WrightLine(Field);
                Reload();
                Result = true;
            }else{
               Result = false;
            }
        }
        return Result;
    }
    public Boolean FileExist(String name){
        try{
            FileInputStream temp = new FileInputStream(name);
            temp.close();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    private String[] getText(){
        String Result[];
        if(FileLoaded){
             Result = new String[FileLineCount];
            for(int i=0;i<Result.length;i++){
                Result[i]=file.nextLine();
            }
        }else{
            Result = new String[1];
            Result[0] = "Null Data Error: There is not currently a file loaded";
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
                String FieldName = Text[i].substring(0,Text[i].indexOf(FieldDelim)).trim().toLowerCase();
                if(FieldName.equals(name.trim().toLowerCase())){
                    Result = i;
                    break;//ends the loop the data has been found
                }else{
                    continue;//moves onto the next line;
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
                outputConcat.write(text);
                Result = true;
            }catch(Exception e){
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
          String temp = Name;
            if(CloseFile()){
                if(OpenFile(temp)){
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
