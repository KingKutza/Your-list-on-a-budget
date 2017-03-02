
public class Table {
	enum Type{text,number,bool};//used to indicate the type of data in a column
	boolean database; //used to tell that table if it is being used in the database or not
	String Name;//the name of a table used to identify it
	int Size;//the number of rows in the table
	static int CONST_collumn_count=10;//a constant to hold the maximum number of columns that a table can have;
	static int CONST_OuterDatum_Size=10;
	static int CONST_InnerDatum_Size=10;
	Column[] table = new Column[CONST_collumn_count];
	public void Table(String name,boolean DB){//the default constructor
		 Name=name;
		 database=DB;
		 Size=0;
	}
	public void addColumn(Type type,int lenght){
		
	}
	private class Column{// a wrapper class that holds all the data for a collumn as well as the relivant metadata
		String Name;
		
		private class OuterDatum{//a container that holds ten inner datums or 100 data points 
			int index;
			int row_count;//used to keep track of the number of rows in the datum to determin when it is nessesary to hand the new row down a recursive ladder
			InnerDatum[] dataarr = new InnerDatum[CONST_OuterDatum_Size];
			OuterDatum next = null;
			//new OuterDatum();//a recursive link to the next outer datum
			public void addRow(String data){//adds a new row of data to the table
				if(row_count<100){//checks to see if there is space left in the current datum for an additional rows
					if(row_count<50){//halvs the data for more efficiant row insertion
						if(row_count<10){//checks to see if it is in the first inner datum
							dataarr[0].addRow( data);
						}else if(row_count<20){//checks to see if it is in the second inner datum
							dataarr[1].addRow( data);
						}else if(row_count<30){//checks to see if it is in the third inner datum
							dataarr[2].addRow( data);
						}else if(row_count<40){//checks to see if it is in the fourth inner datum
							dataarr[3].addRow( data);
						}else{//checks to see if it is in the fifth inner datum
							dataarr[4].addRow( data);
						}
					}else{	
						if(row_count<60){//checks to see if it is in the sixth inner datum
							dataarr[5].addRow( data);
						}else if(row_count<70){//checks to see if it is in the seventh inner datum
							dataarr[6].addRow( data);
						}else if(row_count<80){//checks to see if it is in the eighth inner datum
							dataarr[7].addRow( data);
						}else if(row_count<90){//checks to see if it is in the ninth inner datum
							dataarr[8].addRow( data);
						}else{//checks to see if it is in the tenth inner datum
							dataarr[9].addRow( data);
						}
					}
				}else{
					if{
						
					}else{
						
					}
				}
				
			}
			public void OuterDatum(){
				
			}
			private class InnerDatum{// a container for ten data points
				int index;
				Data[] dataarr = new Data[CONST_InnerDatum_Size];
				public void InnerDatum(){
					
				}
				private class Data{// a container for a single data point
					
					
					public void Data(){
						
					}
					private class Text{
						
					}
					private class Num{
						
					}
					private class Bool{
						
					}
				}
			}	
		}
	}
}
