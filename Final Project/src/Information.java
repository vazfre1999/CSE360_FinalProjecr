public class Information {

	//initializing variables
	String[][] roster; // roster[row][column]
	String[][] attendance;
	int rosterRowCount;
	int attendanceRowCount;
	int dateColCount;
	int additionalUser;
	String[] headerRow = new String[] {
			"ID" , "First Name", "Last Name", "Program and Plan", "Academic Level", "ASURITE"
	};

	public int getAttendancePoint(int rowIndex, int dateColIndex) {
		dateColIndex += 6; // adds six to skip first 6 columns (which aren't date columns)
		return Integer.parseInt(roster[rowIndex][dateColIndex]);
	}

	//method saves the number of rows in Roster
	public void setRosterRowCount(int rowCount){
		rosterRowCount = rowCount;
	}

	//method returns the number of rows in Roster
	public int getRosterRowCount(){
		return rosterRowCount;
	}

	//method returns the number of rows in Roster
	public int getColumnCount(){
		return headerRow.length;
	}

	//method saves the number of rows in Attendance
	public void setAttendnanceRow(int rowCount){
		attendanceRowCount = rowCount;
	}

	//method returns the number of rows in Attendance
	public int getAttendanceRowCount(){
		return attendanceRowCount;
	}

	//method saves the roster array
	public void setRoster(String[][] roster){
		this.roster = roster;
	}

	//method returns the roster array
	public String[][] getRoster(){
		return roster;
	}


	//this method returns the column array
	public String[] getColumnArray() {
		return headerRow;
	}

	//this method saves the column array
	public void setColumninfo(String[] headerArray) {
		this.headerRow = headerArray;
	}

	//this method adds new column for Date
	public String[] addColumn(String date) {
		dateColCount++;

		//initailizing variables
		int num2 = headerRow.length + 1;
		String[] temp = new String[num2];

		//for loop to copy info from column and add date to temp
		for(int i = 0; i < num2; i++) {
			if( i == headerRow.length) {
				temp[i] = date;
			}else {
				temp[i] = headerRow[i];
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
	public String[][] increaseSize(String[][] roster){

		//initializing variables
		int rowCount = getRosterRowCount();
		String[][] newRoster = new String[rowCount][7];

		//adding column and copying info into new array
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < 7; j++) {

				if( j < 6) {
					newRoster[i][j] = roster[i][j];
				}else {
					newRoster[i][j] = "Absent";
				}
			}
		}
		return newRoster;
	}//end of class


	//compares email's and combines duplicates when adding attendance
	public String[][] compareEmails(String [][] attendance){

		//obtain number of rows in attendance
		int rowCount = getAttendanceRowCount();

		//for loop to iterate through
		for (int i = 0; i < rowCount; i++) {
			for(int k = 1; k < rowCount; k++ ) {
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


	//searches for user using email and adds attendance to roster create one table
	public String[][] combine(String[][] roster, String[][] attendance) {

		//comment here
		for(int i = 0; i < getRosterRowCount(); i++ ) {
			for(int j = 0; j < getAttendanceRowCount(); j++ ) {

				if(roster[i][5].compareTo(attendance[j][0]) == 0) {

					roster[i][6]= attendance[j][1];
				}
			}
		}//end of for loop

		return roster;
	}
}
