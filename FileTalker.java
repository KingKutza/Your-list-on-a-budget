package orgin.v02;
import android.util.Log;

import java.io.*;
import java.util.Scanner;
/**
 * Created by Kutza on 4/14/2017.
 */
public class FileTalker {
    String Text[];
    File file;
    public FileTalker(File file){
        this.file=file;
		Read();
    }
    public void Refresh(){//TODO fix
		Wright();
		Read();
	}
    
    
    
    public String[] getText(){
		return Text;
	}
    public void setText(String[] temp){
		if(temp.length>Text.length){
			Extend(temp.length-Text.length);//TODO if the wrighter crashes add one
		}
		for(int i=0;i<temp.length;i++){
				Text[i]=temp[i];
		}
	}
    private void Extend(int amount){
		String Temp[] = new String[Text.length+amount];
		for(int i=0;i<Text.length;i++){
			Temp[i]=Text[i];
		}
			Text = Temp;
	}
	
    private void Read(){//Warning invoking this method overwrites Text
		
		Scanner scann = null;
		String Inport ="";
		String temp[];
		int i=0;
		try{
			scann = new Scanner(file);
			
			for(i=0;scann.hasNextLine();i++){
				Inport+=scann.nextLine();
				Inport+="~";
			}
			
			if(i<=0){//insures than an empty file still opens a Text array for filling later
				i=10;
				temp = new String[i];
			}else{
				temp = new String[i];
				for(int a=0;a<i;a++){
					temp[a]=Inport.substring(0,Inport.indexOf('~'));
					Inport=Inport.substring(Inport.indexOf('~')+1);
				}
			}
			
			Text = temp;
		}catch(FileNotFoundException e){
			//TODO add contigency for file not found on read
		}catch(Exception e){
			Log.d("V0.2Debug","FileTalker Read Threw Exception "+e.getMessage());
		}
		scann.close();
    }
    private void Wright(){
		try{
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			int i = 0;
			while(i<Text.length){
				if(Text[i]!=null && Text[i]!= ""){
					out.write(Text[i]);
					out.newLine();
				}
				i++;
			}
			
			out.close();
		}catch(FileNotFoundException e){
			//TODO add contigency for file not found on Wright
		}catch(Exception e){
			Log.d("V0.2Debug","FileTalker Wright Threw Exception "+e.getMessage());
		};
    }
    private void WrightLine(String line,boolean concat){
		
	}
}
