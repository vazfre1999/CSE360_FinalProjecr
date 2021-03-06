public class Information {

	//initializing variables
	private String[][] roster; // roster[row][column]
	private String[][] attendance; // for new attendance
	private int rosterRowCount;
	private int attendanceRowCount;
	private int totalColCount = 6;
	private int dateColCount; // should equal (totalColCount - 6) at all times
	private String[] headerRow = new String[] {
			"ID" , "First Name", "Last Name", "Program and Plan", "Academic Level", "ASURITE"
	};

	public int getDateColCount() {
		return dateColCount;
	}

	public String[] getHeaderRow() {
		return headerRow;
	}

	// extracts attendance minutes value from chart
	public int getAttendancePoint(int rowIndex, int dateColIndex) {
		dateColIndex += 6; // adds six to skip first 6 columns (which aren't date columns)

		try {
			Integer.parseInt(roster[rowIndex][dateColIndex]);
		} catch (NumberFormatException e) {
			return 0; // cannot convert to integer then return with value 0
		}
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
		int newSize = headerRow.length + 1;
		String[] temp = new String[newSize];

		//for loop to copy info from column and add date to temp
		for(int i = 0; i < newSize; i++) {
			if( i < headerRow.length) {
				temp[i] = headerRow[i];
			}else {
				temp[i] = date;
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

	//method that creates a new row to add date and copies existing data
	public String[][] increaseSize(String[][] roster){

		totalColCount++;
		dateColCount = totalColCount - 6;

		int rowCount = getRosterRowCount();
		String[][] newRoster = new String[rowCount][totalColCount];

		//adding column and copying info into new array
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < totalColCount; j++) {
				if( j < totalColCount-1 ) {	// for all old roster columns
					newRoster[i][j] = roster[i][j];
				} else {					// then the new column get initialized to "Absent"
					newRoster[i][j] = "Absent";
				}
			}
		}
		this.roster = newRoster;
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
					if(i == k) {
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

		this.roster = roster;
		return roster;
	}
}
