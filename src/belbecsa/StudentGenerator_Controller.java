/*
    StudentGenerator_Controller.java
    Author: hdvor
    Modifier: sbelb
    Date: April 6, 2020

    Description
    Class to control the GUI view inputs and actions by using the models
 */
package belbecsa;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Student;
import static models.Student.parse;
import models.StudentStatus;

/**
 * FXML Controller class
 *
 * @author hdvor
 * @modifier sbelb
 */
public class StudentGenerator_Controller implements Initializable {
    //TODO: PART_4_0
    // You will find several of the later steps a lot easier
    // if you use generics with the lstRoster!
    @FXML
    private BorderPane main;
    @FXML
    private ListView<String> lstRoster;
    @FXML
    private Slider sldNum;
    @FXML
    private Label lblGpa;
    @FXML
    private Slider sldGpa;
    @FXML
    private Button btnCreate;
    @FXML
    private Label lblStudentNum;
    @FXML
    private Button btnShowStudents;
    static boolean answer;
    private File activeFile = null;
    ObservableList<String> activeOl = null;
    private final int ID_SIZE = 9;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        activeOl = FXCollections.observableArrayList();
        lstRoster.setStyle("-fx-font-family: \'Courier New\'; -fx-font-size: 10;");       
        lblStudentNum.textProperty().bind(Bindings.format("%.0f", 
                sldNum.valueProperty()));
        //TODO: PART_6_6
        // The slider that bind the lable the slide property value for the 
        // maximum number of students to be shown is given here. You need to 
        // write the code for the other slider that shows the gpa
        lblGpa.textProperty().bind(Bindings.format("%.1f",
                sldGpa.valueProperty()));   
    }
    
    /**
     * method to create CSV files of Student data with a file of names
     * @param event clicking the create CSV button
     * @throws IOException opening file or reading from file
     */
    @FXML
    private void createCSV(ActionEvent event) throws IOException {
        activeFile = new File("students.csv");
        //TODO: PART_4_1
        // Add the code to import part of the data (first name last name) from 
        // "names_raw.txt", and to generate the data for the other properties. 
        // You may need to create another method to help with the task.
        // 1. Create the appropriate objects for opening and writing onto 
        // a file for each record that you are going to write on csv:
         PrintWriter pw = new PrintWriter(activeFile);
         Scanner input = null;
    
        try{
            File names = new File("names_raw.txt");
            input = new Scanner(names);
            pw.print("Student ID,First Name,Last Name,GPA,Status\n");
            while(input.hasNext()) {
                // 2. generate a large random integer and convert it into a 
                // string. Make sure that the string contains 9 digits, if it 
                // does not, concatenate 0s to those numbers that have less than
                // 9 digits
                int randomId = (int)((Math.random() + 1) * 10298357);
                String id = randomId + "";
                int missingDigits = ID_SIZE - id.length();
                if (id.length() > ID_SIZE) {
                    id.substring(0,ID_SIZE);
                } else if(id.length() < ID_SIZE) {
                    for (int i = 0; i < missingDigits; i++) {
                        id += "0";
                    }
                }   
                
                // 3. Read first name and last name from the "names_raw.txt"  
                String firstName = "";
                String lastName = "";
                if(names.exists()){
                    firstName = input.next().trim();
                    lastName = input.next().trim();          
                    // to grab the blank space at the end of each line
                    String blank = input.next();
                }
         
                // 4. Generate a random number between 0.0 and 4.0 formated 
                // that will contain only 1 floating point that will be the 
                // value for the student's gpa
                double randomGpa = Math.random() * 4;
                String gpa = String.format("%.1f", randomGpa);
                randomGpa = Double.parseDouble(gpa);

                // 5. Using a random generated integer with a value between 
                // 0 and 5, randomly select a status from StudentStatus 
                // (0 for GOOD_STANDING....... 5 GRADUATED). Be efficient, 
                // do not use 6 if statements. You may need to review enum
                int randomStatus = (int)((Math.random() * 6));                
                StudentStatus status = null;                
                StudentStatus[] statuses = StudentStatus.values();
                for (int i = 0; i < statuses.length; i++) {
                    if (i == randomStatus) {
                        status = statuses[randomStatus];
                    }
                }
                
                // 6. create a student object with the properties from the 
                // above values
                Student student = new Student(id, lastName, firstName, 
                        randomGpa, status);
                pw.print(student.toString());
            }
            btnCreate.setDisable(true);
                
        // 7. inside the catch block of the exception create an error alert that
        // will show an appropriate message if something goes wrong.
        } catch (IOException e){
            Stage error = getPopup(e);
            error.setTitle("Error!");
            error.show();
        } catch (IllegalArgumentException s) {
            Stage error = getPopup(s);
            error.setTitle("Error!");
            error.show();   
        }
        finally {
            pw.close(); 
        }
        //END PART_4_1
    }
    
    /**
     * method to open roster file with file chooser
     * @param event clicking open option on file menu bar
     */
    @FXML
    private void openRoster(ActionEvent event) {
        FileChooser openFile = new FileChooser();
        openFile.setInitialDirectory(Paths.get(".").toFile());
        openFile.setTitle("Open File");
        //TODO: PART_4_2
        // Create an extension filter here so that only .txt and .csv files 
        // are shown by default.in the FileChooser.
        ExtensionFilter filterText = new ExtensionFilter("Text Files", "*.txt");
        ExtensionFilter filterCsv = new ExtensionFilter("CSV Files", "*.csv");
        openFile.getExtensionFilters().addAll(filterText, filterCsv);
        openFile.setSelectedExtensionFilter(filterCsv);
       
        //END PART_4_2
        Window stage = main.getScene().getWindow();
        try {
            //TODO: PART_4_3 
            // Open the file for reading and iterate through each
            // line of the file. Use the static method for parsing in the 
            // Student class to convert each line into a Student object.
            // Add each student to the roster_list.
            File selectedFile = openFile.showOpenDialog(stage);
            if (selectedFile == null) {
                throw new IOException("You forgot to select a file!");
            } else {
                Scanner input = new Scanner(selectedFile);            
                activeFile = new File(selectedFile.getPath());    
                // clear lists before loading new file onto list
                activeOl.clear();
                lstRoster.getItems().clear();
                String title = input.nextLine().trim();
                String[] headers = title.split(",");
                if(headers.length != 5){
                    throw new IllegalArgumentException("Error in file - "
                            + "record(s) not in correct format");
                }
                activeOl.addAll(String.format("%-12s%-15s%-15s%-8s%-15s\n", 
                   headers[0].trim(), headers[1].trim(), headers[2].trim(), 
                   headers[3].trim(), headers[4].trim()));
                while(input.hasNext()){
                    String nextStudent = input.nextLine().trim();
                    Student student = parse(nextStudent, ",");
                    activeOl.addAll(String.format("%-12s%-15s%-15s%-8.1f%-15s\n", 
                            student.getStudentId(), student.getFirstName(), 
                            student.getLastName(), student.getGpa(), 
                            student.getStatus().getLongName()));    
                }
                input.close();
                lstRoster.setItems(activeOl);
            //END PART_4_3                            
            } 
        //TODO: PART_4_4
        // Add some error handling here! What happens if you have problem 
        // reading in the roster? Maybe a nice alertbox to warn the user
        // that something bad has happened?
        } catch (IllegalArgumentException s) {
            Stage error = getPopup(s);
            error.setTitle("Error!");
            error.show();   
            //END PART_4_4
        }
        catch (IOException s) {
            Stage error = getPopup(s);  
            error.setTitle("Error!");
            error.show();
            //END PART_4_4
        }
    }
    /**
     * method to create a popup window to display error messages for exceptions
     * @param s the exception caused
     * @return popup window stage
     */
    private Stage getPopup(Exception s) {
        Label message = new Label(s.getMessage());
        StackPane popup = new StackPane();     
        popup.getChildren().add(message);
        Scene scene = new Scene(popup, 350, 75);    
        Stage error = new Stage();
        error.setScene(scene);
        return error;    
    }
    
    /**
     * method to filter active file by number filter & gpa filter
     * @param event clicking display students button
     * @throws IOException caused by reading from file
     */
    @FXML
    private void display(ActionEvent event) throws IOException{
        // TODO: PART_4_5
        // Write the code that will restrict the number of students shown       
        greaterThanGpa(event);
         //END PART_4_5
    }

    /**
     * method to filter active file by GPA filter
     * @param event clicking the greater than GPA button
     * @throws IOException caused by reading from the file
     */
    @FXML
    private void greaterThanGpa(ActionEvent event) throws IOException {
        // TODO: PART_4_7
        // Write the code that will filter and display the students that have
        // a gpa above the number shown by the label that is controlled by the
        // slider
        lstRoster.getItems().removeAll(activeOl);
        double gpaNumber = Double.parseDouble(lblGpa.getText());
        int numberStudents = Integer.parseInt(lblStudentNum.getText());
        try {
            if(activeFile == null) throw new IOException("No file selected");
            Scanner input = new Scanner(activeFile); 
            String title = input.nextLine().trim();
            String[] headers = title.split(",");
                if(headers.length != 5){
                    throw new IllegalArgumentException("Error in file - "
                        + "record(s) not in correct format");
                }
            activeOl.addAll(String.format("%-12s%-15s%-15s%-8s%-15s\n", 
                headers[0], headers[1], headers[2], headers[3], headers[4]));
            int counter = 0;
            // if display student button clicked, stop loop at number on slider
            while(input.hasNext()){
                if (counter == numberStudents 
                        && event.getSource().equals(btnShowStudents)) {
                    break;
                }
                String nextStudent = input.nextLine().trim();
                Student student = parse(nextStudent, ",");                   
                if(student.getGpa() > gpaNumber) {
                    activeOl.addAll(String.format("%-12s%-15s%-15s%-8.1f%-15s\n", 
                            student.getStudentId(), student.getFirstName(), 
                            student.getLastName(), student.getGpa(), 
                            student.getStatus().getLongName()));    
                    counter++;
                }     
            }
            input.close();
            // set list view to the new filter set
            lstRoster.setItems(activeOl);
        }catch(IOException x){
           Stage error = getPopup(x);  
           error.show();                       
        }
       // END PART_4_7
    }

    /**
     * method to check if there is any file to save
     * @param event clicking the save option on the menu bar
     * @throws IOException caused if there's no file open to be saved
     */
    @FXML
    private void saveRoster(ActionEvent event) throws IOException{

        //TODO: PART_4_8
        // Checks if activeFile is null. If it is null, nothing to save.
        // Otherwise, if activeFile is not null, call save as to open up the 
        // active file and write out the contents of the lstRoster. 
        // See PART_4_9 for instructions on what that should look like.
        try {
            if(activeFile == null) {
                throw new NullPointerException("Theres nothing to save!");
            } else saveAsRoster();
        } catch(NullPointerException s){ 
                 Stage error = getPopup(s);
                 error.show();
        }
        //End PART_4_8
    }
    
    /**
     * method to save filtered file as new file if there are records
     */
    private void saveAsRoster() {
        //TODO: PART_4_9
        // Implement the body as follows:
        //  a. Use a FileChooser to allow the user to
        //    select the file they want to use to save
        //    their roster to.
        FileChooser save = new FileChooser();
        save.setInitialDirectory(new File("."));
        save.setTitle("Save File As");
        ExtensionFilter csv = new ExtensionFilter("CSV File" , "*.csv");
        save.getExtensionFilters().add(csv);
        save.setInitialFileName("test");
        
        //  b. Write out the contents of the lstRoster
        //    to that file in the .csv format.
        //    id, firstname, lastname, gpa, atatus
        //    note that position should use the short name (GS,AP,AS,BS,E,G)
        try {
            File file = save.showSaveDialog(new Stage());
            if(file == null) throw new IOException("Save not completed!");
            File saved = new File(file.getPath());
            PrintWriter pw = new PrintWriter(saved);
            for(int i = 0; i < lstRoster.getItems().size(); i++) {
                String line = lstRoster.getItems().get(i);
                String id = line.substring(0, 11).trim();
                String firstName = line.substring(12, 26).trim();
                String lastName = line.substring(27, 41).trim();
                String gpa = line.substring(42, 49).trim();
                String statusLong = line.substring(50).trim();
                String lineToWrite;
                if(i==0) {
                    lineToWrite = id + "," + firstName + "," + lastName
                         + "," + gpa + "," + statusLong + "\n";
                } else {
                String statusShort = StudentStatus.longToShortName(statusLong);
                lineToWrite = id + "," + firstName + "," + lastName
                         + "," + gpa + "," + statusShort + "\n";
                }
                pw.print(lineToWrite);
            }
            pw.close();
            activeOl.clear();
            activeOl.add("Your file has been saved");
            lstRoster.setItems(activeOl);


        }catch (IOException x){   
            System.out.println("error");
            Stage error = getPopup(x);
            error.show();
        }
        
    }
    //END PART_4_9
    
    /**
     * method to exit the application after confirming first
     * @param event clicking the exit option on the menu bar
     */
    @FXML
    private void exitHandler(ActionEvent event) {
        //TODO: PART_4_10
        // Ask the user if they are sure they want to
        // quit using an AlertBox.        
        answer = checkAnswer();
        if(answer) {
            System.exit(0);
        }
        //END PART_4_10
    }

    /**
     * method to create confirmation popup window
     * @return value of button clicked, yes = true, no = false
     */
    private static boolean checkAnswer() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Confirm Exit");
        stage.setMinWidth(300);  
        Label message = new Label("Are you sure you want to close?");
        
        // create buttons
        Button yesBtn = new Button("YES");
        Button noBtn = new Button("NO");
        
        yesBtn.setOnAction(e -> {
            answer = true;
            stage.close();
        });
        noBtn.setOnAction(e -> {
            answer = false;
            stage.close();
        });
        
        HBox buttonPane = new HBox(20, yesBtn, noBtn); 
        buttonPane.setAlignment(Pos.CENTER);
        VBox pane = new VBox(20, message, buttonPane);
        pane.setAlignment(Pos.CENTER);
        VBox.setMargin(buttonPane, new Insets(0, 0, 10, 0));
        VBox.setMargin(message, new Insets(10, 0, 0, 0));
        Scene scene = new Scene(pane); 
        stage.setScene(scene);
        stage.showAndWait();
        return answer;
    }
    /**
     * method to clear the active file and list view
     * @param event clicking the clear students button
     */
    @FXML
    private void clear(ActionEvent event) {
        activeFile = null;
        activeOl.clear();
        lstRoster.getItems().clear();
    }
    
    /**
     * method to open text editor with readme file
     * @param event clicking the help > about option on the menu bar
     * @throws IOException cause if file is not found
     */
    @FXML
    private void aboutHandler(ActionEvent event) throws IOException {
        //TODO: PART_4_11
        // Write the code that will open readme.txt file as an external file
        // using your default text editor such as notpad or vi. You will have to
        // do some research on how to do this.        
        try {
            File readMe = new File("./readme.txt");
            Desktop.getDesktop().open(readMe);
        } catch (IOException x){
            Stage error = getPopup(x);
            error.show();
        }
        // END PART_4_11
    }
}
