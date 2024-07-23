package assignment;

import assignment.controller.InputOutput;
import assignment.model.User;

import java.util.Scanner;

public class Main {

  /**
   * The entry point of the application.
   * <p>
   * This method initializes the application by loading previously saved user data from a file,
   * displays a welcome message, and then enters a loop to interact with the user. It presents a
   * menu with options to add, display, delete, save user details, or exit the application. Based on
   * the user's input, it performs the corresponding
   * operation by calling methods from the {@link InputOutput}
   * class and manages the user data accordingly.
   * </p>
   *
   * @param args Command-line arguments (not used in this application).
   */
  public static void main(String[] args) {
    User users = new User().getPreviousData();
    System.out.println("Welcome to our service");
    String isNextInput = "1";
    Scanner sc = new Scanner(System.in);
    InputOutput inOut = new InputOutput();
    while (!isNextInput.equals("5")) {
      System.out.println("What type of operation do you want to perform?\n"
          + "Please select from the list:");
      System.out.println("1. Add User details.");
      System.out.println("2. Display User details.");
      System.out.println("3. Delete User details.");
      System.out.println("4. Save User details.");
      System.out.println("5. Exit");
      System.out.print("Enter your input : ");
      isNextInput = sc.nextLine();
      switch (isNextInput) {
        case "1":
          inOut.addStudent(users);
          break;
        case "2":
          try {
            inOut.displayUserData(users);
          } catch (Exception e) {
            System.out.println(e);
          }
          break;
        case "3":
          inOut.deleteUserData(users);
          break;
        case "4":
          users.saveUserDetails();
          break;
        case "5":
          inOut.exit(users);
          break;
        default:
          System.out.println("Please choose the correct option");
      }
    }
  }
}
