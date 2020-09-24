/*
    Student.java
    Author: H.D
    Modifier: S.B
    Date: April 6, 2020

    Description
    Models a Student object with studentID, firstName, lastName, gpa and status
    First name an Last name have a maximum lengtj and the student number is 
    made out of 9 digits
 */
package models;

/**
 *
 * @author H.D
 * @modifier S.B
 */
public class Student {

    private String studentId;
    private String firstName;
    private String lastName;
    private double gpa;
    private StudentStatus status;

    public static final int MAX_FIRSTNAME_LENGTH = 15;
    public static final int MAX_LASTNAME_LENGTH = 20;

    /**
     * Create a new Student
     *
     * @param studentID the student's ID
     * @param sFirstName the student's first name
     * @param sLastName the student's last name
     * @param gpa the student's GPA
     * @param status the student's status
     */
    public Student(String studentID, String sFirstName, String sLastName,
            double gpa, StudentStatus status) {
        setStudentID(studentID);
        setFirstName(sFirstName);
        setLastName(sLastName);
        setGpa(gpa);
        setStatus(status);
    }

    /**
     * Parses a delimited string of student data. Assuming the delimiter is the
     * comma character ',', the format is:
     * <studentid>,<lastname>,<firstname>,<gpa>, <status>
     * Where <studentid>, <firstname> and <lastname> are strings (which are
     * trimmed at the parsing stage). Where <gpa> is double Where <status> is a
     * string that corresponds to the short name of the student status (GA, AP,
     * AS, BS, E, G)
     *
     * @param data the player data as a delimited string
     * @param delim the delimiter
     * @return a Player object if successfully input, otherwise throws an exception.
     */
    public static Student parse(String data, String delim) {

        //TODO: PART_3_2
        // 1. Split the data into an array of strings using delim
        String[] studentInfo = data.split(delim);        
        
        // 2. Make sure there are exactly 5 elements in the array
        if(studentInfo.length != 5) {
            throw new IllegalArgumentException("Error in file - record(s) not "
                    + "in correct format");
        } 
        
        // 3. trim the first three elements and put them into
        //    some variables (id, firstname and lastname)
        String id = studentInfo[0].trim();
        String lastName = studentInfo[1].trim();
        String firstName = studentInfo[2].trim();
        
        // 4. trim the third elements, parse them into double and store it
        // in a variable (gpa)
        double gpa = Double.parseDouble(studentInfo[3].trim());
        
        // 5. trim the fifth element, parse into a StudentStatus
        //    and then store it in a variable (status)
        String stat = studentInfo[4].trim();        
        StudentStatus status = StudentStatus.valueOfShortName(stat);
       
        // 6. Try to create a new player using the variables. If
        //    you succeed, return the student.
        try {
            Student student = new Student(id, firstName, lastName, gpa, status);
            return student;
        // 7. If you fail at any step, thrown an IllegalArgumentException
        //    with an appropriate message.
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Student " + id + " could not be"
                    + " created from the students.csv file");
        }
        //END PART_3_2
    }

    /**
     * Accessor for the student ID
     *
     * @return the student's ID
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Mutator for for the student's ID
     *
     * @param studentID for the student's ID
     * @throws IllegalArgumentException when the student number does not match
     * the format for a student number
     */
    public void setStudentID(String studentID) throws IllegalArgumentException {
        if (studentID.matches("[0-9]{9}")) {
            this.studentId = studentID;
        } else {
            throw new IllegalArgumentException("Incorrect student no format");
        }
    }

    /**
     * Accessor for the student's first name
     *
     * @return the student's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Mutator for the student's first name
     *
     * @param firstName the student's first name
     */
    public void setFirstName(String firstName) {
        if (firstName.equals(null)) {
            throw new IllegalArgumentException("You must enter your first name");
        } 
            this.firstName = firstName;
    }

    /**
     * Accessor for the student's last name
     *
     * @return the student's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Mutator for the student's last name
     *
     * @param lastName the student's last name
     */
    public void setLastName(String lastName) {
        if (lastName.equals(null)) {
            throw new IllegalArgumentException("You must enter your last name");
        } 
        this.lastName = lastName;
    }

    /**
     * Accessor for the student's gpa
     *
     * @return the student's gpa
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * Mutator for the student's gpa
     *
     * @param gpa the student's gpa
     * @throws IllegalArgumentException when gpa values are outside the gpa
     * limits
     */
    public void setGpa(double gpa) throws IllegalArgumentException {
        if (gpa < 0 || gpa > 4.0) {
            throw new IllegalArgumentException("Invalid GPA value");
        } else {
            this.gpa = gpa;
        }
    }

    /**
     * Accessor for the student's status
     *
     * @return the student's status
     */
    public StudentStatus getStatus() {
        return status;
    }

    /**
     * assigns student's status
     * @param status 
     */
    public void setStatus(StudentStatus status) {
        this.status = status;
    }
    
    /**
     * method to return string representation of a Student
     * @return 
     */
    @Override
    public String toString() {
        //TODO: PART_3_1
        // Generate a string here that is a textual representation
        //  of a Student. You should be able to save this string
        //  to a file.
        String student = (studentId + "," + firstName + "," +  lastName + "," 
                +  gpa + "," + status.getShortName()) + "\n";
        return student;
        //END PART_3_1
    }
}
