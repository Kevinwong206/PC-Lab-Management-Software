import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String args[]) throws IOException {

        int userInput = 0;

        //lab Maintenance variables
        String lName = "";
        String lCode = "";
        String lNameForFile = "";
        int lSize, position = 0;
        
        //PC Maintenance variables
        String PCName = "";
        String IPV4 = "";
        String ManufacturedDate = "";
        String PCType = "";
        String LabName = "";
        int PCRam, PCDate, PCMonth, PCYear, nameCount = 0, PCValidation = 0;

        //software maintenance variables
        String softwareName = "";
        String decision = "";
        String sName = "";
        String sDate = "";
        String sType = "";
        String menuDecision = ""; 
        boolean dateBool = false;
        int sRAM, counter = 0, sCount = 0;

        //Creating new module
        SoftwareModule sModule = new SoftwareModule();
        PCModule PCModule = new PCModule();
        LabModule lModule = new LabModule();

        do {
            printMenu();
            userInput = getOption("Option: ");
            System.out.println();

            if (userInput < 1 || userInput > 6) {
                System.out.println("Invalid Option, please select between 1-6\n");
            }

            if (userInput == 6) {
                System.out.println("System Exit !\n");
                System.exit(0);
            }

            switch (userInput) {
                case 1://------------------------------------------------ Lab Maintenance --------------------------------------------------
                    do {
                        printLabMenu();
                        userInput = getOption("Option: ");

                        if (userInput < 1 || userInput > 7) {
                            System.out.println("\nInvalid Option, please select between 1-6\n");
                        }

                        if (userInput == 7) {
                            System.out.println("\nReturn to main menu !\n");
                        }

                        switch (userInput) {
                            case 1://================================================ Create Lab Profile ==================================================
                                do { //loop if user wants to enter more lab
                                    Lab aLab = new Lab();

                                    //input name 
                                    lName = aLab.inputString("\nPlease enter the lab name: ");
                                    aLab.setLabName(lName);

                                    //input lab code
                                    lCode = aLab.inputString("Please enter the lab code: (The lab Code must start with letter L and must follow with 5 alphabets or numbers) ");

                                    if (lCode.length() > 6 || lCode.charAt(0) != 'L' && lCode.charAt(0) != 'l') {
                                        do {

                                            lCode = aLab.inputString("Please reenter the lab code: (The lab Code must start with letter L and must follow with 5 alphabets or numbers) ");

                                        } while (lCode.length() > 6 || lCode.charAt(0) != 'L' && lCode.charAt(0) != 'l');

                                        aLab.setLabCode(lCode);
                                    } else {
                                        aLab.setLabCode(lCode);
                                    }

                                    //input lab size
                                    lSize = aLab.inputInteger("Please enter the lab size: (The lab size must not exceed 50) ");

                                    if (lSize < 1 || lSize > 50) {
                                        do {

                                            lSize = aLab.inputInteger("Please reenter the lab size: (The lab size must not exceed 50) ");

                                        } while (lSize < 1 || lSize > 50);

                                        aLab.setLabSize(lSize);
                                    } else {
                                        aLab.setLabSize(lSize);
                                    }

                                    //save to file
                                    lModule.saveToFile(aLab);

                                    // add the Lab object to the array list
                                    for (int i = 0; i <= counter; i++) {
                                        lModule.addItemArrayList(aLab);
                                        break;
                                    }

                                    decision = aLab.inputString("\nWould you like to continue adding a new Lab? (Y/N) : ");

                                } while (decision.equalsIgnoreCase("Y"));

                                break;

                            case 2: //================================================ Modify Lab Profile ==================================================

                                lModule.saveToTempArray();

                                do {
                                    Lab aLab = new Lab();
                                    int choice = 0;

                                    lModule.printArrayList();

                                    choice = aLab.inputInteger("\nWhich Lab would you like to edit (starting from 1): ");

                                    //input name 
                                    lName = aLab.inputString("\nPlease enter the lab name: ");
                                    aLab.setLabName(lName);

                                    //input lab code 
                                    lCode = aLab.inputString("Please enter the lab code: (The lab Code must start with letter L and must follow with 5 alphabets or numbers) ");

                                    if (lCode.length() < 6 || lCode.charAt(0) != 'L' && lCode.charAt(0) != 'l') {
                                        do {

                                            lCode = aLab.inputString("Please reenter the lab code: (The lab Code must start with letter L and must follow with 5 alphabets or numbers) ");

                                        } while (lCode.length() < 6 || lCode.charAt(0) != 'L' && lCode.charAt(0) != 'l');

                                        aLab.setLabCode(lCode);
                                    } else {
                                        aLab.setLabCode(lCode);
                                    }

                                    //input lab size
                                    lSize = aLab.inputInteger("Please enter the lab size: (The lab size must not exceed 50) ");

                                    if (lSize < 1 || lSize > 50) {
                                        do {

                                            lSize = aLab.inputInteger("Please reenter the lab size: (The lab size must not exceed 50) ");

                                        } while (lSize < 1 || lSize > 50);

                                        aLab.setLabSize(lSize);
                                    } else {
                                        aLab.setLabSize(lSize);
                                    }

                                    //modify the array list
                                    lModule.modifyAList(choice, aLab);

                                    decision = aLab.inputString("\nWould you like to continue modifying the lab profile? (Y/N) : ");

                                    lModule.saveToFileOverWrite();

                                } while (decision.equalsIgnoreCase("Y"));
                                break;

                            case 3: //================================================ Assign PC to Lab ==================================================

                                //declare scanner
                                Scanner sc = new Scanner(System.in);

                                System.out.println("All PCs: ");
                                System.out.println("----------------------------");

                                //list all the PCs
                                PCModule.printArrayList();
                                System.out.println();

                                System.out.println("All Labs: ");
                                System.out.println("----------------------------");

                                //list all the lab
                                lModule.printArrayList();

                                do {

                                    System.out.println();
                                    System.out.print("Which PC you would like to select? PC Name: ");
                                    PCName = sc.nextLine();

                                    //Search for the position of pcName in the PCArray Array List
                                    position = PCModule.searchFromArrayList(PCName);

                                    System.out.println();
                                    System.out.print("Which lab you would like to assign the PC called " + PCName + " to? Please type in the lab name! ");
                                    lNameForFile = sc.nextLine();

                                    //save to file
                                    PCModule.saveToLabFile(lNameForFile, position);

                                    System.out.print("Would you like to continue assigning any PC to the lab? (Y/N) : ");
                                    decision = sc.nextLine();

                                } while (decision.equalsIgnoreCase("Y"));

                                break;

                            case 4: //================================================ Remove PC from Lab ==================================================

                                do {
                                    //declare scanner
                                    Scanner in = new Scanner(System.in);

                                    //list all the lab
                                    lModule.printArrayList();

                                    System.out.print("The PC you wish to remove is from which lab? (Please choose the lab name) ");
                                    lNameForFile = in.nextLine();

                                    System.out.println("All PCs: ");
                                    System.out.println("----------------------------");

                                    //print all the PC in the lab selected//
                                    PCModule.printPCDetails(lNameForFile);

                                    System.out.print("Which pc you wish to remove from this lab? (Please type the PC number (starting from 1)) : ");
                                    position = in.nextInt();

                                    //Removing process
                                    PCModule.deleteAListFromLabFile(position, lNameForFile);

                                    System.out.print("Would you like to continue removing any PC from the lab? (Y/N) : ");
                                    decision = in.nextLine();

                                } while (decision.equalsIgnoreCase("Y"));

                                break;

                            case 5: //================================================ Delete Lab Profile ==================================================

                                Lab aLab = new Lab();
                                lModule.saveToTempArray();

                                do {
                                    int choice = 0;

                                    lModule.printArrayList();

                                    choice = aLab.inputInteger("\nWhich lab would you like to remove (starting from 1): ");
                                    lModule.deleteAList(choice);
                                    decision = aLab.inputString("\nWould you like to delete another item? (Y/N) : ");

                                } while (decision.equalsIgnoreCase("Y"));
                                break;

                            case 6: //================================================ Display Lab details ==================================================

                                lModule.printArrayList();
                                System.out.println();
                                break;

                            case 7: //================================================ Exit Lab Menu ==================================================
                                break; //exit to main menu
                        } //end of switch case

                        menuDecision = decision("Would you like to return to main menu or remain at Lab Module (Y- remain at lab module, N-return main menu) : ");
                        System.out.println();

                    } while (userInput < 1 || userInput > 7 || menuDecision.equalsIgnoreCase("Y"));
                    break;
                case 2: //------------------------------------------------ PC Maintenance --------------------------------------------------
                    do {
                        printPCMenu();
                        userInput = getOption("Option: ");

                        if (userInput < 1 || userInput > 5) {
                            System.out.println("\nInvalid Option, please select between 1-5\n");
                        }

                        if (userInput == 5) {
                            System.out.println("\nReturn to main menu !\n");
                        }

                        switch (userInput) {
                            case 1://================================================ Create New PC Profile ==================================================
                                do { //loop if user wants to enter more PC
                                    PC pc = new PC();
                                    Scanner input = new Scanner(System.in);

                                    //Input name 
                                    PCName = pc.inputString("\nPlease enter the PC name : ");
                                    pc.setPCName(PCName);
                                    PCValidation = PCModule.findName(PCName);
                                    if (PCName.length() > 40 || PCValidation >= 1) {
                                        do {

                                            PCName = pc.inputString("\nInvalid Input!Your PC Name cannot be more than 40 character and cannot be the same as other user.\nPlease reenter your name : ");
                                            pc.setPCName(PCName);
                                            PCValidation = 0;
                                            PCValidation = PCModule.findName(PCName);

                                        } while (PCName.length() > 40 || PCValidation >= 1);
                                    } else {
                                        pc.setPCName(PCName);
                                    }

                                    nameCount++;

                                    //save PCName to array
                                    PCModule.addNameToArray(nameCount, PCName);

                                    //input IPV4
                                    IPV4 = pc.inputString("\nPlease enter the IPV4 eg:(192.163.0.150) *The maximum of each number is 255 : ");
                                    pc.setIPV4(IPV4);

                                    if (!pc.setIPV4(IPV4)) {
                                        do {

                                            IPV4 = pc.inputString("\nInvalid Input!Your IPV$ does not meet our requirement. Please input again : ");
                                            pc.setIPV4(IPV4);

                                        } while (!pc.setIPV4(IPV4));
                                    } else {
                                        pc.setIPV4(IPV4);
                                    }

                                    //input Ram requirement
                                    PCRam = pc.inputInteger("\nPlease enter the Ram (0-256 GB) : ");

                                    if (PCRam <= 0 || PCRam >= 256) {
                                        do {

                                            PCRam = pc.inputInteger("\nYou Ram is not within 0-256 GB.Please reenter your Ram : ");

                                        } while (PCRam <= 0 || PCRam >= 256);

                                    } else {
                                        pc.setRam(PCRam);
                                    }

                                    //input ManufacturedDate
                                    //Date
                                    System.out.print("\nPlease enter the Manufactured Day: ");
                                    PCDate = pc.inputInteger("\nDay: ");

                                    if (PCDate <= 0 || PCDate > 31) {
                                        do {

                                            PCDate = pc.inputInteger("\nYou date is not valid.Please reenter your date :");

                                        } while (PCDate <= 0 || PCDate > 31);
                                    }

                                    //Month
                                    PCMonth = pc.inputInteger("\nMonth: ");

                                    if (PCMonth <= 0 || PCMonth > 12) {
                                        do {

                                            PCMonth = pc.inputInteger("\nYou month is not valid.Please reenter your month :");

                                        } while (PCMonth <= 0 || PCMonth > 12);
                                    }

                                    //Year
                                    PCYear = pc.inputInteger("\nYear: ");

                                    if (PCYear <= 0 || PCYear > 2021) {
                                        do {

                                            PCMonth = pc.inputInteger("\nYou month is not valid.Please reenter your month :");

                                        } while (PCYear <= 0 || PCYear > 2021);
                                    }

                                    pc.setManufacturedDate(PCDate, PCMonth, PCYear);

                                    //Input PC Type
                                    PCType = pc.inputString("\nPlease enter your type of PC (Laptop / Desktop) : ");
                                    pc.setPCType(PCType);

                                    if (!(PCType.equalsIgnoreCase("Desktop")) && !(PCType.equalsIgnoreCase("Laptop"))) {
                                        do {

                                            PCType = pc.inputString("\nInvalid Input!Please pick Laptop or Desktop only : ");

                                        } while (!(PCType.equalsIgnoreCase("Desktop")) && !(PCType.equalsIgnoreCase("Laptop")));

                                    } else {
                                        pc.setPCType(PCType);
                                    }
                                    //save to file
                                    PCModule.saveToFile(pc);

                                    // add the students object to the array list
                                    for (int i = 0; i <= counter; i++) {
                                        PCModule.addItemArrayList(pc);
                                        break;
                                    }

                                    decision = PC.inputString("\nWould you like to continue adding a new PC? (Y/N) : ");

                                } while (decision.equalsIgnoreCase("Y")); //end for user enter more PC profile
                                break;
                            case 2: //================================================ Modify PC Profile ==================================================
                                PCModule.saveToTempArray();

                                do {
                                    PC pc = new PC();
                                    int choice = 0;
                                    choice = pc.inputInteger("\nWhich PC profile would you like to edit (starting from 1): ");
                                    System.out.println("What would you like to replace it with:");

                                    //Input name 
                                    do {
                                        PCName = pc.inputString("\nPlease enter the PC name : ");
                                        pc.setPCName(PCName);
                                    } while (PCName.length() > 40);

                                    //mpdify the pcname in array
                                    PCModule.modifyArray(choice, PCName);

                                    //input IPV4
                                    do {
                                        IPV4 = pc.inputString("\nPlease enter the IPV4 eg:(192.163.0.150) *The maximum of each number is 255 : ");
                                        pc.setIPV4(IPV4);
                                    } while (IPV4.equals(false));

                                    //input Ram requirement
                                    do {
                                        PCRam = pc.inputInteger("\nPlease enter the Ram (0-256 GB) : ");
                                        pc.setRam(PCRam);
                                    } while (PCRam <= 0 || PCRam >= 256);

                                    //input ManufacturedDate
                                    //Date
                                    do {
                                        System.out.print("\nPlease enter the Manufactured Date: ");
                                        PCDate = pc.inputInteger("\nDate: ");
                                    } while (PCDate <= 0 || PCDate > 31);

                                    //Month
                                    do {
                                        PCMonth = pc.inputInteger("\nMonth: ");
                                    } while (PCMonth <= 0 || PCMonth > 12);

                                    //Year
                                    do {
                                        PCYear = pc.inputInteger("\nYear: ");
                                    } while (PCYear <= 0 || PCYear > 2021);

                                    pc.setManufacturedDate(PCDate, PCMonth, PCYear);

                                    //Input PC Type
                                    do {
                                        PCType = pc.inputString("\nPlease enter your type of PC (Laptop / Desktop) : ");
                                        pc.setPCType(PCType);
                                    } while (!(PCType.equalsIgnoreCase("Desktop")) && !(PCType.equalsIgnoreCase("Laptop")));

                                    PCModule.modifyAList(choice, pc);
                                    decision = pc.inputString("\nWould you like to modify another PC profile? (Y/N) : ");

                                    sModule.saveToFileOverWrite();

                                } while (decision.equalsIgnoreCase("Y")); //end for user enter more PC profile

                                break;
                            case 3: //================================================ Delete PC Profile ==================================================
                                PC delPC = new PC();
                                PCModule.saveToTempArray();
                                do {
                                    int choice = 0;
                                    choice = delPC.inputInteger("\nWhich PC profile would you like to remove (starting from 1): ");
                                    PCModule.deleteAList(choice);
                                    PCModule.deleteArray(choice, PCName);

                                    decision = delPC.inputString("\nWould you like to delete another PC profile? (Y/N) : ");
                                } while (decision.equalsIgnoreCase("Y")); //end for user enter more PC profile
                                break;

                            case 4: //================================================ Assign Software to PC ==================================================
                                //declare scanner
                                Scanner sc = new Scanner(System.in);

                                System.out.println("All Software: ");
                                System.out.println("----------------------------");

                                //list all the PCs
                                sModule.printArrayList();
                                System.out.println();

                                System.out.println("All PCs: ");
                                System.out.println("----------------------------");

                                //list all the lab
                                PCModule.printArrayList();

                                do {

                                    System.out.println();
                                    System.out.print("Which software you would like to select? Software Name: ");
                                    sName = sc.nextLine();

                                    //Search for the position of pcName in the PCArray Array List
                                    position = sModule.searchFromArrayList(sName);

                                    System.out.print("Which lab you would like to assign the software called " + sName + " to? Please type in the PC name! ");
                                    PCName = sc.nextLine();

                                    //save to file
                                    sModule.saveToPCFile(PCName, position);

                                    System.out.print("Would you like to continue assigning any software to the PC? (Y/N) : ");
                                    decision = sc.nextLine();

                                } while (decision.equalsIgnoreCase("Y"));

                                break;
                            case 5: //================================================ Exit PC Maintenance Menu ==================================================
                                break;
                        }//end of switch case
                        
                        PCModule.saveArraytoText();
                        PCModule.saveToTempArray();
                        

                        menuDecision = decision("Would you like to return to main menu or remain at PC Maintenance (Y- remain at PC Maintenance, N-return main menu) : ");
                        System.out.println();

                    } while (userInput < 1 || userInput > 5 || menuDecision.equalsIgnoreCase("Y"));
                    break;

                case 3: //------------------------------------------------ Software Maintenance --------------------------------------------------
                {
                    do {
                        //print software menu  
                        printSoftwareMenu();
                        userInput = getOption("Option: ");

                        //if invalid input
                        if (userInput < 1 || userInput > 5) {
                            System.out.println("\nInvalid Option, please select between 1-5\n");
                        }

                        if (userInput == 5) {
                            System.out.println("\nReturn to main menu !\n");
                        }

                        switch (userInput) {
                            case 1: //================================================ Add Software ==================================================
                                do {
                                    //loop if user wants to enter more software
                                    Software aSoftware = new Software();

                                    //input name 
                                    do {
                                        sName = aSoftware.inputString("\nPlease enter the software name : ");
                                        aSoftware.setSoftwareName(sName);
                                    } while (sName.length() > 30);

                                    sCount++;

                                    sModule.addNameToArray(sCount, sName);

                                    //input type
                                    do {
                                        sType = aSoftware.inputString("Please enter the software type (freeware/shareware/proprietary) : ");
                                        aSoftware.setSoftwareType(sType);
                                    } while (!(sType.equalsIgnoreCase("freeware")) && !(sType.equalsIgnoreCase("shareware")) && !(sType.equalsIgnoreCase("proprietary")));

                                    //input Ram requirement
                                    do {
                                        sRAM = aSoftware.inputInteger("Please enter the software minimum RAM requirement (has to be 1-64,GB format): ");
                                        aSoftware.setRam(sRAM);
                                    } while (sRAM <= 0 || sRAM >= 65);

                                    //input date if type is proprietary
                                    if (sType.equalsIgnoreCase("proprietary")) {
                                        do {
                                            sDate = aSoftware.inputString("Please enter the software expiration date : ");

                                            if (aSoftware.valDate(sDate)) {
                                                dateBool = true;
                                            } else {
                                                dateBool = false;
                                            }

                                            aSoftware.setDate(sDate, dateBool);

                                        } while (!(aSoftware.valDate(sDate)));
                                    } else {
                                        // if input type is not proprietary, user dont have to enter date
                                        System.out.print("Please enter the software expiration date : No Expiration\n");
                                        sDate = "Null";
                                        dateBool = true;
                                        aSoftware.setDate(sDate, dateBool);
                                    }

                                    //saves data to text file
                                    sModule.saveToFile(aSoftware);

                                    // add the students object to the array list
                                    for (int i = 0; i <= counter; i++) {
                                        sModule.addItemArrayList(aSoftware);
                                        break;
                                    }

                                    decision = aSoftware.inputString("\nWould you like to continue adding a new software? (Y/N) : ");

                                } while (decision.equalsIgnoreCase("Y")); //end for user enter more software
                                break;

                            case 2: //================================================ Modify Software ==================================================
                                //save the data to temp array to be modified before save to text file
                                sModule.saveToTempArray();

                                do {
                                    //loop if user wants to modify more    
                                    Software aSoftware = new Software();
                                    int choice = 0;
                                    choice = aSoftware.inputInteger("\nWhich software would you like to edit (starting from 1): ");
                                    System.out.println("Which would you like to replace it with:");

                                    //input name 
                                    do {
                                        sName = aSoftware.inputString("\nPlease enter the software name : ");
                                        aSoftware.setSoftwareName(sName);
                                    } while (sName.length() > 30);

                                    sModule.modifyArray(choice, sName);

                                    //input type
                                    do {
                                        sType = aSoftware.inputString("Please enter the software type (freeware/shareware/proprietary) : ");
                                        aSoftware.setSoftwareType(sType);
                                    } while (!(sType.equalsIgnoreCase("freeware")) && !(sType.equalsIgnoreCase("shareware")) && !(sType.equalsIgnoreCase("proprietary")));

                                    //input Ram requirement
                                    do {
                                        sRAM = aSoftware.inputInteger("Please enter the software minimum RAM requirement (has to be 1-64,GB format): ");
                                        aSoftware.setRam(sRAM);
                                    } while (sRAM <= 0 || sRAM >= 65);

                                    //input date if type is proprietary
                                    if (sType.equalsIgnoreCase("proprietary")) {
                                        do {
                                            sDate = aSoftware.inputString("Please enter the software expiration date : ");

                                            if (aSoftware.valDate(sDate)) {
                                                dateBool = true;
                                            } else {
                                                dateBool = false;
                                            }

                                            aSoftware.setDate(sDate, dateBool);

                                        } while (!(aSoftware.valDate(sDate)));
                                    } else {
                                        // if input type is not proprietary, user dont have to enter date
                                        System.out.print("Please enter the software expiration date : No Expiration\n");
                                        sDate = "Null";
                                        dateBool = true;
                                        aSoftware.setDate(sDate, dateBool);
                                    }

                                    //call method to modify arraylist
                                    sModule.modifyAList(choice, aSoftware);
                                    decision = aSoftware.inputString("\nWould you like to modify another item? (Y/N) : ");
                                    sModule.saveToFileOverWrite();
                                } while (decision.equalsIgnoreCase("Y")); //end for user enter more software
                                break;

                            case 3: //================================================ Delete Software ==================================================
                                Software bSoftware = new Software();
                                sModule.saveToTempArray();
                                do {
                                    // loop if user wants to delete more item
                                    int choice = 0;
                                    choice = bSoftware.inputInteger("\nWhich software would you like to remove (starting from 1): ");

                                    sModule.deleteArray(choice, sName);
                                    // call method to delete elements from the arraylist 
                                    sModule.deleteAList(choice);
                                    decision = bSoftware.inputString("\nWould you like to delete another item? : ");
                                } while (decision.equalsIgnoreCase("Y")); //end for user enter more software
                                break;

                            case 4: //================================================ Display Software  ==================================================
                                //call method to print arraylist
                                sModule.printArrayList();
                                System.out.println();
                                break;

                            case 5: //================================================ Exit Software Menu ==================================================
                                break; //exit to main menu
                        } //end of switch case

                        sModule.saveArraytoText();

                        menuDecision = decision("Would you like to return to main menu or remain at Software Module (Y- remain at software module, N-return main menu) : ");
                        System.out.println();

                    } while (userInput < 1 || userInput > 5 || menuDecision.equalsIgnoreCase("Y"));
                    break;
                }

                case 4: //------------------------------------------------ Search --------------------------------------------------
                    do {
                        printSearchMenu();
                        userInput = getOption("Option: ");

                        if (userInput < 1 || userInput > 3) {
                            System.out.println("\nInvalid Option, please select between 1-3\n");
                        }

                        if (userInput == 5) {
                            System.out.println("\nReturn to main menu !\n");
                        }

                        switch (userInput) {
                            case 1: //================================================ Search for Lab ==================================================
                                lModule.saveToTempArray();
                                do {
                                    int choice = 0;
                                    choice = Lab.inputInteger("\nWhich Lab would you like to display (starting from 1): ");

                                    lModule.searchLab(choice);

                                    decision = Lab.inputString("\nWould you like to search for another item?(Y/N) : ");
                                } while (decision.equalsIgnoreCase("Y")); //end for user to search
                                break;
                            case 2: //================================================ Search for PC ==================================================
                                PCModule.saveToTempArray();
                                do {
                                    int choice = PC.inputInteger("\nWhich PC would you like to display (starting from 1): ");

                                    PCModule.searchPC(choice);

                                    decision = PC.inputString("\nWould you like to search for another item?(Y/N) : ");
                                } while (decision.equalsIgnoreCase("Y")); //end for user to search
                                break;
                            case 3: //================================================ Exit Search Menu ==================================================
                                break;
                        }

                        menuDecision = decision("Would you like to return to main menu or remain at Search Module (Y- remain at Search Module, N-return main menu) : ");
                        System.out.println();

                    } while (userInput < 1 || userInput > 3 || menuDecision.equalsIgnoreCase("Y"));
                    break;

                case 5: //------------------------------------------------ Export to HTML file --------------------------------------------------

                    exportFile();

                    lModule.exportToFile();
                    PCModule.exportToFile();
                    sModule.exportToFile();

                    break;

                case 6: //------------------------------------------------ Exit --------------------------------------------------

                    break; //exit system
            }
            menuDecision = decision("Would you like to return to main menu (Y/N) :");
            System.out.println();
        } while (userInput < 1 || userInput > 6 || menuDecision.equalsIgnoreCase("Y"));
    }//end main

    public static void printMenu() {
        System.out.println("INTI ITS SOFTWARE TRACKING SYSTEM");
        System.out.println();
        System.out.println("1-> Lab Maintenance");
        System.out.println("2-> PC Maintenace");
        System.out.println("3-> Sofware Maintenance");
        System.out.println("4-> Search ");
        System.out.println("5-> Export to HTML file");
        System.out.println("6-> Exit");
        System.out.println();
    }

    public static void printLabMenu() {
        System.out.println("------------------------\n");
        System.out.println("1-> Create Lab Profile");
        System.out.println("2-> Modify Lab Profile");
        System.out.println("3-> Assign PC to Lab");
        System.out.println("4-> Remove PC from Lab");
        System.out.println("5-> Delete Lab Profile");
        System.out.println("6-> Display Lab details");
        System.out.println("7-> Exit Lab Menu");
        System.out.println("\n------------------------\n");
    }

    public static void printPCMenu() {
        System.out.println("------------------------\n");
        System.out.println("1-> Create New PC Profile");
        System.out.println("2-> Modify PC Profile");
        System.out.println("3-> Delete PC Profile");
        System.out.println("4-> Assign Software to PC");
        System.out.println("5-> Exit PC Maintenance Menu");
        System.out.println("\n------------------------\n");
    }

    public static void printSoftwareMenu() {
        System.out.println("------------------------\n");
        System.out.println("1-> Add Software");
        System.out.println("2-> Modify Software");
        System.out.println("3-> Delete Software");
        System.out.println("4-> Display Software ");
        System.out.println("5-> Exit Software Menu");
        System.out.println("\n------------------------\n");
    }

    public static void printSearchMenu() {
        System.out.println("------------------------\n");
        System.out.println("1-> Search for Lab");
        System.out.println("2-> Search for PC");
        System.out.println("3-> Exit Search Menu");
        System.out.println("\n------------------------\n");
    }

    public static int getOption(String prompText) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompText);
        return input.nextInt();
    }

    public static String decision(String prompText) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompText);
        return input.nextLine();
    }

    public static void exportFile() throws IOException {

        //declare FileWriter and PrintWriter object        
        //save the file to ITS.html
        FileWriter fw = new FileWriter("ITS.html");
        PrintWriter pw = new PrintWriter(fw);

        //create html header
        pw.println("<!DOCTYPE html>");
        pw.println("<html>");
        pw.println("<head><title>ITS Software Tracking System</title></head>");
        pw.println("<body bgcolor = \"99FFFF\">");

        pw.println("<img src = \"pic.png\" align = \"center\">");
        pw.println("<h1>ITS Tracking System</h1>");

        //create listing
        pw.println("<ul type = \"disc\">");

        //print listing
        pw.println("<li><a href = \"lab.html\">Lab Details</a></li>");
        pw.println("<li><a href = \"pc.html\">PC Details</a></li>");
        pw.println("<li><a href = \"software.html\">Software Details</a></li>");

        pw.println("</body>");
        pw.println("</html>");

        //close file stream
        System.out.println("The Software Tracking System has been saved into ITS.html file");
        pw.close();
    }
}
