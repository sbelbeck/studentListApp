/*
    Name:  Sam Belbeck
    Assignment:  Assignment 5
    Program: Student Generator
    Date:  April 6, 2020
    
    Description:
    Program creates file with list of student info based on file with a list 
    of names. User can filter the file by 2 criteria, number of students and 
    student gpa. User can save the filtered lists as new file or save over old
    file.
*/

package belbecsa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author H.D
 */
public class StudentGenerator_Main extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * method to start the application
     * @param stage stage of the application
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("StudentGenerator_View.fxml"));
        stage.setTitle("Student and Roster Generator");
        stage.setScene(new Scene(root));
        stage.show();
    }

}
