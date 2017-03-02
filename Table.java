
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
