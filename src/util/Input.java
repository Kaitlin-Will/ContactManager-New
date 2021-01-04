package util;

import java.util.Scanner;

public class Input {

    private Scanner scanner;
    private static int circlesMade = 0;

    public Input(){
         scanner = new Scanner(System.in);
    }

    public static void setCirclesMade(Input input, int number){
        input.circlesMade += number;
    }

    public int getCirclesMade(){
        return this.circlesMade;
    }

    public String getString(){
        return scanner.nextLine();
    }

    public String getString(String prompt){
        System.out.println(prompt);
        return scanner.next();
    }

    public boolean yesNo(){
        String yesNo = scanner.next();
        if(yesNo.equalsIgnoreCase("yes")){
            return true;
        }
        if(yesNo.equalsIgnoreCase("y")){
            return true;
        }
        return false;
    }

    public boolean yesNo(String prompt){
        System.out.println(prompt);
        return yesNo();
    }

    public int getInt(int min, int max){
        int userInt = getInt();
        if (userInt >= min && userInt <= max) {
            System.out.println("Your input is between the min and max.");
            return userInt;
        } else {
            System.out.println("Your input is NOT between the min and max.");
            return getInt(min, max);
        }
    }

    public int getInt(int min, int max, String prompt){
        System.out.println(prompt);
        int userInt = getInt();
        if (userInt >= min && userInt <= max) {
            System.out.println("Your input is between the min and max.");
            return userInt;
        } else {
            System.out.println("Your input is NOT between the min and max.");
            return getInt(min, max);
        }
    }

    public int getInt(){
        try{
            return Integer.valueOf(getString());
        } catch(NumberFormatException e){
            return getInt("Your input is invalid. Try again.");
        } catch(Exception e){
            e.printStackTrace(System.out);
            return getInt("Your input is invalid. Try again.");
        }
    }

    public int getInt(String prompt){
        System.out.println(prompt);
        return getInt();
    }

    public double getDouble(double min, double max) throws Exception {
        double userDouble = getDouble();
        if (userDouble >= min && userDouble <= max) {
            System.out.println("Your input is between the min and max.");
            return userDouble;
        } else {
            System.out.println("Your input is NOT between the min and max.");
            return getDouble(min, max);
        }
    }

    public double getDouble(double min, double max, String prompt) throws Exception {
        System.out.println(prompt);
        double userDouble = getDouble();
        if (userDouble >= min && userDouble <= max) {
            System.out.println("Your input is between the min and max.");
            return userDouble;
        } else {
            System.out.println("Your input is NOT between the min and max.");
            return getDouble(min, max);
        }
    }

    public double getDouble() {
        try{
            return Double.valueOf(getString());
        } catch (NumberFormatException e){
            return getDouble("Your entry is not valid. Try again.");
        } catch(Exception e){
            e.printStackTrace(System.out);
            return getDouble("Your input is invalid. Try again.");
        }
    }

    public double getDouble(String prompt) {
        System.out.println(prompt);
        return getDouble();
    }

    public void getBinary(){
        String binary = getString("Enter a binary number");
        int decimal = Integer.parseInt(binary,2);
        System.out.println("Your number is " + decimal);
    }

    public void getHex(){
        String hex = getString("Enter a hexadecimal number");
        int decimal = Integer.parseInt(hex,16);
        System.out.println("Your number is " + decimal);
    }


}
