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
import java.util.Arrays;
import java.util.List;

public class ContactCRUD{


    Input input = new Input();
    FileReader contactReader = new FileReader("data", "contacts.txt", "contacts.log", "delete.txt");
//    PrintWriter writer = new PrintWriter((OutputStream) contactReader.getFilePath());
//    PrintWriter deleteWriter = new PrintWriter((OutputStream) contactReader.getDeleteFilePath());

    public ContactCRUD() throws IOException {
    }


    public void displayMenuOptions() throws IOException {
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
            displayMenuOptions();
        }
        if(userChoice == 4){
            deleteContact();
        }
        if(userChoice == 5){
            System.out.println("Thank you!");
        }
    }

    public void deleteContact() throws IOException {
        boolean nameExists = false;
        System.out.println("Please enter the contact's first and last name:");
        String userInput = input.getString().trim();
        List<String> contacts = contactReader.getFileLines();
        String[] userSeparated = userInput.split(" ");
        for(String contact:contacts){
            String[] separated = contact.split(" ");
            if (userSeparated[0].equals(separated[0]) && userSeparated[1].equals(separated[1])) {
                nameExists = true;
                contacts.remove(contact);
            }
//            else {
//                Files.write(contactReader.getDeleteFilePath(), Arrays.asList(contact), StandardOpenOption.APPEND);
//            }
        }
        if(!nameExists){
            System.out.println("There is no contact by that name, please try again.");
            deleteContact();
        }
//        Files.delete(contactReader.getFilePath());
//        FileReader contactReader = new FileReader("data", "contacts.txt", "contacts.log", "delete.txt");
//        List <String> newContacts = contactReader.getDeleteFileLines();
//        for(String contact:newContacts){
//                    Files.write(contactReader.getFilePath(), Arrays.asList(contact), StandardOpenOption.APPEND);
//                }

    }

    public void displayAllContacts() throws IOException {
        FileReader contactReader = new FileReader("data", "contacts.txt", "contacts.log", "delete.txt");
        System.out.println("Name      |    Number");
        System.out.println("---------------------");
        List<String> contacts = contactReader.getFileLines();
        for(String contact:contacts){
            String[] separated = contact.split(" ");
            System.out.printf("%s %-5s| %-10s%n", separated[0], separated[1], separated[2]);
        }

    }

    public void findContact() throws IOException {
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
                System.out.printf("%s %-5s| %-10s%n", separated[0], separated[1], separated[2]);
                break;
            }

        }
        if(!nameExists){
            System.out.println("There is no contact by that name, please try again.");
            findContact();
        }
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
