import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class PCModule {

    //create variable
    private ArrayList<PC> PCArray; //stores current running objects
    private ArrayList<String> tempArray;
    private String[] pcName = new String[100];
    private int count;

    //default constructor to initialise variable
    public PCModule() {
        PCArray = new ArrayList<PC>();
        tempArray = new ArrayList<String>();
        count = 0;
    }

    //method to add the object to the arraylist
    public void addItemArrayList(PC pc) {
        PCArray.add(pc);
    }

    //method to add the PCName into array
    public void addNameToArray(int nameCount, String PCName) {

        int nameC = nameCount - 1;
        pcName[nameC] = PCName;
    }

    //Method to Modify the array 
    public void modifyArray(int choice, String PCName) {
        int decision = choice - 1;

        pcName[decision] = PCName;

    }

    //Method to Save the array into text
    public void saveArraytoText() throws IOException {

        FileWriter p = new FileWriter("PCName.txt");
        PrintWriter c = new PrintWriter(p);

        for (int i = 0; i < pcName.length; i++) {

            c.println(pcName[i]);
        }

        c.close();

    }

    //Method to Delete the array
    public void deleteArray(int choice, String PCName) {

        int decision = choice - 1;

        //Loop to delete the array
        for (int i = decision; i < pcName.length; i++) {

            pcName[decision] = pcName[decision++];
        }
    }

    //method to modify the arraylist based on the option user chooses
    public void modifyAList(int choice, PC pc) {
        int decision = choice - 1;
        String tempString = PCArray.toString();
        tempArray.set(decision, tempString);
    }

    //Method to Save the decision to temparray
    public void saveToTempArray() throws IOException {
        File file = new File("PC.txt");
        Scanner sc = new Scanner(file);

        //If it is not empty clear it 
        if (!tempArray.isEmpty()) {
            tempArray.clear();
        }

        while (sc.hasNextLine()) {
            tempArray.add(sc.nextLine());
        }
        sc.close();

    }

    //method to delete arraylist based on the option user chooses
    public void deleteAList(int choice) throws IOException {
        int decision = choice - 1;

        tempArray.remove(decision);
        System.out.println("\nNumber " + choice + " element has been removed");

        FileWriter fw = new FileWriter("PC.txt");
        PrintWriter pw = new PrintWriter(fw);
        for (int i = 0; i < tempArray.size(); i++) {
            pw.println(tempArray.get(i));
        }
        pw.close();

    }

    //method to print the arraylist 
    public void printArrayList() {
        try {
            //the file to be opened for reading  
            FileInputStream fis = new FileInputStream("PC.txt");
            Scanner sc = new Scanner(fis);    //file to be scanned 

            //returns true if there is another line to read  
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine()); //returns the line that was skipped  
            }
            sc.close();     //closes the scanner  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method to save the object to Text File
    public void saveToFile(PC pc) throws IOException {
        //Open filewriter and printWriter
        FileWriter fw = new FileWriter("PC.txt", true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(pc);
        pw.close();

    }

    //Method to Save to file overwrite
    public void saveToFileOverWrite() throws IOException {

        FileWriter fw = new FileWriter("PC.txt");
        PrintWriter pw = new PrintWriter(fw);

        //Loop to print inside the file
        for (int i = 0; i < tempArray.size(); i++) {
            pw.println(tempArray.get(i));
        }
        pw.close();

    }

    //Method to Search from arraylist
    public int searchFromArrayList(String PCName) {

        File file = new File("PCName.txt");
        int index = 0;

        try {
            Scanner scanner = new Scanner(file);

            //now read the file line by line.
            int lineNum = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                //Validate the PCName
                if (line.contains(PCName)) {

                    index = lineNum;
                }

                lineNum++;
            }
        } catch (FileNotFoundException e) {

            System.out.println("The PC is not found in the database!");
        }

        return index;
    }

    //Method to find Name
    public int findName(String PCName) {

        //Initialise value
        File file = new File("PCName.txt");
        int index = 0;
        int lineNum = 1;

        try {
            Scanner scanner = new Scanner(file);

            //now read the file line by line.
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                //If line contains pc name index will be 0 
                if (line.contains(PCName)) {
                    index = 0;
                    index = lineNum;
                }

                lineNum++;
            }
        } catch (FileNotFoundException e) {

            System.out.println("The PC Name is usable !");
        }

        return index;
    }

    //Method to save to labfile
    public void saveToLabFile(String lNameForFile, int position) throws IOException {

        FileWriter fw = new FileWriter(lNameForFile + ".txt", true);
        PrintWriter pw = new PrintWriter(fw);

        String str = tempArray.get(position);

        pw.println(str);

        pw.close();
    }

    //method to print the arraylist 
    public void printPCDetails(String lNameForFile) {

        System.out.println();
        try {
            FileInputStream fis = new FileInputStream(lNameForFile + ".txt");
            Scanner sc = new Scanner(fis);

            //If it is true return truw
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine()); //Return lines that were skipped
            }
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method to delete arraylist based on the option user chooses
    public void deleteAListFromLabFile(int position, String lNameForFile) throws IOException {
        int decision = position - 1;

        tempArray.remove(decision);
        System.out.println("\nNumber " + position + " element has been removed");

        FileWriter fw = new FileWriter(lNameForFile + ".txt");
        PrintWriter pw = new PrintWriter(fw);

        for (int i = 0; i < tempArray.size(); i++) {
            pw.println(tempArray.get(i));
        }

        pw.close();

    }

    //Method to Export to HTML
    public void exportToFile() throws IOException {

        FileWriter fw = new FileWriter("pc.html");
        PrintWriter pw = new PrintWriter(fw);

        //create html header
        pw.println("<!DOCTYPE html>");
        pw.println("<html>");
        pw.println("<head><title>PC details</title></head>");
        pw.println("<body>");

        //create table tag<table> and table header <th>
        pw.println("<table border=\"1\">");
        pw.println("<tr><th>PC Name</th><th>PC PV4</th><th>PC Manufactured Date</th><th>PC Type</th><th>PC Ram</th></tr>");

        pw.println("<p>You can click the PC name to view the software assigned in each PC.</p>");
        pw.println("<p>If there is nothing printed out after you click the pc, it means there is no software assigned under that pc.</p>");
        pw.println("<br/>");

        int i = 0;
        //Iterate the array
        //print the content and <tr><td> tag
        for (PC pc : PCArray) {

            pw.println("<tr>");
            pw.println("<td><a href=\"" + PCArray.get(i).getPC() + ".txt\">" + PCArray.get(i).getPC() + "</td>");
            pw.println("<td>" + PCArray.get(i).getIPV4() + "</td>");
            pw.println("<td>" + PCArray.get(i).getManufacturedDate() + "</td>");
            pw.println("<td>" + PCArray.get(i).getPCType() + "</td>");
            pw.println("<td>" + PCArray.get(i).getPCRam() + "</td>");
            pw.println("</tr>");
            i++;

        }

        //close html document
        pw.println("</table>");
        pw.println("</body>");
        pw.println("</html>");

        //close file stream
        System.out.println("The PC details have been saved into pc.html file");
        pw.close();
    }

    //Method to Search inside the PC
    public void searchPC(int choice) throws IOException {
        int decision = choice - 1;

        System.out.println(tempArray.get(decision));
    }
}
