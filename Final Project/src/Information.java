public class Information {

	//initializing variables
	String[][] roaster;
	String[][] attendance;
	String[] column = new String[] {
			"ID" , "First Name", "Last Name", "Program and Plan", "Academic Level", "ASURITE"
	};
	
	int row;
	
	public void setRoasterRow(int row){
		this.row = row;	
	}
	
	//method returns file
	public int getRoasterRow(){
		return row;
	}
	
	public void setAttendnanceRow(int row){
		this.row = row;	
	}
	
	//method returns file
	public int getAttendanceRow(){
		return row;
	}
	
	//method saves inputed file
	public void setRoaster(String[][] roaster){
		this.roaster = roaster;
	}
	
	//method returns file
	public String[][] getRoaster(){
		return roaster;
	}
	
	//this method returns column info
	public String[] getColumns() {
			return column;
	}
	
	//this saves info in column info
	public void setColumninfo(String[] column) {
		
		this.column = column;
	}
	
	//this method addes new date to column
	public String[] addColumn(String date) {
		
		//initailizing variables
		int num2 = column.length + 1;
		String[] temp = new String[num2];
		
		//for loop to copy info from column and add date to temp
		for(int i = 0; i < num2; i++) {
			if( i == column.length) {
				temp[i] = date;
			}else {
				temp[i] = column[i];
			}
		}
		//save new column info
		setColumninfo(temp);
		return temp;
	}
	
	//method saves inputed file
	public void setAttendance(String[][] attendance){
		this.attendance = attendance;
		
	}
	
	//method returns file
	public String[][] getAttendance(){
		return attendance;
		
	}
	
	//method that creates a new row with existing data
	public String[][] increaseSize(String[][] roaster){
		
		//initializing variables
		int row = getRoasterRow();
		String[][] newRoaster = new String[row][7];

		//adding column and copying info into new array
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < 7; j++) {
				
				if( j < 6) {
					newRoaster[i][j] = roaster[i][j];
				}else {
					newRoaster[i][j] = "Absent";
				}
			}
		}
		return newRoaster;
	}//end of class
	
	
	//compares email's and combines duplicates
	public String[][] compareEmails(String [][] attendance){
		
		//obtain number of rows in attendance
		int row = getAttendanceRow();
		
		//for loop to iterate through 
		for (int i = 0; i < row; i++) {
			for(int k = 1; k < row; k++ ) {
				//comparing to find similar asurite
				if(attendance[i][0].compareTo(attendance[k][0]) == 0) {
					if(i == k) {
						break;
					}else {
						//adding tow integers and storing it in array
						int num1 = Integer.parseInt(attendance[i][1]); //converting into int
						int num2 = Integer.parseInt(attendance[k][1]);
						int result = num1 + num2;
						attendance[i][1] = Integer.toString(result);
						
						//removing duplicate
						attendance[k][0] = "";
						attendance[k][1] = "";
						
					}
				}//end of if
			}
		}//end of for loop
		return attendance;
	}
	
	
	
	//searches for user using email and adds attendance to roaster create one table
	public String[][] combine(String[][] roaster, String[][] attendance){
		
		//comment here
		for(int i = 0; i < getRoasterRow(); i++ ) {
			for(int j = 0; j < getAttendanceRow(); j++ ) {
				
				if(roaster[i][5].compareTo(attendance[j][0]) == 0) {

						roaster[i][6]= attendance[j][1];
				}
			}
		 }//end of for loop
		
		return roaster;
	}

	
	}
