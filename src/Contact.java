import fileIo.FileReader;
import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Contact {

    //Variables
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;

    Input input = new Input();
    FileReader contactReader = new FileReader("data", "contacts.txt", "contacts.log");

    //Constructor
    public Contact(String firstName, String lastName, String phoneNumber) throws IOException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        Files.write(contactReader.getFilePath(), Arrays.asList(this.firstName, this.lastName, this.phoneNumber), StandardOpenOption.APPEND);
    }

    public void displayInfo(){
        System.out.printf("First name: %s%nLast name: %s%nPhone number: %s%n", this.firstName, this.lastName, this.phoneNumber);
    }

    public void addContact(){
        boolean userResponse = input.yesNo("Do you want to add a contact?");
        if(userResponse){
            String userFirst = input.getString("Enter first name");
            String userLast = input.getString("Enter last name");
            String userPhone = input.getString("Enter phone number");
            System.out.printf("First name: %s%nLast name: %s%nPhone number: %s%n", userFirst, userLast, userPhone);
            boolean anotherResponse = input.yesNo("Is this information correct");
            if(anotherResponse){
                Contact c = new Contact(userFirst, userLast, phoneNumber);
            }
        }
    }

    //PSVM
    public static void main(String[] args) throws IOException {
        Contact c1 = new Contact("Will", "Tisdale", "555-867-5309");


    }

    //Custom Methods


    //Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
