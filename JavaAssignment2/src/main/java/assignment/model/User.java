package assignment.model;

import assignment.comparator.StudentComparator;
import assignment.comparator.StudentComparatorByAddress;
import assignment.comparator.StudentComparatorByAge;
import assignment.comparator.StudentComparatorByRollNumber;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class User implements Serializable {

  private static final long serialVersionUID = 2L;
  private transient StudentComparator studentComparator;
  private Set<Student> users;
  private Map<Integer, Student> mapUserRollNumberToStudentObject;

  public User() {
    studentComparator = new StudentComparator();
    users = new TreeSet<>(studentComparator);
    mapUserRollNumberToStudentObject = new HashMap<>();
  }

  private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
    ois.defaultReadObject();
    studentComparator = new StudentComparator();
  }

  public void addUserDetails(Student student) {
    if (mapUserRollNumberToStudentObject.containsKey(student.getRollNumber())) {
      System.out.println("Student with same roll number already exist");
    } else {
      mapUserRollNumberToStudentObject.put(student.getRollNumber(), student);
      users.add(student);
    }
  }

  public Set<Student> displayUserDetails(String type) {
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
    return students;
  }

  public void deleteUserDetails(int rollNumber) {
    if (mapUserRollNumberToStudentObject.containsKey(rollNumber)) {
      Student student = mapUserRollNumberToStudentObject.get(rollNumber);
      users.remove(student);
      mapUserRollNumberToStudentObject.remove(rollNumber);
    } else {
      System.out.println("User doesn't exist");
    }
  }

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

  public int getUserSetSize(){
    return users.size();
  }
}
