import util.Input;

import java.io.IOException;

public class ContactCRUD {

    Input input = new Input();


    public void displayMenuOptions() throws IOException {
        System.out.println("1. View contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit.");
        System.out.println("Enter an option: [1, 2, 3, 4, 5]");
        int userChoice = input.getInt();
        if(userChoice == 2){
            addContact();
        }
    }

    public void addContact() throws IOException {
        boolean userResponse = input.yesNo("Do you want to add a contact?");
        if(userResponse){
            String userFirst = input.getString("Enter first name");
            String userLast = input.getString("Enter last name");
            String userPhone = input.getString("Enter phone number");
            System.out.printf("First name: %s%nLast name: %s%nPhone number: %s%n", userFirst, userLast, userPhone);
            boolean anotherResponse = input.yesNo("Is this information correct");
            if(anotherResponse){
                Contact c = new Contact(userFirst, userLast, userPhone);
            }
        }
    }
}
