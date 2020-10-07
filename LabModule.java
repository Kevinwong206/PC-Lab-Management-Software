import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LabModule {
    //create variable
    private ArrayList <Lab> lArray; //stores current running objects
    private ArrayList <String> tempArray;
    private int count ; 
    
    //default constructor to initialise variable
    public LabModule(){
        lArray = new ArrayList <Lab>();
        tempArray = new ArrayList <String>();
        count = 0;
    }
    
    //Method to save to temparray
    public void saveToTempArray()throws IOException{
        File file = new File ("Lab.txt");
        Scanner sc = new Scanner(file);
        
        //If it is not empty clear it
        if (!tempArray.isEmpty())
            tempArray.clear();
        
        while(sc.hasNextLine())
            tempArray.add(sc.nextLine());
        sc.close();
        
    }
    
    //method to add the object to the arraylist
    public void addItemArrayList(Lab aLab){
        lArray.add(aLab);
    }
    
    //method to modify the arraylist based on the option user chooses
    public void modifyAList(int choice,Lab aLab){
        
        int decision = choice - 1;

        String tempString = aLab.toString();
        tempArray.set(decision, tempString);
    }
    
    //method to delete arraylist based on the option user chooses
    public void deleteAList(int choice)throws IOException{
        int decision = choice -1;

        tempArray.remove(decision);
        System.out.println("\nNumber "+choice + " element has been removed");
        
        FileWriter fw = new FileWriter("Lab.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < tempArray.size(); i++) {
                pw.println(tempArray.get(i));
            }
            pw.close();
    }
    
    //method to print the arraylist 
    public void printArrayList(){
        
        try {
                //the file to be opened for reading  
                FileInputStream fis = new FileInputStream("Lab.txt");
                Scanner sc = new Scanner(fis);    //file to be scanned 

                //returns true if there is another line to read  
                while (sc.hasNextLine()) {
                    System.out.println(sc.nextLine()); //returns the line that was skipped  
                }
                sc.close();     //closes the scanner  
            }catch (IOException e) {
                e.printStackTrace();
            }
    }
    
    //Method to save to file
    public void saveToFile(Lab aLab) throws IOException {
            
        //Open filewriter and printWriter
        FileWriter fw = new FileWriter("Lab.txt", true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(aLab);
        pw.close();
    
    }
    
    //Method to save to file overwrite
    public void saveToFileOverWrite() throws IOException {

        FileWriter fw = new FileWriter("Lab.txt");
        PrintWriter pw = new PrintWriter(fw);
        for (int i = 0; i < tempArray.size(); i++) {
            pw.println(tempArray.get(i));
        }
        pw.close();
    }
    
    //Method to export to file
    public void exportToFile()throws IOException{
        
        FileWriter fw = new FileWriter("lab.html");
        PrintWriter pw = new PrintWriter(fw);
        //create html header
        pw.println("<!DOCTYPE html>");
        pw.println("<html>");
        pw.println("<head><title>Lab details</title></head>");
        pw.println("<body>");
        
        pw.println("<p>You can click the lab name to view the PC assigned in each lab.</p>");
        pw.println("<p>If there is nothing printed out after you click the lab, it means there is no PC assigned under that lab.</p>");
        pw.println("<br/>");
        
        //create table tag<table> and table header <th>
        pw.println("<table border=\"1\">");
        pw.println("<tr><th>Lab Name</th><th>Lab Code</th><th>Lab Size</th></tr>");
        
        int i=0;
        //Iterate the array
        //print the content and <tr><td> tag
        for (Lab aLab : lArray){
            
            pw.println("<tr>");
            pw.println("<td><a href=\"" + lArray.get(i).getlabName() +  ".txt\">" + lArray.get(i).getlabName() + "</td>");
            pw.println("<td>" + lArray.get(i).getLabCode() + "</td>");
            pw.println("<td>" + lArray.get(i).getLabSize() + "</td>");
            pw.println("</tr>");
            i++;
        }
        
        //close html document
        pw.println("</table>");
        pw.println("</body>");
        pw.println("</html>");
        
        //close file stream
        System.out.println("The lab details have been saved into lab.html file");
        pw.close();
    }
    
    //Method to search the lab
     public void searchLab(int choice) throws IOException{
        int decision = choice -1;
            System.out.println(tempArray.get(decision));
    }
}
