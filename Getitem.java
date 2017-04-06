import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class GetItem {
	
	static char PARENTHESIS = 34;
	static char NEWLINE = 10;
	static char nullchar = 0;
	static String QOPEN = "http://api.walmartlabs.com/v1/items1/";//the beginning half of an api query that goes out
	static String QCLOSE = "?apiKey={apiKey}&lsPublisherId={Your LinkShare Publisher Id}&format=xml";//the ending half of an api query that goes out
	static char STARTDELIM = PARENTHESIS;//delimits the beginning of a line in the api response
	static char MIDDELIM = ':';//delimits the name of a category in the response and the data it contains
	static char ENDDELIM = ',';//delimits the end of the line in the api response
	static int RAWQUANTITY = 50;
	
	public void GetItem(){
		//initializes the class
	}
	
	public String getQuery(int x){
		String result ="";
		result = QOPEN+x+QCLOSE;
		return result;
	}
	
	public String readResult(String x){
		
		
			String[][] raw = new String[RAWQUANTITY][2];//the data received from walmart before it is sorted  0 for name 1 for data
			String[][] parsed = new String[9][2];//the data after it is sorted
				parsed[0][0]="name";			parsed[0][1]="null";//contains the name of the item
				parsed[1][0]="date";			parsed[1][1]="null";//contains the date last modified
				parsed[2][0]="salePrice";		parsed[2][1]="null";//contains the price of the item
				parsed[3][0]="stock";			parsed[3][1]="null";//contains whether or not the item is in stock
				parsed[4][0]="thumbnailImage";	parsed[4][1]="null";//contains a link to an image of the item
				parsed[5][0]="clearance";		parsed[5][1]="null";//contains whether or not the item is on sale
				parsed[6][0]="itemId";			parsed[6][1]="null";//contains the Walmart Item ID
				parsed[7][0]="longDescription";	parsed[7][1]="null";//contains a description of the item
				parsed[8][0]="Notes";			parsed[8][1]="null";//contains any programming notes or debugging information
			String tempName = "";//holds the extracted name string before parsing
			String tempData = "";//holds the extracted data string before parsing	
			
			if(entryDataValidator(x)){ 
				
			String mod = x.substring(18);// removes the beginning of the string so it can be parsed
			mod= mod.replace("{","");//
			mod= mod.replace("}","");// Remove all Brackets from the string
			mod= mod.replace("[","");//
			mod= mod.replace("]","");//
			mod= mod.trim();//removes white space from the ends so that a comma can be applied to the end of the final value
			mod+=",";//adds a comma to the end of the final value
			
			for(int i=0;i<RAWQUANTITY;i++){
				//--------------------------------------------------------------------------------------prepares the beginning of the string
				int locSdelim = mod.indexOf(STARTDELIM);//Contains the index of the start delimiter 
				if(locSdelim<=0){locSdelim=0;}//                                                        
				mod = mod.substring(locSdelim);//trims fat off the beginning of the mod sting
				//--------------------------------------------------------------------------------------grabs the name of the data point
				int locMdelim = mod.indexOf(MIDDELIM);//Contains the index of the middle delimiter
				if(locMdelim<=0){locMdelim=0;}//                                                        
				tempName=mod.substring(0,locMdelim);//grabs the name portion of the string
				mod = mod.substring(locMdelim);
				//--------------------------------------------------------------------------------------grabs the data
				int locEdelim = mod.indexOf(ENDDELIM);//Contains the index of the ending delimiter
				int openParenthesis = mod.indexOf(PARENTHESIS);//checks to see the location of the next opening parentheses
				
				if(openParenthesis<locEdelim){//used to make sure that the program ignores characters in the middle of parenthesis
					if(openParenthesis<=0){openParenthesis=0;}//stops string out of bound errors
					String temp = mod.substring(openParenthesis+1);//creates a temporary version of the string starting after the relevant open parenthesis
					int closeParenthesis = temp.indexOf(PARENTHESIS);//finds the relevant close parenthesis
					if(closeParenthesis<=0){closeParenthesis=0;}//stops string out of bound errors                                    
					temp = temp.substring(closeParenthesis);//removes the full value in-between the parenthesis inclusive of the parenthesis 
					int tempdelim = temp.indexOf(ENDDELIM);//finds the end delimiter after the parenthesis
					locEdelim = tempdelim + openParenthesis + closeParenthesis+1;//Determines where the end delimiter is located in the master string
				}
				
				if(locEdelim<=0){locEdelim=0;}//stops string out of bound errors
				tempData=mod.substring(0,locEdelim);//grabs the data portion of the string
				mod = mod.substring(locEdelim);//trims the name and data just acquired off of the string
				//--------------------------------------------------------------------------------------
				//if(tempName.length() >3){
				//tempName = tempName.substring(1, tempName.length()-1);
				//}
				raw[i][0]=tempName;//enters the name found into the raw array
				raw[i][1]=tempData;//enters the data found into the raw array
				
			}
			
			for(int i=0; i<RAWQUANTITY; i++){ //TODO
				raw[i][0] = raw[i][0].replaceAll(Character.toString(PARENTHESIS), "");
				raw[i][1] = raw[i][1].replaceAll(Character.toString(PARENTHESIS),"");
				
				raw[i][1] = raw[i][1].replaceFirst(":", "");
				
				
				System.out.println(raw[i][0]+"  XX~~~~~~~XX  "+raw[i][1]);//debug
			}
			
			boolean stillTrue = true;
				
			for(int i=0; i<RAWQUANTITY; i++){ //validates and inserts the data for name
				
				
				if(raw[i][0].equals(parsed[0][0])){ // if raw is at location 0 (value), i is stepping through raw equals the name we are looking for
					
					if(raw[i][1].length() != -1){  //find and execute once when found it //if data inside of data section of portion(has a name) move name into name slot on parsed and processed file
						
						parsed[0][1]=(raw[i][1]);  //then break to go to next part of code
						
						//break;	
					}else {
						stillTrue = false;
					}	
				}							
				if(raw[i][0].equals(parsed[2][0])){ // PRICE // if raw is at location 0 (value), i is stepping through raw equals the name we are looking for
					
					if(raw[i][1].length() != -1){  //find and execute once when found it //if data inside of data section of portion(has a name) move name into name slot on parsed and processed file
						//if(isInteger(raw[i][1])){
							parsed[2][1]=(raw[i][1]);
							//break;
						//}else {
							//stillTrue = false;
						//}
					}	
				}
				if(raw[i][0].equals(parsed[3][0])){ //STOCK // if raw is at location 0 (value), i is stepping through raw equals the name we are looking for
					
					if(raw[i][1].length() != -1){  //find and execute once when found it //if data inside of data section of portion(has a name) move name into name slot on parsed and processed file	
						raw[i][1] = raw[i][1].replaceAll(Character.toString(PARENTHESIS), "");
						
						if(raw[i][1].equals("Available") ){ //boolean
						
							parsed[3][1]= "true";  //then break to go to next part of code
							
							//break;
						}
							else if 
								(raw[i][1].equals("Unavailable")){
								parsed[3][1] = "false";
							
						}else {
							
							stillTrue = false;
						}
					}	
				}
				if(raw[i][0].equals(parsed[4][0])){ //THUMBNAIL // if raw is at location 0 (value), i is stepping through raw equals the name we are looking for
					System.out.println("1");
					if(raw[i][1].length() != -1){  //find and execute once when found it //if data inside of data section of portion(has a name) move name into name slot on parsed and processed file
						System.out.println(raw[i][1].substring(0, 8));
						if(raw[i][1].substring(0,8).equals("https://")){
							raw[i][1] = raw[i][1].replaceAll(Character.toString(PARENTHESIS), "");
							System.out.println("3");
							parsed[4][1]=(raw[i][1]);  //then break to go to next part of code
							System.out.println("4");
							//break;
						}else{
							System.out.println("5");
							stillTrue = false;
						}
					}
				}
				if(raw[i][0].equals(parsed[5][0])){ // CLEARANCE  if raw is at location 0 (value), i is stepping through raw equals the name we are looking for
					if(raw[i][1].length() != -1){  //find and execute once when found it //if data inside of data section of portion(has a name) move name into name slot on parsed and processed file
						if(raw[i][1].equals("true") || raw[i][1].equals("false")){ //boolean
							parsed[5][1]=(raw[i][1]);  //then break to go to next part of code
							//break;
						}else{
							stillTrue = false;
						}
					}
				}
				if(raw[i][0].equals(parsed[6][0])){ // ITEM ID  if raw is at location 0 (value), i is stepping through raw equals the name we are looking for
					if(raw[i][1].length() != -1){  //find and execute once when found it //if data inside of data section of portion(has a name) move name into name slot on parsed and processed file
						parsed[6][1]=(raw[i][1]);  //then break to go to next part of code
						
						
						//break;	
						
						
					}else {
						stillTrue = false;
					}	
				}
				if(raw[i][0].equals(parsed[7][0])){ // LONG DESCRIPTION if raw is at location 0 (value), i is stepping through raw equals the name we are looking for
					if(raw[i][1].length() != -1){  //find and execute once when found it //if data inside of data section of portion(has a name) move name into name slot on parsed and processed file
						parsed[7][1]=(raw[i][1]);  //then break to go to next part of code
						//break;
					}else{
						stillTrue = false;
					}
				}
				if(raw[i][0].equals(parsed[8][0])){ //NOTES  if raw is at location 0 (value), i is stepping through raw equals the name we are looking for	
					if(raw[i][1].length() != -1){  //find and execute once when found it //if data inside of data section of portion(has a name) move name into name slot on parsed and processed file
						parsed[8][1]=(raw[i][1]);  //then break to go to next part of code
						//break;
					}else {
						stillTrue = false;
					}
				}	
			} //if (stillTrue){ System.out.println("Validator working");//debug
			String Result = "";
			
			
			Result = SIF(parsed[0][1], parsed[2][1], parsed[3][1], parsed[4][1], parsed[5][1], parsed[6][1], parsed[7][1], parsed[8][1]);	
			
			
			if (stillTrue){
				return Result;
			} else {
				return null;
			}
			
		}
			return tempData;	
			
	
	}		
	
	private boolean isInteger(String string) {
	    try {
	        Integer.valueOf(string);
	        System.out.println("true");
	        return true;
	    } catch (NumberFormatException e) {
	    	System.out.println("false");
	        return false;
	    }
	}

	private boolean entryDataValidator(String sub){ //verify data between two pieces (Initial entry data and individual pieces of data given out)
		
		boolean firstResult = false;
		boolean endResult = false;
		boolean returnResult = false; //verify if statement ran
		
		
		if(sub.length() > 20 && sub.indexOf(MIDDELIM)!= -1){ //if the length of the string is greater than 20 and it has a colon
			returnResult = true;
		
			
			String beginning = sub.substring(0, sub.indexOf(MIDDELIM)); //the beginning of the string until the first colon
			String ending = sub.substring(sub.length() -6, sub.length() -0 ); //sub is x; substring is portion of string, sub.length is the number of characters in the string.
			
			
			
			String begList = "null" + "{" + "  " + "   " + PARENTHESIS + "items" + PARENTHESIS; //the first part of the beginning list
			String endList = "}" + "   " + "]" + "}" ; //the end part of the ending of the list
			
			
			if (beginning.equals (begList)){  //the beginning part of the string equals the String begList  { "items" 
				firstResult = true;
				
			}		
			
			if (ending.equals(endList)){ //the ending portion of the string equals the end portion of the endList  } ] } 
				endResult = true;
				
			}
			
					
							
		}
				
		if (firstResult == true && endResult == true && returnResult == true){ //if the first result, end result, and return result is true, return true
			
			return true;
		} 
			
			return false;
		
		
	}
	
	private String SIF(String name, String price, String quantity, String image, String sale, String itemid, String description, String notes){
		String result = "";
		String temp = "";
		//^^^^^^^^^^^^^^^ variables
		//vvvvvvvvvvvvvvv SIF
		result += "SIF:";
		//^^^^^^^^^^^^^^^ SIF
		//vvvvvvvvvvvvvvv name		
		result+= ":";
		result += name;
		result +=":";
		//^^^^^^^^^^^^^^^ name
		//^^^^^^^^^^^^^^^ name
		result+=":";
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");	
		Date dateobj = new Date();
		result+= df.format(dateobj);
		result+=":";
		//^^^^^^^^^^^^^^^ date
		//vvvvvvvvvvvvvvv price	
		result+= ":";
		//temp = String.valueOf(price);
		result += price;
		temp = "";
		result+= ":";
		//^^^^^^^^^^^^^^^ price
		//vvvvvvvvvvvvvvv quantity
		result+= ":";
		//temp = String.valueOf(quantity);
		result += quantity;
		temp ="";
		result+= ":";
		//^^^^^^^^^^^^^^^ quantity
		//vvvvvvvvvvvvvvv image	
		result+= ":";
		result += image;
		result+= ":";		
		//^^^^^^^^^^^^^^^ image
		//vvvvvvvvvvvvvvv sale	
		result+= ":";
		//temp = String.valueOf(sale);
		result += sale;
		temp = "";
		result+= ":";		
		//^^^^^^^^^^^^^^^ sale
		//vvvvvvvvvvvvvvv itemid
		result+= ":";
		//temp = String.valueOf(itemid);
		result += itemid;
		temp = "";
		result+= ":";
		//^^^^^^^^^^^^^^^ itemid	
		//vvvvvvvvvvvvvvv description
		result+= ":";
		result += description;
		result+= ":";		
		//^^^^^^^^^^^^^^^ description
		//vvvvvvvvvvvvvvv notes
		result+= ":";
		result += notes;
		result+= ":";			
		

	return result;
	}
		

}


