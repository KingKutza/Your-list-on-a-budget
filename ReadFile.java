import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
	public void ReadFile(){
		//dose nothing
	}
	private String[] text;
	public void open(String file){
		try{
			Scanner read = new Scanner(new File(file));
			for(int a=0;read.hasNextLine();a++){
				String temp=read.nextLine();
				text[a]=temp;
			}
		}catch(IOException e){
			System.out.println("an error has ocured");
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	public String[] getTextarr(){
		return text;
	}
	public String getText(){
		String result="";
		for(int i=0;i<text.lenght-1;i++){
			result+=text[i];
		}
		return result;
	}
	
	
}
	
