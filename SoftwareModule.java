import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SoftwareModule {

    //create variables
    private ArrayList<Software> sArray; //stores current running objects
    private ArrayList<String> tempArray;
    private String[] softwareName = new String[100];
    private int count;

    //default constructor to initialise variables
    public SoftwareModule() {
        sArray = new ArrayList<Software>();
        tempArray = new ArrayList<String>();

        count = 0;
    }

    //method to add the object to the arraylist
    public void addItemArrayList(Software aSoftware) {
        sArray.add(aSoftware);
    }

    //method to add the PCName into array
    public void addNameToArray(int sCount, String sName) {
        int sC = sCount - 1;
        softwareName[sC] = sName;
    }

    //Method to modify the array
    public void modifyArray(int choice, String sName) {
        int decision = choice - 1;
        softwareName[decision] = sName;
    }

    //Method to save array to text
    public void saveArraytoText() throws IOException {

        FileWriter p = new FileWriter("SoftwareName.txt");
        PrintWriter c = new PrintWriter(p);

        //Loop to save the array
        for (int i = 0; i < softwareName.length; i++) {

            c.println(softwareName[i]);
        }

        c.close();

    }

    //Method to delete the array
    public void deleteArray(int choice, String PCName) {

        int decision = choice - 1;

        for (int i = decision; i < softwareName.length; i++) {

            softwareName[decision] = softwareName[decision++];
        }
    }

    //method to modify the arraylist based on the option user chooses
    public void modifyAList(int choice, Software aSoftware) {
        int decision = choice - 1;
        String tempString = aSoftware.toString();
        tempArray.set(decision, tempString);
    }

    //method to save the data to tempArray
    public void saveToTempArray() throws IOException {
        File file = new File("Software.txt");
        Scanner sc = new Scanner(file);

        //Clear the temp array if it is not empty
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
        // use . remove to remove elemements in the array list 
        tempArray.remove(decision);
        System.out.println("\nNumber " + choice + " element has been removed");

        //write the tempArray to text file
        FileWriter fw = new FileWriter("Software.txt");
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
            FileInputStream fis = new FileInputStream("Software.txt");
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
    public void saveToFile(Software aSoftware) throws IOException {
        //Open filewriter and printWriter
        FileWriter fw = new FileWriter("Software.txt", true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(aSoftware);
        pw.close();
    }

    //Method to save to file overwrite
    public void saveToFileOverWrite() throws IOException {

        FileWriter fw = new FileWriter("Software.txt");
        PrintWriter pw = new PrintWriter(fw);
        for (int i = 0; i < tempArray.size(); i++) {
            pw.println(tempArray.get(i));
        }
        pw.close();
    }

    //Method to search from array list
    public int searchFromArrayList(String sName) {

        File file = new File("SoftwareName.txt");
        int index = 0;

        try {
            Scanner scanner = new Scanner(file);

            //now read the file line by line.
            int lineNum = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.contains(sName)) {

                    index = lineNum;
                }

                lineNum++;
            }
        } catch (FileNotFoundException e) {

            System.out.println("The software is not found in the database!");
        }

        return index;
    }

    //Method to save PC to file
    public void saveToPCFile(String PCName, int position) throws IOException {

        FileWriter fw = new FileWriter(PCName + ".txt", true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(sArray.get(position));

        pw.close();
    }

    //Method to export to file
    public void exportToFile() throws IOException {

        FileWriter fw = new FileWriter("software.html");
        PrintWriter pw = new PrintWriter(fw);

        //create html header
        pw.println("<!DOCTYPE html>");
        pw.println("<html>");
        pw.println("<head><title>PC details</title></head>");
        pw.println("<body>");

        //create table tag<table> and table header <th>
        pw.println("<table border=\"1\">");
        pw.println("<tr><th>Software Name</th><th>Software type</th><th>Software expiration date</th><th>Software RAM</th></tr>");

        int i = 0;
        //Iterate the array
        //print the content and <tr><td> tag
        for (Software aSoftware : sArray) {

            pw.println("<tr>");
            pw.println("<td>" + sArray.get(i).getSoftwareName() + "</td>");
            pw.println("<td>" + sArray.get(i).getSoftwareType() + "</td>");
            pw.println("<td>" + sArray.get(i).getDate() + "</td>");
            pw.println("<td>" + sArray.get(i).getRam() + "</td>");

            pw.println("</tr>");
            i++;

        }

        //close html document
        pw.println("</table>");
        pw.println("</body>");
        pw.println("</html>");

        //close file stream
        System.out.println("The software details have been saved into pc.html file");
        pw.close();
    }
}
