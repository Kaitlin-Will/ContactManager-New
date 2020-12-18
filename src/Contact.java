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
    protected String infoString;

    Input input = new Input();
    FileReader contactReader = new FileReader("data", "contacts.txt", "contacts.log");

    //Constructor
    public Contact(String firstName, String lastName, String phoneNumber) throws IOException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.infoString = firstName + " " +lastName + " " +phoneNumber;

//        if (Files.notExists()) {

            Files.write(contactReader.getFilePath(), Arrays.asList(this.infoString), StandardOpenOption.APPEND);

//        }


    }


    public void displayInfo(){
        System.out.printf("First name: %s%nLast name: %s%nPhone number: %s%n", this.firstName, this.lastName, this.phoneNumber);
    }





    //PSVM
    public static void main(String[] args) throws IOException {
//        Contact c1 = new Contact("Will", "Tisdale", "555-867-5309");


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
