package assignment;

import assignment.controller.InputOutput;
import assignment.model.User;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    User users = new User();
    System.out.println("Welcome to our service");
    String isNextInput = "1";
    Scanner sc = new Scanner(System.in);
    InputOutput inOut = new InputOutput();
    String filename = "studentData.ser";
    try (FileInputStream file = new FileInputStream(filename);
         ObjectInputStream in = new ObjectInputStream(file)) {
      users = (User) in.readObject();
    } catch (Exception e) {
      System.out.println(e + " No previously saved file");
    }

    while (!isNextInput.equals("5")) {
      System.out.println("What type of operation do you want to perform?\nPlease select from the list:");
      System.out.println("1. Add User details.");
      System.out.println("2. Display User details.");
      System.out.println("3. Delete User details.");
      System.out.println("4. Save User details.");
      System.out.println("5. Exit");
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
