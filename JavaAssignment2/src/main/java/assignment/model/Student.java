package assignment.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Student implements Serializable {
  private static final long serialVersionUID = 1L;
  private String fullName;
  private int age;
  private String address;
  private int rollNumber;
  private Set<String> courses;

  /**
   * Constructs a new Student object with default values.
   * <p>
   * This constructor initializes a new Student instance with the following default values:
   * </p>
   * <ul>
   *   <li><b>fullName</b>: An empty string ("")</li>
   *   <li><b>age</b>: 0</li>
   *   <li><b>address</b>: An empty string ("")</li>
   *   <li><b>rollNumber</b>: 0</li>
   *   <li><b>courses</b>: An empty HashSet</li>
   * </ul>
   */
  public Student() {
    fullName = "";
    age = 0;
    address = "";
    rollNumber = 0;
    courses = new HashSet<>();
  }

  public String getFullName() {
    return fullName;
  }

  public int getAge() {
    return age;
  }

  public String getAddress() {
    return address;
  }

  public int getRollNumber() {
    return rollNumber;
  }

  public Student setFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public Set<String> getCourses() {
    return courses;
  }

  public Student setAge(int age) {
    this.age = age;
    return this;
  }

  public Student setAddress(String address) {
    this.address = address;
    return this;
  }

  public Student setRollNumber(int rollNumber) {
    this.rollNumber = rollNumber;
    return this;
  }

  public Student setCourses(Set<String> courses) {
    this.courses = courses;
    return this;
  }
}
