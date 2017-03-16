//3-9-17
//Donald - Kristine
//getting data on a specific item from the walmart open api
public class GetItem {
	static char parenthesis = 34;
	static String QOPEN = "http://api.walmartlabs.com/v1/items1/";//the beginning half of an api query that goes out
	static String QCLOSE = "?apiKey={apiKey}&lsPublisherId={Your LinkShare Publisher Id}&format=xml";//the ending half of an api query that goes out
	static char STARTDELIM = parenthesis;//delimits the beginning of a line in the api response
	static char MIDDELIM = ':';//delimits the name of a category in the response and the data it contains
	static char ENDDELIM = ',';//delimits the end of the line in the api response
	
	public void GetItem(){
		//initializes the class
	}
	public String getQuery(int x){
		String result ="";
		result = QOPEN+x+QCLOSE;
		return result;
	}
	public String readResult(String x){
		String[][] raw = new String[100][2];//the data received from walmart before it is sorted  0 for name 1 for data
		String[][] parsed = new String[9][2];//the data after it is sorted
			parsed[0][0]="name";//contains the name of the item
			parsed[1][0]="date";//contains the date last modified
			parsed[2][0]="salePrice";//contains the price of the item
			parsed[3][0]="stock";//contains whether or not the item is in stock
			parsed[4][0]="thumbnailImage";//contains a link to an image of the item
			parsed[5][0]="clearance";//contains whether or not the item is on sale
			parsed[6][0]="itemId";//contains the Walmart Item ID
			parsed[7][0]="longDescription";//contains a description of the item
			parsed[8][0]="Notes";//contains any programming notes or debugging information
		String tempName = "";//holds the extracted name string before parsing
		String tempData = "";//holds the extracted data string before parsing		
		
		String mod = x.substring(18);// removes the beginning of the string so it can be parsed
		mod= mod.replace("{","");//
		mod= mod.replace("}","");// Remove all Brackets from the string
		mod= mod.replace("[","");//
		mod= mod.replace("]","");//
		mod= mod.trim();//removes whight space from the ends so that a comma can be applyed to the end of the final value
		mod+=",";//adds a comma to the end of the final value
		
		for(int i=0;i<50;i++){
			//--------------------------------------------------------------------------------------preps the beginning of the string
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
			int openParenthesis = mod.indexOf(parenthesis);//checks to see the location of the next opening parentheses
			
			if(openParenthesis<locEdelim){//used to make sure that the program ignores characters in the middle of parenthesis
				if(openParenthesis<=0){openParenthesis=0;}//stops string out of bound errors
				String temp = mod.substring(openParenthesis+1);//creates a temporary version of the string starting after the relevant open parenthesis
				int closeParenthesis = temp.indexOf(parenthesis);//finds the relevant close parenthesis
				if(closeParenthesis<=0){closeParenthesis=0;}//stops string out of bound errors                                    
				temp = temp.substring(closeParenthesis);//removes the full value in-between the parenthesis inclusive of the parenthesis 
				int tempdelim = temp.indexOf(ENDDELIM);//finds the end delimiter after the parenthesis
				locEdelim = tempdelim + openParenthesis + closeParenthesis+1;//Determines where the end delimiter is located in the master string
			}
			
			if(locEdelim<=0){locEdelim=0;}//stops string out of bound errors
			tempData=mod.substring(0,locEdelim);//grabs the data portion of the string
			mod = mod.substring(locEdelim);//trims the name and data just acquired off of the string
			//--------------------------------------------------------------------------------------
			raw[i][0]=tempName;//enters the name found into the raw array
			raw[i][1]=tempData;//enters the data found into the raw array
			System.out.println(raw[i][0]+"  XX~~~~~~~XX  "+raw[i][1]);//debug
		}	
		/*TODO*/	return null;
	}

	
	private String SIF(String name, double price, int quantity, String image, boolean sale, String description, String notes,int itemid){
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
		//vvvvvvvvvvvvvvv price	
		result+= ":";
		temp = String.valueOf(price);
		result += temp;
		temp = "";
		result+= ":";
		//^^^^^^^^^^^^^^^ price
		//vvvvvvvvvvvvvvv quantity
		result+= ":";
		temp = String.valueOf(quantity);
		result += temp;
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
		temp = String.valueOf(sale);
		result += temp;
		temp = "";
		result+= ":";		
		//^^^^^^^^^^^^^^^ sale
		//vvvvvvvvvvvvvvv itemid
		result+= ":";
		temp = String.valueOf(itemid);
		result += temp;
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
