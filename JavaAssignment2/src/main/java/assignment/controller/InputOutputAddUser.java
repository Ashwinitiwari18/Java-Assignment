package assignment.controller;

import static assignment.config.Constant.MAXIMUM_ALLOWED_AGE;
import static assignment.config.Constant.MAXIMUM_ALLOWED_SIZE;
import static assignment.config.Constant.MINIMUM_ALLOWED_AGE;
import static assignment.config.Constant.MINIMUM_NUMBER_OF_COURSES_TO_ENROLL;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class InputOutputAddUser {
  Scanner sc;

  InputOutputAddUser() {
    sc = new Scanner(System.in);
  }

  /**
   * Prompts the user to enter the full name of a student and validates its length.
   * <p>
   * This method prompts the user to input the full name of a student and ensures that the name
   * is no longer than 20 characters. If the entered name exceeds this length, an error message
   * is displayed and the user is prompted to re-enter the name until a valid input is provided.
   * </p>
   *
   * @return The validated full name of the student as a String.
   */
  public String takeFullName() {
    System.out.print("Enter full name of Student (1 - 20 length): ");
    String fullName = "John Doe\n";
    if (sc.hasNextLine()) {
      fullName = sc.nextLine();
    } else {
      sc.next(); // -->important
    }
    if (fullName.length() > MAXIMUM_ALLOWED_SIZE) {
      System.err.println("Name length must be less than equal to 20.\n Re-Enter input : ");
      fullName = takeFullName();
    }
    return fullName;
  }

  /**
   * Prompts the user to enter the age of a student and validates its range.
   * <p>
   * This method prompts the user to input the age of a student and ensures that the age
   * is between 4 and 99, inclusive. If the entered age is outside this range, an error message
   * is displayed and the user is prompted to re-enter the age until a valid input is provided.
   * </p>
   *
   * @return The validated age of the student as an integer.
   */
  public int takeAge() {
    int age = 0;
    try {
      System.out.print("Enter age of Student (4 - 99) : ");
      age = Integer.parseInt(sc.nextLine());

      if (age < MINIMUM_ALLOWED_AGE || age > MAXIMUM_ALLOWED_AGE) {
        throw new Exception("Age should be between 4-99");
      }
    } catch (Exception e) {
      System.err.println(e + ".\n Re-Enter input : ");
      age = takeAge();
    }
    return age;
  }

  /**
   * Prompts the user to enter the address of a student and validates its length.
   * <p>
   * This method prompts the user to input the address of a student and ensures that the address
   * is no longer than 20 characters. If the entered address exceeds this length, an error message
   * is displayed and the user is prompted to re-enter the address until a valid input is provided.
   * </p>
   *
   * @return The validated address of the student as a string.
   */
  public String takeAddress() {
    System.out.print("Enter address of Student (1 - 20 length) : ");
    String address = sc.nextLine();
    if (address.length() > MAXIMUM_ALLOWED_SIZE) {
      System.err.println("Address length must be less than equal to 20.\n Re-Enter input : ");
      address = takeAddress();
    }
    return address;
  }

  /**
   * Prompts the user to enter the roll number of a student and validates the input.
   * <p>
   * This method prompts the user to input the roll number of a student and ensures that
   * the input is a valid integer. If an invalid input is detected, an error message is
   * displayed and the user is prompted to re-enter the roll number until a valid input
   * is provided.
   * </p>
   *
   * @return The validated roll number of the student as an integer.
   */
  public int takeRollNumber() {
    int rollNumber = 0;
    try {
      System.out.print("Enter roll number of Student : ");
      rollNumber =  Integer.parseInt(sc.nextLine());
    } catch (Exception e) {
      System.err.println(e + ".\n Re-Enter input : ");
      rollNumber = takeRollNumber();
    }
    return rollNumber;
  }

  /**
   * Prompts the user to enter a set of courses from A-F and validates the input.
   * <p>
   * This method prompts the user to input a set of courses (minimum four) from A to F.
   * The input is split into a set, converted to uppercase, and validated to ensure all
   * courses are within the valid range (A-F). If any invalid course is found or fewer
   * than four courses are entered, an error message is displayed and the user is prompted
   * to re-enter the courses.
   * </p>
   *
   * @return A set of validated courses.
   */
  public Set<String> takeCourses() {
    System.out.print("Enter set of course from A-F, four different course is mandatory (A - F): ");
    String courses = sc.nextLine();
    Set<String> courseSet = new HashSet<>(Arrays.asList(courses.split(" ")));
    for (String s : courseSet) {
      String course = s.toUpperCase();
      if (!Arrays.asList("A", "B", "C", "D", "E", "F").contains(course)) {
        System.err.print("Please enter choose correct courses from A-F.\n Re-Enter input : ");
        courseSet = takeCourses();
        break;
      }
    }
    if (courseSet.size() < MINIMUM_NUMBER_OF_COURSES_TO_ENROLL) {
      System.err.print("Please enter at least 4 courses.\n Re-Enter input : ");
      courseSet = takeCourses();
    }
    return courseSet;
  }
}
