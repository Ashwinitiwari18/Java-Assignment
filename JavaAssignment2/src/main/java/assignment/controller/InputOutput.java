package assignment.controller;

import assignment.model.Student;
import assignment.model.User;

import java.util.Scanner;
import java.util.Set;

public class InputOutput {
  Scanner sc;

  public InputOutput() {
    sc = new Scanner(System.in);
  }

  /**
   * Adds a new student to the user's collection.
   * <p>
   * This method prompts the user to enter the details of a new student including
   * the full name, age, address, roll number, and a set of courses. It ensures that
   * the entered details are valid by using the respective input methods in the
   * {@link InputOutputAddUser} class. If the entered details are valid, a new
   * {@link Student} object is created and added to the user's collection.
   * </p>
   *
   * @param user The {@link User} object to which the new student will be added.
   */
  public void addStudent(User user) {
    try {
      InputOutputAddUser addUser = new InputOutputAddUser();
      Student student = new Student().setFullName(addUser.takeFullName())
          .setAge(addUser.takeAge())
          .setAddress(addUser.takeAddress())
          .setRollNumber(addUser.takeRollNumber())
          .setCourses(addUser.takeCourses());
      user.addUserDetails(student);
    } catch (Exception e) {
      System.out.println("Please enter details in correct format");
    }
  }

  /**
   * Displays user data sorted by a specified attribute.
   * <p>
   * This method prompts the user to select an attribute by which the student data
   * should be sorted. It retrieves and displays the student data in a formatted table.
   * The available sorting attributes are "name", "roll number", "age", and "address".
   * The method prints a table header, followed by the details of each student sorted
   * according to the chosen attribute. If the attribute is invalid, the behavior depends
   * on the `displayUserDetails` method's implementation.
   * </p>
   *
   * @param user The User object containing the student data to be displayed.
   */
  public void displayUserData(User user) {
    System.out.println("How you want to sort your data\n"
        + "select from name, roll number, age, address");
    String type = "";
    if (sc.hasNextLine()){
      type = sc.nextLine();
    } else {
      type = "name";
    }
    System.out.println("---------------------------------------------------------------------");
    System.out.println("---------------------------------------------");
    System.out.printf("%-20s %-5s %-12s %-20s %-30s%n", "Name", "Age",
        "Roll Number", "Address", "Courses");
    Set<Student> sortedStudent = user.displayUserDetails(type);
    for (Student student : sortedStudent) {
      System.out.printf("%-20s %-5d %-12d %-20s [ ", student.getFullName(),
          student.getAge(), student.getRollNumber(), student.getAddress());
      for (String course : student.getCourses()) {
        System.out.printf("%s, ", course);
      }
      System.out.println("]");
    }
    System.out.println("---------------------------------------------------------------------");
    System.out.println("---------------------------------------------");
  }

  /**
   * Deletes a student's data based on their roll number.
   * <p>
   * This method prompts the user to enter the roll number of the student whose data
   * needs to be deleted. It then calls the `deleteUserDetails` method on the User object
   * to remove the student with the specified roll number.
   * </p>
   *
   * @param user The User object from which the student data will be deleted.
   */
  public void deleteUserData(User user) {
    int rollNumber = 0;
    try {
      System.out.println("Enter roll number : ");
      if (sc.hasNextLine()) {
        rollNumber = Integer.parseInt(sc.nextLine());
      }else {
        rollNumber = 1;
      }
      user.deleteUserDetails(rollNumber);
    } catch (Exception e) {
      System.out.println(rollNumber);
      System.out.println("Enter valid roll number");
      System.out.println(e);
    }
  }

  /**
   * Exits the application with an option to save user data to disk.
   * <p>
   * This method prompts the user to decide whether to save the current user data to disk
   * before exiting the application. If the user inputs 'y', the data is saved by calling
   * the `saveUserDetails` method on the User object.
   * </p>
   *
   * @param user The User object whose data might be saved before exiting.
   */
  public void exit(User user) {
    System.out.println("Do you want to save data to disk (y/n)");
    String input = sc.nextLine();
    if (input.equals("y")) {
      user.saveUserDetails();
    }
  }
}
