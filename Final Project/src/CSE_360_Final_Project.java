import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class CSE_360_Final_Project {

	//This method displays the main menu
	public CSE_360_Final_Project(){

		//Creating tabs in menu
		JMenu fileTab = new JMenu("File");
		JMenu aboutTab = new JMenu("About");

		//creating JFrame
		JFrame frame = new JFrame("CSE360 Final Project");

		//creating JMenuBar
		JMenuBar bar = new JMenuBar();

		JMenuItem roster = new JMenuItem("Load a Roster");
		JMenuItem attendance = new JMenuItem("Add Attendance");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem plot = new JMenuItem("Plot Data");
		JMenuItem studentInformation = new JMenuItem("Student Info");

		//Creating textArea
		JTextArea textArea = new JTextArea();

		//creating object for Information
		Information info = new Information();

		//add items onto its corresponding item
		fileTab.add(roster);
		fileTab.add(attendance);
		fileTab.add(save);
		fileTab.add(plot);
		aboutTab.add(studentInformation);

		//adding fileTab and aboutTab to JMenuBar
		bar.add(fileTab);
		bar.add(aboutTab);

		//adding text into frame
		frame.add(textArea);

		//action event for when you click on about and studentInfo
		studentInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Creating JFrame
				JFrame frame2 = new JFrame("Student Information");


				//creating JLabel with student info
				JLabel student1 = new JLabel("Name: Luis Vazquez" + "    ID: 1214868824" + "    Email: lovazque@asu.edu");
				JLabel student2 = new JLabel("Name: Abraham Lords" + "   ID: 1207874781" + "    Email: allords@asu.edu");
				JLabel student3 = new JLabel("Name: Nan Li" + "          ID: 1217668959" + "    Email: nanli9@asu.edu");

				//creating JPanel
				JPanel panel = new JPanel();


				//adding Label into panel
				panel.add(student1);
				panel.add(student2);
				panel.add(student3);


				panel.setLayout(new GridLayout(3,1 ));
				panel.add(student1);
				panel.add(student2);
				panel.add(student3);
				panel.setBorder(BorderFactory.createEmptyBorder(10, 100, 0, 10));


				//set frame2 visible,location, and its size
				frame2.add(panel);
				frame2.setVisible(true);
				frame2.setSize(600,300);
				frame2.setLocation(500, 125);
			}
		});


		//action event when you want to load Attendance roster
		roster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//creating JfileChooser object
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File selectedFile = chooser.getSelectedFile();
				String file = selectedFile.getAbsolutePath();

				try {
					//creating a FileReader and BufferedReader
					FileReader reader = new FileReader(file);
					BufferedReader buffer = new BufferedReader(reader);
					FileReader reader2 = new FileReader(file);
					BufferedReader buffer2 = new BufferedReader(reader2);

					//Prints Name of columns
					String[] columns = info.getColumnArray();

					//Initialized variables
					int index = 0;
					int count = 0;
					String line = " ";

					while((buffer.readLine()) != null) {
						count++;
					}
					//keep the number of rows
					info.setRosterRowCount(count);

					//creating data Array
					String[][] roster = new String[count][6];

					//parse through file and save in array
					while((line = buffer2.readLine()) != null) {
						//saves info in data2
						roster[index] = line.split(",");
						index++;
					}
					//save data
					info.setRoster(roster);

					//create table with data
					JTable table = new JTable(roster, columns);
					frame.add(new JScrollPane(table));

					//make text area not visible
					textArea.setVisible(false);

					//close BufferedReader
					textArea.read(buffer, null);
					buffer.close();
					buffer2.close();
					textArea.requestFocus();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}//end of actionPerformed()
		});


		//action event when you want to load attendance
		attendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//date picker goes here
				JLabel label = new JLabel("Selected Date:");
				final JTextField text = new JTextField(20);
				JButton b = new JButton("popup");
				JPanel p = new JPanel();
				p.add(label);
				p.add(text);
				p.add(b);
				final JFrame f = new JFrame();
				f.getContentPane().add(p);
				f.pack();
				f.setVisible(true);
				f.setLocation(550, 300);
				f.setSize(450, 150);
				b.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							String date;
							text.setText(new DatePicker2(f).setPickedDate());
							date = new DatePicker2(f).getPickedDate();
							
							//creating JfileChooser object
							JFileChooser chooser = new JFileChooser();
							chooser.showOpenDialog(null);
							File selectedFile = chooser.getSelectedFile();
							String file = selectedFile.getAbsolutePath();
	
							try {
								//creating a FileReader and BUfferedReader
								FileReader read = new FileReader(file);
								BufferedReader buffer = new BufferedReader(read);
								FileReader reader2 = new FileReader(file);
								BufferedReader buffer2 = new BufferedReader(reader2);
	
								//add another column for date
								String[] newColumn = info.addColumn(date);
	
								//Initialized index and line
								int index = 0;
								int fileRowCount = 0;
								String line = " ";
	
								//parse through file and count number of rows
								while((buffer.readLine()) != null) {
									fileRowCount++;
								}
	
								//creating data Array
								String[][] attendance = new String[fileRowCount][2];
								String[][] roster = info.getRoster();
	
								//parse through file and save in array
								while((line = buffer2.readLine()) != null) {
	
									//saves info in data2
									attendance[index] = line.split(",");
									index++;
								}
	
								//setting number of rows
								info.setAttendnanceRow(fileRowCount);
	
								//combines duplicates asurite
								attendance = info.compareEmails(attendance);
								info.setAttendance(attendance);
	
								roster = info.increaseSize(roster);
								roster = info.combine(roster, attendance);
								info.setRoster(roster);	//saving new roster
								
								//create table with data
								JTable table = new JTable(roster, newColumn);
								frame.add(new JScrollPane(table));
	
								//window that displays the number of people is roster, additional attendees, and their name
								JFrame frame3 = new JFrame("Results");
	
								//creating buttons
								int num1 = info.getRosterRowCount();
								JLabel lbl1 = new JLabel("Data loaded for " + num1 + " users in roster");
								JLabel lbl2 = new JLabel("(Number) additional attendee was found: ");
	
								//create a gridpanel to add labels
								JPanel panel = new JPanel();
								panel.setLayout(new GridLayout(3,1 ));
								panel.add(lbl1);
								panel.add(lbl2);
								panel.setBorder(BorderFactory.createEmptyBorder(10, 175, 10, 10));
	
								//add panel to frame
								frame3.add(panel);
								frame3.setVisible(true);
								frame3.setSize(600,250);
								frame3.setLocation(500, 125);
	
								//make text area not visible
								textArea.setVisible(false);
	
								//close BufferedReader
								textArea.read(buffer, null);
								buffer.close();
								buffer2.close();
								textArea.requestFocus();
	
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	
							
						}
				}); //end of action event when b is pressed
			}	
		});//end of action event when attendance is pressed
				
		

		//action event when you want to save
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] headerRow = info.getColumnArray();
				String[][] roster = info.getRoster();
				int columnCount = info.getColumnCount();
				int rowCount = info.getRosterRowCount();

				try {
					FileWriter writer = new FileWriter("out2.txt", true);

					// row index starts at -1 to include header of roster
					for (int rowIndex = -1; rowIndex < rowCount; rowIndex++) {
						for (int colIndex = 0; colIndex < columnCount; colIndex++) {

							 //if at header row index
							if (rowIndex == -1) {
								writer.write(headerRow[colIndex]);
							} else {
								writer.write(roster[rowIndex][colIndex]);
							}
							if (colIndex + 1 < columnCount) {
								writer.write(",");
							} else if (rowIndex + 1 < rowCount) {
								writer.write("\n");
							}
						}
					}
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}

			}
		});

		//code in here

		//action event when you want to plot
		plot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScatterPlot plot = new ScatterPlot("Attendance Roster");
				plot.setSize(800, 400);
				plot.setLocationRelativeTo(null);
				//example.setDefaultCloseOperation(WindowConst ants.EXIT_ON_CLOSE);
				plot.setVisible(true);
			}
		});

		//set frame visible, location, and its size
		frame.setLocation(300, 50);
		frame.setSize(1000, 700);
		frame.setVisible(true);

		//will terminate program when window is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(bar);
	}


	public static void main(String[] args) {
		new CSE_360_Final_Project();

	}//end of main

}
