import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Lab {

    //Initalise the variable
    private String labName;
    private String labCode;
    private int labSize;

    //default constructor to initialise variable
    public Lab() {

        labName = "";
        labCode = "";
        labSize = 0;

    }

    //Set lab name
    public void setLabName(String labName) {

        this.labName = labName;

    }

    //Set lab code
    public void setLabCode(String labCode) {

        this.labCode = labCode;
    }

    //Set lab size
    public void setLabSize(int labSize) {

        this.labSize = labSize;
    }

    //Get lab name
    public String getlabName() {
        return this.labName;
    }

    //Get lab code
    public String getLabCode() {

        return this.labCode;
    }

    //Get lab size
    public int getLabSize() {

        return this.labSize;
    }

    //method to input String
    public static String inputString(String prompText) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompText);
        return input.nextLine();
    }

    //method to input integer
    public static int inputInteger(String prompText) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompText);
        return input.nextInt();
    }

    //Method to create arraylist
    public static ArrayList createArrayList() {
        ArrayList lArrayList = new ArrayList();
        return lArrayList;
    }

    //format the output of the Lab class's object
    public String toString() {

        return "Lab Name= " + labName + ",Lab Code = " + labCode + ", Lab Size = " + labSize;
    }
}
