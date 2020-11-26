import java.awt.*; 
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class CSE_360_Final_Project {

	//This method displays the main menu
	public CSE_360_Final_Project(){
		
		//Creating tabs in menu
		JMenu file = new JMenu("File"); 
		JMenu about = new JMenu("About"); 
		
		//creating JFrame
		JFrame frame = new JFrame("CSE360 Final Project");
		
		//creating JMenuBar
		JMenuBar bar = new JMenuBar(); 
		
		JMenuItem roaster = new JMenuItem("Load a Roster");
		JMenuItem attendance = new JMenuItem("Add Attendance");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem plot = new JMenuItem("Plot Data");
		JMenuItem studentInformation = new JMenuItem("Student Info");
		
		//Creating textArea
		JTextArea text = new JTextArea();
		
		//creating object for Information
		Information in = new Information();
		
		//add items onto its corresponding item
		file.add(roaster);
		file.add(attendance);
		file.add(save);
		file.add(plot);
		about.add(studentInformation);
		
		//adding file and about to JMenuBar
		bar.add(file);
		bar.add(about);
		
		//adding text into frame
		frame.add(text);

		//action event for when you click on about and studentInfo
		studentInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Creating JFrame
				JFrame frame2 = new JFrame("Student Information");
				
				//creating JPanel
				JPanel panel = new JPanel();
				
				//creating JLabel with student info
				JLabel student1 = new JLabel("Name: Luis Vazquez" + "    ID: 1214868824" + "    Email: lovazque@asu.edu");
				//JLabel student2 = new JLabel(" Name: " + "    ID: " + "    Email:  ");
				//JLabel student3 = new JLabel(" Name: " + "    ID: " + "    Email:  ");
				
				//adding Label into panel
				panel.add(student1);
				//panel.add(student2);
				//panel.add(student3);
				
				//add panel into frame
				frame2.add(panel);
				
				//set frame2 visible,location, and its size
				frame2.setVisible(true);
				frame2.setSize(600,200);
				frame2.setLocation(500, 125);
			}
		});
		
		
		//action event when you want to load Attendance roaster
		roaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				//creating JfileChooser object
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File selectedFile = chooser.getSelectedFile();
				String file = selectedFile.getAbsolutePath();
				
				try {
					//creating a FileReader and BUfferedReader
					FileReader reader = new FileReader(file);
					BufferedReader buffer = new BufferedReader(reader);
					FileReader reader2 = new FileReader(file);
					BufferedReader buffer2 = new BufferedReader(reader2);
					
					//Prints Name of columns
					String[] columns = new String[] {
							"ID" , "First Name", "Last Name", "Program and Plan", "Academic Level", "ASURITE"
					};
					
					//Initialized variables
					int index = 0;
					int count = 0;
					String line = " ";
					
					while((buffer.readLine()) != null) {
						 count++;  
					}					
					//keep the number of rows
					in.setRoasterRow(count);
					
					//creating data Array
					String[][] roaster = new String[count][6];
					
					//parse through file and save in array
					while((line = buffer2.readLine()) != null) {
						
						 //saves info in data2
						roaster[index] = line.split(",");
						 index++;  
					}
					//save data
					in.setRoaster(roaster);
					
					//create table with data
					JTable table = new JTable(roaster, columns);
					frame.add(new JScrollPane(table));
					
					//make text area not visible
					text.setVisible(false);
					
					//close BufferedReader
					text.read(buffer, null);
					buffer.close();
					buffer2.close();
					text.requestFocus();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}//end of actionPerformed()
		});
		
		
		//action event when you want to load attendnace 
		attendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
					
					String date = "Date";
					
					//date picker goes here					
					
					
					
			
					//add another column for date
					String[] column = in.addColumn(date);
					
					
					//Initialized index and line
					int index = 0;
					int count = 0;
					String line = " ";
					
					//parse through file and count number of rows
					while((buffer.readLine()) != null) {
						 count++;  
					}					
					
					//creating data Array
					String[][] attendance = new String[count][2];
					String[][] roaster = in.getRoaster();
					
					//parse through file and save in array
					while((line = buffer2.readLine()) != null) {
						
						 //saves info in data2
						attendance[index] = line.split(",");
						 index++;  
					}
					
					//setting number of rows
					in.setAttendnanceRow(count);
					
					//combines duplicates asurite
					attendance = in.compareEmails(attendance);
					in.setAttendance(attendance);
					
					roaster = in.increaseSize(roaster);
					roaster = in.combine(roaster, attendance);
					in.setRoaster(roaster); //save roaster after combining roaster and attendanace
					
					//create table with data
					JTable table = new JTable(roaster, column);
					frame.add(new JScrollPane(table));
					
					//make text area not visible
					text.setVisible(false);
					
					//close BufferedReader
					text.read(buffer, null);
					buffer.close();
					buffer2.close();
					text.requestFocus();
					
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});		
		
		//action event when you want to save 
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//code in here
				
			}
		});
		
		//action event when you want to plot  
		plot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//code in here
				
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
