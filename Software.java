import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Software {
    //create variable
    private String softwareName, softwareType, date;
    private int ram;
    
    //default constructor to initialise variable
    public Software(){
        softwareName = "";
        softwareType = "";
        ram = 0;
        date = "";
    }
    
    //getter for softwareName to return softwareName
    public String getSoftwareName(){
        return this.softwareName;
    }
    
    //getter for softwareType to return softwareType
    public String getSoftwareType(){
        return this.softwareType;
    }
    
    //getter for ram to return ram
    public int getRam(){
        return this.ram;
    }
    
    //getter for date to return date
    public String getDate(){
        return this.date;
    }
    
    //setter for softwareName
    public void setSoftwareName(String name){
        //If satisfy contidition, will set softwareName
        if (name.length()<=30){
            this.softwareName=name;
        }
        
        else 
            System.out.println("Software Name must be within 30 characters");
    }
    
    //setter for softwareType
    public void setSoftwareType(String type){
       //If satisfy contidition, will set softwareType
       if (type.equalsIgnoreCase("freeware") || type.equalsIgnoreCase("shareware") ||  type.equalsIgnoreCase("proprietary")){ 
            this.softwareType = type;
       }
        
       else if (!(type.equalsIgnoreCase("freeware")) && !(type.equalsIgnoreCase("shareware")) && !(type.equalsIgnoreCase("proprietary")))
            System.out.println("Invalid input, please try again :");
    }
    
    //setter for ram
    public void setRam(int ramRequirement){
        //If satisfy contidition, will set ram
        if(ramRequirement>0 && ramRequirement<65){
            this.ram=ramRequirement;
        }
        else 
            System.out.println("RAM storage cannot be lesser than 1 and larger than 64GB");
    }
    
    //setter for softwareName
    public void setDate(String expireDate, boolean dateBool){
        //If satisfy contidition, will set date
        if (dateBool == true)
            this.date=expireDate;
        
        else
            System.out.println("invalid date, please try again\n");
    }
    
    //method to input String
    public static String inputString(String prompText){
        Scanner input = new Scanner (System.in);
        System.out.print(prompText);
        return input.nextLine();  
    }
    
    //method to input integer
    public static int inputInteger(String prompText){
        Scanner input = new Scanner (System.in);
        System.out.print(prompText);
        return input.nextInt();  
    }

    //method to validate the date 
    public static boolean valDate (String date){
        DateFormat validate = new SimpleDateFormat("dd/MM/yyyy");
        Date objectDate = null;
        validate.setLenient(false);
        
        try{
            objectDate = validate.parse(date);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    //method to create arraylist 
    public static ArrayList createArrayList() {
        ArrayList sArrayList = new ArrayList();
        return sArrayList;
    } 
    
    //format the output of the Software class's object
    public String toString() {
        return "Software Name= "+softwareName + ", Software Type = " + softwareType + ", Ram Requirement = "+ ram+ ", Date Expiration = "+date;
    }
}
