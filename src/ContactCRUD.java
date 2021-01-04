import fileIo.FileReader;
import util.Input;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactCRUD{


    Input input = new Input();
    FileReader contactReader = new FileReader("data", "contacts.txt", "contacts.log", "delete.txt");

    public ContactCRUD() throws IOException {

    }

    public void displayMenuOptions() throws IOException {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.println("Enter an option: [1, 2, 3, 4, 5]");
        int userChoice = input.getInt();
        if(userChoice == 1){
            displayAllContacts();
            displayMenuOptions();
        }
        if(userChoice == 2){
            addContact();
            input.getString();
            displayMenuOptions();
        }
        if(userChoice == 3){
            findContact();
            boolean yesNo = input.yesNo("Do you want to continue?");
            input.getString();
            if(yesNo){
                displayMenuOptions();
            }
        }
        if(userChoice == 4){
            deleteContact();
            displayMenuOptions();
        }
        if(userChoice == 5){
            System.out.println("Thank you!");
        }
    }

    public void deleteContact() throws IOException {
        FileReader contactReader = new FileReader("data", "contacts.txt", "contacts.log", "delete.txt");
        boolean nameExists = false;
        System.out.println("Please enter the contact's first and last name:");
        String userInput = input.getString().trim();
        Path contactsPath = Paths.get(contactReader.getDirectoryName(), contactReader.getFileName());
        List<String> contacts = contactReader.getFileLines();
        List<String> newList = new ArrayList<>();
        String[] userSeparated = userInput.split(" ");
        for(String contact:contacts){
            String[] separated = contact.split(" ");
            newList.add(contact);
            if (userSeparated[0].equals(separated[0]) && userSeparated[1].equals(separated[1])) {
                nameExists = true;
                newList.remove(contact);
                continue;
            }
        }
        if(!nameExists){
            System.out.println("There is no contact by that name, please try again.");
            deleteContact();
        }
        Files.write(contactsPath, newList);
        System.out.println("Contact has been deleted");
    }

    public void displayAllContacts() throws IOException {
        FileReader contactReader = new FileReader("data", "contacts.txt", "contacts.log", "delete.txt");
        String name = "Name";
        String number = "Number";
        System.out.println("-----------------------------------");
        System.out.printf("     %-13s|     %s%n", name, number);
        System.out.println("-----------------------------------");
        List<String> contacts = contactReader.getFileLines();
        for(String contact:contacts){
            String[] separated = contact.split(" ");
            if(separated.length > 1){
                String newName = separated[0] + " " + separated[1];
                System.out.printf("%-18s|  %-20s%n", newName, separated[2]);
            }
        }
        System.out.println();
    }

    public boolean findContact() throws IOException {
        FileReader contactReader = new FileReader("data", "contacts.txt", "contacts.log", "delete.txt");
        boolean nameExists = false;
        System.out.println("Please enter the contact's first and last name:");
        String userInput = input.getString().trim();
        List<String> contacts = contactReader.getFileLines();
        String[] userSeparated = userInput.split(" ");
        for(String contact:contacts){
            String[] separated = contact.split(" ");
            if (userSeparated[0].equals(separated[0]) && userSeparated[1].equals(separated[1])) {
                nameExists = true;
                System.out.println();
                System.out.printf("Name: %s %s%nNumber: %s%n", separated[0], separated[1], separated[2]);
                System.out.println();
                return nameExists;
            }

        }
        if(!nameExists){
            System.out.println("There is no contact by that name, please try again.");
            findContact();
        }
        return nameExists;
    }

    public void addContact() throws IOException {
        boolean userResponse = input.yesNo("Do you want to add a contact?");
        if(userResponse){
            String userFirst = input.getString("Enter first name").trim();
            String userLast = input.getString("Enter last name").trim();
            String userPhone = input.getString("Enter phone number").trim();
            System.out.printf("First name: %s%nLast name: %s%nPhone number: %s%n", userFirst, userLast, userPhone);
            boolean anotherResponse = input.yesNo("Is this information correct?");
            if(anotherResponse){
                Contact c = new Contact(userFirst, userLast, userPhone);
            }
        }

    }

}
