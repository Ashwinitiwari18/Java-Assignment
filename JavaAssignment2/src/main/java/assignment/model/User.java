package assignment.model;

import assignment.comparator.StudentComparator;
import assignment.comparator.StudentComparatorByAddress;
import assignment.comparator.StudentComparatorByAge;
import assignment.comparator.StudentComparatorByRollNumber;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class User implements Serializable {

  private static final long serialVersionUID = 2L;
  private transient StudentComparator studentComparator;
  private Set<Student> users;
  private Map<Integer, Student> mapUserRollNumberToStudentObject;

  /**
   * Constructs a new User object with initialized collections.
   * <p>
   * This constructor initializes a new User instance with the following:
   * </p>
   * <ul>
   *   <li><b>studentComparator</b>: An instance of StudentComparator</li>
   *   <li><b>users</b>: A TreeSet of Student objects, sorted using the StudentComparator</li>
   *   <li><b>mapUserRollNumberToStudentObject</b>:
   *   A HashMap mapping student roll numbers to Student objects</li>
   * </ul>
   */
  public User() {
    studentComparator = new StudentComparator();
    users = new TreeSet<>(studentComparator);
    mapUserRollNumberToStudentObject = new HashMap<>();
  }

  private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
    ois.defaultReadObject();
    studentComparator = new StudentComparator();
  }

  /**
   * Loads the previously saved user details from the file "studentData.ser".
   * <p>
   * This method deserializes the User object from the specified file and returns it.
   * If the file does not exist or an error occurs during deserialization,
   * it prints an error message
   * and returns null.
   * </p>
   *
   * @return the deserialized User object, or null if an error occurs or the file does not exist.
   */
  public User getPreviousData() {
    String filename = "studentData.ser";
    User user = null;
    try (FileInputStream file = new FileInputStream(filename);
         ObjectInputStream in = new ObjectInputStream(file)) {
      user = (User) in.readObject();
    } catch (Exception e) {
      System.out.println(e + " No previously saved file");
    }
    return user;
  }

  /**
   * Adds a new student's details to the user collection.
   * <p>
   * This method checks if a student with the same roll number already exists.
   * If not, it adds the student to both the {@code mapUserRollNumberToStudentObject} map
   * and the {@code users} set.
   * </p>
   *
   * @param student The {@link Student} object containing the student's details to be added.
   */
  public void addUserDetails(Student student) {
    if (mapUserRollNumberToStudentObject.containsKey(student.getRollNumber())) {
      System.out.println("Student with same roll number already exist");
    } else {
      mapUserRollNumberToStudentObject.put(student.getRollNumber(), student);
      users.add(student);
    }
  }

  /**
   * Displays the user details sorted by the specified type.
   * <p>
   * This method sorts the student details based on the given type,
   * which can be one of the following:
   * "name", "roll number", "age", or "address". It uses the appropriate comparator for sorting.
   * </p>
   *
   * @param type The type by which to sort the student details.
   *             Valid values are "name", "roll number", "age", and "address".
   * @return A sorted set of {@link Student} objects based on the specified type.
   */
  public List<Student> displayUserDetails(String type,String inAscendingOrDescending) {
    Set<Student> students = new TreeSet<>();
    boolean flag = true;
    switch (type) {
      case "name":
        students = new TreeSet<>(studentComparator);
        break;
      case "roll number":
        students = new TreeSet<>(new StudentComparatorByRollNumber());
        break;
      case "age":
        students = new TreeSet<>(new StudentComparatorByAge());
        break;
      case "address":
        students = new TreeSet<>(new StudentComparatorByAddress());
        break;
      default:
        flag = false;
        System.out.println("Please provide valid input");
    }
    if (flag && users != null) {
      students.addAll(users);
    }
    List<Student> studentList = new ArrayList<>(students);
    if (inAscendingOrDescending.equals("2")) {
      Collections.reverse(studentList);
    }
    return studentList;
  }

  /**
   * Deletes a user's details from the user collection based on the roll number.
   * <p>
   * This method removes the student from both the {@code mapUserRollNumberToStudentObject} map
   * and the {@code users} set if the student with the specified roll number exists.
   * If the student does not exist, it prints a message indicating so.
   * </p>
   *
   * @param rollNumber The roll number of the student to be deleted.
   */
  public void deleteUserDetails(int rollNumber) {
    if (mapUserRollNumberToStudentObject.containsKey(rollNumber)) {
      Student student = mapUserRollNumberToStudentObject.get(rollNumber);
      users.remove(student);
      mapUserRollNumberToStudentObject.remove(rollNumber);
    } else {
      System.out.println("User doesn't exist");
    }
  }

  /**
   * Saves the user details to a file named "studentData.ser".
   * <p>
   * This method serializes the current {@link User} object and writes it to a file.
   * If an exception occurs during the process, it prints an error message.
   * </p>
   */
  public void saveUserDetails() {
    String filename = "studentData.ser";
    try {
      FileOutputStream file = new FileOutputStream(filename);
      ObjectOutputStream out = new ObjectOutputStream(file);
      out.writeObject(this);
      out.close();
      file.close();
      System.out.println("User Details have been saved.");
    } catch (Exception e) {
      System.err.println("IOException is caught");
    }
  }
}
