
public class Table {
	enum Type{text,number,bool};//used to indicate the type of data in a column
	boolean database; //used to tell that table if it is being used in the database or not
	String Name;//the name of a table used to identify it
	int Size;//the number of rows in the table
	static int CONST_column_count=10;//a constant to hold the maximum number of columns that a table can have;
	static int CONST_OuterDatum_Size=10;//do not change this number is hard coded into the mecanism used to find rows
	static int CONST_InnerDatum_Size=10;
	Column[] table = new Column[CONST_column_count];
	public void Table(String name,boolean DB){//the default constructor
		 Name=name;
		 database=DB;
		 Size=0;
	}
	public void addColumn(Type type,int lenght){
		
	}
	
	
	
	
	private class Column{// a wrapper class that holds all the data for a column as well as the relivant metadata
		String Name;
		dataType Type;//holds the datatype of the column
		public void Column(Type datatype){
			dataType=datatype;
		}
		public void addRow(String Text){
			if(dataType==text){
				
			}else{
				//error the user attempted to enter invalid data
			}
		}
		public void addRow(int Number){
			if(dataType==number){
				
			}else{
				//error the user attempted to enter invalid data
			}
		}
		public void addRow(boolean Bool){
			if(dataType==bool){
				
			}else{
				//error the user attempted to enter invalid data
			}
		}
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
							if(dataarr!=null){//makes suhure there is an innerdatum to send the data to
								dataarr[0].addRow(data);
							}else{//if there is not an inner datum in the current spot this adds a new one
								dataarr[0]=new InnerDatum();
								dataarr[0].addRow(data);
							}
						}else if(row_count<20){//checks to see if it is in the second inner datum
							f(dataarr!=null){//makes suhure there is an innerdatum to send the data to
								dataarr[1].addRow(data);
							}else{//if there is not an inner datum in the current spot this adds a new one
								dataarr[1]=new InnerDatum();
								dataarr[1].addRow(data);
							}
						}else if(row_count<30){//checks to see if it is in the third inner datum
							if(dataarr!=null){//makes suhure there is an innerdatum to send the data to
								dataarr[2].addRow(data);
							}else{//if there is not an inner datum in the current spot this adds a new one
								dataarr[2]=new InnerDatum();
								dataarr[2].addRow(data);
							}
						}else if(row_count<40){//checks to see if it is in the fourth inner datum
							if(dataarr!=null){//makes suhure there is an innerdatum to send the data to
								dataarr[3].addRow(data);
							}else{//if there is not an inner datum in the current spot this adds a new one
								dataarr[3]=new InnerDatum();
								dataarr[3].addRow(data);
							}
						}else{//checks to see if it is in the fifth inner datum
							if(dataarr!=null){//makes suhure there is an innerdatum to send the data to
								dataarr[4].addRow(data);
							}else{//if there is not an inner datum in the current spot this adds a new one
								dataarr[4]=new InnerDatum();
								dataarr[4].addRow(data);
							}
						}
					}else{	
						if(row_count<60){//checks to see if it is in the sixth inner datum
							if(dataarr!=null){//makes suhure there is an innerdatum to send the data to
								dataarr[5].addRow(data);
							}else{//if there is not an inner datum in the current spot this adds a new one
								dataarr[5]=new InnerDatum();
								dataarr[5].addRow(data);
							}
						}else if(row_count<70){//checks to see if it is in the seventh inner datum
							if(dataarr!=null){//makes suhure there is an innerdatum to send the data to
								dataarr[6].addRow(data);
							}else{//if there is not an inner datum in the current spot this adds a new one
								dataarr[6]=new InnerDatum();
								dataarr[6].addRow(data);
							}
						}else if(row_count<80){//checks to see if it is in the eighth inner datum
							if(dataarr!=null){//makes suhure there is an innerdatum to send the data to
								dataarr[7].addRow(data);
							}else{//if there is not an inner datum in the current spot this adds a new one
								dataarr[7]=new InnerDatum();
								dataarr[7].addRow(data);
							}
						}else if(row_count<90){//checks to see if it is in the ninth inner datum
							if(dataarr!=null){//makes suhure there is an innerdatum to send the data to
								dataarr[8].addRow(data);
							}else{//if there is not an inner datum in the current spot this adds a new one
								dataarr[8]=new InnerDatum();
								dataarr[8].addRow(data);
							}
						}else{//checks to see if it is in the tenth inner datum
							if(dataarr!=null){//makes suhure there is an innerdatum to send the data to
								dataarr[9].addRow(data);
							}else{//if there is not an inner datum in the current spot this adds a new one
								dataarr[9]=new InnerDatum();
								dataarr[9].addRow(data);
							}
						}
					}
				}else{//acctivated when there is no room left in the current outer datum}
					if(next==null){//checks to see if there is a next outer datum
						next=new OuterDatum();	
					}
					next.addRow(data);
				}
			}
			public void OuterDatum(){
				
			}
			private class InnerDatum{// a container for ten data points
				int index;
				Data[] dataarr = new Data[CONST_InnerDatum_Size];
				public void addRow(String data){
					
					
				}
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
