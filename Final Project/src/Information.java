public class Information {

	//initializing variables
	String[][] roaster;
	String[][] attendance;
	int roasterRow;
	int attendanceRow;
	int additionalUser;
	String[] column = new String[] {
			"ID" , "First Name", "Last Name", "Program and Plan", "Academic Level", "ASURITE"
	};
	
	
	//method saves the number of rows in Roaster
	public void setRoasterRow(int row){
		roasterRow = row;	
	}
	
	//method returns the number of rows in Roaster
	public int getRoasterRow(){
		return roasterRow;
	}
	
	//method saves the number of rows in Attendance
	public void setAttendnanceRow(int row){
		attendanceRow = row;	
	}
	
	//method returns the number of rows in Attendance
	public int getAttendanceRow(){
		return attendanceRow;
	}
	
	//method saves the roaster array 
	public void setRoaster(String[][] roaster){
		this.roaster = roaster;
	}
	
	//method returns the roaster array
	public String[][] getRoaster(){
		return roaster;
	}
	

	//this method returns the column array
	public String[] getColumnInfo() {
			return column;
	}
	
	//this method saves the column array
	public void setColumninfo(String[] column) {
		
		this.column = column;
	}
	
	//this method adds new column for Date
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
	
	//method saves the array of attendance
	public void setAttendance(String[][] attendance){
		this.attendance = attendance;
		
	}
	
	//method returns the array of attendance
	public String[][] getAttendance(){
		return attendance;
		
	}
	
	
	//method that creates a new row to add date and copies existing data
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
	
	
	//compares email's and combines duplicates when adding attendnace
	public String[][] compareEmails(String [][] attendance){
		
		//obtain number of rows in attendance
		int row = getAttendanceRow();
		
		//for loop to iterate through 
		for (int i = 0; i < row; i++) {
			for(int k = 1; k < row; k++ ) {
				//comparing to find similar asurite
				if(attendance[i][0].compareTo(attendance[k][0]) == 0) {
					if(i == k) 
					{
						break;
					}else{
						//adding tow integers and storing it in array
						int num1 = Integer.parseInt(attendance[i][1]); //converting into int
						int num2 = Integer.parseInt(attendance[k][1]);
						int result = num1 + num2;
						attendance[i][1] = Integer.toString(result);
						
						//removing duplicate
						attendance[k][0] = "";
						attendance[k][1] = "";
							
					}
				}
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