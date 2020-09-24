/*
    StudentStatus.java
    Author: H.D
    Modifier: S.B
    Date: April 6, 2020

    Description
    Models Student Status using six constants
*/

package models;

/**
 *
 * @author H.D
 * @modifier S.B
 */
public enum StudentStatus {

    GOOD_STANDING("Good Standing", "GS"),
    ACADEMIC_PROBATION("Academic Probation", "AP"),
    ACADEMIC_SUSPENSION("Academic Suspension", "AS"),
    BEHAVIOUR_SUSPENSION("Behaviour Suspension", "BS"),
    EXPELLED("Expelled", "E"),
    GRADUATED("Graduated", "G");
    
    private final String longName;
    private final String shortName;

    private StudentStatus(String longName, String shortName) {
        this.longName = longName;
        this.shortName = shortName;
    }

    /**
     * Accessor for getting the status in the long form
     * @return the status name in long form
     */
    public String getLongName() {
        return longName;
    }

    /**
     * Accessor for getting the status in the short form
     * @return the status name in short form
     */
    public String getShortName() {
        return shortName;
    }
    
    /**
     * method to get StudentStatus from the status' shortName
     * @param shortName short name of the status
     * @return StudentStatus
     */
    public static StudentStatus valueOfShortName(String shortName) {
        switch(shortName.toUpperCase()) {
            case "GS":
                return GOOD_STANDING;
            case "AP":
                return ACADEMIC_PROBATION;
            case "AS":
                return ACADEMIC_SUSPENSION;
            case "BS":
                return BEHAVIOUR_SUSPENSION;
            case "E":
                return EXPELLED;
            case "G":
                return GRADUATED;
            default:
                throw new IllegalArgumentException(shortName + 
                        " is not a legal status");
        }
    }
    
    /**
     * method to get the shortName from the longName of the status
     * @param longName long name of the status
     * @return shortName of the status
     */
    public static String longToShortName(String longName) {
        switch(longName) {
            case "Good Standing":
                return "GS";
            case "Academic Probation":
                return "AP";
            case "Academic Suspension":
                return "AS";
            case "Behaviour Suspension":
                return "BS";
            case "Expelled":
                return "E";
            case "Graduated":
                return "G";
            default:
                throw new IllegalArgumentException(longName + 
                        " is not a legal status");
        }
    }        
}
