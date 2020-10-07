import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PC {

    //create variable
    private String PCName, IPV4, ManufacturedDate, PCType;
    private int Ram;
    //IPV4 Format
    private static final String IPV4_FORMAT
            = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
            + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    private static final Pattern IPV4_PATTERN = Pattern.compile(IPV4_FORMAT);

    //default constructor to initialise variable
    public PC() {
        PCName = "";
        IPV4 = "";
        ManufacturedDate = "";
        PCType = "";
        Ram = 0;
    }

    //getter for PCName
    public String getPC() {
        return this.PCName;
    }

    //getter for IPV4
    public String getIPV4() {
        return this.IPV4;
    }

    //getter for ManufacturedDate
    public String getManufacturedDate() {
        return this.ManufacturedDate;
    }

    //getter for Ram
    public int getDate() {
        return this.Ram;
    }

    //getter for PCType
    public String getPCType() {
        return this.PCType;
    }

    //getter for PCRam
    public int getPCRam() {
        return this.Ram;
    }

    //setter for PC Name
    public void setPCName(String name) {
        //If satisfy contidition, will set softwareName
        if (name.length() <= 40) {
            this.PCName = name;
        } else {
            System.out.println("Your device name is too long!!Keep it within 40 character");
        }

    }

    //setter for PC Type
    public void setPCType(String name) {

        //Validate the PC
        if (name.equalsIgnoreCase("Desktop") || name.equalsIgnoreCase("Laptop")) {
            this.PCType = name;
        } else if (!(name.equalsIgnoreCase("Desktop")) && !(name.equalsIgnoreCase("Laptop"))) {
            System.out.println("Invalid input, please try again :");
        }

    }

    //setter for IPV4
    public boolean setIPV4(String IPV4) {
        if (IPV4 == null) {
            return false;
        }

        Matcher matcher = IPV4_PATTERN.matcher(IPV4);
        
        this.IPV4=IPV4;
        return matcher.matches();
    }

//setter for ManufacturedDate
    public void setManufacturedDate(int date, int month, int year) {

        //Validate the validity in date,month and year
        if (year < 0 || year > 2021 || month > 12 || month < 1 || date < 0 || date > 32) {
            System.out.println("Invalid input, please try again :");
        } else {
            this.ManufacturedDate = (date + "/" + month + "/" + year);
        }
    }

    //setter for RAM
    public void setRam(int Ram) {
        if (Ram > 0 || Ram < 256) {
            this.Ram = Ram;
        } else {
            System.out.println("Invalid Ram GB ,The Minimum amount of Ram is 0 GB and the Maximum amount of Ram is 256 GB");
        }
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

    //method to create arraylist 
    public static ArrayList createArrayList() {
        ArrayList PCArrayList = new ArrayList();
        return PCArrayList;
    }

    //format the output of the Software class's object
    @Override
    public String toString() {
        return "PC Name = " + PCName + ", IPV4 = " + IPV4 + ", ManufacturedDate = " + ManufacturedDate + ", PCType = " + PCType + ", Ram =" + Ram;
    }

}
