==========================================
STUDENT GENERATOR APPLICATION DESCRIPTION
==========================================

When application is launched for the first time, it will show the interface. 
The first think that needs to be done when application runs for the first time is to create a csv file by pressing Create CSV button. The program will run the code and import part of the data from names_row.txt which must be imported prior to executing the java application. Once the csv file is created the button for creating the CSV is disabled and will remain so till the end of the application.
After creating the csv file, the user can open the file using File > Open > Roster File. The use must browse and select the csv file. If an incorrect file is selected, the program displays an error alert. The list of all students and their records will be shown in the ListView separated by commas, as they are stored in csv. 
The user can restrict the amount of data shown in the LIstView in two different ways:
1.	By using the upper slider, which is bound to a label that shows the number. Once a number is set, the user clicks “Display Students” button to display the as many users as the label shows.
2.	A second way is by using the second slider at the bottom, where it sets a GPA value. After that by clicking Greater than GPA button, the list view will display all only those students whose gpa is greater than the value shown on the label.
The user can save the data that are present on the list view by selecting File > Save Roster File. If the list view does not have any data displayed, the program ignores the command. If the list view contains data, the user will be given the option to save the file using a different file name from the one(s) they had saved before. This way the user later can open any of the files that will contain different number of student records.
User can clear the data and the ListView by clicking the clear button. The clear button does not delete the file, it just removes the data from the view and from the active file loaded up in RAM.
The user can bring open this file in their default text editor, by selecting Help > About
User can exit the application using File > Exit. Before exiting the user will be asked for a confirmation using a confirmation dialog.

